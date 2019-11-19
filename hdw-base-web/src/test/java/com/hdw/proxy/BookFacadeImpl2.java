package com.hdw.proxy;

/**
 * @Descripton 首先定义业务类，无需实现接口（当然，实现接口也可以，不影响的）
 * @Author TuMinglong
 * @Date 2019/4/2 11:40
 */
public class BookFacadeImpl2 implements BookFacade {
    @Override
    public void addBook() {
        System.out.println("新增图书。。。");
    }
}
