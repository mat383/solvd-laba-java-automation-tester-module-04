package com.solvd.laba.testing.mobile.ui.android;

import com.solvd.laba.testing.mobile.ui.common.AlarmPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = AlarmPageBase.class)
public class AlarmPage extends AlarmPageBase {

    @FindBy(xpath = "//android.support.v7.widget.RecyclerView" +
            "[@resource-id='com.google.android.deskclock:id/alarm_recycler_view']" +
            "//android.view.ViewGroup[contains(@content-desc,' Alarm')]")
    private List<Alarm> alarms;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Add alarm']")
    private ExtendedWebElement addAlarmButton;
    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Switch to text input mode for the time input.']")
    private ExtendedWebElement addAlarmSwitchInput;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"android:id/input_hour\"]")
    private ExtendedWebElement addAlarmHour;
    @FindBy(xpath = "//android.widget.EditText[@resource-id=\"android:id/input_minute\"]")
    private ExtendedWebElement addAlarmMinute;
    @FindBy(xpath = "//android.widget.Spinner[@resource-id=\"android:id/am_pm_spinner\"]")
    private ExtendedWebElement addAlarmAmPmSpinner;
    @FindBy(xpath = "//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"AM\"]")
    private ExtendedWebElement addAlarmAmSpinnerLabel;
    @FindBy(xpath = "//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"PM\"]")
    private ExtendedWebElement addAlarmPmSpinnerLabel;
    @FindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]")
    private ExtendedWebElement addAlarmOkButton;

    public AlarmPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean addSimpleAlarm(LocalTime time) {
        this.addAlarmButton.click();
        this.addAlarmSwitchInput.click();
        waitUntil(webDriver -> this.addAlarmHour.getText(), 9);
        this.addAlarmHour.type(String.valueOf(time.get(ChronoField.CLOCK_HOUR_OF_AMPM)));
        this.addAlarmMinute.type(String.valueOf(time.getMinute()));
        this.addAlarmAmPmSpinner.click();
        switch (time.get(ChronoField.AMPM_OF_DAY)) {
            case 0 -> this.addAlarmAmSpinnerLabel.click();
            case 1 -> this.addAlarmPmSpinnerLabel.click();
        }
        this.addAlarmOkButton.click();
        return isAlarmPresent(time);
    }

    /**
     * enables alarm for given time if
     * any found for this time
     * if multiple alarms present for given time
     * then selects one of them
     *
     * @return true if any alarm found
     */
    @Override
    public boolean enableAlarmForHour(LocalTime time) {
        waitUntil(webDriver -> this.alarms.getFirst(), 9);
        Optional<Alarm> selectedAlarm = this.alarms.stream()
                .filter(alarm -> time.equals(alarm.getTime()))
                .findAny();

        waitUntil(webDriver -> this.alarms.getFirst(), 9);
        selectedAlarm.ifPresent(alarm -> alarm.setEnabled(true));
        return selectedAlarm.isPresent();
    }

    @Override
    public List<LocalTime> getAlarmHours() {
        waitUntil(webDriver -> this.alarms.getFirst(), 9);
        return this.alarms.stream()
                .map(Alarm::getTime)
                .toList();
    }

    @Override
    public boolean isAlarmPresent(LocalTime time) {
        waitUntil(webDriver -> this.alarms.stream()
                        .map(Alarm::getTime).
                        filter(time::equals).
                        findAny()
                        .orElse(null),
                9);
        return this.alarms.stream()
                .map(Alarm::getTime)
                .anyMatch(time::equals);
    }

    public static class Alarm extends AbstractUIObject {

        @FindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.deskclock:id/digital_clock']")
        private ExtendedWebElement alarmHour;

        @FindBy(xpath = "//android.widget.Switch[@resource-id='com.google.android.deskclock:id/onoff']")
        private ExtendedWebElement toggle;

        public Alarm(WebDriver driver, SearchContext searchContext) {
            super(driver, searchContext);
        }

        public LocalTime getTime() {
            return LocalTime.parse(this.alarmHour.getText(),
                    DateTimeFormatter.ofPattern("h:mm a", Locale.US));
        }

        public boolean isEnabled() {
            return this.toggle.isChecked();
        }

        public void setEnabled(boolean enabled) {
            if (isEnabled() != enabled) {
                this.toggle.click();
            }

        }
    }
}