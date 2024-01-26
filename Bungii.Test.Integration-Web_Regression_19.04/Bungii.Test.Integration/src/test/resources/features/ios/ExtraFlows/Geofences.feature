@ios
Feature: Geofence Configuration

@regression
Scenario: Verify Geofence Not Active Message Ss Displayed On The Customer App When Geofence Is Set off
Given I am on the "LOG IN" page
And I logged in Customer application using  "valid chicago" user

When I open new "Chrome" browser for "ADMIN PORTAL"
And I navigate to admin portal
Then I log in to admin portal
When I Select "Geofence" from admin sidebar
And I select "Chicago" geofence
And I edit the geofence "Chicago"
And I select "Geo-Status" as "Inactive"
Then I click on the "Save" Button on "Geofence" Screen

When I switch to "ORIGINAL" instance
And I Switch to "customer" application on "same" devices
And I enter pickup location
| Driver | Pickup Location                            |
| Solo   | Berman Nissan of Chicago  |
Then driver eta should be "not be displayed"
And geofence not active message should be displayed

When I open new "Chrome" browser for "ADMIN PORTAL"
And I navigate to admin portal
Then I log in to admin portal
When I Select "Geofence" from admin sidebar
And I uncheck the Active Geofences Only
And I select "Chicago" geofence
And I edit the geofence "Chicago"
And I select "Geo-Status" as "Active"
Then I click on the "Save" Button on "Geofence" Screen