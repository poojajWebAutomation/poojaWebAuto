@android
@bungii
@general
@ondemand
  #These feature will run in baltimore geofence
Feature: On Demand Bungii Promocode Part B
  
  @regression
	#stable
  Scenario Outline: Verify Customer Can Create Ondemand Bungii With Promoter Type Promocode
	
	Given I Switch to "customer" application on "same" devices
	When I am on customer Log in page
	And I am logged in as "<User>" customer

	And I Switch to "driver" application on "same" devices
	And I am logged in as "valid baltimore" driver
	And I tap on "Go Online button" on Driver Home page
	And I Switch to "customer" application on "same" devices

	And I tap on "Menu" > "HOME" link
	And I enter "baltimore pickup and dropoff locations" on Bungii estimate
	And I tap on "Get Estimate button" on Bungii estimate
	And I add loading/unloading time of "30 mins"
	And I tap on "Promo Code" on Bungii estimate
	And I add "<Promo Code>" PromoCode
	And I tap "Add" on Save Money page
	And I tap on "desired Promo Code" on Bungii estimate
	And I get Bungii details on Bungii Estimate
	Then I should see "all elements" on Bungii estimate
	When I add "1" photos to the Bungii
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	Then for a Bungii I should see "Bungii search screen"

	When I Open "driver" application on "same" devices
	And Bungii Driver "accepts On Demand Bungii" request
	Then Bungii driver should see "Enroute screen"

	When I Switch to "customer" application on "same" devices
	When I click "Ok" button on the "BUNGII ACCEPTED" screen

	When I Open "driver" application on "same" devices
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Arrived screen"
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Loading Item screen"
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Driving to DropOff screen"
	When Bungii Driver "slides to the next state"
	Then Bungii driver should see "Unloading Item screen"
	And Bungii Driver "slides to the next state"

	When I Switch to "customer" application on "same" devices
	Then Bungii customer should see "correct details with promo" on Bungii completed page
	When I tap on "OK on complete" on Bungii estimate
	And I tap on "No free money" on Bungii estimate
	And I Switch to "driver" application on "same" devices
	Then Bungii driver should see "correct details" on Bungii completed page
	And I click "On To The Next One" button on the "Bungii Completed" screen
	
	And I wait for "2" mins
	And I open new "Chrome" browser for "ADMIN PORTAL"
	And I navigate to admin portal
	And I log in to admin portal
	And I Select "live trips" from admin sidebar
	And I select trip from trips
	Then On admin trip details page "<Expected value in admin>" should be displayed

	  Examples:
	  | Scenario            | Promo Code    | User            |Expected value in admin |
	  | Promoter type       | promoter type | valid baltimore |promoter                |
  
  
  @ready
  @ad6
      # Also Case Covered, Verify that the Estimated cost on the grey bar is updated on updating load/unload time and promo code
  Scenario Outline: Verify Create and Complete ondemand bungii with Promo code :<Scenario>
	When I am on customer Log in page
	And I am logged in as "valid baltimore" customer
	And I Switch to "driver" application on "same" devices
	And I am logged in as "valid baltimore" driver
	And I Select "HOME" from driver App menu
	Then I tap on "Go Online button" on Driver Home page
	When I Switch to "customer" application on "same" devices
	
	And I enter "baltimore pickup and dropoff locations" on Bungii estimate
	And I tap on "Get Estimate button" on Bungii estimate
	And I add "1" photos to the Bungii
	And I add loading/unloading time of "15 mins"
	And I tap on "Promo code value" on Estimate screen
	And I add "PROMOTER TYPE PROMO" PromoCode
	And I tap "Add" on Save Money page
	Then I should able to see expected promo code in available promo code
	And I tap on "Back" icon of page
	Then I should see "estimated cost" on Bungii estimate
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	Then for a Bungii I should see "Bungii search screen"
	
	Then I click on notification for "on demand trip"
	Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
	When I click "YES" button on alert message
	And I click "ACCEPT" button on the "Bungii Request" screen
	
	And I Switch to "customer" application on "same" devices
	When I click "Ok" button on the "BUNGII ACCEPTED" screen
	
	And I Switch to "driver" application on "same" devices
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And I Switch to "customer" application on "same" devices
	Then Bungii customer should see "correct details with promo" on Bungii completed page
	And I tap on "OK on complete" on Bungii estimate
	And I tap on "No free money" on Bungii estimate
	
	When I Switch to "driver" application on "same" devices
	Then Bungii driver should see "correct details" on Bungii completed page
	And Bungii Driver "completes Bungii"
	
	And I wait for "2" mins
	And I open Admin portal and navigate to "Live Deliveries" page
	And I select trip from live trips
	Then On admin trip details page "<Expected value in admin>" should be displayed
	
	Examples:
	  | Scenario          | Expected value in admin |
	  | Promoter type      | promo                   |

