package org.example.cucumber.stepdefenitions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Затем;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.example.selenide.LoginPage;
import org.example.selenide.OverviewPage;
import org.example.selenide.SmsPage;
import org.example.selenide.TopMenu;
import org.openqa.selenium.By;
import org.testng.Assert;

/*
Переделать автотест из 8-го задания блока «Автоматизации тестирования вебприложений» с использованием библиотеки Cucumber
 */

public class BspbRuStepdefs {
    @Дано("пользователь переходит на сайт {string}")
    public void пользовательПереходитНаСайт(String arg0) {
        Selenide.open(arg0);
    }

    @Тогда("открывается форрма ввода логина и пароля")
    public void открываетсяФоррмаВводаЛогинаИПароля() {
        Selenide.$(By.xpath("//form[@action='/auth/login']")).shouldBe(Condition.visible);
    }

    @Затем("пользователь входит в систему под учетной записью {string} {string}")
    public void пользовательВходитВСистемуПодУчетнойЗаписью(String arg0, String arg1) {
        LoginPage loginPage = new LoginPage();
        loginPage
                .login(arg0, arg1);
    }

    @Тогда("отображается форма двухфакторнаой аутентификации")
    public void отображаетсяФормаДвухфакторнаойАутентификации() {
        Selenide.$(By.xpath("//form[@action='/auth/otp']")).shouldBe(Condition.visible);
    }

    @Затем("пользователь вводит в поле ввода код подтверждения {string} и нажимает кнопку \"Войти\"")
    public void пользовательВводитВПолеВводаКодПодтвержденияИНажимаетКнопку(String arg0) {
        SmsPage smsPage = new SmsPage();
        smsPage.enterCode(arg0);
    }

    @Тогда("осуществляется вход в систему")
    public void осуществляетсяВходВСистему() {
        Assert.assertTrue(Selenide.title().contains("Старт - Интернет банк"));
    }

    @Затем("пользователь переходит через верхнее меню в раздел \"Обзор\"")
    public void пользовательПереходитЧерезВерхнееМенюВРазделОбзор() {
        TopMenu topMenu = new TopMenu();
        topMenu.selectTopMenu("overview");
    }

    @Тогда("открывается страница {string}")
    public void открываетсяСтраницаОбзор(String arg0) {
        Assert.assertTrue(Selenide.title().contains("Обзор"));
    }

    @И("на странице отображается блок {string} с указанием суммы в формате \"123 456 789.00 ₽\"")
    public void наСтраницеОтображаетсяБлокСУказаниемСуммыВФормате(String arg0) {
        OverviewPage overviewPage = new OverviewPage();
        overviewPage.getFinfreedom().shouldHave(Condition.text(arg0));
        overviewPage.getAmount().should(Condition.matchText("\\d{0,3}\\s\\d{0,3}\\s\\d{1,3}\\.\\d{2}\\s."));
    }

    @Затем("пользователь наводит курсор на сумму в блоке \"Финансовая свобода\"")
    public void пользовательНаводитКурсорНаСуммуВБлокеФинансоваяСвобода() {
        OverviewPage overviewPage = new OverviewPage();
        overviewPage.moveCursor();
    }

    @Тогда("появляется надпись {string} с указанием суммы в формате \"123 456 789.00 ₽\"")
    public void появляетсяНадписьСУказаниемСуммыВФормате(String arg0) {
        OverviewPage overviewPage = new OverviewPage();
        overviewPage.getMyAssets().shouldHave(Condition.text(arg0));
        overviewPage.getMyAssets().should(Condition.matchText("\\d{0,3}\\s\\d{0,3}\\s\\d{1,3}\\.\\d{2}\\s."));
    }

}
