package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class OverviewPage {
    private final WebDriver webDriver;

    public  OverviewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void moveCursor() {
        Actions actions = new Actions(webDriver);
        WebElement amount = webDriver.findElement(By.xpath("//span[@class='amount']"));
        actions.moveToElement(amount);
        actions.perform();
    }
}
