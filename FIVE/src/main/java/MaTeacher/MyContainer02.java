package MaTeacher;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 这次改进采用门闩
 */
public class MyContainer02 {
    volatile int count=0;
    CountDownLatch latch=new CountDownLatch(1);

    public void add() {
            for (int i = 0; i < 10; i++) {
                count++;
                System.out.println(Thread.currentThread().getName() + "count=" + count);
                if (count == 5) {
                    System.out.println("把门闩打开使线程2继续执行");
                    latch.countDown();

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //这里想要精确线程2表示到5结束 就要sleep一会 门闩虽然好用 但是要知道它是谁都不得罪 并没有说把线程1暂停.所以 多次模拟发现
                    //线程2得以继续执行的同时 线程1把count加到6 线程2才跟上的机率也很大
                    //所以我觉得不加sleep不太精确 而且用sleep我总觉得不太好 过于人为 相比而言 用synchronized的精确性我个人感觉是最高的.
                    //逻辑理清的情况下我觉得更好.
                }
            }
        }


    public void size(){
//        while(true) {
//            if(count==5) {
        if(count!=5) {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println("count=" + count + "," + Thread.currentThread().getName() + "结束");
//                    break;
//            }
//        }

    }

    public static void main(String[] args) {
        MyContainer02 myContainer02=new MyContainer02();

        new Thread(()->{
            System.out.println("线程2开始.");
            myContainer02.size();
            System.out.println("线程2结束.");
        },"线程2").start();

        new Thread(()->{
            System.out.println("线程1开始");
            myContainer02.add();
            System.out.println("线程1结束");
        },"线程1").start();

    }
}