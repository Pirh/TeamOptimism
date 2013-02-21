package com.optimism.collision;

import com.optimism.components.Vec;

public class Circle {
	
	
	
	public Vec loc = new Vec(0,0);
	public int rad = 0;
	
	
	
	public Circle(Vec loc, int rad) {
		
		this.loc = loc;
		this.rad = rad;
		
	}
	
	

	public Vec getLoc() {
		
		return this.loc;
		
	}
	
	

	public int getRad() {
		
		return this.rad;
		
	}
	
	
}
