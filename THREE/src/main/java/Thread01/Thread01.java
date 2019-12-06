package Thread01;

public class Thread01 extends Thread{
    private String name;
    private int count=10;

    public Thread01(){}
    public Thread01(String name){
        this.name=name;
    }

    public void run(){
        for (int i = 0; i <5 ; i++) {
            synchronized (this){//synchronized关键词:thread和runnable要想正确共享变量都是必不可少
//            System.out.println(name+"运行："+i);
                System.out.println(name+Thread.currentThread().getName()+"第"+i+"次运行"+"此时票数："+count--);

            try {
//                sleep((int)(Math.random()*10));
                sleep(1);//我想试一下间隔相同时间线程是否还是争抢的 结果是的 不是按轮流来的
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }}
}
