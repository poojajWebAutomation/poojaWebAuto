    @android
      #Let this feature run with non critical features
    #These feature will run in Goa geofence
    Feature: Admin God Mode Feature
  # Customer  Testcustomertywd_appleand_A Android - 9393939393
  # Driver Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test
      
      @regression
        #Stable
      Scenario: Verify that the driver can be assigned to a solo scheduled trip irrespective of drive time to pickup
        When I Switch to "customer" application on "same" devices
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_A Android" customer
    
        And I enter "far off Goa pickup and dropoff locations" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "30 mins"
        And I add "1" photos to the Bungii
        And I select Bungii Time as "next possible scheduled"
        And I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I check if the customer is on success screen
        And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I search the delivery of Customer
        When I click on the "Edit" button from the dropdown
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed
        
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |

        When I switch to "ORIGINAL" instance
        And I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_a Android_test" driver
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        #CORE-2718:Driver was assigned to delivery and could not see it in app issue
        Then I should able to see "one" scheduled trip
     
        
      @regression
      Scenario: Verify that correct date of the trip is displayed in past bungii screen
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleMarkFF LutherFF"
          | geofence | Bungii State | Bungii Time  |
          | goa  | Bungii Completed     | NEXT_POSSIBLE |
    
        When I Switch to "customer" application on "same" devices
		Given I am on customer Log in page
		And I am logged in as "Testcustomertywd_appleMarkFF LutherFF" customer
        
        And I tap on "Menu" > "My Bungiis" link
        And "MY BUNGIIS" page should be opened
        And I click on "Past" tab
        When I view last completed bungii
        Then correct date of the trip is displayed as per the timezone of the geofence
    
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8877661161      |                 |
  
      @regression
        #stable
      Scenario: Verify that admin can assign a driver to a solo trip when it is in searching status
     
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time   |
          | goa  | Scheduled    | NEXT_POSSIBLE |
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        And I Select "Edit Trip Details" option
        And I assign driver for the "Solo" trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed
    
        When I switch to "ORIGINAL" instance
        And I Switch to "customer" application on "same" devices
        And I am logged in as "Testcustomertywd_appleand_A Android" customer
        And I tap on "Menu" > "MY BUNGIIS" link
        And I select already scheduled bungii
        Then I verify the "solo driver names"
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |
  
      @regression
        #stable
      Scenario: Verify updating time to past time and date
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time  |
          | goa  | Scheduled | NEXT_POSSIBLE |
    
        And I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        And I Select "Edit Trip Details" option
        And I change the "trip time to past" to future time
        And I Select reason as "Partner initiated" to edit datetime
        And I click on "VERIFY" button
        Then The "Please check the date/time selected. You cannot select a past date/time." message is displayed
    
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |
  
      @regression
        #web
      Scenario: Verify that the list displays all completed Bungiis in descending order of date
      #Given I am logged in as "Testcustomertywd_appleand_A Android" customer
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
        And As a driver "GoaH DriverH" and "GoaI DriverI" perform below action with respective "Duo Scheduled" trip
          | driver1 state      | driver2 state      |
          | Bungii Completed   | Bungii Completed   |
        
  
      @ready
     #stable
      Scenario: Verify that the driver can be assigned to a duo trip irrespective of driver home address
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_A Android" customer
    
        When I Switch to "customer" application on "same" devices
        And I enter "far off Goa pickup and dropoff locations" on Bungii estimate
        And I tap on "two drivers selector" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I select Bungii Time as "2 HOUR DELAY"
        And I add loading/unloading time of "30 mins"
        And I add "1" photos to the Bungii
        And I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I check if the customer is on success screen
        And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I assign driver "Testdriver_goa_b Android_test" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed
    
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |
  
  
      @regression
      #stable
        #web
      Scenario: Verify if admin can update date_time for a solo trip for which no driver has accepted
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time  |
          | goa  | Scheduled | NEXT_POSSIBLE |
    
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        Then I wait for "2" mins
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        And I Select "Edit Trip Details" option
        And I change the "trip time" to future time
        And I Select reason as "Partner initiated" to edit datetime
        And I click on "VERIFY" button
        Then The "Your changes are good to be saved." message is displayed
        And I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed
        When I close "Edit Trip Details" popup
    
        And I wait for "2" mins
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        When I Select "Research Driver" option
        When I close "Edit Trip Details" popup
        And I wait for "2" mins
  
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
		And I Select "Research Driver" option
        Then I should see updated data time
    
        Then I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |
  
      @regression
        #web
      Scenario: Verify the DUO trip started by non control driver and control driver is removed and new driver is added to the same trip
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
        And As a driver "GoaD DriverD" and "GoaE DriverE" perform below action with respective "Duo Scheduled" trip
          | driver1 state  |  driver2 state  |
          | Accepted       |  Enroute       |
    
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        Then I remove "control" driver and researches Bungii
    
        Then I wait for "2" mins
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
    
        When I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |
  
      @regression
        #web
      Scenario: Verify if admin can update date_time for a solo trip for which a driver has accepted and Customer has no conflicting trips at the new time
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time  |
          | goa  | Accepted | NEXT_POSSIBLE |
    
        And I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        And I Select "Edit Trip Details" option
        And I change the "trip time" to future time
        And I Select reason as "Partner initiated" to edit datetime
        And I click on "VERIFY" button
        Then The "Your changes are good to be saved." message is displayed
        And I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed
        When I close "Edit Trip Details" popup
    
        And I wait for "2" mins
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        When I Select "Research Driver" option
        When I close "Edit Trip Details" popup
        And I Select "Scheduled Trip" from admin sidebar
        And I wait for "2" mins
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        Then Updated time change is displayed
    
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393 |                 |
		
  
  
      @ready
        #stable
    #web scenario
      Scenario: DUO: Verify that if non control driver starts delivery and control driver is then removed by Admin and assigned with new driver then noncontroller driver becomes control driver
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
        And As a driver "Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test" perform below action with respective "DUO SCHEDULED" trip
          | driver1 state | driver2 state |
          | Accepted      | Enroute      |
    
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" the customer
        And I remove "control" driver and researches Bungii
  
        Then I wait for "2" mins
        And I open the trip for "Testcustomertywd_appleand_A Android" the customer
        And I Select "Edit Trip Details" option
        And I check if a validation message "Driver 1: Add driver below or Bungii driver search will continue" is shown
        And I assign driver for the "control" trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed
        
        #need to work on this step
        And I verify that noncontrol driver becomes control driver
    
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |
  
      @regression
      #web
      Scenario: Verify that the date and time displayed in edit Schedule bungii page against a drivers schedule list is proper timezone and not in UTC
        Given I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_a Android_test" driver
        And I tap on "Go Online button" on Driver Home page
    
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time   |
          | goa      | Accepted     | NEXT_POSSIBLE |
        And I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" the customer
        Then I check that time is not displayed in UTC
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |
  
	  @regression
        #stable
    #web scenario
	  Scenario: Verify that Admin is NOT allowed to add multiple driver for solo bungii and more than 2 drivers for Duo Delivery
		When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
		  | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
		  | NEXT_POSSIBLE | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
		When I request another "duo" Bungii as a customer in "goa" geofence
		  | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
		  | NEXT_POSSIBLE | 9823741003     | Testcustomertywd_appleNwBBB CustBBB | Cci12345          |
		Then I wait for "2" mins
		When I open new "Chrome" browser for "ADMIN PORTAL"
		And I navigate to admin portal
		And I log in to admin portal
		And I Select "Scheduled Trip" from admin sidebar
		And I open the trip for "Testcustomertywd_appleand_A Android" the customer
		And I Select "Edit Trip Details" option
		And I assign driver for the "Solo" trip
		Then I am not allowed to assign more drivers
		And I click on "Close" button
	
		When I open the trip for "Testcustomertywd_appleNwBBB CustBBB" the customer
		And I Select "Edit Trip Details" option
		And I assign driver for the "Duo" trip
		Then I am not allowed to assign more drivers
	
		And I cancel all bungiis of customer
		  | Customer Phone  | Customer2 Phone |
		  | 9393939393      | 9823741003      |
  
  
      @ready
      #stable
        #web
      Scenario: Verify that changing date and time for a scheduled bungii for which the assigned driver has a conflicting bungii during the newly selected time [Admin can Override]
        When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
        And I save the Bungii Time
        And As a driver "Testdriver_goa_a Android_test" perform below action with respective "SOLO SCHEDULED" trip
          | driver1 state |
          | Accepted      |
        When I request another "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
          | 3 hour ahead  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
        And As a driver "Testdriver_goa_a Android_test" perform below action with respective "SECOND SOLO SCHEDULED" trip
          | driver1 state |
          | Accepted      |
        And I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        When I open the trip for "Testcustomertywd_appleand_C Android" the customer
        And I Select "Edit Trip Details" option
        And I change the "particular trip time 2 hours later" to future time
        And I Select reason as "Customer initiated" to edit datetime
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed
        And I cancel all bungiis of customer
          | Customer Phone | Customer2 Phone |
          | 9393939393     |  9999992222     |
  
      @regression
      #stable
        #web
      Scenario: Verify that changing date_time for a scheduled bungii for which the customer has a conflicting bungii during the newly selected time
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time     |
          | goa      | Accepted     | 0.5 hour ahead  |
        And I save the Bungii Time
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time     |
          | goa      | Accepted     | 3 hour ahead  |
        And I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" the customer
		And I Select "Edit Trip Details" option
        And I change the "particular trip time 2 hours later" to future time
        And I Select reason as "Partner initiated" to edit datetime
        And I click on "VERIFY" button
        Then The "It looks like customer already has a Bungii scheduled at this time. Customer can have only one Bungii at a time" message is displayed
        And I cancel all bungiis of customer
          | Customer Phone | Customer2 Phone |
          | 9393939393     |                 |
  
        
      @regression
      #web
      #stable
      Scenario: Verify that Cancel button goes off once the scheduled Trip is successfully cancelled by admin
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time   |
          | goa      | Accepted     | NEXT_POSSIBLE |
        And I wait for "2" mins
        And I open Admin portal and navigate to "Scheduled Deliveries" page
        And I Cancel Bungii with following details
          | Charge | Comments | Reason                         |
          | 0      | TEST     | Outside of delivery scope      |
        Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
        And "Cancel button" should not be displayed
  
      @regression
      #web
      #stable
      Scenario: Verify if research automatically happens if admin does not add a new driver after removal
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time  |
          | goa      | Accepted     | 0.5 hour ahead |
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I wait for "2" mins
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        And I Select "Edit Trip Details" option
        And I remove current driver from edit popup
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed
        And I wait for "2" mins
        Then new pickuref is generated
    
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |