package com.optimism.scripts;

import java.util.Random;

import com.artemis.Entity;
import com.artemis.World;
import com.optimism.Settings;
import com.optimism.components.Vec;
import com.optimism.components.Velocity;

public class DirectionalMovementScript extends ScriptAction {
	
	double speed;
	
	public DirectionalMovementScript(double speed) {
		this.speed = speed;
	}
	
	@Override
	public void initialize(World world, Entity self) {
		Random r = new Random();
		double randAngle = r.nextFloat()*2*Math.PI;
		Velocity vel = new Velocity(new Vec(0,Settings.enemySpeed*speed).rotate(randAngle));
		self.addComponent(vel);
	}

}
