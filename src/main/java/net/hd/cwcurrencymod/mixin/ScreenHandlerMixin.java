package net.hd.cwcurrencymod.mixin;

import net.hd.cwcurrencymod.util.PendingUpdateHandler;
import net.hd.cwcurrencymod.util.constants.UpdateReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ScreenHandler.class)
public class ScreenHandlerMixin {

    @Inject(method = "onSlotClick", at = @At("HEAD"))
    private void onOnSlotClick(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        ScreenHandler handler = (ScreenHandler)(Object)this;

        if (slotIndex < 0 || slotIndex >= handler.slots.size()) {
            return;
        }

        if(player.getInventory().getStack(slotIndex).isEmpty()) return;

        if (!(player instanceof ServerPlayerEntity serverPlayer)) return;

        PendingUpdateHandler.setPendingUpdate(serverPlayer, UpdateReason.SLOT_CLICK);
    }

    @Inject(method = "onClosed", at = @At("HEAD"))
    private void onOnClosed(PlayerEntity player, CallbackInfo ci) {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) return;

        PendingUpdateHandler.setPendingUpdate(serverPlayer, UpdateReason.INVENTORY_CLOSED);
    }
}