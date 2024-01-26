@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled Part C
  
  Background:

  @regression
    #stable
  Scenario: Verify That a scheduled Bungii cannot be started more than 1hr before the scheduled Trip start time
    When that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1.5 hour ahead |
    When I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then I click "OFFLINE" button on Home screen on driver app
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open first Trip from driver scheduled trip
    #And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then User should see message "60 MINS BEFORE SCHEDULE TRIP TIME" text on the screen
    And I terminate "driver" app on "same" devices
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889916     |                 |
    
 

  @regression
    #stable
  Scenario: Verify That Solo Scheduled Bungii can be started 1 hour before the Scheduled delivery start time
    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                        | Customer Password |
      | NEXT_POSSIBLE | 9999999103     | Testcustomertywd_appleNewQC Customer | Cci12345          |
    And I get TELET time of of the current trip
    And As a driver "Testdrivertywd_appleks_a_drvv Kansas_v" perform below action with respective "SOLO SCHEDULED" trip
      | driver1 state |
      | Accepted      |

    Given I login as customer "9999999103" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drvv Kansas_v" driver
    And I wait for "4" mins
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I wait for "3" mins
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I wait for "4" mins
    And I Select Trip from driver scheduled trip
    And I wait for "3" mins
    And Bungii Driver "Start Schedule Bungii" request
    And Bungii driver should see "General Instructions"
    Then Bungii driver should see "Enroute screen"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 9999999103     |                 |

  @regression
    #stable
  Scenario: Verify That a Solo scheduled Bungii can be started 30 mins before the scheduled delivery start time
    When that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas2   | Accepted     | 0.5 hour ahead |
    When I Switch to "driver" application on "same" devices
    And I wait for "3" mins
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_appleks_a_drvbu Kansas_bu" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "General Instructions"
    And Bungii driver should see "Enroute screen"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8877661165     |                 |
    