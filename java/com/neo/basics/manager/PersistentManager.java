package com.neo.basics.manager;

import com.neo.basics.config.ConfigAccessor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public abstract class PersistentManager<T extends PersistentManagerEntry> extends ConfigAccessor implements Manager<T> {
	protected Map<String, T> cache;
	
	// -1 = save on disable, 0 = save on write, 1+ = save on interval
	private final int saveInterval;
	private boolean autosave;
	
	public PersistentManager(JavaPlugin plugin, String fileName) {
		this(plugin, fileName, -1);
	}
	
	public PersistentManager(JavaPlugin plugin, String fileName, int saveInterval) {
		super(plugin, true, fileName, "data");
		this.cache = new HashMap<>();
		this.saveInterval = saveInterval;
		this.autosave = true;
		load();
		if(saveInterval > 1) {
			(new SaveRunnable()).runTaskTimer(plugin, 0L, 20L * 60L * saveInterval);
		}
	}
	
	protected abstract T load(ConfigurationSection section);
	
	public final void load() {
		boolean autosaveSetting = this.autosave;
		this.autosave = false;
		for(String key : config.getKeys(false)) {
			ConfigurationSection section = config.getConfigurationSection(key);
			if(section != null) {
				register(load(section));
			}
		}
		this.autosave = autosaveSetting;
	}
	
	@Override
	public final T get(String id) {
		return cache.get(id);
	}
	
	@Override
	public final T register(T object) {
		String id = object.getId();
		for(Map.Entry<String, Object> entry : object.toConfiguration().entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			String path = id + "." + key;
			if(value instanceof Map) {
				config.createSection(path, (Map) value);
			} else {
				config.set(path, value);
			}
		}
		if(saveInterval == 0 && autosave) {
			saveConfig();
		}
		return cache.put(id, object);
	}
	
	@Override
	public final T unregister(String id) {
		config.set(id, null);
		if(saveInterval == 0 && autosave) {
			saveConfig();
		}
		return cache.remove(id);
	}
	
	public final void setAutosave(boolean autosave) {
		this.autosave = autosave;
	}
	
	private class SaveRunnable extends BukkitRunnable {
		@Override
		public void run() {
			saveConfig();
		}
	}
}
