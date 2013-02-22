package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.Projector;
import com.optimism.components.Clickable;
import com.optimism.components.Position;
import com.optimism.components.Size;
import com.optimism.input.Input;
import com.optimism.tools.Tuple2Int;


public class MouseInputSystem extends EntityProcessingSystem {
	
	private Input input = new Input();
	
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Size> sm;
	@Mapper ComponentMapper<Clickable> cm;
	
	
	@SuppressWarnings("unchecked")
	public MouseInputSystem(Input input) {
		
		super(Aspect.getAspectForAll(Position.class, Size.class, Clickable.class));
		
		this.input = input;
		
	}
	
	@Override
	public void process(Entity entity) {
		
		Position pos = pm.get(entity);
		Size size = sm.get(entity);
		
		Tuple2Int screenPos = Projector.worldToScreen(pos);
		Tuple2Int screenSize = Projector.worldToScreen(size);
		
		
		cm.get(entity).makeClicked(true);
		
	}
	
	public boolean isClicked(Tuple2Int screenPos, Tuple2Int screenSize, Entity entity){
		
		
		if(input.isMouseDown(1)){
			
			if( (screenPos.getX() < input.mousePosX) && (input.mousePosX < screenSize.getX()) ){
				
				if( (screenPos.getY() < input.mousePosY) && (input.mousePosY < screenSize.getY()) ){
					
					return true;
					
				}
			
			}
			
		}
		
		return false;
		
	}
	
}