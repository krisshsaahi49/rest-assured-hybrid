package com.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportUtil{

    private static volatile ExtentReportUtil extentReportUtil;
    private ExtentSparkReporter sparkReporter;
    private ExtentReports extentReports;
    private ExtentTest extentTest;

    private ExtentReportUtil(){
        String filename = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss-SSS").format(new Date());
        filename = "./reports/"+filename+"-extent-report.html";
        sparkReporter = new ExtentSparkReporter(filename);
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
    }

    public static ExtentReportUtil getInstance(){
        if(extentReportUtil==null){
            synchronized (ExtentReportUtil.class){
                if(extentReportUtil==null){
                    extentReportUtil = new ExtentReportUtil();
                }
            }
        }
        return extentReportUtil;
    }

    public void createTest(String testname){
        extentTest = extentReports.createTest(testname);
    }

    public void extentPass(String message){
        extentTest.pass(MediaEntityBuilder.createScreenCaptureFromPath("./img.png").build());
        extentTest.pass(message);
    }

    public void extentFail(String message){
        extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath("./img.png").build());
        extentTest.fail(message);
    }

    public void extentSkip(String message){
        extentTest.skip(MediaEntityBuilder.createScreenCaptureFromPath("./img.png").build());
        extentTest.skip(message);
    }

    public void flushReport(){
        extentReports.flush();
    }
}
