package com.hdw.thread.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Descripton com.hdw.thread.lock
 * @Author TuMinglong
 * @Date 2019/6/6 10:32
 */
public class BankAddCASRunnable implements Callable<Bank> {

    private AtomicInteger atomicInteger;

    private Bank bank;

    private int i;

    public BankAddCASRunnable(Bank bank, int i, AtomicInteger atomicInteger) {
        this.bank = bank;
        this.i = i;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public Bank call() throws Exception {
        try {
            atomicInteger.addAndGet(i);
            bank.setCount(atomicInteger.get());
            System.out.println("存钱 账号余额：" + bank.getCount());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return bank;
    }
}
