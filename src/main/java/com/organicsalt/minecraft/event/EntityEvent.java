package com.organicsalt.minecraft.event;

import com.organicsalt.minecraft.main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class EntityEvent implements Listener {
    /*
    @EventHandler(priority = EventPriority.HIGH)
    public void PlayerDamage(EntityDamageByEntityEvent event){
        Entity damager=event.getDamager();
        main.plugin.getLogger().info("攻击事件监听到");
        if (damager.getType()== EntityType.PLAYER) {
            Player player = (Player) damager;
            UUID id=player.getUniqueId();
            String weapon=player.getEquipment().getItemInMainHand().getItemMeta().getDisplayName();
            //sql="select level from weapon_upgrade where UUID='" + id + "' and weapon='" + strings[1] + "'"; //查询需要强化的武器等级level
            if(player.getName().equalsIgnoreCase("salt")){//并且查询不为空
                double damage = event.getDamage();
                event.setDamage((damage));//damage=damage+level*2
                player.sendMessage("ID:"+id+"weapon:"+weapon);
            }
        }
    }
    */
}
