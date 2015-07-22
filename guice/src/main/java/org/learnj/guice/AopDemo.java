package org.learnj.guice;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;

public class AopDemo {
	
	public static void main(String[] args) {
		Tool tool = new SimpleTool();
		Injector injector = Guice.createInjector(new Module(){

			@Override
			public void configure(Binder binder) {
				binder.bindInterceptor(Matchers.any(), //
						Matchers.annotatedWith(Names.named("log")), //
						new LoggerMethodInterceptor());
				binder.bind(Tool.class).to(SimpleTool.class);
			}
			
		});
		
		Tool injectedTool = injector.getInstance(Tool.class);
		System.out.println(injectedTool == tool);
		injectedTool.run();
	}
}
