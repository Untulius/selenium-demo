package org.example.selenide;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class TopMenu {
    @Step("Переход в меню {menuTitle}")
    public OverviewPage selectTopMenu(String menuTitle) {
        Selenide.$(By.id(menuTitle)).click();
        return new OverviewPage();
    }
}
