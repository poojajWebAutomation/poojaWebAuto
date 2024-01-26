@web
Feature: Admin_BusinessUsers

  Background:
    Given I am logged in as TestAdmin
    When I click on "Customers" Menu
    Then I should be directed to "Customers Page"

  @knownissue
  Scenario: Verify Customer List - Sort ASC DESC [CORE-1927]
    When I click on "Name" header for "Ascending" order in the "Customer List" table
    Then The column "Name" data gets sorted in "Ascending" order in the "Customer List" table
    When I click on "Name" header for "Descending" order in the "Customer List" table
    Then The column "Name" data gets sorted in "Descending" order in the "Customer List" table
    When I click on "Deliveries Requested" header for "Ascending" order in the "Customer List" table
    Then The column "Deliveries Requested" data gets sorted in "Ascending" order in the "Customer List" table
    When I click on "Deliveries Requested" header for "Descending" order in the "Customer List" table
    Then The column "Deliveries Requested" data gets sorted in "Descending" order in the "Customer List" table
    When I click on "Deliveries Estimated" header for "Ascending" order in the "Customer List" table
    Then The column "Deliveries Estimated" data gets sorted in "Ascending" order in the "Customer List" table
    When I click on "Deliveries Estimated" header for "Descending" order in the "Customer List" table
    Then The column "Deliveries Estimated" data gets sorted in "Descending" order in the "Customer List" table
    When I click on "Spent" header for "Ascending" order in the "Customer List" table
    Then The column "Spent" data gets sorted in "Ascending" order in the "Customer List" table
    When I click on "Spent" header for "Descending" order in the "Customer List" table
    Then The column "Spent" data gets sorted in "Descending" order in the "Customer List" table
    When I click on "Customer Join Date (CST)" header for "Ascending" order in the "Customer List" table
    Then The column "Customer Join Date (CST)" data gets sorted in "Ascending" order in the "Customer List" table
    When I click on "Customer Join Date (CST)" header for "Descending" order in the "Customer List" table
    Then The column "Customer Join Date (CST)" data gets sorted in "Descending" order in the "Customer List" table
  # To remove due to yesterday today values
   # When I click on "Last Activity (CST)" header for "Ascending" order in the "Customer List" table
    #Then The column "Last Activity (CST)" data gets sorted in "Ascending" order in the "Customer List" table
    #When I click on "Last Activity (CST)" header for "Descending" order in the "Customer List" table
    #Then The column "Last Activity (CST)" data gets sorted in "Descending" order in the "Customer List" table
