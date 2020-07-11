package com.organicsalt.minecraft.commands;

import com.organicsalt.minecraft.GUI.SignGUI;
import com.organicsalt.minecraft.GUI.storeInventory;
import com.organicsalt.minecraft.dao.SQLiteManager;
import com.organicsalt.minecraft.main;
import com.organicsalt.minecraft.util.VIPBonus;
import lk.vexview.api.VexViewAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class sign_in implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("sign_in")){
                if(strings.length==0) {
                    Calendar cal = Calendar.getInstance();
                    int day = cal.get(Calendar.DATE);
                    Player player = (Player) commandSender;
                    List<Integer> dates = new ArrayList<>();
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            try {
                                String name = player.getName();
                                ResultSet rs = SQLiteManager.get().findSignData(name);
                                while (rs.next()) {
                                    dates.add(rs.getInt("date"));
                                }
                                if (dates.contains(day)) {
                                    player.sendMessage("今天你已经签过到了!");
                                } else {
                                    SQLiteManager.get().insertData(name, day);
                                    player.sendMessage("签到成功！");
                                    if (day == 1 || day == 4 || day == 8 || day == 11 || day == 15 || day == 18 || day == 22 || day == 25 || day == 29) {
                                        VIPBonus.getBonus(player, 1, 0, 0);
                                    } else if (day == 2 || day == 3 || day == 5 || day == 6 || day == 9 || day == 10 || day == 12 || day == 13 || day == 16 || day == 17 || day == 19 || day == 20 || day == 23 || day == 24 || day == 26 || day == 27 || day == 30 || day == 31) {
                                        VIPBonus.getBonus(player, 0, 1, 0);
                                    } else if (day == 7 || day == 21) {
                                        VIPBonus.getBonus(player, 0, 2, 0);
                                    } else if (day == 14 || day == 28) {
                                        VIPBonus.getBonus(player, 0, 0, 1);
                                    }
                                    player.sendMessage("当月" + day + "日签到奖励已获取！");
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
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }.runTaskAsynchronously(main.plugin);
                    return true;
                }
                else{
                    commandSender.sendMessage("参数过多或不足！");
                }
            }
        }
        else{
            commandSender.sendMessage("你没有这个功能！");
        }
        return false;
    }
}
