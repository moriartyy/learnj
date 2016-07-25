package org.learnj.guice;

import com.google.inject.name.Named;

public class SimpleTool extends Tool {

//	@Inject
//	@Named("tool.name")
//	private String name;
	
	@Override
	@Named("log")
	public void run() {
		System.out.println("Hello, I'm simple tool.");
	}

}
