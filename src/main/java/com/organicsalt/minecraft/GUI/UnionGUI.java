package com.organicsalt.minecraft.GUI;

import com.organicsalt.minecraft.dao.SQLiteManager;
import com.organicsalt.minecraft.main;
import lk.vexview.gui.VexGui;
import lk.vexview.gui.components.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class UnionGUI {
    public static VexGui UnionListGUI(Player player){
        List<VexComponents> vexComponents = new ArrayList<>();
        vexComponents.add(new VexTextField(30,2,60,20,200,1));
        vexComponents.add(new VexButton(2,"创建公会","[local]button.png","[local]button_.png",10,27,40,20));
        vexComponents.add(new VexButton(3,"离开公会","[local]button.png","[local]button_.png",70,27,40,20));
        vexComponents.add(new VexButton(4,"加入公会","[local]button.png","[local]button_.png",10,52,40,20));
        vexComponents.add(new VexButton(5,"解散公会","[local]button.png","[local]button_.png",70,52,40,20));
        vexComponents.add(new VexButton(6,"公会信息","[local]button.png","[local]button_.png",10,77,40,20));
        vexComponents.add(new VexButton(7,"公会成员","[local]button.png","[local]button_.png",70,77,40,20));
        vexComponents.add(new VexButton(8,"晋升成员","[local]button.png","[local]button_.png",10,102,40,20));
        vexComponents.add(new VexButton(9,"降职成员","[local]button.png","[local]button_.png",70,102,40,20));
        vexComponents.add(new VexButton(10,"踢出成员","[local]button.png","[local]button_.png",10,127,40,20));
        vexComponents.add(new VexButton(11,"接受成员","[local]button.png","[local]button_.png",70,127,40,20));
        vexComponents.add(new VexButton(12,"拒绝成员","[local]button.png","[local]button_.png",10,152,40,20));
        vexComponents.add(new VexButton(13,"转让公会","[local]button.png","[local]button_.png",70,152,40,20));
        vexComponents.add(new VexButton(14,"发布公告","[local]button.png","[local]button_.png",10,177,40,20));
        vexComponents.add(new VexButton(15,"关闭界面","[local]button.png","[local]button_.png",70,177,40,20));
        return new VexGui("[local]inv.png", -1, -1, 120, 200, vexComponents);
    }
}
