package IOputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 功能：
 * 文件复制
 * （同上 讲道理视频音频都行）
 */
public class FileCopy {
    public void copyFile(File file,String filepath){
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis = new FileInputStream(file);
            fos=new FileOutputStream(filepath+"\\"+file.getName(),true);
            System.out.println(fis.available());
            byte[] b=new byte[10];
            int count=fis.read(b);
            while(count!=-1) {
                System.out.println("从file中读出:"+b);
                fos.write(b,0,count);
                System.out.println("向fileCopy写进:"+b);
                count=fis.read(b);
                System.out.println("count="+count);
            }
            System.out.print("OVER,boss!");

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if(fis!=null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        FileCopy fc=new FileCopy();
        File file=new File("D:\\trial\\good2.txt");
//        String filepath="C:\\trialCopy";
//        fc.copyFile(file,filepath);

    }
}