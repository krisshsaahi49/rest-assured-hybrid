package com.trivial;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.*;

public class Test1 {

    @Test
    public void test() throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");
//        header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("first name");
        header.createCell(1).setCellValue("last name");

        Row row;
        for (int i = 1; i <= 10; i++) {
            row = sheet.createRow(i);
            row.createCell(0).setCellValue("krissh");
            row.createCell(1).setCellValue("saahi");
        }


        FileOutputStream fileOutputStream = new FileOutputStream("users.xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
    }

    @Test
    public void test2() throws IOException, InvalidFormatException {
        String path = System.getProperty("user.dir");
        path = path + "/users.xlsx";
        System.out.println(path);
        File file = new File(path);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet;
//        sheet = workbook.getSheet("Users");
        sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING -> System.out.print(cell.getStringCellValue() + "\t");
                    case NUMERIC -> System.out.print(cell.getNumericCellValue() + "\t");
                    case BOOLEAN -> System.out.print(cell.getBooleanCellValue() + "\t");
                    default -> System.out.print("UNKNOWN\t");
                }
            }
        }

        workbook.close();
    }

//    @Test
//    public void test3() throws IOException {
//
//        ExcelUtility excelUtility = new ExcelUtility("./src/resources/testdata/user_data.xlsx", "user_data");
//
//        int rowCount = excelUtility.getRowCount();
//        int colCount = excelUtility.getCellCount(1);
//
//        System.out.println(rowCount+"   "+colCount);
//        String[][] apiData = new String[rowCount][colCount];
//
//        for(int i=1; i<rowCount+1; i++){
//            for(int j=0; j<colCount; j++){
//                apiData[i-1][j] = excelUtility.readCell(i, j);
//            }
//        }
//
//
//        Arrays.stream(apiData)
//                .map(row -> String.join(" | ", row))
//                .forEach(System.out::println);
//
//
//    }

    @Test
    public void test5(){
        Logger logger = LogManager.getLogger();
        logger.log(Level.ERROR, "test");
    }


}
