package com.organicsalt.minecraft.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class invest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("invest")){
                if(strings.length==1){
                    //生成付款二维码并显示在玩家界面
                    if(true){//玩家付款成功后
                        int amount = Integer.parseInt(strings[0]);
                        Player player=(Player)commandSender;
                        UUID id=player.getUniqueId();
                        if(amount>0) {
                            //在数据库中查询对应的玩家现有金钱money
                            //sql="select amount from itemInBag where UUID='" + id + "' and item='stamps'"; //获取现有点券money
                            //sql="select vip_point from vip where UUID='" + id + "'";  //查询vip点数vip_point
                            ItemStack itemStack = new ItemStack(Material.GOLD_BLOCK);
                            ItemMeta itemMeta = itemStack.getItemMeta();
                            itemMeta.setDisplayName("§e这是点券");
                            itemMeta.setLore(Arrays.asList("§b该物品可以用来购买付费道具"));
                            itemStack.setItemMeta(itemMeta);
                            itemStack.setAmount(amount);
                            player.getInventory().addItem(itemStack);
                            commandSender.sendMessage("充值" + amount + "点卡成功！");
                            //money=money+amount;
                            //vip_point=vip_point+amount;
                            //将新的money信息写入数据库
                            //更新玩家vip_grade信息
                            //更新玩家vip_level信息
                            //sql="update itemInBag set amount = "+money+" where UUID='" + id + " and item='stamps'";
                            //sql="update vip set vip_point = "+vip_point+" where UUID='" + id + "'";
                            /*
                            int vip_level=0;
                            if(vip_point>=50000){
                                vip_level=13;
                            }
                            else if(vip_point>=20000){
                                vip_level=12;
                            }
                            else if(vip_point>=10000){
                                vip_level=11;
                            }
                            else if(vip_point>=5000){
                                vip_level=10；
                            }
                            else if(vip_point>=2000){
                                vip_level=9；
                            }
                            else if(vip_point>=1000){
                                vip_level=8；
                            }
                            else if(vip_point>=500){
                                vip_level=7；
                            }
                            else if(vip_point>=200){
                                vip_level=6；
                            }
                            else if(vip_point>=100){
                                vip_level=5；
                            }else if(vip_point>=50){
                                vip_level=4；
                            }else if(vip_point>=20){
                                vip_level=3；
                            }else if(vip_point>=10){
                                vip_level=2；
                            }
                            else if(vip_point>=1){
                                vip_level=1；
                            }
                            */
                            //sql="update vip set vip_level = "+vip_level+" where UUID='" + id + "'";

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
