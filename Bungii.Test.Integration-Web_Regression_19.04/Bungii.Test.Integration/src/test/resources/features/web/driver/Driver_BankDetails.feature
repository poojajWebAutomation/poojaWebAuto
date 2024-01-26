@web
Feature: Driver_BankDetails

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I click on "Login" link
    # Driver Bryan Winds
    And I enter driver Phone number as "8888881122" and valid password
    And I click "LOG IN button" on driver portal
    And I click Next on "Driver Details" page
    And I click Next on "Pickup Information" page
    And I click Next on "Documentation" page


  @regression
  Scenario: Verify Driver Application Bank Details Form - Invalid Data Validations On Exisiting Non Fountain Application
    When I click Next on "Bank Details" page
    Then I should see blank fields validation on "Bank Details" page
     When I enter "short bank account" data on Bank Details page
     And I click Next on "Bank Details" page
     Then I should see individual field validations on "bank account on Bank Details" page
     When I enter "invalid" data on Bank Details page
     And I click Next on "Bank Details" page
     Then I should see individual field validations on "Bank Details" page