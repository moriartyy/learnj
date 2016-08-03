package org.learnj.concurrent;

public class SafelyCloseThread {
	
	private volatile boolean stop;
	private Thread thread;
	
	public void start() {
		stop = false;
		thread = new Thread() {
			
			@Override
			public void run() {
				while (true && !stop) {
					long lastPrint = System.currentTimeMillis();
					System.out.println(lastPrint);
					while ((System.currentTimeMillis() - lastPrint) < 1000) {}
				}
			}
		};
		thread.start();
 	}
	
	public void stop() {
		stop = true;
	}
	
	public static void main(String[] args) {
		
		final SafelyCloseThread t = new SafelyCloseThread();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			
			@Override
			public void run() {
				t.stop();
			}
		});
		
		t.start();
	}

}
