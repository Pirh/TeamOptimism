package com.optimism.systems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.artemis.systems.VoidEntitySystem;
import com.optimism.input.Input;
import com.optimism.tools.Tool;


public class DebugFrameSystem extends VoidEntitySystem {
	
	

	private Graphics g;
	private Input input;
	private int frameWidth;
	private String sDelta = "";
	private String sFPS = "";
	private int limit = 0;


	public DebugFrameSystem(Graphics g, Input input, int frameWidth){
		
		this.g = g;
		this.input = input;
		this.frameWidth = frameWidth;
		
	}
	
	

	@Override
	protected void processSystem() {

		limit++;
		
		if(limit == 1) {

			sDelta = String.format("Delta: %.7f", world.delta);
			sFPS = String.format("FPS: %.2f", (1 / world.delta));
			
		}else if(limit >= (int)((1 / world.delta) / 4)){
			
			limit = 0;
			
		}
		
		
		if(input.isKeyToggle(113)) {
			
			g.setColor(Color.GREEN);
			g.setFont(new Font("courier", 0, 12));
			g.drawString("Frame", frameWidth - ("Frame".length() * 8), 1 * 16);
			g.drawString("====", frameWidth - ("====".length() * 8), 2 * 16);
			g.drawString(sDelta, frameWidth - (sDelta.length() * 7), 3 * 16);
			g.drawString(sFPS, frameWidth - (sFPS.length() * 7), 4 * 16);
			
		}
		
		
	}
	
	
	
}
