package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pagefactory.LoginPagePF;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/*
Переделать автотест и PageObjects классы из задания № 6 под
Page Factory и Fluent Interface паттерны
 */
public class Lesson21 {
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
    public void lesson21() {
        LoginPagePF loginPagePF = new LoginPagePF(webDriver);
        loginPagePF
                .login("demo", "demo")
                .enterCode("0000")
                .selectTopMenu("overview")
                .moveCursor();

        Assert.assertTrue(webDriver.getTitle().contains("Обзор"));
        WebElement finfreedom = webDriver.findElement(By.xpath("//div[@id='header-container']//span[@class='text']"));
        Assert.assertEquals(finfreedom.getText(), "Финансовая свобода");

        WebElement amount = webDriver.findElement(By.xpath("//div[@id='header-container']//span[@class='amount']"));
        Assert.assertTrue(amount.isDisplayed());

        WebElement myAssets = webDriver.findElement(By.xpath("//div[@id='header-container']//small[@class='my-assets']/br"));
        Assert.assertTrue(myAssets.isEnabled());
    }

    @AfterMethod
    public void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
