package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.components.Lifespan;


public class ExpirySystem extends EntityProcessingSystem {
	
	@Mapper ComponentMapper<Lifespan> lm;
	
	@SuppressWarnings("unchecked")
	public ExpirySystem() {
		super(Aspect.getAspectForAll(Lifespan.class));
	}

	@Override
	protected void process(Entity entity) {
		Lifespan span = lm.get(entity);
		span.time -= world.delta;
		if (span.time <= 0) {
			entity.deleteFromWorld();
		}
	}

}
