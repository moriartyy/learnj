package org.learnj.lang.volatilevariable;

/**
 * This can not demonstrate how <code>volatile</code> works.
 *
 * @author hongmiao.yu on 2016/7/27.
 */
class VolatileExample {
    int x = 0;
    volatile int b = 0;

    private void write() {
        x = 5;
        b = 1; // When b is set to 1, then x's new value 5 is visible to other threads.
    }

    private void read() {
        while (b != 1) { // System while read b from main memory not cache, when value 1 is read, then x will be 5.
        }
        int X = x;   // We should always read b first, use b as some kind of switch.
        System.out.println(X);
    }

    public static void main(String[] args) throws Exception {
        final VolatileExample example = new VolatileExample();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                example.write();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                example.read();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}
