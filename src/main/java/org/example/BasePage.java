package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    public static final int TIME_OUT_IN_SECONDS = 30;
    protected final WebDriver webDriver;
    protected final WebDriverWait webDriverWait;
    private final TopMenu topMenu;

    public  BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.topMenu = new TopMenu(webDriver);
        this.webDriverWait = new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
    }

    public TopMenu getTopMenu() {
        return topMenu;
    }
}
