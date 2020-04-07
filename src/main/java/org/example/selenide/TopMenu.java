package org.example.selenide;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

public class TopMenu {

    public OverviewPage selectTopMenu(String menuTitle) {
        Selenide.$(By.id(menuTitle)).click();
        return new OverviewPage();
    }
}
