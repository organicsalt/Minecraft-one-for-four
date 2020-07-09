package com.organicsalt.minecraft.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class VIPBonus {
    public static void getBonus(Player player, int complement, int upgrade, int effect){
        ItemStack itemStack_complement = new ItemStack(Material.GOLD_INGOT);
        ItemMeta itemMeta_complement = itemStack_complement.getItemMeta();
        itemMeta_complement.setDisplayName("§e这是补签卡");
        itemMeta_complement.setLore(Arrays.asList("§b该补签卡可以用来补签","§6输入/complement_sign [date]命令进行补签","§4补签之后可以领取签到奖励","§2补签之后还可以领取累计签到奖励"));
        itemStack_complement.setItemMeta(itemMeta_complement);
        itemStack_complement.setAmount(complement);
        player.getInventory().addItem(itemStack_complement);

        ItemStack itemStack_upgrade = new ItemStack(Material.DIAMOND);
        ItemMeta itemMeta_upgrade = itemStack_upgrade.getItemMeta();
        itemMeta_upgrade.setDisplayName("§e这是强化石");
        itemMeta_upgrade.setLore(Arrays.asList("§b该石头可以用来强化武器","§6输入/weapon_upgrade 命令进行强化","§4强化有可能失败","§2武器强化等级越高强化成功率越低"));
        itemStack_upgrade.setItemMeta(itemMeta_upgrade);
        itemStack_upgrade.setAmount(upgrade);
        player.getInventory().addItem(itemStack_upgrade);

        ItemStack itemStack_effects=new ItemStack(Material.FLINT);
        ItemMeta itemMeta_effects = itemStack_effects.getItemMeta();
        itemMeta_effects.setDisplayName("§e这是装扮石");
        itemMeta_effects.setLore(Arrays.asList("§b该石头可以用来开启武器特效","§6输入/weapon_effects up命令进行开启","§4用装扮石赋予武器特效只能覆盖无法叠加","§2武器不同强化等级自身特效不一样"));
        itemStack_effects.setItemMeta(itemMeta_effects);
        itemStack_effects.setAmount(effect);
        player.getInventory().addItem(itemStack_effects);
    }
}
