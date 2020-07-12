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
        height=1;
        double radians = Math.toRadians(degree);
        double x1 = Math.cos(radians);
        double y1 = Math.sin(radians);
        double x2 = -Math.cos(radians);
        double y2 = Math.sin(radians);
        double x3 = Math.cos(radians);
        double y3 = -Math.sin(radians);
        double x4 = -Math.cos(radians);
        double y4 = -Math.sin(radians);
        //double z = Double.parseDouble(args[0]);

        if(effect==Effect.SMOKE) {
            location.add(x1, height, y1);
            location.getWorld().playEffect(location, effect, 1);
            location.subtract(x1, height, y1);
            location.add(x2, height, y2);
            location.getWorld().playEffect(location, effect, 1);
            location.subtract(x2, height, y2);
            location.add(x3, height, y3);
            location.getWorld().playEffect(location, effect, 1);
            location.subtract(x3, height, y3);
            location.add(x4, height, y4);
            location.getWorld().playEffect(location, effect, 1);
            location.subtract(x4, height, y4);
        }
        else {
            location.add(x1, height, y1);
            location.getWorld().playEffect(location, effect, 1);
            location.subtract(x1, height, y1);
        }
        if(effect!=Effect.MOBSPAWNER_FLAMES) {
            if (degree >= 360) {
                degree = 0;
            } else {
                degree += 15;
            }
            if (height >= 2) {
                height = 0;
            } else {
                height += 0.1;
            }
        }
        else{
            if (degree >= 360) {
                degree = 0;
            } else {
                degree += 30;
            }
        }
    }

    public void startEffect(){
        if(effect==Effect.MOBSPAWNER_FLAMES) {
            runTaskTimer(main.plugin, 0L, 4L);
        }
        else{
            runTaskTimer(main.plugin, 0L, 1L);
        }
    }

    public void stopEffect(){
        cancel();
    }
}
