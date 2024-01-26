@ios

Feature: Customer Estimate screen
  As a Bungii customer
  when I request for Bungii
  I Should be navigated to Estimate screen
  
  Background:
    Given I am on Customer logged in Home page
  
  @regression
     #stable
  Scenario: Verify Trip limit [150 miles] For Solo Bungii
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margao Railway Overbridge  | Panjim bus stand |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
  
    
  @regression
    #stable
  Scenario: Verify If Customer Cancels Ondemand Bungii While It Is In Searching driver State Then He Is Navigated To Home Screen & Pickup And Dropoff Location Of Previous Trip Is Retained
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margao Railway Overbridge  | Panjim bus stand |
    And I click "Get Estimate" button on "Home" screen
    And I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 30       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen
    When I click "Cancel" button on "SEARCHING" screen
    Then user is alerted for "CANCEL BUNGII"
    And I should be navigated to "Home" screen
    And Trip Information should be correctly displayed on CUSTOMER HOME screen

  @regression
  #stable
  Scenario: Verify When Bungii Customer Cancels On Heads Up Alert Message Then He Stays On Estimate Screen And All Field Details are retained
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margao Railway Overbridge  | Panjim bus stand |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |Save Trip Info |
      | 15       |           |              | Now  | Default     |No             |
    And I store default card value
    And I click "REQUEST BUNGII" button on "Estimate" screen
    And I reject Alert message
    Then I should be navigated to "ESTIMATE" screen
    And Estimate Screen should have element as per below table
      | Trip Distance    | Load/unload time | Promo Code | Total Estimate   | Payment Method   | Time | Terms And Condition | REQUEST BUNGII |
      | {PREVIOUS VALUE} | 15 mins          |            | {PREVIOUS VALUE} | {PREVIOUS VALUE} | Now  | CHECK               | ENABLED        |
  

  @regression
  Scenario: Verify When Customer Cancels On Estimate Page Then He Is Navigated To Home Screen
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margao Railway Overbridge  | Panjim bus stand|
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    And Trip Information should be correctly displayed on Estimate screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 30       |           |              | Now  | Default     | No             |
    And I click "Cancel" button on "Estimate" screen
    Then I should be navigated to "Home" screen
    And Trip Information should be correctly displayed on CUSTOMER HOME screen

  @regression
  Scenario: Verify If The Information Icons Display Correct Information On Estimate Screen
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margao Railway Overbridge  | Panjim bus stand |
    And I click "Get Estimate" button on "Home" screen

    Then I should be navigated to "Estimate" screen
    And Trip Information should be correctly displayed on Estimate screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |              | NEXT_POSSIBLE | Default     | No             |
    Then "Load/Upload Time" information icon should display correct information
    #removed as part of sprint 32
  #  And "Total estimate" information icon should display correct information
    And "Time" information icon should display correct information

  @regression
  Scenario: Verify Field Elements Of Estimate Screen
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margao Railway Overbridge  | Panjim bus stand |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    And Trip Information should be correctly displayed on Estimate screen
    And Estimate Screen should have element as per below table
      | Trip Distance | Load/unload time | Promo Code | Total Estimate | Payment Method | Time | Terms And Condition | REQUEST BUNGII |
      | <IN MILES>    | SELECT           | ADD        | <IN DOLLAR>    | **** 4242/**** 1117/**** 1881   | Now  | UNCHECK             | DISABLED       |


  @regression
  Scenario: Verify Load Unload Time Functionality And Verify If Estimate Cost Is Recalculated
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margao Railway Overbridge  | Panjim bus stand |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    And check if I have ability to select different load time and Estimate cost is re calculated



  @regression
  Scenario: Verify Estimate Value For The Bungii Should Be Correctly Displayed In Estimate Screen
    And I am on the "LOG IN" page
    And I am on Customer logged in Home page
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                | Geofence  |
      | Solo   | Margao Railway Overbridge  | Panjim bus stand | goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    Then Estimate value for trip should be properly displayed

  @regression
  Scenario: Verify Customer Is Prompted To Go To Add Payment Page If No Payment Exists
    Given I am on the "LOG IN" page
    When I enter Username :9999990216 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                | Geofence  |
      | Solo   | Margao Railway Overbridge  | Panjim bus stand | goa |
    And I click "Get Estimate" button on "Home" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    And I click "REQUEST BUNGII" button on "Estimate" screen
    Then user is alerted for "ADD CARD BEFORE REQUEST BUNGII"
    Then I should be navigated to "PAYMENT MODE" screen
    And "Add New Card" message should be displayed on "PAYMENT" page
    And "Add Image" should be present in "PAYMENT" screen
    And "ADD" should be present in "PAYMENT" screen
    When I Switch to "customer" application on "same" devices
    And I Select "ACCOUNT > LOGOUT" from Customer App menu

    @regression
    Scenario:Verify Customer Can Add Atleast One And Maximum Of Four Images Of Items On Estimate Screen
      When I request for  bungii for given pickup and drop location
        | Driver | Pickup Location | Drop Location                |
        | Solo   | Margao Railway Overbridge  | Panjim bus stand |
      And I click "Get Estimate" button on "Home" screen
      When I enter following details on "Estimate" screen
        | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
        | 30       |           |              | Now  | No image     | No             |
      And I click "REQUEST BUNGII" button on "Estimate" screen
      Then user is alerted for "ADD IMAGE OF ITEM"
      When i add "4 images" of pickup item
      And I click "REQUEST BUNGII" button on "Estimate" screen
      When I click "YES" on alert message
      Then I should be navigated to "SEARCHING" screen
      When I click "Cancel" button on "SEARCHING" screen
      Then user is alerted for "CANCEL BUNGII"
      

  @regression
	@testing1
  Scenario: Verify When There Are No Driver Available For Ondemand Request Then He Should Be Navigated To SET PICKUP TIME Screen With Prefilled Date
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Panjim bus stand  | Margao Railway Overbridge |
    And I click "Get Estimate" button on "Home" screen
    And I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    When I wait for SEARCHING screen to disappear
    Then I should be navigated to "SET PICKUP TIME" screen
    #When I click "SCHEDULE BUNGII" button on "SET PICKUP TIME" screen
    #Then If time is already passed then i should see "Oops! Since there has been a delay in requesting this trip, the scheduled time selected is no longer valid. Please recheck and submit your request." message
    When I select a new time
    And I click "SCHEDULE BUNGII" button on "SET PICKUP TIME" screen
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen

    # Changed after sprint 33 changes
    #Then I should be navigated to "DRIVER NOT AVAILABLE" screen
   # When I click "Ok" button on "DRIVER NOT AVAILABLE" screen
   # Then Alert message with SCHEDULE BUNGII OPTION text should be displayed
   # When I click "Schedule Bungii" on alert message
   # Then I should be navigated to "Estimate" screen
   # And Estimate Screen should have element as per below table
    #  | Trip Distance    | Load/unload time | Promo Code | Total Estimate   | Payment Method | Time | Terms And Condition | REQUEST BUNGII |
    #  | {PREVIOUS VALUE} | SELECT           |            | ~$0.00 | **** 4242/**** 1117/**** 1881   |      | UNCHECK             | DISABLED       |
