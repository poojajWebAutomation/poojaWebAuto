@ios

Feature: Customer Home screen
# 1 valid test case fail

    #test case getting failed , Known issue
  @regression
    #stable
  Scenario:Verify If ETA Bar Remains On Map When Pickup Address Is Cleared
    Given I am on Customer logged in Home page
    Then "Pick up" address should be displayed in text box
    When I click "Pick Up Clear Text" button on "Home" screen
    And "PICK UP" box header and ETA bar header should be correctly displayed

 
  @regression
   #stable
  Scenario: Verify Clear Text Button On Pickup And Dropoff Location
    Given I am on Customer logged in Home page
    And I Select "Home" from Customer App menu
    When I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    When I click "Pick Up Clear Text" button on "Home" screen
    #Then current location should be present as pickup location
    When I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    When I select "Drop" location
    Then "Drop" address should be displayed in text box
    When I click "Drop Clear Text" button on "Home" screen
    Then "Drop" address should be empty
    When I select "Drop" location
    Then "Drop" address should be displayed in text box
    When I click "Pick Up Clear Text" button on "Home" screen
    #Then current location should be present as pickup location
    And "Drop" address should be empty

  @regression
  Scenario:Verify That Dropoff Field Is Displayed Only When Pickup Address Is Set
    Given I am on Customer logged in Home page
    And I open "customer" application on "same" devices
    Then "Pick up" address should be displayed in text box
    Then drop off field should be "displayed"
    When I click "Pick Up Clear Text" button on "Home" screen
    Then drop off field should be "not be displayed"


  @regression
  Scenario:Verify If Driver ETA Is Displayed When There Are Drivers Present In 30 Min Radius Of Pickup Location
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    When I Switch to "customer" application on "same" devices
    Given I am on Customer "A" logged in Home page
    When I select "Pick up" location
    Then "Pick up" address should be displayed in text box
    And driver eta should be "less than 30 mins"

  @regression
  Scenario:Verify Customer Can Set Pickup And Dropoff Locations When No Driver ETA Is Found (Within Geofence)
    Given I am on Customer logged in Home page

    And I enter pickup location
      | Driver | Pickup Location     |
      | Solo   | cancona bus station |
    Then driver eta should be "not be displayed"
    And I enter drop location
      | Driver | Drop Location  |
      | Solo   | Margao Railway Overbridge |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    When I click "Cancel" button on "SEARCHING" screen
    Then user is alerted for "CANCEL BUNGII"

  @regression
  Scenario: Verify Long Haul(>150 miles) Alert Is Shown When Distance between Pickup And Dropoff Should Be >150 Miles)
    Given I am on Customer logged in Home page

    And I enter pickup location
      | Driver | Pickup Location |
      | Solo   | Margao Railway Overbridge  |
    And I enter drop location
      | Driver | Drop Location                   |
      | Solo   | Gateway Of India Apollo Bandar |
    Then user is alerted for "LONG HAUL"


  @regression
  Scenario: Verify ETA Box When Geofence Is Not Active
    Given I am on Customer logged in Home page

    And I enter pickup location
      | Driver | Pickup Location |
      | Solo   | Kolhapur Airport  |
    Then driver eta should be "not be displayed"
    Then geofence not active message should be displayed
    When I click "Request your city" button on "Home" screen
    Then I should be navigated to "bungii.com" screen
  
  @regression
    #stable
  Scenario: Verify ETA And Location Textbox Header - Also Verify Clear Text Button Is Enabled Once Location Is Selected
    #When I logged in Customer application using  "existing" user
    Given I am on Customer logged in Home page
    Then current location should be present as pickup location
    And "Invite referrals" should be present in "Home" screen
    And "PICK UP" box header and ETA bar header should be correctly displayed
    
    And I enter pickup location
      | Driver | Pickup Location     |
      | Solo   | cancona bus station |
    And "Drop" box header and ETA bar header should be correctly displayed
    And Clear Button should be enabled for "Pick up" box
    
    And I enter drop location
      | Driver | Drop Location  |
      | Solo   | Margao Railway Overbridge |
   # When I select "Pick up" location from customer home screen
    #Then "Pick up" address should be displayed in text box
    #When I select "Drop" location from customer home screen
    Then "Drop" address should be displayed in text box
    And Clear Button should be enabled for "Drop" box

    @regression
    Scenario: Verify that existing customer account is successfully deleted
      When I Switch to "customer" application on "same" devices
      When I am on the "LOG IN" page
#    And I logged in Customer application using  "valid nashville" user
      And I logged in as "valid existing" customer
      And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I close "Tutorial" if exist
      When I Select "ACCOUNT > ACCOUNT INFO" from Customer App menu
      Then I should be navigated to "ACCOUNT INFO" screen
      And I click "Delete account" button on "ACCOUNT INFO" screen
      And I confirm that Delete button is disable
      And I enter "invalid" password and click on delete button
      Then I should see "Incorrect password" message
      And I click "Delete account" button on "ACCOUNT INFO" screen
      And I enter "valid" password and click on delete button
      Then I should see "Account deleted successfully" message
      Then I should be navigated to "LOG IN" screen
      And I logged in as "valid existing" customer
      Then Alert message with INVALID_PASSWORD text should be displayed
      When I accept Alert message
