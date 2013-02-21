package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.optimism.components.Body;
import com.optimism.components.Position;


public class CollisionSystem extends EntitySystem {

	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Body> bm;
	
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
		// for entity1 in entities
			// for entity2 in entities
				// Do they collide?
					// Yeah? OK do something then.
	}	
	
	
}
