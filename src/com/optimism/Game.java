package com.optimism;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.artemis.Entity;
import com.artemis.World;
import com.optimism.components.Img;
import com.optimism.components.Position;
import com.optimism.components.Text;
import com.optimism.input.Input;
import com.optimism.input.Sorter;
import com.optimism.systems.CollisionSystem;
import com.optimism.systems.DebugBodySystem;
import com.optimism.systems.DebugFrameSystem;
import com.optimism.systems.DebugInputSystem;
import com.optimism.systems.EnemySpawnSystem;
import com.optimism.systems.MouseInputSystem;
import com.optimism.systems.MovementSystem;
import com.optimism.systems.OrbitRenderSystem;
import com.optimism.systems.PlayerControlSystem;
import com.optimism.systems.PlayerFiringSystem;
import com.optimism.systems.RenderSystem;
import com.optimism.systems.RenderTextSystem;
import com.optimism.systems.UpgradeSystem;


@SuppressWarnings("serial")
public class Game extends Canvas implements KeyListener, MouseListener, MouseMotionListener{
	
	private static Game game = new Game();
	
	private JFrame frame = new JFrame("Frame");
	
	private Input input = new Input();
	private Sorter sorter = new Sorter();
	
	private Graphics g;
	private BufferStrategy buffStrategy;
	
	private int frameWidth = 800;
	private int frameHeight = 600;
	private double timeCurrent = System.nanoTime();
	private double timeLast = System.nanoTime();
	private float delta = 0;
	
	private Img background = new Img("res/background.png");
	
	private World world;
	private GameData data;
	
	
	public static void main(String[] args) {
		
		game.run();

	}
	
	
	
	public Game() {
		
		
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(null);
		
		setBounds(0,0,800,600);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		requestFocus();
		
		createBufferStrategy(3);
		buffStrategy = getBufferStrategy();
		
		Projector.initialize(frameWidth, frameHeight);
		
		g = buffStrategy.getDrawGraphics();
		g.setFont(new Font("courier", 0, 12));
		g.drawString("Loading...", 400, 300);
		
		//Centre frame.
		frame.setLocationRelativeTo(null);
		
		
		// The game has a World
		world = new World();
		
		//Initialize
		initialize();
		data.scoreBanner = world.createEntity();
		data.scoreBanner.addComponent(new Position(400,20));
		data.scoreBanner.addComponent(new Text(""+data.score));
		data.scoreBanner.addToWorld();
		
		// The world has some systems.
		world.setSystem(new PlayerControlSystem(input));
		world.setSystem(new PlayerFiringSystem(input));
		world.setSystem(new MovementSystem());
		world.setSystem(new CollisionSystem(data));
		
		world.setSystem(new UpgradeSystem(data));
		world.setSystem(new EnemySpawnSystem(data));
		
		world.setSystem(new OrbitRenderSystem(g));
		world.setSystem(new RenderSystem(g));
		world.setSystem(new RenderTextSystem(g));
		
		world.setSystem(new MouseInputSystem(input));

		world.setSystem(new DebugBodySystem(data, g, input));
		world.setSystem(new DebugInputSystem(g, input));
		world.setSystem(new DebugFrameSystem(g, input, frameWidth));
		
		// We initialise it after we make all the systems
		world.initialize();

		//Tells frame to listen for all input events.
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		
	}
	
	
	public void initialize() {
		
		Factory.makeBlackHole(world, 150);
		Entity[] ships = Factory.makeShipCircle(world, 2, 250);
		Factory.enemyBlueShip(world, new Position(400,400));
		Factory.makeOrbitRing(world, new Position(Settings.circleCentre), Settings.circleRadius);
		data = new GameData(ships);
	}
	
	
	public void run() {
		
		while (true) {
			
			clear();
			
			//Delta calculation
			timeCurrent = System.nanoTime();
			delta = (float) ((timeCurrent - timeLast) / 1000000000);
			timeLast = timeCurrent;

			//Update input
			try {
				input.update(frame);
			} catch (Exception e) {}
			
			
			// Set the delta
			world.setDelta(delta);
			
			// Runs all the systems
			world.process();
			
			buffStrategy.show();
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {}
			
		}
		
	}
	
	
	
	public void clear(){
		
		g.drawImage(background.sprite, 0, 0, null);
		
	}
	
	
	
	//Methods called by KeyListener, MouseListener and MouseMotionListener.
	//Events are passed to corresponding method in FrameInput along with input.
	public void keyPressed(KeyEvent e) {
		sorter.keyPressed(e, input);
	}
	public void keyReleased(KeyEvent e) {
    	sorter.keyReleased(e, input);
	}
	
	public void keyTyped(KeyEvent e) {
		//Unused!
	}
	
	
	public void mouseMoved(MouseEvent e) {
		sorter.mouseMoved(e, input);
	}
	public void mouseDragged(MouseEvent e) {
		sorter.mouseDragged(e, input);
	}
	public void mousePressed(MouseEvent e) {
		sorter.mousePressed(e, input);
	}
	
	public void mouseReleased(MouseEvent e) {
		sorter.mouseReleased(e, input);
	}

	public void mouseClicked(MouseEvent e) {
		//Unused!
	}
	
	public void mouseEntered(MouseEvent e) {
		sorter.mouseEntered(e, input);
	}
	
	public void mouseExited(MouseEvent e) {
		sorter.mouseExited(e, input);
	}
	
	
	
}
