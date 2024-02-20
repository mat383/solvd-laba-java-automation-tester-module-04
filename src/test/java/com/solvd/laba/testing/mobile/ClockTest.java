package com.solvd.laba.testing.mobile;

import com.solvd.laba.testing.mobile.ui.common.AlarmPageBase;
import com.solvd.laba.testing.mobile.ui.common.ClockPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalTime;

public class ClockTest implements IAbstractTest {

    /**
     * verify adding clock from some city to clock page
     * <p>
     * Steps:
     * 1. Open clock application - result: clock application opens
     * 2. Add clock for specified city - result: clock for specified city appears
     */
    @Test()
    public void verifyAddClockTest() {
        ClockPageBase clocksPage = initPage(ClockPageBase.class);
        Assert.assertTrue(clocksPage.addClock(R.TESTDATA.get("city_for_clock_to_add")),
                "Failed to add clock for city");
    }

    /**
     * Verify adding alarm
     * <p>
     * Steps:
     * 1. Open clock application - result: clock application opens
     * 2. Navigate to alarms page - result: alarms page appears
     * 3. Add alarm - result: alarm is added and appears on the list
     */
    @Test
    public void verifyAddAlarmTest() {
        ClockPageBase clocksPage = initPage(ClockPageBase.class);
        AlarmPageBase alarmPage = clocksPage.goToAlarms();

        LocalTime testAlarmTime = LocalTime.of(
                Integer.parseInt(R.TESTDATA.get("add_alarm_test_hour")),
                Integer.parseInt(R.TESTDATA.get("add_alarm_test_minute")));

        Assert.assertTrue(alarmPage.addSimpleAlarm(testAlarmTime),
                "Failed to add alarm");
    }

    /**
     * Verify enabling predefined alarm
     * <p>
     * Steps:
     * 1. Open clock application - result: clock application opens
     * 2. Navigate to alarms page - result: alarms page appears
     * 3. Enable first predefined alarm - result: alarm is enabled
     */
    @Test
    public void verifyEnableAlarmTest() {
        ClockPageBase clocksPage = initPage(ClockPageBase.class);
        AlarmPageBase alarmPage = clocksPage.goToAlarms();

        Assert.assertFalse(alarmPage.getAlarmHours().isEmpty(),
                "No predefined alarms");

        LocalTime alarmToEnableTime = alarmPage.getAlarmHours().getFirst();

        Assert.assertTrue(alarmPage.enableAlarmForHour(alarmToEnableTime),
                "Failed to enable alarm");
    }
}
