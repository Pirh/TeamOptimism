package com.optimism.collision;

import com.optimism.components.Body;
import com.optimism.components.Vec;

public class Col {
	
	
	
	public static boolean detect(Body b1, Vec p1, Body b2, Vec p2){
		
		for(int i1 = 0; i1 < b1.getSize(); i1++){
			
			for(int i2 = 0; i2 < b2.getSize(); i2++){

				Vec vec1 = b1.getCircle(i1).loc.copy().add(p1);
				Vec vec2 = b2.getCircle(i2).loc.copy().add(p2);
				
				if((vec1.distance(vec2)) < (b1.getCircle(i1).getRad() + b2.getCircle(i2).getRad())){
					
					return true;
				
				}
				
			}
		}
		
		
		return false;
			
		
	}
	
	
	
}
