package com.solvd.laba.testing.web.pages;

import com.solvd.laba.testing.web.pages.components.ShoppingCartButton;
import com.solvd.laba.testing.web.pages.components.SideMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutStepOnePage extends AbstractPage {

    @FindBy(id = "first-name")
    private ExtendedWebElement firstNameInput;
    @FindBy(id = "last-name")
    private ExtendedWebElement lastNameInput;
    @FindBy(id = "postal-code")
    private ExtendedWebElement zipOrPostalCodeInput;
    @FindBy(id = "cancel")
    private ExtendedWebElement cancelButton;
    @FindBy(id = "continue")
    private ExtendedWebElement continueButton;


    @Getter
    @FindBy(id = "shopping_cart_container")
    private ShoppingCartButton shoppingCartButton;

    @Getter
    @FindBy(id = "menu_button_container")
    private SideMenu sideMenu;

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
        setPageURL("checkout-step-one.html");
    }

    public ShoppingCartPage cancelCheckout() {
        this.cancelButton.click();
        return new ShoppingCartPage(getDriver());
    }

    public CheckoutStepTwoPage continueCheckout(String firstName, String lastName, String zipOrPostalCode) {
        this.firstNameInput.type(firstName);
        this.lastNameInput.type(lastName);
        this.zipOrPostalCodeInput.type(zipOrPostalCode);
        this.continueButton.click();
        return new CheckoutStepTwoPage(getDriver());
    }
}
