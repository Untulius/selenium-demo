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

/*
Написать тест, который:
1. Откроет в браузере https://yandex.ru
2. Введет в строке поиска «руддщ цкщдв»
3. Нажмет на кнопку «Найти»
4. Проверит, что строка поиска заполнена значением “hello world”, а название окна (вкладки)
браузера содержит “hello world”
Требования:
Maven проект с зависимостями Selenium WebDriver и TestNG последних стабильных версий. Для
инициализации и закрытия браузера должны использоваться фикстуры тестового фреймворка.
 */
public class Lesson15 {
    private WebDriver webDriver;

    @BeforeMethod
    public void initDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        webDriver = new ChromeDriver();
    }

    @Test
    public void lesson15() {
        webDriver.get("https://yandex.ru/");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement input1 = webDriver.findElement(By.name("text"));
        input1.sendKeys("руддщ цкщдв");

        WebElement button = webDriver.findElement(By.className("search2__button"));
        button.click();

        String inputText = webDriver.findElement(By.xpath("//span/input[@name='text']")).getAttribute("value");
        Assert.assertEquals(inputText, "hello world");

        Assert.assertTrue(webDriver.getTitle().contains("hello world"));
    }

    @AfterMethod
    public void closeDriver() {
        webDriver.quit();
    }

}
