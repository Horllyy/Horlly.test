package Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest04_this {

        /**
         * 常见的方法列表
         * @see AtomicInteger#get()             直接返回值
         * @see AtomicInteger#getAndAdd(int)    增加指定的数据，返回变化前的数据
         * @see AtomicInteger#getAndDecrement() 减少1，返回减少前的数据
         * @see AtomicInteger#getAndIncrement() 增加1，返回增加前的数据
         * @see AtomicInteger#getAndSet(int)    设置指定的数据，返回设置前的数据
         *
         * @see AtomicInteger#addAndGet(int)    增加指定的数据后返回增加后的数据
         * @see AtomicInteger#decrementAndGet() 减少1，返回减少后的值
         * @see AtomicInteger#incrementAndGet() 增加1，返回增加后的值
         * @see AtomicInteger#lazySet(int)      仅仅当get时才会set
         *
         * @see AtomicInteger#compareAndSet(int, int) 尝试新增后对比，若增加成功则返回true否则返回false
         */
        public final static AtomicInteger TEST_INTEGER = new AtomicInteger(1);

        public static void main(String []args) {

            for(int i = 0 ; i < 10 ; i++) { //开启10个线程

                new Thread() {
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        int now = TEST_INTEGER.incrementAndGet(); //自增
//                        方法3 这是bloger的原始方法 他甚至没有带上while循环 所有线程只输出一次 我觉得这根本就是没表现出多线程之间的语句穿插
//                        倒更像是简单的for语句运用 只能说稍稍用了atomic的几个API 但是感谢他 让我有点理解了atimic的原子性 它只局限于那几句加减不被穿插
//                        也就是说不会跳跃的增加 其他的语句 好自为之

                        while (TEST_INTEGER.get()<50) {
//                            TEST_INTEGER.incrementAndGet();
//                            System.out.println(Thread.currentThread().getName() + " get value : " + TEST_INTEGER.get());
                            //方法1 上面两条注释掉的语句是方法1 因为多线程的原因所以会产生跳跃输出

                            System.out.println(Thread.currentThread().getName() + " get value : " + TEST_INTEGER.incrementAndGet());
                            //方法2 这种方法减少了因为多线程语句相互交错带来的交叉率 我减少了语句的条数 尽量控制在语句之间不交叉 结果这确实是目前最准确的一种输出
                        }
//                        if(now==5)
//                        {
//                            System.out.println(Thread.currentThread().getName() + " get value : " + now);
//                        }
                        }
                }.start();
            }
        }
}
