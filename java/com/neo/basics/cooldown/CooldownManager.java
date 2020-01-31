package com.neo.basics.cooldown;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class CooldownManager<T> {
	private Map<T, Cooldown> cooldowns;
	
	public CooldownManager() {
		this.cooldowns = new ConcurrentHashMap<>();
	}
	
	public final long getCooldown(T object) {
		if(cooldowns.containsKey(object))
			return cooldowns.get(object).cooldown;
		return 0;
	}
	
	public final void setCooldown(T object, long cooldown) {
		Cooldown c = cooldowns.get(object);
		if(c == null) {
			c = new Cooldown(object, cooldown);
			cooldowns.put(object, c);
			start(c);
		} else {
			c.cooldown = cooldown;
		}
	}
	
	protected abstract void start(Cooldown cooldown);
	
	private class Cooldown extends BukkitRunnable {
		private T object;
		private long cooldown;
		
		private Cooldown(T object, long cooldown) {
			this.object = object;
			this.cooldown = cooldown;
		}
		
		@Override
		public void run() {
			cooldown--;
			if(cooldown <= 0) {
				cancel();
				cooldowns.remove(object);
			}
		}
	}
}
