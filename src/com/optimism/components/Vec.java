package com.optimism.components;

import com.artemis.Component;

public class Vec extends Component {

	public double x;
	public double y;
	
	public Vec(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec(Vec vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public Vec add(Vec other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	@Override
	public String toString() {
		return String.format("(%f, %f)", x, y);
	}
	
}
