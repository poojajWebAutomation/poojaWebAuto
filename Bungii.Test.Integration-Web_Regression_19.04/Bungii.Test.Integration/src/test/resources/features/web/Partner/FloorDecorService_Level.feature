@web
Feature: Floor and Decore Service Level

  Background:
    Given I navigate to "Partner" portal configured for "FloorDecor service level" URL

  @regression
  Scenario: Verify that 1pallet and 2pallets are shown instead of solo and duo for floor and decor partner portal
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "see 1 pallet and 2 pallets"
    #CORE-3849 changes incorporated
    When I request "Solo" Bungii trip in partner portal configured for "FloorDecor service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Address_Enter|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |CopyPaste    |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Floor & Decor #240" Alias
    And I change the service level to "First Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "FloorDecor service level" on partner screen
      |Product_Description|Dimensions|Weight|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Rb_Sb_Number|ScheduledBy|
      |20 boxes           |20X20X20  | 1570 |Handle with care   |Testartner T    |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |UserFND    |
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
    Then I close the Trip Delivery Details page
    When I navigate to "Admin" portal configured for "QA" URL
    #When I navigate to "Bungii Admin Portal in new tab" URL
    #And I view the Deliveries list on the admin portal
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
#   Core-2641 Verify alias is displayed for partner portal trips on scheduled delivery page
    And I verify alias is displayed correctly on "scheduled delivery page"
    When I view the delivery details
    And I verify alias is displayed correctly on "delivery details page"
#    Core-3294: Verify Stop search button is displayed for partner portal weight based schedule trips
    And I check if "Stop Searching" button is displayed
    Then I stop searching driver
    And I navigate back to Scheduled Deliveries
    And I wait for "2" mins
    When I click on the "Delivery Details" button from the dropdown
    Then I check if the status has been changed to "No Driver(s) Found"
    When I navigate back to Scheduled Deliveries
#   Core-2641 Verify alias is displayed for partner portal trips on all delivery page
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    When I click on "Close" button
    And I wait for "2" mins
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I verify alias is displayed correctly on "all delivery page"
#    Core-3294: Verify Stop search is not displayed for ongoing bungii and All deliveries screen
    When I click on the "Delivery Details" button from the dropdown
    Then I check if "Stop Searching" button is not present

  @ready
    # CORE-4079: Upper left logo navigate portals to "home"
  Scenario: Verify that Partner Portal Logo redirects to homepage
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "see 1 pallet and 2 pallets"
    # CORE-4102: Custom Quotes link more apparent
    And I should see partner disclaimer info
    And I should see "Custom Quotes" title
    And I should see Custom Quotes description
    And I should see "fill out this form" link
    And I click on fill out this form link
    Then I should be redirected to "Quote Request" tab
    And I close the Quote Request tab
    When I request "Solo" Bungii trip in partner portal configured for "FloorDecor service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Floor & Decor #240" Alias
    And I change the service level to "First Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    And I click on Partner Portal Logo in header
    Then I should get Confirmation Alert popup
    And I click on Continue button on popup
    Then I should see header as "Get Quote"
    When I request "Solo" Bungii trip in partner portal configured for "FloorDecor service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Floor & Decor #240" Alias
    And I change the service level to "First Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "FloorDecor service level" on partner screen
      |Product_Description|Dimensions|Weight|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Rb_Sb_Number|ScheduledBy|
      |20 boxes           |20X20X20  | 1570 |Handle with care   |Testartner T    |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |UserFND    |
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
#    CORE-4615--LOGO does not take to HOME page after scheduling page
    And I click on Partner Portal Logo in header
    Then I should see header as "Get Quote"

  @ready
  #CORE-5307: Verify space between What's needed section & custom Quote when disclaimer message is not present
  Scenario: Verify the UI for space between 'Whats's Needed' & 'Custom Quote' when disclaimer message is not present
    When I navigate to "Partner" portal configured for "FloorDecor service level #214" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    And I check "What's needed?" section is displayed
    And I check "Custom Quotes" section is displayed
    And I check "Disclaimer message" is not present
    Then I verify the UI for Space between "What's Needed" & "Custom Quotes" section is corectly displayed