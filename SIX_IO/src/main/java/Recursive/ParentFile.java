package Recursive;

import java.io.File;

public class ParentFile {
    public void getParentFile(File file){
        File pFile = file.getParentFile();
        while(true) {
            if (pFile.getParentFile() != null) {
                System.out.println(pFile.getName());
                pFile=pFile.getParentFile();
            }
            else {
                System.out.println(pFile);//盘符不能命名 所以getName()方法对它来说没有意义 所以直接输file就好
                System.out.println("文件在已在根目录.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        ParentFile pf=new ParentFile();
        File  file=new File("D:\\trial\\aaa\\bbb\\ccc.txt");
        pf.getParentFile(file);
    }
}
//一个简单的向上单线循环
//从上一级直到盘符