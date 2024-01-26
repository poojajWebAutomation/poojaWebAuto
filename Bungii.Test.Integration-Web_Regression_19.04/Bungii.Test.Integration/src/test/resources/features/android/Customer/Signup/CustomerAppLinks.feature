@android
Feature: CustomerApplicationLinks
  Check the basic User information is available.

  Background:
    When I tap on the "Log in" button on Signup Page
    And I enter customers "9999999104" Phone Number
    And I enter customers "generic" Password
    And I tap on the "Log in" Button on Login screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
  @sanity
  @regression
    #Following scenario also covered:
    #Tutorials should only be displayed on the app on first installation.
    #Tutorials - 5 pages
    #Verify text on Tutorials pages and swipe. Should be able to swipe back and forth between pages. (Completed PARTIALLY)
    #Dismiss Tutorials by tapping on Start
  Scenario: Verify Tutorials Is displayed Only On First Installation
    Then "Tutorial" page should be opened
    And I check that "5" pages of turotial are present
    And I check that if i can swipe the pages
    And I tap the "START" button is present on last page
    #When I tap on "Menu" > "Logout" link
    When I tap on the "ACCOUNT>LOGOUT" link
    And I enter customers "9999999104" Phone Number
    And I enter customers "generic" Password
    And I tap on the "Log in" Button on Login screen
    And I verify that the tutorial is displayed only once

    @regression
      #stable
    Scenario: Verify Save Money Button Redirect To Invite Screen
      And I check that if i can swipe the pages
      And I tap the "START" button is present on last page
      When I tap on "Menu" > "MY BUNGIIS" link
      Then "MY BUNGIIS" page should be opened
      When I click on "SAVE MONEY" button
      Then "INVITE" page should be opened
      When I tap on "back page" on Bungii estimate
      When I tap on the "ACCOUNT>PROMOS" link
      Then "Promos" page should be opened
      When I click on "GET MORE MONEY" button
      Then "INVITE" page should be opened
      

        @nobrowser
        Scenario: Verify Sign Up To Drive - When No Browser Is Present On Device
          And I check that if i can swipe the pages
          And I tap the "START" button is present on last page
          When I tap on "Menu" > "SIGN UP TO DRIVE" link
          Then Alert message with Please install a browser in order to access this link. text should be displayed