package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.GameData;
import com.optimism.Settings;
import com.optimism.components.Health;
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
	@Mapper ComponentMapper<Health> hm;
	
	private GameData data;

	public MovementSystem(GameData data) {
		
		super(Aspect.getAspectForAll(Position.class, Velocity.class));	// This line decides which entities to care about.
		
		this.data = data;
		
	}
	
	@Override
	public void process(Entity entity) {
		float dt = world.getDelta();
		Position pos = pm.get(entity);  // Get the position of an entity
		if (pos == null) {
			Tool.print("An entity is missing.");
			return;
		}
		
		if (pos.x < 0 || pos.x > 800 || pos.y < 79 || pos.y > 600) {
			// Entity is out of view, kill it dead.
			entity.deleteFromWorld();
			return;
		}
		double distanceFromCentre = pos.copy().sub(Settings.circleCentre).length();
		Health health = hm.getSafe(entity);
		if (distanceFromCentre > Settings.circleRadius && health != null) {
			if (health.harmful) {
				data.loseHealth(1);
				data.hurt = 8;
				health.harmful = false;
			}
		}
		
		Velocity vel = vm.get(entity);  // Get the velocity of an entity
		pos.add(new Vec(vel).mul(dt));	// Add velocity * deltaTime
		
		Orientation ori = om.getSafe(entity);
		if (ori != null) {
			ori.angle += ori.angularVel * dt;
		}
	}
	
}

class YourePureDeadException extends RuntimeException {
	public YourePureDeadException(String message) { super(message); }
}