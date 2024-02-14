package com.solvd.laba.testing.web.pages.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class sortOrderSelector extends AbstractUIObject {

    //TODO: try to use sortContainer and dependsOn

    @FindBy(className = "active_option")
    private ExtendedWebElement activeSortOptionLabel;

    @FindBy(tagName = "select")
    private ExtendedWebElement sortOptionSelector;

    @FindBy(tagName = "option")
    private List<SortOrder> sortOptions;

    public sortOrderSelector(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    /**
     * checks if expected sort orders (specified in SortOrder enum)
     * matches ones listed on site
     */
    public boolean availableSortOrdersMatchExpected() {
        List<PredefinedSortOrder> predefinedSortOrders = getExpectedSortOrders();
        List<SortOrder> sortOrdersToVerify = List.copyOf(this.sortOptions);
        if (sortOrdersToVerify.size() != predefinedSortOrders.size()) {
            return false;
        }

        // try to find sort order matching each predefined sort order
        for (PredefinedSortOrder predefinedSortOrder : predefinedSortOrders) {
            boolean foundMatching = false;
            for (SortOrder actualSortOrder : sortOrdersToVerify) {
                if (actualSortOrder.matchesPredefinedSortOrder(predefinedSortOrder)) {
                    foundMatching = true;
                    break;
                }
            }
            if (!foundMatching) {
                return false;
            }
        }
        return true;
    }

    public List<PredefinedSortOrder> getExpectedSortOrders() {
        return List.of(PredefinedSortOrder.values());
    }

    /**
     * @return returns true if sort order in sort order selection window
     * matches specified sort order (doesn't check if elements are sorted properly)
     */
    public boolean setSortOrder(PredefinedSortOrder predefinedSortOrder) {
        this.sortOptionSelector.click();
        this.sortOptionSelector.select(predefinedSortOrder.getFullName());
        return getCurrentSortOrderFullName().equals(predefinedSortOrder.getFullName());
    }

    public String getCurrentSortOrderFullName() {
        return activeSortOptionLabel.getText();
    }

    /**
     * sort orders expected to be available on this site
     */
    @Getter
    public enum PredefinedSortOrder {
        SORT_A_TO_Z("az", "Name (A to Z)"),
        SORT_Z_TO_A("za", "Name (Z to A)"),
        SORT_FROM_LOWEST_PRICE("lohi", "Price (low to high)"),
        SORT_FROM_HIGHEST_PRICE("hilo", "Price (high to low)");

        private final String shortName;
        private final String fullName;

        PredefinedSortOrder(String shortName, String fullName) {
            this.shortName = shortName;
            this.fullName = fullName;
        }
    }
}
