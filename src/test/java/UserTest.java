import org.apache.commons.lang3.RandomStringUtils;
import org.example.gui.pages.desktop.HomePage;
import org.example.gui.pages.desktop.LoginPage;
import org.example.gui.pages.desktop.SignUpPage;
import org.example.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.example.constant.ProjectConstant.ERROR_MESSAGE;
import static org.example.constant.ProjectConstant.INVALID_EMAIL;
import static org.example.constant.ProjectConstant.INVALID_PASSWORD;
import static org.example.constant.ProjectConstant.SIGN_UP_FORM_TITLE;
import static org.example.constant.ProjectConstant.TITLE_ACCOUNT_INFORMATION;
import static org.example.constant.ProjectConstant.VALID_EMAIL;
import static org.example.constant.ProjectConstant.VALID_NAME;
import static org.example.constant.ProjectConstant.VALID_PASSWORD;

public class UserTest extends AbstractTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserTest.class);

    @Test
    public void verifyRegisterUser() {
        UserService user = new UserService();
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        LOGGER.info(" automation exercise page is opened");
        Assert.assertTrue(homePage.isPageOpened(), "Page is not opened");
        LoginPage loginPage = homePage.getHeaderMenu().clickButtonLogin();
        String upFormTitle = loginPage.getSignUpFormTitle(SIGN_UP_FORM_TITLE);
        Assert.assertEquals(upFormTitle, SIGN_UP_FORM_TITLE, "New User Signup! is not match");
        SignUpPage signUpPage = loginPage.signIn(user);
        Assert.assertTrue(signUpPage.isTitleAccountInfoPresent(TITLE_ACCOUNT_INFORMATION), "ENTER ACCOUNT INFORMATION is not visible");
        signUpPage.completeSignUpForm(user);
        Assert.assertTrue(signUpPage.isAccountCreatedTitlePresent());
    }

    @Test(dataProvider = "registerUserData")
    public void verifyRegisterUserDataProvider(String name, String email) {
        UserService user = new UserService();
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        LOGGER.info(" automation exercise page is opened");
        Assert.assertTrue(homePage.isPageOpened(), "Page is not opened");
        LoginPage loginPage = homePage.getHeaderMenu().clickButtonLogin();
        String upFormTitle = loginPage.getSignUpFormTitle(SIGN_UP_FORM_TITLE);
        Assert.assertEquals(upFormTitle, SIGN_UP_FORM_TITLE, "New User Signup! is not match");
        SignUpPage signUpPage = loginPage.signIn(name, email);
        Assert.assertTrue(signUpPage.isTitleAccountInfoPresent(TITLE_ACCOUNT_INFORMATION), "ENTER ACCOUNT INFORMATION is not visible");
        signUpPage.completeSignUpForm(user);
        Assert.assertTrue(signUpPage.isAccountCreatedTitlePresent());
    }

    @Test(dataProvider = "loginDataProvider")
    public void verifyUserLogin(String email, String password, boolean expectSuccess, String errorMessage) {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page doesn't open");

        LoginPage loginPage = homePage.getHeaderMenu().clickButtonLogin();
        Assert.assertTrue(loginPage.isLoginToAccountTitlePresent(), "Title doesn't present");
        homePage = loginPage.logIn(email, password);
        if (expectSuccess) {
            Assert.assertTrue(homePage.getHeaderMenu().getUserName().contains(VALID_NAME), "logged in as username is not visible");
        } else {
            Assert.assertEquals(loginPage.loginFailed(), errorMessage, "Login verification failed");
        }
    }

    @DataProvider(name = "registerUserData")
    public Object[][] getRegisterUserData() {
        Object[][] testData = new Object[10][2];
        for (int i = 0; i < 3; i++) {
            String randomFirstName = generateRandomString(4);
            String randomEmail = generateRandomEmail();

            testData[i] = new Object[]{randomFirstName, randomEmail};
        }
        return testData;
    }

    private String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    private String generateRandomEmail() {
        String randomName = generateRandomString(6);
        return randomName + "@example.com";
    }

    @DataProvider(name = "loginDataProvider")
    public Object [][] loginDataProvider() {
        return new Object[][] {
                {VALID_EMAIL, VALID_PASSWORD, true, ""},
                {INVALID_EMAIL, VALID_PASSWORD, false, ERROR_MESSAGE},
                {VALID_EMAIL, INVALID_PASSWORD, false, ERROR_MESSAGE}
        };
    }

}
