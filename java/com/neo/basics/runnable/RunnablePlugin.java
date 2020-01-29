package com.neo.basics.runnable;

import org.bukkit.plugin.java.JavaPlugin;

public class RunnablePlugin extends JavaPlugin {
	private ExampleRunnable runnable;
	
	@Override
	public void onEnable() {
		this.runnable = new ExampleRunnable(this);
		this.runnable.start();
	}
	
	@Override
	public void onDisable() {
		// existing BukkitRunnable objects are automatically cancelled
		// this.runnable.cancel();
	}
}
