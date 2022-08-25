package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Inception.InceptionLoginPage;
import pages.Inception.InceptionMainPage;
import pages.Inception.InceptionRegistrationPage;

import java.util.Arrays;
import java.util.List;

public class InceptionTest extends BaseTest {
    public static final String USERNAME = "petar";
    public static final String LASTNAME = "Vlatkovic";
    public static final String PASSWORD = "#PetarI2022";
    public static final String INVALID_PASSWORD = "###";
    public static final String FULLNAME = "Petar Vlatkovic";
    public static final String EMAIL = "petarvlatkovic25@gmail.com";
    public static final String JMBG = "1234567891234";
    public static final String INVALID_EMAIL = " petar@google.com";

    @Test
    public void loginWithValidCredentials_F1() {
        InceptionLoginPage loginPage = new InceptionLoginPage(driver);
        InceptionMainPage mainPage = new InceptionMainPage(driver);
        loginPage.validLogin(USERNAME, PASSWORD);

        softAssert.assertTrue(mainPage.checkSuccessfullyLogin(FULLNAME), "Login is unsuccessful. There isn't a text contains user's full name.");
        softAssert.assertTrue(mainPage.checkIfUserBoxIsDisplayed(), "There isn't field with users information.");
        softAssert.assertAll();
    }

    @Test
    public void loginWithInvalidPassword_F2() {
        InceptionLoginPage loginPage = new InceptionLoginPage(driver);
        loginPage.validLogin(USERNAME, INVALID_PASSWORD);

        softAssert.assertTrue(loginPage.checkH1Text("Prijava na ePismonoša portal"), "There isn't an error message");
        softAssert.assertTrue(driver.getCurrentUrl().contains("login"), "There isn't an error message");
        softAssert.assertAll();
    }

    @Test
    public void loginWithOneEmptyField_F3() {
        InceptionLoginPage loginPage = new InceptionLoginPage(driver);
        loginPage.loginWithUsernameField(USERNAME);
        softAssert.assertTrue(driver.findElement(By.tagName("h1")).getText().contains("Prijava na ePismonoša portal"));
        softAssert.assertTrue(loginPage.checkUrlChanged("login"), "Url changed after login with empty username field.");

        loginPage.loginWithPasswordField(INVALID_PASSWORD);
        softAssert.assertTrue(driver.findElement(By.tagName("h1")).getText().contains("Prijava na ePismonoša portal"));
        softAssert.assertTrue(loginPage.checkUrlChanged("login"), "Url changed after login with empty password field.");
        softAssert.assertAll();
    }

    @Test
    public void checkForgottenPasswordFunctionality_F4() {
        InceptionLoginPage loginPage = new InceptionLoginPage(driver);
        loginPage.checkForgottenPasswordFunctionality(EMAIL);

        Assert.assertEquals(loginPage.getForgottenPasswordMessageText(), "Na navedenu mail adresu poslat je link za promenu lozinke", "GRESKA");
    }

    @Test
    public void checkErrorMessagesWithInvalidLogin_F5() {
        InceptionLoginPage loginPage = new InceptionLoginPage(driver);
        loginPage.validLogin(USERNAME, INVALID_PASSWORD);

        Assert.assertEquals(loginPage.getTextErrorMessage(), "Pogrešni kredencijali", "There isn't an error message with text `Pogrešni kredencijali`.");
    }

    @Test
    public void checkPasswordVisibility_F6() {
        InceptionLoginPage loginPage = new InceptionLoginPage(driver);
        loginPage.checkPasswordVisibility(PASSWORD);

        for (int i = 0; i < 5; i++) {
            loginPage.clickEyeIcon();
            softAssert.assertTrue(loginPage.checkPasswordType(), "Password visibility is not working.");
        }
        softAssert.assertAll();
    }

    @Test
    public void checkLoginEnterFunctionality_F9() {
        InceptionLoginPage loginPage = new InceptionLoginPage(driver);
        InceptionMainPage mainPage = new InceptionMainPage(driver);
        loginPage.validLoginPressEnter(USERNAME, PASSWORD);

        Assert.assertTrue(mainPage.checkIfUserBoxIsDisplayed(), "There isn't field with users information.");
    }

    @Test
    public void verifyInvalidRegistrationWithoutUpperCaseLowerCaseNumberAndSymbol_NF1() {
        List<String> passwords = Arrays.asList("#petar123", "#PETAR123", "#Petar", "Petar1234");

        InceptionLoginPage loginPage = new InceptionLoginPage(driver);
        InceptionRegistrationPage registrationPage = new InceptionRegistrationPage(driver);

        loginPage.navigateToHomePageAndGoTORegistrationPage();

        for (String password : passwords) {
            registrationPage.registrationWithInvalidPassword(USERNAME, LASTNAME, JMBG, INVALID_EMAIL, password);

            softAssert.assertEquals(registrationPage.getErrorRegistrationMessage(),
                    "Lozinka ne zadovoljava kriterijum od minimum 6 karaktera, bar jedno veliko slovo i jedan broj.",
                    "Password >> " + password + " << is valid password.");
            driver.navigate().refresh();
        }
        softAssert.assertAll();
    }

    @Test
    public void verifyInvalidRegistrationWithValidPasswordWithLessThanSixCharacters_NF2() {
        String shortPassword = "#Pv1";

        InceptionLoginPage loginPage = new InceptionLoginPage(driver);
        InceptionRegistrationPage registrationPage = new InceptionRegistrationPage(driver);

        loginPage.navigateToHomePageAndGoTORegistrationPage();

        registrationPage.registrationWithInvalidPassword(USERNAME, LASTNAME, JMBG, INVALID_EMAIL, shortPassword);

        Assert.assertEquals(registrationPage.getErrorRegistrationMessage(), "Lozinka ne zadovoljava kriterijum od minimum 6 karaktera, bar jedno veliko slovo i jedan broj.", "Valid password.");
    }

    @Test
    public void verifyInvalidBackFunctionalityAfterLogOut_NF3() {
        String h1Text = "Prijava na ePismonoša portal";

        InceptionLoginPage loginPage = new InceptionLoginPage(driver);
        InceptionMainPage mainPage = new InceptionMainPage(driver);
        loginPage.validLogin(USERNAME, PASSWORD);

        softAssert.assertTrue(mainPage.checkSuccessfullyLogin(FULLNAME), "Login is unsuccessful. Wrong username or password is entered");
        mainPage.logOut();
        softAssert.assertTrue(loginPage.checkH1Text(h1Text), "Current page is not a login page. There isn't text `Prijava na ePismonoša portal`");
        driver.navigate().back();
        softAssert.assertTrue(driver.getCurrentUrl().contains("login"), "Current page is not a login page. Current url doesn't contain word `login`");
        softAssert.assertAll();
    }
}