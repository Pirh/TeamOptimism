package com.optimism.systems;

import java.awt.Graphics2D;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.Projector;
import com.optimism.components.Img;
import com.optimism.components.Position;
import com.optimism.tools.Tuple2Int;


public class RenderSystem extends EntityProcessingSystem {
	
	private Graphics2D graphics;
	
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Img> imm;
	
	@SuppressWarnings("unchecked")
	public RenderSystem(Graphics2D graphics) {
		super(Aspect.getAspectForAll(Position.class, Img.class));
		this.graphics = graphics;
	}
	
	@Override
	public void process(Entity entity) {
		Position pos = pm.get(entity);
		Img imComp = imm.get(entity);
		Tuple2Int spos = Projector.positionToScreen(pos);
		graphics.drawImage(imComp.sprite, spos.getX(), spos.getY(), null);
	}
	
}
