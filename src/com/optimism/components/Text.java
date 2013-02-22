package com.optimism.components;

import com.artemis.Component;

public class Text extends Component {

	
	public String str;
	
	
	public Text(String str) {
		
		this.str = str;
		
	}
	
	
	public String getString(){
		
		return this.str;
		
	}
	
	
}
