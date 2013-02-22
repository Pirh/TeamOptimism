package com.optimism;

import java.awt.Color;
import java.awt.Graphics;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.World;
import com.optimism.components.Img;
import com.optimism.components.Position;
import com.optimism.components.Script;
import com.optimism.components.Size;
import com.optimism.components.Text;
import com.optimism.input.Input;
import com.optimism.systems.CollisionSystem;
import com.optimism.systems.DebugBodySystem;
import com.optimism.systems.DebugFrameSystem;
import com.optimism.systems.DebugInputSystem;
import com.optimism.systems.EnemySpawnSystem;
import com.optimism.systems.ExpirySystem;
import com.optimism.systems.MouseInputSystem;
import com.optimism.systems.MovementSystem;
import com.optimism.systems.OrbitRenderSystem;
import com.optimism.systems.PlayerControlSystem;
import com.optimism.systems.PlayerFiringSystem;
import com.optimism.systems.RenderSystem;
import com.optimism.systems.RenderTextSystem;
import com.optimism.systems.ScriptSystem;
import com.optimism.systems.UpgradeSystem;


public class Game {
	
	public World world;
	public EntitySystem[] renderSystems = new EntitySystem[3];
	public GameData data;
	public boolean paused = false;

	public Game(Input input, Graphics graphics) {
		
		// The game has a World
		world = new World();
		
		initialize();
		
		// The world has some systems.
		world.setSystem(new PlayerControlSystem(input));
		world.setSystem(new PlayerFiringSystem(input));
		world.setSystem(new MovementSystem(data));
		world.setSystem(new ScriptSystem());
		world.setSystem(new CollisionSystem(data));
		
		world.setSystem(new UpgradeSystem(data));
		world.setSystem(new EnemySpawnSystem(data));
		
		renderSystems[0] = new OrbitRenderSystem(graphics, data);
		renderSystems[1] = new RenderSystem(graphics);
		renderSystems[2] = new RenderTextSystem(graphics);
		
		world.setSystem(new ExpirySystem());
		
		world.setSystem(renderSystems[0], true);
		world.setSystem(renderSystems[1], true);
		world.setSystem(renderSystems[2], true);
		
		world.setSystem(new MouseInputSystem(input));

		world.setSystem(new DebugBodySystem(data, graphics, input));
		world.setSystem(new DebugInputSystem(graphics, input));
		world.setSystem(new DebugFrameSystem(graphics, input, 800));
		
		// We initialise it after we make all the systems
		world.initialize();
	}
	
	public void initialize() {
		
		//Menu.
		Entity e = world.createEntity();		//NOTE: this is drawn before enemies, please fix.
		e.addComponent(new Img("res/HUD.png"));
		e.addComponent(new Position(400,38));
		e.addComponent(new Size(803,80));
		e.addToWorld();
		
		Factory.makeBlackHole(world, 150);
		Entity[] ships = Factory.makeShipCircle(world, 2, 250);
		Factory.enemyBlueShip(world, new Position(400,400)).removeComponent(Script.class);
		Factory.makeOrbitRing(world, new Position(Settings.circleCentre), Settings.circleRadius);
		data = new GameData(ships);
		
		//Score.
		data.eScore = world.createEntity();
		data.eScore.addComponent(new Position(350,20));
		data.eScore.addComponent(new Text("Level: 1    Score: " + data.score, Color.yellow));
		data.eScore.addToWorld();
		
		//Planet health.
		data.eHealth = world.createEntity();
		data.eHealth.addComponent(new Position(355,40));
		data.eHealth.addComponent(new Text("Planet health: " + data.planetHealth, Color.green));
		data.eHealth.addToWorld();
		
	}
	
	public boolean over() {
		return (data.planetHealth <= 0 || data.allShipsDead);
	}
	
}
