package com.solvd.laba.testing.web.pages;

import com.solvd.laba.testing.web.pages.components.Header;
import com.zebrunner.carina.utils.config.Configuration;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//header")
    private Header header;

    public HomePage(WebDriver driver) {
        super(driver);
        //setPageAbsoluteURL(Configuration.getRequired("home_url"));
    }

    @Override
    public void open() {
        System.out.println(Configuration.getRequired("home_url"));
        openURL(Configuration.getRequired("home_url"));
    }

    public Header getHeader() {
        return header;
    }
}
