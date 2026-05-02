package com.quanta.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.quanta.Quanta;
import com.quanta.content.item.cable.CableBlockItem;
import com.quanta.core.transfer.TransferType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.joml.Quaternionf;
import java.util.List;

public class CableItemRenderer extends BlockEntityWithoutLevelRenderer {

    private static CableItemRenderer instance;
    private BakedModel cableModel;

    private CableItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "block/cable_core");
        this.cableModel = Minecraft.getInstance().getModelManager().getModel(ModelResourceLocation.standalone(location));
    }

    public static CableItemRenderer getInstance() {
        if (instance == null) instance = new CableItemRenderer();
        return instance;
    }

    private TransferType getTypeFromItem(ItemStack stack) {
        if (stack.getItem() instanceof CableBlockItem cableItem) {
            return cableItem.getType();
        }
        return TransferType.QUANTA;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack,
                             MultiBufferSource buffer, int packedLight, int packedOverlay) {

        TransferType type = getTypeFromItem(stack);
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "block/cable_" + type.getSerializedName());

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.5f, 0.5f);

        switch (displayContext) {
            case FIRST_PERSON_LEFT_HAND, FIRST_PERSON_RIGHT_HAND -> {
                poseStack.translate(0.1f, -0.1f, 0.2f);
                poseStack.mulPose(new Quaternionf().rotationX(0.6f));
                poseStack.mulPose(new Quaternionf().rotationY(0.3f));
                poseStack.scale(0.45f, 0.45f, 0.45f);
            }
            case THIRD_PERSON_LEFT_HAND, THIRD_PERSON_RIGHT_HAND -> {
                poseStack.translate(0.0f, -0.1f, 0.0f);
                poseStack.mulPose(new Quaternionf().rotationX(0.3f));
                poseStack.mulPose(new Quaternionf().rotationY(0.2f));
                poseStack.scale(0.4f, 0.4f, 0.4f);
            }
            case GUI -> {
                poseStack.mulPose(new Quaternionf().rotationX(0.5f));
                poseStack.mulPose(new Quaternionf().rotationY(0.5f));
                poseStack.scale(0.5f, 0.5f, 0.5f);
            }
            case GROUND -> {
                poseStack.translate(0.0f, 0.2f, 0.0f);
                poseStack.scale(0.35f, 0.35f, 0.35f);
            }
            default -> poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        var sprite = Minecraft.getInstance().getModelManager()
            .getAtlas(net.minecraft.world.inventory.InventoryMenu.BLOCK_ATLAS).getSprite(texture);
        List<BakedQuad> quads = cableModel.getQuads(null, null, Minecraft.getInstance().level.random, ModelData.EMPTY, null);
        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));

        for (BakedQuad quad : quads) {
            int[] vertices = quad.getVertices().clone();
            BakedQuad newQuad = new BakedQuad(vertices, quad.getTintIndex(), quad.getDirection(), sprite, quad.isShade());
            consumer.putBulkData(poseStack.last(), newQuad, 1.0f, 1.0f, 1.0f, 1.0f, packedLight, packedOverlay);
        }

        poseStack.popPose();
    }
}