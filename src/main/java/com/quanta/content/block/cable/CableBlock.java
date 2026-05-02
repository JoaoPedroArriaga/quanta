package com.quanta.content.block.cable;

import com.mojang.serialization.MapCodec;
import com.quanta.content.blockentity.ModBlockEntities;
import com.quanta.content.blockentity.cable.CableBlockEntity;
import com.quanta.core.transfer.ConnectionConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CableBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {

    public static final BooleanProperty DOWN  = BooleanProperty.create("down");
    public static final BooleanProperty UP    = BooleanProperty.create("up");
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST  = BooleanProperty.create("west");
    public static final BooleanProperty EAST  = BooleanProperty.create("east");
    public static final BooleanProperty WATERLOGGED = BooleanProperty.create("waterlogged");

    // Direction.ordinal(): DOWN=0, UP=1, NORTH=2, SOUTH=3, WEST=4, EAST=5
    public static final BooleanProperty[] DIR_PROPS = {DOWN, UP, NORTH, SOUTH, WEST, EAST};
    public static final Direction[] DIRECTIONS = Direction.values();

    private static final VoxelShape CORE = box(6, 6, 6, 10, 10, 10);
    private static final VoxelShape[] SHAPES = new VoxelShape[64];

    static {
        VoxelShape[] connectors = {
            box(7, 0, 7, 9, 8, 9),     // 0: DOWN
            box(7, 8, 7, 9, 16, 9),    // 1: UP
            box(7, 7, 0, 9, 9, 8),     // 2: NORTH
            box(7, 7, 8, 9, 9, 16),    // 3: SOUTH
            box(0, 7, 7, 8, 9, 9),     // 4: WEST
            box(8, 7, 7, 16, 9, 9)     // 5: EAST
        };
        for (int i = 0; i < 64; i++) {
            VoxelShape shape = CORE;
            for (int j = 0; j < 6; j++) {
                if ((i & (1 << j)) != 0) shape = Shapes.or(shape, connectors[j]);
            }
            SHAPES[i] = shape.optimize();
        }
    }

    public CableBlock(Properties properties) {
        super(properties);
        BlockState state = defaultBlockState();
        for (BooleanProperty prop : DIR_PROPS) state = state.setValue(prop, false);
        registerDefaultState(state.setValue(WATERLOGGED, false));
    }

    public static BooleanProperty getProperty(Direction dir) { return DIR_PROPS[dir.ordinal()]; }

    @Override protected MapCodec<? extends BaseEntityBlock> codec() { return simpleCodec(CableBlock::new); }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DOWN, UP, NORTH, SOUTH, WEST, EAST, WATERLOGGED);
    }

    @Nullable @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluid = ctx.getLevel().getFluidState(ctx.getClickedPos());
        return defaultBlockState().setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction dir, BlockState neighborState,
                                      LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        return state;
    }

    @Override protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        int key = 0;
        for (int i = 0; i < 6; i++) {
            if (state.getValue(DIR_PROPS[i])) {
                if (level.getBlockEntity(pos) instanceof CableBlockEntity be) {
                    Direction dir = DIRECTIONS[i];
                    if (be.getConfig(dir).getMode() != ConnectionConfig.Mode.DISABLED) {
                        key |= (1 << i);
                    }
                } else {
                    key |= (1 << i);
                }
            }
        }
        return SHAPES[key];
    }

    @Override public RenderShape getRenderShape(BlockState state) { return RenderShape.MODEL; }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
                                    BlockPos neighborPos, boolean movedByPiston) {
        if (!level.isClientSide) level.scheduleTick(pos, this, 2);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!level.isClientSide) {
            level.getBlockEntity(pos, ModBlockEntities.CABLE.get()).ifPresent(CableBlockEntity::updateConnections);
            for (Direction dir : DIRECTIONS) {
                BlockPos np = pos.relative(dir);
                level.getBlockEntity(np, ModBlockEntities.CABLE.get()).ifPresent(CableBlockEntity::scheduleConnectionUpdate);
            }
        }
    }

    @Override public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean moving) {
        super.onPlace(state, level, pos, oldState, moving);
        if (!level.isClientSide) level.scheduleTick(pos, this, 2);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
        if (!level.isClientSide && !state.is(newState.getBlock())) {
            level.getBlockEntity(pos, ModBlockEntities.CABLE.get()).ifPresent(be -> be.onRemoved());
            for (Direction dir : DIRECTIONS) {
                BlockPos np = pos.relative(dir);
                level.getBlockEntity(np, ModBlockEntities.CABLE.get()).ifPresent(CableBlockEntity::scheduleConnectionUpdate);
            }
        }
        super.onRemove(state, level, pos, newState, moving);
    }

    @Nullable @Override public BlockEntity newBlockEntity(BlockPos pos, BlockState state) { return new CableBlockEntity(pos, state); }

    @Nullable @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        return createTickerHelper(type, ModBlockEntities.CABLE.get(), (l, p, s, be) -> be.tick());
    }
}