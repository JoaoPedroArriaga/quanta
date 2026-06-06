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
    private static final float PARTICLE_SCALE = 0.3f;
    private static final float CENTER = 0.35f;
    private static final float CONNECTOR_MID = 0.5f;
    private static final float ORBITAL_RADIUS = 0.2f;
    private static final float ORBITAL_SPEED = 3.0f;
    private static final float PULSE_SPEED = 4f;
    private static final float PULSE_MIN = 0.5f;
    private static final float PULSE_MAX = 0.7f;

    public PriorityParticleRenderer(BakedModel priorityModel) {
        this.priorityModel = priorityModel;
    }

    @Override
    public void render(ParticleState state, float orbitAngle, float floatOffset,
                       List<BakedQuad> outputQuads, RandomSource rand) {
        if (!state.hasPriorityParticles()) {
            return;
        }

        float pulse = (float) (Math.sin(orbitAngle * PULSE_SPEED) * (PULSE_MAX - PULSE_MIN) + (PULSE_MAX + PULSE_MIN) / 2f);
        var atlas = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);

        for (var entry : state.getPriorityParticles().entrySet()) {
            ParticleState.PriorityParticleData data = entry.getValue();
            int count = data.count();
            if (count == 0) {
                continue;
            }

            TextureAtlasSprite sprite = getPrioritySprite(atlas, data.priority());
            if (sprite == null) {
                continue;
            }

            float scale = PARTICLE_SCALE * pulse * data.sizeMultiplier();
            Direction face = data.face();
            float cx = CENTER + face.getStepX() * CONNECTOR_MID;
            float cy = CENTER + face.getStepY() * CONNECTOR_MID;
            float cz = CENTER + face.getStepZ() * CONNECTOR_MID;

            for (int i = 0; i < count; i++) {
                float angleOffset = (float) (i * Math.PI * 2 / count);
                float angle = orbitAngle * ORBITAL_SPEED + angleOffset;

                float ox, oy, oz;
                if (face.getAxis().isHorizontal()) {
                    ox = (float) Math.cos(angle) * ORBITAL_RADIUS * 0.4f;
                    oy = (float) Math.sin(angle) * ORBITAL_RADIUS;
                    oz = (float) Math.cos(angle) * ORBITAL_RADIUS * 0.4f;
                } else {
                    ox = (float) Math.cos(angle) * ORBITAL_RADIUS;
                    oy = (float) Math.cos(angle) * ORBITAL_RADIUS * 0.4f;
                    oz = (float) Math.sin(angle) * ORBITAL_RADIUS;
                }

                Vector3f pos = new Vector3f(cx + ox, cy + oy, cz + oz);
                Transformation t = new Transformation(pos, null,
                    new Vector3f(scale, scale, scale), null);
                IQuadTransformer tr = QuadTransformers.applying(t);
                
                for (BakedQuad q : priorityModel.getQuads(null, null, rand, ModelData.EMPTY, null)) {
                    BakedQuad transformed = tr.process(Collections.singletonList(q)).get(0);
                    outputQuads.add(QuadTransformerHelper.replaceTexture(transformed, sprite));
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
        if (texture == null) {
            return null;
        }
        return atlas.getSprite(ResourceLocation.fromNamespaceAndPath("quanta", "block/" + texture));
    }
}