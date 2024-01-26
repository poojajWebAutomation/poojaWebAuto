@android

Feature: Driver Earnings

  @ready
  Scenario: Verify earning page on driver app without tip after successful payment
      Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
        | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
        | NEXT_POSSIBLE | 9999999129     | Cci12345          | Testcustomertywd_appleNewRD Customer |
      And I wait for "2" mins

      And As a driver "Testdrivertywd_appleks_a_drvu Kansas_u" perform below action with respective "Solo Scheduled" Delivery
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
      And I search for "Testdrivertywd_appleks_a_drvu Kansas_u" driver on driver details
      And I click on "Driver Earnings" icon on driver page
      And I click on "View" icon on driver page
      And I get "Trip Earnings" from driver earnings page on admin portal for "solo driver"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_appleks_a_drvu Kansas_u" driver
      And I Select "EARNINGS" from driver App menu

         #    Core-4524 Verify the UI and functionality of Payment setting screen
      And I click on "Payment Setting" button
      And I verify the elements of payment setting screen page
      And I click on "Close Payment Settings" button

         #    Core-2372  Verify UI of earnings page on driver app
      And I verify all the elements on earnings page
      And I get "Itemized Earnings" from earnings page

        #     Core-2469  Verify UI of itemized earnings page on driver app
      And I verify all the elements on itemized earnings page
      Then I compare with earnings from admin portal for "solo driver"

  @ready
  Scenario: Verify earning page on driver app without tip after successful payment for duo partner portal
    When I request Partner Portal "DUO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewRE Customer|
    And I wait for "2" mins

    And As a driver "Testdrivertywd_appleks_a_drvv Kansas_v" and "Testdrivertywd_appleks_a_drvw Kansas_w" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Bungii Completed   | Bungii Completed   |
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "DRIVERS" from admin sidebar
    And I search for "Testdrivertywd_appleks_a_drvv Kansas_v" driver on driver details
    And I click on "Driver Earnings" icon on driver page
    And I click on "View" icon on driver page
    Then I get "Trip Earnings" from driver earnings page on admin portal for "duo first driver"
    And I Select "DRIVERS" from admin sidebar
    And I search for "Testdrivertywd_appleks_a_drvw Kansas_w" driver on driver details
    And I click on "Driver Earnings" icon on driver page
    And I click on "View" icon on driver page
    Then I get "Trip Earnings" from driver earnings page on admin portal for "duo second driver"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drvv Kansas_v" driver
    And I Select "EARNINGS" from driver App menu
    And I get "Itemized Earnings" from earnings page
    Then I compare with earnings from admin portal for "duo first driver"
    And I click on "BACK" button
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drvw Kansas_w" driver
    And I Select "EARNINGS" from driver App menu
    And I get "Itemized Earnings" from earnings page
    Then I compare with earnings from admin portal for "duo second driver"

#  Core-2117 Verify that driver receives notification of address change when app is in foreground/background
  @ready
    Scenario: Verify that driver receives notification of address change when app is in foreground/background
      Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
        | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
        | NEXT_POSSIBLE | 8877661014     | Cci12345          | Testcustomertywd_appleMarkO LutherO |

      And As a driver "Testdrivertywd_appleks_a_drvaj Kansas_aj" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state|
        | Accepted  |
        | Enroute            |

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_appleks_a_drvaj Kansas_aj" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      Then Bungii driver should see "Enroute screen"

      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Live Trips" from admin sidebar
      And I select the live trip for "Testcustomertywd_appleMarkO LutherO" customer
      And I edit the drop off address
      Then I change the drop off address to "4800 East 63rd Street, Kansas City"
      And I edit the pickup address
      Then I change the pickup address to "6700 Lewis Road, Kansas City"
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed

      And I wait for "2" mins
      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I wait for "2" mins
      And I should see the notification for address change

#  Core-2117 Verify that driver can view updated pickup and drop off address after polling refresh on app (live trip)
      And I swipe to check trip details
      Then I check if "dropoff address" is updated for live trip
      Then I check if "pickup address" is updated for live trip



 #  Core-2345 Verify that driver can view updated pickup/drop-off address after polling refresh on app (scheduled trip)
    @ready
    Scenario: Verify that driver can view updated pickup/drop-off address after polling refresh on app (scheduled trip)
      Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
        | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
        | NEXT_POSSIBLE | 8877661015     | Cci12345          | Testcustomertywd_appleMarkP LutherP |

      And As a driver "Testdrivertywd_appleks_a_drvak Kansas_ak" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state|
        | Accepted  |

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_appleks_a_drvak Kansas_ak" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkP LutherP" the customer
      And I Select "Edit Trip Details" option
      And I edit the drop off address
      Then I change the drop off address to "4800 East 63rd Street, Kansas City"
      And I edit the pickup address
      Then I change the pickup address to "6700 Lewis Road, Kansas City"
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed

      And I wait for "2" mins
      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      And I check if "dropoff address" is updated
      And I check if "pickup address" is updated
