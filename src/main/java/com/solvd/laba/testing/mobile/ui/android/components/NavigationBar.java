package com.solvd.laba.testing.mobile.ui.android.components;

import com.solvd.laba.testing.mobile.ui.android.AlarmPage;
import com.solvd.laba.testing.mobile.ui.android.ClockPage;
import com.solvd.laba.testing.mobile.ui.common.AlarmPageBase;
import com.solvd.laba.testing.mobile.ui.common.ClockPageBase;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class NavigationBar extends AbstractUIObject {

    @FindBy(xpath = "//rk[@content-desc='Alarm']")
    private ExtendedWebElement alarmButton;
    @FindBy(xpath = "//rk[@content-desc='Clock']")
    private ExtendedWebElement clockButton;
    @FindBy(xpath = "//rk[@content-desc='Timer']")
    private ExtendedWebElement timerButton;
    @FindBy(xpath = "//rk[@content-desc='Stopwatch']")
    private ExtendedWebElement stopwatchButton;

    public NavigationBar(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public AlarmPageBase goToAlarmPage() {
        this.alarmButton.click();
        return new AlarmPage(getDriver());
    }

    public ClockPageBase goToClockPage() {
        this.clockButton.click();
        return new ClockPage(getDriver());
    }
}
