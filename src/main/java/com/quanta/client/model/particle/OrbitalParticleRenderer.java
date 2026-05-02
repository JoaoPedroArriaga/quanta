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
    private final float particleScale;
    private final float centerOffset;

    public OrbitalParticleRenderer(BakedModel particleModel, float particleScale, float centerOffset) {
        this.particleModel = particleModel;
        this.particleScale = particleScale;
        this.centerOffset = centerOffset;
    }

    public OrbitalParticleRenderer(BakedModel particleModel) {
        this(particleModel, 0.35f, 0.35f);
    }

    @Override
    public void render(ParticleState state, float orbitAngle, float floatOffset,
                       List<BakedQuad> outputQuads, RandomSource rand) {
        if (!state.hasOrbitalParticles()) return;

        for (ParticleState.OrbitalParticleData p : state.getOrbitalParticles()) {
            float ang = orbitAngle * p.speed() + (float) Math.toRadians(p.startAngle());
            float r = p.radius();
            float rad = p.direction() > 0 ? ang : -ang;

            float x = centerOffset + (float) Math.cos(rad) * r;
            float z = centerOffset + (float) Math.sin(rad) * r;
            float y = centerOffset + (float) Math.sin(rad * 1.5f) * r * 0.5f + (float) Math.sin(floatOffset) * 0.03f;

            Transformation t = new Transformation(new Vector3f(x, y, z), null,
                new Vector3f(particleScale, particleScale, particleScale), null);
            IQuadTransformer tr = QuadTransformers.applying(t);
            var sprite = TextureHelper.getParticleSprite(p.type());

            for (BakedQuad q : particleModel.getQuads(null, null, rand, ModelData.EMPTY, null)) {
                BakedQuad transformed = tr.process(Collections.singletonList(q)).get(0);
                outputQuads.add(QuadTransformerHelper.replaceTexture(transformed, sprite));
            }
        }
    }
}