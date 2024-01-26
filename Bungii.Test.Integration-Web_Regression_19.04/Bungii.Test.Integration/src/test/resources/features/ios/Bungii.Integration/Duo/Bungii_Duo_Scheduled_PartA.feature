@ios
@scheduled
@DUO
@bungii
  @all
    # this will run in denver
  # Denver | Customer 8888889917, Valid denver - 9999993015
  # Denver | Driver Testdrivertywd_appledv_b_matt Stark_dvOnE -9999998086  , Testdrivertywd_appledv_b_seni Stark_dvThree -9955112208
  # Denver | Customer denver customer Valid denver & denver customer - 9999993015
  # Denver | Driver valid denver & denver driver 1 - 8888884321 | denver driver 2 - 9955112208
  
Feature: Scheduled DUO Bungii Part A
  I want to use request Scheduling Bungii with duo type

  Background:
    When I Switch to "customer" application on "same" devices

  @regression
    #stable
  Scenario: Verify Status Of Scheduled Duo Bungii Trip In Drivers Scheduled Bungiis Menu Screen When Required Number Of Drivers Have Not Accepted It
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | 15 min ahead | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I Select "MY BUNGIIS" from Customer App menu
    Then trips status should be "Contacting Drivers"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver 1 - contacting drivers"
    Then trips status on bungii details should be "driver 2 - contacting drivers"
    Then message stating contact driver should be "not be displayed"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
    #stable
  Scenario: Verify Status Of Scheduled Duo Bungii Trip In Drivers Scheduled Bungiis Menu Screen When Only One Drivers Accepts It
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      |               |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I Select "MY BUNGIIS" from Customer App menu
    Then trips status should be "Contacting Drivers"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver1 name"
    Then trips status on bungii details should be "driver 2 - contacting drivers"
    Then message stating contact driver should be "not be displayed"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
    #stable
  Scenario: Verify Status Of Scheduled Duo Bungii Trip In Customers Scheduled Bungiis Menu Screen When Both Drivers Have Accepted It
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I Select "MY BUNGIIS" from Customer App menu
    Then trips status should be "estimated cost"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver1 name"
    Then trips status on bungii details should be "driver2 name"
    Then message stating contact driver should be "displayed"

    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |
    
    
  @regression
    #stable
  Scenario: TELET - Verify Customer Cannot Schedule Duo Bungii That Overlaps With Another Scheduled Trip TELET Time
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And I get TELET time of of the current trip
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Duo    | 2052 Welton Street Denver Colorado | 3529 Ringtail Lane Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I try to confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time                | PickUpImage | Save Trip Info |
      | 30       |           |              | <TIME WITHIN TELET> | Default     | No             |
    Then user is alerted for "already scheduled bungii"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |
    
#change login
  @failed
  @regression
  Scenario:Verify That Driver Is Not Able To Accept The DUO Request If The Trip Is Already Accepted By Required Number Of Drivers
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :8888884321 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Then I wait for "1" mins
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip

    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    When I click "Cancel" on alert message if any
    And I click "ACCEPT" button on "Bungii Request" screen
    Then user is alerted for "PICKUP REQUEST NO LONGER AVAILABLE"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

#change login step 1

  @regression
  Scenario: Verify If Driver Receives More Than One Requests Then He Cant Accept The Bungii whos TELET time overlaps With Already accepted Duo Scheduled Bungiis

    Given I Switch to "customer" application on "same" devices
    #trip 1
    Given I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time  | Customer Phone | Customer Password | Customer Name                   |
      | 15 min ahead | 9999993015     | Cci12345          | Testcustomertywd_appleerwf Test |
     #trip 2
    Given I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                      | Customer label |
      | NEXT_POSSIBLE | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark | 2              |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should able to see "two" available trip
    And I Select Trip from available trip

    And I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
    Then I should able to see "zero" available trip

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888889917      |
    

  @regression
    #stable
  Scenario: Verify Driver Is Not Allowed To Start Bungii If The Customer Is Currently In An Ongoing Duo Scheduled Trip
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     | Customer        | Driver1         | Driver2         |
      | denver   | Accepted     | 1 hour ahead | denver customer | denver driver 1 | denver driver 2 |
    Given that ondemand bungii is in progress for the minimum distance chosen
      | geofence | Bungii State | Driver label | Trip Label |
      | denver   | Enroute      | driver 2     | 2          |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Then I wait for "3" mins
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then I wait for "3" mins
    And I Select Trip from scheduled trip
    And I start selected Bungii
    Then user is alerted for "CUSTOMER HAS ONGOING BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 9999993015      |
  
    
  @regression
    #stable
  Scenario: Verify If Customer Is Allowed To Rate Driver For Scheduled Duo Trip
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state    | driver2 state    |
      | Unloading items | Unloading items |
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEMS" trip status screen
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state    | driver2 state    |
      | Bungii Completed | Bungii Completed |
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "Bungii Completed" trip status screen
    And Bungii customer should see "correct rating detail for duo" on Bungii completed page
    When I select "3" Ratting star for duo Driver 1
    Then "3" stars should be highlighted for Driver1
    When I select "4" Ratting star for duo Driver 2
    Then "4" stars should be highlighted for Driver2
    When I click "DONE" button on "BUNGII COMPLETE" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    
 
  @failed
  @ready
  Scenario: Verify If Re-searched Driver Can Cancel Trip After Starting Duo Scheduled Bungii
    When I switch to "ORIGINAL" instance
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    And I wait for "2" mins
    And I remove current driver and researches Bungii
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9999998086 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen
    
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |


  @regression
 #stable
  Scenario: Verify TELET Is Calculated Correctly For Duo Scheduled Trip
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

#    Core-3107 Verify driver(s) can rate each other successfully in a duo delivery
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
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9049840049 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
#  Core-3107 Verify the elements on Driver rating page for each driver in Duo trip
    And I check all the elements are displayed on driver rating page
    And I select "4" Ratting star for solo Driver 1
#  Core-3107 Verify that comments field is correctly validated on driver rating page
    And I add a comment for driver
    And I click "Submit" button on "Rate duo teammate" screen
    And I click "Skip This Step" button on "Rate customer" screen
    And I should be navigated to "Bungii Completed" screen
    Then I check if the rating is saved in the db
    
