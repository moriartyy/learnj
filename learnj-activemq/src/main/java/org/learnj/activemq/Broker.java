package org.learnj.activemq;

import org.apache.activemq.broker.BrokerService;

/**
 * @author hongmiao.yu on 2016/8/3.
 */
public class Broker {

    private BrokerService broker;

    public void start() throws Exception {
        broker = new BrokerService();
        broker.setBrokerName("localhost");
        broker.setUseJmx(false);
        broker.start();
    }

    public void stop() throws Exception {
        broker.stop();
    }
}
