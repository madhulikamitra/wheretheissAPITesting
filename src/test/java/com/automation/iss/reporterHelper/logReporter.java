package com.automation.iss.reporterHelper;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.Scenario;
import org.junit.Assert;

public class logReporter {


    public static void logInfo(Scenario scenario, String message,String status){

        if(scenario!=null && message!=null){
            scenario.log(message);
        }

        ExtentReports reports=new ExtentReports();
        ExtentTest test=reports.createTest(String.valueOf(scenario));

        switch (status)
        {
            case "PASS":
                test.pass(message);
                break;

            case "WARN":
                test.warning(message);
                break;

            case "FAIL":
                Assert.assertTrue("There was a problem in execution",1==0);
                test.fail(message);
                break;
        }
    }



}
