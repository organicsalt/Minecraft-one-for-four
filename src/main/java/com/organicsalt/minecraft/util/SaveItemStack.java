package com.organicsalt.minecraft.util;

import com.comphenix.protocol.utility.StreamSerializer;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class SaveItemStack {

    public static ItemStack getItemStack(String data) {
        StreamSerializer ss = new StreamSerializer();
        try{
            return ss.deserializeItemStack(data);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String ToData(ItemStack itemStack){
        StreamSerializer ss = new StreamSerializer();
        try{
            return ss.serializeItemStack(itemStack);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
