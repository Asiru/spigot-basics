package com.neo.basics.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Example {
	public static void test(Player player) {
		ItemIcon testIcon = ItemIcon.Builder.create(Material.DIAMOND)
		                    .setDisplayName("Test Icon")
		                    .addLore("This is some test lore!")
		                    .build();
		
		InventoryWindow testWindow = InventoryWindow.Builder.create(9)
		                             .setTitle("&5Test Menu")
		                             .setSlot(4, testIcon)
		                             .build();
		
		testWindow.open(player);
	}
}
