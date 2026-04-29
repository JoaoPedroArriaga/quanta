package com.quanta.client.model;

import com.mojang.math.Transformation;
import com.quanta.Quanta;
import com.quanta.block.cable.CableType;
import com.quanta.client.renderer.state.CableRenderState;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.IDynamicBakedModel;
import net.neoforged.neoforge.client.model.IQuadTransformer;
import net.neoforged.neoforge.client.model.QuadTransformers;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.Collections;
import java.util.List;

public class CableBundleModel implements IDynamicBakedModel {
    private static final ChunkRenderTypeSet CUTOUT_SET = ChunkRenderTypeSet.of(RenderType.cutout());
    private static final float PARTICLE_SCALE = 0.40f;
    private static final float CORE_CENTER = 0.35f;

    private final BakedModel coreModel;
    private final BakedModel connectionModel;
    private final BakedModel particleModel;
    
    private final Object2ObjectOpenHashMap<CableType, TextureAtlasSprite> spriteCache = new Object2ObjectOpenHashMap<>();
    private final Object2ObjectOpenHashMap<CableType, TextureAtlasSprite> particleSpriteCache = new Object2ObjectOpenHashMap<>();
    
    private final ThreadLocal<ObjectList<BakedQuad>> quadsCache = ThreadLocal.withInitial(ObjectArrayList::new);

    public CableBundleModel(BakedModel core, BakedModel connection, BakedModel particle) {
        this.coreModel = core;
        this.connectionModel = connection;
        this.particleModel = particle;
    }

