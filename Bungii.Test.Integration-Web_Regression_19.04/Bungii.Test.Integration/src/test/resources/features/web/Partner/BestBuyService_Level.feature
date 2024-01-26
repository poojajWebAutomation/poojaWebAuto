@web
Feature: Service Level

  Background:
    Given I navigate to "Partner" portal configured for "BestBuy service level" URL

  @regression
      #stable
  Scenario: Verify that NA is shown for Best buy service level on configured Partner portal site.
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Solo" Bungii trip in partner portal configured for "BestBuy service level" in "Kansas" geofence
      | Pickup_Address                                 | Delivery_Address                                             |
      | 1735 Noriega St, San Francisco, CA, US, 94122  | 6800 Zoo Drive, Kansas City, United States, Missouri, 64132  |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    Then I should "see Delivery Cost: N/A"
    And I click "GET ESTIMATE" button on Partner Portal
    And I click "Continue" button on Partner Portal

#      Core - 2619 Verify Driver Availability count is reduced by 1 when Solo trip is scheduled
    And I check in the db the number of timeslots available "before schedule for best buy"
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "BestBuy service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Order_Number|EmployeeID     |
      |5067400         |Handle with care   |Testpartner U |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |9281         |12345|
    Then I should "see Delivery Cost: N/A on Delivery Details screen"
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I click on Filter and select check/unchecked all checkbox
    And I click on Apply button on Filter
    Then I should not able to see Filter screen
    And I select the Scheduled Bungii from Delivery List
    Then I should "see Delivery Cost: N/A on Delivery Details screen"
    When As a driver "Testdrivertywd_appleks_a_gruJ Stark_ksOnJ" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I check in the db the number of timeslots available "after schedule for best buy"
#    Core - 2989 Verify that Driver Availability count is incremented by 1 on cancelling a scheduled solo trip
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Scheduled        |
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    Then I check in the db the number of timeslots available "after cancelling solo trip"

      #CORE-3199-To verify that First 5 Partner portal deliveries are indicated on scheduled delivery page
  @ready
  Scenario: To verify that First 5 Partner portal deliveries are indicated on scheduled delivery page
    When  I am logged in as Admin
    And I view the all Scheduled Deliveries list on the admin portal
    And I view the Live Deliveries list on  admin portal
    And I check if partner trips are already present
    And I view All Deliveries list on the admin portal
    And I check if partner trips are already present
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661035 | Testcustomertywd_appleMarkAJ LutherAJ|
    And As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
#    Core-4080 Verify that email notification is sent on configured email id's once first partner portal delivery is scheduled
    And Partner firm should receive "1st-Delivery" email
#   Core - 2619  Verify that Partner Portals with multiple addresses have independent time slots for each store address.
    When I check in the db the number of timeslots available "for bestbuy first address" new portal
    When I check in the db the number of timeslots available "for bestbuy second address" new portal

    And  I am logged in as Admin
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then In "Scheduled Deliveries" the trip should be  having a indicator with the text "New-1"
    When As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And I search the delivery using "Pickup Reference"
    Then In "Live Deliveries" the trip should be  having a indicator with the text "New-1"
    When As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Bungii Completed |
    And I wait for 2 minutes
