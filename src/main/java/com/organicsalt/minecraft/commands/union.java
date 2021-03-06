package com.organicsalt.minecraft.commands;

import com.organicsalt.minecraft.GUI.UnionGUI;
import com.organicsalt.minecraft.dao.SQLiteManager;
import com.organicsalt.minecraft.main;
import lk.vexview.api.VexViewAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class union implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if (s.equalsIgnoreCase("union")){
                Player player = (Player)commandSender;
                UUID id = player.getUniqueId();
                if(strings.length==0){
                    if(player.hasPermission("union")) {
                        VexViewAPI.openGui(player, UnionGUI.UnionListGUI(player));
                    }
                    else{
                        player.sendMessage("你没有权限！");
                    }
                    return true;
                }
                else if(strings.length==2){
                    if(strings[0].equalsIgnoreCase("create")){
                        if(player.hasPermission("union.create")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionData(strings[1]);
                                    try {
                                        if (rs.next()) {
                                            player.sendMessage("该公会名重复！");
                                        } else {
                                            rs = SQLiteManager.get().findUnionDutyData(player.getName());
                                            if (rs.next()) {
                                                player.sendMessage("该玩家已有公会！");
                                            } else {
                                                double x = player.getLocation().getX();
                                                double y = player.getLocation().getY();
                                                double z = player.getLocation().getZ();
                                                SQLiteManager.get().insertData(strings[1], null, player.getName(), x, y, z);
                                                SQLiteManager.get().insertData(player.getName(), strings[1], 4);
                                                player.sendMessage("创建公会成功!");
                                            }
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("leave")){
                        if(player.hasPermission("union.leave")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionDutyData(player.getName());
                                    try {
                                        if (rs.next()) {
                                            String union = rs.getString("union");
                                            int duty = rs.getInt("duty");
                                            if (union.equals(strings[1])) {
                                                if (duty != 4) {
                                                    SQLiteManager.get().deleteUnionData(player.getName(), 1);
                                                    player.sendMessage("§e" + player.getName() + "退出" + "§9" + strings[1] + "§f公会成功！");
                                                } else {
                                                    player.sendMessage("会长无法退出公会！");
                                                }
                                            } else {
                                                player.sendMessage("你不属于这个公会!");
                                            }
                                        } else {
                                            player.sendMessage("你没有公会!");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("join")){
                        if(player.hasPermission("union.join")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionDutyData(player.getName());
                                    try {
                                        if (rs.next()) {
                                            String union = rs.getString("union");
                                            int duty = rs.getInt("duty");
                                            if (union.equals(strings[1]) && duty >= 1) {
                                                player.sendMessage("你已经属于这个公会!");
                                            } else if (union.equals(strings[1]) && duty == 0) {
                                                player.sendMessage("你已经申请了该公会！");
                                            } else if (duty == 0) {
                                                player.sendMessage("你已经申请了其他公会！");
                                            } else if (duty >= 1) {
                                                player.sendMessage("你已经有了公会！");
                                            }
                                        } else {
                                            SQLiteManager.get().insertData(player.getName(), strings[1], 0);
                                            player.sendMessage("申请加入" + "§e" + strings[1] + "§f公会成功!");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("dismiss")){
                        if(player.hasPermission("union.dismiss")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionDutyData(player.getName());
                                    try {
                                        if (rs.next()) {
                                            String union = rs.getString("union");
                                            int duty = rs.getInt("duty");
                                            if (union.equals(strings[1])) {
                                                if (duty != 4) {
                                                    player.sendMessage("你没有足够的权限！");
                                                } else {
                                                    ArrayList<String> members = new ArrayList<String>();
                                                    rs = SQLiteManager.get().findUnionMember(strings[1]);
                                                    while (rs.next()) {
                                                        members.add(rs.getString("name"));
                                                    }
                                                    for (String name : members) {
                                                        SQLiteManager.get().deleteUnionData(name, 1);
                                                    }
                                                    SQLiteManager.get().deleteUnionData(strings[1], 0);
                                                    player.sendMessage("公会已解散！");
                                                }
                                            } else {
                                                player.sendMessage("你不属于这个公会!");
                                            }
                                        } else {
                                            player.sendMessage("你没有公会!");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("info")){
                        if(player.hasPermission("union.info")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionData(strings[1]);
                                    try {
                                        if (rs.next()) {
                                            String announce = rs.getString("announcement");
                                            String name = rs.getString("name");
                                            double x = rs.getDouble("x");
                                            double y = rs.getDouble("y");
                                            double z = rs.getDouble("z");
                                            player.sendMessage(strings[1] + "公会信息如下:");
                                            player.sendMessage("会长-" + name);
                                            player.sendMessage("公会公告-" + announce);
                                            player.sendMessage("公会据点坐标-");
                                            player.sendMessage("x-" + x);
                                            player.sendMessage("y-" + y);
                                            player.sendMessage("z-" + z);
                                        } else {
                                            player.sendMessage("该名字的公会不存在!");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("upgrade")){
                        if(player.hasPermission("union.upgrade")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionDutyData(strings[1]);
                                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                                    try {
                                        if (rs.next()) {
                                            if (rs2.next()) {
                                                String union1 = rs.getString("union");
                                                String union2 = rs2.getString("union");
                                                int duty1 = rs.getInt("duty");
                                                int duty2 = rs2.getInt("duty");
                                                if (union1.equals(union2)) {
                                                    if (duty2 - duty1 > 1) {
                                                        duty1 = duty1 + 1;
                                                        SQLiteManager.get().updateUnionDuty(strings[1], duty1);
                                                        player.sendMessage("§e" + strings[1] + "§f已晋升");
                                                    } else {
                                                        player.sendMessage("你没有足够的权限！");
                                                    }
                                                } else {
                                                    player.sendMessage("§e" + strings[1] + "§f不是你公会的成员！");
                                                }
                                            } else {
                                                player.sendMessage("你没有公会！");
                                            }
                                        } else {
                                            player.sendMessage("§e" + strings[1] + "§f没有公会！");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("demote")){
                        if(player.hasPermission("union.demote")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionDutyData(strings[1]);
                                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                                    try {
                                        if (rs.next()) {
                                            if (rs2.next()) {
                                                String union1 = rs.getString("union");
                                                String union2 = rs2.getString("union");
                                                int duty1 = rs.getInt("duty");
                                                int duty2 = rs2.getInt("duty");
                                                if (union1.equals(union2)) {
                                                    if (duty2 - duty1 >= 1 && duty1 > 1) {
                                                        duty1 = duty1 - 1;
                                                        SQLiteManager.get().updateUnionDuty(strings[1], duty1);
                                                        player.sendMessage("§e" + strings[1] + "§f已降职");
                                                    } else if (duty2 - duty1 <= 1) {
                                                        player.sendMessage("你没有足够的权限！");
                                                    } else {
                                                        player.sendMessage("§e" + strings[1] + "§f无法被降职！");
                                                    }
                                                } else {
                                                    player.sendMessage("§e" + strings[1] + "§f不是你公会的成员！");
                                                }
                                            } else {
                                                player.sendMessage("你没有公会！");
                                            }
                                        } else {
                                            player.sendMessage("§e" + strings[1] + "§f没有公会！");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("kick")){
                        if(player.hasPermission("union.kick")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionDutyData(strings[1]);
                                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                                    try {
                                        if (rs.next()) {
                                            if (rs2.next()) {
                                                String union1 = rs.getString("union");
                                                String union2 = rs2.getString("union");
                                                int duty1 = rs.getInt("duty");
                                                int duty2 = rs2.getInt("duty");
                                                if (union1.equals(union2)) {
                                                    if (duty2 - duty1 > 1 && duty1 >= 1) {
                                                        SQLiteManager.get().deleteUnionData(strings[1], 1);
                                                        player.sendMessage("§e" + strings[1] + "§f已踢出");
                                                    } else if (duty2 - duty1 <= 1) {
                                                        player.sendMessage("你没有足够的权限！");
                                                    } else {
                                                        player.sendMessage("§e" + strings[1] + "§f无法被踢出！");
                                                    }
                                                } else {
                                                    player.sendMessage("§e" + strings[1] + "§f不是你公会的成员！");
                                                }
                                            } else {
                                                player.sendMessage("你没有公会！");
                                            }
                                        } else {
                                            player.sendMessage("§e" + strings[1] + "§f没有公会！");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("accept")){
                        if(player.hasPermission("union.accept")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionDutyData(strings[1]);
                                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                                    try {
                                        if (rs.next()) {
                                            if (rs2.next()) {
                                                String union1 = rs.getString("union");
                                                String union2 = rs2.getString("union");
                                                int duty1 = rs.getInt("duty");
                                                int duty2 = rs2.getInt("duty");
                                                if (union1.equals(union2)) {
                                                    if (duty2 > 1 && duty1 == 0) {
                                                        duty1 = 1;
                                                        SQLiteManager.get().updateUnionDuty(strings[1], duty1);
                                                        player.sendMessage("§e" + strings[1] + "§f申请已通过！");
                                                    } else if (duty2 <= 1) {
                                                        player.sendMessage("你没有足够的权限！");
                                                    } else {
                                                        player.sendMessage("§e" + strings[1] + "§f已经是公会成员！");
                                                    }
                                                } else {
                                                    player.sendMessage("§e" + strings[1] + "§f不是你公会的成员！");
                                                }
                                            } else {
                                                player.sendMessage("你没有公会！");
                                            }
                                        } else {
                                            player.sendMessage("§e" + strings[1] + "§f没有公会！");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("deny")){
                        if(player.hasPermission("union.deny")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionDutyData(strings[1]);
                                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                                    try {
                                        if (rs.next()) {
                                            if (rs2.next()) {
                                                String union1 = rs.getString("union");
                                                String union2 = rs2.getString("union");
                                                int duty1 = rs.getInt("duty");
                                                int duty2 = rs2.getInt("duty");
                                                if (union1.equals(union2)) {
                                                    if (duty2 > 1 && duty1 == 0) {
                                                        SQLiteManager.get().deleteUnionData(strings[1], 1);
                                                        player.sendMessage("§e" + strings[1] + "§f申请已拒绝！");
                                                    } else if (duty2 <= 1) {
                                                        player.sendMessage("你没有足够的权限！");
                                                    } else {
                                                        player.sendMessage("§e" + strings[1] + "§f已经是公会成员！");
                                                    }
                                                } else {
                                                    player.sendMessage("§e" + strings[1] + "§f不是你公会的成员！");
                                                }
                                            } else {
                                                player.sendMessage("你没有公会！");
                                            }
                                        } else {
                                            player.sendMessage("§e" + strings[1] + "§f没有公会！");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("trans")){
                        if(player.hasPermission("union.trans")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionDutyData(strings[1]);
                                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                                    try {
                                        if (rs.next()) {
                                            if (rs2.next()) {
                                                String union1 = rs.getString("union");
                                                String union2 = rs2.getString("union");
                                                int duty1 = rs.getInt("duty");
                                                int duty2 = rs2.getInt("duty");
                                                if (union1.equals(union2)) {
                                                    if (duty2 == 4 && duty1 >= 1) {
                                                        duty1 = 4;
                                                        duty2 = 1;
                                                        SQLiteManager.get().updateUnionDuty(strings[1], duty1);
                                                        SQLiteManager.get().updateUnionDuty(player.getName(), duty2);
                                                        SQLiteManager.get().updateUnionInfoMaster(strings[1], union1);
                                                        player.sendMessage("公会已转让给§e" + strings[1]);
                                                    } else if (duty1 == 0) {
                                                        player.sendMessage("§e" + strings[1] + "§f不是你公会的成员！");
                                                    } else {
                                                        player.sendMessage("你没有足够的权限！");
                                                    }
                                                } else {
                                                    player.sendMessage("§e" + strings[1] + "§f不是你公会的成员！");
                                                }
                                            } else {
                                                player.sendMessage("§e" + strings[1] + "§f不是你公会的成员！");
                                            }
                                        } else {
                                            player.sendMessage("你没有公会！");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("announce")){
                        if(player.hasPermission("union.announce")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionDutyData(player.getName());
                                    try {
                                        if (rs.next()) {
                                            String union = rs.getString("union");
                                            int duty = rs.getInt("duty");
                                            if (duty > 2) {
                                                SQLiteManager.get().updateUnionInfoAnnouncement(strings[1], union);
                                                player.sendMessage("公会公告已发布！");
                                            } else {
                                                player.sendMessage("你没有足够的权限！");
                                            }
                                        } else {
                                            player.sendMessage("你没有公会！");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
                    }
                    else if(strings[0].equalsIgnoreCase("list")) {
                        if(player.hasPermission("union.list")) {
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    ResultSet rs = SQLiteManager.get().findUnionMember(strings[1]);
                                    try {
                                        if (rs.next()) {
                                            String union = rs.getString("union");
                                            ResultSet rs2 = SQLiteManager.get().findUnionMember(union);
                                            player.sendMessage("§e" + union + "§f公会信息如下:");
                                            ArrayList<String> members = new ArrayList<String>();
                                            ArrayList<Integer> duties = new ArrayList<Integer>();
                                            while (rs2.next()) {
                                                members.add(rs2.getString("name"));
                                                duties.add(rs2.getInt("duty"));
                                            }
                                            for (int i = 0; i < members.size(); i++) {
                                                for (int j = i; j < members.size(); j++) {
                                                    if (duties.get(i) < duties.get(j)) {
                                                        int duty = duties.get(j);
                                                        String member = members.get(j);
                                                        duties.set(j, duties.get(i));
                                                        duties.set(i, duty);
                                                        members.set(j, members.get(i));
                                                        members.set(i, member);
                                                    }
                                                }
                                            }
                                            for (int i = 0; i < members.size(); i++) {
                                                switch (duties.get(i)) {
                                                    case 0:
                                                        player.sendMessage("§2申请中:" + "§f" + members.get(i));
                                                        break;
                                                    case 1:
                                                        player.sendMessage("§1成员:" + "§f" + members.get(i));
                                                        break;
                                                    case 2:
                                                        player.sendMessage("§e组长:" + "§f" + members.get(i));
                                                        break;
                                                    case 3:
                                                        player.sendMessage("§4副会长:" + "§f" + members.get(i));
                                                        break;
                                                    case 4:
                                                        player.sendMessage("§5会长:" + "§f" + members.get(i));
                                                        break;
                                                    default:
                                                        break;
                                                }
                                            }
                                        } else {
                                            player.sendMessage("该公会名称不存在！");
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            player.sendMessage("你没有权限！");
                        }
                        return true;
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
        return false;
    }
}
