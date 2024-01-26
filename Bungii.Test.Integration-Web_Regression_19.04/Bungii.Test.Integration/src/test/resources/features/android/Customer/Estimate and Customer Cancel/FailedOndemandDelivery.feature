@android
Feature: Failed Ondemand Delivery

  #This feature will run in goa geofence.
  
  @regression
    #stable
  Scenario: Failed Ondemand Delivery : Verify that 'SET PICKUP TIME' page is shown when no driver accepts on demand bungii
    Given I am on customer Log in page
    When I am logged in as "valid goa customer" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "current location in pickup and dropoff fields long distance" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I wait for "4" mins
    
    Then I verify that "SET PICKUP TIME PAGE" is displayed
    And I verify that "DRIVERS NOT AVAILABLE" is displayed
    When I click on "i info" icon
    Then I verify that "Pickup Message Popup" is displayed
  
  @regression
  #stable
  Scenario: Failed Ondemand Delivery : Verify that the customer can schedule pickup by clicking on SCHEDULE BUNGII button.
    Given I am on customer Log in page
    When I am logged in as "Testcustomertywd_appleMarkGY LutherGY" customer
    And I enter "current location in pickup and dropoff fields long distance" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I wait for "4" mins
    Then I verify that "SET PICKUP TIME PAGE" is displayed
    When I click on "SCHEDULE BUNGII" button
    Then I click "Done" button on "Success" screen
    And the trip is displayed on "MY BUNGII" screen
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8888882028 |                 |
  
  @regression
  #stable
  Scenario: Failed Ondemand Delivery : Verify that the textbox is displayed when customer selects Other.. as the cancelling on demand bungii reason
    Given I am on customer Log in page
    When I am logged in as "Testcustomertywd_appleMarkGZ LutherGZ" customer
    And I enter "current location in pickup and dropoff fields long distance" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I wait for "4" mins
    Then I verify that "SET PICKUP TIME PAGE" is displayed
    When I click on "CANCEL" button
    Then A popup with "Cancel Reasons" should appear
    When I select reason as "Other..."
    And I click on "SUBMIT" button
    Then The "CANCELLATION REASON" textbox should be displayed
    
  @regression
  @nonstable
  Scenario: Failed Ondemand Delivery : Verify that the customer can schedule pickup by clicking on date & time and selecting different time and date
    Given I am on customer Log in page
    When I am logged in as "Testcustomertywd_appleMarkHA LutherHA" customer
    And I enter "current location in pickup and dropoff fields long distance" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I wait for "4" mins
    Then I verify that "SET PICKUP TIME PAGE" is displayed
    When I click on "BUNGII DATE TIME" icon on "SET PICKUP TIME" Page
    And I schedule Bungii at "Future" Time
    And I click on "SCHEDULE BUNGII" button
    And I click "Done" button on "Success" screen
    Then the trip is displayed on "MY BUNGII" screen
    
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8888882028 |                 |
  
  @regression
  @nonstable
  Scenario: Failed Ondemand Delivery : Verify that the customer can see 4 options when cancels the on demand trip
    Given I am on customer Log in page
    When I am logged in as "Testcustomertywd_appleMarkHB LutherHB" customer
    And I enter "current location in pickup and dropoff fields long distance" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I wait for "4" mins
    Then I verify that "SET PICKUP TIME PAGE" is displayed
    When I click on "CANCEL" button
    Then A popup with "Cancel Reasons" should appear
    And I verify that "Four Reasons" is displayed
  

  
  @regression
  @nonstable
  Scenario: Failed Ondemand Delivery : Verify that customer can enter the reason of its own to cancel ondemand bungii search
    Given I am on customer Log in page
    When I am logged in as "valid goa customer" customer
    And I enter "current location in pickup and dropoff fields long distance" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I wait for "4" mins
    Then I verify that "SET PICKUP TIME PAGE" is displayed
    When I click on "CANCEL" button
    Then A popup with "Cancel Reasons" should appear
    When I select reason as "Other..."
    And I click on "SUBMIT" button
    And I enter "reason" on Cancellation popup textbox
    And I click on "SUBMIT REASON" button
  
  @regression
    #stable
  @nonstable
  Scenario Outline: Failed Ondemand Delivery : Verify that user can select each of the reason and submit successfully
    Given I am on customer Log in page
    When I am logged in as "valid goa customer" customer
    And I enter "current location in pickup and dropoff fields long distance" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I wait for "4" mins
    Then I verify that "SET PICKUP TIME PAGE" is displayed
    When I click on "CANCEL" button
    Then A popup with "Cancel Reasons" should appear
    When I select reason as "<Reason>"
    And I click on "SUBMIT" button
    Then I check if a "<Reason>" is shown in the table
    
    Examples:
      | Reason                    |
      | I needed it right away.   |
      | I found an alternative.   |
      | I don’t need it anymore.  |
  
  