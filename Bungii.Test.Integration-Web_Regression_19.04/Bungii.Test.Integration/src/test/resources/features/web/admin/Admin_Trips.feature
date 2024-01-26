@web
Feature: Admin_Trips

  Background:
    Given I am logged in as Admin
    
  @sanity
  @regression
    #test data created in base
    #changed driver name
    #First time promo code added
  Scenario: Verify Driver Removal Research and Cancel As An Admin For Solo Scheduled Pickup
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000006 | Testcustomertywd_appleweb CustF|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I view the all Scheduled Deliveries list on the admin portal
    When I wait for 2 minutes
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    When I wait for 2 minutes
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Solo Scheduled Researched" Delivery
      | driver1 state|
      | Accepted  |
    When I wait for 2 minutes
    And I click on "Close" icon
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Scheduled |
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    When I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    And The first time promo code should get released

  @sanity
  @ready
    #mania needs to be whitelisted
    #test data created in base
  Scenario: Verify Trips List Status Updation For Solo Scheduled Pickup
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000001 | Testcustomertywd_appleweb CustA|
   #Temperary Workaround for Today filter by commenting below steps and adding All filter steps
    #And I view the Scheduled Trips list on the admin portal
    And I wait for "2" mins
    And I view the Live Deliveries list on the admin portal
    And I search the delivery of Customer
    # CORE-4434 - Browser not detecting a delivery in "Delivery List" page as a link
    And I open the delivery in a new browser tab
    Then I should see "Delivery Details" header
    And I close Delivery details page
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    When I search the delivery of Customer
    Then I should see the delivery highlighted in "Blue"
    When I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery of Customer
    # CORE-4434 - Browser not detecting a delivery in "Delivery List" page as a link
    And I open the delivery in a new browser tab
    Then I should see "Delivery Details" header
    And I close Delivery details page
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery of Customer
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    And The delivery should not be highlighted in "Blue" for "Scheduled Deliveries"
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted |
   #Temperary Workaround for Today filter by commenting below steps and adding All filter steps
    #And I view the Scheduled Trips list on the admin portal
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Enroute |
    And I view the Live Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    And The delivery should not be highlighted in "Blue" for "Live Deliveries"
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Arrived |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driver(s) Arrived |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Loading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
         | Driving To Dropoff |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driving To Dropoff |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Unloading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Unloading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverA" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Bungii Completed |
    And I view the Deliveries list on the admin portal
    And I search the delivery of Customer
    Then The Delivery List page should display the delivery in "Payment Successful" state
    And I search the delivery of Customer
    # CORE-4434 - Browser not detecting a delivery in "Delivery List" page as a link
    And I open the delivery in a new browser tab
    Then I should see "Delivery Details" header
    And I close Delivery details page
    When I view All Deliveries list on the admin portal
    And I search the delivery of Customer
    And I open the delivery in a new browser tab
    Then I should see "Delivery Details" header
    And I close Delivery details page
    And I view the Deliveries list on the admin portal
    And I search the delivery of Customer
    And The delivery should not be highlighted in "Blue" for "All Deliveries"
    And Customer should receive "Your Bungii Receipt" email

  @sanity
  @regression
        #test data created in base (need to update driver geofence)
  Scenario: Verify Editing and Removal of driver from Duo Scheduled Trip Started By Non Controlled Driver
    When I request "duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999995001 | Testcustomertywd_appleweb CustZ|
   #Temperary Workaround for Today filter by commenting below steps and adding All filter steps
    #And I view the Scheduled Trips list on the admin portal
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    When As a driver "Testdrivertywd_appledc_a_john Smith" and "Testdrivertywd_appledc_a_jack Smith" perform below action with respective "Duo Scheduled" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled|
    # Non-Control driver starts the trip
    When As a driver "Testdrivertywd_appledc_a_jack Smith" perform below action with respective "Duo" Delivery
      | driver1 state|
      | Enroute |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Remove driver(s) and re-search" radiobutton
    And I remove non control driver "Testdrivertywd_appledc_a_jack Smith"
    Then The driver should get removed successfully
    And I should be able to see the respective bungii with the below status
      |  Status |
      | Driver Removed|

  @regression
    #Failed in Sprint 49
    #test data created in base
  Scenario: Verify Trip Requested and Estimated Count Updation On Customer List For Solo Scheduled Trip
    And I note the Trip Requested count of Customer "Jerome Seinfield"
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999992024| Jerome Seinfield|
    And I view the Customer list on the admin portal
    Then I should be able to see the Trip Requested count incremented in Customers Grid
    When I view the customer details page of Customer "Jerome Seinfield"
    Then Trip should be listed in the grid

  @regression
    #Failed in Sprint 49
      #test data created in base
  Scenario: Verify Trip Requested and Estimated Count Updation On Customer List For Duo Scheduled Trip
    And I note the Trip Requested count of Customer "Krishna Hoderker"
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284174823| Krishna Hoderker|
    And I view the Customer list on the admin portal
    Then I should be able to see the Trip Requested count incremented in Customers Grid
    When I view the customer details page of Customer "Krishna Hoderker"
    Then Trip should be listed in the grid
  
  @regression
    #Failed in Sprint 49
  Scenario: Verify Driver Est. Earnings for for Customer Delivery
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000002 | Testcustomertywd_appleweb CustB|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      |Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
    And I view the Live Deliveries list on the admin portal
    And I wait for 2 minutes
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Unloading Items |
    And I select the scheduled trip on live delivery
    And I click on "Payment Details" button on delivery details
    Then I view the correct Driver Est. Earnings for geofence based pricing model
    And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state|
        | Bungii Completed |
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    Then The Delivery List page should display the delivery in "Payment Successful" state
    And I select the scheduled trip on All Deliveries
    And I click on "Payment Details" button on delivery details
    Then I view the correct Driver Earnings for geofence based pricing model
  
  
  @ready
 #stable
  Scenario: Verify Filters shows future deliveries in All deliveries page
	When I request "duo" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name |
	  | 3_DAY_LATER | 9284174823       | Krishna Hoderker|
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery of Customer
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    When I change filter to "This Week" on Scheduled deliveries
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    When I change filter to "This Month" on Scheduled deliveries
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for "2" mins
	And I view All Deliveries list on the admin portal
    And I search the delivery of Customer
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    When I change filter to "The Past Day" on All deliveries
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    When I change filter to "The Past Week" on All deliveries
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    When I change filter to "The Past 4 Weeks" on All deliveries
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    When I change filter to "The Past 3 Months" on All deliveries
    Then The Delivery List page should display the delivery in "Admin Canceled" state
    When I change filter to "The Beginning of Time" on All deliveries
    Then The Delivery List page should display the delivery in "Admin Canceled" state
  
  @sanity
  @regression
  Scenario: Verify Cancellation of Scheduled Bungii As An Admin
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                  |
      | NEXT_POSSIBLE | 9284000005     | Testcustomertywd_appleweb CustE|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverF" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    And I wait for 2 minutes
    And I view the Deliveries list on the admin portal
    Then The Delivery List page should display the delivery in "Admin Canceled" state

 #This scenario is no longer valid as manually end bungii link is removed for CORE-3257. Can be reused when coding CORE-3257(Edit Delivery Status - Payment Complete)
