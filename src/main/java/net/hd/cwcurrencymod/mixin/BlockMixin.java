package net.hd.cwcurrencymod.mixin;

import net.hd.cwcurrencymod.util.PendingUpdateHandler;
import net.hd.cwcurrencymod.util.constants.UpdateReason;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "onPlaced", at = @At("HEAD"))
    private void onOnPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)placer;

        if (!(player instanceof ServerPlayerEntity serverPlayer)) return;

        PendingUpdateHandler.setPendingUpdate(serverPlayer, UpdateReason.BLOCK_PLACED);
    }
}