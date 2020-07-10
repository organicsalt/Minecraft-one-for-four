package com.organicsalt.minecraft;

import com.organicsalt.minecraft.commands.*;
import com.organicsalt.minecraft.dao.SQLiteManager;
import com.organicsalt.minecraft.event.EntityEvent;
import com.organicsalt.minecraft.event.InventoryGUIEvent;
import com.organicsalt.minecraft.event.PlayerEvent;
import com.organicsalt.minecraft.event.VexGuiEvent;
import com.organicsalt.minecraft.util.PlayerPointsUtil;
import com.organicsalt.minecraft.util.VaultUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;

public class main extends JavaPlugin{
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {

        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        File file=new File(getDataFolder(),"config.yml");
        if(!file.exists()) {
            saveDefaultConfig();
        }
        saveConfig();

        new BukkitRunnable(){
            @Override
            public void run() {
                SQLiteManager.get().enableSQLite();
            }
        }.runTaskAsynchronously(this);
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
        if(Bukkit.getPluginManager().isPluginEnabled("VexView")){
            getLogger().info("Vex界面启动成功");
        }else{
            getLogger().info("Vex界面启动失败");
        }
        Bukkit.getPluginManager().registerEvents(new PlayerEvent(),this);
        Bukkit.getPluginManager().registerEvents(new EntityEvent(),this);
        Bukkit.getPluginManager().registerEvents(new InventoryGUIEvent(),this);
        Bukkit.getPluginManager().registerEvents(new VexGuiEvent(),this);
        this.getCommand("sign").setExecutor(new sign());
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
        new BukkitRunnable(){
            @Override
            public void run() {
                //sql语句
                SQLiteManager.get().shutdown();
            }
        }.runTaskAsynchronously(main.plugin);
        System.out.println("插件结束！");
        super.onDisable();
    }
}
