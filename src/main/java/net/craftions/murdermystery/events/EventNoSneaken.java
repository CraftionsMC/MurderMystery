package net.craftions.murdermystery.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class EventNoSneaken implements Listener {

    @EventHandler
    public void NoSneaking(PlayerToggleSneakEvent e){
        e.setCancelled(true);

    }
}
