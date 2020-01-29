package com.neo.basics.runnable;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ExampleRunnable extends BukkitRunnable {
	// this runnable will fire every 60 seconds
	private static final long PERIOD = 60L;
	
	// this runnable will do something extra every 5 loops
	private static final int INTERVAL = 5;
	
	private JavaPlugin plugin;
	private int elapsed;
	
	public ExampleRunnable(JavaPlugin plugin) {
		this.plugin = plugin;
		this.elapsed = 0;
	}
	
	@Override
	public void run() {
		// Code here runs every loop
		
		if(elapsed >= INTERVAL) {
			// Code here only runs every interval of loops
			
			elapsed = 0;
		}
		elapsed++;
	}
	
	public void start() {
		this.runTaskTimer(plugin, 0L, PERIOD * 20L);
	}
}
