package com.hdw.thread.lock;

import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Descripton com.hdw.thread.lock
 * @Author TuMinglong
 * @Date 2019/6/6 10:36
 */
public class BankSubRunnable implements Callable<Bank> {

    private ReentrantLock reentrantLock;

    private Bank bank;

    private Integer i;

    public BankSubRunnable(Bank bank, Integer i, ReentrantLock reentrantLock) {
        this.bank = bank;
        this.i = i;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public Bank call() throws Exception {
        reentrantLock.lock();
        try {
            if (bank.getCount() >= i) {
                bank.setCount(bank.getCount() - i);
                System.out.println("取钱 账号余额：" + bank.getCount());
            } else {
                System.out.println("不可取钱 账号余额：" + bank.getCount());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        return bank;
    }
}
