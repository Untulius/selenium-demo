package org.example.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class OverviewPagePF extends BasePagePF {

    @FindBy(xpath = "//span[@class='amount']")
    private WebElement amountField;

    public OverviewPagePF(WebDriver webDriver) {
        super(webDriver);
    }

    public void moveCursor() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(amountField).pause(Duration.ofSeconds(5)).build().perform();
    }
}
