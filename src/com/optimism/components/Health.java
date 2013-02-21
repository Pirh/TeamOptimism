package com.optimism.components;

import com.artemis.Component;

public class Health extends Component{
	private int health;
	
	public Health(int h){
		this.health = h;
	}
	
	public void gain(int i){
		this.health = this.health + i;
	}
	
	public void lose(int i){
		this.health = this.health - i;
	}
	
	public boolean dead() {
		return this.health <= 0;
	}
}
