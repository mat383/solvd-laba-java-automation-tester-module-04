package com.solvd.laba.testing.web.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.Optional;

public class LoginPage extends AbstractPage {


    @FindBy(id = "user-name")
    private ExtendedWebElement usernameField;

    @FindBy(id = "password")
    private ExtendedWebElement passwordField;

    @FindBy(id = "login-button")
    private ExtendedWebElement loginButton;

    @FindBy(css = ".error-message-container h3")
    private ExtendedWebElement errorLabel;

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

    /**
     * informs about login prompt state,
     * i.e. invalid credentials,
     * locked out user
     */
    public State getState() {
        if (!this.errorLabel.isElementPresent()) {
            return State.NORMAL;
        }
        return State.ofErrorMessage(this.errorLabel.getText());
    }

    @Getter
    public enum State {
        NORMAL(""),
        INVALID_CREDENTIALS("Epic sadface: Username and password do not match any user in this service"),
        LOCKED_OUT_USER("Epic sadface: Sorry, this user has been locked out.");

        private String errorMessage;

        State(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public static State ofErrorMessage(String errorMessage) {
            return Arrays.stream(State.values())
                    .filter(state -> errorMessage.equals(state.getErrorMessage()))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("Error message doesn't fit any state"));
        }
    }

}
