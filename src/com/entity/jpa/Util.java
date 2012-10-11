package com.entity.jpa;

public class Util {
	
	private static String location = "";

	public static String getLocation() {
		return location;
	}

	public static void setLocation(String location) {
		Util.location = location;
	}
	
	public static String getOutFileLocation() {
		return location+"data/outFiles/";
	}
	

}
