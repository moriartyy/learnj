package org.learnj.spring.base.initialization;

/**
 * @author hongmiao.yu 2016/6/30
 */
public class InstanceInitialization {
    private  String p1 = "By def.";

    InstanceInitialization() {
        p1 = "By constructor.";
    }

    public static void main(String[] args) {
        System.out.println(new InstanceInitialization().p1);
    }
}
