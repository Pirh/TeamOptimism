package com.optimism.scripts;

import com.artemis.Entity;
import com.artemis.World;
import com.optimism.tools.Tool;


public class TestScript extends ScriptAction {
	
	@Override
	public void initialize(World world, Entity self) {
		Tool.print("Script initialized.");
	}
	
	@Override
	public void run(World world, Entity self) {
		Tool.print("Script running...");
	}

}
