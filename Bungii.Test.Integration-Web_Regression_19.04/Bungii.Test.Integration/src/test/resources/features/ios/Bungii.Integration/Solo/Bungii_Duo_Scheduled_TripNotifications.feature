@ios
@scheduled
@DUO
@bungii
    # this will run in denver
  # Denver | Customer 8888889917, Valid denver - 9999993015
  # Denver | Driver Testdrivertywd_appledv_b_matt Stark_dvOnE -9999998086  , Testdrivertywd_appledv_b_seni Stark_dvThree -9955112208
  # Denver | Customer denver customer Valid denver & denver customer - 9999993015
  # Denver | Driver valid denver & denver driver 1 - 8888884321 | denver driver 2 - 9955112208

Feature: Scheduled DUO Bungii Trip Notifications
  I want to use request Scheduling Bungii with duo type
  
  Background:
	When I Switch to "customer" application on "same" devices
  
  
  @ready
  #stable
  Scenario: Notification : Verify If Customer Receives Notification Once Required Number Of Drivers Accepts Duo Scheduled Bungii
	When I request "duo" Bungii as a customer in "denver" geofence
	  | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
	  | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
	Given I am on the "LOG IN" page
	When I enter Username :8888889917 and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
	  | driver1 state | driver2 state |
	  | Accepted      | Accepted      |
	And I click on notification for "Customer" for "SCHEDULED PICKUP ACCEPTED"
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8888889917     |                 |

	@ready
  Scenario: Notification : Verify Customer Receives Notification When Control Driver Starts Duo Scheduled Bungii
	When I request "duo" Bungii as a customer in "denver" geofence
	  | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
	  | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
	Given I am on the "LOG IN" page
	When I enter Username :8888889917 and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
	  | driver1 state | driver2 state |
	  | Enroute       | Accepted      |
	And I click on notification for "Customer" for "DRIVERS ARE ENROUTE"
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8888889917     |                 |

	@ready
  #stable
    #Run at last as lot of wait time is required [15 mins]
  Scenario: Notification : Verify If Customer Receives Notification After Admin Researches Drivers And Then Both Drivers Accept It
	When I request "duo" Bungii as a customer in "denver" geofence
	  | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
	  | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
	Given I am on the "LOG IN" page
	When I enter Username :8888889917 and  Password :{VALID}
	And I click "Log In" button on "Log In" screen
	And I Select "Home" from Customer App menu
	And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
	When I Switch to "driver" application on "same" devices
	And I open Admin portal and navigate to "Scheduled Deliveries" page
	
	And I verify status and researches Bungii with following details
	  | label                | Status of Trip      |
	  | DUO_SCH_DONOT_ACCEPT | Driver(s) Not Found |
	
	And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
	  | driver1 state | driver2 state |
	  | Accepted      | Accepted      |
	And I view virtual notification for "Customer" for "SCHEDULED PICKUP ACCEPTED"
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8888889917     |                 |

	@ready
  #stable
  Scenario: Verify Customer Can Cancel Duo Scheduled Bungii Through SMS To Admin If Required Number Of Drivers Have Accepted
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time     | Customer        | Driver1         | Driver2         |
	  | denver   | Accepted     | 0.75 hour ahead | denver customer | denver driver 1 | denver driver 2 |
	When I Switch to "customer" application on "same" devices
	Given I am on the "LOG IN" page
	And I logged in Customer application using  "valid denver" user
	And I Select "MY BUNGIIS" from Customer App menu
    #Then I wait for "1" mins
	And I select already scheduled bungii
	When I Cancel selected Bungii
	Then correct support details should be displayed to customer on "ADMIN-SMS" app
	
	And I open Admin portal and navigate to "Scheduled Deliveries" page
	
	And I Cancel Bungii with following details
	  | Charge | Comments |
	  | 0      | TEST     |
	Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
	And Bungii must be removed from the List
	When I switch to "ORIGINAL" instance
	And I Switch to "customer" application on "same" devices
	And I Select "MY BUNGIIS" from Customer App menu
	Then Bungii must be removed from "SCHEDULED BUNGIIS" screen


	@ready
   #stable
  Scenario: Verify Customer Receives alert When Duo Scheduled Bungii Is Requested At A Time Outside Working Hours
    #When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
	Given I login as "valid denver" customer and on Home page
	And I request for  bungii for given pickup and drop location
	  | Driver | Pickup Location                    | Drop Location                    | Geofence |
	  | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
	And I click "Get Estimate" button on "Home" screen
	When I try to schedule bungii for "today - after working hour"
	Then user is alerted for "OUTSIDE BUISSNESS HOUR"
	When I try to schedule bungii for "tommorow - before working hour"
	Then user is alerted for "OUTSIDE BUISSNESS HOUR"
  
  @regression
    #stable
  Scenario: Verify Driver Doesnt Receive Short Stacked Request If The Driver Location Is More Than 100 Mins From The Current Location Of Driver To The Pickup Of Requesting Trip [1 Device]
	Given that ondemand bungii is in progress
	  | geofence | Bungii State   |
	  | goa      | UNLOADING ITEM |
	
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	When I Switch to "customer" application on "same" devices
	And I am on the "LOG IN" page
	When I logged in Customer application using  "valid customer2" user
	When I request for  bungii for given pickup and drop location
	  | Driver | Pickup Location          | Drop Location  |
	  | Solo   | St mary paralytic centre | froggyland Goa |
	
	And I click "Get Estimate" button on "Home" screen
	And I confirm trip with following details
	  | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
	  | 15       |           |              | Now  | Default     |
	Then I should be navigated to "SEARCHING" screen
	
	When I Switch to "driver" application on "same" devices
	When I verify that driver to pickup time is greater than 100 mins for second trip
	And I should not get notification for "driver" for "stack trip"
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE | CUSTOMER2_PHONE |
  
  @regression
    #stable
  Scenario: Verify Driver Doesnt Receive Long Stacked Request If The Driver Location Is More Than 100 Mins From The Current Location Of Driver To The Pickup Of Requesting Trip [1 Device]
	Given that ondemand bungii is in progress
	  | geofence | Bungii State |
	  | goa      | LOADING ITEM |
	
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	
	When I Switch to "customer" application on "same" devices
	And I am on the "LOG IN" page
	When I logged in Customer application using  "valid customer2" user
	When I request for  bungii for given pickup and drop location
	  | Driver | Pickup Location          | Drop Location  |
	  | Solo   | St mary paralytic centre | froggyland Goa |
	
	And I click "Get Estimate" button on "Home" screen
	And I confirm trip with following details
	  | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
	  | 15       |           |              | Now  | Default     |
	Then I should be navigated to "SEARCHING" screen
	
	When I Switch to "driver" application on "same" devices
	When I verify that driver to pickup time is greater than 100 mins for second trip
	And I should not get notification for "driver" for "stack trip"
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE | CUSTOMER2_PHONE |