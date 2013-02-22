package com.optimism.scripts;

import com.artemis.Entity;
import com.artemis.World;
import com.optimism.Settings;
import com.optimism.components.Position;
import com.optimism.components.Vec;
import com.optimism.components.Velocity;
import com.optimism.tools.Tool;


public class FlutterScript extends ScriptAction {
	
	public int frames = 0;
	public double waitTime = 0.0;
	public Vec destination = null;
	
	@Override
	public void run(World world, Entity self) {
		if (destination == null) {
			if (waitTime <= 0) {
				// Get a new destination
				Position pos = self.getComponent(Position.class);
				destination = pos.copy().sub(Settings.circleCentre);
				destination.rotate(Tool.randAngle()/5);
				destination.mul(1.4).add(Settings.circleCentre);
				self.getComponent(Velocity.class).set(destination.copy().sub(pos).normalize().mul(Settings.enemySpeed*5));
			} else {
				waitTime -= world.delta;
			}
		} else {
			// Check if destination has been reached.
			Vec disp = destination.copy().sub(self.getComponent(Position.class));
			Vec vel = self.getComponent(Velocity.class);
			if (disp.dot(vel) < 0) {
				// Destination reached
				destination = null;
				waitTime = 2.0;
				vel.set(new Vec(0,0));
			}
		}
		if (frames % 5 == 0) {
			Position pos = self.getComponent(Position.class);
			pos.add(new Vec(0,2).rotate(Tool.randAngle()));
		}
		frames += 1;
	}

}
