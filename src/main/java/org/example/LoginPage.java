package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver webDriver;
    private By userNameField = By.name("username");
    private By passwordField = By.name("password");
    private By enterButton = By.xpath("//button[.='Войти']");

    public  LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void login(String login, String pass){
        webDriver.findElement(userNameField).clear();
        webDriver.findElement(userNameField).sendKeys(login);

        webDriver.findElement(passwordField).clear();
        webDriver.findElement(passwordField).sendKeys(pass);

        webDriver.findElement(enterButton).click();
    }
}