#  @sanity
#  @regression
#    #stable
#    #test data created in base
#  Scenario: Verify Manually Ending Bungii As An Admin For Solo Scheduled Pickup
#    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
#      | Bungii Time   | Customer Phone | Customer Name |
#      | NEXT_POSSIBLE | 9284000002 | Testcustomertywd_appleweb CustB|
#    And As a driver "Testdrivertywd_appledc_a_web TestdriverB" perform below action with respective "Solo Scheduled" Delivery
#      | driver1 state|
#      |Accepted |
#      | Enroute  |
#      | Arrived |
#      | Loading Item |
#    And I view the Live Deliveries list on the admin portal
#    Then I should be able to see the respective bungii with the below status
#      |  Status |
#      | Loading Items |
#    When I view the delivery details
#    Then the Bungii details is displayed successfully
#    And I click on "Manually End Bungii" link
#    And Enter the End Date and Time
#    And Click on "Calculate Cost" button
#    Then the amount is calculated and shown to admin
#    And Click on "Confirm" button
#    And I view the Deliveries list on the admin portal
#    Then The Delivery List page should display the delivery in "Payment Successful" state

 #CORE-2052 : To verify search happens when admin changes from solo to duo when trip was accepted by only 1 drive
  @ready
  Scenario: To verify search happens when admin changes from solo to duo when trip was accepted by only 1 driver
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661221     | Testcustomertywd_appleMarkHN LutherHN|
    And As a driver "Testdrivertywd_appledc_a_drvar Washingtonar" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the below status
    |  Status |
    | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I change the delivery type from "Solo" to "Duo"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I wait for 2 minutes
    And I get the new "pickup reference"
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    And  I search the delivery using "Pickup Reference"
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I click on "Add Driver" and add "Testdrivertywd_appledc_a_drvas Washingtonas" driver
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I get the latest "pickup Reference"
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |


 #CORE-2052 : To verify research happens when admin changes from solo to duo and assigns only one driver
