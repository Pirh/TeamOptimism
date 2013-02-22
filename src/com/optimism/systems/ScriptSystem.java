package com.optimism.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.components.Script;


public class ScriptSystem extends EntityProcessingSystem {
	
	@Mapper ComponentMapper<Script> sm;
	
	@SuppressWarnings("unchecked")
	public ScriptSystem() {
		super(Aspect.getAspectForAll(Script.class));
	}

	@Override
	protected void process(Entity entity) {
		Script script = sm.get(entity);
		if (!script.script.initialized) {
			script.script.initialize(world, entity);
			script.script.initialized = true;
		}
		script.script.run(world, entity);
	}

}
