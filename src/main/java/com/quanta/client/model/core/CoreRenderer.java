package com.quanta.client.model.core;

import com.quanta.client.model.utils.QuadTransformerHelper;
import com.quanta.client.model.utils.TextureHelper;
import com.quanta.client.renderer.state.CableRenderState;
import com.quanta.core.transfer.TransferType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.util.RandomSource;
import net.neoforged.neoforge.client.model.data.ModelData;

import java.util.List;

public class CoreRenderer implements ICableRenderer {

    private final BakedModel coreModel;

    public CoreRenderer(BakedModel coreModel) {
        this.coreModel = coreModel;
    }

    @Override
    public void render(CableRenderState state, List<BakedQuad> outputQuads, RandomSource rand) {
        if (!state.shouldShowCore()) return;
        TransferType baseType = state.getBaseType();
        var sprite = TextureHelper.getSpriteForType(baseType);
        for (BakedQuad quad : coreModel.getQuads(null, null, rand, ModelData.EMPTY, null)) {
            outputQuads.add(QuadTransformerHelper.replaceTexture(quad, sprite));
        }
    }
}