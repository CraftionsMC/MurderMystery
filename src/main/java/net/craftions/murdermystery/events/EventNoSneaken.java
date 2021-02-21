package net.craftions.murdermystery.events;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class EventSneak implements Listener {

    public void (PlayerToggleSneakEvent e){
        e.setCancelled(PlayerToggleSneakEvent);

    }
}
