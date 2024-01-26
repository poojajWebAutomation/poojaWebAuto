@web
Feature: Mileage and Pricing after Admin Edits

  Background:
    Given I am logged in as Admin

  @ready
  Scenario: Verify Mileage and Pricing on admin edit drop off address in Enroute/Arrived/loading status- Solo geofence Based
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 8877661104     | Testcustomertywd_appleMarkDA LutherDA|
    And As a driver "Testdrivertywd_appleks_a_drvbj Kansas_bj" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state |
      | Accepted      |
      | Enroute       |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      |  Trip Started |
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "400 Speedway Boulevard"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then I check if miles are updated for "drop-off" in "enroute"
    Then I check if correct "customer price-enroute" is displayed on delivery details

  @ready
  Scenario: Verify Mileage and Pricing on admin edit pickup address in Arrived status- Solo Geofence based
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 8877661105     | Testcustomertywd_appleMarkDB LutherDB|
    And As a driver "Testdrivertywd_appleks_a_drvbk Kansas_bk" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
    And I get the old values of pickup and drop off
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | Driver(s) Arrived |
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I edit the pickup address
    Then I change the pickup address to "400 Speedway Boulevard"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then I check if miles are updated for "pick-up" in "arrived"
    Then I check if correct "customer price-arrived" is displayed on delivery details

  @ready
  Scenario: Verify Mileage and Pricing on admin edit Pickup and drop off address in Arrived status-Duo Customer App
    When I request "duo" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
      | NEXT_POSSIBLE | 8877661106     | Testcustomertywd_appleMarkDC LutherDC | Cci12345         |
    And As a driver "GoaF DriverF" and "GoaG DriverG" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
      | Enroute       | Enroute       |
      | Arrived       | Arrived       |
    And I wait for "2" mins
    And I get the old values of pickup and drop off
    And I view the Live Deliveries list on the admin portal
    And I select the live trip for "Testcustomertywd_appleMarkDC LutherDC" customer
    And I click on "Edit Trip Details" radiobutton
    And I edit the pickup address
    Then I change the pickup address to "Navelim Village Panchayat"
    And I edit the drop off address
    Then I change the drop off address to "Shree Shantadurga Petroleum and CNG Station"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then I check if miles are updated for "pick-up and drop-off" in "arrived"
    Then I check if correct "customer price-duo" is displayed on delivery details

  @ready
  Scenario: Verify Mileage and Pricing on admin edit drop off address in Unloading status- On demand Customer app
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 8877661107     | Testcustomertywd_appleMarkDD LutherDD | 2              | Cci12345          |
    And As a driver "Testdrivertywd_applega_a_drvaj Atlanta_aj" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      | Accepted |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
    And I get the old values of pickup and drop off
    And I view the Live Deliveries list on the admin portal
    And I select the live trip for "Ondemand" customer
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "2200 Belcourt Parkway, Roswell"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then I check if miles are updated for "drop-off" in "unloading"
    Then I check if correct "customer price-unloading" is displayed on delivery details

  @ready
  Scenario: Verify Mileage and Pricing on admin edit address for Short stack trip
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661108 | Testcustomertywd_appleMarkDE LutherDE|
    And As a driver "Testdrivertywd_appledc_a_drvW WashingtonW" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted |
      | Enroute  |
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence from a partner location
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661109 | Testcustomertywd_appleMarkDF LutherDF|
    And As a driver "Testdrivertywd_appledc_a_drvW WashingtonW" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      |Stacked Pickup Accepted |
    And I wait for "2" mins
    And I get the old values of pickup and drop off
    And I view the Live Deliveries list on the admin portal
    And I select the live trip for "Testcustomertywd_appleMarkDF LutherDF" customer
    And I click on "Edit Trip Details" radiobutton
    And I edit the pickup address
    Then I change the pickup address to "14800 Carrs Mill Road, Woodbine"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    And I select the live trip for "Duo" customer for delivery details
    Then I check if miles are updated for "drop-off" in "stack"
    Then I check if correct "customer price-stack" is displayed on delivery details

#    Core-3007:Verify Partner portals trip driver search happen based on the time configured in admin portal for respective geofence
#    Following test case takes maximum 50 mins to execute
  @ready
    @higherexecutiontime
    Scenario: Verify Partner portals trip driver search happen based on the time configured in admin portal for respective geofence
    When I request Partner Portal "Solo" Trip for "Cort Furniture" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      | memphis| NEXT_POSSIBLE_FIRST_SLOT | 8877661155 | Testcustomertywd_BppleMarkEZ LutherEZ|
    And I wait for 2 minutes
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for 2 minutes
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    And I wait for 2 minutes
    When I view the delivery details
    And I wait for 2 minutes
    And I get the value of "status"
    And I wait for 2 minutes
    And I get the value of "initial request time"
    And I wait for 2 minutes
    And I get the value of "schedule time"
    And I wait for 2 minutes
    And I get the value of "driver fixed earning"
#   Core-3225: Verify that no indicator is displayed for trips without boosted driver earnings
    And I check "* is not displayed"
    And I check if the status is "No Driver(s) Found"
    And I calculate time for "driver boost"
    And I navigate back to Scheduled Deliveries
    When I view the delivery details
    And I check if the status is "Assigning Driver(s)"
    And I get the value of "driver fixed earning after boost"
#   Core-3225: Verify that indicator is displayed for trips with boosted driver earnings on delivery details page
    And I check "* is displayed"
    And As a driver "Testdrivertywd_applemm_a_drva Memphisa" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Arrived         |
    And I wait for 2 minutes
    And I navigate back to Scheduled Deliveries
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Driver(s) Arrived |
    When I click on the "Edit" button from the dropdown
    And I edit the drop off address
    Then I change the drop off address to "1590 Century Center Parkway"
    And I click on "Verify" button on Edit Scheduled bungii popup
    And I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for 2 minutes
    When I click on the "Delivery Details" button from the dropdown
#    Core-3225:Verify that indicator is removed for trips when admin edits drop off address after driver earnings were increased for scheduled and live trips
    Then I check "* is not displayed after edit"
