@web
@new
Feature: Admin_Refund
  
  Background:
	Given I am logged in as Admin
	

  @regression
  Scenario: Verify Partial Refund for Solo Scheduled Delivery
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999602     | Testcustomertywd_appleNewB Customer|
	And As a driver "Testdrivertywd_appledc_a_drve Driver" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	Then The "Issue Refund" section should be displayed
	When I select "Partial Refund" radio button
	And I enter "Customer Refund Amount" as "5" dollars
	And I enter "Bungii Internal Notes" as "Internal Note"
	And I click on "Continue" button on Issue Refund popup
	Then I should see "Issue Refund - Confirm Details" popup
     And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
	And I should see breakdown of Before and After Refund earnings
	And I should see Bungii Internal Note
	When I select "Are you sure you want to proceed with refund request ?" checkbox
	And I click on "Process Refund" button on Issue Refund popup
	Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
	When I click on "OK" button
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
	#CORE-2507: Verify Change status icon is not displayed for trip which was partially refunded prior status change
	#logged issue with ticket number CORE-4452
	When I view All Deliveries list on the admin portal
    And I search the delivery using "Pickup Reference"
	Then I should see the change status link "Not Displayed"
  
  @regression
  Scenario: Verify Complete Refund for Solo Scheduled Delivery and Full Driver Payment
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999603     | Testcustomertywd_appleNewC Customer|
	And As a driver "Testdrivertywd_appledc_a_drvf Driver" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	Then The "Issue Refund" section should be displayed
	When I select "Complete Refund" radio button
	Then I should see Customer Refund Amount and Driver Earnings
	When I enter "Bungii Internal Notes" as "Internal Note"
	And I click on "Continue" button on Issue Refund popup
	Then I should see "Issue Refund - Confirm Details" popup
	And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
	And I should see breakdown of Before and After Refund earnings
	And I should see Bungii Internal Note
	When I select "Are you sure you want to proceed with refund request ?" checkbox
	And I click on "Process Refund" button on Issue Refund popup
	Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
	When I click on "OK" button
   #CORE-4730:Verify customer full refund without changing driver earrings should be taken by weekly job if driver has weekly payment settings
	And I check the status for "weekly payment" of "Disbursement type" in db
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
   #CORE-2507: Verify Change status icon is not displayed for trip which was already refunded prior status change
	#logged issue with ticket number CORE-4452
	When I view All Deliveries list on the admin portal
	And I search the delivery using "Pickup Reference"
	Then I should see the change status link "Not Displayed"

  
  @regression
  Scenario: Verify Complete Refund for Solo Scheduled Delivery and partial Driver payment
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999604     | Testcustomertywd_appleNewD Customer|
	And As a driver "Testdrivertywd_appledc_a_drvg Driver" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
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
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
#	  Core-2748: Refund receipt emails sent to Bungii Admin email.
	And Customer should receive "Bungii: Refund Confirmation" email  
	And Admin should receive "Bungii Refund Receipt for customer" email


  @regression
  Scenario: Verify Close Reset and Go Back on Issue Refund Popup
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999605     | Testcustomertywd_appleNewE Customer|
	And As a driver "Testdrivertywd_appledc_a_drvh Driver" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	And I enter "Customer Refund Amount" as "5.01" dollars
	When I update "Earnings" as "5.01" percentage
	When I enter "Bungii Internal Notes" as "Internal Note"
	When I enter "Notes" as "Driver Note"
	And I click on "Continue" button on Issue Refund popup
	And I click on "GO BACK" button
	Then The "Issue Refund" section should be displayed
	And I click on "RESET" button
	Then the values should be reverted to origional value
	And I click on "Close icon" button
	Then The "Issue Refund" section should not be displayed


