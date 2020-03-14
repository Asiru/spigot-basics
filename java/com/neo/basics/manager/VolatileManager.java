package com.neo.basics.manager;

import java.util.HashMap;
import java.util.Map;

public class VolatileManager<T extends ManagerEntry> implements Manager<T> {
	protected Map<String, T> registry;
	
	protected VolatileManager() {
		this.registry = new HashMap<>();
	}
	
	@Override
	public T get(String id) {
		return registry.get(id);
	}
	
	@Override
	public T register(T object) {
		return registry.put(object.getId(), object);
	}
	
	@Override
	public T unregister(String id) {
		return registry.remove(id);
	}
}
