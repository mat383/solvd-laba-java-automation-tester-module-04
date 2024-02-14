package com.solvd.laba.testing.web.pages.components;

import com.solvd.laba.testing.web.pages.ProductsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;

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

    public boolean isUsernameFieldPresent() {
        // TODO: implement
        return false;
    }

    public void typeUsername(String username) {
        this.usernameField.type(username);
    }

    public void typePassword(String password) {
        this.passwordField.type(password);
    }

    /**
     * clicks login button, if login was successful,
     * returns new ProductsPage, if failed returns
     * empty Optional
     */
    public Optional<ProductsPage> clickLoginButton() {
        this.loginButton.click();
        ProductsPage productsPage = new ProductsPage(getDriver());

        if (productsPage.isPageOpened()) {
            return Optional.of(productsPage);
        } else {
            return Optional.empty();
        }
    }

    public void getLoginPromptState() {
        // informs about login prompt state (like invalid login
    }
}
