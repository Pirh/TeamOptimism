package com.optimism.systems;

import java.awt.Graphics;
import java.awt.Graphics2D;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.optimism.Projector;
import com.optimism.components.Img;
import com.optimism.components.Orientation;
import com.optimism.components.Position;
import com.optimism.components.Size;
import com.optimism.tools.Tuple2Int;


public class RenderSystem extends EntityProcessingSystem {
	
	private Graphics graphics;
	
	@Mapper ComponentMapper<Position> pm;
	@Mapper ComponentMapper<Orientation> om;
	@Mapper ComponentMapper<Img> imm;
	@Mapper ComponentMapper<Size> sm;
	
	
	@SuppressWarnings("unchecked")
	public RenderSystem(Graphics graphics) {
		super(Aspect.getAspectForAll(Position.class, Img.class, Size.class));
		this.graphics = graphics;
	}
	
	@Override
	public void process(Entity entity) {
		Graphics2D g = (Graphics2D) graphics.create();	// Make a graphics copy (apparently this is good practice??)
		
		Position pos = pm.get(entity);
		Size size = sm.get(entity);
		Img imComp = imm.get(entity);
		Tuple2Int screenPos = Projector.worldToScreen(pos);
		Tuple2Int screenSize = Projector.worldToScreen(size);
		
		int halfX = screenSize.getX()/2;
		int halfY = screenSize.getY()/2;
		
		// Rotate if the entity has an angle
		Orientation ori = om.getSafe(entity);
		double angle = (ori == null) ? 0:ori.angle;
		g.translate(screenPos.getX()-halfX, screenPos.getY()-halfY);
		g.rotate(angle, halfX, halfY);
		
		// Image, x, y, width, height, ImageObserver
		g.drawImage(imComp.sprite, 0, 0, screenSize.getX(), screenSize.getY(), null);
		
		g.dispose();	// Dispose of the copy.
	}
	
}
