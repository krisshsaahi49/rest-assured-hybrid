package com.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.List;

public class ExcelUtil {
    // private instance
    private static volatile ExcelUtil excelUtil;

    private static Workbook workbook;
    private static Sheet sheet;
    private static Row row;
    FileInputStream inputStream;

    // private constructor
    private ExcelUtil() throws IOException {
        String filePath = "./src/resources/testdata/user_data.xlsx";
        File file = new File(filePath);
        if(file.exists()){
            try {
                inputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                workbook = new XSSFWorkbook();
                if(sheet==null){
                    sheet = workbook.createSheet();
                }else {
                    sheet = workbook.getSheetAt(0);
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public static ExcelUtil getInstance() throws IOException {
        if(excelUtil==null){
            synchronized (ExcelUtil.class){
                if(excelUtil==null){
                    excelUtil = new ExcelUtil();
                }
            }
        }
        return excelUtil;
    }

    public int rowCount(){
        return sheet.getLastRowNum()+1;
    }

    public int colCount(){
        return sheet.getRow(1).getLastCellNum();
    }

    public String[][] readAllData(){
        String[][] data = new String[rowCount()-1][colCount()];
        for(int i=1; i<rowCount(); i++){
            for(int j=0; j<colCount(); j++){
                switch (sheet.getRow(i).getCell(j).getCellType()){
                    case STRING -> data[i-1][j] = sheet.getRow(i).getCell(j).getStringCellValue();
                    case NUMERIC -> data[i-1][j] = String.valueOf(sheet.getRow(i).getCell(j).getNumericCellValue());
                    case BOOLEAN -> data[i-1][j] = String.valueOf(sheet.getRow(i).getCell(j).getBooleanCellValue());
                    default -> data[i][j] = "";
                }
            }
        }
        return data;
    }

    public int colIndex(String colname){
        for (int i=0;i<colCount();i++){
            switch (sheet.getRow(0).getCell(i).getCellType()){
                case STRING -> {
                    if(sheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(colname)){
                        return i;
                    }
                }

                case NUMERIC -> {
                    if(String.valueOf(sheet.getRow(0).getCell(i).getNumericCellValue()).equalsIgnoreCase(colname)){
                        return i;
                    }
                }

                case BOOLEAN -> {
                    if(String.valueOf(sheet.getRow(0).getCell(i).getBooleanCellValue()).equalsIgnoreCase(colname)){
                        return i;
                    }
                }

            }
        }
        return -1;
    }

    public List<?> getColumnData(String colname){
        int index = colIndex(colname);
        List<Object> colData = new ArrayList<>();
        for(int i=1; i<rowCount(); i++){
            switch (sheet.getRow(i).getCell(index).getCellType()){
                case STRING -> colData.add(sheet.getRow(i).getCell(index).getStringCellValue());

                case NUMERIC -> colData.add(BigDecimal.valueOf(sheet.getRow(i).getCell(index).getNumericCellValue()).longValue());

                case BOOLEAN -> colData.add(sheet.getRow(i).getCell(index).getBooleanCellValue());

                default -> colData.add("");
            }
        }
        return colData;
    }

    public List<Object> getRowData(int index){
        List<Object> rowData = new ArrayList<>();
        for(int i=0; i<colCount(); i++){
            switch (sheet.getRow(index).getCell(i).getCellType()){

                case STRING -> rowData.add(sheet.getRow(index).getCell(i).getStringCellValue());
                case NUMERIC -> rowData.add(BigDecimal.valueOf(sheet.getRow(index).getCell(i).getNumericCellValue()).longValue());
                case BOOLEAN -> rowData.add(sheet.getRow(index).getCell(i).getBooleanCellValue());
                default -> rowData.add("");
            }
        }
        return rowData;
    }

    public void closeExcel() throws IOException {
        workbook.close();
        inputStream.close();
    }

}
