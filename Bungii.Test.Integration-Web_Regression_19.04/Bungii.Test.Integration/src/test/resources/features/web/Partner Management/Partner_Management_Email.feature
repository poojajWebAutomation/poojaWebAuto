@web
@partnermanagement
Feature: Adding Email In Partner Management

  Background:
    Given  I navigate to "Partner Management"
    And I add credentials of "Valid user"
    And I click on the "Login" Button

  #CORE-3817:To verify adding an email id in the partner management portal for a store / child node
 @ready
  Scenario:To verify adding an email id in the partner management portal for a store / child node
    When I search for "EC Barton / Home Outlet" partner on partner management
    And I click on the "Arrow" link
    And I click on the "Partner Portal" link
    Then The "partner portal" "Location Type text" should be displayed
    #CORE-3817:To verify same email id can be added/ edited for the partner on Partner management portal
    And I click on the "Edit Email" Button
    Then The "Edit Email Address" "Header" should be displayed
    When I add "Secondary email address" as the new email
    And I click on "Save Email" button
    Then The "Secondary email address" "Email" should be displayed
    And I click on the "Edit Email" Button
    Then The "Edit Email Address" "Header" should be displayed
    When I add "Primary email address" as the new email
    And I click on "Save Email" button
    Then The "Primary email address" "Email" should be displayed

#CORE-3817:To verify adding of email id field in the partner management portal for a management node
  @ready
  Scenario: To verify add and edit of email id field in the partner management portal for a management node
    When I search for "EC Barton / Home Outlet" partner on partner management
    And I click on the "Partner location" link
    Then The "Management" "Location Type text" should be displayed
    Then I search for "EC Barton / Home Outlet" partner on partner management
    And I click on the "Add Email Address" Button
    When I add "Primary email address" as the new email
    And I click on "Save Email" button
    Then All email addresses should be displayed for the mentioned partner
    And I click on the "Edit Email Address" Button
    When I add "Primary email address for edit" as the new email
    And I click on "Save Email" button
    Then Edited email addresses should be displayed for the mentioned partner


