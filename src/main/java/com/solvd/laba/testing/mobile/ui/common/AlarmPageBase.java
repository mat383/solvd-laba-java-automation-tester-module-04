package com.solvd.laba.testing.mobile.ui.common;

import com.solvd.laba.testing.mobile.ui.common.components.NavigationBarBase;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.time.LocalTime;
import java.util.List;

public abstract class AlarmPageBase extends AbstractPage {
    public AlarmPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract NavigationBarBase getNavigationBar();

    public abstract boolean addSimpleAlarm(LocalTime time);

    public abstract boolean enableAlarmForHour(LocalTime time);

    public abstract List<LocalTime> getAlarmHours();

    public abstract boolean isAlarmPresent(LocalTime time);
}
