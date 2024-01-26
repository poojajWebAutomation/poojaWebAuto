@web

Feature: FloorDecor Multiple PhoneNo

  Background:
    Given I navigate to "Partner" portal configured for "fnd multiple phone" URL

  @regression
  Scenario: To verify ability to add multiple SMS recipients phone numbers on Delivery Details Screen for solo delivery
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    #CORE-3849 changes incorporated
    When I request "Solo" Bungii trip in partner portal configured for "fnd multiple phone" in "atlanta" geofence
      | Pickup_Address                                 | Delivery_Address                                |Address_Enter|
      | 610 Holcomb Bridge Rd, Roswell, GA, US, 30076  | 610 Holcomb Bridge Rd, Roswell, GA, US, 30076   |CopyPaste    |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Floor & Decor #130" Alias
    And I change the service level to "First Threshold" in "Partner" portal
    And I click "GET ESTIMATE" button on Partner Portal
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I click "Add SMS recipient" button "three times" to add more recipients
    Then New mobile recipients field should be added
    When I enter all details on "Delivery Details" for "fnd multiple phone" on partner screen
      |Product_Description|Dimensions|Weight|Special_Instruction|Customer_Name   |Customer_Mobile|SMS_Recipient1|SMS_Recipient2|SMS_Recipient3|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Rb_Sb_Number|ScheduledBy     |
      |10 boxes           |20X20X20  | 1570 |Handle with care   |Testpartner N   |9998881112     |   9991119999 |   9992119999 |  9993119999  |Test Pickup        |9999999358          |Test Drop contact     |9998881112       |For decoration  |007         |UserFNDMultiple |
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the extra recipient numbers added on delivery details"
    Then I close the Trip Delivery Details page
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status             |
      | Assigning Driver(s)|
    When I view the delivery details in admin portal
    And I click on "Delivery Overview" button on delivery details
    Then Only First added customer phone number should be displayed on Admin portal Delivery details page
    And Added recipient phone numbers should be stored in database

  @regression
  Scenario: To verify ability to add multiple SMS recipients phone numbers on Delivery Details Screen for duo delivery
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    #CORE-3849 changes incorporated
    When I request "Duo" Bungii trip in partner portal configured for "fnd multiple phone" in "atlanta" geofence
      | Pickup_Address                                 | Delivery_Address                                |Address_Enter|
      | 610 Holcomb Bridge Rd, Roswell, GA, US, 30076  | 610 Holcomb Bridge Rd, Roswell, GA, US, 30076   |CopyPaste    |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Floor & Decor #130" Alias
    And I change the service level to "First Threshold" in "Partner" portal
    And I click "GET ESTIMATE" button on Partner Portal
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I click "Add SMS recipient" button "two times" to add more recipients
    Then New mobile recipients field should be added
    When I enter all details on "Delivery Details" for "fnd multiple phone" on partner screen
      |Product_Description|Dimensions|Weight|Product_Description2|Dimensions2|Weight2|Special_Instruction|Customer_Name   |Customer_Mobile|SMS_Recipient1|SMS_Recipient2|SMS_Recipient3|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Rb_Sb_Number|ScheduledBy     |
      |10 boxes           |20X20X20  | 1570 |    20boxes         | 10x10x10  | 1200  |Handle with care   |Testpartner N   |9998881112     |   9991119999 |   9992119999 |  9993119999  |Test Pickup        |9999999358          |Test Drop contact     |9998881112       |For decoration  |007         |UserFNDMultiple |
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the extra recipient numbers added on delivery details"
    Then I close the Trip Delivery Details page
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status             |
      | Assigning Driver(s)|
    When I view the delivery details in admin portal
    And I click on "Delivery Overview" button on delivery details
    Then Only First added customer phone number should be displayed on Admin portal Delivery details page
    And Added recipient phone numbers should be stored in database
