@web
Feature: Admin_Schedule_Delivery_Edit

  Background:
    Given I am logged in as Admin

  @regression
      #failed in sprint 49
    Scenario: Verify editing drop off address for the Solo scheduled delivery
      When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
        | Bungii Time   | Customer Phone | Customer Name |
        | NEXT_POSSIBLE | 9999999200     | Testcustomertywd_appleNewM Customer  |
      And I view the all Scheduled Deliveries list on the admin portal
      And I wait for "2" mins
      Then I should be able to see the respective bungii with the below status
        |  Status |
        | Assigning Driver(s) |
      Then I check the price for delivery
      When I click on "Edit" link beside scheduled bungii
      And I click on "Edit Trip Details" radiobutton
      And I edit the drop off address
      Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
      #4400 Massachusetts Avenue Northwest
      And I change the customer note to "PickupNote edited successfully."
      And I click on "Verify" button on Edit Scheduled bungii popup
      When I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      And I wait for "2" mins
      And  I refresh the page
      And I get the new pickup reference generated
      And  I search the delivery using "Pickup Reference"
      #CORE-4152:Verify that estimated delivery time is calculated correctly when admin edits scheduled address of customer trip
      When I click on the "Delivery Details" button from the dropdown
      Then The "Scheduled Time" for customer delivery should match
      Then The "Estimated Delivery Time" for customer delivery should match
      And I view the all Scheduled Deliveries list on the admin portal
      And  I search the delivery using "Pickup Reference"
      And I click on the dropdown beside scheduled bungii
      Then I should see the "History" underlined
      #CORE-3382
      When I click the "Notes & History" link
      And I click on "History"
      Then The "History" tab should be selected
      And I should see drop off address edit history
      And I close the Note
      When I view the delivery details in admin portal
      Then the updated drop off address should be displayed on delivery details page
      And I confirm Pickup note is "Updated"
      And Delivery price is recalculated based on updated value of drop off address

  @regression
      #stable
  Scenario: Verify editing drop off address for the duo scheduled trip
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999202     | Testcustomertywd_appleNewO Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    Then I check the price for delivery
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I remove the customer note
    And I click on "Verify" button on Edit Scheduled bungii popup
      #Then Tick mark should be displayed beside driver and scheduled date
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I view the delivery details
    Then the updated drop off address should be displayed on delivery details page
    And I confirm Pickup note is "Deleted"
    And Delivery price is recalculated based on updated value of drop off address
  
  @regression
    #stable
   #  ADP-687
  Scenario: Verify editing drop off address outside of scope for the Solo scheduled trip.
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999203     | Testcustomertywd_appleNewP Customer|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    And I change the drop off address to "8500 Scudder Avenue, Copiague"
    And I click on "Verify" button on Edit Scheduled bungii popup
    Then "Oops! It looks like this trip is a little outside our scope." message should be displayed
#    Core-3294: Verify Stop search button is displayed for customer schedule trips
    When I click on the "Delivery Details" button from the dropdown
    And I check if "Stop Searching" button is displayed
    Then I stop searching driver
    And I wait for "2" mins
#    Core-3294: Verify status of the trip is driver not found after admin stop search
    And I refresh the page
    Then I check if the status has been changed to "No Driver(s) Found"
#    Core-3294: Verify stop search button is hidden when admin stop search any trip
    Then I check if "Stop Searching" button is not present
#    Core-3294: Verify stop search status is updated in DB
    Then I check if stop search status is updated in DB
#   Core-3294: Verify stop search button is enabled when admin edit trip after it was stopped searching
    When I navigate back to Scheduled Deliveries
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on the "Delivery Details" button from the dropdown
    Then I check if "Stop Searching" button is displayed
#   Core-3294: Verify stop search button is shown under Live delivery for status assigning driver
    And I get the new pickup reference generated
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Assigning Driver(s)|
    When I click on the "Delivery Details" button from the dropdown
    Then I check if "Stop Searching" button is displayed
