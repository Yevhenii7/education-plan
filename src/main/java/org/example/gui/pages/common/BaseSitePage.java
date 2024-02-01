package org.example.gui.pages.common;

import org.example.gui.components.Header;
import org.example.gui.components.WeValuePrivacyAd;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class BaseSitePage extends AbstractPage {

    @FindBy(xpath = "//header[contains(@class,'page')]")
    private Header header;

    public BaseSitePage(WebDriver driver) {
        super(driver);
    }

    public WeValuePrivacyAd getWeValuePrivacyAd() {
        return new WeValuePrivacyAd(getDriver());
    }

    public Header getHeaderMenu() {
        return header;
    }

}
