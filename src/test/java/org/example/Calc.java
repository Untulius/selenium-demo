package org.example;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Calc {

    @DataProvider
    public Object[][] dataProvider() {
        return new Object[][]{
                {2, 2, 4},
                {3, 2, 6},
                {3, 4, 8}
        };
    }

    @Test(dataProvider = "dataProvider")
    public void calculator(int a, int b, int c) {
        Assert.assertEquals(sum(a, b), c);
    }

    @Step("Сумма - {a} + {b}")
    public float sum(float a, float b) {
        return a + b;
    }
}
