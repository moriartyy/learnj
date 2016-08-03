package org.learnj.dubbo.consumer;

import org.learnj.dubbo.api.CallbackListener;
import org.learnj.dubbo.api.CallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hongmiao.yu on 2015/12/1.
 */
public class Bootstrap {

    public static void main(String[] args) throws IOException {

        final Logger logger = LoggerFactory.getLogger(Bootstrap.class);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        CallbackListener listener = new CallbackListener() {
            @Override
            public void onCallback(String key) {
                logger.info("handle {}", key);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationConsumer.xml");
        context.start();
        CallbackService callbackService = (CallbackService) context.getBean("callbackService");
        logger.info("sending call1");
        callbackService.call("call1", listener);
//        logger.info("sending call1");
//        callbackService.doAnything("call2", listener);
        System.in.read();
    }
}
