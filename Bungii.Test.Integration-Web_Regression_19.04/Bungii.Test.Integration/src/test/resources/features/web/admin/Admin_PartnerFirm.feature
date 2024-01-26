@web
Feature: Admin_PartnerFirm

  Background:
    Given I am logged in as TestAdmin
    
  #@ready
  #@email
  #Faling at line no. 24
  @knownissue
  Scenario: Verify Partner Firm Scheduled Email - Ondemand Bulk Trip
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
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I cancel bungii as a driver "Testdrivertywd_appledc_a_web Sundarg"
    Then Partner firm should not receive "Bungii Delivery Pickup Canceled" email


  #@ready
  @email
    @failed
  @knownissue
    #Faling for 'Failed trips email' step (line no. 41)
  Scenario: Verify Partner Firm Scheduled Email - Solo Ondemand
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999359 | Testcustomertywd_appleWashD Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarm" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I cancel bungii as a driver "Testdrivertywd_appledc_a_web Sundarm"
    Then Partner firm should receive "Bungii Delivery Pickup Canceled" email
    And Admin receives "Failed On-Demand Trips" trip email for "Driver Cancelled" status

  #@sanity
  #@ready
  @email
  @failed
  @knownissue
    #Passed
      #test data created in base
  Scenario: Verify Partner Firm Email Upon Driver Acceptance And Removal Research - Duo Scheduled
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999355 | Testcustomertywd_appleWashA Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundara" and "Testdrivertywd_appledc_a_web Sundarb" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Close" button
    When I click on "Edit" link beside scheduled bungii
    Then Pickup should be unassigned from the driver
    And I click on "Research" button
    And I wait for "2" mins
    And As a driver "Testdrivertywd_appledc_a_web Sundarc" perform below action with respective "Duo Scheduled Researched" Delivery
      | driver1 state|
      | Accepted  |
    Then Partner firm should receive "Bungii Delivery Pickup Updated" email
    
  #@ready
  @email
  @knownissue
  #Faling for 'Failed trips email' step (line no. 101)
  #test data created in base
  Scenario: Verify Partner Firm Cancellation Email - Duo Scheduled
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999356 | Testcustomertywd_appleWashB Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundard" and "Testdrivertywd_appledc_a_web Sundare" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    When I view the Completed Deliveries on the admin portal
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    And Partner firm should receive "Bungii Delivery Pickup Canceled" email
    And Admin receives "Failed Scheduled Trips" trip email for "Admin Cancelled" status


  #@ready
  @email
  @failed
  @knownissue
    #Passed
    #test data created in base
  Scenario: Verify Partner Firm Email Upon Driver Acceptance And Remove Research - Solo Scheduled
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999358 | Testcustomertywd_appleWashC Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarg" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      |Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Close" button
    When I click on "Edit" link beside scheduled bungii
    Then Pickup should be unassigned from the driver
    And I click on "Research" button
    And As a driver "Testdrivertywd_appledc_a_web Sundarh" perform below action with respective "Solo Scheduled Researched" Delivery
      | driver1 state|
      | Accepted  |
    Then Partner firm should receive "Bungii Delivery Pickup Updated" email

  #@ready
  @email
  @failed
  @knownissue
  #Faling for 'Failed trips email' step (line no. 160)
  #test data created in base
  Scenario: Verify Partner Email When Cancel Scheduled Bungii As An Admin
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
    When I view the Deliveries list on the admin portal
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    And Partner firm should receive "Bungii Delivery Pickup Canceled" email
    And Admin receives "Failed Scheduled Trips" trip email for "Admin Canceled" status


  #@ready
  @knownissue
  @email
  @failed
    @log
    ##Passed
    #Create driver in base
  Scenario: Verify Partner Firm Scheduled Email - Solo Scheduled Bulk Trip
    When I click on "Bulk Delivery Upload  > Upload Deliveries" Menu
    And I select business user "Testcustomertywd_apple-Jd1"
    And I upload image and csv file associated with the "Solo Scheduled" trip
    And I click on "Upload" button on "Upload Deliveries" page
    When I click on "Confirm" button on "Upload Deliveries" page
    Then the "Deliveries have been requested successfully." message is displayed
    And I click on the "OK" Button on "Confirmation" popup
    And I note the Pickupref of trip
    When As a driver "Testdrivertywd_appledc_a_web Sundark" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
     ##################
    And I view the Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I update the Scheduled date of the trip by 15 minutes
    And I select "No drivers available" as the reason from the reason dropdown
    And I remove driver "Testdrivertywd_appledc_a_web Sundark" and add the new driver "Testdrivertywd_appledc_a_web Sundarj"
    And I click on "Verify" button on Edit Scheduled bungii popup
    Then Tick mark should be displayed beside driver and scheduled date
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    Then Partner firm should receive "Bungii Delivery Pickup Updated" email
    #################
    When I cancel bungii as a customer "Testcustomertywd_apple-Jd1" with phone number "9999794897"
    Then Partner firm should receive "Bungii Delivery Pickup Canceled" email

  #@ready
  @knownissue
  @email
  @failed
    #Passed
    #test data created in base
  Scenario: Verify Partner Firm Email For Long Stacked Bungii - Solo Scheduled
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999362 | Testcustomertywd_appleWashG Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarl" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
       |Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999363 | Testcustomertywd_appleWashI Shah|
    And As a driver "Testdrivertywd_appledc_a_web Sundarl" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      |Stacked Pickup Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I cancel bungii as a customer "Testcustomertywd_appleWashI Shah" with phone number "9999999363"
    Then Partner firm should not receive "Bungii Delivery Pickup Canceled" email

  #@ready
  @knownissue
  @email
  @failed
    #Passed
    #test data created in base
  Scenario: Verify Partner Firm Email For Short Stacked Bungii - Solo Scheduled
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661153 | Testcustomertywd_BppleMarkEX LutherEX|
    And As a driver "Testdrivertywd_appledc_a_drvaf Washingtonaf" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted |
    And I wait for "2" mins
    Then I should be able to see the respective bungii trip with the below status
      |  Status |
      | Scheduled |
    And As a driver "Testdrivertywd_appledc_a_drvaf Washingtonaf" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661154 | Testcustomertywd_BppleMarkEY LutherEY|
    And As a driver "Testdrivertywd_appledc_a_drvaf Washingtonaf" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      |Stacked Pickup Accepted |
    Then Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I cancel bungii as a customer "Testcustomertywd_BppleMarkEY LutherEY" with phone number "8877661154"
    Then Partner firm should not receive "Bungii Delivery Pickup Canceled" email

  #@sanity
  #@ready
  @knownissue
  @failed
    #Failing at line no. 307
    #test data created in base
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
    And Partner firm should receive "Bungii Delivery Pickup Scheduled" email
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Close" button
    When I click on "Edit" link beside scheduled bungii
    Then Pickup should be unassigned from the driver
    And I click on "Research" button
    And As a driver "Testdrivertywd_appledc_a_web Sundarn" perform below action with respective "Solo Scheduled Researched" Delivery
      | driver1 state|
      | Accepted  |
    When I wait for 2 minutes
    And I click on "Close" icon
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Scheduled |
    Then Partner firm should receive "Bungii Delivery Pickup Updated" email
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    When I view All Deliveries list on the admin portal
    And I wait for "2" mins
    When  I search the delivery using "Pickup Reference"
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    And Partner firm should not receive "Bungii Delivery Pickup Canceled" email

  @ready
    #Newer cluster concept is not completly configured on QA Auto
  Scenario: Verify that Point of interests are getting populated for the newer clusters
    When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 9999992222     | Testcustomertywd_appleand_C Android| Cci12345 |
    And As a driver "Testdriver_goa_b Android_test" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state     |
      | Accepted         |
      | Enroute          |
      | Arrived           |
      | Loading Item     |
      | Driving To Dropoff|
      | Unloading Item    |
      | Bungii Completed  |
    Then I wait for "2" mins
    When I click on "Potential Partners > Assign Partner" Menu
    And I select "Kansas" geofence
    Then I verify that the point of interests fields are populated
    When I get the count of "Pickup Trips"
    And I click on "View Trips" hyperlink
    Then I verify the field "Pickups in this Cluster"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 9999992222     |                 |

