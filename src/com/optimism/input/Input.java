package com.optimism.input;

import java.util.HashSet;

import javax.swing.JFrame;

import com.optimism.tools.Tuple2Int;


public class Input{
	
	//Variables: Keyboard.
	public HashSet<Integer> setKeyDown = new HashSet<Integer>();
	public HashSet<Integer> setKeyDownInput = new HashSet<Integer>();
	public HashSet<Integer> setKeyDownLast = new HashSet<Integer>();
	public HashSet<Integer> setKeyHit = new HashSet<Integer>();
	public HashSet<Integer> setKeyHitTemp = new HashSet<Integer>();
	
	public HashSet<Tuple2Int> setKeyDownMod = new HashSet<Tuple2Int>();
	public HashSet<Tuple2Int> setKeyDownInputMod = new HashSet<Tuple2Int>();
	public HashSet<Tuple2Int> setKeyDownLastMod = new HashSet<Tuple2Int>();
	public HashSet<Tuple2Int> setKeyHitMod = new HashSet<Tuple2Int>();
	public HashSet<Tuple2Int> setKeyHitTempMod = new HashSet<Tuple2Int>();
	

	public HashSet<Integer> setKeyToggle = new HashSet<Integer>();
	public HashSet<Integer> setKeyToggleInput = new HashSet<Integer>();
	public HashSet<Integer> setKeyTogglable = new HashSet<Integer>();
	
	//Required by BulletInput to check if any key is modded by 1, 2 or 3.
	public HashSet<Tuple2Int> setKeyDownModOnly = new HashSet<Tuple2Int>();
	
	
	//Variables: Mouse.
	public HashSet<Integer> setMouseDown = new HashSet<Integer>();
	
	public boolean mouseMove = false;
	public boolean mouseEnter = false;
	public int mousePosX;
	public int mousePosY;
	public Tuple2Int mousePos;
	public String mousePosNeat;
	
	
	
	//Method: update. Called every frame to update sets of keys.
	public void update(JFrame frame){
		
		if(setKeyTogglable.isEmpty()){
			setKeyTogglable.add(27);
			setKeyTogglable.add(112);
			setKeyTogglable.add(113);
		}

		setKeyDown.clear();
		setKeyDown.addAll(setKeyDownInput);
		setKeyToggle.clear();
		setKeyToggle.addAll(setKeyToggleInput);
		setKeyHitTemp.clear();
		setKeyHitTemp.addAll(setKeyDownInput);

		setKeyHitTemp.removeAll(setKeyDownLast);
		setKeyHit.clear();
		setKeyHit.addAll(setKeyHitTemp);

		setKeyDownLast.clear();
		setKeyDownLast.addAll(setKeyDown);
		

		setKeyDownMod.clear();
		setKeyDownMod.addAll(setKeyDownInputMod);
		setKeyHitTempMod.clear();
		setKeyHitTempMod.addAll(setKeyDownInputMod);

		setKeyHitTempMod.removeAll(setKeyDownLastMod);
		setKeyHitMod.clear();
		setKeyHitMod.addAll(setKeyHitTempMod);
		
		setKeyDownLastMod.clear();
		setKeyDownLastMod.addAll(setKeyDownMod);
		
		
		//Remove all keys if frame looses focus.
		if (!(frame.isFocused())){

			setKeyDownInput.clear();
			setKeyDownLast.clear();
			setKeyDown.clear();
			setKeyHit.clear();
			setKeyHitTemp.clear();
			
			setKeyDownInputMod.clear();
			setKeyDownLastMod.clear();
			setKeyDownMod.clear();
			setKeyHitMod.clear();
			setKeyHitTempMod.clear();
			
		}
		
		
		
	}
	
	
	
	//Methods called to get data.
	public boolean isKeyDown(int key){
		if (setKeyDown.contains(key)){
			return true;
		}else{
			return false;
		}
	}

	public boolean isKeyDownMod(int key, int mod){
		if (setKeyDownMod.contains(new Tuple2Int(key, mod))){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isKeyHit(int key){
		if (setKeyHit.contains(key)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isKeyHitMod(int key, int mod){
		if (setKeyHitMod.contains(new Tuple2Int(key, mod))){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isKeyDownAny(){
		if (setKeyDown.isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean isKeyToggle(int key){
		if (setKeyToggle.contains(key)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isKeyTogglable(int key){
		if (setKeyTogglable.contains(key)){
			return true;
		}else{
			return false;
		}
	}
	
	public String getKeyDown(){
		return setKeyDown.toString();
	}
	
	public String getKeyDownMod(){
		return setKeyDownMod.toString();
	}

	public String getKeyHit(){
		return setKeyHit.toString();
	}

	public String getKeyHitMod(){
		return setKeyHitMod.toString();
	}
	
	public String getKeyToggle(){
		return setKeyToggle.toString();
	}
	
	
	public boolean isMouseEnter(){
		return mouseEnter;
	}
	
	public boolean isMouseDown(int button){
		return setMouseDown.contains(button);
	}
	
	public Tuple2Int getMousePos(){
		return mousePos = new Tuple2Int(mousePosX, mousePosY);
	}
	
	public String getMousePosNeat(){
		return new Tuple2Int(mousePosX, mousePosY).toString();
	}
	
	public String getMouseDown(){
		return setMouseDown.toString();
	}
	
	
	
	//Methods called by keyboard / mouse.
	public void keyDown(int key) {
		setKeyDownInput.add(key);
	}
	
	public void keyUp(int key) {
		setKeyDownInput.remove(key);
	}

	public void keyDownMod(Tuple2Int key) {
		setKeyDownInputMod.add(key);
	}
	
	public void keyUpMod(Tuple2Int key) {
		setKeyDownInputMod.remove(key);
	}
	
	public void keyToggle(int key) {
		if(setKeyToggleInput.contains(key)){
			setKeyToggleInput.remove(key);
		}else{
			setKeyToggleInput.add(key);
		}
	}
	
	
	public void mouseMove(Tuple2Int mouse){
		mouseMove = true;
		mousePosX = mouse.getX();
		mousePosY = mouse.getY();
	}
	
	public void mouseEnter(boolean mouse){
		mouseEnter = mouse;
	}
	
	public void mouseDown(int button){
		setMouseDown.add(button);
	}
	
	public void mouseUp(int button){
		setMouseDown.remove(button);
	}
	
	
	
}
