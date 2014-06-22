package com.csdig.cms.utils;

public class OSUtils {
	private static String osName = (String) System.getProperties().get("os.name");

	public static boolean isWindowOs() {
		return osName.startsWith("Win") || osName.startsWith("win");
	}

}
