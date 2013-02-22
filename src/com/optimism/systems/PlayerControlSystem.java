package com.optimism.systems;

import java.awt.event.KeyEvent;

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
import com.optimism.input.HackInput;


public class PlayerControlSystem extends EntityProcessingSystem {
	
	private HackInput input;
	
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Orientation> om;
	
	@SuppressWarnings("unchecked")
	public PlayerControlSystem(HackInput input) {
		super(Aspect.getAspectForAll(Position.class, Controllable.class));
		this.input = input;
	}
	
	@Override
	public void process(Entity entity) {
		double dTheta = 0;
		if (input.leftHack > 0) {
			dTheta = -1;
		} else if (input.rightHack > 0) {
			dTheta = 1;
		}
		dTheta *= world.getDelta() * Settings.moveSpeed;
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
