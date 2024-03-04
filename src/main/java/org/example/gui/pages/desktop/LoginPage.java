package org.example.gui.pages.desktop;

import org.example.gui.components.LoginForm;
import org.example.gui.components.SignUpForm;
import org.example.gui.pages.common.BaseSitePage;
import org.example.services.UserService;
import org.example.utils.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BaseSitePage {

    @FindBy(xpath = "//div[@class='login-form']")
    private LoginForm loginForm;

    @FindBy(xpath = "//div[@class='signup-form']")
    private SignUpForm signupForm;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginForm getLoginForm() {
        return loginForm;
    }

    public SignUpForm getSignUpForm() {
        return signupForm;
    }
}
