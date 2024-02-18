package com.solvd.laba.testing.web;

import com.solvd.laba.testing.web.pages.InventoryPage;
import com.solvd.laba.testing.web.pages.LoginPage;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.Test;

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
}