#   Core-3647 Verify that email notification is sent on configured email id's once first 3 partner portal trips are completed
    And Partner firm should receive "1st-Initial deliveries" email
    And I view All Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference"
    Then The "All Deliveries" page should display the delivery in "Payment Successful" form
    And In "All Deliveries" the trip should be  having a indicator with the text "New-1"
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661035 | Testcustomertywd_appleMarkAJ LutherAJ|
    When As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I wait for 2 minutes
    And  I am logged in as Admin
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then In "Scheduled Deliveries" the trip should be  having a indicator with the text "New-2"
    When I click on the "Edit" button from the dropdown
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "Fells Point Dental, Fell Street"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I get the new pickup reference generated
    And I wait for "2" mins
    And As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
    And I wait for 2 minutes
    When I view the Live Deliveries list on  admin portal
    And I search the delivery using "Pickup Reference"
    Then In "Live Deliveries" the trip should be  having a indicator with the text "New-2"
    And As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Bungii Completed |
    And I wait for 2 minutes
    And Partner firm should receive "2nd-Initial deliveries" email
    And I view All Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference"
    Then The "All Deliveries" page should display the delivery in "Payment Successful" form
    And In "All Deliveries" the trip should be  having a indicator with the text "New-2"
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661035 | Testcustomertywd_appleMarkAJ LutherAJ|
    When I navigate to "Partner" portal configured for "BestBuy2 service level" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I click the "Track Deliveries" button on Partner Portal
    And I click on the delivery based on customer name
    And I click "Cancel Delivery link" button on Partner Portal
    And I click "Cancel Delivery" button on Partner Portal
    Then I click "OK" button on Partner Portal
    When I navigate to "Admin" portal configured for "QA" URL
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then In "All Deliveries" the trip should be  having a indicator with the text "New-3"
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661035 | Testcustomertywd_appleMarkAJ LutherAJ|
    And As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state   |
      | Accepted      |
      | Enroute       |
      | Driver Canceled |
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then In "All Deliveries" the trip should be  having a indicator with the text "New-4"
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661035 | Testcustomertywd_appleMarkAJ LutherAJ|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    And I click on the "Edit" button from the dropdown
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for "2" mins
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then In "All Deliveries" the trip should be  having a indicator with the text "New-5"
    And Revive button should be displayed beside the trip
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
    When I click on "Confirm" button on Revival Popup
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    Then In "Scheduled Deliveries" the trip should be  having a indicator with the text "New-5"
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661036 | Testcustomertywd_appleMarkAK LutherAK|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The delivery should not be having indicator
    And As a driver "TestDrivertywd_applemd_a_billC Stark_bltTwO" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state   |
      | Accepted      |
      | Enroute       |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
      | Bungii Completed |
    And I wait for 2 minutes
    Then Partner firm should receive "3rd-Initial deliveries" email

  @ready
  Scenario: Verify Driver Availability count is reduced by 2 when Duo trip is scheduled
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Duo" Bungii trip in partner portal configured for "BestBuy service level" in "Kansas" geofence
      | Pickup_Address                                 | Delivery_Address                                             |
      | 1735 Noriega St, San Francisco, CA, US, 94122  | 6800 Zoo Drive, Kansas City, United States, Missouri, 64132  |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    Then I should "see Delivery Cost: N/A"
    And I click "GET ESTIMATE" button on Partner Portal
    And I check in the db the number of timeslots available "before schedule for best buy"
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "BestBuy service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Order_Number|EmployeeID     |
      |5067400         |Handle with care   |Testcustomertywd_appleMarkZ LutherZ |8877661025     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |ON1         |12345|
    Then I should "see Delivery Cost: N/A on Delivery Details screen"
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When As a driver "Testdrivertywd_appleks_a_gruJ Stark_ksOnJ" and "Testdrivertywd_appleks_a_gruK Stark_ksOnK" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I check in the db the number of timeslots available "after schedule for duo for best buy"
    And I click "New Bungii" button on Partner Portal
    When I request "Solo" Bungii trip in partner portal configured for "BestBuy service level" in "Kansas" geofence
      | Pickup_Address                                 | Delivery_Address                                             |
      | 1735 Noriega St, San Francisco, CA, US, 94122  | 6800 Zoo Drive, Kansas City, United States, Missouri, 64132  |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    Then I should "see Delivery Cost: N/A"
    And I click "GET ESTIMATE" button on Partner Portal
#   Core - 2619 Verify that available deliveries are reduced in each partner portal having similar store addresses.
    And I check in the db the number of timeslots available "before schedule for best buy"
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "BestBuy service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Order_Number|EmployeeID     |
      |5067400         |Handle with care   |Testcustomertywd_appleMarkAA LutherAA |8877661026     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |ON1         |12345|
    Then I should "see Delivery Cost: N/A on Delivery Details screen"
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When As a driver "Testdrivertywd_appleks_a_drvad Kansas_ad" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I check in the db the number of timeslots available "after schedule for best buy"
#    Core - 2989 Verify that Driver Availability count is incremented/reduced when admin edits date and time
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Scheduled        |
    And I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Date" and select future time
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I check in the db the number of timeslots available "after changing date and time"

