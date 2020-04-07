package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.example.selenide.LoginPage;
import org.example.selenide.OverviewPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
Переделать автотест из предыдущего задания под Selenide
 */
public class Lesson22 {

    @BeforeMethod
    public void initDriver() {
        Selenide.open("https://idemo.bspb.ru");
    }

    @Test
    public void lesson22() {

        LoginPage loginPage = new LoginPage();
        loginPage
                .login("demo", "demo")
                .enterCode("0000")
                .selectTopMenu("overview")
                .moveCursor();

        Assert.assertTrue(Selenide.title().contains("Обзор"));
        OverviewPage overviewPage = new OverviewPage();
        overviewPage.getFinfreedom().shouldHave(Condition.text("Финансовая свобода"));
        overviewPage.getAmount().should(Condition.matchText("\\d{0,3}\\s\\d{0,3}\\s\\d{1,3}\\.\\d{2}\\s."));
        overviewPage.getMyAssets().shouldHave(Condition.text("Моих средств"));
        overviewPage.getMyAssets().should(Condition.matchText("\\d{0,3}\\s\\d{0,3}\\s\\d{1,3}\\.\\d{2}\\s."));

    }
}
