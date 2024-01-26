@web
Feature: Partner Integration with Admin and Driver
  
  Background:
    Given I navigate to "Partner" portal configured for "normal" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"

  @regression
  @sanity
    #stable
  Scenario: Delivery List Status Updation For Solo Scheduled Pickup on Partner Portal
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, District of Columbia, United States, 20005  | 234 13th Street Northeast, Washington, District of Columbia, United States, 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner A      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Admin" portal configured for "QA" URL
    And I wait for "2" mins
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    And I should see the delivery highlighted in "Blue"
    When I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    And The delivery should not be highlighted in "Blue" for "Scheduled Deliveries"
    And I should see the delivery highlighted in "Grey"
    When As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted |
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I should see the delivery highlighted in "Grey"
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    And I should see the delivery highlighted in "Grey"

    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Enroute |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Trip Started |
    And I should see the delivery highlighted in "Grey"

    And The delivery should not be highlighted in "Blue" for "Live Deliveries"
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    When I navigate to "Delivery Status URL"
   #CORE-2266:To verify the driver phone is masked in the tracking sms of in progress bungii for geofence based partner portal delivery (SOLO)
    Then The "Phone Icon" "Image" should be displayed
    And I click on "Phone" button
    Then The "Call Alert Message" "Text" should be displayed
    Then The "Confirm" "Button" should be displayed
    Then The "Cancel" "Button" should be displayed
    And I click on "Cancel Call" button
    Then Delivery Status should be displayed correctly as "En Route To Pickup"
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Arrived |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Driver(s) Arrived |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    When I navigate to "Delivery Status URL again"
    Then Delivery Status should be displayed correctly as "Driver Arrived At Pickup"
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Loading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      |  Status |
      | Loading Items |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    When I navigate to "Delivery Status URL again"
    Then Delivery Status should be displayed correctly as "Loading Items"
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Driving To Dropoff |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Driving To Dropoff |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    When I navigate to "Delivery Status URL again"
    Then Delivery Status should be displayed correctly as "Driving To Drop Off"
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Unloading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Unloading Items |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    When I navigate to "Delivery Status URL again"
    Then Delivery Status should be displayed correctly as "Unloading Items"
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Bungii Completed |
    And I wait for "2" mins
    And I view the Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    And The delivery should not be highlighted in "Blue" for "All Deliveries"
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Completed    |
    When I navigate to "Delivery Status URL again"
    Then Delivery Status should be displayed correctly as "Successfully Completed"
    #CORE-2266:To verify the driver cannot be called for completed delivery
    Then The Phone Icon should not be displayed

  @regression
    #stable
  Scenario: Verify Cancelling Partner Portal Solo Scheduled trip from Admin Portal
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
      |Furniture       |Testpartner B      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    #And I view the Scheduled Trips list on the admin portal
    And I view the Deliveries list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Canceled       |

  @regression
    #Stable
  Scenario: Verify Cancelling Partner Portal Solo Scheduled trip from Driver
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
      |Furniture       |Testpartner C      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status             |
      | Assigning Driver(s)|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted |
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Enroute |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state   |
      | Driver Canceled |
    And I view the Deliveries list on the admin portal
    And I wait for 2 minutes
    And I search the delivery of Customer
    Then Revive button should be displayed beside the trip
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
    And I should see "Partner" details on review popup
    And I should not see "Pickup Origin" on review popup
    And "Confirm" and "Cancel" buttons should have background color "blue" and "white" respectively

    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status    |
      | Canceled       |
   #CORE-3372: To verify delivery status is updated when Partner Portal delivery is Driver canceled
    And I select "Check / uncheck all" option from the filter
    And I click on "Apply" button
    And I select "Check / uncheck all" option from the filter
    And I click on "Apply" button
    And I select "Canceled" option from the filter
    And I click on "Apply" button
    And I click on the delivery based on customer name
    And I get time stamp for "Driver Cancelled" delivery step
    Then I should see the delivery status highlighted and to be set as "Canceled" on partner portal delivery details page

  @regression
    #stable
  Scenario: Verify Cancelling Partner Portal Duo Scheduled trip by control Driver
    When I request "Duo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, District of Columbia, United States, 20005  | 234 13th Street Northeast, Washington, District of Columbia, United States, 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner D     |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    #When I navigate to "Bungii Admin Portal in new tab" URL
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status             |
      | Assigning Driver(s)|
    When As a driver "Testdrivertywd_appledc_a_ronny James" and "Testdrivertywd_appledc_a_mate Gate" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    When As a driver "Testdrivertywd_appledc_a_ronny James" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state |
      | Enroute       |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    When I navigate to "Delivery Status URL"
    Then Delivery Status should be displayed correctly as "En Route To Pickup"
    When As a driver "Testdrivertywd_appledc_a_ronny James" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state   |
      | Driver Canceled |
    And I view the Deliveries list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Canceled       |
    When I navigate to "Delivery Status URL"
    Then Delivery Status should be displayed correctly as "Delivery Cancelled"
   #CORE-2266:To verify the driver cannot be called for canceled delivery
    Then The Phone Icon should not be displayed

  @regression
  Scenario: Verify Cancelling Partner Portal Duo Scheduled trip by Non control Driver
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
      |Furniture       |Testpartner E      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    #When I navigate to "Bungii Admin Portal in new tab" URL
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    When As a driver "Testdrivertywd_appledc_a_ronny James" and "Testdrivertywd_appledc_a_mate Gate" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    When As a driver "Testdrivertywd_appledc_a_ronny James" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state |
      | Enroute       |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    When As a driver "Testdrivertywd_appledc_a_mate Gate" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state   |
      | Driver Canceled |
    And I view the Deliveries list on the admin portal
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Canceled       |

  
  @ready
    #Failed in Sprint 49
  Scenario: Verify Solo Scheduled trip cannot cancel in Partner portal once the Trip started
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
      |Furniture       |Testpartner G      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    #When I navigate to "Bungii Admin Portal in new tab" URL
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status             |
      | Assigning Driver(s)|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted |
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Scheduled      |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Enroute |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status       |
      | Trip Started |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state   |
      | Bungii Canceled |
    And I view the Live Deliveries list on the admin portal
    And I navigate to partner portal
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the trip details"
    And I click "Cancel Delivery link" button on Partner Portal
    Then I should "see the cancel delivery warning message"
    And I click "Cancel Delivery" button on Partner Portal
    Then I should "see Delivery cancellation failed message"
    And I click "OK on Delivery Cancellation Failed" button on Partner Portal
    And I close the Trip Delivery Details page
    And I should logout from Partner Portal
  
  @ready
  Scenario: Verify Driver Est. Earning for Fixed Pricig Partner Portal Trip
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                  |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | Legacy granite, 7730 Oak St, Falls Church, VA 22043, United States|30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name                             |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testcustomertywd Apple New QE Customer      |9999999105     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    #When I navigate to "Bungii Admin Portal in new tab" URL
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    And As a driver "Testdrivertywd_appledc_a_drvj WashingtonDC_j" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted |
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Scheduled |
    And I select the partner portal scheduled trip on scheduled delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_drvj WashingtonDC_j" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Enroute |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Trip Started |
    And I select the scheduled trip on live delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_drvj WashingtonDC_j" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Arrived |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Driver(s) Arrived |
    And I select the scheduled trip on live delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_drvj WashingtonDC_j" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Loading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      |  Status |
      | Loading Items |
    And I select the scheduled trip on live delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_drvj WashingtonDC_j" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Driving To Dropoff |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Driving To Dropoff |
    And I select the scheduled trip on live delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_drvj WashingtonDC_j" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Unloading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status |
      | Unloading Items |
    And I select the scheduled trip on live delivery
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_drvj WashingtonDC_j" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Bungii Completed |
    Then I wait for "2" mins
    And I view All Deliveries list on the admin portal
    Then I should be able to see the respective partner portal trip with "Payment Successful" state
    #Then The Delivery List page should display the delivery in "Payment Successful" state
    And I select the scheduled trip on All Deliveries
    Then I view the correct Driver Earnings for geofence based pricing model

    @ready
  Scenario: Verify Solo Scheduled delivery can cancel in Partner portal after accepting it by driver
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
        |Furniture       |Testpartner G      |9998881111     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I click "Track Deliveries" button on Partner Portal
      Then I should "see the trip in the Delivery List"
    #When I navigate to "Bungii Admin Portal in new tab" URL
      When I navigate to "Admin" portal configured for "QA" URL
      And I view the Scheduled Deliveries list on the admin portal
      Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        | Assigning Driver(s)|
      And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
        | driver1 state|
        | Accepted |
      And I view the partner portal Scheduled Trips list on the admin portal
      Then I should be able to see the respective bungii partner portal trip with the below status
        | Status    |
        | Scheduled |
      And I navigate to partner portal and view the Trip status with below status
        | Partner_Status |
        | Scheduled      |
      And I navigate to partner portal
      And I select the Scheduled Bungii from Delivery List
      Then I should "see the trip details"
      And I click "Cancel Delivery link" button on Partner Portal
      Then I should "see the cancel delivery warning message"
      And I click "Cancel Delivery" button on Partner Portal
      Then I should "Your delivery has been canceled message"
      And I click "OK" button on Partner Portal
      Then Admin should receive the "Partner Delivery Canceled!" email
      #And I close the Trip Delivery Details page
      #And I should logout from Partner Portal

  @ready
  Scenario: Verify Solo Scheduled delivery can cancel in Partner portal before accepting it by driver
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
      |Furniture       |Testpartner G      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    And I navigate to partner portal
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the trip details"
    And I click "Cancel Delivery link" button on Partner Portal
    Then I should "see the cancel delivery warning message"
    And I click "Cancel Delivery" button on Partner Portal
    Then I should "Your delivery has been canceled message"
    And I click "OK" button on Partner Portal
    Then Admin should receive the "Partner Delivery Canceled!" email
      #And I close the Trip Delivery Details page
      #And I should logout from Partner Portal

  @ready
  Scenario: Verify that the portal's customer can open the link to provide driver rating for solo delivery.
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 9999999228 | Testcustomertywd_appleNewMO Customer|
    And As a driver "Testdrivertywd_appleks_a_drve Kansas_e" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state |
      | Accepted      |
      | enroute       |
      |Arrived         |
      |Loading Item     |
      |Driving To Dropoff |
      |Unloading Item    |
      |Bungii Completed  |
    And I open the link to provide driver rating
    Then I check details on link page open for driver rating
    Then I change the rating to "1" stars for "Driver1"
    Then I change the rating to "2" stars for "Driver1"
    Then I change the rating to "3" stars for "Driver1"
    Then I change the rating to "4" stars for "Driver1"
    And I click on "Submit" button on Driver Rating Page
    Then I should "see Ratings submitted successfully message"
    Then Submitted driver ratings are saved in the database

    @ready