#   Core-3294: Verify error is shown when admin stop search again before its status is synced
    Then I stop searching driver
    When I click on the "Delivery Details" button from the dropdown
    Then I check if error is shown when admin stop search again before its status is synced

  @regression
    #stable
    #  ADP-687
  Scenario: Verify editing drop off address outside of scope for the Duo scheduled trip.
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999201 | Testcustomertywd_appleNewN Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    And I change the drop off address to "8500 Scudder Avenue, Copiague"
    And I click on "Verify" button on Edit Scheduled bungii popup
    Then "Oops! It looks like this trip is a little outside our scope." message should be displayed
  
  @regression
    #stable
  Scenario: Verify editing drop off address for the Solo scheduled delivery when driver is assigned.
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999204     | Testcustomertywd_appleNewQ Customer|
    And As a driver "Testdrivertywd_appledc_a_webaa Testdriveraa" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then the updated drop off address should be displayed on delivery details page
    And Delivery price is recalculated based on updated value of drop off address

    
  @regression
    #stable
     Scenario: Verify editing drop off address for the Partner Portal Solo Scheduled delivery.
       When I request Partner Portal "SOLO" Trip for "MRFM" partner
         |Geofence| Bungii Time   | Customer Phone | Customer Name |
         |Kansas| NEXT_POSSIBLE | 9999999205 | Testcustomertywd_appleNewR Customer|
       And I view the all Scheduled Deliveries list on the admin portal
       Then I should be able to see the respective bungii with the below status
         |  Status |
         | Assigning Driver(s) |
       And I check the price for delivery
       When I click on "Edit" link beside scheduled bungii
       And I click on "Edit Trip Details" radiobutton
       And I edit the drop off address
       Then I change the drop off address to "400 Speedway Boulevard"
       And I click on "Verify" button on Edit Scheduled bungii popup
       When I click on "Save" button on Edit Scheduled bungii popup
       Then "Bungii Saved!" message should be displayed
       And I wait for "2" mins
       When I view the delivery details in admin portal
       Then the updated drop off address should be displayed on delivery details page
       And Delivery price is recalculated based on updated value of drop off address


  @ready
  Scenario: Verify that additional notes textbox is not displayed for Partner portal on Schedule deliveries edit page on admin portal
    When I navigate to "Partner" portal configured for "normal" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner A        |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo    |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I am logged in as Admin
    And I wait for "2" mins
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      |  Assigning Driver(s)  |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    Then I should see "Special Instructions" field empty


  @ready
  Scenario: To verify admin is able to add notes for customer trips in admin portal when customer has not added any additional notes
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                         | Customer Note|
      | NEXT_POSSIBLE | 9999999200     | Testcustomertywd_appleNewM Customer   |      Blank   |
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    Then I check the price for delivery
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    Then I should see "Additional Notes" field empty
    And I change the customer note to "New Note Added by Admin"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    And I confirm Pickup note is "Added"

  #Core-3922 Verify trip is highlighted when gap in scheduled time and initial request time is less than 24hrs (Partner portal)
  @ready
  Scenario: Verify trip is highlighted when gap in scheduled time and initial request time is less than 24hrs (Partner portal)
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE_FIRST_SLOT | 8877661022     | Cci12345          | Testcustomertywd_appleMarkW LutherW |
    And I wait for "3" mins
    #CORE-3295:Verify status is shown as 'No Driver(s) Found' on All deliveries screen when required number of drivers has not accepted the trip
    When I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s)" state
    Then I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on the "Delivery Details" button from the dropdown
    Then The delivery should show "Assigning Driver(s)" status on delivery details
    And I wait for "3" mins
    And I wait for "3" mins
    And I view the Live Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    When I click on the "Delivery Details" button from the dropdown
    Then The delivery should show "Assigning Driver(s)" status on delivery details
    And I wait for "3" mins
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    And I wait for "3" mins
    And I view the all Scheduled Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s) with no loader" state
    When I click on the "Delivery Details" button from the dropdown
    Then The delivery should show "No Driver(s) Found" status on delivery details
    #Core-3922 Verify that deliveries are highlighted only on Scheduled and Live deliveries page
    When I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should see the "orange" background colour
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    Then The delivery should be in "Assigning Driver(s) with no loader" state
    When I click on the "Delivery Details" button from the dropdown
    Then The delivery should show "No Driver(s) Found" status on delivery details
    And I view the Live Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should see the "orange" background colour
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The delivery should be in "No Driver(s) Found" state
    When I click on the "Delivery Details" button from the dropdown
    Then The delivery should show "No Driver(s) Found" status on delivery details
