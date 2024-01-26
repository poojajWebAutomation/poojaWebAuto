@web
@new
Feature: Admin_Accessorial_Charges
  Background:
	Given I am logged in as Admin

@test
@regression
#CORE-2447 : issue still exist in qaauto
#CORE-5251 : Verify that admin can add accessorial charge for Payment Successful customer Solo Scheduled Deliveries
Scenario: Verify Accessorial Charges Can be added to Payment Successful Solo Scheduled Deliveries
When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
| Bungii Time   | Customer Phone | Customer Name |
| NEXT_POSSIBLE | 9999999221 | Testcustomertywd_appleNewMH Customer|
And As a driver "Testdrivertywd_appledc_a_drva Driver" perform below action with respective "Solo Scheduled" Delivery
| driver1 state|
|Accepted |
| Enroute  |
| Arrived |
| Loading Item |
| Driving To Dropoff |
| Unloading Item |
| Bungii Completed |
And I wait for 2 minutes
And I view the Deliveries list on the admin portal
And I search the delivery of Customer and view it
Then I should see "Accessorial Charges" section displayed
When I add following accessorial charges and save it
| Amount   | Fee Type                     | Comment                                    | Driver Cut |
|  8       | Additional Mileage           | Charges due to Additional Mileage          | 1          |
|  12      | Additional Weight / Pallet   | Charges due to Additional Weight / Pallet  | 2          |
|  15      | Cancelation                  | Charges due to Cancelation                 | 3          |
|  18      | Customer Rejected / Returned | Charges due to Customer Rejected / Returned| 4          |
|  20      | Excess Wait Time             | Charges due to Excess wait                 | 5          |
|  20.5    | Limited Access               | Charges due to Limited Access              | 5.5        |
|  25.65   | Mountainous                  | Charges due to mountainous reason          | 10         |
|  100     | Other                        | Charges due to other reasons               | 20         |
Then I should see the following fee type displayed
| Fee Type         			   |
| Additional Mileage           |
| Additional Weight / Pallet   |
| Cancelation                  |
| Customer Rejected / Returned |
| Excess Wait Time 			   |
| Limited Access               |
| Mountainous                  |
| Other                        |
And I should see following details in the Accessorial charges section
| Additional Mileage | Additional Weight / Pallet | Cancelation |Customer Rejected / Returned |Excess Wait Time | Limited Access | Mountainous | Other | Total   |
| $8                 | $12                        | $15         | $18                         | $20             | $20.5          | $25.65      | $100  | $219.15 |
And I click on the Accessorial Charges links and I should see the Drivers cut displayed
| Fee Type                     | Driver Cut |
| Additional Mileage           | 1          |
| Additional Weight / Pallet   | 2          |
| Cancelation                  | 3          |
| Customer Rejected / Returned | 4          |
| Excess Wait Time             | 5          |
| Limited Access               | 5.5        |
| Mountainious                 | 10         |
| Other                        | 20         |
 And "accessorial_fee_amount" should show total amount in the triprequest table in Database
 And "business_notes" should show comment without quotes in the trippaymentdetails table in Database
 And I wait for 2 minutes
 And I should see the following fee type displayed in the Report Database
| Fee Type         			   |
| Additional Mileage           |
| Additional Weight / Pallet   |
| Cancelation                  |
| Customer Rejected / Returned |
| Excess Wait Time 			   |
| Limited Access               |
| Mountainous                  |
| Other                        |

@ready
Scenario: Verify Accessorial Charges Field Validations - Blank
When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
| Bungii Time   | Customer Phone | Customer Name |
| NEXT_POSSIBLE | 9999999222 | Testcustomertywd_appleNewMI Customer|
And As a driver "Testdrivertywd_appledc_a_drvb Driver" perform below action with respective "Solo Scheduled" Delivery
| driver1 state|
|Accepted |
| Enroute  |
| Arrived |
| Loading Item |
| Driving To Dropoff |
| Unloading Item |
| Bungii Completed |
And I wait for 2 minutes
And I view the Deliveries list on the admin portal
And I search the delivery of Customer and view it
Then I should see "Accessorial Charges" section displayed
And I should get following error for following accessorial charges fields values when saved
|Amount |Driver Cut|Fee Type                     |Comment         | Field         | Message                |
| Blank |     Blank|Blank                        | Blank          | Fee Type      |Please select fee type. |
| Blank |     Blank|Blank                        | This is Comment| Fee Type      |Please select fee type. |
| Blank |     Blank|Excess Wait Time             | Blank          | Amount        |Please enter amount.    |
| Blank |     Blank|Additional Mileage           | This is Comment| Amount        |Please enter amount.    |
| 10	|     Blank|Cancelation                  | Blank          | Driver Amount |Please enter driver cut.|
| 10	|     Blank|Additional Weight / Pallet   | This is Comment| Driver Amount |Please enter driver cut.|
| 10    |         2|Blank                        | Blank          | Fee Type      |Please select fee type. |
| 10    |         2|Blank                        | This is Comment| Fee Type      |Please select fee type. |
| 10    |         2| Customer Rejected / Returned| Blank          | Comment       |Please add a comment.   |
| 10    |         2|Excess Wait Time             | Accessorial charges Comments: Accessorial charges comment having more than 500 characters in Excess Wait Time field column entered to identify whether it causes issues like "CORE-2446 SPRINT43:: QA environment:: Saving Comments of 500 characters for accessorial fees gives Application Error" Please note that If data gets validation message  without any application error then it means that the above issue no longer exists and it is working as expected. !!! Accessorial charges Comments Ends, Thank you!!!!|Comment|Comment exceeds 500 characters.|

