package com.optimism.components;

import java.util.ArrayList;

import com.artemis.Component;
import com.optimism.collision.Circle;

public class Body extends Component {
	
	
	
	public ArrayList<Circle> bodies;
	
	
	
	public Body(ArrayList<Circle> bodies){
		
		this.bodies = bodies;
		
	}
	
	
	
	public int getSize(){
		
		return bodies.size();
		
	}
	
	
	
	public Circle getCircle(int i){
		
		return bodies.get(i);
		
	}
	
	
	
}
