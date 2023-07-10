package erpDemoTest.testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = "erpDemoTest.stepDefs",
        tags = "@regression",
        plugin = {"pretty",
                "html:target/cucumber.html",
                "json:target/cucumber.json",
                "junit:target/cukes.xml",
                "rerun:target.rerun.txt"}
)

public class testRunner extends AbstractTestNGCucumberTests {
}
