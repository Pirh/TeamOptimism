package com.optimism.systems;

import java.awt.Color;
import java.awt.Graphics;

import com.artemis.Entity;
import com.artemis.utils.ImmutableBag;
import com.optimism.components.Body;
import com.optimism.components.Position;
import com.optimism.components.Vec;
import com.optimism.input.Input;


public class DebugBodySystem extends CollisionSystem {
	
	private Graphics g;
	private Input input;


	public DebugBodySystem(Graphics g, Input input){
		super();
		this.g = g;
		this.input = input;
		
	}
	
	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		
		if(input.isKeyToggle(114)){
		
			for(int i1 = 0; i1 < entities.size(); i1++){
			
				Entity entity = entities.get(i1);
				Body body = entity.getComponent(Body.class);
			
				for(int i2 = 0; i2 < body.getSize(); i2++){

					Vec vec = body.getCircle(i2).loc.copy().add(entity.getComponent(Position.class));
					double rad = body.getCircle(i2).rad;

					g.setColor(Color.GREEN);
					g.fillOval(
							(int)(vec.x - rad),
							(int)(vec.y - rad),
							(int)rad*2,
							(int)rad*2
					);
				
				}
			
			}
		}

		super.processEntities(entities);
		
	}
	
	@Override
	public void collide(Entity e1, Entity e2) {
		
		if(input.isKeyToggle(114)){
			
			Body body = e1.getComponent(Body.class);
			
			for(int i = 0; i < body.getSize(); i++){

				Vec vec = body.getCircle(i).loc.copy().add(e1.getComponent(Position.class));
				double rad = body.getCircle(i).rad;
				
				g.setColor(Color.RED);
				g.fillOval(
					(int)(vec.x - rad),
					(int)(vec.y - rad),
					(int)rad*2,
					(int)rad*2
				);
			
			}
		}
	}
	
}
