@ios
@scheduled
@DUO
@bungii
    # this will run in denver
  # Denver | Customer 8888889917, Valid denver - 9999993015
  # Denver | Driver Testdrivertywd_appledv_b_matt Stark_dvOnE -9999998086  , Testdrivertywd_appledv_b_seni Stark_dvThree -9955112208
  # Denver | Customer denver customer Valid denver & denver customer - 9999993015
  # Denver | Driver valid denver & denver driver 1 - 8888884321 | denver driver 2 - 9955112208

Feature: Scheduled DUO Bungii Trip Processing Over
  I want to use request Scheduling Bungii with duo type
  
  Background:
	When I Switch to "customer" application on "same" devices

@regression
@failures
Scenario: Verify Customer Can Request Cancel Scheduled Duo Bungii Through SMS To Admin If One Driver Accepts And Processing Is Over
When I request "duo" Bungii as a customer in "denver" geofence
| Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
| NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "Duo Scheduled" trip
| driver1 state |
| Accepted      |
When I Switch to "customer" application on "same" devices
Given I am on the "LOG IN" page
When I enter Username :8888889917 and  Password :{VALID}
And I click "Log In" button on "Log In" screen
And I Select "MY BUNGIIS" from Customer App menu
And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
Then I wait for "4" mins
And I select already scheduled bungii
When I Cancel selected Bungii
  #When I Switch to "customer" application on "same" devices
  Then correct support details should be displayed to customer on "ADMIN-SMS" app

And I open Admin portal and navigate to "Scheduled Deliveries" page

And I Cancel Bungii with following details
| Charge | Comments |
| 0      | TEST     |
When I switch to "ORIGINAL" instance
And I Switch to "customer" application on "same" devices
And I Select "MY BUNGIIS" from Customer App menu
Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

@ready
@failures
Scenario: Verify Customer Can Request Cancel Scheduled Duo Bungii Through SMS To Admin If No Driver Accepts But Processing Is Over
When I request "duo" Bungii as a customer in "denver" geofence
| Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
| NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |

When I Switch to "customer" application on "same" devices
Given I am on the "LOG IN" page
When I enter Username :8888889917 and  Password :{VALID}
And I click "Log In" button on "Log In" screen
And I Select "MY BUNGIIS" from Customer App menu
And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
Then I wait for "2" mins
And I select already scheduled bungii
When I Cancel selected Bungii
  #When I Switch to "customer" application on "same" devices
  Then correct support details should be displayed to customer on "ADMIN-SMS" app

And I open Admin portal and navigate to "Scheduled Deliveries" page

And I Cancel Bungii with following details
| Charge | Comments |
| 0      | TEST     |
When I switch to "ORIGINAL" instance
And I Switch to "customer" application on "same" devices
And I Select "MY BUNGIIS" from Customer App menu
Then Bungii must be removed from "SCHEDULED BUNGIIS" screen
    #its scheduled time not initial request time