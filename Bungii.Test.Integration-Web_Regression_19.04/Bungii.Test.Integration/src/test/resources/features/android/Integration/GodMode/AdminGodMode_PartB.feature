@android
@adminmode
    #These feature will run in Kansas geofence
Feature: God Mode Feature Part B
      #Customer B - Testcustomertywd_appleand_B Android 9999991020
      # Driver A and B  - Testdriver_goa_a Android_test and Testdriver_goa_b Android_test

@regression
 #stable
Scenario: Admin God Mode: Verify that admin can assign one or both drivers to a duo trip when it is in searching status

When I request "duo" Bungii as a customer in "goa" geofence
| Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
| NEXT_POSSIBLE | 9999991020     | Testcustomertywd_appleand_B Android | Cci12345         |

When I open new "Chrome" browser for "ADMIN PORTAL"
And I wait for 2 minutes
When I open new "Chrome" browser for "ADMIN PORTAL"
And I navigate to admin portal
And I log in to admin portal
And I Select "Scheduled Trip" from admin sidebar
And I open the trip for "Testcustomertywd_appleand_B Android" the customer
And I Select "Edit Trip Details" option
And I assign driver for the "Duo" trip
And I click on "VERIFY" button
And The "Your changes are good to be saved." message is displayed
Then I click on "SAVE CHANGES" button
And The "Bungii Saved!" message is displayed

When I switch to "ORIGINAL" instance
And I Switch to "customer" application on "same" devices
And I am logged in as "Testcustomertywd_appleand_B Android" customer
And I tap on "Menu" > "MY BUNGIIS" link
And I select already scheduled bungii
Then I verify the "duo driver names"

And I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| 9999991020      |                 |


@regression
    #stable
Scenario: Admin God Mode: Verify that admin can assign a driver to a Solo Scheduled trip when it has been re-searched
Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_B Android"
| geofence | Bungii State | Bungii Time   |
| goa   | Accepted     | NEXT_POSSIBLE |
  
And I open Admin portal and navigate to "Scheduled Deliveries" page
  Then I wait for "2" mins
  And I open the trip for "Testcustomertywd_appleand_B Android" the customer
And I remove current driver and researches Bungii
  
  And I open Admin portal and navigate to "Scheduled Deliveries" page
  Then I wait for "2" mins
And I open the trip for "Testcustomertywd_appleand_B Android" the customer
And I Select "Edit Trip Details" option
And I assign driver for the "Solo" trip
And I click on "VERIFY" button
And The "Your changes are good to be saved." message is displayed
Then I click on "SAVE CHANGES" button
And The "Bungii Saved!" message is displayed

When I switch to "ORIGINAL" instance
And I Switch to "customer" application on "same" devices
Given I am on customer Log in page
And I am logged in as "Testcustomertywd_appleand_B Android" customer

And I tap on "Menu" > "MY BUNGIIS" link
And I select already scheduled bungii
Then I verify the "solo driver names"

And I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| 9999991020      |                 |


@regression
#stable
Scenario: Admin God Mode: Verify admin can assign one [controlled] driver on DUO trip when it has been re-searched
When I request "duo" Bungii as a customer in "kansas" geofence
| Bungii Time   | Customer Phone | Customer Name                         | Customer Password |
| NEXT_POSSIBLE | 8877661117     | Testcustomertywd_appleMarkDN LutherDN | Cci12345          |
  And As a driver "Testdrivertywd_appleks_a_drvb Kansas_b" and "Testdrivertywd_appleks_a_drvc Kansas_c" perform below action with respective "DUO SCHEDULED" trip
| driver1 state | driver2 state |
| Accepted      | Accepted      |
Then I wait for "2" mins
And I open Admin portal and navigate to "Scheduled Deliveries" page
And I open the trip for "Testcustomertywd_appleMarkDN LutherDN" the customer
And I remove "control" driver and researches Bungii
Then I wait for "2" mins
And I open the trip for "Testcustomertywd_appleMarkDN LutherDN" the customer
And I Select "Edit Trip Details" option
And I assign driver for the "control" trip
And I click on "VERIFY" button
And The "Your changes are good to be saved." message is displayed
Then I click on "SAVE CHANGES" button
And The "Bungii Saved!" message is displayed

When I switch to "ORIGINAL" instance
And I Switch to "customer" application on "same" devices
Given I am on customer Log in page
And I am logged in as "Testcustomertywd_appleMarkDN LutherDN" customer
And I tap on "Menu" > "MY BUNGIIS" link
And I select already scheduled bungii
Then I verify the "control driver name"

And I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| 8877661117      |                 |


@regression
  #stable
