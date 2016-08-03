package org.learnj.common.component;

import com.google.common.base.Throwables;

public abstract class AbstractLiftcycleComponent implements LiftcycleComponent {

	@Override
	public void start() {
		try {
			this.doStart();
		} catch (Exception e) {
			Throwables.propagate(e);
		}
	}

	@Override
	public void stop() {
		try {
			this.doStop();
		} catch (Exception e) {
			Throwables.propagate(e);
		}
	}
	
	
	@Override
	public void close() {
		try {
			this.doClose();
		} catch (Exception e) {
			Throwables.propagate(e);
		}
	}
	
	protected abstract void doStart() throws Exception;
	protected abstract void doStop() throws Exception;
	protected abstract void doClose() throws Exception;

}
