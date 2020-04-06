package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.example.selenide.LoginPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

/*
Переделать автотест из предыдущего задания под Selenide
 */
public class Lesson22 {

    @Test
    public void lesson22() {
        open("https://idemo.bspb.ru:");

        LoginPage loginPage = new LoginPage(WebDriverRunner.getWebDriver());
        loginPage
                .login("demo", "demo")
                .enterCode("0000")
                .selectTopMenu("overview")
                .moveCursor();

        $(title()).shouldHave(Condition.matchesText("Обзор"));
        //Assert.assertTrue(webDriver.getTitle().contains("Обзор"));

        $(By.xpath("//div[@id='header-container']//span[@class='text']")).shouldHave(Condition.text("Финансовая свобода"));
        //WebElement finfreedom = webDriver.findElement(By.xpath("//div[@id='header-container']//span[@class='text']"));
        //Assert.assertEquals(finfreedom.getText(), "Финансовая свобода");

        $(By.xpath("//div[@id='header-container']//span[@class='amount']")).should(Condition.visible);
        //WebElement amount = webDriver.findElement(By.xpath("//div[@id='header-container']//span[@class='amount']"));
        //Assert.assertTrue(amount.isDisplayed());

        $(By.xpath("//div[@id='header-container']//small[@class='my-assets']/br")).should(Condition.enabled);
        //WebElement myAssets = webDriver.findElement(By.xpath("//div[@id='header-container']//small[@class='my-assets']/br"));
        //Assert.assertTrue(myAssets.isEnabled());
    }
}
