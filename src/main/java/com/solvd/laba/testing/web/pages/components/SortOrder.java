package com.solvd.laba.testing.web.pages.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class SortOrder extends AbstractUIObject {
    public SortOrder(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getShortName() {
        return getRootExtendedElement().getAttribute("value");
    }

    public String getFullName() {
        return getRootExtendedElement().getText();
    }

    public boolean matchesPredefinedSortOrder(sortOrderSelector.PredefinedSortOrder predefinedSortOrder) {
        return getShortName().equals(predefinedSortOrder.getShortName())
                && getFullName().equals(predefinedSortOrder.getFullName());
    }

}
