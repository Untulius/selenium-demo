package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Lesson16 {
    private WebDriver webDriver;

    @BeforeMethod
    public void initDriver(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @Test
    public void lesson16(){
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
