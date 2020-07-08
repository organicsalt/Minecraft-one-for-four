package com.organicsalt.minecraft.commands;

import com.organicsalt.minecraft.GUI.effectInventory;
import com.organicsalt.minecraft.GUI.storeInventory;
import com.organicsalt.minecraft.effects.AroundEffect;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class weapon_effects implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            if (s.equalsIgnoreCase("weapon_effects")) {
                if (strings.length == 1) {
                    Player player = (Player) commandSender;
                    if (strings[0].equalsIgnoreCase("on")) {//如果是开启武器特效指令
                        if(AroundEffect.status==false){
                            if (player.getItemInHand().getType() == Material.WOOD_SWORD) {
                                AroundEffect aroundEffect = new AroundEffect(player, Effect.MOBSPAWNER_FLAMES);
                                aroundEffect.startEffect();
                            } else if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
                                AroundEffect aroundEffect = new AroundEffect(player, Effect.SMOKE);
                                aroundEffect.startEffect();
                            } else if (player.getItemInHand().getType() == Material.IRON_SWORD) {
                                AroundEffect aroundEffect = new AroundEffect(player, Effect.ENDER_SIGNAL);
                                aroundEffect.startEffect();
                            }
                            AroundEffect.status = true;
                            commandSender.sendMessage("特效开启成功!");
                        }
                        else{
                            commandSender.sendMessage("当前特效已开启，请先关闭特效!");
                        }
                        return true;
                    } else if (strings[0].equalsIgnoreCase("off")) {//如果是关闭武器特效指令
                        if(AroundEffect.status==true) {
                            AroundEffect aroundEffect = new AroundEffect(player, null);
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
