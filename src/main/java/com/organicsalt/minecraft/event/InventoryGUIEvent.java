package com.organicsalt.minecraft.event;

import com.organicsalt.minecraft.GUI.effectInventory;
import com.organicsalt.minecraft.GUI.storeInventory;
import com.organicsalt.minecraft.main;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryGUIEvent implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void InventoryStoreClick(InventoryClickEvent event){
        Inventory inventory=event.getInventory();
        String title=inventory.getTitle();
        if(title.equalsIgnoreCase(storeInventory.storeGui)){
            HumanEntity whoClicked=event.getWhoClicked();
            Player player=(Player)whoClicked;
            int rawslot=event.getRawSlot();
            //main.plugin.getLogger().info((String.valueOf(rawslot)));
            if(rawslot==1||rawslot==4||rawslot==7||rawslot==49){
                event.setCancelled(true);
                /*
                //连接数据库查询物品价格sign_money|upgrade_money|effects_money,和玩家现有金钱money
                if(strings[0].equalsIgnoreCase("sign")){
                    //sql="select amount from itemInBag where UUID='" + id + "' and item='stamps'"; //获取现有点券money
                    if(true){//如果1*amount<=money
                        //sql="select amount from itemInBag where UUID='" + id + "' and item='complement_card'";  //查询玩家现有补签卡张数complement_card
                        //将money和amount数量的sign写入数据库
                        //complement_card=complement_card+amount;
                        //money=money-1*amount;
                        //sql="update itemInBag set amount = "+complement_card+" where UUID='" + id + " and item='complement_card'";
                        //sql="update itemInBag set amount = "+money+" where UUID='" + id + " and item='stamps'";
                    }
                    else if(false){
                        commandSender.sendMessage("余额不足，购买补签卡失败，请充值！");
                    }
                }
                else if(strings[0].equalsIgnoreCase("upgrade")){
                    //sql="select amount from itemInBag where UUID='" + id + "' and item='stamps'"; //获取现有点券money
                    if(true){//如果5*amount<=money
                        //sql="select amount from itemInBag where UUID='" + id + "' and item='upgrade_stone'";  //查询玩家现有强化石个数upgrade_stone
                        //将money和amount数量的upgrade_stone写入数据库
                        //upgrade_stone=upgrade_stone+amount;
                        //money=money-5*amount;
                        //sql="update itemInBag set amount = "+upgrade_stone+" where UUID='" + id + " and item='upgrade_stone'";
                        //sql="update itemInBag set amount = "+money+" where UUID='" + id + " and item='stamps'";
                    }
                    else if(false){
                        commandSender.sendMessage("余额不足，购买强化石失败，请充值！");
                    }
                }
                else if(strings[0].equalsIgnoreCase("effects")){
                    //sql="select amount from itemInBag where UUID='" + id + "' and item='stamps'"; //获取现有点券money
                    if(true){//如果20*amount<=money
                        //sql="select amount from itemInBag where UUID='" + id + "' and item='effects_stone'";  //查询玩家现有强化石个数effects_stone
                        //将money和amount数量的effects_stone写入数据库
                        //effects_stone=effects_stone+amount;
                        //money=money-20*amount;
                        //sql="update itemInBag set amount = "+money+" where UUID='" + id + " and item='effects_stone'";
                        //sql="update itemInBag set amount = "+money+" where UUID='" + id + " and item='stamps'";
                    }
                    else if(false){
                        commandSender.sendMessage("余额不足，购买装扮石失败，请充值！");
                    }
                }

            */
                if(rawslot==49){
                    player.sendMessage("退出商城");
                    player.closeInventory();
                }
            }
            else{
                event.setCancelled(true);
                player.sendMessage("这个不能点击！");
            }
        }
        else if(title.equalsIgnoreCase(effectInventory.effectGui)){
            HumanEntity whoClicked=event.getWhoClicked();
            Player player=(Player)whoClicked;
            int rawslot=event.getRawSlot();
            main.plugin.getLogger().info((String.valueOf(rawslot)));
            if(rawslot>=0&&rawslot<=53&&rawslot!=22) {
                event.setCancelled(true);
                if(rawslot==47){
                    //开启赋予武器特效界面
                    //查看背包内是否有装扮石
                    //sql="select status from weapon_effect where UUID='" + id + "' and weapon='" + strings[1] + "'"; //获取武器特效开启状况
                    //sql="select amount from itemInBag where UUID='" + id + "' and item='effects_stone'";  //查询玩家现有强化石个数effects_stone
                    if (true) {//如果能够开启特效，那么消耗一个装扮石
                        player.sendMessage( "特效赋予成功!");
                        //数据库中写入strings[1]的武器特效信息，背包中装扮石数量-1
                        //产生随机特效random_effect
                        //effects_stone=effects_stone-1;
                        //sql="insert into weapon_effect values('" + strings[1] + "', '" + random_effect + "', '"+ id + "', 1)";
                        //sql="update itemInBag set amount = "+effects_stone+" where UUID='" + id + " and item='effects_stone'";
                    } else if (false) {//如果没有装扮石，那么输出装扮石余额不足
                        player.sendMessage("装扮石余额不足!");
                    } else if (false) {//如果不能赋予武器特效，那么输出该武器已经赋予特效
                        player.sendMessage("你已经赋予特效!");
                    } else {
                        player.sendMessage("武器名称不存在！");
                    }
                }
                else if(rawslot==51){
                    player.sendMessage("退出商城");
                    player.closeInventory();
                }
                else{
                    player.sendMessage("这个不能点击");
                }
            }
        }
    }
}
