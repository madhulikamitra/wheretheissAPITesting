package com.automation.iss.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        dryRun = false,
   monochrome = true,

        plugin = { "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","pretty"},
        tags = "@Regression",
        features={"src/test/resources"},
        glue={"com.automation.iss"}


)


public class TestRunner {
}
