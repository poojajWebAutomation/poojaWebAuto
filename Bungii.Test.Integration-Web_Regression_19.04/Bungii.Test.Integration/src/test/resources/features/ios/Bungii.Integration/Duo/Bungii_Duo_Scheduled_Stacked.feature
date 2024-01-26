@ios
@DUO
@scheduled
@bungii
Feature: Scheduled Bungii STACKING
  I want  request Scheduled Bungii with Duo type
  
  Background:
	When I Switch to "customer" application on "same" devices
  
  @regression
     #stable
  Scenario: STACKING | Verify Driver Can Receive Long Stack Ondemand Request When Ongoing Ondemand Bungii is On Arrived State - Goa Geofence [1 Device]
	Given that ondemand bungii is in progress
	  | geofence | Bungii State |
	  | goa      | ARRIVED      |
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid" driver
	
	When I request another "Solo Ondemand" Bungii as a customer in "goa" geofence
	  | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
	  | now         | 9403960183     | Mark Cuban    |               | Cci12345          |
	
	And I wait for 1 minutes
	And I view and accept virtual notification for "second" delivery of "Driver" for "stack trip"
	And stack trip information should be displayed on deck
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE | 9403960183      |

@ready
Scenario: STACKING | Verify Manually Ending Bungii For A Driver That Has Stacked Bungii Should See Summary And Start Screen Of The Stacked Bungii [1 Device]
Given that ondemand bungii is in progress
| geofence | Bungii State        |
| goa      | DRIVING TO DROP OFF |
When I Switch to "driver" application on "same" devices
And I am on the "LOG IN" page on driverApp
And I am logged in as "valid" driver
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
| Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
| now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
And I view and accept virtual notification for "Driver" for "stack trip"
And stack trip information should be displayed on deck

When I Switch to "customer" application on "same" devices
Given I am on the "LOG IN" page
When I enter Username :9403960183 and  Password :{VALID}
And I click "Log In" button on "Log In" screen
And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
And I close "Tutorial" if exist

When bungii admin manually end bungii created by "CUSTOMER1"

When I Switch to "driver" application on "same" devices
And I click "Skip This Step" button on "Rate customer" screen
Then I should be navigated to "Bungii completed" screen
When I click "On To The Next One" button on "Bungii completed" screen
Then I should be navigated to "EN ROUTE" screen

When I Switch to "customer" application on "same" devices
Then I should be navigated to "EN ROUTE" screen

Then I cancel all bungiis of customer
| Customer Phone | Customer2 Phone |
| CUSTOMER1_PHONE | 9403960183      |
  
  @regression
 #stable
  Scenario: STACKING | Verify Driver Can Receive Short Stack Ondemand Request when Ongoing Ondemand Bungii is On Unloading Item State - Goa Geofence [1 Device]
	Given that ondemand bungii is in progress
	  | geofence | Bungii State   |
	  | goa      | UNLOADING ITEM |
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid" driver
	When I request another "Solo Ondemand" Bungii as a customer in "goa" geofence
	  | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
	  | now         | 9403960183     | Mark Cuban    |               | Cci12345          |
  
	And I wait for 2 minutes
	And I view and accept virtual notification for "second" delivery of "Driver" for "stack trip"
	And stack trip information should be displayed on deck

#	  Core-3098 Verify online/offline pop up is not shown when driver has accepted stack trip
	  And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
	  And I click "Skip This Step" button on "Rate customer" screen
	  Then I should be navigated to "Bungii Completed" screen
	  When I click "On To The Next One" button on "Bungii completed" screen
	  And I check online or offline pop up is not displayed
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE | 9403960183      |

	
  @regression
#stable
Scenario:STACKING | Verify Driver can start the Stack Bungii [1 Device]
Given that ondemand bungii is in progress
| geofence | Bungii State   |
| goa      | UNLOADING ITEM |
When I Switch to "driver" application on "same" devices
And I am on the "LOG IN" page on driverApp
And I am logged in as "valid" driver
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

When I Switch to "customer" application on "same" devices
When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
| Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
| now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
	And I wait for 2 minutes
And I view and accept virtual notification for "Driver" for "stack trip"
When I Switch to "driver" application on "ORIGINAL" devices
And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
Then I should be navigated to "Bungii Completed" screen
And I click "Skip This Step" button on "Rate customer" screenAnd I click "Skip This Step" button on "Rate customer" screen
When I click "On To The Next One" button on "Bungii completed" screen
Then I should be navigated to "EN ROUTE" screen

Then I cancel all bungiis of customer
| Customer Phone | Customer2 Phone |
| CUSTOMER1_PHONE | 9403960183      |


@regression
    #stable
