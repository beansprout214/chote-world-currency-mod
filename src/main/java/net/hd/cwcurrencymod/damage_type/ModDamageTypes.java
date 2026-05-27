package net.hd.cwcurrencymod.damage_type;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.damage_type.custom.OverdoseDamageSource;
import net.hd.cwcurrencymod.util.constants.RegisteredDamageTypes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import org.jspecify.annotations.Nullable;

public class ModDamageTypes {

    @Nullable
    private static RegistryKey<DamageType> getKey(RegisteredDamageTypes damageType) {
        RegistryKey<DamageType> source = damageType.key();

        if (source == null) {
            ChoteWorldCurrencyMod.LOGGER.error("Attempted to get an unregistered damage source: {}",damageType);
            return null;
        }

        return source;
    }

    public static DamageSource getDamageSource(ServerWorld world, RegisteredDamageTypes damageType) {
        RegistryKey<DamageType> key = getKey(damageType);
        if(key == null) return null;

        RegistryEntry<DamageType> entry = world.getRegistryManager().getEntryOrThrow(key);

        switch(damageType){
            case OVERDOSE:
                return new OverdoseDamageSource(entry);
        }

        return world.getDamageSources().create(key);
    }

    public static void registerModDamageTypes() {
        ChoteWorldCurrencyMod.LOGGER.info("Registering Mod Damage Types for " + ChoteWorldCurrencyMod.MOD_ID);
    }
}
