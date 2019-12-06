package Runnable01;

public class Thread02 implements Runnable {
    private String name;
    private int count=10;

    public Thread02(String name){
        this.name=name;
    }

    @Override
    public void run(){
        for (int i = 0; i <5 ; i++) {
            synchronized (this) {
                System.out.println("name=" + name + " 当前" + Thread.currentThread().getName() + " 运行：" + i + " 此时count=" + count--);

                try {
//                sleep((int)(Math.random()*10));
                    Thread.sleep(1);//我想试一下间隔相同时间线程是否还是争抢的 结果是的 不是按轮流来的
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
