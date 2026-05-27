package net.hd.cwcurrencymod.util;

import net.hd.cwcurrencymod.util.constants.UpdateReason;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.UUID;

public class InventoryListener implements InventoryChangedListener {
    public static HashMap<UUID, InventoryChangedListener> listeners = new HashMap<>();

    private final ServerPlayerEntity player;

    public InventoryListener(ServerPlayerEntity player) {
        this.player = player;
    }

    @Override
    public void onInventoryChanged(Inventory sender) {
        PendingUpdateHandler.setPendingUpdate(player, UpdateReason.ENDER_CHEST_UPDATED);
    }
}
