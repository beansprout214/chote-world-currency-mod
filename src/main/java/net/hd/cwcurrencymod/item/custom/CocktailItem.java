package net.hd.cwcurrencymod.item.custom;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.component.ModComponents;
import net.hd.cwcurrencymod.component.custom.CocktailContentsComponent;
import net.hd.cwcurrencymod.component.custom.UsesRemainingComponent;
import net.hd.cwcurrencymod.damage_type.ModDamageTypes;
import net.hd.cwcurrencymod.data.cocktail_types.CocktailRecord;
import net.hd.cwcurrencymod.item.ModItems;
import net.hd.cwcurrencymod.util.constants.CocktailTypes;
import net.hd.cwcurrencymod.util.constants.RegisteredDamageTypes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class CocktailItem extends Item {
    public CocktailItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        var contents = stack.get(ModComponents.COCKTAIL_CONTENTS);
        if (contents != null) {
            applyCocktailEffect(user, contents.record());
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

    public static void applyCocktailEffect(LivingEntity entity, CocktailRecord cocktailRecord) {
        CocktailTypes type = CocktailTypes.fromId(cocktailRecord.id());

        if(entity.getEntityWorld().isClient()) return;

        ServerWorld serverWorld = (ServerWorld)entity.getEntityWorld();
        PlayerEntity playerEntity = (PlayerEntity)entity;

        playerEntity.incrementStat(Stats.USED.getOrCreateStat(ModItems.COCKTAIL));

        switch(type) {
            case WATER:
                entity.heal(1f);
                break;
            case BLAZE_BOURBON:
                entity.setOnFireForTicks(CocktailRecord.BLAZE_BOURBON.duration());
                break;
            case BITE_OF_87:
                float health = entity.getHealth();

                if (health > 1f) {
                    entity.damage(serverWorld, ModDamageTypes.getDamageSource(serverWorld, RegisteredDamageTypes.COCKTAIL_BITE_OF_87), health - 1f);
                    break;
                }
                entity.damage(serverWorld, ModDamageTypes.getDamageSource(serverWorld, RegisteredDamageTypes.COCKTAIL_BITE_OF_87), Float.MAX_VALUE);
                break;
            case DEATH_SENTENCE:
                entity.damage(serverWorld, ModDamageTypes.getDamageSource(serverWorld, RegisteredDamageTypes.COCKTAIL_DEATH_SENTENCE), Float.MAX_VALUE);
                break;
        }
    }

    public static ItemStack createCocktail(String key, CocktailRecord record, Formatting color) {
        ItemStack stack = new ItemStack(ModItems.COCKTAIL);

        stack.set(ModComponents.COCKTAIL_CONTENTS,
                new CocktailContentsComponent(record));

        stack.set(
                DataComponentTypes.CUSTOM_NAME,
                Text.translatable(key)
                        .formatted(color)
                        .styled(style -> style.withItalic(false))
        );

        return stack;
    }

    public static ItemStack createCocktail(String key, CocktailRecord record) {
        ItemStack stack = new ItemStack(ModItems.COCKTAIL);

        stack.set(ModComponents.COCKTAIL_CONTENTS,
                new CocktailContentsComponent(record));

        stack.set(
                DataComponentTypes.CUSTOM_NAME,
                Text.translatable(key)
                        .styled(style -> style.withItalic(false))
        );

        return stack;
    }

    @Nullable
    public static ItemStack getCocktail(CocktailTypes type) {
        switch(type){
            case WATER:
                return CocktailItem.createCocktail("cocktail.chote-world-currency-mod.water", CocktailRecord.WATER);

            case BLAZE_BOURBON:
                return CocktailItem.createCocktail("cocktail.chote-world-currency-mod.blaze_bourbon", CocktailRecord.BLAZE_BOURBON, Formatting.RED);

            case BITE_OF_87:
                return CocktailItem.createCocktail("cocktail.chote-world-currency-mod.bite_of_87", CocktailRecord.BITE_OF_87, Formatting.GRAY);

            case DEATH_SENTENCE:
                return CocktailItem.createCocktail("cocktail.chote-world-currency-mod.death_sentence", CocktailRecord.DEATH_SENTENCE, Formatting.DARK_GRAY);

        }

        return null;
    }
}
