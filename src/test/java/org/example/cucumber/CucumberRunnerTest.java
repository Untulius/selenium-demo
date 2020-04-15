package org.example.cucumber;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeMethod;

/*
Переделать автотест из 8-го задания блока «Автоматизации тестирования вебприложений» с использованием библиотеки Cucumber
 */

@CucumberOptions(
        plugin = "json:target/cucumber-report.json",
        features = "src/test/resources/feature",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"org.example.cucumber.stepdefenitions"}
)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {

    @BeforeMethod
    public void selenideConfigure() {
        Configuration.timeout = 5_000;
        Configuration.browser = "chrome";
        Configuration.clickViaJs = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }
}
