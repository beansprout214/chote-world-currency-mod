package net.hd.cwcurrencymod.recipe;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeTypes {

    public static final RecipeType<CocktailRecipeDeprecated> COCKTAIL_MIXING = register("cocktail_mixing");

    private static <T extends Recipe<?>> RecipeType<T> register(String id) {
        return Registry.register(Registries.RECIPE_TYPE, Identifier.of(ChoteWorldCurrencyMod.MOD_ID,id), new RecipeType<T>() {
            public String toString() {
                return id;
            }
        });
    }

    public static void register() {
        ChoteWorldCurrencyMod.LOGGER.info("Registering Recipe Types for " + ChoteWorldCurrencyMod.MOD_ID);
    }
}