package com.organicsalt.minecraft.commands;

import com.organicsalt.minecraft.util.PlayerPointsUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class invest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("invest")){
                if(strings.length==1){
                    Player player=(Player)commandSender;
                    if (player.hasPermission("invest")) {
                        int amount = Integer.parseInt(strings[0]);
                        UUID id = player.getUniqueId();
                        if (amount > 0) {
                            PlayerPointsUtil.playerPoints.getAPI().give(player.getUniqueId(),amount);
                            commandSender.sendMessage("充值"+amount+"点券成功！");
                            return true;
                        }
                        else{
                            commandSender.sendMessage("请输入正确的充值数量！");
                        }
                    }
                    else{
                        commandSender.sendMessage("你没有权限！");
                        return true;
                    }
                }
                else{
                    commandSender.sendMessage("参数过多或不足");
                }
            }
        }
        else{
            commandSender.sendMessage("你没有这个功能！");
        }
        return false;
    }
}
