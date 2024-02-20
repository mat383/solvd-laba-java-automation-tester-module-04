package com.solvd.laba.testing.mobile;

import com.solvd.laba.testing.mobile.ui.common.AlarmPageBase;
import com.solvd.laba.testing.mobile.ui.common.ClockPageBase;
import com.solvd.laba.testing.mobile.ui.common.StopwatchPageBase;
import com.solvd.laba.testing.mobile.ui.common.TimerPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
        AlarmPageBase alarmPage = clocksPage.getNavigationBar().goToAlarmPage();

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
        AlarmPageBase alarmPage = clocksPage.getNavigationBar().goToAlarmPage();

        Assert.assertFalse(alarmPage.getAlarmHours().isEmpty(),
                "No predefined alarms");

        LocalTime alarmToEnableTime = alarmPage.getAlarmHours().getFirst();

        Assert.assertTrue(alarmPage.enableAlarmForHour(alarmToEnableTime),
                "Failed to enable alarm");
    }

    /**
     * Verify that simple stopwatch functions work
     * <p>
     * Steps:
     * 1. Open clock application - result: clock application opens
     * 2. Navigate to stopwatch page - result: stopwatch page appears
     * 3. Start stopwatch and stop it after predefined time - result: stopwatch runs, stops and shows
     * at lest amount of time predefined
     * 4. Reset stopwatch - result: stopwatch is reset - it shows zero
     */
    @Test
    public void verifyStopwatchStartStopTest() {
        // navigate to stopwatch
        ClockPageBase clocksPage = initPage(ClockPageBase.class);
        StopwatchPageBase stopwatchPage = clocksPage.getNavigationBar().goToStopwatchPage();

        // test stopwatch
        LocalTime timeToWait = LocalTime.ofSecondOfDay(Integer.parseInt(R.TESTDATA.get("stopwatch_test_wait_seconds")));
        SoftAssert softAssert = new SoftAssert();
        Assert.assertTrue(stopwatchPage.startStopwatch(), "Failed to start stopwatch");
        // wait
        pause(timeToWait.toSecondOfDay());
        Assert.assertTrue(stopwatchPage.stopStopwatch(), "Failed to stop stopwatch");
        softAssert.assertTrue(stopwatchPage.getTime().isAfter(timeToWait), "Stopwatch time is less than expected");
        softAssert.assertTrue(stopwatchPage.resetStopwatch(), "Failed to reset stopwawtch");
        softAssert.assertAll();
    }

    /**
     * Verify that simple timer functions work
     * <p>
     * Steps:
     * 1. Open clock application - result: clock application opens
     * 2. Navigate to timer page - result: timer page appears
     * 3. Start timer for predefined time - result: timer starts and runs for predefined time
     * 4. Stop timer - result: timer stops
     */
    @Test
    public void verifyTimerFroShortDurationTest() {
        // navigate to stopwatch
        ClockPageBase clocksPage = initPage(ClockPageBase.class);
        TimerPageBase timerPage = clocksPage.getNavigationBar().goToTimerPage();

        LocalTime timerTimeToSet = LocalTime.ofSecondOfDay(
                Long.parseLong(R.TESTDATA.get("timer_short_test_duration_seconds")));

        timerPage.startTimer(timerTimeToSet);
        pause(timerTimeToSet.toSecondOfDay());
        Assert.assertTrue(timerPage.isTimerFinished(), "Timer didn't finished in time");
        Assert.assertTrue(timerPage.stopTimer(), "Failed to stop timer");
    }
}
