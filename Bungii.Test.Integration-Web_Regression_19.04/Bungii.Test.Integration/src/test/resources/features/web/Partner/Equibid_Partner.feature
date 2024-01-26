@web
Feature: Equibid Partner Portal

  Background:
    Given I navigate to "Partner" portal configured for "Equip-bid" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"

  @regression
  Scenario: To check pickup addres is editable for Equip-bid Partner Portal
      When I request "Solo" Bungii trip in partner portal configured for "Equip-bid" in "Kansas" geofence
        | Pickup_Address                                                 | Delivery_Address                                                    |
        | 6800 Zoo Drive, Kansas City, United States, Missouri, 64132    | 200 West 12th Street, Kansas City, United States, Missouri, 64105   |
      And I select Next Possible Pickup Date and Pickup Time
        |Trip_Time            |
        |NEXT_POSSIBLE        |
      And I click "GET QUOTE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter all details on "Delivery Details" for "Equip-bid" on partner screen
        |Items_To_Deliver|Special_Instruction|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Bidder_Number|
        |Furniture       |Handle with care   |Testpartner A|9998882222     |Test Pickup        |9999999369          |Test Dropcontact     |9998881112        |For Testing     |BN123        |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I click "Track Deliveries" button on Partner Portal
      Then I should "see the trip in the Delivery List"
      When  I am logged in as Admin
      And I wait for "2" mins
      #CORE-4969 : Verify Drop Off contact name & Phone number is displayed on Scheduled delivery details page on admin Portal
      And I view the all Scheduled Deliveries list on the admin portal
      And I search the delivery using "Pickup Reference"
      And I click on "Delivery Details" link beside scheduled bungii
      Then I should see correct Drop Off details on Delivery Details page


  @regression
  Scenario: To check that pickup default address is shown when click on start over after editing the pickup address for Equip-bid partner portal
        When I request "Solo" Bungii trip in partner portal configured for "Equip-bid" in "Kansas" geofence
          | Pickup_Address                                                 | Delivery_Address                                                    |
          | 6800 Zoo Drive, Kansas City, United States, Missouri, 64132    | 200 West 12th Street, Kansas City, United States, Missouri, 64105   |
        And I click on "Start Over" button
        Then default pickup address should be shown

    #CORE-4529:To verify that 2nd email is sent to configured email IDs when a new delivery is scheduled with date/time earlier than 1st delivery (Partner portal)
  @ready
  Scenario: To verify that 2nd email is sent to configured email IDs when a new delivery is scheduled with date/time earlier than 1st delivery (Partner portal)
    When I request Partner Portal "Solo" Trip for "Equip-bid" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |kansas| NEXT_POSSIBLE_THIRD_SLOT | 8877661132 | Testcustomertywd_appleMarkEC LutherEC|
    And I wait for 1 minutes
    When I request Partner Portal "Solo" Trip for "Equip-bid" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |kansas| NEXT_POSSIBLE | 8877661133 | Testcustomertywd_appleMarkED LutherED|
    And I wait for 2 minutes
    Then Partner firm should receive "2nd-Initial Email For Scheduled Delivery Before First Delivery" email
