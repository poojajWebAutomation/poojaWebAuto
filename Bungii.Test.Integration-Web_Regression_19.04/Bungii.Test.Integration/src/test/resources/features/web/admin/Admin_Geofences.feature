@web
@test
Feature: Admin_Geofence

  Background:
    Given I am logged in as TestAdmin
    When I click on "Geofences  > Geofences" Menu
    Then I should be directed to "Geofences Page"


  @sanity
  @regression
  Scenario: Verify Default Geofences Attribute settings
   When I click on "Geofences  > Attributes" Menu
    Then I should be directed to "Attributes Page"
    When I go to "Geofence Attributes" page
    Then I verify that the default settings are displayed
    
  @sanity
  @regression
    #Issues reopned ADP-427
  Scenario: Verify Add Edit New Geofence
    When I click on the "Scale" Button
    And I enter following values in "Geofence" fields
      | Primary                                | Secondary                              | Geo-Name          | Geo-TimeZone | Geo-Status|
      | e{o~FpctuOjE\|j_Ao\|e@veBfe@mbt@lqe@_rM| km_}FhtotOznYf~gDcoeDxy]cx@stsBlmoC{orA| ZONE-<<UniqueNo>> | MST          |Active|
    When I click on the "Save" Button on "Geofence" Screen
    Then the geofence gets saved successfully and it is displayed in the "Geofences" grid
    When I click on the geofence name "ZONE-"
    Then Geofence data is populated correctly
    When I edit the geofence "ZONE-"
    And I enter following values in "Geofence" fields
      | Primary                                              | Secondary                   | Geo-Name  | Geo-TimeZone | Geo-Status|
      | e{o~FpctuOjE\|j_Ao\|e@veBfe@mbt@lqe@_rM      | km_}FhtotOznYf~gDcoeDxy]cx@stsBlmoC{orA        |     | PST            |Inactive|
    When I click on the "Save" Button on "Geofence" Screen
    And I uncheck the Active Geofences Only Checkbox
    Then the geofence gets saved successfully and it is displayed in the "Geofences" grid
#   Core-3843 Verify that zip codes of newly added geofence are present in downloaded file
    When I click on the geofence "new-geofence"
    #CORE-4010 test case incorporated
    Then I observe log details in the Geo-History section
    And I note the Geo History log records count
    And I edit the geofence "new-geofence"
    And I "activate" status for "new-geofence" geofence
    And I open a newly created geofence
    And I check that log record is shown for "Status" change in Geo History
    And I check correct log details are shown
    And I click on changes hyperlink
    Then I should see all fields with old and new changed value
    And I click on the "Cancel" Button
    And I open a newly created geofence
    And I edit the geofence "ZONE-"
    And I change the "Region" for the geofence
    |New_Region|
    |Northeast |
    When I click on the "Save" Button on "Geofence" Screen
    And I open a newly created geofence
    And I check that log record is shown for "Region" change in Geo History
    And I check correct log details are shown
    And I click on changes hyperlink
    Then I should see all fields with old and new changed value
    And I click on the "Cancel" Button
    And I open a newly created geofence
    And I edit the geofence "ZONE-"
    And I change the "Timezone" for the geofence
      |Geo-TimeZone|
      |IST         |
    When I click on the "Save" Button on "Geofence" Screen
    And I open a newly created geofence
    And I check that log record is shown for "Timezone" change in Geo History
    And I check correct log details are shown
    And I click on changes hyperlink
    Then I should see all fields with old and new changed value
    And I click on the "Cancel" Button
    And I open a newly created geofence
    And I edit the geofence "ZONE-"
    And I change the "Geo-Coding" for the geofence
      |Primary                                 |Secondary                              |
      |e{o~FpctuOjE\|j_Ao\|e@veBfe@mbt@lqe@_vN |km_}FhtotOznYf~gDcoeDxy]cx@stsBlmoC{orB|
    When I click on the "Save" Button on "Geofence" Screen
    And I open a newly created geofence
    And I check that log record is shown for "Geo-Coding" change in Geo History
    And I check correct log details are shown
    And I click on changes hyperlink
    Then I should see all fields with old and new changed value
    And I click on the "Cancel" Button
    And I click on "Download Zip Codes" button
    Then I verify if "new-geofence" are downloaded

  @sanity
  @regression
  Scenario: Verify Available Timezones And Statuses On New Geofence
    When I click on the "Scale" Button
    Then the following timezones are listed in the "Geo-TimeZone" dropdown
      |Timezone|
      |MST     |
      |EST     |
      |CST     |
      |PST     |
      |IST     |
    And the following statuses are listed in the "Geo-Status" dropdown
      |Status  |
      |Active  |
      |Inactive|

  @sanity
  @regression
  Scenario: Verify Only Active Timezones Are Listed In Geofence Filter Throughout Application
    When I navigate to following pages one by one
      |Page |
      | Dashboard    |
      | Customers     |
      | Drivers       |
      | All Deliveries          |
      | Scheduled Deliveries |
      | Live Deliveries |
      |Partners    |
    Then I should see active zone in the dropdown on the "respective" page
    And I should not see inactive zone in the dropdown on the "respective" page

  @sanity
  @regression
    #Issues logged- CORE-6481
    Scenario: Verify Settings Behavior Of Solo And Duo Settings on Geofence
    When I click on the geofence "Chicago"
    And I click on the "Settings" Button on "Geofence" Screen
    Then I cannot uncheck "Solo" for "Driver(s) for Scheduled trip" settings when "Duo" is checked
    When I "uncheck" option "Duo" for Scheduled trip
    Then I can deselect "Solo" option for Scheduled trip
    When I "check" option "Duo" for Scheduled trip
    Then The "solo" gets selected automatically
    When I uncheck both on demand and Scheduled for a geofence
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then The validation error message is displayed


  
    #NEED TO VERIFY VALUES OF THESE PARAMETERS SCHEDULE_PICKUP_FROM_TIME and SCHEDULE_PICKUP_TO_TIME
  #this test script will fail as the value of above parameters are set as 30 mins and 840 mins.
  #It should be 15 mins and 1410 mins, currently validations are put considering 15 and 1410 mins
  @regression
  Scenario: Verify Minimum Scheduled Time For Solo Or Duo Trip Cannot Be More Than The Difference Between SCHEDULE_PICKUP_FROM_TIME And SCHEDULE_PICKUP_TO_TIME
    When I click on the geofence "Chicago"
    And I click on the "Settings" Button on "Geofence" Screen
    And I change the value of "Minimum scheduled time for Duo trip" to "14" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "15" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "16" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "841" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "840" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then Enter value should get saved and error message is not displayed
    And I click on the "Settings" Button on "Geofence" Screen
    #And I change the value of "Minimum scheduled time for Duo trip" to "1415" minutes
    #And I click on the "Save" Button on "Geofence Settings" Screen
    #Then check if error message is displayed for "duo trip"

    And I change the value of "Minimum scheduled time for Solo trip" to "14" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "15" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "16" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "841" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "840" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then Enter value should get saved and error message is not displayed
   # And I change the value of "Minimum scheduled time for Solo trip" to "1410" minutes
  # And I click on the "Save" Button on "Geofence Settings" Screen
  #  Then check if error message is displayed for "solo trip"
   # And I change the value of "Minimum scheduled time for Solo trip" to "1415" minutes
   # And I click on the "Save" Button on "Geofence Settings" Screen
    #Then check if error message is displayed for "solo trip"

 
        #NEED TO VERIFY VALUES OF THIS PARAMETER SCHEDULED_PICKUP_MAX_PROCESSING_TIME
  #In database this value is set as 120 mins, needs to be checked.
  @regression
  Scenario:Verify Minimum Scheduled Time For Solo Or Duo Trip Cannot Be Less Than SCHEDULED_PICKUP_MAX_PROCESSING_TIME
    When I click on the geofence "Chicago"
    And I click on the "Settings" Button on "Geofence" Screen
    And I change the value of "Minimum scheduled time for Duo trip" to "29" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "duo trip"
    And I change the value of "Minimum scheduled time for Duo trip" to "30" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then Enter value should get saved and error message is not displayed
    And I click on the "Settings" Button on "Geofence" Screen
    And I change the value of "Minimum scheduled time for Solo trip" to "29" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then check if error message is displayed for "solo trip"
    And I change the value of "Minimum scheduled time for Solo trip" to "30" minutes
    And I click on the "Save" Button on "Geofence Settings" Screen
    Then Enter value should get saved and error message is not displayed

  @regression
  Scenario: Verify and add new attribute in Geofence Attributes page
    When I load Geofence Attributes Page
    Then I should be directed to "Geofence Attributes Page"
    #Creating 'New Attribute' functionality through admin is removed from admin Portal V2
#    When I load Geofence Attributes Page and Click on New Attributes button
#    And I enter following values in "Geofence Attributes" fields
#      | Key                                              | Default-Value                   | Description  | Label|
#      | BusinessFAQ   | BusinessFAQ        | This is Business FAQ Link | BusinessFAQ |
#    And I click on the "Save" Button on "GeofenceAttributes" Screen
#    Then The geofence Attributes gets saved successfully and it is displayed in the grid
#    When I search by Name "BusinessFAQ" in "GeofenceAttributes" page geofence
#    And I check the Searched result is displayed correctly
#      Then I logout of Admin Portal

    #Creating 'New Attribute' functionality through admin is removed from admin Portal V2
#  @regression
#  Scenario: Verify and check attributes are empty
#    When I load Geofence Attributes Page and Click on New Attributes button
#    And I enter following values in "GeofenceAttributes" fields
#      | Key                                              | Default-Value                   | Description  | Label|
#      |      |        |  | |
#    When I click on the "Save" Button on "GeofenceAttributes" Screen
#    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed  in geofence popup

  #Creating 'New Attribute' functionality through admin is removed from admin Portal V2
#  @regression
#  Scenario: Verify Field Validations on Geofence Attributes page
#    When I load Geofence Attributes Page and Click on New Attributes button
#    And I click on the "Save" Button on "GeofenceAttributes" Screen
#    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed  in geofence popup
#    And I enter following values in "Geofence Attributes" fields
#      | Key                                              | Default-Value                   | Description  | Label|
#      | BusinessFAQ      | BusinessFAQ        |  | |
#    And I click on the "Save" Button on "GeofenceAttributes" Screen
#    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed  in geofence popup

    @regression
      #Issue Raised ADP-749
    Scenario:Verify setting driver-bungii cuts for geofence
      When I click on the geofence "Boston"
      And I click on the "Settings" Button on "Geofence" Screen
      And I set "Blank" % Bungii Cut Per Delivery for the geofence
      And I click on the "Save" Button on "Geofence Settings" Screen
      Then I see "Blank Bungii rate" validation error message.
      And I set "Above100" % Bungii Cut Per Delivery for the geofence
      Then I see "Above 100 Bungii rate" validation error message.
      And I set "Below Zero" % Bungii Cut Per Delivery for the geofence
      Then I see "Below Zero Bungii rate" validation error message.
      And I set "Valid" % Bungii Cut Per Delivery for the geofence
      Then I check that correct Driver cut calculated based on Bungii Cut Per Delivery

    #Creating 'New Attribute' functionality through admin is removed from admin Portal V2
#  @regression
#  Scenario: Verify An application error has occured message is not displayed when user keeps label field blank on Geofence Attributes page
#    When I load Geofence Attributes Page and Click on New Attributes button
#    And I enter following values in "Geofence Attributes" fields
#      | Key              | Default-Value                   | Description  | Label|
#      | Attr1            | Attr                            |  Desc       |      |
#    And I click on the "Save" Button on "GeofenceAttributes" Screen
#    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed  in geofence popup

# Core-3843 Verify that only active geofence zip codes are downloaded in csv file
  @regression
    #Issue Raised ADP-751
    Scenario: Verify that only active geofence zip codes are downloaded in csv file
      When I click on "Download Zip Codes" button
#     Core-3843 Verify that only active geofence zip codes are downloaded in csv file
      And I verify if "only active geofence zip codes" are downloaded
      #Core-4056: The date on downloaded zip code file name should be in proper format
      Then The date on the downloaded csv should have proper date format
#     Core-3843 Verify the downloaded zip codes file after active geofence is converted to inactive
      When I click on the geofence "Chicago"
      And I edit the geofence "Chicago"
      And I "deactivate" status for "Chicago" geofence
      And I click on "Download Zip Codes" button
      And I verify if "deactive geofence is not" are downloaded
#     Core-3843 Verify the downloaded zip codes after inactive geofence is converted to active
      And I uncheck the Active Geofences Only Checkbox
      When I click on the geofence "Chicago-inactive"
      And I edit the geofence "Chicago"
      And I "activate" status for "Chicago" geofence
      And I click on "Download Zip Codes" button
      Then I verify if "active geofence" are downloaded
#     Core-3843 Verify that zip codes of extended part of geofence are updated on the csv file
      Then I verify if "count of Chicago" are downloaded
      When I click on the geofence "Chicago"
      And I edit the geofence "Chicago"
      And I "extend" geofence polylines
      And I click on "Download Zip Codes" button
      Then I verify if "count of Chicago after extend" are downloaded
#    Core-3843 Verify that zip codes are removed from csv file when the geofence plotting is reduced
      When I click on the geofence "Chicago"
      And I edit the geofence "Chicago"
      And I "reduce" geofence polylines
      And I click on "Download Zip Codes" button
      Then I verify if "count of Chicago after reduce" are downloaded


# Core-4701 Verify that Scheduled Driver Search time doesn't change when Driver Boosted Earning Period is changed
  @ready
  Scenario: Verify that Scheduled Driver Search time doesnt change when Driver Boosted Earning Period is changed
    When I click on the geofence "Chicago"
    And I click on the "Settings" Button on "Geofence" Screen
    Then I check "scheduled driver search time"
    And I change the "driver boosted earnings"
    Then I click on the "Save" Button on "Geofence" Screen
    And I click on the "Settings" Button on "Geofence" Screen
    Then I check "scheduled driver search time after edit"