Scenario: Verify Driver see minutes from pickup address in avaliable trips for Duo Bungii [1 Device]
Given that duo schedule bungii is in progress
| geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
| goa      | Scheduled     | 0.5 hour ahead | valid    | valid   | valid driver 2 |

And I Switch to "driver" application on "same" devices
And I am on the "LOG IN" page on driverApp
And I am logged in as "valid duo driver 1" driver
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

And I Select "AVAILABLE BUNGIIS" from driver App menu
Then I should see driver minutes to pickup address of "Duo" delivery
Then I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| CUSTOMER1_PHONE |                 |
  
  @regression
	#stable
  Scenario: STACKING | Verify Non Control Driver Of Ongoing Bungii Can Accept Short Stack Request [1 Device]
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State       | Bungii Time   | Customer     | Driver1            | Driver2        |
	  | goa      | Driving To Dropoff | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
	
	And I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid driver 2" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	And I Switch to "customer" application on "same" devices
	When I request another "Solo Ondemand" Bungii as a customer in "goa" geofence
	  | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
	  | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
  
	And I view and accept virtual notification for "second" delivery of "Driver" for "stack trip"
	And stack trip information should be displayed on deck
 
	#Only Control driver can finish the trip without waiting for non control driver
	And As a driver "valid duo driver 1" perform below action with respective "Duo Scheduled" Delivery
	  | driver1 state|
	  | Unloading Item |
	  | Bungii Completed |
	
	And I Switch to "driver" application on "same" devices
	And I click "Skip This Step" button on "Rate customer" screen
	When I click "On To The Next One" button on "Bungii completed" screen
	Then I should be navigated to "EN ROUTE" screen
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | CUSTOMER1_PHONE     | 9403960183 |
  
  @regression
	#stable
  Scenario: STACKING | Verify Non Control Driver Of Ongoing Bungii Can Accept Long Stack Request [1 Device]
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State       | Bungii Time   | Customer     | Driver1            | Driver2        |
	  | goa      | arrived | 0.5 hour ahead | customer-duo | valid duo driver 1 | valid driver 2 |
	
	And I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid driver 2" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	And I Switch to "customer" application on "same" devices
	When I request another "Solo Ondemand" Bungii as a customer in "goa" geofence
	  | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
	  | now         | 9403960183     | Mark Cuban    | 2              | Cci12345          |
	
	And I view and accept virtual notification for "second" delivery of "Driver" for "stack trip"
	And stack trip information should be displayed on deck
 
	#Only Control driver can finish the trip without waiting for non control driver
	And As a driver "valid duo driver 1" perform below action with respective "Duo Scheduled" Delivery
	  | driver1 state|
	  | Loading Item |
	  | Driving To Dropoff |
	  | Unloading Item |
	  | Bungii Completed |
	
	And I Switch to "driver" application on "same" devices
	And I click "Skip This Step" button on "Rate customer" screen
	When I click "On To The Next One" button on "Bungii completed" screen
	Then I should be navigated to "EN ROUTE" screen
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | CUSTOMER1_PHONE     | 9403960183 |

  @ready
  Scenario: STACKING | Verify Driver Can Receive Long Stack Request And Customer Can Cancel the stacked request when Drivers Existing Bungii is On Loading Item State [1 Device]
	Given that ondemand bungii is in progress
	  | geofence | Bungii State |
	  | goa      | LOADING ITEM |
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	When I Switch to "customer" application on "same" devices
	Given I am on the "LOG IN" page
	When I enter Username :9403960183 and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I request for  bungii for given pickup and drop location
	  | Driver | Pickup Location                 | Drop Location                      | geofence  |
	  | Solo   | Margao Railway Overbridge  | peerbaugh Rd, Peer wadi				  | 	goa |
	And I click "Get Estimate" button on "Home" screen
	Then I should be navigated to "Estimate" screen
	When I confirm trip with following details
	  | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
	  | 15       |           |              | Now  | Default     |
	Then I should be navigated to "SEARCHING" screen
	
	And I wait for 2 minutes
	And I view and accept virtual notification for "Driver" for "stack trip"
	And stack trip information should be displayed on deck
	
	When I Switch to "customer" application on "same" devices
	Then I should be navigated to "BUNGII ACCEPTED" screen
	When I click "Ok" button on "BUNGII ACCEPTED" screen
	When I click "CANCEL BUNGII" on bungii accepted screen
	Then I see "Alert: Bungii cancel confirmation" on bungii accepted screen
	When I click "Dismiss on Alert message" on bungii accepted screen
	
	When I Switch to "customer" application on "same" devices
	Then I should be navigated to "BUNGII ACCEPTED" screen
	When I click "CANCEL BUNGII" on bungii accepted screen
	When I click "CANCEL BUNGII on Alert message" on bungii accepted screen
	When I Switch to "customer" application on "same" devices
	Then I should be navigated to "HOME" screen
	Then I wait for "1" mins
	And I click on notification for "Driver" for "CUSTOMER CANCEL STACK TRIP"
	When I Switch to "driver" application on "same" devices
	And stack trip information should not be displayed on deck
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE | 9403960183      |

	@ready
  Scenario: STACKING | Verify Customer Can Cancel Stacked Ondemand Bungii Accepted By a Driver [1 Device]
	Given that ondemand bungii is in progress
	  | geofence | Bungii State   |
	  | goa      | LOADING ITEM |
	
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	When I Switch to "customer" application on "same" devices
	Given I am on the "LOG IN" page
	When I enter Username :9403960183 and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I request for  bungii for given pickup and drop location
	  | Driver | Pickup Location                 | Drop Location                      | geofence  |
	  | Solo   | Margao Railway Overbridge  | peerbaugh Rd, Peer wadi				  | 	goa |
	And I click "Get Estimate" button on "Home" screen
	Then I should be navigated to "Estimate" screen
	When I confirm trip with following details
	  | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
	  | 15       |           |              | Now  | Default     |
	Then I should be navigated to "SEARCHING" screen
	And I wait for 1 minutes
	And I view and accept virtual notification for "Driver" for "stack trip"
	And stack trip information should be displayed on deck
	
	When I Switch to "customer" application on "same" devices
	Then I should be navigated to "BUNGII ACCEPTED" screen
	When I click "Ok" button on "BUNGII ACCEPTED" screen
	When I click "CANCEL BUNGII" on bungii accepted screen
	When I click "CANCEL BUNGII on Alert message" on bungii accepted screen
	When I Switch to "customer" application on "same" devices
	Then I should be navigated to "Home" screen
 
	When I Switch to "driver" application on "same" devices
	And I click on notification for "Driver" for "CUSTOMER CANCEL STACK TRIP"
	And stack trip information should not be displayed on deck
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |  9403960183   |

  #CORE-3545
  @ready
  Scenario:To verify that driver can successfully Complete on going trip when admin cancels Stack trip
	Given that ondemand bungii is in progress
	  | geofence | Bungii State   |
	  | goa      | UNLOADING ITEM |
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
	 | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
	 | now         | 8877661177     | Testcustomertywd_BppleMarkFV LutherFV | 2             | Cci12345         |
	And I view and accept virtual notification for "Driver" for "stack trip"
	And stack trip information should be displayed on deck
	When I open new "Chrome" browser for "ADMIN PORTAL"
	And I navigate to admin portal
	And I log in to admin portal
	And I Select "live trips" from admin sidebar
	And I wait for "2" mins
	And I open the trip for "LutherFV" the customer for delivery details
	And I click on "Edit" link beside live delivery
	And I click on "Edit Delivery Status" radiobutton
	And I click on "Delivery Canceled" radiobutton
	And I click on "UPDATE BUNGII" button
	Then The "Pick up has been successfully canceled." message should be displayed for live delivery
	And I Switch to "driver" application on "ORIGINAL" devices
	And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
	And I click "Skip This Step" button on "Rate customer" screen
	Then I should be navigated to "Bungii Completed" screen

  #CORE-4426
  @ready
  Scenario:To verify that driver can successfully Complete on going trip when admin manually completes Stack trip
	Given that ondemand bungii is in progress
	 | geofence | Bungii State   |
	 | goa      | UNLOADING ITEM |
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
	 | Bungii Time | Customer Phone | Customer Name | Customer label | Customer Password |
	 | now         | 8877661178     | Testcustomertywd_appleMarkFW LutherFW | 2              | Cci12345          |
	And I view and accept virtual notification for "Driver" for "stack trip"
	And stack trip information should be displayed on deck
	When I open new "Chrome" browser for "ADMIN PORTAL"
	And I navigate to admin portal
	And I log in to admin portal
	And I Select "live trips" from admin sidebar
	And I wait for "2" mins
	And I open the trip for "LutherFW" the customer for delivery details
	And I click on "Edit" link beside live delivery
	And I click on "Edit Delivery Status" radiobutton
	And I click on "Delivery Completed" radiobutton
	And I enter delivery completion date and time as per geofence
	And I click on "CALCULATE COST" button
	Then Confirmation message on edit live delivery pop up should be displayed
	And I click on "Confirm" button
	Then The "Pick up has been successfully updated." message should be displayed for live delivery
	And I Switch to "driver" application on "ORIGINAL" devices
	And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
	And I click "Skip This Step" button on "Rate customer" screen
	Then I should be navigated to "Bungii Completed" screen