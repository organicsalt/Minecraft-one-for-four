package com.organicsalt.minecraft.GUI;

import com.organicsalt.minecraft.dao.SQLiteManager;
import com.organicsalt.minecraft.main;
import org.bukkit.entity.Player;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class SignGUI {
    public static VexGui SignGUI(Player player){
        List<VexComponents> vexComponents = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        ArrayList<Integer> dates = new ArrayList<>();
        try {
            String name = player.getName();
            ResultSet rs = SQLiteManager.get().findSignData(name);
            while (rs.next()) {
                dates.add(rs.getInt("date"));
            }
            if(day==1&&(dates.size()>1||!dates.contains(1))) {
                SQLiteManager.get().deleteSignData();
                dates.clear();
            }
            vexComponents.add(new VexButton(16,"","[local]Sign.png","[local]Sign_.png",20,60,40,40));
            vexComponents.add(new VexButton(17,"","[local]Signa.png","[local]Signa_.png",20,130,40,40));
            if(dates.size()>=7){
                vexComponents.add(new VexImage("[local]rew7_b.png", 330, 20, 60, 40));
            }
            else{
                vexComponents.add(new VexImage("[local]rew7_a.png", 330, 20, 60, 40));
            }
            if(dates.size()>=14) {
                vexComponents.add(new VexImage("[local]rew14_b.png", 330, 60, 60, 40));
            }
            else{
                vexComponents.add(new VexImage("[local]rew14_a.png", 330, 60, 60, 40));
            }
            if(dates.size()>=21) {
                vexComponents.add(new VexImage("[local]rew21_b.png", 330, 100, 60, 40));
            }
            else{
                vexComponents.add(new VexImage("[local]rew21_a.png", 330, 100, 60, 40));
            }
            if(dates.size()>=28) {
                vexComponents.add(new VexImage("[local]rew28_b.png", 330, 140, 60, 40));
            }
            else{
                vexComponents.add(new VexImage("[local]rew28_a.png", 330, 140, 60, 40));
            }
            if(month<10) vexComponents.add(new VexImage("[local]m0"+month+".png", 170, 20, 25, 25));
            else vexComponents.add(new VexImage("[local]m"+month+".png", 145, 20, 50, 25));
            vexComponents.add(new VexText(56,42, Arrays.asList(String.valueOf(dates.size()))));
            int d=0;
            switch (month){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:d=31;break;
                case 4:
                case 6:
                case 9:
                case 11:d=30;break;
                case 2:{
                    if((year%4==0&&year%100!=0)||year%400==0){
                        d=29;
                    }
                    else{
                        d=28;
                    }
                    break;
                }
                default:break;
            }
            for(int i=1;i<=d;i++) {
                if(i<10) {
                    if(dates.contains(i)){
                        vexComponents.add(new VexImage("[local]Y0" + i + ".png", 120 + (i - 1) % 7 * 25, 50 + (i - 1) / 7 * 25, 25, 25));
                    }
                    else {
                        vexComponents.add(new VexImage("[local]S0" + i + ".png", 120 + (i - 1) % 7 * 25, 50 + (i - 1) / 7 * 25, 25, 25));
                    }
                }
                else{
                    if(dates.contains(i)){
                        vexComponents.add(new VexImage("[local]Y" + i + ".png", 120 + (i - 1) % 7 * 25, 50 + (i - 1) / 7 * 25, 25, 25));
                    }
                    else {
                        vexComponents.add(new VexImage("[local]S" + i + ".png", 120 + (i - 1) % 7 * 25, 50 + (i - 1) / 7 * 25, 25, 25));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //vexComponents.add(new VexCheckBox(2,"[local]check.png","[local]check_.png",50,100,10,10,false));
        //vexComponents.add(new VexTextField(100,50,80,15,10,3));
        //vexComponents.add((new VexPlayerDraw(200,70,30,player.getUniqueId(),player.getName())));
        vexComponents.add(new VexButton(18,"关闭界面","[local]button.png","[local]button_.png",180,180,40,20));
        return new VexGui("[local]bg.png", -1, -1, 400, 200,vexComponents);
    }
}
