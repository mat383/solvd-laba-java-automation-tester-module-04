package com.solvd.laba.testing.web;

import com.solvd.laba.testing.web.pages.InventoryPage;
import com.solvd.laba.testing.web.pages.LoginPage;
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


        InventoryPage inventoryPage = loginPage.logIn("standard_user", "secret_sauce").orElse(null);
        Assert.assertNotNull(inventoryPage, "login unsuccessful");

        for (var productCard : inventoryPage.getProductCards()) {
            productCard.addToCart();
            System.out.printf("- %s :: %s\n", productCard.getName(), Double.toString(productCard.getPrice()));
            System.out.printf("  %s\n", productCard.getDescription());
            System.out.printf("  in card: %s\n", productCard.getCartButtonState());
        }

        Assert.assertTrue(inventoryPage.sort(InventoryPage.SortOrder.SORT_A_TO_Z), "sort A->Z failed");
        Assert.assertTrue(inventoryPage.sort(InventoryPage.SortOrder.SORT_Z_TO_A), "sort Z->A failed");
        Assert.assertTrue(inventoryPage.sort(InventoryPage.SortOrder.SORT_FROM_LOWEST_PRICE), "sort from cheapest failed");
        Assert.assertTrue(inventoryPage.sort(InventoryPage.SortOrder.SORT_FROM_HIGHEST_PRICE), "sort from most expensive failed");


        for (var productCard : inventoryPage.getProductCards()) {
            productCard.removeFromCart();
            System.out.printf("- %s :: %s\n", productCard.getName(), Double.toString(productCard.getPrice()));
            System.out.printf("  %s\n", productCard.getDescription());
            System.out.printf("  in card: %s\n", productCard.getCartButtonState());
        }

        //System.out.println(inventoryPage.getPrimaryHeader().getSideMenu().isOpened());
        inventoryPage.getSideMenu().logOut();
        //inventoryPage.getPrimaryHeader().getSideMenu().openSideMenu();
        //LoginPage shoppingCartPage = inventoryPage.getPrimaryHeader().getSideMenu().logOut();
        System.out.println();
    }
}