#   CORE-2505 : Driver rating fix
    Scenario: Verify that the rating is stored in database when driver clicks submit button without clicking on rating
      When I request Partner Portal "SOLO" Trip for "MRFM" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |Kansas| NEXT_POSSIBLE | 9999999228 | Testcustomertywd_appleNewMO Customer|
      And As a driver "Testdrivertywd_appleks_a_drve Kansas_e" perform below action with respective "Solo Scheduled" partner portal trip
        | driver1 state |
        | Accepted      |
        | enroute       |
        |Arrived         |
        |Loading Item     |
        |Driving To Dropoff |
        |Unloading Item    |
        |Bungii Completed  |
      And I open the link to provide driver rating
      Then I check details on link page open for driver rating
      And I click on "Submit" button on Driver Rating Page
      Then I should "see Ratings submitted successfully message"
      Then Default driver ratings are saved in the database


  @regression
  Scenario: Verify that the portal's customer can open the link to provide driver rating for duo delivery.
    When I request Partner Portal "Duo" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 9999999229     | Testcustomertywd_appleNewMP Customer|
    When As a driver "Testdrivertywd_appleks_a_drvf Kansas_f" and "Testdrivertywd_appleks_a_drvg Kansas_g" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
      | Enroute       | Enroute       |
      |Arrived         |Arrived         |
      |Loading Item     |Loading Item     |
      |Driving To Dropoff |Driving To Dropoff |
      |Unloading Item    |Unloading Item    |
      |Bungii Completed  |Bungii Completed  |
    And I open the link to provide driver rating
    Then I check details on link page open for driver rating
    Then I change the rating to "3" stars for "Driver1"
    Then I change the rating to "4" stars for "Driver2"
    And I click on "Submit" button on Driver Rating Page
    Then I should "see Ratings submitted successfully message"
    Then Submitted driver ratings are saved in the database

    @ready
  Scenario: Verify that the portal's customer can provide driver rating for delivery only once.
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 9999999228     | Testcustomertywd_appleNewMO Customer|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state |
      | Accepted      |
      | enroute       |
      |Arrived         |
      |Loading Item     |
      |Driving To Dropoff |
      |Unloading item    |
      |Bungii Completed  |
    And I open the link to provide driver rating
    Then I check details on link page open for driver rating
    Then I change the rating to "3" stars for "Driver1"
    And I click on "Submit" button on Driver Rating Page
    Then I should "see Ratings submitted successfully message"
    And I open the link to provide driver rating
    Then I check that rating stars are not shown on driver rating page once the ratings are submitted for the delivery

  @ready
    #CORE-3257 - Manually end bungii functionality is removed
    #Failed in Sprint 49
  Scenario: Verify that Admin is not allow to Cancelling Partner Portal Solo Scheduled trip as manually end link is removed
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
      |Furniture       |Testpartner F      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |MASTER CARD2|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    #When I navigate to "Bungii Admin Portal in new tab" URL
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted     |
      | Enroute      |
      | Arrived      |
      | Loading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      |  Status |
      | Loading Items |
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | In-Progress    |
    And I view the Live Deliveries list on the admin portal
    ##And I click on Partner Portal Bungii delivery
    And I open the live delivery details in admin portal
    And Manually end bungii link is removed for live trips
    And I navigate to partner portal
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the trip details"
    And I click "Cancel Delivery link" button on Partner Portal
    Then I should "see the cancel delivery warning message"
    And I click "Cancel Delivery" button on Partner Portal
    Then I should "see Delivery cancellation failed message"
    And I click "OK on Delivery Cancellation Failed" button on Partner Portal
    And I close the Trip Delivery Details page
    And I should logout from Partner Portal

    #And I click on "Manually End Bungii" link
    #And Enter the End Date and Time
    #And Click on "Calculate Cost" button
    #Then the amount is calculated and shown to admin
    #And Click on "Confirm" button
    #And I view the Deliveries list on the admin portal
    ##Then The Delivery List page should display the delivery in "Payment Successful" state
    #Then I should be able to see the respective partner portal trip with "Payment Successful" state
    #And I navigate to partner portal and view the Trip status with below status
    #  | Partner_Status |
    #  | Completed      |

