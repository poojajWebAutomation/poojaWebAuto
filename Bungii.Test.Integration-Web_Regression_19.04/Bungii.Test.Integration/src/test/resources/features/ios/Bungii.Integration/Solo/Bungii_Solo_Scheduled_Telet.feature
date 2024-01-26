@ios
@scheduled
@bungii
    # this will run in denver
Feature: Solo Scheduled Bungii - TELET
  I want to use request Scheduling Bungii with Solo type

  Background:
    #When I clear all notification
    When I Switch to "customer" application on "same" devices
    
  @regression
    #Stable
  Scenario:Verify If Driver Rating Is Shown To Customer On Bungii Details Page When Driver Accepts Scheduled Bungii
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
    And I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    Then ratting should be correctly displayed on Bungii detail page
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  #stable
  Scenario: Verify Customer Is Not Allowed To Request Bungii If TELET Time Of The New Bungii Overlaps With Already Scheduled Bungii
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                         | Customer Password |
      | NEXT_POSSIBLE | 8877661122     | Testcustomertywd_appleMarkDS LutherDS | Cci12345          |

    And I get TELET time of of the current trip
    And I am on the "LOG IN" page
    When I enter Username :8877661122 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |

    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time                | PickUpImage | Save Trip Info |
      | 30       |           |              | <TIME WITHIN TELET> | Default     | No             |
    Then user is alerted for "already scheduled bungii"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
    #stable
  Scenario: Verify If Incoming Scheduled Trip Request TELET (Trip A) Overlaps Start Time Of Previously Scheduled Trip (Trip B) Then Driver Doesnt Receive Notification Or Offline SMS
    Given that solo schedule bungii is in progress
      | geofence   | Bungii State | Bungii Time   |
      | denver10   | Accepted     | NEXT_POSSIBLE |
    And I get TELET time of of the current trip
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledv_b_mattS DenverS" driver
    
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    When I enter Username :8877661121 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen

    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |

    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time                                    | PickUpImage | Save Trip Info |
      | 30       |           |              | <START TIME WITHIN TELET OF CUSTOMER 1> | Default     | No             |

    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should able to see "zero" available trip

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone      |
      | CUSTOMER1_PHONE | CUSTOMER_PHONE_EXTRA |

  @regression
  Scenario: Verify If Incoming On-demend Trip Request TELET (Trip A) Overlaps Start Time Of Previously Scheduled Trip (Trip B) Then Driver Doesnt Receive Notification Or Offline SMS
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver11   | Accepted     | NEXT_POSSIBLE |

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledv_b_mattT DenverT" driver
    Then I change driver status to "Online"

    When I Switch to "customer" application on "same" devices

    When I request "Solo Ondemand" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                         | Customer label |
      | now         | 8877661124     | Cci12345          | Testcustomertywd_appleMarkDU LutherDU | 2              |

    And I should not get notification for "driver" for "ON DEMAND TRIP"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |

  @regression
  Scenario: Verify If Incoming Ondemand Trip TELET Overlaps Scheduled Trip TELET Then Request Should Not Be Sent To Driver
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver12   | Accepted     | 0.5 hour ahead |
    When I clear all notification
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledv_b_mattU DenverU" driver
    Then I change driver status to "Online"

    When I Switch to "customer" application on "same" devices

    When I request "Solo Ondemand" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                         | Customer label |
      | now         | 8877661127     | Cci12345          | Testcustomertywd_appleMarkDX LutherDX | 2              |

    And I should not get notification for "driver" for "ON DEMAND TRIP"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |


  @regression
    #Added case of CORE-3685 to existing script
  #stable
  Scenario: Verify If Incoming Scheduled Request Start Time (Trip 3) Overlaps With TELET Of Accepted Stacked request (Trip 2) Then Driver Doesnt Receive Scheduled Notification
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | denver13   | Enroute      |
    #When I clear all notification
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I wait for "2" mins
    When I Switch to "customer" application on "same" devices
    And I logged in as "valid existing stack" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I should be navigated to "Home" screen

    When I request "Solo Ondemand" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      | Customer label |
      | now         | 8877661128     | Cci12345          | Testcustomertywd_appleMarkDY LutherDY | 2              |
    
    And I view and accept virtual notification for "Driver" for "stack trip"

    And I get TELET time of currrent trip of customer 2

    And I Switch to "customer" application on "same" devices
    When I Select "ACCOUNT > ACCOUNT INFO" from Customer App menu
    Then I should be navigated to "ACCOUNT INFO" screen
    And I click "Delete account" button on "ACCOUNT INFO" screen
    #And I confirm the account deletion for customer
    And I enter "valid" password and click on delete button
    Then I should see "Account can't be deleted due to pending deliveries" message
    And I click "Cancel" button on "Delete Account" screen
    When I Select "ACCOUNT > LOGOUT" from Customer App menu
    Then I should be navigated to "LOG IN" screen
    #Given I am on the "LOG IN" page
    When I enter Username :8877661129 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen

    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |

    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time                              | PickUpImage | Save Trip Info |
      | 30       |           |              | <TIME WITHIN TELET OF CUSTOMER 2> | large image | Yes            |
    When I click "Done" button on "Success" screen
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8877661128      |

 
       #its scheduled time not initial request time
  @regression
    #stable
  Scenario: Verify TELET Is Calculated Correctly [InitialRequestTime+EstimatedDuration*1.5+30Mins] For Solo Scheduled Delivery
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver3   | Scheduled    | NEXT_POSSIBLE |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
    #Stable
  Scenario: Verify Customer Cannot Schedule Solo Bungii That Overlaps With Another Scheduled Deliveries TELET Time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver4   | Scheduled    | NEXT_POSSIBLE |
    
    And I get TELET time of of the current trip
    Given I login as "valid denver4" customer and on Home page
    
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I try to confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time                | PickUpImage | Save Trip Info |
      | 30       |           |              | <TIME WITHIN TELET> | Default     | No             |
    Then user is alerted for "already scheduled bungii"
    And I click "Cancel" button on "Estimate" screen
    
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |              | <AFTER TELET> | Default     | No             |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888889917      |

  @regression
    #Test this case manually on QA since on QA_Auto working offline time is set as 11.45pm to 12.00am and hence both slot can't consider for this case
   #Stable
  Scenario: Verify Customer Doesnt Receives Notification When Solo Scheduled Bungii Is Requested At A Time Outside Working Hours
    Given I login as "valid denver" customer and on Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I try to schedule bungii for "today - after working hour"
    Then user is alerted for "OUTSIDE BUISSNESS HOUR"
    When I try to schedule bungii for "tommorow - before working hour"
    Then user is alerted for "OUTSIDE BUISSNESS HOUR"
  
    
  @knownissue
  #KNOWN ISSUE , TELET TIME IS NOT RECALCULATED
  Scenario: Verify TELET Of Re-searched Trip Should Not Be Same As That Of Previous Trip - KNOWN ISSUE
	Given that solo schedule bungii is in progress
	  | geofence | Bungii State | Bungii Time   |
	  | denver   | Accepted     | NEXT_POSSIBLE |
	And I get TELET time of of the current trip
	Then Telet time of current trip should be correctly calculated
	When I Switch to "driver" application on "same" devices
	And I am on the "LOG IN" page on driverApp
	And I am logged in as "valid denver" driver
	When I Switch to "customer" application on "same" devices
	Then I wait for "1" mins
	And I open Admin portal and navigate to "Scheduled Deliveries" page
	And I remove current driver and researches Bungii
	When I switch to "ORIGINAL" instance
	When I Switch to "driver" application on "same" devices
	Then Telet time of research trip should be not be same as previous trips

    #CORE-3606 :Verify Customer Signature screen is shown on driver app for Partner trips
  @ready
  Scenario:Verify Customer Signature screen is shown on driver app for Partner trips
    When I request Partner Portal "SOLO" Trip for "Cort Furniture" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      | atlanta| NEXT_POSSIBLE | 8877661075 | Testcustomertywd_appleMarkBX LutherBX|
    And As a driver "Testdrivertywd_applega_a_drvac Atlanta_ac" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |

    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_appleMarkBX LutherBX" customer
    And I edit the drop off address
    Then I change the drop off address to "100 Robin Road Extension"
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I get the new pickup reference generated

    And I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applega_a_drvac Atlanta_ac" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I click "More Options" button on "update" screen
    And I click "Customer Signature" button on "update" screen
    #CORE:4665:Verify that Customer or partner name is shown to driver if drop-off contact name was left blank in partner portal delivery creation
    Then I should see the customers name under the customer name field

    When I request "Solo" Bungii as a customer in "atlanta" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | 1_DAY_LATER | 9284174823       | Krishna Hoderker|
    
    And I should be able to add the text "Signed By customer" in the signed by field
    And I should be able to add customer signature
    And I click on "Clear Signature" button
    And I view and accept virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
    And I should be able to add customer signature
    And I click "Submit Data" button on "update" screen
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBX LutherBX" the customer
    And I select "Admin Canceled" from the dropdown
    And I select "Customer initiated - other reason" as the reason from the reason dropdown
    And I click on "Confirm Status" button
    And I click on "Cancel Status" button
    And I wait for 2 minutes
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBX LutherBXr" the customer
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I should see the customer signature row "Present" in admin portal all delivery details page
    And The customer signature field is "Signature Present"

  #CORE-3606 :Verify Customer signature can be skipped on driver app
  @ready
  Scenario:Verify Customer signature can be skipped on driver app
    When I request Partner Portal "SOLO" Trip for "BestBuy2 service level" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |baltimore| NEXT_POSSIBLE | 8877661076 | Testcustomertywd_appleMarkBY LutherBY|
    And As a driver "TestDrivertywd_applemd_a_billF Stark_bltTwOF" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted     |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "TestDrivertywd_applemd_a_billF Stark_bltTwOF" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I should see the "Customer signature" header "Displayed"
   #CORE-4665:Verify that drop-off contact name populates in customer name on driver app for partner portal trips
    Then I should see the dropoff contact name under the customer name field
    And I click on "Skip Customer Signature" button
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkBY LutherBY" the customer
    #CORE-4656:Verify customer signature settings on Admin portal when it is configured as Enabled and Not Required
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I should see the customer signature row "Present" in admin portal all delivery details page
    And The customer signature field is "Not required N/A"

