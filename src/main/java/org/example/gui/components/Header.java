package org.example.gui.components;

import org.example.gui.pages.desktop.LoginPage;
import org.example.utils.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Header extends AbstractComponent {

    @FindBy(xpath = "//div[@class='logo pull-left']/a/img[contains(@alt, '%s')]")
    private ExtendedWebElement logo;

    @FindBy(xpath = "//a[contains(text(),'Sign In')]")
    private ExtendedWebElement signInButton;

    @FindBy(xpath = "//a[contains(@href, '/login')]")
    private ExtendedWebElement buttonLogin;

    @FindBy(xpath = "//a[contains(., 'Logged in as')]/b")
    private ExtendedWebElement userName;


    public Header(SearchContext searchContext, WebDriver driver) {
        super(searchContext, driver);
    }

    public String getTextLogo(String logoTitle) {
        logo.format(logoTitle);
        return logo.getText();
    }

    public LoginPage clickButtonLogin() {
        buttonLogin.click();
        return new LoginPage(getDriver());
    }

    public String getUserName() {
       return userName.getText();
    }

}
