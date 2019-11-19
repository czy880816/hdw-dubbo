package com.hdw.thread.lock;

import io.swagger.models.auth.In;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Descripton com.hdw.thread.lock
 * @Author TuMinglong
 * @Date 2019/6/6 10:32
 */
public class BankAddRunnable implements Callable<Bank> {

    private ReentrantLock reentrantLock;

    private Bank bank;

    private int i;

    public BankAddRunnable(Bank bank, int i, ReentrantLock reentrantLock) {
        this.bank = bank;
        this.i = i;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public Bank call() throws Exception {
        reentrantLock.lock();
        try {
            bank.setCount(bank.getCount() + i);
            System.out.println("存钱 账号余额：" + bank.getCount());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        return bank;
    }
}
