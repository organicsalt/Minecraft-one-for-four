package com.organicsalt.minecraft.effects;

import com.organicsalt.minecraft.main;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AroundEffect extends BukkitRunnable {
    private Player player;
    private Effect effect;
    private double degree=0;
    private double height=0;
    public static boolean status=false;
    public AroundEffect(Player player,Effect effect){
        this.player=player;
        this.effect=effect;
    }
    @Override
    public void run(){
        if(player==null||!player.isOnline()||!status){
            cancel();
        }
        Location location = player.getLocation();
        double radians = Math.toRadians(degree);
        double x = Math.cos(radians);
        double y = Math.sin(radians);
        //double z = Double.parseDouble(args[0]);
        location.add(x, height, y);
        location.getWorld().playEffect(location, effect, 1);
        location.subtract(x, height, y);

        if (degree>=360){
            degree = 0;
        }else{
            degree += 15;
        }
        if(height>=2){
            height=0;
        }else{
            height += 0.1;
        }
    }

    public void startEffect(){
        runTaskTimer(main.plugin,0L,1L);
    }

    public void stopEffect(){
        cancel();
    }
}