@ready
  Scenario: To verify research happens when admin changes from solo to duo and assigns only one driver
  When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
   | Bungii Time   | Customer Phone | Customer Name |
   | NEXT_POSSIBLE | 9999999358 | Testcustomertywd_appleWashC Shah|
  And I wait for 2 minutes
  And I view the all Scheduled Deliveries list on the admin portal
  And  I search the delivery using "Pickup Reference"
  Then I should be able to see the respective bungii with the below status
    | Status           |
    | Assigning Driver(s)|
  When I click on "Edit" link beside scheduled bungii
  And I click on "Edit Trip Details" radiobutton
  And I change the delivery type from "Solo" to "Duo"
  And I click on "Add Driver" and add "Testdrivertywd_appledc_a_web TestdriverB" driver
  And I click on "Verify" button on Edit Scheduled bungii popup
  And I click on "Save" button on Edit Scheduled bungii popup
  Then "Bungii Saved!" message should be displayed
  When I wait for 2 minutes
  And I get the new "pickup reference"
  And I view the all Scheduled Deliveries list on the admin portal
  Then I should be able to see the respective bungii with the below status
    | Status           |
    | Assigning Driver(s)|
  When I click on "Edit" link beside scheduled bungii
  And I click on "Remove driver(s) and re-search" radiobutton
  And I select the first driver
  And I click on "Remove Driver" button
  And I click on "Research" button
  When I wait for 2 minutes
  And  I refresh the page
  And I click on the dropdown beside scheduled bungii
  #CORE-3382
  And I click the "Notes & History" link
  And I click on "History"
  Then The "History" tab should be selected
  And I should see solo to duo and assign remove one driver edit history
  And I close the Note
  And I get the latest "pickup Reference"
  And I view the all Scheduled Deliveries list on the admin portal
  And  I search the delivery using "Pickup Reference"
  Then I should be able to see the respective bungii with the below status
    | Status           |
    | Assigning Driver(s)|

  #CORE-2052 : To verify research does not happen when admin changes from duo to solo when bungii was accepted by driver
  @ready
  Scenario: To verify research does not happen when admin changes from duo to solo when bungii was accepted by driver
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999355 | Testcustomertywd_appleWashA Shah|
    When As a driver "Testdrivertywd_appledc_a_web Sundarb" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I change the delivery type from "Duo" to "Solo"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I wait for 2 minutes
    And I get the new "pickup reference"
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |

  #CORE-2052 : To verify search happens when admin changes from duo to solo when no driver has accepted
  @regression
  Scenario: To verify search happens when admin changes from duo to solo when no driver has accepted
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999356 | Testcustomertywd_appleWashB Shah|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I change the delivery type from "Duo" to "Solo"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I wait for 2 minutes
    And I get the new "pickup reference"
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
    |  Status |
    | Assigning Driver(s)|

  #CORE-3295:Verify that 'Assigning driver(s)' status with Loading icon is shown when it is searching for driver(s) on Schedule Deliveries screen
  @ready
  Scenario: Verify that 'Assigning driver(s)' status with Loading icon is shown when it is searching for driver(s) on Schedule Deliveries screen
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661088 | Testcustomertywd_appleMarkCK LutherCK|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I click on the filter link and should see "Assigning Driver(s)" checkbox displayed
    And I click on "APPLY" button
    When  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s)" state
    Then I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    When As a driver "Testdrivertywd_appledc_a_web Sundarb" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s)" state
    And I view the Live Deliveries list on  admin portal
    When  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s)" state
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    And I view the Live Deliveries list on  admin portal
    When  I search the delivery using "Pickup Reference"
    Then I should see the message "No deliveries found." displayed
    And I view the all Scheduled Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    And I click on "Edit" link beside scheduled bungii
    When I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Duo: Driver not found - one driver" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    When I click on "Close" button
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    And I click on the filter link and should see "No Driver(s) Found" checkbox displayed
    And I click on "Filter" icon on "All Deliveries" Page
    When I select filter "Statuses" as "Admin Canceled"
    And I click on "Apply" button on "All Deliveries" page
    And  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Admin Canceled - No Driver(s) Found" state
    Then Revive button should be displayed beside the trip
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
    When I click on "Confirm" button on Revival Popup
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And  I search the delivery using "Pickup Reference"
    And I click on "Edit" button
    And I click on "Edit Trip Details" radiobutton
    And I edit the pickup address
    Then I change the pickup address to "New Country Lane"
    And I edit the drop off address
    Then I change the drop off address to "Bethesda Outdoor Pool"
    And I change the customer note to "New Note Added by Admin"
    And I change the delivery type from "Duo" to "Solo"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When I click on "Close" button
    And I wait for 2 minutes
    And I get the new pickup reference generated
    And  I search the delivery using "Pickup Reference"
    And I click on the "Notes & History" link beside scheduled bungii for "Schedule Deliveries"
    And I click on "History" button
    Then I should see the changes done by admin
      | Event                    |                                Old Value                            |   New Value  |
      | Pickup Address Change    |   	Western Avenue Street Chevy Chase Maryland United States 2081  | New Country Lane Hickory Ridge Columbia Maryland United States 21044   |
      | Dropoff Address Change   |   	Unnamed Road Street Street Washington United States 20015      | 6300 Hillandale Road Bethesda Maryland United States 20815    |
      | Duo To Solo              |   	DUO                                                            | SOLO                                                               |

  #CORE-3295:Verify stop searching should change the status to Assigning drivers without loading icon
  @regression
  Scenario: Verify stop searching should change the status to Assigning drivers without loading icon
    When I request "duo" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661089 | Testcustomertywd_appleMarkCL LutherCL|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s)" state
    Then I should be able to see the respective bungii with the below status
      | Status           |
      | Assigning Driver(s)|
    When I click on the "Delivery Details" button from the dropdown
    Then I stop searching driver
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Assigning Driver(s) with no loader" state
    And As a driver "Testdrivertywd_appledc_a_web TestdriverE" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    When  I search the delivery using "Pickup Reference"
    And I click on "Edit" button
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    When I click on "Close" button
    And I wait for 2 minutes
    And I view the Live Deliveries list on  admin portal
    And I get the new pickup reference generated
    When  I search the delivery using "Pickup Reference"
    And I click on "Edit" button
    When I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I select "Duo: Driver not found - both driver" from the "Cancellation Reason" dropdown
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    When I click on "Close" button
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The delivery should be in "Admin Canceled - No Driver(s) Found" state

