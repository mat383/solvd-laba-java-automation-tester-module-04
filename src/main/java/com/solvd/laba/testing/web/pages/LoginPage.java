package com.solvd.laba.testing.web.pages;

import com.solvd.laba.testing.web.pages.components.LoginPrompt;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage {

    @Getter
    @FindBy(id = "login_button_container")
    private LoginPrompt loginPrompt;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        openURL(Configuration.getRequired("main_url"));
    }
}
