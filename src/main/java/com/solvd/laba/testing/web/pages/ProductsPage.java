package com.solvd.laba.testing.web.pages;

import com.solvd.laba.testing.web.pages.components.PrimaryHeader;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends AbstractPage {

    @Getter
    @FindBy(xpath = "//*[@id='header_container']//*[contains(@class,'primary_header')]")
    private PrimaryHeader primaryHeader;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }
}
