@android

Feature: Rejection Popup on Driver App

#  Rejection popup functionality enabled for Kansas geofence
  @ready
  Scenario: Verify that rejection popup,cancel functionality and all reasons are displayed for scheduled deliveries on available trips page
    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drvaf Kansas_af" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661005     | Cci12345          | Testcustomertywd_appleMarkF LutherF |

    Then I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I wait for "4" mins
    And I navigate to admin portal
    And I wait for "4" mins
    And I log in to admin portal
    And I wait for "4" mins
    And I Select "Scheduled Trip" from admin sidebar
    And I wait for "4" mins
    And I open the trip for "Testcustomertywd_appleMarkF LutherF" the customer for delivery details
    Then I check if delivery status is "No Driver(s) Found"

#    Core-3008: To verify that customer trip with "no driver found" status is displayed under Available Deliveries of driver app

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    And I click on the back button and verify the rejection popup
    And I check that latest reason is saved when "back" button is clicked
    And I check if all reasons are displayed on rejection popup
    And I click on "CANCEL" button on rejection popup
    Then I should be navigated to "BUNGII DETAILS" screen
    And I click on the back button and verify the rejection popup
    And I click on "SUBMIT" button on rejection popup
    Then I check if the reason is saved in db

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkF LutherF" the customer for delivery details
    Then I stop searching driver

    And I wait for "2" mins
    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drvaf Kansas_af" driver
    And I click "Log In" button on Log In screen on driver app
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I check if "Testcustomertywd_appleMarkF LutherF" customer trip that is rejected is displayed

#  Rejection popup functionality is disabled for Washington geofence
  @ready
  Scenario: Verify that Rejection reason pop-up is not displayed to driver when the toggle is disabled on Admin Portal
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :9049840018 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    Given I Switch to "customer" application on "same" devices
    Given I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661003     | Cci12345          | Testcustomertywd_appleMarkD LutherD |

    Then I wait for "2" mins
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    And I click on the back button and verify that rejection popup is absent

  @ready
  Scenario: Verify UI and behaviour of Rejection reason pop-up for stacked trips
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | atlanta  | ARRIVED      |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
        #put driver on background
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 8877661004     | Testcustomertywd_appleMarkE LutherE | 2              | Cci12345          |

    And I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "view stack message" request
    Then "correct stack trip details" should be displayed on Bungii request screen
    And I tap on the "REJECT" Button on Bungii Request screen
    And I verify the rejection popup is displayed
    And I click on "SUBMIT" button on rejection popup
    Then Bungii driver should see "Arrived screen"

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |


    @ready
    Scenario: Verify rejection reason pop-up for on-demand trips
      Given I am on customer Log in page
      When I am logged in as "valid boston" customer
      And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I close "Tutorial" if exist

      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "valid boston" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      And I tap on "Go Online button" on Driver Home page

      And I Switch to "customer" application on "same" devices
      And I enter "new boston pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add "2" photos to the Bungii
      And I add loading/unloading time of "30 mins"
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate

      And I Open "driver" application on "same" devices
      And Bungii Driver "rejects On Demand Bungii" request
      And I verify the rejection popup is displayed
      And I click on "SUBMIT" button on rejection popup
      Then Bungii driver should see "Home screen"

#    Core-3008: To verify that partner portal trip with "no driver found" status is displayed under Available Deliveries of driver app
  @ready
    Scenario: Verify that partner portal trip with "no driver found" status is displayed under Available Deliveries of driver app
      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_appledc_a_drvJ WashingtonJ" driver

      When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 8877661010 | Testcustomertywd_appleMarkK LutherK|
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I wait for "4" mins
      And I navigate to admin portal
      And I wait for "4" mins
      And I log in to admin portal
      And I wait for "4" mins
      And I Select "Scheduled Trip" from admin sidebar
      And I wait for "3" mins
      And I open the trip for "Testcustomertywd_appleMarkK LutherK" the customer for delivery details
      Then I check if delivery status is "No Driver(s) Found"

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      Then I Select Trip from available trip


  @ready
  #CORE-3545
  Scenario:To verify that driver can successfully Complete on going trip when admin cancels Stack trip
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | atlanta  | UNLOADING ITEM |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 8877661175     | Testcustomertywd_appleMarkFT LutherFT | 2              | Cci12345          |
    And I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "view stack message" request
    And I tap on the "ACCEPT" Button on Bungii Request screen
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I wait for "2" mins
    And I open the trip for "Testcustomertywd_appleMarkFT LutherFT" the customer for delivery details
    And I click on "Edit" link beside live delivery
    And I click on "Edit Delivery Status" radiobutton
    And I click on "Delivery Canceled" radiobutton
    And I click on "UPDATE BUNGII" button
    Then The "Pick up has been successfully canceled." message should be displayed for live delivery
    And I Switch to "driver" application on "ORIGINAL" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then Bungii driver should see "Unloading Items screen"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "skips to rate customer"
    Then Bungii Driver "completes Bungii"

  @ready
  #CORE-4426
  Scenario:To verify that driver can successfully Complete on going trip when admin manually completes Stack trip
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | atlanta  | UNLOADING ITEM |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    When I Switch to "customer" application on "same" devices
    And I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 8877661176     | Testcustomertywd_appleMarkFU LutherFU | 2              | Cci12345          |
    And I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "view stack message" request
    And I tap on the "ACCEPT" Button on Bungii Request screen
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "live trips" from admin sidebar
    And I wait for "2" mins
    And I open the trip for "Testcustomertywd_appleMarkFU LutherFU" the customer for delivery details
    And I click on "Edit" link beside live delivery
    And I click on "Edit Delivery Status" radiobutton
    And I click on "Delivery Completed" radiobutton
    And I enter delivery completion date and time as per geofence
    And I click on "CALCULATE COST" button
    Then Confirmation message on edit live delivery pop up should be displayed
    And I click on "Confirm" button
    Then The "Pick up has been successfully updated." message should be displayed for live delivery
    And I Switch to "driver" application on "ORIGINAL" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then Bungii driver should see "Unloading Items screen"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "skips to rate customer"
    Then Bungii Driver "completes Bungii"