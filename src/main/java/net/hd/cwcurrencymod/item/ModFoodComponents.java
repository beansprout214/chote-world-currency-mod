package net.hd.cwcurrencymod.item;

import net.hd.cwcurrencymod.effect.ModEffects;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class ModFoodComponents {

    public static final FoodComponent STIMULANT = new FoodComponent.Builder().alwaysEdible().build();
    public static final ConsumableComponent STIMULANT_EFFECT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(ModEffects.GEEKED, 300,9))).build();

    public static final FoodComponent ADDERALL_PILL = new FoodComponent.Builder().alwaysEdible().build();
    public static final ConsumableComponent ADDERALL_PILL_EFFECT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(ModEffects.GEEKED, 2400),0.9f)).build();


    public static final FoodComponent CHOTE_COIN = new FoodComponent.Builder().alwaysEdible().build();
    public static final ConsumableComponent CHOTE_COIN_EFFECT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 5, 1), 0.5f)).build();

    public static final FoodComponent CHOITE_CENT = new FoodComponent.Builder().alwaysEdible().build();
    public static final ConsumableComponent CHOITE_CENT_EFFECT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 5), 0.5f)).build();

}