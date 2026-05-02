package com.quanta.client.model.core;

import com.quanta.client.renderer.state.CableRenderState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.RandomSource;
import java.util.List;

public interface ICableRenderer {
    void render(CableRenderState state, List<BakedQuad> outputQuads, RandomSource rand);
}