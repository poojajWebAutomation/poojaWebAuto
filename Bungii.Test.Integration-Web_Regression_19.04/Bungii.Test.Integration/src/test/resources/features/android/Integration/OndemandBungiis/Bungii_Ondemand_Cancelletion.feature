@android
@ondemand
@general
    @bungii
   #These feature will run in boston geofence
    Feature: OnDemandBungii_Cancellation
    Scenarios where customer requests a Bungii and driver accepts/rejects and cancels the Bungii.
      
      @regression
      Scenario: Verify Driver Can Cancel Ondemand Bungii In Enroute State
        Given that ondemand bungii is in progress
          | geofence | Bungii State |
          | boston2   | Enroute      |
    
        When I Switch to "customer" application on "same" devices
        And I am logged in as "Testcustomertywd_appleMarkFP LutherFP" customer
        And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
        Then for a Bungii I should see "Enroute screen"
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdrivertywd_applebs_a_gruB Stark_bsOnB" driver
        And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
        And I close "Tutorial" if exist

        Then Bungii driver should see "Enroute screen"
        When Bungii Driver "clicks More Options"
        When Bungii Driver "cancels Bungii"
        And I Switch to "customer" application on "same" devices
        Then Alert message with DRIVER CANCELLED text should be displayed
        When I click "OK" on alert message
        Then "Home" page should be opened
  
    @sanity
    @regression
    Scenario: Verify Driver Can Cancel Ondemand Bungii In Arrived State
      Given that ondemand bungii is in progress
        | geofence | Bungii State |
        | boston3   | ARRIVED      |
    
      And I am on customer Log in page
      When I am logged in as "Testcustomertywd_appleMarkFQ LutherFQ" customer
      And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I close "Tutorial" if exist
      
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_applebs_a_gruC Stark_bsOnC" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

#      Then Driver should see "Arrived screen"
      Then Bungii driver should see "Arrived screen"
      When Bungii Driver "clicks More Options"
      When Bungii Driver "cancels Bungii"
      And I Switch to "customer" application on "same" devices
      Then Alert message with DRIVER CANCELLED text should be displayed
      When I click "OK" on alert message
      Then "Home" page should be opened
  
    @ready
    Scenario: Verify Driver Can Cancel Ondemand Bungii With Promocode In Enroute State
      Given I am on customer Log in page
      When I am logged in as "valid boston" customer
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "valid boston" driver
      And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I close "Tutorial" if exist
      
      And I tap on "Go Online button" on Driver Home page
      And I Switch to "customer" application on "same" devices
      And I enter "new boston pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add "2" photos to the Bungii
      And I add loading/unloading time of "30 mins"
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      
      And I wait for "1" mins
      And I Open "driver" application on "same" devices
      And Bungii Driver "accepts On Demand Bungii" request
      Then Bungii driver should see "General Instructions"
      Then Bungii driver should see "Enroute screen"
      When Bungii Driver "clicks More Options"
      When Bungii Driver "cancels Bungii"
      And I Switch to "customer" application on "same" devices
      And I click "OK" on the alert message
      Then Alert message with DRIVER CANCELLED text should be displayed
      When I click "OK" on alert message
      #And I tap on "Menu" > "Promos" link
      When I tap on the "ACCOUNT>PROMOS" link
      Then I should see unused promo code
  
    @ready
      #stable
    Scenario: Verify Driver Can Cancel Ondemand Bungii With Promocode In Arrived State
      Given I am on customer Log in page
      When I am logged in as "valid boston" customer
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "valid boston" driver
      And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I close "Tutorial" if exist
      
      And I tap on "Go Online button" on Driver Home page
      And I Switch to "customer" application on "same" devices
      And I enter "new boston pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add "2" photos to the Bungii
      And I add loading/unloading time of "30 mins"
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
  
      And I wait for "1" mins
      And I Open "driver" application on "same" devices
      And Bungii Driver "accepts On Demand Bungii" request
      Then Bungii driver should see "General Instructions"
      And Bungii driver should see "Enroute screen"
      When Bungii Driver "slides to the next state"
      Then Bungii driver should see "Arrived screen"
      When Bungii Driver "clicks More Options"
      When Bungii Driver "cancels Bungii"
      And I click "OK" on the alert message
      Then Alert message with DRIVER CANCELLED text should be displayed
      When I click "OK" on alert message
      #And I tap on "Menu" > "Promos" link
      When I tap on the "ACCOUNT>PROMOS" link
      Then I should see unused promo code