#CORE-3606 :Verify customer signature screen is shown for only the control driver when he/she completes the trip first
  @ready @duo
  Scenario:Verify customer signature screen is shown for only the control driver when he/she completes the trip first
    When I request Partner Portal "Duo" Trip for "Cort Furniture" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      | atlanta| NEXT_POSSIBLE | 8877661077 | Testcustomertywd_BppleMarkBZ LutherBZ|

    And As a driver "Testdrivertywd_applega_a_drvad Atlanta_ad" and "Testdrivertywd_applega_a_drvae Atlanta_ae" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And  I search the delivery using "Pickup Reference"
    When I click on the "Edit" button from the dropdown
    And I select the first driver
    And I click on "Remove Driver" button
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_applega_a_bryan Stark_altFour" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I get the new pickup reference generated
    And As a driver "Testdrivertywd_applega_a_drvae Atlanta_ae" and "Testdrivertywd_applega_a_bryan Stark_altFour" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Driving To Drop-off       | Driving To Drop-off       |

    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applega_a_drvae Atlanta_ae" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    And I connect to "extra1" using "Driver2" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applega_a_bryan Stark_altFour" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click on "Got It" button

    When I Switch to "driver" application on "Driver2" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click on "Got It" button

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I should see the "Customer signature" header "Displayed"
    Then I should see the customers name under the customer name field
    And I should be able to add the text "Signed By customer" in the signed by field
    And I should be able to add customer signature
    And I click on "Clear Signature" button
    And I should be able to add customer signature
    And I click "Submit Data" button on "update" screen
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I should be navigated to "Rate duo teammate" screen

    When I Switch to "driver" application on "Driver2" devices
    Then I should see the "Customer signature" header "Not Displayed"
    And I should be navigated to "Rate duo teammate" screen

