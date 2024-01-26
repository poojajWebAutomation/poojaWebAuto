@web
  Feature: Patner_Login

    Background:
      Given I navigate to "Partner" portal configured for "normal" URL

    @test
    @regression
    @sanity
    #CORE-3251--Text Support and Support Email Verification on Partner Portal
    
    Scenario: Verify Partner portal login with Valid Credentials
      When I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"
      Then I should see "Text Support Number and Email"
      And I should logout from Partner Portal

    @regression
    Scenario:Verify Driver Login Validations With Blank Credentials
      When I click "SIGN IN" button on Partner Portal
      Then I should "see validations message for blank password field"

    @regression
    Scenario:Verify Partner Login Validations With Invalid Password
      When I enter "invalid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "see validations message for incorrect password field"
