package com.solvd.laba.testing.web.pages;

import com.solvd.laba.testing.web.pages.components.InventoryButton;
import com.solvd.laba.testing.web.pages.components.ShoppingCartButton;
import com.solvd.laba.testing.web.pages.components.SideMenu;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.decorator.PageOpeningStrategy;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public class InventoryPage extends AbstractPage {
    @FindBy(css = ".header_secondary_container .select_container .active_option")
    private ExtendedWebElement activeSortOrderLabel;

    @FindBy(css = ".header_secondary_container .select_container .product_sort_container")
    private ExtendedWebElement sortOrderSelector;

    @FindBy(id = "inventory_container")
    private ExtendedWebElement inventoryContainer;

    @FindBy(css = "#inventory_container .inventory_item")
    private List<ProductCard> productCards;


    @Getter
    @FindBy(id = "shopping_cart_container")
    private ShoppingCartButton shoppingCartButton;

    @Getter
    @FindBy(id = "menu_button_container")
    private SideMenu sideMenu;

    public InventoryPage(WebDriver driver) {
        super(driver);
        setPageOpeningStrategy(PageOpeningStrategy.BY_ELEMENT);
        setUiLoadedMarker(inventoryContainer);
        setPageURL("inventory.html");
    }


    // TODO: simplify + write doc

    /**
     * changes sort order, returns true on
     * success, that is:
     * 1. label in sort order selector changes
     * 2. elements are sorted
     */
    public boolean sort(SortOrder sortOrder) {
        this.sortOrderSelector.click();
        this.sortOrderSelector.select(sortOrder.getFullName());
        if (!this.activeSortOrderLabel.getText().equals(sortOrder.getFullName())) {
            return false;
        }
        return isSorted(sortOrder);
    }

    public boolean isSorted(SortOrder sortOrder) {
        List<ProductCard> actualProductsOrder = getProductCards();

        if (actualProductsOrder.size() < 2) {
            return true;
        }

        ListIterator<ProductCard> iterator = actualProductsOrder.listIterator();
        ProductCard previousCard;
        ProductCard currentCard = iterator.next();

        while (iterator.hasNext()) {
            previousCard = currentCard;
            currentCard = iterator.next();
            if (sortOrder.getComparator().compare(previousCard, currentCard) > 0) {
                return false;
            }
        }

        return true;
    }

    public List<ProductCard> getProductCards() {
        return Collections.unmodifiableList(this.productCards);
    }


    /**
     * sort orders expected to be available on this site
     */
    @Getter
    public enum SortOrder {
        SORT_A_TO_Z("az", "Name (A to Z)", SortOrder::compareAlphabeticalAZ),
        SORT_Z_TO_A("za", "Name (Z to A)", SortOrder::compareAlphabeticalZA),
        SORT_FROM_LOWEST_PRICE("lohi", "Price (low to high)", SortOrder::comparePriceLowToHigh),
        SORT_FROM_HIGHEST_PRICE("hilo", "Price (high to low)", SortOrder::comparePriceHighToLow);

        private final String shortName;
        private final String fullName;
        private final Comparator<ProductCard> comparator;
        // TODO add comparator for ProductCards

        SortOrder(String shortName, String fullName, Comparator<ProductCard> comparator) {
            this.shortName = shortName;
            this.fullName = fullName;
            this.comparator = comparator;
        }

        public static int compareAlphabeticalAZ(ProductCard o1, ProductCard o2) {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.getProductName(), o2.getProductName());
        }

        public static int compareAlphabeticalZA(ProductCard o1, ProductCard o2) {
            return -1 * String.CASE_INSENSITIVE_ORDER.compare(o1.getProductName(), o2.getProductName());
        }

        public static int comparePriceHighToLow(ProductCard o1, ProductCard o2) {
            return -1 * o1.getPrice().compareTo(o2.getPrice());
        }

        public static int comparePriceLowToHigh(ProductCard o1, ProductCard o2) {
            return o1.getPrice().compareTo(o2.getPrice());
        }
    }


    public static class ProductCard extends AbstractUIObject {

        @FindBy(xpath = ".//a[contains(@id,'title_link')]")
        public ExtendedWebElement nameLink;
        @FindBy(className = "inventory_item_name")
        public ExtendedWebElement productName;
        @FindBy(className = "inventory_item_desc")
        public ExtendedWebElement description;
        @FindBy(className = "inventory_item_price")
        public ExtendedWebElement price;
        @FindBy(xpath = ".//*[contains(@class,'pricebar')]//button[contains(@class,'btn_inventory')]")
        public InventoryButton inventoryButton;

        public ProductCard(WebDriver driver, SearchContext searchContext) {
            super(driver, searchContext);
        }

        public String getProductName() {
            return this.productName.getText();
        }

        public String getDescription() {
            return this.description.getText();
        }

        public BigDecimal getPrice() {
            return new BigDecimal(this.price.getText().replace("$", ""));
        }


        /**
         * informs about cart button state (add to cart or remove from cart),
         * and throws RuntimeError when state doesn't match known states
         */
        public InventoryButton.ButtonState getCartButtonState() {
            return this.inventoryButton.getState();
        }

        public void addToCart() {
            this.inventoryButton.addToCart();
        }

        public void removeFromCart() {
            this.inventoryButton.removeFromCart();
        }

        public DetailsPage goToDetails() {
            this.nameLink.click();
            return new DetailsPage(getDriver());
        }
    }
}
