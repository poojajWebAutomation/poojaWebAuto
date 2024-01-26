@web
Feature: Admin_OndemandTrips

  Background:
    Given I am logged in as Admin

  @sanity
  @regression
    #stable
    #test data created in base
#  Scenario: Verify Manually Ending Bungii As An Admin For Solo Ondemand Pickup
#  CORE-3257 - Manually end bungii functionality is removed
  Scenario: Verify Manually End Bungii Link is Removed for Solo Ondemand Pickup
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000003 | Testcustomertywd_appleweb CustC|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverC" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      |Accepted |
      | Arrived |
      | Loading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    When I view the delivery details
    Then the Bungii details is displayed successfully
    And Manually end bungii link is removed for live trips
#    And I click on "Manually End Bungii" link
#    And Enter the End Date and Time
#    And Click on "Calculate Cost" button
#    Then the amount is calculated and shown to admin
#    And Click on "Confirm" button
#    And I view the Deliveries list on the admin portal
#    #Then The Delivery List page should display the trip in "Payment Successful" state
#    Then The Delivery List page should display the delivery in "Payment Successful" state
    And As a driver "Testdrivertywd_appledc_a_web TestdriverC" perform below action with respective "Solo Ondemand" trip
      | driver1 state|
      |Driving To Dropoff |
      | Unloading Items |
      | Bungii Completed |

  @sanity
  @regression
    #test data created in base
    #changed to "Solo Ondemand" from "Solo Scheduled"
  Scenario: Verify Delivery List Status Updation For Solo Ondemand Pickup
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9284000004 | Testcustomertywd_appleweb CustD|
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Assigning Driver(s)|
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Accepted |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Trip Started |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Arrived |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driver(s) Arrived |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Loading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Loading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Driving To Dropoff |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Driving To Dropoff |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Unloading Item |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      | Status |
      | Unloading Items |
    And As a driver "Testdrivertywd_appledc_a_web TestdriverD" perform below action with respective "Solo Ondemand" Delivery
      | driver1 state|
      | Bungii Completed |
    And I view the Deliveries list on the admin portal
    Then The Delivery List page should display the delivery in "Payment Successful" state
    
  @ready
    @creditcardprocessing
  Scenario: Verify Driver Does Not receive Ondemand requests If He Is Not Assigned To Geofence In Which His Current Location Is
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999101 | Testcustomertywd_appleNewQA Customer|
    Then The driver "Testdrivertywd_appledc_a_web TestdriverY" should receive On Demand requests as he is assigned to "washingtondc" geofence
    When I cancel "On Demand" of customer "9999999101"
    And I request "Solo Ondemand" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999101 | Testcustomertywd_appleNewQA Customer|
    Then the driver "Testdrivertywd_appledc_a_web TestdriverY" should not receive On Demand requests as he is assigned NOT to "goa" geofence


  @regression
  Scenario:  Verify Search On Delivery List
    When I click on "Deliveries > All Deliveries" Menu
    And I change filter to "The Beginning of Time" on All deliveries
    And I search by client name "Vishal"
    Then All the clients named "Vishal" should be displayed on the delivery list grid

  @regression
    #Issue Raised ADP-683
    #stable
  Scenario: Verify Filters On Delivery List
    When I click on "Deliveries > All DeliveriesPage" Menu
    And I click on "Filter" icon on "All Deliveries" Page
    Then All statuses except "Price Estimated" are selected
    And All types and categories are selected
    When I select filter "Statuses" as "Payment Unsuccessful"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Payment Unsuccessful Status"
    When I select filter "Statuses" as "Driver Not Arrived"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Driver Not Arrived Status"
    When I select filter "Statuses" as "Payment Successful"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Payment Successful Status"
    When I select filter "Statuses" as "Customer Canceled"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Customer Canceled Status"
    When I select filter "Statuses" as "Driver Canceled"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Driver Canceled Status"
    When I select filter "Statuses" as "Admin Canceled"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Admin Canceled Status"
    When I select filter "Statuses" as "Pickup with Error"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Pickup with Error Status"
    When I select filter "Statuses" as "Price Estimated"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Price Estimated Status"
    When I select filter "Statuses" as "No Driver(s) Found"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "No Driver(s)Found Status"
    When I select filter "Statuses" as "Driver Removed"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Driver Removed Status"
    When I select filter "Statuses" as "Promoter Payment Pending"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Promoter Payment Pending Status"
    When I select filter "Type" as "Solo"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Solo Type"
    When I select filter "Type" as "Duo"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Duo Type"
    When I select filter "Category" as "On-Demand"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "On-Demand Category"
    When I select filter "Category" as "Scheduled"
    And I click on "Apply" button on "All Deliveries" page
    Then the triplist grid shows the results by type "Scheduled Category"

