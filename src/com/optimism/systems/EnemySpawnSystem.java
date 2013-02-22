package com.optimism.systems;

import java.util.Random;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.World;
import com.artemis.utils.ImmutableBag;
import com.optimism.Factory;
import com.optimism.GameData;
import com.optimism.Settings;
import com.optimism.components.Health;
import com.optimism.components.Position;
import com.optimism.components.Vec;
import com.optimism.components.Velocity;


public class EnemySpawnSystem extends EntitySystem {

	private GameData data;
	private Random r = new Random();
	
	@SuppressWarnings("unchecked")
	public EnemySpawnSystem(GameData data) {
		super(Aspect.getAspectForAll(Health.class));
		this.data = data;
	}
	
	@Override
	protected boolean checkProcessing() { return true; }
	
	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		if (r.nextFloat() < Settings.spawnRate) {
			randomEnemy(world);
		}
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
