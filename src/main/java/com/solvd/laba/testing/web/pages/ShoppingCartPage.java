package com.solvd.laba.testing.web.pages;

import com.solvd.laba.testing.web.pages.components.ShoppingCartButton;
import com.solvd.laba.testing.web.pages.components.ShoppingCartItem;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends AbstractPage {

    @Getter
    @FindBy(xpath = "//*[@id='header_container']//*[contains(@class,'primary_header')]")
    private ShoppingCartButton primaryHeader;

    @FindBy(css = ".card_list .card_item")
    private List<ShoppingCartItem> shoppingCartItems;

    @FindBy(id = "checkout")
    private ExtendedWebElement checkoutButton;
    @FindBy(id = "continue-shopping")
    private ExtendedWebElement continueShoppingButton;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * goes back to inventory page (by clicking continue shopping)
     */
    public InventoryPage goBackToInventoryPage() {
        this.continueShoppingButton.click();
        return new InventoryPage(getDriver());
    }

    public CheckoutStepOnePage goToCheckout() {
        this.continueShoppingButton.click();
        return new CheckoutStepOnePage(getDriver());
    }
}
