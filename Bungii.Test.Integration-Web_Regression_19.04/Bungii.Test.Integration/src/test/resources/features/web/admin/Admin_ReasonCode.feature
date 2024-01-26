@web
@new
Feature: Admin_Reason_Code

  Background:
    Given I am logged in as Admin

  @ready
  @testing
  Scenario: Verify Reason dropdown is displayed for Solo re-schedule delivery when no Driver accepts,initiated by Customer and Admin edits only time
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999106 | Testcustomertywd_appleNewQF Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated time should be displayed on delivery details page
    #CORE-4152: Estimated delivery time is correct when admin edits Scheduled time
    And I get the new pickup reference generated
    And I view the all Scheduled Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    When I click on the "Delivery Details" button from the dropdown
    Then The "Scheduled Time" for customer delivery should match
    Then The "Estimated Delivery Time" for customer delivery should match

  @ready
  Scenario: Verify Reason dropdown from Duo re-schedule delivery when no Driver accepts,initiated by Customer and Admin edits only time
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999107     | Testcustomertywd_appleNewQG Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated time should be displayed on delivery details page

  @ready
    Scenario: Verify Reason dropdown is displayed for Customer SOLO re-scheduled delivery when NO Driver accepts and Admin edits only Date
      When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
        | Bungii Time   | Customer Phone | Customer Name |
        | NEXT_POSSIBLE | 9999999108 | Testcustomertywd_appleNewQH Customer|
      And I view the all Scheduled Deliveries list on the admin portal
      And I wait for "2" mins
      Then I should be able to see the respective bungii with the below status
        |  Status |
        | Assigning Driver(s) |
      When I click on "Edit" link beside scheduled bungii
      And I click on "Edit Trip Details" radiobutton
      And I click on the "Date" and select future time
      And I click on "Reason" for change time
      And I click on "Customer initiated" in the dropdown
      And I click on "Verify" button on Edit Scheduled bungii popup
      When I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      And I wait for "2" mins
      Then the updated date should be displayed on delivery details page
     #CORE-4152: Estimated delivery time is correct when admin edits Scheduled date
     And I get the new pickup reference generated
     And I view the all Scheduled Deliveries list on the admin portal
     When  I search the delivery using "Pickup Reference"
     When I click on the "Delivery Details" button from the dropdown
     Then The "Scheduled Time" for customer delivery should match
     Then The "Estimated Delivery Time" for customer delivery should match

  @ready
  Scenario: Verify Reason dropdown is displayed for Customer SOLO/ DUO  re-scheduled delivery  when NO Driver accepts and Admin edits both Date and Time
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999109 | Testcustomertywd_appleNewQI Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Date" and select future time
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    And  I refresh the page
    And I click on the dropdown beside scheduled bungii
    #CORE-3382
    When I click the "Notes & History" link
    And I click on "History"
    Then The "History" tab should be selected
    And I should see edit date time history
    And I close the Note
    Then the updated date should be displayed on delivery details page


  @ready
  Scenario: Verify Reason dropdown is hidden when Admin does not edit date or time or both and verify reason dropdown values
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999110     | Testcustomertywd_appleNewQJ Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I check if the "Reason" field is hidden
    And I click on the "Time" and select future time
    And I click on "Reason" for change time and check reason dropdown values
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated time should be displayed on delivery details page


  @ready
  Scenario: Verify Reason dropdown for Customer DUO re-scheduled delivery when Single Driver accepts
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name  |
      | NEXT_POSSIBLE | 9999999111     | Testcustomertywd_appleNewQK Customer|
    And As a driver "Testdrivertywd_appledc_a_drvl WashingtonDC_l" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      |Accepted |
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Date" and select future time
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated date should be displayed on delivery details page

  @ready
  Scenario: Verify Reason dropdown for Customer DUO re-scheduled delivery when  Both Drivers do not accept the delivery
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999112     | Testcustomertywd_appleNewQL Customer|
    And I wait for "5" mins
    And I wait for "3" mins
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "5" mins
    And I wait for "5" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    Then the updated time should be displayed on delivery details page

  @regression
  Scenario: Verify Reason is not mandatory when pickup/drop off address edited and driver is assigned by Admin for Customer delivery
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999113     | Testcustomertywd_appleNewQM Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I check if the "Reason" field is hidden
    And I click on "Add Driver" and add "Testdrivertywd_appledc_a_drvl WashingtonDC_l" driver
    And I check if the "Reason" field is hidden
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then the updated drop off address should be displayed on delivery details page

  @regression
  Scenario: Verify reschedule reason is not displayed for LIVE trips
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999114     | Testcustomertywd_appleNewQN Customer|
    And As a driver "Testdrivertywd_appledc_a_drvn WashingtonDC_n" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | Loading Items |
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I check if the "Reason" field is present
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I check if the "Reason" field is present
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I open the live delivery details in admin portal
    Then the updated drop off address should be displayed on delivery details page


  @regression
  Scenario: Verify Reason is not mandatory when Service Level edited  for Partner delivery
    When I navigate to "Partner" portal configured for "service level" URL
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                   |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002  |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Biglots" Alias
    And I change the service level to "Room of Choice" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
      |Furniture       |Handle with care   |Testcustomertywd_appleNewQO Customer |9999999115    |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I close the Trip Delivery Details page
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I check if the "Reason" field is hidden
    And I change the service level to "Threshold" in "Admin" portal
    And I check if the "Reason" field is hidden
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed


  @regression
  Scenario: Verify Reason is not mandatory when drop-off/pickup address edited and driver is assigned for Partner delivery
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 9999999116 | Testcustomertywd_appleNewQP Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    And I check the price for delivery
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I check if the "Reason" field is hidden
    And I edit the drop off address
    Then I change the drop off address to "400 Speedway Boulevard"
    And I check if the "Reason" field is hidden
    And I click on "Add Driver" and add "Testdrivertywd_appleks_a_drvk Kansas_k" driver
    And I check if the "Reason" field is hidden
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then the updated drop off address should be displayed on delivery details page


  @ready
    #Raised CORE-5958 as Admin is unable to edit the date to future.
  Scenario: Verify Reason dropdown for PARTNER SOLO(Weight based pricing) re-scheduled delivery when NO Driver accepts and Admin edits only Date when Partner Initiated
    When I navigate to "Partner" portal configured for "FloorDecor service level" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "see 1 pallet and 2 pallets"
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
      |20 boxes           |20X20X20  | 1570 |Handle with care   |Testcustomertywd_appleNewQR Customer  |9999999117     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |FND166 |
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Date" and select future time
    And I click on "Reason" for change time
    And I click on "Partner initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    And I get the latest pickup reference generated for "9999999117"
    And I search the delivery using "Pickup Reference"
    Then the updated time should be displayed on delivery details page


  @ready
  Scenario: Verify Reason dropdown for PARTNER DUO(Geofence based pricing) re-scheduled delivery when Both Driver do not accept and admin edits only time when Partner Initiated
    Given I navigate to "Partner" portal configured for "normal" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Duo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
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
      |Furniture       |Testcustomertywd_appleNewQS Customer   |9999999118     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    And I wait for "5" mins
    Then I should "see Done screen"
    And I wait for "4" mins
    When I navigate to "Admin" portal configured for "QA" URL
    And I wait for "4" mins
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "5" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Partner initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    And I get the latest pickup reference generated for "9999999118"
    And I search the delivery using "Pickup Reference"
    Then the updated time should be displayed on delivery details page

  #CORE:2507-Verify Admin is able to change the status of the trip to Admin cancelled/partner cancelled/ driver cancelled
  @ready
  Scenario Outline: Verify Admin is able to change the status of the trip to Admin cancelled/partner cancelled/ driver cancelled
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661049 | Testcustomertywd_appleMarkAX LutherAX|
    And As a driver "TestDrivertywd_applemd_a_billD Stark_bltTwOD" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      |Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
      | Bungii Completed |
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference"
    Then The "All Deliveries" page should display the delivery in "Payment Successful" form
    And  I search the delivery using "Pickup Reference"
   Then I should see the change status link "Is Displayed"
    And I select "<Status>" from the dropdown
    And I select "<Reason>" as the reason from the reason dropdown

    And I click on "Confirm Status" button
    And I click on "Cancel Status" button
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    #CORE-3764 - QA- Transaction history not displayed for payment successful trips with changed status to Canceled
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I should see "Transaction history" section
    And I should see following details in the Transaction history section
      | Delivery Total   | Customer Refund | Driver Testdrivertywd_appleky_a_eapi Driver One Earnings | Bungii Earnings |
      |  $43.00          | $43.00          | $0.00            | $0.00         |
    And I view All Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference"
    Then Revive button should be displayed beside the trip
    Then The "All Deliveries" should be in "<Trip Status>" state
    And The amount should be "Refunded" and in "voided" state
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
    When I click on "Confirm" button on Revival Popup
    And I get the new pickup reference generated
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The "Scheduled Deliveries" should be in "<After Revive Status>" state
    When I click on the "Edit" button from the dropdown
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    Examples:
      | Status            | Reason                         | Trip Status                           |    After Revive Status                |
      | Admin Canceled    | Solo: Driver not found         |  Admin Canceled - No Driver(s) Found  |      Assigning Driver(s)              |
      | Partner Canceled  |Outside of delivery scope       |  Partner Canceled                     |      Assigning Driver(s)             |
      | Driver Canceled   | Driver initiated               |  Driver Canceled                      |      Assigning Driver(s)              |

  #CORE-2507 :Verify accessorial charges are not refunded on status change
  @regression
  Scenario: Verify accessorial charges are not refunded on status change
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661050 | Testcustomertywd_appleMarkAY LutherAY|
    And As a driver "Testdrivertywd_appledc_a_drvK WashingtonK" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      |Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
      | Bungii Completed |
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    #CORE-4152:Verify that Estimated Delivery time is displayed correctly on all deliveries details page of Admin portal
    When I click on the "Delivery Details" button from the dropdown
    Then The "Scheduled Time" for customer delivery should match
    Then The "Estimated Delivery Time" for customer delivery should match
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The Delivery List page should display the delivery in "Payment Successful" state
    And I search the delivery of Customer and view it
    When I add following accessorial charges and save it
      | Amount   | Fee Type                     | Comment                                    | Driver Cut |
      |  18      | Additional Mileage           | Charges due to Additional Mileage          | 1          |
      |  12      | Additional Weight / Pallet   | Charges due to Additional Weight / Pallet  | 2          |
      |  14      | Cancelation                  | Charges due to Cancelation                 | 3          |
      |  18      | Customer Rejected / Returned | Charges due to Customer Rejected / Returned| 4          |
      |  10      | Excess Wait Time             | Charges due to Excess wait                 | 2          |
      |  30.50   | Limited Access               | Charges due to Limited Access              | 4.5        |
      |  25.65   | Mountainous                  | Charges due to mountainous reason          | 10         |
      |  100     | Other                        | Charges due to other reasons               | 20         |
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should see the change status link "Is Displayed"
    And I select "Customer Canceled" from the dropdown
    And I select "Other" as the reason from the reason dropdown
    Then I should be able to see the comment textbox displayed
    And I enter the text "Vehicle breakdown" in the textarea
    And I click on "Confirm Status" button
    And I click on "Cancel Status" button
    Then The Below accessorial charges should be present in the db
      | Additional Mileage  | Additional Weight / Pallet| Cancelation| Customer Rejected / Returned| Excess Wait Time | Limited Access | Mountainous | Other | Total   |
      | $18                 | $12                       | $14        |  $18                        | $10              | $30.50          | $25.65      | $100  | $228.15 |