Scenario: Admin God Mode: Verify if admin can assign one [non controlled] driver on duo trip when it has been re-searched
When I request "duo" Bungii as a customer in "goa" geofence
| Bungii Time   | Customer Phone | Customer Name                         | Customer Password |
| NEXT_POSSIBLE | 8877661118     | Testcustomertywd_appleMarkDO LutherDO | Cci12345          |
  And As a driver "Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test" perform below action with respective "DUO SCHEDULED" trip
| driver1 state | driver2 state |
| Accepted      | Accepted      |
  
  Then I wait for "2" mins
And I open Admin portal and navigate to "Scheduled Deliveries" page
And I open the trip for "Testcustomertywd_appleMarkDO LutherDO" the customer
  And I remove "noncontroller" driver from Bungii and researches it

Then I wait for "2" mins
And I open the trip for "Testcustomertywd_appleMarkDO LutherDO" the customer
And I Select "Edit Trip Details" option
  And I assign "noncontroller" driver to Bungii
And I click on "VERIFY" button
And The "Your changes are good to be saved." message is displayed
Then I click on "SAVE CHANGES" button
And The "Bungii Saved!" message is displayed

When I switch to "ORIGINAL" instance
And I Switch to "customer" application on "same" devices
And I am logged in as "Testcustomertywd_appleMarkDO LutherDO" customer
And I tap on "Menu" > "MY BUNGIIS" link
And I select already scheduled bungii
Then I verify the "noncontrol driver name"

And I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| 8877661118      |                 |


@regression
  #stable
Scenario: Admin God Mode: Verify if admin can assign both drivers on duo trip when it has been re-searched
When I request "duo" Bungii as a customer in "goa" geofence
| Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
| NEXT_POSSIBLE | 9999991020     | Testcustomertywd_appleand_B Android | Cci12345         |
  And As a driver "Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test" perform below action with respective "DUO SCHEDULED" trip
| driver1 state | driver2 state |
| Accepted      | Accepted      |
And I open Admin portal and navigate to "Scheduled Deliveries" page
  Then I wait for "2" mins
  And I open the trip for "Testcustomertywd_appleand_B android" the customer
And I remove current driver and researches Bungii
  
  And I open Admin portal and navigate to "Scheduled Deliveries" page
  Then I wait for "2" mins
  And I open the trip for "Testcustomertywd_appleand_B android" the customer

And I Select "Edit Trip Details" option
And The "Adding a driver through this feature overrides driver assigning restrictions." message is displayed
And I assign driver for the "Duo" trip
And I click on "VERIFY" button
And The "Your changes are good to be saved." message is displayed
Then I click on "SAVE CHANGES" button
And The "Bungii Saved!" message is displayed

When I switch to "ORIGINAL" instance
And I Switch to "customer" application on "same" devices
And I am logged in as "Testcustomertywd_appleand_B Android" customer
And I tap on "Menu" > "MY BUNGIIS" link
And I select already scheduled bungii
Then I verify the "duo driver names"
And I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| 9999991020      |                 |

@regression
  #stable
Scenario: Admin God Mode: Verify that if admin can remove and assign controlling driver on duo trip when non controlled driver has started the trip
When I request "duo" Bungii as a customer in "goa" geofence
| Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
| NEXT_POSSIBLE | 9999991020     | Testcustomertywd_appleand_B Android | Cci12345         |
  And As a driver "GoaF DriverF" and "GoaG DriverG" perform below action with respective "DUO SCHEDULED" trip
| driver1 state | driver2 state |
| Accepted      | Enroute      |

Then I wait for "2" mins
When I open new "Chrome" browser for "ADMIN PORTAL"
And I navigate to admin portal
And I log in to admin portal
And I Select "Scheduled Trip" from admin sidebar
And I open the trip for "Testcustomertywd_appleand_B Android" the customer
And I Select "Edit Trip Details" option
And I remove "controller" driver from Bungii and researches it

Then I wait for "2" mins
And I open Admin portal and navigate to "Scheduled Deliveries" page
And I open the trip for "Testcustomertywd_appleand_B Android" the customer

And I Select "Edit Trip Details" option
And I assign "noncontroller" driver to Bungii
  
 # And I assign driver for the "control driver" trip
   #this guy will become non control once saved and trip will start
And I click on "VERIFY" button
And The "Your changes are good to be saved." message is displayed
Then I click on "SAVE CHANGES" button
And The "Bungii Saved!" message is displayed

When I switch to "ORIGINAL" instance
And I Switch to "customer" application on "same" devices
And I am logged in as "Testcustomertywd_appleand_B Android" customer
  Then for a Bungii I should see "Enroute screen"

And I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| 9999991020      |                 |
