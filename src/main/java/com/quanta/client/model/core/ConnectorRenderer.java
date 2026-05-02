package com.quanta.client.model.core;

import com.mojang.math.Transformation;
import com.quanta.client.model.utils.QuadTransformerHelper;
import com.quanta.client.model.utils.TextureHelper;
import com.quanta.client.model.utils.TransformHelper;
import com.quanta.client.renderer.state.CableRenderState;
import com.quanta.core.transfer.TransferType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.neoforged.neoforge.client.model.IQuadTransformer;
import net.neoforged.neoforge.client.model.QuadTransformers;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.List;

public class ConnectorRenderer implements ICableRenderer {

    private final BakedModel connectionModel;

    public ConnectorRenderer(BakedModel connectionModel) {
        this.connectionModel = connectionModel;
    }

    @Override
    public void render(CableRenderState state, List<BakedQuad> outputQuads, RandomSource rand) {
        TransferType baseType = state.getBaseType();
        var sprite = TextureHelper.getSpriteForType(baseType);

        for (Direction dir : Direction.values()) {
            if (!state.hasConnection(dir) || !state.isConnectionEnabled(dir)) continue;

            Vector3f pos = getConnectorPosition(dir);
            Transformation transform = new Transformation(pos, null, null, null)
                .compose(TransformHelper.getRotationForDirection(dir));
            IQuadTransformer transformer = QuadTransformers.applying(transform);

            for (BakedQuad quad : connectionModel.getQuads(null, null, rand, ModelData.EMPTY, null)) {
                BakedQuad transformed = transformer.process(Collections.singletonList(quad)).get(0);
                outputQuads.add(QuadTransformerHelper.replaceTexture(transformed, sprite));
            }
        }
    }

    private Vector3f getConnectorPosition(Direction dir) {
        float e = 0.5f;
        return switch (dir) {
            case NORTH -> new Vector3f(0, 0, 0);
            case SOUTH -> new Vector3f(0, 0, 0);
            case EAST  -> new Vector3f(e, 0, 0);
            case WEST  -> new Vector3f(-e, 0, 0);
            case UP    -> new Vector3f(0, e, 0);
            case DOWN  -> new Vector3f(0, -e, 0);
        };
    }
}