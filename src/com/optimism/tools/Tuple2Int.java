package com.optimism.tools;

public class Tuple2Int {

	private Integer valX;
	private Integer valY;
	
	
	public Tuple2Int(int x, int y) {
		
	  valX = x;
	  valY = y;
	  
	}
	
	
	public int getX() {
		return valX;
	}
	public int getY() {
		return valY;
	}
	
	public void modX(int x) {
		valX += x;
	}
	public void modY(int y) {
		valY += y;
	}
	
	public void setX(int x) {
		valX = x;
	}
	public void setY(int y) {
		valY = y;
	}
	
	
	@Override
	public String toString() {
		
		return "[" + valX + "," + valY + "]";
		
	}
	
	
	@Override
	public int hashCode() {
		
		return (valX.hashCode() ^ valY.hashCode());
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) return false;
		if (!(obj instanceof Tuple2Int)) return false;
		
		Tuple2Int tuple = (Tuple2Int) obj;
		return ( (valX == tuple.getX()) && (valY == tuple.getY()) );
	
	}

}
