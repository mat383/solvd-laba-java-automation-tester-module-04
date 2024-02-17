package com.solvd.laba.testing.mobile;

import com.solvd.laba.testing.mobile.ui.common.ClocksPageBase;
import com.zebrunner.carina.core.IAbstractTest;
import org.testng.annotations.Test;

public class ClockTest implements IAbstractTest {

    @Test()
    void startTest() {
        ClocksPageBase clocksPage = initPage(ClocksPageBase.class);
        clocksPage.clickAddClock();
    }
}
