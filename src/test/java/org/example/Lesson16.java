package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

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
под ними. Проверить, что появился текст, соответствующий значению атрибута value, выделенной
кнопки.
5. Проверить, что появилась ссылка с текстом “Great! Return to menu” и нажать на неё.
Инициализация и закрытие вебдравера при помощи фикстур.
 */
public class Lesson16 {
    private static final Logger LOG = LoggerFactory.getLogger(Lesson16.class);
    private WebDriver webDriver;

    @BeforeClass
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get("https://savkk.github.io/selenium-practice/");
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void buttonPageTest() {
        webDriver.findElement(By.id("button")).click();
        webDriver.findElement(By.id("first")).click();

        WebElement labelExcellent = webDriver.findElement(By.xpath("//label[.='Excellent!']"));
        Assert.assertEquals(labelExcellent.getText(), "Excellent!", "Не появился текст «Excellent!»");

        WebElement button = webDriver.findElement(By.className("button-primary"));
        Assert.assertEquals(button.getAttribute("value"), "Click me too!", "Не появилась кнопка \"Click me too!\"");
        button.click();

        WebElement link = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(link.getText(), "Great! Return to menu", "Текст ссылки не “Great! Return to menu”");
        link.click();
    }

    @Test
    public void checkBoxesPageTest() {
        webDriver.findElement(By.id("checkbox")).click();

        WebElement checkBoxOne = webDriver.findElement(By.id("one"));
        checkBoxOne.click();
        webDriver.findElement(By.id("go")).click();
        WebElement labelResult = webDriver.findElement(By.id("result"));
        Assert.assertEquals(labelResult.getText(), checkBoxOne.getAttribute("value"), "Текст не соответствует атрибуту value из выделенных чек-боксов");

        WebElement radioBoxThree = webDriver.findElement(By.id("radio_three"));
        radioBoxThree.click();
        webDriver.findElement(By.id("radio_go")).click();
        WebElement labelRadioResult = webDriver.findElement(By.id("radio_result"));
        Assert.assertEquals(labelRadioResult.getText(), radioBoxThree.getAttribute("value"), "Текст не соответствует атрибуту value выделенной radio button");

        WebElement linkReturn = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(linkReturn.getText(), "Great! Return to menu",  "Текст ссылки не “Great! Return to menu”");
        linkReturn.click();
    }

    @AfterClass
    public void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
