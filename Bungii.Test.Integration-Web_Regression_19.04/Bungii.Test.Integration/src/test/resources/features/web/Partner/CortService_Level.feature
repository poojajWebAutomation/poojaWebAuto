@web
Feature: Cort Service Level

  Background:
    Given I navigate to "Partner" portal configured for "Cort service level" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"

  @regression
  Scenario: Verify partner invoice Payment method selected by default
    When I request "Solo" Bungii trip in partner portal configured for "Cort service level" in "washingtondc" geofence
        | Pickup_Address                                                                     | Delivery_Address                                                    |
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
    And I select Pickup Date and Pickup Time on partner portal
      |PickUp_Date  |PickUp_Time          |
      |Today+1      |09:30 AM             |
    And I click "GET ESTIMATE" button on Partner Portal
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "Cort service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Bodc_Code|
      |Furniture         |Handle with care   |Testpartner U |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |SVC02/09/00 |
    And I enter the value "Test ScheduledBy" in Scheduled by field
    And I check the Bodc Code dropdown options
    And I select the value in Bodc Code
    Then Partner invoice should be selected as default Payment Method
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I should logout from Partner Portal

 #CORE-2419 :Verify that the delivery scheduling days can be configured to more than 5 days
  @ready
  Scenario: Verify that the delivery scheduling days can be configured to more than 5 days
    When I change the pickup address to "1 International Square, Kansas City, United States, Missouri, 64153" on partner portal
    And I click on the checkbox
    And I should be able to schedule a trip "28"days from today
    And I click on "Start Over" button
    And I request "Solo" Bungii trip in partner portal configured for "Cort service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
    And I should be able to schedule a trip "28"days from today
    Then The time should be different when the pickup address is changed to a different geofence
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    When I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Cort Furniture #7302" Alias
    When I change the service level to "First Threshold" in "Partner" portal
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
   When I enter all details on "Delivery Details" for "Cort service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Bodc_Code|
      |Furniture         |Handle with care   |Testpartner U |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |SVC02/09/00 |
    And I enter the value "Test ScheduledBy" in Scheduled by field
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD2|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I wait for 2 minutes
    When I am logged in as Admin

   And I view the all Scheduled Deliveries list on the admin portal
   And  I search the delivery using "Pickup Reference"
   # CORE-3595 - Searching Drivers status for scheduled deliveries 5+ days out
   Then I should see the status of the trip as "Pending"
   Then I should see the trip scheduled for "28" days ahead
   When I click on the "Edit" button from the dropdown
   And I click on "Edit Trip Details" radiobutton
   And I change the trip delivery date to "8" days ahead from today
   And I assign driver "Testdrivertywd_appledc_a_drvj WashingtonDC_j" for the trip
   And I select reason as "Partner initiated"
   And I click on "Verify" button on Edit Scheduled bungii popup
   And I click on "Save" button on Edit Scheduled bungii popup
   Then "Bungii Saved!" message should be displayed
   And I get the new pickup reference generated
   And I wait for "2" mins
   When I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
   Then I should be able to see the respective bungii with the below status
   |  Status |
   | Scheduled |
   And  I search the delivery using "Pickup Reference"
   When I click on the "Edit" button from the dropdown
   And I select the first driver
   And I click on "Remove Driver" button
   And I wait for 2 minutes
   And I view the all Scheduled Deliveries list on the admin portal
   And  I search the delivery using "Pickup Reference"
   Then I should be able to see the respective bungii with the below status
      |  Status |
      | Driver Removed |
   When I click on the "Edit" button from the dropdown
   And I click on "Research" button
   And I wait for 2 minutes
   And I view the all Scheduled Deliveries list on the admin portal
   And  I search the delivery using "Pickup Reference"
   Then I should be able to see the respective bungii with the below status
     |  Status |
    | Pending |
   And I view the all Scheduled Deliveries list on the admin portal
    And I unselect the Pending status from the filter category
    And I click on the "Apply" Button
    And I get the new pickup reference generated
    Then I should see the message "No deliveries found." displayed
    When I select filter "Statuses" as "Pending"
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Pending |
    And  I search the delivery using "Pickup Reference"
    When I click on the "Edit" button from the dropdown
    And I click on "Edit Trip Details" radiobutton
    And I change the trip delivery date to "3" days ahead from today
    And I select reason as "Partner initiated"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I get the new pickup reference generated
    And I wait for "2" mins
    When I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
     |  Status |
     | Assigning Driver(s) |
    And The scheduled trip date should be changed to the new date
    And I click on the "Edit" button from the dropdown
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    When I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for "2" mins
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then Revive button should be displayed beside the trip

    #CORE-4136
  @regression
  Scenario: Verify tooltip verbiage reflects number of days based on advance scheduling days configured in database
    When I click on tooltip beside "Pickup Date" field
    Then I should see tooltip value based on configured value in database
