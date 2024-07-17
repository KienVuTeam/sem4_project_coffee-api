package com.project.others;

public class StringHandle {
	private static StringHandle instance = null;
	
	public static StringHandle getInstance() {
		if (instance == null) {
			instance = new StringHandle();
		}
		return instance;
	}
	
	public String leftWid(String s, int size) {
		 return s.substring(0, Math.min(size, s.length()));
	}
}
