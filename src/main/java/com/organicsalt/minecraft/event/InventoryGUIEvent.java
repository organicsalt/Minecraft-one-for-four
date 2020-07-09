package com.organicsalt.minecraft.event;

import com.organicsalt.minecraft.GUI.effectInventory;
import com.organicsalt.minecraft.GUI.storeInventory;
import com.organicsalt.minecraft.GUI.upgradeInventory;
import com.organicsalt.minecraft.main;
import com.organicsalt.minecraft.util.PlayerPointsUtil;
import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

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
            if((rawslot>=0&&rawslot<=8)||rawslot==49){
                event.setCancelled(true);
                if(rawslot==0){
                    PlayerPoints points=PlayerPointsUtil.getPlayerPoints();
                    int complement=main.plugin.getConfig().getInt("complement");
                    if(points.getAPI().take(player.getUniqueId(),1*complement)){
                        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName("§e这是补签卡");
                        itemMeta.setLore(Arrays.asList("§b该补签卡可以用来补签","§6输入/complement_sign [date]命令进行补签","§4补签之后可以领取签到奖励","§2补签之后还可以领取累计签到奖励"));
                        itemStack.setItemMeta(itemMeta);
                        itemStack.setAmount(1);
                        player.getInventory().addItem(itemStack);
                        player.sendMessage("购买§e1§f个§9补签卡§f成功！");
                    }
                    else{
                        player.sendMessage("账户余额不足！");
                    }
                }
                else if(rawslot==1){
                    PlayerPoints points=PlayerPointsUtil.getPlayerPoints();
                    int complement=main.plugin.getConfig().getInt("complement");
                    if(points.getAPI().take(player.getUniqueId(),10*complement)){
                        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName("§e这是补签卡");
                        itemMeta.setLore(Arrays.asList("§b该补签卡可以用来补签","§6输入/complement_sign [date]命令进行补签","§4补签之后可以领取签到奖励","§2补签之后还可以领取累计签到奖励"));
                        itemStack.setItemMeta(itemMeta);
                        itemStack.setAmount(10);
                        player.getInventory().addItem(itemStack);
                        player.sendMessage("购买§e10§f个§9补签卡§f成功！");
                    }
                    else{
                        player.sendMessage("账户余额不足！");
                    }
                }
                else if(rawslot==2){
                    PlayerPoints points=PlayerPointsUtil.getPlayerPoints();
                    int complement=main.plugin.getConfig().getInt("complement");
                    if(points.getAPI().take(player.getUniqueId(),64*complement)){
                        ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName("§e这是补签卡");
                        itemMeta.setLore(Arrays.asList("§b该补签卡可以用来补签","§6输入/complement_sign [date]命令进行补签","§4补签之后可以领取签到奖励","§2补签之后还可以领取累计签到奖励"));
                        itemStack.setItemMeta(itemMeta);
                        itemStack.setAmount(64);
                        player.getInventory().addItem(itemStack);
                        player.sendMessage("购买§e64§f个§9补签卡§f成功！");
                    }
                    else{
                        player.sendMessage("账户余额不足！");
                    }
                }
                else if(rawslot==3){
                    PlayerPoints points=PlayerPointsUtil.getPlayerPoints();
                    int upgrade=main.plugin.getConfig().getInt("upgrade");
                    if(points.getAPI().take(player.getUniqueId(),1*upgrade)){
                        ItemStack itemStack = new ItemStack(Material.DIAMOND);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName("§e这是强化石");
                        itemMeta.setLore(Arrays.asList("§b该石头可以用来强化武器","§6输入/weapon_upgrade 命令进行强化","§4强化有可能失败","§2武器强化等级越高强化成功率越低"));
                        itemStack.setItemMeta(itemMeta);
                        itemStack.setAmount(1);
                        player.getInventory().addItem(itemStack);
                        player.sendMessage("购买§e1§f个§9强化石§f成功！");
                    }else{
                        player.sendMessage("账户余额不足！");
                    }

                }
                else if(rawslot==4){
                    PlayerPoints points=PlayerPointsUtil.getPlayerPoints();
                    int upgrade=main.plugin.getConfig().getInt("upgrade");
                    if(points.getAPI().take(player.getUniqueId(),10*upgrade)){
                        ItemStack itemStack = new ItemStack(Material.DIAMOND);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName("§e这是强化石");
                        itemMeta.setLore(Arrays.asList("§b该石头可以用来强化武器","§6输入/weapon_upgrade 命令进行强化","§4强化有可能失败","§2武器强化等级越高强化成功率越低"));
                        itemStack.setItemMeta(itemMeta);
                        itemStack.setAmount(10);
                        player.getInventory().addItem(itemStack);
                        player.sendMessage("购买§e10§f个§9强化石§f成功！");
                    }
                    else{
                        player.sendMessage("账户余额不足！");
                    }
                }
                else if(rawslot==5){
                    PlayerPoints points=PlayerPointsUtil.getPlayerPoints();
                    int upgrade=main.plugin.getConfig().getInt("upgrade");
                    if(points.getAPI().take(player.getUniqueId(),64*upgrade)){
                        ItemStack itemStack = new ItemStack(Material.DIAMOND);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName("§e这是强化石");
                        itemMeta.setLore(Arrays.asList("§b该石头可以用来强化武器","§6输入/weapon_upgrade 命令进行强化","§4强化有可能失败","§2武器强化等级越高强化成功率越低"));
                        itemStack.setItemMeta(itemMeta);
                        itemStack.setAmount(64);
                        player.getInventory().addItem(itemStack);
                        player.sendMessage("购买§e64§f个§9强化石§f成功！");
                    }
                    else{
                        player.sendMessage("账户余额不足！");
                    }
                }
                else if(rawslot==6){
                    PlayerPoints points=PlayerPointsUtil.getPlayerPoints();
                    int effect=main.plugin.getConfig().getInt("effect");
                    if(points.getAPI().take(player.getUniqueId(),1*effect)){
                        ItemStack itemStack=new ItemStack(Material.FLINT);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName("§e这是装扮石");
                        itemMeta.setLore(Arrays.asList("§b该石头可以用来开启武器特效","§6输入/weapon_effects up命令进行开启","§4用装扮石赋予武器特效只能覆盖无法叠加","§2武器不同强化等级自身特效不一样"));
                        itemStack.setItemMeta(itemMeta);
                        itemStack.setAmount(1);
                        player.getInventory().addItem(itemStack);
                        player.sendMessage("购买§e1§f个§9装扮石§f成功！");
                    }
                    else{
                        player.sendMessage("账户余额不足！");
                    }
                }
                else if(rawslot==7){
                    PlayerPoints points=PlayerPointsUtil.getPlayerPoints();
                    int effect=main.plugin.getConfig().getInt("effect");
                    if(points.getAPI().take(player.getUniqueId(),10*effect)){
                        ItemStack itemStack=new ItemStack(Material.FLINT);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName("§e这是装扮石");
                        itemMeta.setLore(Arrays.asList("§b该石头可以用来开启武器特效","§6输入/weapon_effects up命令进行开启","§4用装扮石赋予武器特效只能覆盖无法叠加","§2武器不同强化等级自身特效不一样"));
                        itemStack.setItemMeta(itemMeta);
                        itemStack.setAmount(10);
                        player.getInventory().addItem(itemStack);
                        player.sendMessage("购买§e10§f个§9装扮石§f成功！");
                    }
                    else{
                        player.sendMessage("账户余额不足！");
                    }
                }
                else if(rawslot==8){
                    PlayerPoints points=PlayerPointsUtil.getPlayerPoints();
                    int effect=main.plugin.getConfig().getInt("effect");
                    if(points.getAPI().take(player.getUniqueId(),64*effect)){
                        ItemStack itemStack=new ItemStack(Material.FLINT);
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        itemMeta.setDisplayName("§e这是装扮石");
                        itemMeta.setLore(Arrays.asList("§b该石头可以用来开启武器特效","§6输入/weapon_effects up命令进行开启","§4用装扮石赋予武器特效只能覆盖无法叠加","§2武器不同强化等级自身特效不一样"));
                        itemStack.setItemMeta(itemMeta);
                        itemStack.setAmount(64);
                        player.getInventory().addItem(itemStack);
                        player.sendMessage("购买§e64§f个§9装扮石§f成功！");
                    }
                    else{
                        player.sendMessage("账户余额不足！");
                    }
                }
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
            //main.plugin.getLogger().info((String.valueOf(rawslot)));
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
        else if(title.equalsIgnoreCase(upgradeInventory.upgradeGui)){
            HumanEntity whoClicked=event.getWhoClicked();
            Player player=(Player)whoClicked;
            int rawslot=event.getRawSlot();
            //main.plugin.getLogger().info((String.valueOf(rawslot)));
            if(rawslot>=0&&rawslot<=53&&rawslot!=22) {
                event.setCancelled(true);
                if(rawslot==47) {
                    //链接数据库查询背包中强化石的数量
                    //sql="select amount from itemInBag where UUID='" + id + "' and item='upgrade_stone'";  //查询玩家现有强化石个数upgrade_stone
                    //sql="select level from weapon_upgrade where UUID='" + id + "' and weapon='" + strings[1] + "'"; //查询需要强化的武器等级level 记得要设置displayname
                    if(true) {//22格里面有武器
                        if (true) {//如果强化石的数量足够
                            //如果武器没有强化等级
                            //sql="insert into weapon_upgrade values('" + strings[1] + "', 0, '"+ id + "')";
                            //随机函数判断是否强化成功
                            //对应数量的强化石销毁
                            //upgrade_stone=upgrade_stone-level*2;
                            //sql="update itemInBag set amount = "+upgrade_stone+" where UUID='" + id + " and item='upgrade_stone'";
                            if (true) {//如果强化成功
                                //强化成功武器等级+1
                                //level=level+1;
                                //sql="update weapon_upgrade set level = "+ level +" where UUID='" + id + " and weapon='" + strings[1] + "'";
                                player.sendMessage("强化成功！");
                                //如果是+7以上强化成功将在工会内部广播
                            } else {
                                player.sendMessage("强化失败！");
                                //强化失败武器等级不变
                            }
                        } else {
                            player.sendMessage("强化石数量不足！");
                        }
                    }
                    else{
                        player.sendMessage("武器不存在！");
                    }
                }
                else if(rawslot==51){
                    player.sendMessage("退出强化");
                    player.closeInventory();
                }
                else{
                    player.sendMessage("这个不能点击");
                }

            }
        }
    }
}
