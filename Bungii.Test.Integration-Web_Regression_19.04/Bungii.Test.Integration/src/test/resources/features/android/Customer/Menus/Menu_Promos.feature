@android
Feature: Menu_SaveMoney
  @regression
  Scenario: Verify First Time Promo Code For New Users Notification For Existing Customer
    Given I am logged in as "existing" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
   # When I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    And I add "first time" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar stating first time code is for new users" on Save Money page
  #  And I should see the "first time" PromoCode selected by default
    #And I tap on "Menu" > "Logout" link
    And I tap on the "ACCOUNT>LOGOUT" link

  @sanity
  @regression
  Scenario: Verify Addition Of New Promocode With Valid Promocode
    Given I am logged in as "existing" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    #When I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    And I add "valid" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "promocode added" on Save Money page
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Verify Addition Of New Promocode With Invalid Promocode
    Given I am logged in as "existing" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    #When I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    And I add "invalid" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for invalid code" on Save Money page
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Verify Addition Of New Promocode With Expired Promocode
    Given I am logged in as "existing" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    #When I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    And I add "expired" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for expired code" on Save Money page
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Verify Addition Of New Promocode With Already Added Promocode
    Given I am logged in as "existing" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    #When I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    And I add "valid" PromoCode
    And I tap "Add" on Save Money page
    And I add "valid" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for already added code" on Save Money page
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link

  @regression
  Scenario:  Verify Referral Are For New Users Notification for Newly Registered Customer
    Given I am logged in as "newly registered" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    #When I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    And I add "referral" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar stating referrals are only for new users" on Save Money page
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Verify Used One Off Code Notification When Promocode Is Already Utilized
    Given I am logged in as "existing" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    #When I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    And I add "used one off" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message for used one off code" on Save Money page
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Verify First Time Promo User Who Has Referral Code Behavior
    Given I am logged in as "having referral code" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    #And I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    And I add "referral" PromoCode
    And I tap "Add" on Save Money page
    Then I should see "snackbar message stating referral already exists" on Save Money page
    When I tap on the "i" icon
    Then I should see "Promo code for first Bungii selected by default" message on the Promos page
    When I add "valid" PromoCode
    And I tap "Add" on Save Money page
    And I select "different promo code when first time promo code is present" on the Promos page
    Then I should see "First time promo code not used" message on the Promos page
    And I tap on the "ACCOUNT>LOGOUT" link
    #And I tap on "Menu" > "Logout" link

  @regression
  Scenario: Verify Referral Invite When Facebook App Is Already Installed
    Given I have "facebook" app "installed"
    When I am logged in as "existing" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    #And I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    And I tap "Get More Money" on Save Money page
    Then I should see "all elements" on Invite Page
    When I tap "Share" on Invite page
    And I tap "Share on Facebook" on Invite page
    And I share on "Facebook with app installed"
#    Then I should see post "on Facebook app"

  #@regression
  @regression
  Scenario: Verify When Customer With No Twitter App Shares Promocode Via Twitter Then It Opens in Browser
    Given I am logged in as "existing" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    #When I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    And I tap "Get More Money" on Save Money page
    Then I should see "Referral Code" on Invite Page
    When I tap "Share" on Invite page
    And I tap "Share on Twitter" on Invite page
    Then I should see post "on Twitter in browser"
    And I Switch to "customer" application on "same" devices

  @regression
  Scenario: Verify Promocode Is Refunded Upon Cancellation Of Bungii With Promocode Applied To It
    Given I am on customer Log in page
    When I am logged in as "no promocode" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Promo Code" on Bungii estimate
    And I add "valid" PromoCode
    And I tap "Add" on Promos page
    And I select the added promo code
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    When I tap on "Cancel during search" on Bungii estimate
    Then for a Bungii I should see "Bungii Home page with locations"
    #When I tap on "Menu" > "Promos" link
    When I tap on the "ACCOUNT>PROMOS" link
    Then I should see the unused promo code

  @regression
  Scenario: Verify Promocode From The Trip Gets Automatically Applied To The Researched Trip
    Given I am on customer Log in page
    When I am logged in as "no promocode" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I tap on "Promo Code" on Bungii estimate
    And I add "valid" PromoCode
    And I tap "Add" on Promos page
    And I select the added promo code
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    Then for a Bungii I should see "Bungii search screen"
    When I tap on "Cancel during search" on Bungii estimate
    Then for a Bungii I should see "Bungii Home page with locations"
    When I tap on "Get Estimate button" on Bungii estimate
    Then I should see the previously added promo code present for current Bungii request

  @regression
  Scenario: Verify Already Added Expired Promocodes Are Not Available To Customer On Estimate Screen
    Given I am on customer Log in page
    When I am logged in as "New" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "atlanta pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then I should see the "expired promo code" no more displayed on the estimates page
    When I add loading/unloading time of "30 mins"
    And I tap on "Promo Code" on Bungii estimate
    Then I should not see the expired promo code on the Promos page