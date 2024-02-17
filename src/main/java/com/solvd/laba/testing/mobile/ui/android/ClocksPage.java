package com.solvd.laba.testing.mobile.ui.android;

import com.solvd.laba.testing.mobile.ui.common.ClocksPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ClocksPageBase.class)
public class ClocksPage extends ClocksPageBase {

    @FindBy(xpath = "//android.widget.Button[@content-desc='Add city']")
    private ExtendedWebElement addClockButton;

    public ClocksPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddClock() {
        this.addClockButton.click();
    }
}
