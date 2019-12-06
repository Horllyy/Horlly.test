package MyThreadPoolCollections;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThreadCallableFuture implements Callable{
    public int randomNum(){
        int num=(int)Math.random()*1000;
        return num;
    }

    @Override
    public Integer call(){
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return randomNum();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadCallableFuture A=new ThreadCallableFuture();
        FutureTask<Integer> futureTask=new FutureTask<>(A);
//        Thread t=new Thread(futureTask);
//        t.start();
        new Thread(futureTask).start();
        System.out.println(futureTask.isDone());
        System.out.println(futureTask.get());
        System.out.println(futureTask.isDone());
    }
}
