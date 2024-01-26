@android
Feature: EstimateBungii

  Background:
    Given I am on customer Log in page
    And I am logged in as "no promocode" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist

  @regression
  Scenario: Verify if the information icons display correct information On Bungii Confirmation Screen
    #When I enter "current location in pickup and dropoff fields" on Bungii estimate
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    Then "Load/Upload Time" information icon should display correct information
    #removed as part of sprint 32
    #And "Total estimate" information icon should display correct information
    And "Time" information icon should display correct information

  @regression
  Scenario: Verify Customer Home Screen Navigation Upon Cancellation On Estimate Screen
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I tap on "back" on Bungii estimate
    Then Trip Information should be correctly displayed on CUSTOMER HOME screen

  @regression
  Scenario: Verify All Fields Of Estimate Screen
    When I Select "ACCOUNT" from customer app menu list
    Then "ACCOUNT INFO" page should be opened
    Then I get customer account details
    Then I click on "Navigate Back" button on the "ACCOUNT INFO" page of customer app
    When I Select "HOME" from customer app menu list
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    Then I should see "all elements" on Bungii estimate

  @regression
  Scenario: Verify Load/Unload Time functionality And Estimate Cost Recalculation
    When I Select "HOME" from customer app menu list
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then check if I have ability to select different load time and Estimate cost is re calculated

  
  @regression
  Scenario: Verify Add Promo Code On Estimate Screen
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I tap on "Promo Code" on Bungii estimate
    And I add "valid" PromoCode
    And I tap "Add" on Promos page
    Then I should see "promocode added" on Bungii estimate page

  @regression
  #stable
  Scenario: Verify Customer Can Add Minimum of One And Maximum Of Four Images Of Items
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened
    And I add "1" photos to the Bungii
    And I add "4" photos to the Bungii

  @regression
  @nonstable
  Scenario: Verify Next Available Scheduled Time In Correct Timezone Is Selected On Estimate Screen For Duo Scheduled Bungii
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    And I wait for 15 minutes slot overlap period if occurs
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened
    Then correct details next available scheduled time should be displayed
  
  @regression
      #stable
  Scenario: Verify When Customer Switches From Ondemand To Scheduled Bungii On Account Of Unavailibility Of Driver Then Customer Is Taken To Schedule Bungii Screen
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    When I wait for SEARCHING screen to disappear
    Then "SET PICKUP TIME" page should be opened
    And I should see "Schedule Bungii option" on DRIVER NOT AVAILABLE screen
    Then I verify that "SET PICKUP TIME PAGE" is displayed
    When I click on "BUNGII DATE TIME" icon on "SET PICKUP TIME" Page
    And I schedule Bungii at "Future" Time
    When I tap "Schedule Bungii" button on DRIVER NOT AVAILABLE screen
    Then I should be navigated to "Success!" screen
    And I click "Done" button on "Success" screen
