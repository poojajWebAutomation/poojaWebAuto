@web
Feature: Admin_Revival
  
  Background:
	Given I am logged in as Admin
	  
  @regression
  Scenario: Verify Admin can cancel the Revived Delivery
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999227     | Testcustomertywd_appleNewMN Customer|
	And As a driver "Testdrivertywd_appledc_a_drvr Driver" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	When I cancel bungii as a driver "Testdrivertywd_appledc_a_drvr Driver"
	And I wait for 2 minutes
	And I view the Deliveries list on the admin portal
	And I search the delivery of Customer
	Then Revive button should be displayed beside the trip
	When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	And I should see "Customer" details on review popup
	And I should not see "Pickup Origin" on review popup
	And "Confirm" and "Cancel" buttons should have background color "blue" and "white" respectively
	When I click on "Confirm" button on Revival Popup
	And I wait for 2 minutes
	And I view the all Scheduled Deliveries list on the admin portal
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Assigning Driver(s) |
	#	Core-4307: Verify the history is displayed for DRIVER canceled delivery  which is revived
	And I click on the dropdown beside scheduled bungii
	Then I should see the "History" underlined
	When I click the "Notes & History" link
	And I click on "History"
	Then I should be able to see "admin-revive-driver cancelled"
	And I close the Note
	And I click on "Edit" link beside scheduled bungii
	And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
	And I enter cancellation fee and Comments
	And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
	And I click on "Submit" button
	Then The "Pick up has been successfully canceled." message should be displayed
	And I wait for 2 minutes
	When I view the Deliveries list on the admin portal
	And I search the delivery of Customer
	Then The Delivery List page should display the delivery in "Admin Canceled" state
	#	Core-4307: Verify the history is displayed for ADMIN canceled delivery which is revived
	Then Revive button should be displayed beside the trip
	When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	And I should see "Customer" details on review popup
	And I should not see "Pickup Origin" on review popup
	And "Confirm" and "Cancel" buttons should have background color "blue" and "white" respectively
	When I click on "Confirm" button on Revival Popup
	And I wait for 2 minutes
	And I view the all Scheduled Deliveries list on the admin portal
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
		|  Status |
		| Assigning Driver(s) |
	And I click on the dropdown beside scheduled bungii
	When I click the "Notes & History" link
	And I click on "History"
	Then I should be able to see "admin-revive-admin cancelled"
	Then I should be able to see "history prior revival"


  @regression
	  #stable
  Scenario: Verify Admin can Assign driver and assigned driver can complete the Revived Delivery
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999225     | Testcustomertywd_appleNewML Customer|
	And As a driver "Testdrivertywd_appledc_a_drvs Driver" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	When I cancel bungii as a driver "Testdrivertywd_appledc_a_drvs Driver"

	And I wait for 2 minutes
	And I view the Deliveries list on the admin portal
	And I search the delivery of Customer
	Then Revive button should be displayed beside the trip
	When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	When I click on "Confirm" button on Revival Popup

	And I wait for 2 minutes
	And I view the all Scheduled Deliveries list on the admin portal
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Assigning Driver(s) |
	And I click on "Edit" link beside scheduled bungii
	When I click on "Edit Trip Details" radiobutton
	And I assign driver "Testdrivertywd_appledc_a_drvs Driver" for the trip
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	When I click on "Close" button

	And I refresh the page
	And As a driver "Testdrivertywd_appledc_a_drvs Driver" perform below action with respective "Solo Scheduled Researched" Delivery
	  | driver1 state|
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I wait for 2 minutes
	And I view All Deliveries list on the admin portal
	And I search the delivery of Customer
	Then The Delivery List page should display the delivery in "Payment Successful" state
  
  @regression
	  #stable
	#Verify Admin can Assign driver and admin can manually end bungii in loading item state of the Revived Delivery
	#CORE-3257 - Manually end bungii functionality is removed
	Scenario: Verify Admin can Assign driver to the Revived Delivery and Manually End Bungii link is Removed
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999226     | Testcustomertywd_appleNewMM Customer|
	And As a driver "Testdrivertywd_appledc_a_drvt Driver" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	When I cancel bungii as a driver "Testdrivertywd_appledc_a_drvt Driver"
	And I wait for 2 minutes
	And I view the Deliveries list on the admin portal
	And I search the delivery of Customer
	Then Revive button should be displayed beside the trip
	When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	When I click on "Confirm" button on Revival Popup
	And I wait for 2 minutes
	And I view the all Scheduled Deliveries list on the admin portal
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Assigning Driver(s) |
	And I click on "Edit" link beside scheduled bungii
	When I click on "Edit Trip Details" radiobutton
	And I assign driver "Testdrivertywd_appledc_a_drvt Driver" for the trip
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	When I click on "Close" button
	And I refresh the page
	And As a driver "Testdrivertywd_appledc_a_drvt Driver" perform below action with respective "Solo Scheduled Researched" Delivery
	  | driver1 state|
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	And I view the Live Deliveries list on the admin portal
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Loading Items |
	When I view the delivery details
	And I click on "Delivery Overview" button on delivery details
	Then the Bungii details is displayed successfully
	And Manually end bungii link is removed for live trips
#	And I click on "Manually End Bungii" link
#	And Enter the End Date and Time
#	And Click on "Calculate Cost" button
#	Then the amount is calculated and shown to admin
#	And Click on "Confirm" button
#	And I wait for 2 minutes
#	And I view the Deliveries list on the admin portal
#	Then The Delivery List page should display the delivery in "Payment Successful" state
	And As a driver "Testdrivertywd_appledc_a_drvt Driver" perform below action with respective "Solo Scheduled" Delivery
	| driver1 state|
	| Driving To Dropoff |
	| Unloading Item |
  	|Bungii Completed |


	@regression
  Scenario: Verify Revived button is displayed against the Driver canceled Revived Delivery
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999224     | Testcustomertywd_appleNewMK Customer|
	And As a driver "Testdrivertywd_appledc_a_drvat Washingtonat" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	When I cancel bungii as a driver "Testdrivertywd_appledc_a_drvat Washingtonat"
	And I wait for 2 minutes
	And I view the Deliveries list on the admin portal
	And I search the delivery of Customer
	Then Revive button should be displayed beside the trip
	When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	When I click on "Confirm" button on Revival Popup
	And I view the all Scheduled Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then I should be able to see the respective bungii with the below status
	  |  Status |
	  | Assigning Driver(s) |
	And I click on "Edit" link beside scheduled bungii
	When I click on "Edit Trip Details" radiobutton
	And I assign driver "Testdrivertywd_appledc_a_drvat Washingtonat" for the trip
	And I click on "VERIFY" button
	And the "Your changes are good to be saved." message is displayed
	Then I click on "SAVE CHANGES" button
	And the "Bungii Saved!" message is displayed
	When I click on "Close" button
	And I refresh the page
	And As a driver "Testdrivertywd_appledc_a_drvat Washingtonat" perform below action with respective "Solo Scheduled Researched" Delivery
	  | driver1 state|
	  | Enroute  |
	  | Arrived |
	When I cancel bungii as a driver "Testdrivertywd_appledc_a_drvat Washingtonat"
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer
	Then Revive button should be displayed beside the trip