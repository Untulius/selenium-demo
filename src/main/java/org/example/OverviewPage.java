package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class OverviewPage extends BasePage {
    private By amountField = By.xpath("//span[@class='amount']");

    public OverviewPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void moveCursor() {
        Actions actions = new Actions(webDriver);
        WebElement amount = webDriver.findElement(amountField);
        actions.moveToElement(amount).pause(Duration.ofSeconds(5)).build().perform();
    }
}
