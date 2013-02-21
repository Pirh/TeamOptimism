package com.optimism.systems;

import java.awt.event.KeyEvent;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.Arena;
import com.optimism.components.Controllable;
import com.optimism.components.Position;
import com.optimism.components.Vec;
import com.optimism.input.Input;


public class PlayerControlSystem extends EntityProcessingSystem {
	
	private Input input;
	
	@Mapper ComponentMapper<Position> pm;
	
	@SuppressWarnings("unchecked")
	public PlayerControlSystem(Input input) {
		super(Aspect.getAspectForAll(Position.class, Controllable.class));
		this.input = input;
	}
	
	@Override
	public void process(Entity entity) {
		double dTheta = 0;
		if (input.isKeyDown(KeyEvent.VK_LEFT)) {
			dTheta = 1;
		} else if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
			dTheta = -1;
		}
		dTheta *= world.getDelta();
		Position pos = pm.get(entity);
		Vec stretch = pos.copy().sub(Arena.circleCentre);
		stretch.rotate(dTheta);
		pos.set(Arena.circleCentre).add(stretch);
	}

}
