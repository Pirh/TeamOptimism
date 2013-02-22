package com.optimism;

import com.artemis.Entity;
import com.optimism.components.Text;


public class GameData {
	
	public Entity[] players;
	public long score = 0;
	public int planetHealth = 100;
	public int level = 0;

	public Entity eScore;
	public Entity eHealth;
	
	public GameData(Entity[] playerShips) {
		this.players = playerShips;
	}
	
	public void replacePlayers(Entity[] replacements) {
		for (int i=0; i<players.length; i++) {
			players[i].deleteFromWorld();
		}
		this.players = replacements;
	}
	
	public void gainScore(long amount) {
		score += amount;
		eScore.addComponent(new Text("Score: " + score));
	}
	
	public void loseHealth(long amount) {
		planetHealth += amount;
		eHealth.addComponent(new Text("Planet health: " + planetHealth));
	}

}
