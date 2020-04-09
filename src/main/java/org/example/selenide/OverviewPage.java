package org.example.selenide;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class OverviewPage {

    private SelenideElement amountField = $(By.xpath("//span[@class='amount']"));
    private SelenideElement finfreedom = $(By.xpath("//div[@id='header-container']//span[@class='text']"));
    private SelenideElement amount = $(By.xpath("//div[@id='header-container']//span[@class='amount']"));
    private SelenideElement myAssets = $(By.xpath("//div[@id='header-container']//small[@class='my-assets'][contains(text(),'Моих')]"));

    @Step("Наведение курсора")
    public void moveCursor() {
        amountField.hover();
    }

    @Step("Получение блока Финансоввая свобода")
    public SelenideElement getFinfreedom() {
        return finfreedom;
    }

    @Step("Получение блока Сумма")
    public SelenideElement getAmount() {
        return amount;
    }

    @Step("Получение блока Моих средств")
    public SelenideElement getMyAssets() {
        return myAssets;
    }
}
