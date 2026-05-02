package com.quanta.client.model.utils;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.neoforged.neoforge.client.model.IQuadTransformer;

public class QuadTransformerHelper {

    public static BakedQuad replaceTexture(BakedQuad quad, TextureAtlasSprite newSprite) {
        int[] vertices = quad.getVertices().clone();
        TextureAtlasSprite oldSprite = quad.getSprite();

        float u0 = oldSprite.getU0();
        float v0 = oldSprite.getV0();
        float u1 = oldSprite.getU1();
        float v1 = oldSprite.getV1();
        float newU0 = newSprite.getU0();
        float newV0 = newSprite.getV0();
        float newU1 = newSprite.getU1();
        float newV1 = newSprite.getV1();
        float scaleU = (newU1 - newU0) / (u1 - u0);
        float scaleV = (newV1 - newV0) / (v1 - v0);

        for (int i = 0; i < 4; i++) {
            int idxU = IQuadTransformer.UV0 + i * IQuadTransformer.STRIDE;
            int idxV = idxU + 1;
            float oldU = Float.intBitsToFloat(vertices[idxU]);
            float oldV = Float.intBitsToFloat(vertices[idxV]);
            float newU = newU0 + (oldU - u0) * scaleU;
            float newV = newV0 + (oldV - v0) * scaleV;
            vertices[idxU] = Float.floatToRawIntBits(newU);
            vertices[idxV] = Float.floatToRawIntBits(newV);
        }

        return new BakedQuad(vertices, quad.getTintIndex(), quad.getDirection(), newSprite, quad.isShade());
    }
}