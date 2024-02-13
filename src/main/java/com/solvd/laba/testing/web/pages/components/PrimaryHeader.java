package com.solvd.laba.testing.web.pages.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import com.zebrunner.carina.webdriver.locator.Context;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;


public class PrimaryHeader extends AbstractUIObject {


    @FindBy(xpath = ".//*[@id='shopping_cart_container']//*[contains(@class,'shopping_cart_link']")
    private ExtendedWebElement shoppingCartLink;

    // present only if at lease one item is in the shopping cart
    @Context(dependsOn = "shoppingCartLink")
    @FindBy(className = "shopping_cart_badge")
    private ExtendedWebElement shoppingCartBadge;

    @FindBy(id = "react-burger-menu-btn")
    private ExtendedWebElement sideMenuOpenButton;

    public PrimaryHeader(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }
}
