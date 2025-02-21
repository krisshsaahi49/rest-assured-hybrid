package com.util;

import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.util.List;


public class DataProviders {

    @DataProvider(name= "userdata")
    public String[][] getAllData() throws IOException {
        ExcelUtil excelUtil = ExcelUtil.getInstance();
        return excelUtil.readAllData();
    }

    @DataProvider(name = "usernames")
    public Object[][] getUsernames() throws IOException {
        ExcelUtil excelUtil = ExcelUtil.getInstance();
        List<?> usernameList = excelUtil.getColumnData("username"); // Get column data

        // Convert List<Object> to Object[][]
        Object[][] data = new Object[usernameList.size()][1];
        for (int i = 0; i < usernameList.size(); i++) {
            data[i][0] = usernameList.get(i);
        }
        return data;
    }
}