    private TextureAtlasSprite getSpriteForType(CableType type) {
        TextureAtlasSprite sprite = spriteCache.get(type);
        if (sprite == null) {
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "block/" + type.textureName);
            sprite = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS).getSprite(location);
            spriteCache.put(type, sprite);
        }
        return sprite;
    }
    
    private TextureAtlasSprite getParticleSprite(CableType type) {
        TextureAtlasSprite sprite = particleSpriteCache.get(type);
        if (sprite == null) {
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(Quanta.MOD_ID, "block/quanta_particle_" + type.name().toLowerCase());
            sprite = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS).getSprite(location);
            particleSpriteCache.put(type, sprite);
        }
        return sprite;
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side,
                                    RandomSource rand, ModelData extraData, @Nullable RenderType renderType) {
        CableRenderState renderState = extraData.get(CableRenderState.PROPERTY);
        if (renderState == null || renderState.getPresentCables().isEmpty()) {
            return Collections.emptyList();
        }

        ObjectList<BakedQuad> allQuads = quadsCache.get();
        allQuads.clear();
        
        CableType baseType = renderState.getBaseType();
        
        // ===== 1. CORE =====
        if (renderState.shouldShowCore()) {
            List<BakedQuad> coreQuads = coreModel.getQuads(null, null, rand, ModelData.EMPTY, renderType);
            TextureAtlasSprite baseSprite = getSpriteForType(baseType);
            
            for (BakedQuad quad : coreQuads) {
                allQuads.add(replaceTexture(quad, baseSprite));
            }
        }
        
        // ===== 2. CONECTORES =====
        for (Direction dir : Direction.values()) {
            if (!renderState.hasConnection(dir)) continue;
            
            Vector3f connectorPos = getConnectionOffset(dir);
            Transformation translation = new Transformation(connectorPos, null, null, null);
            Transformation rotation = getRotationForDirection(dir);
            Transformation finalTransform = translation.compose(rotation);
            
            List<BakedQuad> baseQuads = connectionModel.getQuads(null, null, rand, ModelData.EMPTY, renderType);
            if (baseQuads.isEmpty()) continue;
            
            IQuadTransformer connectorTransformer = QuadTransformers.applying(finalTransform);
            TextureAtlasSprite sprite = getSpriteForType(baseType);
            
            for (BakedQuad quad : baseQuads) {
                BakedQuad transformed = connectorTransformer.process(Collections.singletonList(quad)).get(0);
                allQuads.add(replaceTexture(transformed, sprite));
            }
        }
        
        // ===== 3. PARTÍCULAS ORBITAIS =====
        if (renderState.hasParticles()) {
            float orbitAngle = renderState.getOrbitAngle();
            float floatOffset = renderState.getFloatOffset();
            List<CableType> particles = renderState.getParticles();
            
            for (int i = 0; i < particles.size(); i++) {
                CableType particleType = particles.get(i);
                CableRenderState.ParticleConfig config = renderState.getParticleConfig(particleType);
                
                float angle = orbitAngle * config.speed();
                float startRad = (float)Math.toRadians(config.startAngle());
                float rad = angle + startRad;
                float finalRad = config.direction() > 0 ? rad : -rad;
                
                float radius = config.radius();
                float x = CORE_CENTER + (float) Math.cos(finalRad) * radius;
                float z = CORE_CENTER + (float) Math.sin(finalRad) * radius;
                float y = CORE_CENTER + (float) Math.sin(finalRad * config.verticalFreq()) * (radius * 0.3f) + (float) Math.sin(floatOffset) * 0.03f;
                
                Transformation particleTransform = new Transformation(
                    new Vector3f(x, y, z), 
                    null, 
                    new Vector3f(PARTICLE_SCALE, PARTICLE_SCALE, PARTICLE_SCALE), 
                    null
                );
                
                List<BakedQuad> particleQuads = particleModel.getQuads(null, null, rand, ModelData.EMPTY, renderType);
                if (particleQuads.isEmpty()) continue;
                
                IQuadTransformer particleTransformer = QuadTransformers.applying(particleTransform);
                TextureAtlasSprite particleSprite = getParticleSprite(particleType);
                
                for (BakedQuad quad : particleQuads) {
                    BakedQuad transformed = particleTransformer.process(Collections.singletonList(quad)).get(0);
                    allQuads.add(replaceTexture(transformed, particleSprite));
                }
            }
        }
        
        return allQuads;
    }
    
    private BakedQuad replaceTexture(BakedQuad quad, TextureAtlasSprite newSprite) {
        int[] vertices = quad.getVertices().clone();
        TextureAtlasSprite oldSprite = quad.getSprite();
        
        float u0 = oldSprite.getU0(), v0 = oldSprite.getV0();
        float u1 = oldSprite.getU1(), v1 = oldSprite.getV1();
        float newU0 = newSprite.getU0(), newV0 = newSprite.getV0();
        float newU1 = newSprite.getU1(), newV1 = newSprite.getV1();
        float scaleU = (newU1 - newU0) / (u1 - u0);
        float scaleV = (newV1 - newV0) / (v1 - v0);
        
        for (int i = 0; i < 4; i++) {
            int idxU = IQuadTransformer.UV0 + i * IQuadTransformer.STRIDE;
            int idxV = idxU + 1;
            float oldU = Float.intBitsToFloat(vertices[idxU]);
            float oldV = Float.intBitsToFloat(vertices[idxV]);
            float newU = newU0 + (oldU - u0) * scaleU;
            float newV = newV0 + (oldV - v0) * scaleV;
            vertices[idxU] = Float.floatToRawIntBits(newU);
            vertices[idxV] = Float.floatToRawIntBits(newV);
        }
        
        return new BakedQuad(vertices, quad.getTintIndex(), quad.getDirection(), newSprite, quad.isShade());
    }
    
    private Vector3f getConnectionOffset(Direction dir) {
        float extension = 0.5f;
        return switch (dir) {
            case NORTH -> new Vector3f(0, 0, -extension);
            case SOUTH -> new Vector3f(0, 0, extension);
            case EAST -> new Vector3f(extension, 0, 0);
            case WEST -> new Vector3f(-extension, 0, 0);
            case UP -> new Vector3f(0, extension, 0);
            case DOWN -> new Vector3f(0, -extension, 0);
        };
    }
    
    private Transformation getRotationForDirection(Direction direction) {
        Quaternionf quat = new Quaternionf();
        Vector3f center = new Vector3f(0.5f, 0.5f, 0.5f);
        
        switch (direction) {
            case NORTH: quat.rotationY((float) Math.PI); break;
            case SOUTH: quat.rotationY(0); break;
            case EAST:  quat.rotationY((float) (Math.PI / 2)); break;
            case WEST:  quat.rotationY((float) (-Math.PI / 2)); break;
            case UP:    quat.rotationX((float) (-Math.PI / 2)); break;
            case DOWN:  quat.rotationX((float) (Math.PI / 2)); break;
        }
        
        Transformation toCenter = new Transformation(center, null, null, null);
        Transformation rot = new Transformation(null, quat, null, null);
        Transformation fromCenter = new Transformation(new Vector3f(-0.5f, -0.5f, -0.5f), null, null, null);
        return toCenter.compose(rot).compose(fromCenter);
    }
    
    @Override
    public boolean useAmbientOcclusion() { return true; }
    @Override
    public boolean isGui3d() { return false; }
    @Override
    public boolean usesBlockLight() { return false; }
    @Override
    public boolean isCustomRenderer() { return false; }
    
    @Override
    public TextureAtlasSprite getParticleIcon() {
        return getSpriteForType(CableType.ENERGY);
    }
    
    @Override
    public ItemOverrides getOverrides() { return ItemOverrides.EMPTY; }
    
    @Override
    public ChunkRenderTypeSet getRenderTypes(BlockState state, RandomSource rand, ModelData data) {
        return CUTOUT_SET;
    }
}