#CORE-3381:To verify that customer trips can be revived after admin cancels
  @regression
  Scenario:To verify that customer trips can be revived after admin cancels
  When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
    | Bungii Time   | Customer Phone | Customer Name |
    | NEXT_POSSIBLE | 8877661062 | Testcustomertywd_appleMarkBK LutherBK|
   And As a driver "Testdrivertywd_appledc_a_drvM WashingtonM" perform below action with respective "Solo Scheduled" Delivery
    | driver1 state|
    |Accepted |
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
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
    Then The Delivery List page should display the delivery in "Admin Canceled" state
#   Core-4307: Verify the history is displayed for ADMIN canceled delivery- Driver accepted
    And I click on the dropdown beside scheduled bungii
    When I click the "Notes & History On Completed Delivery" link
    And I click on "History"
    And I should be able to see "admin cancelled event - driver accepted"
    And I close the Note
    When I view the Completed Deliveries on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then Revive button should be displayed beside the trip
    When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
    When I click on "Cancel" button on Revival Popup
    Then Revive button should be displayed beside the trip
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	When I click on "Confirm" button on Revival Popup
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
    |  Status |
    | Assigning Driver(s)|
   When I click on the "Delivery Details" button from the dropdown
    Then The pickup reference should be changed to the new pickup reference
    And I view All Deliveries list on the admin portal
    And I search the delivery using old pickup reference
    Then The Delivery List page should display the delivery in "Admin Canceled" state
  And I get the latest "pickup Reference"
  And I view the all Scheduled Deliveries list on the admin portal
  And  I search the delivery using "Pickup Reference"
  When I click on the "Edit" button from the dropdown
  And I click on "Edit Trip Details" radiobutton
  And I click on "Add Driver" and add "Testdrivertywd_appledc_a_drvN WashingtonM" driver
  And I click on "Verify" button on Edit Scheduled bungii popup
  And I click on "Save" button on Edit Scheduled bungii popup
  Then "Bungii Saved!" message should be displayed
  When I wait for 2 minutes
  And I view the all Scheduled Deliveries list on the admin portal
  And I get the latest "pickup Reference"
  And  I search the delivery using "Pickup Reference"
  Then I should be able to see the respective bungii with the below status
    |  Status |
    | Scheduled |
  And As a driver "Testdrivertywd_appledc_a_drvN WashingtonM" perform below action with respective "Solo Scheduled" Delivery
    | driver1 state|
    | Enroute  |
    | Arrived |
    | Loading Item |
    | Driving To Dropoff |
    | Unloading Item |
    | Bungii Completed |
    When I wait for 2 minutes
  And I view All Deliveries list on the admin portal
  And I search the delivery of Customer and view it
  When I click on "ISSUE REFUND" button
  Then The "Issue Refund" section should be displayed
  When I select "Partial Refund" radio button
  When I update "Earnings" as "10.00" dollars
  And I enter "Customer Refund Amount" as "5" dollars
  When I update "Earnings" as "10.00" dollars
  And I enter "Bungii Internal Notes" as "Internal Note"
  When I enter "Notes" as "Driver Note"
  And I click on "Continue" button on Issue Refund popup
  Then I should see "Issue Refund - Confirm Details" popup
  And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
  And I should see breakdown of Before and After Refund earnings
  And I should see Bungii Internal Note
  When I select "Are you sure you want to proceed with refund request ?" checkbox
  And I click on "Process Refund" button on Issue Refund popup
  Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
  When I click on "OK" button
  And I wait for 1 minutes
  And The amount should be "Refunded" and in "voided" state

    #CORE-3381:To verify that admin can fully refund completed trips which were revived
  @regression
  Scenario:To verify that admin can fully refund completed trips which were revived
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661063 | Testcustomertywd_appleMarkBL LutherBL|
    And As a driver "Testdrivertywd_appledc_a_drvM WashingtonM" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      |Accepted |
      | Enroute  |
    And I wait for 2 minutes
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    And I click on "Edit" link beside live delivery
    And I click on "Edit Delivery Status" radiobutton
    And I click on "Delivery Canceled" radiobutton
    And I click on "UPDATE BUNGII" button
    Then The "Pick up has been successfully canceled." message should be displayed for live delivery
    And I wait for 2 minutes
    And I view the Deliveries list on the admin portal
    Then The Delivery List page should display the delivery in "Driver Canceled" state
    Then Revive button should be displayed beside the trip
    When I click on "Revive" button
    Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
    When I click on "Confirm" button on Revival Popup
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    And As a driver "Testdrivertywd_appledc_a_drvO WashingtonO" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
      | Bungii Completed |
    And I wait for 2 minutes
    And I view the Deliveries list on the admin portal
    And I search the delivery of Customer and view it
    When I click on "ISSUE REFUND" button
    Then The "Issue Refund" section should be displayed
    When I select "Complete Refund" radio button
    When I update "Earnings" as "10.00" dollars
    Then I should see Customer Refund Amount and Driver Earnings
    When I enter "Bungii Internal Notes" as "Internal Note"
    When I enter "Notes" as "Driver Note"
    And I click on "Continue" button on Issue Refund popup
    Then I should see "Issue Refund - Confirm Details" popup
    And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
    And I should see breakdown of Before and After Refund earnings
    And I should see Bungii Internal Note
    And I should see Bungii Driver Note
    When I select "Are you sure you want to proceed with refund request ?" checkbox
    And I click on "Process Refund" button on Issue Refund popup
    Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
    When I click on "OK" button
    And I wait for 1 minutes
    And The amount should be "Refunded" and in "voided" state

  #CORE-2584:To verify the customer duo delivery marked as Payment successful from payment unsuccessful
  @ready
  Scenario:To verify the customer duo delivery marked as Payment successful from payment unsuccessful
    When I request "Duo" Bungii as a customer in "phoenix" geofence
      | Bungii Time   | Customer Phone | Customer Name                         | Customer Password |
      |  3_DAY_LATER | 8877661100     | Testcustomertywd_appleMarkCW LutherCW| Cci12345          |
    And As a driver "Testdrivertywd_appleph_a_drvaw Phoenix_aw" and "Testdrivertywd_appleph_a_drvax Phoenix_ax" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I wait for 2 minutes
    When I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The timezone should be "MST-Phoenix" on "Scheduled Deliveries" page
    When I click on the "Edit" button from the dropdown
    And I click on "Remove driver(s) and re-search" radiobutton
    And I select the first driver
    And I click on "Remove Driver" button
    And I click on "Research" button
    Then Pickup should be unassigned from the driver
    And I wait for 2 minutes
    And I get the new pickup reference generated
    When I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I should see the trip scheduled for "3" days ahead
    When I click on the "Edit" button from the dropdown
    And I click on "Edit Trip Details" radiobutton
    And I change the trip delivery date to "0" days ahead from today
    And I select reason as "Partner initiated"
    And I click on "Add Driver" and add "Testdrivertywd_appleph_a_drvay Phoenix_ay" driver
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for 2 minutes
    And I get the new pickup reference generated
    When I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    And The scheduled trip date should be changed to the new date
    When As a driver "Testdrivertywd_appleph_a_drvax Phoenix_ax" and "Testdrivertywd_appleph_a_drvay Phoenix_ay" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state | driver2 state |
      | Enroute       | Enroute       |
    And I wait for 2 minutes
    And I view the Live Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The timezone should be "MST-Phoenix" on "Live Deliveries" page
    And I should be able to see the respective bungii with the status
      | Status       |
      | Trip Started |
    And I click on "Edit" button
    And I click on "Edit Trip Details" radiobutton
    And I edit the pickup address
    Then I change the pickup address to "15225 North 71st Drive, Peoria, AZ"
    And I edit the drop off address
    Then I change the drop off address to "17334 North 63rd Avenue"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    When As a driver "Testdrivertywd_appleph_a_drvax Phoenix_ax" and "Testdrivertywd_appleph_a_drvay Phoenix_ay" perform below action with respective "Duo Scheduled" partner portal trip
      | driver1 state    | driver2 state     |
      |Arrived           |Arrived            |
      |Loading Item      |Loading Item       |
      |Driving To Dropoff|Driving To Dropoff |
      |Unloading Item    |Unloading Item     |
      |Bungii Completed  |Bungii Completed   |
    And I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The timezone should be "MST-Phoenix" on "All Deliveries" page
    And The "All Deliveries" should be in "Payment Pending" state
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    When I click on the "Change Payment status" button from the dropdown
    And the "Are you sure, you want to change the payment status?" message is displayed
    Then I should see all the information in the change payment status modal
    And I click on "Confirm Change Payment Status" button
    And I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The "All Deliveries" should be in "Payment Successful" state
    Then The delivery status should be in "Payment Successful" state in db
    Then I should see the change status link "Not Displayed"
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then The "Issue Refund" "button" should not be displayed
    Then I should see "Accessorial Charges" section displayed
    When I add following accessorial charges and save it
      | Amount   | Fee Type                     | Comment                                    | Driver Cut |
      |  18      | Additional Mileage           | Charges due to Additional Mileage          | 1          |
      |  12      | Additional Weight / Pallet   | Charges due to Additional Weight / Pallet  | 2          |
      |  14      | Cancelation                  | Charges due to Cancelation                 | 3          |
      |  18      | Customer Rejected / Returned | Charges due to Customer Rejected / Returned| 4          |
      |  10      | Excess Wait Time             | Charges due to Excess wait                 | 2          |
      |  20.50    | Limited Access              | Charges due to Limited Access              | 4.5        |
      |  25.65   | Mountainous                  | Charges due to mountainous reason          | 10         |
      |  100     | Other                        | Charges due to other reasons               | 20         |
    And I should see following details in the Accessorial charges section
      | Additional Mileage  | Additional Weight / Pallet| Cancelation| Customer Rejected / Returned| Excess Wait Time | Limited Access | Mountainous | Other | Total   |
      | $18                 | $12                       | $14        |  $18                        |$10               | $20.50         | $25.65      | $110  | $218.15 |


