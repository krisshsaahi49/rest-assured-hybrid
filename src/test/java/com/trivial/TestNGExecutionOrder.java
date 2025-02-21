package com.trivial;

import org.testng.annotations.*;

public class TestNGExecutionOrder {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("1️⃣ @BeforeSuite → Runs before the entire suite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("2️⃣ @BeforeTest → Runs before <test> block in testng.xml");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("3️⃣ @BeforeClass → Runs before the first test method in the class");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("4️⃣ @BeforeMethod → Runs before every @Test method");
    }

    @Test
    public void testMethod1() {
        System.out.println("➡ @Test (testMethod1) → Actual test execution");
    }

    @Test
    public void testMethod2() {
        System.out.println("➡ @Test (testMethod2) → Actual test execution");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("5️⃣ @AfterMethod → Runs after every @Test method");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("6️⃣ @AfterClass → Runs after all test methods in the class");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("7️⃣ @AfterTest → Runs after all test methods in a <test> block");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("8️⃣ @AfterSuite → Runs after the entire suite execution");
    }
}
