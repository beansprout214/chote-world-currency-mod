package net.hd.cwcurrencymod.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record UsesRemainingComponent(int maxUses, int usesRemaining) {
    public static final Codec<UsesRemainingComponent> CODEC =
            RecordCodecBuilder.create(instance -> instance.group(
                    Codec.INT.fieldOf("maxUses").forGetter(UsesRemainingComponent::maxUses),
                    Codec.INT.fieldOf("usesRemaining").forGetter(UsesRemainingComponent::usesRemaining)
            ).apply(instance, UsesRemainingComponent::new));

    public static final UsesRemainingComponent COCKTAIL = new UsesRemainingComponent(5,5);

    public UsesRemainingComponent decrement() {
        return new UsesRemainingComponent(maxUses, Math.clamp(usesRemaining-1,0,maxUses));
    }

    public boolean usesLeft() {
        return usesRemaining > 0;
    }
}
