package org.example.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement userNameField = $(By.name("username"));
    private SelenideElement passwordField = $(By.name("password"));
    private SelenideElement enterButton = $(By.xpath("//button[.='Войти']"));

    public SmsPage login(String login, String pass) {
        userNameField.setValue(login);
        passwordField.setValue(pass);
        enterButton.click();
        return new SmsPage();
    }
}
