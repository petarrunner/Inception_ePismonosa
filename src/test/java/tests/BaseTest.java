package tests;

import helpers.BaseHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.time.Duration;


public class BaseTest extends BaseHelper {
    @BeforeMethod
    public void testInit() {
        if (!firstTest) {
            driver = new ChromeDriver();
            wdWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            js = (JavascriptExecutor) driver;
        }
        firstTest = false;
        softAssert = new SoftAssert();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void testTearDown() {
        driver.quit();
    }

}