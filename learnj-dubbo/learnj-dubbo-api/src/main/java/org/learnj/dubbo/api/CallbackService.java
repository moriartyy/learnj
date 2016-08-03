package org.learnj.dubbo.api;

/**
 * Created by hongmiao.yu on 2015/12/1.
 */
public interface CallbackService {

    void call(String key, CallbackListener listener);

}
