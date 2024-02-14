package com.solvd.laba.testing.web.pages.components;

import com.solvd.laba.testing.web.pages.DetailsPage;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

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

    public void addToCart() {
        if (!isInCard()) {
            this.cartButton.click();
        }
    }

    public void removeFromCart() {
        if (isInCard()) {
            this.cartButton.click();
        }
    }

    public boolean isInCard() {
        return this.cartButton.getText().equals("Remove");
    }

    public DetailsPage gotoDetails() {
        this.nameLink.click();
        return new DetailsPage(getDriver());
    }
}
