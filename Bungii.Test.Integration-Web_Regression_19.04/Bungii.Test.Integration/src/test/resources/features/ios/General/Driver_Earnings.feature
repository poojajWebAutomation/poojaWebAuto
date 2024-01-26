@ios

Feature: Driver Earnings

  @ready
  Scenario: Verify earning page on driver app without tip after successful payment
    Given I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 9999999129    | Cci12345          | Testcustomertywd_appleNewRD Customer |
    And I wait for "2" mins

    And As a driver "Testdrivertywd_appledc_a_drvB WashingtonB" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
      | Enroute            |
      | Arrived            |
      | Loading Item       |
      | Driving To Dropoff |
      | Unloading Item     |
      | Bungii Completed     |

    And I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "DRIVERS" from admin sidebar
    And I search for "Testdrivertywd_appledc_a_drvB WashingtonB" driver on driver details
    And I click on "Driver Earnings" icon on driver page
    And I click on "View" icon on driver page
    And I get "Trip Earnings" from driver earnings page on admin portal for "solo driver"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledc_a_drvB WashingtonB" driver
    And I Select "EARNINGS" from driver App menu

       #    Core-2372  Verify UI of earnings page on driver app
    And I verify all the elements on earnings page
    And I get "Itemized Earnings" from earnings page

        #    Core-2469  Verify UI of itemized earnings page on driver app
    And I verify all the elements on itemized earnings page
    Then I compare with earnings from admin portal for "solo driver"


  @ready
  Scenario: Verify earning page on driver app without tip after successful payment for duo partner portal
    When I request Partner Portal "DUO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 9999999131 | Testcustomertywd_appleNewRF Customer|
    And I wait for "2" mins

    And As a driver "Testdrivertywd_appleks_a_drvy Kansas_y" and "Testdrivertywd_appleks_a_drvz Kansas_z" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Bungii Completed   | Bungii Completed   |
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "DRIVERS" from admin sidebar
    And I search for "Testdrivertywd_appleks_a_drvy Kansas_y" driver on driver details
    And I click on "Driver Earnings" icon on driver page
    And I click on "View" icon on driver page
    Then I get "Trip Earnings" from driver earnings page on admin portal for "duo first driver"
    And I Select "DRIVERS" from admin sidebar
    And I search for "Testdrivertywd_appleks_a_drvz Kansas_z" driver on driver details
    And I click on "Driver Earnings" icon on driver page
    And I click on "View" icon on driver page
    Then I get "Trip Earnings" from driver earnings page on admin portal for "duo second driver"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appleks_a_drvy Kansas_y" driver
    And I Select "EARNINGS" from driver App menu
    And I get "Itemized Earnings" from earnings page
    Then I compare with earnings from admin portal for "duo first driver"
    And I click on "BACK" button
    When I Select "ACCOUNT > LOGOUT" from driver App menu
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appleks_a_drvz Kansas_z" driver
    And I Select "EARNINGS" from driver App menu
    And I get "Itemized Earnings" from earnings page
    Then I compare with earnings from admin portal for "duo second driver"

#  Core-2345 Verify that driver can view updated pickup/drop-off address after polling refresh on app (scheduled trip)
  @ready
  Scenario: Verify that driver can view updated pickup/drop-off address after polling refresh on app (scheduled trip)
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661019     | Cci12345          | Testcustomertywd_appleMarkT LutherT |
    
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvan Kansas_an" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appleks_a_drvan Kansas_an" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkT LutherT" the customer
    And I Select "Edit Trip Details" option
    And I edit the drop off address
    Then I change the drop off address to "4800 East 63rd Street, Kansas City"
    And I edit the pickup address
    Then I change the pickup address to "6700 Lewis Road, Kansas City"
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I check if "dropoff address" is updated
    And I check if "pickup address" is updated
#   Core - 3113 Verify that driver can rate customer after delivery completes with any admin edits.
    And I start selected Bungii
    When I slide update button on "EN ROUTE TO PICKUP" Screen
    When I slide update button on "ARRIVED AT PICKUP" Screen
    When I slide update button on "LOADING ITEMS AT PICKUP" Screen
    When I slide update button on "DRIVING TO DROP-OFF" Screen
    When I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I select "4" customer rating
    And I click "Submit" button on "Rate customer" screen
    Then I should be navigated to "Bungii Completed" screen