#   Core-3390: Verify driver tracking in each statues of ongoing trip
  @regression
  Scenario: Verify driver tracking in each statues of ongoing trip
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661112 | Testcustomertywd_appleMarkDI LutherDI|
    And As a driver "Testdrivertywd_appledc_a_drvX WashingtonX" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted     |
      | Enroute      |
    And I wait for 2 minutes
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    When I view the delivery details
    And I click on "Load" button
    Then I check if "driver location" is updated for live trip
    And As a driver "Testdrivertywd_appledc_a_drvX WashingtonX" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Arrived      |
    And I wait for 2 minutes
    And I refresh the page
    And I click on "Load" button
    Then I check if "driver location" is updated for live trip
    And As a driver "Testdrivertywd_appledc_a_drvX WashingtonX" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Loading Item |
    And I wait for 2 minutes
    And I refresh the page
    And I click on "Load" button
    Then I check if "driver location" is updated for live trip
    And As a driver "Testdrivertywd_appledc_a_drvX WashingtonX" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Driving To Dropoff |
    And I wait for 2 minutes
    And I refresh the page
    And I click on "Load" button
    Then I check if "driver location" is updated for live trip
    And As a driver "Testdrivertywd_appledc_a_drvX WashingtonX" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Unloading Item |
    And I wait for 2 minutes
    And I refresh the page
    And I click on "Load" button
    Then I check if "driver location" is updated for live trip
    And As a driver "Testdrivertywd_appledc_a_drvX WashingtonX" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Bungii Completed |
    And I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    When I click on the "Delivery Details" button from the dropdown
    And I click on "Load" button
    Then I check if "driver location" button is not present

