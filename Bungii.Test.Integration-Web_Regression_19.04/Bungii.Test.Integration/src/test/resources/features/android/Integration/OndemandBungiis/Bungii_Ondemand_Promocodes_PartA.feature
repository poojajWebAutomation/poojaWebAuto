@android
@bungii
@general
@ondemand
  #These feature will run in baltimore geofence
Feature: On Demand Bungii Promocodes Part A
  
  @regression
  Scenario Outline: Verify Customer Can Create An Ondemand Bungii With Promocode - Case:<Scenario>
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
	And I tap "OK on Driver Accepted screen" during a Bungii
	Then for a Bungii I should see "Enroute screen"
	
	When I Switch to "driver" application on "same" devices
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Arrived screen"
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Loading Item screen"
	And Bungii Driver "slides to the next state"
	Then Bungii driver should see "Driving to DropOff screen"
	And Bungii Driver "slides to the next state"
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
	And I Select "completed deliveries" from admin sidebar
	And I select trip from all deliveries
	Then On admin trip details page "<Expected value in admin>" should be displayed
	Examples:
	  | Scenario            | Promo Code    | User            |Expected value in admin |
	  | Promo fixed         | valid         | valid baltimore |promo                   |
	  | Promo percentage    | valid percent | valid baltimore |promo                   |
	  | valid one off fixed | valid one off | valid baltimore |oneoff                  |
  
  @ready
  Scenario Outline: Verify Customer Can Create An Ondemand Bungii With First Time Promocode
	When I Switch to "driver" application on "same" devices
	And I am logged in as "valid baltimore" driver
	And I Select "HOME" from driver App menu
	Then I tap on "Go Online button" on Driver Home page
	
	When I Switch to "customer" application on "same" devices
	And I am on customer Log in page
	And I am logged in as "newly registered customer" customer
	And I enter "baltimore pickup and dropoff locations" on Bungii estimate
	And I tap on "Get Estimate button" on Bungii estimate
	And I add "1" photos to the Bungii
	And I add loading/unloading time of "15 mins"
	And I tap on "Promo code value" on Estimate screen
	
	And I add "first time" PromoCode
	And I tap "Add" on Save Money page
	Then I should able to see expected promo code in available promo code
	And I tap on "Back" icon of page
	
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	Then for a Bungii I should see "Bungii search screen"
	
	When I click on notification for "on demand trip"
	Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
	When I click "YES" button on alert message
	Then I click "ACCEPT" button on the "Bungii Request" screen
	
	When I Switch to "customer" application on "same" devices
	Then I click "Ok" button on the "BUNGII ACCEPTED" screen
	
	When I Switch to "driver" application on "same" devices
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	And Bungii Driver "slides to the next state"
	When I Switch to "customer" application on "same" devices
	Then Bungii customer should see "correct details with promo" on Bungii completed page
	And I tap on "OK on complete" on Bungii estimate
	And I tap on "No free money" on Bungii estimate
	
	When I Switch to "driver" application on "same" devices
	Then Bungii driver should see "correct details" on Bungii completed page
	And I click "On To The Next One" button on the "Bungii Completed" screen
	
	Then I wait for "2" mins
	
	And I open new "Chrome" browser for "ADMIN PORTAL"
	And I navigate to admin portal
	And I log in to admin portal
	And I Select "completed deliveries" from admin sidebar
	And I select the trip from trips
	Then On admin trip details page "<Expected value in admin>" should be displayed
	Examples:
	  | Scenario         | Expected value in admin |
	  | First time       | promo                   |
  
  @ready
  Scenario: Verify Customer Can Create Ondemand Bungii With FB Share Code
	Given that ondemand bungii is in progress
	  | geofence  | Bungii State   |
	  | baltimore | UNLOADING ITEM |
	
	And I Switch to "customer" application on "same" devices
	And I am logged in as "valid baltimore" customer
	
	And I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid baltimore" driver
	And Bungii Driver "slides to the next state"
	Then Bungii Driver "completes Bungii"
	
	And I Switch to "customer" application on "same" devices
	And I tap on "OK on complete" on Bungii estimate
	When I click "YES, I'LL TAKE $5" button on the "Promotion" screen
	And I share on "Facebook with app installed"
	
	And I Switch to "customer" application on "same" devices
	And I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    # test line to check if facebook app is not installed
	
	And I enter "baltimore pickup and dropoff locations" on Bungii estimate
	And I tap on "Get Estimate button" on Bungii estimate
	And I add loading/unloading time of "15 mins"
	And I tap on "Promo code" on Estimate screen
	Then I should able to see expected promo code in available promo code
	And The "referral code received with out first time tag" is displayed
	When I tap on "Back" button of android mobile
	And I add "1" photos to the Bungii
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	Then for a Bungii I should see "Bungii search screen"
	
	Then I click on notification for "on demand trip"
	Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
	When I click "YES" button on alert message
	And I click "ACCEPT" button on the "Bungii Request" screen
	
	And I Switch to "customer" application on "same" devices
	Then I click "Ok" button on the "Bungii Request" screen
	
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
  
