package com.neo.basics.manager;

import java.util.Map;

public interface PersistentManagerEntry extends ManagerEntry {
	Map<String, Object> toConfiguration();
}
