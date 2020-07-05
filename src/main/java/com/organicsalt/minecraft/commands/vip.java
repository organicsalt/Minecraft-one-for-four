package com.organicsalt.minecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class vip implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("vip")){
                if(strings.length==2){
                    if(strings[0].equalsIgnoreCase("bonus")){
                        int amount = Integer.parseInt(strings[1]);
                        if(amount>0){
                            //sql="select gift from vip where UUID = '" + id + "'"; 查询gift
                            //sql="select vip_level from vip where UUID = '" + id + "'"; 查询vip_level
                            //查询数据库玩家第amount阶VIP奖励是否被领取 (gift>>(amount-1))&1
                            if(true){//如果没被领取
                                //领取奖励，并将数据库中玩家VIP第amount阶的领取信息设置为已领取
                                //gift=gift+Math.pow(2,amount-1);
                                //sql="update vip set gift = " + gift + " where UUID='" + id + "'";
                                commandSender.sendMessage("领取VIP"+amount+"奖励成功！");
                            }
                            else if(false){
                                commandSender.sendMessage("VIP"+amount+"奖励已领取！");
                            }
                        }
                        else{
                            commandSender.sendMessage("请输入正确的VIP级别!");
                        }
                    }
                    else{
                        commandSender.sendMessage("请正确输入指令！");
                        return false;
                    }
                }
                else if(strings.length==1){
                    if(strings[0].equalsIgnoreCase("grade")){
                        //查询数据库玩家VIP点数vip_grade
                        //sql="select vip_grade from vip where UUID = '" + id + "'"; 查询vip_grade
                        int vip_grade=10;
                        commandSender.sendMessage("你有"+vip_grade+"点VIP值！");
                    }
                    else if(strings[0].equalsIgnoreCase("level")){
                        //查询数据库玩家VIP等级vip_level
                        //sql="select vip_level from vip where UUID = '" + id + "'"; 查询vip_level
                        int vip_level=2;
                        commandSender.sendMessage("你当前为VIP"+vip_level+"！");
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
