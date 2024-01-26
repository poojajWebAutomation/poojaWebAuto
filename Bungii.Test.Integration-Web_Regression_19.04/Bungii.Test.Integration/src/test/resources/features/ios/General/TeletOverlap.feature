@ios

Feature: Overlapping TELET

  @ready
  Scenario: Verify that driver is allowed to start only the first scheduled solo partner trip when there is another overlapping (TELET) Solo partner trip
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE_FIRST_SLOT | 8877661025     | Testcustomertywd_appleMarkZ LutherZ|
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvar Kansas_ar" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 8877661026     | Testcustomertywd_appleMarkAA LutherAA |
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAA LutherAA" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvar Kansas_ar" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvar Kansas_ar" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    And I start selected Bungii
    And I check conflicting pop up is displayed
    And I click on "BACK" button
    And I open first Trip from driver scheduled trip
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen on driver

  @ready
  Scenario: Verify when trip1 and trip2 have overlapping time and trip2 time is edited before trip1 and trip2 is able to start
   #trip - 1 second slot
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661027     | Cci12345          | Testcustomertywd_appleMarkAB LutherAB |
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvas Kansas_as" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    #trip - 2 third slot
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE_THIRD_SLOT | 8877661028     | Cci12345          | Testcustomertywd_appleMarkAC LutherAC |
    And I wait for "1" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAC LutherAC" the customer
    And I Select "Edit Trip Details" option
    And I change the "trip time to before the overlapping trip" to future time
    And I Select reason as "Partner initiated" to edit datetime
    And I assign driver "Testdrivertywd_appleks_a_drvas Kansas_as" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvas Kansas_as" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    And I start selected Bungii
    And I check conflicting pop up is displayed
    And I click on "BACK" button
    And I open first Trip from driver scheduled trip
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen on driver

  @ready
  Scenario: Verify solo1 and solo2 delivery at same time  for same driver , solo2 by admin assign, driver cancelled  solo1 and revived and solo2 is started but before starting driver accepts solo1
#   solo-1
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661029     | Cci12345          |Testcustomertywd_appleMarkAD LutherAD |
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvat Kansas_at" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
#   solo-2
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661030     | Cci12345          | Testcustomertywd_appleMarkAE LutherAE |
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAE LutherAE" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvat Kansas_at" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvat Kansas_at" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open first Trip from driver scheduled trip
    And I start selected Bungii
    And I click "More Options" button on "update" screen
    And I click "Cancel Delivery" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Trips" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAD LutherAD" the customer
    When I click on "REVIVE" button
    When I click on "CONFIRM" button
    And I wait for "2" mins
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAD LutherAD" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvat Kansas_at" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open first Trip from driver scheduled trip
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen on driver

  @ready
  Scenario: Verify driver1 control driver of trip1-duo trip accepted and delivery is in no driver found, driver1 accepted trip2 ,admin assign driver2 to trip1, driver1 should be able to start trip1
#  trip1
    Given I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661031     | Cci12345          | Testcustomertywd_appleMarkAF LutherAF |
    And I wait for "4" mins
    And As a driver "Testdrivertywd_appleks_a_drvau Kansas_au" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I wait for "4" mins
    And I wait for "4" mins

#   trip2
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661032     | Cci12345          | Testcustomertywd_appleMarkAG LutherAG |
    And I wait for "4" mins
    And As a driver "Testdrivertywd_appleks_a_drvau Kansas_au" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAF LutherAF" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvac Kansas_ac" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvau Kansas_au" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open first Trip from driver scheduled trip
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE TO PICKUP" trip status screen on driver

  @ready
  Scenario: Verify driver1 control driver of trip1-duo trip accepted and delivery is in no driver found, driver1 accepted trip2 ,admin assign driver2 to trip1, driver1 should be able to start trip2
  #  trip1
    Given I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661033     | Cci12345          | Testcustomertywd_appleMarkAH LutherAH |
    And I wait for "4" mins
    And As a driver "Testdrivertywd_appleks_a_drvav Kansas_av" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

#   trip2
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661034     | Cci12345          | Testcustomertywd_appleMarkAI LutherAI |
    And I wait for "4" mins
    And I wait for "4" mins
    And I wait for "4" mins
    And As a driver "Testdrivertywd_appleks_a_drvav Kansas_av" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkAH LutherAH" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvac Kansas_ac" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvav Kansas_av" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    Then I start selected Bungii



