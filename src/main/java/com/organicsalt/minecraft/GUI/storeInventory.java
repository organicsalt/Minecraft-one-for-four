package com.organicsalt.minecraft.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class storeInventory {
    public static String storeGui="§4商城界面";
    public static void storeGUI(Player player){
        Inventory inventory= Bukkit.createInventory(null,54,storeGui);
        ItemStack itemStack_wood = new ItemStack(Material.WOOD);
        ItemMeta itemMeta_wood = itemStack_wood.getItemMeta();
        itemMeta_wood.setDisplayName("这里不能放入物品！");
        itemStack_wood.setItemMeta(itemMeta_wood);

        ItemStack itemStack_complement1 = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta_complement1 = itemStack_complement1.getItemMeta();
        itemMeta_complement1.setDisplayName("§e这是补签卡");
        itemMeta_complement1.setLore(Arrays.asList("§b该补签卡可以用来补签","§6输入/complement_sign [date]命令进行补签","§4补签之后可以领取签到奖励","§2补签之后还可以领取累计签到奖励"));
        itemStack_complement1.setItemMeta(itemMeta_complement1);
        itemStack_complement1.setAmount(1);

        ItemStack itemStack_complement10 = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta_complement10 = itemStack_complement10.getItemMeta();
        itemMeta_complement10.setDisplayName("§e这是补签卡");
        itemMeta_complement10.setLore(Arrays.asList("§b该补签卡可以用来补签","§6输入/complement_sign [date]命令进行补签","§4补签之后可以领取签到奖励","§2补签之后还可以领取累计签到奖励"));
        itemStack_complement10.setItemMeta(itemMeta_complement10);
        itemStack_complement10.setAmount(10);

        ItemStack itemStack_complement64 = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta_complement64 = itemStack_complement64.getItemMeta();
        itemMeta_complement64.setDisplayName("§e这是补签卡");
        itemMeta_complement64.setLore(Arrays.asList("§b该补签卡可以用来补签","§6输入/complement_sign [date]命令进行补签","§4补签之后可以领取签到奖励","§2补签之后还可以领取累计签到奖励"));
        itemStack_complement64.setItemMeta(itemMeta_complement64);
        itemStack_complement64.setAmount(64);

        ItemStack itemStack_upgrade1 = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta_upgrade1 = itemStack_upgrade1.getItemMeta();
        itemMeta_upgrade1.setDisplayName("§e这是强化石");
        itemMeta_upgrade1.setLore(Arrays.asList("§b该石头可以用来强化武器","§6输入/weapon_upgrade 命令进行强化","§4强化有可能失败","§2武器强化等级越高强化成功率越低"));
        itemStack_upgrade1.setItemMeta(itemMeta_upgrade1);
        itemStack_upgrade1.setAmount(1);

        ItemStack itemStack_upgrade10 = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta_upgrade10 = itemStack_upgrade10.getItemMeta();
        itemMeta_upgrade10.setDisplayName("§e这是强化石");
        itemMeta_upgrade10.setLore(Arrays.asList("§b该石头可以用来强化武器","§6输入/weapon_upgrade 命令进行强化","§4强化有可能失败","§2武器强化等级越高强化成功率越低"));
        itemStack_upgrade10.setItemMeta(itemMeta_upgrade10);
        itemStack_upgrade10.setAmount(10);

        ItemStack itemStack_upgrade64 = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta_upgrade64 = itemStack_upgrade64.getItemMeta();
        itemMeta_upgrade64.setDisplayName("§e这是强化石");
        itemMeta_upgrade64.setLore(Arrays.asList("§b该石头可以用来强化武器","§6输入/weapon_upgrade 命令进行强化","§4强化有可能失败","§2武器强化等级越高强化成功率越低"));
        itemStack_upgrade64.setItemMeta(itemMeta_upgrade64);
        itemStack_upgrade64.setAmount(64);

        ItemStack itemStack_effects1=new ItemStack(Material.FLINT);
        ItemMeta itemMeta_effects1 = itemStack_effects1.getItemMeta();
        itemMeta_effects1.setDisplayName("§e这是装扮石");
        itemMeta_effects1.setLore(Arrays.asList("§b该石头可以用来开启武器特效","§6输入/weapon_effects up命令进行开启","§4用装扮石赋予武器特效只能覆盖无法叠加","§2武器不同强化等级自身特效不一样"));
        itemStack_effects1.setItemMeta(itemMeta_effects1);
        itemStack_effects1.setAmount(1);

        ItemStack itemStack_effects10=new ItemStack(Material.FLINT);
        ItemMeta itemMeta_effects10 = itemStack_effects10.getItemMeta();
        itemMeta_effects10.setDisplayName("§e这是装扮石");
        itemMeta_effects10.setLore(Arrays.asList("§b该石头可以用来开启武器特效","§6输入/weapon_effects up命令进行开启","§4用装扮石赋予武器特效只能覆盖无法叠加","§2武器不同强化等级自身特效不一样"));
        itemStack_effects10.setItemMeta(itemMeta_effects10);
        itemStack_effects10.setAmount(10);

        ItemStack itemStack_effects64=new ItemStack(Material.FLINT);
        ItemMeta itemMeta_effects64 = itemStack_effects64.getItemMeta();
        itemMeta_effects64.setDisplayName("§e这是装扮石");
        itemMeta_effects64.setLore(Arrays.asList("§b该石头可以用来开启武器特效","§6输入/weapon_effects up命令进行开启","§4用装扮石赋予武器特效只能覆盖无法叠加","§2武器不同强化等级自身特效不一样"));
        itemStack_effects64.setItemMeta(itemMeta_effects64);
        itemStack_effects64.setAmount(64);

        ItemStack itemStack_button_exit = new ItemStack(Material.WOOD_BUTTON);
        ItemMeta itemMeta_button_exit=itemStack_button_exit.getItemMeta();
        itemMeta_button_exit.setDisplayName("退出商城");
        itemStack_button_exit.setItemMeta(itemMeta_button_exit);

        player.openInventory(inventory);


        inventory.setItem(0,itemStack_complement1);
        inventory.setItem(1,itemStack_complement10);
        inventory.setItem(2,itemStack_complement64);
        inventory.setItem(3,itemStack_upgrade1);
        inventory.setItem(4,itemStack_upgrade10);
        inventory.setItem(5,itemStack_upgrade64);
        inventory.setItem(6,itemStack_effects1);
        inventory.setItem(7,itemStack_effects10);
        inventory.setItem(8,itemStack_effects64);

        for(int i=9;i<=53;i++){
            if(i!=49){
                inventory.setItem(i,itemStack_wood);
            }
        }
        inventory.setItem(49,itemStack_button_exit);


    }

}
