package net.hd.cwcurrencymod.item.custom;

import net.hd.cwcurrencymod.damage_type.ModDamageTypes;
import net.hd.cwcurrencymod.util.constants.RegisteredDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class StimulantItem extends Item {
    public static HashMap<UUID, Integer> playersOnCooldown = new HashMap<>();

    public StimulantItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BRUSH;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            if(player instanceof ServerPlayerEntity serverPlayer) {

                boolean onCooldown = StimulantItem.playersOnCooldown.getOrDefault(serverPlayer.getUuid(),0) > 0;

                if(onCooldown) {
                    ServerWorld serverWorld = (ServerWorld)world;
                    serverPlayer.damage(serverWorld, ModDamageTypes.getDamageSource(serverWorld, RegisteredDamageTypes.OVERDOSE), Float.MAX_VALUE);
                    playersOnCooldown.remove(player.getUuid());
                } else {
                    StimulantItem.playersOnCooldown.put(serverPlayer.getUuid(), 1200);
                }
            }
        }
        return super.finishUsing(stack, world, user);
    }


    public static void tick() {
        Iterator<Map.Entry<UUID, Integer>> it = playersOnCooldown.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<UUID, Integer> entry = it.next();
            int newValue = entry.getValue() - 1;

            if (newValue <= 0) {
                it.remove();
            } else {
                entry.setValue(newValue);
            }
        }
    }
}
