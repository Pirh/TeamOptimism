package com.optimism.components;

import com.artemis.Component;
import com.optimism.collision.Circle;


public class Body extends Component {
	
	
	
	public Circle[] bodies;
	
	
	
	public Body(Circle... bodies){
		
		this.bodies = bodies;
		
	}
	
	
	
	public int getSize(){
		
		return bodies.length;
		
	}
	
	
	
	public Circle getCircle(int i){
		
		return bodies[i];
		
	}
	
	
	
}
