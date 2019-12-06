package IOputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 功能：
 * 文件加解密
 * （主播说图片音频都行 但我没试过 要是我写的严谨讲道理也行）
 */
public class FileSecret {
    public void copyfileIntoSecret02(File file,String filepath){
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
                for (int i = 0; i <b.length; i++) {
                    b[i]= (byte) (b[i]+10);
                }
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

    public void copyfileOutOfSecret02(File file,String filepath){
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
                for (int i = 0; i <b.length; i++) {
                    b[i]= (byte) (b[i]-10);
                }
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


    public void fileIntoSecret(File file) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File middlefile=new File(file.getParent()+"\\middleFile.txt");
        try {
            fis = new FileInputStream(file);
            long value=fis.available();
            System.out.println(value);
            fos = new FileOutputStream(middlefile,true);
            byte[] b = new byte[5];

            int count=(int)(value/5+1);
            System.out.println("count="+count);
            while (count-->0) {
                fis.read(b);
                System.out.println("从file中读出:" + b);
                for (int i = 0; i <b.length; i++) {
                    b[i]= (byte) (b[i]+1);
                }
                if(count==0)
                    fos.write(b,0,(int)(value%5));
                else
                    fos.write(b);
                fos.flush();
                System.out.println("向fileCopy写进:" + b);
            }
            System.out.println(middlefile.renameTo(file));//I failed 在源文件上加密失败 他妈的 我周末再来弄!!!
            System.out.print("OVER,boss!");

//            int count = fis.read(b);
//            while (count!=-1) {
//                System.out.println("从file中读出:" + b);
//                for (int i = 0; i <b.length; i++) {
//                    b[i]= (byte) (b[i]+1);
//                }
//                fos.write(b,0,count);
//                fos.flush();
//                System.out.println("向fileCopy写进:"+b);
//                count=fis.read(b);
//                System.out.println("count="+count);
//            }
//            System.out.print("OVER,boss!");//死循环

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void fileOutOfSecret(File file){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(file);
            System.out.println(fis.available());
            byte[] b = new byte[10];
            int count = fis.read(b);
            while (count != -1) {
                System.out.println("从file中读出:" + b);
                for (int i = 0; i <b.length; i++) {
                    b[i]= (byte) (b[i]-10);
                }
                fos.write(b, 0, count);
                System.out.println("向fileCopy写进:" + b);
                count = fis.read(b);
            }
            System.out.print("OVER,boss!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        FileSecret fileSecret=new FileSecret();
        File file=new File("D:\\trial\\good1.txt");
        String filepath="C:\\trialCopy";
//        fileSecret.copyfileIntoSecret02(file,filepath);//复制加密
        fileSecret.copyfileOutOfSecret02(file,filepath);//复制解密


//        fileSecret.fileIntoSecret(file);//源文件加密
//        fileSecret.fileOutOfSecret(file);//源文件解密
    }
}

/**
 * 加密解密程序还不完整 使用起来还需要一定逻辑
 * 这对人民群众来讲可能是想自杀的软件
 * 需要改进的地方有:
 * (因为现在是需要我手动调换加密和解密的路径和文件
 * 而且因为没有删除原文件的功能实现 导致我为了避免重复写每次都要手动删除文件内容...)
 * 1.既然只能复制加解密 那么有两种选择 1.同一文件夹不同命名 2.不同文件夹任意命名(最好关联)
 * 我觉得在同一个文件夹内比较好 可以顺便设置isHidden属性 顺便删除原文件 反正对一般人来讲看不懂就好了吧
 *
 * 2.这就需要一个小小的文件路径转换 不要让使用者手动编写 而只要运行不同方法就能自己加解密
 *  类似于两个按钮 要杜绝让使用者自己输参数
 *
 * 3.或者干脆自己编写完整这个加解密程序吧有时间 比如 在学校学习没有工作的时候
 *
 * 创新需要时间.妹妹 be patient.你已经具有很强的逻辑思维了 你现在要做就是把严谨和继续学习+不断练习当作日常操纵
 * 永远不要害怕新东西新思想加新功能 大胆去做 大不了copy原件以防万一...(怂
 */