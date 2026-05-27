package net.hd.cwcurrencymod.effect;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> SLIMEY = registerStatusEffect("slimey",
            new SlimeyEffect(StatusEffectCategory.NEUTRAL, 0x36ebab)
                    .addAttributeModifier(EntityAttributes.MOVEMENT_SPEED,
                            Identifier.of(ChoteWorldCurrencyMod.MOD_ID, "slimey"), -0.25f,
                            EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final RegistryEntry<StatusEffect> GEEKED = registerStatusEffect("geeked",
            new GeekedEffect(StatusEffectCategory.NEUTRAL, 0xfff8e3)
                    .addAttributeModifier(EntityAttributes.MINING_EFFICIENCY, Identifier.of(ChoteWorldCurrencyMod.MOD_ID,"geeked_mining_efficiency"), 1.2F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(EntityAttributes.ATTACK_SPEED, Identifier.of(ChoteWorldCurrencyMod.MOD_ID,"geeked_haste"), 0.1F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(EntityAttributes.MOVEMENT_SPEED, Identifier.of(ChoteWorldCurrencyMod.MOD_ID,"geeked_speed"), 0.1F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(ChoteWorldCurrencyMod.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        ChoteWorldCurrencyMod.LOGGER.info("Registering Mod Effects for " + ChoteWorldCurrencyMod.MOD_ID);
    }
}