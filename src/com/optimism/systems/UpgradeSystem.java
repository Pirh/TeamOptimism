package com.optimism.systems;

import com.artemis.Entity;
import com.artemis.systems.VoidEntitySystem;
import com.optimism.Factory;
import com.optimism.GameData;
import com.optimism.Settings;


public class UpgradeSystem extends VoidEntitySystem {

	private GameData data;
	
	private long[] levelMilestones = new long[] {200, 500, 1000, 2000, 5000, 1<<30};
	

	public UpgradeSystem(GameData data) {
		this.data = data;
	}
	
	@Override
	protected void processSystem() {
		long nextLevel = levelMilestones[data.level];
		if (data.score >= nextLevel) {
			levelUp();
		}
	}
	
	private void levelUp() {
		data.level += 1;
		
		addShip();
	}

	private void addShip() {
		int numShips = data.players.length + 1;
		Entity[] newPlayers = Factory.makeShipCircle(world, numShips, Settings.circleRadius);
		data.replacePlayers(newPlayers);
	}

}
