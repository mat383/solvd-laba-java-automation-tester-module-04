package com.solvd.laba.testing.web.pages;

import com.solvd.laba.testing.web.pages.components.InventoryButton;
import com.solvd.laba.testing.web.pages.components.ShoppingCartButton;
import com.solvd.laba.testing.web.pages.components.SideMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class DetailsPage extends AbstractPage {

    @FindBy(className = "inventory_details_name")
    public ExtendedWebElement productName;
    @FindBy(className = "inventory_details_desc")
    public ExtendedWebElement description;
    @FindBy(className = "inventory_details_price")
    public ExtendedWebElement price;
    @FindBy(className = "btn_inventory")
    public InventoryButton inventoryButton;
    @FindBy(id = "back-to-products")
    public ExtendedWebElement goBackToInventory;


    @Getter
    @FindBy(id = "shopping_cart_container")
    private ShoppingCartButton shoppingCartButton;

    @Getter
    @FindBy(id = "menu_button_container")
    private SideMenu sideMenu;

    public DetailsPage(WebDriver driver) {
        super(driver);
    }

    public InventoryPage goBackToInventory() {
        this.goBackToInventory.click();
        return new InventoryPage(getDriver());
    }

    /**
     * informs about cart button state (add to cart or remove from cart),
     * and throws RuntimeError when state doesn't match known states
     */
    public InventoryButton.ButtonState getCartButtonState() {
        return this.inventoryButton.getState();
    }

    public void addToCart() {
        this.inventoryButton.addToCart();
    }

    public void removeFromCart() {
        this.inventoryButton.removeFromCart();
    }

    public String getProductName() {
        return this.productName.getText();
    }

    public String getDescription() {
        return this.description.getText();
    }

    public BigDecimal getPrice() {
        return new BigDecimal(this.price.getText().replace("$", ""));
    }
}
