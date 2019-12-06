package MaTeacher;

import java.util.concurrent.*;

public class Future01 {
    public static void main(String[] args) throws InterruptedException,ExecutionException{

        FutureTask<Integer> task=new FutureTask<Integer>(()->{
            TimeUnit.SECONDS.sleep(1);
            return 1000;
        });
        new Thread(task).start();

        System.out.println(task);
        System.out.println(task.isDone());
        System.out.println(task.get());
        System.out.println(task.isDone());

        ExecutorService ser=Executors.newFixedThreadPool(5);
        Future<Integer> f=ser.submit(()->{
            TimeUnit.SECONDS.sleep(1);
            return 1;
        });

        System.out.println(ser);
        ser.shutdown();

        System.out.println(f.isDone());
        System.out.println(f.get());
        System.out.println(f.isDone());
        System.out.println(ser);

        TimeUnit.SECONDS.sleep(1);
        System.out.println(ser);

    }
}
//这里两种线程执行方式 一种使用实现callable接口的thread执行 另一种使用线程池来执行