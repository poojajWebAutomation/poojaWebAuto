@web
Feature: Admin_Price_Override

  @regression
  Scenario: Verify the Price override button is displayed, check save and cancel button functionality and Estimated Customer charge is updated on admin portal
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
    |Furniture       |Handle with care   |Testcustomertywd_appleNewQT Customer |9999999119    |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
    |CardNo   |Expiry |Postal_Code      |Cvv      |
    |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "1" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    When I view the delivery details
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And "Save" and "Cancel" buttons should have background color "blue" and "white" respectively
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    And I click on "Cancel" button on price override pop-up
    And I wait for "2" mins
    Then I check if values of "Customer price" and "Driver cut" remain unchanged
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    And I check the new values of "Customer price" for "Service level"
    Then I check the new values of "Driver Fixed Earnings" for "Service level"
    When I navigate back to Scheduled Deliveries
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for "2" mins
    And I view the Deliveries list on the admin portal
    And I search the delivery of Customer
    Then I check if "Price Override" icon is displayed

  @ready
  Scenario: Verify customer price override menu, driver cut price override menu and admin override multiple times on same delivery
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
      |Furniture       |Handle with care   |Testcustomertywd_appleNewQU Customer |9999999120    |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "1" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    When I view the delivery details
    And I click on "Payment Details" button on delivery details
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I check if the "Customer price" menu is displayed
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I check if the "Driver cut" menu is displayed
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    And I check the new values of "Customer price" for "Service level"
    Then I check the new values of "Driver Fixed Earnings" for "Service level"
    When I get the old values of "Customer price" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    Then I check the new values of "Customer price" for "Service level"

  @regression
  Scenario: Verify change Pickup/Drop off address after override for driver earnings and customer cost before driver accepts, check if price override is reflected on partner portal,indicator is displayed and verify Driver cut is less than Customer price for Admin override
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
      |Furniture       |Handle with care   |Testcustomertywd_appleNewQV Customer |9999999121    |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I am logged in as Admin
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "1" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    When I view the delivery details
    And I check if "Price Override" button is displayed
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I click on "Price Override" button on delivery details
    And I increase the "Driver cut" more than "Customer price"
    And I select Reason as "Driver Incentive"
    And I click on "Save" button on price override pop-up
    And "Delivery cost cannot be less than or equal to driver earnings." error message should be displayed
    Then I click on "Cancel" button on price override pop-up
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    And I check the new values of "Customer price" for "Service level"
    Then I check the new values of "Driver Fixed Earnings" for "Service level"
    And I navigate back to Scheduled Deliveries
    Then I check if "Price Override" icon is displayed
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I navigate to "Partner" portal configured for "service level" URL
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
    And I check if the delivery cost is updated on partner portal
    Then I close the Trip Delivery Details page

  @regression
  Scenario: Verify Admin override is not present for Schedule Customer delivery and Live delivery
    When I am logged in as Admin
    And I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999122     | Testcustomertywd_appleNewQW Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    And I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    When I view the delivery details
    And I check if "Price override" button is not present
    Then I navigate back to Scheduled Deliveries
    And As a driver "Testdrivertywd_appledc_a_drvq WashingtonDC_q" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
    And I view the Live Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | Loading Items |
    When I view the delivery details
    Then I check if "Price override" button is not present
    When I navigate back to Live Deliveries
    And I click on "Edit" link beside live delivery
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed

  @regression
  Scenario: Verify the estimated charge and driver earnings before and after driver accepts and when service level is updated over a admin override functionality
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
        |Furniture       |Handle with care   |Testcustomertywd_appleNewQX Customer |9999999123   |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I am logged in as Admin
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s) |
    When I view the delivery details
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    And I check the new values of "Customer price" for "Service level"
    Then I check the new values of "Driver Fixed Earnings" for "Service level"
    When I navigate back to Scheduled Deliveries
    And As a driver "Testdrivertywd_appledc_a_drvr WashingtonDC_r" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Accepted      |
    And I wait for "2" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        | Scheduled |
    When I view the delivery details