#This scenario is no longer valid as manually end bungii link is removed for CORE-3257. Can be reused when coding CORE-3257(Edit Delivery Status - Payment Complete)
#  Scenario: Verify Complete Refund for manually ended solo scheduled bungii and partial payment for driver
#	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
#	  | Bungii Time   | Customer Phone | Customer Name                  |
#	  | NEXT_POSSIBLE | 9999999606     | Testcustomertywd_appleNewF Customer|
#	And As a driver "Testdrivertywd_appledc_a_drvi Driver" perform below action with respective "Solo Scheduled" Delivery
#	  | driver1 state|
#	  |Accepted |
#	  | Enroute  |
#	  | Arrived |
#	  | Loading Item |
#	And I view the Live Deliveries list on the admin portal
#	Then I should be able to see the respective bungii with the below status
#	  |  Status |
#	  | Loading Items |
#	When I view the delivery details
#	Then the Bungii details is displayed successfully
#	And I click on "Manually End Bungii" link
#	And Enter the End Date and Time
#	And Click on "Calculate Cost" button
#	Then the amount is calculated and shown to admin
#	And Click on "Confirm" button
#	And I wait for "2" mins
#	And I view the Deliveries list on the admin portal
#	Then The Delivery List page should display the delivery in "Payment Successful" state
#	And I search the delivery of Customer and view it
#	When I click on "ISSUE REFUND" button
#	Then The "Issue Refund" section should be displayed
#	When I select "Complete Refund" radio button
#	When I update "Earnings" as "10.00" dollars
#	Then I should see Customer Refund Amount and Driver Earnings
#	When I enter "Bungii Internal Notes" as "Internal Note"
#	When I enter "Notes" as "Driver Note"
#	And I click on "Continue" button on Issue Refund popup
#	Then I should see "Issue Refund - Confirm Details" popup
#	And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
#	And I should see breakdown of Before and After Refund earnings
#	And I should see Bungii Internal Note
#	When I select "Are you sure you want to proceed with refund request ?" checkbox
#	And I click on "Process Refund" button on Issue Refund popup
#	Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
#	When I click on "OK" button
#	And I search the delivery of Customer and view it
#	Then The "Issue Refund" button should not be displayed
  
  @regression
  Scenario: Verify Issue Refund button is not displayed for Customer Canceled Delivery
	  When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
		| Bungii Time   | Customer Phone | Customer Name                  |
		| NEXT_POSSIBLE | 9999999607     | Testcustomertywd_appleNewG Customer|
	  And I view the Scheduled Deliveries list on the admin portal
	  Then I should be able to see the respective bungii with the below status
		|  Status |
		|Assigning Driver(s)|
	  When I cancel bungii as a customer "Testcustomertywd_appleNewG Customer" with phone number "9999999607"
	When I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	  Then The "Issue Refund" button should not be displayed
  
  @regression
  Scenario: Verify Issue Refund button is not displayed for Admin Canceled Delivery
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999608     | Testcustomertywd_appleNewH Customer|
	  And I view the Scheduled Deliveries list on the admin portal
	  Then I should be able to see the respective bungii with the below status
		|Status |
		|Assigning Driver(s)|
	  When I click on "Edit" link beside scheduled bungii
	  And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
	  And I enter cancellation fee and Comments
	  And I click on "Submit" button
	  Then The "Pick up has been successfully canceled." message should be displayed
	  When I click on "Close" button
	And I wait for 2 minutes
	And I view All Deliveries list on the admin portal
	And I search the delivery of Customer
	Then The Delivery List page should display the delivery in "Admin Canceled" state
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
  
  @regression
      Scenario: Verify Issue Refund button is not displayed for Driver Canceled Delivery
		When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
		  | Bungii Time   | Customer Phone | Customer Name                  |
		  | NEXT_POSSIBLE | 9999999609     | Testcustomertywd_appleNewI Customer|
		And As a driver "Testdrivertywd_appledc_a_drvj Driver" perform below action with respective "Solo Scheduled" Delivery
		  | driver1 state|
		  |Accepted |
		  | Enroute  |
		When I cancel bungii as a driver "Testdrivertywd_appledc_a_drvj Driver"
	    And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
		Then The "Issue Refund" button should not be displayed
  
  @regression
  Scenario: Verify Complete Refund for Solo Ondemand Delivery and partial Driver payment
	When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999990     | Testcustomertywd_appleNewJ Customer|
	And As a driver "Testdrivertywd_appledc_a_drvk Driver" perform below action with respective "Solo Ondemand" Delivery
	  | driver1 state|
	  | Enroute  |
	  | Arrived |
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
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
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
  
  @regression
  Scenario: Verify Complete Refund for Duo Delivery and partial Driver payment
	When I request "duo" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name |
	  | NEXT_POSSIBLE | 9999999991 | Testcustomertywd_appleNewK Customer|
	When As a driver "Testdrivertywd_appledc_a_drvl Driver" and "Testdrivertywd_appledc_a_drvm Driver" perform below action with respective "Duo Scheduled" trip
	  | driver1 state | driver2 state |
	  | Bungii Completed      | Bungii Completed      |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	Then The "Issue Refund" section should be displayed
	When I select "Complete Refund" radio button
	When I update "Earnings" as "10.00" dollars
	Then I should see Customer Refund Amount and Driver Earnings
	When I enter "Bungii Internal Notes" as "Internal Note"
	When I enter "Notes" as "Driver Note" for both drivers
	  And I check "Same for 2nd driver"
	And I click on "Continue" button on Issue Refund popup
	Then I should see "Issue Refund - Confirm Details" popup
	And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
	And I should see breakdown of Before and After Refund earnings for both driver
	And I should see Bungii Internal Note
	And I should see Bungii Driver Note for both drivers
	When I select "Are you sure you want to proceed with refund request ?" checkbox
	And I click on "Process Refund" button on Issue Refund popup
	Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
	When I click on "OK" button
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
 
	@regression
  Scenario: Verify Complete Refund for Duo Delivery and complete Driver payment
	When I request "duo" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name |
	  | NEXT_POSSIBLE | 9999999992 | Testcustomertywd_appleNewL Customer|
	When As a driver "Testdrivertywd_appledc_a_drvn Driver" and "Testdrivertywd_appledc_a_drvo Driver" perform below action with respective "Duo Scheduled" trip
	  | driver1 state | driver2 state |
	  | Bungii Completed      | Bungii Completed      |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	Then The "Issue Refund" section should be displayed
	When I select "Complete Refund" radio button
	And I check "Same for 2nd driver"
	Then I should see Customer Refund Amount and Driver Earnings
	When I enter "Bungii Internal Notes" as "Internal Note"
	And I click on "Continue" button on Issue Refund popup
	Then I should see "Issue Refund - Confirm Details" popup
	And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
	And I should see breakdown of Before and After Refund earnings for both driver
	And I should see Bungii Internal Note
	When I select "Are you sure you want to proceed with refund request ?" checkbox
	And I click on "Process Refund" button on Issue Refund popup
	Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
	When I click on "OK" button
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed
	 
  @regression
  Scenario: Verify Partial Refund Calculations of Solo Scheduled Delivery
	And I wait for 1 minutes
	When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name                  |
	  | NEXT_POSSIBLE | 9999999601     | Testcustomertywd_appleNewA Customer|
	And As a driver "Testdrivertywd_appledc_a_drvd Driver" perform below action with respective "Solo Scheduled" Delivery
	  | driver1 state|
	  |Accepted |
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
	When I select "Partial Refund" radio button

	And I enter "Customer Refund Amount" as "5.01" dollars
	Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
	And "Customer Refund Amount Percentage" field should be auto calculated based on Delivery Total and Driver Earnings

	When I enter "Customer Refund Amount" as "10" percentage
	Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
	And "Customer Refund Amount" field should be auto calculated based on Delivery Total and Driver Earnings

	When I update "Earnings" as "10.00" dollars
	Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
	And "Customer Refund Amount Percentage" field should be auto calculated based on Delivery Total and Driver Earnings
	And Notes text area should be displayed

	When I update "Earnings" as origional value of amount
	Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
	And "Customer Refund Amount Percentage" field should be auto calculated based on Delivery Total and Driver Earnings
	And Notes text area should not be displayed

	When I update "Earnings" as "5.01" percentage
	Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
	And "Customer Refund Amount" field should be auto calculated based on Delivery Total and Driver Earnings
	And Notes text area should be displayed

	When I update "Earnings" as origional value of percentage
	Then "Bungii Earnings and percentage" fields should be auto calculated based on Delivery Total and Driver Earnings
	And "Customer Refund Amount Percentage" field should be auto calculated based on Delivery Total and Driver Earnings
	And Notes text area should not be displayed

  @regression
  Scenario: Verify Partial Refund for Duo Delivery and complete Driver payment
	When I request "duo" Bungii as a customer in "washingtondc" geofence
	  | Bungii Time   | Customer Phone | Customer Name |
	  | NEXT_POSSIBLE | 9999999992 | Testcustomertywd_appleNewL Customer|
	When As a driver "Testdrivertywd_appledc_a_drvp Driver" and "Testdrivertywd_appledc_a_drvq Driver" perform below action with respective "Duo Scheduled" trip
	  | driver1 state | driver2 state |
	  | Bungii Completed      | Bungii Completed      |
	And I view the Deliveries list on the admin portal
	And I wait for 2 minutes
	And I search the delivery of Customer and view it
	When I click on "ISSUE REFUND" button
	Then The "Issue Refund" section should be displayed
	When I select "Partial Refund" radio button
	And I enter "Customer Refund Amount" as "5.01" dollars
	And I enter "Customer Refund Amount" as "15.01" dollars from second driver
	When I enter "Bungii Internal Notes" as "Internal Note"
	And I click on "Continue" button on Issue Refund popup
	Then I should see "Issue Refund - Confirm Details" popup
	And I should see Original Delivery Charge & Customer Refund & Total Customer Charge for duo delivery
	And I should see breakdown of Before and After Refund earnings for both driver
	And I should see Bungii Internal Note
	When I select "Are you sure you want to proceed with refund request ?" checkbox
	And I click on "Process Refund" button on Issue Refund popup
	Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
	When I click on "OK" button
	And I search the delivery of Customer and view it
	Then The "Issue Refund" button should not be displayed

   #CORE-3380 : To verify that on Driver listing marketing filter only searched region and geofence is displayed on UI.
	@ready
	Scenario:To verify that on Driver listing marketing filter only searched region and geofence is displayed on UI
		When I click on the "Select Geofence" dropdown
		And I Enter the text "Washington DC"
		Then I should see "Washington DC" highlighted
		And I click on the "Washington DC" checkbox
		And I should see the region of the city highlighted
		And I clear the filter applied
		And I click on the "Customer" link from the sidebar
		When I click on the "Select Geofence" dropdown
		And I Enter the text "Boston"
		Then I should see "Boston" highlighted
		When I click on the "Boston" checkbox
        And I should see the region of the city highlighted
		And I click on the "Driver" link from the sidebar
		And I click on the "Select Geofence" dropdown
		And I Enter the text "Goa"
		Then I should see "Goa" highlighted
		When I click on the "Goa" checkbox
		Then I should see the region of the city highlighted
		When I click on the "Sort City" link
		Then I should see the drivers sorted with the applied geofence filter
		And I clear the filter applied
		And I click on the "Non Active Drivers" link from the sidebar
		When I click on the "Select Geofence" dropdown
		And I Enter the text "Washington DC"
		Then I should see "Washington DC" highlighted
		When I click on the "Washington DC" checkbox
		Then I should see the region of the city highlighted
		And I clear the filter applied
		When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
			| Bungii Time   | Customer Phone | Customer Name                        |
			| NEXT_POSSIBLE | 8877661060     | Testcustomertywd_appleMarkBI LutherBI|
		And I wait for 2 minutes
		And I view the all Scheduled Deliveries list on the admin portal
		And I clear the filter applied
		Then I should be able to see the respective bungii with the below status
			|  Status            |
			| Assigning Driver(s)|
		And I should see the region of the city highlighted
		And I clear the filter applied
		When I click on the "Select Geofence" dropdown
		And I Enter the text "Boston"
		Then I should see "Boston" highlighted
		When I click on the "Boston" checkbox
		And I search the delivery of Customer
		Then I should see the message "No deliveries found." displayed
		When As a driver "Testdrivertywd_appledc_a_drvL WashingtonL" perform below action with respective "Solo Scheduled" Delivery
			| driver1 state|
			| Accepted     |
		    | Enroute      |
		And I wait for 2 minutes
		And I view the Live Deliveries list on  admin portal
		And I clear the filter applied
		Then I should be able to see the respective bungii with the below status
			|  Status     |
			| Trip Started|
		And I clear the filter applied
		When I click on the "Select Geofence" dropdown
		And I Enter the text "Boston"
		Then I should see "Boston" highlighted
		When I click on the "Boston" checkbox
		Then I should see the region of the city highlighted
		And I search the delivery of Customer
		Then I should see the message "No deliveries found." displayed
		And As a driver "Testdrivertywd_appledc_a_drvL WashingtonL" perform below action with respective "Solo Scheduled" Delivery
			| driver1 state      |
			| Arrived            |
			| Loading Item       |
			| Driving To Dropoff |
			| Unloading Item     |
			| Bungii Completed   |
		And I wait for 2 minutes
		And I view All Deliveries list on the admin portal
		When I clear the filter applied
		Then The Delivery List page should display the delivery in "Payment Successful" state
		And I clear the filter applied
		When I click on the "Select Geofence" dropdown
		And I Enter the text "Boston"
		Then I should see "Boston" highlighted
		And I click on the "Boston" checkbox
		When I should see the region of the city highlighted
		And I search the delivery of Customer
		Then I should see the message "No deliveries found." displayed

   #CORE-3009 :To verify that sub-menu with name "Active driver map" is present under geofence menu of Admin portal
	@regression
	Scenario:To verify that sub-menu with name "Active driver map" is present under geofence menu of Admin portal
		And I click on the "Driver" link from the sidebar
		Then The "Active Driver Map" "Link" should be displayed
		And I click on the "Active Driver Map" link from the sidebar
		Then The "Map" "Image" should be displayed
		And I click on "Filter" button
		#Core-4117:Verify for the newly added filters are present on Active driver map page
		Then The "Online Drivers" "Button" should be displayed
		Then The "Activated Date" "Textbox" should be displayed
		Then The "Most Recent Delivery" "textbox" should be displayed
		#CORE-3848:Verify that slider for Vehicle payload displays correct details in the result
		Then The "Vehicle Payload" "Slider" should be displayed
		And I slide the "vehicle payload" to "500 lbs"
		And I click on Apply button on Filter
		And I "Unselect" all the "Equipment" checkboxes from the filter
		And I "Unselect" all the "Vehicle Type" checkboxes from the filter
		And I click on the "Select Geofence" dropdown
		And I Enter the text "Kansas"
		When I click on the "Kansas" checkbox
		Then The "Testdrivertywd_appleks_a_drvbg Kansas_bg" "Driver name" should be displayed
		And Driver icon should be displayed on the map for "Testdrivertywd_appleks_a_drvbg Kansas_bg"
		Then The "Details" "Popup" should be displayed
		Then The drivers name "Testdrivertywd_appleks_a_drvbg Kansas_bg" phone number "9049840270" and vehicle type "Unspecified" should be displayed
		When I clear the filter applied
		And I refresh the page
		And I click on the "Select Geofence" dropdown
		And I Enter the text "Kansas"
		When I click on the "Kansas" checkbox
		And I click on "Filter" button
		#CORE-3848:Verify that slider for Vehicle Bed length displays correct details in the result
		Then The "Vehicle Bed Length" "Slider" should be displayed
		And I slide the "Vehicle Bed Length" to "100 In"
		And I click on Apply button on Filter
		And I "Unselect" all the "Equipment" checkboxes from the filter
		And I "Unselect" all the "Vehicle Type" checkboxes from the filter
		Then The "Testdrivertywd_appleks_a_drvbf Kansas_bf" "Driver name" should be displayed
		And Driver icon should be displayed on the map for "Testdrivertywd_appleks_a_drvbf Kansas_bf"
		Then The "Details" "Popup" should be displayed
		Then The drivers name "Testdrivertywd_appleks_a_drvbf Kansas_bf" phone number "9049840269" and vehicle type "Unspecified" should be displayed
		When I clear the filter applied
		And I refresh the page
		And I click on the "Select Geofence" dropdown
		And I Enter the text "Kansas"
		When I click on the "Kansas" checkbox
		And I click on "Filter" button
		#CORE-3848:Verify the ON/OFF toggle for Trailer on Active Driver Map page
		Then The "Trailer" "Button" should be displayed
		And I click on Apply button on Filter
		And I "Unselect" all the "Equipment" checkboxes from the filter
		And I "Unselect" all the "Vehicle Type" checkboxes from the filter
		And I click on "Filter" button
		And I click on "Trailer" button
		And I click on Apply button on Filter
		Then The "Testdrivertywd_appleks_a_drvbe Kansas_be" "Driver name" should be displayed
		And Driver icon should be displayed on the map for "Testdrivertywd_appleks_a_drvbe Kansas_be"
		Then The "Details" "Popup" should be displayed
		Then The drivers name "Testdrivertywd_appleks_a_drvbe Kansas_be" phone number "9049840268" and vehicle type "Unspecified" should be displayed
		When I clear the filter applied
		And I refresh the page
		And I click on the "Select Geofence" dropdown
		And I Enter the text "Kansas"
		When I click on the "Kansas" checkbox
		And I click on "Filter" button
		#CORE-3848:Verify that all required filters are present on Active driver map page
		Then The "Appliance Dolly" "Checkbox" should be displayed
		Then The "Furniture Dolly" "Checkbox" should be displayed
		Then The "Hand Dolly" "Checkbox" should be displayed
		Then The "Lift Gate" "Checkbox" should be displayed
		Then The "Ramp" "Checkbox" should be displayed
		Then The "Box Truck" "Checkbox" should be displayed
		Then The "Moving Van" "Checkbox" should be displayed
		Then The "Pickup Truck" "Checkbox" should be displayed
		Then The "SUV" "Checkbox" should be displayed
		And I click on Apply button on Filter
		And I "Unselect" all the "Equipment" checkboxes from the filter
		And I "Unselect" all the "Vehicle Type" checkboxes from the filter
		When I select filter "Vehicle Type" as "Box Truck"
		And I click on Apply button on Filter
		Then The "Testdrivertywd_appleks_a_drval Kansas_al" "Driver name" should be displayed
		And Driver icon should be displayed on the map for "Testdrivertywd_appleks_a_drval Kansas_al"
		Then The "Details" "Popup" should be displayed
		Then The drivers name "Testdrivertywd_appleks_a_drval Kansas_al" phone number "9049840217" and vehicle type "Truck" should be displayed
		When I select filter "Vehicle Type" as "Box Truck"
		And I click on Apply button on Filter
		When I select filter "Vehicle Type" as "Moving Van"
		And I click on Apply button on Filter
		Then The "Testdrivertywd_appleks_a_drvam Kansas_am" "Driver name" should be displayed
		And Driver icon should be displayed on the map for "Testdrivertywd_appleks_a_drvam Kansas_am"
		Then The "Details" "Popup" should be displayed
		Then The drivers name "Testdrivertywd_appleks_a_drvam Kansas_am" phone number "9049840218" and vehicle type "Van" should be displayed
		When I select filter "Vehicle Type" as "Moving Van"
		And I click on Apply button on Filter
		When I select filter "Vehicle Type" as "Pickup Truck"
		And I click on Apply button on Filter
		Then The "Testdrivertywd_appleks_a_drvbc Kansas_bc" "Driver name" should be displayed
		And Driver icon should be displayed on the map for "Testdrivertywd_appleks_a_drvbc Kansas_bc"
		Then The "Details" "Popup" should be displayed
		Then The drivers name "Testdrivertywd_appleks_a_drvbc Kansas_bc" phone number "9049840266" and vehicle type "Truck" should be displayed
		When I select filter "Vehicle Type" as "Pickup Truck"
		And I click on Apply button on Filter
		When I select filter "Vehicle Type" as "SUV"
		And I click on Apply button on Filter
		Then The "Testdrivertywd_appleks_a_drvbd Kansas_bd" "Driver name" should be displayed
		And Driver icon should be displayed on the map for "Testdrivertywd_appleks_a_drvbd Kansas_bd"
		Then The "Details" "Popup" should be displayed
		Then The drivers name "Testdrivertywd_appleks_a_drvbd Kansas_bd" phone number "9049840267" and vehicle type "SUV" should be displayed
		And I click on the "Driver" link from the sidebar
		When I search driver "Testdrivertywd_appleks_a_drvbd Kansas_bd"
		And I click on "Profile" icon
		And I edit the Driver
		Then The "Driver Status" "Label" should be displayed
		And I change the driver status to "Inactive"
		And I confirm the "Confirm" action
		And I click on "Driver status change" button
		And I click on the "Driver" link from the sidebar
		When I search driver "Testdrivertywd_appleks_a_drvbc Kansas_bc"
		And I click on "Profile" icon
		And I edit the Driver
		Then The "Driver Status" "Label" should be displayed
		And I change the driver status to "Suspended"
		And I confirm the "Confirm" action
		And I click on "Driver status change" button
		And I click on the "Driver" link from the sidebar
		And I click on the "Active Driver Map" link from the sidebar
		When I clear the filter applied
		And I "Unselect" all the "Equipment" checkboxes from the filter
		And I "Unselect" all the "Vehicle Type" checkboxes from the filter
		And I click on the "Select Geofence" dropdown
		And I Enter the text "Kansas"
		When I click on the "Kansas" checkbox
		When I select filter "Vehicle Type" as "SUV"
		And I click on Apply button on Filter
		Then The "Testdrivertywd_appleks_a_drvbd Kansas_bd" "Driver name" should not be displayed
		When I select filter "Vehicle Type" as "SUV"
		And I click on Apply button on Filter
		When I select filter "Vehicle Type" as "Pickup Truck"
		And I click on Apply button on Filter
		Then The "Testdrivertywd_appleks_a_drvbc Kansas_bc" "Driver name" should not be displayed
		And I click on the "Driver" link from the sidebar
		When I clear the filter applied
		When I select a driver "9999999931" whose status is "New Application"
		And I click on the "Active Driver Map" link from the sidebar
		When I clear the filter applied
		And I "Unselect" all the "Equipment" checkboxes from the filter
		And I "Unselect" all the "Vehicle Type" checkboxes from the filter
		And I click on the "Select Geofence" dropdown
		And I Enter the text "Kansas"
		When I click on the "Kansas" checkbox
		Then The driver having status "New Application" should not be present in active driver map
		And I click on the "Driver" link from the sidebar
		When I clear the filter applied
		When I select a driver "6637633622" whose status is "Rejected"
		And I click on the "Active Driver Map" link from the sidebar
		When I clear the filter applied
		And I "Unselect" all the "Equipment" checkboxes from the filter
		And I "Unselect" all the "Vehicle Type" checkboxes from the filter
		And I click on the "Select Geofence" dropdown
		And I Enter the text "Kansas"
		When I click on the "Kansas" checkbox
		Then The driver having status "Rejected" should not be present in active driver map

  #CORE-4117:Verify result on “Most Recent Delivery” filter for active driver when his delivery was completed by Admin
  @ready
  Scenario:Verify result on “Most Recent Delivery” filter for active driver when his delivery was completed by Admin
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
    |Geofence| Bungii Time   | Customer Phone | Customer Name |
    |Kansas| NEXT_POSSIBLE | 8877661152 | Testcustomertywd_appleMarkEW LutherEW|
    And As a driver "Testdrivertywd_appleks_a_drvbs Kansas_bs" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
      |Enroute    |
    And I wait for 2 minutes
    And I click on the "Driver" link from the sidebar
	And I click on the "Active Driver Map" link from the sidebar
    And I click on the "Select Geofence" dropdown
    And I Enter the text "Kansas"
    When I click on the "Kansas" checkbox
    And I "Unselect" all the "Equipment" checkboxes from the filter
    And I "Unselect" all the "Vehicle Type" checkboxes from the filter
	#Core-4117:Verify calendar UI and its functionality for "Activated date" filter
	And I click on "Filter" button
	When I click on the "Activated Date" textbox
	And I select a range from "First Date" of current month to the "Last Date" of the month for "Activated Date"
	And I click on Apply button on Filter
	Then The "Testdrivertywd_appleks_a_drvbt Kansas_bt" "Driver name" should be displayed
	And I click on the "Driver" link from the sidebar
	And I click on the "Active Driver Map" link from the sidebar
	And I click on the "Select Geofence" dropdown
	And I Enter the text "Kansas"
	When I click on the "Kansas" checkbox
	And I "Unselect" all the "Equipment" checkboxes from the filter
	And I "Unselect" all the "Vehicle Type" checkboxes from the filter
    And I click on "Filter" button
    And I click on "Online Drivers" button
    And I click on Apply button on Filter
    Then The "Testdrivertywd_appleks_a_drvbs Kansas_bs" "Driver name" should be displayed
    And I view the Live Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
	When I click on the "Edit" button from the dropdown
    And I click on "Edit Delivery Status" radiobutton
    And I click on "Delivery Completed" radiobutton
    And I enter delivery completion date and time as per geofence
    And I click on "CALCULATE COST" button
    Then Confirmation message on edit live delivery pop up should be displayed
    And I click on "Confirm" button
    And I wait for 2 minutes
    And I click on the "Driver" link from the sidebar
    And I click on the "Select Geofence" dropdown
    And I Enter the text "Kansas"
    When I click on the "Kansas" checkbox
	And I click on the "Active Driver Map" link from the sidebar
	And I "Unselect" all the "Equipment" checkboxes from the filter
    And I "Unselect" all the "Vehicle Type" checkboxes from the filter
    And I click on "Filter" button
    When I click on the "Most Recent Delivery" textbox
	And I select a range from "First Date" of current month to the "Last Date" of the month for "Most Recent Delivery"
	And I click on Apply button on Filter
	Then The "Testdrivertywd_appleks_a_drvbs Kansas_bs" "Driver name" should be displayed

 #CORE-4730:Verify customer full refund changing driver earnings is taken by Same day job if driver payment setting is Same day
  @ready
  Scenario:Verify customer full refund changing driver earnings is taken by Same day job if driver payment setting is Same day
	  When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
		  | Bungii Time   | Customer Phone | Customer Name                       |
		  | NEXT_POSSIBLE | 8877661180     |Testcustomertywd_appleMarkFY LutherFY|
	  And As a driver "Testdrivertywd_appledc_a_drvac Washingtonac" perform below action with respective "Solo Scheduled" Delivery
		  | driver1 state|
		  | Accepted     |
		  | Enroute      |
		  | Arrived      |
		  | Loading Item |
		  | Driving To Dropoff |
		  | Unloading Item     |
		  | Bungii Completed   |
	  And I view the Deliveries list on the admin portal
	  And I wait for 2 minutes
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
	  When I select "Are you sure you want to proceed with refund request ?" checkbox
	  And I click on "Process Refund" button on Issue Refund popup
	  Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
	  When I click on "OK" button
	  And I check the status for "same day payment" of "Disbursement type" in db
	  And The amount should be "Refunded" and in "voided" state

	#CORE-4730:Verify customer full refund without changing driver earnings is taken by Same day job if driver payment setting is Same day
	@ready
	Scenario:Verify customer full refund without changing driver earnings is taken by Same day job if driver payment setting is Same day
		When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
			| Bungii Time   | Customer Phone | Customer Name                        |
			| NEXT_POSSIBLE | 8877661179     | Testcustomertywd_appleMarkFX LutherFX|
		And As a driver "Testdrivertywd_appledc_a_drvad Washingtonad" perform below action with respective "Solo Scheduled" Delivery
			| driver1 state|
			| Accepted     |
			| Enroute      |
			| Arrived      |
			| Loading Item |
			| Driving To Dropoff |
			| Unloading Item     |
			| Bungii Completed   |
		And I view the Deliveries list on the admin portal
		And I wait for 2 minutes
		And I search the delivery of Customer and view it
		When I click on "ISSUE REFUND" button
		Then The "Issue Refund" section should be displayed
		When I select "Complete Refund" radio button
		Then I should see Customer Refund Amount and Driver Earnings
		When I enter "Bungii Internal Notes" as "Internal Note"
		And I click on "Continue" button on Issue Refund popup
		Then I should see "Issue Refund - Confirm Details" popup
		And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
		And I should see breakdown of Before and After Refund earnings
		And I should see Bungii Internal Note
		When I select "Are you sure you want to proceed with refund request ?" checkbox
		And I click on "Process Refund" button on Issue Refund popup
		Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
		When I click on "OK" button
		And I check the status for "same day payment" of "Disbursement type" in db

	 #CORE-4730:Verify Customer Partial refund with changing driver earnings is taken by Same day job if driver payment setting is Same day
	 #CORE-4730:Verify Customer Partial refund making driver earnings zero is taken by Same day job if driver payment setting is Same day
	#Issue logged CORE-5802
	@ready
	Scenario Outline:Verify Customer Partial refund with changing driver earnings is taken by Same day job if driver payment setting is Same day
		When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
			| Bungii Time   | Customer Phone | Customer Name                  |
			| NEXT_POSSIBLE | <Customer Phone>     | <Customer Name>|
		And As a driver "<Driver Name>" perform below action with respective "Solo Scheduled" Delivery
			| driver1 state|
			|Accepted |
			| Enroute  |
			| Arrived |
			| Loading Item |
			| Driving To Dropoff |
			| Unloading Item |
			| Bungii Completed |
		And I view the Deliveries list on the admin portal
		And I wait for 2 minutes
		And I search the delivery of Customer and view it
		When I click on "ISSUE REFUND" button
		Then The "Issue Refund" section should be displayed
		When I select "Partial Refund" radio button
		And I enter "Customer Refund Amount" as "5" dollars
		When I update "Earnings" as "<Driver Earning>" dollars
		When I enter "Notes" as "Driver Note"
		And I enter "Bungii Internal Notes" as "Internal Note"
		And I click on "Continue" button on Issue Refund popup
		Then I should see "Issue Refund - Confirm Details" popup
		And I should see Original Delivery Charge & Customer Refund & Total Customer Charge
		And I should see Bungii Internal Note
		When I select "Are you sure you want to proceed with refund request ?" checkbox
		And I click on "Process Refund" button on Issue Refund popup
		Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
		When I click on "OK" button
		And I check the status for "same day payment" of "Disbursement type" in db
		And The amount should be "Refunded" and in "voided" state

		Examples:
			| Customer Name                         |         Driver Name                         |   Customer Phone  |  Driver Earning  |
			| Testcustomertywd_appleMarkFR LutherFR | Testdrivertywd_appledc_a_drvae Washingtonae |     8877661173    |  10.00           |
			| Testcustomertywd_appleMarkFS LutherFS |Testdrivertywd_appledc_a_drvaf Washingtonaf  |   8877661174      |  0               |

	@ready
	#Issue Raised CORE-5802: Payment method not automatically getting selected for Partial Refund
	#CORE-4644: Verify Bungii earnings and Driver share view details link of Transaction history when partial refund is performed
	Scenario: Verify Partial Refund for Duo Delivery and complete Driver payment
		When I request "duo" Bungii as a customer in "washingtondc" geofence
			| Bungii Time   | Customer Phone | Customer Name                      |
			| NEXT_POSSIBLE | 8877661216	 | Testcustomertywd_appleMarkHI LutherHI|
		When As a driver "Testdrivertywd_appledc_a_drval Washingtonal" and "Testdrivertywd_appledc_a_drvam Washingtonam" perform below action with respective "Duo Scheduled" trip
			| driver1 state     | driver2 state    |
			| Bungii Completed  | Bungii Completed |
		And I view All Deliveries list on the admin portal
		And I wait for 2 minutes
		And I search the delivery of Customer and view it
		When I click on "ISSUE REFUND" button
		Then The "Issue Refund" section should be displayed
		When I select "Partial Refund" radio button
		And I refund "Driver1" earnings to customer
		And I enter "Driver1 Earnings" as "0" dollars
		And I note the "Driver2 Earnings"
		When I enter "Driver1 Notes" as "Partial Refund"
		And I enter "Bungii Internal Notes" as "Internal Note"
		And I click on "Continue" button on Issue Refund popup
		Then I should see "Issue Refund - Confirm Details" popup
		And I note the "Bungii Earnings after refund"
		When I select "Are you sure you want to proceed with refund request ?" checkbox
		And I click on "Process Refund" button on Issue Refund popup
		Then "We are processing your Refund Request. We will let you know once it has been processed successfully." is displayed
		When I click on "OK" button
		And I view All Deliveries list on the admin portal
		And I wait for 2 minutes
		And I search the delivery of Customer and view it
		Then I should be able to see correct Transaction history on delivery details page