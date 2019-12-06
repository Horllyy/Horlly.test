package horlly.test.two;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import static java.lang.Math.E;
import static java.lang.Math.PI;


public class IOtest{
    public static void main(String[] args) {
        IOtest iOtest = new IOtest();
        System.out.println("this is testRead:");
        iOtest.testRead();
        System.out.println("this is testWrite::");
        iOtest.testWrite();
        System.out.println("this is otherTest::");
        iOtest.otherTest();

        EventQueue.invokeLater(()->{
            JFrame frame=new ResourceTestFrame();
            frame.setTitle("ResourceTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    public void testRead(){

        //从计算机中读取文件
        Scanner sc = null;
        try {
              sc = new Scanner(new FileInputStream(new File("/a.txt")));//相对路径可以
//            sc = new Scanner(new FileInputStream(new File("D:\\trial\\trial1.txt")));//绝对路径也可以
//            sc = new Scanner(new FileInputStream(new File("/a.gif")));//图片算了 这个方法只针对文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
        sc.close();




    }

    public void testWrite(){
        //写文件至计算机
        try {
            PrintWriter out=new PrintWriter("D:\\trial\\trial3.txt","UTF-8");
            out.write("this is a trial for writing");//文件是构造成功了 东西没写进来
            out.close();//哇 牛逼。要加close才会提交之前write的那些缓存。用out.flush也可以，但是close更完美。
//            out.print("this is a trial for writing.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public void otherTest(){

        String str="helloworld";

        System.out.println("this is a :\u0022);
                System.out.println("this is a : \"");
        System.out.println("this is a : \' \u0022);
                System.out.println(-12%2+"\u03C0");
        System.out.println(PI);
        System.out.println(E);
        //一个小秘密 上面这段不能放在下面 System.out会被关掉hhhh 可能是因为默认的敌不过定义的吧。
        PrintStream ps = new PrintStream(System.out);
        int i = 2;
        int j = 10;
        double k=2.55555;
        ps.printf("  %d\n%d", i, j);
        ps.printf("%.2f\n",k);
        ps.printf("%tc\n", new Date());
        ps.printf("%tT\n", new Date());
        ps.close();

    }
}

class ResourceTestFrame extends JFrame {
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_LENGTH=300;

    public ResourceTestFrame()
    {
        setSize(DEFAULT_WIDTH,DEFAULT_LENGTH);
        URL aboutURL= getClass().getResource("good.jpg");
        System.out.println(getClass());
        System.out.println(aboutURL);
        Image img=new ImageIcon(aboutURL).getImage();
        setIconImage(img);
    }
}