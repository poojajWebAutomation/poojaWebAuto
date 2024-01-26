@ios

  Feature: Partner Portal Cases for mobile friendly portals
  #  Core-4584

    @ready
    Scenario: Verify UI and functionality for fixed pricing partner portal
      When I switch to "ORIGINAL" instance
      And I terminate "customer" app on "same" devices
      When I open new "Safari" browser for "MOBILE DEVICE"

      And I open "fixed pricing" partner portal
      And I verify the ui links on "get estimate" page for "fixed pricing" partner
      When I enter all details on "get estimate page" for "fixed pricing"
        | Pickup_Address                                                                     | Delivery_Address                                                    |
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
      And I select "Threshold" service level
      And I click on "Continue" button
      When I enter all details on "delivery details" for "fixed pricing portal"
        |Product_Description|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Reciept|
        |20 boxes           |Handle with care   |Biglots         |2442442440     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |1212   |
      And I enter following Credit Card details on Partner Portal
        |Card_Type   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/25  |VALID POSTAL CODE|VALID CVV|
      And I click on "Schedule Bungii" button
      Then I verify the ui links on "success" page for "fixed pricing" partner

    @ready
    Scenario: Verify login and logout screens of partner portal
      When I switch to "ORIGINAL" instance
      And I terminate "customer" app on "same" devices
      When I open new "Safari" browser for "MOBILE DEVICE"
      And I open "fixed pricing" partner portal
      And I click on "log-out" icon
      Then I should be navigated to "login page"

    @ready
    Scenario: Verify UI and functionality for Kiosk mode partner portal
      When I switch to "ORIGINAL" instance
      And I terminate "customer" app on "same" devices
      When I open new "Safari" browser for "MOBILE DEVICE"

      And I open "kiosk mode" partner portal
      And I verify the ui links on "get estimate" page for "kiosk mode" partner
      When I enter all details on "get estimate page" for "kiosk mode"
        | Pickup_Address                                                                     | Delivery_Address                                                    |
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
      And I select "15 minutes" load time
      And I click on "Get Estimate" button
      And I click on "Kioski mode Continue" button
      When I enter all details on "delivery details" for "kiosk mode portal"
        |Product_Description|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Reciept|
        |20 boxes           |Handle with care   |Kiosk Customer         |2442442440     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |1212   |
      And I select "Partner Invoice" as Payment Method
      # CORE-5628 Partner user cannot change payment method without adding password in KIOSK mode enabled partner portal
      And I should see "Admin Password Required" message
      And I click on "Kioski mode Continue" button
      And I should see "Password is required." message
      And I enter "valid" password for Admin access
      And I click on "Kioski mode Continue" button
      And I click on "Schedule Bungii" button
      Then I verify the ui links on "success" page for "fixed pricing" partner

    @ready
    #CORE-5039:To verify that driver receives PN for multiple secondary geofence for fixed pricing partner scheduled deliveries
    Scenario: To verify that driver receives PN for multiple secondary geofence for fixed pricing partner scheduled deliveries
      When I switch to "ORIGINAL" instance
      And I terminate "customer" app on "same" devices
      When I open new "Safari" browser for "MOBILE DEVICE"

      And I open "fixed pricing" partner portal
      When I enter all details on "get estimate page" for "fixed pricing"
        | Pickup_Address                                                      | Delivery_Address                             |
        | 198 Laurel Race Track Road, Laurel, United States, Maryland, 20725  | 1318 Annapolis Road, Odenton, MD 21113, USA  |
      And I select "Threshold" service level
      And I click on "Continue" button
      When I enter all details on "delivery details" for "fixed pricing portal"
        |Product_Description|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Reciept|
        |20 boxes           |Handle with care   |Biglots         |8877661190     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |1212   |
      And I enter following Credit Card details on Partner Portal
        |Card_Type   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD   |12/25  |VALID POSTAL CODE|VALID CVV|
      And I click on "Schedule Bungii" button
      Then I verify the ui links on "success" page for "fixed pricing" partner
      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I login as "new washington" driver on "same" device and make driver status as "Online"

      And I wait for "4" mins
      Then I view and reject virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"

      And I connect to "extra1" using "Driver2" instance
      And I Switch to "driver" application on "same`" devices
      And I am on the "LOG IN" page on driverApp
      And I am logged in as "testdrivertywd_applemd_a_billl baltimorel" driver
      And I wait for "4" mins
      And I view and accept virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
      And I should be navigated to "SCHEDULED BUNGII" screen
      Then I Select Trip from scheduled trip