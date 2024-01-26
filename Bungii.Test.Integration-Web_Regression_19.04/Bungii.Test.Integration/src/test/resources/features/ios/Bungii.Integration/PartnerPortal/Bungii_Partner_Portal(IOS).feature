@ios
Feature: Partner Portal Cases integration with IOS

  @ready
  Scenario: Verify Partner portal name shown on driver app
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
    When I Switch to "driver" application on "same" devices
    And I login as "valid partner kansas driver" driver on "same" device and make driver status as "Online"
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    #And I Select Trip from available trip
    And I Select Partner portal Trip from available trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And I click on "i earning" icon
    And I click on "Close Payment Settings" button
#    Core - 2569 Verify ~ sign under earnings is shown on Driver app for Variable pricing Deliveries
    And I check if variable sign is shown under "available bungii details"
    Then Partner Portal name should be displayed in "AVAILABLE BUNGIIS" section
    When I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I check if variable sign is shown under "schedule bungii details"
#    Core- 4524: Verify that icon 'i' is displayed next to Delivery earnings and on clicking on it pop-up is displayed
    And I click on "i earning" icon
    And I click on "Close Payment Settings" button
    Then I should be navigated to "BUNGII DETAILS" screen
    Then Partner Portal name should be displayed in "SCHEDULED BUNGIIS" section
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen on driver
    Then Partner Portal name should be displayed in "EN ROUTE TO PICKUP" section
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    Then I should be navigated to "ARRIVED AT PICKUP" trip status screen on driver
    Then Partner Portal name should be displayed in "ARRIVED AT PICKUP" section
    And I slide update button on "ARRIVED AT PICKUP" Screen
    Then I should be navigated to "LOADING ITEMS AT PICKUP" trip status screen on driver
    Then Partner Portal name should be displayed in "LOADING ITEMS AT PICKUP" section
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    #   Core-3412 Verify Photo verification screen is not shown for partner trip which has Photo verification disabled but enabled for geofence
    #    Photo verification disabled for MRFM
    And I check if "photo verification" button is "not displayed"
    Then I should be navigated to "DRIVING TO DROP-OFF" trip status screen on driver
    Then Partner Portal name should be displayed in "DRIVING TO DROP-OFF" section
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then I should be navigated to "UNLOADING ITEMS AT DROP-OFF" trip status screen on driver
    Then Partner Portal name should be displayed in "UNLOADING ITEMS AT DROP-OFF" section
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
      #  Core - 3113 Verify that Rate customer page UI and elements are displayed correctly to driver
    And I check the elements displayed on rate customer screen
    And I select "4" customer rating
      #  Core - 3113 Verify that comment field on Rate customer page is validated correctly
    And I add comment on rate customer page
    Then I should be navigated to "Bungii Completed" screen
    When I click "On To The Next One" button on "Bungii completed" screen
      #  Core-3098 Verify online/offline pop up is shown for solo Partner portal trip and go-offline functionality
    And I check online or offline pop up is displayed
    And I click on "GO OFFLINE" button
    And I Select "HOME" from driver App menu
    And I check if the status is "OFFLINE"
      #  Core-4556 Verify DB after trip id completed for driver with weekly payment
    And I check the status for "weekly payment" in db
    Then I check the status for "weekly payment-external reference" in db

#  Core-2569: Verify ~ sign under earnings is not shown on Driver app for Fixed pricing Deliveries
  @ready
  Scenario: Verify ~ sign under earnings is not shown on Driver app for Fixed pricing Deliveries
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applega_a_steveC Stark_altOnEC" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request Partner Portal "SOLO" Trip for "Biglots" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |atlanta  | NEXT_POSSIBLE | 8877661047 | Testcustomertywd_appleMarkAV LutherAV|

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    And I check if variable sign is not shown under "available bungii details"
    When I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I check if variable sign is not shown under "schedule bungii details"


 #  Core-2411:Verify that driver's status remains Online when his previous status was Online once he starts the schedule trip
  @ready
  Scenario: Verify that driver's status remains Online when his previous status was Online once he starts the schedule tripfor ios
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 8877661017 | Testcustomertywd_appleMarkR LutherR|
    And I Switch to "driver" application on "same" devices
    And I login as "valid partner kansas driver2" driver on "same" device and make driver status as "Offline"
    And Driver status should be "Offline"
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And Partner Portal name should be displayed in "AVAILABLE BUNGIIS" section
    When I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And Partner Portal name should be displayed in "SCHEDULED BUNGIIS" section
    When I start selected Bungii
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And Driver status should be "Online"
    And I Switch to "customer" application on "same" devices
    When I am on Customer logged in Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                      | geofence  |
      | Solo   | 775 Brasilia Avenue, Kansas City |  648 Madrid Avenue, Kansas City | kansas |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen
    And I Switch to "driver" application on "same" devices
    And I view and check virtual notification for "Driver" for "on demand trip"



