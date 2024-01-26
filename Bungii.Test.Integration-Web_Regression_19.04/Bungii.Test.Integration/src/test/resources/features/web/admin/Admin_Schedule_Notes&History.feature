@web
Feature: Admin Notes & History

  Background:
    Given I am logged in as Admin

  @testing
  @ready
  #Issue raised ADP-662
  Scenario: To verify Notes-Customer service notes can be added, edited and deleted  by admin1
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                       |
      | NEXT_POSSIBLE | 8877661000     | Testcustomertywd_appleMarkA LutherA |
    And As a driver "Testdrivertywd_appleks_a_gruE Stark_ksOnE" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    And I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    When I enter more than 2100 characters in the text area
    Then I should see the "Save" button disabled
    When I enter the text "Added Driver" in the text area
    And I click the "Save" button
    Then I should see the text Displayed under notes
    When I click on the "Delivery Details" link scheduled bungii and click on the"View" button
    Then  I should see the note created by the admin
    And I close the Note
    When As a driver "Testdrivertywd_appleks_a_gruE Stark_ksOnE" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Enroute       |
    And I wait for 2 minutes
    When I view the Live Deliveries list on  admin portal
    And I search the delivery using "Pickup Reference"
    And I click on the "Notes & History" link beside scheduled bungii for "Live Deliveries"
    And I click the "Edit" button
    And I enter more than 2100 characters in the text area
    Then I should see the "Save" button disabled
    When I change the text to "Added Customer"
    And I click the "Update" button
    Then I should see the text message updated to the new message
    When I click on the "Delivery Details" link scheduled bungii and click on the"View" button
    Then  I should see the note created by the admin
    And I close the Note
    When As a driver "Testdrivertywd_appleks_a_gruE Stark_ksOnE" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state      |
      | Arrived            |
      | Loading Item       |
      | Driving To Dropoff |
      | Unloading Item     |
      | Bungii Completed   |
    And I wait for 2 minutes
    Then The "All Deliveries" page should display the delivery in "Payment Successful" form
    When  I search the delivery using "Pickup Reference"
    And I click on the "Notes & History" link beside scheduled bungii for "All Deliveries"
    And I click the "Delete" button
    Then I should see the note deleted
    And I close the Note
    When I click on the "Delivery Details" link scheduled bungii and click on the"View" button
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed


  @regression
  Scenario:To verify the Notes-Customer service notes of Admin1 can be edited by Admin1 only
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                       |
      | NEXT_POSSIBLE | 8877661001     | Testcustomertywd_appleMarkB LutherB |
    And I wait for 2 minutes
    #CORE-4152:Verify that Estimated Delivery time is displayed correctly for customer trips
    When I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    When I click on the "Delivery Details" button from the dropdown
    Then The "Scheduled Time" for customer delivery should match
    Then The "Estimated Delivery Time" for customer delivery should match
    And As a driver "Testdrivertywd_appleks_a_gruF Stark_ksOnF" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I wait for 2 minutes
    When I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    And I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    When I enter the text "Added Driver" in the text area
    And I click the "Save" button
    Then I should see the text Displayed under notes
    And I log into another "Bungii Admin" portal in a new tab
    When I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference" as "Admin2"
    And I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    Then I should see the "Note" created by "Admin1"
    And The "Edit" link should not be displayed
    When I create a new note as "Admin2"
    And I click the "Save" button
    Then A new note should be displayed under notes
    When  I am logged in as TestAdmin
    And  I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference" as "Admin3"
    And I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    Then I should see all the notes


  @ready
  Scenario: To verify the Notes-Customer service notes field is displayed on delivery details page for Geofence based pricing portal.
    When I navigate to "Partner" portal configured for "normal"
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "kansas" geofence
      | Pickup_Address                                                   | Delivery_Address                                              | Load_Unload_Time |
      | 400 Grand Boulevard, Kansas City, United States, Missouri, 64106 | 1300 Main Street, Kansas City, United States, Missouri, 64105 | 30 minutes       |
    And I select Next Possible Pickup Date and Pickup Time
      | Trip_Time     |
      | NEXT_POSSIBLE |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      | Items_To_Deliver | Customer_Name | Customer_Mobile | Pickup_Contact_Name | Pickup_Contact_Phone |
      | Furniture        | Testpartner C | 9998881111      | Test Pickup         | 9999999359           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      | CardNo     | Expiry | Postal_Code       | Cvv       |
      | VISA CARD5 | 12/23  | VALID POSTAL CODE | VALID CVV |
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I navigate to "Admin" portal
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    Then I should be able to see the respective bungii with the status
      | Status            |
      | Assigning Driver(s) |
    When I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note
    When As a driver "Testdrivertywd_appleks_a_gruG Stark_ksOnG" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    Then I should be able to see the respective bungii with the status
      | Status    |
      | Scheduled |
    When I search the delivery using "Pickup Reference" as "Admin1"
    And I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note
    When As a driver "Testdrivertywd_appleks_a_gruG Stark_ksOnG" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Enroute       |
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Trip Started |
    When I click on the "Notes & History" link beside scheduled bungii for "Live Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note
    When As a driver "Testdrivertywd_appleks_a_gruG Stark_ksOnG" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Arrived       |
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    Then I should be able to see the respective bungii with the status
      | Status            |
      | Driver(s) Arrived |
    When I click on the "Notes & History" link beside scheduled bungii for "Live Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note
    When As a driver "Testdrivertywd_appleks_a_gruG Stark_ksOnG" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Loading Item  |
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    Then I should be able to see the respective bungii with the status
      | Status        |
      | Loading Items |
    When I click on the "Notes & History" link beside scheduled bungii for "Live Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note
    When As a driver "Testdrivertywd_appleks_a_gruG Stark_ksOnG" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state      |
      | Driving To Dropoff |
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    Then I should be able to see the respective bungii with the status
      | Status             |
      | Driving To Dropoff |
    When I click on the "Notes & History" link beside scheduled bungii for "Live Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note
    When As a driver "Testdrivertywd_appleks_a_gruG Stark_ksOnG" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state  |
      | Unloading Item |
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    Then I should be able to see the respective bungii with the status
      | Status          |
      | Unloading Items |
    When I click on the "Notes & History" link beside scheduled bungii for "Live Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note
    When As a driver "Testdrivertywd_appleks_a_gruG Stark_ksOnG" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state    |
      | Bungii Completed |
    And I wait for 2 minutes
    Then The "All Deliveries" page should display the delivery in "Payment Successful" form
    And I search the delivery using "Pickup Reference" as "Admin1"
    When I click on the "Notes & History" link beside scheduled bungii for "All Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note


  @ready
