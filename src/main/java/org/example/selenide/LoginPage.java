package org.example.selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement userNameField = $(By.name("username"));
    private SelenideElement passwordField = $(By.name("password"));
    private SelenideElement enterButton = $(By.xpath("//button[.='Войти']"));

    @Step("Вход в систему {login} / {pass}")
    public SmsPage login(String login, String pass) {
        userNameField.setValue(login);
        passwordField.setValue(pass);
        enterButton.click();
        return new SmsPage();
    }
}
