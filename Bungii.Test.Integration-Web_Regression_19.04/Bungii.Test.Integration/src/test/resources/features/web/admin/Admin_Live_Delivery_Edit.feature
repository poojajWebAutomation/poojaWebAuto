@web
Feature: Admin_Live_Delivery_Edit

  Background:
    Given I am logged in as Admin

  @regression
    #stable
  Scenario: Verify editing drop off address for the Solo scheduled live delivery.
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999207     | Testcustomertywd_appleNewT Customer|
    And As a driver "Testdrivertywd_appledc_a_webdd Testdriverdd" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
    And I view the Live Deliveries list on the admin portal
    And I wait for 2 minutes
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | Loading Items |
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    #CORE-4152:To verify updated projected estimated delivery time for trips on live admin edit address
    And I view the Live Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    When I click on the "Delivery Details" button from the dropdown
    And I click on "Delivery Overview" button on delivery details
    Then The "Scheduled Time" for customer delivery should match
    Then The "Estimate dropOff time after admin live edit" for customer delivery should match
    And I view the Live Deliveries list on the admin portal
    When I open the live delivery details in admin portal
    Then the updated drop off address should be displayed on delivery details page
    And Delivery price is recalculated based on updated value of drop off address

  @regression
    #stable
    Scenario: Verify editing pickup address for the Solo live delivery.
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999209     | Testcustomertywd_appleNewV Customer|
    And As a driver "Testdrivertywd_appledc_a_webff Testdriverff" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | Loading Items |
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I edit the pickup address
    Then I change the pickup address to "4400 Massachusetts Avenue Northwest"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    And  I refresh the page
    And I click on the dropdown beside scheduled bungii
    And I wait for "2" mins
    Then I should see the "History" underlined
    When I click on the Notes link for Live Deliveries
    And I click on "History"
    Then The "History" tab should be selected
    And I should see pickup address edit history
    And I close the Note
    When I open the live delivery details in admin portal
    And I confirm the change pickup address on delivery details page
    And Delivery price is recalculated based on updated value of drop off address
  
  @regression
    #stable
     Scenario: Verify editing drop off address for the Partner Portal Solo live delivery.
      When I request Partner Portal "SOLO" Trip for "MRFM" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Accepted      |
        | Enroute       |
        | Arrived       |
        | Loading Item   |
      And I view the Live Deliveries list on the admin portal
      Then I should be able to see the respective bungii with the below status
        |  Status       |
        | Loading Items |
      When I click on "Edit" link beside live delivery
      And I click on "Edit Trip Details" radiobutton
      And I edit the drop off address
      Then I change the drop off address to "400 Speedway Boulevard"
      And I click on "Verify" button on Edit Scheduled bungii popup
      When I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      And I wait for "2" mins
