package com.optimism.components;

import com.artemis.Component;
import com.optimism.collision.Circle;


public class Body extends Component {
	
	public enum Team {
		ALLY, ENEMY
	}
	
	public Circle[] bodies;
	public Team team;
	
	
	public Body(Team team, Circle... bodies){
		
		this.team = team;
		this.bodies = bodies;
		
	}
	
	
	
	public int getSize(){
		
		return bodies.length;
		
	}
	
	
	
	public Circle getCircle(int i){
		
		return bodies[i];
		
	}
	
	
	
}
