@android
@duo
@bungii
  #These feature will runs kansas geofence [9 scenarios]

Feature: Scheduled Duo Bungiis
	
  @regression
  @sanity
	#stable
  Scenario: Verify Customer can request Scheduled Duo Bungii [Kansas Geofence]
	Given I am logged in as "valid atlanta" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I enter "kansas pickup and dropoff locations" on Bungii estimate
	And I tap on "two drivers selector" on Bungii estimate
	Then I should see "two drivers selected" on Bungii estimate
	When I tap on "Get Estimate button" on Bungii estimate
	And I add "1" photos to the Bungii
	And I select Bungii Time as "next possible scheduled for duo"
	And I add loading/unloading time of "30 mins"
	And I get Bungii details on Bungii Estimate
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	And I check if the customer is on success screen
	And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |

#CORE-3507 : To verify that driver's vehicle info is displayed on Duo teammate scree
  @regression
  @sanity
   #stable
	@add
  Scenario: Verify Duo Bungii Completion - Android [Kansas Geofence]
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
	  | Kansas   | Accepted     | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |

	When I Switch to "customer" application on "same" devices
	And I am logged in as "valid kansas" customer

	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "kansas driver 1" driver
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then Bungii driver should see "General Instructions"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	  And Driver adds photos to the Bungii
	  And Bungii Driver "slides to the next state"
	Then I accept Alert message for "Reminder: both driver at pickup"
	And Bungii driver should see "Loading Item screen"

	When I Switch to "customer" application on "same" devices
	Then "Loading Item screen" page should be opened

	And I connect to "extra1" using "Driver2" instance
	And I Open "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "kansas driver 2" driver
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then Bungii driver should see "General Instructions"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Driver adds photos to the Bungii
	And Bungii Driver "slides to the next state"
	Then I accept Alert message for "Reminder: both driver at pickup"
	And Bungii driver should see "Loading Item screen"
	And Bungii Driver "slides to the next state"
	And Driver adds photos to the Bungii
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Driver adds photos to the Bungii
	And Bungii Driver "slides to the next state"
	Then I accept Alert message for "Reminder: both driver at drop off"

	When I Switch to "driver" application on "ORIGINAL" devices
	And Bungii Driver "slides to the next state"
	And Driver adds photos to the Bungii
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Driver adds photos to the Bungii
	And Bungii Driver "slides to the next state"
	Then I accept Alert message for "Reminder: both driver at drop off"

	When I Switch to "customer" application on "same" devices
	And Bungii customer should see "correct details" on Bungii completed page
	And I tap on "OK on complete" on Bungii estimate
	And I tap on "No free money" on Bungii estimate

	When I Switch to "driver" application on "same" devices
	Then Bungii driver should see "correct details" on Bungii completed page
	Then Bungii Driver "completes Bungii"

	And I Switch to "driver" application on "Driver2" devices
	Then Bungii driver should see "correct details" on Bungii completed page
	Then Bungii Driver "completes Bungii"
	
  @regression
	#stable
  Scenario: Verify that Duo scheduled Bungii can be started 1 hr before the scheduled Trip start time
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time    | Customer | Driver1 | Driver2        |
	  | Kansas   | Accepted     | 1 hour ahead | Kansas customer | Kansas driver 1 | Kansas driver 2 |
	
	And I Switch to "driver" application on "same" devices
	And I wait for "3" mins
	And I am on the LOG IN page on driver app
	And I am logged in as "Kansas driver 1" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I wait for "3" mins
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I wait for "2" mins
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then Bungii driver should see "General Instructions"
	Then Bungii driver should see "Enroute screen"
	Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	
	And I connect to "extra1" using "Driver2" instance
	And I Open "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	Then I am logged in as "Kansas driver 2" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then Bungii driver should see "General Instructions"
        Then Bungii driver should see "Enroute screen"
	
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
  
  @regression
	#Stable
  Scenario: Verify that Duo scheduled Bungii can be started 30 mins before the scheduled Trip start time
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time    | Customer | Driver1 | Driver2        |
	  | Kansas   | Accepted     | 0.5 hour ahead | Kansas customer | Kansas driver 1 | Kansas driver 2 |
	
	When I Switch to "driver" application on "ORIGINAL" devices
	And I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "Kansas driver 1" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
	Then Bungii driver should see "General Instructions"
        Then Bungii driver should see "Enroute screen"
 
	And I connect to "extra1" using "Driver2" instance
	And I Open "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "non controller kansas driver 2" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I Select Trip from driver scheduled trip
	And Bungii Driver "Start Schedule Bungii" request
  	Then Bungii driver should see "General Instructions"
        Then Bungii driver should see "Enroute screen"
 
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |
  
	
  @regression
	#stable
	@q
  Scenario: Verify Customer Are Notified When One Driver Cancels The Duo Bungii
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
	  | Kansas   | enroute     | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
  
	When I Switch to "customer" application on "same" devices
	And I am on customer Log in page
	And I am logged in as "valid kansas" customer
	And I terminate "customer" app on "same" devices
 
	And I connect to "extra1" using "Driver1" instance
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "Kansas driver 1" driver
	And I click the "Cancel" button on "update" screen
	Then Alert message with DRIVER CANCEL BUNGII text should be displayed
	When I click "YES" on the alert message
	
	When I switch to "ORIGINAL" instance
	And I click on notification for "DRIVER CANCELLED BUNGII"
	Then Alert message with DRIVER CANCELLED text should be displayed
	
	
  @regression
   #stable
	@nonstable
	@ss
  Scenario: Verify Driver Notification When Other Driver Cancels Duo Bungii
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer        | Driver1            | Driver2         |
	  | Kansas   | enroute     | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
  
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "Kansas driver 1" driver
        And I terminate "driver" app on "same" devices
    #driver1 in background
	And I connect to "extra1" using "Driver1" instance
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "Kansas driver 2" driver
	And I click the "Cancel" button on "update" screen
	Then Alert message with DRIVER CANCEL BUNGII text should be displayed
	When I click "YES" on the alert message
	
	When I switch to "ORIGINAL" instance
	Then I click on notification for "OTHER DRIVER CANCELLED BUNGII"
	Then Alert message with OTHER DRIVER CANCELLED BUNGII text should be displayed
  
  
  @regression
	@nonstable
  Scenario Outline: Verify Customer Amount Calculation in Admin portal For The Scheduled Duo Bungii Having Promocode <PROMO CODE> Applied To It [Kansas Geofence]
	Given I am logged in as "valid kansas" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
	And I enter "kansas pickup and dropoff locations" on Bungii estimate
	And I tap on "two drivers selector" on Bungii estimate
	Then I should see "two drivers selected" on Bungii estimate
	When I tap on "Get Estimate button" on Bungii estimate
	And I add large image photos to the Bungii
	And I add loading/unloading time of "30 mins"
	And I tap on "Promo Code" on Bungii estimate
	And I add "<PROMO CODE>" PromoCode
	And I tap "Add" on Save Money page
	And I tap on "desired Promo Code" on Bungii estimate
	And I get Bungii details on Bungii Estimate
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	And I check if the customer is on success screen
	And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
	
	And I accept and complete "kansas" geofence trip of "Kansas customer" customer as a "Kansas driver 1" and "Kansas driver 2" driver
	When I Switch to "customer" application on "same" devices
	
	Then I wait for "2" mins
	And I open Admin portal and navigate to "Deliveries" page
	And I select "The Beginning of Time" from search peroid
	And I select trip from all deliveries
	Then On admin trip details page "promo" should be displayed
	
	Examples:
	  | PROMO CODE      |
	  |PROMO DOLLAR OFF |
	  |PROMO PERCENT OFF|


