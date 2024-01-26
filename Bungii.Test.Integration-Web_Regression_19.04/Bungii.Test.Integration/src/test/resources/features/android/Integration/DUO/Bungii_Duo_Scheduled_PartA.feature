@android
@duo
@bungii
  #These feature will run in atlanta geofence

Feature: Bungii Duo Scheduled Part A

  #change user login
  @ready
  Scenario: Verify When Customer Cancels Duo Trip Accepted By One Driver Then Controller Driver Gets A Notification Though The App Remains In Background
    Given that duo schedule bungii is in accepted by controlled driver
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2 |
      | atlanta  | Accepted    | NEXT_POSSIBLE | valid    | valid   | NA |

    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "valid atlanta" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    #put driver on background
    When I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    Then I Cancel selected Bungii

    #When I Switch to "driver" application on "same" devices
    And I click on notification for "CUSTOMER CANCELLED SCHEDULED BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  #@regression
  @ready
    @nonstable
    @ss
  Scenario:Verify When Customer Cancels Duo Trip Accepted By One Driver Then Driver Gets A Notification When The App Remains open
    Given that duo schedule bungii is in accepted by controlled driver
      | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2 |
      | atlanta  | Accepted    | NEXT_POSSIBLE | valid    | valid   | NA |

    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I am logged in as "valid atlanta" customer
	And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I close "Tutorial" if exist
	
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
	And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
	And I Select "SCHEDULED BUNGIIS" from driver App menu
  
    When I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "MY BUNGIIS" link
    And I select already scheduled bungii
    When I Cancel selected Bungii
  
    #When I Switch to "driver" application on "same" devices
    And I click on notification for "CUSTOMER CANCELLED SCHEDULED BUNGII"
    Then Alert message with CUSTOMER CANCELLED SCHEDULED BUNGII text should be displayed
    When I click "OK" on alert message
    
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
    
    @regression
    #stable
     @sanity

      #CORE-3685(Android)--stack related change incorporated
  Scenario:  LONG STACK BUNGII: Verify Driver can accept and complete long stack Bungii [Altanta Geofence]
    Given that ondemand bungii is in progress
      | geofence | Bungii State   |
      | atlanta  | ARRIVED |
  
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
      
    And I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    And I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
  
    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I enter customers "9871450107" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I tap on "Menu" > "ACCOUNT" link
    Then "ACCOUNT INFO" page should be opened
    And I tap on the "Delete account" Link
    Then "Delete account" page should be opened
    And I enter customers "valid1" Password
    Then The user should see "snackbar validation message invalid password for account deletion" on log in page
    And I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip
    And Bungii Driver "slides to the next state"
    And Bungii Driver "skips to rate customer"
    Then Bungii Driver "completes Bungii"
  
    Then I click on notification for "CUSTOMER -Driver started stack Bungii"
    Then "Enroute screen" page should be opened
  
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
  
    When I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
  
    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii Driver "completes Bungii"
  
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 9871450107     | 8888889910      |
    
  
  @regression
    #stable
  Scenario:  SHORT STACK BUNGII: Verify Driver can accept and complete short stack Bungii [Altanta Geofence]
    Given that ondemand bungii is in progress
      | geofence | Bungii State   |
      | atlanta  | UNLOADING ITEM |
    
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
  
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
    
    And I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    Then I calculate projected driver arrival time
    And I accept Alert message for "Alert: Display Stack trip after current trip"
    
    When I Switch to "customer" application on "same" devices
    And I am on customer Log in page
    And I enter customers "9871450107" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    And I Switch to "driver" application on "ORIGINAL" devices
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for short stack trip
    And Bungii Driver "slides to the next state"
    And Bungii Driver "skips to rate customer"
    Then Bungii Driver "completes Bungii"
    And I click "Next Bungii" button on the "Bungii Completed" screen

