package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.optimism.collision.Col;
import com.optimism.components.Body;
import com.optimism.components.Damage;
import com.optimism.components.Health;
import com.optimism.components.Position;


public class CollisionSystem extends EntitySystem {

	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Body> bm;
	@Mapper ComponentMapper<Health> hm;
	@Mapper ComponentMapper<Damage> dm;
	
	
	@SuppressWarnings("unchecked")
	public CollisionSystem() {
		
		super(Aspect.getAspectForAll(Position.class, Body.class));
		
	}
	
	
	@Override
	// This just means the system is always being run.
	protected boolean checkProcessing() {
		
		return true;
		
	}
	
	
	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		
		for(int i1 = 0; i1 < entities.size(); i1++){
			
			for(int i2 = i1 + 1; i2 < entities.size(); i2++){

				boolean collided = Col.detect(
						entities.get(i1).getComponent(Body.class),
						entities.get(i1).getComponent(Position.class),
						entities.get(i2).getComponent(Body.class),
						entities.get(i2).getComponent(Position.class)
						);
				
				if(collided){
					collide(entities.get(i1), entities.get(i2));
				}
				
			}
			
		}
		
	}
	
	
	
	protected void collide(Entity e1, Entity e2) {

		if(hm.getSafe(e1) != null){
			
			if(dm.getSafe(e2) != null){
				
				hm.get(e1).lose(dm.get(e2).damage);
				e2.deleteFromWorld();
				
			}
			
		}

		if(hm.getSafe(e2) != null){
			
			if(dm.getSafe(e1) != null){
				
				hm.get(e2).lose(dm.get(e1).damage);
				e1.deleteFromWorld();
				
			}
			
		}
		
	}
	
	
	
}
