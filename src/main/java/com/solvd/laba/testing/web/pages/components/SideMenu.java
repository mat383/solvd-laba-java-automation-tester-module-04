package com.solvd.laba.testing.web.pages.components;

import com.solvd.laba.testing.web.pages.InventoryPage;
import com.solvd.laba.testing.web.pages.LoginPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SideMenu extends AbstractUIObject {

    @FindBy(id = "react-burger-menu-btn")
    private ExtendedWebElement openButton;
    @FindBy(id = "react-burger-cross-btn")
    private ExtendedWebElement closeButton;
    @FindBy(id = "inventory_sidebar_link")
    private ExtendedWebElement inventoryLink;
    @FindBy(id = "logout_sidebar_link")
    private ExtendedWebElement logoutLink;
    @FindBy(id = "reset_sidebar_link")
    private ExtendedWebElement resetAppStateLink;

    public SideMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    /**
     * opens sidebar, does anything only if sidebar is closed
     */
    public void openSideMenu() {
        if (!isOpened()) {
            this.openButton.click();
        }
    }

    /**
     * closes sidebar, does anything only if sidebar is opened
     */
    public void closeSideMenu() {
        if (isOpened()) {
            this.closeButton.click();
        }
    }

    public boolean isOpened() {
        return this.closeButton.isVisible();
    }

    /**
     * open side menu if closed and go to inventory page
     */
    public InventoryPage goToInventoryPage() {
        openSideMenu();
        this.inventoryLink.click();
        return new InventoryPage(getDriver());
    }

    /**
     * open side menu if closed and logs out
     */
    public LoginPage logOut() {
        openSideMenu();
        this.logoutLink.click();
        return new LoginPage(getDriver());
    }

    /**
     * open side menu if closed and resets app state
     */
    public void resetWebState() {
        openSideMenu();
        this.resetAppStateLink.click();
    }
}
