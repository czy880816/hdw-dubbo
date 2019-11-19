package com.hdw.thread.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Descripton com.hdw.thread.lock
 * @Author TuMinglong
 * @Date 2019/6/6 10:36
 */
public class BankSubCASRunnable implements Callable<Bank> {

    private AtomicInteger atomicInteger;

    private Bank bank;

    private Integer i;

    public BankSubCASRunnable(Bank bank, Integer i, AtomicInteger atomicInteger) {
        this.bank = bank;
        this.i = i;
        this.atomicInteger = atomicInteger;
    }

    @Override
    public Bank call() throws Exception {
        try {
            if (bank.getCount() >= i) {
                atomicInteger.addAndGet(-i);
                bank.setCount(atomicInteger.get());
                System.out.println("取钱 账号余额：" + bank.getCount());
            } else {
                System.out.println("不可取钱 账号余额：" + bank.getCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return bank;
    }
}
