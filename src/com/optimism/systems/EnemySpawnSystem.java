package com.optimism.systems;

import java.util.Random;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.systems.IntervalEntitySystem;
import com.artemis.utils.ImmutableBag;
import com.optimism.Factory;
import com.optimism.GameData;
import com.optimism.Settings;
import com.optimism.components.Health;
import com.optimism.components.Position;
import com.optimism.components.Vec;
import com.optimism.components.Velocity;
import com.optimism.tools.Tool;


public class EnemySpawnSystem extends IntervalEntitySystem {

	private GameData data;
	private Random r = new Random();
	
	@SuppressWarnings("unchecked")
	public EnemySpawnSystem(GameData data) {
		super(Aspect.getAspectForAll(Health.class), 0.2f);
		this.data = data;
	}
	
	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		double chance = spawnChance(entities.size());
		if (r.nextFloat() < chance) {
			randomEnemy(world);
		}
	}
	
	private double spawnChance(int numberOfShips) {
		double baseChance = 0.5;
		double levelChance = (data.level+1 / 6.0);
		double densityChance = Tool.clamp(0.01, (20-numberOfShips)/20.0, 1.0);
		return (baseChance * levelChance * densityChance);
	}
	
	private static Entity randomEnemyKind(World world) {
		Random r = new Random();
		int enemyKind = r.nextInt(2);
		double randAngle = r.nextFloat()*Math.PI*2;
		double randRadius = r.nextFloat()*Settings.spawnRadius;
		Position pos = new Position(Settings.circleCentre.copy().add(new Vec(0,randRadius).rotate(randAngle)));
		Entity enemy;
		switch (enemyKind) {
		case 1: enemy = Factory.enemyRedShip(world, pos); break;
		default: enemy = Factory.enemyBlueShip(world, pos); break;
		}
		return enemy;
	}
	public static Entity randomEnemy(World world) {
		Random r = new Random();
		Entity enemy = randomEnemyKind(world);
		double randAngle = r.nextFloat()*Math.PI*2;
		Velocity vel = new Velocity(new Vec(0,Settings.enemySpeed).rotate(randAngle));
		enemy.addComponent(vel);
		return enemy;
	}
	
}
