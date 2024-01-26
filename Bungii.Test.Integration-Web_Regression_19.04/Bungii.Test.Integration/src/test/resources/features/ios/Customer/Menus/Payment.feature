@ios

Feature: Payment page
  As a Bungii customer
  I Should able to add/remove/change payment card

  Background:
    Given I am on Customer logged in Home page

  @knownissue
  Scenario Outline: Verify Customer Cannot Add Invalid Card - <Scenario> Scenario
    When I Select "ACCOUNT > PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    When I click "Add new" button on "PAYMENT" screen
    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
    Then I should see <Expected Message> on Payment page
    And I click "Cancel" button on "PAYMENT" screen

    Examples:
      | Scenario       | CardNo       | Expiry | Expected Message | Postal Code       | Cvv       |
      | INVALID_EXPIRY | VISA CARD    | 12/02  | "invalid expiry" | VALID POSTAL CODE | VALID CVV |
      | INVALID_CARD   | INVALID CARD | 12/29  | "invalid card"   | VALID POSTAL CODE | VALID CVV |
      | FRAUD_CARD     | FRAUD CARD   | 12/29  | "There was a problem processing your credit card; please double check your payment information and try again." | VALID POSTAL CODE | VALID CVV |
  
  
  @knownissue
  Scenario Outline: Verify Customer Can Add New Payment Card -  <Scenario> Scenario
    When I Select "ACCOUNT > PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    And PAYMENT page should be properly displayed
    When I click "Add new" button on "PAYMENT" screen
    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
    And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
    Then I should see "new card" on Payment page

    Examples:
      | Scenario           | CardNo        | Expiry | Postal Code       | Cvv       |
      | ValidCard Discover | DISCOVER CARD | 12/22  | VALID POSTAL CODE | VALID CVV |
      #| ValidCard Visa     | VISA CARD     | 12/22  | VALID POSTAL CODE | VALID CVV |
    


  #commented this due to base to auto data issue
    #From sprint30 , we can delete the default card
 
  #@regression
  Scenario Outline: Verify Customer Can Delete Payment Card
    Given I am on the "SIGN UP" page
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
    Then I should be navigated to "Home" screen
    When I Select "ACCOUNT > PAYMENT" from Customer App menu
    When I click "ADD" button on "PAYMENT" screen
    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
    And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
    Then I should see "new card" on Payment page
    When I swipe "other" card on the payment page
    And I tap on "Delete" on Payment page
    Then Alert message with Delete Warning text should be displayed
    When I accept Alert message
    Then I should see "the card has been deleted" on Payment page
    Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   |CardNo        | Expiry | Postal Code       | Cvv       |
      | VALID    | Mike       | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook |VISA CARD     | 12/22  | VALID POSTAL CODE | VALID CVV |



  @regression
  Scenario Outline:  Verify Customer Without Payment Card Should See Add Payment Card Message
    When I Select "ACCOUNT > LOGOUT" from Customer App menu
    Then I should be navigated to "LOG IN" screen
    When I enter Username :<Username> and  Password :<Password>
    And I click "Log In" button on "Log In" screen
    Then User should be successfully logged in to the application
    When I Select "ACCOUNT > PAYMENT" from Customer App menu
    Then "Add New Card" message should be displayed on "PAYMENT" page
    And "Add Image" should be present in "PAYMENT" screen
    And "ADD" should be present in "PAYMENT" screen
    And I Select "ACCOUNT > LOGOUT" from Customer App menu

    Examples:
      | Scenario          | Username       | Password       |
      | New_Register_User | {with no card} | {with no card} |

#CMA1513
#use customer with only one card
  @knownissue
  @failures
  Scenario Outline: Verify Customer Cannot Delete Payment Method Linked To Any On-going Or Scheduled Trips
    When I Switch to "customer" application on "same" devices
    Given I am on the "SIGN UP" page
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
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    Then I should be navigated to "Home" screen
    When I Select "PAYMENT" from Customer App menu
    When I click "ADD" button on "PAYMENT" screen
    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
    And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
    Then I should see "new card" on Payment page
    
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone  | Customer Name       | Customer Password |
      | NEXT_POSSIBLE | NEW_USER_NUMBER | VishalIHHnZkrz Test | Cci12345          |
    When I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "newly created user" user
    When I Select "PAYMENT" from Customer App menu
    When I swipe "other" card on the payment page
    And I tap on "Delete" on Payment page
    Then Alert message with Delete Warning text should be displayed
    When I accept Alert message
    Then Alert message with CARD IS ASSOCIATED TO TRIP text should be displayed
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | NEW_USER_NUMBER |                 |
    When I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "newly created user" user
    When I Select "PAYMENT" from Customer App menu
    When I swipe "other" card on the payment page
    And I tap on "Delete" on Payment page
    Then Alert message with Delete Warning text should be displayed
    When I accept Alert message
    Then I should see "the card has been deleted" on Payment page
    Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   | CardNo    | Expiry | Postal Code       | Cvv       |
      | VALID    | Mike       | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook | VISA CARD | 12/22  | VALID POSTAL CODE | VALID CVV |
  
  
  @knownissue
    #this test case is from customer signup module. but as this require bungii to be created , moved to this feature file
  Scenario Outline: Verify If Trip Completed Count On Admin Portal Is Updated When Customer Completes A Bungii - SSL ISSUE
    When I Switch to "customer" application on "same" devices
    
    Given I am on the "SIGN UP" page
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
    Then I should be navigated to "Home" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I Select "ACCOUNT > PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    When I click "Add-Button" button on "PAYMENT" screen
    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
    And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
    Then I should see "new card" on Payment page
    
    When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone  | Customer Name |  | Customer Password |
      | NEXT_POSSIBLE | NEW_USER_NUMBER |               |  | Cci12345          |
    And As a driver "testdriver4 Test" perform below action with respective "Solo Scheduled" trip
      | driver1 state      |
      | Accepted           |
      | Enroute            |
      | Arrived            |
      | Loading Item       |
      | Driving To Dropoff |
      | Unloading Item     |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9955112203 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    And I click "On To The Next One" button on "Bungii completed" screen
    Then I wait for "2" mins
    And I open Admin portal and navigate to "Customers" page
    Then trips requested count should be "1"
    Examples:
      | First Name | Last Name       | Email ID                        | Phone Number       | Password | Referral Code | Source   | CardNo        | Expiry | Postal Code       | Cvv       |
      | Donaldd    | {RANDOM_STRING} | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook | DISCOVER CARD | 12/22  | VALID POSTAL CODE | VALID CVV |
  