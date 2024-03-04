package org.example.gui.components;

import org.example.gui.pages.desktop.SignUpPage;
import org.example.services.UserService;
import org.example.utils.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SignUpForm extends AbstractComponent {

    @FindBy(xpath = ".//input[@data-qa='signup-name']")
    private ExtendedWebElement signupNameField;

    @FindBy(xpath = ".//input[@data-qa='signup-email']")
    private ExtendedWebElement signupEmailField;

    @FindBy(xpath = ".//button[@data-qa='signup-button']")
    private ExtendedWebElement signUpButton;

    @FindBy(xpath = ".//div[@class='signup-form']/h2[text()='%s']")
    private ExtendedWebElement signUpFormTitle;

    public SignUpForm(SearchContext searchContext, WebDriver driver) {
        super(searchContext, driver);
    }

    public void inputName(String name) {
        if (isSignUpNamePresent()) {
            signupNameField.type(name);
        }
    }

    public boolean isSignUpNamePresent() {
        return signupEmailField.isDisplayed();
    }

    public void inputSignUpEmail(String email) {
        if (isSignUpEmailPresent()) {
            signupEmailField.type(email);
        }
    }

    public boolean isSignUpEmailPresent() {
        return signupEmailField.isDisplayed();
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

    public SignUpPage signUpButtonClick() {
        if (isSignUpButtonPresent()) {
            signUpButton.click();
        }
        return new SignUpPage(getDriver());
    }

    public boolean isSignUpButtonPresent() {
        return signUpButton.isDisplayed();
    }

    public String getSignUpFormTitle(String formTitle) {
        signUpFormTitle.format(formTitle);
        return signUpFormTitle.getText();
    }

}
