package net.hd.cwcurrencymod.mixin.invoker;

import net.minecraft.entity.effect.StatusEffectInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StatusEffectInstance.class)
public interface StatusEffectInstanceAccessor {
    @Invoker("isActive")
    boolean cwcurrency$invokeIsActive();
}