#CORE-3507:To verify that for converted trip from solo to duo displays the vehicle info on drivers app -pp
@ready
Scenario:To verify that for converted trip from solo to duo displays the vehicle info on drivers app
	  When I request Partner Portal "Solo" Trip for "MRFM" partner
		  |Geofence| Bungii Time   | Customer Phone | Customer Name |
		  |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
	Then I wait for "2" mins
	When I open new "Chrome" browser for "ADMIN PORTAL"
	And I navigate to admin portal
	And I log in to admin portal
	And I Select "Scheduled Trip" from admin sidebar
	And I open the trip for "Testcustomertywd_appleNewU Customer" the customer
	And I Select "Edit Trip Details" option
	And I change delivery type from "Solo to Duo"
	And I click on "VERIFY" button
	Then I click on "SAVE CHANGES" button
	And The "Bungii Saved!" message is displayed
	When I click on "Close" button
	Then I wait for "2" mins
	And I get the new pickup reference generated
	When As a driver "Testdrivertywd_appleks_a_drvak Kansas_ak" and "Testdrivertywd_appleks_a_drvaj Kansas_aj" perform below action with respective "Duo Scheduled" partner portal trip
		| driver1 state | driver2 state |
		| Accepted      | Accepted      |
		| Enroute       | Enroute       |
	When I switch to "ORIGINAL" instance
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "Testdrivertywd_appleks_a_drvak Kansas_ak" driver
	And I click on the Duo teammate image
	Then I should see the driver vehicle information
	And I click on device "Back" button
	And I slide update button on "EN ROUTE" Screen
	And I click on the Duo teammate image
	Then I should see the driver vehicle information
	And I click on device "Back" button
	And I slide update button on "ARRIVED" Screen
	And I accept Alert message for "Reminder: both driver at pickup"
	And I click on the Duo teammate image
	Then I should see the driver vehicle information
	And I click on device "Back" button

	And I connect to "extra1" using "Driver2" instance
	And I Open "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "Testdrivertywd_appleks_a_drvaj Kansas_aj" driver
	And I slide update button on "EN ROUTE" Screen
	And I click on the Duo teammate image
	Then I should see the driver vehicle information
	And I click on device "Back" button
	And I slide update button on "ARRIVED" Screen
	Then I accept Alert message for "Reminder: both driver at pickup"
	And I slide update button on "LOADING ITEM" Screen
	And I slide update button on "DRIVING TO DROP OFF" Screen
	And I slide update button on "UNLOADING ITEM" Screen
	Then I accept Alert message for "Reminder: both driver at drop off"
	Then I should be navigated to "Rate duo teammate" screen

	When I Switch to "driver" application on "ORIGINAL" devices
	And I slide update button on "LOADING ITEM" Screen
	And I click on the Duo teammate image
	Then I should see the driver vehicle information
	And I click on device "Back" button
	And I slide update button on "DRIVING TO DROP OFF" Screen
	And I click on the Duo teammate image
	Then I should see the driver vehicle information
	And I click on device "Back" button
	And I slide update button on "UNLOADING ITEM" Screen
	Then I accept Alert message for "Reminder: both driver at drop off"
	And I should be navigated to "Rate duo teammate" screen