@regression
#stable
Scenario: Verify that same delivery is shown for other driver under Deliveries section When admin adds driver to duo trip
  When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
    | Bungii Time   | Customer Phone | Customer Name |
    | NEXT_POSSIBLE | 9766209256 | Testcustomertywd_applekrishna Hoderker|
  And As a driver "Testdrivertywd_appledc_a_web Sundarn" perform below action with respective "Duo Scheduled" Delivery
    | driver1 state|
    | Accepted  |
  And I view the Scheduled Deliveries list on the admin portal
  And I search the delivery of Customer
  Then I should be able to see the respective bungii with the below status
    |  Status |
    | Assigning Driver(s) |
  And I click on "Edit" link beside live delivery
  When I click on "Edit Trip Details" radiobutton
  And I assign driver "Testdrivertywd_appledc_a_web Sundarm" for the trip
  And I click on "VERIFY" button
  And the "Your changes are good to be saved." message is displayed
  Then I click on "SAVE CHANGES" button
  And the "Bungii Saved!" message is displayed
  When I click on "Close" button
  Then I wait for "2" mins
  And I refresh the page
  And I search the delivery of Customer "Testcustomertywd_applekrishna Hoderker"
  Then I verify that the "Testdrivertywd_appledc_a_web Sundarm" is displayed
  
  @regression
  Scenario: Verify that Admin does not get "Customer has ongoing trip" alert when he edits an already edited schedule bungii
    When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |Customer Password|
      | NEXT_POSSIBLE | 9999992222     | Testcustomertywd_appleand_C android|Cci12345         |
    And As a driver "Testdriver_goa_b Android_test" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state     |
      | Accepted          |
    And I wait for 2 minutes
    And I view the Scheduled Deliveries list on the admin portal
    And I search the delivery of Customer
    And I click on the "Edit" button from the dropdown
    And I click on "Edit Trip Details" radiobutton
    Then I should not get alert as "Customer has ongoing trip"
