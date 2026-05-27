package net.hd.cwcurrencymod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.hd.cwcurrencymod.block.ModBlocks;
import net.hd.cwcurrencymod.component.ModComponents;
import net.hd.cwcurrencymod.damage_type.ModDamageTypes;
import net.hd.cwcurrencymod.item.ModItemGroups;
import net.hd.cwcurrencymod.item.ModItems;
import net.hd.cwcurrencymod.item.custom.StimulantItem;
import net.hd.cwcurrencymod.recipe.ModRecipeSerializers;
import net.hd.cwcurrencymod.recipe.ModRecipeTypes;
import net.hd.cwcurrencymod.util.*;
import net.hd.cwcurrencymod.util.constants.UpdateReason;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChoteWorldCurrencyMod implements ModInitializer {
	public static final String MOD_ID = "chote-world-currency-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModDamageTypes.registerModDamageTypes();
		ModComponents.registerDataComponentTypes();
		ModRecipeTypes.register();
		ModRecipeSerializers.register();

		ServerPlayerEvents.JOIN.register(player -> {
			InventoryListener listener = new InventoryListener(player);
			InventoryListener.listeners.put(player.getUuid(),listener);
			player.getEnderChestInventory().addListener(listener);
			PendingUpdateHandler.setPendingUpdate(player, UpdateReason.PLAYER_JOINED);
		});

		ServerPlayerEvents.LEAVE.register(player -> {
			InventoryChangedListener listener = InventoryListener.listeners.remove(player.getUuid());
			if (listener != null) {
				player.getEnderChestInventory().removeListener(listener);
			}
		});

		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
				if (PendingUpdateHandler.consumePendingUpdate(player)) {
					CurrencyManager.refreshPlayerCurrencyScore(player, server);
				}
			}

			StimulantItem.tick();
		});
	}
}