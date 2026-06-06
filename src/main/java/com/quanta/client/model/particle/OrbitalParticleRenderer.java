package com.quanta.client.model.particle;

import com.mojang.math.Transformation;
import com.quanta.client.model.utils.QuadTransformerHelper;
import com.quanta.client.model.utils.TextureHelper;
import com.quanta.client.renderer.state.ParticleState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.util.RandomSource;
import net.neoforged.neoforge.client.model.IQuadTransformer;
import net.neoforged.neoforge.client.model.QuadTransformers;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.List;

public class OrbitalParticleRenderer implements IParticleRenderer {

    private final BakedModel particleModel;
    private static final float PARTICLE_SCALE = 0.35f;
    private static final float CENTER = 0.35f;
    private static final float FLOAT_AMPLITUDE = 0.03f;

    public OrbitalParticleRenderer(BakedModel particleModel) {
        this.particleModel = particleModel;
    }

    @Override
    public void render(ParticleState state, float orbitAngle, float floatOffset,
                       List<BakedQuad> outputQuads, RandomSource rand) {
        if (!state.hasOrbitalParticles()) {
            return;
        }

        for (ParticleState.OrbitalParticleData p : state.getOrbitalParticles()) {
            float ang = orbitAngle * p.speed() + (float) Math.toRadians(p.startAngle());
            float r = p.radius();
            float rad = p.direction() > 0 ? ang : -ang;

            float x = CENTER + (float) Math.cos(rad) * r;
            float z = CENTER + (float) Math.sin(rad) * r;
            float y = CENTER + (float) Math.sin(rad * 1.5f) * r * 0.5f + (float) Math.sin(floatOffset) * FLOAT_AMPLITUDE;

            Transformation t = new Transformation(new Vector3f(x, y, z), null,
                new Vector3f(PARTICLE_SCALE, PARTICLE_SCALE, PARTICLE_SCALE), null);
            IQuadTransformer tr = QuadTransformers.applying(t);
            var sprite = TextureHelper.getParticleSprite(p.type());

            for (BakedQuad q : particleModel.getQuads(null, null, rand, ModelData.EMPTY, null)) {
                BakedQuad transformed = tr.process(Collections.singletonList(q)).get(0);
                outputQuads.add(QuadTransformerHelper.replaceTexture(transformed, sprite));
            }
        }
    }
}