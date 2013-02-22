package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.Factory;
import com.optimism.Settings;
import com.optimism.components.Controllable;
import com.optimism.components.Position;
import com.optimism.components.Velocity;
import com.optimism.components.Weapon;
import com.optimism.input.HackInput;


public class PlayerFiringSystem extends EntityProcessingSystem {
	
	private HackInput input;
	
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Weapon> wm;
	
	@SuppressWarnings("unchecked")
	public PlayerFiringSystem(HackInput input) {
		super(Aspect.getAspectForAll(Position.class, Weapon.class, Controllable.class));
		this.input = input;
	}
	
	@Override
	public void process(Entity entity) {
		Weapon wep = wm.get(entity);
		if (input.spaceHack > 0 && wep.ready()) {
			Position pos = pm.get(entity);
			Velocity vel = new Velocity(Settings.circleCentre.copy().sub(pos).normalize().mul(Settings.bulletSpeed));
			Factory.playerBullet(world, new Position(pos), vel);
			wep.fired();
		}
		wep.reload(world.getDelta());
	}

}
