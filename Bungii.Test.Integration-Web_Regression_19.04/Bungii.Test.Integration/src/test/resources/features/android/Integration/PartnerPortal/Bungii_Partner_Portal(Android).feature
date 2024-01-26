  @android

  Feature: Partner Portal Cases integration with Android
  @ready
  Scenario: Verify that the Partner name shown on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drva Kansas_a" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
    |Geofence| Bungii Time   | Customer Phone | Customer Name |
    |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip

#    Core - 2569 Verify ~ sign under earnings is shown on Driver app for Variable pricing Deliveries
    And I check if variable sign is shown under "available bungii details"

    Then Partner Portal name should be displayed in "AVAILABLE BUNGIIS" section
#   Core-4524: Verify that icon 'i' is displayed next to Delivery earnings and on clicking on it pop-up is displayed
    And I check if "i" icon is displayed
    And I click on "i earning" icon
    And I click on "Close Payment Settings" button
    #CORE-4983 Customer names is trimmed on all delivery pages for driver app
    And I check if customer name is "Testcustomertywd_appleNewU C" under "available bungii details"
    And I tap on "ACCEPT" on driver Trip details Page
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click on the "OK" Button on "Accept Delivery" popup
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And I check if "i" icon is displayed
    And I click on "i earning" icon
    And I click on "Close Payment Settings" button
    And I check if variable sign is shown under "schedule bungii details"
    Then Partner Portal name should be displayed in "SCHEDULED BUNGIIS" section
    And I check if customer name is "Testcustomertywd_appleNewU C" under "schedule bungii details"
    And I start selected Bungii
    Then Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"
    Then Partner Portal name should be displayed in "EN ROUTE" section
#   Core-2618 Verify that referral icon is not shown during in process trip on driver app
    And I swipe to check trip details
    And I check if customer name is "Testcustomertywd_appleNewU C" under "EN ROUTE"
    And I click on "CLOSE" button
    And I check if referral icon is not shown
    And I slide update button on "EN ROUTE" Screen
    Then Bungii driver should see "Arrived screen"
    Then Partner Portal name should be displayed in "ARRIVED" section
    And I swipe to check trip details
    And I check if customer name is "Testcustomertywd_appleNewU C" under "ARRIVED"
    And I click on "CLOSE" button
    And I slide update button on "ARRIVED" Screen
    Then Bungii driver should see "Loading Items screen"
    Then Partner Portal name should be displayed in "LOADING ITEMS" section
    And I swipe to check trip details
    And I check if customer name is "Testcustomertywd_appleNewU C" under "LOADING ITEMS"
    And I click on "CLOSE" button
    And I slide update button on "LOADING ITEM" Screen
    Then Bungii driver should see "Driving to Drop-Off screen"
    Then Partner Portal name should be displayed in "DRIVING TO DROP-OFF" section
    And I swipe to check trip details
    And I check if customer name is "Testcustomertywd_appleNewU C" under "DRIVING TO DROP-OFF"
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then Bungii driver should see "Unloading Items screen"
    Then Partner Portal name should be displayed in "UNLOADING ITEMS" section
    And I swipe to check trip details
    And I check if customer name is "Testcustomertywd_appleNewU C" under "UNLOADING ITEMS"
    And I slide update button on "UNLOADING ITEM" Screen

#  Core-3098 Verify online/offline pop up is shown for solo Partner portal trip and check stay online functionality
    And Bungii Driver "skips to rate customer"
    And I click "Next Bungii" button on the "Bungii Completed" screen
    And I check online or offline pop up is displayed
    And I click on "STAY ONLINE" button
    And I Select "HOME" from driver App menu
    And I check if the status is "ONLINE"

