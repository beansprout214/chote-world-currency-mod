package net.hd.cwcurrencymod.util;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.util.constants.UpdateReason;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.*;

public class PendingUpdateHandler {
    public static final Set<UUID> pendingUpdates = new HashSet<>();
    public static final Map<UUID, UpdateReason> pendingUpdateReason = new HashMap<>();

    public static void setPendingUpdate(ServerPlayerEntity player, UpdateReason reason) {
        if (hasPendingUpdate(player)) {
            return;
        }
        UUID id = player.getUuid();
        pendingUpdates.add(id);
        pendingUpdateReason.put(id, reason);
//        ChoteWorldCurrencyMod.LOGGER.info(
//                "Set pending update for {} for reason {}",
//                player.getName().getString(),
//                reason
//        );
    }

    public static void clearPendingUpdate(ServerPlayerEntity player) {
        UUID id = player.getUuid();
        pendingUpdates.remove(id);
        pendingUpdateReason.remove(id);
        ChoteWorldCurrencyMod.LOGGER.info(
                "Cleared pending update for {}",
                player.getName().getString()
        );
    }

    public static boolean hasPendingUpdate(ServerPlayerEntity player) {
        return pendingUpdates.contains(player.getUuid());
    }

    public static boolean consumePendingUpdate(ServerPlayerEntity player) {
        UUID id = player.getUuid();
        if (pendingUpdates.remove(id)) {
            UpdateReason reason = pendingUpdateReason.remove(id);
            ChoteWorldCurrencyMod.LOGGER.info(
                    "Successfully consumed pending update for {} for reason {}",
                    player.getName().getString(),
                    reason.name()
            );
            return true;
        }
        return false;
    }
}
