@android

  Feature: Partner Portal Cases for mobile friendly portals
  #  Core-4584

    @ready
    Scenario: Verify UI and functionality for weight based partner portal
      When I switch to "ORIGINAL" instance
      And I terminate "customer" app on "same" devices
      When I open new "Chrome" browser for "MOBILE DEVICE"
      And I open "weight based" partner portal
      And I verify the ui links on "get estimate" page for "weight based" partner
      When I enter all details on "get estimate page" for "weight based portal"
        | Pickup_Address                                                                     | Delivery_Address                                                    |
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
      And I click on "Get Estimate" button
      When I enter all details on "delivery details" for "weight based portal"
        |Product_Description|Dimensions|Weight|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Rb_Sb_Number|ScheduledBy|
        |20 boxes           |20X20X20  | 1570 |Handle with care   |Testartner T    |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |UserFND    |
      And I click on "Schedule Bungii" button
      Then I verify the ui links on "success" page for "weight based" partner

    @ready
    Scenario: Verify UI and functionality for geofence based partner portal
      When I switch to "ORIGINAL" instance
      And I terminate "customer" app on "same" devices
      When I open new "Chrome" browser for "MOBILE DEVICE"
      And I open "geofence based" partner portal
      And I verify the ui links on "get estimate" page for "geofence based" partner
      When I enter all details on "get estimate page" for "geofence based"
        | Pickup_Address                                                                     | Delivery_Address                                                    |
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
      And I click on "Get Quote" button
      And I click on "CONTINUE" button
      When I enter all details on "delivery details" for "geofence based portal"
        |Product_Description|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Lot_Number |Delivery_Purpose|Bidder_Number|
        |20 boxes           |Testartner T    |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |002        |Decor           |007          |
      And I enter following Credit Card details on Partner Portal
        |Card_Type   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/25  |VALID POSTAL CODE|VALID CVV|
      And I click on "Disclaimer" button
      Then I click on "Schedule Bungii" button
      Then I verify the ui links on "success" page for "geofence based" partner

    @ready
    #CORE-5039:To verify that driver receives PN for multiple secondary geofence for fixed pricing partner scheduled deliveries
    Scenario: To verify that driver receives PN for multiple secondary geofence for fixed pricing partner scheduled deliveries
      When I switch to "ORIGINAL" instance
      And I terminate "customer" app on "same" devices
      And I open new "Chrome" browser for "MOBILE DEVICE"
      Then I open "geofence based" partner portal
      When I enter all details on "get estimate page" for "geofence based"
        | Pickup_Address                                                      | Delivery_Address                             |
        | 198 Laurel Race Track Road, Laurel, United States, Maryland, 20725  | 1318 Annapolis Road, Odenton, MD 21113, USA  |
      And I click on "Get Quote" button
      And I click on "CONTINUE" button
      Then I enter all details on "delivery details" for "geofence based portal"
        |Product_Description|Customer_Name                        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Lot_Number |Delivery_Purpose|Bidder_Number|
        |20 boxes           |Testcustomertywd_BppleMarkHM LutherHM|8877661220     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |002        |Decor           |007          |
      And I enter following Credit Card details on Partner Portal
        |Card_Type   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD   |12/25  |VALID POSTAL CODE|VALID CVV|
      And I click on "Disclaimer" button
      Then I click on "Schedule Bungii" button
      Then I verify the ui links on "success" page for "geofence based" partner

      When I Switch to "driver" application on "same" devices
      And I am logged in as "Testdrivertywd_appledc_a_drvah Washingtonah" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I tap on "Go Online button" on Driver Home page
      And I wait for "4" mins
      Then I should see a popup "New Bungii Request" displayed
      And I click on "View Request" button

      When I connect to "extra1" using "Driver2" instance
      And I am logged in as "TestDrivertywd_applemd_a_bilL BaltimoreL" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I tap on "Go Online button" on Driver Home page
      And I wait for "4" mins
      Then I should see a popup "New Bungii Request" displayed
      And I click on "View Request" button
      And I wait for 1 minutes
      And I click on "Accept" button
      Then I Select Trip from driver scheduled trip