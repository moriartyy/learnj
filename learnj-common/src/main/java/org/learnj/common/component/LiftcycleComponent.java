package org.learnj.common.component;

import java.io.Closeable;

public interface LiftcycleComponent extends Closeable {
	
	void start();
	void stop();

}
