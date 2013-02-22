package com.optimism.scripts;

import com.artemis.Entity;
import com.artemis.World;
import com.optimism.Settings;
import com.optimism.components.Vec;
import com.optimism.components.Velocity;
import com.optimism.tools.Tool;

public class SquidScript extends ScriptAction {

	private double countdown;
	private boolean done = false;

	public SquidScript(double time) {
		this.countdown = time;
	}
	
	@Override
	public void run(World world, Entity self) {
		if (!done) {
			countdown -= world.delta;
			if (countdown <= 0) {
				Vec vel = new Vec(0,1).mul(Settings.enemySpeed*15).rotate(Tool.randAngle());
				self.getComponent(Velocity.class).set(vel);
				done = true;
			}
		}
	}
	
}
