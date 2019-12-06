package MyThreadPoolCollections;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorRunnable {
    public static void main(String[] args) {
        ThreadPoolExecutor executor =
                new ThreadPoolExecutor(1, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3));

        AThread A=new AThread();//new一个实现runnable接口的类的线程
        executor.execute(A);//执行这个线程run方法里边的任务

        executor.execute(new AThread());//或者直接这样new 上面的有点傻 这里名字有意义吗 没有
        executor.execute(new AThread());
        executor.execute(new AThread());
        executor.execute(new AThread());
        executor.execute(new AThread());//你会发现给这些线程取名字没意义 线程池根本不鸟你这线程叫什么 它自己有线程
                                        //你其实只是提供里边runnable类型的的任务罢了 它只care你有几个run里边的任务
                                        //1(核心线程数)+3(队列里可以放的数)+2(最大线程-核心线程=最多还能加几个新线程)=6 最多了
        executor.shutdown();
    }
}
class AThread implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
