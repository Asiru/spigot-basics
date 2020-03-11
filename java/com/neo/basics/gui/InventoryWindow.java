package com.neo.basics.gui;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryWindow {
	private Inventory inventory;
	
	private static class Builder {
		private int size;
		private InventoryType type;
		private String title;
	}
}
