package com.quanta.client;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.quanta.Quanta;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.geometry.IGeometryBakingContext;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;
import net.neoforged.neoforge.client.model.geometry.IUnbakedGeometry;

import java.util.function.Function;

public class CableBundleGeometry implements IUnbakedGeometry<CableBundleGeometry> {

    @Override
    public BakedModel bake(IGeometryBakingContext context, ModelBaker baker,
                           Function<Material, TextureAtlasSprite> spriteGetter,
                           ModelState modelState, ItemOverrides overrides) {
        BakedModel core = baker.bake(ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "block/cable_core"), modelState);
        BakedModel connection = baker.bake(ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "block/cable_connection"), modelState);
        BakedModel particle = baker.bake(ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "entity/particle_orbit"), modelState);
        return new CableBundleModel(core, connection, particle, particle);
    }

    public static class Loader implements IGeometryLoader<CableBundleGeometry> {
        @Override
        public CableBundleGeometry read(JsonObject json, JsonDeserializationContext ctx) throws JsonParseException {
            return new CableBundleGeometry();
        }
    }
}