@regression
Scenario: Verify Accessorial Charges Field Validations - invalid Data
When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
  | Bungii Time   | Customer Phone | Customer Name                        |
  | NEXT_POSSIBLE | 9999999223     | Testcustomertywd_appleNewMJ Customer |
 And As a driver "Testdrivertywd_appledc_a_drvc Driver" perform below action with respective "Solo Scheduled" Delivery
| driver1 state      |
| Accepted           |
| Enroute            |
| Arrived            |
| Loading Item       |
| Driving To Dropoff |
| Unloading Item     |
| Bungii Completed   |
And I wait for 2 minutes
And I view the Deliveries list on the admin portal
And I search the delivery of Customer and view it
Then I should see "Accessorial Charges" section displayed
And I should get following error for following accessorial charges fields values when saved
| Amount       | Driver Cut | Fee Type                     | Comment                                   | Field  | Message                                             |
|100000000000  | 1          | Excess Wait Time             | Charges due to excess waiting             | Amount | Amount can contain at most 3 digits and 2 decimals. |
| 1000         | 32         | Additional Mileage           | Charges due to additional mileage         | Amount | Amount can contain at most 3 digits and 2 decimals. |
| 10.5689      | -100       | Cancelation                  | Charges due to cancelation                | Amount | Amount can contain at most 3 digits and 2 decimals. |
| -10          | Blank      | Additional Weight / Pallet   | Charges due to additional weight/pallet   | Driver Amount | Please enter driver cut. |
| -1.56        | 231        | Customer Rejected / Returned | Charges due to customer rejected/returned | Driver Amount | Driver cut cannot be more than total accessorial fees.|
| -1.56        | -10        | Excess Wait Time             | Charges due to excess waiting             | Driver Amount | Driver cut can contain at most 3 digits and 2 decimals.|