#   Core-3390: Verify both driver's location is tracked in admin portal in case of Duo trip - partner portal
  @regression
  Scenario: Verify both driver's location is tracked in admin portal in case of Duo trip - partner portal
    When I request Partner Portal "Duo" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 8877661113     | Testcustomertywd_appleMarkDJ LutherDJ|
    When As a driver "Testdrivertywd_appleks_a_drvf Kansas_f" and "Testdrivertywd_appleks_a_drvg Kansas_g" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I view the delivery details
    And I click on "Load" button
    Then I check if "driver duo location" button is not present
    When I navigate back to Scheduled Deliveries
    When As a driver "Testdrivertywd_appleks_a_drvf Kansas_f" and "Testdrivertywd_appleks_a_drvg Kansas_g" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Enroute       | Enroute       |
    And I wait for 2 minutes
    And I view the Live Deliveries list on the admin portal
    And I search the delivery of Customer
    And I click on the "Delivery details" link beside scheduled bungii for "Live Duo Deliveries"
    And I click on "Load" button
    Then I check if "driver location-duo" is updated for live trip
    When As a driver "Testdrivertywd_appleks_a_drvf Kansas_f" and "Testdrivertywd_appleks_a_drvg Kansas_g" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Arrived       | Arrived       |
    And I wait for 2 minutes
    And I refresh the page
    And I click on "Load" button
    Then I check if "driver location-duo" is updated for live trip
    When As a driver "Testdrivertywd_appleks_a_drvf Kansas_f" and "Testdrivertywd_appleks_a_drvg Kansas_g" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Loading Item  | Loading Item       |
    And I wait for 2 minutes
    And I refresh the page
    And I click on "Load" button
    Then I check if "driver location-duo" is updated for live trip
    When As a driver "Testdrivertywd_appleks_a_drvf Kansas_f" and "Testdrivertywd_appleks_a_drvg Kansas_g" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Driving To Dropoff  | Driving To Dropoff       |
    And I wait for 2 minutes
    And I refresh the page
    And I click on "Load" button
    Then I check if "driver location-duo" is updated for live trip
    When As a driver "Testdrivertywd_appleks_a_drvf Kansas_f" and "Testdrivertywd_appleks_a_drvg Kansas_g" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Unloading Item| Unloading Item|
    And I wait for 2 minutes
    And I refresh the page
    And I click on "Load" button
    Then I check if "driver location-duo" is updated for live trip


  #Core-4152: To verify updated projected estimated delivery time for trips revived canceled or after payment status change
  @ready
  Scenario: To verify updated projected estimated delivery time for trips revived canceled or after payment status change
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661143 | Testcustomertywd_appleMarkEN LutherEN|
    And As a driver "Testdrivertywd_appledc_a_drvZ WashingtonZ" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      |Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
      | Bungii Completed |
    And I wait for 2 minutes
    #CORE-3822:Delivery Payment and driver earnings not displayed on admin portal for trips marked admin canceled after payment successful status
    And  I view the Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    And I Store the value for driver earnings and delivery payment
    And  I view the Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    And I select "Admin Canceled" from the dropdown
    And I select "Customer initiated - other reason" as the reason from the reason dropdown
    And I click on "Confirm" button
    And I click on "Cancel Status" button
    And I wait for 2 minutes
    And  I view the Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then The driver earnings and the delivery payment should be "$0.00"
    #CORE-4152:Verify that Estimated Delivery time is displayed correctly on all deliveries details page of Admin portal
    And I view the Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then Revive button should be displayed beside the trip
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
    When I click on "Confirm" button on Revival Popup
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    When I click on the "Delivery Details" button from the dropdown
    Then The "Scheduled Time" for customer delivery should match
    Then The "Estimated Delivery Time" for customer delivery should match