@ios
Feature: DriverMenu
  In Bungii Driver
  As a logged in driver
  I want to check all menu links

  Background:
    Given I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid nashville" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
  @sanity
  @regression
  @menu
  Scenario: Verify Driver App Menu Option Navigation - ACCOUNT | LEADERBOARD | SCHEDULED BUNGIIS | AVAILABLE BUNGIIS | EARNINGS | ALERT SETTINGS | STORE | FEEDBACK | LOGOUT
    When I Select "ACCOUNT > ACCOUNT INFO" from driver App menu
    Then I should be navigated to "ACCOUNT INFO" screen
   Then I should be able to see data on "ACCOUNT INFO" page
    When I Select "LEADERBOARD" from driver App menu
    Then I should be navigated to "LEADERBOARD" screen
    Then I should be able to see data on "LEADERBOARD" page
    When I Select "SCHEDULED BUNGIIS" from driver App menu
    Then I should be navigated to "SCHEDULED BUNGII" screen
    Then I should be able to see data on "SCHEDULED BUNGIIS" page
    When I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
    Then I should be able to see data on "AVAILABLE BUNGIIS" page
    When I Select "EARNINGS" from driver App menu
    Then I should be navigated to "EARNINGS" screen
    Then I should be able to see data on "EARNINGS" page
    When I Select "ACCOUNT > ALERT SETTINGS" from driver App menu
    Then I should be navigated to "ALERT SETTINGS" screen
    Then I should be able to see data on "ALERT SETTINGS" page
    When I Select "ACCOUNT > PRIVACY POLICY" from driver App menu
    Then I should be navigated to "PRIVACY POLICY" screen
    Then I should be able to see data on "PRIVACY POLICY" page
    When I Select "BUNGII STORE" from driver App menu
    Then I should be navigated to "BUNGII STORE" screen
    Then I should be able to see data on "BUNGII STORE" page
    When I Select "FEEDBACK" from driver App menu
    Then I should be navigated to "FEEDBACK" screen
    Then I should be able to see data on "FEEDBACK" page
    When I Select "ACCOUNT > LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    Then I should be navigated to "LOG IN" screen
  
  @regression
    #stable
  Scenario: Verify Driver App Menu Option Navigation - FAQ
    When I Select "FAQ" from driver App menu
    Then I should be navigated to "FAQ" screen
    Then I should be able to see data on "FAQ" page
    When I Select "ACCOUNT > LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    Then I should be navigated to "LOG IN" screen
    
    
    
    # All below 11 scenarios are covered in one above to save the time of execution
  Scenario: Verify Driver FAQ Menu
    When I Select "FAQ" from driver App menu
    Then I should be navigated to "FAQ" screen
    Then I should be able to see data on "FAQ" page
    
  Scenario: Verify Driver LEADERBOARD Menu
    When I Select "LEADERBOARD" from driver App menu
    Then I should be navigated to "LEADERBOARD" screen
    Then I should be able to see data on "LEADERBOARD" page
    
  Scenario: Verify Driver SCHEDULED BUNGII Menu
    When I Select "SCHEDULED BUNGIIS" from driver App menu
    Then I should be navigated to "SCHEDULED BUNGII" screen
    Then I should be able to see data on "SCHEDULED BUNGIIS" page
    
  Scenario:Verify Driver AVAILABLE BUNGIIS menu
    When I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
    Then I should be able to see data on "AVAILABLE BUNGIIS" page
    
  Scenario: Verify Driver EARNINGS Menu
    When I Select "EARNINGS" from driver App menu
    Then I should be navigated to "EARNINGS" screen
    Then I should be able to see data on "EARNINGS" page
    
  Scenario: Verify Driver ITEMIZED EARNINGS Screen
    When I Select "EARNINGS" from driver App menu
    Then I should be navigated to "EARNINGS" screen
    When I click "ITEMIZED EARNINGS" button on "Home" screen on driverApp
    Then I should be able to see data on "ITEMIZED EARNINGS" page
    
  Scenario: Verify Driver ACCOUNT Menu
    When I Select "ACCOUNT > ACCOUNT INFO" from driver App menu
    Then I should be navigated to "ACCOUNT INFO" screen
    Then I should be able to see data on "ACCOUNT INFO" page
    
  Scenario: Verify Driver ALERT SETTINGS Menu
    When I Select "ACCOUNT > ALERT SETTINGS" from driver App menu
    Then I should be navigated to "ALERT SETTINGS" screen
    Then I should be able to see data on "ALERT SETTINGS" page
    
  Scenario:Verify Driver LOGOUT Menu
    When I Select "ACCOUNT > LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    Then I should be navigated to "LOG IN" screen
    
  Scenario: Verify Driver STORE Menu
    When I Select "BUNGII STORE" from driver App menu
    Then I should be navigated to "BUNGII STORE" screen
    Then I should be able to see data on "BUNGII STORE" page
    
  Scenario: Verify Driver FEEDBACK Menu
    When I Select "FEEDBACK" from driver App menu
    Then I should be navigated to "FEEDBACK" screen
    Then I should be able to see data on "FEEDBACK" page
    
  

