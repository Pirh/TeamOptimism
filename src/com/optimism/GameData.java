package com.optimism;

import java.awt.Color;

import com.artemis.Entity;
import com.optimism.components.Text;


public class GameData {
	
	public Entity[] players;
	public long score = 0;
	public int planetHealth = 20;
	public int level = 0;
	public int flash = 0;
	public int hurt = 0;
	public Color flashCol = Color.white;
	public boolean allShipsDead = false;

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
		String levelText = (level==6) ? "Max Level!" : "Level: "+(level+1);
		eScore.addComponent(new Text(String.format("%s    Score: %d", levelText, score), Color.yellow));
	}
	
	public void loseHealth(long amount) {
		planetHealth -= amount;
		eHealth.addComponent(new Text("Planet health: " + planetHealth, Color.green));
	}

}