#  Core-2418: Verify Driver Pricing by weight for Solo delivery for Floor n Decor Partner
  @ready
  Scenario: Verify Driver Pricing by weight for Solo delivery for Floor n Decor Partner
    When I Switch to "driver" application on "same" devices
    And I enter phoneNumber :9766000001 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661044 | Testcustomertywd_appleMarkAS LutherAS|
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAS LutherAS" the customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "solo"
    And I calculate the driver share and check for "solo"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
#    Core-2537 Verify whether driver can accept deliveries which have suitable payload for his vehicle
    When I accept selected Bungii
    And I click "OK" button on alert message
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I verify the driver earnings displayed on driver app for "solo"

    # Core-2418 Verify Driver Pricing is recalculated for Floor n Decor delivery when admin edits the address and service level of Schedule Trip
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAS LutherAS" the customer
    And I Select "Edit Trip Details" option
    And I edit the drop off address
    Then I change the drop off address to "14800 Carrs Mill Road, Woodbine"
    And I change the service level to "Customer Return - First Threshold" in "Admin" portal
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    Then The "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I get the new pickup reference generated
    And I wait for "2" mins
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAS LutherAS" the customer for delivery details
    And I get the estimated charge "for customer"
    And I calculate the driver share and check for "changed address and service level"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I verify the driver earnings displayed on driver app for "changed address and service level"
    And I start selected Bungii for "floor and decor"
    Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen on driver
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And I slide update button on "ARRIVED AT PICKUP" Screen
    Then Bungii driver should see "Pickup instructions"
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen

#    Core-2418 Verify Driver Pricing is recalculated for Floor n Decor delivery when admin edits the address and service level of Live Trip
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_appleMarkAS LutherAS" customer
#   Core-2641 Verify alias is displayed for partner portal trips on Live delivery page
    And I verify alias is displayed correctly on "live delivery page"
    And I Select "Edit Trip Details" option
    And I edit the drop off address
    Then I change the drop off address to "3315 Shepherd Street, Chevy Chase, Maryland"
    And I change the service level to "First Threshold" in "Admin" portal
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    Then The "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I get the new pickup reference generated
    And I wait for "2" mins
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_appleMarkAS LutherAS" customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "solo"
    And I calculate the driver share and check for "solo"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
#    Core-3412 Verify driver is able to upload Photo verification from More Option
    And I click "More Options" button on "update" screen
    And I click "Take Photo" button on "update" screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then Bungii driver should see "Drop-off instructions"
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii Completed" screen

#  Driver : 9049840255 Payload capacity : 1011 lbs
#  Core-2418: Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor and Decor Partner
  @ready
  Scenario:Verify Driver Pricing by weight for Duo delivery with both Pallet weight lies same tier for Floor n Decor Partner
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I enter phoneNumber :9049840255 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I mark the driver "online"

    When I request Partner Portal "DUO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661040 | Testcustomertywd_appleMarkAO LutherAO|
    And I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAO LutherAO" the customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "duo"
    And I calculate the driver share and check for "duo"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I verify the driver earnings displayed on driver app for "duo"

#  Core-2418: Verify Driver Pricing for Floor n Decor delivery when admin edits the  date/time or research Schedule Trip
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAO LutherAO" the customer
    And I Select "Edit Trip Details" option
    And I click on the "Time" and select future time
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    Then The "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I wait for "2" mins
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAO LutherAO" the customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "duo"
    And I calculate the driver share and check for "duo"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I verify the driver earnings displayed on driver app for "duo"
#   Core-2537: Verify whether driver can accept delivery that are upto 100 lb more then the payload
    And I select "Pallet-1" from items
    And I accept selected Bungii
    And I click "OK" button on alert message
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
#   Core-2537: Verify whether admin is able to assign the delivery without any validations
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAO LutherAO" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appledc_a_drvH WashingtonH" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed


