package com.optimism.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.optimism.tools.Tuple2Int;

public class Sorter {
	
	/*
	Methods called by KeyListener, MouseListener and MouseMotionListener from Frame.
	
	(Modifier keys are Shift, Ctrl and Alt)
	I'm sorry I couldn't make key press and key release any neater... It basically just adds all pressed keys to keyDown,
	 those without a modifier to keyDownMod(key, 0) and those with a modifier to: keyDownMod(key, 1) for Shift, 2 for Ctrl and 3 for Alt.
	 multiple modifier, such a Sift+Ctrl+A will not work, instead each is presses individually.
	So Sift+Ctrl+A is really Sift+A and Ctrl+A.
	
	Known errors:
	-TAB is not detected by key listener.
	--The key listener was designed to ignore keys and are used to change focus :'(
	--Looks to be unfixable without extending applet.
	*/
	
	
	
	//Key pressed.
	public void keyPressed(KeyEvent e, Input input) {
		
		//Assigns required information from e to a tuple key.
		Tuple2Int key = new Tuple2Int(e.getKeyCode(), e.getModifiers());
		//Passes pressed key to input.keyDown.
		input.keyDown(key.getX());
		//Used to add key as unmodded only if no modifiers are pressed.
		boolean modded = false;
		
		//If key wasn't already pressed and is togglable, key is passed to input.keyToggle.
		if( (!(input.isKeyDown(key.getX()))) && (input.isKeyTogglable(key.getX())) ){
			input.keyToggle(key.getX());
		}
		
		//This basically manages any events involving modifier keys (now simpler and works better!).
		if(input.isKeyDown(16)){
			if( !( (key.getX() == 16) || (key.getX() == 17) || (key.getX() == 18) ) ){
				modded = true;
				input.keyDownMod(new Tuple2Int(key.getX(), 1));
				input.setKeyDownInputMod.remove(new Tuple2Int(16,0));
				input.setKeyDownModOnly.add( new Tuple2Int(key.getX(),1) );
			}
		}
		
		if(input.isKeyDown(17)){
	        if( !( (key.getX() == 16) || (key.getX() == 17) || (key.getX() == 18) ) ){
				modded = true;
	           	input.keyDownMod(new Tuple2Int(key.getX(), 2));
	        	input.setKeyDownInputMod.remove(new Tuple2Int(17,0));
				input.setKeyDownModOnly.add( new Tuple2Int(key.getX(),2) );
	        }
		}
		
		if(input.isKeyDown(18)){
			if( !( (key.getX() == 16) || (key.getX() == 17) || (key.getX() == 18) ) ){
				modded = true;
	          	input.keyDownMod(new Tuple2Int(key.getX(), 3));
	        	input.setKeyDownInputMod.remove(new Tuple2Int(18,0));
				input.setKeyDownModOnly.add( new Tuple2Int(key.getX(),3) );
	        }
		}
		
		//This part of the if is simple, if a key was pressed with no modifier,
		//it passes the key to input.keyDownMod with no modifier and removes any modified versions of that key.
		if(modded == false){
			input.keyDownMod(new Tuple2Int(key.getX(), 0));
			input.setKeyDownInputMod.remove(new Tuple2Int(key.getX(),1));
			input.setKeyDownInputMod.remove(new Tuple2Int(key.getX(),2));
			input.setKeyDownInputMod.remove(new Tuple2Int(key.getX(),3));
			input.setKeyDownModOnly.remove( new Tuple2Int(key.getX(),1) );
			input.setKeyDownModOnly.remove( new Tuple2Int(key.getX(),2) );
			input.setKeyDownModOnly.remove( new Tuple2Int(key.getX(),3) );
		}
		
	}
	
	
	
