package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.optimism.GameData;
import com.optimism.collision.Col;
import com.optimism.components.Body;
import com.optimism.components.Body.Team;
import com.optimism.components.Damage;
import com.optimism.components.Health;
import com.optimism.components.Position;
import com.optimism.components.Score;


public class CollisionSystem extends EntitySystem {

	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Body> bm;
	@Mapper ComponentMapper<Health> hm;
	@Mapper ComponentMapper<Damage> dm;
	@Mapper ComponentMapper<Score> sm;
	
	private GameData data;
	
	@SuppressWarnings("unchecked")
	public CollisionSystem(GameData data) {
		
		super(Aspect.getAspectForAll(Position.class, Body.class));
		this.data = data;
	}
	
	
	@Override
	// This just means the system is always being run.
	protected boolean checkProcessing() {
		
		return true;
		
	}
	
	
	@Override
	protected void processEntities(ImmutableBag<Entity> entities) {
		
		for(int i1 = 0; i1 < entities.size(); i1++){
			
			for(int i2 = i1 + 1; i2 < entities.size(); i2++){

				boolean collided = Col.detect(
						entities.get(i1).getComponent(Body.class),
						entities.get(i1).getComponent(Position.class),
						entities.get(i2).getComponent(Body.class),
						entities.get(i2).getComponent(Position.class)
						);
				
				if(collided){
					collide(entities.get(i1), entities.get(i2), true);
				}
				
			}
			
		}
		
	}
	
	
	
	protected void collide(Entity e1, Entity e2, boolean recurse) {

		Health health = hm.getSafe(e1);
		
		if (health != null) {
			
			Damage damage = dm.getSafe(e2);
			if (damage != null) {
				
				Team team1 = bm.get(e1).team;
				Team team2 = bm.get(e2).team;
				if (team1 != team2) {
				
					health.lose(damage.damage);
					e2.deleteFromWorld();
					
					if (health.dead()) {
						Score score = sm.getSafe(e1);
						if (score != null) {
							data.gainScore(score.amount);
							score.amount = 0;
						}
						e1.deleteFromWorld();
					}
				}
				
				return;
			}
			
		}
		if (recurse) {
			collide(e2, e1, false);
		}
	}
	
	
	
}
