package net.hd.cwcurrencymod.component.custom.tooltips;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public record PookieBearTooltip() implements TooltipAppender {
    public static final Codec<PookieBearTooltip> CODEC =
            MapCodec.unitCodec(new PookieBearTooltip());

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        textConsumer.accept(Text.translatable("tooltip.chote-world-currency-mod.pookie_bear1").formatted(Formatting.DARK_GRAY));
        textConsumer.accept(Text.translatable("tooltip.chote-world-currency-mod.pookie_bear2").formatted(Formatting.DARK_GRAY));
    }
}