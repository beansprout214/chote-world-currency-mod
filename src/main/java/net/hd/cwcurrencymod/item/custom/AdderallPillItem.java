package net.hd.cwcurrencymod.item.custom;

import net.fabricmc.fabric.api.item.v1.ComponentTooltipAppenderRegistry;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.function.Consumer;


public class AdderallPillItem extends Item {
    public AdderallPillItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
}