#	  Core-3773: Change payment status of Customer deliveries with promoter promo codes applied
	@ready
	Scenario: Change payment status of Customer deliveries with promoter promo codes applied
		When I Switch to "driver" application on "same" devices
		And I am logged in as "valid baltimore" driver
		And I Switch to "customer" application on "same" devices
		When I am on customer Log in page
		And I am logged in as "valid baltimore" customer
		And I enter "baltimore pickup and dropoff locations" on Bungii estimate
		And I tap on "Get Estimate button" on Bungii estimate
		And I add "1" photos to the Bungii
		And I add loading/unloading time of "15 mins"
		And I select Bungii Time as "next possible scheduled"
		And I tap on "Promo code value" on Estimate screen
		And I add "PROMOTER TYPE PROMO-1" PromoCode
		And I tap "Add" on Save Money page
		Then I should able to see expected promo code in available promo code
		And I tap on "Back" icon of page
		Then I should see "estimated cost" on Bungii estimate
		And I tap on "Request Bungii" on Bungii estimate
		And I tap on "Yes on HeadsUp pop up" on Bungii estimate


		And I Switch to "driver" application on "same" devices
		And I Select "AVAILABLE BUNGIIS" from driver App menu
		And I Select Trip from available trip
		And I tap on "ACCEPT" on driver Trip details Page
		And I Select "SCHEDULED BUNGIIS" from driver App menu
		When I Select Trip from driver scheduled trip
		And I start selected Bungii
		Then Bungii driver should see "General Instructions"
		And I slide update button on "EN ROUTE" Screen
		And I slide update button on "ARRIVED" Screen
		When Bungii driver uploads "1" image
		And I slide update button on "ARRIVED" Screen
		And I slide update button on "LOADING ITEM" Screen
		When Bungii driver uploads "1" image
		And I slide update button on "LOADING ITEM" Screen
		And I slide update button on "DRIVING TO DROP-OFF" Screen
		And I slide update button on "UNLOADING ITEMS" Screen
		When Bungii driver uploads "1" image
		And I slide update button on "UNLOADING ITEMS" Screen
		And Bungii Driver "skips to rate customer"
		And I wait for "2" mins
		Then I should be navigated to "Bungii completed" screen
		And I wait for "2" mins

		And I open new "Chrome" browser for "ADMIN PORTAL"
		And I wait for "2" mins
		And I navigate to admin portal
		And I wait for "2" mins
		And I log in to admin portal
		And I wait for "2" mins
		And I Select "completed deliveries" from admin sidebar
		And I wait for "2" mins
		And I wait for "2" mins
		And  I search the delivery using "Customer name"
		And I select "Customer Canceled" from the dropdown
		And I select "Customer initiated - other reason" as the reason from the reason dropdown
		And I click on "Confirm Status" button
		And I should see "STATUS CHANGE SUCCESSFUL" message
		Then I click on "Close Status" button
