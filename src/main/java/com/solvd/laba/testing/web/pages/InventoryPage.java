package com.solvd.laba.testing.web.pages;

import com.solvd.laba.testing.web.pages.components.PrimaryHeader;
import com.solvd.laba.testing.web.pages.components.ProductCard;
import com.solvd.laba.testing.web.pages.components.sortOrderSelector;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Collections;
import java.util.List;

public class InventoryPage extends AbstractPage {

    @Getter
    @FindBy(xpath = "//*[@id='header_container']//*[contains(@class,'primary_header')]")
    private PrimaryHeader primaryHeader;

    @FindBy(xpath = "//*[@id='header_container']//*[contains(@class,'header_secondary_container')]" +
            "//*[contains(@class,'select_container')]")
    private sortOrderSelector sortOrderSelector;

    @FindBy(id = "inventory_container")
    private ExtendedWebElement inventoryContainer;

    @FindBy(css = "#inventory_container .inventory_item")
    private List<ProductCard> productCards;

    public InventoryPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(inventoryContainer);
    }

    public boolean availableSortOrdersMatchExpected() {
        return this.sortOrderSelector.availableSortOrdersMatchExpected();
        //TODO: is this method needed, or would it be better to just provide getSortOrderSelector?
    }

    public boolean setSortOrder(sortOrderSelector.PredefinedSortOrder predefinedSortOrder) {
        return this.sortOrderSelector.setSortOrder(predefinedSortOrder);
        //TODO: is this method needed, or would it be better to just provide getSortOrderSelector?
    }

    public List<ProductCard> getProductCards() {
        return Collections.unmodifiableList(this.productCards);
    }
}
