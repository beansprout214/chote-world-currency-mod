package net.hd.cwcurrencymod.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hd.cwcurrencymod.data.cocktail_types.CocktailRecord;

import java.util.List;

public record CocktailContentsComponent(CocktailRecord record) {

    public static final Codec<CocktailContentsComponent> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    CocktailRecord.CODEC.fieldOf("record").forGetter(CocktailContentsComponent::record)
            ).apply(instance, CocktailContentsComponent::new));

    public static final CocktailContentsComponent DEFAULT = new CocktailContentsComponent(CocktailRecord.WATER);
}