package com.quanta.core.transfer;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import java.util.ArrayList;
import java.util.List;

public class NearestFirstStrategy implements TransferStrategy {
    @Override
    public List<Direction> orderTargets(CableContext context, TransferType type) {
        List<Direction> targets = new ArrayList<>(6);
        BlockPos source = context.getPos();

        for (Direction dir : Direction.values()) {
            if (context.canTransfer(dir, type)) {
                targets.add(dir);
            }
        }

        targets.sort((a, b) -> {
            BlockPos posA = source.relative(a);
            BlockPos posB = source.relative(b);
            return Double.compare(
                source.distSqr(posA),
                source.distSqr(posB)
            );
        });

        return targets;
    }
}
