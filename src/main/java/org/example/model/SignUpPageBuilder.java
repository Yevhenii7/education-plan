package org.example.model;

import org.example.gui.pages.desktop.SignUpPage;
import org.openqa.selenium.WebDriver;

public class SignUpPageBuilder {

    private String password;
    private String day;
    private String month;
    private String year;
    private String firstName;
    private String lastName;
    private String address;
    private String country;
    private String state;
    private String city;
    private String zipCode;
    private String mobileNumber;

    public SignUpPageBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public SignUpPageBuilder withDateOfBirth(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
        return this;
    }

    public SignUpPageBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public SignUpPageBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public SignUpPageBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public SignUpPageBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public SignUpPageBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public SignUpPageBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public SignUpPageBuilder withZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public SignUpPageBuilder withMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public SignUpPage build(WebDriver driver) {
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.selectMaleSex();
        signUpPage.inputPassword(password);
        signUpPage.selectDateOfBirth(day, month, year);
        signUpPage.selectCheckboxNewsletter();
        signUpPage.selectCheckboxSubscribe();
        signUpPage.inputFirstName(firstName);
        signUpPage.inputLastName(lastName);
        signUpPage.inputAddress(address);
        signUpPage.selectCountry(country);
        signUpPage.inputState(state);
        signUpPage.inputCity(city);
        signUpPage.inputZipCode(zipCode);
        signUpPage.inputMobileNumber(mobileNumber);
        signUpPage.clickSubmit();
        return signUpPage;
    }
}
