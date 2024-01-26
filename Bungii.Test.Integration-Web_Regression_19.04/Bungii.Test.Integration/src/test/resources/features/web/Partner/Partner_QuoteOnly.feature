@web
Feature: Quote Only Feature
  
  Background:
	Given I navigate to "Partner" portal configured for "service level" URL
	  
@test
@regression
Scenario Outline: Verify partner can get the Quote from quote-only page for partner delivery of <Type> for Service <ServiceName> for <Distance> distance range
And I navigate to "Quote-Only" page
  When I request "<Type>" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
| Pickup_Address                                                                     | Delivery_Address            |
| 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | <DeliveryAddress>           |
And I click "Service Level List" button on Partner Portal
Then I should "see all the Service Level" for "Biglots" Alias
And I change the service level to "<ServiceName>"
Then I should see the estimate cost quote
  
  Examples:
	|Type|DeliveryAddress                                                                  |ServiceName   |Distance |Address_Enter|
	|Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|White Glove   |0-10     |CopyPaste    |
	|Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|White Glove   |0-10     |CopyPaste    |
	|Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |White Glove   |10-15    |             |
	|Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |White Glove   |10-15    |             |
	|Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |White Glove   |15-30    |             |
	|Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |White Glove   |15-30    |             |
	|Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |White Glove   |30-100   |             |
	|Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |White Glove   |30-100   |             |
	|Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |White Glove   |Above 100|             |
	|Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |White Glove   |Above 100|             |
	|Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Room of Choice|0-10     |             |
	|Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Room of Choice|0-10     |             |
	|Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Room of Choice|10-15    |             |
	|Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Room of Choice|10-15    |             |
	|Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Room of Choice|15-30    |             |
	|Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Room of Choice|15-30    |             |
	|Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Room of Choice|30-100   |             |
	|Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Room of Choice|30-100   |             |
	|Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Room of Choice|Above 100|             |
	|Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Room of Choice|Above 100|             |
	|Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Threshold     |0-10     |             |
	|Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Threshold     |0-10     |             |
	|Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Threshold     |10-15    |             |
	|Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Threshold     |10-15    |             |
	|Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Threshold     |15-30    |             |
	|Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Threshold     |15-30    |             |
	|Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Threshold     |30-100   |             |
	|Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Threshold     |30-100   |             |
	|Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Threshold     |Above 100|             |
	|Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Threshold     |Above 100|             |
	|Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Curbside      |0-10     |             |
	|Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Curbside      |0-10     |             |
	|Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Curbside      |10-15    |             |
	|Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Curbside      |10-15    |             |
	|Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Curbside      |15-30    |             |
	|Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Curbside      |15-30    |             |
	|Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Curbside      |30-100   |             |
	|Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Curbside      |30-100   |             |
	|Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Curbside      |Above 100|             |
	|Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Curbside      |Above 100|             |
  
  @regression
  Scenario: Verify partner can startover from quote-only page
	And I navigate to "Quote-Only" page
	When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
	  | Pickup_Address                                                                     | Delivery_Address            |
	  | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879            |
    And I click on "Start Over" button
	Then Fields get reset to default state
  
  @regression
  Scenario: Verify Pickup Time should not be displayed on quote-only page
	And I navigate to "Quote-Only" page
	Then I should see header as "Get Quote"
	And Pickup Time field should not be displayed