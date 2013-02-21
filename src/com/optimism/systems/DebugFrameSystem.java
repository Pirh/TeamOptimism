package com.optimism.systems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.artemis.systems.VoidEntitySystem;
import com.optimism.input.Input;


public class DebugFrameSystem extends VoidEntitySystem {
	
	

	private Graphics g;
	private Input input;
	private int frameWidth;



	public DebugFrameSystem(Graphics g, Input input, int frameWidth){
		
		this.g = g;
		this.input = input;
		this.frameWidth = frameWidth;
		
	}
	
	

	@Override
	protected void processSystem() {
		
		
		if(input.isKeyToggle(113)){
			
			//Font font = new Font("Serif", Font.BOLD, 8);
			//g.setFont(font);
			g.setColor(Color.GREEN);
			//g.drawString("Frame", frameWidth - ("Frame".length() * 8), 1 * 16);
			//g.drawString("====", frameWidth - ("====".length() * 8), 2 * 16);
			//g.drawString("Delta: ", frameWidth - ("Delta:".length() * 8), 3 * 16);
			//g.drawString("FPS: ", frameWidth - ("FPS: ".length() * 8), 4 * 16);
			g.drawString("Frame", 8, (-1 * 16 + 600));
			g.drawString("====", 8, (-2 * 16 + 600));
			g.drawString("Delta: " + world.delta, 8, (-3 * 16 + 600));
			g.drawString("FPS: " + (1 / world.delta), 8, (-4 * 16 + 600));
			
		}
		
		
	}
	
	
	
}
