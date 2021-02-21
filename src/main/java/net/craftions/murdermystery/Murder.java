package net.craftions.murdermystery;

import net.craftions.murdermystery.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.craftions.murdermystery.commands.CommandDisableAutoStart;
import net.craftions.murdermystery.commands.CommandEnableAutoStart;
import net.craftions.murdermystery.commands.CommandStart;

public class Murder extends JavaPlugin{
	
	public static String prefix = "[§cMurder§eMystery§r] ";
	public static Murder plugin;
	public static Boolean isStarted = false;
	public static Boolean isStarting = false;
	public static Boolean isProtected = true;
	
	@Override
	public void onEnable() {
		getCommand("start").setExecutor(new CommandStart());
		getCommand("disable-autostart").setExecutor(new CommandDisableAutoStart());
		getCommand("enable-autostart").setExecutor(new CommandEnableAutoStart());
		plugin = this;
		this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getPluginManager().registerEvents(new EventPlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new EventPlayerHit(), this);
		Bukkit.getPluginManager().registerEvents(new EventPlayerDeath(), this);
		Bukkit.getPluginManager().registerEvents(new EventFoodChange(), this);
		Bukkit.getPluginManager().registerEvents(new EventItemDrop(), this);
		Bukkit.getPluginManager().registerEvents(new EventChat(), this);
		Bukkit.getPluginManager().registerEvents(new EventPlayerDisconnect(), this);
		Bukkit.getPluginManager().registerEvents(new EventInventoryClick(), this);
		Bukkit.getPluginManager().registerEvents(new EventCommandDispatch(), this);
		Bukkit.getPluginManager().registerEvents(new EventNoSneaken(), this);
		System.out.println(prefix + "Das Plugin wurde geladen!");
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		System.out.println(prefix + "Das Plugin wurde entladen!");
		super.onDisable();
	}
}
