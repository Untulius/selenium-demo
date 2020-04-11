package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
Добавить дополнительные тесты к тестам из задания 17.
1. На странице “Prompt, Alert and Confirm” получить, а затем ввести
полученный пароль в диалоговое окно ввода. Проверить, что после
успешного ввода пароля появляется сообщение «Great!» и кнопка «Return
to Menu». Перейти на главную страницу нажав на кнопку “Return to Menu”.
2. Написать негативный тест на первое задание.
3. На странице Table удалить и добавить несколько записей в таблицу.
Проверить, что после выполнения этих действий появляется кнопка “Return
to Menu”. Перейти на главную страницу нажав на кнопку “Return to Menu”.
 */
public class Lesson18 {
    private static final Logger LOG = LoggerFactory.getLogger(Lesson16.class);
    private WebDriver webDriver;

    @BeforeClass
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();
    }

    @Test
    public void lesson18() {
        webDriver.get("https://savkk.github.io/selenium-practice/");
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        webDriver.findElement(By.id("alerts")).click();
        webDriver.findElement(By.xpath("//button[.='Get password']")).click();
        Alert alert = webDriver.switchTo().alert();
        String alertText = alert.getText();
        String password = alertText.substring(alertText.lastIndexOf(" ") + 1);
        alert.accept();
        webDriver.findElement(By.xpath("//button[.='Enter password']")).click();
        Alert prompt = webDriver.switchTo().alert();
        prompt.sendKeys(password);
        prompt.accept();

        Assert.assertEquals(webDriver.findElement(By.xpath("//label[.='Great!']")).getText(), "Great!");
        Assert.assertTrue(webDriver.findElement(By.xpath("//button[.='Return to menu']")).isDisplayed());
        webDriver.findElement(By.xpath("//button[.='Return to menu']")).click();
        Alert confirm = webDriver.switchTo().alert();
        confirm.accept();

        //2. Написать негативный тест на первое задание:
        webDriver.findElement(By.id("alerts")).click();
        webDriver.findElement(By.xpath("//button[.='Get password']")).click();
        Alert alertNegativeTest = webDriver.switchTo().alert();
        alertNegativeTest.accept();
        webDriver.findElement(By.xpath("//button[.='Enter password']")).click();
        Alert promptNegativeTest = webDriver.switchTo().alert();
        promptNegativeTest.sendKeys("noValidPassword1234");
        promptNegativeTest.accept();

        Assert.assertFalse(isElementPresent("//label[.='Great!']"));
        Assert.assertFalse(isElementPresent("//button[.='Return to menu']"));

        //3.
        webDriver.navigate().back();
        webDriver.findElement(By.id("table")).click();
        deleteRow(5);
        deleteRow(3);
        deleteRow(1);

        addRow("Yandex", "Moscow", "Russia");
        addRow("Google", "Mountain View", "1600 Amphitheatre Parkway, Mountain View, CA 94043");

        WebElement linkReturnFromTable = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(linkReturnFromTable.getText(), "Great! Return to menu");
        linkReturnFromTable.click();
    }

    public void deleteRow(int id) {
        List<WebElement> checkbox = webDriver.findElements(By.xpath("//input[@type = 'checkbox'] "));
        checkbox.get(id).click();
        webDriver.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void addRow(String company, String contact, String country) {
        fillField("Company", company);
        fillField("Contact", contact);
        fillField("Country", country);
        webDriver.findElement(By.xpath("//input[@value='Add']")).click();
    }

    public void fillField(String fieldTitle, String inputText) {
        WebElement input = webDriver.findElement(By.xpath("//label[.='" + fieldTitle + "']/following::input"));
        input.sendKeys(inputText);
    }

    @AfterMethod
    public void closeDriver() {
        webDriver.quit();
    }

    public boolean isElementPresent(String locator) {
        try {
            webDriver.findElement(By.xpath(locator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
