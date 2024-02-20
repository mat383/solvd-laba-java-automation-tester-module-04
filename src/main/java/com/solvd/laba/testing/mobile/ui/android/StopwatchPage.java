package com.solvd.laba.testing.mobile.ui.android;

import com.solvd.laba.testing.mobile.ui.android.components.NavigationBar;
import com.solvd.laba.testing.mobile.ui.common.StopwatchPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = StopwatchPageBase.class)
public class StopwatchPage extends StopwatchPageBase {

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.google.android.deskclock:id/toolbar']")
    private NavigationBar navigationBar;

    @FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.google.android.deskclock:id/fab']")
    private ExtendedWebElement stopStartButton;
    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.deskclock:id/stopwatch_time_text']")
    private ExtendedWebElement timeIndicator;
    @FindBy(xpath = "//android.widget.Button[@content-desc='Reset']")
    private ExtendedWebElement resetButton;

    public StopwatchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NavigationBar getNavigationBar() {
        return this.navigationBar;
    }

    @Override
    public boolean startStopwatch() {
        if (!isStopwatchRunning()) {
            this.stopStartButton.click();
            return isStopwatchRunning();
        }
        return true;
    }

    @Override
    public boolean stopStopwatch() {
        if (isStopwatchRunning()) {
            this.stopStartButton.click(10L);
            return !isStopwatchRunning();
        }
        return true;
    }

    @Override
    public boolean resetStopwatch() {
        if (this.resetButton.isVisible()) {
            this.resetButton.click();
        }
        return getTime().equals(LocalTime.MIN);
    }

    @Override
    public boolean isStopwatchRunning() {
        return this.stopStartButton.getAttribute("content-desc").equals("Pause");
    }

    @Override
    public LocalTime getTime() {
        String timeAsString = this.timeIndicator.getText();
        if (timeAsString.contains(":")) {
            return LocalTime.parse("0:" + timeAsString, DateTimeFormatter.ofPattern("H:m:ss"));
        } else {
            return LocalTime.parse("0:0:" + timeAsString, DateTimeFormatter.ofPattern("H:m:s"));
        }
    }

}
