@web
Feature: Admin_Promoter

  Background:
    Given I am logged in as Admin
    When I click on "Promo Codes > Free Deliveries Codes  > Partners" Menu
    Then I should be directed to "Promoters Page"

  @regression
    #Issue Raised ADP-618 as for 'Name' field attribute value is not changing though the 'Name' field is in ascending order
    #Failing due to pagination bar appears on Partners list
  Scenario: Verify Promoter Grid Sort ASC DESC
    When I click on "Name" header "Ascending" on "Promoter" grid
    Then the "Partners" list should be sorted by "Ascending" order of "Name"
    When I click on "Name" header "Descending" on "Promoter" grid
    Then the "Partners" list should be sorted by "Descending" order of "Name"
    When I click on "Created" header "Ascending" on "Promoter" grid
    Then the "Partners" list should be sorted by "Ascending" order of "Created"
    When I click on "Created" header "Descending" on "Promoter" grid
    Then the "Partners" list should be sorted by "Descending" order of "Created"
    When I click on "Code Initials" header "Ascending" on "Promoter" grid
    Then the "Partners" list should be sorted by "Ascending" order of "Code Initials"
    When I click on "Code Initials" header "Descending" on "Promoter" grid
    Then the "Partners" list should be sorted by "Descending" order of "Code Initials"

  @sanity
  @regression
  Scenario: Verify Add New Promoter
    When I click on the "New Partners" Button
    And I enter following values in fields in "Add New Partner" popup
      | Promoter Name | Code Initials | Description  | Status  |
      | PT<<Unique>>         | PTR<<Unique>>| PTRDescription | Active  |
    When I click on the "Save" Button on "Add New Partner" popup
    Then the promoter "PT<<Unique>>" gets saved successfully and it is displayed in the Promoters grid
    When I search by promoter Name "PT<<CurrentDateTime>>"
    Then the promoter "PT<<Unique>>" is displayed in the Promocodes grid

  @sanity
  @regression
    #Issue logged ADP-695
    #Issue logged CORE-6319-promo code popup not getting displayed
  Scenario: Verify Adding Promotion To Promoter And Adding Promocodes to Promotion
    When I click on the "New Partners" Button
    And I enter following values in fields in "Add New Partner" popup
      | Promoter Name | Code Initials    | Description  | Status  |
      | PT<<Unique>>         | PTR<<Unique>> | PTR Description | Active  |
    When I click on the "Save" Button on "Add New Partner" popup
    Then the promoter "PT<<Unique>>" gets saved successfully and it is displayed in the Promoters grid
    When I view Details of promoter Name "PT<<CurrentDateTime>>"
    And I click on "New Event" button on the "Events" page
    And I enter following values in fields in "Add Event" popup
      | Promotion Name | Promotion Start Date    | Expiration Date  |
      | PT<<Unique>>         | <<CurrentDateTime>>   | <<CurrentDateTime>>+1 |
    And I click on the "Save" Button on "Add Event" popup
    Then the "Do you wish to generate promo codes?" is displayed
    When I click on the "Yes" Button on "Generate Promo Code" popup
    And I enter following values in fields on "Promo Code" popup
      | Promo Code Name     | No Of Codes  |
      | DP<<Unique>> | 5        |
    When I click on the "Save" Button
    Then the "Delivery By Partner" type 5 promocodes gets saved successfully and it is displayed in the Promocodes grid
    When I search by first code generated for above promocode
    Then the promocode is displayed in the Promocodes grid

  @sanity
  @regression
    #Issue logged ADP-708
  Scenario: Verify Adding Payment To Promoter
    When I click on the "New Partners" Button
    And I enter following values in fields in "Add New Partner" popup
      | Promoter Name | Code Initials    | Description  | Status  |
      | PT<<Unique>>         | PTR<<Unique>> | PTR Description | Active  |
    When I click on the "Save" Button on "Add New Partner" popup
    Then the promoter "PT<<Unique>>" gets saved successfully and it is displayed in the Promoters grid
    When I click on "Free Deliveries Codes  > Free Delivery Credit Card" Menu
    And I select "PT<<Unique>> " from the "Select Partners" dropdown
    And I click on "Add Payment Method" button on "Free Delivery Credit Card" page
    And I enter following card details on "Free Delivery Credit Card" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |4111111111111111 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Promoter Cards" screen
    Then the card is added to the promoter "PT<<CurrentDateTime>>"
  
  @sanity
  @regression
  Scenario: Verify Adding Payment To Promoter - Fraud Card
    When I click on the "New Partners" Button
    And I enter following values in fields in "Add New Partner" popup
      | Promoter Name | Code Initials    | Description  | Status  |
      | PT<<Unique>>         | PTR<<Unique>> | PTR Description | Active  |
    When I click on the "Save" Button on "Add New Partner" popup
    Then the promoter "PT<<Unique>>" gets saved successfully and it is displayed in the Promoters grid
    When I click on "Free Deliveries Codes  > Free Delivery Credit Card" Menu
    And I select "PT<<Unique>> " from the "Select Partners" dropdown
    And I click on "Add Payment Method" button on "Free Delivery Credit Card" page
    And I enter following card details on "Free Delivery Credit Card" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |4000111111111511 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Promoter Cards" screen
    Then "payment declined error" message is displayed

  @regression
  Scenario: Verify Cancellation of Add New Promoter
    When I click on the "New Partners" Button
    And I click on the "Cancel" Button on "Add New Partner" popup
    Then the "Add New Partner" popup gets removed from UI

  @regression
   #Issue logged ADP-694
  Scenario: Verify Field Validations Of Add New Promoter
    When I click on the "New Partners" Button
    When I click on the "Save" Button on "Add New Partner" popup
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed
    When I enter "Code Initials" field with below values and click Save
    |Value|Message|
    |ABC... | Please enter a valid Code containing alphanumeric and special characters like $,&,#,@,!,%,?,+ only                 |
    Then the "corresponding" message is displayed beside the "respective" field

