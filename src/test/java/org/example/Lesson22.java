package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.example.selenide.LoginPage;
import org.example.selenide.OverviewPage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

/*
Переделать автотест из предыдущего задания под Selenide
 */
public class Lesson22 {

    @BeforeMethod
    public void initDriver() {
        open("https://idemo.bspb.ru:");
    }

    @Test
    public void lesson22() {

        LoginPage loginPage = new LoginPage(WebDriverRunner.getWebDriver());
        loginPage
                .login("demo", "demo")
                .enterCode("0000")
                .selectTopMenu("overview")
                .moveCursor();

        Assert.assertTrue(WebDriverRunner.getWebDriver().getTitle().contains("Обзор"));
        OverviewPage overviewPage = new OverviewPage(WebDriverRunner.getWebDriver());
        Assert.assertEquals(overviewPage.getFinfreedom().getText(), "Финансовая свобода");

        String amountMoney = overviewPage.getAmount().getText();
        Assert.assertTrue(amountMoney.matches("\\d{0,3}\\s\\d{0,3}\\s\\d{1,3}\\.\\d{2}\\s."));

        String myAssets = overviewPage.getMyAssets().getText();
        Assert.assertTrue(myAssets.contains("Моих средств"));
        String amountMyAssets = myAssets.replaceAll("Моих средств ", "");
        Assert.assertTrue(amountMyAssets.matches("\\d{0,3}\\s\\d{0,3}\\s\\d{1,3}\\.\\d{2}\\s."));

        //$(title()).shouldHave(Condition.matchesText("Обзор"));
        //Assert.assertTrue(webDriver.getTitle().contains("Обзор"));

        //$(By.xpath("//div[@id='header-container']//span[@class='text']")).shouldHave(Condition.text("Финансовая свобода"));
        //WebElement finfreedom = webDriver.findElement(By.xpath("//div[@id='header-container']//span[@class='text']"));
        //Assert.assertEquals(finfreedom.getText(), "Финансовая свобода");

        //$(By.xpath("//div[@id='header-container']//span[@class='amount']")).should(Condition.visible);
        //WebElement amount = webDriver.findElement(By.xpath("//div[@id='header-container']//span[@class='amount']"));
        //Assert.assertTrue(amount.isDisplayed());

        //$(By.xpath("//div[@id='header-container']//small[@class='my-assets']/br")).should(Condition.enabled);
        //WebElement myAssets = webDriver.findElement(By.xpath("//div[@id='header-container']//small[@class='my-assets']/br"));
        //Assert.assertTrue(myAssets.isEnabled());

    }
}
