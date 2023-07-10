package erpDemoTest.stepDefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Hooks {
    public static WebDriver driver;
    @Before
    public void openBrowser() {
        //1- Start the driver
        driver = new ChromeDriver();
        //3- Configurations
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //4- Navigate to
        driver.navigate().to("https://edu-hazem.odoo.com/web/login");
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}