#CORE-3507 : To verify that vehicle info is not displayed for solo trips --pp
@ready
Scenario:To verify that for converted trip from duo to solo does not display the vehicle info on drivers app
	When I request Partner Portal "Duo" Trip for "MRFM" partner
		|Geofence| Bungii Time   | Customer Phone | Customer Name |
		|Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
	Then I wait for "2" mins
	When I open new "Chrome" browser for "ADMIN PORTAL"
	And I navigate to admin portal
	And I log in to admin portal
	And I Select "Scheduled Trip" from admin sidebar
	And I open the trip for "Testcustomertywd_appleNewU Customer" the customer
	And I Select "Edit Trip Details" option
	And I change delivery type from "Duo to Solo"
	And I click on "VERIFY" button
	Then I click on "SAVE CHANGES" button
	And The "Bungii Saved!" message is displayed
	When I click on "Close" button
	Then I wait for "2" mins
	And I get the new pickup reference generated
	And As a driver "Testdrivertywd_appleks_a_drvak Kansas_ak" perform below action with respective "Solo Scheduled" trip
		| driver1 state      |
		| Accepted           |
		| Enroute            |
	When I switch to "ORIGINAL" instance
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "Testdrivertywd_appleks_a_drvak kansas_ak" driver
	Then The bungii teammate icon should not be displayed

#CORE-3507 : To verify that vehicle info is displayed on duo teammate screen for duo customer trip
@ready
Scenario:To verify that vehicle info is displayed on duo teammate screen for duo customer trip
Given that duo schedule bungii is in progress
| geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
| Kansas   | enroute     | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
When I Switch to "driver" application on "same" devices
And I am on the LOG IN page on driver app
And I am logged in as "kansas driver 1" driver
And I click on the Duo teammate image
Then I should see the driver vehicle information
And I click on device "Back" button
And Bungii Driver "slides to the next state"
And I click on the Duo teammate image
Then I should see the driver vehicle information
And I click on device "Back" button
#CORE-4007:To verify DUO Team mates details on Customer DUO delivery
#CORE-4007:To verify DUO Team mates animation starts only at ARRIVED status(ANDROID)
Then The "Contact Duo Teammate" "Animation Text" should be displayed
And Bungii Driver "slides to the next state"
When Bungii driver uploads "1" image
And Bungii Driver "slides to the next state"
Then I accept Alert message for "Reminder: both driver at pickup"
And I click on the Duo teammate image
Then I should see the driver vehicle information
And I click on device "Back" button
Then The "Contact Duo Teammate" "Animation Text" should not be displayed