#CORE-3606 :Verify customer signature screen is shown only for control driver , even when non control driver completes trip firstfirst
  @ready @duo
  Scenario:Verify customer signature screen is shown only for control driver , even when non control driver completes trip first
    When I request Partner Portal "Duo" Trip for "Cort Furniture" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      | atlanta| NEXT_POSSIBLE | 8877661078 | Testcustomertywd_BppleMarkCA LutherCA|
    And As a driver "Testdrivertywd_applega_a_drvaf Atlanta_af" and "Testdrivertywd_applega_a_drvag Atlanta_ag" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Driving To Drop-off  |  Driving To Drop-off  |
    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applega_a_drvaf Atlanta_af" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    And I connect to "extra1" using "Driver2" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applega_a_drvag Atlanta_ag" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click on "Got It" button
    #CORE-4007 :To verify DUO Team mates animation when non control driver reaches Arrived and Control driver is yet to reach
    Then The "Contact Duo Teammate" "Animation Text" should be displayed
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I should be navigated to "Rate duo teammate" screen

    When I Switch to "driver" application on "ORIGINAL" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    And I click on "Got It" button
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I should see the "Customer signature" header "Displayed"
    Then I should see the customers name under the customer name field
    And I should be able to add the text "Signed By customer" in the signed by field
    And I should be able to add customer signature
    And I click "Submit Data" button on "update" screen
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    And I should be navigated to "Rate duo teammate" screen