#  Core-2638: Verify the changed Driver cut is reflected in driver app
    @ready
    Scenario: Verify the changed Driver cut is reflected in driver app
      When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 9999999127 | Testcustomertywd_appleNewRB Customer|
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleNewRB Customer" the customer
      And I Select "Edit Trip Details" option
      And I assign driver "Testdrivertywd_appledc_a_drvC WashingtonC" for the trip
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I wait for "2" mins
      And I open the trip for "Testcustomertywd_appleNewRB Customer" the customer for delivery details
      Then I click on "Price Override" button and change the driver cut
      When I wait for "2" mins
      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_appledc_a_drvC WashingtonC" driver
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I check if driver cut is reflected
#     Core-2291: Verify Price override Prices is retained on admin edit address for Live trips post price override- weight based partner
      And I Select Trip from driver scheduled trip
      And I start selected Bungii for "floor and decor"
      Then Bungii driver should see "General Instructions"
      And I slide update button on "EN ROUTE" Screen

      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "live trips" from admin sidebar
      And I select the live trip for "Testcustomertywd_appleNewRB Customer" customer
      And I Select "Edit Trip Details" option
      And I edit the drop off address
      Then I change the drop off address to "3315 Shepherd Street, Chevy Chase, Maryland"
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      Then The "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I get the new pickup reference generated
      And I wait for "2" mins
      And I Select "live trips" from admin sidebar
      And I select the live trip for "Testcustomertywd_appleNewRB Customer" customer for delivery details
      Then I check price override prices are retained on admin edit address

  #  Core-2569: Verify ~ sign under earnings is not shown on Driver app for Fixed pricing Deliveries
    @ready
    Scenario: Verify ~ sign under earnings is not shown on Driver app for Fixed pricing Deliveries
      When I am logged in as "Testdrivertywd_applega_a_steveB Stark_altOnEB" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      When I request Partner Portal "SOLO" Trip for "Biglots" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |atlanta  | NEXT_POSSIBLE | 8877661046 | Testcustomertywd_appleMarkAU LutherAU|

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      And I check if variable sign is not shown under "available bungii details"
      And I tap on "ACCEPT" on driver Trip details Page
      #CORE-4581: Confirmation of acceptance of delivery in driver's app
      Then I should see "Delivery Accepted" popup displayed
      And I click on the "OK" Button on "Accept Delivery" popup
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      Then I check if variable sign is not shown under "schedule bungii details"

      When I tap on "Back" button of android mobile
      And I Select "HOME" from driver App menu
      And I tap on "Back" button of android mobile
#     Core-3371 Verify Push Notification is sent with schedule date/time to driver when partner(Fixed Pricing) cancels trip
      When I open new "Chrome" browser for "ADMIN PORTAL"
      When I navigate to "Partner" portal configured for "service level" URL
      When I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      When I click the "Track Deliveries" button on Partner Portal
      And I click on the delivery based on customer name
      And I click "Cancel Delivery link" button on Partner Portal
      And I click "Cancel Delivery" button on Partner Portal
      Then I click "OK" button on Partner Portal
      And I wait for "1" mins

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      Then I check the notification for "partner cancel trip"


#  Core-2411:Verify that driver's status remains Online when his previous status was Online once he starts the schedule trip
    @ready
    Scenario: Verify that driver's status remains Online when his previous status was Online once he starts the schedule trip
      When I request Partner Portal "SOLO" Trip for "MRFM" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |Kansas| NEXT_POSSIBLE | 8877661018 | Testcustomertywd_appleMarkS LutherS|
      And I am logged in as "Testdrivertywd_appleks_a_drval Kansas_al" driver
      Then I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And Driver status should be "Offline"
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then Partner Portal name should be displayed in "AVAILABLE BUNGIIS" section
      And I tap on "ACCEPT" on driver Trip details Page
      #CORE-4581: Confirmation of acceptance of delivery in driver's app
      Then I should see "Delivery Accepted" popup displayed
      And I click on the "OK" Button on "Accept Delivery" popup
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      When I Select Trip from driver scheduled trip
      Then Partner Portal name should be displayed in "SCHEDULED BUNGIIS" section
      And I start selected Bungii
      And Bungii driver should see "General Instructions"
      And Bungii driver should see "Enroute screen"
      And Partner Portal name should be displayed in "EN ROUTE" section
      And Driver status should be "Online"
      And I Switch to "customer" application on "same" devices
      When I am on customer Log in page
      And I am logged in as "valid kansas" customer
      And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I close "Tutorial" if exist
      And I enter "new Kansas pickup less than 10 miles" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add "2" photos to the Bungii
      And I add loading/unloading time of "30 mins"
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I Switch to "driver" application on "ORIGINAL" devices
      Then I click on notification for "STACK TRIP"
      And I should see "New Bungii Request" popup displayed



