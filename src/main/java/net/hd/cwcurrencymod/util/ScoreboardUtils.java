package net.hd.cwcurrencymod.util;

import net.hd.cwcurrencymod.ChoteWorldCurrencyMod;
import net.minecraft.scoreboard.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.apache.logging.log4j.core.jmx.Server;

public class ScoreboardUtils {
    private static Scoreboard getScoreboard(ServerPlayerEntity player) {
        return player.getEntityWorld().getScoreboard();
    }

    private static ScoreHolder getScoreHolder(ServerPlayerEntity player) {
        return ScoreHolder.fromName(player.getNameForScoreboard());
    }

    private static ScoreAccess getScoreAccess(ServerPlayerEntity player, MinecraftServer server, String objectiveName) {
        Scoreboard scoreboard = getScoreboard(player);

        ScoreboardObjective objective = getOrCreateObjective(player, server, objectiveName);

        ScoreHolder scoreHolder = getScoreHolder(player);

        return scoreboard.getOrCreateScore(
                scoreHolder,
                objective,
                true
        );
    }

    private static ScoreboardObjective findScoreboardObjective (ServerPlayerEntity player, String objectiveName) {
        Scoreboard scoreboard = getScoreboard(player);
        return scoreboard.getObjectives().stream().filter(obj -> obj.getName().equals(objectiveName)).findFirst().orElse(null);
    }

    private static ScoreboardObjective getOrCreateObjective (ServerPlayerEntity player, MinecraftServer server, String objectiveName) {
        Scoreboard scoreboard = getScoreboard(player);

        ScoreboardObjective objective = findScoreboardObjective(player, objectiveName);
        if (objective != null) return objective;

        objective = scoreboard.addObjective(
                objectiveName,
                ScoreboardCriterion.DUMMY,
                Text.literal("Chote Coins"),
                ScoreboardCriterion.RenderType.INTEGER,
                true,
                null
        );

        return objective;
    }

    public static void updateObjectiveForPlayer (ServerPlayerEntity player, MinecraftServer server, String objectiveName, int value) {
        ChoteWorldCurrencyMod.LOGGER.info("Setting {}'s {} objective to {}",player.getStringifiedName(),objectiveName,value);
        ScoreAccess scoreAccess = getScoreAccess(player, server, objectiveName);
        scoreAccess.setScore(value);
    }
}
