package org.example.selenide;

import org.example.pagefactory.BasePagePF;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class OverviewPage extends BasePage {

    @FindBy(xpath = "//span[@class='amount']")
    private WebElement amountField;

    @FindBy(xpath = "//div[@id='header-container']//span[@class='text']")
    private WebElement finfreedom;

    @FindBy(xpath = "//div[@id='header-container']//span[@class='amount']")
    private WebElement amount;

    @FindBy(xpath = "//div[@id='header-container']//small[@class='my-assets'][contains(text(),'Моих')]")
    private WebElement myAssets;

    public OverviewPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void moveCursor() {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(amountField).pause(Duration.ofSeconds(5)).build().perform();
    }

    public WebElement getFinfreedom() {
        return finfreedom;
    }

    public WebElement getAmount() {
        return amount;
    }

    public WebElement getMyAssets() {
        return myAssets;
    }
}