#CORE-2584:To verify the Partner geofence based solo delivery marked as Payment successful from payment unsuccessful
  @ready
  Scenario:To verify the Partner geofence based solo delivery marked as Payment successful from payment unsuccessful
    And I set the pickup address for "Equip-bid in phoenix geofence"
    When I request Partner Portal "SOLO" Trip for "Equip-bid" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |phoenix| NEXT_POSSIBLE | 8877661101 | Testcustomertywd_appleMarkCX LutherCX|
    And As a driver "Testdrivertywd_appleph_a_drvaw Phoenix_aw" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      |Arrived         |
      |Loading Item     |
      |Driving To Dropoff |
      |Unloading item    |
      |Bungii Completed  |
    And I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The "All Deliveries" should be in "Payment Pending" state
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    And I click on "Transaction History" button
    Then I should see the transaction charges "before" changing delivery Status
    And I click on "Close" button
    And I click on "OK Delivery Details Page" button
    And  I search the delivery using "Pickup Reference"
    When I click on the "Change Payment status" button from the dropdown
    And the "Are you sure, you want to change the payment status?" message is displayed
    Then I should see all the information in the change payment status modal
    And I click on "Confirm Change Payment Status" button
    And I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The "All Deliveries" should be in "Payment Successful" state
    Then The delivery status should be in "Payment Successful" state in db
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then The "Issue Refund" "button" should not be displayed
    And I click on "Transaction History" button
    Then I should see the transaction charges "after" changing delivery Status
    And I click on "Close" button
    Then I should see "Accessorial Charges" section displayed
    When I add following accessorial charges and save it
      | Amount   | Fee Type         | Comment                           | Driver Cut |
      |  10      | Excess Wait Time | Charges due to Excess wait        | 2          |
      |   20.5   | Cancelation      | Charges due to Cancelation        | 4.5        |
      |  25.65   | Mountainous      | Charges due to mountainous reason | 10       |
      |  100     | Other            | Charges due to other reasons      | 20         |
    And I should see following details in the Accessorial charges section
      | Excess Wait Time | Cancelation | Mountainous | Other | Total   |
      | $10              | $20.5       | $25.65      | $100  | $156.15 |

 #CORE-2826:To verify whether text search displays results matching the contains criteria on Deliveries pages
 # Using last name to search delivery not pushed to prod as core 2826 is rnd ticket
  @ready
  Scenario:To verify whether text search displays results matching the contains criteria on Deliveries pages
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 8877661103 | Testcustomertywd_appleMarkCZ LutherCZ|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery based on customer "first name"
    Then The "Scheduled Deliveries" should be in "Assigning Driver(s)" state
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    And As a driver "Testdrivertywd_appleks_a_drvbi Kansas_bi" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state |
      | Accepted      |
      | Enroute      |
    And I wait for 2 minutes
    And I view the Live Deliveries list on the admin portal
    And I search the delivery based on customer "first name with space in front and back"
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Trip Started|
    And As a driver "Testdrivertywd_appleks_a_drvbi Kansas_bi" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state |
      | Driver Canceled |
    And I wait for 2 minutes
    And I view the Deliveries list on the admin portal
    When I change filter to "The Past Day" on All deliveries
    And I search the delivery based on customer "first name"
    Then The "All Deliveries" should be in "Driver Canceled" state
    When I change filter to "The Past Week" on All deliveries
    And I search the delivery based on customer "first name"
    Then The "All Deliveries" should be in "Driver Canceled" state
    When I change filter to "The Past 4 Weeks" on All deliveries
    And I search the delivery based on customer "first name"
    Then The "All Deliveries" should be in "Driver Canceled" state
    When I change filter to "The Past 3 Months" on All deliveries
    And I search the delivery based on customer "first name"
    Then The "All Deliveries" should be in "Driver Canceled" state
    When I change filter to "The Beginning of Time" on All deliveries
    And I search the delivery based on customer "first name with space in front and back"
    Then The "All Deliveries" should be in "Driver Canceled" state

  #CORE-4009: Date filter in Live Deliveries page of AP for Customer & Partner portal trips
  @ready
  Scenario: To verify the Date filter in Live Deliveries page of Admin Portal
    When I view the Live Deliveries list on the admin portal
    Then The "Date Filter" is set to "All" by default
    When I click on "Date Filter" button on the "Live deliveries" page
    Then  I should see All Filter Options in dropdown
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                       |
      | NEXT_POSSIBLE | 8877661141     | Testcustomertywd_appleMarkEL LutherEL|
    And I wait for 2 minutes
    And As a driver "Testdrivertywd_appleks_a_drvbq Kansas_bq" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute      |
    And I wait for 2 minutes
    When I view the Live Deliveries list on  admin portal
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Today" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Trip Started |
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Tomorrow" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should see the message "No deliveries found." displayed
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "All" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Trip Started |
    When I request Partner Portal "Solo" Trip for "Equip-bid" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |kansas| NEXT_POSSIBLE | 8877661145 | Testcustomertywd_appleMarkEP LutherEP|
    And I wait for 2 minutes
    When As a driver "Testdrivertywd_appleks_a_drvbr Kansas_br" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute      |
    When I navigate to "Admin" portal
    And I view the Live Deliveries list on  admin portal
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Today" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Trip Started |
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Tomorrow" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should see the message "No deliveries found." displayed
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "All" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Trip Started |
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                       |
      |  1_DAY_LATER | 8877661142   | Testcustomertywd_appleMarkEM LutherEM |
    And I wait for 2 minutes
    When I view the Live Deliveries list on  admin portal
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Today" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should see the message "No deliveries found." displayed
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Tomorrow" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Assigning Driver(s) |
    When I change filter to "All" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Assigning Driver(s) |
    When I request Partner Portal "Solo" Trip for "Equip-bid" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |kansas| 1_DAY_LATER | 8877661146 | Testcustomertywd_appleMarkEQ LutherEQ|
    And I wait for 2 minutes
    When I navigate to "Admin" portal
    And I view the Live Deliveries list on  admin portal
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Today" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should see the message "No deliveries found." displayed
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Tomorrow" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Assigning Driver(s) |
    When I change filter to "All" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Assigning Driver(s) |
    When I request Partner Portal "Solo" Trip for "Equip-bid" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |kansas| 4_DAY_LATER | 8877661147 | Testcustomertywd_appleMarkER LutherER|
    And I wait for 2 minutes
    When I navigate to "Admin" portal
    And I view the Live Deliveries list on  admin portal
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Today" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should see the message "No deliveries found." displayed
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Tomorrow" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should see the message "No deliveries found." displayed
    When I change filter to "All" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Assigning Driver(s) |

