package com.trivial;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestListener;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main implements ITestListener {
    ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest test;

    @BeforeSuite
    public void setup(){
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.SS").format(new Date());

        String reportName = "Test-Report-" + timestamp + ".html"; // Ensure proper file extension
        extentSparkReporter = new ExtentSparkReporter("./reports/" + reportName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
    }

    @Test
    public void test1(Method method){
        test = extentReports.createTest(method.getName()); // ✅ Create new ExtentTest instance
        test.info("Test started...");
        test.pass("Step 1: Successfully opened the application");
        test.pass("Step 2: Entered valid credentials");
        test.skip("Step 3: Login button failed to work");
        test.log(Status.PASS, "Step 4 ---- ");
        test.pass(MediaEntityBuilder.createScreenCaptureFromPath("img.png").build());
        test.info("Test ended.");
    }

    @Test
    public void test2(Method method){
        test = extentReports.createTest(method.getName()); // ✅ Separate ExtentTest
        test.info("Test started...");
        test.pass("Step 1: Successfully opened the application");
        test.pass("Step 2: Entered valid credentials");
        test.info("Test ended.");
    }

    @Test
    public void test3(Method method){
        test = extentReports.createTest(method.getName()); // ✅ Separate ExtentTest
        test.info("Test started...");
        test.pass("Step 1: Successfully opened the application");
        test.pass("Step 2: Entered valid credentials");
        test.fail("Step 3: Login button failed to work");
        test.info("Test ended.");
    }

    @AfterSuite
    public void teardown(){
        extentReports.flush(); // ✅ Flush to save the report
    }
}

