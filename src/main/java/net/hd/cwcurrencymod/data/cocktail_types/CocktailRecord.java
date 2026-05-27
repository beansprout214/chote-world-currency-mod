package net.hd.cwcurrencymod.data.cocktail_types;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hd.cwcurrencymod.util.constants.CocktailTypes;

public record CocktailRecord(String id, int duration, int amplifier) {
    public static final Codec<CocktailRecord> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.STRING.fieldOf("id").forGetter(CocktailRecord::id),
                    Codec.INT.fieldOf("duration").forGetter(CocktailRecord::duration),
                    Codec.INT.fieldOf("amplifier").forGetter(CocktailRecord::amplifier)
            ).apply(instance, CocktailRecord::new));

    public static final CocktailRecord WATER = new CocktailRecord(CocktailTypes.WATER.id(), 300, 0);
    public static final CocktailRecord BLAZE_BOURBON = new CocktailRecord(CocktailTypes.BLAZE_BOURBON.id(), 160, 0);
    public static final CocktailRecord BITE_OF_87 = new CocktailRecord(CocktailTypes.BITE_OF_87.id(), 0, 0);
    public static final CocktailRecord DEATH_SENTENCE = new CocktailRecord(CocktailTypes.DEATH_SENTENCE.id(), 0, 0);
    public static final CocktailRecord BLAKE_SHAKE = new CocktailRecord(CocktailTypes.BLAKE_SHAKE.id(), 0, 0);
    public static final CocktailRecord CHOTE_FLOAT = new CocktailRecord(CocktailTypes.CHOTE_FLOAT.id(), 0, 0);

}