package erpDemoTest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static erpDemoTest.stepDefs.Hooks.driver;

public class P01_orderProduct {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    /**************************
     ***** Public methods *****
     **************************/
    public void userRedirected(String navigateToUrl) {
        driver.navigate().to(navigateToUrl);
    }
    public WebElement columnSelection(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(By.className("o-autocomplete--dropdown-menu")));
        return driver.findElement(By.linkText(name));
    }

    /**************************
     ****** Used methods ******
     **************************/
    public WebElement loginMail() {
        return driver.findElement(By.id("login"));
    }
    public WebElement loginPass() {
        return driver.findElement(By.id("password"));
    }
    public WebElement clickLoginBtn() {
        return driver.findElement(By.className("btn-primary"));
    }
    public WebElement clickInventory() {
        return driver.findElement(By.id("result_app_9"));
    }
    public List<WebElement> clickOperations() {
        return driver.findElements(By.xpath("//header/nav/div/div[@class=\"o-dropdown dropdown o-dropdown--no-caret\"]"));
    }
    public WebElement clickTransfer() {
        return driver.findElement(By.linkText("Transfers"));
    }
    public WebElement addNewTransfer() {
        return driver.findElement(By.className("o_list_button_add"));
    }
    public WebElement operationType() {
        return driver.findElement(By.id("picking_type_id"));
    }
    public WebElement selectVendorName() {
        return driver.findElement(By.id("partner_id"));
    }
    public WebElement addANewLine() {
        return driver.findElement(By.cssSelector("td[class=\"o_field_x2many_list_row_add\"] a[href=\"#\"]"));
    }
    public WebElement selectProductLine() {
        return driver.findElement(By.cssSelector("[name=\"product_id\"] > [class=\"o_field_many2one_selection\"] [class=\"o-autocomplete--input o_input\"]"));
    }
    public WebElement quantity() {
        return driver.findElement(By.xpath("//input[@inputmode=\"decimal\"]"));
        //return driver.findElement(By.name("product_uom_qty"));
    }
    public WebElement markToDo() {
        return driver.findElement(By.name("action_confirm"));
    }
    public WebElement setQuantities(){
        wait.until(ExpectedConditions.elementToBeClickable(By.name("action_set_quantities_to_reservation")));
        return driver.findElement(By.name("action_set_quantities_to_reservation"));
    }
    public WebElement validate() {
        return driver.findElement(By.cssSelector("button[name=\"button_validate\"] span"));
    }
    public String storeValidationID() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("name"))));
        return driver.findElement(By.name("name")).getText();
    }
    public WebElement clickAccountModule() {
        return driver.findElement(By.id("result_app_4"));
    }
    public WebElement clickAccount() {
        return driver.findElement(By.cssSelector("span[data-section=\"126\"]"));
    }
    public WebElement clickJournalEntries() {
        return driver.findElement(By.cssSelector("a[href=\"#menu_id=128&action=229\"]"));
    }
    public WebElement assertProductID(String validationId) {
        return driver.findElement(By.cssSelector("[data-tooltip=\"" + validationId + "\"]"));
    }
    public WebElement assertReceiptJournal() {
        return driver.findElement(By.name("journal_div"));
    }
    public String getValueOfTotal() {
        return driver.findElement(By.cssSelector("td[class=\"o_list_number text-end\"]")).getText();
    }
    public List<WebElement> assertStockDebitAndCredit(String index) {
        return driver.findElements(By.cssSelector("tbody >tr:nth-child(" + index +") >td"));
    }
}