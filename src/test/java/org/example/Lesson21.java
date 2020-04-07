package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pagefactory.LoginPagePF;
import org.example.pagefactory.OverviewPagePF;
import org.openqa.selenium.WebDriver;
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
        webDriver.get("https://idemo.bspb.ru");
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
        OverviewPagePF overviewPagePF = new OverviewPagePF(webDriver);
        Assert.assertEquals(overviewPagePF.getFinfreedom().getText(), "Финансовая свобода");

        String amountMoney = overviewPagePF.getAmount().getText();
        Assert.assertTrue(amountMoney.matches("\\d{0,3}\\s\\d{0,3}\\s\\d{1,3}\\.\\d{2}\\s."));

        String myAssets = overviewPagePF.getMyAssets().getText();
        Assert.assertTrue(myAssets.contains("Моих средств"));
        String amountMyAssets = myAssets.replaceAll("Моих средств ", "");
        Assert.assertTrue(amountMyAssets.matches("\\d{0,3}\\s\\d{0,3}\\s\\d{1,3}\\.\\d{2}\\s."));
    }

    @AfterMethod
    public void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}
