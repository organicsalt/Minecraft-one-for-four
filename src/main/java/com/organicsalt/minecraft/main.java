package com.organicsalt.minecraft;

import com.organicsalt.minecraft.commands.*;
import com.organicsalt.minecraft.event.EntityEvent;
import com.organicsalt.minecraft.event.InventoryGUIEvent;
import com.organicsalt.minecraft.event.PlayerEvent;
import com.organicsalt.minecraft.util.PlayerPointsUtil;
import com.organicsalt.minecraft.util.VaultUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin=this;
        if(VaultUtil.setupEconomy()){
            getLogger().info("Vault插件启动成功！");
        }
        else{
            getLogger().info("Vault插件启动失败！");
        }
        if(PlayerPointsUtil.hookPlayerPoints()){
            getLogger().info(("PlayerPoints插件启动成功！"));
        }
        else{
            getLogger().info(("PlayerPoints插件启动失败！"));
        }
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(),this);
        Bukkit.getPluginManager().registerEvents(new EntityEvent(),this);
        Bukkit.getPluginManager().registerEvents(new InventoryGUIEvent(),this);
        this.getCommand("sign_in").setExecutor(new sign_in());
        this.getCommand("complement_sign").setExecutor(new complement_sign());
        this.getCommand("query_sign").setExecutor(new query_sign());
        this.getCommand("weapon_effects").setExecutor(new weapon_effects());
        this.getCommand("invest").setExecutor(new invest());
        this.getCommand("buy").setExecutor(new buy());
        this.getCommand("vip").setExecutor(new vip());
        this.getCommand("weapon_upgrade").setExecutor(new weapon_upgrade());
        this.getCommand("union").setExecutor(new union());
        getLogger().info("插件启动成功！");
        super.onEnable();
    }

    @Override
    public void onDisable() {
        System.out.println("插件结束！");
        super.onDisable();
    }
}
