@web
Feature: Driver_RegistrationCompletion

  Background:
    Given I navigate to "Bungii Driver URL"
    Then I should be directed to "signup tab" on Driver portal
    When I click on "Login" link
    #Driver Testdrivertywd_applefl_a_gruF Stark_flOnF
    And I enter driver Phone number as "9049840062" and valid password
    And I click "LOG IN button" on driver portal
    And I click Next on "Driver Details" page
    And I click Next on "Pickup Information" page
    And I click Next on "Documentation" page
    And I click Next on "Bank Details" page

  #@ready
  #knownissue fixed
  @outdatedFeature
  #@knownissue
        #there is one issue There was a problem processing your credit card; please double check your payment information and try again. when saved
  Scenario: Verify Driver Application Terms And Conditions Form - Uncheck Terms On Exisiting Non Fountain Application
    When I uncheck "agree to the Terms and Conditions." checkbox
    And I click Next on "Terms & Conditions" page
    Then I should see blank fields validation on "Terms & Conditions" page

  #@ready
  # knownissue fixed
  @outdatedFeature
  #@knownissue
    #there is one issue There was a problem processing your credit card; please double check your payment information and try again. when saved
  Scenario: Verify Driver Application Terms And Conditions Form - Check Terms On Exisiting Non Fountain Application
    When I click "I agree to the Terms and Conditions" on driver portal
    And I check "agree to the Terms and Conditions." checkbox
    And I click Next on "Terms & Conditions" page
    Then I should be directed to "Video Training" on Driver portal
    When I click Next on "Video Training" page
    Then I should see blank fields validation on "Video Training" page

