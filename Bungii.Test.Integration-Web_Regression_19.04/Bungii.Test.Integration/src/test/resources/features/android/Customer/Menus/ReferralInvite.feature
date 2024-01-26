@android
Feature: ReferralInvite

  Background:
    And I Switch to "customer" application on "same" devices
    Given I am logged in as "existing" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I tap on "Menu" > "Home" link
    And I tap "Referral Invite link" on Home page
    Then I should see "Referral Code" on Invite Page


  @regression
  Scenario: Verify Sharing by Text
    When I tap "Share" on Invite page
    And I tap "Share by Text Message" on Invite page
    Then I should see post "on text message app"

  @regression
  Scenario: Verify Sharing by Email
    When I tap "Share" on Invite page
    And I tap "Share by Email" on Invite page
    Then I should see post "on gmail app"

  @regression
  Scenario: Verify When Customer With No Twitter App Shares Referral Invite Via Twitter Then It Opens in Browser
    Given I have "twitter" app "not installed"
    When I tap "Share" on Invite page
    And I tap "Share on Twitter" on Invite page
    Then I should see post "on Twitter in browser"

  @regression
  Scenario: Verify Screen With Proper Info And Promocode is displayed on Invite Screen
    Then I should see "all elements" on Invite Page
    When I tap "Back" on Invite page
    Then "Home" page should be opened
  
  @notwitter
 # @regression
  Scenario: Verify When Customer With Twitter App Shares Referral Invite Via Twitter
    Given I have "twitter" app "installed"
    When I tap "Share" on Invite page
    And I tap "Share on Twitter" on Invite page
    Then I should see post "Tweet Post in Twitter app"

