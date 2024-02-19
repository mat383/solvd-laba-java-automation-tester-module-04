package com.solvd.laba.testing.web.pages.components;

import com.solvd.laba.testing.web.pages.ShoppingCartPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;


@Getter
public class ShoppingCartButton extends AbstractUIObject {


    @FindBy(className = "shopping_cart_link")
    private ExtendedWebElement shoppingCartLink;

    // present only if at lease one item is in the shopping cart
    //@Context(dependsOn = "shoppingCartLink")
    // TODO: figure why dependsOn doesn't work
    @FindBy(className = "shopping_cart_badge")
    private ExtendedWebElement shoppingCartBadge;

    public ShoppingCartButton(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    /**
     * opens shopping cart page
     */
    public ShoppingCartPage goToShoppingCart() {
        this.shoppingCartLink.click();
        return new ShoppingCartPage(getDriver());
    }

    /**
     * @return returns number in shopping cart badge,
     * or empty optional if badge is not present
     */
    public Optional<Integer> getShoppingCartBadgeCount() {
        // TODO: rename
        if (this.shoppingCartBadge.isElementPresent()) {
            return Optional.of(Integer.parseInt(this.shoppingCartBadge.getText()));
        }
        return Optional.empty();
    }

}
