package com.optimism.tools;

public class Tool {
	
	public static void print(String str){
		
		System.out.println(str);
		
	}
	
	public static double clamp(double min, double value, double max) {
		return Math.max(min, Math.min(value, max));
	}
	
}
