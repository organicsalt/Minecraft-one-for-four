package com.organicsalt.minecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class complement_sign implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("complement_sign")){
                if(strings.length==1){
                    //连接数据库查询玩家签到的日期
                    //sql="select "+strings[0]+" from role_sign where UUID = '" + id + "'"; 查询签到的日子date
                    if(true){//如果输入的日期合法
                        //sql="select amount from itemInBag where UUID='" + id + "' and item='complement_card'";  //查询玩家现有补签卡张数complement_card
                        if(true){//如果能够补签，那么消耗一张补签卡
                            commandSender.sendMessage("补签成功!");
                            //数据库中写入签到信息，背包中补签卡数量-
                            //complement_card=complement_card-1;
                            //sql="update itemInBag set amount = "+complement_card+" where UUID='" + id + " and item='complement_card'";
                            //sql="insert into role_sign values('"+UUID+"', "+strings[0]+")";
                        }
                        else if(false) {//如果没有补签卡，那么输出补签卡余额不足
                            commandSender.sendMessage("补签卡余额不足!");
                        }
                        else if(false) {//如果不能补签，那么输出该日已经签到
                            commandSender.sendMessage("该日期你已经签到!");
                        }
                    }
                    else{
                        commandSender.sendMessage("请输入正确的日期!");
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
