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
public class PrimaryHeader extends AbstractUIObject {


    @FindBy(xpath = ".//*[@id='shopping_cart_container']//*[contains(@class,'shopping_cart_link')]")
    private ExtendedWebElement shoppingCartLink;

    // present only if at lease one item is in the shopping cart
    //@Context(dependsOn = "shoppingCartLink")
    // TODO: figure why dependsOn doesn't work
    @FindBy(className = "shopping_cart_badge")
    private ExtendedWebElement shoppingCartBadge;

    @Getter
    @FindBy(id = "menu_button_container")
    private SideMenu sideMenu;

    public PrimaryHeader(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ShoppingCartPage gotoShoppingCart() {
        this.shoppingCartLink.click();
        return new ShoppingCartPage(getDriver());
    }

    /**
     * @return returns number in shopping cart badge,
     * or empty optional if badge is not present
     */
    public Optional<Integer> getShoppingCartBadgeCount() {
        if (this.shoppingCartBadge.isElementPresent()) {
            return Optional.of(Integer.parseInt(this.shoppingCartBadge.getText()));
        }
        return Optional.empty();
    }

}
