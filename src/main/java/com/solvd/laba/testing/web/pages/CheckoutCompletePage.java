package com.solvd.laba.testing.web.pages;

import com.solvd.laba.testing.web.pages.components.ShoppingCartButton;
import com.solvd.laba.testing.web.pages.components.SideMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends AbstractPage {

    @FindBy(id = "back-to-products")
    private ExtendedWebElement backHomeButton;

    @Getter
    @FindBy(id = "shopping_cart_container")
    private ShoppingCartButton shoppingCartButton;

    @Getter
    @FindBy(id = "menu_button_container")
    private SideMenu sideMenu;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
        setPageURL("checkout-complete.html");
    }

    public InventoryPage goBackToInventoryPage() {
        this.backHomeButton.click();
        return new InventoryPage(getDriver());
    }
}
