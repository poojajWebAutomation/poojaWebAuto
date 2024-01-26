@android
    #These feature will run in Boston geofence
Feature: Bungii Requests
	  
	  @regression
		#Stable
	  Scenario:  Verify Customer Can Schedule Solo Bungii Only 5 Days Ahead Including Current Date
	  Given I login as customer "8805368840" and is on Home Page
	  And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	  And I close "Tutorial" if exist
		And I enter "boston pickup and dropoff locations" on Bungii estimate
	  And I tap on "Get Estimate button" on Bungii estimate
	  And I add loading/unloading time of "30 mins"
	  And I get Bungii details on Bungii Estimate
	  And I add "1" photos to the Bungii
	  When I confirm trip with following detail
	  | Day | Trip Type |
	  | 2   | SOLO      |
	  And I tap on "Request Bungii" on Bungii estimate
	  And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	  And I click "Done" button on "Success" screen
		
	  And I tap on "Menu" > "Home" link
	  And I enter "boston pickup and dropoff locations" on Bungii estimate
	  And I tap on "Get Estimate button" on Bungii estimate
	  And I add loading/unloading time of "30 mins"
	  And I get Bungii details on Bungii Estimate
	  And I add "1" photos to the Bungii
	  When I confirm trip with following detail
	  | Day | Trip Type |
	  | 4   | SOLO      |
	  And I tap on "Request Bungii" on Bungii estimate
	  And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	  And I click "Done" button on "Success" screen
	  
	  And I tap on "Menu" > "Home" link
	  And I enter "boston pickup and dropoff locations" on Bungii estimate
	  And I tap on "Get Estimate button" on Bungii estimate
	  Then I try to schedule bungii for "today+5"
	  | Day | Trip Type |
	  | 5   | SOLO      |
	  Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8805368840     |                 |
	  
	  
	  @regression
	  Scenario:  Verify Customer Can Schedule DUO Bungii Only 5 days ahead Including Current Date
	  Given I login as customer "8805368840" and is on Home Page
	  And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	  And I close "Tutorial" if exist
		
	  And I enter "boston pickup and dropoff locations" on Bungii estimate
	  And I tap on "two drivers selector" on Bungii estimate
	  Then I should see "two drivers selected" on Bungii estimate
	  When I tap on "Get Estimate button" on Bungii estimate
	  And I confirm trip with following detail
	  | Day | Trip Type |
	  | 2   | DUO       |
	  And I add loading/unloading time of "30 mins"
	  And I get Bungii details on Bungii Estimate
	  And I add "1" photos to the Bungii
	  And I tap on "Request Bungii" on Bungii estimate
	  And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	  And I click "Done" button on "Success" screen
	  
	  And I tap on "Menu" > "Home" link
	  And I enter "boston pickup and dropoff locations" on Bungii estimate
	  And I tap on "two drivers selector" on Bungii estimate
	  Then I should see "two drivers selected" on Bungii estimate
	  When I tap on "Get Estimate button" on Bungii estimate
	  And I confirm trip with following detail
	  | Day | Trip Type |
	  | 3   | DUO       |
	  And I add loading/unloading time of "30 mins"
	  And I get Bungii details on Bungii Estimate
	  And I add "1" photos to the Bungii
	  And I tap on "Request Bungii" on Bungii estimate
	  And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	  And I click "Done" button on "Success" screen
	  
	  And I tap on "Menu" > "Home" link
	  And I enter "boston pickup and dropoff locations" on Bungii estimate
	  And I tap on "two drivers selector" on Bungii estimate
	  Then I should see "two drivers selected" on Bungii estimate
	  When I tap on "Get Estimate button" on Bungii estimate
	  Then I try to schedule bungii for "today+5"
	  | Day | Trip Type |
	  | 5   | DUO       |
	  Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8805368840     |                 |
	  
	  @ready
	  Scenario: Verify Driver Can Reject Ondemand Bungii Request
	  Given I am on customer Log in page
	  When I am logged in as "valid boston" customer
	  And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	  And I close "Tutorial" if exist
		
	  And I Switch to "driver" application on "same" devices
	  And I am on the LOG IN page on driver app
	  And I am logged in as "valid boston" driver
	  And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
		
	  And I tap on "Go Online button" on Driver Home page
		
	  And I Switch to "customer" application on "same" devices
	  And I enter "new boston pickup and dropoff locations" on Bungii estimate
	  And I tap on "Get Estimate button" on Bungii estimate
	  And I add "2" photos to the Bungii
	  And I add loading/unloading time of "30 mins"
	  And I tap on "Request Bungii" on Bungii estimate
	  And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	  
	  And I Open "driver" application on "same" devices
	  And Bungii Driver "rejects On Demand Bungii" request
	  Then Bungii driver should see "Home screen"

#	  Core-3098 Verify online/Offline pop up is shown when Driver has schedule trip accepted for future days

	@ready
	Scenario: Verify online/Offline pop up is shown when Driver has schedule trip accepted for future days
		And I Switch to "driver" application on "same" devices
		And I am on the LOG IN page on driver app
		And I am logged in as "valid boston" driver
		And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

		When I request "Solo" Bungii as a customer in "boston" geofence
			| Bungii Time   | Customer Phone | Customer Name |
			| 3_DAY_LATER | 8877661007       | Testcustomertywd_appleMarkH LutherH|

		And I select "View Request" from pop-up
		And I click "ACCEPT NOTIFICATION" button on Bungii Request screen

		And I Switch to "customer" application on "same" devices
		When I request "Solo Scheduled" Bungii as a customer in "boston" geofence
			| Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
			| NEXT_POSSIBLE | 8877661004     | Testcustomertywd_appleMarkE LutherE | Cci12345          |

		And As a driver "valid boston" perform below action with respective "Solo Scheduled" Delivery
			| driver1 state |
			|Accepted |
			| Enroute  |
			| Arrived |
			| Loading Item |
			| Driving To Dropoff |
			| Unloading Item |

		And I Switch to "driver" application on "same" devices
		And I am logged in as "valid boston" driver
		And I slide update button on "UNLOADING ITEMS" Screen
		And Bungii Driver "skips to rate customer"
		Then Bungii Driver "completes Bungii"
		And I click "Next Bungii" button on the "Bungii Completed" screen
		And I check online or offline pop up is displayed