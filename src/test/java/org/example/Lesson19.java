package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/*
Добавить дополнительные автотесты к тестам из предыдущих заданий.
1. Дополнить все ранее написанные тесты, проверкой, что после
правильного выполнения каждого задания в cookie появляется запись с
названием – ‘button’, ‘alerts’, ‘table’ и т.п. и значением ‘done’.
 */
public class Lesson19 {
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
    public void lesson19() throws IOException {
        webDriver.get("https://savkk.github.io/selenium-practice/");
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        //Lesson16:
        webDriver.findElement(By.id("button")).click();
        webDriver.findElement(By.id("first")).click();

        WebElement labelExcellent = webDriver.findElement(By.xpath("//label[.='Excellent!']"));
        Assert.assertEquals(labelExcellent.getText(), "Excellent!");

        WebElement button = webDriver.findElement(By.className("button-primary"));
        Assert.assertEquals(button.getAttribute("value"), "Click me too!");
        button.click();

        WebElement link = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(link.getText(), "Great! Return to menu");
        link.click();

        webDriver.findElement(By.id("checkbox")).click();

        WebElement checkBoxOne = webDriver.findElement(By.id("one"));
        checkBoxOne.click();
        webDriver.findElement(By.id("go")).click();
        WebElement labelResult = webDriver.findElement(By.id("result"));
        Assert.assertEquals(labelResult.getText(), checkBoxOne.getAttribute("value"));

        WebElement radioBoxThree = webDriver.findElement(By.id("radio_three"));
        radioBoxThree.click();
        webDriver.findElement(By.id("radio_go")).click();
        WebElement labelRadioResult = webDriver.findElement(By.id("radio_result"));
        Assert.assertEquals(labelRadioResult.getText(), radioBoxThree.getAttribute("value"));

        WebElement linkReturn = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(linkReturn.getText(), "Great! Return to menu");
        linkReturn.click();


        //Lesson17:
        webDriver.findElement(By.id("select")).click();
        WebElement heroElement = webDriver.findElement(By.name("hero"));
        Select heroSelect = new Select(heroElement);
        heroSelect.selectByVisibleText("Frederick Phillips Brooks, Jr.");

        WebElement languagesElement = webDriver.findElement(By.name("languages"));
        Select languagesSelect = new Select(languagesElement);
        languagesSelect.selectByValue("Java");
        languagesSelect.selectByValue("C++");
        languagesSelect.selectByValue("Pascal");

        webDriver.findElement(By.id("go")).click();
        WebElement languagesResult = webDriver.findElement(By.xpath("//select[@name='hero']/following-sibling::label"));
        Assert.assertEquals(languagesResult.getText(), "Frederick Phillips Brooks, Jr.");

        WebElement heroResult = webDriver.findElement(By.xpath("//select[@name='languages']/following-sibling::label"));
        Assert.assertEquals(heroResult.getText(), "Java, C++, Pascal");

        WebElement linkReturnFromSelect = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(linkReturnFromSelect.getText(), "Great! Return to menu");
        linkReturnFromSelect.click();

        webDriver.findElement(By.id("form")).click();
        fillField("First Name:", "Ivan");
        fillField("Last Name:", "Petrov");
        fillField("Email:", "ivan.petrov@yandex.ru");
        fillField("Address:", "Moscow, The Red Square, 1");

        webDriver.findElement(By.xpath("//input[@name='sex']")).click();

        webDriver.findElement(By.tagName("textarea")).sendKeys("London is the capital of Great Britain");

        Path testFile1 = Files.createFile(Paths.get("src\\HelloWorld.txt"));
        webDriver.findElement(By.xpath("//input[@type='file']")).sendKeys(testFile1.toAbsolutePath().toString());
        Files.delete(testFile1);

        webDriver.findElement(By.xpath("//input[@type='submit']")).click();
        WebElement linkReturnFromForm = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(linkReturnFromForm.getText(), "Great! Return to menu");
        linkReturnFromForm.click();

        webDriver.findElement(By.id("iframe")).click();
        webDriver.switchTo().frame("code-frame");
        String codeText = webDriver.findElement(By.id("code")).getText();
        String code = codeText.substring(codeText.lastIndexOf(" ") + 1);
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.name("code")).sendKeys(code);
        webDriver.findElement(By.name("ok")).click();

        WebElement linkReturnFromIFrame = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(linkReturnFromIFrame.getText(), "Great! Return to menu");
        linkReturnFromIFrame.click();

        //Lesson18:
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

        webDriver.navigate().back();
        webDriver.findElement(By.id("table")).click();
        webDriver.findElement(By.xpath("//table/tbody/tr[3]/td/input")).click();
        webDriver.findElement(By.xpath("//input[@value='Delete']")).click();
        fillField("Company", "Yandex");
        fillField("Contact", "Moscow");
        fillField("Country", "Russia");
        webDriver.findElement(By.xpath("//input[@value='Add']")).click();
        fillField("Company", "Google");
        fillField("Contact", "Moscow");
        fillField("Country", "1600 Amphitheatre Parkway, Mountain View, CA 94043");
        webDriver.findElement(By.xpath("//input[@value='Add']")).click();
        WebElement linkReturnFromTable = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(linkReturnFromTable.getText(), "Great! Return to menu");
        linkReturnFromTable.click();
    }

    @AfterMethod
    public void closeDriver() {
        webDriver.quit();
    }

    public void fillField(String fieldTitle, String inputText) {
        WebElement input = webDriver.findElement(By.xpath("//label[.='" + fieldTitle + "']/following-sibling::input"));
        input.sendKeys(inputText);
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
