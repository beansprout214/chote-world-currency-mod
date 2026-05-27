package net.hd.cwcurrencymod.client.render.tint.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.hd.cwcurrencymod.component.ModComponents;
import net.hd.cwcurrencymod.util.CocktailUtil;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class CocktailTintSource implements TintSource {
    public static final Codec<CocktailTintSource> CODEC = MapCodec.unitCodec(new CocktailTintSource());
    public static final MapCodec<CocktailTintSource> MAP_CODEC = MapCodec.unit(new CocktailTintSource());

    @Override
    public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        var contents = stack.get(ModComponents.COCKTAIL_CONTENTS);
        if (contents == null) return 0xFFFFFF;

        return CocktailUtil.getColor(contents);
    }

    @Override
    public MapCodec<? extends TintSource> getCodec() {
        return CocktailTintSource.MAP_CODEC;
    }

    public CocktailTintSource() {
        super();
    }
}
