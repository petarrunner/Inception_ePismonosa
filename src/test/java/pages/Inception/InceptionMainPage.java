package pages.Inception;

import helpers.BaseHelper;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InceptionMainPage extends BaseHelper {
    WebDriver driver;

    public InceptionMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void clickProfileIcon() {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("user-box"))).click();
    }

    private void clickLogOutButton() {
        driver.findElement(By.xpath("//*[text()='Odjavi se']")).click();

    }

    public boolean checkSuccessfullyLogin(@NotNull String fullName) {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("user-info")));
        String fullNameProfile = driver.findElement(By.className("user-info")).getText();
        return fullNameProfile.contains(fullName);
    }

    public void logOut() {
        clickProfileIcon();
        clickLogOutButton();
    }
}