#    Core - 2989 Verify that Driver Availability count is reduced when admin converts a trip from solo to duo
  @ready
  Scenario: Verify that Driver Availability count is reduced when admin converts a trip from solo to duo
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Solo" Bungii trip in partner portal configured for "BestBuy service level" in "Kansas" geofence
      | Pickup_Address                                 | Delivery_Address                                             |
      | 1735 Noriega St, San Francisco, CA, US, 94122  | 6800 Zoo Drive, Kansas City, United States, Missouri, 64132  |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    Then I should "see Delivery Cost: N/A"
    And I click "GET ESTIMATE" button on Partner Portal
    And I check in the db the number of timeslots available "before schedule for best buy"
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "BestBuy service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Order_Number|EmployeeID     |
      |5067400         |Handle with care   |Testcustomertywd_appleMarkAB LutherAB|8877661027     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |ON1         |12345|
    Then I should "see Delivery Cost: N/A on Delivery Details screen"
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When As a driver "Testdrivertywd_appleks_a_gruE Stark_ksOnE" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I check in the db the number of timeslots available "after schedule for best buy"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Scheduled        |
    And I click on "Edit" link beside scheduled bungii
    When I click on "Edit Trip Details" radiobutton
    And I change delivery type from "Solo to Duo"
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed
    And I wait for "2" mins
    Then I check in the db the number of timeslots available "after changing bungii type from solo to duo"
#    Core - 2989 Verify that Driver Availability count is incremented by 2 on cancelling a scheduled duo trip
    When I click on "Close" button
    And I refresh the page
    And I get the new pickup reference generated
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery of Customer
    Then I should be able to see the respective bungii with the below Delivery Type
      | Type |
      | Duo |
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    Then I check in the db the number of timeslots available "after cancelling duo trip"

#    Core - 2989 Verify that Driver Availability count is incremented when admin converts a trip from duo to solo
  @ready
  Scenario: Verify that Driver Availability count is incremented when admin converts a trip from duo to solo
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Duo" Bungii trip in partner portal configured for "BestBuy service level" in "Kansas" geofence
      | Pickup_Address                                 | Delivery_Address                                             |
      | 1735 Noriega St, San Francisco, CA, US, 94122  | 6800 Zoo Drive, Kansas City, United States, Missouri, 64132  |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    Then I should "see Delivery Cost: N/A"
    And I click "GET ESTIMATE" button on Partner Portal
    And I check in the db the number of timeslots available "before schedule for best buy"
#    Core - 2619 Verify that Driver Availability count is not reduced/incremented for a different partner portal once a slot is utilized/restored in one partner portal under same geofence
    And I check in the db the number of timeslots available "before schedule for mrfm"
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "BestBuy service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Order_Number|EmployeeID     |
      |5067400         |Handle with care   |Testcustomertywd_appleMarkAC LutherAC |8877661028     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |ON1         |12345|
    Then I should "see Delivery Cost: N/A on Delivery Details screen"
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When As a driver "Testdrivertywd_appleks_a_drvaw Kansas_aw" and "Testdrivertywd_appleks_a_drvax Kansas_ax" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I check in the db the number of timeslots available "after schedule for duo for best buy"
    And I check in the db the number of timeslots available "after schedule for mrfm"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Scheduled        |
    And I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I wait for "2" mins
    When I click on "Edit Trip Details" radiobutton
    And I change delivery type from "Duo to Solo"
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed
    And I wait for "2" mins
    And I check in the db the number of timeslots available "after changing bungii type from duo to solo"

