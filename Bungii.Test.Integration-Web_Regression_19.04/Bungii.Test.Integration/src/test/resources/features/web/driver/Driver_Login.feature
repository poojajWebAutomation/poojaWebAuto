@web
Feature: Driver_Login

  Background:
    Given I navigate to "Bungii Driver URL"
    When I click "LOG IN link" on driver portal
    Then I should be directed to "LOG IN tab" on Driver portal

  #@sanity
  #@ready
  #knownissue fixed
  @regression
  Scenario: Verify Driver Login With Valid Credentials
    When I enter "valid" driver Phone Number on Driver portal
    And I enter "valid" driver Password on Driver portal
    And I click "LOG IN button" on driver portal
    Then the driver should "be logged in"
    Then the driver logout from dashboard

  @regression
  @demo
  Scenario: Verify Driver Login Validations With Blank Credentials
    When I click "LOG IN button" on driver portal
    Then the driver should "see validation message for blank fields"

  @regression
  Scenario: Verify Driver Login Validations With Invalid Phone Number
    When I enter "invalid" driver Phone Number on Driver portal
    And I click "LOG IN button" on driver portal
    Then the driver should "see validation message for invalid phone field"

  @regression
  Scenario: Verify Driver Login Validations With Invalid Password
    When I enter "valid" driver Phone Number on Driver portal
    And I enter "invalid" driver Password on Driver portal
    And I click "LOG IN button" on driver portal
    Then the driver should "see validation message for incorrect credentials"

  @regression
  Scenario: Verify unmasked password(show password)is provided for password field on login page
    When I enter "valid" driver Phone Number on Driver portal
    And I enter "valid" driver Password on Driver portal
    Then The password for driver login should be masked
    When I click on the open "Eye" link on the driver login page
    Then I should see the password in the form of text

  @regression
  Scenario: Verify updated text is displayed in "Earn Extra Cash" potential earnings on driver portal
    Then I should see updated text "$45" in "Earn Extra Cash" potential earnings on "driver" portal

  @regression
  Scenario: Verify updated text is displayed in "Earn Extra Cash" potential earnings on bungii.com portal
    When I am on the "bungii.com" Portal
    Then I should see updated text "$45" in "Earn Extra Cash" potential earnings on "bungii.com" portal

  @ready
  #CORE-5314: Verify removal of "X-Powered-By" key from HTTP response headers
  Scenario: Verify "X-Powered-By" key is removed from HTTP response headers of Driver Auth services
    When I hit the Driver Auth services API & view the HTTP response headers for a page
    Then I verify "X-Powered-By" key is removed from HTTP response headers