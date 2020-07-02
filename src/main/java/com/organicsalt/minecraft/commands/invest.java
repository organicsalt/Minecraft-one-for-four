package com.organicsalt.minecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class invest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("invest")){
                if(strings.length==1){
                    //生成付款二维码并显示在玩家界面
                    if(true){//玩家付款成功后
                        int amount = Integer.parseInt(strings[0]);
                        if(amount>0) {
                            //在数据库中查询对应的玩家现有金钱money
                            //money=money+amount
                            //将新的money信息写入数据库
                            //更新玩家vip_grade信息
                            //更新玩家vip_level信息
                            commandSender.sendMessage("充值" + amount + "点卡成功！");
                        }
                        else{
                            commandSender.sendMessage("请输入正确的充值数量！");
                        }
                    }
                    else if(false){//玩家付款失败或未付款
                        commandSender.sendMessage("充值失败，请重新充值！");
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
