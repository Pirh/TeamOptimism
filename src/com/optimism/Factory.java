package com.optimism;

import com.artemis.Entity;
import com.artemis.World;
import com.optimism.collision.Circle;
import com.optimism.components.Body;
import com.optimism.components.Controllable;
import com.optimism.components.Damage;
import com.optimism.components.Health;
import com.optimism.components.Img;
import com.optimism.components.OrbitRing;
import com.optimism.components.Orientation;
import com.optimism.components.Position;
import com.optimism.components.Size;
import com.optimism.components.Vec;
import com.optimism.components.Velocity;
import com.optimism.components.Weapon;


public class Factory {
	
	public static Entity playerShip(World world, Position pos) {
		return makeShip(world, pos, new Size(48,48), "res/player-ship.png", 10, 1, true);
	}
	public static Entity playerBullet(World world, Position pos, Velocity vel) {
		return makeBullet(world, pos, vel, "res/player-bullet.png", 6, 1, Body.Team.ALLY);
	}
	public static Entity enemyBlueShip(World world, Position pos) {
		return makeShip(world, pos, new Size(32,32), "res/enemy-blue.png", 12, 3, false);
	}
	
	
	
	public static Body simpleBody(double radius, Body.Team team) {
		Circle circle = new Circle(new Vec(0,0), radius);
		return new Body(team, circle);
	}
	
	/** Makes the centre ring */
	public static Entity makeOrbitRing(World world, Position pos, double radius) {
		Entity ring = world.createEntity();
		ring.addComponent(pos);
		ring.addComponent(new OrbitRing(radius));
		ring.addToWorld();
		return ring;
	}
	
	/** Makes the black hole */
	public static Entity makeBlackHole(World world, double radius) {
		Entity hole = world.createEntity();
		hole.addComponent(new Position(Settings.circleCentre));
		hole.addComponent(new Velocity(0,0));
		hole.addComponent(new Size(radius*2, radius*2));
		hole.addComponent(new Orientation(0, 0.1));
		hole.addComponent(new Img("res/wormhole.png"));
		hole.addToWorld();
		return hole;
	}

	/** Makes a ship. No it really honestly does. */
	public static Entity makeShip(World world, Position pos, Size size, String imageName, double radius, int health, boolean isPlayer) {
		Entity ship = world.createEntity();
		Img img = new Img(imageName);
		ship.addComponent(pos);
		ship.addComponent(size);
		ship.addComponent(new Velocity(0,0));
		ship.addComponent(img);
		Body.Team team = (isPlayer) ? Body.Team.ALLY : Body.Team.ENEMY;
		ship.addComponent(simpleBody(radius, team));
		ship.addComponent(new Health(health));
		ship.addComponent(new Weapon(Settings.firingRate));
		Orientation ori = new Orientation(0);
		ship.addComponent(ori);
		if (isPlayer) {
			ship.addComponent(Controllable.FLAG);
		}
		ship.addToWorld();
		return ship;		
	}
	
	/** Makes a circle of ships. */
	public static void makeShipCircle(World world, int n, double distance) {
		double angle = (2*Math.PI) / n;
		Vec stretch = new Vec(0,Settings.circleRadius);
		for (int i=0; i<n; i++) {
			Vec pos = new Vec(Settings.circleCentre).add(stretch);
			playerShip(world, new Position(pos));
			stretch.rotate(angle);
		}
	}
	
	/** Makes a bullet. */
	public static Entity makeBullet(World world, Position pos, Velocity vel, String image, double radius, int damage, Body.Team team) {
		Entity bullet = world.createEntity();
		bullet.addComponent(pos);
		bullet.addComponent(vel);
		bullet.addComponent(new Size(16,16));
		bullet.addComponent(new Img(image));
		bullet.addComponent(new Damage(damage));
		bullet.addComponent(new Orientation(vel.angle()));
		bullet.addComponent(simpleBody(radius, team));
		bullet.addToWorld();
		return bullet;
	}
	
}
