package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SmsPage {
    private final WebDriver webDriver;
    private By codeField = By.name("otpCode");
    private By loginButton = By.id("login-otp-button");

    public SmsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterCode(String code) {
        webDriver.findElement(codeField).sendKeys(code);
        webDriver.findElement(loginButton).click();
    }
}
