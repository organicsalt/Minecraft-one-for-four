package com.organicsalt.minecraft.commands;

import com.organicsalt.minecraft.GUI.effectInventory;
import com.organicsalt.minecraft.GUI.upgradeInventory;
import com.organicsalt.minecraft.util.PlayerPointsUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class weapon_upgrade implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player=(Player)commandSender;
            if (s.equalsIgnoreCase("weapon_upgrade")){
                if(player.hasPermission("weapon_upgrade")) {
                    if (strings.length == 0) {
                        upgradeInventory.upgradeGUI((Player) commandSender);
                    } else {
                        commandSender.sendMessage("参数过多或不足");
                        return false;
                    }
                }
                else{
                    player.sendMessage("你没有权限！");
                    return true;
                }
            }
        }
        else{
            commandSender.sendMessage("你没有这个功能！");
        }
        return false;
    }
}
