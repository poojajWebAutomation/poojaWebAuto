@android
Feature: Menu_Support
  Test scenarios related to Support page

  Background:
    Given I am logged in as "existing" customer
    When I tap on "Menu" > "Support" link

    
  @regression
  Scenario: Verify Validation On Blank Question In Support Field
    And I enter "space" text in Support field
    Then The user should see "Send button disabled" on Support page
    And The user should see "validation message" on Support page
  
  @ready
  @nonstable
  Scenario: Verify Support Menu Snackbar Validation on text
    Then "Support" page should be opened
    And The user should see "support question" on Support page
    When I enter "valid" text in Support field
    Then The user should see "snackbar message" on tap of "Send" on Support page