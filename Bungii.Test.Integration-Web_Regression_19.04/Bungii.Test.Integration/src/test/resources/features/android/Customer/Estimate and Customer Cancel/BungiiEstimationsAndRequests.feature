    @android
    @notes
    @bungii
    #These feature will run in Goa geofence
    Feature: Verify Bungii Estimations and Requests
  
      #Customer : Testcustomertywd_appleand_E Android   9393939393
      #Driver    Testdriver_goa_e Android_test" driver
      #Geofence : Goa
  ##############################################################BLOCK SHARES USER WITH FAILED ONDEMAND FEATURE
      @ready
      @nonstable
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Duo Bungii request, when viewed from Available Trips page
        When I Switch to "customer" application on "same" devices
        Given I am on customer Log in page
        And I am logged in as "valid goa customer" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
    
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "two drivers selector" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        #And I select Bungii Time as "next possible scheduled"
        And I select Bungii Time as "2 HOUR DELAY"
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I click "Done" button on "Success" screen
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from available trip
        Then I should be able to see "Customer Note" Text
        And I terminate "driver" app on "same" devices
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8888882028      |                 |
  
      @regression
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in the Bungii Details page for a Scheduled Bungii
        When I Switch to "customer" application on "same" devices
    
        Given I am on customer Log in page
        And I am logged in as "valid goa customer" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        #And I select Bungii Time as "OLD BUNGII TIME"
        And I select Bungii Time as "2 HOUR DELAY"
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I click "Done" button on "Success" screen
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from available trip
        And I tap on "ACCEPT" on driver Trip details Page
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        And I Select Trip from driver scheduled trip
        Then I should be able to see "Customer Note" Text
        And I terminate "driver" app on "same" devices
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8888882028      |                 |

      @ready
      @nonstable
      Scenario: Verify that driver is able to correctly view all the text entered in Details field while a solo bungii is in progress
        When I Switch to "customer" application on "same" devices
        Given I am on customer Log in page
        And I am logged in as "valid goa customer" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
    
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        #And I select Bungii Time as "OLD BUNGII TIME"
        And I select Bungii Time as "next possible scheduled"
    
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I click "Done" button on "Success" screen
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from available trip
        And I tap on "ACCEPT" on driver Trip details Page
        #CORE-4581: Confirmation of acceptance of delivery in driver's app
        Then I should see "Delivery Accepted" popup displayed
        And I click on the "OK" Button on "Accept Delivery" popup
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        And I Select Trip from driver scheduled trip
        Then I start selected Bungii
        Then Bungii driver should see "General Instructions"
        When I slide update button on "EN ROUTE" Screen
        And I slide update button on "ARRIVED" Screen
        And I click on "Delivery Instructions" button
        Then I should be able to see "Details From Customer" Text
        And I terminate "driver" app on "same" devices
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8888882028      |                 |

      @ready
      @nonstable
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Solo Bungii request, when viewed from Available Trips page
        When I Switch to "customer" application on "same" devices
    
        Given I am on customer Log in page
        And I am logged in as "valid goa customer" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        #And I select Bungii Time as "next possible scheduled"
        And I select Bungii Time as "1 HOUR DELAY"
    
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I click "Done" button on "Success" screen
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from driver available trip
        Then I should be able to see "Customer Note" Text
        And I terminate "driver" app on "same" devices
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8888882028      |                 |
        

      @regression
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Duo Bungii request
        When I Switch to "customer" application on "same" devices
  
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "two drivers selector" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        #And I select Bungii Time as "BUNGII TIME"
        And I select Bungii Time as "1 HOUR DELAY"
  
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        Then I click "Done" button on "Success" screen
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from driver available trip
        And I tap on "ACCEPT" on driver Trip details Page
        #CORE-4581: Confirmation of acceptance of delivery in driver's app
        Then I should see "Delivery Accepted" popup displayed
        And I click on the "OK" Button on "Accept Delivery" popup
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        And I Select Trip from driver scheduled trip
        Then I should be able to see "Customer Note" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9889889888      |                 |
  
       