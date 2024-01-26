@web
Feature: Admin_Delivery_Type_Change
  
  Background:
	Given I am logged in as Admin
	
  
  @regression
  Scenario: Verify Admin can Change delivery from Duo to Solo when one driver accepts the delivery
	When I request "duo" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999218     | Testcustomertywd_appleNewME Customer|
	When As a driver "Testdrivertywd_appledc_a_webkk Testdriverkk" and "Testdrivertywd_appledc_a_webll Testdriverll" perform below action with respective "Duo Scheduled" trip
	  | driver1 state | driver2 state |
	  | Accepted      | NA      |
	And I view the all Scheduled Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Assigning Driver(s) |
	And I click on "Edit" link beside scheduled bungii
	When I click on "Edit Trip Details" radiobutton
	And I change delivery type from "Duo to Solo"
	#When I remove control driver "Testdrivertywd_appledc_a_webkk Testdriverkk" on edit popup
 
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	When I click on "Close" button
	And I refresh the page
	And I get the new pickup reference generated
	And I view the all Scheduled Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below Delivery Type
	  | Type |
	  | Solo |
	And I should be able to see the respective bungii with the below status
	  |  Status |
	  | Scheduled |
	And I click on "Edit" link beside scheduled bungii
	Then Under Drivers: for Driver 1: "Testdrivertywd_appledc_a_webkk Testdriverkk" should be displayed
	And I should see Bungii Type as "SOLO" in "Research Scheduled Bungii" section
	When I click on "Cancel entire Bungii and notify driver(s)" radiobutton
	Then I should see Bungii Type as "SOLO" in "Cancel entire Bungii and notify driver(s)" section
	When I click on "Edit Trip Details" radiobutton
	Then Under Driver Details: for Driver 1: "Testdrivertywd_appledc_a_webkk Testdriverkk" should be displayed
	And I should see Bungii Type as "SOLO" in "Edit Trip Details" section

