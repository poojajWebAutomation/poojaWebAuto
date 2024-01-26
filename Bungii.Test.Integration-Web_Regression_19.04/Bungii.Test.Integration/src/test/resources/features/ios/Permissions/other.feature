@ios
@promos
Feature: Share Code
  As a Bungii customer
  I Should able to add new promo code
  
  Background:
	Given I am on Customer logged in Home page
	
@knownissue
Scenario: Verify Referral Invite When Facebook App Is Already Installed
Given I have "facebook" app "installed"
When I Select "ACCOUNT > PROMOS" from Customer App menu
Then I should be navigated to "PROMOS" screen
When I click "GET MORE MONEY" button on "PROMOS" screen
Then I should be navigated to "Invite" screen
Then I get Invite Code
When I click "SHARE" button on "INVITE" screen
And I click "SHARE ON FACEBOOK" button on "INVITE" screen
Then I should see "popup to post" Overlay Facebook screen
When I enter "valid data" on Overlay Facebook screen
  #  And I tap "Next" button on Overlay Facebook screen
And I tap "POST" button on Overlay Facebook screen
#    When I tap "Share" button on Overlay Facebook screen
Then I should be navigated to "Invite" screen
  
  
  @knownissue
  Scenario: Verify Customer Is Alerted When He Tries To Share Invite Code Using Twitter Application
	Given I have "twitter" app "installed"
	When I Select "Home" from Customer App menu
	And I click "Invite referrals" button on "HOME" screen
	Then I should be navigated to "Invite" screen
	When I get Invite Code
	And I click "SHARE" button on "INVITE" screen
	And I click "SHARE ON TWITTER" button on "INVITE" screen
	Then I should see draft post in "twitter" application
	And I should be navigated to "Invite" screen