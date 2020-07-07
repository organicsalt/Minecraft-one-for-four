package com.organicsalt.minecraft.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class upgradeInventory {
    public static String upgradeGui="§4强化界面";
    public static void upgradeGUI(Player player){
        Inventory inventory= Bukkit.createInventory(null,54,upgradeGui);
        ItemStack itemStack_wood = new ItemStack(Material.WOOD);
        ItemMeta itemMeta_wood = itemStack_wood.getItemMeta();
        itemMeta_wood.setDisplayName("这里不能放入物品！");
        itemStack_wood.setItemMeta(itemMeta_wood);

        ItemStack itemStack_button_upgrade = new ItemStack(Material.STONE_BUTTON);
        ItemMeta itemMeta_button_upgrade = itemStack_button_upgrade.getItemMeta();
        itemMeta_button_upgrade.setDisplayName("§e强化物品");
        itemStack_button_upgrade.setItemMeta(itemMeta_button_upgrade);

        ItemStack itemStack_button_exit = new ItemStack(Material.STONE_BUTTON);
        ItemMeta itemMeta_button_exit=itemStack_button_exit.getItemMeta();
        itemMeta_button_exit.setDisplayName("退出强化");
        itemStack_button_exit.setItemMeta(itemMeta_button_exit);

        player.openInventory(inventory);
        for(int i=0;i<=53;i++){
            if(i!=47&&i!=51&&i!=22){
                inventory.setItem(i,itemStack_wood);
            }
        }
        inventory.setItem(47,itemStack_button_upgrade);
        inventory.setItem(51,itemStack_button_exit);
    }
}
