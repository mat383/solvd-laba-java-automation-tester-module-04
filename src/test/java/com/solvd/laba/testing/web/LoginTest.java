package com.solvd.laba.testing.web;

import com.solvd.laba.testing.web.pages.LoginPage;
import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
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
        WebDriver driver = getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.getLoginPrompt().typeUsername("standard_user");
        loginPage.getLoginPrompt().typePassword("secret_sauce");
        loginPage.getLoginPrompt().clickLoginButton();

    }
}
