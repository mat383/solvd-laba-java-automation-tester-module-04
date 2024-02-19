package com.solvd.laba.testing.web;

import com.solvd.laba.testing.web.pages.*;
import com.solvd.laba.testing.web.pages.components.InventoryButton;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.util.Optional;

public class WebTest extends AbstractTest {

    /**
     * Validate login-logout process with valid credentials
     * <p>
     * Steps:
     * 1. Open login page - result: login page loads
     * 2. Log in with valid credentials - result: inventory page appears
     * 3. log out - result: login page appears
     */
    @Test
    public void verifyValidLoginLogoutTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        Assert.assertTrue(loginPage.isPageOpened(), "Login page didn't open");

        InventoryPage inventoryPage = loginPage.logIn(
                        R.TESTDATA.get("standard_user_username"),
                        R.TESTDATA.get("standard_user_password"))
                .orElse(null);
        Assert.assertNotNull(inventoryPage, "login unsuccessful");

        loginPage = inventoryPage.getSideMenu().logOut();
        Assert.assertTrue(loginPage.isPageOpened(), "Log out was unsuccessful");
    }

    /**
     * verify that login with invalid credentials fails
     * <p>
     * Steps:
     * 1. Open login page - result: login page loads
     * 2. Login with invalid credentials - result: login fails and displays info about invalid credentials
     */
    @Test
    public void verifyLoginWithInvalidCredentialsTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        Assert.assertTrue(loginPage.isPageOpened(), "Login page didn't open");

        Optional<InventoryPage> inventoryPage = loginPage.logIn(
                R.TESTDATA.get("invalid_credentials_username"),
                R.TESTDATA.get("invalid_credentials_password"));
        Assert.assertTrue(inventoryPage.isEmpty(), "login was successful but failure was expected");
        Assert.assertEquals(loginPage.getState(), LoginPage.State.INVALID_CREDENTIALS);
    }

    /**
     * Validate that item sorting works correctly
     * <p>
     * Steps:
     * 1. Open login page - result: login page loads
     * 2. Log in with valid credentials - result: inventory page appears
     * 3. Sort products alphabetically a to z - result: items are in correct order
     * 4. Sort products alphabetically z to a - result: items are in correct order
     * 5. Sort products by price from highest - result: items are in correct order
     * 6. Sort products by price from lowest - result: items are in correct order
     */
    @Test
    public void verifyItemsSortingTest() {
        // login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        Assert.assertTrue(loginPage.isPageOpened(), "Login page didn't open");

        InventoryPage inventoryPage = loginPage.logIn(
                        R.TESTDATA.get("standard_user_username"),
                        R.TESTDATA.get("standard_user_password"))
                .orElse(null);
        Assert.assertNotNull(inventoryPage, "login unsuccessful");

        // test sorting
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(inventoryPage.sort(InventoryPage.SortOrder.SORT_A_TO_Z),
                "Alphabetical sorting from a to z failed");
        softAssert.assertTrue(inventoryPage.sort(InventoryPage.SortOrder.SORT_Z_TO_A),
                "Alphabetical sorting from z to a failed");
        softAssert.assertTrue(inventoryPage.sort(InventoryPage.SortOrder.SORT_FROM_HIGHEST_PRICE),
                "Sorting by price from highest failed");
        softAssert.assertTrue(inventoryPage.sort(InventoryPage.SortOrder.SORT_FROM_LOWEST_PRICE),
                "Sorting by price from lowest failed");
        softAssert.assertAll("sorting failed");
    }

    /**
     * Validate that checkout process works correctly
     * <p>
     * Steps:
     * 1. Open login page - result: login page loads
     * 2. Log in with valid credentials - result: inventory page appears
     * 3. Add first two items to cart - result: items are added to cart, indicated by buttons in items & cart badge
     * 4. Go to shopping cart page - result: shopping cart page opens, containing added products
     * 5. Go through first step of checkout inserting test data - result: second step of checkout appears
     * with purchased items, and properly calculated total price and tax
     * 6. Finish checkout process - result: checkout complete page appears
     * 7. Go back to inventory - result: inventory page appears with no items in the cart
     */
    @Test
    public void verifyCheckoutProcessTest() {
        // login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        Assert.assertTrue(loginPage.isPageOpened(), "Login page didn't open");

        InventoryPage inventoryPage = loginPage.logIn(
                        R.TESTDATA.get("standard_user_username"),
                        R.TESTDATA.get("standard_user_password"))
                .orElse(null);
        Assert.assertNotNull(inventoryPage, "login unsuccessful");
        inventoryPage.assertPageOpened();

        // add two items to cart
        SoftAssert softAssert = new SoftAssert();

        InventoryPage.ProductCard firstProductInventoryCard = inventoryPage.getProductCards().get(0);
        String firstProductName = firstProductInventoryCard.getProductName();
        BigDecimal firstProductPrice = firstProductInventoryCard.getPrice();
        firstProductInventoryCard.addToCart();
        softAssert.assertEquals(firstProductInventoryCard.getCartButtonState(), InventoryButton.ButtonState.REMOVE_FROM_CART,
                "Add to cart button (first product) didn't change state");

        InventoryPage.ProductCard secondProductInventoryCard = inventoryPage.getProductCards().get(1);
        String secondProductName = secondProductInventoryCard.getProductName();
        BigDecimal secondProductPrice = secondProductInventoryCard.getPrice();
        secondProductInventoryCard.addToCart();
        softAssert.assertEquals(secondProductInventoryCard.getCartButtonState(), InventoryButton.ButtonState.REMOVE_FROM_CART,
                "Add to cart button (second product) didn't change state");

        softAssert.assertEquals(inventoryPage.getShoppingCartButton().getShoppingCartBadgeCount().get(),
                Integer.valueOf(2),
                "Cart badge indicating number of item in cart didn't match expected number");

        // go to cart & validate products
        ShoppingCartPage shoppingCartPage = inventoryPage.getShoppingCartButton().goToShoppingCart();
        shoppingCartPage.assertPageOpened();

        softAssert.assertTrue(shoppingCartPage.isProductInShoppingCart(firstProductName, firstProductPrice),
                "Cannot find first product with same price in the shopping cart");
        softAssert.assertTrue(shoppingCartPage.isProductInShoppingCart(secondProductName, secondProductPrice),
                "Cannot find second product with same price in the shopping cart");


        // go through checkout
        CheckoutStepOnePage checkoutStepOnePage = shoppingCartPage.goToCheckout();
        checkoutStepOnePage.assertPageOpened();
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.continueCheckout(
                R.TESTDATA.get("standard_user_first_name"),
                R.TESTDATA.get("standard_user_last_name"),
                R.TESTDATA.get("standard_user_postal_code"));
        checkoutStepTwoPage.assertPageOpened();

        softAssert.assertTrue(checkoutStepTwoPage.orderContains(firstProductName, firstProductPrice),
                "Cannot find first product with the same price in the checkout");
        softAssert.assertTrue(checkoutStepTwoPage.orderContains(secondProductName, secondProductPrice),
                "Cannot find second product with the same price in the checkout");
        softAssert.assertTrue(checkoutStepTwoPage.isPriceCalculationCorrect(),
                "Invalid order cost calculations at second step of checkout");

        // finalize order
        CheckoutCompletePage checkoutCompletePage = checkoutStepTwoPage.finalizeCheckout();
        checkoutCompletePage.assertPageOpened();

        // go back to inventory
        inventoryPage = checkoutCompletePage.goBackToInventoryPage();
        inventoryPage.assertPageOpened();

        // check if products appear to be out of shopping cart
        inventoryPage.getProductCards().stream()
                .filter(productCard -> firstProductName.equals(productCard.getProductName())
                        || secondProductName.equals(productCard.getProductName()))
                .forEach(productCard -> softAssert.assertEquals(
                        productCard.getCartButtonState(),
                        InventoryButton.ButtonState.ADD_TO_CART));
        softAssert.assertTrue(inventoryPage.getShoppingCartButton().getShoppingCartBadgeCount().isEmpty(),
                "shopping cart badge indicate that there is something in the cart");

        softAssert.assertAll();
    }


    /**
     * validate ordering item from item details page
     * <p>
     * Steps:
     * 1. Open login page - result: login page loads
     * 2. Log in with valid credentials - result: inventory page appears
     * 3. Go to details page of the first item - result: details page appears with same price and item name
     * 4. Add item to cart - result: button for adding to cart changes state to REMOVE_FROM_CART and badge appears on
     * shopping cart with number one
     * 5. Go to shopping cart page - result: shopping cart page opens, containing added product
     * 6. Go through first step of checkout inserting test data - result: second step of checkout appears
     * with purchased items, and properly calculated total price and tax
     * 8. Finish checkout process - result: checkout complete page appears
     * 9. Go back to inventory - result: inventory page appears with no items in the cart
     */
    @Test
    public void validateOrderFromDetailsPageTest() {
        // login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        Assert.assertTrue(loginPage.isPageOpened(), "Login page didn't open");

        InventoryPage inventoryPage = loginPage.logIn(
                        R.TESTDATA.get("standard_user_username"),
                        R.TESTDATA.get("standard_user_password"))
                .orElse(null);
        Assert.assertNotNull(inventoryPage, "login unsuccessful");
        inventoryPage.assertPageOpened();

        // select first product from list and save its name and price
        SoftAssert softAssert = new SoftAssert();

        InventoryPage.ProductCard productInventoryCard = inventoryPage.getProductCards().getFirst();
        String productName = productInventoryCard.getProductName();
        BigDecimal productPrice = productInventoryCard.getPrice();

        // go to product details
        DetailsPage detailsPage = productInventoryCard.goToDetails();
        detailsPage.assertPageOpened();

        detailsPage.addToCart();
        softAssert.assertEquals(detailsPage.getCartButtonState(), InventoryButton.ButtonState.REMOVE_FROM_CART,
                "Add to cart button didn't reflect adding to cart");
        softAssert.assertEquals(detailsPage.getShoppingCartButton().getShoppingCartBadgeCount().get(), Integer.valueOf(1),
                "Shopping cart badge didn't reflect adding to cart");

        // go to cart & validate product
        ShoppingCartPage shoppingCartPage = detailsPage.getShoppingCartButton().goToShoppingCart();
        shoppingCartPage.assertPageOpened();

        softAssert.assertTrue(shoppingCartPage.isProductInShoppingCart(productName, productPrice),
                "Cannot find product with same price in the shopping cart");

        // go through checkout
        CheckoutStepOnePage checkoutStepOnePage = shoppingCartPage.goToCheckout();
        checkoutStepOnePage.assertPageOpened();
        CheckoutStepTwoPage checkoutStepTwoPage = checkoutStepOnePage.continueCheckout(
                R.TESTDATA.get("standard_user_first_name"),
                R.TESTDATA.get("standard_user_last_name"),
                R.TESTDATA.get("standard_user_postal_code"));
        checkoutStepTwoPage.assertPageOpened();

        softAssert.assertTrue(checkoutStepTwoPage.orderContains(productName, productPrice),
                "Cannot find product with the same price in the checkout");
        softAssert.assertTrue(checkoutStepTwoPage.isPriceCalculationCorrect(),
                "Invalid order cost calculations at second step of checkout");

        // finalize order
        CheckoutCompletePage checkoutCompletePage = checkoutStepTwoPage.finalizeCheckout();
        checkoutCompletePage.assertPageOpened();

        // go back to inventory
        inventoryPage = checkoutCompletePage.goBackToInventoryPage();
        inventoryPage.assertPageOpened();

        // check if product appear to be out of shopping cart
        inventoryPage.getProductCards().stream()
                .filter(productCard -> productName.equals(productCard.getProductName()))
                .forEach(productCard -> softAssert.assertEquals(
                        productCard.getCartButtonState(),
                        InventoryButton.ButtonState.ADD_TO_CART));
        softAssert.assertTrue(inventoryPage.getShoppingCartButton().getShoppingCartBadgeCount().isEmpty(),
                "shopping cart badge indicate that there is something in the cart");

        softAssert.assertAll();
    }
}

