package net.hd.cwcurrencymod.util.constants;

import com.mrcrayfish.backpacked.core.ModItems;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Set;

public class SubInventoryConstants {
    public static final Set<ComponentType<?>> SUB_INVENTORY_DATA_COMPONENTS = Set.of(
            DataComponentTypes.CONTAINER,
            DataComponentTypes.BUNDLE_CONTENTS
    );

    public static final Set<Item> SUB_INVENTORY_ITEMS = Set.of(
            Items.SHULKER_BOX,
            Items.BLACK_SHULKER_BOX,
            Items.BLUE_SHULKER_BOX,
            Items.BROWN_SHULKER_BOX,
            Items.CYAN_SHULKER_BOX,
            Items.GRAY_SHULKER_BOX,
            Items.GREEN_SHULKER_BOX,
            Items.LIGHT_BLUE_SHULKER_BOX,
            Items.LIGHT_GRAY_SHULKER_BOX,
            Items.LIME_SHULKER_BOX,
            Items.MAGENTA_SHULKER_BOX,
            Items.ORANGE_SHULKER_BOX,
            Items.PINK_SHULKER_BOX,
            Items.PURPLE_SHULKER_BOX,
            Items.RED_SHULKER_BOX,
            Items.WHITE_SHULKER_BOX,
            Items.YELLOW_SHULKER_BOX,

            Items.BUNDLE,
            Items.BLACK_BUNDLE,
            Items.BLUE_BUNDLE,
            Items.BROWN_BUNDLE,
            Items.CYAN_BUNDLE,
            Items.GRAY_BUNDLE,
            Items.GREEN_BUNDLE,
            Items.LIGHT_BLUE_BUNDLE,
            Items.LIGHT_GRAY_BUNDLE,
            Items.LIME_BUNDLE,
            Items.MAGENTA_BUNDLE,
            Items.ORANGE_BUNDLE,
            Items.PINK_BUNDLE,
            Items.PURPLE_BUNDLE,
            Items.RED_BUNDLE,
            Items.WHITE_BUNDLE,
            Items.YELLOW_BUNDLE,

            ModItems.BACKPACK.holder().value()
            );
}
