package net.hd.cwcurrencymod.item.custom;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.component.ModComponents;
import net.hd.cwcurrencymod.component.custom.UsesRemainingComponent;
import net.hd.cwcurrencymod.item.ModItems;
import net.hd.cwcurrencymod.util.CocktailUtil;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class CocktailItem extends Item {
    public CocktailItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        var contents = stack.get(ModComponents.COCKTAIL_CONTENTS);
        if (contents != null) {
            CocktailUtil.applyCocktailEffect(user, contents.record());
        }

        // if user is in creative mode, dont decrement stack
        if (user.isInCreativeMode()) return stack;

        // get or create component
        UsesRemainingComponent usesRemainingComponent = stack.get(ModComponents.USES_REMAINING);
        if (usesRemainingComponent == null) {
            usesRemainingComponent = UsesRemainingComponent.COCKTAIL;
        }

        // decrement uses
        stack.set(ModComponents.USES_REMAINING, usesRemainingComponent.decrement());
        usesRemainingComponent = stack.get(ModComponents.USES_REMAINING);

        // log # of uses left, and, if uses remain, return the stack
        ChoteWorldCurrencyMod.LOGGER.info("{}'s cocktail has {} uses remaining", user.getStringifiedName(), usesRemainingComponent.usesRemaining());
        if (usesRemainingComponent.usesLeft()) {
            return stack;
        }

        // if no uses remain, get final stack
        ItemStack finalStack = super.finishUsing(stack, world, user);

        // if user died, drop on ground
        if (!user.isAlive()) {
            user.dropItem(new ItemStack(ModItems.COCKTAIL_GLASS), true, false);
            return ItemStack.EMPTY;
        }

        // otherwise spawn stack in inventory
        return finalStack;
    }

    private ItemStack fill(ItemStack stack, PlayerEntity player, ItemStack outputStack) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return ItemUsage.exchangeStack(stack, player, outputStack);
    }
}
