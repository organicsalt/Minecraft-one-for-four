package com.organicsalt.minecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class buy implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("你好"+commandSender.getName());
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("buy")){
                if(strings.length==2){
                    //连接数据库查询物品价格sign_money|upgrade_money|effects_money,和玩家现有金钱money
                    int amount = Integer.parseInt(strings[1]);
                    if(amount>0){
                        if(strings[0].equalsIgnoreCase("sign")){

                            if(true){//如果sign_money*amount<=gold
                                //gold=gold-sign_money*amount;
                                //将gold和amount数量的sign写入数据库
                                commandSender.sendMessage("购买"+amount+"件补签卡成功！");
                            }
                            else if(false){
                                commandSender.sendMessage("余额不足，购买补签卡失败，请充值！");
                            }
                        }
                        else if(strings[0].equalsIgnoreCase("upgrade")){
                            if(true){//如果upgrade_money*amount<=gold
                                //gold=gold-upgrade_money*amount;
                                //将gold和amount数量的upgrade写入数据库
                                commandSender.sendMessage("购买"+amount+"件强化石成功！");
                            }
                            else if(false){
                                commandSender.sendMessage("余额不足，购买强化石失败，请充值！");
                            }
                        }
                        else if(strings[0].equalsIgnoreCase("effects")){
                            if(true){//如果effects_money*amount<=gold
                                //gold=gold-effects_money*amount;
                                //将gold和amount数量的effects写入数据库
                                commandSender.sendMessage("购买"+amount+"件装扮石成功！");
                            }
                            else if(false){
                                commandSender.sendMessage("余额不足，购买装扮石失败，请充值！");
                            }
                        }
                        else{
                            commandSender.sendMessage("请正确输入指令购买的物品：sign(补签卡)、upgrade(强化石)、effects(装扮石)");
                        }
                    }
                    else{
                        commandSender.sendMessage("请输入正确的物品数量！");
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
        return true;
    }
}