#    Core-2418: Verify Driver Pricing by weight for Solo delivery for Floor n Decor Partner
    @ready
    Scenario: Verify Driver Pricing by weight for Solo delivery for Floor n Decor Partner
      When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 8877661083 | Testcustomertywd_BppleMarkCF LutherCF|
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_BppleMarkCF LutherCF" the customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "solo"
      And I calculate the driver share and check for "solo"

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_appledc_a_web TestdriverA" driver
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      And I verify the driver earnings displayed on driver app for "solo"
      #Core-2537 Verify whether driver can accept deliveries which have suitable payload for his vehicle
      And I tap on "ACCEPT" on driver Trip details Page
      #CORE-4581: Confirmation of acceptance of delivery in driver's app
      Then I should see "Delivery Accepted" popup displayed
      And I click on the "OK" Button on "Accept Delivery" popup
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip

      # Core-2418 Verify Driver Pricing is recalculated for Floor n Decor delivery when admin edits the address and service level of Schedule Trip
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_BppleMarkCF LutherCF" the customer
      And I Select "Edit Trip Details" option
      And I edit the drop off address
      Then I change the drop off address to "14800 Carrs Mill Road, Woodbine"
      And I change the service level to "Customer Return - First Threshold" in "Admin" portal
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      Then The "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I get the new pickup reference generated
      And I wait for "2" mins
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_BppleMarkCF LutherCF" the customer for delivery details
      And I get the estimated charge "for customer"
      And I calculate the driver share and check for "changed address and service level"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      When I tap on "Back" button of android mobile
      And I Select Trip from driver scheduled trip
      And I verify the driver earnings displayed on driver app for "changed address and service level"
      And I start selected Bungii for "floor and decor"
      Then Bungii driver should see "General Instructions"
      And I slide update button on "EN ROUTE" Screen
      Then Bungii driver should see "Pickup Instructions"
      And I slide update button on "ARRIVED" Screen

#    Core-2418 Verify Driver Pricing is recalculated for Floor n Decor delivery when admin edits the address and service level of Live Trip
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "live trips" from admin sidebar
      And I select the live trip for "Testcustomertywd_BppleMarkCF LutherCF" customer
      And I Select "Edit Trip Details" option
      And I edit the drop off address
      Then I change the drop off address to "3315 Shepherd Street, Chevy Chase, Maryland"
      And I change the service level to "First Threshold" in "Admin" portal
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      Then The "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I get the new pickup reference generated
      And I wait for "2" mins
      And I Select "live trips" from admin sidebar
      And I select the live trip for "Testcustomertywd_BppleMarkCF LutherCF" customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "solo"
      Then I calculate the driver share and check for "solo"


#  Driver : 9049840256 Payload capacity : 1011 lbs
#  Core-2418: Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor and Decor Partner
    @ready
    Scenario:Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor n Decor Partner
      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I enter phoneNumber :9049840256 and  Password :Cci12345
      And I click "Log In" button on Log In screen on driver app
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      When I request Partner Portal "DUO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 8877661042 | Testcustomertywd_appleMarkAQ LutherAQ|
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAQ LutherAQ" the customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "duo"
      And I calculate the driver share and check for "duo"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then I verify the driver earnings displayed on driver app for "duo"

