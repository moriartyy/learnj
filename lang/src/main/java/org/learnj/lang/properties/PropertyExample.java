package org.learnj.lang.properties;

import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

/**
 * @author hongmiao.yu on 2016/7/28.
 */
public class PropertyExample {

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();

        System.out.println((Boolean)map.get("dd"));
    }
}
