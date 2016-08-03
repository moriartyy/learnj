package org.learnj.metrics.metrics;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

public class TimerDemo {
	
	static final MetricRegistry metrics = new MetricRegistry();
	private static Timer timer = metrics.timer(MetricRegistry.name(TimerDemo.class, "calculation-duration"));

	public static void main(String[] args) throws InterruptedException {
		// TODOAuto-generated method stub
		startReport();
		Random rn = new Random();
		while (true) {
			// 统计开始
			final Timer.Context context = timer.time();
			int sleepTime = rn.nextInt(2000);
			Thread.sleep(sleepTime);
			System.out.println("处理耗时:" + sleepTime);
			// 统计结束
			context.stop();
		}
	}

	static void startReport() {
		// 注册metrics,每个1秒打印metrics到控制台
		ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS).build();
		reporter.start(1, TimeUnit.SECONDS);
	}

}
