package net.hd.cwcurrencymod.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.client.render.item.property.CocktailUsesProperty;
import net.hd.cwcurrencymod.client.render.tint.custom.CocktailTintSource;
import net.minecraft.client.render.item.property.numeric.NumericProperties;
import net.minecraft.client.render.item.tint.TintSourceTypes;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ModMapManager {
    public static void bootstrap() {
        TintSourceTypes.ID_MAPPER.put(
                Identifier.of(ChoteWorldCurrencyMod.MOD_ID, "cocktail_tint_source"),
                CocktailTintSource.MAP_CODEC
        );

        NumericProperties.ID_MAPPER.put(
                Identifier.of(ChoteWorldCurrencyMod.MOD_ID, "cocktail_uses"),
                CocktailUsesProperty.MAP_CODEC
        );
    }
}
