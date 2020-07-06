package com.organicsalt.minecraft.commands;

import com.organicsalt.minecraft.GUI.storeInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class sign_in implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("sign_in")){
                //连接数据库查询已经签到的日子
                //sql="select "+strings[0]+" from role_sign where UUID = '" + id + "'"; 查询签到的日子date
                if(true){//如果能够签到
                    Player player=(Player)commandSender;
                    storeInventory.storeGUI(player);
                    commandSender.sendMessage("签到成功!");
                    //向玩家背包发送当日签到奖励
                    //sql="insert into role_sign values('"+UUID+"', "+strings[0]+")";
                }
                else{//如果不能签到
                    commandSender.sendMessage("今天你已经签过到了!");
                }
            }

        }
        else{
            commandSender.sendMessage("你没有这个功能！");
        }
        return true;
    }
}
