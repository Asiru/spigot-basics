package com.neo.basics.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryWindow {
	private Inventory inventory;
	
	private InventoryWindow(Inventory inventory, Map<Integer, ItemIcon> icons) {
		this.inventory = inventory;
		for(Map.Entry<Integer, ItemIcon> entry : icons.entrySet()) {
			setSlot(entry.getKey(), entry.getValue());
		}
	}
	
	public void open(Player player) {
		player.openInventory(inventory);
	}
	
	public boolean isOpen() {
		return !inventory.getViewers().isEmpty();
	}
	
	public void setSlot(int slot, ItemIcon icon) {
		if(slot < 0 || slot >= inventory.getSize()) {
			return;
		}
		if(icon == null) {
			inventory.setItem(slot, null);
		} else {
			inventory.setItem(slot, icon.getDisplay());
		}
	}
	
	public static class Builder {
		private InventoryType type;
		private int rows;
		private String title;
		private Map<Integer, ItemIcon> icons;
		
		private Builder(InventoryType type, int rows) {
			this.type = type;
			this.rows = rows;
			this.title = null;
			this.icons = new HashMap<>();
		}
		
		public Builder setType(InventoryType type) {
			this.type = type;
			return this;
		}
		
		public Builder setRows(int rows) {
			this.rows = rows;
			return this;
		}
		
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}
		
		public Builder setSlot(int slot, ItemIcon icon) {
			this.icons.put(slot, icon);
			return this;
		}
		
		public Builder clearSlot(int slot) {
			this.icons.remove(slot);
			return this;
		}
		
		public InventoryWindow build() {
			String colorTitle = "Menu";
			if(title != null) {
				colorTitle = ChatColor.translateAlternateColorCodes('&', this.title);
			}
			
			Inventory inventory;
			if(type == null) {
				inventory = Bukkit.createInventory(null, rows * 9, colorTitle);
			} else {
				inventory = Bukkit.createInventory(null, type, colorTitle);
			}
			return new InventoryWindow(inventory, icons);
		}
		
		public static Builder create(int rows) {
			return new Builder(null, rows);
		}
		
		public static Builder create(InventoryType type) {
			return new Builder(type, -1);
		}
	}
}
