package com.organicsalt.minecraft.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class buy implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("buy")){
                if(strings.length==2){
                    Player player = (Player) commandSender;
                    //连接数据库查询物品价格sign_money|upgrade_money|effects_money,和玩家现有金钱money
                    int amount = Integer.parseInt(strings[1]);
                    if(amount>0){
                        if(strings[0].equalsIgnoreCase("sign")){
                            if(true){//如果sign_money*amount<=gold
                                //gold=gold-sign_money*amount;
                                //将gold和amount数量的sign写入数据库
                                ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
                                ItemMeta itemMeta = itemStack.getItemMeta();
                                itemMeta.setDisplayName("§e这是一个补签卡");
                                itemMeta.setLore(Arrays.asList("§b该补签卡可以用来补签","§6输入/complement_sign [date]命令进行补签","§4补签之后可以领取签到奖励","§2补签之后还可以领取累计签到奖励"));
                                itemStack.setItemMeta(itemMeta);
                                itemStack.setAmount(amount);
                                player.getInventory().addItem(itemStack);
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
                                ItemStack itemStack = new ItemStack(Material.DIAMOND);
                                ItemMeta itemMeta = itemStack.getItemMeta();
                                itemMeta.setDisplayName("§e这是一个强化石");
                                itemMeta.setLore(Arrays.asList("§b该石头可以用来强化武器","§6输入/weapon_upgrade [weapon]命令进行强化","§4强化有可能失败","§2武器强化等级越高强化成功率越低"));
                                itemStack.setItemMeta(itemMeta);
                                itemStack.setAmount(amount);
                                player.getInventory().addItem(itemStack);
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
                                ItemStack itemStack=new ItemStack(Material.FLINT);
                                ItemMeta itemMeta = itemStack.getItemMeta();
                                itemMeta.setDisplayName("§e这是一个装扮石");
                                itemMeta.setLore(Arrays.asList("§b该石头可以用来开启武器特效","§6输入/weapon_effects [on/off/up] [weapon]命令进行开启","§4用装扮石赋予武器特效只能覆盖无法叠加","§2武器不同强化等级自身特效不一样"));
                                itemStack.setItemMeta(itemMeta);
                                itemStack.setAmount(amount);
                                player.getInventory().addItem(itemStack);
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
