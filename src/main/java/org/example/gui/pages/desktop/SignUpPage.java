package org.example.gui.pages.desktop;

import org.example.gui.pages.common.AbstractPage;
import org.example.services.UserService;
import org.example.utils.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static org.example.constant.ProjectConstant.ADDRESS;
import static org.example.constant.ProjectConstant.CITY;
import static org.example.constant.ProjectConstant.COUNTRY;
import static org.example.constant.ProjectConstant.MOBILE_NUMBER;
import static org.example.constant.ProjectConstant.VALID_PASSWORD;
import static org.example.constant.ProjectConstant.STATE;
import static org.example.constant.ProjectConstant.ZIP_CODE;

public class SignUpPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='login-form']//b[text()='%s']")
    private ExtendedWebElement titleAccountInformation;

    @FindBy(id = "id_gender1")
    private ExtendedWebElement maleRadioButton;

    @FindBy(id = "password")
    private ExtendedWebElement passwordField;

    @FindBy(id = "days")
    private ExtendedWebElement day;

    @FindBy(id = "months")
    private ExtendedWebElement month;

    @FindBy(id = "years")
    private ExtendedWebElement year;

    @FindBy(id = "newsletter")
    private ExtendedWebElement newsletterCheckbox;

    @FindBy(id = "optin")
    private ExtendedWebElement subscribeCheckbox;

    @FindBy(id = "first_name")
    private ExtendedWebElement firstName;

    @FindBy(id = "last_name")
    private ExtendedWebElement lastName;

    @FindBy(id = "address1")
    private ExtendedWebElement address;

    @FindBy(id = "country")
    private ExtendedWebElement country;

    @FindBy(id = "state")
    private ExtendedWebElement state;

    @FindBy(id = "city")
    private ExtendedWebElement city;

    @FindBy(id = "zipcode")
    private ExtendedWebElement zipcode;

    @FindBy(id = "mobile_number")
    private ExtendedWebElement mobileNumber;
    @FindBy(xpath = "(//button[@type='submit'])[1]")
    private ExtendedWebElement submitButton;

    @FindBy(xpath = "//h2[@class='title text-center']//b[text()='Account Created!']")
    private ExtendedWebElement accountCreatedTitle;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTitleAccountInfoPresent(String title) {
        titleAccountInformation.format(title);
        return titleAccountInformation.isDisplayed();
    }

    public void selectMaleSex() {
        maleRadioButton.click();
    }

    public void inputPassword(String pass) {
        passwordField.type(pass);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        selectDay(day);
        selectMonth(month);
        selectYears(year);
    }

    public void selectDay(String days) {
        Select daySelect = new Select(day.getElement());
        daySelect.selectByVisibleText(days);
    }

    public void selectMonth(String months) {
        Select monthSelect = new Select(month.getElement());
        monthSelect.selectByVisibleText(months);
    }

    public void selectYears(String years) {
        Select yearSelect = new Select(year.getElement());
        yearSelect.selectByVisibleText(years);
    }

    public void selectCheckboxNewsletter() {
        newsletterCheckbox.click();
    }

    public void selectCheckboxSubscribe() {
        subscribeCheckbox.click();
    }

    public void inputFirstName(String name) {
        firstName.type(name);
    }

    public void inputLastName(String name) {
        lastName.type(name);
    }

    public void inputAddress(String addressName) {
        address.type(addressName);
    }

    public void selectCountry(String countries) {
        Select countrySelect = new Select(country.getElement());
        countrySelect.selectByVisibleText(countries);
    }

    public void inputState(String typeOfState) {
        state.type(typeOfState);
    }

    public void inputCity(String cityType) {
        city.type(cityType);
    }

    public void inputZipCode(String zipCode) {
        zipcode.type(zipCode);
    }

    public void inputMobileNumber(String mobileNum) {
        mobileNumber.type(mobileNum);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public boolean isAccountCreatedTitlePresent() {
      return accountCreatedTitle.isDisplayed();
    }

    public void completeSignUpForm(UserService user) {
        selectMaleSex();
        inputPassword(VALID_PASSWORD);
        selectDateOfBirth("27", "March", "2021");
        selectCheckboxNewsletter();
        selectCheckboxSubscribe();
        inputFirstName(user.getUser().getName());
        inputLastName(user.getUser().getLastName());
        inputAddress(ADDRESS);
        selectCountry(COUNTRY);
        inputState(STATE);
        inputCity(CITY);
        inputZipCode(ZIP_CODE);
        inputMobileNumber(MOBILE_NUMBER);
        clickSubmit();
    }
}
