package org.example.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class OverviewPage {

    private SelenideElement amountField = $(By.xpath("//span[@class='amount']"));
    private SelenideElement finfreedom = $(By.xpath("//div[@id='header-container']//span[@class='text']"));
    private SelenideElement amount = $(By.xpath("//div[@id='header-container']//span[@class='amount']"));
    private SelenideElement myAssets = $(By.xpath("//div[@id='header-container']//small[@class='my-assets'][contains(text(),'Моих')]"));

    public void moveCursor() {
        amountField.hover();
    }

    public SelenideElement getFinfreedom() {
        return finfreedom;
    }

    public SelenideElement getAmount() {
        return amount;
    }

    public SelenideElement getMyAssets() {
        return myAssets;
    }
}
