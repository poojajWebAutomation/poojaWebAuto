@ios
  @notification
Feature: Solo Scheduled Bungii Part Rework
  Background:
    #When I clear all notification
	When I Switch to "customer" application on "same" devices

@ready
Scenario: Driver Notification : 30 Mins Before Scheduled Delivery
Given that solo schedule bungii is in progress
| geofence | Bungii State | Bungii Time   |
| denver   | Accepted    | 0.75 hour ahead |

And I Switch to "driver" application on "same" devices
And I am on the "LOG IN" page on driverApp
And I am logged in as "valid denver" driver
When I wait for Minimum duration for Bungii Start Time
And I click on notification for "driver" for "TAP NOTIFICATION TO ACTIVATE BUNGII"
Then I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| CUSTOMER1_PHONE |                 |

@ready
Scenario:CUSTOMER: Notification - 2 hours before scheduled trip
Given that solo schedule bungii is in progress
| geofence | Bungii State | Bungii Time    |
| denver   | Accepted     | 2 hour ahead |
And I am on the "LOG IN" page
And I logged in Customer application using  "valid denver" user
And I click on notification for "customer" for "T-2 BEFORE SCHEDULED TRIP"
Then I cancel all bungiis of customer
| Customer Phone  | Customer2 Phone |
| CUSTOMER1_PHONE |                 |