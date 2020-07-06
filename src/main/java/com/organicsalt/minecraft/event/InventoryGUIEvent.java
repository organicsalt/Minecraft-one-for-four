package com.organicsalt.minecraft.event;

import com.organicsalt.minecraft.GUI.storeInventory;
import com.organicsalt.minecraft.main;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryGUIEvent implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void InventoryStoreClick(InventoryClickEvent event){
        Inventory inventory=event.getInventory();
        String title=inventory.getTitle();
        if(title.equalsIgnoreCase(storeInventory.storeGui)){
            HumanEntity whoClicked=event.getWhoClicked();
            Player player=(Player)whoClicked;
            int rawslot=event.getRawSlot();
            main.plugin.getLogger().info((String.valueOf(rawslot)));
            if(rawslot==1||rawslot==4||rawslot==7||rawslot==49){
                event.setCancelled(true);
                if(rawslot==49){
                    player.sendMessage("退出商城");
                    player.closeInventory();
                }
            }
            else{
                event.setCancelled(true);
                player.sendMessage("这个不能点击！");
            }
        }
    }
}
