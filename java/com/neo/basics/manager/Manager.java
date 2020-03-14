package com.neo.basics.manager;

public interface Manager<T extends ManagerEntry> {
	T get(String id);
	T register(T object);
	T unregister(String id);
}
