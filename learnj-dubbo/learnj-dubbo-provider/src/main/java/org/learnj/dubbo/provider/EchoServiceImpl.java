package org.learnj.dubbo.provider;

import org.learnj.dubbo.api.EchoService;

/**
 * Created by hongmiao.yu on 2015/12/4.
 */
public class EchoServiceImpl implements EchoService {

    @Override
    public String echo(String message) {
        return message;
    }
}
