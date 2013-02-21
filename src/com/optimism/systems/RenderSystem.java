package com.optimism.systems;

import java.awt.Graphics2D;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.Projector;
import com.optimism.components.Position;


public class RenderSystem extends EntityProcessingSystem {
	
	private Graphics2D graphics;
	
	@Mapper ComponentMapper<Position> pm;
	//@Mapper ComponentMapper<ImageComp> imm;
	
	public RenderSystem(Graphics2D graphics) {
		super(Aspect.getAspectForAll(Position.class));
		this.graphics = graphics;
	}
	
	@Override
	public void process(Entity entity) {
		Position pos = pm.get(entity);
		//ImageComp imComp = imm.get(entity);
		//Tuple2Int spos = Projector.positionToScreen(pos);
		//graphics.drawImage(imComp.image, spos.getX(), spos.getY(), null);
	}
	
}