#  Driver : 9049840254 Payload capacity : 500 lbs
#  Core-2418: Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
  @ready
  Scenario:Verify Driver Pricing by weight for Duo delivery with Pallet weight in different tier for Floor n Decor Partner
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I enter phoneNumber :9049840254 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request Partner Portal "DUO" Trip for "Floor and Decor - Different Weights" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661041 | Testcustomertywd_appleMarkAP LutherAP|
    And I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAP LutherAP" the customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "duo"
    And I calculate the driver share and check for "duo-different tier"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I verify the driver earnings displayed on driver app for "duo-different tier"
#   Core-2537: Verify whether drivers with low payload capacity are allowed to accept deliveries with high weight
    And I select "Pallet-1" from items
    And I accept selected Bungii
    Then I check inadequate payload pop up is displayed
    And I click "OK" button on alert message
#  Core-2418: Verify Driver Pricing for Floor n Decor delivery when admin convert duo trip to solo
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAP LutherAP" the customer
    And I Select "Edit Trip Details" option
    And I change delivery type from "Duo to Solo"
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    Then The "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I wait for "2" mins
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAP LutherAP" the customer for delivery details
    And I get the estimated charge "for customer"
    And I get the driver earnings displayed for "solo"
    Then I calculate the driver share and check for "duo to solo conversion"

#   Core-2537: Verify that information of both the pallets are displayed separately on drivers app when a delivery is converted from duo to solo
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I check information of both the pallets are displayed separately

#  Driver : 9049840258 Payload capacity : 1111 lbs
#  Core-2546: Verify for DUO delivery when a pallet is already accepted by driver it is not available for other driver
  @ready
  Scenario:Verify for DUO delivery when a pallet is already accepted by driver it is not available for other driver
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I enter phoneNumber :9049840258 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request Partner Portal "DUO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661091 | Testcustomertywd_appleMarkCN LutherCN|

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
#   Core-2546: Verify pallet details are displayed on AVAILABLE Bungii menu
    And I check "pallet-1" details are displayed on "available bungii" page
    And I select "Pallet-1" from items
#   Core-2546: Verify driver can accept using AVAILABLE BUNGII menu when driver pallet is equal to payload capacity
    And I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
#   Core-2546: Verify pallet details are displayed on SCHEDULE Bungii menu
    And I check "pallet-1" details are displayed on "schedule bungii" page

    When I Select "ACCOUNT > LOGOUT" from driver App menu
    Then I should be able to see data on "LOGOUT" page
    Then I should be navigated to "LOG IN" screen
    And I enter phoneNumber :9049840259 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    And I select "Pallet-1" from items
    And I accept selected Bungii
    Then I check already accepted pallet pop up is displayed

#CORE-3271:To verify that SOLO lift icon is displayed on driver app for partner delivery that was scheduled without checkbox
  @ready
  Scenario:To verify that SOLO lift icon is displayed on driver app for partner delivery that was scheduled without checkbox
    When I open new "Chrome" browser for "ADMIN PORTAL"
    When I navigate to "Partner" portal configured for "Tile Shop" URL
    #CORE-1735:To verify the pickup time for SOLO and DUO when Partner has not provided Lead time for Partner site
    Then The "SOLO" delivery shoudnt have lead time
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I click on "Solo" button
    Then The First timeslot should display the time without partner portal lead time
    Then The "DUO" delivery shoudnt have lead time
    When I click on "DUO" button
    Then The First timeslot should display the time without partner portal lead time
    #CORE-3271:To verify that SOLO lift icon is displayed on driver app for partner delivery that was scheduled without che
    When I request Partner Portal "Duo" Trip for "Tile Shop" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |nashville| NEXT_POSSIBLE | 8877661093 | Testcustomertywd_BppleMarkCP LutherCP|

    And I wait for 2 minutes
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_BppleMarkCP LutherCP" the customer
    And I click on the "Edit" button from the dropdown
    And I Select "Edit Trip Details" option
    And I change delivery type from "Duo to Solo"
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    Then The "Bungii Saved!" message is displayed
    When I click on "CLOSE" button

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applens_a_kayQ Stark_nsOnEQ" driver
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should see "SOLO LIFT" header displayed
    And I start selected Bungii
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And I click on "GOT IT" button
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then I should see "SOLO LIFT" header displayed
    And I click on "GOT IT" button
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen

