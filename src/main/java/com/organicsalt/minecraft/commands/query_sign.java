package com.organicsalt.minecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class query_sign implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("你好"+commandSender.getName());
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("query_sign")){
                //连接数据库查询已经签到的日子
                if(true){//如果查询有返回结果
                    commandSender.sendMessage("你的签到记录如下：");
                    //向玩家发送当月签到结果
                }
                else{//如果查询不到结果
                    commandSender.sendMessage("系统没有你的签到记录!");
                }
            }

        }
        else{
            commandSender.sendMessage("你没有这个功能！");
        }
        return true;
    }
}
