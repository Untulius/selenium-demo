package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TopMenu {
    private final WebDriver webDriver;

    public  TopMenu(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void selectTopMenu(String menuTitle){
        WebElement menu = webDriver.findElement(By.id(menuTitle));
        menu.click();
    }
}
