package com.solvd.laba.testing.web.pages;

import com.solvd.laba.testing.web.pages.components.ShoppingCartButton;
import com.solvd.laba.testing.web.pages.components.SideMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ShoppingCartPage extends AbstractPage {

    @FindBy(className = "cart_item")
    private List<ShoppingCartItem> shoppingCartItems;

    @FindBy(id = "checkout")
    private ExtendedWebElement checkoutButton;
    @FindBy(id = "continue-shopping")
    private ExtendedWebElement continueShoppingButton;


    @Getter
    @FindBy(id = "shopping_cart_container")
    private ShoppingCartButton shoppingCartButton;

    @Getter
    @FindBy(id = "menu_button_container")
    private SideMenu sideMenu;


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        setPageURL("cart.html");
    }

    /**
     * goes back to inventory page (by clicking continue shopping)
     */
    public InventoryPage goBackToInventoryPage() {
        this.continueShoppingButton.click();
        return new InventoryPage(getDriver());
    }

    public CheckoutStepOnePage goToCheckout() {
        this.checkoutButton.click();
        return new CheckoutStepOnePage(getDriver());
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return Collections.unmodifiableList(this.shoppingCartItems);
    }

    public ShoppingCartItem getShoppingCartItemByName(String itemName) {
        return this.shoppingCartItems.stream()
                .filter(shoppingCartItem -> itemName.equals(shoppingCartItem.getProductName()))
                .findAny().get();
    }

    public boolean removeItemFromShoppingCart(ShoppingCartItem item) {
        item.removeFromCart();
        return isProductInShoppingCart(item);
    }

    public boolean isProductInShoppingCart(ShoppingCartItem item) {
        return isProductInShoppingCart(item.getProductName());
    }

    public boolean isProductInShoppingCart(String itemName) {
        return this.shoppingCartItems.stream()
                .map(ShoppingCartItem::getProductName)
                .anyMatch(itemName::equals);
    }

    /**
     * checks if item with specified name & price per unit
     * is in shopping cart
     */
    public boolean isProductInShoppingCart(String itemName, BigDecimal itemPrice) {
        return this.shoppingCartItems.stream()
                .anyMatch(shoppingCartItem -> itemName.equals(shoppingCartItem.getProductName())
                        && (itemPrice.compareTo(shoppingCartItem.getPrice()) == 0));
    }

    public BigDecimal getTotalPrice() {
        return this.shoppingCartItems.stream()
                .map(shoppingCartItem ->
                        shoppingCartItem.getPrice().multiply(BigDecimal.valueOf(shoppingCartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public static class ShoppingCartItem extends AbstractUIObject {

        @FindBy(className = "cart_quantity")
        private ExtendedWebElement quantity;
        @FindBy(className = "inventory_item_name")
        private ExtendedWebElement productName;
        @FindBy(className = "inventory_item_desc")
        private ExtendedWebElement description;
        @FindBy(className = "inventory_item_price")
        private ExtendedWebElement price;
        @FindBy(css = ".item_pricebar .cart_button")
        private ExtendedWebElement removeButton;

        public ShoppingCartItem(WebDriver driver, SearchContext searchContext) {
            super(driver, searchContext);
        }

        public int getQuantity() {
            return Integer.parseInt(this.quantity.getText());
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

        public void removeFromCart() {
            this.removeButton.click();
        }
    }
}