#CORE-2419:Verify that correct date and time slots are displayed for partner portals having multiple pickup addresses
  @ready
  Scenario: Verify that correct date and time slots are displayed for partner portals having multiple pickup addresses
    When I navigate to "Partner" portal configured for "BestBuy2 service level" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I select the "First" address from the pickup address dropdown
    And I should be able to schedule a trip "29"days from today
    And I select the "Second" address from the pickup address dropdown
    And I should be able to schedule a trip "29"days from today
    Then The pickup time should be same for both the addresses from the dropdown

  #CORE-2342:To verify whether correct default contact name and phone no is added when store address is selected as pickup address
  @regression
  Scenario:To verify whether correct default contact name and phone no. is added when store address is selected as pickup address
    When I navigate to "Partner" portal configured for "BestBuy2 service level" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I add the delivery address as "700 South Potomac Street, Baltimore, Maryland, 21224"
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should "see Delivery Details screen"
    Then The Pickup contact name "Best Buy Customer Service" and pickup contact phone number "(992) 326-1261" field should be filled

 #CORE-3381: To verify that Revive button is not present for deliveries scheduled more than 5 days in advance
  @ready
  Scenario: To verify that Revive button is not present for deliveries scheduled more than 5 days in advance
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661061 | Testcustomertywd_BppleMarkBJ LutherBJ|
    And I wait for 2 minutes
    And  I am logged in as Admin
    And I view the all Scheduled Deliveries list on the admin portal
    When I click on the "Edit" button from the dropdown
    And I click on "Edit Trip Details" radiobutton
    And I change the trip delivery date to "7" days ahead from today
    And I select reason as "Partner initiated"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I get the new pickup reference generated
    And I wait for "2" mins
    When I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Pending |
    And The scheduled trip date should be changed to the new date
    And I click on the "Edit" button from the dropdown
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for "2" mins
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The revive button should not be displayed

  @ready
      #stable
  Scenario: Verify that Order number field accepts only integer values on configured Best buy Partner portal site.
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Solo" Bungii trip in partner portal configured for "BestBuy service level" in "Kansas" geofence
      | Pickup_Address                                 | Delivery_Address                               |
      | 9301 Quivira Rd, Overland Park, KS, US, 66215  | 9301 Quivira Rd, Overland Park, KS, US, 66215  |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    Then I should "see Delivery Cost: N/A"
    And I click "GET ESTIMATE" button on Partner Portal
    And I click "Continue" button on Partner Portal
#      Core - 3367 Verify that Order number field accepts only integer values on configured Best buy Partner portal site.
    And I check in the db the number of timeslots available "before schedule for best buy"
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "BestBuy service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Order_Number|EmployeeID     |
      |5067400         |Handle with care   |Testpartner U |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |12345         |12345|
    Then I should "see Delivery Cost: N/A on Delivery Details screen"
    And I check if "Order number" field accepts only integer values
    And I check if "Employee number" field accepts only integer values
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
#     Core-4246 Verify searching delivery using external order id on Admin portal
    When  I am logged in as Admin
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery using "ExternalOrderId"
    Then I should be able to see the respective bungii with the status
      | Status            |
      | Assigning Driver(s) |
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the status
      | Status            |
      | Assigning Driver(s) |
    And I search the delivery using "Invalid ExternalOrderId"
    Then I should see the message "No deliveries found." displayed
    When As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    And I search the delivery using "Invalid ExternalOrderId"
    Then I should see the message "No deliveries found." displayed
    When As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Arrived       |
      | Loading Item  |
      | Driving To Dropoff |
      | Unloading Item |
      | Bungii Completed |
    And I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    And I search the delivery using "ExternalOrderId"
    Then The "All Deliveries" page should display the delivery in "Payment Successful" form
    And  I search the delivery using "Pickup Reference"
    Then The "All Deliveries" page should display the delivery in "Payment Successful" form
    And I search the delivery using "Invalid ExternalOrderId"
    Then I should see the message "No deliveries found." displayed

#     CORE-4118: Verify Partner email is sent when admin edit the drop off address of the Ongoing trip- Fixed pricing - B2C
  @ready
  Scenario: Verify Partner email is sent when admin edit the drop off address  of the Ongoing trip- Fixed pricing - B2C
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661217 | Testcustomertywd_BppleMarkHJ LutherHJ|
    And As a driver "TestDrivertywd_applemd_a_billL BaltimoreL" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted     |
      | Enroute  |
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the Live Deliveries list on the admin portal
    And I wait for 2 minutes
    When  I search the delivery using "Pickup Reference"
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "Club International, West Baltimore Street, Baltimore, MD, USA"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "1" mins
    Then Partner firm should receive "Bungii Delivery Updated" email

#     CORE-4118: Verify Partner email is sent when payment method used is Customer Card for B2B
  @ready
  Scenario: Verify Partner email is sent when payment method used is Customer Card for B2B
    When I request Partner Portal "SOLO" Trip for "Biglots" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |atlanta  | NEXT_POSSIBLE | 8877661218 | Testcustomertywd_BppleMarkHK LutherHK|
    And As a driver "Testdrivertywd_applega_a_drvaq Atlanta_aq" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
      | Enroute  |
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the Live Deliveries list on the admin portal
    And I wait for "2" mins
    When  I search the delivery using "Pickup Reference"
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I edit the pickup address
    Then I change the pickup address to "Atlanta International Airport, Spine"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "1" mins
    Then Partner firm should receive "Bungii Delivery Updated" email
