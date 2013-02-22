package com.optimism.systems;

import java.awt.Color;

import com.artemis.Entity;
import com.artemis.systems.VoidEntitySystem;
import com.optimism.Factory;
import com.optimism.GameData;
import com.optimism.Settings;
import com.optimism.components.Lifespan;
import com.optimism.components.Position;
import com.optimism.components.Velocity;


public class UpgradeSystem extends VoidEntitySystem {

	private GameData data;
	
	private long[] levelMilestones = new long[] {150, 750, 2000, 5000, 10000, 1L<<62};
	private long[] vanityMilestones = new long[] {20000, 50000, 100000, 200000, 500000, 1000000};
	
	private long lastMilestone = 0;
	
	private Color goldColour = new Color(255,255,200);

	public UpgradeSystem(GameData data) {
		this.data = data;
	}
	
	@Override
	protected void processSystem() {
		long nextLevel = levelMilestones[data.level];
		if (data.score >= nextLevel) {
			levelUp();
		}
		for (long milestone: vanityMilestones) {
			if (data.score >= milestone && milestone > lastMilestone) {
				lastMilestone = milestone;
				applause();
			}
		}
	}
	
	private void applause() {
		data.flash = 3;
		data.flashCol = goldColour;
		Entity label = Factory.label(world, ""+lastMilestone+"p!", new Position(380,300), Color.yellow);
		label.addComponent(new Lifespan(1.0));
		label.addComponent(new Velocity(0,-100));
	}
	
	private void levelUp() {
		data.level += 1;
		data.flash = 3;
		data.flashCol = Color.white;
		Entity label = Factory.label(world, "Level Up!", new Position(380,300), Color.yellow);
		label.addComponent(new Lifespan(0.8));
		label.addComponent(new Velocity(0,-100));
		
		addShip();
	}

	private void addShip() {
		int numShips = data.level + 2;
		Entity[] newPlayers = Factory.makeShipCircle(world, numShips, Settings.circleRadius);
		data.replacePlayers(newPlayers);
	}

}
