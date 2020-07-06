package com.organicsalt.minecraft.util;

import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getServer;

public class PlayerPointsUtil {
    public static PlayerPoints playerPoints = null;

    public static boolean hookPlayerPoints() {
        final Plugin plugin = getServer().getPluginManager().getPlugin("PlayerPoints");
        playerPoints = PlayerPoints.class.cast(plugin);
        return playerPoints != null;
    }

    public static PlayerPoints getPlayerPoints(){
        return playerPoints;
    }
}