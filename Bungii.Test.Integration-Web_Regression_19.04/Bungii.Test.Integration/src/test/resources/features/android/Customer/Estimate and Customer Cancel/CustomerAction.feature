@android
Feature: Bungii

  Background:
    Given I am on customer Log in page
    When I am logged in as "Testcustomertywd_appleMarkFO LutherFO" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"

  @regression
  Scenario: Verify Customer Bungii Cancellation on Heads Up Popup
    And I get Bungii details on Bungii Estimate
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Cancel on HeadsUp pop up" on Bungii estimate
    Then I should see "Bungii Estimate page with all details" on Bungii estimate

 @regression
  Scenario: Verify Customer Bungii Cancellation on Search
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    When I tap on "Cancel during search" on Bungii estimate
    Then for a Bungii I should see "Bungii Home page with locations"
