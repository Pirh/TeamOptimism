package com.optimism.components;


// This is a component. It only has DATA. No logic.
public class Velocity extends Vec {

	public Velocity(double x, double y) {
		super(x,y);
	}
	
	public Velocity(Vec v) {
		super(v);
	}
	
}
