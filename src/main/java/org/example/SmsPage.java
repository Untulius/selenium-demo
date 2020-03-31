package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SmsPage {
    private final WebDriver webDriver;

    public SmsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void enterCode(String code) {
        webDriver.findElement(By.name("otpCode")).sendKeys(code);
        webDriver.findElement(By.id("login-otp-button")).click();
    }
}
