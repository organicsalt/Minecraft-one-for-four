package com.organicsalt.minecraft.commands;

import com.organicsalt.minecraft.GUI.effectInventory;
import com.organicsalt.minecraft.GUI.storeInventory;
import com.organicsalt.minecraft.dao.SQLiteManager;
import com.organicsalt.minecraft.effects.AroundEffect;
import com.organicsalt.minecraft.main;
import com.organicsalt.minecraft.util.SaveItemStack;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class weapon_effects implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            if (s.equalsIgnoreCase("weapon_effects")) {
                if (strings.length == 1) {
                    Player player = (Player) commandSender;
                    if (strings[0].equalsIgnoreCase("on")) {//如果是开启武器特效指令
                        if(AroundEffect.status==false){
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    String weapon = SaveItemStack.ToData(player.getEquipment().getItemInHand());
                                    ResultSet rs = SQLiteManager.get().findWeaponData(weapon);
                                    try {
                                        if (rs.next()) {
                                            int effect = rs.getInt("effect");
                                            if(effect==1){
                                                AroundEffect aroundEffect = new AroundEffect(player, Effect.SMOKE);
                                                aroundEffect.startEffect();
                                            }
                                            else if(effect==2){
                                                AroundEffect aroundEffect = new AroundEffect(player, Effect.MOBSPAWNER_FLAMES);
                                                aroundEffect.startEffect();
                                            }
                                            else if(effect==3){
                                                AroundEffect aroundEffect = new AroundEffect(player, Effect.ENDER_SIGNAL);
                                                aroundEffect.startEffect();
                                            }
                                        }
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                    AroundEffect.status = true;
                                    commandSender.sendMessage("特效开启成功!");
                                }
                            }.runTaskAsynchronously(main.plugin);
                        }
                        else{
                            commandSender.sendMessage("当前特效已开启，请先关闭特效!");
                        }
                        return true;
                    } else if (strings[0].equalsIgnoreCase("off")) {//如果是关闭武器特效指令
                        if(AroundEffect.status==true) {
                            AroundEffect.status = false;
                            commandSender.sendMessage("特效关闭成功!");
                        }
                        else{
                            commandSender.sendMessage("当前特效已关闭，请勿重复关闭!");
                        }
                        return true;
                    }
                    else if (strings[0].equalsIgnoreCase("up")) {//如果是赋予武器特效指令
                        effectInventory.effectGUI(player);
                        return true;
                    }
                }
            } else {
                commandSender.sendMessage("参数过多或不足");
            }
        }
        else{
            commandSender.sendMessage("你没有这个功能！");
        }
        return false;
    }
}
