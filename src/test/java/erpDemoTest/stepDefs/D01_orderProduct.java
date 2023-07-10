package erpDemoTest.stepDefs;

import erpDemoTest.pages.P01_orderProduct;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.testng.asserts.SoftAssert;

public class D01_orderProduct {
    P01_orderProduct orderP = new P01_orderProduct();
    public static String projectName = "edu-Hazem";
    public String productName;
    public String productID;
    public String validationID;
    public String JournalEntryID;

    @Given("user login to database {string} {string}")
    public void loginToOdoo(String arg0, String arg1) {
        orderP.loginMail().sendKeys(arg0);
        orderP.loginPass().sendKeys(arg1);
        orderP.clickLoginBtn().click();
    }

    @When("user navigate to Transfer and add new {string}")
    public void userNavigateToReceiptsAndAddNewReceipt(String action) {
        orderP.clickInventory().click();
        orderP.clickOperations().get(0).click();
        orderP.clickTransfer().click();
        orderP.addNewTransfer().click();
        orderP.operationType().click();
        orderP.columnSelection(projectName + ": " + action).click();
    }

    @And("^user enter vendor name (.*)$")
    public void userEnterVendorName(String vendorName) {
        orderP.selectVendorName().sendKeys(vendorName);
        orderP.columnSelection(vendorName).click();
    }

    @And("^user enter valid product Name (.*) and Quantity (.*)$")
    public void userEnterValidProductData(String productName, String productQuantity) {
        // set product name to use in assertion
        this.productName = productName;
        // user adding new line for data
        orderP.addANewLine().click();

        // user enter valid product name
        orderP.selectProductLine().sendKeys(productName);
        orderP.columnSelection(productName).click();

        // user enter valid quantity of products
        // clear the field and add value to quantity
        orderP.quantity().click();
        orderP.quantity().clear();
        orderP.quantity().sendKeys(productQuantity);
        orderP.quantity().sendKeys(Keys.ENTER);
    }

    @And("user validate and get product id validation")
    public void userValidateAndWaitForSuccessfulValidation() {
        if(orderP.markToDo().isDisplayed()){
            orderP.markToDo().click();
            orderP.setQuantities().click();
            orderP.validate().click();
        }
        else
            orderP.validate().click();
        // store unique product/receipt id to make assertion
        productID = orderP.storeValidationID();
    }

    @Then("user enter journal entries")
    public void userEnterJournalEntries() {
        orderP.userRedirected("https://edu-hazem.odoo.com/web");
        orderP.clickAccountModule().click();
        orderP.clickAccount().click();
        orderP.clickJournalEntries().click();
    }

    @And("assert new receipt added with unique receipt id")
    public void newReceiptAdded() {
        SoftAssert soft = new SoftAssert();
        validationID = productID + " - " + productName;

        // assert product with validation id: receiptID + ProductName
        soft.assertEquals(orderP.assertProductID(validationID).getText(), validationID);

        soft.assertAll();
    }

    @And("assert Journal Data is {string}")
    public void assertJournalData(String arg0) {
        SoftAssert soft = new SoftAssert();
        // value sh
        // enter product Journal to check data
        orderP.assertProductID(validationID).click();
        // assert Journal added correctly
        soft.assertEquals(orderP.assertReceiptJournal().getText(), arg0);
        soft.assertAll();
    }

    @And("assert Journal Item for Account Stock Input {string}")
    public void assertJournalItemForAccountStockInput(String arg0) {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(orderP.assertStockDebitAndCredit("1").get(0).getText(), arg0);
        soft.assertAll();
    }
    @And("assert Journal Item for Debit Stock Input")
    public void assertJournalItemForDebitStockInput() {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(orderP.assertStockDebitAndCredit("1").get(3).getText(), "0.00 E£");
        soft.assertAll();
    }

    @And("assert Journal Item for Credit Stock Input")
    public void assertJournalItemForCreditStockInput() {
        SoftAssert soft = new SoftAssert();
        String valueTotal = orderP.getValueOfTotal();
        System.out.println(valueTotal);
        soft.assertEquals(orderP.assertStockDebitAndCredit("1").get(4).getText(), valueTotal + " E£");
        soft.assertAll();
    }

    @And("assert Journal Item for Account Stock Valuation {string}")
    public void assertJournalItemForAccountStockValuation(String arg0) {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(orderP.assertStockDebitAndCredit("2").get(0).getText(), arg0);
        soft.assertAll();
    }

    @And("assert Journal Item for Debit Stock Valuation")
    public void assertJournalItemForDebitStockValuation() {
        SoftAssert soft = new SoftAssert();
        String valueTotal = orderP.getValueOfTotal();
        System.out.println(valueTotal);
        soft.assertEquals(orderP.assertStockDebitAndCredit("2").get(3).getText(),valueTotal + " E£");
        soft.assertAll();
    }

    @And("assert Journal Item for Credit Stock Valuation")
    public void assertJournalItemForCreditStockValuation() {
        SoftAssert soft = new SoftAssert();
        soft.assertEquals(orderP.assertStockDebitAndCredit("2").get(4).getText(),"0.00 E£");
        soft.assertAll();
    }
}