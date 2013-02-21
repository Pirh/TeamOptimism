package com.optimism.collision;

import com.optimism.components.Body;

public class Col {
	
	
	
	public static boolean detect(Body b1, Body b2){
		
		for(int i1 = 0; i1 < b1.getSize(); i1++){
			
			for(int i2 = 0; i2 < b2.getSize(); i2++){
				
				if((b1.getCircle(i1).loc.distance(b2.getCircle(i2).loc)) < (b1.getCircle(i1).getRad() + b2.getCircle(i2).getRad())){
					
					return true;
				
				}
				
			}
		}
		
		
		return false;
			
		
	}
	
	
	
}
