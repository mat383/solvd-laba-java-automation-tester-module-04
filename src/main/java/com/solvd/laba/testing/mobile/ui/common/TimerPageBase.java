package com.solvd.laba.testing.mobile.ui.common;

import com.solvd.laba.testing.mobile.ui.common.components.NavigationBarBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.time.LocalTime;

public abstract class TimerPageBase extends AbstractPage {
    public TimerPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract NavigationBarBase getNavigationBar();

    public abstract void startTimer(LocalTime duration);

    public abstract boolean isTimerRunning();

    public abstract boolean isTimerFinished();

    public abstract boolean stopTimer();
}
