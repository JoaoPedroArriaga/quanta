package com.quanta.content.blockentity.cable.component;

import com.quanta.capability.QuantaCapabilities;
import com.quanta.content.block.cable.CableBlock;
import com.quanta.content.blockentity.cable.CableBlockEntity;
import com.quanta.core.transfer.ConnectionConfig;
import com.quanta.core.transfer.TransferType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;

import java.util.*;

public class CableConnectionManager {

    private final CableBlockEntity be;
    private final CableTypeManager typeManager;
    private final Map<Direction, ConnectionConfig> configs = new EnumMap<>(Direction.class);
    private final int[] connectionMasks = new int[6];
    private boolean dirty = true;

    public CableConnectionManager(CableBlockEntity be, CableTypeManager typeManager) {
        this.be = be;
        this.typeManager = typeManager;
        for (Direction d : Direction.values()) {
            configs.put(d, new ConnectionConfig());
        }
    }

    private Level getLevel() {
        return be.getLevel();
    }

    private BlockPos getPos() {
        return be.getBlockPos();
    }

    public void updateConnections() {
        Level level = getLevel();
        if (level == null) {
            return;
        }
        BlockPos pos = getPos();

        int[] old = connectionMasks.clone();
        Arrays.fill(connectionMasks, 0);

        for (TransferType t : typeManager.getTypes()) {
            int bit = t.bitMask;
            for (Direction dir : Direction.values()) {
                if (!configs.get(dir).getMode().isEnabled()) {
                    continue;
                }
                BlockPos neighborPos = pos.relative(dir);
                var neighborBe = level.getBlockEntity(neighborPos);
                if (neighborBe instanceof CableBlockEntity neighborCable) {
                    if (neighborCable.getTypeManager().hasType(t)
                        && neighborCable.getConnectionManager().getConfig(dir.getOpposite()).getMode().isEnabled()) {
                        connectionMasks[dir.ordinal()] |= bit;
                    }
                } else if (canConnectExternal(dir, t)) {
                    connectionMasks[dir.ordinal()] |= bit;
                }
            }
        }

        if (!Arrays.equals(old, connectionMasks)) {
            updateBlockState();
        }
        dirty = false;
    }

    private boolean canConnectExternal(Direction dir, TransferType t) {
        Level level = getLevel();
        if (level == null) {
            return false;
        }
        if (level.getBlockEntity(getPos().relative(dir)) instanceof CableBlockEntity) {
            return false;
        }
        return hasExternalConnection(dir, t);
    }

    public boolean hasExternalConnection(Direction dir, TransferType type) {
        Level level = getLevel();
        if (level == null) {
            return false;
        }
        BlockPos neighborPos = getPos().relative(dir);
        
        // VERIFICAÇÃO CRÍTICA: não é conexão externa se for outro cabo
        if (level.getBlockEntity(neighborPos) instanceof CableBlockEntity) {
            return false;
        }
        
        Direction opposite = dir.getOpposite();
        return switch (type) {
            case QUANTA -> level.getCapability(
                QuantaCapabilities.QUANTA_ENERGY, neighborPos, opposite) != null;
            case ITEM -> level.getCapability(
                Capabilities.ItemHandler.BLOCK, neighborPos, opposite) != null;
            case FLUID -> level.getCapability(
                Capabilities.FluidHandler.BLOCK, neighborPos, opposite) != null;
            case GAS -> level.getCapability(
                QuantaCapabilities.QUANTA_GAS, neighborPos, opposite) != null;
        };
    }

    private void updateBlockState() {
        Level level = getLevel();
        if (level == null) {
            return;
        }
        BlockPos pos = getPos();
        BlockState state = be.getBlockState();
        for (int i = 0; i < 6; i++) {
            Direction dir = Direction.values()[i];
            state = state.setValue(CableBlock.DIR_PROPS[i],
                connectionMasks[i] != 0 && configs.get(dir).getMode().isEnabled());
        }
        if (state != be.getBlockState()) {
            level.setBlock(pos, state, 3);
        }
    }

    public int getConnectionMask(Direction d) {
        return connectionMasks[d.ordinal()];
    }

    public int[] getConnectionMasks() {
        return connectionMasks;
    }

    public void setConnectionMasks(int[] masks) {
        if (masks.length == 6) {
            System.arraycopy(masks, 0, this.connectionMasks, 0, 6);
        }
    }

    public boolean hasConnection(Direction d, TransferType t) {
        return (connectionMasks[d.ordinal()] & t.bitMask) != 0;
    }

    public ConnectionConfig getConfig(Direction d) {
        return configs.get(d);
    }

    public Map<Direction, ConnectionConfig> getConfigs() {
        return Collections.unmodifiableMap(configs);
    }

    public void setConfig(Direction d, ConnectionConfig c) {
        configs.put(d, c);
        dirty = true;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void markClean() {
        dirty = false;
    }

    public void markDirty() {
        dirty = true;
    }
}