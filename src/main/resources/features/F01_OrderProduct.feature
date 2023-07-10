@regression
Feature: F01_orderProduct | user could add a product to inventory with receipt
  Scenario Outline: Check receipt is added successfully with valid credentials
    Given user login to database "edutestodoo@teml.net" "123123"
    When user navigate to Transfer and add new "Receipts"
    And user enter vendor name <vendorName>
    And user enter valid product Name <productName> and Quantity <productQuantity>
    And user validate and get product id validation
    And user enter journal entries
    Then assert new receipt added with unique receipt id
    And assert Journal Data is "Inventory Valuation"
    And assert Journal Item for Account Stock Input "3000 Stock Input"
    And assert Journal Item for Debit Stock Input
    And assert Journal Item for Credit Stock Input
    And assert Journal Item for Account Stock Valuation "1000 Stock Valuation"
    And assert Journal Item for Debit Stock Valuation
    And assert Journal Item for Credit Stock Valuation

    Examples:
      |vendorName|productName|productQuantity|
      |Administrator|000 Product|30         |


  Scenario Outline: Check Delivery is added successfully with valid credentials
    Given user login to database "edutestodoo@teml.net" "123123"
    When user navigate to Transfer and add new "Delivery Orders"
    And user enter vendor name <vendorName>
    And user enter valid product Name <productName> and Quantity <productQuantity>
    And user validate and get product id validation
    And user enter journal entries
    Then assert new receipt added with unique receipt id
    And assert Journal Data is "Inventory Valuation"
    And assert Journal Item for Account Stock Input "1000 Stock Valuation"
    And assert Journal Item for Debit Stock Input
    And assert Journal Item for Credit Stock Input
    And assert Journal Item for Account Stock Valuation "2000 Stock Output"
    And assert Journal Item for Debit Stock Valuation
    And assert Journal Item for Credit Stock Valuation

    Examples:
    |vendorName|productName|productQuantity|
    |Administrator|000 Product|33          |