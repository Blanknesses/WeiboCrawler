import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcelOperation {
    private String excelPath;
    private XSSFWorkbook sheets;
    private XSSFSheet sheet;
    private int maxRow;

    public ExcelOperation(String excelPath, String sheetName){
        try{
            this.excelPath = excelPath;
            this.sheets = new XSSFWorkbook(new FileInputStream(excelPath));
            this.sheet = sheets.getSheet(sheetName);
            this.maxRow = sheet.getPhysicalNumberOfRows();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getExcelDateByIndex(int row, int colum){
        XSSFRow row1 = sheet.getRow(row);
        String cell = row1.getCell(colum).toString();
        return cell;
    }

    public synchronized void writeDateToExcel(int row, int colum, String date){
        try {
            XSSFRow row1 = sheet.getRow(row);
            if (row1 == null)
                row1 = sheet.createRow(row);
            XSSFCell cell = row1.getCell(colum + 1);
            if (cell == null)
                cell = row1.createCell(colum + 1);
            cell.setCellValue(date);
            sheets.write(new FileOutputStream(excelPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getData(int row, int colum, String cookie){
        ExecutorService pool = Executors.newFixedThreadPool(5);
        QueryCompany query = new QueryCompany();
        query.setCookie(cookie);
        Random random = new Random();
        for (; row < maxRow; row ++){
            //从Excel中获取简称
            //String companyShortName = getExcelDateByIndex(row, colum);
            //从Excel中获取全称
            String companyFullName = getExcelDateByIndex(row, colum );
            int finalRow = row;
            /*Runnable runnable = ()->{
                String date = query.getConpanyRegisterDate(companyFullName);
                try {
                    Thread.sleep(random.nextInt(10000) + 5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (date == "-1")
                    return;
                else if (date == "0") {
                    *//*date = query.getConpanyRegisterDate(companyFullName);
                    if (date == "-1")
                        date = "0";*//*
                    return;
                }
                String[] strs = date.split("=");
                writeDateToExcel(finalRow, colum + 2, strs[0]);
                writeDateToExcel(finalRow, colum + 3, strs[1]);
                System.out.println(Thread.currentThread().getName() + "write sucess" + "No." + finalRow);
            };
            pool.submit(runnable);*/
            String date = query.getConpanyRegisterDate(companyFullName);
            try {
                Thread.sleep(random.nextInt(5000) + 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (date == "-1" || date == "0") {
                System.out.println(Thread.currentThread().getName() + "has null" + "No." + finalRow);
                continue;
            }
            String[] strs = date.split("=");
            writeDateToExcel(finalRow, colum + 2, strs[0]);
            writeDateToExcel(finalRow, colum + 3, strs[1]);
            System.out.println(Thread.currentThread().getName() + "write sucess" + "No." + finalRow);
        }
    }
}
