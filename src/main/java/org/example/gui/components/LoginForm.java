package org.example.gui.components;

import org.example.gui.pages.desktop.HomePage;
import org.example.gui.pages.desktop.SignUpPage;
import org.example.services.UserService;
import org.example.utils.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends AbstractComponent {

    @FindBy(xpath = "//div[@class='login-form']/h2[text()='Login to your account']")
    private ExtendedWebElement loginToAccountTitle;

    @FindBy(xpath = "//input[@name='name']")
    private ExtendedWebElement nameField;

    @FindBy(xpath = "//input[@data-qa='login-email']")
    private ExtendedWebElement emailLoginField;

    @FindBy(xpath = "//input[@name='password']")
    private ExtendedWebElement passwordField;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    private ExtendedWebElement logInButton;

    @FindBy(xpath = "(//p)[1]")
    private ExtendedWebElement errorMessage;


    public LoginForm(SearchContext searchContext, WebDriver driver) {
        super(searchContext, driver);
    }

    public void inputLoginEmail(String email) {
        emailLoginField.type(email);
    }


    public void inputPassword(String password) {
        passwordField.type(password);
    }

    public HomePage logInButtonClick() {
        logInButton.click();
        return new HomePage(getDriver());
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
