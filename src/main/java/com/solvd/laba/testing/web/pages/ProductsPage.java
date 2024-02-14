package com.solvd.laba.testing.web.pages;

import com.solvd.laba.testing.web.pages.components.PrimaryHeader;
import com.solvd.laba.testing.web.pages.components.sortOrderSelector;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends AbstractPage {

    @Getter
    @FindBy(xpath = "//*[@id='header_container']//*[contains(@class,'primary_header')]")
    private PrimaryHeader primaryHeader;

    @FindBy(xpath = "//*[@id='header_container']//*[contains(@class,'header_secondary_container')]" +
            "//*[contains(@class,'select_container')]")
    private sortOrderSelector sortOrderSelector;

    @FindBy(id = "inventory_container")
    private ExtendedWebElement inventoryContainer;

    public ProductsPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(inventoryContainer);
    }

    public boolean availableSortOrdersMatchExpected() {
        return this.sortOrderSelector.availableSortOrdersMatchExpected();
    }

    public boolean setSortOrder(sortOrderSelector.PredefinedSortOrder predefinedSortOrder) {
        return this.sortOrderSelector.setSortOrder(predefinedSortOrder);
    }
}
