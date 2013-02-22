package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.Settings;
import com.optimism.components.Controllable;
import com.optimism.components.Orientation;
import com.optimism.components.Position;
import com.optimism.components.Vec;
import com.optimism.input.Input;


public class PlayerControlSystem extends EntityProcessingSystem {
	
	private Input input;
	private int motion = 0;
	
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Orientation> om;
	
	@SuppressWarnings("unchecked")
	public PlayerControlSystem(Input input) {
		super(Aspect.getAspectForAll(Position.class, Controllable.class));
		this.input = input;
	}
	
	@Override
	public void process(Entity entity) {
		if (input.isKeyDown(37)) {
			motion = -Settings.inputHack;
		} else if (input.isKeyDown(39)) {
			motion = Settings.inputHack;
		} else {
			motion -= Math.signum(motion);
		}
		double dTheta = Math.signum(motion) * world.getDelta() * Settings.moveSpeed;
		Position pos = pm.get(entity);
		Vec stretch = pos.copy().sub(Settings.circleCentre);
		stretch.rotate(dTheta);
		pos.set(Settings.circleCentre).add(stretch);
		
		Orientation ori = om.getSafe(entity);
		if (ori != null) {
			ori.angle = Math.PI + stretch.angle();
		}
	}

}
