package com.neo.basics.util;

import org.bukkit.Bukkit;

public class Utils {
	private static final int MAJOR_VER = 0, MINOR_VER = 1, PATCH_VER = 2;
	private static final int[] VERSION = new int[3];
	
	static {
		// determine current Bukkit version
		String[] split = Bukkit.getBukkitVersion().split("-")[0].split("\\.");
		String majorVer = split[0]; // For 1.10 will be "1"
		String minorVer = split[1]; // For 1.10 will be "10"
		String patchVer = split.length > 2 ? split[2] : "0"; // For 1.10 will be "0", for 1.9.4 will be "4"
		
		VERSION[MAJOR_VER] = Integer.valueOf(majorVer);
		VERSION[MINOR_VER] = Integer.valueOf(minorVer);
		VERSION[PATCH_VER] = Integer.valueOf(patchVer);
	}
	
	// parameters are the earliest necessary version
	public static boolean isVersionBefore(int majorVer, int minorVer, int patchVer) {
		return VERSION[MAJOR_VER] < majorVer || VERSION[MINOR_VER] < minorVer || VERSION[PATCH_VER] < patchVer;
	}
}
