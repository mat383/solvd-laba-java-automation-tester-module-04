package com.solvd.laba.testing.mobile.ui.common;

import com.solvd.laba.testing.mobile.ui.common.components.NavigationBarBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.time.LocalTime;

public abstract class StopwatchPageBase extends AbstractPage {
    public StopwatchPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract NavigationBarBase getNavigationBar();

    public abstract boolean startStopwatch();

    public abstract boolean stopStopwatch();

    public abstract boolean resetStopwatch();

    public abstract boolean isStopwatchRunning();

    public abstract LocalTime getTime();
}
