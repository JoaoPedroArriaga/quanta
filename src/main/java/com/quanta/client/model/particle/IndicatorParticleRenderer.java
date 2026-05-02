package com.quanta.client.model.particle;

import com.mojang.math.Transformation;
import com.quanta.client.model.utils.QuadTransformerHelper;
import com.quanta.client.renderer.state.ParticleState;
import com.quanta.core.transfer.ConnectionConfig;
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

public class IndicatorParticleRenderer implements IParticleRenderer {

    private final BakedModel indicatorModel;
    private final float indicatorScale;

    public IndicatorParticleRenderer(BakedModel indicatorModel, float indicatorScale) {
        this.indicatorModel = indicatorModel;
        this.indicatorScale = indicatorScale;
    }

    public IndicatorParticleRenderer(BakedModel indicatorModel) {
        this(indicatorModel, 0.12f);
    }

    @Override
    public void render(ParticleState state, float orbitAngle, float floatOffset,
                       List<BakedQuad> outputQuads, RandomSource rand) {
        if (!state.hasTypeIndicatorParticles()) return;
        var atlas = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);

        for (var e : state.getTypeIndicatorParticles().entrySet()) {
            ParticleState.TypeIndicatorParticleData d = e.getValue();
            if (d.mode() == ConnectionConfig.Mode.DISABLED) continue;

            TextureAtlasSprite sprite = getModeSprite(atlas, d.mode(), d.whitelist());
            if (sprite == null) continue;

            Direction face = d.face();
            float cx = d.x(), cy = d.y(), cz = d.z();

            if (d.mode() == ConnectionConfig.Mode.INSERT || d.mode() == ConnectionConfig.Mode.EXTRACT) {
                renderHelical(d, cx, cy, cz, orbitAngle, floatOffset, sprite, outputQuads, rand);
            } else {
                renderOrbitalAroundConnector(face, cx, cy, cz, orbitAngle, sprite, outputQuads, rand);
            }
        }
    }

    private void renderHelical(ParticleState.TypeIndicatorParticleData d,
                                float cx, float cy, float cz,
                                float orbitAngle, float floatOffset,
                                TextureAtlasSprite sprite,
                                List<BakedQuad> outputQuads, RandomSource rand) {
        Direction face = d.face();
        boolean isInsert = d.mode() == ConnectionConfig.Mode.INSERT;
        float direction = isInsert ? 1f : -1f;
        float angle = orbitAngle * 3f;
        float radius = 0.15f;
        float t = ((floatOffset % 1f) + 1f) % 1f;
        if (!isInsert) t = 1f - t;

        Vector3f pos = getHelicalPosition(face, cx, cy, cz, angle, radius, t, direction);
        Transformation transform = new Transformation(pos, null,
            new Vector3f(indicatorScale, indicatorScale, indicatorScale), null);
        IQuadTransformer tr = QuadTransformers.applying(transform);
        for (BakedQuad q : indicatorModel.getQuads(null, null, rand, ModelData.EMPTY, null)) {
            outputQuads.add(QuadTransformerHelper.replaceTexture(tr.process(Collections.singletonList(q)).get(0), sprite));
        }
    }

    private Vector3f getHelicalPosition(Direction face, float cx, float cy, float cz,
                                         float angle, float radius, float t, float direction) {
        float hx = (float) Math.cos(angle) * radius;
        float hy = (float) Math.sin(angle) * radius;
        return switch (face) {
            case NORTH -> new Vector3f(cx + hx, cy + hy, cz - t * 0.5f);
            case SOUTH -> new Vector3f(cx + hx, cy + hy, cz + t * 0.5f);
            case EAST  -> new Vector3f(cx + t * 0.5f, cy + hy, cz + hx);
            case WEST  -> new Vector3f(cx - t * 0.5f, cy + hy, cz + hx);
            case UP    -> new Vector3f(cx + hx, cy + t * 0.5f, cz + hy);
            case DOWN  -> new Vector3f(cx + hx, cy - t * 0.5f, cz + hy);
        };
    }

    private void renderOrbitalAroundConnector(Direction face, float cx, float cy, float cz,
                                               float orbitAngle, TextureAtlasSprite sprite,
                                               List<BakedQuad> outputQuads, RandomSource rand) {
        float angle = orbitAngle * 2.5f;
        float radius = 0.18f;
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
        Transformation transform = new Transformation(pos, null,
            new Vector3f(indicatorScale, indicatorScale, indicatorScale), null);
        IQuadTransformer tr = QuadTransformers.applying(transform);
        for (BakedQuad q : indicatorModel.getQuads(null, null, rand, ModelData.EMPTY, null)) {
            outputQuads.add(QuadTransformerHelper.replaceTexture(tr.process(Collections.singletonList(q)).get(0), sprite));
        }
    }

    private TextureAtlasSprite getModeSprite(net.minecraft.client.renderer.texture.TextureAtlas atlas,
                                              ConnectionConfig.Mode mode, boolean whitelist) {
        String texture = switch (mode) {
            case INSERT -> whitelist ? "quanta_particle_whitelist" : "quanta_particle_insert";
            case EXTRACT -> whitelist ? "quanta_particle_blacklist" : "quanta_particle_extract";
            case DISABLED -> null;
        };
        if (texture == null) return null;
        return atlas.getSprite(ResourceLocation.fromNamespaceAndPath("quanta", "block/" + texture));
    }
}