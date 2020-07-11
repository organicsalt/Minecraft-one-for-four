package com.organicsalt.minecraft.event;

import com.organicsalt.minecraft.dao.SQLiteManager;
import com.organicsalt.minecraft.effects.AroundEffect;
import com.organicsalt.minecraft.main;
import com.organicsalt.minecraft.util.SaveItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EntityEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerDamage(EntityDamageByEntityEvent event){
        Entity damager = event.getDamager();
        if (damager.getType() == EntityType.PLAYER) {
            Player player = (Player) damager;
            String weapon = SaveItemStack.ToData(player.getEquipment().getItemInHand());
            ResultSet rs = SQLiteManager.get().findWeaponData(weapon);
            try {
                if(rs.next()){
                    int level = rs.getInt("level");
                    double damage = event.getDamage();
                    event.setDamage(damage + level * 2);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
