package com.solvd.laba.testing.mobile.ui.android;

import com.solvd.laba.testing.mobile.ui.android.components.NavigationBar;
import com.solvd.laba.testing.mobile.ui.common.ClockPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@DeviceType(pageType = DeviceType.Type.ANDROID_PHONE, parentClass = ClockPageBase.class)
public class ClockPage extends ClockPageBase {

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Cities']")
    private ExtendedWebElement addClockButton;

    @FindBy(xpath = "//android.widget.EditText[@resource-id='com.google.android.deskclock:id/search_src_text']")
    private ExtendedWebElement searchCityInput;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.deskclock:id/city_name']")
    private List<ExtendedWebElement> searchResults;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.deskclock:id/city_name']")
    private List<ExtendedWebElement> cityClocks;

    @FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.google.android.deskclock:id/toolbar']")
    private NavigationBar navigationBar;

    public ClockPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NavigationBar getNavigationBar() {
        return this.navigationBar;
    }

    @Override
    public boolean addClock(String city) {
        this.addClockButton.click();
        this.searchCityInput.type(city);
        waitUntil(webDriver -> this.searchResults.getFirst(), 9);
        if (this.searchResults.isEmpty()) {
            return false;
        }
        this.searchResults.getFirst().click();
        return isClockForCityPresent(city);
    }

    @Override
    public List<String> getClocks() {
        waitUntil(webDriver -> this.cityClocks.getFirst(), 9);
        return this.cityClocks.stream()
                .map(ExtendedWebElement::getText)
                .toList();
    }

    @Override
    public boolean isClockForCityPresent(String cityName) {
        waitUntil(webDriver -> this.cityClocks.getFirst(), 9);
        return this.cityClocks.stream()
                .map(ExtendedWebElement::getText)
                .anyMatch(cityName::equalsIgnoreCase);
    }
}
