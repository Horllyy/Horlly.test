package horlly.test.two;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.*;
import java.sql.SQLSyntaxErrorException;
import java.util.Date;
import java.util.Scanner;

import static java.lang.Math.*;

@SpringBootApplication
public class TwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwoApplication.class, args);
    }

    public void test(){

        String str="helloworld";

        System.out.println("this is a :\u0022);
                System.out.println("this is a : \"");
        System.out.println("this is a : \' \u0022);
                System.out.println(-12%2+"\u03C0");
        System.out.println(PI);
        System.out.println(E);

     //从计算机中读取文件
        Scanner sc = null;
        try {
            sc = new Scanner(new FileInputStream(new File("D:\\trial\\trial1.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
        sc.close();

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
//这个springboot运行出了点问题 可能因为我框架不熟里面文件乱加 出问题了 我下午把idea里的新建项目框架分一下开来。
//下午我吧其他的都区分出去看一下。