package com.quanta.core.transfer;

import net.minecraft.core.Direction;
import java.util.List;

@FunctionalInterface
public interface TransferStrategy {
    List<Direction> orderTargets(CableContext context, TransferType type);
}