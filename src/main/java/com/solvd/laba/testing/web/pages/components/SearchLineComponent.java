package com.solvd.laba.testing.web.pages.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchLineComponent extends AbstractUIObject {

    @FindBy(id = "searchDropdownBox")
    private ExtendedWebElement productTypeSelect;

    @FindBy(id = "twotabsearchtextbox")
    private ExtendedWebElement searchInput;

    @FindBy(id = "nav-search-submit-button")
    private ExtendedWebElement searchButton;

    public SearchLineComponent(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getProductTypeSelect() {
        return productTypeSelect;
    }

    public ExtendedWebElement getSearchInput() {
        return searchInput;
    }

    public void typeSearchInputValue(String value) {
        this.searchInput.type(value);
    }

    public String getSearchInputPlaceholder() {
        return this.searchInput.getAttribute("placeholder");
    }

    public ExtendedWebElement getSearchButton() {
        return searchButton;
    }

    public void clickSearchButton() {
        this.searchButton.click();
    }
}
