package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.components.Orientation;
import com.optimism.components.Position;
import com.optimism.components.Vec;
import com.optimism.components.Velocity;

@SuppressWarnings("unchecked")
public class MovementSystem extends EntityProcessingSystem {
	
	// This auto-creates some ComponentMappers. This is the fastest way to get components.
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Velocity> vm;
	@Mapper ComponentMapper<Orientation> om;

	public MovementSystem() {
		super(Aspect.getAspectForAll(Position.class, Velocity.class));	// This line decides which entities to care about.
	}
	
	@Override
	public void process(Entity entity) {
		float dt = world.getDelta();
		Position pos = pm.get(entity);  // Get the position of an entity
		Velocity vel = vm.get(entity);  // Get the velocity of an entity
		pos.add(new Vec(vel).mul(dt));	// Add velocity * deltaTime
		
		Orientation ori = om.getSafe(entity);
		if (ori != null) {
			ori.angle += ori.angularVel * dt;
		}
	}
	
}