#Core 2968 -To verify that admin can add accessorial fees to driver cancelled partner delivery
#CORE-5251 : To verify revive trip when Accessorial charge is added
@regression
Scenario: To verify the revive trip when accessorial charges are added
When I request Partner Portal "SOLO" Trip for "MRFM" partner
|Geofence| Bungii Time   | Customer Phone | Customer Name |
|Kansas  | NEXT_POSSIBLE | 9999999228     | Testcustomertywd_appleNewMO Customer|
And As a driver "Testdrivertywd_appleks_a_drvae Kansas_ae" perform below action with respective "Solo Scheduled" partner portal trip
| driver1 state |
| Accepted      |
| Enroute      |
And I wait for 1 minutes
And As a driver "Testdrivertywd_appleks_a_drvae Kansas_ae" perform below action with respective "Solo Scheduled" partner portal trip
| driver1 state |
| Driver Canceled |
#Core-4330 Verify terms and privacy policy is displayed on login page
And I login to driver portal on a new tab with driver phone number "9049840210"
And I click on "Driver Details" link to get the total driver earnings value screen and navigate back to admin portal
And I wait for 2 minutes
And I view the Deliveries list on the admin portal
And I should see field name as partner on delivery listing screen
When  I search the delivery using "Pickup Reference"
And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
And I should see field name as partner on delivery detail screen
Then I should see "Accessorial Charges" section displayed
When I add following accessorial charges and save it
| Amount   | Fee Type                     | Comment                                    | Driver Cut |
|  18      | Additional Mileage           | Charges due to Additional Mileage          | 5          |
|  13      | Additional Weight / Pallet   | Charges due to Additional Weight / Pallet  | 6          |
|  14      | Cancelation                  | Charges due to Cancelation                 | 3          |
|  19      | Customer Rejected / Returned | Charges due to Customer Rejected / Returned| 5          |
|  10      | Excess Wait Time             | Charges due to Excess wait                 | 2          |
|  20.5    | Limited Access               | Charges due to Limited Access              | 4.5        |
|  25.65   | Mountainous                  | Charges due to mountainous reason          | 10         |
|  100     | Other                        | Charges due to other reasons               | 20         |
And I should see following details in the Accessorial charges section
| Additional Mileage  | Additional Weight / Pallet| Cancelation| Customer Rejected / Returned| Excess Wait Time | Limited Access | Mountainous | Other | Total   |
| $18                 | $13                       | $14        | $19                         | $10              | $20.5          | $25.65      | $100  | $220.15 |
And I view the Deliveries list on the admin portal
When  I search the delivery using "Pickup Reference"
Then Revive button should be displayed beside the trip
When I click on "Revive" button
Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
When I click on "Confirm" button on Revival Popup
And I wait for 2 minutes
And I view the all Scheduled Deliveries list on the admin portal
When  I search the delivery using "Pickup Reference"
#Core-3295:Verify status is Assigning driver for driver cancelled trip which was revived after adding Accessorial charges
Then The delivery should be in "Assigning Driver(s) with no loader" state
And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
Then the Accessorial Charges should not be displayed
And I view the Deliveries list on the admin portal
And I use the old pickup reference to search the driver cancelled trip
And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
Then I should see "Accessorial Charges" section displayed
And I should see following details in the Accessorial charges section
| Additional Mileage  | Additional Weight / Pallet| Cancelation| Customer Rejected / Returned| Excess Wait Time | Limited Access | Mountainous | Other | Total   |
| $18                 | $13                       | $14        | $19                         | $10              | $20.5          | $25.65      | $100  | $220.15 |
And I click on the Accessorial Charges links and I should see the Drivers cut displayed
| Fee Type                     | Driver Cut |
| Additional Mileage           | 5          |
| Additional Weight / Pallet   | 6          |
| Cancelation                  | 3          |
| Customer Rejected / Returned | 5          |
| Excess Wait Time             | 2          |
| Limited Access               | 4.5        |
| Mountainious                 | 10         |
| Other                        | 20         |
And I navigate to "Driver" portal
Then The accessorial charges cut should be displayed in total earnings

#CORE-3381 : To verify that admin can add accessorial charges for partner canceled deliveries after revival
#CORE-5251 : To verify revive trip when no Accessorial charge is added
@ready
Scenario: To verify that admin can add accessorial charges for partner canceled deliveries after revival
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
    |Geofence| Bungii Time   | Customer Phone | Customer Name |
    |Kansas  | NEXT_POSSIBLE | 8877661064     | Testcustomertywd_BppleMarkBM LutherBM|
	Given I navigate to "Partner" portal configured for "normal" URL
	And I enter "valid" password on Partner Portal
	And I click "SIGN IN" button on Partner Portal
	Then I should "be logged in"
	And I click "Track Deliveries" button on Partner Portal
	And I click on the delivery based on customer name
	And I click "Cancel Delivery link" button on Partner Portal
	And I click "Cancel Delivery" button on Partner Portal
	Then I click "OK" button on Partner Portal
    And I am logged in as Admin
	And I wait for 2 minutes
	And I view All Deliveries list on the admin portal
	And I search the delivery using "Pickup Reference"
	Then The "All Deliveries" should be in "Partner Canceled" state
#	Core-4307: Verify the history is displayed for PARTNER canceled delivery when DRIVER NOT ACCEPTED
	And I click on the dropdown beside scheduled bungii
	When I click the "Notes & History On Completed Delivery" link
	And I click on "History"
	Then I should be able to see "partner cancelled event - driver not accepted"
	And I close the Note
	When I view the Deliveries list on the admin portal
	And I search the delivery using "Pickup Reference"
	Then Revive button should be displayed beside the trip
	When I click on "Revive" button
	Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId anad Pickup Origin
	When I click on "Confirm" button on Revival Popup
	And I wait for 2 minutes
	And I view the all Scheduled Deliveries list on the admin portal
	And  I search the delivery using "Pickup Reference"
	Then I should be able to see the respective bungii with the below status
		|  Status |
		| Assigning Driver(s)|
	And I view the Deliveries list on the admin portal
	And I search the delivery using old pickup reference
	And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
	Then I should see "Accessorial Charges" section displayed
	When I add following accessorial charges and save it
		| Amount   | Fee Type                     | Comment                                    |
		|  25      | Additional Mileage           | Charges due to Additional Mileage          |
		|  18      | Additional Weight / Pallet   | Charges due to Additional Weight / Pallet  |
		|  12      | Cancelation                  | Charges due to Cancelation                 |
		|  19      | Customer Rejected / Returned | Charges due to Customer Rejected / Returned|
		|  10      | Excess Wait Time             | Charges due to Excess wait                 |
		|  30.50   | Limited Access               | Charges due to Limited Access              |
		|  25.65   | Mountainous                  | Charges due to mountainous reason          |
		|  110     | Other                        | Charges due to other reasons               |
	And I should see following details in the Accessorial charges section
		| Additional Mileage  | Additional Weight / Pallet| Cancelation| Customer Rejected / Returned| Excess Wait Time | Limited Access | Mountainous | Other | Total   |
		| $25                 | $18                       | $12        |  $19                        |$10               | $30.5          | $25.65      | $110  | $250.15 |
	And I set the cancelled delivery pickup reference as recent pickup reference to verify data in db
	Then The Below accessorial charges should be present in the db
		| Additional Mileage  | Additional Weight / Pallet| Cancelation| Customer Rejected / Returned| Excess Wait Time | Limited Access | Mountainous | Other | Total   |
		| $25                 | $18                       | $12        |  $19                        | $10              | $30.50          | $25.65      | $110  | $250.15 |



