package IOputStream;

import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.OutputStream;
        import java.util.ArrayList;
        import java.util.Iterator;

        import org.apache.poi.hssf.usermodel.HSSFCell;
        import org.apache.poi.hssf.usermodel.HSSFRow;
        import org.apache.poi.hssf.usermodel.HSSFWorkbook;
        import org.apache.poi.ss.usermodel.Cell;
        import org.apache.poi.ss.usermodel.CellStyle;
        import org.apache.poi.ss.usermodel.Row;
        import org.apache.poi.ss.usermodel.Sheet;
        import org.apache.poi.ss.usermodel.Workbook;
        import org.apache.poi.xssf.usermodel.XSSFRow;

/**
 * 我觉得他原来的构造器不够科学
 * 有些为了格式完整瞎搞的感觉 虽然功能可以实现 但我读起来总是怪怪的
 * 比如有一个if else里面东西完全一样 那你ifelse个尼玛啊。。。
 * 反正我自己写一遍吧
 * 熟悉熟悉这几天学的东西也挺好的
 *
 * 其他的增删改查功能我没全部看完 但感觉没问题 应该不会像构造器这么匪夷所思吧。。。啊反正有的先用着
 * 细节慢慢细化精化吧
 */
public class ExcelUpdate {

    private boolean isXls=false;
    private Sheet sheet;
    private Workbook wb;
    private String savePath;

    public ExcelUpdate(String excelPath,String sheetname) throws IOException{
        savePath=excelPath;
        isXls=excelPath.endsWith(".xls");
//        if(!isXls){
//            excelPath+=".xls";
//        }
        //不知道为啥这个加后缀没用 估计是为了安全性吧
        File file=new File(excelPath);
        FileInputStream excelfile=null;
        if(file.exists()){
            System.out.println("此表格已存在，正在覆盖...");
            excelfile=new FileInputStream(excelPath);
            wb = new HSSFWorkbook(excelfile);
            partInit(sheetname);
            System.out.println("覆盖完毕，请执行后续步骤");
        }
        else
        {
            System.out.println("此表不存在,正在创建...");
                wb = new HSSFWorkbook();
                partInit(sheetname);
                System.out.println("创建完毕，请执行后续步骤");

        }

//        savePath=excelPath;
//        isXls=excelPath.endsWith(".xls");
//        FileInputStream excelfile = null;
//        File file = new File (excelPath);
//        if(file.exists()){
//            excelfile=new FileInputStream(excelPath);
//        }
//        if(!isXls){
////	wb = new XSSFWorkbook(excelfile);
////	setActiveSheet(sheetname);
//            if(null!=excelfile){
//                wb =new XSSFWorkbook(excelfile);
//                setActiveSheet(sheetname);
//            } else {
//                wb =new XSSFWorkbook();
//                createSheet(sheetname);
//            }
//        } else {
////	wb = new HSSFWorkbook(excelfile);
////	setActiveSheet(sheetname);
//            if(null!=excelfile){
//                wb =new HSSFWorkbook(excelfile);
//                setActiveSheet(sheetname);
//            } else {
//                wb =new HSSFWorkbook();
//                createSheet(sheetname);
//            }

//        }
    }

    public void partInit(String sheetname){
        int sheetindex=wb.getSheetIndex(sheetname);
        if(sheetindex!=-1)
            setActiveSheet(sheetname);
        else {
            createSheet(sheetname);
            setActiveSheet(sheetname);
        }
    }
    //一些重复部分 我看着难受直接部分初始化封装吧

    public void insertColumn(int columnIndex) {
        if(isXls){
            int maxCellNum=columnIndex;
            for(Iterator<Row> rowIterator=sheet.rowIterator();rowIterator.hasNext();){
                HSSFRow row = (HSSFRow) rowIterator.next();
                for(int i=row.getLastCellNum()-1;i>=columnIndex;i--){
                    HSSFCell cell2 = row.getCell(i);
                    if(cell2==null){
                        continue;
                    }
                    row.moveCell(cell2, (short)(i+1));
                }
                HSSFCell cell = row.getCell(columnIndex);
                if(cell!=null){
                    row.removeCell(cell);
                }
                if(maxCellNum<row.getLastCellNum()){
                    maxCellNum=row.getLastCellNum();
                }
            }
            for(int n=maxCellNum-2;n>columnIndex;n--){
                int width=sheet.getColumnWidth(n-1);
                sheet.setColumnWidth(n, width);

            }
        } else {
            int maxCellNum=columnIndex;
            for(Iterator<Row> rowIterator=sheet.rowIterator();rowIterator.hasNext();){
                XSSFRow row = (XSSFRow)rowIterator.next();
                for(int i=row.getLastCellNum()-1;i>=columnIndex;i--){
                    Cell cell2 = row.getCell(i);
                    if (cell2==null) {
                        continue;
                    }
                    String value = getCellvalue(row.getRowNum(), i);
                    setCellvalue(row.getRowNum(), i+1, value);
                    setCellStyle(row.getRowNum(), i+1, cell2.getCellStyle());
                    row.removeCell(cell2);
                }
                if(maxCellNum<row.getLastCellNum()){
                    maxCellNum=row.getLastCellNum();
                }
            }
            for(int n=maxCellNum-2;n>columnIndex;n--){
                int width = sheet.getColumnWidth(n-1);
                sheet.setColumnWidth(n, width);

            }
        }
    }

    public void insertRow(int rowIndex) {
// TODO 自动生成的方法存根
        sheet.shiftRows(rowIndex, sheet.getLastRowNum(), 1,true,false);
        sheet.createRow(rowIndex);
    }


    public void createSheet(String sheetName) {
// TODO 自动生成的方法存根
        sheet = wb.createSheet(sheetName);
    }