#    Core-3098 Verify online/offline pop up is not shown when driver has accepted stack trip
    And I check online or offline pop up is not displayed
    
    Then I click on notification for "CUSTOMER -Driver started stack Bungii"
    Then "Enroute screen" page should be opened
    
    When I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    
    When I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
    
    And I Switch to "driver" application on "ORIGINAL" devices
    And Bungii Driver "skips to rate customer"
    Then Bungii Driver "completes Bungii"
    
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 9871450107     |                 |
  
  @ready
  Scenario: Manually Ending A Bungii For A Driver Who Has Stacked Bungii Should Display Summary And Start With The Stacked Bungii
    Given that ondemand bungii is in progress
      | geofence | Bungii State        |
      | atlanta  | DRIVING TO DROP OFF |
    
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver

    #switch to customer so that driver app is in background :Click by notification
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
  
    And I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    
    When I Open "customer" application on "same" devices
    And I am on customer Log in page
    And I enter customers "9871450107" Phone Number
    And I enter customers "valid" Password
    And I tap on the "Log in" Button on Login screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    
    Then for a Bungii I should see "bungii accepted screen"
    
    When I Switch to "driver" application on "same" devices
    When bungii admin manually end bungii created by "CUSTOMER1"
    Then Bungii driver should see "summary" on Bungii completed page
    Then Bungii Driver "tab On to Next"
    Then "Enroute screen" page should be opened
    
    When I Switch to "customer" application on "same" devices
    Then "Enroute screen" page should be opened
    
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |
    
 #need to do in atlanta
          #move to top
 
  @ready
    @nonstable
  Scenario: Verify Short Stack Request Acceptance By Control Driver
    Given that duo schedule bungii is in progress
      | geofence | Bungii State       | Bungii Time   | Customer | Driver1 | Driver2        |
      | atlanta  | Driving To Dropoff | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
    
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
      #put driver app in background
    And I Open "customer" application on "same" devices
    
    And I connect to "extra1" using "Driver2" instance
    And I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid driver 2" driver
      #put driver app in background
    And I Open "customer" application on "same" devices
    
    When I Switch to "customer" application on "ORIGINAL" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |
    
    #step to make driver app in background
    And I Open "customer" application on "Driver2" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "reject stack message" request

          #control driver accept  stack trip
    And I Open "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    Then I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"
    
    And I Open "driver" application on "Driver2" devices
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then I accept Alert message for "Reminder: both driver at drop off"
    Then Bungii Driver "tab On to Next"
    
    And I Switch to "driver" application on "ORIGINAL" devices
    Then Bungii Driver "tab On to Next"
    Then "Enroute screen" page should be opened
    
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |                | CUSTOMER2_PHONE |
  
  
  @ready
    @nonstable
	@s
  Scenario: Verify Bungii Details - Call SMS
	
	Given that duo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   | Customer | Driver1 | Driver2        |
	  | atlanta  | enroute      | NEXT_POSSIBLE | valid    | valid   | valid driver 2 |
	
	And I Switch to "customer" application on "same" devices
	And I am logged in as "valid atlanta" customer
	
	And I Switch to "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid atlanta" driver
	
	And I connect to "extra1" using "Driver2" instance
	And I Open "driver" application on "same" devices
	And I am on the LOG IN page on driver app
	And I am logged in as "valid driver 2" driver
	
	Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	
	When I Switch to "driver" application on "ORIGINAL" devices
	Then Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
	
	When I Switch to "customer" application on "same" devices
	Then Trip Information should be correctly displayed on "EN ROUTE" status screen for customer
	When I tap "SMS for driver 1" during a Bungii
	Then correct details should be displayed on "Driver 1 SMS" app
	#When I tap "Call for driver 1" during a Bungii
	#Then correct details should be displayed on "Driver 1 Calling" app
	When I tap "SMS for driver 2" during a Bungii
	Then correct details should be displayed on "Driver 2 SMS" app
	#When I tap "Call for driver 2" during a Bungii
	#Then correct details should be displayed on "Driver 2 Calling" app
	
	When I Switch to "driver" application on "Driver2" devices
	And Bungii Driver taps "SMS for a driver" during a Bungii
	Then correct details should be displayed to driver on "Driver 2 SMS" app
	#When Bungii Driver taps "Call for a driver" during a Bungii
	#Then correct details should be displayed to driver on "Driver 2 Calling" app
	When Bungii Driver taps "Contact support for driver" during a Bungii
	Then correct details should be displayed to driver on "Support-SMS" app
	When Bungii Driver taps "SMS for a customer" during a Bungii
	Then correct details should be displayed to driver on "SMS" app
	#When Bungii Driver taps "Call for a customer" during a Bungii
	#Then correct details should be displayed to driver on "Calling" app
	When Bungii Driver taps "Contact support" during a Bungii
	Then correct details should be displayed to driver on "Support-SMS" app
	When Bungii Driver taps "View items" during a Bungii
	Then Bungii driver should see "Pickup Item"
	
	
	When I Switch to "driver" application on "ORIGINAL" devices
	When Bungii Driver taps "SMS for a driver" during a Bungii
	Then correct details should be displayed to driver on "Driver 1 SMS" app
	#When Bungii Driver taps "Call for a driver" during a Bungii
	#Then correct details should be displayed to driver on "Driver 1 Calling" app
	When Bungii Driver taps "Contact support for driver" during a Bungii
	Then correct details should be displayed to driver on "Support-SMS" app
	When Bungii Driver taps "SMS for a customer" during a Bungii
	Then correct details should be displayed to driver on "SMS" app
	#When Bungii Driver taps "Call for a customer" during a Bungii
	#Then correct details should be displayed to driver on "Calling" app
	When Bungii Driver taps "Contact support" during a Bungii
	Then correct details should be displayed to driver on "Support-SMS" app
	When Bungii Driver taps "View items" during a Bungii
	Then Bungii driver should see "Pickup Item"
	Then I cancel all bungiis of customer
	  | Customer Phone | Customer2 Phone |
	  |  CUSTOMER1_PHONE |  |
  
  @regression