#    Core 3294: Verify stop search button is not displayed for trip in advance schedule status and schedule status
    Then I check if "Stop Searching" button is not present
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    And I check the new values of "Customer price" for "Service level"
    Then I check the new values of "Driver Fixed Earnings" for "Service level"
    When I navigate back to Scheduled Deliveries
    And I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I change the service level to "Threshold" in "Admin" portal
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I view the delivery details
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I wait for "2" mins
    Then I check the new values of "Estimated Charge" and "Driver Fixed Earnings" for changed "Service level"

  @regression
  Scenario: Verify Price override for driver earnings only in Duo fixed pricing portals and when different driver share is entered ,minimum of both share is displayed in admin portal and check if indicator is displayed
    When I navigate to "Partner" portal configured for "service level" URL
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I request "Duo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
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
        |Furniture       |Handle with care   |Testcustomertywd_appleNewRA Customer |9999999126  |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And As a driver "Testdrivertywd_appledc_a_drvs WashingtonDC_s" perform below action with respective "Duo Scheduled" Delivery
         | driver1 state |
         | Accepted      |
    And I wait for "2" mins
    When I am logged in as Admin
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        | Assigning Driver(s) |
    When I view the delivery details
    And I get the old values of "Driver cut" for "Service level-duo"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Driver cut" for "Service level-duo"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    Then I check the new values of "Driver Fixed Earnings" for "Service level-duo"
    When I navigate back to Scheduled Deliveries
#    Core-4307: Verify the history displays the Price override performed for Driver Earnings only
    And I click on the dropdown beside scheduled bungii
    When I click the "Notes & History" link
    And I click on "History"
    Then I should be able to see "price-override driver earnings only"
    And I close the Note
    Then I check if "Price Override" icon is displayed
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I change delivery type from "Duo to Solo"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed

  @regression
  Scenario: Verify fnd deliveries and driver app for change Service Level after override for driver earnings and customer cost before driver accepts and check if indicator is displayed
    When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661056 | Testcustomertywd_BppleMarkBE LutherBE|
    When I am logged in as Admin
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        | Assigning Driver(s)|
    When I view the delivery details
    And I get the old values of "Customer price" for "Service level - fnd"
    And I get the old values of "Driver cut" for "Service level - fnd"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    And I check the new values of "Customer price" for "Service level - fnd"
    Then I check the new values of "Driver Fixed Earnings" for "Service level - fnd"
    When I navigate back to Scheduled Deliveries
    And I check if "Price Override" icon is displayed
    And I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I change the service level to "Customer Return - First Threshold" in "Admin" portal
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    When I view the delivery details
    And I get the old values of "Customer price" for "Service level - fnd"
    And I get the old values of "Driver cut" for "Service level - fnd"
    And I wait for "2" mins
    Then I check the new values of "Estimated Charge" and "Driver Fixed Earnings" for changed "Service level - fnd"

  @ready
  Scenario: Verify fnd deliveries and driver app for cancel and revive trip after Price Override is performed
    When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661056 | Testcustomertywd_BppleMarkBE LutherBE|
    When I am logged in as Admin
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        |Assigning Driver(s)|
    When I view the delivery details
    And I get the old values of "Customer price" for "Service level - fnd"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    Then I check the new values of "Estimated Charge" for "Service level - fnd"
    When I navigate back to Scheduled Deliveries
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for "2" mins
    And I view the Deliveries list on the admin portal
    And I search the delivery of Customer
    Then Revive button should be displayed beside the trip
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId, Pickup Origin and Partner Name
    When I click on "Confirm" button on Revival Popup
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery of Customer
    Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        |Assigning Driver(s)|

  @ready
  Scenario: Verify that indicator is displayed for price override delivery on live deliveries page and after the trip is revived with accessorial charges
    When I navigate to "Partner" portal configured for "service level" URL
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                     | Delivery_Address                                                   |
      | 3410 Beech Street Northwest Washington District of Columbia 20015  | 	3315 Shepherd Street Chevy Chase Maryland 20815  |
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
      |Furniture       |Handle with care   |Testcustomertywd_appleNewRC Customer |9999999128   |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I am logged in as Admin
    And I view the partner portal Scheduled Trips list on the admin portal
    And As a driver "Testdrivertywd_appledc_a_drvC WashingtonC" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I wait for "2" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Scheduled |
    When I view the delivery details
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    And I check the new values of "Estimated Charge" for "Service level"
    Then I check the new values of "Driver Fixed Earnings" for "Service level"
    When I navigate back to Scheduled Deliveries
    And As a driver "Testdrivertywd_appledc_a_drvC WashingtonC" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Enroute  |
    And I view the Live Deliveries list on the admin portal
    And I wait for "2" mins
    And I should be able to see the respective bungii with the below status
      |  Status       |
      |Trip Started   |
    Then I check if "Price Override" icon is displayed
    When I cancel bungii as a driver "Testdrivertywd_appledc_a_drvC WashingtonC"
    And I wait for 2 minutes
    And I view the Deliveries list on the admin portal
    And I search the delivery of Customer and view it
    Then I should see "Accessorial Charges" section displayed
    When I add following accessorial charges and save it
      | Amount   | Fee Type         | Comment                           | Driver Cut |
      |  10      | Excess Wait Time | Charges due to Excess wait        | 2          |
      |   20.5    | Cancelation      | Charges due to Cancelation        | 4.5        |
      |  25.65   | Mountainous      | Charges due to mountainous reason | 10.0       |
      |  100     | Other            | Charges due to other reasons      | 20         |
    And I navigate back to Scheduled Deliveries
    Then I check if "Price Override" icon is displayed

