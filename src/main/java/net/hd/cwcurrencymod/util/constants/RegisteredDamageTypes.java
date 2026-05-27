package net.hd.cwcurrencymod.util.constants;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public enum RegisteredDamageTypes {
    OVERDOSE("overdose"),
    COCKTAIL_DEATH_SENTENCE("cocktail_death_sentence"),
    COCKTAIL_BITE_OF_87("cocktail_bite_of_87");

    private final String id;
    private final RegistryKey<DamageType> key;

    RegisteredDamageTypes(String id) {
        this.id = id;
        this.key = RegistryKey.of(
                RegistryKeys.DAMAGE_TYPE,
                Identifier.of(ChoteWorldCurrencyMod.MOD_ID, id)
        );
    }

    public String id() {
        return id;
    }

    public RegistryKey<DamageType> key() {
        return key;
    }

    public static RegisteredDamageTypes fromId(String id) {
        for (RegisteredDamageTypes type : values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }
}
