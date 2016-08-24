package org.learnj.concurrent.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Loster on 2016/8/12.
 */
public class JExecutorService {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

    class MyThread extends Thread {



    }
}
