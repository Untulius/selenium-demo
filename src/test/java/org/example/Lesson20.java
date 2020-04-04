package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/*
Автоматизировать следующий кейс на сайте https://idemo.bspb.ru:
№ Действие Ожидаемый результат
1. Войти в систему под учетной записью demo /demo.
Отображается форма двухфакторнаой аутентификации
2. Ввести в поле ввода кода подтверждения 0000.Нажать кнопку войти.
Осуществлён вход в систему
3. Перейти через верхнее меню в раздел Обзор. Открылась страница «Обзор».
На странице отображается блок «Финансовая свобода» с указанием суммы в формате “123 456 789.00 ₽”
4. Навести курсор на сумму в блоке «Финансовая свобода»
Появляется надпись: «Моих средств» с указанием суммы в формате “123 456 789.00 ₽”
В выполнении задания использовать паттерн PageObjects
 */
public class Lesson20 {
    private WebDriver webDriver;

    @BeforeClass
    public void downloadDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void initDriver() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://idemo.bspb.ru:");
    }

    @Test
    public void lesson20() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("demo", "demo");
        WebElement smsForm = webDriver.findElement(By.id("login-form"));
        Assert.assertTrue(smsForm.isEnabled());

        SmsPage smsPage = new SmsPage(webDriver);
        smsPage.enterCode("0000");
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://idemo.bspb.ru/welcome");

        OverviewPage overviewPage = new OverviewPage(webDriver);
        overviewPage.getTopMenu().selectTopMenu("overview");

        Assert.assertTrue(webDriver.getTitle().contains("Обзор"));
        WebElement finfreedom = webDriver.findElement(By.xpath("//div[@id='header-container']//span[@class='text']"));
        Assert.assertEquals(finfreedom.getText(), "Финансовая свобода");

        WebElement amount = webDriver.findElement(By.xpath("//div[@id='header-container']//span[@class='amount']"));
        Assert.assertTrue(amount.isDisplayed());

        WebElement myAssets = webDriver.findElement(By.xpath("//div[@id='header-container']//small[@class='my-assets']/br"));
        overviewPage.moveCursor();
        Assert.assertTrue(myAssets.isEnabled());
    }

    @AfterMethod
    public void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}