# driver crash
  Scenario: To verify the Notes-Customer service notes field is displayed on delivery details page for Weight based pricing portal
    When I navigate to "Partner" portal configured for "FloorDecor service level" URL
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "see 1 pallet and 2 pallets"
    When I request "Solo" Bungii trip in partner portal configured for "FloorDecor service level" in "washingtondc" geofence
      | Pickup_Address                                                                    | Delivery_Address                                                   |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005 | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837 |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Floor & Decor #240" Alias
    And I change the service level to "First Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      | Trip_Time     |
      | NEXT_POSSIBLE |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "FloorDecor service level" on partner screen
      | Product_Description | Dimensions | Weight | Special_Instruction | Customer_Name | Customer_Mobile | Pickup_Contact_Name | Pickup_Contact_Phone | Drop_Off_Contact_Name | Drop_Contact_Phone | Delivery_Purpose | Rb_Sb_Number | ScheduledBy |
      | 20 boxes            | 20X20X20   | 100   | Handle with care    | Testartner T  | 9998881111      | Test Pickup         | 9999999359           | Test Dropcontact      | 9998881112         | For decoration   | 007          | FND166  |
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I am logged in as Admin
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    Then I should be able to see the respective bungii with the status
      | Status            |
      | Assigning Driver(s) |
    When I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note
    When As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the status
      | Status    |
      | Scheduled |
    When I search the delivery using "Pickup Reference" as "Admin1"
    And I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note


  @regression
  Scenario: To verify the Notes-Customer service notes field is displayed on delivery details page for Fixed pricing portal
    When I navigate to "Partner" portal configured for "BestBuy service level"
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Solo" Bungii trip in partner portal configured for "BestBuy service level" in "Kansas" geofence
      | Pickup_Address                                | Delivery_Address                                            |
      | 1735 Noriega St, San Francisco, CA, US, 94122 | 6800 Zoo Drive, Kansas City, United States, Missouri, 64132 |
    And I select Next Possible Pickup Date and Pickup Time
      | Trip_Time     |
      | NEXT_POSSIBLE |
    Then I should "see Delivery Cost: N/A"
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "BestBuy service level" on partner screen
      | Items_To_Deliver | Special_Instruction | Customer_Name | Customer_Mobile | Pickup_Contact_Name | Pickup_Contact_Phone | Drop_Off_Contact_Name | Drop_Contact_Phone | Order_Number | EmployeeID |
      | 5067400          | Handle with care    | Testpartner U | 9998881111      | Test Pickup         | 9999999359           | Test Dropcontact      | 9998881112         | ON1          | 12345 |
    Then I should "see Delivery Cost: N/A on Delivery Details screen"
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I navigate to "Admin" portal
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    Then I should be able to see the respective bungii with the status
      | Status            |
      | Searching Drivers |
    When I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note
    When As a driver "Testdrivertywd_appleks_a_gruH Stark_ksOnH" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the status
      | Status    |
      | Scheduled |
    When I search the delivery using "Pickup Reference" as "Admin1"
    And I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note
    When As a driver "Testdrivertywd_appleks_a_gruH Stark_ksOnH" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Enroute       |
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Trip Started |
    When I click on the "Notes & History" link beside scheduled bungii for "Live Deliveries"
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    And I close the Note


  @regression
  Scenario:To verify the Notes-Customer service notes of Admin1 can be added multiple times after ADMIN2 has added Notes to the same delivery
    When  I am logged in as TestAdmin
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                       |
      | NEXT_POSSIBLE | 8877661002     | Testcustomertywd_appleMarkC LutherC |
    When As a driver "Testdrivertywd_appleks_a_gruI Stark_ksOnI" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I wait for 2 minutes
    When I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    And I click on the dropdown beside scheduled bungii
    Then I should see the "Notes" field not underlined
    When I click the "Notes & History" link
    Then I should see the following text message "No notes available. Please start entering notes to appear here." displayed
    When I enter the text "Added Driver" in the text area
    And I click the "Save" button
    Then I should see the text Displayed under notes
    And I log into another "Bungii Admin" portal in a new tab
    When I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference" as "Admin1"
    And I click on the dropdown beside scheduled bungii
    Then I should see the "Notes" underlined
    When I click the "Notes & History" link
    Then I should see the "Note" created by "Admin2"
    And The "Edit" link should not be displayed
    When I create multiple notes
    Then I should see the notes displayed̥

  #CORE-3382
  @ready
  Scenario:To verify admin is able to see edit History for customer solo trip
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                         |
      | NEXT_POSSIBLE | 8877661201     | Testcustomertywd_BppleMarkGT LutherGT |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    And I click on the dropdown beside scheduled bungii
    Then I should see the "History" field not underlined
    When I click the "Notes & History" link
    And I click on "History"
    Then The "History" tab should be selected
    And I should see no history text
    And I close the Note
    And As a driver "Testdrivertywd_appleks_a_drvcj Kansas_cj" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I wait for 2 minutes
    And As a driver "Testdrivertywd_appleks_a_drvcj Kansas_cj" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state      |
      | Enroute            |
      | Arrived            |
      | Loading Item       |
      | Driving To Dropoff |
      | Unloading Item     |
      | Bungii Completed   |
