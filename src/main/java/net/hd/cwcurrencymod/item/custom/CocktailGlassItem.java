package net.hd.cwcurrencymod.item.custom;

import net.hd.cwcurrencymod.util.constants.CocktailTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.item.consume.UseAction;
import net.minecraft.potion.Potions;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class CocktailGlassItem extends Item {
    public CocktailGlassItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return ActionResult.PASS;
        } else {
            if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos blockPos = blockHitResult.getBlockPos();
                if (!world.canEntityModifyAt(user, blockPos)) {
                    return ActionResult.PASS;
                }

                if (world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                    world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    world.emitGameEvent(user, GameEvent.FLUID_PICKUP, blockPos);
                    return ActionResult.SUCCESS.withNewHandStack(this.fill(itemStack, user, CocktailItem.getCocktail(CocktailTypes.WATER)));
                }
            }

            return ActionResult.PASS;
        }
    }

    private ItemStack fill(ItemStack stack, PlayerEntity player, ItemStack outputStack) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return ItemUsage.exchangeStack(stack, player, outputStack);
    }
}
