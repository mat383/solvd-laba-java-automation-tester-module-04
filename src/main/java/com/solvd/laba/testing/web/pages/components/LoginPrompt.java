package com.solvd.laba.testing.web.pages.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPrompt extends AbstractUIObject {

    @FindBy(xpath = ".//input[@id='user-name']")
    private ExtendedWebElement usernameField;

    @FindBy(xpath = ".//input[@id='password']")
    private ExtendedWebElement passwordField;

    @FindBy(xpath = ".//input[@id='login-button']")
    private ExtendedWebElement loginButton;

    @FindBy(id = "bm-menu-wrap")
    private SideMenu sideMenu;

    public LoginPrompt(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void typeUsername(String username) {
        this.usernameField.type(username);
    }

    public void typePassword(String password) {
        this.passwordField.type(password);
    }

    public void clickLoginButton() {
        this.loginButton.click();
        // TODO: change to return loggged page
        // TODO: return optional<Page>
    }

    public void getLoginPromptState() {
        // informs about login prompt state (like invalid login
    }
}
