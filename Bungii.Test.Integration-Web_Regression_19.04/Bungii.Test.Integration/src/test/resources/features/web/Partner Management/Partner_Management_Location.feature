@web
@partnermanagement
Feature: Partner Management Location

  Background:
    Given  I navigate to "Partner Management"
    And I add credentials of "Valid user"
    And I click on the "Login" Button

  @ready
  Scenario:To verify Clear Filter functionality for partner listing page
    When I search for "Best Buy" partner on partner management
    Then Only "One partner location" should be displayed
    When I click on the "Clear filter" Button
    Then Only "Multiple partners locations" should be displayed
    #To verify Location name are in alphabetically order
    And All the partners are in alphabetical order

  @ready
  Scenario:To verify Partner hyper link on searching a Location
   #To verify Location name can be searched using first 4 characters of the Location name on Search textbox
    When I search for "Cort" partner on partner management
    Then I should see the text "Cort" highlighted
    And I click on the "Partner location" link
    Then The "Cort Furniture" "Partner Location" should be displayed
    And I click on the "Clear text" link
    Then Only "Multiple partners locations" should be displayed
    When I search for "Best Buy" partner on partner management
    #To verify child location are displayed on Partner Listing page only when a location has a child location
    Then The "Arrow" "Link" should be displayed
    When I click on the "Arrow" link
    Then The "Best Buy child Partners" "Links" should be displayed
    Then The "Partners" "Link" should be displayed
    #To verify navigation from child location to parent location
    When I click on the "Partners" link
    Then Only "One partner location" should be displayed
    #To verify refresh functionality for partner listing page
    And  I refresh the page
    Then Only "One partner location" should be displayed

  @ready
  Scenario:To verify Partner hyper link on searching a Location
    When I search for "Best Buy" partner on partner management
    When I click on the "Arrow" link
    When I click on the "First Child Partner" link
    Then The "Best Buy #11" "Header" should be displayed
    Then The "best buy #11" "Text" should be displayed
    Then The "Location Settings" "Header" should be displayed
    Then The "Partner Portal" "Header" should be displayed
    Then The "Trip Settings" "Header" should be displayed
    Then The "Geofence Settings" "Header" should be displayed
    Then The "Customer Interaction" "Header" should be displayed
    Then The "Messaging and Notification" "Header" should be displayed
    Then The "Payment Settings" "Header" should be displayed
    Then The "Pricing Setting" "Header" should be displayed


  @ready
  Scenario Outline:To verify Location name cannot be searched using special characters and numbers
    When I search for "<TextType>" partner on partner management
    And the "No data found for the search filter. Click here to clear" message is displayed
    Examples:
      |  TextType      |
      | 1234567876     |
      | %#Q$#!()*&     |
      | allan135       |
      | abc123$%^&     |


  @ready
  Scenario:To verify Location type for each kind of location
    When I search for "Best Buy" partner on partner management
    And I click on the "Partner location" link
    Then The "Management" "Location Type text" should be displayed
    When I click on the "Arrow" link
    When I search for "Best Buy #11" partner on partner management
    And I click on the "Partner Portal" link
    Then The "partner portal" "Location Type text" should be displayed
    When I click on the "Clear filter" Button
    When I click on the "Partners" link
    When I search for "E-API Walmart" partner on partner management
    And I click on the "Partner location" link
    When I click on the "Arrow" link
    And I click on the "Partner Portal" link
    Then The "Enterprise Partner" "Location Type text" should be displayed

  @ready
  Scenario:To verify the Geofence setting are displayed for Partner CHILD location
    When I search for "Cort Furniture" partner on partner management
    When I click on the "Arrow" link
    And I click on the "Partner Portal" link
    Then The "Location Settings" "Header" should be displayed
    # To verify details of Location setting header when user clicks on Child location by navigating through parent location
    Then The "(928) 417-4823" "Contact number" should be displayed
    Then The "BUNGIIAUTO@GMAIL.COM" "Email address" should be displayed
    Then The "1641 Cobb Pkwy SE Marietta GA 30060" "Pickup Address" should be displayed
    #To verify partner Portal Settings are displayed on the Child locations
    Then The "Live Store" "Status" should be displayed
    Then The "https://qaauto-cortfurniture7302.undefined" "Subdomain URL" should be displayed
    Then The "CORT FURNITURE" "Partner Portal Name" should be displayed
    Then The "Quote only mode" "not applicable cross" should be displayed
    Then The "Kiosk mode" "not applicable cross" should be displayed
    Then The "Quick access mode" "not applicable cross" should be displayed
    #To verify the Geofence setting are displayed for Partner CHILD locatio
    Then The "Earliest Schedule Time" "Table Name" should be displayed
    Then The "Solo" "Row Name" should be displayed
    Then The "Duo" "Row Name" should be displayed
    Then The earliest schedule time for solo should be "30 mins" and for duo "30 mins"
    #To verify the Customer Interaction- What's Needed section for child location
    Then The "What's  Needed" "Row Name" should be displayed
    #To verify the Customer Interaction -Label section for child location
    Then The "Label" "Row Name" should be displayed
    #To verify the Customer Interaction- Disclaimer section for child location
    Then The "Disclaimer" "Row Name" should be displayed
    Then The "Acknowledgment" "Row Name" should be displayed
    #To verify the trip setting for Child location
    Then The "Operating hours" "Row Name" should be displayed
    Then The "30 days" "Advance scheduled days" should be displayed
    Then The "150 miles" "Milage cap" should be displayed
    Then The "Allowed" "Multiple customer phone number" should be displayed
    Then The "Required delivery verification" "Row Name" should be displayed
    Then The "Verify before loading" "green tick" should be displayed
    Then The "Verify after load" "green tick" should be displayed
    Then The "Verify after unload" "green tick" should be displayed
    Then The "Required barcode scan verification" "green tick" should be displayed
    Then The "Barcode Scan at Pickup" "red cross" should be displayed
    Then The "Barcode Scan at Drop-off" "red cross" should be displayed
    Then The "Portal specific settings" "Text" should be displayed
    Then The "Allow user to edit default address" "Text" should be displayed
    Then The "Show delivery amount to customer" "Text" should be displayed

  #CORE-4398:Verify barcode scanning flag is shown on Partner Management
  @ready
  Scenario:Verify barcode scanning flag is shown on Partner Management
    When I search for "Floor & Decor" partner on partner management
    When I click on the "Arrow" link
    When I search for "Floor & Decor #106" partner on partner management
    And I click on the "Partner Portal" link
    Then The "Required barcode scan verification" "green tick" should be displayed
    Then The "Barcode Scan at Pickup" "green tick" should be displayed
    Then The "Barcode Scan at Drop-off" "green tick" should be displayed


  @ready
    #CORE-4656 To verify Customer signature indicator is present under trip setting in partner management portal
  Scenario:To verify the Customer signature setting visible in AP
    When I search for "Cort Furniture" partner on partner management
    And I click on the "Arrow" link
    And I click on the "Partner Portal" link
    Then The Customer Signature as "Enabled (Required)" should be displayed under Trip Setting
    When I click on the "Partners" link
    And I search for "Best Buy #11" partner on partner management
    And I click on the "Arrow" link
    And I click on the "Partner Portal" link
    Then The Customer Signature as "Enabled (Not Required)" should be displayed under Trip Setting
    When I click on the "Partners" link
    And I search for "Best Buy KC" partner on partner management
    And I click on the "Arrow" link
    And I click on the "Partner Portal" link
    Then The Customer Signature as "Disabled" should be displayed under Trip Setting