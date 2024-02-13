package com.solvd.laba.testing.web.pages.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SideMenu extends AbstractUIObject {

    @FindBy(id = "react-burger-cross-btn")
    private ExtendedWebElement allItemsLink;
    @FindBy(id = "inventory_sidebar_link")
    private ExtendedWebElement aboutLink;
    @FindBy(id = "logout_sidebar_link")
    private ExtendedWebElement logoutLink;
    @FindBy(id = "reset_sidebar_link")
    private ExtendedWebElement resetAppStateLink;

    public SideMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
}
