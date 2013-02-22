package com.optimism.components;

import com.artemis.Component;


public class Clickable extends Component {

	public Boolean clicked = false;
	

	public void makeClicked(Boolean bool){
	
		this.clicked = bool;
	
	}

	public boolean isClicked(){
	
		return this.clicked;
	
	}

}