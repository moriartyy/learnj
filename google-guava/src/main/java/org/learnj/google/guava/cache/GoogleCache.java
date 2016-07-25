package org.learnj.google.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author hongmiao.yu on 2016/7/14.
 */
public class GoogleCache {

    private static Cache<String, CacheItem> cache = CacheBuilder.newBuilder()
            .initialCapacity(10)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();


    public static void main(String[] args) throws IOException {

        System.out.println("Press any key to start...");
        next();

        cache.put("key1", new CacheItem());
        int i = 0;
        while (true) {
            cache.put("key" + i++, new CacheItem());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        System.out.println("Size: " + cache.size());
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Size after sleep: " + cache.size());
//
//        System.out.println("Press any key to gc...");
//        next();
//        System.gc();
//        System.out.println("Size: " + cache.size());
//
//        System.out.println("Press any key to gc again...");
//        next();
//        System.gc();
//        System.out.println("Size: " + cache.size());
//
//        System.out.println("Press any key to exit...");
//        next();
    }

    private static boolean next() throws IOException {
        while (System.in.read() != 'n') {

        }
        return true;
    }

}
