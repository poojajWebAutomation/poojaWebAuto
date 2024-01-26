@android

Feature: Overlapping TELET

  @ready
  Scenario: Verify that driver is allowed to start only the first scheduled solo customer trip when there is another overlapping (TELET) Solo customer trip
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE_FIRST_SLOT | 8877661022     | Cci12345          | Testcustomertywd_appleMarkW LutherW |
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661023     | Cci12345          | Testcustomertywd_appleMarkX LutherX |
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkX LutherX" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvao Kansas_ao" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvao Kansas_ao" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then User should see message "THERE IS A CONFLICTING DELIVERY" text on the screen
    And I click on device "Back" button
    And I open first Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8877661022      |  8877661023     |

  @ready
  Scenario: Verify that driver is allowed to start only the first scheduled solo partner trip when there is another overlapping (TELET) duo partner trip
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE_FIRST_SLOT | 8877661022     | Testcustomertywd_appleMarkW LutherW|
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    When I request Partner Portal "DUO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 8877661023     | Testcustomertywd_appleMarkX LutherX|
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkX LutherX" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvao Kansas_ao" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvao Kansas_ao" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then User should see message "THERE IS A CONFLICTING DELIVERY" text on the screen
    And I click on device "Back" button
    And I open first Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8877661022      |  8877661023     |

  @ready
  Scenario: Verify that driver is allowed to start only the first scheduled duo customer trip when there is another overlapping (TELET) Solo customer trip
    Given I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE_FIRST_SLOT | 8877661022     | Cci12345          | Testcustomertywd_appleMarkW LutherW  |
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvap Kansas_ap" and "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |

    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661023     | Cci12345          | Testcustomertywd_appleMarkX LutherX |
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkX LutherX" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvao Kansas_ao" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvao Kansas_ao" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then User should see message "THERE IS A CONFLICTING DELIVERY" text on the screen
    And I click on device "Back" button
    And I open first Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8877661022      |  8877661023     |

  @ready
  Scenario: Verify that driver is allowed to start only the first scheduled duo customer trip when there is another overlapping (TELET) duo customer trip
    Given I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE_FIRST_SLOT | 8877661022     | Cci12345          | Testcustomertywd_appleMarkW LutherW |
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvap Kansas_ap" and "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |

    Given I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661023     | Cci12345          | Testcustomertywd_appleMarkX LutherX |
    And I wait for "2" mins
    And As a driver "Testdrivertywd_appleks_a_drvaq Kansas_aq" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkX LutherX" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvao Kansas_ao" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvao Kansas_ao" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then User should see message "THERE IS A CONFLICTING DELIVERY" text on the screen
    And I click on device "Back" button
    And I open first Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8877661022      |  8877661023     |

  @ready
  Scenario: Verify that driver is allowed to start only the first scheduled duo partner trip when there is another overlapping (TELET) duo partner trip
    When I request Partner Portal "DUO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE_FIRST_SLOT | 8877661022     | Testcustomertywd_appleMarkW LutherW|
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvap Kansas_ap" and "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |

    When I request Partner Portal "DUO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 8877661023     | Testcustomertywd_appleMarkX LutherX|
    And I wait for "2" mins
    And As a driver "Testdrivertywd_appleks_a_drvaq Kansas_aq" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkX LutherX" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvao Kansas_ao" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvao Kansas_ao" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then User should see message "THERE IS A CONFLICTING DELIVERY" text on the screen
    And I click on device "Back" button
    And I open first Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8877661022      |  8877661023     |

  @ready
  Scenario: Verify when trip1 and trip2 have overlapping time and trip2 time is edited before trip1 and trip2 is able to start
   #trip - 1 second slot
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661022     | Cci12345          | Testcustomertywd_appleMarkW LutherW |
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    #trip - 2 third slot
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE_THIRD_SLOT | 8877661023     | Cci12345          | Testcustomertywd_appleMarkX LutherX |
    And I wait for "1" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkX LutherX" the customer
    And I Select "Edit Trip Details" option
    And I change the "trip time to before the overlapping trip" to future time
    And I Select reason as "Partner initiated" to edit datetime
    And I assign driver "Testdrivertywd_appleks_a_drvao Kansas_ao" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvao Kansas_ao" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then User should see message "THERE IS A CONFLICTING DELIVERY" text on the screen
    And I click on device "Back" button
    And I open first Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8877661022      |  8877661023     |

  @ready
  Scenario: Verify trip1 and trip2 time are overlapping and trip1 timing is admin edited after trip2 and trip2 is started
   #trip - 1 first slot
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE_FIRST_SLOT | 8877661022     | Cci12345          | Testcustomertywd_appleMarkW LutherW |

    #trip - 2 second slot
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661023     | Cci12345          | Testcustomertywd_appleMarkX LutherX |
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkW LutherW" the customer
    And I Select "Edit Trip Details" option
    And I change the "trip time to after the overlapping trip" to future time
    And I Select reason as "Partner initiated" to edit datetime
    And I assign driver "Testdrivertywd_appleks_a_drvao Kansas_ao" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvao Kansas_ao" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then User should see message "THERE IS A CONFLICTING DELIVERY" text on the screen
    And I click on device "Back" button
    And I open first Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8877661022      |  8877661023     |

  @ready
  Scenario: Verify solo1 and solo2 delivery at same time  for same driver , solo2 by admin assign, driver cancelled  solo1 and revived and solo2 is started but before starting driver accepts solo1
#   solo-1
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661024     | Cci12345          |Testcustomertywd_appleMarkY LutherY |
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
#   solo-2
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661023     | Cci12345          | Testcustomertywd_appleMarkX LutherX |
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkX LutherX" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvao Kansas_ao" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvao Kansas_ao" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open first Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message

    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "completed deliveries" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkY LutherY" the customer
    When I click on "Revive" button
    When I click on "Confirm" button
    And I wait for "2" mins
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkY LutherY" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvao Kansas_ao" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open first Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8877661024      |  8877661023     |

  @ready
  Scenario: Verify driver1 control driver of trip1-duo trip accepted and delivery is in no driver found, driver1 accepted trip2 ,admin assign driver2 to trip1, driver1 should be able to start trip1
#  trip1
    Given I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661022     | Cci12345          | Testcustomertywd_appleMarkW LutherW |
    And I wait for "4" mins
    And As a driver "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I wait for "4" mins
    And I wait for "4" mins

#   trip2
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661023     | Cci12345          | Testcustomertywd_appleMarkX LutherX |
    And I wait for "4" mins
    And As a driver "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkW LutherW" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvac Kansas_ac" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvao Kansas_ao" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open first Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8877661022      |  8877661023     |

  @ready
  Scenario: Verify driver1 control driver of trip1-duo trip accepted and delivery is in no driver found, driver1 accepted trip2 ,admin assign driver2 to trip1, driver1 should be able to start trip2
  #  trip1
    Given I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661022     | Cci12345          | Testcustomertywd_appleMarkW LutherW |
    And I wait for "4" mins
    And As a driver "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "Duo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

#   trip2
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8877661023     | Cci12345          | Testcustomertywd_appleMarkX LutherX |
    And I wait for "4" mins
    And I wait for "4" mins
    And I wait for "4" mins
    And As a driver "Testdrivertywd_appleks_a_drvao Kansas_ao" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleMarkW LutherW" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvac Kansas_ac" for the trip
    And I click on "VERIFY" button
    And The "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And The "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvao Kansas_ao" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8877661022      |  8877661023     |