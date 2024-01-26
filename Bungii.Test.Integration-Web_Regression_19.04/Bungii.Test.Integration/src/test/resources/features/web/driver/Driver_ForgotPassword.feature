@web
Feature: Driver_ForgotPassword

  Background:
    Given I navigate to "Bungii Driver URL"
    When I click "LOG IN link" on driver portal
    And I click "Forgot Password" on driver portal

  @regression
  Scenario: Verify Driver Forgot Password Navigation
    Then I should be directed to "Forgot Password tab" on Driver portal
    And I enter "valid" Phone Number on Forgot password page
    And I click "Send Verification Code" on driver portal
    #CORE-3356:Send verification code to driver via email
    And I wait for "1" mins
    Then Driver should receive "BUNGII: Your verification code" email
    When I click "Back to Login" on driver portal
    Then I should be directed to "LOG IN tab" on Driver portal

  @regression
  Scenario: Verify Driver Forgot Password Validation on Blank Phone Number
    When I click "Send Verification Code" on driver portal
    Then I should see blank fields validation on "Forgot Password" page

  @regression
    @login
  Scenario: Verify Driver Forgot Password Validation on Invalid Phone Number
    When I enter "invalid" Phone Number on Forgot password page
    And I click "Send Verification Code" on driver portal
    Then driver should see "validation for invalid phone" during phone verification

  @regression
  Scenario: Verify Driver Forgot Password With Existing Phone Number
    When I enter "valid" Phone Number on Forgot password page
    And I click "Send Verification Code" on driver portal
    Then I should be directed to "Verify Your Phone tab" on Driver portal
    And driver should see "correct phone number" during phone verification

  @regression
  Scenario: Verify Blank Code Validation On Reset password Screen
    When I enter "valid" Phone Number on Forgot password page
    And I click "Send Verification Code" on driver portal
    And I click "Reset Password" on driver portal
    Then I should see blank fields validation on "Verify your phone" page

  @regression
  Scenario: Verify Resend Code On Reset password Screen
    When I enter "valid" Phone Number on Forgot password page
    And I click "Send Verification Code" on driver portal
    And I click "Resend Code on Verify your phone page" on driver portal
    Then driver should see "new verification code" during phone verification

  @regression
  Scenario: Verify Driver Reset Password Field Validation For Invalid Verification Code
    When I enter "valid" Phone Number on Forgot password page
    And I click "Send Verification Code" on driver portal
    And I enter "invalid" code on Verify your phone page
    And I enter "valid" new password on Verify your phone page
    And I enter "correct" password in Confirm password field
    And I click "Reset Password" on driver portal
    Then driver should see "validation for invalid code" during phone verification

  @regression
    #Issue Raised-ADP-827
  Scenario: Verify Driver Reset Password Field Validation For Invalid Password
    When I enter "valid" Phone Number on Forgot password page
    And I click "Send Verification Code" on driver portal
    And I enter "short" new password on Verify your phone page
    And I enter "correct" password in Confirm password field
    And I click "Reset Password" on driver portal
    Then driver should see "validations for password fields" during phone verification
    When I enter "invalid" new password on Verify your phone page
    Then driver should see "validation for invalid password" during phone verification

  @regression
  Scenario: Verify Driver Forgot Password Success
    When I enter "valid" Phone Number on Forgot password page
    And I click "Send Verification Code" on driver portal
    And I enter "valid" code on Verify your phone page
    And I enter "valid" new password on Verify your phone page
    And I enter "correct" password in Confirm password field
    And I click "Reset Password" on driver portal
    Then I should be directed to "LOG IN tab" on Driver portal
    Then driver should see "success message driver login page" during phone verification