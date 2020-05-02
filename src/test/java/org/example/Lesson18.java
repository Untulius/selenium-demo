package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static final Logger LOG = LoggerFactory.getLogger(Lesson18.class);
    private WebDriver webDriver;

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
        Assert.assertEquals(linkReturn.getText(), "Great! Return to menu", "Текст ссылки не “Great! Return to menu”");
        linkReturn.click();
    }

    @Test
    public void selectPageTest() {
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
        Assert.assertEquals(languagesResult.getText(), "Frederick Phillips Brooks, Jr.", "Отображаемое значение не соответствует выбранному");

        WebElement heroResult = webDriver.findElement(By.xpath("//select[@name='languages']/following-sibling::label"));
        Assert.assertEquals(heroResult.getText(), "Java, C++, Pascal", "Отображаемые значения не соответствуют выбранным");

        WebElement linkReturnFromSelect = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(linkReturnFromSelect.getText(), "Great! Return to menu", "Текст ссылки не “Great! Return to menu”");
        linkReturnFromSelect.click();
    }

    @Test
    public void formPageTest() throws IOException {
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
        Assert.assertEquals(linkReturnFromForm.getText(), "Great! Return to menu", "Текст ссылки не “Great! Return to menu”");
        linkReturnFromForm.click();
    }

    @Test
    public void iFramePageTest() {
        webDriver.findElement(By.id("iframe")).click();
        webDriver.switchTo().frame("code-frame");
        String codeText = webDriver.findElement(By.id("code")).getText();
        String code = codeText.substring(codeText.lastIndexOf(" ") + 1);
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.name("code")).sendKeys(code);
        webDriver.findElement(By.name("ok")).click();

        WebElement linkReturnFromIFrame = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(linkReturnFromIFrame.getText(), "Great! Return to menu", "Текст ссылки не “Great! Return to menu”");
        linkReturnFromIFrame.click();
    }

    @Test
    public void alertsTest() {
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

        Assert.assertEquals(webDriver.findElement(By.xpath("//label[.='Great!']")).getText(), "Great!", "Текст сообщения не \"Great!\"");
        Assert.assertTrue(webDriver.findElement(By.xpath("//button[.='Return to menu']")).isDisplayed(), "Не отображается кнопка 'Return to menu'");
        webDriver.findElement(By.xpath("//button[.='Return to menu']")).click();
        Alert confirm = webDriver.switchTo().alert();
        confirm.accept();
    }

    @Test
    public void alertsNegativeTest() {
        webDriver.findElement(By.id("alerts")).click();
        webDriver.findElement(By.xpath("//button[.='Get password']")).click();
        Alert alertNegativeTest = webDriver.switchTo().alert();
        alertNegativeTest.accept();
        webDriver.findElement(By.xpath("//button[.='Enter password']")).click();
        Alert promptNegativeTest = webDriver.switchTo().alert();
        promptNegativeTest.sendKeys("noValidPassword1234");
        promptNegativeTest.accept();

        Assert.assertFalse(isElementPresent("//label[.='Great!']"), "Присутствует элемент с текстом 'Great!'");
        Assert.assertFalse(isElementPresent("//button[.='Return to menu']"), "Отображается кнопка 'Return to menu'");
        webDriver.navigate().back();
    }

    @Test
    public void tablePageTest() {
        webDriver.findElement(By.id("table")).click();
        deleteRow(5);
        deleteRow(3);
        deleteRow(1);

        addRow("Yandex", "Moscow", "Russia");
        addRow("Google", "Mountain View", "1600 Amphitheatre Parkway, Mountain View, CA 94043");

        WebElement linkReturnFromTable = webDriver.findElement(By.xpath("//a[.='Great! Return to menu']"));
        Assert.assertEquals(linkReturnFromTable.getText(), "Great! Return to menu", "Текст ссылки не “Great! Return to menu”");
        linkReturnFromTable.click();
    }

    private void deleteRow(int id) {
        List<WebElement> checkbox = webDriver.findElements(By.xpath("//input[@type = 'checkbox'] "));
        checkbox.get(id).click();
        webDriver.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    private void addRow(String company, String contact, String country) {
        fillField("Company", company);
        fillField("Contact", contact);
        fillField("Country", country);
        webDriver.findElement(By.xpath("//input[@value='Add']")).click();
    }

    private void fillField(String fieldTitle, String inputText) {
        WebElement input = webDriver.findElement(By.xpath("//label[.='" + fieldTitle + "']/following::input"));
        input.sendKeys(inputText);
    }

    private boolean isElementPresent(String locator) {
        try {
            webDriver.findElement(By.xpath(locator));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @AfterClass
    public void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
