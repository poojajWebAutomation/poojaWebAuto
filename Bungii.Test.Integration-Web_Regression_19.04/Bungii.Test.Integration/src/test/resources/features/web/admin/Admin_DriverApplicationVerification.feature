@web
Feature: Admin_DriverApplicationVerification

  Background:
    Given I am logged in as Admin
    And there is a pending application for driver verification

  @knownissue
  @email
      #test data created in base
    #raised bug ADP-740 for approve button not available on v2
  Scenario: Verify Driver Application Approval - NonFountain
    When I click "Verify" button against the "John MwrB" applicant
    Then I should be directed to "Driver Verification Page"
    And I check if each field has an "accept" option
    And I check if each field has an "reject" option
    And I check if the Save and cancel buttons are seen by default
    When I verify and approve all the verification fields
    And I click on the "Approve Application" Button
    And I confirm the "Driver Application Approval" action
    Then the status of the driver application should be marked as "Active"
    And I should receive "BUNGII: Time to Hit the Road!" email

  @knownissue
    #test data created in base
  @email
    #raised bug ADP-740 for rejecting without entering the reason
  Scenario: Verify Driver Application Rejection - NonFountain
    When I click "Verify" button against the "John dMIk" applicant
    Then I should be directed to "Driver Verification Page"
    When I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I do not enter the reject reason
    And I click on the "Submit" Button
    Then the validation message "Please add reject reason" is displayed
    When I enter the reject reason
    And I click on the "Submit" Button
    Then the status of the driver application should be marked as "Rejected"
    And I should receive "Your application has been rejected." email
    
  
  @regression
    #test data created in base
  Scenario: Verify Resend Button Button visibility On All Field Approval - NonFountain
    When I click "Verify" button against the "John Annie" applicant
    Then I should be directed to "Driver Verification Page"
    And I verify and approve all the verification fields
    Then the "Resend Application" button is not visible

  @regression
    #fixed with sprint 49
    #cancel button doesnt work
    #test data created in base
  Scenario: Verify Driver Application Rejection Cancellation - NonFountain
    When I click "Verify" button against the "John Johnie" applicant
    Then I should be directed to "Driver Verification Page"
    When I click on "Reject Application" link
    And I reject the "Driver Reject Application"confirm action
    And I click on the "Cancel" Button
    Then the status of the driver application should be marked as "Pending Verification"

  @regression
    #test data created in base
  Scenario: Verify Driver Application Rejection With All Fields Approved - NonFountain
    When I click "Verify" button against the "Nilesh PM" applicant
    Then I should be directed to "Driver Verification Page"
    When I verify and approve all the verification fields
    And I click on "Reject Application" link
    And I confirm the "Driver Reject Application" action
    And I enter the reject reason
    And I click on the "Submit" Button
    Then the status of the driver application should be marked as "Rejected"

  @regression
        #test data created in base
  Scenario: Verify Driver Application Field Approval Or Rejection - NonFountain
    When I click "Verify" button against the "John Jamie" applicant
    Then I should be directed to "Driver Verification Page"
    When I verify and approve the "Driver Picture" field
    Then the status of the field changes to "accepted"
    When I verify and reject the "Driver Picture" field
    Then the status of the field changes to "rejected"
    When I click and reset the status of "Driver Picture" field
    Then the status of the field resets to default
    
  @ready
    #moved in sprint 49 to ready as it needs fix
    #test data created in base
    #raised bug ADP-737 for resend button not functioning
  Scenario: Verify Driver Application Resubmission Of Rejected Application - NonFountain
    When I click "Verify" button against the "Drake Martin" applicant
    And I verify all the fields except "Date of Birth"
    And I click on the "Resend Application" Button
    And I confirm the "Driver Resend Application" action
    And I login to the driver portal as driver "Drake Martin"
    And I update the rejected "Date of Birth" field
    And I update the accepted "Social Security Number" field
    And I click on "Update" button
    And I submit the updated application
    #And I logout of driver portal  #Not logging out due to clicking
    And I am logged in as Admin
    Then there is a pending application for driver verification
    When I click "Verify" button against the "Drake Martin" applicant
    Then The accepted tick is removed for "Date Of Birth" field previously accepted by admin

  @regression
    #test data created in base
  Scenario: Verify Approve Application Button visibility On Field Rejection - NonFountain
    When I click "Verify" button against the "John owPH" applicant
    Then I should be directed to "Driver Verification Page"
    When I verify and reject the invalid verification fields
    Then the "Approve Application" button is not visible
  
  @knownissue
    #test data created in base. driver is having sneha email
    #raised bug ADP-737 for resend button not functioning
  Scenario: Verify Driver Application Resend Application - NonFountain
    When I click "Verify" button against the "James KbpK" applicant
    Then I should be directed to "Driver Verification Page"
    When I verify and reject the invalid verification fields
    And I click on the "Resend Application" Button
    And I confirm the "Driver Resend Application" action
    Then the status of the driver application should be marked as "Re-sent to Driver"
    Then I should receive "BUNGII: Action Required!" email
  #  When I login as driver "John PxLK"
   # And Correct the fields and resubmit
   # Then Admin receives "" email
