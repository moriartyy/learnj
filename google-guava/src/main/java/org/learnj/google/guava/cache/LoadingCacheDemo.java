package org.learnj.google.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author hongmiao.yu on 2016/7/15.
 */
public class LoadingCacheDemo {

    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, Long> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MILLISECONDS)
                .build(new CacheLoader<String, Long>() {
                    @Override
                    public Long load(String key) throws Exception {
                        return System.currentTimeMillis();
                    }
                });

        for (int i=0; i<10; i++) {
            System.out.println(cache.get(String.valueOf(i)));
        }
    }



}
