package org.example.selenide;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class OverviewPage {
    private final WebDriver webDriver;

    @FindBy(xpath = "//span[@class='amount']")
    private WebElement amountField;

    public OverviewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void moveCursor() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(amountField).pause(Duration.ofSeconds(5)).build().perform();
    }
}
