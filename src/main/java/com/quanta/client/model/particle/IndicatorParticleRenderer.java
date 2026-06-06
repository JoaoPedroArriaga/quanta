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
    private static final float PARTICLE_SCALE = 0.25f;
    private static final float CENTER = 0.4f;
    private static final float CONNECTOR_START = 0.3f;
    private static final float CONNECTOR_END = 0.5f;
    private static final float PERP_AMPLITUDE = 0.15f;
    private static final float PERP_FREQUENCY = 4f;
    private static final float FLOW_SPEED = 0.6f;

    public IndicatorParticleRenderer(BakedModel indicatorModel) {
        this.indicatorModel = indicatorModel;
    }

    @Override
    public void render(ParticleState state, float orbitAngle, float floatOffset,
                       List<BakedQuad> outputQuads, RandomSource rand) {
        if (!state.hasTypeIndicatorParticles()) {
            return;
        }

        var atlas = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);

        for (var entry : state.getTypeIndicatorParticles().entrySet()) {
            ParticleState.TypeIndicatorParticleData data = entry.getValue();
            if (data.mode() == ConnectionConfig.Mode.DISABLED) {
                continue;
            }

            TextureAtlasSprite sprite = getModeSprite(atlas, data.mode(), data.whitelist());
            if (sprite == null) {
                continue;
            }

            Direction face = data.face();
            boolean isInsert = data.mode() == ConnectionConfig.Mode.INSERT;
            renderFlowParticle(face, isInsert, orbitAngle, floatOffset, sprite, outputQuads, rand);
        }
    }

    private void renderFlowParticle(Direction face, boolean isInsert,
                                     float orbitAngle, float floatOffset,
                                     TextureAtlasSprite sprite,
                                     List<BakedQuad> outputQuads, RandomSource rand) {
        float t = ((floatOffset * FLOW_SPEED) % 1f + 1f) % 1f;
        
        if (!isInsert) {
            t = 1f - t;
        }
        
        float range = CONNECTOR_END - CONNECTOR_START;
        float dist = CONNECTOR_START + t * range;
        
        float perp1 = (float) Math.sin(orbitAngle * PERP_FREQUENCY) * PERP_AMPLITUDE;
        float perp2 = (float) Math.cos(orbitAngle * PERP_FREQUENCY) * PERP_AMPLITUDE;
        
        float x = CENTER;
        float y = CENTER;
        float z = CENTER;
        
        switch (face) {
            case WEST  -> { x = CENTER - dist; y = CENTER + perp1; z = CENTER + perp2; }
            case EAST  -> { x = CENTER + dist; y = CENTER + perp1; z = CENTER + perp2; }
            case DOWN  -> { y = CENTER - dist; x = CENTER + perp1; z = CENTER + perp2; }
            case UP    -> { y = CENTER + dist; x = CENTER + perp1; z = CENTER + perp2; }
            case NORTH -> { z = CENTER - dist; x = CENTER + perp1; y = CENTER + perp2; }
            case SOUTH -> { z = CENTER + dist; x = CENTER + perp1; y = CENTER + perp2; }
        }
        
        Vector3f pos = new Vector3f(x, y, z);
        Transformation transform = new Transformation(pos, null,
            new Vector3f(PARTICLE_SCALE, PARTICLE_SCALE, PARTICLE_SCALE), null);
        IQuadTransformer tr = QuadTransformers.applying(transform);
        
        for (BakedQuad q : indicatorModel.getQuads(null, null, rand, ModelData.EMPTY, null)) {
            BakedQuad transformed = tr.process(Collections.singletonList(q)).get(0);
            outputQuads.add(QuadTransformerHelper.replaceTexture(transformed, sprite));
        }
    }

    private TextureAtlasSprite getModeSprite(net.minecraft.client.renderer.texture.TextureAtlas atlas,
                                              ConnectionConfig.Mode mode, boolean whitelist) {
        String texture = switch (mode) {
            case INSERT -> whitelist ? "quanta_particle_whitelist" : "quanta_particle_insert";
            case EXTRACT -> whitelist ? "quanta_particle_blacklist" : "quanta_particle_extract";
            case DISABLED -> null;
        };
        if (texture == null) {
            return null;
        }
        return atlas.getSprite(ResourceLocation.fromNamespaceAndPath("quanta", "block/" + texture));
    }
}