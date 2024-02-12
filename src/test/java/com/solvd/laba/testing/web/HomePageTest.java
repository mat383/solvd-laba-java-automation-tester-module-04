package com.solvd.laba.testing.web;

import com.solvd.laba.testing.web.pages.HomePage;
import com.solvd.laba.testing.web.pages.components.SearchLineComponent;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageTest extends AbstractTest {

    @Test
    public void verifySearchLineTest() {
        SoftAssert sa = new SoftAssert();
        WebDriver webDriver = getDriver();
        HomePage page = new HomePage(webDriver);
        page.open();


        SearchLineComponent searchLineComponent = page.getHeader().getSearchLineComponent();

        sa.assertTrue(searchLineComponent.getProductTypeSelect().isPresent(1), "Product type select is not present");
        Assert.assertTrue(page.getHeader().getRootExtendedElement().isPresent(), "Header root is not present");
        Assert.assertTrue(searchLineComponent.getRootExtendedElement().isPresent(1), "Search root is not present");
        Assert.assertTrue(searchLineComponent.getSearchButton().isPresent(1), "Search button is not present");
        Assert.assertTrue(searchLineComponent.getSearchInput().isPresent(10), "Search input is not present");
        sa.assertEquals(searchLineComponent.getSearchInputPlaceholder(), "Search Amazon");
        searchLineComponent.typeSearchInputValue("iphone");
        searchLineComponent.clickSearchButton();

        System.out.println("");
    }
}
