package com.optimism.systems;

import java.util.Random;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.optimism.Factory;
import com.optimism.Settings;
import com.optimism.components.Health;


public class EnemySpawnSystem extends EntitySystem {

	private Random r = new Random();
	
	@SuppressWarnings("unchecked")
	public EnemySpawnSystem() {
		super(Aspect.getAspectForAll(Health.class));
	}
	
	@Override
	protected boolean checkProcessing() { return true; }
	
	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		if (r.nextFloat() < Settings.spawnRate) {
			Factory.randomEnemy(world);
		}
	}
	
}
