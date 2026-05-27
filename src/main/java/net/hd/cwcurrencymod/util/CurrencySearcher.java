package net.hd.cwcurrencymod.util;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.block.ModBlocks;
import net.hd.cwcurrencymod.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.stream.Stream;

public class CurrencySearcher extends InventorySearcher {

    public int getItemStackValue(ItemStack stack) {
        if(stack.isOf(ModItems.CHOITE_CENT)) {
//            ChoteWorldCurrencyMod.LOGGER.info("ItemStack is cent");
            return stack.getCount();
        }

        if(stack.isOf(ModBlocks.CHOITE_CENT_BLOCK.asItem())) {
//            ChoteWorldCurrencyMod.LOGGER.info("ItemStack is cent block");
            return stack.getCount() * 8;
        }

        if(stack.isOf(ModItems.CHOTE_COIN)) {
//            ChoteWorldCurrencyMod.LOGGER.info("ItemStack is coin");
            return stack.getCount() * 64;
        }

        if(stack.isOf(ModBlocks.CHOTE_COIN_BLOCK.asItem())) {
//            ChoteWorldCurrencyMod.LOGGER.info("ItemStack is coin block");
            return stack.getCount() * 64 * 8;
        }

        return 0;
    }

    @Override
    public int countContainer(ItemStack containerStack, Item item) {
        ContainerComponent container = containerStack.get(DataComponentTypes.CONTAINER);
        if (container == null) return 0;
        return getCountFromItemStackStream(container.stream(), item);
    }

    @Override
    public int countBundle(ItemStack bundle, Item item) {
        BundleContentsComponent container = bundle.get(DataComponentTypes.BUNDLE_CONTENTS);
        if (container == null) return 0;
        return getCountFromItemStackStream(container.stream(), item);
    }

    @Override
    public int getCountFromItemStackStream(Stream<ItemStack> stream, Item item) {
        return stream
                .filter(stack -> !stack.isEmpty())
                .mapToInt(stack -> handleItemStack(stack, item))
                .sum();
    }

    @Override
    public int handleItemStack(ItemStack stack, Item targetItem) {
        // container check
        if(stack.getComponents().contains(DataComponentTypes.CONTAINER)) {
            return countContainer(stack, targetItem);
        }

        // bundle check
        if(stack.getComponents().contains(DataComponentTypes.BUNDLE_CONTENTS)) {
            return countBundle(stack, targetItem);
        }

        return getItemStackValue(stack);
    }

}