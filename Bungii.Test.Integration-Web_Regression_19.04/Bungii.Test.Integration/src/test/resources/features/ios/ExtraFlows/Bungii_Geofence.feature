@ios
Feature: Bungii Geofence Based Calculation

  @regression
    @testmac
    #Stable
  Scenario: Verify Minimum Scheduled Time Should Be Displayed On The Date Picker Of The Estimate Screen Based On When Solo Is Selected By Customer
    Given I am on the "LOG IN" page
    And I logged in Customer application using  "valid chicago" user

    And I open Admin portal and navigate to "Geofence" page
    #And I select "Chicago" geofence
    #And I activate "Chicago" geofence
	And I select "Chicago" geofence
	And I click on the "Settings" Button on "Geofence" Screen
	And I get the value of "Minimum scheduled time for Solo trip"
	And I change the value of "Minimum scheduled time for Solo trip" to "30" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                           | Drop Location                                      | Geofence  |
	  | Solo    | 63 East Ida B. Wells Drive, Chicago, IL 60605, USA | 63 East Ida B. Wells Drive, Chicago, IL 60605, USA | chicago   |

    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen

    And I select pickup time
    Then correct next available scheduled time should be displayed


  @regression
    #Stable
  Scenario: Verify When Duo Is Selected Then Time Is Selected To Next Available Scheduled Time For A Selected Geofence
	Given I am on the "LOG IN" page
	And I logged in Customer application using  "valid chicago" user

    And I open Admin portal and navigate to "Geofence" page
   # And I select "Chicago" geofence
   # And I activate "Chicago" geofence
    And I select "Chicago" geofence
    And I click on the "Settings" Button on "Geofence" Screen
    And I get the value of "Minimum scheduled time for Duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "30" minutes
    And I click on the "Save" Button on "GeofenceSettings" Screen
  
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
	And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location     | Drop Location                                      | Geofence  |
      | Duo    | 63 East Ida B. Wells Drive, Chicago, IL 60605, USA | 63 East Ida B. Wells Drive, Chicago, IL 60605, USA | chicago   |
    
    #And I wait for 15 minutes slot overlap period if occurs
    And I click "Get Estimate" button on "Home" screen
    And I calculate the schedule time
    Then correct next available scheduled time should be displayed

  @regression
   #stable
  Scenario: Verify Customer Can Change Default Payment Card
    And I logged in Customer application using  "valid denver" user
    When I Select "ACCOUNT > PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    When I get "current" default card
    And I tap on "Other card" on Payment page
    And I tap on "Checkbox" on Payment page
    When I click "SAVE" button on "PAYMENT" screen
    Then I should see "new default card" on Payment page

#    Core-2618 Verify that admin can set referral code amount on geofence settings page
  @regression
    #ADP-669 need to be fixed
    Scenario: Verify that admin can set referral code amount on geofence settings page
      When I open Admin portal and navigate to "Geofence" page
      And I select "Chicago" geofence
      And I click on the "Settings" Button on "Geofence" Screen
      And I set "set referral code amount"
#     Core-2618 Verify admin can set no. of deliveries for referral payout on geofence setting page
      And I set "set no. of deliveries"
      Then I click on the "Save" Button on "GeofenceSettings" Screen
#     Core-2618 Verify that updating referral amount in geofence settings updates value in invite screen
      When I switch to "ORIGINAL" instance
      When I Switch to "driver" application on "same" devices
      And I am on the "LOG IN" page on driverApp
      And I enter phoneNumber :9049840247 and  Password :Cci12345
      And I click "Log In" button on "Log In" screen on driverApp
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I click on "$" icon
      Then I check if the amount is updated on invite screen