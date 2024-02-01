package org.example.gui.pages.common;

import org.example.enums.PageOpeningStrategy;
import org.example.utils.ExtendedFieldDecorator;
import org.example.utils.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class AbstractPage {

    private final WebDriver driver;
    private String url;
    private ExtendedWebElement element;
    private PageOpeningStrategy pageOpeningStrategy;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new ExtendedFieldDecorator(new DefaultElementLocatorFactory(driver), driver), this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void setPageOpeningStrategy(PageOpeningStrategy pageOpeningStrategy) {
        this.pageOpeningStrategy = pageOpeningStrategy;
    }

    protected void setElement(ExtendedWebElement element) {
        this.element = element;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    public boolean isPageOpened() {
        switch (pageOpeningStrategy) {
            case BY_ELEMENT:
                return element.isDisplayed();
            case BY_URL:
                return driver.getCurrentUrl().equals(url);
            case BY_URL_AND_ELEMENT:
                return element.isDisplayed() && driver.getCurrentUrl().equals(url);
            default:
                throw new IllegalArgumentException("Invalid page opening strategy");
        }
    }

    public void open() {
        getDriver().get(url);
    }
}