#CORE-3606 :Verify driver app when admin completes the trip before signature is taken
  @ready
  @duo
  Scenario:Verify driver app when admin completes the trip before signature is taken
    When I request Partner Portal "Solo" Trip for "Cort Furniture" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      | atlanta| NEXT_POSSIBLE | 8877661079 | Testcustomertywd_BppleMarkCB LutherCB|
    And As a driver "Testdrivertywd_applega_a_drvah Atlanta_ah" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted     |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applega_a_drvah Atlanta_ah" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I open the trip for "Testcustomertywd_BppleMarkCB LutherCB" the customer
    And I click on "Edit" link beside live delivery
    And I click on "Edit Delivery Status" radiobutton
    And I click on "Delivery Completed" radiobutton
    And I enter delivery completion date and time as per geofence
    And I click on "CALCULATE COST" button
    Then Confirmation message on edit live delivery pop up should be displayed
    And I click on "CONFIRM CHANGES" button
    Then The "Pick up has been successfully updated." message should be displayed for live delivery
    And I click on "CLOSE" button
    And I wait for 2 minutes
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_BppleMarkCB LutherCB" the customer
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I should see the customer signature row "Present" in admin portal all delivery details page
    #CORE-4656:To verify customer signature settings on Admin portal when it is configured as Required N/A on Partner management
    And The customer signature field is "Required N/A"

    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    Then I see "Rate customer" screen
    And I select "4" customer rating
    And I click "Submit" button on "Rate customer" screen
    Then I should be navigated to "Bungii Completed" screen

#CORE-3606:Verify driver app when admin completes the trip after signature is taken
  @ready
  Scenario:Verify driver app when admin completes the trip after signature is taken
    When I request Partner Portal "Solo" Trip for "Cort Furniture" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      | atlanta| NEXT_POSSIBLE | 8877661080 | Testcustomertywd_BppleMarkCC LutherCC|
    And As a driver "Testdrivertywd_applega_a_drvai Atlanta_ai" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted     |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applega_a_drvai Atlanta_ai" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I should see the "Customer signature" header "Displayed"
    Then I should see the customers name under the customer name field
    And I should be able to add the text "Signed By customer" in the signed by field
    And I should be able to add customer signature
    And I click "Submit Data" button on "update" screen

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I open the trip for "Testcustomertywd_BppleMarkCC LutherCC" the customer
    And I click on "Edit" link beside live delivery
    And I click on "Edit Delivery Status" radiobutton
    And I click on "Delivery Completed" radiobutton
    And I enter delivery completion date and time as per geofence
    And I click on "CALCULATE COST" button
    Then Confirmation message on edit live delivery pop up should be displayed
    And I click on "CONFIRM CHANGES" button
    Then The "Pick up has been successfully updated." message should be displayed for live delivery
    And I click on "CLOSE" button
    And I wait for 2 minutes
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_BppleMarkCC LutherCC" the customer
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I should see the customer signature row "Present" in admin portal all delivery details page
    And The customer signature field is "Signature Present"

    And I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    Then I see "Rate customer" screen
    And I select "4" customer rating
    And I click "Submit" button on "Rate customer" screen
    Then I should be navigated to "Bungii Completed" screen