#CORE-3271:To verify that SOLO lift with customer Help is displayed on driver app for partner delivery that was scheduled with checkbox selected
  @ready
  Scenario: To verify that SOLO lift with customer Help is displayed on driver app for partner delivery that was scheduled with checkbox selected
    When I open new "Chrome" browser for "ADMIN PORTAL"
    When I navigate to "Partner" portal configured for "Equip-bid" URL
    #CORE-1735:To verify the pickup time for SOLO and DUO when Partner has provided Lead time specific for Partner site
    Then The "Solo" deliveries should have a lead time for "Kansas" partner portal
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I click on "Solo" button
    Then The first timeslot should display the time including the provided partner portal lead time
    When I click on "DUO" button
    Then The "Duo" deliveries should have a lead time for "Kansas" partner portal
    Then The first timeslot should display the time including the provided partner portal lead time
    #CORE-3271:To verify that SOLO lift with customer Help is displayed on driver app for partner delivery that was scheduled with checkbox selected
    When I request Partner Portal "SOLO" Trip for "Equip-bid" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |kansas| NEXT_POSSIBLE | 8877661094  | Testcustomertywd_appleMarkCQ LutherCQ|
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appleks_a_drvbg Kansas_bg" driver
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    Then I should see "CUSTOMER HELP" header displayed
    When I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should see "CUSTOMER HELP" header displayed
    And I start selected Bungii

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And  I wait for 1 minutes
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_appleMarkCT LutherCT" customer
    And I Select "Edit Trip Details" option
    And I edit the drop off address
    Then I change the drop off address to "4800 East 63rd Street, Kansas City"
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I swipe to check trip details
    Then The "admin edits dropoff Address" should match
    And I click on "Close" button
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    Then The "DROP-OFF(Expected time)" "Label" should be displayed
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then The "DROP-OFF(Expected time)" "Label" should be displayed
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkCQ LutherCQ" the customer
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then "Customer Help" icon should be displayed in all deliveries details page

#    Core-2291: Verify Mileage and Pricing on admin edit pickup address in Loading/Driving to drop off status- Solo Fixed Pricing
  @ready
  Scenario: Verify Mileage and Pricing on admin edit pickup address in Loading/Driving to drop off status- Solo Fixed Pricing
    When I request Partner Portal "Solo" Trip for "Biglots" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |atlanta  | NEXT_POSSIBLE | 8877661110 | Testcustomertywd_appleMarkDG LutherDG|
    And As a driver "Testdrivertywd_applega_a_steveE Stark_altOnEE" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state |
      | Accepted      |
    And I get the old values of pickup and drop off
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I enter phoneNumber :9049840081 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii for "floor and decor"
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And I click on "GOT IT" button
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkDG LutherDG" the customer
    And I click on "Edit" link beside live delivery
    And I Select "Edit Trip Details" option
    And I edit the pickup address
    Then I change the pickup address to "The Punchline Comedy Club"
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I wait for "2" mins
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_appleMarkDG LutherDG" customer for delivery details
    Then I check if miles are updated for "pick-up" in "Loading"
    Then I check if correct "customer price-loading" is displayed on delivery details

#  Core-2291: Verify Mileage and Pricing on admin edit drop off address in Driving to drop off status- Solo Weight Based
  @ready
  Scenario: Verify Mileage and Pricing on admin edit drop off address in Unloading status- Solo Weight Based
    When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661111 | Testcustomertywd_BppleMarkDH LutherDH|
    And I get the old values of pickup and drop off
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I enter phoneNumber :9766000001 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    When I accept selected Bungii
    And I click "OK" button on alert message
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii for "floor and decor"
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And I slide update button on "ARRIVED AT PICKUP" Screen
    Then Bungii driver should see "Pickup instructions"
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then Bungii driver should see "Drop-off instructions"

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I open the trip for "Testcustomertywd_BppleMarkDH LutherDH" the customer
    And I click on "Edit" link beside live delivery
    And I Select "Edit Trip Details" option
    And I edit the drop off address
    Then I change the drop off address to "14800 Carrs Mill Road, Woodbine"
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    Then The "Bungii Saved!" message is displayed
    When I click on "CLOSE" button
    And I wait for "2" mins
    And I Select "live trips" from admin sidebar
    And I select the live trip for "Testcustomertywd_BppleMarkDH LutherDH" customer for delivery details
    Then I check if miles are updated for "drop-off" in "driving to dropoff"
    Then I check if correct "customer price-driving to dropoff" is displayed on delivery details

