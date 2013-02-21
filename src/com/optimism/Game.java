package com.optimism;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.artemis.World;
import com.optimism.components.Img;
import com.optimism.input.Input;
import com.optimism.systems.MovementSystem;
import com.optimism.systems.PlayerControlSystem;
import com.optimism.systems.RenderSystem;


public class Game extends Canvas {
	
	private static Game game = new Game();
	
	private JFrame frame = new JFrame("Frame");
	
	private Input input = new Input();
	
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
		
		//Update input
		try {
			input.update(frame);
		} catch (Exception e) {}
		
		// The game has a World
		world = new World();
		
		// The world has some systems.
		world.setSystem(new PlayerControlSystem(input));
		world.setSystem(new MovementSystem());
		world.setSystem(new RenderSystem(g));
		
		// We initialise it after we make all the systems
		world.initialize();
		
		// We make the player
		Factory.makeShipCircle(world, 12, 250);
	}
	
	
	
	public void run() {
		
		while (true) {
			
			clear();
			
			//Delta calculation
			timeCurrent = System.nanoTime();
			delta = (float) ((timeCurrent - timeLast) / 1000000000);
			timeLast = timeCurrent;
			
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
	
	
	
}
