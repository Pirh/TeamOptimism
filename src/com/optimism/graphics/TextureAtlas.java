package com.optimism.graphics;


public class TextureAtlas {

	private static final double unit = 16.0/1024.0;
	
	public static final TexArea playerShip = new TexArea(0, 0, unit*4, unit*4);
	public static final TexArea playerBullet = new TexArea(unit*4, 0, unit, unit);
	public static final TexArea blackHole = new TexArea(0, unit*4, unit*8, unit*8);
	
}
