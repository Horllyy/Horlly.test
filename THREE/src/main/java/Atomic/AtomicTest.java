package Atomic;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    private static final AtomicInteger at=new AtomicInteger();
    private static final ExecutorService es= Executors.newFixedThreadPool(20);
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        long start=System.currentTimeMillis();
        for(int i=0;i<1000;i++)
        {
            Thread t1=new Thread() {
                public void run()
                {
                    System.out.println(Thread.currentThread().getName()+"实现了一次自增原子操作，结果为："+at.incrementAndGet());
                }
            };
            es.execute(t1);
        }
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("计算过程的耗时为："+(System.currentTimeMillis()-start-5000));
        System.out.println("累加1000次，得到结果"+at);


    }

}