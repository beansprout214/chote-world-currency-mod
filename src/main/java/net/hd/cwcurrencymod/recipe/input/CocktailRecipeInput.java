package net.hd.cwcurrencymod.recipe.input;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.input.RecipeInput;

import java.util.List;

public record CocktailRecipeInput(List<ItemStack> stacks) implements RecipeInput {

    @Override
    public ItemStack getStackInSlot(int slot) {
        return stacks.get(slot);
    }

    @Override
    public int size() {
        return stacks.size();
    }
}