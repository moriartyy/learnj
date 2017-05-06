package org.learnj.concurrent;

public class HowToSafelyCloseThread {
	
	private volatile boolean stop;
	private Thread thread;
	
	public void start() {
		stop = false;
		thread = new Thread() {
			
			@Override
			public void run() {
				while (!stop) {
					long lastPrint = System.currentTimeMillis();
					System.out.println(lastPrint);
				}
			}
		};
		thread.start();
 	}
	
	public void stop() {
		stop = true;
	}
	
	public static void main(String[] args) {
		
		final HowToSafelyCloseThread t = new HowToSafelyCloseThread();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			
			@Override
			public void run() {
				t.stop();
			}
		});
		
		t.start();
	}

}
