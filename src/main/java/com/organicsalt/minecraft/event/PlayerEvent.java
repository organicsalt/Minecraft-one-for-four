package com.organicsalt.minecraft.event;

import com.connorlinfoot.titleapi.TitleAPI;
import com.organicsalt.minecraft.dao.SQLiteManager;
import com.organicsalt.minecraft.effects.AroundEffect;
import com.organicsalt.minecraft.main;
import com.organicsalt.minecraft.util.PlayerPointsUtil;
import com.organicsalt.minecraft.util.SaveItemStack;
import net.minecraft.server.v1_12_R1.Items;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerEvent implements Listener {
    public static int points = 0;
    public static  boolean flag = false;

    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerJoin(PlayerJoinEvent event){
        main.plugin.getLogger().info("触发了加入服务器事件:"+event.getPlayer().getName());
        Player player=event.getPlayer();
        new BukkitRunnable(){
            @Override
            public void run() {
                ResultSet rs=SQLiteManager.get().findVIPData(player.getName());
                try {
                    if (!rs.next()) {
                        int vip_point = 0;
                        int vip_level = 0;
                        int gift = 0;
                        SQLiteManager.get().insertData(player.getName(), vip_level, vip_point, gift);
                    }
                    else{
                        int vip_point = rs.getInt("point");
                        int vip_level = rs.getInt("level");
                        int gift = rs.getInt("gift");
                        if(vip_level>1&&vip_level<=6){
                            player.sendMessage("§4欢迎VIP进来:§e"+player.getName()+"");
                        }
                        else if(vip_level>6) {
                            player.sendMessage("§4欢迎至尊VIP进来:§c【"+player.getName()+"】");
                        }
                        int[] gifts=new int[15];
                        int i=vip_level;
                        boolean flag=false;
                        while(i>0){
                            if((gift&1)==0){
                                gifts[i]=1;
                                flag=true;
                            }
                            gift=gift>>1;
                            i=i-1;
                        }
                        if(flag){
                            player.sendMessage("你有如下等级VIP礼包尚未领取，请输入/vip bonus [count]进行领取！");
                            for(int j=1;j<=vip_level;j++){
                                if(gifts[j]==1) player.sendMessage("§c"+j);
                            }
                        }
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(main.plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerCommandPreprocess(PlayerCommandPreprocessEvent event){
        int points2 = PlayerPointsUtil.getPlayerPoints().getAPI().look(event.getPlayer().getUniqueId());
        if(points>0&&points2-points>0){
            int vip=points2-points;
            new BukkitRunnable(){
                @Override
                public void run() {
                    ResultSet rs=SQLiteManager.get().findVIPData(event.getPlayer().getName());
                    try {
                        if (rs.next()) {
                            int vip_point = rs.getInt("point") + vip;
                            int vip_level = 0;
                            int gift = rs.getInt("gift");
                            if (vip_point >= 50000) {
                                vip_level = 13;
                            } else if (vip_point >= 20000) {
                                vip_level = 12;
                            } else if (vip_point >= 10000) {
                                vip_level = 11;
                            } else if (vip_point >= 5000) {
                                vip_level = 10;
                            } else if (vip_point >= 2000) {
                                vip_level = 9;
                            } else if (vip_point >= 1000) {
                                vip_level = 8;
                            } else if (vip_point >= 500) {
                                vip_level = 7;
                            } else if (vip_point >= 200) {
                                vip_level = 6;
                            } else if (vip_point >= 100) {
                                vip_level = 5;
                            } else if (vip_point >= 50) {
                                vip_level = 4;
                            } else if (vip_point >= 20) {
                                vip_level = 3;
                            } else if (vip_point >= 10) {
                                vip_level = 2;
                            } else if (vip_point >= 1) {
                                vip_level = 1;
                            }
                            SQLiteManager.get().updateVIP(vip_level,vip_point, gift, event.getPlayer().getName());
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }
            }.runTaskAsynchronously(main.plugin);
            event.getPlayer().sendMessage("VIP信息已更新！");
        }
        points=points2;
    }

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        if(AroundEffect.status==true){ //当前特效已开启
            Player player = event.getPlayer();
            if (player.getItemInHand().getType() == Material.IRON_SWORD) {  //IRON_SWORD
                player.getWorld().strikeLightningEffect(player.getTargetBlock(null,1).getLocation());
            }
            else if(player.getItemInHand().getType()==Material.WOOD_SWORD){
                Location location=player.getTargetBlock(null,1).getLocation();
                player.getWorld().createExplosion(location.getX(),location.getY(),location.getZ(),1.0f,true,false);
            }
            else if(player.getItemInHand().getType()==Material.DIAMOND_SWORD){
                Location location=player.getTargetBlock(null,1).getLocation();
                location.getWorld().playEffect(location, Effect.MOBSPAWNER_FLAMES, 1);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerMove(PlayerMoveEvent event){
        Player player=event.getPlayer();
        String name=player.getName();
        new BukkitRunnable() {
            @Override
            public void run() {
                ResultSet rs=SQLiteManager.get().findUnionDutyData(name);
                try {
                    if(rs.next()){
                        String union=rs.getString("union");
                        ResultSet rs2=SQLiteManager.get().findUnionData(union);
                        if(rs2.next()){
                            double x=rs2.getDouble("x");
                            double y=rs2.getDouble("y");
                            double z=rs2.getDouble("z");
                            double px=player.getLocation().getX();
                            double py=player.getLocation().getY();
                            double pz=player.getLocation().getZ();
                            double dis=Math.sqrt((x-px)*(x-px)+(y-py)*(y-py)+(z-pz)*(z-pz));
                            if(dis<=10.0&&!flag){
                                TitleAPI.sendTitle(player,10,20,10,"欢迎来到你的公会领地！");
                                flag=true;
                            }
                            else if(dis>10.0&&flag){
                                TitleAPI.sendTitle(player,10,20,10,"你已离开你的公会领地！");
                                flag=false;
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(main.plugin);
    }
}
