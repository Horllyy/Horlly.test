package Recursive;

import java.io.File;

public class ParentFileRecurse {
    private void ParentFile(File pFile){
        if(pFile.getParent()!=null){
            this.ParentFile(pFile.getParentFile());
            System.out.println(pFile.getName());
        }
    }

    public static void main(String[] args) {
        ParentFileRecurse pfr=new ParentFileRecurse();
        File pFile=new File("D:\\trial\\aaa\\bbb\\ccc.txt");
        pfr.ParentFile(pFile);
    }
}
//一个简单的向上单线递归
//特点是从最基础的一级开始打印 更符合人的阅读规律