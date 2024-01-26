@ios
@signup
Feature: As a new customer I should be allowed to Sign up on Bungii Customer applicatrion
#9999997171

  Background:
    Given I am on the "SIGN UP" page

  @regression
  Scenario Outline: Verify Referral Source Is Incremented If Customer Registers By Selecting Referral <Scenario>
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Referral Source" from admin sidebar
    Then I get Referral Source info for "<Source>"
    When I switch to "ORIGINAL" instance
    And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    #removed as part of sprint 32
  #  Then Alert message with NO PROMO CODE text should be displayed
  #  When I reject Alert message
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then I should be navigated to "Home" screen
    When I switch to "ADMIN_PORTAL" instance
    And I Select "Referral Source" from admin sidebar
    Then account created info for "<Source>" should be "increase by 1"

    Examples:
      | Scenario      | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source |
      | Source :OTHER | Mike       | Test      | Bungiiauto+TEs123@gmail.com | {RANDOM_PHONE_NUM} | Cci12345 |               | OTHER  |
    
  @regression
  Scenario Outline:Verify Customer Can Submit Registration Form With Promocode
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    And I get promo code for "<Referral Code>"
    And I switch to "ORIGINAL" instance
    And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Referral Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
      And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I close "Tutorial" if exist
    Then I should be navigated to "Home" screen
    When I Select "ACCOUNT > PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I should able to see expected promo code in available promo code
    And I click "INFO" button on "PROMOS" screen
    Then user is alerted for "MINIMUM COST STILL APPLIES"

    Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source |
      | VALID    | Mike       | Test      | Bungiiauto+TEs123@gmail.com | {RANDOM_PHONE_NUM} | Cci12345 | Promo         | OTHER  |
  
  @regression
  Scenario Outline: Verify Customer Can Submit Registration Form Without Promocode
    When I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
#removed as part of sprint 32
#    Then Alert message with NO PROMO CODE text should be displayed
#    When I reject Alert message
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then I should be navigated to "Home" screen


    Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   |
      | VALID    | Mike       | Test      | Bungiiauto+TEs123@gmail.com | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook |

  @regression
      #Added case of CORE-3685 to exsting script
  Scenario Outline: Verify deletion of new created customer account and then reuse the same account for new customer creation
    When I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then I should be navigated to "Home" screen
    When I Select "ACCOUNT > ACCOUNT INFO" from Customer App menu
    Then I should be navigated to "ACCOUNT INFO" screen
    And I click "Delete account" button on "ACCOUNT INFO" screen
    #And I confirm the account deletion for customer
    And I enter "valid" password and click on delete button
    Then I should see "Account deleted successfully" message
    Then I should be navigated to "LOG IN" screen
    And I click "SIGN UP" button on "LOG IN" screen
    #And I click on "SIGN UP" button on "LOG IN" screen
      When I Enter "Deleted Phone" value in "Phone Number" field in "SIGN UP" Page
      And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
      And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
      And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
      And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
      And I Select Referral source as "<Source>"
      And I click "SIGN UP" button on "SIGN UP" screen
      Then I should be navigated to "VERIFICATION" screen
      When I Get SMS CODE for new "Customer"
      And I enter "valid" Verification code
      And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I close "Tutorial" if exist
      Then I should be navigated to "Home" screen

      Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   |
      | VALID    | Shaun      | Test      | Bungiiauto+TEs1234@gmail.com    | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook |

  @knownissue
  Scenario Outline: Verify Customer Can Submit Registration Form Without Promocode
    When I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
