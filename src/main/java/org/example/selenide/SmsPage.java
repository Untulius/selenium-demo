package org.example.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SmsPage {

    private SelenideElement codeField = $(By.name("otpCode"));
    private SelenideElement loginButton = $(By.id("login-otp-button"));

    public TopMenu enterCode(String code) {
        codeField.setValue(code);
        loginButton.click();
        return new TopMenu();
    }
}
