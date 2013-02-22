package com.optimism;

import com.artemis.Entity;


public class GameData {
	
	public Entity[] players;
	public long score = 0;
	public int level = 0;
	
	public GameData(Entity[] playerShips) {
		this.players = playerShips;
	}
	
	public void replacePlayers(Entity[] replacements) {
		for (int i=0; i<players.length; i++) {
			players[i].deleteFromWorld();
		}
		this.players = replacements;
	}

}
