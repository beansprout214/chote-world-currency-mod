package net.hd.cwcurrencymod.recipe;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeSerializers {

    public static final RecipeSerializer<?> COCKTAIL_MIXING = RecipeSerializer.SHAPELESS;

    public static void register() {
        ChoteWorldCurrencyMod.LOGGER.info("Registering Recipe Serializers for " + ChoteWorldCurrencyMod.MOD_ID);
    }
}