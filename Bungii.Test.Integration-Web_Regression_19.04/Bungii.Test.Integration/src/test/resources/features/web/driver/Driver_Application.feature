@web
Feature: Driver_Application

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I enter "valid" details on Signup page
    And I enter "unique" driver phone number on Signup page
    And I click "Signup button" on driver portal
    Then I should be directed to "phone verification page" on Driver portal

  #@sanity
  #@ready
  @outdatedFeature
  @email
    #@knownissue
  Scenario: Verify Driver Signup And Form Completion For Non Fountain Flow
    When I enter "correct" verification code
    And I click "Submit verification code" on driver portal
    Then I should be directed to "Verification Successful page" on Driver portal
    And I should see "Logged in user name" on Driver Registration
    When I click "Continue Registration" on driver portal
    Then I should be directed to "Driver Details page" on Driver portal
    When I enter "valid" data on Driver Details page
    And I click Next on "Driver Details" page
    Then I should be directed to "Pickup Info page" on Driver portal
    When I enter "valid" data on Pickup Information page
    And I click Next on "Pickup Information" page
    Then I should be directed to "Documentation page" on Driver portal
    When I enter "valid" data on Documentation page
    And I click Next on "Documentation" page
    Then I should be directed to "Bank Details page" on Driver portal
    When I enter "valid" data on Bank Details page
    And I click Next on "Bank Details" page
    Then I should be directed to "Terms & Conditions" on Driver portal
    When I click "I agree to the Terms and Conditions" on driver portal
    When I click Next on "Terms & Conditions" page
    And I click "I have viewed the entire video" on driver portal
    And I click Next on "Video Training" page
    Then I should be directed to "Finish" on Driver portal
    When I click "Continue on Finish page" on driver portal
    Then I should be directed to "Dashboard" on Driver portal
    And I should receive "BUNGII: Application Received." email
    And Admin should receive "New driver registration complete!" email
    And the driver logout from dashboard

  @ready
  Scenario: Verify Driver Signup and check in db
    When I enter "correct" verification code
    And I click "Submit verification code" on driver portal
    Then I verify correct "Records" is set in db