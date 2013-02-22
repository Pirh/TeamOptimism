package com.optimism.components;

import java.awt.Color;

import com.artemis.Component;

public class Text extends Component {

	
	public String str;
	public Color color;
	
	
	public Text(String str, Color color) {
		
		this.str = str;
		this.color = color;
		
	}
	
	
	public String getString(){
		
		return this.str;
		
	}
	
	
}
