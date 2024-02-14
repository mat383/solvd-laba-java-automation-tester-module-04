package com.solvd.laba.testing.web;

import com.solvd.laba.testing.web.pages.DetailsPage;
import com.solvd.laba.testing.web.pages.LoginPage;
import com.solvd.laba.testing.web.pages.ProductsPage;
import com.solvd.laba.testing.web.pages.components.sortOrderSelector;
import com.zebrunner.carina.core.AbstractTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends AbstractTest {

    /**
     * Validate login-logout process with valid credentials
     * <p>
     * Steps:
     * 1. Open login page - expected result: login page loads
     * 2. Type in valid username - expected result: valid username is inserted into username field
     * 3. Type in valid password - expected result: valid password is inserted into password field
     * 4.
     */
    @Test
    public void verifyValidLoginLogoutTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        loginPage.isPageOpened();


        ProductsPage productsPage = loginPage.logIn("standard_user", "secret_sauce").orElse(null);
        Assert.assertNotNull(productsPage, "login unsuccessful");

        for (var productCard : productsPage.getProductCards()) {
            productCard.addToCart();
            System.out.printf("- %s :: %s\n", productCard.getName(), Double.toString(productCard.getPrice()));
            System.out.printf("  %s\n", productCard.getDescription());
            System.out.printf("  in card: %s\n", Boolean.toString(productCard.isInCard()));
        }

        System.out.println(productsPage.setSortOrder(sortOrderSelector.PredefinedSortOrder.SORT_FROM_HIGHEST_PRICE));

        for (var productCard : productsPage.getProductCards()) {
            productCard.addToCart();
            System.out.printf("- %s :: %s\n", productCard.getName(), Double.toString(productCard.getPrice()));
            System.out.printf("  %s\n", productCard.getDescription());
            System.out.printf("  in card: %s\n", Boolean.toString(productCard.isInCard()));
        }

        DetailsPage detailsPage = productsPage.getProductCards().get(3).gotoDetails();
    }
}
