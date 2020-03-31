package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver webDriver;

    public  LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void login(String login, String pass){
        WebElement username =  webDriver.findElement(By.name("username"));
        username.clear();
        username.sendKeys(login);

        WebElement password =  webDriver.findElement(By.name("password"));
        password.clear();
        password.sendKeys(pass);

        webDriver.findElement(By.xpath("//button[.='Войти']")).click();
    }
}
