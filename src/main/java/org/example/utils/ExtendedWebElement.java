package org.example.utils;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.constant.ConfigConstant.PAGE_ELEMENT_TO;

public class ExtendedWebElement {

    private final static Logger LOGGER = LogManager.getLogger(ExtendedWebElement.class);

    private WebElement webElement;

    private final WebDriverWait wait;

    private final WebDriver driver;

    private final ElementLocator locator;

    private String formatLocatorStr;

    private final FindBy byAnnotation;

    public ExtendedWebElement(ElementLocator locator, WebDriver driver, FindBy annotation) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(PAGE_ELEMENT_TO));
        this.formatLocatorStr = getLocatorStr(locator);
        this.locator = locator;
        this.driver = driver;
        this.byAnnotation = annotation;
    }

    public ExtendedWebElement(WebElement element, ElementLocator locator, WebDriver driver, FindBy annotation) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(PAGE_ELEMENT_TO));
        this.formatLocatorStr = getLocatorStr(locator);
        this.locator = locator;
        this.driver = driver;
        this.byAnnotation = annotation;
        this.webElement = element;
    }

    private By getByFromAnnotation(FindBy annotation, String locatorForm) {
        if (!annotation.id().isEmpty()) {
            return By.id(locatorForm);
        }
        if (!annotation.xpath().isEmpty()) {
            return By.xpath(locatorForm);
        }
        if (!annotation.name().isEmpty()) {
            return By.name(locatorForm);
        }
        if (!annotation.css().isEmpty()) {
            return By.cssSelector(locatorForm);
        }
        if (!annotation.className().isEmpty()) {
            return By.className(locatorForm);
        }
        if (!annotation.linkText().isEmpty()) {
            return By.linkText(locatorForm);
        }
        if (!annotation.partialLinkText().isEmpty()) {
            return By.partialLinkText(locatorForm);
        }
        if (!annotation.tagName().isEmpty()) {
            return By.tagName(locatorForm);
        }
        return null;
    }

    private String getLocatorStr(ElementLocator locator) {
        String[] parts = locator.toString().split(":");
        String res = parts[1].trim();
        return res.substring(0, res.length() - 1);
    }

    private String getByFromLocator(ElementLocator locator) {
        return locator.toString().split("'")[1].split(":")[0];
    }

    public void format(Object... args) {
        formatLocatorStr = String.format(getLocatorStr(locator), args);
    }

    public void click() {
        try {
            waitForClickable();
            webElement.click();
            LOGGER.info(getName() + " :element is clicked");
        } catch (Exception e) {
            LOGGER.error(getName() + " :element is not clickable");
            throw e;
        }
    }

    public boolean clickIfPresent(int timeout) {
        boolean present = this.isDisplayed(timeout);
        if (present) {
            this.click();
        }

        return present;
    }

    public void submit() {
        try {
            waitForClickable();
            webElement.submit();
            LOGGER.info(getName() + " :is submitted");
        } catch (Exception e) {
            LOGGER.error(getName() + " :element is not intractable");
            throw e;
        }
    }

    public void clear() {
        try {
            waitForClickable();
            webElement.clear();
            LOGGER.info(getName() + " :is cleared");
        } catch (Exception e) {
            LOGGER.error(getName() + " :element is not intractable");
            throw e;
        }
    }

    public String getTagName() {
        isDisplayed();
        return webElement.getTagName();
    }

    private void waitForClickable() {
        if (webElement == null) {
            webElement = wait.until(ExpectedConditions.elementToBeClickable(getBy()));
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        }
    }

    public void type(String text) {
        try {
            waitForClickable();
            webElement.sendKeys(text);
            LOGGER.info(getName() + " :text is typed:" + text);
        } catch (Exception e) {
            LOGGER.error(getName() + " :element is not intractable");
            throw e;
        }
    }

    public void secretType(String text) {
        try {
            waitForClickable();
            webElement.sendKeys(text);
            LOGGER.info(getName() + " :text is typed:" + "*******");
        } catch (Exception e) {
            LOGGER.error(getName() + " :element is not intractable");
            throw e;
        }
    }

    private boolean isDisplayed(WebDriverWait wait) {
        try {
            if (webElement == null) {
                webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(getBy()));
            } else {
                wait.until(ExpectedConditions.visibilityOf(webElement));
            }
            LOGGER.info(getName() + " :element is displayed");
            return true;
        } catch (Exception e) {
            LOGGER.info(getName() + " :element is not displayed");
        }
        return false;
    }

    public boolean isDisplayed() {
        return isDisplayed(this.wait);
    }

    public boolean isDisplayed(int timeoutInMillis) {
        WebDriverWait waitInner = new WebDriverWait(driver, Duration.ofMillis(timeoutInMillis));
        return isDisplayed(waitInner);
    }

    private boolean isElementDisappeared(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    private boolean isDisappeared(WebDriverWait wait) {
        try {
            wait.until(driver -> isElementDisappeared(getBy()));
            LOGGER.info(getName() + " :element is disappeared");
            return true;
        } catch (Exception e) {
            LOGGER.info(getName() + " :element is not disappeared");
        }
        return false;
    }

    public boolean isDisappeared() {
        return isDisappeared(wait);
    }

    public boolean isDisappeared(int timeoutInMillis) {
        WebDriverWait waitInner = new WebDriverWait(driver, Duration.ofMillis(timeoutInMillis));
        return isDisappeared(waitInner);
    }

    public Point getLocation() {
        isDisplayed();
        return webElement.getLocation();
    }

    public Dimension getSize() {
        isDisplayed();
        return webElement.getSize();
    }

    public Rectangle getRect() {
        isDisplayed();
        return webElement.getRect();
    }

    public String getCssValue(String propertyName) {
        isDisplayed();
        return webElement.getCssValue(propertyName);
    }

    public WebElement getElement() {
        if (webElement == null) {
            try {
                isDisplayed();
            } catch (NoSuchElementException e) {
                LOGGER.info(e);
            }
        }
        return webElement;
    }

    public String getText() {
        isDisplayed();
        return webElement.getText();
    }

    public String getAttribute(String name) {
        isDisplayed();
        return webElement.getAttribute(name);
    }


    public boolean isSelected() {
        isDisplayed();
        return webElement.isSelected();
    }

    public boolean isEnabled() {
        isDisplayed();
        return webElement.isEnabled();
    }

    public By getBy() {
        return getByFromAnnotation(byAnnotation, formatLocatorStr);
    }

    public String getName() {
        return getByFromLocator(locator) + " " + formatLocatorStr;
    }
}