	//Key released.
	public void keyReleased(KeyEvent e, Input input) {
		
		//Assigns required information from e to a tuple key.
		Tuple2Int key = new Tuple2Int(e.getKeyCode(), e.getModifiers());
		//Passes key to input.keyUp and input.keyUpMod with all possible modifiers.
		input.keyUp(key.getX());
		input.keyUpMod(new Tuple2Int(key.getX(), 0));
		input.keyUpMod(new Tuple2Int(key.getX(), 1));
		input.keyUpMod(new Tuple2Int(key.getX(), 2));
		input.keyUpMod(new Tuple2Int(key.getX(), 3));
		input.setKeyDownModOnly.remove( new Tuple2Int(key.getX(),1) );
		input.setKeyDownModOnly.remove( new Tuple2Int(key.getX(),2) );
		input.setKeyDownModOnly.remove( new Tuple2Int(key.getX(),3) );
		
		//This bit works perfectly!
	    if(input.isKeyDown(16) && !( (key.getX() == 16) || (key.getX() == 17) || (key.getX() == 18) ) && input.setKeyDownModOnly.isEmpty() ){
	    	input.keyDownMod(new Tuple2Int(16, 0));
	    }
	    if(input.isKeyDown(17) && !( (key.getX() == 16) || (key.getX() == 17) || (key.getX() == 18) ) && input.setKeyDownModOnly.isEmpty() ){
	    	input.keyDownMod(new Tuple2Int(17, 0));
	    }
	    if(input.isKeyDown(18) && !( (key.getX() == 16) || (key.getX() == 17) || (key.getX() == 18) ) && input.setKeyDownModOnly.isEmpty() ){
	    	input.keyDownMod(new Tuple2Int(18, 0));
	    }
	    
	    //If modifier key is lifed while modded key is down, this fixes it! :D (Ryan helped).
	    ArrayList<Tuple2Int> arrayKeyDownModOnly = new ArrayList<Tuple2Int>(input.setKeyDownModOnly.toArray().length);
	    for (Tuple2Int e2 : input.setKeyDownModOnly){
	    	arrayKeyDownModOnly.add(e2);
	    }
	    
	    if( (key.getX() == 16) && !(input.setKeyDownModOnly.isEmpty()) ){
			for(int i = 0; i < arrayKeyDownModOnly.size(); i++){
				if(arrayKeyDownModOnly.get(i).getY() == 1){
					input.keyDownMod( new Tuple2Int(arrayKeyDownModOnly.get(i).getX(), 0) );
					input.keyUpMod(arrayKeyDownModOnly.get(i));
				}
			}
	    }
	    if( (key.getX() == 17) && !(input.setKeyDownModOnly.isEmpty()) ){
			for(int i = 0; i < arrayKeyDownModOnly.size(); i++){
				if(arrayKeyDownModOnly.get(i).getY() == 2){
					input.keyDownMod( new Tuple2Int(arrayKeyDownModOnly.get(i).getX(), 0) );
					input.keyUpMod(arrayKeyDownModOnly.get(i));
				}
			}
	    }
	    if( (key.getX() == 18) && !(input.setKeyDownModOnly.isEmpty()) ){
			for(int i = 0; i < arrayKeyDownModOnly.size(); i++){
				if(arrayKeyDownModOnly.get(i).getY() == 3){
					input.keyDownMod( new Tuple2Int(arrayKeyDownModOnly.get(i).getX(), 0) );
					input.keyUpMod(arrayKeyDownModOnly.get(i));
				}
			}
	    }
	    
	}
	
	
	
	//Mouse move.
	public void mouseMoved(MouseEvent e, Input input) {			//Used when no click.
		input.mouseMove(new Tuple2Int(e.getX(), e.getY()));
	}
	
	public void mouseDragged(MouseEvent e, Input input) {		//Used when clicked.
		input.mouseMove(new Tuple2Int(e.getX(), e.getY()));
	}
	
	
	///mouse button clicked.
	public void mousePressed(MouseEvent e, Input input) {
		input.mouseDown(e.getButton());
	}
	
	//Mouse button released.
	public void mouseReleased(MouseEvent e, Input input) {
		input.mouseUp(e.getButton());
	}
	
	
	//Mouse entered frame.
	public void mouseEntered(MouseEvent e, Input input) {
		input.mouseEnter(true);
	}
	
	//Mouse left frame.
	public void mouseExited(MouseEvent e, Input input) {
		input.mouseEnter(false);
	}
	
	
	
}
