package com.GBHSR.testRunner;
 
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
 
@CucumberOptions(
    features = "src/test/resources/features/login.feature",
    glue = {"com.GBHSR.stepDefinition","com.GBHSR.hooks"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
 
}