package com.quanta.core.transfer;

import net.minecraft.core.Direction;
import java.util.ArrayList;
import java.util.List;

public class PriorityStrategy implements TransferStrategy {

    @Override
    public List<Direction> orderTargets(CableContext context, TransferType type) {
        List<Direction> targets = new ArrayList<>(6);

        for (Direction dir : Direction.values()) {
            if (context.canTransfer(dir, type)) {
                targets.add(dir);
            }
        }

        targets.sort((a, b) -> Integer.compare(
            context.getConfig(b).getPriority().level,
            context.getConfig(a).getPriority().level
        ));

        return targets;
    }
}