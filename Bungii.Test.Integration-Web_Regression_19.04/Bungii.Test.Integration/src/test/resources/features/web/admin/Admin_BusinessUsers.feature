@web
Feature: Admin_BusinessUsers

  Background:
    Given I am logged in as TestAdmin
#    When I click on "Business Users  > Business Users" Menu
    When I click on "Bulk Delivery Upload  > Partners" Menu
    Then I should be directed to "Partners Page"

  @sanity
  @regression
    @demo
  Scenario: Verify Add Edit New Bulk Delivery Upload
    When I click on the "New Partner" Button
    And I enter following values in "New Partner" fields
      | Name                                  | Phone                   | Email  |
      | Testcustomertywdapple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    When I click on the "Save" Button on "Partner" popup
    Then the partner gets saved successfully and it is displayed in the "Partners" grid
    When I search by Name "Testcustomertywdapple<<UniqueNo>>" in "Business Users" page
    Then the user "TestcustomertywdappleBiz<<UniqueNo>>" is displayed in the Partners grid
    When I edit the "Phone Number" and "Email"
    And I click on the "Save" Button on "Partner" popup
    Then the partner gets updated successfully and it is displayed in the Partners grid
    #BOC
    #update existing business user
    When I search by Name "Testcustomertywdapple" in "Partners" page
    And I Update the "Phone Number" and "Email"
    And I click on the "Save" Button on "Partner" popup
    Then the partner gets updated successfully and it is displayed in the Partners grid

  @regression
    @failed
  Scenario: Verify Adding Duplicate Partner Phone Number
    #Unique phone number
    When I click on the "New Partner" Button
    And I enter following values in "New Partner" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywdapple<<UniqueNo>>	  | 9999839229         | test@creativecapsule.com       |
    And I enter above same Phone number in Phone Number fields
    And I click on the "Save" Button on "Partner" popup
    Then the "Phone number already exists." message is displayed
    When I click on the "Cancel" Button on "Partner" popup
    #To check that a new business user cannot be added having same phone number as an existing customer and vice versa.
    And I click on the "New Partner" Button
    And I enter the following values in "New Partner" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywdapple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    And I change the status to "Inactive"
    Then I click on the "Save" Button on "Partner" popup
    And the partner does not get saved successfully
    #EOC

  @sanity
  @regression
  Scenario: Verify Adding Payment To Bulk Delivery Upload
    When I click on the "New Partner" Button
    And I enter following values in "New Partner" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywdapple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    When I click on the "Save" Button on "Partner" popup
    Then the partner gets updated successfully and it is displayed in the Partners grid
    When I click on "Bulk Delivery Upload  > Partner Payment" Menu
    And I select "Testcustomertywdapple<<UniqueNo>>" from the "Select Partner" dropdown
    And I click on "Add Payment Method" button on "Partner Payment" page
    And I enter following card details
      |Card Number | Expiration Date | CVV | Postal Code|
      |4242424242424242 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Partner Payment" screen
    Then the card is added to the user "Testcustomertywdapple<<UniqueNo>>"
    When I click on "Bulk Delivery Upload  > Upload Deliveries" Menu
    Then the partner is displayed in Upload Deliveries since payment is set
    When I select user "Testcustomertywdapple<<UniqueNo>>"
    And I upload image to be associated with the delivery
    And I click on "Upload" button on "Upload Deliveries" page
    Then the pickup from the csv are listed down
    When I click on "Confirm" button on "Upload Deliveries" page
    Then the "Deliveries have been requested successfully." message is displayed
    
  @regression
  Scenario: Verify Adding Payment To Bulk Delivery Upload - Fraud Card
    When I click on the "New Partner" Button
    And I enter following values in "New Partner" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywdapple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    When I click on the "Save" Button on "Partner" popup
    Then the partner gets updated successfully and it is displayed in the Partners grid
    When I click on "Bulk Delivery Upload  > Partner Payment" Menu
    And I select "Testcustomertywdapple<<UniqueNo>>" from the "Select Partner" dropdown
    And I click on "Add Payment Method" button on "Partner Payment" page
    And I enter following card details
      |Card Number | Expiration Date | CVV | Postal Code|
      |4000111111111511 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Partner Payment" screen
    Then "payment declined error" message is displayed
    
    
  @sanity
  @regression
  Scenario: Verify Bulk Delivery upload Is Not Available In Upload deliveries Until Payment Is Set
    When I click on the "New Partner" Button
    And I enter following values in "New Partner" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywdapple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    And I click on the "Save" Button on "Partner" popup
    Then the partner gets updated successfully and it is displayed in the Partners grid
    When I click on "Bulk Delivery Upload  > Upload Deliveries" Menu
    Then the partner is not displayed in Upload deliveries since payment is not set

  @regression
  Scenario: Verify Partners - Cancellation
    When I click on the "New Partner" Button
    And I click on the "Cancel" Button on "Partner" popup
    Then the "Partners" popup gets removed from UI
    #BOC search to check pagination
    When I search by the Code "Testcustomertywdapple"
    And I check if pages exists
    And I check that "Previous" and "Next" button exists
    Then I verify that pagination exists
    #search for invalid data
    When I search by the Code "@#$@@"
    Then the "No Data." message is displayed
    #EOC

  @regression
  Scenario: Verify Add New Partner - Field validations
    When I click on the "New Partner" Button
    And I click on the "Save" Button on "Partner" popup
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed
    When I enter invalid phone number and email field
    Then the "Oops! The phone number is invalid." message is displayed for the "Phone Number" field
    And the "Oops! The email address is invalid." message is displayed for the "Email" field
	
  @regression
    #Failting because of ADP-500
	#Unable to download or read from the downloads folder of VM
  Scenario: Verify Add New Business User And Add Payment Method - Field validations In Uploaded CSV For Bulk Trips
    When I click on the "New Partner" Button
    And I enter following values in "New Partner" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywdapple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    And I click on the "Save" Button on "Partner" popup
    Then the partner gets updated successfully and it is displayed in the Partners grid
    When I click on "Bulk Delivery Upload  > Partner Payment" Menu
    And I select "Testcustomertywdapple<<UniqueNo>>" from the "Select Partner" dropdown
    And I click on "Add Payment Method" button on "Partner Payment" page
    And I enter following card details
      |Card Number | Expiration Date | CVV | Postal Code|
      |4242424242424242 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Partner Payment" screen
    Then the card is added to the user "Testcustomertywdapple<<UniqueNo>>"
    When I click on "Bulk Delivery Upload  > Upload Deliveries" Menu
    Then the partner is displayed in Upload Deliveries since payment is set
    When I select user "Testcustomertywdapple<<UniqueNo>>"
    And I select the file with invalid data for "Pickup address"
    And I click on "Upload" button on "Upload Deliveries" page
    And I click on the error link and download the file with error
    Then the error "Max pickup Dropoff Distance exceeded" is displayed in the csv file
    When I click on "Cancel" button on "Upload Deliveries" page
    And  I select user "Testcustomertywdapple<<UniqueNo>>"
    And I select the file with invalid data for "Pickup Date"
    And I click on "Upload" button on "Upload Deliveries" page
    And I click on the error link and download the file with error
    Then the error "Please enter a valid date time" is displayed in the csv file
    When I click on "Cancel" button on "Upload Deliveries" page
    And I select user "Testcustomertywdapple<<UniqueNo>>"
    And I select the file with invalid data for "Loading/Unloading time"
    And I click on "Upload" button on "Upload Deliveries" page
    And I click on the error link and download the file with error
    Then the error "Loading/Unloading time should be a multiple of 15 minutes ranging from 15 to 90" is displayed in the csv file
    When I click on "Cancel" button on "Upload Deliveries" page
    And I select user "Testcustomertywdapple<<UniqueNo>>"
    And I select the file with invalid data for "No of Drivers"
    And I click on "Upload" button on "Upload Deliveries" page
    And I click on the error link and download the file with error
    Then the error "Invalid no. of drivers" is displayed in the csv file
    When I click on "Cancel" button on "Upload Deliveries" page
    And I select user "Testcustomertywdapple<<UniqueNo>>"
    And I select the file with invalid data for "Blank CSV"
    And I click on "Upload" button on "Upload Deliveries" page
    Then The error "Please check the CSV for errors." is displayed

#    Core-5313 : Verify input validations for partner name
  @ready
  Scenario Outline: Verify input validations for partner name for <Scenario>
    When I click on the "New Partner" Button
    And I enter following values in "New Partner" fields
      | Name                      | Phone                   | Email  |
      | <Partner Name>            	  | <<UniquePhone>>         | test@creativecapsule.com       |
    Then The error "<Expected Message>" is displayed

    Examples:
      | Scenario                | Partner Name             | Expected Message |
      | Blank                   | BLANK                    | Oops! The name is invalid. |
      | Underscore              | Test_Partner             | Validation Message         |
      | Invalid Name            | <>.,/";:()*&^%$#@!       | Validation Message         |