#Core 2968 -To verify that admin can add Accessorial fee for driver cancelled on demand trip
  @regression
  Scenario:To verify that admin can add Accessorial fee for driver cancelled on demand trip
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence
    | Bungii Time   | Customer Phone | Customer Name |
    | NEXT_POSSIBLE | 8877661116 | Testcustomertywd_appleMarkDM LutherDM|
    And As a driver "Testdrivertywd_appledc_a_drvI WashingtonI" perform below action with respective "Solo Ondemand" Delivery
    | driver1 state|
    |Accepted      |
    When I cancel bungii as a driver "Testdrivertywd_appledc_a_drvI WashingtonI"
    And I wait for 2 minutes
    And I view the Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
    Then I should see "Accessorial Charges" section displayed
    #CORE-5251 : To verify the FeeType list under Accessorial charge is displayed with all the option in alphabetical order
    When I click on "Select Fee Type" dropdown
    Then I should see Fee type sorted in alphabetical order
    When I add following accessorial charges and save it
    | Amount   | Fee Type                     | Comment                                   | Driver Cut |
    |  18      | Additional Mileage           | Charges due to Additional Mileage          | 1          |
    |  12      | Additional Weight / Pallet   | Charges due to Additional Weight / Pallet  | 2          |
    |  14      | Cancelation                  | Charges due to Cancelation                 | 3          |
    |  18      | Customer Rejected / Returned | Charges due to Customer Rejected / Returned| 4          |
    |  10      | Excess Wait Time             | Charges due to Excess wait                 | 2          |
    |  20.5    | Limited Access               | Charges due to Limited Access              | 4.5        |
    |  25.65   | Mountainous                  | Charges due to mountainous reason          | 10         |
    |  100     | Other                        | Charges due to other reasons               | 20         |
    And I should see following details in the Accessorial charges section
    | Additional Mileage | Additional Weight / Pallet | Cancelation |Customer Rejected / Returned |Excess Wait Time | Limited Access | Mountainous | Other | Total   |
    | $18 | $12 | $14 | $18 | $10 | $20.5 | $25.65 | $100  | $218.15 |
#   Core-4307: Verify the history is displayed for accessorial fees
    And I navigate back to Scheduled Deliveries
    And I click on the dropdown beside scheduled bungii
    When I click the "Notes & History On Completed Delivery" link
    And I click on "History"
    Then I should be able to see "accessorial charges"

  #CORE-3295:Verify admin is not able to edit the on demand trips when its status is assigning driver on Live deliveries screen
  @regression
Scenario:Verify admin is not able to edit the on demand trips when its status is assigning driver on Live deliveries screen
    When I request "Solo Ondemand" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661090 | Testcustomertywd_appleMarkCM LutherCM|
    And I wait for 2 minutes
    And I view the Live Deliveries list on the admin portal
    When  I search the delivery using "Pickup Reference"
    Then The edit option should not be displayed for live deliveries
#    Core-3294: Verify Stop search button is not displayed for customer on demand trips
    When I view the delivery details for live deliveries
    Then I check if "Stop Searching" button is not present

#CORE-2584:To verify the customer ON DEMAND delivery marked as Payment successful from payment unsuccessful
 @ready
   #Issue Raised ADP-722
  Scenario:To verify the customer ON DEMAND delivery marked as Payment successful from payment unsuccessful
    When I request "Solo Ondemand" Bungii as a customer in "phoenix" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 8877661102 | Testcustomertywd_appleMarkCY LutherCY|
    And As a driver "Testdrivertywd_appleph_a_drvaz Phoenix_az" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      |Arrived         |
      |Loading Item     |
      |Driving To Dropoff |
      |Unloading item    |
      |Bungii Completed  |
    And I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The "All Deliveries" should be in "Payment Pending" state
    And I click on the "Change Payment status" link beside scheduled bungii for "Payment Pending Deliveries"
#    When I click on the "Change Payment status" button from the dropdown
    And the "Are you sure, you want to change the payment status?" message is displayed
    Then I should see all the information in the change payment status modal
    And I click on "Confirm Change Payment Status" button
    And I wait for 2 minutes
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then The "All Deliveries" should be in "Payment Successful" state