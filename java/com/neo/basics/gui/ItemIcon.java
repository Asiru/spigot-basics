package com.neo.basics.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemIcon {
	private final ItemStack display;
	private InventoryWindow destination;
	private Runnable onClick;
	
	private ItemIcon(ItemStack display) {
		this.display = display;
	}
	
	public ItemStack getDisplay() {
		return display;
	}
	
	public InventoryWindow getDestination() {
		return destination;
	}
	
	public void setDestination(InventoryWindow destination) {
		this.destination = destination;
	}
	
	public Runnable getOnClick() {
		return onClick;
	}
	
	public void setOnClick(Runnable onClick) {
		this.onClick = onClick;
	}
	
	public static class Builder {
		private final Material material;
		private int amount;
		private String displayName;
		private List<String> lore;
		
		private Builder(Material material) {
			this.material = material;
			this.amount = 1;
			this.displayName = null;
			this.lore = new ArrayList<>();
		}
		
		public Builder setAmount(int amount) {
			this.amount = amount;
			return this;
		}
		
		public Builder setDisplayName(String displayName) {
			this.displayName = displayName;
			return this;
		}
		
		public Builder clearLore() {
			this.lore.clear();
			return this;
		}
		
		public Builder addLore(String lore) {
			this.lore.add(ChatColor.translateAlternateColorCodes('&', lore));
			return this;
		}
		
		public ItemIcon build() {
			ItemStack display = new ItemStack(material);
			display.setAmount(amount);
			ItemMeta meta = Bukkit.getItemFactory().getItemMeta(material);
			if(displayName != null) {
				meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
			}
			meta.setLore(lore);
			display.setItemMeta(meta);
			
			return new ItemIcon(display);
		}
		
		public static Builder create(Material material) {
			return new Builder(material);
		}
	}
}
