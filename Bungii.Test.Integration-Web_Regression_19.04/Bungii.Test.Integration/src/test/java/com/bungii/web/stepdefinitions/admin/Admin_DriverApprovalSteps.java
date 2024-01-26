package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_DrivePage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.driver.Driver_PickUpInfoPage;
import com.bungii.web.pages.partnerManagement.PartnerManagement_Email;
import com.bungii.web.pages.partnerManagement.PartnerManagement_LocationPage;
import com.bungii.web.pages.partnerManagement.PartnerManagement_LoginPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_DriverApprovalSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(Admin_DriverApprovalSteps.class);
    Admin_LoginPage adminLoginPage = new Admin_LoginPage();
    Admin_MenuLinksPage adminMenuLinksPage = new Admin_MenuLinksPage();
    Admin_DashboardPage adminDashboardPage = new Admin_DashboardPage();
    Admin_DriverVerificationPage admin_DriverVerificationPage = new Admin_DriverVerificationPage();
    Admin_GetAllBungiiDriversPage admin_GetAllBungiiDriversPage = new Admin_GetAllBungiiDriversPage();
    Admin_PromoCodesPage admin_PromoCodesPage = new Admin_PromoCodesPage();
    Admin_ReferralSourcePage admin_ReferralSourcePage = new Admin_ReferralSourcePage();
    Admin_BusinessUsersPage admin_BusinessUsersPage = new Admin_BusinessUsersPage();
    Admin_PromoterPage admin_PromoterPage = new Admin_PromoterPage();
    Admin_GeofencePage admin_GeofencePage = new Admin_GeofencePage();
    Admin_CustomerPage admin_customerPage = new Admin_CustomerPage();
    Admin_DriversPage admin_DriverPage = new Admin_DriversPage();
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    Driver_DashboardPage driver_DashboardPage = new Driver_DashboardPage();
    Driver_PickUpInfoPage Page_Driver_PickupInfo = new Driver_PickUpInfoPage();
    Admin_PartnerPortalPage admin_partnerPortalPage = new Admin_PartnerPortalPage();
    Admin_PartnersPage admin_partnersPage = new Admin_PartnersPage();
    Driver_DrivePage driver_drivePage = new Driver_DrivePage();
    GeneralUtility utility = new GeneralUtility();
    ActionManager action = new ActionManager();
    PartnerManagement_Email Page_PartnerManagement_Email = new PartnerManagement_Email();
    PartnerManagement_LoginPage Page_PartnerManagement_Login = new PartnerManagement_LoginPage();
    PartnerManagement_LocationPage Page_PartnerManagement_Location = new PartnerManagement_LocationPage();
    Admin_GeofenceAtrributesPage admin_geofenceAtrributesPage =  new Admin_GeofenceAtrributesPage();
    Driver_DashboardPage Page_Driver_Dashboard = new Driver_DashboardPage();
    Admin_LoginPage Page_AdminLogin = new Admin_LoginPage();

    @Given("^I am logged in as Admin$")
    public void i_am_logged_in_as_admin() throws Throwable {
        utility.AdminLogin();
    }

    @Given("I am logged in to Query Panel")
    public void iAmLoggedInToQueryPanel() throws Throwable {
        try {
            utility.QueryPanelLogin();
            } catch (Exception e) {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I am logged in as TestAdmin$")
    public void i_am_logged_in_as_Testadmin() throws Throwable {
        utility.TestAdminLogin();
    }

    @And("^there is a pending application for driver verification$")
    public void there_is_a_pending_driver_verification() throws Throwable {
        Thread.sleep(3000);
        testStepAssert.isElementDisplayed(adminMenuLinksPage.Menu_Dashboard(true), "I should be naviagate to Admin Dashboard", "I was navigated to admin Dashboard", "Admin Dashboard is not visible");
        //WebAssertionManager.ElementDisplayed(adminDashboardPage.RecentDriverRegistrations);
        testStepAssert.isElementDisplayed(adminDashboardPage.PendingVerification().get(0), "There should be Pending application", "There is Pending application", "There is not Pending application");
    }

    @When("^I click \"([^\"]*)\" button against the applicant name$")
    public void i_click_something_button_against_the_applicant_name(String button) throws Throwable {

        // cucumberContextManager.setScenarioContext("LASTNAME", "KSqc");
        try {
            //Search code
            action.click(adminDashboardPage.Link_ViewAllDriverRegistrations());
            String Lastname = (String) cucumberContextManager.getScenarioContext("LASTNAME");
            action.clearSendKeys(admin_GetAllBungiiDriversPage.TextBox_Search(), Lastname);
            action.click(admin_GetAllBungiiDriversPage.Button_Search());
            Thread.sleep(4000);
            switch (button) {
                case "Verify":
                    action.click(admin_GetAllBungiiDriversPage.GridRow_PendingVerificationLink(Lastname));
                    break;
            }
            log("I click on " + button + " button against the applicant name",
                    "I have clicked on " + button + " button against the applicant name", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @When("^I click \"([^\"]*)\" button against the \"([^\"]*)\" applicant$")
    public void i_click_something_button_against_the_something_applicant(String strArg1, String applicantName) throws Throwable {
        try {
            action.click(adminDashboardPage.Link_ViewAllDriverRegistrations());
            String[] name = applicantName.split(" ");
            //action.clearSendKeys(admin_GetAllBungiiDriversPage.TextBox_Search(),name[1]);
            action.clearSendKeys(admin_GetAllBungiiDriversPage.TextBox_Search(), applicantName);
            cucumberContextManager.setScenarioContext("FIRSTNAME", name[0]);
            cucumberContextManager.setScenarioContext("LASTNAME", name[1]);
            action.click(admin_GetAllBungiiDriversPage.Button_Search());
            Thread.sleep(4000);
            switch (strArg1) {
                case "Verify":
                    action.click(admin_GetAllBungiiDriversPage.GridRow_PendingVerificationLink(applicantName));
                    break;
            }
            log("I should be able to click " + strArg1 + " against " + applicantName, "I click " + strArg1 + " against " + applicantName, false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click \"([^\"]*)\" button for the \"([^\"]*)\" driver$")
    public void i_click_something_button_for_the_something_driver(String strArg1, String applicantName) throws Throwable {
        try {
            switch (strArg1) {
                case "Profile":
                    action.click(admin_GetAllBungiiDriversPage.Driver_Profile(applicantName));
                    break;
                case "Edit":
                    String Old_Phone_Number = action.getText(admin_GetAllBungiiDriversPage.Driver_Phone());
                    cucumberContextManager.setScenarioContext("Old_Phone", Old_Phone_Number);
                    action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Edit());
                    break;
                case "Save":
                    action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Save());
                    break;
                case "Cancel":
                    action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Cancel());
                    break;

            }
            log("I should able to click " + strArg1 + " against " + applicantName, "I have clicked " + strArg1 + " against " + applicantName, false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I change the \"([^\"]*)\" phone number$")
    public void i_enter_confirm_comment_for_edited_phone_and_something_it(String strArg1) throws Throwable {

        action.clearSendKeys(admin_GetAllBungiiDriversPage.Driver_PhoneEntry(), PropertyUtility.getDataProperties("driver.mobile.change"));
        //action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Save());
        log("I should able to change " + strArg1 + " phone number.", "I have changed " + strArg1 + " phone number.", true);
    }

    @And("^I enter confirm comment for edited phone and \"([^\"]*)\" it$")
    public void i_confirm_the_edited_phone(String State) throws Throwable {
        try {
            //action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_updated_comment());
            switch (State) {
                case "Save":
                    action.clearSendKeys(admin_GetAllBungiiDriversPage.Driver_Mobile_Updated_Comment(), "Driver phone updated");
                    action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Updated_Comment_Save());
                    break;
                case "Cancel":
                    action.waitUntilIsElementExistsAndDisplayed(admin_GetAllBungiiDriversPage.Driver_Mobile_Updated_Comment(), (long) 5000);
                    action.clearSendKeys(admin_GetAllBungiiDriversPage.Driver_Mobile_Updated_Comment(), "Driver phone updated");
                    action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Updated_Comment_Cancel());
                    break;
            }
            log("I should able to enter confirm comment for edited phone and " + State + " it", "I have entered confirm comment for the edited phone and " + State + " it", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I see updated driver phone number$")
    public void i_see_updated_phone_number() throws Throwable {
        try {
            Thread.sleep(2000);
            String Edited_Phone_Number = action.getAttributeValue(admin_GetAllBungiiDriversPage.Driver_Phone());

            testStepVerify.isEquals(Edited_Phone_Number, PropertyUtility.getDataProperties("driver.mobile.change"));
            log("Driver phone number should get update.", "Driver phone number is updated.", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I see unchanged driver phone number$")
    public void i_see_unchanged_phone_number() throws Throwable {
        try {
            String Display_Phone_Number = action.getText(admin_GetAllBungiiDriversPage.Driver_Phone());

            testStepVerify.isEquals(Display_Phone_Number, (String) cucumberContextManager.getScenarioContext("Old_Phone"));
            log("Driver phone number should remain un change.", "Driver phone number is unchanged.", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I cancel the edited phone number$")
    public void i_cancel_the_edited_phone_number() throws Throwable {
        try {
            action.click(admin_GetAllBungiiDriversPage.Driver_Mobile_Cancel());

            log("I should able to cancel the edited phone number", "I have cancelled the edited phone number.", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should be directed to \"([^\"]*)\"$")
    public void i_should_be_directed_to_something(String screen) throws Throwable {
        try {
            switch (screen) {
                case "Driver Verification Page":
                    testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Title_DriverVerificationPage(true), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                    break;
                case "Promo Code Page":
                    testStepAssert.isElementDisplayed(admin_PromoCodesPage.Title_PromocodesPage(true), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                    break;
                case "Standard Codes Page":
                    testStepAssert.isElementDisplayed(admin_PromoCodesPage.Title_StandardcodesPage(true), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                    break;
                case "Referral Source Page":
                    testStepAssert.isElementDisplayed(admin_ReferralSourcePage.Title_ReferralSourcePage(true), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                    break;
//            case "Business Users Page":
                case "Partners Page":
                    testStepAssert.isElementDisplayed(admin_BusinessUsersPage.Header_BusinessUsers(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                    break;
                case "Promoters Page":
                    testStepAssert.isElementDisplayed(admin_PromoterPage.Title_PromoterPage(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                    break;
                case "Geofences Page":
                    testStepAssert.isElementDisplayed(admin_GeofencePage.Header_Geofences(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                    break;
                case "Attributes Page":
                    testStepAssert.isElementDisplayed(admin_GeofencePage.Header_Attributes(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                    break;
                case "Customers Page":
                    testStepAssert.isElementDisplayed(admin_customerPage.Label_CustomerList(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                    break;
                case "Drivers Page":
                    testStepAssert.isElementDisplayed(admin_DriverPage.Label_DriversPageHeader(), "I should be navigated to " + screen, "I am navigated to " + screen, "I am not navigates to " + screen);
                    break;
                case "Partner Portal Page":
                    testStepAssert.isElementDisplayed(admin_partnerPortalPage.Label_PartnerListHeader(), "I should be navigated to " + screen, "I am navigated to " + screen, "I am not navigates to " + screen);
                    break;
                case "Unlock Partners Page":
                    testStepAssert.isElementDisplayed(admin_partnersPage.Label_Unlock_Partners(), "I should be navigated to " + screen, "I am navigated to " + screen, "I am not navigates to " + screen);
                    break;
                case "Geofence Attributes Page":
                    testStepAssert.isElementDisplayed(admin_geofenceAtrributesPage.Label_Geofence_Attributes(), "I should be navigated to " + screen, "I am navigated to " + screen, "I am not navigates to " + screen);
                    break;
                case "Updated Terms & Conditions":
                    action.switchToTab(1);
                    String currentUrlTermsAndConditions= action.getCurrentURL();
                    String expctedUrlTermsAndConditions=PropertyUtility.getDataProperties("driver.terms.conditions.link");
                    testStepVerify.isEquals(currentUrlTermsAndConditions, expctedUrlTermsAndConditions, "Updated Terms & Conditons should be displayed", "Updated Terms & Conditons is displayed", "Updated Terms & Conditons is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Heading_DriverAgreement()), "Updated Terms & Conditons should be displayed","Updated Terms & Conditons is displayed", "Subpoint Updated Terms & Conditons is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_Definations()), "Subpoint Definations should be displayed","Subpoint Definations is displayed", "Subpoint Definations is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_UseOfBungiiServices()), "Subpoint Use of Bungii Services should be displayed","Subpoint Use of Bungii Services is displayed", "Subpoint Use of Bungii Services is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_LocationBasedServices()), "Subpoint Location Based Services should be displayed","Subpoint Location Based Services is displayed", "Subpoint Location Based Services is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_Ratings()), "Subpoint Ratings should be displayed","Subpoint Ratings is displayed", "Subpoint Ratings is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_Mobiledevices()), "Subpoint Mobile Devices should be displayed","Subpoint Mobile Devices is displayed", "Subpoint Mobile Devices is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_YouAndYourVehicle()), "Subpoint You And Your Vehicle should be displayed","Subpoint You And Your Vehicle is displayed", "Subpoint You And Your Vehicle is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_FinancialTerms()), "Subpoint Financial Terms should be displayed","Subpoint Financial Terms is displayed", "Subpoint Financial Terms is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_ProprietaryRightsLicense()), "Subpoint Proprietary Rights License should be displayed","Subpoint Proprietary Rights License is displayed", "Subpoint Proprietary Rights License is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_Confidentiality()), "Subpoint Confidentiality should be displayed","Subpoint Confidentiality is displayed", "Subpoint Confidentiality is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_Privacy()), "Subpoint Privacy should be displayed","Subpoint Privacy is displayed", "Subpoint Privacy is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_Insurance()), "Subpoint Insurance should be displayed","Subpoint Insurance is displayed", "Subpoint Insurance is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_RepresentationsAndWarrantiesDisclaimers()), "Subpoint Representations And Warranties Disclaimers should be displayed","Subpoint Representations And Warranties Disclaimers is displayed", "Representations And Warranties Disclaimers is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_Indemnification()), "Subpoint Indemnification should be displayed","Subpoint Indemnification is displayed", "Subpoint Indemnification is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_LimitsOfLiability()),"Subpoint Limits Of Liability should be displayed", "Subpoint Limits Of Liability is displayed", "Subpoint Limits Of Liability is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_TermAndTermination()), "Subpoint Term And Termination should be displayed","Subpoint Term And Termination is displayed", "Subpoint Term And Termination is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_RelationshipOfTheParties()), "Subpoint Relationship Of The Parties should be displayed","Subpoint Relationship Of The Parties is displayed", "Subpoint Relationship Of The Parties is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_DisputeResolutionArbitration()),"Subpoint Dispute Resolution Arbitration should be displayed", "Subpoint Dispute Resolution Arbitration is displayed", "Subpoint Dispute Resolution Arbitration is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.SubPoint_MiscellaneousTerms()), "Subpoint Miscellaneous should be displayed","Subpoint Miscellaneous Terms is displayed", "Subpoint Miscellaneous Terms is not displayed");
                    break;
                case "Updated Privacy Policy":
                    action.switchToTab(1);
                    String currentUrlPrivacyPolicy= action.getCurrentURL();
                    String expctedUrlPrivacyPolicy=PropertyUtility.getDataProperties("driver.privacy.policy.link");
                    testStepVerify.isEquals(currentUrlPrivacyPolicy, expctedUrlPrivacyPolicy, "Updated Privacy Policy should be displayed", "Updated Privacy Policy is displayed", "Updated Privacy Policy is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Heading_PrivacyPolicy()), "Heading Privacy Policy should be displayed","Heading Privacy Policy is displayed", "Heading Privacy Policy is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Subpoint_CollectionOfInformation()), "Subpoint Collection Of Information should be displayed","Subpoint Collection Of Information is displayed", "Subpoint Collection Of Information is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Subpoint_UseOfInformation()), "Subpoint Use Of Information should be displayed","Subpoint Use Of Information is displayed", "Subpoint Use Of Information is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Subpoint_SharingOfInformation()), "Subpoint Sharing Of Information should be displayed","Subpoint Sharing Of Information is displayed", "Subpoint Sharing Of Information is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Subpoint_AdvertisingAndAnalyticsServices()), "Subpoint Advertising And Analytics Services should be displayed","Subpoint Advertising And Analytics Services is displayed", "Subpoint Advertising And Analytics Services is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Subpoint_Security()), "Subpoint Security should be displayed","Subpoint Security is displayed", "Subpoint Security is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Subpoint_AgeLimit()), "Subpoint Age Limit should be displayed","Subpoint Age Limit is displayed", "Subpoint Age Limit is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Subpoint_YourChoices()), "Subpoint Your Choices should be displayed","Subpoint Your Choices is displayed", "Subpoint Your Choices is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Subpoint_CaliforniaResidents()), "Subpoint California Residents should be displayed","Subpoint California Residents is displayed", "Subpoint California Residents is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Subpoint_ChangesToThePrivacyPolicy()), "Subpoint Changes To The Privacy Policy should be displayed","Subpoint Changes To The Privacy Policy is displayed", "Subpoint Changes To The Privacy Policy is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(Page_Driver_Dashboard.Subpoint_ContactingUs()), "Subpoint Contacting Us should be displayed","Subpoint Contacting Us Terms is displayed", "Subpoint Contacting Us Terms is not displayed");
                    break;
                case "Rejected API Deliveries Page":
                    testStepAssert.isElementDisplayed(admin_customerPage.Header_RejectedAPIDeliveries(), "I should be navigate to " + screen, "I am navigate to " + screen, "I am not navigate to " + screen);
                    break;

            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I should see \"([^\"]*)\" submenu$")
    public void i_should_see_something_submenu(String submenu) throws Throwable {
        try{
            testStepAssert.isElementDisplayed(admin_partnerPortalPage.Menu_UnlockPartners(),"I should see "+submenu+" submenu", "I see "+submenu+" submenu", "I do not see "+submenu+" submenu");


        } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I verify and approve all the verification fields$")
    public void i_verify_and_approve_all_the_verification_fields() throws Throwable {
        try {
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Driver Picture",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("First Name",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Last Name",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Street address",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("City",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("State",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Zip code",true));
            // action.click(admin_DriverVerificationPage.Verify_Approve_DriverSSN());
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Birthday",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup images",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup make",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup model",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup year",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup license number",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("License image",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("License number",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("License expiration",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Insurance image",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Insurance Expiration",true));

            if(action.isElementPresent(admin_DriverVerificationPage.Verify_DriverDetails("Routing Number",true, true))) {
                action.click(admin_DriverVerificationPage.Verify_DriverDetails("Routing Number",true));
            }
            if(action.isElementPresent(admin_DriverVerificationPage.Verify_DriverDetails("Account Number",true,true))) {
                action.click(admin_DriverVerificationPage.Verify_DriverDetails("Account Number",true));
            }
            log("I verify and approve all the verification fields",
                    "I have verified and approved all the verification fields", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I click on the \"([^\"]*)\" Button$")
    public void iClickOnTheButton(String arg0) throws Throwable {
        try {
            String Name = null, xpath = null;
            switch (arg0) {
                case "Approve Application":
                    action.click(admin_DriverVerificationPage.Button_DriverApproveApplication());
                    Thread.sleep(5000);
                    break;
                case "Submit":
                    action.click(admin_DriverVerificationPage.Button_Submit());
                    Thread.sleep(5000);
                    break;
                case "Resend Application":
                    action.click(admin_DriverVerificationPage.Button_DriverResentButton());
                    break;
                case "Cancel":
                     if (action.isElementPresent(admin_GeofencePage.Text_GeoHistory(true))) {
                         action.click(admin_GeofencePage.Button_GeofenceCancel());
                    }
                    else {
                        action.click(admin_DriverVerificationPage.Button_Cancel());
                    }
                    break;
                case "New Code":
                    action.click(admin_PromoCodesPage.Button_NewCode());
                    break;
                case "Apply":
                    action.click(admin_PromoCodesPage.Button_Apply());
                    Thread.sleep(2000);
                    break;
                case "Reset":
                    action.click(admin_PromoCodesPage.Button_Reset());
                    Thread.sleep(2000);
                    break;
                case "Save":
                    action.click(admin_PromoCodesPage.Button_Save());
                    Thread.sleep(2000);
                    break;
//            case "New Business User":
                case "New Partner":
                    action.click(admin_BusinessUsersPage.Button_CreateBusinessUser());
                    break;
                case "New Partners":
                    action.click(admin_PromoterPage.Button_NewPromoter());
                    break;
                case "New Portal Parter":
                    action.click(admin_partnerPortalPage.Button_NewPartner());
                    break;
                case "Edit":
                    Name = (String) cucumberContextManager.getScenarioContext("PROMOCODE_NAME");
                    xpath = String.format("//tr[1]/td[text()='%s']/following-sibling::td//button[contains(text(),'Edit')]", Name);
                    cucumberContextManager.setScenarioContext("XPATH", xpath);
                    SetupManager.getDriver().findElement(By.xpath(xpath)).click();
                    break;
//BOC
                case "Add Payment Method":
                    action.click(admin_BusinessUsersPage.Button_RequestPayment());
                    break;

//EOC

                case "Scale":
                    Thread.sleep(5000);
                    action.click(admin_GeofencePage.Button_Scale());
                    break;
                case "Edit Email":
                    action.click(Page_PartnerManagement_Email.Button_EditEmail());
                    break;
                case "Login":
                    action.click(Page_PartnerManagement_Login.Button_Login());
                    Thread.sleep(5000);
                    break;
                case "Clear filter":
                    action.click(Page_PartnerManagement_Location.Button_ClearFilter());
                    break;
                case "Logout":
                    action.click(Page_PartnerManagement_Login.Button_Logout());
                    break;
                case "Add Email Address":
                    action.click(Page_PartnerManagement_Email.Button_AddEmailAddress());
                    break;
                case "Edit Email Address":
                    action.click(Page_PartnerManagement_Email.Button_EditEmailAddress(1));
                    break;
                case "Forget Password":
                    action.click(adminLoginPage.Button_ForgotPassword());
                    break;
                case "Send Verification Code":
                    action.click(adminLoginPage.Button_SendVerificationCode());
                    break;
                case "Reset Password":
                    action.click(adminLoginPage.Button_ResetPassword());
                    break;
            }
            log("I click on the " + arg0 + " button",
                    "I have clicked on the " + arg0 + " button");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("^I confirm the \"([^\"]*)\" action$")
    public void i_confirm_the_something_action(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Driver Application Approval":
                    action.click(admin_DriverVerificationPage.Button_DriverConfirmApproval());
                    break;

                case "Driver Resend Application":
                    action.click(admin_DriverVerificationPage.Button_DriverConfirmResend());
                    break;

                case "Driver Reject Application":
                    action.click(admin_DriverVerificationPage.Button_DriverConfirmReject_Yes());
                    break;
                case "Confirm":
                    action.click(admin_DriverPage.Button_ConfirmDriverStatusChange());
                    break;
            }
            log("I can confirm " + strArg1 + " action",
                    "I have confirmed " + strArg1 + " action");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^the \"([^\"]*)\" button is not visible$")
    public void i_check_if_something_button_is_visible(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Approve Application":
                    testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Button_DriverApproveApplication(), "Approve Application button should be displayed", "Approve Application button is displayed", "Approve Application button is not displayed");
                    break;
                case "Resend Application":
                    testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Button_DriverResentButton(), "Resend Application button should be displayed", "Resend Application button is displayed", "Resend Application button is not displayed");
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^the status of the driver application should be marked as \"([^\"]*)\"$")
    public void theStatusOfTheDriverApplicationShouldBeMarkedAs(String arg0) throws Throwable {

        try {
            //Search code
            String Lastname = (String) cucumberContextManager.getScenarioContext("LASTNAME");
            action.clearSendKeys(admin_GetAllBungiiDriversPage.TextBox_Search(), Lastname);
            action.click(admin_GetAllBungiiDriversPage.Button_Search());
            Thread.sleep(3000);
            switch (arg0) {
                case "Active":
                    testStepAssert.isElementDisplayed(admin_GetAllBungiiDriversPage.GridRow_BungiiActiveDriver(Lastname), "Active Status should be displayed", "Active Status is displayed", "Active Status is not displayed");
                    break;
                case "Rejected":
                    testStepAssert.isElementDisplayed(admin_GetAllBungiiDriversPage.GridRow_BungiiRejectedDriver(Lastname), "Rejected Status should be displayed", "Rejected Status is displayed", "Rejected Status is not displayed");
                    break;
                case "Re-sent to Driver":
                    testStepAssert.isElementDisplayed(admin_GetAllBungiiDriversPage.GridRow_BungiiResentToDriver(Lastname), "Re-sent to Driver Status should be displayed", "Re-sent to Driver Status is displayed", "Re-sent to Driver Status is not displayed");
                    break;
                case "Pending Verification":
                    testStepAssert.isElementDisplayed(admin_GetAllBungiiDriversPage.GridRow_BungiiPendingVerification(Lastname), "Pending Verification Status should be displayed", "Pending Verification Status is displayed", "Pending Verification Status is not displayed");
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^the validation message \"([^\"]*)\" is displayed$")
    public void theValidationMessageIsDisplayed(String arg0) throws Throwable {
        testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Validation_Message_PleaseAddRejectionReason(), "I check if a validation message is displayed", "Validation message is displayed", "Validation message is not displayed");

    }

    @When("^I enter the reject reason$")
    public void iEnterTheRejectReason() throws Throwable {
        try {
            action.clearSendKeys(admin_DriverVerificationPage.Textinput_ReasonforRejectDriverApplication(), "Invalid values found. Please review and resend the application");
            log("I enter the reject reason",
                    "I have I entered the reject reason");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^the status of the field changes to \"([^\"]*)\"$")
    public void theStatusOfTheFieldChangesTo(String arg0) throws Throwable {
        try {
            switch (arg0) {
                case "Accepted":
                    testStepAssert.isElementDisplayed(admin_DriverVerificationPage.Textinput_ReasonforReject_DriverDetails("Drive Picture","AcceptedRejected"), "I check status of the field ", "Status is accepted", "Field is not accepted");
                    break;
                case "Rejected":
                    testStepAssert.isElementValueEquals(admin_DriverVerificationPage.Textinput_ReasonforReject_DriverDetails("Drive Picture","AcceptedRejected"), "", "I check status of the field ", "Status is rejected", "Field is not rejected");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^the status of the field resets to default$")
    public void theStatusOfTheFieldResetsToDefault() throws Throwable {
        Thread.sleep(3000);
        testStepAssert.isNotElementDisplayed(admin_DriverVerificationPage.Textinput_ReasonforReject_DriverDetails("Drive Picture","AcceptedRejected",true), "I check status field ", "Element is not displayed", "Element is displayed");

    }

    @And("^I verify all the fields except \"([^\"]*)\"$")
    public void i_verify_all_the_fields_except_something(String strArg1) throws Throwable {
        try {
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Driver Picture",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("First Name",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Last Name",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Street address",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("City",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("State",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Zip code",true));
            // action.click(admin_DriverVerificationPage.Verify_Approve_DriverSSN());
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Birthday",false));
            action.sendKeys(admin_DriverVerificationPage.Textinput_ReasonforReject_DriverDetails("Birthday","AcceptedRejected"), "Invalid DOB");
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup images",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup make",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup model",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup year",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Pickup license number",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("License image",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("License number",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("License expiration",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Insurance image",true));
            action.click(admin_DriverVerificationPage.Verify_DriverDetails("Insurance Expiration",true));

            if(action.isElementPresent(admin_DriverVerificationPage.Verify_DriverDetails("Routing Number",true, true))) {
                action.click(admin_DriverVerificationPage.Verify_DriverDetails("Routing Number",true));
            }
            if(action.isElementPresent(admin_DriverVerificationPage.Verify_DriverDetails("Account Number",true,true))) {
                action.click(admin_DriverVerificationPage.Verify_DriverDetails("Account Number",true));
            }
            log("I can verify all the fields except DOB",
                    "I have verified all the fields except DOB");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error in approving/rejecting driver application fields",
                    true);
        }
    }

    @When("^I login as driver \"([^\"]*)\"$")
    public void i_login_as_driver_something(String driverName) throws Throwable {
        try {
            utility.NavigateToDriverLogin();
            action.click(Page_Driver_Login.Tab_LogIn());
            switch (driverName) {
                case "John PxLK":
                    action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), PropertyUtility.getDataProperties("web.valid.driver21.phone"));
                    break;
            }
            action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("web.valid.common.driver.password"));
            action.click(Page_Driver_Login.Button_DriverLogin());
            log("I login as driver",
                    "I have logged in as driver");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^Admin receives \"([^\"]*)\" email$")
    public void admin_receives_something_email(String strArg1) {
        //  throw new PendingException();
    }

    @And("^Correct the fields and resubmit$")
    public void correct_the_fields_and_resubmit() {
        try {
            action.click(driver_DashboardPage.Link_DriverDetails());
            action.clearSendKeys(driver_DashboardPage.TextBox_DOB(), "12/12/1991");
            action.click(driver_DashboardPage.Button_Update());

            action.click(driver_DashboardPage.Link_PickupInfo());
            action.click(driver_DashboardPage.Link_RemoveFile3());
            action.click(driver_DashboardPage.Link_RemoveFile2());
            action.click(driver_DashboardPage.Link_RemoveFile1());

            utility.addImageInDropZone(Page_Driver_PickupInfo.DropZoneHiddenFileTag_TruckImage(), getTruckImages());
            action.invisibilityOfElementLocated(Page_Driver_PickupInfo.Wrapper_Spinner());
            int size = Page_Driver_PickupInfo.Div_UploadedImages().size();
            int count = 0;
            while (size != 3) {
                Thread.sleep(2000);
                if (count >= 20)
                    break;

                size = Page_Driver_PickupInfo.Div_UploadedImages().size();
                count++;
            }


            action.click(driver_DashboardPage.Button_Update());
            action.click(driver_DashboardPage.Button_Submit());
            action.click(driver_DashboardPage.Button_Yes());
            log("I correct the fields and resubmit",
                    "I have correct the fields and resubmited");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Given("^I am on the \"([^\"]*)\" Portal$")
    public void i_am_on_the_something_portal(String portal) throws Throwable {
        try {
            switch (portal) {
                case "Admin":
                    utility.NavigateToAdminPortal();
                    break;

                case "Driver":
                    utility.NavigateToDriverLogin();
                    break;
                case "bungii.com":
                    utility.NavigateToBungiiPortal();
                    break;
            }

            log("I navigate to " + portal + " portal",
                    "I navigate to " + portal + " portal");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^I should see updated text \"([^\"]*)\" in \"([^\"]*)\" potential earnings on \"([^\"]*)\" portal$")
    public void i_should_see_updated_text_something_in_something_potential_earnings_on_portal(String strArg1, String strArg2, String portal) throws Throwable {
        try {
            switch (portal.toLowerCase()) {
                case "driver":
                case "admin":
                    testStepAssert.isEquals(action.getText(adminLoginPage.Label_ExtraEarnings()), "Earn Extra Cash. Who couldn't use more money? Earn up to $45/hour driving with Bungii.", "Updated extra earning text should be displayed", "Updated extra earning text is displayed", "Updated extra earning text is not displayed");
                    break;
                case "bungii.com":
                    testStepAssert.isEquals(action.getText(driver_drivePage.Label_ExtraEarnings()), "$45/hour", "Updated extra earning text should be displayed", "Updated extra earning text is displayed", "Updated extra earning text is not displayed");
                    break;

            }
            log("I should be able to see the updated text.","I am able to see the updated text.",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @Then("^I check terms and privacy policy is displayed on login page$")
    public void i_check_terms_and_privacy_policy_is_displayed_on_login_page() throws Throwable {
       try{
           testStepAssert.isElementDisplayed(Page_Driver_Login.Link_Terms(),
                   "The terms link should be displayed",
                   "The terms link is displayed",
                   "The terms link is not displayed");
           testStepAssert.isEquals(Page_Driver_Login.Link_Terms().getAttribute("href"),PropertyUtility.getDataProperties("terms.page.link"),
                   "The correct link should be present for terms.",
                   "The correct link is present for terms.",
                   "The correct link is not present for terms.");
           testStepAssert.isElementDisplayed(Page_Driver_Login.Link_PrivacyPolicy(),
                   "The privacy policy link should be displayed",
                   "The privacy policy  link is displayed",
                   "The privacy policy link is not displayed");
           testStepAssert.isEquals(Page_Driver_Login.Link_PrivacyPolicy().getAttribute("href"),PropertyUtility.getDataProperties("privacy.policy.page.link"),
                   "The correct link should be present for privacy policy.",
                   "The correct link is present for privacy policy.",
                   "The correct link is not present for privacy policy.");
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step Should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }
    @Then("^I check if \"([^\"]*)\" are displayed$")
    public void i_check_if_something_are_displayed(String detailType) throws Throwable {
        try{
            switch (detailType){
                case "Wallet details":
                    testStepAssert.isElementDisplayed(driver_DashboardPage.Text_BranchWalletCreated(),
                            "The branch wallet created details should be displayed.",
                            "The branch wallet created details are displayed.",
                            "The branch wallet created details are not displayed.");
                    break;
                case "Processing details":
                    testStepAssert.isElementDisplayed(driver_DashboardPage.Text_BranchProcessing(),
                            "The branch wallet processing details should be displayed.",
                            "The branch wallet processing details are displayed.",
                            "The branch wallet processing details are not displayed.");
                    break;
                case "Acc not created":
                    testStepAssert.isElementDisplayed(driver_DashboardPage.Text_AccNotCreated(),
                            "Account not created should be displayed.",
                            "Account not created is displayed.",
                            "Account not created is not displayed.");
                    break;
            }
            log("I should be able to check "+detailType,"I am able to check "+detailType,false);
        }
        catch (Exception e){
            logger.error("Error performing step", e);
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I enter \"([^\"]*)\" Verification code$")
    public void i_enter_something_verificationcode(String type) {
        try {
            switch (type) {
                case "valid":
                   Thread.sleep(3000);
                   String adminPhoneNumber = (String) cucumberContextManager.getScenarioContext("Admin3LoginPhoneNumber");
                    String smsCode =  DbUtility.getAdminVerificationCode(adminPhoneNumber);
                    action.sendKeys(adminLoginPage.Textbox_VerificationCode(),smsCode);
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
            }
            log("I should be able to enter "+type+" verification code","I could enter "+type+" verification code",false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I enter \"([^\"]*)\" admin password$")
    public void i_enter_something_admin_password(String passwordType) throws Throwable {
        try{
       switch (passwordType){
           case "New":
               action.clearSendKeys(adminLoginPage.Textbox_NewPassword(),PropertyUtility.getDataProperties("admin.user3.new.password"));
               Thread.sleep(3000);
               action.clearSendKeys(adminLoginPage.Textbox_ConfrimNewPassword(),PropertyUtility.getDataProperties("admin.user3.new.password"));
               break;
       }
       log("I should be able to enter "+passwordType+" password in the new password textbox","I could enter "+passwordType+" password in the new password textbox",false );
    } catch (Exception e){
        logger.error("Error performing step", e);
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }

    @When("^I login to admin portal with new password$")
    public void i_login_to_admin_portal_with_new_password() throws Throwable {
        try{
        Thread.sleep(2000);
        action.clearSendKeys(Page_AdminLogin.TextBox_Phone(), PropertyUtility.getDataProperties("admin.user3"));
        action.clearSendKeys(Page_AdminLogin.TextBox_Password(), PropertyUtility.getDataProperties("admin.user3.new.password"));
        action.click(Page_AdminLogin.Button_AdminLogin());
        log("I should be able to login to admin portal with new password","I could login to admin portal with new password",false);
    } catch (Exception e){
        logger.error("Error performing step", e);
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }

    @When("^I block test \"([^\"]*)\"$")
    public void i_block_test_something(String admin) throws Throwable {
        try{
            switch (admin){
                case "admin3":
                    for(int passwordBlockTries =1; passwordBlockTries<=5;passwordBlockTries++){  // 5 is the number of attempts required to block admin
                        action.clearSendKeys(Page_AdminLogin.TextBox_Phone(), PropertyUtility.getDataProperties("admin.user3"));
                        action.clearSendKeys(Page_AdminLogin.TextBox_Password(), PropertyUtility.getDataProperties("admin.user3.fake.password"));
                        action.click(Page_AdminLogin.Button_AdminLogin());
                    }
                break;
            }

        log("I should be able to block test admin3", "I could block test admin3",false);
    } catch (Exception e){
        logger.error("Error performing step", e);
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }


    public String[] getTruckImages() {
        String[] truckImageList = new String[3];
        truckImageList[0] = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"), PropertyUtility.getImageLocations("TRUCK1_IMAGE"));
        truckImageList[1] = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"), PropertyUtility.getImageLocations("TRUCK2_IMAGE"));
        truckImageList[2] = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"), PropertyUtility.getImageLocations("TRUCK3_IMAGE"));
        return truckImageList;
    }
}
