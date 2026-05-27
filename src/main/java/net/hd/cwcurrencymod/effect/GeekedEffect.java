package net.hd.cwcurrencymod.effect;

import net.hd.cwcurrencymod.damage_type.ModDamageTypes;
import net.hd.cwcurrencymod.util.constants.RegisteredDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

public class GeekedEffect extends StatusEffect {
    public GeekedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        return super.applyUpdateEffect(world, entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    public static boolean applyGeekedDamage(ServerWorld serverWorld, LivingEntity entity, float amount) {
        return entity.damage(serverWorld, ModDamageTypes.getDamageSource(serverWorld, RegisteredDamageTypes.OVERDOSE), amount);
    }

    public static boolean applyGeekedDamagePostAmplified(ServerWorld serverWorld, LivingEntity entity) {
        float currHealth = entity.getHealth();
        return applyGeekedDamage(serverWorld, entity, currHealth-1f);
    }

}
