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
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
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
                            int year_sign = rs.getInt("year");
                            int month_sign = rs.getInt("month");
                            if(year==year_sign&&month==month_sign) dates.add(rs.getInt("day"));
                        }
                        if(dates.contains(day)){
                            player.sendMessage("今天你已经签过到了!");
                        }
                        else{
                            SQLiteManager.get().insertDateData(name, year, month, day);
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
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
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
                            int year_sign = rs.getInt("year");
                            int month_sign = rs.getInt("month");
                            if(year==year_sign&&month==month_sign) dates.add(rs.getInt("day"));
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
                                SQLiteManager.get().insertDateData(name, year, month, least);
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
        else if(buttonID==2){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String union=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionData(union);
                    try {
                        if(rs.next()){
                            player.sendMessage("该公会名重复！");
                        }
                        else{
                            rs = SQLiteManager.get().findUnionDutyData(player.getName());
                            if(rs.next()){
                                player.sendMessage("该玩家已有公会！");
                            }
                            else {
                                double x = player.getLocation().getX();
                                double y = player.getLocation().getY();
                                double z = player.getLocation().getZ();
                                SQLiteManager.get().insertData(union, null, player.getName(), x, y, z);
                                SQLiteManager.get().insertData(player.getName(),union,4);
                                player.sendMessage("创建公会成功!");
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==3){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String union2=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionDutyData(player.getName());
                    try {
                        if(rs.next()){
                            String union = rs.getString("union");
                            int duty = rs.getInt("duty");
                            if(union.equals(union2)){
                                if(duty!=4){
                                    SQLiteManager.get().deleteUnionData(player.getName(),1);
                                    player.sendMessage("§e"+player.getName()+"退出"+"§9"+union2+"§f公会成功！");
                                }
                                else{
                                    player.sendMessage("会长无法退出公会！");
                                }
                            }
                            else{
                                player.sendMessage("你不属于这个公会!");
                            }
                        }
                        else{
                            player.sendMessage("你没有公会!");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==4){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String union2=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionDutyData(player.getName());
                    try {
                        if(rs.next()){
                            String union = rs.getString("union");
                            int duty = rs.getInt("duty");
                            if(union.equals(union2)&&duty>=1){
                                player.sendMessage("你已经属于这个公会!");
                            }
                            else if(union.equals(union2)&&duty==0){
                                player.sendMessage("你已经申请了该公会！");
                            }
                            else if(duty==0){
                                player.sendMessage("你已经申请了其他公会！");
                            }
                            else if(duty>=1){
                                player.sendMessage("你已经有了公会！");
                            }
                        }
                        else{
                            SQLiteManager.get().insertData(player.getName(), union2,0);
                            player.sendMessage("申请加入"+"§e"+union2+"§f公会成功!");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==5){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String union2=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionDutyData(player.getName());
                    try {
                        if(rs.next()){
                            String union = rs.getString("union");
                            int duty = rs.getInt("duty");
                            if(union.equals(union2)){
                                if(duty!=4){
                                    player.sendMessage("你没有足够的权限！");
                                }
                                else{
                                    ArrayList<String> members=new ArrayList<String>();
                                    rs = SQLiteManager.get().findUnionMember(union2);
                                    while(rs.next()){
                                        members.add(rs.getString("name"));
                                    }
                                    for(String name:members){
                                        SQLiteManager.get().deleteUnionData(name,1);
                                    }
                                    SQLiteManager.get().deleteUnionData(union2,0);
                                    player.sendMessage("公会已解散！");
                                }
                            }
                            else{
                                player.sendMessage("你不属于这个公会!");
                            }
                        }
                        else{
                            player.sendMessage("你没有公会!");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==6){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String union=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionData(union);
                    try {
                        if(rs.next()){
                            String announce = rs.getString("announcement");
                            String name = rs.getString("name");
                            double x = rs.getDouble("x");
                            double y = rs.getDouble("y");
                            double z = rs.getDouble("z");
                            player.sendMessage(union+"公会信息如下:");
                            player.sendMessage("会长-"+name);
                            player.sendMessage("公会公告-"+announce);
                            player.sendMessage("公会据点坐标-");
                            player.sendMessage("x-"+x);
                            player.sendMessage("y-"+y);
                            player.sendMessage("z-"+z);
                        }
                        else{
                            player.sendMessage("该名字的公会不存在!");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==7){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String union2=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionMember(union2);
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
                                        player.sendMessage("§e组长:" + "§f"  + members.get(i));
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
                        } else{
                            player.sendMessage("该公会名称不存在！");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==8){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String name=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionDutyData(name);
                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                    try {
                        if(rs.next()){
                            if(rs2.next()){
                                String union1 = rs.getString("union");
                                String union2 = rs2.getString("union");
                                int duty1 = rs.getInt("duty");
                                int duty2 = rs2.getInt("duty");
                                if(union1.equals(union2)){
                                    if(duty2-duty1>1){
                                        duty1=duty1+1;
                                        SQLiteManager.get().updateUnionDuty(name, duty1);
                                        player.sendMessage("§e"+name+"§f已晋升");
                                    }
                                    else{
                                        player.sendMessage("你没有足够的权限！");
                                    }
                                }
                                else{
                                    player.sendMessage("§e"+name+"§f不是你公会的成员！");
                                }
                            }
                            else{
                                player.sendMessage("你没有公会！");
                            }
                        }
                        else{
                            player.sendMessage("§e"+name+"§f没有公会！");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==9){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String name=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionDutyData(name);
                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                    try {
                        if(rs.next()){
                            if(rs2.next()){
                                String union1 = rs.getString("union");
                                String union2 = rs2.getString("union");
                                int duty1 = rs.getInt("duty");
                                int duty2 = rs2.getInt("duty");
                                if(union1.equals(union2)){
                                    if(duty2-duty1>=1&&duty1>1){
                                        duty1=duty1-1;
                                        SQLiteManager.get().updateUnionDuty(name, duty1);
                                        player.sendMessage("§e"+name+"§f已降职");
                                    }
                                    else if(duty2-duty1<=1){
                                        player.sendMessage("你没有足够的权限！");
                                    }
                                    else{
                                        player.sendMessage("§e"+name+"§f无法被降职！");
                                    }
                                }
                                else{
                                    player.sendMessage("§e"+name+"§f不是你公会的成员！");
                                }
                            }
                            else{
                                player.sendMessage("你没有公会！");
                            }
                        }
                        else{
                            player.sendMessage("§e"+name+"§f没有公会！");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==10){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String name=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionDutyData(name);
                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                    try {
                        if(rs.next()){
                            if(rs2.next()){
                                String union1 = rs.getString("union");
                                String union2 = rs2.getString("union");
                                int duty1 = rs.getInt("duty");
                                int duty2 = rs2.getInt("duty");
                                if(union1.equals(union2)){
                                    if(duty2-duty1>1&&duty1>=1){
                                        SQLiteManager.get().deleteUnionData(name, 1);
                                        player.sendMessage("§e"+name+"§f已踢出");
                                    }
                                    else if(duty2-duty1<=1){
                                        player.sendMessage("你没有足够的权限！");
                                    }
                                    else{
                                        player.sendMessage("§e"+name+"§f无法被踢出！");
                                    }
                                }
                                else{
                                    player.sendMessage("§e"+name+"§f不是你公会的成员！");
                                }
                            }
                            else{
                                player.sendMessage("你没有公会！");
                            }
                        }
                        else{
                            player.sendMessage("§e"+name+"§f没有公会！");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==11){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String name=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionDutyData(name);
                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                    try {
                        if(rs.next()){
                            if(rs2.next()){
                                String union1 = rs.getString("union");
                                String union2 = rs2.getString("union");
                                int duty1 = rs.getInt("duty");
                                int duty2 = rs2.getInt("duty");
                                if(union1.equals(union2)){
                                    if(duty2>1&&duty1==0){
                                        duty1=1;
                                        SQLiteManager.get().updateUnionDuty(name, duty1);
                                        player.sendMessage("§e"+name+"§f申请已通过！");
                                    }
                                    else if(duty2<=1){
                                        player.sendMessage("你没有足够的权限！");
                                    }
                                    else{
                                        player.sendMessage("§e"+name+"§f已经是公会成员！");
                                    }
                                }
                                else{
                                    player.sendMessage("§e"+name+"§f不是你公会的成员！");
                                }
                            }
                            else{
                                player.sendMessage("你没有公会！");
                            }
                        }
                        else{
                            player.sendMessage("§e"+name+"§f没有公会！");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==12){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String name=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionDutyData(name);
                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                    try {
                        if(rs.next()){
                            if(rs2.next()){
                                String union1 = rs.getString("union");
                                String union2 = rs2.getString("union");
                                int duty1 = rs.getInt("duty");
                                int duty2 = rs2.getInt("duty");
                                if(union1.equals(union2)){
                                    if(duty2>1&&duty1==0){
                                        SQLiteManager.get().deleteUnionData(name, 1);
                                        player.sendMessage("§e"+name+"§f申请已拒绝！");
                                    }
                                    else if(duty2<=1){
                                        player.sendMessage("你没有足够的权限！");
                                    }
                                    else{
                                        player.sendMessage("§e"+name+"§f已经是公会成员！");
                                    }
                                }
                                else{
                                    player.sendMessage("§e"+name+"§f不是你公会的成员！");
                                }
                            }
                            else{
                                player.sendMessage("你没有公会！");
                            }
                        }
                        else{
                            player.sendMessage("§e"+name+"§f没有公会！");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==13){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String name=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionDutyData(name);
                    ResultSet rs2 = SQLiteManager.get().findUnionDutyData(player.getName());
                    try {
                        if(rs.next()){
                            if(rs2.next()){
                                String union1 = rs.getString("union");
                                String union2 = rs2.getString("union");
                                int duty1 = rs.getInt("duty");
                                int duty2 = rs2.getInt("duty");
                                if(union1.equals(union2)){
                                    if(duty2==4&&duty1>=1){
                                        duty1=4;
                                        duty2=1;
                                        SQLiteManager.get().updateUnionDuty(name, duty1);
                                        SQLiteManager.get().updateUnionDuty(player.getName(), duty2);
                                        SQLiteManager.get().updateUnionInfoMaster(name, union1);
                                        player.sendMessage("公会已转让给§e"+name);
                                    }
                                    else if(duty1==0){
                                        player.sendMessage("§e"+name+"§f不是你公会的成员！");
                                    }
                                    else{
                                        player.sendMessage("你没有足够的权限！");
                                    }
                                }
                                else{
                                    player.sendMessage("§e"+name+"§f不是你公会的成员！");
                                }
                            }
                            else{
                                player.sendMessage("§e"+name+"§f不是你公会的成员！");
                            }
                        }
                        else{
                            player.sendMessage("你没有公会！");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
        }
        else if(buttonID==14){
            new BukkitRunnable(){
                @Override
                public void run() {
                    Player player=event.getPlayer();
                    String announce=event.getGui().getTextField(1).getTypedText();
                    ResultSet rs = SQLiteManager.get().findUnionDutyData(player.getName());
                    try {
                        if(rs.next()){
                            String union = rs.getString("union");
                            int duty = rs.getInt("duty");
                            if(duty>2){
                                SQLiteManager.get().updateUnionInfoAnnouncement(announce, union);
                                player.sendMessage("公会公告已发布！");
                            }
                            else{
                                player.sendMessage("你没有足够的权限！");
                            }
                        }
                        else{
                            player.sendMessage("你没有公会！");
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
