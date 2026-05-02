package com.quanta.client.model.particle;

import com.quanta.client.renderer.state.ParticleState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.util.RandomSource;
import java.util.List;

public interface IParticleRenderer {
    void render(ParticleState state, float orbitAngle, float floatOffset,
                List<BakedQuad> outputQuads, RandomSource rand);
}