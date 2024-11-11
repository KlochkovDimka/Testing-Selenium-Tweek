package org.example.baseAction;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

abstract public class BaseSeleniumTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        this.driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("screenshots/screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BaseSeleniumPage.setDriver(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
