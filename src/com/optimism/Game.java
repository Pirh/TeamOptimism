package com.optimism;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
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

import com.artemis.World;
import com.optimism.components.Img;
import com.optimism.components.Position;
import com.optimism.components.Size;
import com.optimism.input.Input;
import com.optimism.input.Sorter;
import com.optimism.systems.CollisionSystem;
import com.optimism.systems.DebugBodySystem;
import com.optimism.systems.DebugInputSystem;
import com.optimism.systems.MovementSystem;
import com.optimism.systems.OrbitRenderSystem;
import com.optimism.systems.PlayerControlSystem;
import com.optimism.systems.PlayerFiringSystem;
import com.optimism.systems.RenderSystem;
import com.optimism.tools.Tool;


@SuppressWarnings("serial")
public class Game extends Canvas implements KeyListener, MouseListener, MouseMotionListener{
	
	private static Game game = new Game();
	
	private JFrame frame = new JFrame("Frame");
	
	private Input input = new Input();
	private Sorter sorter = new Sorter();
	
	private Graphics2D g;
	private BufferStrategy buffStrategy;
	
	private int frameWidth = 800;
	private int frameHeight = 600;
	private double timeCurrent = 0;
	private double timeLast = 0;
	private float delta = 0;
	
	private Img background = new Img("res/background.png");
	
	private World world;
	
	
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
		
		createBufferStrategy(2);
		buffStrategy = getBufferStrategy();
		
		Projector.initialize(frameWidth, frameHeight);
		
		g = (Graphics2D) buffStrategy.getDrawGraphics();
		
		//Centre frame.
		frame.setLocationRelativeTo(null);
		
		// The game has a World
		world = new World();
		
		// The world has some systems.
		world.setSystem(new PlayerControlSystem(input));
		world.setSystem(new PlayerFiringSystem(input));
		world.setSystem(new MovementSystem());
		world.setSystem(new CollisionSystem());
		world.setSystem(new OrbitRenderSystem(g));
		world.setSystem(new RenderSystem(g));
		world.setSystem(new DebugBodySystem(g, input));
		world.setSystem(new DebugInputSystem(g, input));
		
		// We initialise it after we make all the systems
		world.initialize();
		
		// We make the player
		Factory.makeShipCircle(world, 2, 250);
		Factory.makeShip(world, new Position(400,100), new Size(48,48), "res/player-ship.png", false);
		// And the orbit
		Factory.makeOrbitRing(world, new Position(Settings.circleCentre), Settings.circleRadius);

		//Tells frame to listen for all input events.
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
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
			} catch (Exception e) {
				Tool.print("INPUT CRASHED! Tell James.");
			}
			
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
