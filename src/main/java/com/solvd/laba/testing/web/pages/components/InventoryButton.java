package com.solvd.laba.testing.web.pages.components;

import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

public class InventoryButton extends AbstractUIObject {
    public InventoryButton(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getLabel() {
        return this.getRootExtendedElement().getText();
    }

    /**
     * informs about cart button state, and throws
     * RuntimeError when state doesn't match known states
     */
    public ButtonState getState() {
        return ButtonState.ofLabel(getLabel());
    }

    public void addToCart() {
        if (getState() == ButtonState.ADD_TO_CART) {
            this.getRootExtendedElement().click();
        }
    }

    public void removeFromCart() {
        if (getState() == ButtonState.REMOVE_FROM_CART) {
            this.getRootExtendedElement().click();
        }
    }

    @Getter
    public enum ButtonState {
        ADD_TO_CART("Add to cart"),
        REMOVE_FROM_CART("Remove");

        final String label;

        ButtonState(String label) {
            this.label = label;
        }

        /**
         * converts label to CartButtonState corresponding
         * to given label. Throws invalid argument exception
         * when label doesn't match any known state
         */
        public static ButtonState ofLabel(String label) {
            return Arrays.stream(ButtonState.values())
                    .filter(buttonState -> label.equals(buttonState.getLabel()))
                    .findAny()
                    .orElseThrow(() ->
                            new IllegalArgumentException(("Unknown cart button label," +
                                    " no known state matches label: '%s'").formatted(label))
                    );
        }
    }
}
