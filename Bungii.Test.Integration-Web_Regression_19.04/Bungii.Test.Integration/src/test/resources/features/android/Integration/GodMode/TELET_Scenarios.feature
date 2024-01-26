    @android
    #These feature will run in Goa geofence and STABLE and will run with regression and not bungii in android
    Feature: Driver Assignments with TELET Overlap
      #Customer D and C - Testcustomertywd_appleand_D Android 9999990074 and Testcustomertywd_appleand_C Android 9999992222
      # Driver D and C  - Testdriver_goa_d Android_test and Testdriver_goa_c Android_test
      
 @regression
   #Stable
    Scenario: Verify that TELET time of solo scheduled when trip is not started and same driver is assigned to another scheduled trip at same time
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 8877661003     | Testcustomertywd_appleMarkD LutherD | Cci12345          |
      And I get TELET time of of the current trip
      When I request another "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 8877661192     | Testcustomertywd_appleMarkGK LutherGK | Cci12345          |
   
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      #And I click on "Edit Trip1" button
      And I open the trip for "Testcustomertywd_appleMarkD LutherD" the customer
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed
      When I click on "Close" button
   
      #And I click on "Edit Trip2" button
   And I open the trip for "Testcustomertywd_appleMarkGK LutherGK" the customer
   And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_d Android_test" driver
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I should able to see "two" scheduled trip
   
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 8877661003      | 8877661192      |
  
  
	  @regression
   #Stable
    Scenario: Verify that TELET time of solo scheduled when trip is not started and same driver is assigned to another scheduled trip at overlapping time
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "GoaN DriverN" driver

      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And I get TELET time of of the current trip
      When I request another "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
        | TELET OVERLAP  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
   And I open the trip for "Testcustomertywd_appleand_D Android" the customer
      And I Select "Edit Trip Details" option
      And I assign driver "GoaN DriverN" for the trip
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed
      When I click on "Close" button
   
   And I open the trip for "Testcustomertywd_appleand_C Android" the customer
      And I Select "Edit Trip Details" option
      And I assign driver "GoaN DriverN" for the trip
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I click on "No Thanks" button
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I should able to see "two" scheduled trip
   
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      | 9999992222      |

   @regression
   #Stable

    Scenario: Verify that TELET time of duo scheduled when trip is not started and controlled driver is assigned to another scheduled trip at same time
     And I Switch to "driver" application on "same" devices
     And I am on the LOG IN page on driver app
     And I am logged in as "GoaO DriverO" driver

      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                         | Customer Password |
        | NEXT_POSSIBLE | 8877661038     | Testcustomertywd_appleMarkAM LutherAM | Cci12345          |
      And I get TELET time of of the current trip
      When I request another "duo" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                         | Customer Password |
        | TELET SAME TIME  | 8877661039     | Testcustomertywd_appleMarkAN LutherAN | Cci12345          |

      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleMarkAM LutherAM" the customer
      And I Select "Edit Trip Details" option
      And I assign driver "GoaO DriverO" for the trip
      And I assign driver "Testdriver_goa_c Android_test" for the trip
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed
      When I click on "Close" button
  
      And I open the trip for "Testcustomertywd_appleMarkAN LutherAN" the customer
      And I Select "Edit Trip Details" option
      And I assign driver "GoaO DriverO" for the trip
      And I assign driver "Testdriver_goa_c Android_test" for the trip
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I click on "No Thanks" button
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I should able to see "two" scheduled trip
   
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 8877661038      | 8877661039      |
  
	@regression
   #Stable
    Scenario: Verify that TELET time of duo scheduled when trip is not started and non controlled driver is assigned to another scheduled trip at same time
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "GoaP DriverP" driver

      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 8877661193     | Testcustomertywd_appleMarkGL LutherGL | Cci12345          |
      And I get TELET time of of the current trip
      When I request another "duo" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 8877661194     | Testcustomertywd_appleMarkGM LutherGM | Cci12345          |
   
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
  
   And I open the trip for "Testcustomertywd_appleMarkGL LutherGL" the customer
      And I Select "Edit Trip Details" option
      And I assign driver "GoaP DriverP" for the trip
      And I assign driver "Testdriver_goa_c Android_test" for the trip
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed

      When I click on "Close" button
   And I open the trip for "Testcustomertywd_appleMarkGM LutherGM" the customer
      And I Select "Edit Trip Details" option
      And I assign driver "GoaP DriverP" for the trip
      And I assign driver "Testdriver_goa_c Android_test" for the trip
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I click on "No Thanks" button
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I should able to see "two" scheduled trip
   
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 8877661193      | 8877661194      |

   @regression
   #Stable
    Scenario: Verify that TELET time of duo scheduled when trip is not started and both driver is assigned to another scheduled trip at same time

     And I Switch to "driver" application on "same" devices
     And I am on the LOG IN page on driver app
     And I am logged in as "GoaQ DriverQ" driver

      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 8877661195     | Testcustomertywd_appleMarkGN LutherGN | Cci12345          |
      And I get TELET time of of the current trip
      When I request another "duo" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 8877661196     | Testcustomertywd_appleMarkGO LutherGO  | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
  And I open the trip for "Testcustomertywd_appleMarkGN LutherGN" the customer
      And I Select "Edit Trip Details" option
      And I assign driver "GoaQ DriverQ" for the trip
      And I assign driver "Testdriver_goa_c Android_test" for the trip
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed

      When I click on "Close" button
  And I open the trip for "Testcustomertywd_appleMarkGO LutherGO " the customer
      And I Select "Edit Trip Details" option
      And I assign driver "GoaQ DriverQ" for the trip
      And I assign driver "Testdriver_goa_c Android_test" for the trip
      And I click on "VERIFY" button
      And The "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And The "Bungii Saved!" message is displayed

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I click on "No Thanks" button
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I should able to see "two" scheduled trip
  
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 8877661195      | 8877661196      |
  
	  @regression
      #Stable
      Scenario: Verify that TELET time of duo scheduled when trip is not started and controlled driver is assigned to another scheduled trip TELET overlap
        And I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "GoaR DriverR" driver

        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE |  8877661197    | Testcustomertywd_appleMarkGP LutherGP | Cci12345          |
        And I get TELET time of of the current trip
        When I request another "duo" Bungii as a customer in "goa" geofence
          | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
          | TELET OVERLAP  |    8877661198  | Testcustomertywd_appleMarkGQ LutherGQ | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
   And I open the trip for "Testcustomertywd_appleMarkGP LutherGP" the customer
        And I Select "Edit Trip Details" option
        And I assign driver "GoaR DriverR" for the trip
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed

        When I click on "Close" button
   And I open the trip for "Testcustomertywd_appleMarkGQ LutherGQ" the customer
        And I Select "Edit Trip Details" option
        And I assign driver "GoaR DriverR" for the trip
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed

        When I switch to "ORIGINAL" instance
        And I Switch to "driver" application on "same" devices
        And I click on "No Thanks" button
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        Then I should able to see "two" scheduled trip
   
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8877661197      | 8877661198      |
  
	  @regression
      #Stable
      Scenario: Verify that TELET time of duo scheduled when trip is not started and non controlled driver is assigned to another scheduled trip at same time

        And I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "GoaS DriverS" driver

        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 8877661199     | Testcustomertywd_appleMarkGR LutherGR| Cci12345          |
        And I get TELET time of of the current trip
        When I request another "duo" Bungii as a customer in "goa" geofence
          | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
          | TELET OVERLAP  | 8877661200     | Testcustomertywd_appleMarkGS LutherGS| Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
  And I open the trip for "Testcustomertywd_appleMarkGR LutherGR" the customer
        And I Select "Edit Trip Details" option
        And I assign driver "GoaS DriverS" for the trip
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed

        When I click on "Close" button
  And I open the trip for "Testcustomertywd_appleMarkGS LutherGS" the customer
        And I Select "Edit Trip Details" option
        And I assign driver "GoaS DriverS" for the trip
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed

        When I switch to "ORIGINAL" instance
        And I Switch to "driver" application on "same" devices
        And I click on "No Thanks" button
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        Then I should able to see "two" scheduled trip
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8877661199      | 8877661200      |
  
  
	  @ready
   #Stable
      Scenario: Verify that TELET time of duo scheduled when trip is not started and both driver is assigned to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_d Android_test" driver
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        When I request another "duo" Bungii as a customer in "goa" geofence
          | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
          | TELET OVERLAP  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
  And I open the trip for "Testcustomertywd_appleand_D Android" the customer
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed

        When I click on "Close" button
  And I open the trip for "Testcustomertywd_appleand_C Android" the customer
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed

        When I switch to "ORIGINAL" instance
        And I Switch to "driver" application on "same" devices
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        Then I should able to see "two" scheduled trip
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999990074      | 9999992222      |
  
  
	  @ready
   #Stable
      Scenario: Verify that TELET is impacted of solo scheduled when trip is not started and driver is assigned to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_d Android_test" driver
        When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        When I request another "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
          | TELET SAME TIME  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
   And I open the trip for "Testcustomertywd_appleand_D Android" the customer
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed

        When I click on "Close" button
   And I open the trip for "Testcustomertywd_appleand_C Android" the customer
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed

        When I switch to "ORIGINAL" instance
        And I Switch to "driver" application on "same" devices
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        Then I should able to see "two" scheduled trip
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999990074      | 9999992222      |
  
	  @regression
   #Stable
      Scenario: Verify that TELET is impacted of duo scheduled when trip is started and driver is assigned to another scheduled trip at same time
        
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 8877661202     | Testcustomertywd_appleMarkGU LutherGU | Cci12345          |
        And I get TELET time of of the current trip
        And As a driver "GoaT DriverT" and "GoaU DriverU" perform below action with respective "Duo Scheduled" trip
          | driver1 state | driver2 state |
          | Enroute       | Enroute       |
        
        When I request another "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
          | TELET SAME TIME  | 8877661203     | Testcustomertywd_appleMarkGV LutherGV | Cci12345          |
      
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "GoaT DriverT" driver
        
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleMarkGV LutherGV" the customer
        And I Select "Edit Trip Details" option
        And I assign driver "GoaT DriverT" for the trip
        And I click on "VERIFY" button
        And The "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And The "Bungii Saved!" message is displayed

        When I switch to "ORIGINAL" instance
        And I Switch to "driver" application on "same" devices
        And I slide update button on "EN ROUTE" Screen
        And I slide update button on "ARRIVED" Screen
        When Bungii driver uploads "1" image
        And I slide update button on "ARRIVED" Screen
        Then I accept Alert message for "Reminder: both driver at pickup"
        And I slide update button on "LOADING ITEM" Screen
        When Bungii driver uploads "1" image
        And I slide update button on "LOADING ITEM" Screen
        And I slide update button on "DRIVING TO DROP-OFF" Screen
        And I slide update button on "UNLOADING ITEMS" Screen
        When Bungii driver uploads "1" image
        And I slide update button on "UNLOADING ITEMS" Screen
        Then I accept Alert message for "Reminder: both driver at drop off"
        When Bungii Driver "rates driver"
        And Bungii Driver "skips to rate customer"
        Then Bungii Driver "tab On to Next"
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        Then I should able to see "one" scheduled trip
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8877661202      | 8877661203      |
  
  
	  @regression
   #Stable
    Scenario: Verify that if driver have two TELET overlapping bungiis and admin researches any one of the Bungii then concerned driver does not recieve push notification
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 8877661204     | Testcustomertywd_appleMarkGW LutherGW | Cci12345          |
      And As a driver "GoaV DriverV" perform below action with respective "Solo Scheduled" trip
        | driver1 state      |
        | Accepted           |
        
      And I get TELET time of of the current trip
      When I request another "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | TELET OVERLAP | 8877661205     | Testcustomertywd_appleMarkGX LutherGX  | Cci12345          |
      And As a driver "GoaW DriverW" perform below action with other "Solo Scheduled" trip
        | driver1 state      |
        | Accepted           |
      
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "GoaW DriverW" driver
        
      Then I wait for "1" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
       And I open the trip for "Testcustomertywd_appleMarkGW LutherGW" the customer
        Then I remove "control" driver and researches Bungii
        
      When I switch to "ORIGINAL" instance
      Then I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      | 9999992222   |
  
  
    
        
    
  

