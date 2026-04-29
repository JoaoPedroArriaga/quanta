package com.quanta.block.cable;

import com.mojang.serialization.MapCodec;
import com.quanta.blockentity.ModBlockEntities;
import com.quanta.blockentity.cable.QuantumCableBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class QuantumCableBlock extends BaseEntityBlock {
    
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    
    // Core: de 6 a 10 (0.375 a 0.625) - tamanho 0.25
    private static final VoxelShape CORE = box(6, 6, 6, 10, 10, 10);
    
    // Conectores: X/Y de 7 a 9 (0.4375 a 0.5625) - largura 0.125
    // Z: de 0 a 6 (north) ou 10 a 16 (south) - comprimento 0.375
    private static final VoxelShape NORTH_CONNECTOR = box(7, 7, 0, 9, 9, 6);
    private static final VoxelShape SOUTH_CONNECTOR = box(7, 7, 10, 9, 9, 16);
    private static final VoxelShape EAST_CONNECTOR = box(10, 7, 7, 16, 9, 9);
    private static final VoxelShape WEST_CONNECTOR = box(0, 7, 7, 6, 9, 9);
    private static final VoxelShape UP_CONNECTOR = box(7, 10, 7, 9, 16, 9);
    private static final VoxelShape DOWN_CONNECTOR = box(7, 0, 7, 9, 6, 9);
    
    // Cache de shapes combinadas
    private static final Map<Integer, VoxelShape> SHAPE_CACHE = new ConcurrentHashMap<>();
    
    public QuantumCableBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
            .setValue(NORTH, false).setValue(SOUTH, false)
            .setValue(EAST, false).setValue(WEST, false)
            .setValue(UP, false).setValue(DOWN, false));
    }
    
    // Helper para criar VoxelShape a partir de coordenadas em pixels (0-16)
    private static VoxelShape box(int x1, int y1, int z1, int x2, int y2, int z2) {
        return Block.box(x1, y1, z1, x2, y2, z2);
    }
    
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(QuantumCableBlock::new);
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }
    
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState();
    }
    
    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos,
                                    Block neighborBlock,
                                    BlockPos neighborPos, boolean movedByPiston) {
        if (!level.isClientSide) {
            CableNetworkManager.rebuildNetwork(level, pos);
            CableNetworkManager.rebuildNetwork(level, neighborPos);
            refreshCable(level, pos);
            refreshCable(level, neighborPos);
        }
    }
    
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide) {
            CableNetworkManager.registerCable(level, pos);
            refreshCable(level, pos);
        }
    }
    
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!level.isClientSide && !state.is(newState.getBlock())) {
            CableNetworkManager.removeCable(level, pos);
            for (Direction dir : Direction.values()) {
                refreshCable(level, pos.relative(dir));
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }
    
    private void refreshCable(Level level, BlockPos pos) {
        if (level.getBlockEntity(pos) instanceof QuantumCableBE be) {
            be.refreshConnections();
        }
    }
    
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.QUANTUM_CABLE.get(), 
            (lvl, pos, st, be) -> be.tick());
    }
    
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int key = 0;
        if (state.getValue(NORTH)) key |= 1;
        if (state.getValue(SOUTH)) key |= 2;
        if (state.getValue(EAST)) key |= 4;
        if (state.getValue(WEST)) key |= 8;
        if (state.getValue(UP)) key |= 16;
        if (state.getValue(DOWN)) key |= 32;
        
        return SHAPE_CACHE.computeIfAbsent(key, k -> {
            VoxelShape shape = CORE;
            if ((k & 1) != 0) shape = Shapes.or(shape, NORTH_CONNECTOR);
            if ((k & 2) != 0) shape = Shapes.or(shape, SOUTH_CONNECTOR);
            if ((k & 4) != 0) shape = Shapes.or(shape, EAST_CONNECTOR);
            if ((k & 8) != 0) shape = Shapes.or(shape, WEST_CONNECTOR);
            if ((k & 16) != 0) shape = Shapes.or(shape, UP_CONNECTOR);
            if ((k & 32) != 0) shape = Shapes.or(shape, DOWN_CONNECTOR);
            return shape;
        });
    }
    
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new QuantumCableBE(pos, state);
    }
    
    public static BooleanProperty getProperty(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
            case UP -> UP;
            case DOWN -> DOWN;
        };
    }
}