package net.hd.cwcurrencymod.util;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.hd.cwcurrencymod.block.ModBlocks;
import net.hd.cwcurrencymod.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

public class CurrencyManager {
    public static int getChoiteCentRawValue(int coins, int coinBlocks, int cents, int centBlocks) {
        return
                cents +
                centBlocks * 8 +
                coins * 64 +
                coinBlocks * 64 * 8;
    }

    public static int getChoteCoinRawValue(int coins, int coinBlocks, int cents, int centBlocks) {
        return getChoiteCentRawValue(coins, coinBlocks, cents, centBlocks)/64;
    }

    public static void refreshPlayerCurrencyScore (ServerPlayerEntity player, MinecraftServer server) {
        CurrencySearcher searcher = new CurrencySearcher();
        int centCount = searcher.logAllItems(player,server, ModItems.CHOTE_COIN);
        ScoreboardUtils.updateObjectiveForPlayer(player,server,"currency",centCount/64);
    }
}
