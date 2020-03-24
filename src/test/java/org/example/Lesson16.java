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

/*
На сайте https://savkk.github.io/selenium-practice/ в автотесте автоматизировать следующие действия:
1. На странице “Button” нажать на кнопку “CLICK ME!”, проверить, что появились текст «Excellent!» и
кнопка “CLICK ME TOO!”.
2. Нажать на кнопку “CLICK ME TOO!”. Проверить, что появилась ссылка с текстом “Great! Return to
menu” и нажать на неё.
3. На странице “Checkboxes” выбрать один или несколько из представленных чек-боксов и нажать на
кнопку “GET RESULTS” под ними. Проверить, что появился текст, соответствующий атрибуту value
из выделенных чек-боксов.
4. На той же странице выбрать любую радио кнопку и нажать кнопку “GET RESULTS”, находящуюся
под ними. Проверить, что появился текст, соответствующий значению атрибута value, выбеленной
кнопки.
5. Проверить, что появилась ссылка с текстом “Great! Return to menu” и нажать на неё.
Инициализация и закрытие вебдравера при помощи фикстур.
 */
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
