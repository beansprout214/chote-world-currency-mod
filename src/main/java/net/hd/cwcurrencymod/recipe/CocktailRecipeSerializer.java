package net.hd.cwcurrencymod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;

public class CocktailRecipeSerializer implements RecipeSerializer<CocktailRecipeDeprecated> {

    public static final MapCodec<CocktailRecipeDeprecated> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.listOf().fieldOf("ingredients").forGetter(CocktailRecipeDeprecated -> CocktailRecipeDeprecated.ingredients),
                    ItemStack.CODEC.fieldOf("result").forGetter(CocktailRecipeDeprecated -> CocktailRecipeDeprecated.result)
            ).apply(instance, CocktailRecipeDeprecated::new)
    );

    // ItemStack.CODEC includes components — this is what makes component-rich outputs work
    public static final PacketCodec<RegistryByteBuf, CocktailRecipeDeprecated> PACKET_CODEC = PacketCodec.tuple(
            Ingredient.PACKET_CODEC.collect(PacketCodecs.toList()),
            CocktailRecipeDeprecated -> CocktailRecipeDeprecated.ingredients,
            ItemStack.PACKET_CODEC,
            CocktailRecipeDeprecated -> CocktailRecipeDeprecated.result,
            CocktailRecipeDeprecated::new
    );

    @Override
    public MapCodec<CocktailRecipeDeprecated> codec() {
        return CODEC;
    }

    @Override
    public PacketCodec<RegistryByteBuf, CocktailRecipeDeprecated> packetCodec() {
        return PACKET_CODEC;
    }
}