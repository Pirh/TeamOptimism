package com.optimism.components;


// This is a component. It only has DATA. No logic.
public class Position extends Vec {
	
	public Position(double x, double y) {
		super(x,y);
	}
	
	public Position(Vec v) {
		super(v);
	}
	
}
