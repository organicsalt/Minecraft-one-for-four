package com.organicsalt.minecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class weapon_upgrade implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("weapon_upgrade")){
                if(strings.length==1) {
                    if(true){//如果strings[0]的武器存在
                        //链接数据库查询背包中强化石的数量
                        //sql="select amount from itemInBag where UUID='" + id + "' and item='upgrade_stone'";  //查询玩家现有强化石个数upgrade_stone
                        //sql="select level from weapon_upgrade where UUID='" + id + "' and weapon='" + strings[1] + "'"; //查询需要强化的武器等级level 记得要设置displayname
                        if(true){//如果强化石的数量足够
                            //如果武器没有强化等级
                            //sql="insert into weapon_upgrade values('" + strings[1] + "', 0, '"+ id + "')";

                            //随机函数判断是否强化成功
                            //对应数量的强化石销毁
                            //upgrade_stone=upgrade_stone-level*2;
                            //sql="update itemInBag set amount = "+upgrade_stone+" where UUID='" + id + " and item='upgrade_stone'";
                            if(true){//如果强化成功
                                //强化成功武器等级+1
                                //level=level+1;
                                //sql="update weapon_upgrade set level = "+ level +" where UUID='" + id + " and weapon='" + strings[1] + "'";
                                commandSender.sendMessage(strings[0]+"强化成功！");
                                //如果是+7以上强化成功将在工会内部广播
                            }
                            else{
                                commandSender.sendMessage(strings[0]+"强化失败！");
                                //强化失败武器等级不变
                            }
                        }
                        else{
                            commandSender.sendMessage("强化石数量不足！");
                        }
                    }
                    else{
                        commandSender.sendMessage(strings[0]+"武器不存在！");
                    }
                }
                else{
                    commandSender.sendMessage("参数过多或不足");
                    return false;
                }
            }
        }
        return true;
    }
}
