@ios
    # this will run in denver
Feature: Trip Alert Settings
  I want to use request Scheduling Bungii with Solo type
  
	  #Always Last scenario from this feature file since it changes settings for the driver
	  @regression
		#stable
	  Scenario: Verify Driver Doesnt Receive Scheduled Request If The Request Is Sent Outside Of Time That Is Set In Trip Alert Settings
	  When I Switch to "driver" application on "same" devices
	  And I am on the "LOG IN" page on driverApp
	  And I am logged in as "valid denver" driver
		And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
		When I Select "ACCOUNT > ALERT SETTINGS" from driver App menu
	  And I update denvers driver todays trip alert setting to outside current time
		
	  When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
	  | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
	  | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
	  And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
	  
	  When I Switch to "driver" application on "same" devices
	  And I Select "AVAILABLE BUNGIIS" from driver App menu
	  Then I should be navigated to "AVAILABLE BUNGIIS" screen
	  And I should able to see "one" available trip
		# As a part of https://bungii.atlassian.net/browse/CORE-2718, driver should see trip in available
	 
		And I Select "ACCOUNT > ALERT SETTINGS" from driver App menu
	  And I update trip setting of "TODAY" to "12:00 AM" to "11:59 PM"
		And I update trip setting of "TOMORROW" to "12:00 AM" to "11:59 PM"
		And I update trip setting of "YESTERDAY" to "12:00 AM" to "11:59 PM"
	 
		Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8888889917     |                 |
  
  
  @regression
  #stable
  Scenario: Verify Minimum Scheduled Time Should Be Displayed On The Date Picker Of The Estimate Screen Based On When Duo Is Selected By Customer
	Given I am on the "LOG IN" page
	And I logged in Customer application using  "valid chicago" user
	
	When I open new "Chrome" browser for "ADMIN PORTAL"
	And I navigate to admin portal
	And I log in to admin portal
	And I Select "Geofence" from admin sidebar
	And I select "Chicago" geofence
	And I activate "Chicago" geofence
	And I select "Chicago" geofence
	And I click on the "Settings" Button on "Geofence" Screen
	And I get the value of "Minimum scheduled time for Duo trip"
	And I change the value of "Minimum scheduled time for Duo trip" to "30" minutes
	And I click on the "Save" Button on "GeofenceSettings" Screen
	
	When I switch to "ORIGINAL" instance
	And I Switch to "customer" application on "same" devices
	And I request for  bungii for given pickup and drop location
	  | Driver | Pickup Location     | Drop Location                                      | Geofence  |
	  | Duo    | 63 East Ida B. Wells Drive, Chicago, IL 60605, USA | 63 East Ida B. Wells Drive, Chicago, IL 60605, USA | chicago   |
	
	And I click "Get Estimate" button on "Home" screen
	Then I should be navigated to "Estimate" screen
	
	And I select pickup time
	Then correct next available scheduled time should be displayed
  
  @regression
	#stable
  Scenario: Verify Correct Data Is Displayed In Trip And Sms Alert Settings Upon Switching Between Trip And SMS Alerts Tabs
	Given I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "new driver" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
 
	When I Select "ACCOUNT > ALERT SETTINGS" from driver App menu
	And I save "DELIVERY ALERT" settings data
	When I click "SMS ALERT" button on "ALERT SETTINGS" screen on driverApp
	And I update sms setting of "sunday" to "09:00 a.m." to "11:00 p.m."
	And I save "SMS ALERT" settings data
	When I click "DELIVERY ALERT" button on "ALERT SETTINGS" screen on driverApp
	Then previous "DELIVERY ALERT" data should be retained
	And I update trip setting of "sunday" to "05:00 a.m." to "11:00 p.m."
	
	When I click "SMS ALERT" button on "ALERT SETTINGS" screen on driverApp
	Then previous "SMS ALERT" data should be retained
