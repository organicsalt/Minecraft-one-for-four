package com.organicsalt.minecraft.commands;

import com.organicsalt.minecraft.dao.SQLiteManager;
import com.organicsalt.minecraft.main;
import com.organicsalt.minecraft.util.VIPBonus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class vip implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("vip")){
                Player player = (Player)commandSender;
                if(strings.length==2){
                    if(strings[0].equalsIgnoreCase("bonus")){
                        int amount = Integer.parseInt(strings[1]);
                        if(amount>0&&amount<14){
                            new BukkitRunnable(){
                                @Override
                                public void run() {
                                    ResultSet rs= SQLiteManager.get().findVIPData(player.getName());
                                    try {
                                        int level = rs.getInt("level");
                                        int point = rs.getInt("point");
                                        int gift = rs.getInt("gift");
                                        if((gift&((int)Math.pow(2,amount-1)))==0){//如果没被领取
                                            if(level >= amount) {//如果可以领取
                                                switch (amount){
                                                    case 1:{
                                                        VIPBonus.getBonus(player, 1,0,0);
                                                        break;
                                                    }
                                                    case 2:{
                                                        VIPBonus.getBonus(player, 2,0,0);
                                                        break;
                                                    }
                                                    case 3:{
                                                        VIPBonus.getBonus(player, 5,0,0);
                                                        break;
                                                    }
                                                    case 4:{
                                                        VIPBonus.getBonus(player, 2,1,0);
                                                        break;
                                                    }
                                                    case 5:{
                                                        VIPBonus.getBonus(player, 0,2,0);
                                                        break;
                                                    }
                                                    case 6:{
                                                        VIPBonus.getBonus(player, 0,5,0);
                                                        break;
                                                    }
                                                    case 7:{
                                                        VIPBonus.getBonus(player, 2,0,1);
                                                        break;
                                                    }
                                                    case 8:{
                                                        VIPBonus.getBonus(player, 0,2,1);
                                                        break;
                                                    }
                                                    case 9:{
                                                        VIPBonus.getBonus(player, 0,5,1);
                                                        break;
                                                    }
                                                    case 10:{
                                                        VIPBonus.getBonus(player, 2,0,2);
                                                        break;
                                                    }
                                                    case 11:{
                                                        VIPBonus.getBonus(player, 0,5,2);
                                                        break;
                                                    }
                                                    case 12:{
                                                        VIPBonus.getBonus(player, 0,10,5);
                                                        break;
                                                    }
                                                    case 13:{
                                                        VIPBonus.getBonus(player, 0,20,5);
                                                        break;
                                                    }
                                                }
                                                gift = gift + (int)Math.pow(2,amount-1);
                                                SQLiteManager.get().updateVIP(level, point, gift, player.getName());
                                                commandSender.sendMessage("领取VIP" + amount + "奖励成功！");
                                            }
                                            else{
                                                commandSender.sendMessage("该阶段奖励无法领取！");
                                            }
                                        }
                                        else{
                                            commandSender.sendMessage("VIP"+amount+"奖励已领取！");
                                        }
                                    }catch(SQLException e){
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            commandSender.sendMessage("请输入正确的VIP级别!");
                        }
                    }
                    else{
                        commandSender.sendMessage("请正确输入指令！");
                        return false;
                    }
                    return true;
                }
                else if(strings.length==1){
                    if(strings[0].equalsIgnoreCase("grade")){
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                ResultSet rs= SQLiteManager.get().findVIPData(player.getName());
                                try {
                                    if (rs.next()) {
                                        int vip_point = rs.getInt("point");
                                        commandSender.sendMessage("你有§e"+vip_point+"§f点VIP值！");
                                    }
                                }catch(SQLException e){
                                    e.printStackTrace();
                                }
                            }
                        }.runTaskAsynchronously(main.plugin);
                    }
                    else if(strings[0].equalsIgnoreCase("level")){
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                ResultSet rs= SQLiteManager.get().findVIPData(player.getName());
                                try {
                                    if (rs.next()) {
                                        int vip_level= rs.getInt("level");
                                        commandSender.sendMessage("你当前为VIP§e"+vip_level+"§f！");
                                    }
                                }catch(SQLException e){
                                    e.printStackTrace();
                                }
                            }
                        }.runTaskAsynchronously(main.plugin);
                    }
                    return true;
                }
                else{
                    commandSender.sendMessage("参数过多或不足");
                }
            }
        }
        else{
            commandSender.sendMessage("你没有这个功能！");
        }
        return false;
    }
}
