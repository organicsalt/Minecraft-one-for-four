package com.organicsalt.minecraft.GUI;

import org.bukkit.entity.Player;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.*;

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
        vexComponents.add(new VexButton(16,"","[local]Sign.png","[local]Sign_.png",20,60,40,40));
        vexComponents.add(new VexButton(17,"","[local]Signa.png","[local]Signa_.png",20,130,40,40));
        vexComponents.add(new VexImage("[local]rew7_a.png", 330, 20, 60, 40)); //7_b
        vexComponents.add(new VexImage("[local]rew14_a.png", 330, 60, 60, 40));
        vexComponents.add(new VexImage("[local]rew21_a.png", 330, 100, 60, 40));
        vexComponents.add(new VexImage("[local]rew28_a.png", 330, 140, 60, 40));
        if(month<10) vexComponents.add(new VexImage("[local]m0"+month+".png", 170, 20, 25, 25));
        else vexComponents.add(new VexImage("[local]m"+month+".png", 145, 20, 50, 25));
        //vexComponents.add(new VexText(100,100, Arrays.asList("这是测试文本","第二行")));
        int days=0;
        vexComponents.add(new VexText(56,42, Arrays.asList(String.valueOf(days))));
        for(int i=1;i<=31;i++) {
            if(i<10) vexComponents.add(new VexImage("[local]S0" + i + ".png", 120+(i-1)%7*25, 50+(i-1)/7*25, 25, 25));
            else vexComponents.add(new VexImage("[local]S" + i + ".png", 120+(i-1)%7*25, 50+(i-1)/7*25, 25, 25));
        }
        //vexComponents.add(new VexCheckBox(2,"[local]check.png","[local]check_.png",50,100,10,10,false));
        //vexComponents.add(new VexTextField(100,50,80,15,10,3));
        //vexComponents.add((new VexPlayerDraw(200,70,30,player.getUniqueId(),player.getName())));
        vexComponents.add(new VexButton(18,"关闭界面","[local]button.png","[local]button_.png",180,180,40,20));
        return new VexGui("[local]bg.png", -1, -1, 400, 200,vexComponents);
    }
}
