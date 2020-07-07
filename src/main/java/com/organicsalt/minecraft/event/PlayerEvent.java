package com.organicsalt.minecraft.event;

import com.organicsalt.minecraft.effects.AroundEffect;
import com.organicsalt.minecraft.main;
import com.organicsalt.minecraft.util.PlayerPointsUtil;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerEvent implements Listener {
    public static int points = 0;

    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerJoin(PlayerJoinEvent event){
        main.plugin.getLogger().info("触发了加入服务器事件:"+event.getPlayer().getName());
        Player player=event.getPlayer();
        UUID id = player.getUniqueId();
        //sql="select * from vip where UUID = '" + id + "'";
        if(true){//该用户UUID存在，能查询到结果
            if(true){//如果是vip
                player.sendMessage("§4欢迎至尊VIP进来:§c【"+player.getName()+"】");
            }
        }
        else{//该用户不存在，为他创建一张新表
            //sql="insert into vip values('" + id + "', 0, 0, 0)";
        }
    }

    @EventHandler
    public void playerCommandPreprocess(PlayerCommandPreprocessEvent event){
       // main.plugin.getLogger().info("触发了命令执行事件:"+event.getPlayer().getName());
        if(points>0){
            int points2 = PlayerPointsUtil.getPlayerPoints().getAPI().look(event.getPlayer().getUniqueId());
            //event.getPlayer().sendMessage(event.getPlayer().getDisplayName()+"现有点券是"+points2);
            //event.getPlayer().sendMessage(event.getPlayer().getDisplayName()+"原有点券是"+points);
            if(points2-points>0){
                int vip=points2-points;
                //vip_point=vip_point+vip;
                //更新玩家vip_grade信息
                //更新玩家vip_level信息
                //sql="update itemInBag set amount = "+money+" where UUID='" + id + " and item='stamps'"; ?
                //sql="update vip set vip_point = "+vip_point+" where UUID='" + id + "'";
                            /*
                            int vip_level=0;
                            if(vip_point>=50000){
                                vip_level=13;
                            }
                            else if(vip_point>=20000){
                                vip_level=12;
                            }
                            else if(vip_point>=10000){
                                vip_level=11;
                            }
                            else if(vip_point>=5000){
                                vip_level=10；
                            }
                            else if(vip_point>=2000){
                                vip_level=9；
                            }
                            else if(vip_point>=1000){
                                vip_level=8；
                            }
                            else if(vip_point>=500){
                                vip_level=7；
                            }
                            else if(vip_point>=200){
                                vip_level=6；
                            }
                            else if(vip_point>=100){
                                vip_level=5；
                            }else if(vip_point>=50){
                                vip_level=4；
                            }else if(vip_point>=20){
                                vip_level=3；
                            }else if(vip_point>=10){
                                vip_level=2；
                            }
                            else if(vip_point>=1){
                                vip_level=1；
                            }
                            */
                //sql="update vip set vip_level = "+vip_level+" where UUID='" + id + "'";
                event.getPlayer().sendMessage("VIP信息已更新！");
            }
            points=points2;
        }
        else{
            int points2 = PlayerPointsUtil.getPlayerPoints().getAPI().look(event.getPlayer().getUniqueId());
            //event.getPlayer().sendMessage(event.getPlayer().getDisplayName()+"现有点券是"+points2);
            points=points2;
        }
    }

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        if(AroundEffect.status==true){ //当前特效已开启
            Player player = event.getPlayer();
            if (player.getItemInHand().getType() == Material.IRON_SWORD) {  //IRON_SWORD
                // 在给定坐标中生成一道闪电. 在本例中, 这个坐标是玩家准星瞄准的地方.
                // 只能指向1格以内的坐标.
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

    /*没作用
    @EventHandler
    public void PlayerItemHeld(PlayerItemHeldEvent event){
        Player player=event.getPlayer();
        player.sendMessage("物品已更换:"+event.getPlayer().getItemInHand().getItemMeta().getDisplayName());
        if(player.getItemInHand().getType()==Material.IRON_SWORD){
            AroundEffect aroundEffect = new AroundEffect(player, Effect.ENDER_SIGNAL);
            aroundEffect.startEffect();
        }
        else if(player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
            AroundEffect aroundEffect = new AroundEffect(player, Effect.SMOKE);
            aroundEffect.startEffect();
        }
        else if (player.getItemInHand().getType() == Material.WOOD_SWORD) {
            AroundEffect aroundEffect = new AroundEffect(player, Effect.MOBSPAWNER_FLAMES);
            aroundEffect.startEffect();
        }
    }
    */
}
