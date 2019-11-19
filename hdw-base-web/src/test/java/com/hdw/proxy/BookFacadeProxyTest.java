package com.hdw.proxy;

/**
 * @Descripton com.hdw.proxy
 * @Author TuMinglong
 * @Date 2019/4/2 11:24
 */
public class BookFacadeProxyTest {

    public static void main(String[] args) {
        /**
         * JDK动态代理所用到的代理类在程序调用到代理类对象时才由JVM真正创建，JVM根据传进来的 业务实现类对象 以及 方法名 ，
         * 动态地创建了一个代理类的class文件并被字节码引擎执行，然后通过该代理类对象进行方法调用。我们需要做的，只需指定代理类的预处理、调用后操作即可。
         *
         * 在使用时，首先创建一个业务实现类对象和一个代理类对象，然后定义接口引用（这里使用向上转型）并用代理对象.
         * bind(业务实现类对象)的返回值进行赋值。最后通过接口引用调用业务方法即可。
         * 接口引用真正指向的是一个绑定了业务类的代理类对象，所以通过接口方法名调用的是被代理的方法们
         */
        BookFacadeImpl bookFacadeImpl = new BookFacadeImpl();
        BookFacadeProxy proxy = new BookFacadeProxy();
        BookFacade bookFacade = (BookFacade) proxy.bind(bookFacadeImpl);
        bookFacade.addBook();

        /**
         * cglib是针对类来实现代理的，原理是对指定的业务类生成一个子类，并覆盖其中业务方法实现代理。因为采用的是继承，所以不能对final修饰的类进行代理。
         * 创建业务类和代理类对象，然后通过  代理类对象.getInstance(业务类对象)  返回一个动态代理类对象（它是业务类的子类，可以用业务类引用指向它）。最后通过动态代理类对象进行方法调用。
         */
        BookFacadeImpl2 bookFacade2 = new BookFacadeImpl2();
        BookFacadeCglib cglib = new BookFacadeCglib();
        BookFacadeImpl2 bookCglib = (BookFacadeImpl2) cglib.getInstance(bookFacade2);
        bookCglib.addBook();


        BookFacadeCglib cglib2 = new BookFacadeCglib();
        BookFacadeImpl2 bookCglib2 = (BookFacadeImpl2) cglib.getProxy(BookFacadeImpl2.class);
        bookCglib2.addBook();

    }
}
