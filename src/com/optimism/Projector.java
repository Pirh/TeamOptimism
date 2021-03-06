package com.optimism;

import com.optimism.components.Vec;
import com.optimism.tools.Tuple2Int;


public class Projector {

	public static double gameWidth = 800;
	public static double gameHeight = 600;
	
	public static int screenWidth;
	public static int screenHeight;
	
	public static double widthRatio;
	public static double heightRatio;
	
	public static final Vec centre = new Vec(gameWidth/2, gameHeight/2);
	
	public static void initialize(int width, int height) {
		screenWidth = width;
		screenHeight = height;
		widthRatio = screenWidth/gameWidth;
		heightRatio = screenHeight/gameHeight;
	}
	
	// Takes a Position vector and returns screen coordinates for drawing.
	public static Tuple2Int worldToScreen(Vec pos) {
		return new Tuple2Int((int) (pos.x*widthRatio), (int) (pos.y*heightRatio));
	}
	
}
