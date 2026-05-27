package net.hd.cwcurrencymod.util;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.component.ModComponents;
import net.hd.cwcurrencymod.component.custom.CocktailContentsComponent;
import net.hd.cwcurrencymod.damage_type.ModDamageTypes;
import net.hd.cwcurrencymod.data.cocktail_types.CocktailRecord;
import net.hd.cwcurrencymod.item.ModItems;
import net.hd.cwcurrencymod.item.custom.CocktailItem;
import net.hd.cwcurrencymod.util.constants.CocktailTypes;
import net.hd.cwcurrencymod.util.constants.RegisteredDamageTypes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class CocktailUtil {
    public static int getColor(CocktailContentsComponent contentsComponent) {
        CocktailTypes type = CocktailTypes.fromId(contentsComponent.record().id());
        if (type == null) {
            ChoteWorldCurrencyMod.LOGGER.error("Attempted to fetch the color of CocktailType with id {}, which doesn't exist",contentsComponent.record().id());
        }
        switch (type) {
            case WATER:
                return 0xFF527cd1;
            case BLAZE_BOURBON:
                return 0xFFff672b;
            case BITE_OF_87:
                return 0xFF965823;
            case DEATH_SENTENCE:
                return 0xFF363636;
            case BLAKE_SHAKE:
                return 0xFF42d10f;
            case CHOTE_FLOAT:
                return 0xFFffa30f;
            case null:
                break;
        }
        return 0xFFFFFFFF;
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

                if (health > 4f) {
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
                return createCocktail("cocktail.chote-world-currency-mod.water", CocktailRecord.WATER);

            case BLAZE_BOURBON:
                return createCocktail("cocktail.chote-world-currency-mod.blaze_bourbon", CocktailRecord.BLAZE_BOURBON, Formatting.RED);

            case BITE_OF_87:
                return createCocktail("cocktail.chote-world-currency-mod.bite_of_87", CocktailRecord.BITE_OF_87, Formatting.GRAY);

            case DEATH_SENTENCE:
                return createCocktail("cocktail.chote-world-currency-mod.death_sentence", CocktailRecord.DEATH_SENTENCE, Formatting.DARK_GRAY);

            case BLAKE_SHAKE:
                return createCocktail("cocktail.chote-world-currency-mod.blake_shake", CocktailRecord.BLAKE_SHAKE, Formatting.GREEN);

            case CHOTE_FLOAT:
                return createCocktail("cocktail.chote-world-currency-mod.chote_float", CocktailRecord.CHOTE_FLOAT, Formatting.GOLD);

        }

        return null;
    }

    private static boolean isCocktailEntityOfType(ItemEntity cocktailEntity, CocktailTypes targetType) {
        CocktailRecord cocktailRecord = cocktailEntity.getStack().get(ModComponents.COCKTAIL_CONTENTS).record();
        if (cocktailRecord == null) return false;
        return CocktailTypes.fromId(cocktailRecord.id()) == targetType;
    }

    private static boolean isOtherEntity(ItemEntity otherEntity, Item targetItem) {
        return otherEntity.getStack().getItem().equals(targetItem);
    }

    private static boolean handleInteraction(ItemEntity cocktailEntity, ItemEntity otherEntity) {
        if (isOtherEntity(otherEntity, Items.BLAZE_POWDER) && isCocktailEntityOfType(cocktailEntity, CocktailTypes.WATER)) {
            cocktailEntity.setStack(getCocktail(CocktailTypes.BLAZE_BOURBON));
            otherEntity.getStack().decrement(1);
            return true;
        }
        return false;
    }

    public static void interactWithNearby(ItemEntity cocktailEntity) {
        Box box = cocktailEntity.getBoundingBox().expand(0.75f);

        List<ItemEntity> nearby = cocktailEntity.getEntityWorld().getEntitiesByClass(
                ItemEntity.class,
                box,
                other -> other != cocktailEntity
        );

        for (ItemEntity otherEntity : nearby) {
            if (handleInteraction(cocktailEntity, otherEntity)) return;
        }
    }
}
