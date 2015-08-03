package org.learnj.metrics.repoters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;
import com.codahale.metrics.graphite.GraphiteUDP;

public class GraphiteDemo {
	
    public static void main(String args[]) {
    	
        final MetricRegistry metrics = new MetricRegistry();
        final Meter requests = metrics.meter("hichao.coflex.agent.requestResponseTime");
    	
        GraphiteReporter reporter = GraphiteReporter.forRegistry(metrics)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build(new Graphite("192.168.1.26", 2003));
        reporter.start(5, TimeUnit.SECONDS);
        
        while (true) {
        	
            //计数一次
            requests.mark();
            System.out.println(requests.getCount());
            wait5Seconds();
        }
    }
    
    static void wait5Seconds() {
        try {
        	Random random = new Random(System.currentTimeMillis());
            Thread.sleep(random.nextInt(5000));
        }
        catch(InterruptedException e) {}
    }

}
