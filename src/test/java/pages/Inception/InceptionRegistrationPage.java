package pages.Inception;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InceptionRegistrationPage extends BaseHelper {
    WebDriver driver;
    @FindBy(id = "firstName")
    WebElement FIRST_NAME_INPUT;
    @FindBy(id = "lastName")
    WebElement LAST_NAME_INPUT;
    @FindBy(className = "button-base")
    WebElement CREATE_ACCOUNT_BUTTON;
    @FindBy(className = "MuiInputBase-root")
    List<WebElement> LIST_INPUT_FIELDS;

    public InceptionRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void enterName(String name) {
        wdWait.until(ExpectedConditions.visibilityOf(FIRST_NAME_INPUT));
        FIRST_NAME_INPUT.sendKeys(name);
    }

    private void enterLastName(String lastName) {
        LAST_NAME_INPUT.sendKeys(lastName);
    }

    private void enterJMBG(String jmbg) {
        LIST_INPUT_FIELDS.get(2).findElement(By.tagName("input")).sendKeys(jmbg);
    }

    private void enterEmail(String email) {
        LIST_INPUT_FIELDS.get(4).findElement(By.tagName("input")).sendKeys(email);
    }

    private void enterPassword(String password) {
        LIST_INPUT_FIELDS.get(5).findElement(By.tagName("input")).sendKeys(password);
    }

    private void reEnterPassword(String password) {
        LIST_INPUT_FIELDS.get(6).findElement(By.tagName("input")).sendKeys(password);
    }

    private void clickButtonCreateUser() {
        CREATE_ACCOUNT_BUTTON.click();
    }
    
    public String getErrorRegistrationMessage() {
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div/div[1]/div[2]")));

        WebElement errMsgField = driver.findElement(By.className("Toastify__toast-body")).findElements(By.tagName("div")).get(1);
        return errMsgField.getText();
    }

    public void registrationWithInvalidPassword(String name, String lastName, String jmbg, String email, String password) {
        enterName(name);
        enterLastName(lastName);
        enterJMBG(jmbg);
        enterEmail(email);
        enterPassword(password);
        reEnterPassword(password);
        clickButtonCreateUser();
    }


}