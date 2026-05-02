package com.quanta.client.model.particle;

import com.mojang.math.Transformation;
import com.quanta.client.model.utils.QuadTransformerHelper;
import com.quanta.client.renderer.state.ParticleState;
import com.quanta.core.transfer.Priority;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.neoforge.client.model.IQuadTransformer;
import net.neoforged.neoforge.client.model.QuadTransformers;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.List;

public class PriorityParticleRenderer implements IParticleRenderer {

    private final BakedModel priorityModel;
    private final float priorityScale;

    public PriorityParticleRenderer(BakedModel priorityModel, float priorityScale) {
        this.priorityModel = priorityModel;
        this.priorityScale = priorityScale;
    }

    public PriorityParticleRenderer(BakedModel priorityModel) { this(priorityModel, 0.15f); }

    @Override
    public void render(ParticleState state, float orbitAngle, float floatOffset,
                       List<BakedQuad> outputQuads, RandomSource rand) {
        if (!state.hasPriorityParticles()) return;

        float pulse = (float) (Math.sin(orbitAngle * 4) * 0.3 + 0.7);
        var atlas = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);

        for (var e : state.getPriorityParticles().entrySet()) {
            ParticleState.PriorityParticleData d = e.getValue();
            int count = d.count();
            if (count == 0) continue;

            TextureAtlasSprite sprite = getPrioritySprite(atlas, d.priority());
            if (sprite == null) continue;

            float scale = priorityScale * pulse * d.sizeMultiplier();
            Direction face = d.face();
            float cx = d.x(), cy = d.y(), cz = d.z();
            float radius = 0.2f;

            for (int i = 0; i < count; i++) {
                float angleOffset = (float) (i * Math.PI * 2 / count);
                float angle = orbitAngle * 2.5f + angleOffset;

                float ox, oy, oz;
                if (face.getAxis().isHorizontal()) {
                    ox = (float) Math.cos(angle) * radius * 0.5f;
                    oy = (float) Math.sin(angle) * radius;
                    oz = (float) Math.cos(angle) * radius * 0.5f;
                } else {
                    ox = (float) Math.cos(angle) * radius;
                    oy = (float) Math.cos(angle) * radius * 0.5f;
                    oz = (float) Math.sin(angle) * radius;
                }

                Vector3f pos = new Vector3f(cx + ox, cy + oy, cz + oz);
                Transformation t = new Transformation(pos, null, new Vector3f(scale, scale, scale), null);
                IQuadTransformer tr = QuadTransformers.applying(t);
                for (BakedQuad q : priorityModel.getQuads(null, null, rand, ModelData.EMPTY, null)) {
                    outputQuads.add(QuadTransformerHelper.replaceTexture(tr.process(Collections.singletonList(q)).get(0), sprite));
                }
            }
        }
    }

    private TextureAtlasSprite getPrioritySprite(net.minecraft.client.renderer.texture.TextureAtlas atlas, Priority priority) {
        String texture = switch (priority) {
            case LOW, LOWEST -> "quanta_particle_priority_low";
            case HIGH, HIGHEST, CRITICAL -> "quanta_particle_priority_high";
            default -> null;
        };
        if (texture == null) return null;
        return atlas.getSprite(ResourceLocation.fromNamespaceAndPath("quanta", "block/" + texture));
    }
}