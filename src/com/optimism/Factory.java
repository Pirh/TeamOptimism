package com.optimism;

import com.artemis.Entity;
import com.artemis.World;
import com.optimism.collision.Circle;
import com.optimism.components.Body;
import com.optimism.components.Img;
import com.optimism.components.Position;
import com.optimism.components.Size;
import com.optimism.components.Vec;
import com.optimism.components.Velocity;


public class Factory {
	
	public static Body simpleBody(double radius) {
		Circle circle = new Circle(new Vec(0,0), radius);
		return new Body(circle);
	}

	/** Makes a ship. No it really honestly does. */
	public static Entity makeShip(World world, Position pos, Size size, String imageName) {
		Entity ship = world.createEntity();
		Img img = new Img(imageName);
		ship.addComponent(pos);
		ship.addComponent(size);
		ship.addComponent(new Velocity(0,0));
		ship.addComponent(img);
		ship.addComponent(simpleBody(img.sprite.getWidth()));
		ship.addToWorld();
		return ship;		
	}
	
	/** Makes a circle of ships. */
	public static void makeShipCircle(int n, double distance) {
		double angle = (2*Math.PI) / n;
		Vec stretch = new Vec(0,distance);
		for (int i=0; i<n; i++) {
			
		}
	}
	
}
