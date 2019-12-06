package Atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest02 {

    public static void main(String[] args) {
         AtomicInteger count=new AtomicInteger(50);
//        ExecutorService es= Executors.newFixedThreadPool(20);
        for (int i = 0; i <4 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (count.get()>5)
                    {
                    System.out.println(Thread.currentThread().getName() + "实现一次减1：" + count.decrementAndGet());
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            }).start();
//            es.execute(t1);
        }

    }
}
