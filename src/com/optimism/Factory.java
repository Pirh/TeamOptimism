package com.optimism;

import com.artemis.Entity;
import com.artemis.World;
import com.optimism.components.Img;
import com.optimism.components.Position;
import com.optimism.components.Velocity;


public class Factory {

	/** Makes a ship. No it really honestly does. */
	public static Entity makeShip(World world, Position pos, String imageName) {
		Entity ship = world.createEntity();
		ship.addComponent(pos);
		ship.addComponent(new Velocity(0,0));
		ship.addComponent(new Img(imageName));
		ship.addToWorld();
		return ship;		
	}
	
}
