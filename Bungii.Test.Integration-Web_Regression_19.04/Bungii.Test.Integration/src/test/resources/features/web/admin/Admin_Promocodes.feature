@web
Feature: Admin_Promocodes

  Background:
    Given I am logged in as Admin
    When I click on "Promo Codes > Standard Codes" Menu
    Then I should be directed to "Standard Codes Page"

  @sanity
  @regression
  Scenario: Verify Add New Promocode of Type Promo
    When I click on the "New Code" Button
    And I enter following values in fields
      | Promo Code Type   | Promo Code Name    | Code  | Discount Value  | Discount Category | Expiration Date |
      | Promo                   | Promo<<CurrentDateTime>>|   P<<CurrentDateTime>> | 5  | Dollars  | <<NextDayDateTime>>           |
    When I click on the "Save" Button
    Then the "Promo" type promocode gets saved successfully and it is displayed in the Promocodes grid
    When I search by Name "Promo<<CurrentDateTime>>"
    Then the promocode "Promo<<CurrentDateTime>>" is displayed in the Promocodes grid

  @sanity
  @regression
  Scenario: Verify Add New Promocode of Type One Off
    When I click on the "New Code" Button
    And I enter following values in fields
      | Promo Code Type   | Promo Code Name    | Code  | Discount Value  | Discount Category | Expiration Date |
      | One Off           | OO<<CurrentDateTime>>|   O<<CurrentDateTime>> | 50  | Percent  |           |
    When I click on the "Save" Button
    Then the "One Off" type promocode gets saved successfully and it is displayed in the Promocodes grid
    When I search by Code "O<<CurrentDateTime>>"
    Then the promocode "OO<<CurrentDateTime>>" is displayed in the Promocodes grid
    #Editing the New Promocode_OneOff
    When I click on the "Edit" Button
    And I edit the Promo Code Name
    When I click on the "Save" Button
    When I search by Code "O<<CurrentDateTime>>"
    Then the edited promocode is displayed in the Promocodes grid

  @regression
  Scenario: Verify Add New Promocode of Type Delivery By Partner
    When I click on the "New Code" Button
    And I enter following values in fields
      | Promo Code Type                | Promo Code Name    | Select Promoter   | Select Promotion  | No Of Codes  |
      | Delivery By Partner           | DP<<CurrentDateTime>>|   World Market Promotion  |  Promotion | 5        |
    When I click on the "Save" Button
    Then the "Delivery By Partner" type 5 promocodes gets saved successfully and it is displayed in the Promocodes grid
    When I search by first code generated for above promocode
    Then the promocode is displayed in the Promocodes grid
    When I view the searched promocode
    Then the searched promocode data gets populated correctly

  @sanity
  @regression
  Scenario: Verify Add Edit Promocode of Type Delivery By Partner Multiple
    When I click on the "New Code" Button
    And I enter following values in fields
      | Promo Code Type                | Promo Code Name    | Select Promoter   | Select Promotion  | Code  |
      | Delivery By Partner (M)       | DM<<CurrentDateTime>>|   World Market Promotion  |  Promotion | M<<CurrentDateTime>>        |
    When I click on the "Save" Button
    Then the "Delivery By Partner (M)" type promocode gets saved successfully and it is displayed in the Promocodes grid

  @regression
  Scenario: Verify Cancellation of Add New Promocode
    When I click on the "New Code" Button
    And I click on the "Cancel" Button on "Add New Promocode" popup
    Then the "Add New Promocode" popup gets removed from UI
    #BOC search to check pagination
    #When I search by the Code "Promo"
    When I check if pages exists
   # And I check that "Previous" and "Next" button exists
    Then I verify that pagination exists
    #search for invalid data
    When I search by the Code "@#$@@"
    Then the "No data. " message is displayed
