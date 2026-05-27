package net.hd.cwcurrencymod.component.custom;

import com.mojang.serialization.Codec;
import net.hd.cwcurrencymod.data.cocktail_types.CocktailRecord;

import java.util.List;

public record CocktailContentsComponent(CocktailRecord record) {
    public static final Codec<CocktailContentsComponent> CODEC =
            CocktailRecord.CODEC.xmap(CocktailContentsComponent::new, CocktailContentsComponent::record);

    public static final CocktailContentsComponent DEFAULT = new CocktailContentsComponent(CocktailRecord.WATER);
}