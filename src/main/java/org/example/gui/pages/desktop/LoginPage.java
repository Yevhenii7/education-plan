package org.example.gui.pages.desktop;

import org.example.gui.pages.common.BaseSitePage;
import org.example.services.UserService;
import org.example.utils.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseSitePage {

    @FindBy(xpath = "//div[@class='signup-form']/h2[text()='%s']")
    private ExtendedWebElement signUpForm;

    @FindBy(xpath = "//div[@class='login-form']/h2[text()='Login to your account']")
    private ExtendedWebElement loginToAccountTitle;

    @FindBy(xpath = "//input[@name='name']")
    private ExtendedWebElement nameField;

    @FindBy(xpath = "(//input[@name='email'])[1]")
    private ExtendedWebElement emailLoginField;

    @FindBy(xpath = "(//input[@name='email'])[2]")
    private ExtendedWebElement emailSignUpField;

    @FindBy(xpath = "//input[@name='password']")
    private ExtendedWebElement passwordField;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    private ExtendedWebElement signUpButton;

    @FindBy(xpath = "(//button[@type='submit'])[1]")
    private ExtendedWebElement logInButton;

    @FindBy(xpath = "(//p)[1]")
    private ExtendedWebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getSignUpFormTitle(String formTitle) {
        signUpForm.format(formTitle);
        return signUpForm.getText();
    }

    public void inputName(String name) {
        nameField.type(name);

    }

    public void inputSignUpEmail(String email) {
        emailSignUpField.type(email);
    }

    public void inputLoginEmail(String email) {
        emailLoginField.type(email);
    }


    public void inputPassword(String password) {
        passwordField.type(password);
    }

    public SignUpPage signUpButtonClick() {
        signUpButton.click();
        return new SignUpPage(getDriver());
    }

    public HomePage logInButtonClick() {
        logInButton.click();
        return new HomePage(getDriver());
    }

    public SignUpPage signIn(UserService user) {
        inputName(user.getUser().getName());
        inputSignUpEmail(user.getUser().getEmail());
        return signUpButtonClick();
    }

    public SignUpPage signIn(String name, String email) {
        inputName(name);
        inputSignUpEmail(email);
        return signUpButtonClick();
    }

    public boolean isLoginToAccountTitlePresent() {
        return loginToAccountTitle.isDisplayed();
    }

    public HomePage logIn(String email, String password) {
        inputLoginEmail(email);
        inputPassword(password);
        return logInButtonClick();
    }

    public String loginFailed() {
        return errorMessage.getText();
    }
}
