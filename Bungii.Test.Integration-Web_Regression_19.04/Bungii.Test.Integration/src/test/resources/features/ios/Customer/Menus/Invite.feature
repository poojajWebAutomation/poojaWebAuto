@ios
Feature: Customer	Referral Invite page
  As a Bungii customer I Should able to share my referral code

  Background:
    When I Switch to "customer" application on "same" devices
    Given I am on Customer logged in Home page

  @regression
  Scenario: Verify Proper Info And Promocode Is Displayed On Customer Invite Screen
    When I Select "Home" from Customer App menu
    Then "Invite referrals" should be present in "Home" screen
    When I click "Invite referrals" button on "HOME" screen
    Then I should be navigated to "Invite" screen
    Then Invite Referral page should be properly displayed
    And I click "Done" button on "Invite" screen
  
  @regression
  Scenario: Verify Customer Can Share Code Using Text Message
    When I Select "Home" from Customer App menu
    And I click "Invite referrals" button on "HOME" screen
    Then I should be navigated to "Invite" screen
    When I get Invite Code
    And I click "SHARE" button on "INVITE" screen
    And I click "SHARE BY TEXT MESSAGE" button on "INVITE" screen
    #UPON CLICKING ON SMS APP IT CLOSES IN BROWSERSTACK HENCE BELOW STEPS ARE COMMENTED OUT
    #Then I should see draft post in "Message" application
    #And I click "Done" button on "Invite" screen

  @regression
    #on browserstack phones mail app doesnt launch thus commented code after click
  Scenario: Verify Customer Can Share Code Using Email
    When I Select "Home" from Customer App menu
    And I click "Invite referrals" button on "HOME" screen
    Then I should be navigated to "Invite" screen
    When I get Invite Code
    And I click "SHARE" button on "INVITE" screen
    And I click "SHARE BY EMAIL" button on "INVITE" screen
    #Then I should see "No Mail Accounts" message
   # Then I should see draft post in "MAIL" application
    #Mail account removed from browserstack
    
# this test case is to run individually not in suite
 # @regression
  Scenario: Verify Customer Is Alerted When He Tries To Share Invite Code Using Twitter When Twitter App Is Not Installed
    Given I have "twitter" app "not installed"
    When I Select "Home" from Customer App menu
    And I click "Invite referrals" button on "HOME" screen
    Then I should be navigated to "Invite" screen
    When I get Invite Code
    And I click "SHARE" button on "INVITE" screen
    And I click "SHARE ON TWITTER" button on "INVITE" screen
    Then I should be navigated to "Invite" screen
    Then user is alerted for "No twitter installed"

  