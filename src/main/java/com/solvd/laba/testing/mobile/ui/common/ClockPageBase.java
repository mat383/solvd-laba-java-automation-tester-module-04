package com.solvd.laba.testing.mobile.ui.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class ClockPageBase extends AbstractPage {

    public ClockPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract AlarmPageBase goToAlarms();

    public abstract boolean addClock(String city);

    /**
     * get list of cities for which clocks are shown
     */
    public abstract List<String> getClocks();

    public abstract boolean isClockForCityPresent(String cityName);

}
