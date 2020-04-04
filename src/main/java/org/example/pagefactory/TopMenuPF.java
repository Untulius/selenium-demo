package org.example.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopMenuPF {
    private final WebDriver webDriver;

    public TopMenuPF(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public OverviewPagePF selectTopMenu(String menuTitle) {
        WebElement menu = webDriver.findElement(By.id(menuTitle));
        menu.click();
        return new OverviewPagePF(webDriver);
    }
}
