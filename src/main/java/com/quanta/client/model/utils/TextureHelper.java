package com.quanta.client.model.utils;

import com.quanta.Quanta;
import com.quanta.core.transfer.TransferType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

public class TextureHelper {

    private static String getTextureName(TransferType type) {
        return switch (type) {
            case QUANTA -> "quanta_cable";
            case ITEM -> "quantum_item_pipe";
            case FLUID -> "quantum_liquid_pipe";
            case GAS -> "quantum_pressurized_pipe";
        };
    }

    public static TextureAtlasSprite getSpriteForType(TransferType type) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(
            Quanta.MOD_ID, "block/" + getTextureName(type));
        return Minecraft.getInstance().getModelManager()
            .getAtlas(InventoryMenu.BLOCK_ATLAS).getSprite(location);
    }

    public static TextureAtlasSprite getParticleSprite(TransferType type) {
        String textureName = switch (type) {
            case QUANTA -> "quanta_particle";
            case ITEM -> "quanta_particle_item";
            case FLUID -> "quanta_particle_fluid";
            case GAS -> "quanta_particle_gas";
        };
        
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "block/" + textureName);
        return Minecraft.getInstance().getModelManager()
            .getAtlas(InventoryMenu.BLOCK_ATLAS).getSprite(location);
    }
}