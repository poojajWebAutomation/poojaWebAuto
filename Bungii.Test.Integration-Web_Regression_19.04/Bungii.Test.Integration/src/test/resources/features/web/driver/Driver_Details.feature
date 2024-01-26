@web
Feature: Driver_Details

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I click on "Login" link
    #Driver Testdrivertywd_applefl_a_gruC Stark_flOnD
    And I enter driver Phone number as "9049840059" and valid password
    And I click "LOG IN button" on driver portal
    
  @outdatedFeature
    #stable
  Scenario: Verify Driver Application Details Form - Invalid Data Validations On Exisiting Non Fountain Application
    When I click Next on "Driver Details" page
     Then I should see blank fields validation on "Driver Details" page
     When I enter "invalid" data on Driver Details page
    And I click Next on "Driver Details" page
     Then I should see individual field validations on "Driver Details" page

    @ready
    Scenario: Verify Driver Details Page - is uneditable for Non Fountain Application
      When I click Next on "Driver Details" page
      And I verify all fields on "Driver Details" page

    @ready
    Scenario: Verify Driver Basic Info Page - is uneditable for Non Fountain Application
      When I click Next on "Driver Basic Info" page
      And I verify all fields on "Driver Basic Info" page



