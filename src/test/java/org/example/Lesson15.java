package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Lesson15 {
    private WebDriver webDriver;

    @BeforeMethod
    public void initDriver(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @Test
    public void lesson15(){
        webDriver.get("https://yandex.ru/");
        //webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement input = webDriver.findElement(By.name("text"));
        input.sendKeys("руддщ цкщдв");
        WebElement button = webDriver.findElement(By.className("search2__button"));
        button.click();

       // WebElement input2 = webDriver.findElement(By.xpath("//span[.='hello world']"));
        //Assert.assertEquals(input2.getText(), "hello world");
        WebElement title = webDriver.findElement(By.tagName("title"));

    }

    @AfterMethod
    public void closeDriver(){
        webDriver.quit();
    }

}
