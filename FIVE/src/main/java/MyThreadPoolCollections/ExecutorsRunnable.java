package MyThreadPoolCollections;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsRunnable {
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(5);
        for (int i = 0; i <6 ; i++) {
            service.execute(()->{
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(Thread.currentThread().getName());
        System.out.println(service);

        service.shutdown();
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
    }
}
//好 经检验是这样的 当线程在main函数中时 main函数会一马当先先执行 即使你这个执行器确实写在我前面
//当然 我让main睡几秒 其他线程会趁机执行完 但当我醒来你还没执行完 那不好意思了 一般情况下其他线程抢不过我main
//其他的自己看执行结果 一目了然

// runnable executors(submit/execute) 无返回值 executorservice