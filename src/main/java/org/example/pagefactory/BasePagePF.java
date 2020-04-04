package org.example.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePagePF {
    public static final int TIME_OUT_IN_SECONDS = 30;
    protected final WebDriver webDriver;
    protected final WebDriverWait webDriverWait;
    private final TopMenuPF topMenu;

    public BasePagePF(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.topMenu = new TopMenuPF(webDriver);
        this.webDriverWait = new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS);
        PageFactory.initElements(webDriver, this);
    }

}
