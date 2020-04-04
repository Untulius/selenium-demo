package org.example.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPagePF {
    private final WebDriver webDriver;

    @FindBy(name = "username")
    private WebElement userNameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[.='Войти']")
    private WebElement enterButton;

    public LoginPagePF(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public SmsPagePF login(String login, String pass) {
        userNameField.clear();
        userNameField.sendKeys(login);

        passwordField.clear();
        passwordField.sendKeys(pass);

        enterButton.click();

        return new SmsPagePF(webDriver);
    }
}
