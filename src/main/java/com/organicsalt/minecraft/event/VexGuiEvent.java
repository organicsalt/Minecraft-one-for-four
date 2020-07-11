package com.organicsalt.minecraft.event;

import com.organicsalt.minecraft.GUI.SignGUI;
import com.organicsalt.minecraft.dao.SQLiteManager;
import com.organicsalt.minecraft.main;
import com.organicsalt.minecraft.util.VIPBonus;
import lk.vexview.api.VexViewAPI;
import lk.vexview.event.ButtonClickEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class VexGuiEvent implements Listener {
    @EventHandler
    public void PlayerClickButton(ButtonClickEvent event){
        int buttonID=(int)event.getButtonID();
        if(buttonID==15||buttonID==18){
            event.getGui().setClosable(true);
            event.getPlayer().closeInventory();
        }
        else if(buttonID==16){
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DATE);
            Player player=event.getPlayer();
            List<Integer> dates=new ArrayList<>();
            new BukkitRunnable(){
                @Override
                public void run() {
                    try {
                        String name = player.getName();
                        ResultSet rs = SQLiteManager.get().findSignData(name);
                        while (rs.next()) {
                            dates.add(rs.getInt("date"));
                        }
                        if(dates.contains(day)){
                            player.sendMessage("今天你已经签过到了!");
                        }
                        else{
                            SQLiteManager.get().insertData(name,day);
                            player.sendMessage("签到成功！");
                            if(day==1||day==4||day==8||day==11||day==15||day==18||day==22||day==25||day==29){
                                VIPBonus.getBonus(player,1,0,0);
                            }
                            else if(day==2||day==3||day==5||day==6||day==9||day==10||day==12||day==13||day==16||day==17||day==19||day==20||day==23||day==24||day==26||day==27||day==30||day==31){
                                VIPBonus.getBonus(player,0,1,0);
                            }
                            else if(day==7||day==21){
                                VIPBonus.getBonus(player,0,2,0);
                            }
                            else if(day==14||day==28){
                                VIPBonus.getBonus(player,0,0,1);
                            }
                            player.sendMessage("当月"+day+"日签到奖励已获取！");
                            if(dates.size()==6){
                                VIPBonus.getBonus(player,0,1,0);
                                player.sendMessage("7日累计签到奖励已获取！");
                            }
                            else if(dates.size()==13){
                                VIPBonus.getBonus(player,0,2,0);
                                player.sendMessage("14日累计签到奖励已获取！");
                            }
                            else if(dates.size()==20){
                                VIPBonus.getBonus(player,0,3,0);
                                player.sendMessage("21日累计签到奖励已获取！");
                            }
                            else if(dates.size()==27){
                                VIPBonus.getBonus(player,0,0,1);
                                player.sendMessage("28日累计签到奖励已获取！");
                            }

                            event.getGui().setClosable(true);
                            player.closeInventory();
                            VexViewAPI.openGui(player, SignGUI.SignGUI(player));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==17){
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DATE);
            Player player=event.getPlayer();
            List<Integer> dates=new ArrayList<>();
            new BukkitRunnable(){
                @Override
                public void run() {
                    try {
                        String name = player.getName();
                        ResultSet rs = SQLiteManager.get().findSignData(name);
                        while (rs.next()) {
                            dates.add(rs.getInt("date"));
                        }
                        if(dates.size()==day){
                            player.sendMessage("你没有可以补签的日子!");
                        }
                        else{
                            Inventory inventory=player.getInventory();
                            int count=0;
                            for (ItemStack itemStack:inventory.getStorageContents()) {
                                if(itemStack!=null){
                                    if(itemStack.hasItemMeta()) {
                                        if(itemStack.getItemMeta().getDisplayName().equals("§e这是补签卡")){
                                            count=count+itemStack.getAmount();
                                        }
                                    }
                                }
                            }
                            if(count>0) {
                                int least = 0;
                                for (int i = 1; i <= day; i++) {
                                    if (!dates.contains(i)) {
                                        least = i;
                                        break;
                                    }
                                }
                                SQLiteManager.get().insertData(name, least);
                                player.sendMessage("第" + least + "日补签成功！");
                                ItemStack itemStack = new ItemStack(Material.GOLD_INGOT);
                                ItemMeta itemMeta = itemStack.getItemMeta();
                                itemMeta.setDisplayName("§e这是补签卡");
                                itemMeta.setLore(Arrays.asList("§b该补签卡可以用来补签","§6输入/complement_sign [date]命令进行补签","§4补签之后可以领取签到奖励","§2补签之后还可以领取累计签到奖励"));
                                itemStack.setItemMeta(itemMeta);
                                itemStack.setAmount(1);
                                player.getInventory().removeItem(itemStack);
                                if (least == 1 || least == 4 || least == 8 || least == 11 || least == 15 || least == 18 || least == 22 || least == 25 || least == 29) {
                                    VIPBonus.getBonus(player, 1, 0, 0);
                                } else if (least == 2 || least == 3 || least == 5 || least == 6 || least == 9 || least == 10 || least == 12 || least == 13 || least == 16 || least == 17 || least == 19 || least == 20 || least == 23 || least == 24 || least == 26 || least == 27 || least == 30 || least == 31) {
                                    VIPBonus.getBonus(player, 0, 1, 0);
                                } else if (least == 7 || least == 21) {
                                    VIPBonus.getBonus(player, 0, 2, 0);
                                } else if (least == 14 || least == 28) {
                                    VIPBonus.getBonus(player, 0, 0, 1);
                                }
                                player.sendMessage("当月" + least + "日签到奖励已获取！");
                                if (dates.size() == 6) {
                                    VIPBonus.getBonus(player, 0, 1, 0);
                                    player.sendMessage("7日累计签到奖励已获取！");
                                } else if (dates.size() == 13) {
                                    VIPBonus.getBonus(player, 0, 2, 0);
                                    player.sendMessage("14日累计签到奖励已获取！");
                                } else if (dates.size() == 20) {
                                    VIPBonus.getBonus(player, 0, 3, 0);
                                    player.sendMessage("21日累计签到奖励已获取！");
                                } else if (dates.size() == 27) {
                                    VIPBonus.getBonus(player, 0, 0, 1);
                                    player.sendMessage("28日累计签到奖励已获取！");
                                }

                                event.getGui().setClosable(true);
                                player.closeInventory();
                                VexViewAPI.openGui(player, SignGUI.SignGUI(player));
                            }
                            else{
                                player.sendMessage("补签卡数量不足！");
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else{
            //event.getPlayer().sendMessage("buttonid:"+buttonID);
        }
    }
}
