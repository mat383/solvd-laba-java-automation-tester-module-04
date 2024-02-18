package com.solvd.laba.testing.web.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;

public class LoginPage extends AbstractPage {


    @FindBy(id = "user-name")
    private ExtendedWebElement usernameField;

    @FindBy(id = "password")
    private ExtendedWebElement passwordField;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageURL("");
    }


    /**
     * performs login with given credentials,
     * if login was successful returns new ProductsPage,
     * if failed returns empty Optional
     */
    public Optional<InventoryPage> logIn(String username, String password) {
        // login
        this.usernameField.type(username);
        this.passwordField.type(password);
        loginButton.click();

        // check if login was successful
        InventoryPage productsPage = new InventoryPage(getDriver());

        if (productsPage.isPageOpened()) {
            return Optional.of(productsPage);
        } else {
            return Optional.empty();
        }
    }

}