# Driver with Same day payment setting:9049840342
# Driver with 2x payment setting:9049830013
  @ready
  #CORE-5251 : Verify that admin can add accessorial charge for Payment Successful customer Duo Scheduled Deliveries
  Scenario:Verify that 'i' icon is displayed on delivery details page for those drivers who has selected same day payment
    When I request "duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661144 | Testcustomertywd_appleMarkEO LutherEO|
    And I wait for 2 minutes
    When I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    When I view the delivery details
    And I check if "i" icon is displayed
    When As a driver "Testdrivertywd_appledc_a_drvaa Washingtonaa" and "Testdrivertywd_appledc_a_drvm Driver" perform below action with respective "Duo Scheduled" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
      | Enroute       | Enroute       |
    And I wait for 2 minutes
    And I view the Live Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    When I click on the "Delivery Details" button from the dropdown
    And I check if "i" icon is displayed
    When As a driver "Testdrivertywd_appledc_a_drvaa Washingtonaa" and "Testdrivertywd_appledc_a_drvm Driver" perform below action with respective "Duo Scheduled" trip
      | driver1 state | driver2 state |
      | Arrived       | Arrived       |
      | Loading Items | Loading Items |
      | Driving To Drop-off | Driving To Drop-off  |
      | Unloading items     | Unloading items      |
      |Bungii Completed  |Bungii Completed  |
    And I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    #Core-4556: Verify status of the trip is payment successful after driver completes the trip: Same day payment (Customer card)
    Then The "All Deliveries" should be in "Payment Successful" state
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I check if "same day payment i" icon is displayed
    #CORE-5251 :Verify correct disbursement type set in db for driver with same day & 2x payment setting
    #CORE-4730:Verify refund changing driver earrings for duo trip with one driver payment setting as weekly and other as Same day
    When I click on "ISSUE REFUND" button
    Then The "Issue Refund" section should be displayed
    When I select "Complete Refund" radio button
    When I update "Earnings" as "10.00" dollars
    When I enter "Notes" as "Driver Note"
    And I check "Same for 2nd driver"
    Then I should see Customer Refund Amount and Driver Earnings
    When I enter "Bungii Internal Notes" as "Internal Note"
    When I enter "Notes" as "Driver Note" for both drivers
    And I click on "Continue" button on Issue Refund popup
    Then I should see "Issue Refund - Confirm Details" popup
    And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
    And I should see Bungii Internal Note
    And I should see Bungii Driver Note for both drivers
    When I select "Are you sure you want to proceed with refund request ?" checkbox
    And I click on "Process Refund" button on Issue Refund popup
    Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
    When I click on "OK" button
    Then I verify correct "Disbursement type" is set in db
    When I add following accessorial charges for Duo trip and save it
      | Amount   | Fee Type                     | Comment                                    | Driver1 Cut| Driver2 Cut|
      |  25      | Additional Mileage           | Charges due to Additional Mileage          | 5          | 5          |
      |  15      | Additional Weight / Pallet   | Charges due to Additional Weight / Pallet  | 6          | 7          |
      |  18      | Cancelation                  | Charges due to Cancelation                 | 3          | 6          |
      |  19      | Customer Rejected / Returned | Charges due to Customer Rejected / Returned| 4          | 3          |
      |  10      | Excess Wait Time             | Charges due to Excess wait                 | 2          | 3          |
      |  30.5    | Limited Access               | Charges due to Limited Access              | 4          | 7          |
    #CORE-5467: Verify that Added accessorial charges are displyaed under accessorial charge section when one driver cut is 0
      |  45.65   | Mountainous                  | Charges due to mountainous reason          | 10         | 0          |
      |  80      | Other                        | Charges due to other reasons               | 0          | 6          |
    Then I should see the following fee type displayed
      | Fee Type         			 |
      | Additional Mileage           |
      | Additional Weight / Pallet   |
      | Cancelation                  |
      | Customer Rejected / Returned |
      | Excess Wait Time 			 |
      | Limited Access               |
      | Mountainous                  |
      | Other                        |
    And I should see following details in the Accessorial charges section
      | Additional Mileage | Additional Weight / Pallet | Cancelation |Customer Rejected / Returned |Excess Wait Time | Limited Access | Mountainous | Other | Total   |
      | $25                | $15                        | $18         | $19                         | $10             | $30.5          | $45.65      | $80   | $243.15 |
    Then I verify correct "Payment transaction type" is set in db for accessorial charge


  #CORE-4520
  @ready
  Scenario: Verify that admin received outside secondary polyline email for partner delivery
    When I request "duo" Bungii as a customer in "newjersey" geofence
      | Bungii Time   | Customer Phone | Customer Name                  | Polyline  |
      | NEXT_POSSIBLE | 9999995001     | Testcustomertywd_appleweb CustZ| Secondary |
    And I wait for "2" mins
    And I view the all Scheduled Deliveries list on the admin portal
    And I search the delivery based on customer "first name"
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s)|
    And I note the "Trip details"
    Then Admin should receive the "Delivery scheduled beyond secondary polyline" email

