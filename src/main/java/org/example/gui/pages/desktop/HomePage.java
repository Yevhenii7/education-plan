package org.example.gui.pages.desktop;

import org.example.enums.PageOpeningStrategy;
import org.example.gui.pages.common.BaseSitePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BaseSitePage {

    public HomePage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_URL);
        setUrl("https://www.automationexercise.com/");
    }

}