    public void delColumn(int columnIndex) {
        if(isXls){
            int maxCellNum = columnIndex;
            for(Iterator<Row> rowiIterator = sheet.rowIterator();rowiIterator.hasNext();){
                HSSFRow row = (HSSFRow) rowiIterator.next();
                HSSFCell cell = row.getCell(columnIndex);
                if(cell!=null){
                    row.removeCell(cell);
                }

                for(int i=columnIndex;i<row.getLastCellNum();i++){
                    HSSFCell cell2= row.getCell(i+1);
                    if(cell2==null){
                        continue;
                    }
                    row.moveCell(cell2, (short)i);

                }
                if(maxCellNum<row.getLastCellNum()){
                    maxCellNum=row.getLastCellNum();
                }
            }
            for(int n= columnIndex;n<maxCellNum;n++){
                int width=sheet.getColumnWidth(n+1);
                sheet.setColumnWidth(n, width);
            }
        } else {
            int maxCellNum = columnIndex;
            for(Iterator<Row> rowiIterator = sheet.rowIterator();rowiIterator.hasNext();){
                XSSFRow row = (XSSFRow) rowiIterator.next();
                Cell cell = row.getCell(columnIndex);
                if(cell!=null){
                    row.removeCell(cell);
                }

                for(int i=columnIndex;i<row.getLastCellNum();i++){
                    Cell cell2= row.getCell(i+1);
                    if(cell2==null){
                        continue;
                    }
                    String value = getCellvalue(row.getRowNum(), i+1);
                    setCellvalue(row.getRowNum(), i,value);
                    setCellStyle(row.getRowNum(), i, cell2.getCellStyle());
                    row.removeCell(cell2);
                }
                if(maxCellNum<row.getLastCellNum()){
                    maxCellNum=row.getLastCellNum();
                }
            }
            for(int n = columnIndex;n<maxCellNum;n++){
                int width = sheet.getColumnWidth(n+1);
                sheet.setColumnWidth(n, width);
            }
        }
    }


    public void delRow(int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        sheet.removeRow(row);
        sheet.shiftRows(rowIndex+1, sheet.getLastRowNum(), -1, true, false);
    }


    public String getCellvalue(int rowIndex, int columnIndex) {
        Row row = sheet.getRow(rowIndex);
        if(row==null){
            return "";
        }
        Cell cell = row.getCell(columnIndex);
        if(cell==null){
            return "";
        }
        cell.setCellType(Cell.CELL_TYPE_STRING);

        return cell.getStringCellValue();
    }

    /**
     * 获取某行的列数
     */
    public int getColumnNumb(int rowIndex) {
// TODO 自动生成的方法存根
        return sheet.getRow(rowIndex).getLastCellNum();
    }
    /**
     * 获取最后行索引
     */

    public int getLastRowIndex() {

        return sheet.getLastRowNum();
    }

    /**
     * 按照行读取内容
     */

    public ArrayList<String> getRow(int rowIndex) {
        ArrayList<String> al = new ArrayList<String>();
        Row row = sheet.getRow(rowIndex);
        for(int i=0;i<row.getLastCellNum();i++){
            al.add(getCellvalue(rowIndex, i));
        }
        return al;
    }


    public void setRow(int rowIndex,ArrayList<String> al) {
        for(int i=0;i<al.size();i++){
            setCellvalue(rowIndex, i, al.get(i));
        }
    }


    public void saveExcel() throws IOException {
// TODO 自动生成的方法存根
        OutputStream stream = new FileOutputStream(savePath);
        wb.write(stream);
        stream.close();
//        wb.close();
    }


    public void setActiveSheet(int sheetIndex) {
        sheet=wb.getSheetAt(sheetIndex);
        if (sheet==null) {
            sheet=wb.createSheet();
        }
    }


    public void setActiveSheet(String sheetName) {
        sheet=wb.getSheet(sheetName);
        if (sheet==null) {
            sheet=wb.createSheet(sheetName);
        }
    }


    public void setCellStyle(int rowIndex, int columnIndex, CellStyle cellStyle) {
// TODO 自动生成的方法存根
        Row row =sheet.getRow(rowIndex);
        if(row==null){
            return ;
        }
        Cell cell =row.getCell(columnIndex);
        if(cell==null){
            return ;
        }
        cell.setCellStyle(cellStyle);
    }


    public void setCellvalue(int rowIndex, int columnIndex,String value) {
        Row row =sheet.getRow(rowIndex);
        if(row==null){
            row=sheet.createRow(rowIndex);
        }
        Cell cell =row.createCell(columnIndex);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(value);
    }
    public static void main(String[] args) throws IOException {
        ExcelUpdate we = new ExcelUpdate("D:\\trial\\excel04.xls", "Sheet2");
//	      we.delColumn(2);
//	      we.delRow(1);//删row没有问题
//        we.insertRow(1);//增row没有问题
        we.setCellvalue(1, 1, "2B");//改row和col没有问题
        we.setCellvalue(2, 1, "3B");
//        we.setActiveSheet("Test2");//增sheet没有问题
//        we.setCellvalue(1, 1, "2B2");
//        we.setCellvalue(2, 1, "3B2");
//        we.setCellvalue(1, 1, "2B");//改row和col没有问题
//        we.setCellvalue(2, 1, "3B");

//        we.sheet.createRow(1);
        //create和insert的不同之处在于create直接会覆盖掉原行数据生成新的行
        //所以上面insert封装方法在create之前先移了一下原始数据
//        we.setCellvalue(2, 1, "3B");
        we.saveExcel();//save没有问题
        System.out.println("后续步骤执行完毕");

    }

}
/**
 * 总结： 增（行 列） 删（行 列） 查（或待更精细） 改（行 列）没有问题
 */