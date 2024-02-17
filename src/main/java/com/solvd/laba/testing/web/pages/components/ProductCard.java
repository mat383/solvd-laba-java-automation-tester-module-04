package com.solvd.laba.testing.web.pages.components;

import com.solvd.laba.testing.web.pages.DetailsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;

public class ProductCard extends AbstractUIObject {

    @FindBy(xpath = ".//a[contains(@id,'title_link')]")
    public ExtendedWebElement nameLink;
    @FindBy(className = "inventory_item_name")
    public ExtendedWebElement name;
    @FindBy(className = "inventory_item_desc")
    public ExtendedWebElement description;
    @FindBy(className = "inventory_item_price")
    public ExtendedWebElement price;
    @FindBy(xpath = ".//*[contains(@class,'pricebar')]//button[contains(@class,'btn_inventory')]")
    public ExtendedWebElement cartButton;

    public ProductCard(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getName() {
        return this.name.getText();
    }

    public String getDescription() {
        return this.description.getText();
    }

    public double getPrice() {
        return Double.parseDouble(this.price.getText().replace("$", ""));
    }

    public String getCartButtonLabel() {
        return this.cartButton.getText();
    }

    /**
     * informs about cart button state, and throws
     * RuntimeError when state doesn't match known states
     *
     * @return
     */
    public CartButtonState getCartButtonState() {
        return CartButtonState.ofLabel(getCartButtonLabel());
    }

    public boolean isCartButtonInKnownState() {
        return CartButtonState.isLabelKnown(getCartButtonLabel());
    }

    public void addToCart() {
        if (getCartButtonState() == CartButtonState.ADD_TO_CART) {
            this.cartButton.click();
        }
    }

    public void removeFromCart() {
        if (getCartButtonState() == CartButtonState.REMOVE_FROM_CART) {
            this.cartButton.click();
        }
    }

    public DetailsPage goToDetails() {
        this.nameLink.click();
        return new DetailsPage(getDriver());
    }

    @Getter
    public enum CartButtonState {
        ADD_TO_CART("Add to cart"),
        REMOVE_FROM_CART("Remove");

        final String label;

        CartButtonState(String label) {
            this.label = label;
        }

        /**
         * converts label to CartButtonState corresponding
         * to given label. Throws invalid argument exception
         * when label doesn't match any known state
         */
        public static CartButtonState ofLabel(String label) {
            return Arrays.stream(CartButtonState.values())
                    .filter(cartButtonState -> label.equals(cartButtonState.getLabel()))
                    .findAny()
                    .orElseThrow(() ->
                            new IllegalArgumentException(("Unknown cart button label," +
                                    " no known state matches label: '%s'").formatted(label))
                    );
        }

        public static boolean isLabelKnown(String label) {
            return Arrays.stream(CartButtonState.values())
                    .anyMatch(cartButtonState -> cartButtonState.getLabel().equals(label));
        }
    }
}
