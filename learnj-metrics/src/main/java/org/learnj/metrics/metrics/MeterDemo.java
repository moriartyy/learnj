package org.learnj.metrics.metrics;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

public class MeterDemo {
	
    static final MetricRegistry metrics = new MetricRegistry();
    public static void main(String args[]) {
        startReport();
        //metrics:事件总数，平均速率,包含1分钟，5分钟，15分钟的速率
        Meter requests = metrics.meter("requests");
        //计数一次
        requests.mark();
        wait5Seconds();
    }
    
    static void startReport() {
        //注册metrics,每个1秒打印metrics到控制台
        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(1, TimeUnit.SECONDS);
    }

    static void wait5Seconds() {
        try {
            Thread.sleep(5*1000);
        }
        catch(InterruptedException e) {}
    }

}