@ready
#CORE-5251 : To verify that admin can add accessorial charges for Admin canceled Partner portal deliveries
Scenario: To verify that admin can add accessorial charges for Admin canceled Partner portal deliveries
	When I request Partner Portal "SOLO" Trip for "MRFM" partner
		|Geofence| Bungii Time   | Customer Phone | Customer Name |
		|Kansas  | NEXT_POSSIBLE | 8877661182     | Testcustomertywd_BppleMarkGA LutherGA|
	And As a driver "Testdrivertywd_appleks_a_drvbz Kansas_bz" perform below action with respective "Solo Scheduled" partner portal trip
		| driver1 state |
		| Accepted      |
	And I view the all Scheduled Deliveries list on the admin portal
	When I wait for 2 minutes
	Then I should be able to see the respective bungii with the below status
		|  Status |
		| Scheduled |
	When I click on "Edit" link beside scheduled bungii
	And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
	And I enter cancellation fee and Comments
	And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
	And I click on "Submit" button
	Then The "Pick up has been successfully canceled." message should be displayed
	When I wait for 2 minutes
	When I view All Deliveries list on the admin portal
	And  I search the delivery using "Pickup Reference"
	Then The Delivery List page should display the delivery in "Admin Canceled" state
	And I click on the "Delivery details" link beside scheduled bungii for "Completed Deliveries"
	Then I should see "Accessorial Charges" section displayed
	When I add following accessorial charges and save it
		| Amount   | Fee Type                     | Comment                                    | Driver Cut |
		|  8       | Additional Mileage           | Charges due to Additional Mileage          | 1          |
		|  12      | Additional Weight / Pallet   | Charges due to Additional Weight / Pallet  | 2          |
		|  15      | Cancelation                  | Charges due to Cancelation                 | 3          |
		|  18      | Customer Rejected / Returned | Charges due to Customer Rejected / Returned| 4          |
		|  20      | Excess Wait Time             | Charges due to Excess wait                 | 5          |
		|  20.5    | Limited Access               | Charges due to Limited Access              | 5.5        |
		|  25.65   | Mountainous                  | Charges due to mountainous reason          | 10         |
		|  100     | Other                        | Charges due to other reasons               | 20         |
	Then I should see the following fee type displayed
		| Fee Type         			   |
		| Additional Mileage           |
		| Additional Weight / Pallet   |
		| Cancelation                  |
		| Customer Rejected / Returned |
		| Excess Wait Time 			   |
		| Limited Access               |
		| Mountainous                  |
		| Other                        |
	And I should see following details in the Accessorial charges section
		| Additional Mileage | Additional Weight / Pallet | Cancelation |Customer Rejected / Returned |Excess Wait Time | Limited Access | Mountainous | Other | Total   |
		| $8 | $12 | $15 | $18 | $20 | $20.5 | $25.65 | $100  | $219.15 |
	And I click on the Accessorial Charges links and I should see the Drivers cut displayed
		| Fee Type                     | Driver Cut |
		| Additional Mileage           | 1          |
		| Additional Weight / Pallet   | 2          |
		| Cancelation                  | 3          |
		| Customer Rejected / Returned | 4          |
		| Excess Wait Time             | 5          |
		| Limited Access               | 5.5        |
		| Mountainious                 | 10         |
		| Other                        | 20         |
	And "accessorial_fee_amount" should show total amount in the triprequest table in Database
	And "business_notes" should show comment without quotes in the trippaymentdetails table in Database
	And I wait for 2 minutes
	And I should see the following fee type displayed in the Report Database
		| Fee Type         			   |
		| Additional Mileage           |
		| Additional Weight / Pallet   |
		| Cancelation                  |
		| Customer Rejected / Returned |
		| Excess Wait Time 			   |
		| Limited Access               |
		| Mountainous                  |
		| Other                        |