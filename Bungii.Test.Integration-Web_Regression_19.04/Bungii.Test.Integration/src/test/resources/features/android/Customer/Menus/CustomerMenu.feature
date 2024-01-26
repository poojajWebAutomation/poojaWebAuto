@android
Feature: CustomerMenu
  In Bungii Customer
  As a logged in customer
  I want to check all menu links

  Background:
    Given I am on Customer logged in Home page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
#failing due to BCKD-1103
  #@regression
  Scenario: Verify Device Token De-Registration On Customer Logout
    Then Customer active flag should be "1"
    #And I tap on "Menu" > "Logout" link
    And I tap on the "ACCOUNT>LOGOUT" link
    Then Customer active flag should be "0"
    
    ################
  @regression
    #long45 minutes
  Scenario: Verify Customer App Menu Item Navigations - FAQ | SIGN UP | ACCOUNT | PAYMENT | SUPPORT | PROMOS | PRIVACY POLICY | MY BUNGIIS | LOGOUT
    When I tap on "Menu" > "Home" link
    Then "Home" page should be opened
    
    When I Switch to "customer" application on "same" devices
    When I tap on "Menu" > "FAQ" link
    Then "FAQ" page should be opened
    Then I should see "first answer dropdown open" on FAQ page
    When I tap on "expanded first question" on FAQ page
    Then I should see "first answer dropdown close" on FAQ page
    And I should see "last question" on FAQ page
    When I tap on "Menu" > "ACCOUNT" link
    Then "ACCOUNT INFO" page should be opened
    And logged in Customer details should be displayed
    Then I click on "Navigate Back" button on the "ACCOUNT INFO" page of customer app
    When I tap on "Menu" > "Payment" link
    Then "Payment" page should be opened
    Then I click on "Navigate Back" button on the "PAYMENT" page of customer app
    When I tap on "Menu" > "Support" link
    Then "Support" page should be opened
    When I tap on the "ACCOUNT>PROMOS" link
    Then "Promos" page should be opened
    Then I click on "Navigate Back" button on the "PROMOS" page of customer app
    When I tap on the "ACCOUNT > PRIVACY POLICY" link
    Then "Privacy Policy" page should be opened
    Then I click on "Navigate Back" button on the "PRIVACY POLICY" page of customer app
    When I tap on "Menu" > "My Bungiis" link
    Then "MY BUNGIIS" page should be opened
    And I click on "Scheduled" tab
    And I should see "Scheduled info" message displayed
    And I click on "Past" tab
    And I should see "Past info" message displayed

    When I tap on "Menu" > "SIGN UP TO DRIVE" link
    Then "bungii.com" page should be opened
    
    When I Switch to "customer" application on "same" devices
    And I tap on the "ACCOUNT>LOGOUT" link
    Then "Login" page should be opened

