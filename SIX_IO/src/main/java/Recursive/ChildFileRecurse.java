package Recursive;

import java.io.File;

public class ChildFileRecurse {
    public void getChildFile(String path,File cfile){
        if(cfile.isDirectory()) {
            File[] cfiles = cfile.listFiles();
            path=path+"\\"+cfile.getName();
            if (cfiles.length != 0) {
                for (int i = 0; i < cfiles.length; i++) {
                    if (i == 0) {
//                        System.out.println(cfile.getName() + "的目录下有:");//上一级目录名字
//                        System.out.println(cfile.getPath()+ "的目录下有:");//自根目录开始名字
                        System.out.println(path+ "的目录下有:");//自我给的路径开始的目录名字
                    }
                    System.out.println(cfiles[i].getName());
                }
                for (File file : cfiles
                ) {
                    this.getChildFile(path,file);
                }
            }
            else System.out.println(cfile.getName()+"是个空文件夹");
        }
//        else System.out.println(cfile.getName()+"已经是最后一级.");
    }

    public static void main(String[] args) {
        ChildFileRecurse childFileRecurse=new ChildFileRecurse();
        File cfile=new File("D:\\trial");
        childFileRecurse.getChildFile("在",cfile);//这里是为了安全性考虑 万一这是一个相对路径 我不想让其他人知道
                                                         //此目录之前的目录 所以虽然我一开始用了getPath() 省事
                                                         // 但后来还是花了一部分时间采用了上一级叠加的方式来增加用户的隐私性
    }
}
//不说了 真是个完美的程序 写程序真的很好玩


//public class ChildFileRecurse {
//    String cpath="";
//    public void getChildFile(String path,File cfile){
//        if(cfile.isDirectory()) {
//            File[] cfiles = cfile.listFiles();
//            cpath=path+"\\"+cfile.getName();
//            if (cfiles.length != 0) {
//                for (int i = 0; i < cfiles.length; i++) {
//                    if (i == 0) {
////                        System.out.println(cfile.getName() + "的目录下有:");//上一级目录名字
////                        System.out.println(cfile.getPath()+ "的目录下有:");
//                          System.out.println(cpath+ "的目录下有:");//自我给的路径开始的目录名字
//                    }
//                    System.out.println(cfiles[i].getName());
//                }
//                for (File file : cfiles
//                ) {
//                    this.getChildFile(cpath,file);
//                }
//            }
//        }
////        else System.out.println(cfile.getName()+"已经是最后一级.");
//    }
//
//    public static void main(String[] args) {
//        ChildFileRecurse childFileRecurse=new ChildFileRecurse();
//        File cfile=new File("D:\\trial");
//        childFileRecurse.getChildFile("在",cfile);
//    }
//}
