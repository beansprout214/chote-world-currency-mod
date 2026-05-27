package net.hd.cwcurrencymod.recipe;

import net.hd.cwcurrencymod.recipe.input.CocktailRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CocktailRecipe implements CraftingRecipe {

    final String group;
    final CraftingRecipeCategory category;
    final ItemStack result;
    final List<Ingredient> ingredients;

    public CocktailRecipe(String group, CraftingRecipeCategory category, ItemStack result, List<Ingredient> ingredients) {
        this.group = group;
        this.category = category;
        this.result = result;
        this.ingredients = ingredients;
    }

    @Override
    public RecipeSerializer<? extends CraftingRecipe> getSerializer() {
        return ModRecipeSerializers.COCKTAIL_MIXING;
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        return false;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        return null;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.NONE;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.MISC;
    }
}