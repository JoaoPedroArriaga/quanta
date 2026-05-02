package com.quanta.client.model.particle;

import com.quanta.client.renderer.state.ParticleState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.RandomSource;
import java.util.ArrayList;
import java.util.List;

public class ParticleManager {
    private final List<IParticleRenderer> renderers = new ArrayList<>();
    public void register(IParticleRenderer renderer) { renderers.add(renderer); }
    public void renderAll(ParticleState state, float orbitAngle, float floatOffset,
                          List<BakedQuad> outputQuads, RandomSource rand) {
        for (IParticleRenderer r : renderers) r.render(state, orbitAngle, floatOffset, outputQuads, rand);
    }
}