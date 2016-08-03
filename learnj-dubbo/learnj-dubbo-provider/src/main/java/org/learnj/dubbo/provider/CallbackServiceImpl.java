package org.learnj.dubbo.provider;

import org.learnj.dubbo.api.CallbackListener;
import org.learnj.dubbo.api.CallbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by hongmiao.yu on 2015/12/1.
 */
public class CallbackServiceImpl implements CallbackService {

    private static final Logger logger = LoggerFactory.getLogger(CallbackServiceImpl.class);
    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    public void call(String key, CallbackListener listener) {
        logger.info("received {}", key);
        long start = System.currentTimeMillis();
        List<Future<Long>> results = new ArrayList<>();
        for (int i=0; i<2; i++) {
            results.add(executeCallBack(key, listener));
        }

        for (Future<Long> f : results) {
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        logger.info("Total cost: {}", System.currentTimeMillis() - start);
    }

    private Future<Long> executeCallBack(final String key, final CallbackListener listener) {
        return executor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                long start = System.currentTimeMillis();
                listener.onCallback(key);
                logger.info("key:{}, callback cost: {}", key, System.currentTimeMillis() - start);
                return System.currentTimeMillis() - start;
            }
        });
    }
}
