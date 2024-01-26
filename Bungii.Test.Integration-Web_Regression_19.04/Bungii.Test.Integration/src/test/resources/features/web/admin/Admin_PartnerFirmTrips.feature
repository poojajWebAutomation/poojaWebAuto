@web
  #Email steps from Admin_PartnerFirm.feature file has been commented out
Feature: Admin_PartnerFirmTrips

  Background:
    Given I am logged in as TestAdmin
  
  @sanity
  @regression
      #test data created in base
  Scenario: Verify Partner Firm Upon Driver Acceptance And Removal Research - Duo Scheduled
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999355 | Testcustomertywd_appleWashA Shah|
    When As a driver "Testdrivertywd_appledc_a_web Sundara" and "Testdrivertywd_appledc_a_web Sundarb" perform below action with respective "Duo Scheduled" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    #Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    And As a driver "Testdrivertywd_appledc_a_web Sundarc" perform below action with respective "Duo Scheduled Researched " Delivery
      | driver1 state|
      | Accepted  |
    #Then Partner firm should receive "Bungii Delivery Pickup Updated" email
  
  @regression
  Scenario: Verify Partner Firm Scheduled  - Ondemand Bulk Trip
    When I click on "Bulk Delivery Upload  > Upload Deliveries" Menu
    And I select business user "Testcustomertywd_apple-Jd1"
    And I upload image and csv file associated with the "Ondemand" trip
    And I click on "Upload" button on "Upload Deliveries" page
    When I click on "Confirm" button on "Upload Deliveries" page
    Then the "Deliveries have been requested successfully." message is displayed
    And I note the Pickupref of trip
    When As a driver "Testdrivertywd_appledc_a_web Sundarg" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Accepted  |
    #Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I cancel bungii as a driver "Testdrivertywd_appledc_a_web Sundarg"
    #Then Partner firm should not receive "Bungii Delivery Pickup Canceled" email

  @regression
  Scenario: Verify Partner Firm Scheduled  - Solo Ondemand
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999359 | Testcustomertywd_appleWashD Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarm" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      |Accepted |
    #Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I cancel bungii as a driver "Testdrivertywd_appledc_a_web Sundarm"
    #Then Partner firm should receive "Bungii Delivery Pickup Canceled" email
    #And Admin receives "Failed Scheduled Trips" trip email for "Driver Cancelled" status

 
    
  @regression
    #stable
      #test data created in base
  Scenario: Verify Partner Firm Cancellation - Duo Scheduled
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999356 | Testcustomertywd_appleWashB Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundard" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      |Accepted |
    And As a driver "Testdrivertywd_appledc_a_web Sundare" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      |Accepted |
    #Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    #When I view the Trips list on the admin portal
    And I wait for "2" mins
    And I view the Deliveries list on the admin portal
    And I search the delivery based on customer "Testcustomertywd_appleWashB Shah"
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    #And Partner firm should receive "Bungii Delivery Pickup Canceled" email
    #And Admin receives "Failed On-Demand Trips" trip email for "Admin Cancelled" status


  @regression
    #stable
    #test data created in base
  Scenario: Verify Partner Firm Upon Driver Acceptance And Remove Research - Solo Scheduled
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999358 | Testcustomertywd_appleWashC Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarg" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      |Accepted |
    #Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    And I get the new pickup reference generated
    #Then Partner firm should receive "Bungii Delivery Pickup Updated" email

  @regression
    #stable
    #test data created in base
  Scenario: Verify Partner When Cancel Scheduled Bungii As An Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999360 | Testcustomertywd_appleWashE Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundari" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for "2" mins
    When I view the Deliveries list on the admin portal
    And I search the delivery based on customer "Testcustomertywd_appleWashE Shah"
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    #And Partner firm should receive "Bungii Delivery Pickup Canceled" email
    #And Admin receives "Failed On-Demand Trips" trip email for "Admin Cancelled" status


  @regression
    @testpath
    #Create driver in base
  Scenario: Verify Partner Scheduled - Solo Scheduled Bulk Trip
    When I click on "Bulk Delivery Upload  > Upload Deliveries" Menu
    And I select business user "Testcustomertywd_apple-Jd1"
    And I upload image and csv file associated with the "Solo Scheduled" trip
    And I click on "Upload" button on "Upload Deliveries" page
    When I click on "Confirm" button on "Upload Deliveries" page
    When I click on "Close" button on "Upload Deliveries" page
    Then the "Trips have been requested successfully." message is displayed
    And I note the Pickupref of trip
    When As a driver "Testdrivertywd_appledc_a_web Sundark" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    #Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
     ##################
     And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    And I get the scheduled time of the trip
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I update the Scheduled date of the trip by 15 minutes
    And I remove driver "Testdrivertywd_appledc_a_web Sundark" and add the new driver "Testdrivertywd_appledc_a_web Sundarj"
    When I select reason as "Partner initiated"
    And I click on "Verify" button on Edit Scheduled bungii popup
    Then Tick mark should be displayed beside driver and scheduled date
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I get the new pickup reference generated


  @regression
    #stable
    #test data created in base
  Scenario: Verify Partner Firm For Long Stacked Bungii - Solo Scheduled
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999362 | Testcustomertywd_appleWashG Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarl" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
       |Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
    #Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999363 | Testcustomertywd_appleWashI Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarl" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      |Stacked Pickup Accepted |
    #Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    And I get the new pickup reference generated

  @ready
    #failed in sprint 49 regression
    #test data created in base
  Scenario: Verify Partner Firm For Short Stacked Bungii - Solo Scheduled
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999102 | Testcustomertywd_appleNewQB Customer|
    And As a driver "Testdrivertywd_appledc_a_drvaj Washingtonaj" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted |
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    And As a driver "Testdrivertywd_appledc_a_drvaj Washingtonaj" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
    #Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661115 | Testcustomertywd_appleMarkDL LutherDL|
    And As a driver "Testdrivertywd_appledc_a_drvaj Washingtonaj" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      |Stacked Pickup Accepted |
    And I get the new pickup reference generated

  @sanity
  @regression
    #failed in sprint 49 regression
    #test data created in base
    #changed driver name
  Scenario: Verify Partner Firm Driver Removal Research And Cancel As An Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9766209256 | Testcustomertywd_applekrishna Hoderker|
    And As a driver "Testdrivertywd_appledc_a_web Sundarn" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    #And Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    And As a driver "Testdrivertywd_appledc_a_web Sundarn" perform below action with respective "Solo Scheduled Researched" Delivery
      | driver1 state|
      | Accepted  |
    When I wait for 2 minutes
    And I click on "Close" icon
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Scheduled |
    #Then Partner firm should receive "Bungii Delivery Pickup Updated" email
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for "2" mins
    When I view the Deliveries list on the admin portal
    And I search the delivery based on customer "Testcustomertywd_applekrishna Hoderker"
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    #And Partner firm should not receive "Bungii Delivery Pickup Canceled" email
