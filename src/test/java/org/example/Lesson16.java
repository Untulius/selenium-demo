package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Lesson16 {
    private WebDriver webDriver;

    @BeforeClass
    public void downloadDriver(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver(){
        webDriver = new ChromeDriver();
    }

    @Test
    public void lesson16(){
        webDriver.get("https://savkk.github.io/selenium-practice/");


    }

    @AfterMethod
    public void closeDriver(){
        webDriver.quit();
    }
}
