package com.quanta.client;

import com.quanta.client.model.core.ConnectorRenderer;
import com.quanta.client.model.core.CoreRenderer;
import com.quanta.client.model.particle.*;
import com.quanta.client.model.utils.TextureHelper;
import com.quanta.client.renderer.state.CableRenderState;
import com.quanta.core.transfer.TransferType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.Nullable;
import java.util.*;

public class CableBundleModel implements IDynamicBakedModel {

    private static final ChunkRenderTypeSet CUTOUT_SET = ChunkRenderTypeSet.of(RenderType.cutout());
    private final CoreRenderer coreRenderer;
    private final ConnectorRenderer connectorRenderer;
    private final ParticleManager particleManager;

    public CableBundleModel(BakedModel core, BakedModel connection, BakedModel particle, BakedModel indicator) {
        this.coreRenderer = new CoreRenderer(core);
        this.connectorRenderer = new ConnectorRenderer(connection);
        this.particleManager = new ParticleManager();
        this.particleManager.register(new OrbitalParticleRenderer(particle));
        this.particleManager.register(new IndicatorParticleRenderer(indicator));
        this.particleManager.register(new PriorityParticleRenderer(indicator));
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side,
                                    RandomSource rand, ModelData extraData, @Nullable RenderType renderType) {
        CableRenderState renderState = extraData.get(CableRenderState.PROPERTY);
        if (renderState == null || renderState.getPresentCables().isEmpty()) return Collections.emptyList();

        renderState.updateAngles();
        List<BakedQuad> allQuads = new ArrayList<>();
        coreRenderer.render(renderState, allQuads, rand);
        connectorRenderer.render(renderState, allQuads, rand);
        if (renderState.hasParticles()) {
            particleManager.renderAll(renderState.getParticleState(),
                renderState.getOrbitAngle(), renderState.getFloatOffset(), allQuads, rand);
        }
        return allQuads;
    }

    @Override public boolean useAmbientOcclusion() { return true; }
    @Override public boolean isGui3d() { return false; }
    @Override public boolean usesBlockLight() { return false; }
    @Override public boolean isCustomRenderer() { return false; }
    @Override public TextureAtlasSprite getParticleIcon() { return TextureHelper.getSpriteForType(TransferType.QUANTA); }
    @Override public ItemOverrides getOverrides() { return ItemOverrides.EMPTY; }
    @Override public ChunkRenderTypeSet getRenderTypes(BlockState s, RandomSource r, ModelData d) { return CUTOUT_SET; }
}