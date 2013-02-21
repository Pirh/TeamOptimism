package com.optimism.components;

import com.artemis.Component;


public class Orientation extends Component {
	
	public double angle;
	public double angularVel = 0;
	
	public Orientation(double angle, double angleVel) {
		this.angle = angle;
		this.angularVel = angleVel;
	}
	
	public Orientation(double angle) {
		this(angle, 0.0);
	}

}