When I connect to "extra1" using "Driver2" instance
And I Open "driver" application on "same" devices
And I am on the LOG IN page on driver app
And I am logged in as "kansas driver 2" driver
And I click on the Duo teammate image
Then I should see the driver vehicle information
And I click on device "Back" button
When Bungii Driver "slides to the next state"
And I click on the Duo teammate image
Then I should see the driver vehicle information
When I click on device "Back" button
And Bungii Driver "slides to the next state"
When Bungii driver uploads "1" image
And Bungii Driver "slides to the next state"
And I click on the Duo teammate image
Then I should see the driver vehicle information
And I click on device "Back" button
And Bungii Driver "slides to the next state"
When Bungii driver uploads "1" image
And I click on the Duo teammate image
Then I should see the driver vehicle information
And I click on device "Back" button
And Bungii Driver "slides to the next state"
#CORE-4007:To verify DUO Team mates animation is not visible when driver is at DRIVING TO DROP-OFF stage(ANDROID)
Then The "Contact Duo Teammate" "Animation Text" should not be displayed
And I click on the Duo teammate image
Then I should see the driver vehicle information
And I click on device "Back" button
And Bungii Driver "slides to the next state"
#CORE-4007:To verify DUO Team mates animation when non control driver reaches Arrived and Control driver is yet to reach
#CORE-4007:To verify DUO Team mates animation when driver is at UNLOADING ITEMS stage (ANDROID)
Then The "Contact Duo Teammate" "Animation Text" should be displayed
And Bungii Driver "slides to the next state"
When Bungii driver uploads "1" image
And I click on the Duo teammate image
Then I should see the driver vehicle information
And I click on device "Back" button
And Bungii Driver "slides to the next state"
When I accept Alert message for "Reminder: both driver at drop off"
#CORE-4007:To verify DUO Team mates animation is not visible when driver has COMPLETED delivery
Then The "Contact Duo Teammate" "Animation Text" should not be displayed
Then I should be navigated to "Rate duo teammate" screen

When I Switch to "driver" application on "ORIGINAL" devices
And Bungii Driver "slides to the next state"
When Bungii driver uploads "1" image
And Bungii Driver "slides to the next state"
And Bungii Driver "slides to the next state"
And Bungii Driver "slides to the next state"
When Bungii driver uploads "1" image
And Bungii Driver "slides to the next state"
When I accept Alert message for "Reminder: both driver at drop off"
Then I should be navigated to "Rate duo teammate" screen

#CORE-3271:To verify that DUO lift icon is displayed on driver app for all duo partner deliveries
@ready
Scenario: To verify that DUO lift icon is displayed on driver app for all duo partner deliveries
When I Switch to "driver" application on "same" devices
And I am logged in as "Testdrivertywd_applens_a_kayU Stark_nsOnEU" driver
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

And I connect to "extra1" using "Driver2" instance
And I Switch to "driver" application on "same" devices
And I am logged in as "Testdrivertywd_applens_a_kayV Stark_nsOnEV" driver
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

When I request Partner Portal "Duo" Trip for "Tile Shop" partner
|Geofence| Bungii Time   | Customer Phone | Customer Name |
|nashville| NEXT_POSSIBLE | 8877661098 | Testcustomertywd_appleMarkCU LutherCU|
And I wait for 1 minutes

When I Switch to "driver" application on "ORIGINAL" devices
And I Select "AVAILABLE BUNGIIS" from driver App menu
And I Select Trip from available trip
And I get the service level time for "tileshop134" having "First Threshold" service type
Then The "Arrival time at pickup" "Text" should be displayed
Then The "Expected time at drop-off" "Text" should be displayed
Then The "Arrival time" should match
Then The "Expected time at drop-off for duo" should match
And I select "Pallet-1" from items
Then I should see "DUO LIFT" header displayed
And I tap on "ACCEPT" on driver Trip details Page

