package com.optimism.tools;

import java.util.Random;

public class Tool {
	
	public static Random r = new Random();
	
	public static void print(String str){
		
		System.out.println(str);
		
	}
	
	public static double clamp(double min, double value, double max) {
		return Math.max(min, Math.min(value, max));
	}
	
	public static double randAngle() {
		return r.nextFloat() * 2 * Math.PI;
	}
	
}
