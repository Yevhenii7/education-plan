package org.example.gui.components;

import org.example.gui.pages.common.AbstractPage;
import org.example.utils.ExtendedWebElement;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import static org.example.constant.ConfigConstant.PAGE_ELEMENT_TO;

public class WeValuePrivacyAd extends AbstractPage {

    @FindBy(id = "card")
    private ExtendedWebElement okBtn;

    public WeValuePrivacyAd(WebDriver driver) {
        super(driver);
    }

    public void closeAdIfPresent() {
        okBtn.clickIfPresent(PAGE_ELEMENT_TO);
    }
}