#EOC

  @regression
  Scenario: Verify Field Validations On Add New Promocode Popup Upon Blank Inputs
    When I click on the "New Code" Button
    And I select promocode type as "Delivery By Partner"
    And I click on the "Save" Button
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed
    And the "Please select Partner." message is displayed for the "Select Partner" field
    And the "Please enter a value greater than or equal to 1." message is displayed for the "No of Codes" field
    When I enter "No of Codes" field with below values and click Save
    |Value|Message|
    |0 | Please enter a value greater than or equal to 1.                 |
    |71|Please enter a value less than or equal to 70.       |
    Then the "corresponding" message is displayed beside the "respective" field
    #Validating all the promo codes
    And I click on the "Cancel" Button on "Add New Promocode" popup
    When I click on the "New Code" Button
    And I select promocode type as "Promo"
    And I click on the "Save" Button
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed
    And I click on the "Cancel" Button on "Add New Promocode" popup
    When I click on the "New Code" Button
    And I select promocode type as "One Off"
    And I click on the "Save" Button
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed
    And I click on the "Cancel" Button on "Add New Promocode" popup
    When I click on the "New Code" Button
    And I select promocode type as "Delivery By Partner (M)"
    And I click on the "Save" Button
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed
    And the "Please select Partner." message is displayed for the "Select Partner" field
    And the "Please select Partner." message is displayed for the "Select Partner" field
    And I click on the "Cancel" Button on "Add New Promocode" popup
    When I click on the "New Code" Button
    And I select promocode type as "Promo"
    And I change the "Expiration Date" to past date
    And I click on the "Save" Button
    Then the "Please enter a valid date." message is displayed
    When I click on the "New Code" Button
    And I enter following values in fields
      | Promo Code Type   | Promo Code Name         | Code      | Discount Value  | Discount Category | Expiration Date    |
      | Promo             | Promo<<CurrentDateTime>>|   P<<CurrentDateTime>> | 5  | Dollars           | <<NextDayDateTime>>|
    And I change the "Expiration Date" to future date
    And I click on the "Save" Button
    Then the date gets saved
    #Promo code and name with only special characters
    #Not implemented so far this validation so keeping on hold
  Scenario: Verify Field Validations On Add New Promocode Popup For invalid Inputs
    When I click on the "New Code" Button
    And I enter following values in fields
      | Promo Code Type | Promo Code Name | Code      | Discount Value  | Discount Category | Expiration Date    |
      | Promo           | $*#*#*#*$       |  INP<<CurrentDateTime>> | 5 | Dollars           | <<NextDayDateTime>>|
    Then the "Please enter a valid Promo Code Name containing alphanumeric and special characters only" message is displayed
    And I click on the "Save" Button
    And I click on the "Cancel" Button on "Add New Promocode" popup
    When I click on the "New Code" Button
    And I enter the following values in fields
      | Promo Code Type   | Promo Code Name       | Code      | Discount Value  | Discount Category | Expiration Date    |
      | Promo             | VP<<CurrentDateTime>> |           | 5               | Dollars           | <<NextDayDateTime>>|
    And I click on the "Save" Button
    Then the "Please enter a valid Code containing alphanumeric and special characters like $,&,#,@,!,%,?,+ only" message is displayed

  #Scenario: Admin_PromocodeGrid_Sort  - Not yet implemented

  @sanity
  @regression
  Scenario: Verify Promocode Grid Filters
    When I click on "Filter" icon
    Then the "Code Type" and "Creation Date" is set to "All" by default
    When I select "Code Type" as "Promo"
    And I click on the "Apply" Button
    Then the promocode grid shows the results by type "Promo"
    When I click on "Filter" icon
    And I select "Code Type" as "Referral"
    And I click on the "Apply" Button
    Then the promocode grid shows the results by type "Referral"
    When I click on "Filter" icon
    And I select "Code Type" as "One Off"
    And I click on the "Apply" Button
    Then the promocode grid shows the results by type "One Off"
    When I click on "Filter" icon
    And I select "Code Type" as "FB Shared"
    And I click on the "Apply" Button
    Then the promocode grid shows the results by type "One Off" having Code value starting with "FBSHARE"
    When I click on "Filter" icon
    And I select "Code Type" as "Delivery By Partner"
    And I click on the "Apply" Button
    Then the promocode grid shows the results by type "Delivery By Partner"
    When I click on "Filter" icon
    And I select "Code Type" as "Delivery By Partner (M)"
    And I click on the "Apply" Button
    Then the promocode grid shows the results by type "Delivery By Partner (M)"
    When I click on "Filter" icon
    And I select "Active" as "Hide Expired"
    And I click on the "Apply" Button
    Then the promocode grid shows the only the "Active" promocodes
    When I click on "Filter" icon
    And I uncheck "Hide Expired"
    And I click on the "Apply" Button
    Then the promocode grid shows the both "Active & Expired" promocodes
    
  @regression
  Scenario: Verify search by XSS script on Standard Codes grid
    When I search by string "<script>alert('hello')</script>"
    Then the "No data. " message is displayed

#  CORE-4776 - Verify Promo code name field does not accepts all blank spaces
  @ready
  Scenario: Verify Promo code name field does not accepts all blank spaces
    When I click on the "New Code" Button
    And I enter "Standard Code Name" as "Blank space"
    And I enter "Code" as "CORE-4776"
    Then the "Should contain alphanumeric and -@#$&_: special characters." message is displayed
