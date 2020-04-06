package org.example.selenide;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopMenu {
    private final WebDriver webDriver;

    public TopMenu(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public OverviewPage selectTopMenu(String menuTitle) {
        WebElement menu = webDriver.findElement(By.id(menuTitle));
        menu.click();
        return new OverviewPage(webDriver);
    }
}
