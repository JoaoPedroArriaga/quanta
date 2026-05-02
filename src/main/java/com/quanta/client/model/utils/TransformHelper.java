package com.quanta.client.model.utils;

import com.mojang.math.Transformation;
import net.minecraft.core.Direction;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class TransformHelper {

    private static final Vector3f CENTER = new Vector3f(0.5f, 0.5f, 0.5f);

    public static Transformation getRotationForDirection(Direction direction) {
        Quaternionf quat = new Quaternionf();
        switch (direction) {
            case DOWN  -> quat.rotationX((float) (Math.PI / 2));
            case UP    -> quat.rotationX((float) (-Math.PI / 2));
            case NORTH -> quat.rotationY(0);
            case SOUTH -> quat.rotationY((float) Math.PI);
            case WEST  -> quat.rotationY((float) (-Math.PI / 2));
            case EAST  -> quat.rotationY((float) (Math.PI / 2));
        }
        Transformation toCenter = new Transformation(CENTER, null, null, null);
        Transformation rot = new Transformation(null, quat, null, null);
        Transformation fromCenter = new Transformation(new Vector3f(-0.5f, -0.5f, -0.5f), null, null, null);
        return toCenter.compose(rot).compose(fromCenter);
    }
}