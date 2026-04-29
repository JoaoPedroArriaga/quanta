package com.quanta.datagen;

import com.quanta.Quanta;
import com.quanta.block.ModBlockItems;
import com.quanta.block.ModBlocks;
import com.quanta.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.ICondition;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }
    
    @Override
    protected void buildRecipes(RecipeOutput output) {
        // Quantum Dust
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.QUANTUM_DUST.get(), 1)
            .requires(ModItems.ENDER_DUST.get())
            .requires(Items.REDSTONE)
            .unlockedBy("has_ender_dust", has(ModItems.ENDER_DUST.get()))
            .save(output);
        
        // Quantum Steel
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.QUANTUM_STEEL.get())
            .pattern(" Q ")
            .pattern("QIQ")
            .pattern(" Q ")
            .define('Q', ModItems.QUANTUM_DUST.get())
            .define('I', Items.IRON_INGOT)
            .unlockedBy("has_quantum_dust", has(ModItems.QUANTUM_DUST.get()))
            .save(output);
        
        // Particle Reconstructor
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PARTICLE_RECONSTRUCTOR.get())
            .pattern("QSQ")
            .pattern("SMS")
            .pattern("QSQ")
            .define('Q', ModItems.QUANTUM_STEEL.get())
            .define('S', Items.SMOOTH_STONE)
            .define('M', Items.FURNACE)
            .unlockedBy("has_quantum_steel", has(ModItems.QUANTUM_STEEL.get()))
            .save(output);
        
        // Energy Quantifier
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENERGY_QUANTIFIER.get())
            .pattern("QCQ")
            .pattern("CFC")
            .pattern("QCQ")
            .define('Q', ModItems.QUANTUM_STEEL.get())
            .define('C', Items.COPPER_INGOT)
            .define('F', Items.FURNACE)
            .unlockedBy("has_quantum_steel", has(ModItems.QUANTUM_STEEL.get()))
            .save(output);
        
        // Quanta Engine
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.QUANTA_COLLAPSER.get())
            .pattern("QSQ")
            .pattern("SGS")
            .pattern("QSQ")
            .define('Q', ModItems.QUANTUM_STEEL.get())
            .define('S', Items.SMOOTH_STONE)
            .define('G', Items.GOLD_INGOT)
            .unlockedBy("has_quantum_steel", has(ModItems.QUANTUM_STEEL.get()))
            .save(output);
        
        // Quanta Cable
        /*ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlockItems.QUANTA_CABLE.get(), 8)
            .pattern("QQQ")
            .pattern("QRQ")
            .pattern("QQQ")
            .define('Q', ModItems.QUANTUM_STEEL.get())
            .define('R', Items.REDSTONE)
            .unlockedBy("has_quantum_steel", has(ModItems.QUANTUM_STEEL.get()))
            .save(output);*/
    }
}