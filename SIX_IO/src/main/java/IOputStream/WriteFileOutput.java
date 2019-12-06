package IOputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 功能：
 * 文件写入 输出流
 */

public class WriteFileOutput {
    public void writeFile(File file){
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(file);
            String str="Horlly is really a adorable amazing charming intelligent girl.";
            byte[] b=str.getBytes();
            fos.write(b);
            fos.flush();
            System.out.println("写入完毕,老大.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
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
        WriteFileOutput wfo=new WriteFileOutput();
        File file=new File("D:\\trial\\good3.txt");
        wfo.writeFile(file);
    }
}