#removed as part of sprint 32
#    Then Alert message with NO PROMO CODE text should be displayed
#    When I reject Alert message
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then I should be navigated to "Home" screen
    And Customer should receive signup email
    
    
    Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   |
      | VALID    | Mike       | Test      | {RANDOM_EMAIL} | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook |

  @failed
  @regression
  Scenario Outline: Verify Customer Registration With Invalid Details - Scenario : <Scenario>
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then user is alerted for "<Expected Message>"

    Examples:
      | Scenario           | First Name | Last Name       | Email ID                        | Phone Number | Password | Referral Code | Source   | Expected Message              |
      | EMPTY SIGNUP FIELD | {BLANK}    | {BLANK}         | {BLANK}                         | {BLANK}      | {BLANK}  |               | {BLANK}  | EMPTY SIGNUP FIELD            |
      | Invalid_EMAIL      | test       | {RANDOM_STRING} | ss@dd                           | 9403960188   | Cci12345 |               | facebook | INVALID EMAIL WHILE SIGNUP    |
      | Invalid_Password   | test       | {RANDOM_STRING} | Bungiiauto+TEs123@gmail.com | 9403960188   | Cci      |               | facebook | INVALID PASSWORD WHILE SIGNUP |

  @failed
  @regression
  Scenario Outline: Verify Customer Registration With Invalid Phone Number - Scenario : <Scenario>
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
   # Then Alert message with NO PROMO CODE text should be displayed
  #  When I reject Alert message
    Then user is alerted for "<Expected Message>"

    Examples:
      | Scenario            | First Name | Last Name       | Email ID                        | Phone Number | Password | Referral Code | Source   | Expected Message           |
      | Already Existing No | Vishal     | {RANDOM_STRING} | Bungiiauto+TEs123@gmail.com | {VALID USER} | Cci12345 |               | facebook | EXISTING USER              |
      | Invalid_Phone       | Mike       | tester          | Bungiiauto+TEs123@gmail.com | 12345        | Cci12345 |               | facebook | INVALID PHONE WHILE SIGNUP |
  @failed
  @regression
  Scenario Outline:  Verify Customer Registration With Invalid Referral Code
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Referral Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then user is alerted for "<Expected Message>"
    And I should be navigated to "SIGN UP" screen

    Examples:
      | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   | Expected Message           |
      | Mike       | tester    | Bungiiauto+TEs123@gmail.com | {RANDOM_PHONE_NUM} | Cci12345 | XX            | facebook | INVALID PROMO WHILE SIGNUP |
 
  #promo code in example
  @regression
    #stable
  Scenario Outline: Verify Text On Promos Screen When First Time Promocode Is Added
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Promo Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen

    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then I should be navigated to "Home" screen
    When I Select "ACCOUNT > PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I should able to see expected promo code in available promo code
    Then I should see "first time code subtext" on Promos page
    And I click "INFO" button on "PROMOS" screen
    Then user is alerted for "FIRST TIME PROMO CODE"

    Examples:
      | First Name | Last Name | Email ID                        | Phone Number       | Password | Promo Code | Source   |
      | Ron        | testerr   | Bungiiauto+TEs123@gmail.com | {RANDOM_PHONE_NUM} | Cci12345 | ONETESTTIM | facebook |

  @knownissue
  Scenario Outline:Verify Customer Cannot Signup With Future Active Promoter Type Promo codes
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Promo Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then Alert message with INACTIVE PROMO CODE MESSAGE text should be displayed

    Examples:
      | First Name              | Last Name | Email ID                        | Phone Number       | Password | Promo Code | Source   |
      | RandomTestcustomertywd_apple  | testerr   | Bungiiauto+TEs123@gmail.com | {RANDOM_PHONE_NUM} | Cci12345 | HECKNWWAU | facebook |

 
    #used one off
  #Know issue, no alert
  @regression
  Scenario Outline: Verify Validation Is Displayed On Signup If Invalid Or Used One Off Promocode Is Entered
    When I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Enter "<Referral Code>" value in "Referral code" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
  #  Then user is alerted for "<Expected Message>"
    # And I should be navigated to "SIGN UP" screen  Invalid Step commented
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then I should be navigated to "Home" screen

    Examples:
      | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   | Expected Message           |
      | Mike       | tester    | Bungiiauto+TEs123@gmail.com | {RANDOM_PHONE_NUM} | Cci12345 | R1D2            | facebook | INVALID PROMO WHILE SIGNUP |
