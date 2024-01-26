@web
Feature: Admin_PaymentMethods

  Background:
    Given I am logged in as TestAdmin
    When I click on "Partner Portal  > Partner Card" Menu
    Then I should be directed to "Partner Cards Page"


  @regression
  Scenario: Verify Add Payment Method in Partner Cards
    And I select "Costco" from the "Partner Cards" dropdown
    When I click on "Add Payment Method" button on "Partner Cards" page
    And I enter following card details on "Partner Cards" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |4111111111111111 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Partner Cards" screen
    Then the card is added to the user "partner Cards" page


  @regression
  Scenario: Verify Add Payment Method in Partner Cards with Cancel
    And I select "MRFM" from the "Partner Cards" dropdown
    When I click on "Add Payment Method" button on "Partner Cards" page
    Then I click on "Cancel" button on "Partner Cards" screen
    Then The "Add Partner Cards" details screen is removed from UI


  @regression
  Scenario: Verify Add Payment Methods in Bungii Cards
    When I click on "Partner Portal  > Bungii Card" Menu
    When I click on "Add Payment Method" button on "Bungii Cards" page
    And I enter following card details on "Bungii Cards" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |4111111111111111 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Bungii Cards" screen
    Then the card is added to the user "Bungii Cards" page


  @regression
  Scenario: Verify Add Payment Method in Bungii Cards with Cancel
    When I click on "Partner Portal  > Bungii Card" Menu
    When I click on "Add Payment Method" button on "Bungii Cards" page
    And I select "MRFM" from the "Bungii Cards" dropdown
    Then I click on "Cancel" button on "Bungii Cards" screen
    Then The "Add Bungii Cards" details screen is removed from UI


  @regression
  Scenario: Verify Field Validations On Add New Partner Card page Upon Blank Inputs
    When I click on "Add Payment Method" button on "Partner Cards" page
    And I click on "Save" button on "Partner Cards" screen
    Then the "Please check your information and try again." message is displayed
    And the "Please fill out a card number." message is displayed for the "Card Number" field
    And the "Please fill out an expiration date." message is displayed for the "Expiration Date" field
    And the "Please fill out a CVV." message is displayed for the "CVV" field
    And the "Please fill out a postal code." message is displayed for the "Postal Code" field
    Then I click on "Cancel" button on "Partner Cards" screen
    When I click on "Add Payment Method" button on "Partner Cards" page
    And I enter following card details on "Partner Cards" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |4111111111111111 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Partner Cards" screen
    Then The "Partner Card" gets saved successfully and it is displayed in the grid


  @regression
  Scenario: Verify Field Validations On Add New Bungii Card page Upon Blank Inputs
    When I click on "Partner Portal  > Bungii Card" Menu
    When I click on "Add Payment Method" button on "Bungii Cards" page
    And I click on "Save" button on "Bungii Cards" screen
    Then the "Please check your information and try again." message is displayed
    And the "Please fill out a card number." message is displayed for the "Card Number" field
    And the "Please fill out an expiration date." message is displayed for the "Expiration Date" field
    And the "Please fill out a CVV." message is displayed for the "CVV" field
    And the "Please fill out a postal code." message is displayed for the "Postal Code" field
    Then I click on "Cancel" button on "Bungii Cards" screen
    When I click on "Add Payment Method" button on "Bungii Cards" page
    And I enter following card details on "Bungii Cards" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |4111111111111111 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Bungii Cards" screen
    Then The "Bungii Cards" gets saved successfully and it is displayed in the grid

  @regression
  Scenario: Verify Field Validations Of Add New Partner Card
    When I click on "Add Payment Method" button on "Partner Cards" page
    And I click on "Save" button on "Partner Cards" screen
    Then the "Please check your information and try again." message is displayed
    When I enter following card details on "Partner Cards" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |1231 2312 3242 3534 | 13/00      | 1236  |     12345|
    And I click on "Save" button on "Partner Card" screen
    And the "This card number is not valid." message is displayed for the "Card Number" field
    And the "This expiration date is not valid." message is displayed for the "Expiration Date" field
    And the "This security code is not valid." message is displayed for the "CVV" field
    Then I click on "Cancel" button on "Partner Cards" screen
    When I click on "Add Payment Method" button on "Partner Cards" page
    And I enter following card details on "Partner Cards" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |4111111111111111 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Partner Cards" screen
    Then The "Partner Cards" gets saved successfully and it is displayed in the grid

  @regression
  Scenario: Verify Field Validations Of Add New Bungii Card
    When I click on "Partner Portal  > Bungii Card" Menu
    When I click on "Add Payment Method" button on "Bungii Cards" page
    And I click on "Save" button on "Bungii Cards" screen
    Then the "Please check your information and try again." message is displayed
    When I enter following card details on "Bungii Cards" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |1231 2312 3242 3534 | 13/00      | 1236  |      12345|
    And I click on "Save" button on "Bungii Card" screen
    And the "This card number is not valid." message is displayed for the "Card Number" field
    And the "This expiration date is not valid." message is displayed for the "Expiration Date" field
    And the "This security code is not valid." message is displayed for the "CVV" field
    Then I click on "Cancel" button on "Bungii Cards" screen
    When I click on "Add Payment Method" button on "Bungii Cards" page
    And I enter following card details on "Partner Cards" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |4111111111111111 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Bungii Cards" screen
    Then The "Bungii Cards" gets saved successfully and it is displayed in the grid
  
  @regression
    #stable
  Scenario: Verify Fraud Card detection - Bungii Cards
    When I click on "Partner Portal  > Bungii Card" Menu
    When I click on "Add Payment Method" button on "Bungii Cards" page
    When I enter following card details on "Bungii Cards" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |4000111111111511 | 12/39      | 1236  |     12345|
    And I click on "Save" button on "Bungii Cards" screen
    Then "payment declined error" message is displayed
  
  @regression
  Scenario: Verify Fraud Card detection - Partner Cards
    When I click on "Partner Portal  > Partner Card" Menu
    When I click on "Add Payment Method" button on "Partner Cards" page
    When I enter following card details on "Partner Cards" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |4000111111111511 | 12/39      | 1236  |     12345|
    And I click on "Save" button on "Partner Cards" screen
    Then "payment declined error" message is displayed

  #Core-2397 : Verify duplicate partner entries are shown in dropdown on Partner Card page
  @regression
  Scenario: Verify duplicate partner entries are shown in dropdown on Partner Card page
    When I click on "Partner Portal  > Partner Card" Menu
    And I click on "Partners" dropdown
    Then I check if duplicate partner entries are shown in dropdown
