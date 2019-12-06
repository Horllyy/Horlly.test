package blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueTest02 {
    private int queueSize = 9;
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize);

    public static void main(String[] args) {
        BlockingQueueTest02 test = new BlockingQueueTest02();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                try {
                    queue.take();
                    System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer extends Thread {

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                try {
                    queue.put(1);
                    System.out.println("向队列中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

//这个语句中部分打印语句没有意义 不代表正确值 只能算一个大概值 但基本流程使用方法 可以借鉴一下
//嗯结构也挺清晰