#     Core - 3113 Verify that driver can rate customer after delivery completes with any admin edits.
      And I start selected Bungii
      Then Bungii driver should see "General Instructions"
      Then Bungii driver should see "Enroute screen"
      And I slide update button on "EN ROUTE" Screen
      And I slide update button on "ARRIVED" Screen
      And I slide update button on "LOADING ITEM" Screen
      And I slide update button on "DRIVING TO DROP OFF" Screen
      And I slide update button on "UNLOADING ITEM" Screen
      When Bungii Driver "rates customer"
      And I click on "SUBMIT RATING" button
      Then I click "Next Bungii" button on the "Bungii Completed" screen

#    Core-2117  Verify that already accepted stacked trip does not change if current trips address(s) changes
   @ready
   Scenario: Verify that already accepted stacked trip does not change if current trips address(s) changes
     Given that ondemand bungii is in progress
       | geofence | Bungii State        |
       | atlanta  | ARRIVED             |

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
     Then I accept Alert message for "Alert: Display Stack trip after current trip"
     And stack trip information should be displayed on deck
     And I get TELET time of currrent trip of customer 2

     When I Switch to "customer" application on "same" devices
     And I am logged in as "Testcustomertywd_apple_AGQFCg Test" customer
     And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
     And I close "Tutorial" if exist
     When I tap on "Menu" > "ACCOUNT" link
     Then "ACCOUNT INFO" page should be opened
     And I tap on the "Delete account" Link
     Then "Delete account" page should be opened
     And I enter customers "valid1" Password
     And I click on "Delete" button
     Then The user should see "snackbar validation message active bungii for account deletion" on log in page
     And I click on "Account Cancel" button
     Then I click on "Navigate Back" button on the "ACCOUNT INFO" page of customer app
     When I tap on "Menu" > "Home" link
     Then "Home" page should be opened

     When I open new "Chrome" browser for "ADMIN PORTAL"
     And I navigate to admin portal
     And I log in to admin portal
     And I Select "Live Trips" from admin sidebar
     And I select the live trip for "Ondemand" customer
     And I edit the drop off address
     Then I change the drop off address to "2200 Belcourt Parkway, Roswell"
     And I click on "VERIFY" button
     And The "Your changes are good to be saved." message is displayed
     Then I click on "SAVE CHANGES" button
     And The "Bungii Saved!" message is displayed

     When I switch to "ORIGINAL" instance
     When I Switch to "driver" application on "same" devices
     And I should see the notification for address change
     And stack trip information should be displayed on deck
     And I get TELET time of currrent trip of customer 2

     Then I cancel all bungiis of customer
       | Customer Phone  | Customer2 Phone |
       | CUSTOMER1_PHONE | CUSTOMER2_PHONE |

#   Core-4414 Verify Branch app link is shown for all the existing drivers which is not registered for Branch app
  @ready
  Scenario: Verify Branch app link is shown for all the existing drivers which is not registered for Branch app
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drvu Kansas_u" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
 #   Core-4524: Verify that selected payment method is not updated to default when driver logs out from app
    And I Select "EARNINGS" from driver App menu
    And I click on "Payment Setting" button
    And I click on "Change default payment" button
    When I Select "ACCOUNT" from driver App menu
    And The "ACCOUNT" page is opened
    When I Select "LOGOUT" from ACCOUNT menu
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drvu Kansas_u" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "EARNINGS" from driver App menu
    Then I check if "changed payment" button is displayed
 #   Validating Driver not registered for Branch app in Db
    And I check "no branch registration" in db
    And I Select "EARNINGS" from driver App menu
    Then I check if "Branch app" button is displayed
    And I click on "Branch app" button
    Then I should be navigated to "default browser"

#   Core-4414 Verify Branch app link is shown for all the existing drivers who is enrolled and wallet is created
#   Driver with Branch Wallet: 9049840056
  @ready
  Scenario: Verify Branch app link is shown for all the existing drivers who is enrolled and wallet is created
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appledv_b_mattJ Stark_dvOnEJ" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
 #   Validating Driver registered for Branch app with wallet in Db
    And I check "branch registered with wallet" in db
    And I Select "EARNINGS" from driver App menu
    Then I check if "Branch app" button is displayed
    And I click on "Branch app" button
    Then I should be navigated to "default browser"

#   Core-4414 Verify Branch app link is shown for all the existing drivers who is enrolled in Branch but no wallet created
#   Driver without Branch Wallet: 9049840054
  @ready
  Scenario: Verify Branch app link is shown for all the existing drivers who is enrolled in Branch but no wallet created
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appledv_b_mattH Stark_dvOnEH" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
 #   Validating Driver registered for Branch app with wallet in Db
    And I check "branch registered without wallet" in db
    And I Select "EARNINGS" from driver App menu
    Then I check if "Branch app" button is displayed
    And I click on "Branch app" button
    Then I should be navigated to "default browser"