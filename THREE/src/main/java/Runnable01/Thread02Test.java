package Runnable01;

import Atomic.TicketTest;
import Thread01.Thread01;

public class Thread02Test {
    public static void main(String[] args) {
//        Thread02 C=new Thread02("C");
//        Thread02 D=new Thread02("D");
//        new Thread(C).start();
//        new Thread(D).start();
//////        Thread02 B=A;//试试看重复运行线程会不会抛出异常
//        C.run();
//        D.run();
// (1)run()方法是顺序执行的 不是多线程 start才是多线程执行开始方法 0123401234
// 同时 上面这段将ru改成start以后也只能用于extends Thread类 ，implements Runnable接口按上面这样声明是没办法使用start方法的 得用下面这种方法声明线程

//        new Thread(new Thread02("C")).start();
//        new Thread(new Thread02("D")).start();//正确的Runnable接口声明线程方法 但它新建了两个进程互不相关  所以同样的步骤执行两遍
//        Thread02 A=new Thread02();

        //(2)可以理解成两个进程 互不相关的其实 0011223344

        Thread02 A=new Thread02("class Thread02");//its a runnable
        new Thread(A,"线程1").start();
        new Thread(A,"线程2").start();
        //(3)这才是一个进程中的两个线程 0123456789


    }
}
