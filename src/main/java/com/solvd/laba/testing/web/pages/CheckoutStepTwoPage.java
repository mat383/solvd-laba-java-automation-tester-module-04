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
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

public class CheckoutStepTwoPage extends AbstractPage {
    // tax amount, tax is calculated by multiplying by this number
    // so 0.5 is 50% tax
    private static final BigDecimal TAX_AMOUNT = new BigDecimal("0.08");
    @FindBy(className = "cart_item")
    private List<CheckoutItem> checkoutItems;
    @FindBy(className = "summary_subtotal_label")
    private ExtendedWebElement priceWithoutTax;
    @FindBy(className = "summary_tax_label")
    private ExtendedWebElement tax;
    @FindBy(className = "summary_total_label")
    private ExtendedWebElement priceWithTax;

    @FindBy(id = "cancel")
    private ExtendedWebElement cancelButton;
    @FindBy(id = "finish")
    private ExtendedWebElement finishButton;


    @Getter
    @FindBy(id = "shopping_cart_container")
    private ShoppingCartButton shoppingCartButton;

    @Getter
    @FindBy(id = "menu_button_container")
    private SideMenu sideMenu;

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
        setPageURL("checkout-step-two.html");
    }

    public InventoryPage cancelCheckout() {
        this.cancelButton.click();
        return new InventoryPage(getDriver());
    }

    public CheckoutCompletePage finalizeCheckout() {
        this.finishButton.click();
        return new CheckoutCompletePage(getDriver());
    }

    public BigDecimal getTotalPriceWithoutTax() {
        return extractPriceFromString(this.priceWithoutTax.getText());
    }

    public BigDecimal getTax() {
        return extractPriceFromString(this.tax.getText());
    }

    public BigDecimal getTotalPriceWithTax() {
        return extractPriceFromString(this.priceWithTax.getText());
    }

    public boolean isPriceCalculationCorrect() {
        BigDecimal calculatedTotalNoTax = this.checkoutItems.stream()
                .map(CheckoutItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        if (calculatedTotalNoTax.compareTo(getTotalPriceWithoutTax()) != 0) {
            return false;
        }

        BigDecimal expectedTax = calculatedTotalNoTax.multiply(TAX_AMOUNT).setScale(2, RoundingMode.HALF_UP);

        if (expectedTax.compareTo(getTax()) != 0) {
            return false;
        }

        return calculatedTotalNoTax.add(expectedTax).compareTo(getTotalPriceWithTax()) == 0;
    }

    public List<CheckoutItem> getCheckoutItems() {
        return Collections.unmodifiableList(this.checkoutItems);
    }

    public boolean orderContains(CheckoutItem item) {
        return orderContains(item.getProductName());
    }

    public boolean orderContains(String itemName) {
        return this.checkoutItems.stream()
                .map(CheckoutItem::getProductName)
                .anyMatch(itemName::equals);
    }

    /**
     * checks if order contains specified item with specified price per unit
     */
    public boolean orderContains(String itemName, BigDecimal itemPrice) {
        return this.checkoutItems.stream()
                .anyMatch(checkoutItem -> itemName.equals(checkoutItem.getProductName())
                        && (itemPrice.compareTo(checkoutItem.getPricePerItem()) == 0));
    }


    /**
     * extracts price from string - takes everything after $ sign
     */
    private static BigDecimal extractPriceFromString(String text) {
        String amountAsString = text.substring(text.indexOf("$") + 1);
        return new BigDecimal(amountAsString);
    }


    public static class CheckoutItem extends AbstractUIObject {
        @FindBy(className = "cart_quantity")
        private ExtendedWebElement quantity;
        @FindBy(className = "inventory_item_name")
        private ExtendedWebElement productName;
        @FindBy(className = "inventory_item_desc")
        private ExtendedWebElement description;
        @FindBy(className = "inventory_item_price")
        private ExtendedWebElement price;

        public CheckoutItem(WebDriver driver, SearchContext searchContext) {
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

        public BigDecimal getPricePerItem() {
            return extractPriceFromString(this.price.getText());
        }

        /**
         * @return total price of this entry (unit price * quantity)
         */
        public BigDecimal getTotalPrice() {
            return getPricePerItem().multiply(BigDecimal.valueOf(getQuantity()));
        }
    }
}
