package IOputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 这个是用来瞎测试一些性能的 没啥用 随便玩
 */
public class ExcelTrial extends ExcelUtil {

    public static void main(String[] args) throws IOException {
        File file=new File("D:\\trial\\excel06.xls");
        System.out.println(file.exists());
//        boolean boo=file.createNewFile();
//        System.out.println(boo);//诡异 太诡异了
        FileInputStream fileInputStream=new FileInputStream(file);
        Workbook wb=new HSSFWorkbook(fileInputStream);
        System.out.println("index="+wb.getSheetIndex("Sheet5"));

    }
}
/**
 * 我知道了 这段测试
 * Workbook wb=new HSSFWorkbook(fileInputStream);报错 0bytes...
 *  Workbook wb=new HSSFWorkbook(); 不报错
 *  因为这是一个FileInputStream类型的输出流 大声和我念 输出流
 *  所以刚刚新建的文件只有0字节就不行 没意义
 *  这时候直接用无参数的方法Workbook wb=new HSSFWorkbook();就行了
 *
 *  噢总之这是我目前想到的最合理的解释了
 *  至于为啥我新建了文件后这个带参数方法还是不能用 就很诡异了。。。
 *  我以后再试试
 *
 *
 * 总结：
 * 1.源文件存在 带参数方法可行
 *
 * 2.源文件不存在 啥方法都不可行 你没文件啊
 *
 * 3.源文件不存在 我创建一个 带参数方法不可行 无参数方法可行
 */
