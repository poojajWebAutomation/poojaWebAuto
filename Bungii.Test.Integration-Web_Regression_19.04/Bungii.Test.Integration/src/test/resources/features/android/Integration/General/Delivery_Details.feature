@android
@general
@bungii
    #These feature will run in Goa geofence
Feature: Bungii Details and Pickup Note
     #Testcustomertywd_appleand_F Android 9999999999
      #driverF.phone.name=Driver_goa_f Android_test 9999999996
  
  @regression
   #stable
  Scenario: Verify that for Duo trips if Admin portal displays Application error when one driver is accepted by driver and other is assigned by ADMIN
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Driver_goa_f Android_test" driver
    
    When I request "duo" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name    | Customer Password |
      | NEXT_POSSIBLE | 9403960181     | johnny oliver | Cci12345          |
  
    When I Switch to "driver" application on "same" devices
#    And I Select "Home" from driver App menu
    And I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    Then I Select "SCHEDULED BUNGIIS" from driver App menu
    
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    And I open the trip for "johnny oliver" the customer
    And I Select "Edit Trip Details" option
    And I assign driver for the "Solo" trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 9403960181     |                 |
    
  @regression
    #stable
  Scenario: Verify that the My Bungii Past trip is visible when admin manually ends bungii
    Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleMarkGJ LutherGJ"
      | geofence | Bungii State | Bungii Time     |
      | goa      | Unloading Items | 0.5 hour ahead  |
    When bungii admin manually end bungii created by "CUSTOMER1"
  
	Then I Switch to "customer" application on "same" devices
	When I am on customer Log in page
	And I am logged in as "Testcustomertywd_appleMarkGJ LutherGJ" customer
    And I tap on "Menu" > "MY BUNGIIS" link
    And "MY BUNGIIS" page should be opened
    And I click on "Past" tab
    And I open the trip for "Testdriver_goa_f Android_test" driver
    Then I verify the field "driver name"
    And I verify the field "trip cost"
  
  
  @ready
 #stable
  Scenario: Blank Customer Note : Verify that the Pickup note is not displayed as NULL or undefined when customer does not add a pickup note
    When I am on the LOG IN page on driver app
    And I am logged in as "Testdriver_goa_f Android_test" driver
    And I tap on "Go Online button" on Driver Home page
    
    When I Switch to "customer" application on "same" devices
    And I am logged in as "johnny oliver" customer
    And I enter "Goa pickup and dropoff location" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    Then "Estimate" page should be opened
    When I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
	And I wait for "1" mins
  
    When I click on notification for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" button on alert message
    Then I should be able to see "No Note" Text
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9403960181      |                 |
  
  
  @ready
    #Stable
  Scenario: Apostrophe in Customer note : Verify that Customer notes is diplayed in driver ondemand push notification
    Given I am on customer Log in page
    And I am logged in as "Testcustomertywd_appleand_F Android" customer
    
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Driver_goa_f Android_test" driver
   And I tap on "Go Online button" on Driver Home page
   
   And I Switch to "customer" application on "same" devices
    And I enter "Goa pickup and dropoff location" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "Customer note should contain apostrophe in the note's." in Additional Notes field
    And I click on "ADD NOTE" button
    Then "Estimate" page should be opened
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
  
    And I wait for "1" mins
    When I click on notification for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" button on alert message
    Then I should be able to see "Customer Note" Text
    And I reject "On Demand Bungii" request
  
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9999999999      |                 |
  
  @regression
    #Stable
  Scenario: Apostrophe in Customer note : Verify that application error is not thrown on re-search of delivery with apostrophe in Customer notes
    Given I am on customer Log in page
    And I am logged in as "Testcustomertywd_appleMarkF LutherF" customer
    
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Driver_goa_f Android_test" driver
    And I tap on "Go Online button" on Driver Home page
    
    And I Switch to "customer" application on "same" devices
    And I enter "Goa pickup and dropoff location" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "Customer note should contain apostrophe in the note's." in Additional Notes field
    And I click on "ADD NOTE" button
    And I select Bungii Time as "1 HOUR DELAY"
    Then "Estimate" page should be opened
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    
    And I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkF LutherF" the customer
    And I Select "Edit Trip Details" option
    And I assign driver for the "Solo" trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed
    When I close "Edit Trip Details" popup
  
    And I wait for "2" mins
    And I open the trip for "Testcustomertywd_appleMarkF LutherF" the customer
    And I Select "Edit Trip Details" option
    And I wait for "1" mins
    And I assign driver for the "Solo" trip
    Then I remove current driver and researches Bungii
    
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8877661005      |                 |

#    Core-2618 Verify that admin can set referral code amount on geofence settings page
  @ready
  Scenario: Verify that admin can set referral code amount on geofence settings page
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Geofence" from admin sidebar
    And I select "Chicago" geofence
    And I click on the "Settings" Button on "Geofence" Screen
    And I set "set referral code amount"
#     Core-2618 Verify admin can set no. of deliveries for referral payout on geofence setting page
    And I set "set no. of deliveries"
    Then I click on the "Save" Button on "GeofenceSettings" Screen
#     Core-2618 Verify that updating referral amount in geofence settings updates value in invite screen
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :9049840247 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I click on "$" icon
    Then I check if the amount is updated on invite screen


