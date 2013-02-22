package com.optimism.systems;

import com.artemis.Entity;
import com.artemis.systems.VoidEntitySystem;
import com.optimism.Factory;
import com.optimism.GameData;
import com.optimism.Settings;
import com.optimism.tools.Tool;


public class UpgradeSystem extends VoidEntitySystem {

	private GameData data;
	private long previousScore = 0;
	
	private long[] newShipMilestones = new long[] {200, 500, 1000};
	

	public UpgradeSystem(GameData data) {
		this.data = data;
	}
	
	@Override
	protected void processSystem() {
		for (int i=0; i<newShipMilestones.length; i++) {
			long milestone = newShipMilestones[i];
			if (previousScore < milestone && data.score >= milestone) {
				increaseShipNumber();
			}
		}
		
		previousScore = data.score;
	}
	
	private void increaseShipNumber() {
		Tool.print("Ships up!");
		int numShips = data.players.length + 1;
		Entity[] newPlayers = Factory.makeShipCircle(world, numShips, Settings.circleRadius);
		data.replacePlayers(newPlayers);
	}

}
