@web
  Feature: Partner_Estimate

    Background:
      Given I navigate to "Partner" portal configured for "normal" URL
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"

    @regression
    @sanity
    Scenario: Verify that correct estimate cost calculated for Solo Bungii
      #CORE-3849 changes incorporated
      When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
        | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|Address_Enter|
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |CopyPaste    |
      And I select Pickup Date and Pickup Time on partner portal
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |09:30 AM             |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      Then I check correct estimated price calculated on Partner Portal
      And I should logout from Partner Portal

    @regression
      #stable
    Scenario:Verify If Partner User Cancel Solo Bungii After Entering Delivery Details Then He Is Navigated back To Get Estimate Screen
      When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
        | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Pickup Date and Pickup Time on partner portal
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |10:30 AM             |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" for "normal" on partner screen
        |Items_To_Deliver|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |Testpartner L |9975960666     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Back to Estimate" button on Partner Portal
      Then I should "see Get Estimate screen"
      And I should logout from Partner Portal

    @regression
      #stable
    Scenario:Verify If Partner User Cancel OnDemand Bungii Before Entering Delivery Details Then He Is Navigated back To Get Estimate Screen
      When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
        | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Pickup Date and Pickup Time on partner portal
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |11:30 AM             |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      And I click "Back to Estimate" button on Partner Portal
      And I should "see Get Estimate screen"
      And I should logout from Partner Portal

    @regression
    Scenario:Verify Validation message is display for Mandatory fields on "Get Estimate" screen
      When I clear the existing pickup address details
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "see validation message for mandatory fields"
      And I should logout from Partner Portal

    @regression
    Scenario: Verify information icon display correct information on "Get Estimate" screen
      When I click on "WHAT’S NEEDED?" information icon and verify its text contents
      And I click on "Delivery Address" information icon and verify its text contents
      And I click on "Load/Unload Time" information icon and verify its text contents
      And I click on "PickUp Date" information icon and verify its text contents
      And I should logout from Partner Portal

    @regression
    @sanity
    Scenario:Verify that Get Estimate cost get recalculate on changing the Load/Unload Time
      When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
        | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Pickup Date and Pickup Time on partner portal
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |11:30 AM             |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I change the "Load Unload Time" and click on Get Estimate button
        |Load_Unload_Time|
        |15 minutes      |
      Then Estimate Cost should get recalculate
      And I should logout from Partner Portal

    @regression
    Scenario: Verify that Get Estimate cost get recalculated on changing the Delivery Address
      When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
        | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Pickup Date and Pickup Time on partner portal
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |11:30 AM             |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I change the "Delivery Address" and click on Get Estimate button
        |Delivery_Address                                                                    |
        |700 L'Enfant Plaza Southwest, Washington, United States, District of Columbia, 20024|
      Then Estimate Cost should get recalculate
      And I should logout from Partner Portal

    @regression
    Scenario:Verify that clearing Pickup address clears Address field on "Get Estimate" screen
      When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
        | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I clear the Pickup Address on Get Estimate screen of Partner Portal
      Then I check that Address field on Get Estimate screen get clear
      And I should logout from Partner Portal
