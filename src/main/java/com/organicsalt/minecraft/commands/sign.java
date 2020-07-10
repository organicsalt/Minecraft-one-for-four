package com.organicsalt.minecraft.commands;

import com.organicsalt.minecraft.GUI.SignGUI;
import lk.vexview.api.VexViewAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class sign implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("sign")){
                Player player = (Player) commandSender;
                if(strings.length==0){
                    VexViewAPI.openGui(player, SignGUI.SignGUI(player));
                    return true;
                }
                else{//如果不能签到
                    commandSender.sendMessage("参数过多!");
                }
            }
        }
        return false;
    }
}
