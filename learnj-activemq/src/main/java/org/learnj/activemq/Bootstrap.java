package org.learnj.activemq;

import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author hongmiao.yu on 2016/8/3.
 */
public class Bootstrap {

    static CountDownLatch keepaliveLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        final Broker broker = new Broker();
        broker.start();

        final Producer producer = new Producer();
        producer.start();
        producer.send("hello");


        final Consumer consumer = new Consumer();
        consumer.start();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    keepaliveLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    producer.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    broker.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));

    }
}
