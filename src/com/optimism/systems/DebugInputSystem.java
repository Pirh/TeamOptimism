package com.optimism.systems;

import java.awt.Color;
import java.awt.Graphics;

import com.artemis.systems.VoidEntitySystem;
import com.optimism.input.Input;


public class DebugInputSystem extends VoidEntitySystem {
	
	
	
	private Graphics g;
	private Input input;



	public DebugInputSystem(Graphics g, Input input){
		
		this.g = g;
		this.input = input;
		
	}
	
	

	@Override
	protected void processSystem() {
		
		
		if(input.isKeyToggle(112)){
			
			g.setColor(Color.GREEN);
			g.drawString("Input", 8, (1 * 16));
			g.drawString("=====", 8, (2 * 16));
			g.drawString("Keys down: " + input.getKeyDown(), 8, (3 * 16));
			g.drawString("Keys down mod: " + input.getKeyDownMod(), 8, (4 * 16));
			g.drawString("Keys hit: " + input.getKeyHit(), 8, (5 * 16));
			g.drawString("Keys hit mod: " + input.getKeyHitMod(), 8, (6 * 16));
			g.drawString("Keys toggled: " + input.getKeyToggle(), 8, (7 * 16));
			g.drawString("Mouse pos: " + input.getMousePosNeat(), 8, (8 * 16));
			g.drawString("Mouse down: " + input.getMouseDown(), 8, (9 * 16));
			
		}
		
		
	}
	
	
	
}
