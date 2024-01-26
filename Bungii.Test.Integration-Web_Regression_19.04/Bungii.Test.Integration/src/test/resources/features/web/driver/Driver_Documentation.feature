@web
Feature: Driver_Documentation

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I click on "Login" link
    #Driver Testdrivertywd_applefl_a_gruF Stark_flOnF
    And I enter driver Phone number as "9049840062" and valid password
    And I click "LOG IN button" on driver portal
    #And I click Next on "Driver Details" page
    #And I click Next on "Pickup Information" page

  #@ready
  @outdatedFeature
  #@knownissue
  Scenario: Verify Driver Application Documentation Form - Invalid Data Validations On Exisiting Non Fountain Application
    When I click Next on "Documentation" page
     Then I should see blank fields validation on "Documentation" page
     When I enter "invalid date" data on Documentation page
     And I click Next on "Documentation" page
     Then I should see individual field validations on "date on Documentation" page
    When I enter "invalid" data on Documentation page
     When I click Next on "Documentation" page
     Then I should see individual field validations on "Documentation" page

    @ready
      #CORE-6393 raised for incorrect field name
    Scenario: Verify Documentation Upload Page - is editable for  Non Fountain Application
      When I click Next on "Documentation" page
      And I verify all fields on "Documentation" page
      When I enter "update" data on Documentation page
      And I click Next on "Driver Details" page
      Then I click Next on "Documentation" page