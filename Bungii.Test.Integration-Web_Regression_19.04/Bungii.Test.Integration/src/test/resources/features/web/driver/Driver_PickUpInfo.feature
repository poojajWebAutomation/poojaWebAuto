@web
Feature: Driver_PickupInfo

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I click on "Login" link
    #Driver Testdrivertywd_applefl_a_gruE Stark_flOnE
    And I enter driver Phone number as "9049840061" and valid password
    And I click "LOG IN button" on driver portal
    And I click Next on "Driver Details" page

  #@ready
  #knownissue fixed
  @outdatedFeature
  #@regression
  Scenario: Verify Driver Application Pickup Info Form - Invalid Data Validations On Exisiting Non Fountain Application
    When I click Next on "Pickup Information" page
     Then I should see blank fields validation on "Pickup Information" page
     When I enter "less truck image - i" data on Pickup Information page
     And I click Next on "Pickup Information" page
     Then I should see individual field validations on "Pickup Information - i" page
     When I enter "less truck image - ii" data on Pickup Information page
     And I click Next on "Pickup Information" page
     Then I should see individual field validations on "Pickup Information - ii" page


  @ready
  Scenario: Verify Vehicle Information Page - is uneditable for Non Fountain Application
    When I click Next on "Vehicle Information" page
    And I verify all fields on "Vehicle Information" page


  @ready
  Scenario: Verify Terms & Privacy Policy Page - is uneditable for Non Fountain Application
    When I click Next on "Terms & Conditions" page
    Then I should be directed to "Updated Terms & Conditions"
    And I close "Updated Terms & Conditions" Page
    When I navigate to "Driver Details"
    And I click Next on "Privacy Policy" page
    Then I should be directed to "Updated Privacy Policy"
    And I close "Updated Privacy Policy" Page



