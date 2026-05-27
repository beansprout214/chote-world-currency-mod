package net.hd.cwcurrencymod.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.effect.GeekedEffect;
import net.hd.cwcurrencymod.mixin.invoker.StatusEffectInstanceAccessor;
import net.hd.cwcurrencymod.util.PendingUpdateHandler;
import net.hd.cwcurrencymod.util.constants.UpdateReason;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstanceMixin {

    @Unique
    private boolean cwcurrencymod$lastTickHiddenEffectResult;

    @Unique
    private int cwcurrencymod$lastAmplifier;

    @WrapOperation(method = "update", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/effect/StatusEffectInstance;tickHiddenEffect()Z"
    ))
    private boolean cwcurrency$wrapTickHiddenEffect(StatusEffectInstance instance, Operation<Boolean> original) {

        cwcurrencymod$lastTickHiddenEffectResult = original.call(instance);

        return cwcurrencymod$lastTickHiddenEffectResult;
    }

    @Inject(method = "update", at = @At(value = "RETURN", ordinal = 2))
    private void onUpdate(ServerWorld world, LivingEntity entity, Runnable hiddenEffectCallback, CallbackInfoReturnable<Boolean> cir) {
        StatusEffectInstance effectInstance = (StatusEffectInstance)(Object)this;
//        ChoteWorldCurrencyMod.LOGGER.info("Amplifier: {}, THE: {}, isActive: {}",effectInstance.getAmplifier(),cwcurrencymod$lastTickHiddenEffectResult,cir.getReturnValue());

        if (
                cwcurrencymod$lastAmplifier == 9 &&
                !cwcurrencymod$lastTickHiddenEffectResult &&
                cir.getReturnValue() == false
        ) {
            ChoteWorldCurrencyMod.LOGGER.info("{}'s effect expired",entity.getStringifiedName());
            GeekedEffect.applyGeekedDamagePostAmplified(world,entity);
        }

        if (
                cwcurrencymod$lastAmplifier == 9 &&
                effectInstance.getAmplifier() != 9
        ) {
            ChoteWorldCurrencyMod.LOGGER.info("{}'s effect expired, with hidden effect",entity.getStringifiedName());
            GeekedEffect.applyGeekedDamagePostAmplified(world,entity);
        }

        cwcurrencymod$lastAmplifier = effectInstance.getAmplifier();
    }
}