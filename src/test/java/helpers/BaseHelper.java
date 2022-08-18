package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class BaseHelper {
    protected static WebDriver driver = new ChromeDriver();
    protected static WebDriverWait wdWait = new WebDriverWait(driver, Duration.ofSeconds(20));
    protected static JavascriptExecutor js = (JavascriptExecutor) driver;
    protected static SoftAssert softAssert = new SoftAssert();
    protected static boolean firstTest = true;
    protected static int TIME_SEC = 0;

    public void sleep(int timeSec) {
        try {
            Thread.sleep(timeSec * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
