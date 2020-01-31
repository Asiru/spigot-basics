package com.neo.basics;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public final class Basics extends JavaPlugin implements Listener, CommandExecutor {
	private static final boolean DEBUG = true;
	private Set<Reloadable> reloadables;
	
	// Managers
	// Listeners
	// CommandExecutors
	
	@Override
	public void onEnable() {
		// Plugin startup logic
		this.reloadables = new HashSet<>();
		
		// Initialize Managers
		
		// Initialize Listeners
		if(DEBUG) {
			registerListener(this);
		}
		
		// Initialize Commands
		if(DEBUG) {
			registerCommand("debug", this);
		}
	}
	
	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
	
	public void reloadAll() {
		for(Reloadable r : reloadables)
			r.reload();
	}
	
	// Debugging events here
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// Debugging commands here
		
		return false;
	}
	
	private void registerListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, this);
		if(listener instanceof Reloadable) {
			registerReloadable((Reloadable) listener);
		}
	}
	
	private void registerCommand(String name, CommandExecutor executor) {
		PluginCommand command = getCommand(name);
		if(command != null) {
			command.setExecutor(executor);
			if(executor instanceof TabCompleter) {
				command.setTabCompleter((TabCompleter) executor);
			}
			if(executor instanceof Reloadable) {
				registerReloadable((Reloadable) executor);
			}
		} else {
			getLogger().log(Level.SEVERE, "Could not register command: /" + name);
		}
	}
	
	private void registerReloadable(Reloadable reloadable) {
		this.reloadables.add(reloadable);
	}
}
