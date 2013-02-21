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
	
	public Vec set(Vec vec) {
		this.x = vec.x;
		this.y = vec.y;
		return this;
	}
	
	public Vec add(Vec other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public Vec sub(Vec other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}
	
	public Vec mul(Vec other) {
		this.x *= other.x;
		this.y *= other.y;
		return this;
	}
	
	public Vec div(Vec other) {
		this.x /= other.x;
		this.y /= other.y;
		return this;
	}
	
	public Vec mul(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}
	
	public Vec div(double scalar) {
		this.x /= scalar;
		this.y /= scalar;
		return this;
	}
	
	public Vec rotate(double angle) {
		double newX = x * Math.cos(angle) - y * Math.sin(angle);
		double newY = x * Math.sin(angle) + y * Math.cos(angle);
		this.x = newX;
		this.y = newY;
		return this;
	}
	
	public double angle() {
		return Math.acos( this.x / this.copy().length() ) * Math.signum(this.y);
	}
	
	public double dot(Vec other) {
		return this.x * other.x + this.y * other.y;
	}
	
	public double length() {
		return Math.sqrt(this.dot(this));
	}
	
	public Vec normalize() {
		double length = this.length();
		if (length > 0) {
			return this.div(length);
		} else {
			return this;
		}
	}
	
	public Vec copy() {
		return new Vec(this);
	}
	
	public double distance(Vec v){
		
		 return Math.sqrt( ((this.x - v.x) * (this.x - v.x)) + ((this.y - v.y) * (this.y - v.y)));
		
	}
	
	@Override
	public String toString() {
		return String.format("(%f, %f)", x, y);
	}
	
}
