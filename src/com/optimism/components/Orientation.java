package com.optimism.components;

import com.artemis.Component;


public class Orientation extends Component {
	
	public double angle;
	public double angularVel = 0;
	
	public Orientation(double angle) {
		this.angle = angle;
	}

}