#  Core-2418: Verify Driver Pricing for Floor n Decor delivery when admin edits the  date/time or research Schedule Trip
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAQ LutherAQ" the customer
      And I Select "Edit Trip Details" option
      And I click on the "Time" and select future time
      And I click on "Reason" for change time
      And I click on "Customer initiated" in the dropdown
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      Then The "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I wait for "2" mins
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAQ LutherAQ" the customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "duo"
      And I calculate the driver share and check for "duo"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I click on the back button and verify that rejection popup is absent
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then I verify the driver earnings displayed on driver app for "duo"
      #Core-2537: Verify whether driver can accept delivery that are upto 100 lb more then the payload
      And I select "Pallet-1 available page" from items
      And I tap on "ACCEPT" on driver Trip details Page
      #CORE-4581: Confirmation of acceptance of delivery in driver's app
      Then I should see "Delivery Accepted" popup displayed
      And I click on the "OK" Button on "Accept Delivery" popup
      Then I should be navigated to "AVAILABLE BUNGIIS" screen

#  Driver : 9049840253 Payload capacity : 500 lbs
#  Core-2418: Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
    @ready
    Scenario:Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I enter phoneNumber :9049840253 and  Password :Cci12345
      And I click "Log In" button on Log In screen on driver app
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      When I request Partner Portal "DUO" Trip for "Floor and Decor - Different Weights" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 8877661043 | Testcustomertywd_appleMarkAR LutherAR|
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAR LutherAR" the customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "duo"
      And I calculate the driver share and check for "duo-different tier"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then I verify the driver earnings displayed on driver app for "duo-different tier"
#   Core-2537: Verify whether drivers with low payload capacity are allowed to accept deliveries with high weight
      And I select "Pallet-1" from items
      Then I check inadequate payload pop up is displayed
#  Core-2418: Verify Driver Pricing for Floor n Decor delivery when admin convert duo trip to solo
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAR LutherAR" the customer
      And I Select "Edit Trip Details" option
      And I change delivery type from "Duo to Solo"
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      Then The "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I wait for "2" mins
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAR LutherAR" the customer for delivery details
      And I get the estimated charge "for customer"
      And I get the driver earnings displayed for "solo"
      And I calculate the driver share and check for "duo to solo conversion"

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I tap on "Back" button of android mobile
      And I Select Trip from available trip
      Then I verify the driver earnings displayed on driver app for "solo"
 #   Core-2537: Verify that information of both the pallets are displayed separately on drivers app when a delivery is converted from duo to solo
      Then I check information of both the pallets are displayed separately

