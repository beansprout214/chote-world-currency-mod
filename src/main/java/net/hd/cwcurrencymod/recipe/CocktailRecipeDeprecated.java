package net.hd.cwcurrencymod.recipe;

import net.hd.cwcurrencymod.recipe.input.CocktailRecipeInput;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class CocktailRecipeDeprecated {

//    // the list of ingredients needed for this specific recipe
//    // not static, because each unique recipe instance has its own ingredients
//    final List<Ingredient> ingredients;
//
//    // similarly, each unique recipe instance also has its own result
//    final ItemStack result;
//
//
//    public CocktailRecipeDeprecated(List<Ingredient> ingredients, ItemStack result) {
//        this.ingredients = ingredients;
//        this.result = result;
//    }
//
//    // checks to see if ingredient matches recipe
//    @Override
//    public boolean matches(CocktailRecipeInput input, World world) {
//        // sets all needed ingredients as "remaining" items, which are still needed
//        List<Ingredient> remaining = new ArrayList<>(ingredients);
//
//        // iterates through all slots of input
//        for (int i = 0; i < input.size(); i++) {
//            // get the ItemStack inside of slot i
//            ItemStack stack = input.getStackInSlot(i);
//            // if nothing is there, move on
//            if (stack.isEmpty()) continue;
//
//            // default to NOT matching
//            boolean matched = false;
//            // iterate throughout the remaining ingredients to find one that matches
//            for (int j = 0; j < remaining.size(); j++) {
//                // fetches the j item of the remaining list
//                // tests to see if the itemstack matches the ingredient fetched
//                // if it does, the ingredient matches and can immediately move on
//                if (remaining.get(j).test(stack)) {
//                    remaining.remove(j);
//                    matched = true;
//                    break;
//                }
//                // if code down here is ran, that means the if statement was never true
//                // this means the itemstack didn't match any of the remaining ingredients
//            }
//            // if any of the ingredients dont match, return false
//            if (!matched) return false; // input has something the recipe doesn't want
//        }
//
//        // if all of the remaining ingredients have been located, this will be empty, meaning the ingredients match
//        // if any ingredients still remain and the method hasnt returned yet, the recipe is not fully completed
//        return remaining.isEmpty(); // all recipe ingredients were satisfied
//    }
//
//    @Override
//    public ItemStack craft(CocktailRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
//        return result.copy();
//    }
//
//    @Override
//    public boolean isIgnoredInRecipeBook() {
//        return Recipe.super.isIgnoredInRecipeBook();
//    }
//
//    @Override
//    public boolean showNotification() {
//        return Recipe.super.showNotification();
//    }
//
//    @Override
//    public String getGroup() {
//        return Recipe.super.getGroup();
//    }
//
//    @Override
//    public RecipeSerializer<? extends CocktailRecipeDeprecated> getSerializer() {
//        return ModRecipeSerializers.COCKTAIL_MIXING;
//    }
//
//    @Override
//    public RecipeType<CocktailRecipeDeprecated> getType() {
//        return ModRecipeTypes.COCKTAIL_MIXING;
//    }
//
//    @Override
//    public IngredientPlacement getIngredientPlacement() {
//        return IngredientPlacement.NONE;
//    }
//
//    @Override
//    public List<RecipeDisplay> getDisplays() {
//        return Recipe.super.getDisplays();
//    }
//
//    @Override
//    public RecipeBookCategory getRecipeBookCategory() {
//        return null;
//    }
}