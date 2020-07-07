package com.organicsalt.minecraft.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class effectInventory {
    public static String effectGui="§4特效界面";
    public static void effectGUI(Player player){
        Inventory inventory= Bukkit.createInventory(null,54,effectGui);
        ItemStack itemStack_wood = new ItemStack(Material.WOOD);
        ItemMeta itemMeta_wood = itemStack_wood.getItemMeta();
        itemMeta_wood.setDisplayName("这里不能放入物品！");
        itemStack_wood.setItemMeta(itemMeta_wood);

        ItemStack itemStack_button_effect = new ItemStack(Material.WOOD_BUTTON);
        ItemMeta itemMeta_button_effect = itemStack_button_effect.getItemMeta();
        itemMeta_button_effect.setDisplayName("§e赋予特效");
        itemStack_button_effect.setItemMeta(itemMeta_button_effect);

        ItemStack itemStack_button_exit = new ItemStack(Material.WOOD_BUTTON);
        ItemMeta itemMeta_button_exit=itemStack_button_exit.getItemMeta();
        itemMeta_button_exit.setDisplayName("退出商城");
        itemStack_button_exit.setItemMeta(itemMeta_button_exit);

        player.openInventory(inventory);
        for(int i=0;i<=53;i++){
            if(i!=47&&i!=51&&i!=22){
                inventory.setItem(i,itemStack_wood);
            }
        }
        inventory.setItem(47,itemStack_button_effect);
        inventory.setItem(51,itemStack_button_exit);
    }

}
