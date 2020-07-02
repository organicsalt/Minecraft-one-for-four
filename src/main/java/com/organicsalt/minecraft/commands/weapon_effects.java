package com.organicsalt.minecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class weapon_effects implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("weapon_effects")){
                if(strings.length==2) {
                    if(true) {//如果名字为strings[1]的武器存在
                        if (strings[0].equalsIgnoreCase("on")) {//如果是开启武器特效指令
                            //连接数据库查询strings[1]的武器特效状况
                            if (true) {//如果strings[1]的武器特效尚未开启
                                //开启strings[1]的器特效
                                commandSender.sendMessage(strings[1]+"特效开启成功!");
                            } else {//如果strings[1]的武器特效已经开启
                                commandSender.sendMessage(strings[1]+"特效已开启!");
                            }
                        } else if (strings[0].equalsIgnoreCase("off")) {//如果是关闭武器特效指令
                            //连接数据库查询strings[1]的武器特效状况
                            if (true) {//如果strings[1]的武器特效已开启
                                //关闭strings[1]的武器特效
                                commandSender.sendMessage(strings[1]+"特效关闭成功!");
                            } else {//如果strings[1]的武器特效尚未开启
                                commandSender.sendMessage(strings[1]+"特效尚未开启!");
                            }
                        }
                        else if(strings[0].equalsIgnoreCase("up")) {//如果是赋予武器特效指令
                            //查看背包内是否有装扮石
                            if(true){//如果能够开启特效，那么消耗一个装扮石
                                commandSender.sendMessage(strings[1]+"特效赋予成功!");
                                //数据库中写入strings[1]的武器特效信息，背包中装扮石数量-1
                            }
                            else if(false) {//如果没有装扮石，那么输出装扮石余额不足
                                commandSender.sendMessage("装扮石余额不足!");
                            }
                            else if(false) {//如果不能赋予武器特效，那么输出该武器已经赋予特效
                                commandSender.sendMessage(strings[1]+"你已经赋予特效!");
                            }
                        }
                    }
                    else{
                        commandSender.sendMessage("武器名称不存在！");
                    }
                }
                else{
                    commandSender.sendMessage("参数过多或不足");
                    return false;
                }
            }
        }
        else{
            commandSender.sendMessage("你没有这个功能！");
        }
        return true;
    }
}