#  Core-4367 Verify "Admin Cancelled" trips for driver(s) with Branch app wallet(customer trip)
    @ready
    Scenario: Verify Admin Cancelled trips for driver(s) with Branch app wallet(customer trip)
      When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
        | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
        | NEXT_POSSIBLE | 8877661150     | Testcustomertywd_appleMarkEU LutherEU | Cci12345          |
      And As a driver "Testdrivertywd_appledv_b_mattJ Stark_dvOnEJ" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Accepted      |
      And I wait for 2 minutes
      And I view the all Scheduled Deliveries list on the admin portal
      And  I search the delivery using "Pickup Reference"
      When I click on the "Edit" button from the dropdown
      And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
      And I enter cancellation fee and Comments
      And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
      And I click on "Submit" button
      Then The "Pick up has been successfully canceled." message should be displayed
      Then I check "driver not paid status" in db

 #4845 Verify Export All Records button is enabled when there is no data present for any partners
  @ready
  Scenario: Verify Export All Records button is enabled when there is no data present for any partners
    When I click on "Deliveries > Rejected API Deliveries" Menu
    Then I should be directed to "Rejected API Deliveries Page"
    And I select partner to "E-API Walmart"
    Then I check if Export All Records button is disabled

  #CORE-4693 Deliveries gets stuck in "Trip completed" status when the deliveries are scheduled using customer card and their Drivers do not have Branch wallet
  @ready
  Scenario: To Verify the status of customer trip completed by driver(s) with no Branch app wallet
    When I request "Solo Ondemand" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                         | Customer Password |
      | NEXT_POSSIBLE | 8877661222     | Testcustomertywd_BppleMarkHO LutherHO | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_mattZ DenverZ" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state |
      | Accepted  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
      | Bungii Completed |
    And I wait for 2 minutes
    And I view All Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference"
    Then The "All Deliveries" should be in "Payment Successful" state