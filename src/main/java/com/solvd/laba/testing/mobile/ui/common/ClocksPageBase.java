package com.solvd.laba.testing.mobile.ui.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

public abstract class ClocksPageBase extends AbstractPage {

    public ClocksPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void clickAddClock();
}
