package org.example.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SmsPagePF {
    private final WebDriver webDriver;

    @FindBy(name = "otpCode")
    private WebElement codeField;

    @FindBy(id = "login-otp-button")
    private WebElement loginButton;

    public SmsPagePF(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public TopMenuPF enterCode(String code) {
        codeField.sendKeys(code);
        loginButton.click();
        return new TopMenuPF(webDriver);
    }
}
