package com.organicsalt.minecraft.event;

import lk.vexview.event.ButtonClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class VexGuiEvent implements Listener {
    @EventHandler
    public void PlayerClickButton(ButtonClickEvent event){
        int buttonID=(int)event.getButtonID();
        if(buttonID==15||buttonID==18){
            event.getGui().setClosable(true);
            event.getPlayer().closeInventory();
        }
        else{
            //event.getPlayer().sendMessage("buttonid:"+buttonID);
        }
    }
}
