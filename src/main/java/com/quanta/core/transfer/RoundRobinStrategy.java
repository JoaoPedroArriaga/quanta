package com.quanta.core.transfer;

import net.minecraft.core.Direction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoundRobinStrategy implements TransferStrategy {
    private int counter = 0;

    @Override
    public List<Direction> orderTargets(CableContext context, TransferType type) {
        List<Direction> targets = new ArrayList<>(6);

        for (Direction dir : Direction.values()) {
            if (context.canTransfer(dir, type)) {
                targets.add(dir);
            }
        }

        if (targets.isEmpty()) return targets;

        counter = (counter + 1) % targets.size();
        if (counter > 0) {
            Collections.rotate(targets, counter);
        }

        return targets;
    }
}