#  Core-3842 Verify that Estimated Delivery time is displayed correctly on delivery details of geofence based Partner portal
  @ready
  Scenario:Verify that Estimated Delivery time is displayed correctly on delivery details of geofence based Partner portal
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 8877661048 | Testcustomertywd_appleMarkAW LutherAW|
    And I calculate the estimated delivery time for "geofence based portal"
    And I wait for "2" mins

    When I navigate to "Admin" portal configured for "QA" URL
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |

    When I view the delivery details
    Then I check if correct "estimated time geofence based Partner portal" is displayed

#   Core-3842 Verify that Estimated delivery time is calculated correctly when admin edits scheduled delivery address of partner trip
    When I navigate back to Scheduled Deliveries
    And I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    And I change the drop off address to "4800 East 63rd Street, Kansas City"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I get the new pickup reference generated
    And I wait for "2" mins
    When I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I view the delivery details
    And I calculate the estimated delivery time for "edited address for geofence based portal"
#   Core-3391 Verify that Estimated time on admin portal details delivery page gets updated when delivery address is changed
    Then I check if correct "estimated time geofence based Partner portal" is displayed

 #CORE-3381 -To verify that revive button works fine for partner portal/customers having special characters in name field
  @ready
  Scenario: To verify that revive button works fine for partner portal/customers having special characters in name field
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
      |Furniture       |Testpartner"$%2@~%  |1122334455     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I navigate to "Admin" portal configured for "QA" URL
    And I wait for 2 minutes
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status             |
      | Assigning Driver(s)|
    When I click on the "Edit" button from the dropdown
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should see the cancelled trip icon displayed for the delivery
    Then Revive button should be displayed beside the trip
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
    When I click on "Confirm" button on Revival Popup
    And I wait for 2 minutes
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status             |
      | Assigning Driver(s)|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state|
      | Accepted     |
      | Enroute      |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
      | Bungii Completed |
    And I wait for 2 minutes
    And I navigate to partner portal and view the Trip status with below status
      | Partner_Status |
      | Completed      |

  @ready
  Scenario: Verify background color turns red when solo delivery remains in Assigning Drivers state with loading icon and scheduled time have passed
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
      |Furniture       |Testpartner B      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
      And I wait for "15" mins
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    And I should see the delivery highlighted in "Red"

  @ready
  Scenario: Verify background color turns red when dup delivery remains in Assigning Drivers state with loading icon and scheduled time have passed
    When I request Partner Portal "Duo" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 8877661068     | Testcustomertywd_BppleMarkBQ LutherBQ|
    And As a driver "Testdrivertywd_appleks_a_drvba Kansas_ba" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state |
      | Accepted      |
    And I wait for "15" mins
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status    |
      | Assigning Driver(s) |
    And I should see the delivery highlighted in "Red"

  @ready
  Scenario: Verify background color turns red when solo delivery remains in Assigning Drivers state with loading icon and scheduled time have passed
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
      |Furniture       |Testpartner B      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
      And I wait for "15" mins
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    And I should see the delivery highlighted in "Red"
    And I wait for "15" mins
    Then I should see "Time out" tooltip beside the bungii


  @ready
  Scenario: Verify background color turns red when dup delivery remains in Assigning Drivers state with loading icon and scheduled time have passed
    When I request Partner Portal "Duo" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 8877661068     | Testcustomertywd_BppleMarkBQ LutherBQ|
    And As a driver "Testdrivertywd_appleks_a_drvba Kansas_ba" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state |
      | Accepted      |
    And I wait for "15" mins
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status    |
      | Assigning Driver(s) |
    And I should see the delivery highlighted in "Red"

    @test30
      #this is to test SQS /SNS changes
    Scenario Outline: To create multiple partner portal trips
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | <Customer_Phone> | <Customer_Name>|

      Examples:
      |Customer_Phone|Customer_Name                         |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |
      |8877661048    |Testcustomertywd_appleMarkAW LutherAW |


  #CORE-4520
  @ready
  Scenario: Verify that admin received outside secondary polyline email for partner delivery
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "newjersey" geofence
      | Pickup_Address                                                | Delivery_Address                                              |Load_Unload_Time|
      | 1963 Oak Tree Road, Edison, New Jersey, United States, 08820  | 508 Hamilton Avenue, Trenton, New Jersey, United States, 08609|30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner B      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD4|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    Then Admin should receive the "Partner Delivery scheduled beyond secondary polyline" email
    And I wait for "2" mins
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    When I view All Deliveries list on the admin portal
    Then The Delivery List page should display the delivery in "Admin Canceled" state