#    Core-2960 Verify customer price override and driver earnings override is retained for a weight based delivery converted from DUO to SOLO when driver not accepted the delivery
  @regression
    Scenario: Verify customer price override and driver earnings override is retained for a weight based delivery converted from DUO to SOLO when driver not accepted the delivery
      When I request Partner Portal "DUO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661056 | Testcustomertywd_BppleMarkBE LutherBE|
      When I am logged in as Admin
      And I view the partner portal Scheduled Trips list on the admin portal
      And I wait for "2" mins
      Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        |Assigning Driver(s)|
      When I view the delivery details
      And I get the old values of "Customer price" for "Service level - fnd"
      And I get the old values of "Driver cut" for "Service level - fnd-duo"
      And I click on "Price Override" button on delivery details
      And I change the "Customer price"
      And I select Reason as "Custom Quote"
      And I change the "Driver cut-duo"
      And I select Reason as "Driver Incentive"
      Then I click on "Save" button on price override pop-up
      And I click on "Ok" button on price override pop-up
      And I wait for "2" mins
      Then I check the new values of "Customer price" for "Service level - fnd"
      When I navigate back to Scheduled Deliveries
      And I click on "Edit" link beside scheduled bungii
      When I click on "Edit Trip Details" radiobutton
      And I change delivery type from "Duo to Solo"
      And I click on "Verify" button on Edit Scheduled bungii popup
      And I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      And I refresh the page
      And I get the new pickup reference generated
      And I view the all Scheduled Deliveries list on the admin portal
      And I wait for 2 minutes
      And I open the trip for "Testcustomertywd_BppleMarkBE LutherBE" the customer for delivery details
      When I view the delivery details
      Then I check "Customer price" is retained after "duo to solo" conversion
      Then I check "Driver Earning" is retained after "duo to solo" conversion

  @regression
  Scenario: Verify the schedule delivery can be edited DUO to SOLO Bungii without price override for weight based pricing
      When I request Partner Portal "DUO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 8877661057 | Testcustomertywd_BppleMarkBF LutherBF|
      When I am logged in as Admin
      And I view the partner portal Scheduled Trips list on the admin portal
      And I wait for "2" mins
      Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        |Assigning Driver(s)|
 #    Core-2960 Verify the schedule delivery can be edited DUO to SOLO Bungii without price override for weight based pricing
      And I click on "Edit" link beside scheduled bungii
      When I click on "Edit Trip Details" radiobutton
      And I change delivery type from "Duo to Solo"
      And I click on "Verify" button on Edit Scheduled bungii popup
      And I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      And I refresh the page
      And I get the new pickup reference generated
      And I view the all Scheduled Deliveries list on the admin portal
      And I wait for 2 minutes
      And I search the delivery of Customer
      Then I should be able to see the respective bungii with the below Delivery Type
        | Type |
        | Solo |
#     Core-2960 Verify for weight based matrix delivery SOLO to DUO option is disabled when driver not accepted
      And I click on "Edit" link beside scheduled bungii
      When I click on "Edit Trip Details" radiobutton
      Then I check if DUO option is disabled
#     Core-2960 Verify for weight based matrix delivery SOLO to DUO option is disabled when admin assign driver
      And I click on "Add Driver" and add "Testdrivertywd_appledc_a_drvl WashingtonDC_l" driver
      And I click on "Verify" button on Edit Scheduled bungii popup
      And I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      And I click on "Edit" link beside scheduled bungii
      When I click on "Edit Trip Details" radiobutton
      Then I check if DUO option is disabled

