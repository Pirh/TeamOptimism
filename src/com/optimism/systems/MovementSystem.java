package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.GameData;
import com.optimism.Projector;
import com.optimism.components.Orientation;
import com.optimism.components.Position;
import com.optimism.components.Vec;
import com.optimism.components.Velocity;
import com.optimism.tools.Tool;

@SuppressWarnings("unchecked")
public class MovementSystem extends EntityProcessingSystem {
	
	// This auto-creates some ComponentMappers. This is the fastest way to get components.
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Velocity> vm;
	@Mapper ComponentMapper<Orientation> om;
	
	private GameData data;

	public MovementSystem(GameData data) {
		
		super(Aspect.getAspectForAll(Position.class, Velocity.class));	// This line decides which entities to care about.
		
		this.data = data;
		
	}
	
	@Override
	public void process(Entity entity) {
		float dt = world.getDelta();
		Position pos = pm.get(entity);  // Get the position of an entity
		
		double distanceFromOrigin = pos.copy().sub(Projector.centre).length();
		if (distanceFromOrigin > 500) {
			// Entity is out of view, kill it dead.
			entity.deleteFromWorld();
			data.loseHealth(10);
			Tool.print("" + data.planetHealth);
			return;
		}
		
		Velocity vel = vm.get(entity);  // Get the velocity of an entity
		pos.add(new Vec(vel).mul(dt));	// Add velocity * deltaTime
		
		Orientation ori = om.getSafe(entity);
		if (ori != null) {
			ori.angle += ori.angularVel * dt;
		}
	}
	
}
