package net.hd.cwcurrencymod.mixin;

import net.hd.cwcurrencymod.util.PendingUpdateHandler;
import net.hd.cwcurrencymod.util.constants.UpdateReason;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    @Inject(method = "markDirty", at = @At("HEAD"))
    private void onDirty(CallbackInfo ci) {
        PlayerInventory inv = (PlayerInventory)(Object)this;

        if (!(inv.player instanceof ServerPlayerEntity serverPlayer)) return;

        PendingUpdateHandler.setPendingUpdate(serverPlayer, UpdateReason.MARK_DIRTY);
    }

    @Inject(method = "dropSelectedItem", at = @At("HEAD"))
    private void ondropSelectedItem(boolean entireStack, CallbackInfoReturnable<ItemStack> ci) {
        PlayerInventory inv = (PlayerInventory)(Object)this;

        if (!(inv.player instanceof ServerPlayerEntity serverPlayer)) return;

        PendingUpdateHandler.setPendingUpdate(serverPlayer, UpdateReason.DROP_SELECTED_ITEM);
    }

    @Inject(method = "addStack(ILnet/minecraft/item/ItemStack;)I", at = @At("HEAD"))
    private void onAddStack(int slot, ItemStack stack, CallbackInfoReturnable<Integer> ci) {
        PlayerInventory inv = (PlayerInventory)(Object)this;

        if (!(inv.player instanceof ServerPlayerEntity serverPlayer)) return;

        PendingUpdateHandler.setPendingUpdate(serverPlayer, UpdateReason.ADD_STACK);
    }
}