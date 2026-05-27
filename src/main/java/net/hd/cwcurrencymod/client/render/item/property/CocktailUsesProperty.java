package net.hd.cwcurrencymod.client.render.item.property;

import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.hd.cwcurrencymod.component.ModComponents;
import net.minecraft.client.render.item.property.numeric.NumericProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HeldItemContext;
import org.jspecify.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class CocktailUsesProperty implements NumericProperty {
    public static final MapCodec<CocktailUsesProperty> MAP_CODEC = MapCodec.unit(new CocktailUsesProperty());

    @Override
    public float getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable HeldItemContext heldItemContext, int seed) {
        var comp = stack.get(ModComponents.USES_REMAINING);
        if (comp == null || comp.maxUses() == 0) return 1.0f;
        return (float) comp.usesRemaining() / comp.maxUses();
        // 5/5=1.0, 4/5=0.8, 3/5=0.6, 2/5=0.4, 1/5=0.2, 0/5=0.0→fallback
    }

    @Override
    public MapCodec<? extends NumericProperty> getCodec() {
        return MAP_CODEC;
    }
}