package com.solvd.laba.testing.mobile.ui.common.components;

import com.solvd.laba.testing.mobile.ui.common.AlarmPageBase;
import com.solvd.laba.testing.mobile.ui.common.ClockPageBase;
import com.solvd.laba.testing.mobile.ui.common.StopwatchPageBase;
import com.solvd.laba.testing.mobile.ui.common.TimerPageBase;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class NavigationBarBase extends AbstractUIObject {


    public NavigationBarBase(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public abstract AlarmPageBase goToAlarmPage();

    public abstract ClockPageBase goToClockPage();

    public abstract TimerPageBase goToTimerPage();

    public abstract StopwatchPageBase goToStopwatchPage();
}