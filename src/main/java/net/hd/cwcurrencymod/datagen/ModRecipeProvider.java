package net.hd.cwcurrencymod.datagen;


import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.block.ModBlocks;
import net.hd.cwcurrencymod.item.ModItems;
import net.hd.cwcurrencymod.item.custom.CocktailItem;
import net.hd.cwcurrencymod.util.constants.CocktailTypes;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                // COINS -> COIN BLOCK
                this.createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHOTE_COIN_BLOCK)
                        .pattern("XXX")
                        .pattern("X X")
                        .pattern("XXX")
                        .input('X', ModItems.CHOTE_COIN)
                        .criterion(hasItem(ModItems.CHOTE_COIN), conditionsFromItem(ModItems.CHOTE_COIN))
                        .offerTo(this.exporter);

                // CENTS -> CENT BLOCK
                this.createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHOITE_CENT_BLOCK)
                        .pattern("XXX")
                        .pattern("X X")
                        .pattern("XXX")
                        .input('X', ModItems.CHOITE_CENT)
                        .criterion(hasItem(ModItems.CHOITE_CENT), conditionsFromItem(ModItems.CHOITE_CENT))
                        .offerTo(this.exporter);

                // CENT BLOCK -> COIN
                this.createShaped(RecipeCategory.MISC, ModItems.CHOTE_COIN)
                        .pattern("XXX")
                        .pattern("X X")
                        .pattern("XXX")
                        .input('X', ModBlocks.CHOITE_CENT_BLOCK)
                        .criterion(hasItem(ModBlocks.CHOITE_CENT_BLOCK), conditionsFromItem(ModBlocks.CHOITE_CENT_BLOCK))
                        .offerTo(this.exporter, RegistryKey.of(RegistryKeys.RECIPE, Identifier.of(ChoteWorldCurrencyMod.MOD_ID, "chote_coin_from_choite_cent_block")));

                // COIN -> CENT BLOCK
                offerShapelessRecipe(ModBlocks.CHOITE_CENT_BLOCK,ModItems.CHOTE_COIN,RecipeCategory.MISC.getName(),8);

                // COIN BLOCK -> COINS
                offerShapelessRecipe(ModItems.CHOTE_COIN,ModBlocks.CHOTE_COIN_BLOCK,RecipeCategory.MISC.getName(),8);

                // CENT BLOCK -> CENTS
                offerShapelessRecipe(ModItems.CHOITE_CENT,ModBlocks.CHOITE_CENT_BLOCK,RecipeCategory.MISC.getName(),8);

                this.createShapeless(RecipeCategory.MISC,ModItems.GEL_CAPSULE,1)
                        .input(Items.HONEYCOMB)
                        .input(Items.SLIME_BALL)
                        .input(Items.WATER_BUCKET)
                        .criterion(hasItem(Items.HONEYCOMB), conditionsFromItem(Items.HONEYCOMB))
                        .criterion(hasItem(Items.SLIME_BALL), conditionsFromItem(Items.SLIME_BALL))
                        .criterion(hasItem(Items.WATER_BUCKET), conditionsFromItem(Items.WATER_BUCKET))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.MISC,ModItems.ADDERALL_PILL,1)
                        .input(ModItems.GEL_CAPSULE)
                        .input(ModItems.STIMULANT)
                        .criterion(hasItem(ModItems.GEL_CAPSULE), conditionsFromItem(ModItems.GEL_CAPSULE))
                        .criterion(hasItem(ModItems.STIMULANT), conditionsFromItem(ModItems.STIMULANT))
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.MISC, ModItems.COCKTAIL_GLASS)
                        .pattern("X X")
                        .pattern(" X ")
                        .pattern(" X ")
                        .input('X',Items.GLASS)
                        .criterion(hasItem(Items.GLASS), conditionsFromItem(Items.GLASS))
                        .offerTo(this.exporter);

                List<ItemConvertible> STIMULANT_SMELTABLES = List.of(ModItems.RAW_STIMULANT);

                this.offerSmelting(STIMULANT_SMELTABLES, RecipeCategory.MISC, ModItems.STIMULANT, 0.25f, 200, "stimulant");

            }
        };
    }

    @Override
    public String getName() {
        return "Chote World Currency Mod Recipes";
    }
}