@ios
@scheduled
@bungii
    # this will run in denver
Feature: Solo Scheduled Bungii Part B
  I want to use request Scheduling Bungii with Solo type

  Background:
    When I Switch to "customer" application on "same" devices
    
  @regression
   #stable
  Scenario: Verify Scheduled Bungii Notification Information of Estimated Earnings Date etc
    When I Switch to "driver" application on "same" devices
    And I login as "valid denver" driver on "same" device and make driver status as "Online"
    When I Switch to "customer" application on "same" devices

    And I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      |
      | now         | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark |
    
    And I view and check virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
    #stable
  Scenario:Verify If Driver Receives Scheduled Duo Bungii Request While In Offline State
    When I Switch to "driver" application on "same" devices
    And I login as "valid denver" driver on "same" device and make driver status as "OFFLINE"

    And I Switch to "customer" application on "same" devices
    And I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      |
      | now         | 8877661156     | Cci12345          | Testcustomertywd_BppleMarkFA LutherFA|
    And I view and check virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8877661156     |                 |

  @ready
  #stable
  Scenario: Verify If Driver Receives More Than One Requests Then He Cant Accept The Bungii whos TELET time overlaps With Already accepted Solo Scheduled Bungiis
    Given I Switch to "customer" application on "same" devices

    #trip 1
    #Change the Bunggi time from 15 minutes ahead to 0.5 hour ahead since minimum schedule tiem at geofence is set to 30 minutes
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver   | Scheduled    | 0.5 hour ahead |
     #trip 2
    Given I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                      | Customer label |
      | NEXT_POSSIBLE | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark | 2              |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
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
  Scenario: Verify Driver Receives Alert Stating That The Trip Has Already Been Accepted By Him If He Receives Request Notification After Accepting The Trip From Available Trips
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    
    Given I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                      |
      | NEXT_POSSIBLE | 8877661157     | Cci12345          | Testcustomertywd_appleMarkFB LutherFB |
    
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message

	And I view and try accepting virtual notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
    Then user is virtually alerted for "PICKUP ALREADY ACCEPTED BY YOU"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8877661157     |                 |

  @regression
  #stable
  Scenario: Verify Status Of Scheduled Bungii In The Scheduled Trip Page When Only One Driver Accepts It

    And I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8877661158     | Testcustomertywd_appleMarkFC LutherFC | Cci12345          |

    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      |               |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9999998086 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then trips status should be "contacting other driver"
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And "correct duo scheduled trip details" should be displayed on Bungii Details screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8877661158     |                 |

  @regression
  #stable
  Scenario:Verify Details In The Bungii Details Screen When Required Number Of Drivers Accepts Trip

    And I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                         | Customer Password |
      | NEXT_POSSIBLE | 8877661159     | Testcustomertywd_appleMarkFD LutherFD | Cci12345          |

    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9999998086 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then trips status should be "estimated cost of duo trip"
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And "correct duo scheduled trip details" should be displayed on Bungii Details screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8877661159     |                 |

  @ready
    #Added case of CORE-3685 to existing script
  Scenario: Verify Customer Receives Notification When Driver Starts Solo Scheduled Bungii
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
      | Enroute       |
    When I Select "ACCOUNT > ACCOUNT INFO" from Customer App menu
    Then I should be navigated to "ACCOUNT INFO" screen
    And I click "Delete account" button on "ACCOUNT INFO" screen
    #And I confirm the account deletion for customer
    And I enter "valid" password and click on delete button
    Then I should see "Account can't be deleted due to active deliveries" message
    And I click "Cancel" button on "Delete Account" screen
    And I click on notification for "Customer" for "DRIVERS ARE ENROUTE"
    #this notification is not logged in the db since(browserstack issue)
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @ready
  Scenario: Verify Driver Doesnt Receive Scheduled Trip Request If His Home Is Over 30 Mins Away From Pickup Location
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    When I Switch to "driver" application on "same" devices
    And I am logged in as "valid denver" driver
    And I Switch to "customer" application on "same" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                        | Drop Location                    | Geofence |
      | Solo   | Edmondson Trail Head  Colorado Springs | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
      | 30       |           |              | NEXT_POSSIBLE | Default     |
    Then I should be navigated to "Success" screen
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @ready
  Scenario: Verify Re-searched Trip Request Show Urgent Notification Text If Admin Re-searches Bungii Less Than Hour From Scheduled Trip Time Or For Trip Time Between 24 Hours Prior To Current Time
    When I clear all notification
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted     | NEXT_POSSIBLE |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    When I Switch to "customer" application on "same" devices
    Then I wait for "1" mins
    And I open Admin portal and navigate to "Scheduled Deliveries" page

    And I remove current driver and researches Bungii
    When I switch to "ORIGINAL" instance
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    When I Switch to "customer" application on "same" devices
    And Notification for "driver" for "URGENT SCHEDULED PICKUP AVAILABLE" should be displayed
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @ready
 #stable
  Scenario: Verify Alert Message Is Displayed When Customer Tries To Contact Driver More Than One Hour From Scheduled Time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver   | Accepted     | 1.25 hour ahead |
    
    Given I login as "valid denver" customer and on Home page
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    When I try to contact driver using "sms driver1"
    Then user is alerted for "more than 1 hour from scheduled time"
    Then correct support details should be displayed to customer on "SMS" app
    When I try to contact driver using "call driver1"
    Then user is alerted for "more than 1 hour from scheduled time"
    Then correct support details should be displayed to customer on "SMS" app
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  @regression
    #stable
    @authfailure
  Scenario: Verify Customer Can Contact Control Driver When Non-control Driver Starts The Trip
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                         | Customer Password |
      | NEXT_POSSIBLE | 8877661160     | Testcustomertywd_BppleMarkFE LutherFE | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      |    Accepted   | Enroute       |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    When I try to contact driver using "sms driver1"
    Then correct support details should be displayed to customer on "SMS" app
    When I try to contact driver using "sms driver2"
    Then correct support details should be displayed to customer on "SMS" app
    #CAll app doesnt open on browserstack devices
    #When I try to contact driver using "call driver2"
    #Then correct support details should be displayed to customer on "call" app
    #When I try to contact driver using "call driver1"
    #Then correct support details should be displayed to customer on "call" app
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8877661160     |                 |


