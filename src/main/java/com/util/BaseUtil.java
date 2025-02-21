package com.util;

import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class BaseUtil implements ITestListener {
    LogUtil logUtil;
    ExtentReportUtil extentReportUtil;
    ExcelUtil excelUtil;

    public void onTestStart(ITestResult result) {
        extentReportUtil.createTest(result.getName());
        logUtil.logInfo("Test started : "+result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        extentReportUtil.extentPass("Test passed: "+result.getName());
        logUtil.logInfo("Test passed: "+result.getName());
    }

    public void onTestFailure(ITestResult result) {
        extentReportUtil.extentFail("Test failed: "+result.getName());
        logUtil.logError("Test failed: "+result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        extentReportUtil.extentSkip("Test skipped: "+result.getName());
        logUtil.logInfo("Test skipped: "+result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void onStart(ITestContext context) {
        logUtil = LogUtil.getInstance();
        extentReportUtil = ExtentReportUtil.getInstance();
        try {
            excelUtil = ExcelUtil.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void onFinish(ITestContext context) {
        try {
            excelUtil.closeExcel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentReportUtil.flushReport();
    }

}