#  Driver : 9049840258 Payload capacity : 1111 lbs
#  Core-2546: Verify for DUO delivery when a pallet is already accepted by driver it is not available for other driver
    @ready
    Scenario:Verify for DUO delivery when a pallet is already accepted by driver it is not available for other driver
      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I enter phoneNumber :9049840258 and  Password :Cci12345
      And I click "Log In" button on Log In screen on driver app
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      When I request Partner Portal "DUO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 8877661092 | Testcustomertywd_appleMarkCO LutherCO|

      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      #Core-2546: Verify pallet details are displayed on AVAILABLE Bungii menu
      And I select "Pallet-1" from items
      And I check "pallet-1" details are displayed on "available bungii" page
      #Core-2546: Verify driver can accept using AVAILABLE BUNGII menu when driver pallet is equal to payload capacity
      And I tap on "ACCEPT" on driver Trip details Page
      #CORE-4581: Confirmation of acceptance of delivery in driver's app
      Then I should see "Delivery Accepted" popup displayed
      And I click on the "OK" Button on "Accept Delivery" popup
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      #Core-2546: Verify pallet details are displayed on SCHEDULE Bungii menu
      And I check "pallet-1" details are displayed on "schedule bungii" page
      And I tap on "Back" button of android mobile
      And I connect to "extra1" using "Driver2" instance
      And I Open "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I enter phoneNumber :9766000001 and  Password :Cci12345
      And I click "Log In" button on Log In screen on driver app
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      And I select "Pallet-1" from items
      Then I check already accepted pallet pop up is displayed

      #CORE-3271:To verify that SOLO lift icon is displayed on driver app for partner delivery that was scheduled without checkbox
      @ready
    Scenario:To verify that SOLO lift icon is displayed on driver app for partner delivery that was scheduled without checkbox
        When I request Partner Portal "Duo" Trip for "Tile Shop" partner
          |Geofence| Bungii Time   | Customer Phone | Customer Name |
          |nashville| NEXT_POSSIBLE | 8877661096 | Testcustomertywd_appleMarkCS LutherCS|
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkCS LutherCS" the customer
      And I Select "Edit Trip Details" option
      And I change delivery type from "Duo to Solo"
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      Then The "Bungii Saved!" message is displayed
      When I click on "CLOSE" button

      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I am logged in as "Testdrivertywd_applens_a_kayT Stark_nsOnET" driver
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then I should see "SOLO LIFT" header displayed
      And I tap on "ACCEPT" on driver Trip details Page
      #CORE-4581: Confirmation of acceptance of delivery in driver's app
      Then I should see "Delivery Accepted" popup displayed
      And I click on the "OK" Button on "Accept Delivery" popup
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      Then I should see "SOLO LIFT" header displayed
      And I start selected Bungii
      Then Bungii driver should see "General Instructions"
      And I slide update button on "EN ROUTE" Screen
      And I click on "GOT IT" button
      And I slide update button on "ARRIVED" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "ARRIVED" Screen
      And I slide update button on "LOADING ITEM" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "LOADING ITEM" Screen
      And I slide update button on "DRIVING TO DROP-OFF" Screen
      Then I should see "SOLO LIFT" header displayed
      And I click on "GOT IT" button
      And I slide update button on "UNLOADING ITEMS" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "UNLOADING ITEMS" Screen

    #CORE-3271:To verify that SOLO lift with customer Help is displayed on driver app for partner delivery that was scheduled with checkbox selected
    @ready
    Scenario:To verify that SOLO lift with customer Help is displayed on driver app for partner delivery that was scheduled with checkbox selected
      When I Switch to "driver" application on "same" devices
      And I am logged in as "Testdrivertywd_appleks_a_drvbh Kansas_bh" driver
      When I request Partner Portal "Solo" Trip for "Equip-bid" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |kansas| NEXT_POSSIBLE | 8877661097 | Testcustomertywd_appleMarkCT LutherCT|
       And I wait for 1 minutes
       And I Switch to "driver" application on "same" devices
       And I Select "AVAILABLE BUNGIIS" from driver App menu
       And I Select Trip from available trip
      Then The "Arrival time at pickup" "Text" should be displayed
      Then The "Expected time at drop-off" "Text" should be displayed
      Then The "Arrival time" should match
      Then The "Expected time at drop-off" should match
       Then I should see "CUSTOMER HELP" header displayed
       And I tap on "ACCEPT" on driver Trip details Page
      #CORE-4581: Confirmation of acceptance of delivery in driver's app
      Then I should see "Delivery Accepted" popup displayed
      And I click on the "OK" Button on "Accept Delivery" popup

      #Issue raised ADP-744
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And  I wait for 2 minutes
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkCT LutherCT" the customer
      And I Select "Edit Trip Details" option
      And I edit the drop off address
      Then I change the drop off address to "6700 Lewis Road, Kansas City"
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      Then The "Arrival time at pickup" "Text" should be displayed
      Then The "Expected time at drop-off" "Text" should be displayed
      Then The "Arrival time" should match
      Then The "Expected time at drop-off" should match
      Then I should see "CUSTOMER HELP" header displayed
      And I start selected Bungii for "Equip-bid"
      Then Bungii driver should see "General Instructions"
     #CORE-4122:To verify 'Arrival time at pickup' and 'Expected time at drop off' values displayed for Live Bungii delivery where admin edits addresses during Enroute state
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And  I wait for 1 minutes
      And I Select "live trips" from admin sidebar
      And I select the live trip for "Testcustomertywd_appleMarkCT LutherCT" customer
      And I Select "Edit Trip Details" option
      And I edit the drop off address
      Then I change the drop off address to "6800 Zoo Drive, Kansas City"
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I click "OK" on alert message
      And I swipe to check trip details
      Then The "admin edits dropoff Address" should match
      And I click on "CLOSE" button

      And I slide update button on "EN ROUTE" Screen
      And I slide update button on "ARRIVED" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "ARRIVED" Screen
      And I slide update button on "LOADING ITEM" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "LOADING ITEM" Screen
      Then The "DROP-OFF(Expected time)" "Label" should be displayed
      And I slide update button on "DRIVING TO DROP-OFF" Screen
      Then The "DROP-OFF(Expected time)" "Label" should be displayed
      And I slide update button on "UNLOADING ITEMS" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "UNLOADING ITEMS" Screen
      And I wait for 2 minutes
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "completed deliveries" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkCT LutherCT" the customer
      And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
      Then "Customer Help" icon should be displayed in all deliveries details page

   #CORE:4122: To verify 'Arrival time at pickup' and 'Expected time at drop off' values displayed for Live Bungii delivery where admin edits addresses after Arrived state
   @ready
    Scenario:To verify 'Arrival time at pickup' and 'Expected time at drop off' values displayed for Live Bungii delivery where admin edits addresses after Arrived state
      When I Switch to "driver" application on "same" devices
      And I am logged in as "Testdrivertywd_appleks_a_drvbo Kansas_bo" driver
      When I request Partner Portal "Solo" Trip for "Equip-bid" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |kansas| NEXT_POSSIBLE | 8877661135 | Testcustomertywd_appleMarkEF LutherEF|
      And I wait for 1 minutes
      And I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then The "Arrival time at pickup" "Text" should be displayed
      Then The "Expected time at drop-off" "Text" should be displayed
      Then The "Arrival time" should match
      Then The "Expected time at drop-off" should match
      And I tap on "ACCEPT" on driver Trip details Page
      #CORE-4581: Confirmation of acceptance of delivery in driver's app
      Then I should see "Delivery Accepted" popup displayed
      And I click on the "OK" Button on "Accept Delivery" popup
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      Then The "Arrival time at pickup" "Text" should be displayed
      Then The "Expected time at drop-off" "Text" should be displayed
      Then The "Arrival time" should match
      Then The "Expected time at drop-off" should match
      And I start selected Bungii for "Equip-bid"
      Then Bungii driver should see "General Instructions"
      Then The "PICKUP(Arrival time)" "Label" should be displayed
      And I slide update button on "EN ROUTE" Screen
      Then The "PICKUP(Arrival time)" "Label" should be displayed
     #CORE-4122:To verify 'Arrival time at pickup' and 'Expected time at drop off' values displayed for Live Bungii delivery where admin edits addresses after Arrived state
     When I open new "Chrome" browser for "ADMIN PORTAL"
     And I navigate to admin portal
     And I log in to admin portal
     And  I wait for 2 minutes
     And I Select "live trips" from admin sidebar
     And I select the live trip for "Testcustomertywd_appleMarkEF LutherEF" customer
     And I Select "Edit Trip Details" option
     And I edit the drop off address
     Then I change the drop off address to "4800 East 63rd Street, Kansas City"
     And I click on "VERIFY" button
     And The "Your changes are good to be saved." message is displayed
     Then I click on "SAVE CHANGES" button

     When I switch to "ORIGINAL" instance
     And I Switch to "driver" application on "same" devices
     And I click "OK" on alert message
     And I swipe to check trip details
     #CORE:4122:To verify time values displayed at Stops information for in progress Scheduled Solo Bungii
     Then The "driver at arrival state" should match
     And I click on "CLOSE" button
     Then I save the dropoff latitude and longitude of the first delivery

      When I request "Solo Ondemand" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661136     | Testcustomertywd_appleMarkEG LutherEG |
      And I wait for 1 minutes
      And I click on "View Request" button
     #CORE-4122:To verify UI & "time to arrival" and "expected time to drop off" for stacked deliveries on ios and android apps
      Then The "stacked bungii" should match
      Then The "Stacked delivery dropOff range" should match
      And I click on "Accept" button
      And I click on "OK" button
      And I slide update button on "ARRIVED" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "ARRIVED" Screen
      Then The "PICKUP(Arrival time)" "Label" should be displayed
      And I slide update button on "LOADING ITEM" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "LOADING ITEM" Screen
      And I slide update button on "DRIVING TO DROP-OFF" Screen
      And I slide update button on "UNLOADING ITEMS" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "UNLOADING ITEMS" Screen

     #CORE-4122:To verify Arrival Time/ Expected time values on various states of on demand in progress Bungii
     @ready
    Scenario:To verify Arrival Time/ Expected time values on various states of on demand in progress Bungii
       When I switch to "ORIGINAL" instance
       When I Switch to "driver" application on "same" devices
       And I am logged in as "Testdrivertywd_appleks_a_drvbp Kansas_bp" driver
       And I tap on "Go Online button" on Driver Home page
       When I request "Solo Ondemand" Bungii as a customer in "kansas" geofence
         | Bungii Time   | Customer Phone | Customer Name |
         | NEXT_POSSIBLE | 8877661134     | Testcustomertywd_appleMarkEE LutherEE |
       And I wait for 1 minutes
       And I click on "View Request" button
       And I wait for 1 minutes
       Then The "Ondemand bungii" should match
       Then The "Ondemand delivery dropOff range" should match
       And I tap on "ACCEPT" on driver Trip details Page
       And I Select "SCHEDULED BUNGIIS" from driver App menu
       And I Select Trip from driver scheduled trip
       Then The "Arrival time at pickup" "Text" should be displayed
       Then The "Expected time at drop-off" "Text" should be displayed
       Then The "Ondemand bungii" should match
       Then The "Ondemand delivery dropOff range" should match
       And I start selected Bungii for "Equip-bid"
       Then Bungii driver should see "General Instructions"
       Then The "PICKUP(Arrival time)" "Label" should be displayed
       And I slide update button on "EN ROUTE" Screen
     And I slide update button on "ARRIVED" Screen
     When Bungii driver uploads "1" image
     And I slide update button on "ARRIVED" Screen
     #CORE-4122:To verify Arrival Time/ Expected time values on various states of on demand in progress Bungii
     Then The "PICKUP(Arrival time)" "Label" should be displayed
     And I slide update button on "LOADING ITEM" Screen
     When Bungii driver uploads "1" image
     And I slide update button on "LOADING ITEM" Screen
     Then The "DROP-OFF(Expected time)" "Label" should be displayed
     And I slide update button on "DRIVING TO DROP-OFF" Screen
     Then The "DROP-OFF(Expected time)" "Label" should be displayed
     And I slide update button on "UNLOADING ITEMS" Screen
     When Bungii driver uploads "1" image
     And I slide update button on "UNLOADING ITEMS" Screen

    #CORE-4398:Verify driver is able to scan barcode only for configured partner
    @ready
    Scenario:Verify driver is able to scan barcode only for configured partner
      When I Switch to "driver" application on "same" devices
      And I am logged in as "Testdrivertywd_applega_a_drvak Atlanta_ak" driver
      #CORE-4398:Verify barcode scanning for solo trip
      When I request Partner Portal "SOLO" Trip for "Floor and Decor 106" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |atlanta| NEXT_POSSIBLE | 8877661148 | Testcustomertywd_appleMarkES LutherES|
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      And I tap on "ACCEPT" on driver Trip details Page
      #CORE-4581: Confirmation of acceptance of delivery in driver's app
      Then I should see "Delivery Accepted" popup displayed
      And I click on the "OK" Button on "Accept Delivery" popup
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      And I start selected Bungii for "floor and decor 106"
      Then Bungii driver should see "General Instructions"
      And I slide update button on "EN ROUTE" Screen
      Then The "Barcode" "Image" should be displayed
      Then The "Scan the item(s) barcode before loading & after unloading." "Instruction" should be displayed
      And I click on "GOT IT" button
      Then The "Please take photos and scan item(s) barcode before loading, just ‘slide to load items’ and follow the prompts." "Notification" should be displayed
      #CORE-4398:Verify barcode scanning with combination of Photo verification
      #CORE-4398:Verify driver is able to scan barcode from More option
      When Bungii Driver "clicks More Options"
      Then The "Scan Item barcode" "Button" should be displayed
      When I click on "Scan item barcode" button
      When I click on "Allow" button
      Then The "BARCODE SCANNER" "Header" should be displayed
      Then The "Scan barcode" "Text" should be displayed
      Then The "Hold steady and center the barcode to scan.You need to scan any one item to proceed" "Instruction" should be displayed
      Then The "Skip" "Button" should be displayed
      When I click on "Skip" button
      #CORE-4398:Verify Notification messages shown to driver when barcode is enabled
      And I slide update button on "ARRIVED" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "ARRIVED" Screen
      And I slide update button on "LOADING ITEM" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "LOADING ITEM" Screen
      And I slide update button on "DRIVING TO DROP-OFF" Screen
      And I click on "GOT IT" button
      When Bungii Driver "clicks More Options"
      Then The "Scan Item barcode" "Button" should be displayed
      When I click on "Scan item barcode" button
      Then The "BARCODE SCANNER" "Header" should be displayed
      Then The "Scan barcode" "Text" should be displayed
      Then The "Hold steady and center the barcode to scan. You need to scan any one item to proceed" "Instruction" should be displayed
      Then The "Skip" "Button" should be displayed
      #CORE-4398:Verify driver is allowed to skip barcode scanning
      When I click on "Skip" button
      #CORE-4398:Verify Notification messages shown to driver when barcode is enabled
      Then The "Please take photos and scan item(s) barcode after unloading, just ‘slide to complete Bungii’ and follow the prompts" "Notification" should be displayed
      And I slide update button on "UNLOADING ITEMS" Screen
      When Bungii driver uploads "1" image
      And I slide update button on "UNLOADING ITEMS" Screen


  #CORE-4656:To verify customer signature settings on Admin portal when it is configured as Enabled and Required on Partner management
    @ready
    Scenario:Verify Customer Signature screen is shown on driver app for Partner trips
      When I request Partner Portal "Solo" Trip for "Cort Furniture" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        | atlanta| NEXT_POSSIBLE | 8877661069 | Testcustomertywd_appleMarkBR LutherBR|
      And As a driver "Testdrivertywd_applega_a_steveG Stark_altOnEG" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state|
        | Accepted     |
      And I wait for 2 minutes
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And  I search the delivery using "Pickup Reference"
      And I click on the "Delivery details" link beside scheduled bungii for "Scheduled Deliveries"
      Then I should see the customer signature row "Present" in admin portal "Scheduled Delivery details" page
      And The customer signature field is "Required N/A"
      And As a driver "Testdrivertywd_applega_a_steveG Stark_altOnEG" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state|
        | Enroute  |
        | Arrived |
      And I wait for 2 minutes
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "live trips" from admin sidebar
      And  I search the delivery using "Pickup Reference"
      And I click on the "Delivery details" link beside scheduled bungii for "Live Deliveries"
      Then I should see the customer signature row "Present" in admin portal "Live Delivery details" page
      And The customer signature field is "Required N/A"
      And As a driver "Testdrivertywd_applega_a_steveG Stark_altOnEG" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state|
        | Loading Item |
        | Driving To Dropoff |
        | Unloading Item |
      And I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I am logged in as "Testdrivertywd_applega_a_steveG Stark_altOnEG" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      When I slide update button on "UNLOADING ITEMS" Screen
      And Bungii driver uploads "1" image
      When Bungii Driver "clicks More Options"
      And I click "Customer Signature" button on "update" screen
      And I should be able to add the text "Signed By customer" in the signed by field
      And I should be able to add customer signature
      And I click "Submit" button on "update" screen
      And I slide update button on "UNLOADING ITEM" Screen
      And I wait for 2 minutes
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Completed Deliveries" from admin sidebar
      And  I search the delivery using "Pickup Reference"
      And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
      Then I should see the customer signature row "Present" in admin portal "All Deliveries details" page
      And The customer signature field is "Signature Present"