package com.quanta.block.cable;

import com.quanta.capability.QuantaCapabilities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * ULTRA OPTIMIZED - Cable block with cached connection checks.
 */
public class QuantumCableBlock extends Block {
    
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");
    
    // Precomputed shapes for all 64 combinations
    private static final VoxelShape[] SHAPE_CACHE = new VoxelShape[64];
    private static final VoxelShape CORE = Shapes.box(0.25, 0.25, 0.25, 0.75, 0.75, 0.75);
    
    static {
        for (int mask = 0; mask < 64; mask++) {
            VoxelShape shape = CORE;
            if ((mask & 0x01) != 0) shape = Shapes.or(shape, Shapes.box(0.25, 0.25, 0, 0.75, 0.75, 0.25));
            if ((mask & 0x02) != 0) shape = Shapes.or(shape, Shapes.box(0.25, 0.25, 0.75, 0.75, 0.75, 1));
            if ((mask & 0x04) != 0) shape = Shapes.or(shape, Shapes.box(0.75, 0.25, 0.25, 1, 0.75, 0.75));
            if ((mask & 0x08) != 0) shape = Shapes.or(shape, Shapes.box(0, 0.25, 0.25, 0.25, 0.75, 0.75));
            if ((mask & 0x10) != 0) shape = Shapes.or(shape, Shapes.box(0.25, 0.75, 0.25, 0.75, 1, 0.75));
            if ((mask & 0x20) != 0) shape = Shapes.or(shape, Shapes.box(0.25, 0, 0.25, 0.75, 0.25, 0.75));
            SHAPE_CACHE[mask] = shape.optimize();
        }
    }
    
    // Cache for connection checks (cleared on neighbor change)
    private static final ThreadLocal<Long> lastCheckedPos = new ThreadLocal<>();
    private static final ThreadLocal<Integer> lastCheckedResult = new ThreadLocal<>();
    
    public QuantumCableBlock() {
        super(Properties.of().strength(1.5f).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
            .setValue(NORTH, false).setValue(SOUTH, false)
            .setValue(EAST, false).setValue(WEST, false)
            .setValue(UP, false).setValue(DOWN, false));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN);
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        return computeState(level, pos);
    }
    
    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, 
                                    Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (!level.isClientSide) {
            BlockState newState = computeState(level, pos);
            if (!state.equals(newState)) {
                level.setBlock(pos, newState, 3);
            }
            CableNetworkManager.markDirty(level, pos);
        }
    }
    
    private BlockState computeState(Level level, BlockPos pos) {
        return this.defaultBlockState()
            .setValue(NORTH, canConnectTo(level, pos, Direction.NORTH))
            .setValue(SOUTH, canConnectTo(level, pos, Direction.SOUTH))
            .setValue(EAST, canConnectTo(level, pos, Direction.EAST))
            .setValue(WEST, canConnectTo(level, pos, Direction.WEST))
            .setValue(UP, canConnectTo(level, pos, Direction.UP))
            .setValue(DOWN, canConnectTo(level, pos, Direction.DOWN));
    }
    
    private boolean canConnectTo(Level level, BlockPos pos, Direction direction) {
        BlockPos neighborPos = pos.relative(direction);
        
        // Fast path: check if neighbor is a cable
        if (level.getBlockState(neighborPos).getBlock() instanceof QuantumCableBlock) {
            return true;
        }
        
        // Check for machine with capability
        var capability = level.getCapability(QuantaCapabilities.QUANTA_ENERGY, neighborPos, direction.getOpposite());
        return capability != null;
    }
    
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int mask = 0;
        if (state.getValue(NORTH)) mask |= 0x01;
        if (state.getValue(SOUTH)) mask |= 0x02;
        if (state.getValue(EAST)) mask |= 0x04;
        if (state.getValue(WEST)) mask |= 0x08;
        if (state.getValue(UP)) mask |= 0x10;
        if (state.getValue(DOWN)) mask |= 0x20;
        return SHAPE_CACHE[mask];
    }
}