And I Switch to "driver" application on "driver2" devices
And I Select "AVAILABLE BUNGIIS" from driver App menu
And I Select Trip from available trip
Then The "Arrival time at pickup" "Text" should be displayed
Then The "Expected time at drop-off" "Text" should be displayed
Then The "Arrival time" should match
Then The "Expected time at drop-off for duo" should match
Then I should see "DUO LIFT" header displayed
And I select "Pallet-2" from items
And I tap on "ACCEPT" on driver Trip details Page

When I Switch to "driver" application on "ORIGINAL" devices
And I Select "SCHEDULED BUNGIIS" from driver App menu
And I Select Trip from driver scheduled trip
Then The "Arrival time at pickup" "Text" should be displayed
Then The "Expected time at drop-off" "Text" should be displayed
Then The "Arrival time" should match
Then The "Expected time at drop-off for duo" should match
Then I should see "DUO LIFT" header displayed
And I start selected Bungii for "Tile Shop"
Then Bungii driver should see "General Instructions"
Then The "PICKUP(Arrival time)" "Label" should be displayed

And I Switch to "driver" application on "driver2" devices
And I Select "SCHEDULED BUNGIIS" from driver App menu
And I Select Trip from driver scheduled trip
Then The "Arrival time at pickup" "Text" should be displayed
Then The "Expected time at drop-off" "Text" should be displayed
Then The "Arrival time" should match
Then The "Expected time at drop-off for duo" should match
Then I should see "DUO LIFT" header displayed
And I start selected Bungii for "Tile Shop"
Then Bungii driver should see "General Instructions"
Then The "PICKUP(Arrival time)" "Label" should be displayed


When I Switch to "driver" application on "ORIGINAL" devices
When I slide update button on "EN ROUTE" Screen
And I click on "GOT IT" button
Then The "PICKUP(Arrival time)" "Label" should be displayed
#CORE-4007:To verify DUO Team mates details for Weight based DUO Partner deliver
Then The "Contact Duo Teammate" "Animation Text" should be displayed
And I slide update button on "ARRIVED" Screen
When Bungii driver uploads "1" image
And I slide update button on "ARRIVED" Screen
Then The "PICKUP(Arrival time)" "Label" should be displayed

And I Switch to "driver" application on "driver2" devices
When I slide update button on "EN ROUTE" Screen
And I click on "GOT IT" button
Then The "PICKUP(Arrival time)" "Label" should be displayed
And I slide update button on "ARRIVED" Screen
When Bungii driver uploads "1" image
And I slide update button on "ARRIVED" Screen
Then The "PICKUP(Arrival time)" "Label" should be displayed

When I Switch to "driver" application on "ORIGINAL" devices
And I slide update button on "LOADING ITEM" Screen
When Bungii driver uploads "1" image
And I slide update button on "LOADING ITEM" Screen
Then The "DROP-OFF(Expected time)" "Label" should be displayed

And I Switch to "driver" application on "driver2" devices
And I slide update button on "LOADING ITEM" Screen
When Bungii driver uploads "1" image
And I slide update button on "LOADING ITEM" Screen
Then The "DROP-OFF(Expected time)" "Label" should be displayed

When I Switch to "driver" application on "ORIGINAL" devices
And I slide update button on "DRIVING TO DROP-OFF" Screen
Then I should see "DUO LIFT" header displayed
And I click on "GOT IT" button
#CORE-4007:To verify DUO Team mates details for Weight based DUO Partner deliver
Then The "Contact Duo Teammate" "Animation Text" should be displayed
Then The "DROP-OFF(Expected time)" "Label" should be displayed

And I Switch to "driver" application on "driver2" devices
And I slide update button on "DRIVING TO DROP-OFF" Screen
Then I should see "DUO LIFT" header displayed
And I click on "GOT IT" button
Then The "DROP-OFF(Expected time)" "Label" should be displayed

When I Switch to "driver" application on "ORIGINAL" devices
And I slide update button on "UNLOADING ITEMS" Screen
When Bungii driver uploads "1" image
And I slide update button on "UNLOADING ITEMS" Screen
Then I accept Alert message for "Reminder: both driver at drop off"
And I should be navigated to "Rate duo teammate" screen

And I Switch to "driver" application on "driver2" devices
And I slide update button on "UNLOADING ITEMS" Screen
When Bungii driver uploads "1" image
And I slide update button on "UNLOADING ITEMS" Screen
Then I accept Alert message for "Reminder: both driver at drop off"
And I should be navigated to "Rate duo teammate" screen