#Stable
  Scenario: Verify Duo Bungii can be Requested As An Android Customer
    Given I am logged in as "valid atlanta" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    Then I should see "two drivers selected" on Bungii estimate
    When I tap on "Get Estimate button" on Bungii estimate
    And I select Bungii Time as "next possible scheduled for duo"
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      |  CUSTOMER1_PHONE |  |


  @ready
  Scenario: Verify driver(s) can rate each other successfully in a duo delivery
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8877661051     | Testcustomertywd_appleMarkAZ LutherAZ | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_mattC Stark_dvOnEC" and "Testdrivertywd_appledv_b_mattD Stark_dvOnED" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Unloading Items | Unloading Items |

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :9049840050 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "UNLOADING ITEMS" Screen
    When Bungii driver uploads "1" image
    And I slide update button on "UNLOADING ITEMS" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
#  Core-3107 Verify the elements on Driver rating page for each driver in Duo trip
    And I check all the elements are displayed on driver rating page
    And I select "3" Ratting star for solo Driver 1
#  Core-3107 Verify that comments field is correctly validated on driver rating page
    And I add a comment for driver
    And I click "Driver Submit" button on "Rate duo teammate" screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I check if the rating is saved in the db

# Core 448: Verify Projected Arrival Time and Try to Finish time when Drop off address was edited by Admin when driver had accepted long stack trip in Arrived Status
  @ready
  Scenario:  Verify Projected Arrival Time and Try to Finish time when Drop off address was edited by Admin when driver had accepted long stack trip in Arrived Status
    Given that ondemand bungii is in progress
      | geofence | Bungii State   |
      | atlanta  | ARRIVED |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |

    And I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    And I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I Switch to "customer" application on "same" devices
    And I am logged in as "Testcustomertywd_apple_AGQFCg Test" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Ondemand" customer
    And I Select "Edit Trip Details" option
    And I edit the pickup address
    Then I change the pickup address to "Hair Kymistry, Powers"
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    When I Switch to "customer" application on "same" devices
    And I calculate the "telet" time after "changed pickup"
    Then Correct details should do be displayed on BUNGII ACCEPTED with recalculation screen for Stack screen


# Core 448: Verify Projected Arrival Time and Try to Finish time for short stack when admin edits the drop off address of the Stacked trip
  @ready
  Scenario:  Verify Projected Arrival Time and Try to Finish time for short stack when admin edits the drop off address of the Stacked trip
    Given that ondemand bungii is in progress
      | geofence | Bungii State        |
      | atlanta  | DRIVING TO DROP OFF |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |

    And I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    And I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for short stack trip

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_apple_AGQFCg Test" customer
    And I Select "Edit Trip Details" option
    And I edit the drop off address
    Then I change the drop off address to "Hair Kymistry, Powers"
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "ORIGINAL" devices
    And stack trip information should be displayed on deck
    Then try to finish time should be correctly displayed for short stack trip

    #   Core-2369: Verify time calculation for Short stack trip after editing the service level on Live deliveries
  @ready
  Scenario: Verify time calculation for Short stack trip after editing the service level on Live deliveries
    When I request Partner Portal "SOLO" Trip for "Biglots" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |atlanta  | NEXT_POSSIBLE | 8877661132 | Testcustomertywd_appleMarkEC LutherEC|
    And As a driver "Testdrivertywd_applega_a_steveE Stark_altOnEE" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
      | Driving To Dropoff |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_applega_a_steveE Stark_altOnEE" driver

    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |

    Then I click on notification for "STACK TRIP"
    And Bungii Driver "accepts stack message" request
    And I accept Alert message for "Alert: Display Stack trip after current trip"
    And stack trip information should be displayed on deck

    When I Switch to "customer" application on "same" devices
    And I am logged in as "Testcustomertywd_apple_AGQFCg Test" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_appleMarkEC LutherEC"
    And I Select "Edit Trip Details" option
    And I change the service level to "Room of Choice" in "Admin" portal
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I Switch to "customer" application on "same" devices
    And I calculate the "telet" time after "changed service level"
    Then Correct details should do be displayed on BUNGII ACCEPTED with recalculation screen for Stack screen