@regression
  #stable
  Scenario: Verify Admin can Change delivery from Duo to Solo when both driver accepts the delivery and one driver is removed
	When I request "duo" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999216     | Testcustomertywd_appleNewMC Customer|
	When As a driver "Testdrivertywd_appledc_a_webmm Testdrivermm" and "Testdrivertywd_appledc_a_webnn Testdrivernn" perform below action with respective "Duo Scheduled" trip
	  | driver1 state | driver2 state |
	  | Accepted      | Accepted      |
	And I view the all Scheduled Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Scheduled |
	And I click on "Edit" link beside scheduled bungii
	When I click on "Edit Trip Details" radiobutton
	And I change delivery type from "Duo to Solo"
	And I click on "VERIFY" button
	Then I should see "Solo trip cannot have 2 drivers" message on edit popup
	When I remove non control driver "Testdrivertywd_appledc_a_webnn Testdrivernn" on edit popup
	And I change delivery type from "Duo to Solo"
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	When I click on "Close" button
	And I refresh the page
	And I get the new pickup reference generated
	And I view the all Scheduled Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below Delivery Type
	  | Type |
	  | Solo |
	And I should be able to see the respective bungii with the below status
	  |  Status |
	  | Scheduled |
	And I click on "Edit" link beside scheduled bungii
	Then Under Drivers: for Driver 1: "Testdrivertywd_appledc_a_webmm Testdrivermm" should be displayed
	And I should see Bungii Type as "SOLO" in "Research Scheduled Bungii" section
	When I click on "Cancel entire Bungii and notify driver(s)" radiobutton
	Then I should see Bungii Type as "SOLO" in "Cancel entire Bungii and notify driver(s)" section
	When I click on "Edit Trip Details" radiobutton
	Then Under Driver Details: for Driver 1: "Testdrivertywd_appledc_a_webmm Testdrivermm" should be displayed
	And I should see Bungii Type as "SOLO" in "Edit Trip Details" section
  
  @regression
  Scenario: Verify Admin cannot Change delivery from Duo to Solo when non control driver starts the delivery and admin removes control driver
	When I request "duo" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999217     | Testcustomertywd_appleNewMD Customer|
	When As a driver "Testdrivertywd_appledc_a_weboo Testdriveroo" and "Testdrivertywd_appledc_a_webpp Testdriverpp" perform below action with respective "Duo Scheduled" trip
	  | driver1 state | driver2 state |
	  |  Accepted     | Enroute    |
	And I view the all Scheduled Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Scheduled |
	And I click on "Edit" link beside scheduled bungii
	When I click on "Edit Trip Details" radiobutton
	And I change delivery type from "Duo to Solo"
	And I click on "VERIFY" button
	Then I should see "Solo trip cannot have 2 drivers" message on edit popup
	When I remove control driver "Testdrivertywd_appledc_a_weboo Testdriveroo" on edit popup
 
	#And I change delivery type from "Duo to Solo"
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	When I click on "Close" button
	And I get the new pickup reference generated
	And As a driver "Testdrivertywd_appledc_a_webpp Testdriverpp" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I view All Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then The Delivery List page should display the delivery in "Payment Successful" state
  
  @regression
  Scenario: Verify Admin can Change delivery from Solo to Duo
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999219     | Testcustomertywd_appleNewMF Customer|
	
	And I view the all Scheduled Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Assigning Driver(s) |
	And I click on "Edit" link beside scheduled bungii
	When I click on "Edit Trip Details" radiobutton
	And I change delivery type from "Solo to Duo"
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	When I click on "Close" button
	And I refresh the page
	And I get the new pickup reference generated
	And I view the all Scheduled Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below Delivery Type
	  | Type |
	  | Duo |
	And the cost of the delivery should be doubled
	And I should be able to see the respective bungii with the below status
	  |  Status |
	  | Assigning Driver(s) |
	#CORE-4152:Verify that estimated delivery time is calculated correctly when admin edits changes type of customer trip from solo - duo
	When I click on the "Delivery Details" button from the dropdown
	And I click on "Delivery Overview" button on delivery details
	Then The "Scheduled Time" for customer delivery should match
	Then The "Estimated Delivery Time" for customer delivery should match
	And I view the all Scheduled Deliveries list on the admin portal
	When  I search the delivery using "Pickup Reference"
	And I click on "Edit" link beside scheduled bungii
	Then Under Drivers: for both Driver 1 and 2 : "Bungii driver is being searched" should be displayed
	And I should see Bungii Type as "DUO" in "Research Scheduled Bungii" section
	When I click on "Cancel entire Bungii and notify driver(s)" radiobutton
	Then I should see Bungii Type as "DUO" in "Cancel entire Bungii and notify driver(s)" section
	When I click on "Edit Trip Details" radiobutton
	Then Under Driver Details: for both Driver 1 and 2 : "Add driver below or Bungii driver search will continue" should be displayed
	And I should see Bungii Type as "DUO" in "Edit Trip Details" section
  
  @regression
  Scenario: Verify Admin can Change delivery from Duo to Solo
	When I request "duo" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999220     | Testcustomertywd_appleNewMG Customer|
	
	And I view the all Scheduled Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Assigning Driver(s) |
	And I click on "Edit" link beside scheduled bungii
	When I click on "Edit Trip Details" radiobutton
	And I change delivery type from "Duo to Solo"
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	When I click on "Close" button
	And I refresh the page
	And I get the new pickup reference generated
	And I view the all Scheduled Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below Delivery Type
	  | Type |
	  | Solo |
	And the cost of the delivery should be halved
	And I should be able to see the respective bungii with the below status
	  |  Status |
	  | Assigning Driver(s) |
	 #CORE-4152:Verify that estimated delivery time is calculated correctly when admin edits changes type of customer trip from duo-solo
	When I click on the "Delivery Details" button from the dropdown
	And I click on "Delivery Overview" button on delivery details
	Then The "Scheduled Time" for customer delivery should match
	Then The "Estimated Delivery Time" for customer delivery should match
	And I view the all Scheduled Deliveries list on the admin portal
	When  I search the delivery using "Pickup Reference"
	And I click on "Edit" link beside scheduled bungii
	Then Under Drivers: for Driver 1: "Bungii driver is being searched" should be displayed
	And I should see Bungii Type as "SOLO" in "Research Scheduled Bungii" section
	When I click on "Cancel entire Bungii and notify driver(s)" radiobutton
	Then I should see Bungii Type as "SOLO" in "Cancel entire Bungii and notify driver(s)" section
	When I click on "Edit Trip Details" radiobutton
	Then Under Driver Details: for Driver 1: "Add driver below or Bungii driver search will continue" should be displayed
	And I should see Bungii Type as "SOLO" in "Edit Trip Details" section

  @ready
  Scenario: Verify that driver est earning doesnt get change for the delivery having Promoter type Promocode when it changes from Solo to Duo and vice-versa
	  When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
		  | Bungii Time   | Customer Phone | Customer Password | Customer Name                       |
		  | NEXT_POSSIBLE | 9823741001     | Cci12345          | Testcustomertywd_applePromo Customer|
	  And I view the all Scheduled Deliveries list on the admin portal
	  And I wait for 2 minutes
	  And I search the delivery of Customer
	  Then I should be able to see the respective bungii with the below status
		  |  Status |
		  | Assigning Driver(s) |
	  When I view the delivery details
	  And I click on "Payment Details" button on delivery details
	  Then I note the Driver Est. Earnings for the search delivery
	  And I navigate back to Scheduled Deliveries
	  And I click on "Edit" link beside scheduled bungii
	  When I click on "Edit Trip Details" radiobutton
	  And I change delivery type from "Solo to Duo"
	  And I click on "VERIFY" button
	  And the "Your changes are good to be saved." message is displayed
	  Then I click on "SAVE CHANGES" button
	  And the "Bungii Saved!" message is displayed
	  When I click on "Close" button
	  And I refresh the page
	  And I get the new pickup reference generated
	  And I view the all Scheduled Deliveries list on the admin portal
	  And I wait for 2 minutes
	  And I search the delivery of Customer
	  Then I should be able to see the respective bungii with the below Delivery Type
		  | Type |
		  | Duo |
	  And the cost of the delivery should be zero
	  When I view the delivery details
	  Then I confirm that Driver Est. Earnings for the delivery remain same
	  And I navigate back to Scheduled Deliveries
	  And I click on "Edit" link beside scheduled bungii
	  When I click on "Edit Trip Details" radiobutton
	  And I change delivery type from "Duo to Solo"
	  And I click on "VERIFY" button
	  And the "Your changes are good to be saved." message is displayed
	  Then I click on "SAVE CHANGES" button
	  And the "Bungii Saved!" message is displayed
	  When I click on "Close" button
	  And I refresh the page
	  And I get the new pickup reference generated
	  And I view the all Scheduled Deliveries list on the admin portal
	  And I wait for 2 minutes
	  And I search the delivery of Customer
	  Then I should be able to see the respective bungii with the below Delivery Type
		  | Type |
		  | Solo |
	  And the cost of the delivery should be zero
	  When I view the delivery details
	  And I click on "Payment Details" button on delivery details
	  Then I confirm that Driver Est. Earnings for the delivery remain same
  	  And I navigate back to Scheduled Deliveries
	  Then I should be able to see the respective bungii with the below status
		  |  Status |
		  | Assigning Driver(s) |

