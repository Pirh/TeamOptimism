package com.optimism.components;

import com.artemis.Component;

public class Weapon extends Component {

	public double reloadTime;
	public double reloadTimeLeft;
	
	public Weapon(double reloadTime) {
		this.reloadTime = reloadTime;
	}
	
	public void reload(double time) {
		reloadTimeLeft -= time;
	}
	
	public void fired() {
		reloadTimeLeft = reloadTime;
	}
	
	public boolean ready() {
		return reloadTimeLeft <= 0;
	}
	
}
