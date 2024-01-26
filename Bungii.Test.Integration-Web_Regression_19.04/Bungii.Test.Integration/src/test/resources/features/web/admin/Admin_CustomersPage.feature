@web
Feature: Admin_CustomersPage

  Background:
    Given I am logged in as TestAdmin


  @regression
  Scenario: Verify Customer Search On Customers Page
    When I click on "Customers" Menu
    Then I should be directed to "Customers Page"
    When I enter "customers" "first name" in the "Customers search" box
    Then I should see "customer first name" listed on the "Customers" page

    When I enter "customers" "last name" in the "Customers search" box
    Then I should see "customer last name" listed on the "Customers" page

  @regression
    #knownissue fixed
  Scenario: Verify Customer Search On Various Pages
    When I navigate to following pages one by one
      |Page |
      | Dashboard |
    And I enter "customers" "first name" in the "Dashboard search" box as "Testcustomertywd_appleuHWLbz"
    Then I should see "customer first name" listed on the "Dashboard" page
    When I enter "customers" "last name" in the "Dashboard search" box as "Stark"
    Then I should see "customer last name" listed on the "Dashboard" page

    When I navigate to following pages one by one
      |Page |
      |Completed Deliveries|
    And I enter "customers" "first name" in the "Deliveries search" box as "Testcustomertywd_appleuHWLbz"
    Then I should see "customer first name" listed on the "Deliveries" page
    When I enter "customers" "last name" in the "Deliveries search" box as "Stark"
    Then I should see "customer last name" listed on the "All Deliveries" page


@regression
  Scenario: Verify First and Last Name of customer and driver On Scheduled Trips and Live Trips page
    When I navigate to following pages one by one
      |Page |
      | Scheduled Deliveries |
    And I enter "customers" "first name" in the "Scheduled Deliveries search" box
    Then I should see "customer first name" listed on the "Scheduled Deliveries" page
    When I enter "customers" "last name" in the "Scheduled Deliveries search" box
    Then I should see "customer last name" listed on the "Scheduled Deliveries" page

    When I navigate to following pages one by one
      |Page |
      | Live Deliveries |
    And I enter "customers" "first name" in the "Live Deliveries search" box
    Then I should see "customer first name" listed on the "Live Deliveries" page
    When I enter "customers" "last name" in the "Live Deliveries search" box
    Then I should see "customer last name" listed on the "Live Deliveries" page
  
  
  @regression
  Scenario: Verify search by XSS script on Customers Grid
    When I navigate to following pages one by one
      |Page |
      | Dashboard |
    And I enter "<script>alert('hello')</script>" in the "Dashboard search" box
    Then the "No Customers found." message is displayed
    
  
    #CORE-2024
  @regression
  Scenario: Verify Cancel With Cancel icon beside Customer Phone and Email
    When I click on "Customers" Menu
    Then I should be directed to "Customers Page"
    When I enter "customers" "first name" in the "Customers search" box
    Then I should see "customer first name" listed on the "Customers" page
    When I view the searched customer
    When I edit "Phone" to "9800000048" and Cancel it
    And I edit "Email" to "bungiiauto48@gmail.com" and Cancel it
    When I navigate to Customer List
    When I enter "customers" "first name" in the "Customers search" box
    Then old Phone is displayed for the customer in the Customer List
    When I view the searched customer
    Then old Phone and Email is displayed for the customer

  #CORE-2024
  @regression
  Scenario: Verify Cancel With Cancel button on Update comments popup for edit Customer Phone and Email
    When I click on "Customers" Menu
    Then I should be directed to "Customers Page"
    When I enter "customers" "first name" in the "Customers search" box
    Then I should see "customer first name" listed on the "Customers" page
    When I view the searched customer
    When I edit "Phone" to "9800000048" and Cancel on Comments popup
    And I edit "Email" to "bungiiauto48@gmail.com" and Cancel on Comments popup
    When I navigate to Customer List
    When I enter "customers" "first name" in the "Customers search" box
    Then old Phone is displayed for the customer in the Customer List
    When I view the searched customer
    Then old Phone and Email is displayed for the customer
     
     #CORE-2024
  @regression
  Scenario: Verify Edit Customer Phone and Email with Blank Values
    When I click on "Customers" Menu
    Then I should be directed to "Customers Page"
    When I enter "customers" "first name" in the "Customers search" box
    Then I should see "customer first name" listed on the "Customers" page
    When I view the searched customer
    When I edit "Phone" to "" and try to save it
    Then I should see "Phone number is required." message for "Phone" field
    And I edit "Email" to "" and try to save it
    Then I should see "Email address is required." message for "Email" field
    
     #CORE-2024
  @regression
    #Stable
  Scenario: Verify Edit Customer Phone and Email
    When I click on "Customers" Menu
    Then I should be directed to "Customers Page"
    When I enter "customers" "first name" in the "Customers search" box
    Then I should see "customer first name" listed on the "Customers" page
    When I view the searched customer
    When I edit "Phone" to "9700000004" and save it
    And I edit "Email" to "bungiiautoo585@gmail.com" and save it
    And I wait for "2" mins
    When I navigate to Customer List
    When I enter "customers" "first name" in the "Customers search" box
    Then updated Phone is displayed for the customer in the Customer List
    When I click on "Customers" Menu
    And I enter "customers" "first name" in the "Customers search" box
    And I view the searched customer
    And I edit "Phone" to "9000000004" and save it
    And I edit "Email" to "bungiiauto585@gmail.com" and save it
    And I wait for "2" mins
    When I navigate to Customer List
    When I enter "customers" "first name" in the "Customers search" box
    Then updated Phone is displayed for the customer in the Customer List