#CORE-4122: To verify 'Arrival time at pickup' and 'Expected time at drop off' values displayed for Live Bungii delivery where admin edits addresses during Enroute state
 @ready
  Scenario:To verify Arrival time at pickup and Expected time at drop off values displayed for Live Bungii delivery where admin edits addresses during Enroute state
    When I request Partner Portal "Solo" Trip for "Equip-bid" partner
     |Geofence| Bungii Time   | Customer Phone | Customer Name |
     |kansas| NEXT_POSSIBLE | 8877661137 | Testcustomertywd_appleMarkEH LutherEH|
   And As a driver "Testdrivertywd_appleks_a_drvci Kansas_ci" perform below action with respective "Solo Scheduled" trip
     | driver1 state |
     | Accepted      |
   When I Switch to "driver" application on "same" devices
   And I am on the "LOG IN" page on driverApp
   And I am logged in as "Testdrivertywd_appleks_a_drvci Kansas_ci" driver
   And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
   And I Select "SCHEDULED BUNGIIS" from driver App menu
   And I open first Trip from driver scheduled trip
   Then The "Arrival time at Pickup" "Text" should be displayed
   Then The "Expected time at drop-off" "Text" should be displayed
   Then The "Arrival time" should match
   Then The "Expected time at drop-off" should match
   And I start selected Bungii

    #CORE-4122:To verify 'Arrival time at pickup' and 'Expected time at drop off' values displayed for Live Bungii delivery where admin edits addresses during Enroute state   When I open new "Chrome" browser for "ADMIN PORTAL"
   When I open new "Chrome" browser for "ADMIN PORTAL"
   And I navigate to admin portal
   And I log in to admin portal
   And  I wait for 2 minutes
   And I Select "live trips" from admin sidebar
   And I select the live trip for "Testcustomertywd_appleMarkEH LutherEH" customer
   And I Select "Edit Trip Details" option
   And I edit the drop off address
   Then I change the drop off address to "4800 East 63rd Street, Kansas City"
   And I click on "VERIFY" button
   And The "Your changes are good to be saved." message is displayed
   Then I click on "SAVE CHANGES" button


    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I swipe to check trip details
    Then The "admin edits dropoff Address" should match
    And I click on "Close" button

    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen

#CORE-4122:To verify 'Arrival time at pickup' and 'Expected time at drop off' values displayed for Live Bungii delivery where admin edits addresses after Arrived state
  @ready
  Scenario:Verify Arrival time at pickup and Expected time at drop off values displayed for Live Bungii delivery where admin edits addresses after Arrived state
    When I request Partner Portal "Solo" Trip for "Equip-bid" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |kansas| NEXT_POSSIBLE | 8877661139 | Testcustomertywd_appleMarkEJ LutherEJ|
    And As a driver "Testdrivertywd_appleks_a_drvbn Kansas_bn" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appleks_a_drvbn Kansas_bn" driver
   And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open first Trip from driver scheduled trip
   Then The "Arrival time at Pickup" "Text" should be displayed
   Then The "Expected time at drop-off" "Text" should be displayed
   Then The "Arrival time" should match
   Then The "Expected time at drop-off" should match
   And I start selected Bungii
   And I slide update button on "EN ROUTE TO PICKUP" Screen

   When I open new "Chrome" browser for "ADMIN PORTAL"
   And I navigate to admin portal
   And I log in to admin portal
   And  I wait for 1 minutes
   And I Select "live trips" from admin sidebar
   And I select the live trip for "Testcustomertywd_appleMarkEJ LutherEJ" customer
   And I Select "Edit Trip Details" option
   And I edit the drop off address
   Then I change the drop off address to "4800 East 63rd Street, Kansas City"
   And I click on "VERIFY" button
   And The "Your changes are good to be saved." message is displayed
   Then I click on "SAVE CHANGES" button


   When I switch to "ORIGINAL" instance
   When I Switch to "driver" application on "same" devices
   And I swipe to check trip details
   Then The "driver at arrival state" should match
   And I click on "Close" button
  Then I save the dropoff latitude and longitude of the first delivery

    When I request "Solo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | 1_DAY_LATER | 8877661140       | Testcustomertywd_appleMarkEK LutherEK|
    And I wait for 2 minutes
    And I click "Available Bungii Icon" button on "update" screen
    And I Select Trip from available trip
    Then The "stacked bungii" should match
    Then The "Stacked delivery dropOff range" should match
    When I accept selected Bungii
    And I click on "BACK" button

    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
   And I slide update button on "LOADING ITEMS AT PICKUP" Screen
   And Driver adds photos to the Bungii
   And I slide update button on "LOADING ITEMS AT PICKUP" Screen
   And I slide update button on "DRIVING TO DROP-OFF" Screen
   And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
   And Driver adds photos to the Bungii
   And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
   And I click "Skip This Step" button on "Rate customer" screen
   Then I should be navigated to "Bungii completed" screen

