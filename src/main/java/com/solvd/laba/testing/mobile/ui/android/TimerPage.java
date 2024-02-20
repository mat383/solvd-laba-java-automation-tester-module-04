package com.solvd.laba.testing.mobile.ui.android;

import com.solvd.laba.testing.mobile.ui.android.components.NavigationBar;
import com.solvd.laba.testing.mobile.ui.common.TimerPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.time.LocalTime;
import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = TimerPageBase.class)
public class TimerPage extends TimerPageBase {

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.google.android.deskclock:id/toolbar']")
    private NavigationBar navigationBar;

    @FindBy(xpath = "//android.widget.ImageButton[@resource-id='com.google.android.deskclock:id/fab']")
    private ExtendedWebElement stopStartButton;

    @FindBy(xpath = "//android.widget.Button[contains(@resource-id, 'com.google.android.deskclock:id/timer_setup_digit_')]")
    private List<ExtendedWebElement> digits;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.deskclock:id/timer_time_text']")
    private ExtendedWebElement timerTime;

    public TimerPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NavigationBar getNavigationBar() {
        return this.navigationBar;
    }

    @Override
    public void startTimer(LocalTime duration) {
        insertNumberAsTwoDigits(duration.getHour());
        insertNumberAsTwoDigits(duration.getMinute());
        insertNumberAsTwoDigits(duration.getSecond());
        this.stopStartButton.click();
    }

    @Override
    public boolean isTimerRunning() {
        return this.stopStartButton.getAttribute("content-desc").equals("Stop")
                && !isTimerFinished();
    }

    @Override
    public boolean isTimerFinished() {
        return this.timerTime.getText().startsWith("−");
    }

    @Override
    public boolean stopTimer() {
        if (isTimerFinished()) {
            this.stopStartButton.click();
            return this.stopStartButton.getAttribute("content-desc").equals("Start");
        }
        return true;
    }

    private void clickDigit(int digit) {
        if (digit == 0) {
            this.digits.get(9).click();
        } else {
            this.digits.get(digit - 1).click();
        }
    }

    /**
     * used to insert number for hour, minute and second
     * to start timer
     */
    private void insertNumberAsTwoDigits(int number) {
        clickDigit(number / 10);
        clickDigit(number % 10);
    }

}
