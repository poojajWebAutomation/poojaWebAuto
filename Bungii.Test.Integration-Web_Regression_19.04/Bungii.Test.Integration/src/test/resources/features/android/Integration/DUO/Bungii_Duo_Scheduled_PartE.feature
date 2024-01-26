@android
@duo
@bungii

Feature: Bungii Duo Scheduled Part E
# All Stable 5 Cases
Background:

@regression
       #stable
Scenario:Verify When Bungii Is Not Started Driver Can Cancel Scheduled Bungii From The App :duo
Given that duo schedule bungii is in progress
| geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
| kansas   | Accepted     | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |

And I Switch to "driver" application on "same" devices
And I am on the LOG IN page on driver app
And I am logged in as "Kansas driver 1" driver
And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

And I Select "SCHEDULED BUNGIIS" from driver App menu
And I Select Trip from driver scheduled trip
When Bungii Driver "cancels Bungii request"
Then user is alerted for "FOR EMERGENCY CONTACT SUPPORT LINE"
And correct details should be displayed on the "SMS FOR CANCEL INCASE OF EMERGENCEY" app

And I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| CUSTOMER1_PHONE |                 |

@regression
    #stable
  @st
Scenario: Verify Control Driver Can Cancel Duo Bungii From The App In The First Two States Of Started Bungii :enroute
Given that duo schedule bungii is in progress
| geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
| kansas   | enroute      | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
When I Switch to "customer" application on "same" devices
Given I am on customer Log in page
And I am logged in as "valid kansas 2" customer
Then for a Bungii I should see "Enroute screen"

And I Switch to "driver" application on "same" devices
And I am on the LOG IN page on driver app
And I am logged in as "Kansas driver 1" driver
Then Bungii driver should see "Enroute screen"
And I click the "Cancel" button on "update" screen
Then Alert message with DRIVER CANCEL BUNGII text should be displayed
When I click "YES" on the alert message
And I Select "HOME" from driver App menu
Then Bungii driver should see "Home screen"

When I Switch to "customer" application on "same" devices
Then Alert message with DRIVER CANCELLED text should be displayed
When I click "OK" on alert message
Then "Home" page should be opened

And I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| CUSTOMER1_PHONE |                 |
  
  @regression
	 #stable
        #new scenario added
  Scenario: Verify Customer can Schedule Bungii for a time that doesnt overlap With Another Scheduled Trip TELET Time :Duo
	When I request "duo" Bungii as a customer in "Kansas" geofence
	  | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
	  | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
	And I get TELET time of of the current trip
	And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "SOLO SCHEDULED" trip
	  | driver1 state |
	  | Accepted      |
	
	And I login as customer "8805368840" and is on Home Page
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	And I enter "kansas pickup and dropoff locations less than 150 miles" on Bungii estimate
	And I tap on "two drivers selector" on Bungii estimate
	Then I should see "two drivers selected" on Bungii estimate
	
	And I tap on "Get Estimate button" on Bungii estimate
	Then "Estimate" page should be opened
	
	When I confirm trip with following details
	  | Day | Trip Type | Time          |
	  | 0   | DUO       | <AFTER TELET> |
	And I add loading/unloading time of "30 mins"
	And I get Bungii details on Bungii Estimate
	And I add "1" photos to the Bungii
	And I tap on "Request Bungii" on Bungii estimate
	And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	And I click "Done" button on "Success" screen
	
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  | 8805368840     |                 |

  @regression
  #stable
  Scenario: Verify If Control Driver Is Allowed To Complete The Trip And Proper Summary Is Shown
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State    | Bungii Time   | Customer        | Driver1         | Driver2         |
	  | Kansas   | unloading items | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
	
	And I Switch to "customer" application on "same" devices
	When I am on customer Log in page
	And I am logged in as "valid kansas" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	When I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "kansas driver 1" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	
	And Bungii Driver "slides to the next state"
	Then I accept Alert message for "Reminder: both driver at drop off"
	
	When I Switch to "customer" application on "same" devices
	Then Bungii customer should see "correct details for duo trip" on Bungii completed page
	And I tap on "OK on complete" on Bungii estimate
	And I tap on "No free money" on Bungii estimate
	
	When I Switch to "driver" application on "same" devices
	Then Bungii driver should see "correct details for duo trip" on Bungii completed page
	And I click "On To The Next One" button on the "Bungii Completed" screen
  
  @regression
    #stable
	@nonstable
  Scenario:Verify Driver Cannot Start Bungii If The Customer Is Currently In An Ongoing Trip - Duo
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time  | Customer        | Driver1         | Driver2         |
	  | Kansas   | Accepted     | 1 hour ahead | Kansas customer B | Kansas driver 1 | Kansas driver 2 |
	Given that ondemand bungii is in progress for the minimum distance chosen
	  | geofence | Bungii State | Driver label | Trip Label |
	  | Kansas   | Enroute      | Kansas 2     | 2          |
	And I Switch to "driver" application on "same" devices
	And I wait for "3" mins
	And I am on the LOG IN page on driver app
	And I wait for "3" mins
	And I am logged in as "Kansas driver 1" driver
	And I wait for "3" mins
	And I Select "SCHEDULED BUNGIIS" from driver App menu
	And I wait for "3" mins
	And I Select Trip from driver scheduled trip
	And I wait for "3" mins
	And I start selected Bungii
	Then I should see "CUSTOMER HAS ONGOING BUNGII" on screen
	Then I cancel all bungiis of customer
	  | Customer Phone  | Customer2 Phone |
	  | CUSTOMER1_PHONE |                 |