package com.neo.basics.gui;

import org.bukkit.Material;

public class Example {
	public void test() {
		ItemIcon testIcon = ItemIcon.Builder.create(Material.DIAMOND)
				.setDisplayName("Test Icon")
				.addLore("This is some test lore!")
				.build();
	}
}