#  Core-2117 Verify that driver can view updated pickup and drop off address after polling refresh on app (live trip)
  @ready
  Scenario: Verify that driver can view updated pickup/drop-off address after polling refresh on app (live trip)
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661013     | Cci12345          | Testcustomertywd_appleMarkN LutherN |

    And As a driver "Testdrivertywd_appleks_a_drvai Kansas_ai" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
      | Enroute            |

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appleks_a_drvai Kansas_ai" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_appleMarkO LutherO" customer
    And I Select "Edit Trip Details" option
    And I edit the drop off address
    Then I change the drop off address to "4800 East 63rd Street, Kansas City"
    And I edit the pickup address
    Then I change the pickup address to "6700 Lewis Road, Kansas City"
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
     And I swipe to check trip details
    Then I check if "dropoff address" is updated for live trip
    Then I check if "pickup address" is updated for live trip

  #    Core-2117  Verify that already accepted stacked trip does not change if current trips address(s) changes
  @ready
  Scenario: Verify that already accepted stacked trip does not change if current trips address(s) changes
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | goa      | Enroute      |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    And I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "existing" user

    And I connect to "extra1" using "Customer2" instance
    And I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid customer2" user

    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location        | Drop Location                |
      | Solo   | Creative capsule verna | Margao Railway Overbridge |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 15       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen

    When I Switch to "customer" application on "ORIGINAL" devices
    And I view and accept virtual notification for "Driver" for "stack trip"
    And stack trip information should be displayed on deck
    And try to finish time should be correctly displayed for long stack trip

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Ondemand" customer
    And I Select "Edit Trip Details" option
    And I edit the drop off address
    Then I change the drop off address to "Margao Railway Overbridge"
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    Then stack trip information should be displayed on deck

#   Core-4414 Verify Branch app link is shown for all the existing drivers which is not registered for Branch app
  @ready
  Scenario: Verify Branch app link is shown for all the existing drivers which is not registered for Branch app
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applega_a_drvae Atlanta_ae" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "EARNINGS" from driver App menu
#   Core-4524 Verify the UI and functionality of Payment setting screen
    And I click on "Payment Setting" button
    And I verify the elements of payment setting screen page
#   Core-4524: Verify that selected payment method is not updated to default when driver logs out from app
    And I click on "Change default payment" button
    When I Select "ACCOUNT > LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    Then I should be navigated to "LOG IN" screen
    And I am logged in as "Testdrivertywd_applega_a_drvae Atlanta_ae" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "EARNINGS" from driver App menu
    Then I check if "changed payment" button is displayed
#   Validating Driver not registered for Branch app in Db
    And I check "no branch registration" in db
    Then I check if "Branch app" button is displayed
    And I click on "Branch app" button
    Then I should be navigated to "default browser"

#   Core-4414 Verify Branch app link is shown for all the existing drivers who is enrolled and wallet is created
#   Driver with Branch Wallet: 9049840055
  @ready
  Scenario: Verify Branch app link is shown for all the existing drivers who is enrolled and wallet is created
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledv_b_mattI Stark_dvOnEI" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
#   Validating Driver registered for Branch app with wallet in Db
    And I check "branch registered with wallet" in db
    And I Select "EARNINGS" from driver App menu
    Then I check if "Branch app" button is displayed
    And I click on "Branch app" button
    Then I should be navigated to "default browser"

#   Core-4414 Verify Branch app link is shown for all the existing drivers who is enrolled in Branch but no wallet created
#   Driver without Branch Wallet: 9049840053
  @ready
  Scenario: Verify Branch app link is shown for all the existing drivers who is enrolled in Branch but no wallet created
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledv_b_mattG Stark_dvOnEG" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
#   Validating Driver registered for Branch app without wallet in Db
    And I check "branch registered without wallet" in db
    And I Select "EARNINGS" from driver App menu
    Then I check if "Branch app" button is displayed
    And I click on "Branch app" button
    Then I should be navigated to "default browser"