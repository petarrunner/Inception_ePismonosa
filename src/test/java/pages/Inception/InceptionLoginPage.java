package pages.Inception;

import helpers.BaseHelper;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class InceptionLoginPage extends BaseHelper {
    WebDriver driver;
    private static final String WEBSITE_URL = "https://test-app.epismonosa.rs";
    @FindBy(className = "login-radio-large")
    List<WebElement> LOGIN_OPTIONS;
    @FindBy(className = "MuiFormControl-root")
    List<WebElement> LOGIN_FORM;
    @FindBy(className = "button-base")
    WebElement LOGIN_BUTTON;
    @FindBy(className = "MuiAlert-message")
    WebElement ERROR_MESSAGE;
    @FindBy(className = "link-text")
    WebElement FORGOTTEN_PASSWORD_LINK;
    @FindBy(className = "MuiInputBase-inputMarginDense")
    WebElement CHANGE_PASSWORD_EMAIL_INPUT;
    @FindBy(className = "dialog-actions")
    List<WebElement> CHANGE_PASSWORD_BUTTONS;
    @FindBy(className = "py-2")
    WebElement CHANGE_PASSWORD_CONFIRM_MESSAGE;
    @FindBy(className = "clickable")
    WebElement EYE_ICON;
    @FindBy(className = "MuiOutlinedInput-inputAdornedEnd")
    WebElement PASSWORD_INPUT;
    @FindBy(className = "MuiTypography-body1")
    WebElement NEW_REGISTRATION_FIELD;

    public InceptionLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void navigationToHomePage() {
        driver.get(WEBSITE_URL);
    }

    private void loginWithUsernameAndPassword() {
        js.executeScript("arguments[0].click();", LOGIN_OPTIONS.get(0));
    }

    private void enterUsername(String username) {
        wdWait.until(ExpectedConditions.visibilityOf(LOGIN_FORM.get(0)));
        LOGIN_FORM.get(0).findElement(By.tagName("input")).sendKeys(username);
    }

    private void enterPassword(String password) {
        LOGIN_FORM.get(1).findElement(By.tagName("input")).sendKeys(password);
    }

    private void clickLoginButton() {
        LOGIN_BUTTON.click();
    }

    private void pressEnterKey() {
        LOGIN_BUTTON.sendKeys(Keys.RETURN);
    }

    private void clearInputUsername() {
        LOGIN_FORM.get(0).findElement(By.tagName("input")).clear();
    }

    public boolean checkUrlChanged(String text) {
        return driver.getCurrentUrl().contains(text);
    }

    public String getTextErrorMessage() {
        wdWait.until(ExpectedConditions.visibilityOf(ERROR_MESSAGE));
        return ERROR_MESSAGE.getText();
    }

    private void clickButtonForgottenPassword() {
        wdWait.until(ExpectedConditions.visibilityOf(FORGOTTEN_PASSWORD_LINK));
        FORGOTTEN_PASSWORD_LINK.click();

    }

    private void enterEmailAddress(String email) {
        wdWait.until(ExpectedConditions.visibilityOf(CHANGE_PASSWORD_EMAIL_INPUT));
        CHANGE_PASSWORD_EMAIL_INPUT.sendKeys(email);
    }

    private void clickConfirmButton() {
        CHANGE_PASSWORD_BUTTONS.get(0).findElement(By.tagName("button")).click();
    }

    public String getForgottenPasswordMessageText() {
        wdWait.until(ExpectedConditions.visibilityOf(CHANGE_PASSWORD_CONFIRM_MESSAGE));
        return CHANGE_PASSWORD_CONFIRM_MESSAGE.getText();
    }

    public void clickEyeIcon() {
        EYE_ICON.click();
    }

    public boolean checkPasswordType() {
        String passwordType = PASSWORD_INPUT.getAttribute("type");
        String eyeIconType = EYE_ICON.getAttribute("data-icon");
        return ((passwordType.equals("password") && eyeIconType.equals("eye")) || (passwordType.equals("text") && eyeIconType.equals("eye-slash")));
    }

    private void clickNewRegistration() {
        wdWait.until(ExpectedConditions.visibilityOf(NEW_REGISTRATION_FIELD));
        NEW_REGISTRATION_FIELD.findElement(By.tagName("a")).click();
    }

    public boolean checkH1Text(@NotNull String h1TextExpected) {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h1")));
        String h1Text = driver.findElement(By.tagName("h1")).getText();
        return h1TextExpected.equals(h1Text);

    }

    public void validLogin(String username, String password) {
        navigationToHomePage();
        loginWithUsernameAndPassword();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void validLoginPressEnter(String username, String password) {
        navigationToHomePage();
        loginWithUsernameAndPassword();
        enterUsername(username);
        enterPassword(password);
        pressEnterKey();
    }

    public void loginWithUsernameField(String username) {
        navigationToHomePage();
        loginWithUsernameAndPassword();
        enterUsername(username);
        clickLoginButton();
    }

    public void loginWithPasswordField(String password) {
        clearInputUsername();
        enterPassword(password);
        clickLoginButton();
    }


    public void checkForgottenPasswordFunctionality(String email) {
        navigationToHomePage();
        loginWithUsernameAndPassword();
        clickButtonForgottenPassword();
        enterEmailAddress(email);
        clickConfirmButton();

    }

    public void checkPasswordVisibility(String password) {
        navigationToHomePage();
        loginWithUsernameAndPassword();
        enterPassword(password);
    }

    public void navigateToHomePageAndGoTORegistrationPage() {
        navigationToHomePage();
        clickNewRegistration();
    }
}
