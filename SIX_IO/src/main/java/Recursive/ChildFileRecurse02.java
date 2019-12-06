package Recursive;

import java.io.File;

public class ChildFileRecurse02 {
    public void getChildFile02 (File cfile){
        if(cfile.isDirectory()) {
            File[] cfiles = cfile.listFiles();
            if (cfiles.length != 0) {
                for (int i = 0; i < cfiles.length; i++) {
//                    if (i == 0) {
//                        System.out.println(cfiles[i].getPath() + "的目录下有:");//自我给的路径开始的目录名字
//                    }
                    System.out.println(cfiles[i].getPath());
                    //想要删除文件夹 只需在这句的位置加delete
                    // 哦不对 还要加上isFile和文件夹is空的if判断 然后在最外面加上大while循环直到里面没有文件夹和文件
                    //意思就是我们每次不是只能删除叶子文件和空文件夹嘛 那么每次删最外面一层咯 所以要while好几遍
                    //好吧 这是最简单的想法 或许有更简单的 以后再说吧 我有空去看看高手怎么做的
                    //(讲道理这种肯定会有封装方法的吧 都这么频繁了...除了练练思维也没啥用了)
                    // 噢总的来说还是简单的 不做了
                    this.getChildFile02(cfiles[i]);
                }

            }
            else System.out.println(cfile.getName()+"是个空文件夹");
        } }

    public static void main(String[] args) {
        ChildFileRecurse02 childFileRecurse=new ChildFileRecurse02();
        File cfile=new File("D:\\trial");
        childFileRecurse.getChildFile02(cfile);
    }
}

//案列也想的太简单了 不太安全啊