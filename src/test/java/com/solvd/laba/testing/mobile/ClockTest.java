package com.solvd.laba.testing.mobile;

import com.solvd.laba.testing.mobile.ui.common.ClockPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ClockTest implements IAbstractTest {

    @Test()
    void startTest() {
        ClockPageBase clocksPage = initPage(ClockPageBase.class);
        Assert.assertTrue(clocksPage.addClock("Lisbon"));
        System.out.println();
    }
}