#    Core-2960 Verify customer price override and driver earnings override is retained for a fixed pricing delivery converted from DUO to SOLO when driver accepted the delivery
    @ready
      Scenario: Verify customer price override and driver earnings override is retained for a fixed pricing delivery converted from DUO to SOLO when driver accepted the delivery
      When I request Partner Portal "DUO" Trip for "Biglots" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |atlanta  | NEXT_POSSIBLE_THIRD_SLOT | 8877661058 | Testcustomertywd_BppleMarkBG LutherBG|
      And As a driver "Testdrivertywd_applega_a_steveE Stark_altOnEE" and "Testdrivertywd_applega_a_steveF Stark_altOnEF" perform below action with respective "DUO SCHEDULED" trip
        | driver1 state | driver2 state |
        | Accepted      | Accepted      |
      When I am logged in as Admin
      And I view the partner portal Scheduled Trips list on the admin portal
      And I wait for "2" mins
      Then I should be able to see the respective bungii partner portal trip with the below status
        | Status           |
        | Scheduled        |
      When I view the delivery details
      And I get the old values of "Customer price" for "Service level"
      And I get the old values of "Driver cut" for "Service level"
      And I click on "Price Override" button on delivery details
      And I change the "Customer price"
      And I select Reason as "Custom Quote"
      And I change the "Driver cut-duo"
      And I select Reason as "Driver Incentive"
      Then I click on "Save" button on price override pop-up
      And I click on "Ok" button on price override pop-up
      And I wait for "2" mins
      Then I check the new values of "Customer price" for "Service level - fnd"
      When I navigate back to Scheduled Deliveries
      And I click on "Edit" link beside scheduled bungii
      When I click on "Edit Trip Details" radiobutton
      And I change delivery type from "Duo to Solo"
      When I remove control driver "Testdrivertywd_applega_a_steveE Stark_altOnEE" on edit popup
      And I click on "Verify" button on Edit Scheduled bungii popup
      And I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      And I wait for 2 minutes
      And I refresh the page
      And I get the new pickup reference generated
      And I view the all Scheduled Deliveries list on the admin portal
      And I search the delivery of Customer "Testcustomertywd_BppleMarkBG LutherBG"
      When I click on the "Delivery Details" button from the dropdown
      Then I check "Customer price" is retained after "duo to solo" conversion
      Then I check "Driver Earning" is retained after "duo to solo" conversion

#    Core-2960 Verify customer price override and driver earnings are retained for a fixed pricing delivery converted from SOLO to DUO when driver1 accepted and driver2 admin assign
  @ready
  Scenario: Verify customer price override and driver earnings are retained for a fixed pricing delivery converted from SOLO to DUO when driver1 accepted and driver2 admin assign
    When I request Partner Portal "SOLO" Trip for "Biglots" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |atlanta  | NEXT_POSSIBLE_THIRD_SLOT | 8877661059 | Testcustomertywd_BppleMarkBH LutherBH|
    And As a driver "Testdrivertywd_applega_a_steveD Stark_altOnED" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    When I am logged in as Admin
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Scheduled        |
    When I view the delivery details
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And I wait for "2" mins
    And I check the new values of "Customer price" for "Service level"
    Then I check the new values of "Driver Fixed Earnings" for "Service level"
    When I navigate back to Scheduled Deliveries
    And I click on "Edit" link beside scheduled bungii
    When I click on "Edit Trip Details" radiobutton
    And I change delivery type from "Solo to Duo"
    And I assign driver "Testdrivertywd_applega_c_mark Stark_altThree" for the trip
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for 2 minutes
    And I refresh the page
    And I search the delivery of Customer "Testcustomertywd_BppleMarkBH LutherBH"
    When I click on the "Delivery Details" button from the dropdown
    Then I check "Customer price" is retained after "solo to duo" conversion
    Then I check "Driver Earning" is retained after "solo to duo" conversion
#    Core-2960 Verify price override validation when customer cost is less than or equal to driver earnings in SOLO to DUO
    And I click on "Price Override" button on delivery details
    And I change the "Customer price - equal to driver earnings"
    And I select Reason as "Custom Quote"
    And I click on "Save" button on price override pop-up
    Then I check if error message is displayed

  @ready
  Scenario: Verify Overriden prices are retained after admin edits Live trip
    When I request Partner Portal "SOLO" Trip for "Biglots" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |atlanta  | NEXT_POSSIBLE_THIRD_SLOT | 8877661059 | Testcustomertywd_BppleMarkBH LutherBH|
    When I am logged in as Admin
    And I view the partner portal Scheduled Trips list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      |Assigning Driver(s)|
    When I view the delivery details
    And I get the old values of "Customer price" for "Service level"
    And I get the old values of "Driver cut" for "Service level"
    And I check if "Price Override" button is displayed
    And I click on "Price Override" button on delivery details
    And I change the "Customer price"
    And I select Reason as "Custom Quote"
    And I change the "Driver cut"
    And I select Reason as "Driver Incentive"
    Then I click on "Save" button on price override pop-up
    And I click on "Ok" button on price override pop-up
    And As a driver "Testdrivertywd_applega_a_steveD Stark_altOnED" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
    And I wait for 2 minutes
    And I view the Live Deliveries list on the admin portal
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "349 Ferst Dr NW"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And As a driver "Testdrivertywd_applega_a_steveD Stark_altOnED" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Driving To Dropoff |
      | Unloading Item |
      | Bungii Completed |
    And I wait for "2" mins
    And I view the Deliveries list on the admin portal
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I verify overriden prices are retained