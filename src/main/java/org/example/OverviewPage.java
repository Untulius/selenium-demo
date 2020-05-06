package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class OverviewPage {
    private final WebDriver webDriver;

    public OverviewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void moveCursor() {
        Actions actions = new Actions(webDriver);
        WebElement amount = webDriver.findElement(By.xpath("//span[@class='amount']"));
        actions.moveToElement(amount).pause(Duration.ofSeconds(2)).build().perform();
    }

    public WebElement getFinfreedom() {
        return webDriver.findElement(By.xpath("//div[@id='header-container']//span[@class='text']"));
    }

    public WebElement getAmount() {
        return webDriver.findElement(By.xpath("//div[@id='header-container']//span[@class='amount']"));
    }

    public WebElement getMyAssets() {
        return webDriver.findElement(By.xpath("//div[@id='header-container']//small[@class='my-assets'][contains(text(),'Моих')]"));
    }
}
