package IOputStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelToByte {

    /**
     * 第三次尝试
     * 圆满成功 效果喜人
     */


    /**
     * 用java将txt数据导入excel
     * 然而这个不能够 虽然看上去代码就很不错 但是没到txt转excel这个级别
     * 充其量就是创建一个表格 填入几个数据罢了 这个我前面的增删改查也能做好
     */
        public static void main(String args[])
        {
            try
            {
                //打开文件
                WritableWorkbook book= Workbook.createWorkbook(new File("D:\\trial\\测试.xls"));
                //生成名为“第一页”的工作表，参数0表示这是第一页
                WritableSheet sheet=book.createSheet("第一页",0);
                //在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
                //以及单元格内容为test
                Label label=new Label(0,0,"test");
                //将定义好的单元格添加到工作表中
                sheet.addCell(label);
     /*生成一个保存数字的单元格
       必须使用Number的完整包路径，否则有语法歧义
       单元格位置是第二列，第一行，值为789.123*/
                jxl.write.Number number = new jxl.write.Number(1,0,789.123);
                sheet.addCell(number);
                //写入数据并关闭文件
                book.write();
                book.close();
            }catch(Exception e)
            {
                System.out.println(e);
            }

        }




/**
 * 第二次尝试
 * 成功一半

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

    public class ExcelToByte {

        private static Map<Integer, String> keyMapName = new LinkedHashMap<>();
        private static Map<String, String> keyMapContext = new LinkedHashMap<>();

        static ConcurrentLinkedQueue<String> queues = new ConcurrentLinkedQueue<>();

        static ObjectMapper objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

        public static void main(String[] args){

            try {
                HSSFWorkbook wb = null;
                POIFSFileSystem fs = null;

                String filePath = "D:\\trial\\excel02.xls";
                fs = new POIFSFileSystem(new FileInputStream(new File(filePath)));
                wb = new HSSFWorkbook(fs);

                HSSFSheet sheet = wb.getSheetAt(0);
                System.out.println("sheet的名字是："+sheet.getSheetName());
                Integer rowNum;
                HSSFRow rowName = sheet.getRow(1);
                System.out.println( "rowName="+rowName);
                for (int cellNum = rowName.getFirstCellNum(); cellNum < rowName.getLastCellNum(); cellNum++){
                    keyMapName.put(cellNum, rowName.getCell(cellNum).getStringCellValue());
                }

                for(rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++){
                    HSSFRow row = sheet.getRow(rowNum);
                    if(row != null){
                        for (int cellNum = row.getFirstCellNum(); cellNum< row.getLastCellNum(); cellNum++){
                            if(row.getCell(cellNum).getStringCellValue() != null && !"".equals(row.getCell(cellNum).getStringCellValue())) {
                                keyMapContext.put(keyMapName.get(cellNum), row.getCell(cellNum).getStringCellValue());
                            }
                        }
                    }
                    if(keyMapContext != null && keyMapContext.size() > 0) {
                        queues.add(objectMapper.writeValueAsString(keyMapContext));
                    }
                }

                File file = new File("D:\\trial\\excel02.txt");

                FileOutputStream fileOutputStream = new FileOutputStream(file);
                OutputStreamWriter outputStream = new OutputStreamWriter(fileOutputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStream);

                int i = 0;
                while (true){
                    if(!queues.isEmpty()) {
                        String context = queues.poll();
                        System.out.println("第"+i+"个context="+context);
                        if(context == null) continue;
                        bufferedWriter.write(context);
                        bufferedWriter.newLine();
                        i++;
                        System.out.println("total ：" + i);
                    }
                    if(queues.isEmpty()){
                        try{
                            bufferedWriter.flush();
                            outputStream.flush();
                            bufferedWriter.close();
                            outputStream.close();
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }
                        catch (Exception e){
                            e.printStackTrace(System.out);
                        }
                        break;
                    }
                }
            } catch (Exception e){
                System.out.println("something wrong");
                e.printStackTrace();
            }
        }
*/


    /**
     * 第一次尝试
     * 几乎完全失败
     */
//    public static void main(String[] args) throws IOException {
//        File file=new File("D:\\trial\\excel03.xls");
//        FileInputStream fileInputStream=new FileInputStream(file);
//        HSSFWorkbook workBook = new HSSFWorkbook(fileInputStream);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try {
//            workBook.write(baos);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        byte[] bytes = baos.toByteArray();
//        System.out.println(bytes.length);
//
//        for (byte b:bytes
//             ) {
//            System.out.println(b);
//        }
//    }
}

/**
 * 用java将excel数据导入txt
 * 成功
 */
class WriteTxt {

    public static void main(String[] args) {
// TODO Auto-generated method stub
        String filepath = "D:\\trial\\excel02.xls";

        try {
            Workbook workbook = Workbook.getWorkbook(new File(filepath));
            Sheet sheet = workbook.getSheet(0);
            File file = new File("D:\\trial\\excel02.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
// j为行数，getCell("列号","行号")
            int j = sheet.getRows();
            int y = sheet.getColumns();
            for (int i = 0; i < j; i++) {
                for(int x=0; x<y; x++){

                    Cell c = sheet.getCell(x, i);
                    String s = c.getContents();
                    bw.write(s);
                    bw.write(" ");
                    bw.flush();
                }
                bw.newLine();
                bw.flush();
            }
            System.out.println("写入结束");
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
