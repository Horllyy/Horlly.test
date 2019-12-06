package Thread01;

public class Thread01Test {
    public static void main(String[] args) {
//        Thread01 A=new Thread01("A");
//        Thread01 B=new Thread01("B");
////        Thread01 B=A;//试试看重复运行线程会不会抛出异常
//
//        A.start();
//        B.start();
        //(1)thread独有开启方式,但是它是两个实例.0011223344

        Thread01 A=new Thread01("class Thread01");
        new Thread(A,"线程1").start();
        new Thread(A,"线程2").start();
        //(2)要创建同一个实例的两个线程还是要这样定义 这个和runnable一样.0123456789
    }
}
//