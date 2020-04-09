package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.selenide.LoginPage;
import org.example.selenide.OverviewPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

/*
Настроить Allure в проекте из предыдущего задания. Проставить над
методами с бизнес шагами аннотацию @Step
 */
@Epic("https://idemo.bspb.ru")
@Feature("Страница логина")
public class Lesson23 {

    @BeforeMethod
    public void initDriver() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.timeout = 5_000;
        Selenide.open("https://idemo.bspb.ru");

    }

    @Story("Вход в систему")
    @Description("Проверка входа в систему")
    @Owner("Victor Untulius")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void loginWrongTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("дршлиджшиж", "qwfwevw");
        $(withText("Неверные данные")).shouldBe(Condition.visible);
    }

    @Story("СМС-авторизация")
    @Description("Проверка ввода СМС кода")
    @Owner("Victor Untulius")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void smsWrongTest() {
        LoginPage loginPage = new LoginPage();
        loginPage
                .login("demo", "demo")
                .enterCode("1234");
        $(withText("Неверный код")).shouldBe(Condition.visible);
    }

    @Test
    public void lesson23() {

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