#     CORE-4118: Verify Partner email is not sent when payment method used is Customer Card
      And Partner firm should not receive "Bungii Delivery Updated" email
      When I open the live delivery details in admin portal
      Then the updated drop off address should be displayed on delivery details page
      And Delivery price is recalculated based on updated value of drop off address

    #CORE-3257
  @regression
    Scenario Outline:Verify that admin user is able to cancel customer live delivery with <TripStatus> status
      When I request "Solo Scheduled" Bungii as a customer in "nashville" geofence
        | Bungii Time   | Customer Phone  | Customer Name |
        | NEXT_POSSIBLE | <CustomerPhone> | <CustomerName>|
      And As a driver "<DriverName>" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Accepted      |
      And As a driver "<DriverName>" perform below action with respective "Solo Scheduled" trip
        | driver1 state    |
        | <DriverStatus> |
      And I view the Live Deliveries list on the admin portal
      And I wait for 2 minutes
      Then I should be able to see the respective bungii with the below status
        |  Status       |
        | <TripStatus> |
      And I wait for 2 minutes
      And I click on "Edit" link beside live delivery
      And I click on "Edit Delivery Status" radiobutton
      And I click on "Delivery Canceled" radiobutton
      And I click on "UPDATE BUNGII" button
      Then The "Pick up has been successfully canceled." message should be displayed for live delivery
      And I wait for 2 minutes
      And I view the Deliveries list on the admin portal
      When  I search the delivery using "Pickup Reference"
      Then The Delivery List page should display the delivery in "Driver Canceled" state

      Examples:
      |DriverStatus        |CustomerPhone|CustomerName                        |DriverName                                |TripStatus  |
      |Enroute             |9999999137   |Testcustomertywd_appleNewRL Customer|Testdrivertywd_applens_a_kayD Stark_nsOnED|Trip Started|
      |Arrived             |9999999138   |Testcustomertywd_appleNewRM Customer|Testdrivertywd_applens_a_kayE Stark_nsOnEE|Driver(s) Arrived|
      |Loading Item        |9999999139   |Testcustomertywd_appleNewRN Customer|Testdrivertywd_applens_a_kayF Stark_nsOnEF|Loading Items    |
      |Driving To Dropoff  |9999999143   |Testcustomertywd_appleNewRR Customer|Testdrivertywd_applens_a_kayG Stark_nsOnEG|Driving To Dropoff    |
      |Unloading Item      |9999999144   |Testcustomertywd_appleNewRS Customer|Testdrivertywd_applens_a_kayH Stark_nsOnEH|Unloading Items   |

  @ready
  Scenario Outline:Verify that admin user is able to cancel partner live delivery with <StatusType> status
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | <CustomerPhone> | <CustomerName>|
    And As a driver "<DriverName>" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And As a driver "<DriverName>" perform below action with respective "Solo Scheduled" trip
      | driver1 state    |
      | <DriverStatus> |
    And I view the Live Deliveries list on the admin portal
    And I wait for 2 minutes
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | <TripStatus> |
    And I click on "Edit" link beside live delivery
    And I click on "Edit Delivery Status" radiobutton
    And I click on "Delivery Canceled" radiobutton
    And I click on "UPDATE BUNGII" button
    Then The "Pick up has been successfully canceled." message should be displayed for live delivery
    And I view the Deliveries list on the admin portal
    And I wait for 2 minutes
    And  I search the delivery using "Pickup Reference"
    Then The Delivery List page should display the delivery in "Driver Canceled" state
    #CORE-3372:To verify delivery status is updated when PartnerPortal delivery is marked as Delivery Canceled on Live deliveries
    When I navigate to "Partner" portal configured for "normal" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I click "Track Deliveries" button on Partner Portal
    #And I select "Check / uncheck all" option from the filter
    And I select "Canceled" option from the filter
    And I click on "Apply" button
    When I click on the delivery based on customer name
    And I get time stamp for "Admin Cancelled" delivery step
    Then The admin "Canceled" delivery should be highlighted in partner portal delivery details page



    Examples:
      |DriverStatus        |CustomerPhone|CustomerName                        |DriverName                              |TripStatus  |
      |Enroute             |9999999150   |Testcustomertywd_appleNewRY Customer|Testdrivertywd_appleks_a_drvaa Kansas_aa|Trip Started|

  @regression
  Scenario Outline:Verify that admin user is able to mark customer live delivery complete with <TripStatus> status
    When I request "Solo Scheduled" Bungii as a customer in "nashville" geofence
      | Bungii Time   | Customer Phone  | Customer Name |
      | NEXT_POSSIBLE | <CustomerPhone> | <CustomerName>|
    And As a driver "<DriverName>" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And As a driver "<DriverName>" perform below action with respective "Solo Scheduled" trip
      | driver1 state    |
      | <DriverStatus> |
    And I wait for 2 minutes
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | <TripStatus> |
    And I click on "Edit" link beside live delivery
    And I click on "Edit Delivery Status" radiobutton
    And I click on "Delivery Completed" radiobutton
    And I enter delivery completion date and time as per geofence
    And I click on "CALCULATE COST" button
    Then Confirmation message on edit live delivery pop up should be displayed
    And I click on "Confirm" button
    And I click on "Close" button
    And I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The "All Deliveries" should be in "Payment Successful" state

    Examples:
      |DriverStatus        |CustomerPhone|CustomerName                        |DriverName                                |TripStatus  |
      |Enroute             |9999999145   |Testcustomertywd_appleNewRT Customer|Testdrivertywd_applens_a_kayI Stark_nsOnEI|Trip Started|
      |Arrived             |9999999146   |Testcustomertywd_appleNewRU Customer|Testdrivertywd_applens_a_kayJ Stark_nsOnEJ|Driver(s) Arrived|
      |Loading Item        |9999999147   |Testcustomertywd_appleNewRV Customer|Testdrivertywd_applens_a_kayK Stark_nsOnEK|Loading Items    |
      |Driving To Dropoff  |9999999148   |Testcustomertywd_appleNewRW Customer|Testdrivertywd_applens_a_kayL Stark_nsOnEL|Driving To Dropoff    |
      |Unloading Item      |9999999149   |Testcustomertywd_appleNewRX Customer|Testdrivertywd_applens_a_kayM Stark_nsOnEM|Unloading Items   |

  @ready
  Scenario Outline:Verify that admin user is able to mark partner live delivery complete with <TripStatus> status
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | <CustomerPhone> | <CustomerName>|
    And As a driver "<DriverName>" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And As a driver "<DriverName>" perform below action with respective "Solo Scheduled" trip
      | driver1 state    |
      | <DriverStatus> |
    And I wait for 2 minutes
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | <TripStatus> |
    And I click on "Edit" link beside live delivery
    And I click on "Edit Delivery Status" radiobutton
    And I click on "Delivery Completed" radiobutton
    And I enter delivery completion date and time as per geofence
    And I click on "CALCULATE COST" button
    Then Confirmation message on edit live delivery pop up should be displayed
    And I click on "Confirm" button
    Then The "Pick up has been successfully updated." message should be displayed for live delivery
    And I click on "Close" button
    And I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The "All Deliveries" should be in "Payment Successful" state

    #CORE-3372:To verify delivery status is updated when PartnerPortal delivery is marked as Delivery complete on Live deliveries
    When I navigate to "Partner" portal configured for "normal" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I click "Track Deliveries" button on Partner Portal
    And I select "Check / uncheck all" option from the filter
    And I click on "Apply" button
    And I select "Check / uncheck all" option from the filter
    And I click on "Apply" button
    And I select "Completed" option from the filter
    And I click on "Apply" button
    When I click on the delivery based on customer name
    And I get time stamp for "Admin Completed" delivery step
    Then The admin "Done" delivery should be highlighted in partner portal delivery details page


    Examples:
      |DriverStatus        |CustomerPhone|CustomerName                        |DriverName                                |TripStatus  |
      |Enroute             |9999999151   |Testcustomertywd_appleNewZ Customer|Testdrivertywd_appleks_a_drvab Kansas_ab|Trip Started|
