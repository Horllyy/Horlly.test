package MaTeacher;


/**
 * while消耗cpu太大了 所以在while成功后我把这个程序改成了synchronized关键字格式
 * 线程2不再需要while反复查询是否到了5
 * 它可以节约消耗放心睡觉 等线程1到达5之后叫醒它 来进行下一步工作.
 * 当然 这里对锁的了解要求比较高 明确两点:wait释放锁 notify不释放锁
 *
 * 还有 记得先让线程2启动 为啥 为了让它立刻进入wait状态
 */
public class MyContainer01 {
     volatile int count=0;
     Object lock=new Object();

    public void add() {
        synchronized(lock) {
            for (int i = 0; i < 10; i++) {
                count++;
                System.out.println(Thread.currentThread().getName() + "count=" + count);
                if (count == 5) {
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void size(){
        if(count!=5){
        synchronized(lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        while(true) {
//            if(count==5) {
            System.out.println("count=" + count + "," + Thread.currentThread().getName() + "结束");
            //                    break;
//            }
//        }
            lock.notify();

        }
    }

    public static void main(String[] args) {
        MyContainer01 myContainer01=new MyContainer01();

        new Thread(()->{
            System.out.println("线程2开始.");
            myContainer01.size();
            System.out.println("线程2结束.");
        },"线程2").start();

        new Thread(()->{
            System.out.println("线程1开始");
                myContainer01.add();
                System.out.println("线程1结束");
        },"线程1").start();

    }
}
