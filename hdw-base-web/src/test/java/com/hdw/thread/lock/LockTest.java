package com.hdw.thread.lock;

import java.sql.Time;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description com.hdw.lock
 * @Author TuMinglong
 * @Date 2019/2/26 17:35
 **/
public class LockTest {

    public static void main(String[] args) {
        /**
         * 非公平锁
         */
        ReentrantLock lock = new ReentrantLock();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Bank bank = new Bank();
        bank.setId(1);
        bank.setName("小明");
        bank.setCount(10000);
        for (int i = 0; i < 10; i++) {
            System.out.println("存钱次数：" + i);
            Future<Bank> result = pool.submit(new BankAddRunnable(bank, 100, lock));

        }
        for (int j = 0; j < 20; j++) {
            System.out.println("取现次数：" + j);
            Future<Bank> result2 = pool.submit(new BankSubRunnable(bank, 1000, lock));
        }
        pool.shutdown();


        /**
         * cas 乐观锁
         */
        ThreadPoolExecutor pool2 = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Bank bank2 = new Bank();
        bank2.setId(1);
        bank2.setName("小张");
        bank2.setCount(10000);
        AtomicInteger atomicInteger = new AtomicInteger(10000);
        for (int i = 0; i < 10; i++) {
            System.out.println("存钱次数：" + i);
            Future<Bank> result = pool2.submit(new BankAddCASRunnable(bank2, 100, atomicInteger));

        }

        for (int j = 0; j < 20; j++) {
            System.out.println("取现次数：" + j);
            Future<Bank> result2 = pool2.submit(new BankSubCASRunnable(bank2, 1000, atomicInteger));
        }
        pool2.shutdown();
    }
}