#    Core-4556: Verify DB after trip id completed for driver with same day payment
  @ready
  Scenario: Verify DB after trip id completed for driver with same day payment
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I enter phoneNumber :9049840343 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |washingtondc| NEXT_POSSIBLE | 8877661111 | Testcustomertywd_BppleMarkDH LutherDH|

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Partner portal Trip from available trip
    When I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii for "floor and decor"
    Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen on driver
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    And I slide update button on "ARRIVED AT PICKUP" Screen
    Then Bungii driver should see "Pickup instructions"
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    Then Bungii driver should see "Drop-off instructions"
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii Completed" screen
    And I check the status for "same day payment" in db
    Then I check the status for "same day payment-external reference" in db

#  Core-4556: Verify status of the trip is payment successful after driver completes the trip: Same day payment (Monthly Invoice)
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The "All Deliveries" should be in "Payment Successful" state


#CORE-4398:Verify driver is able to scan barcode only for configured partner
  @ready
  Scenario:Verify driver is able to scan barcode only for configured partner
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_applega_a_drval Atlanta_al" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
     #CORE-4398:Verify barcode scanning for solo trip
    When I request Partner Portal "SOLO" Trip for "Floor and Decor 106" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |atlanta| NEXT_POSSIBLE | 8877661149 |Testcustomertywd_appleMarkET LutherET|
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    When I accept selected Bungii
    #CORE-4581: Confirmation of acceptance of delivery in driver's app
    Then I should see "Delivery Accepted" popup displayed
    And I click "OK" button on alert message
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    And I slide update button on "EN ROUTE TO PICKUP" Screen
    Then The "Barcode" "Image" should be displayed
    Then The "Scan the item(s) barcode before loading & after unloading." "Instruction" should be displayed
    And I click on "GOT IT" button
    Then The "Please take photos and scan item(s) barcode before loading, just ‘slide to load items’ and follow the prompts." "Notification" should be displayed
      #CORE-4398:Verify barcode scanning with combination of Photo verification
      #CORE-4398:Verify driver is able to scan barcode from More option
    And I click "More Options" button on "update" screen
    Then The "Scan Item barcode" "Button" should be displayed
    When I click on "Scan item barcode" button
    When I click on "Allow" button
    Then The "BARCODE SCANNER" "Header" should be displayed
    Then The "Scan barcode" "Text" should be displayed
    Then The "Hold steady and center the barcode to scan. You need to scan any one item to proceed." "Instruction" should be displayed
    Then The "Skip" "Button" should be displayed
    When I click on "Skip" button
    #CORE-4398:Verify Notification messages shown to driver when barcode is enabled
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "ARRIVED AT PICKUP" Screen
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "LOADING ITEMS AT PICKUP" Screen
    And I slide update button on "DRIVING TO DROP-OFF" Screen
    And I click on "GOT IT" button
    And I click "More Options" button on "update" screen
    Then The "Scan Item barcode" "Button" should be displayed
    When I click on "Scan item barcode" button
    Then The "BARCODE SCANNER" "Header" should be displayed
    Then The "Scan barcode" "Text" should be displayed
    Then The "Hold steady and center the barcode to scan.You need to scan any one item to proceed" "Instruction" should be displayed
    Then The "Skip" "Button" should be displayed
      #CORE-4398:Verify driver is allowed to skip barcode scanning
    When I click on "Skip" button
      #CORE-4398:Verify Notification messages shown to driver when barcode is enabled
    Then The "Please take photos and scan item(s) barcode after unloading, just ‘slide to complete Bungii’ and follow the prompts" "Notification" should be displayed
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And Driver adds photos to the Bungii
    And I slide update button on "UNLOADING ITEMS AT DROP-OFF" Screen
    And I click "Skip This Step" button on "Rate customer" screen
    Then I should be navigated to "Bungii completed" screen