package org.learnj.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Loster on 2016/8/25.
 */
public class WhatHappeningWhileAcquiringLock {

    private static ReentrantLock lock = new ReentrantLock();
    private static volatile boolean isOwnerThreadAcquiredLock = false;

    public static void main(String[] args) {

        LockOwnerThread ownerThread = new LockOwnerThread();
        ownerThread.start();

        while (!isOwnerThreadAcquiredLock) {
            //wait for owner thread to acquire lock.
            try {
                Thread.sleep(1);
            } catch (InterruptedException ignored) {
            }
        }

        lock.lock(); // We can not acquire the lock, so unsafe.park() is called, and the thread is disabled for thread scheduling purposes.

        synchronized (WhatHappeningWhileAcquiringLock.class) {
            try {
                WhatHappeningWhileAcquiringLock.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static private class LockOwnerThread extends Thread {

        @Override
        public void run() {
            lock.lock();
            try {
                isOwnerThreadAcquiredLock = true;
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException ignored) {
            } finally {
                lock.unlock();
            }
        }
    }
}
