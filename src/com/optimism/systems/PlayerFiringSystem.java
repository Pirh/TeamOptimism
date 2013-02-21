package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.Arena;
import com.optimism.Factory;
import com.optimism.components.Controllable;
import com.optimism.components.Position;
import com.optimism.components.Velocity;
import com.optimism.input.Input;
import com.optimism.tools.Tool;


public class PlayerFiringSystem extends EntityProcessingSystem {
	
	private Input input;
	
	@Mapper ComponentMapper<Position> pm;
	
	@SuppressWarnings("unchecked")
	public PlayerFiringSystem(Input input) {
		super(Aspect.getAspectForAll(Position.class, Weapon.class, Controllable.class));
		this.input = input;
	}
	
	@Override
	public void process(Entity entity) {
		if (input.isKeyDown(32)) {
			Tool.print("Firing!");
			Position pos = pm.get(entity);
			Velocity vel = new Velocity(Arena.circleCentre.copy().sub(pos).normalize().mul(100));
			Entity bullet = Factory.makeBullet(world, new Position(pos), vel, "res/player-bullet.png", 1);
		}
	}

}
