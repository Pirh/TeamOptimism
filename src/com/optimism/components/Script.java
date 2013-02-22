package com.optimism.components;


import com.artemis.Component;
import com.optimism.scripts.ScriptAction;


public class Script extends Component {

	public ScriptAction script;
	
	public Script(ScriptAction script) {
		this.script = script;
	}
	
}
