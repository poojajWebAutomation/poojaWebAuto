@web
Feature: Service Level

  Background:
    Given I navigate to "Partner" portal configured for "service level" URL

  @test
  @regression
  Scenario: Verify that service level options display on configured Partner portal site.
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |
    Then I should "see Service Level"
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
      |Furniture       |Handle with care   |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD2|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
    Then I close the Trip Delivery Details page
    And I should logout from Partner Portal

    @regression
  Scenario: Verify that service level options display on configured Partner portal site only after selecting the pickup  and delivery address.
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "see No service selected"
      When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |
    Then I should "see Service Level"

  @regression
    #stable
  Scenario: Verify that service level partner portal trip is shown in Admin portal
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                   |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002  |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Biglots" Alias
    And I change the service level to "Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
      |Furniture       |Handle with care   |Testartner T |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |DISCOVER CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
#    Core-3391 Verify that new field Estimated Delivery Time is displayed on details delivery page of Partner portal
    And I check if correct "estimated time on partner portal" is displayed
    Then I close the Trip Delivery Details page
    When I navigate to "Admin" portal configured for "QA" URL
    #When I navigate to "Bungii Admin Portal in new tab" URL
    #And I view the Deliveries list on the admin portal
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|

#    Core-3391 Verify that new field Estimated Delivery Time is displayed on details delivery page of Admin portal
    When I view the delivery details
    Then I check if correct "estimated time on admin portal" is displayed
    #Then the Bungii details is displayed successfully
    #And I should logout from Partner Portal

  #CORE-1541
  @regression
    #stable
  Scenario: Verify that correct Estimate duration for service level partner portal trip is shown in Admin portal
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                   |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002  |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Biglots" Alias
    And I change the service level to "Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
      |Furniture       |Handle with care   |Testpartner S |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD2|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
    #    Core - 3842 Verify that Estimated Delivery time is displayed correctly on delivery details of Fixed distance based Partner portal
    And I calculate the estimated delivery time for "fixed distance based"
    And I check if correct "estimated time fixed distance based Partner portal" is displayed
    Then I close the Trip Delivery Details page
    When I navigate to "Admin" portal configured for "QA" URL
    And I wait for 2 minutes
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Assigning Driver(s) |
    And I select the partner portal scheduled trip on scheduled delivery
    And I click on "Delivery Overview" button on delivery details
    Then I should "see correct Estimation Duration" for "Biglots" Alias

#   Core-3391 Verify that Estimated time on partner portal details delivery page gets updated when delivery address is changed
    When I navigate back to Scheduled Deliveries
    And I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    And I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I get the new pickup reference generated
    And I wait for "2" mins

    When I navigate to "Partner" portal configured for "service level" URL
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    And I calculate the estimated delivery time for "fixed distance based"
    And I check if correct "estimated time fixed distance based Partner portal" is displayed

#CORE-1862 scenario
  @regression
    #stable
  Scenario Outline: Verify driver earning calculated for partner delivery of <Type> for Service <ServiceName> for <Distance> distance range
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    #CORE-3849 changes incorporated
    When I request "<Type>" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address            |Address_Enter|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | <DeliveryAddress>           |CopyPaste    |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Biglots" Alias
    And I change the service level to "<ServiceName>" in "Partner" portal
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
      |Furniture       |Handle with care   |Testpartner R |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
    Then I close the Trip Delivery Details page
    When I navigate to "Admin" portal configured for "QA" URL
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Assigning Driver(s) |
    And I select the partner portal scheduled trip on scheduled delivery

    #CORE-3849 changes incorporated
    Examples:
      |Type|DeliveryAddress                                                                  |ServiceName   |Distance |Address_Enter|
      |Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Curbside      |Above 100|CopyPaste    |
      |Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Curbside      |Above 100|CopyPaste    |

    #CORE-1574(Web side part)
    @ready
  Scenario: Verify that admin can update service level for the Partner Portal delivery from Scheduled Deliveries page
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    And I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                   |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002  |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Biglots" Alias
    When I change the service level to "Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name                       |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
      |Furniture       |Handle with care   |Testcustomertywd_appleNwAAA CustAAA |9998881111     |Test Pickup        |9823741002          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD2|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    When I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
    When I close the Trip Delivery Details page
    And I navigate to "Admin" portal configured for "QA" URL
    And I view the all Scheduled Deliveries list on the admin portal
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status    |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I change the service level to "White Glove" in "Admin" portal
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    And I get the new pickup reference generated
    And I search the delivery of Customer
    And I click on the dropdown beside scheduled bungii
    And I click the "Notes & History" link
    And I click on "History"
    Then The "History" tab should be selected
    And I should see edit Service level history
    And I close the Note
    When I view the partner portal delivery details in admin portal
    Then the change service level should be displayed on delivery details page
    And the price for the delivery shown as per the changed service level
    When I navigate to partner portal
    And I select the Scheduled Bungii from Delivery List
    Then the change service level should be displayed on partner portal delivery details page
    And the price for the partner portal delivery shown as per the changed service level


  @regression
  Scenario Outline: Verify service level estimate cost calculation for partner delivery of <Type> for Service <ServiceName> for <Distance> distance range
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I request "<Type>" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address            |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | <DeliveryAddress>           |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Biglots" Alias
    And I change the service level to "<ServiceName>"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    And I check correct price is shown for selected service
    
    Examples:
      |Type|DeliveryAddress                                                                  |ServiceName   |Distance |
      |Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|White Glove   |0-10     |
      |Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|White Glove   |0-10     |
      |Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |White Glove   |10-15    |
      |Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |White Glove   |10-15    |
      |Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |White Glove   |15-30    |
      |Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |White Glove   |15-30    |
      |Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |White Glove   |30-100   |
      |Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |White Glove   |30-100   |
      |Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |White Glove   |Above 100|
      |Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |White Glove   |Above 100|
      |Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Room of Choice|0-10     |
      |Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Room of Choice|0-10     |
      |Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Room of Choice|10-15    |
      |Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Room of Choice|10-15    |
      |Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Room of Choice|15-30    |
      |Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Room of Choice|15-30    |
      |Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Room of Choice|30-100   |
      |Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Room of Choice|30-100   |
      |Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Room of Choice|Above 100|
      |Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Room of Choice|Above 100|
      |Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Threshold     |0-10     |
      |Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Threshold     |0-10     |
      |Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Threshold     |10-15    |
      |Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Threshold     |10-15    |
      |Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Threshold     |15-30    |
      |Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Threshold     |15-30    |
      |Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Threshold     |30-100   |
      |Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Threshold     |30-100   |
      |Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Threshold     |Above 100|
      |Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Threshold     |Above 100|
      |Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Curbside      |0-10     |
      |Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Curbside      |0-10     |
      |Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Curbside      |10-15    |
      |Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Curbside      |10-15    |
      |Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Curbside      |15-30    |
      |Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Curbside      |15-30    |
      |Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Curbside      |30-100   |
      |Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Curbside      |30-100   |
      |Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Curbside      |Above 100|
      |Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Curbside      |Above 100|

 #    Core - 3842 Verify that Estimated Delivery time is displayed correctly on delivery details of weight based Partner portal
  @ready
  Scenario: Verify that Estimated Delivery time is displayed correctly on delivery details of weight based Partner portal
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
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
    And I calculate the estimated delivery time for "weight based"
    And I check if correct "estimated time weight based Partner portal" is displayed
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the partner portal Scheduled Trips list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Assigning Driver(s)|
    When I view the delivery details
    Then I check if correct "estimated time on admin portal" is displayed
