package com.optimism;

import com.artemis.Entity;
import com.artemis.World;
import com.optimism.collision.Circle;
import com.optimism.components.Body;
import com.optimism.components.Controllable;
import com.optimism.components.Img;
import com.optimism.components.OrbitRing;
import com.optimism.components.Position;
import com.optimism.components.Size;
import com.optimism.components.Vec;
import com.optimism.components.Velocity;


public class Factory {
	
	public static Body simpleBody(double radius) {
		Circle circle = new Circle(new Vec(0,0), radius);
		return new Body(circle);
	}
	
	/** Makes the centre ring */
	public static Entity makeOrbitRing(World world, Position pos, double radius) {
		Entity ring = world.createEntity();
		ring.addComponent(pos);
		ring.addComponent(new OrbitRing(radius));
		ring.addToWorld();
		return ring;
	}

	/** Makes a ship. No it really honestly does. */
	public static Entity makeShip(World world, Position pos, Size size, String imageName, boolean isPlayer) {
		Entity ship = world.createEntity();
		Img img = new Img(imageName);
		ship.addComponent(pos);
		ship.addComponent(size);
		ship.addComponent(new Velocity(0,0));
		ship.addComponent(img);
		ship.addComponent(simpleBody(size.x/2));
		if (isPlayer) {
			ship.addComponent(Controllable.FLAG);
		}
		ship.addToWorld();
		return ship;		
	}
	
	/** Makes a circle of ships. */
	public static void makeShipCircle(World world, int n, double distance) {
		double angle = (2*Math.PI) / n;
		Vec stretch = new Vec(0,Arena.circleRadius);
		for (int i=0; i<n; i++) {
			Vec pos = new Vec(Arena.circleCentre).add(stretch);
			makeShip(world, new Position(pos), new Size(48,48), "res/player-ship.png", true);
			stretch.rotate(angle);
		}
	}
	
}
