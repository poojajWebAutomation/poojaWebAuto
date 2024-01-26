@web
Feature: Other Miscellaneous

  Background:
    Given I navigate to "Partner" portal configured for "normal" URL

  @regression
    #stable
  Scenario: Verify Partner portal logout after clearing local storage
  When I enter "valid" password on Partner Portal
  And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I Clear the browser local storage and refresh the Page
  Then I should be navigated to Login screen

    @ready
    Scenario: To verify that Reports option is added in menu button of PP
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"
      And The dropdown menu should have an "Hamburger" icon
      When I click on the "Dropdown" button on the top right side of the page
      Then I should see "Reports" as an option
      When I click on the "Reports" link
      Then I should see "Delivery History Report" message on the popup
      When I click on the "Today" link
      And I click on "Generate Report" button
      Then I should see the the message "No deliveries for selected dates"
      When I select the month which is two months ahead of the current month
      Then The dates should be disabled
      When I click on the below filter options i should see the respective date displayed
      |Filter DateBy |
      |Today         |
      |Yesterday     |
      |This Week     |
      |Last Week     |
      |Last 7 Days   |
      |This Month    |
      |Last Month    |


  @regression
  Scenario: verify that on click of generate report csv file is downloaded
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
   When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner A      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I store the delivery details
    When I click on the "Dropdown" button on the top right side of the page
    And I click on the "Reports" link
    Then I should see "Delivery History Report" message on the popup
    When I click on the "Today" link
    And I click on "Generate Report" button
    Then The csv file should get downloaded having name "Partner-deliveries.csv"
    And The csv file data should match the details of the delivery