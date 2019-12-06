package Atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {

    /**
     * 常见的方法列表
     * @see AtomicIntegerArray#addAndGet(int, int) 执行加法，第一个参数为数组的下标，第二个参数为增加的数量，返回增加后的结果
     * @see AtomicIntegerArray#compareAndSet(int, int, int) 对比修改，参数1：数组下标，参数2：原始值，参数3，修改目标值，修改成功返回true否则false
     * @see AtomicIntegerArray#decrementAndGet(int) 参数为数组下标，将数组对应数字减少1，返回减少后的数据
     * @see AtomicIntegerArray#incrementAndGet(int) 参数为数组下标，将数组对应数字增加1，返回增加后的数据
     *
     * @see AtomicIntegerArray#getAndAdd(int, int) 和addAndGet类似，区别是返回值是变化前的数据
     * @see AtomicIntegerArray#getAndDecrement(int) 和decrementAndGet类似，区别是返回变化前的数据
     * @see AtomicIntegerArray#getAndIncrement(int) 和incrementAndGet类似，区别是返回变化前的数据
     * @see AtomicIntegerArray#getAndSet(int, int) 将对应下标的数字设置为指定值，第二个参数为设置的值，返回是变化前的数据
     */
    private final static AtomicIntegerArray ATOMIC_INTEGER_ARRAY = new AtomicIntegerArray(new int[]{1,2,3,4,5,6,7,8,9,10});

    public static void main(String []args) throws InterruptedException {
        Thread []threads = new Thread[10];
        for(int i = 0 ; i < 10 ; i++) {
            final int index = i;
            final int threadNum = i;
            threads[i] = new Thread() {
                public void run() {
                    System.out.println("当前线程："+Thread.currentThread().getName());
                    int result = ATOMIC_INTEGER_ARRAY.addAndGet(index, index + 1);
                    System.out.println("线程编号为：" + threadNum + " , 对应的原始值为：" + (index + 1) + "，增加后的结果为：" + result);
                }
            };
            threads[i].start();
        }
        for(Thread thread : threads) {
            thread.join();
            System.out.println("当前join线程："+thread.getName());
        }
        System.out.println("=========================>\n执行已经完成，结果列表：");
        for(int i = 0 ; i < ATOMIC_INTEGER_ARRAY.length() ; i++) {
            System.out.println(ATOMIC_INTEGER_ARRAY.get(i)+Thread.currentThread().getName());
        }
    }
}
/**好 通过一系列的测试 我有理由相信 在默认情况下 多个线程执行 最先执行的反而是mian线程。
 *所以当我把程序里的forEach注释掉以后 结果先输出main再输出线程run方法里的内容。
 *
 * 其次 forEach的作用也不是让这些线程按顺序进行 事实上 除了forEach自己内部的语句顺序执行 其他线程并不遵从它的顺序
 * 所以也有理由相信 forEach的功能仅仅是为了让所有子线程在mian线程之前执行。(其实不用这样也没事 这里没有特殊顺序要求 所以mian先执行完也没事)
 *
 * 最后 多线程的确在正常运行 以相互之间语句穿插的方式 我想在输出结果里面表达的很清楚。
 * */