package com.bungii.web.stepdefinitions.driver;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_LoginPage;
import com.bungii.web.pages.driver.*;
import com.bungii.web.pages.partner.Partner_Delivery_StatusPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.pass;

public class DriverRegistrationSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(DriverRegistrationSteps.class);
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    Driver_ForgotPasswordPage Page_ForgotPassword = new Driver_ForgotPasswordPage();
    Driver_VerifyPhonePage Page_VerifyPhone = new Driver_VerifyPhonePage();
    Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage();
    Driver_DetailsPage Page_Driver_Details = new Driver_DetailsPage();
    Driver_PickUpInfoPage Page_Driver_Pickup = new Driver_PickUpInfoPage();
    Driver_DocumentationPage Page_Driver_Documentation = new Driver_DocumentationPage();
    Driver_BankDetailsPage Page_Driver_BankDetails = new Driver_BankDetailsPage();
    Driver_TermsPage Page_Driver_Terms = new Driver_TermsPage();
    Driver_VideoTrainingPage Page_Driver_Video = new Driver_VideoTrainingPage();
    Driver_FinishPage Page_Driver_Finish = new Driver_FinishPage();
    Driver_DashboardPage Page_Driver_Dashboard = new Driver_DashboardPage();
    Partner_Delivery_StatusPage partner_Delivery_StatusPage= new Partner_Delivery_StatusPage();
    GeneralUtility utility = new GeneralUtility();
    ActionManager action = new ActionManager();
    Admin_LoginPage adminLoginPage = new Admin_LoginPage();

    WebDriver driver = SetupManager.getDriver();


    @Given("^I navigate to \"([^\"]*)\"$")
    public void i_navigate_to_something(String page) throws Throwable {
        try{
        switch (page)
        {
            case "Bungii Driver URL":
                utility.NavigateToDriverLogin();
                break;
            case "Driver Details":
                action.switchToTab(0);
                action.click(Page_Driver_Details.Menu_DriverDetails());
                break;
            case "Delivery Status URL":
                utility.NavigateDriverRatingWebLink();
                Thread.sleep(1000);
                testStepAssert.isElementDisplayed(partner_Delivery_StatusPage.Label_DeliveryDetailsTitle(),"Delivery Status Page should be shown","Delivery Status page is shown","Delivery Status page is not shown");
                log("I navigate to Delivery status page" ,
                        "I navigated to Delivery status page" , false);
                break;
            case "Delivery Status URL again":
                action.switchToTab(2);
                action.refreshPage();
                Thread.sleep(1000);
                break;
            case "Partner Management":
                utility.NavigateToPartnerManagementLogin();;
                Thread.sleep(1000);
                break;
            case "Dashboard":
                action.switchToTab(0);
                Thread.sleep(1000);
                break;
            case "Login Page":
                action.switchToTab(0);
                Thread.sleep(1000);
                break;
        }
        pass("I should be navigate to " + page,
                "I am navigate to " + page, false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Given("^I am logged in as driver$")
    public void GivenIAmLoggedInAsDriver() {
       //  utility.DriverLogin("9999999113", "Cci12345");
try{
        utility.DriverLogin(PropertyUtility.getDataProperties("DriverPhoneNumber"), PropertyUtility.getDataProperties("DriverPassword"));
        pass("I am logged in as driver",
                "I was logged in as driver using " + PropertyUtility.getDataProperties("DriverPhoneNumber") + " phone number", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the driver logout from dashboard$")
    public void the_driver_logout_from_dashboard() throws Throwable {
        try{
            utility.DriverLogout();
            log("I should be logged out from dashboard ","I clicked ", false);
            } catch(Exception e){
             logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
             error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I enter \"([^\"]*)\" details on Signup page$")
    public void i_enter_something_details_on_signup_page(String strArg1) throws Throwable {
        try{
            switch (strArg1) {
            case "valid":
                action.clearSendKeys(Page_Driver_Reg.TextBox_FirstName(), PropertyUtility.getDataProperties("DriverFirstName"));
                String Lastname = utility.GetUniqueLastName();
                int randomnumber = utility.GetUniqueNumber();
                action.clearSendKeys(Page_Driver_Reg.TextBox_LastName(),Lastname);
                cucumberContextManager.setScenarioContext("FIRSTNAME", PropertyUtility.getDataProperties("DriverFirstName"));
                cucumberContextManager.setScenarioContext("LASTNAME", Lastname);
                cucumberContextManager.setFeatureContextContext("LASTNAME", Lastname);

                action.clearSendKeys(Page_Driver_Reg.TextBox_Email(), "bungiiauto+"+randomnumber+"@gmail.com"); //PropertyUtility.getDataProperties("DriverEmail"));
                action.clearSendKeys(Page_Driver_Reg.TextBox_CreatePassword(), PropertyUtility.getDataProperties("DriverPassword"));
                //action.clearSendKeys(Page_Driver_Reg.TextBox_ConfirmPassword(), PropertyUtility.getDataProperties("DriverPassword"));
                action.selectElementByText(Page_Driver_Reg.Dropdown_Location(),PropertyUtility.getDataProperties("DriverLocation"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Driver_Reg.TextBox_FirstName(), PropertyUtility.getDataProperties("Invalid_DriverName"));
                action.clearSendKeys(Page_Driver_Reg.TextBox_LastName(), PropertyUtility.getDataProperties("Invalid_DriverName"));
                action.clearSendKeys(Page_Driver_Reg.TextBox_Email(), PropertyUtility.getDataProperties("Invalid_DriverEmail"));
                action.clearSendKeys(Page_Driver_Reg.TextBox_CreatePassword(), PropertyUtility.getDataProperties("Invalid_DriverPassword"));
                //action.clearSendKeys(Page_Driver_Reg.TextBox_ConfirmPassword(), PropertyUtility.getDataProperties("Short_DriverPassword"));
                action.selectElementByText(Page_Driver_Reg.Dropdown_Location(),PropertyUtility.getDataProperties("DriverLocation"));
                break;
            case "short password":
                action.clearSendKeys(Page_Driver_Reg.TextBox_CreatePassword(), PropertyUtility.getDataProperties("Short_DriverPassword"));
                action.selectElementByText(Page_Driver_Reg.Dropdown_Location(),PropertyUtility.getDataProperties("DriverLocation"));
                break;
            default:
                break;
        }
        log("I should able to enter "+ strArg1+" on signup pages","I entered  "+strArg1 +" on sign up page", true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I click \"([^\"]*)\" on driver portal$")
    public void i_click_something_on_driver_portal(String p0) throws Throwable {
      try{
          switch (p0) {
            case "LOG IN link":
                action.click(Page_Driver_Login.Tab_LogIn());
                break;
            case "LOG IN button":
                action.click(Page_Driver_Login.Button_DriverLogin());
                break;
            case "Forgot Password":
                action.click(Page_ForgotPassword.Link_ForgotPassword());
                break;
            case "Back to Login":
                action.click(Page_ForgotPassword.Link_BackToLogin());
                break;
            case "Send Verification Code":
                action.click(Page_ForgotPassword.Button_SendVerifCode());
                break;
            case "Resend Code on Verify your phone page":
                String Code_Initial = DbUtility.getVerificationCode(PropertyUtility.getDataProperties("DriverPhoneNumber"));
                cucumberContextManager.setScenarioContext("Code_Initial", Code_Initial);
                action.click(Page_VerifyPhone.Button_ResendCode());
                break;
            case "Reset Password":
                action.click(Page_VerifyPhone.Button_ResetPassword());
                break;
            case "Signup button":
                action.click(Page_Driver_Reg.Button_SignUp());
                break;
            case "Resend verification code":
                String DriverPhone = (String) cucumberContextManager.getScenarioContext("DriverPhone");
                String VerifCode_Initial = DbUtility.getVerificationCode(DriverPhone);
                cucumberContextManager.setScenarioContext("VerifCode_Initial", VerifCode_Initial);
                action.click(Page_Driver_Reg.Button_ResendCode());
                break;
            case "Submit verification code":
                action.click(Page_Driver_Reg.Button_SubmitVerification());
                break;
            case "Continue Registration":
                action.click(Page_Driver_Reg.Button_ContinueReg());
                break;
            case "I agree to the Terms and Conditions":
                if (Page_Driver_Terms.CheckBox_Agree(true) != null) {
                    if (Page_Driver_Terms.CheckBox_Agree(true).isSelected() == false)
                        action.click(Page_Driver_Terms.CheckBox_Agree_Click());
                }
                break;
            case "I have viewed the entire video":
                if (Page_Driver_Video.CheckBox_Viewed(true) != null) {
                    if (Page_Driver_Video.CheckBox_Viewed(true).isSelected() == false)
                        action.click(Page_Driver_Video.CheckBox_Viewed_Click());
                }
                break;
            case "Continue on Finish page":
                action.click(Page_Driver_Finish.Button_FinishContinue());
                break;
              case "Terms":
                  action.click(Page_Driver_Dashboard.Menu_Terms());
                  break;
            case "Terms & Conditions":
                  action.click(Page_Driver_Dashboard.Menu_TermsAndConditions());
                  break;
            case "Privacy Policy":
                  action.click(Page_Driver_Dashboard.Menu_PrivacyPolicy());
                  break;
            default:
                break;
        }
        log("I able to click "+p0+" on driver portal","I clicked  "+p0 +" on driver page", true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should be directed to \"([^\"]*)\" on Driver portal$")
    public void i_should_be_directed_to_something_on_driver_portal(String strArg1) throws Throwable {
       try{
           switch (strArg1) {
            case "signup tab":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.Header_DriverRegistration()), PropertyUtility.getMessage("DriverRegistrationHeader"), PropertyUtility.getMessage("DriverRegistrationHeader")+ " should be displayed",PropertyUtility.getMessage("DriverRegistrationHeader")+ " is displayed",PropertyUtility.getMessage("DriverRegistrationHeader")+ " is not displayed");
                break;
            case "LOG IN tab":
                testStepVerify.isEquals(action.getText(Page_Driver_Login.Header_DriverLogin()), PropertyUtility.getMessage("DriverLoginHeader"), PropertyUtility.getMessage("DriverLoginHeader")+" should be displayed", PropertyUtility.getMessage("DriverLoginHeader")+" is displayed", PropertyUtility.getMessage("DriverLoginHeader")+" is not displayed");
                break;
            case "Forgot Password tab":
                testStepVerify.isEquals(action.getText(Page_ForgotPassword.Header_ForgotPassword()), PropertyUtility.getMessage("DriverForgotPasswordHeader"), PropertyUtility.getMessage("DriverForgotPasswordHeader")+" should be displayed", PropertyUtility.getMessage("DriverForgotPasswordHeader")+" is displayed", PropertyUtility.getMessage("DriverForgotPasswordHeader")+" is not displayed");
                break;
            case "Verify Your Phone tab":
                testStepVerify.isEquals(action.getText(Page_VerifyPhone.Header_VerifyPhone()), PropertyUtility.getMessage("DriverVerifyPhoneHeader"),  PropertyUtility.getMessage("DriverVerifyPhoneHeader")+" should be displayed", PropertyUtility.getMessage("DriverForgotPasswordHeader")+" is displayed", PropertyUtility.getMessage("DriverVerifyPhoneHeader")+" is not displayed");
                break;
            case "phone verification page":
                try {
                    testStepVerify.isEquals(action.getText(Page_Driver_Reg.Text_Verification()), PropertyUtility.getMessage("RegSuccess"), PropertyUtility.getMessage("RegSuccess") + " should be displayed", PropertyUtility.getMessage("RegSuccess") + " is displayed", PropertyUtility.getMessage("RegSuccess") + " is not displayed");
                }
                catch(Exception ex) {
                    logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
                    error("Driver should log in to driver portal", "Driver is not signed in due to error.", true);
                }
                break;
            case "Verification Successful page":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.Header_VerificationSuccess()), PropertyUtility.getMessage("SMSVerifSuccess"),  PropertyUtility.getMessage("SMSVerifSuccess")+" should be displayed", PropertyUtility.getMessage("SMSVerifSuccess")+" is displayed", PropertyUtility.getMessage("SMSVerifSuccess")+" is not displayed");
                break;
            case "Driver Details page":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("DriverDetailsHeader"),  PropertyUtility.getMessage("DriverDetailsHeader")+" should be displayed", PropertyUtility.getMessage("DriverDetailsHeader")+" is displayed", PropertyUtility.getMessage("DriverDetailsHeader")+" is not displayed");
                break;
            case "Pickup Info page":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("PickupInfoHeader"),  PropertyUtility.getMessage("PickupInfoHeader")+" should be displayed", PropertyUtility.getMessage("PickupInfoHeader")+" is displayed", PropertyUtility.getMessage("PickupInfoHeader")+" is not displayed");
                break;
            case "Documentation page":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("DocHeader"),  PropertyUtility.getMessage("DocHeader")+" should be displayed", PropertyUtility.getMessage("DocHeader")+" is displayed", PropertyUtility.getMessage("PickupInfoHeader")+" is not displayed");
                break;
            case "Bank Details page":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("BankDetHeader"),  PropertyUtility.getMessage("BankDetHeader")+" should be displayed", PropertyUtility.getMessage("BankDetHeader")+" is displayed", PropertyUtility.getMessage("BankDetHeader")+" is not displayed");
                break;
            case "Terms & Conditions":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("TermsHeader"),  PropertyUtility.getMessage("TermsHeader")+" should be displayed", PropertyUtility.getMessage("TermsHeader")+" is displayed", PropertyUtility.getMessage("TermsHeader")+" is not displayed");
                testStepVerify.isEquals(action.getText(Page_Driver_Terms.Terms_H5()), PropertyUtility.getMessage("TermsSubHeader"),  PropertyUtility.getMessage("TermsSubHeader")+" should be displayed", PropertyUtility.getMessage("TermsSubHeader")+" is displayed", PropertyUtility.getMessage("TermsSubHeader")+" is not displayed");
                testStepVerify.isEquals(action.getText(Page_Driver_Terms.Text_Terms()), PropertyUtility.getMessage("TermsText"),  PropertyUtility.getMessage("TermsText")+" should be displayed", PropertyUtility.getMessage("TermsText")+" is displayed", PropertyUtility.getMessage("TermsText")+" is not displayed");
                break;
            case "Video Training":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("VideoHeader"),  PropertyUtility.getMessage("VideoHeader")+" should be displayed", PropertyUtility.getMessage("VideoHeader")+" is displayed", PropertyUtility.getMessage("VideoHeader")+" is not displayed");
                testStepVerify.isElementDisplayed(Page_Driver_Video.Screen_Video(true), "Video screen should be displayed", "Video screen was displayed", "Video screen was not displayed");
                break;
            case "Finish":
                testStepVerify.isEquals(action.getText(Page_Driver_Details.DriverReg_AllPagesHeader()), PropertyUtility.getMessage("FinishHeader"),  PropertyUtility.getMessage("FinishHeader")+" should be displayed", PropertyUtility.getMessage("FinishHeader")+" is displayed", PropertyUtility.getMessage("FinishHeader")+" is not displayed");
                break;
            case "Dashboard":
                testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.Header_Dashboard()), PropertyUtility.getMessage("DriverDashboardHeader"));
                //testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.SideNavigationSetting()), PropertyUtility.getMessage("DriverHomeSetting"),  PropertyUtility.getMessage("DriverHomeSetting")+" should be displayed", PropertyUtility.getMessage("DriverHomeSetting")+" is displayed", PropertyUtility.getMessage("DriverHomeSetting")+" is not displayed");
                //testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.SideNavigationGeneral()), PropertyUtility.getMessage("DriverHomeGENERAL"),  PropertyUtility.getMessage("DriverHomeGENERAL")+" should be displayed", PropertyUtility.getMessage("DriverHomeGENERAL")+" is displayed", PropertyUtility.getMessage("DriverHomeGENERAL")+" is not displayed");
                break;
            default:
                break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see \"([^\"]*)\" on Driver Registration$")
    public void i_should_see_something_on_driver_registration(String p0) throws Throwable {
        try{
            switch (p0) {
            case "new verification code":
                String VerifCode_Initial = (String) cucumberContextManager.getScenarioContext("VerifCode_Initial");
                String DriverPhone = (String) cucumberContextManager.getScenarioContext("DriverPhone");
                Thread.sleep(5000);
                String VerifCode_Updated = DbUtility.getVerificationCode(DriverPhone);
                cucumberContextManager.setScenarioContext("VerificationCode", VerifCode_Updated);
                testStepVerify.isFalse(VerifCode_Updated.equals(VerifCode_Initial), "New verification code should not be same as old code", "New verification code is not same as old code", "New verification code is same as old code");
                break;
            case "Logged in user name":
                String UserName = PropertyUtility.getDataProperties("DriverFirstName") + " " + (String)cucumberContextManager.getFeatureContextContext("LASTNAME");//PropertyUtility.getDataProperties("DriverLastName");
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.Text_DriverName()), UserName,UserName+ " should be displayed",UserName+ " is displayed",UserName+ " is not displayed");
                break;
            case "correct field validations":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_FirstName()), PropertyUtility.getMessage("DReg_FirstName_Invalid"),  PropertyUtility.getMessage("DReg_FirstName_Invalid")+" should be displayed", PropertyUtility.getMessage("DReg_FirstName_Invalid")+" is displayed", PropertyUtility.getMessage("DReg_FirstName_Invalid")+" is not displayed");
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_LastName()), PropertyUtility.getMessage("DReg_LastName_Invalid"),  PropertyUtility.getMessage("DReg_LastName_Invalid")+" should be displayed", PropertyUtility.getMessage("DReg_LastName_Invalid")+" is displayed", PropertyUtility.getMessage("DReg_LastName_Invalid")+" is not displayed");
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_Email()), PropertyUtility.getMessage("DReg_Email_Invalid"),  PropertyUtility.getMessage("DReg_Email_Invalid")+" should be displayed", PropertyUtility.getMessage("DReg_Email_Invalid")+" is displayed", PropertyUtility.getMessage("DReg_Email_Invalid")+" is not displayed");
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_Phone()), PropertyUtility.getMessage("DReg_Phone_Invalid"),  PropertyUtility.getMessage("DReg_Phone_Invalid")+" should be displayed", PropertyUtility.getMessage("DReg_Phone_Invalid")+" is displayed", PropertyUtility.getMessage("DReg_Phone_Invalid")+" is not displayed");
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_CreatePassword()), PropertyUtility.getMessage("DReg_Password_Invalid"),  PropertyUtility.getMessage("DReg_Password_Invalid")+" should be displayed", PropertyUtility.getMessage("DReg_Password_Invalid")+" is displayed", PropertyUtility.getMessage("DReg_Password_Invalid")+" is not displayed");
                break;
            case "Global validation message":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_BlankFields()), PropertyUtility.getMessage("Err_Pages_BlankFields"),  PropertyUtility.getMessage("Err_Pages_BlankFields")+" should be displayed", PropertyUtility.getMessage("Err_Pages_BlankFields")+" is displayed", PropertyUtility.getMessage("Err_Pages_BlankFields")+" is not displayed");
                break;
            case "existing phone error":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_ExistingPhone()), PropertyUtility.getMessage("DReg_Phone_Exists"),  PropertyUtility.getMessage("DReg_Phone_Exists")+" should be displayed", PropertyUtility.getMessage("DReg_Phone_Exists")+" is displayed", PropertyUtility.getMessage("DReg_Phone_Exists")+" is not displayed");
                break;
            case "field validation for short password":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_ShortPassword()), PropertyUtility.getMessage("DReg_Password_Short"),  PropertyUtility.getMessage("DReg_Password_Short")+" should be displayed", PropertyUtility.getMessage("DReg_Password_Short")+" is displayed", PropertyUtility.getMessage("DReg_Password_Short")+" is not displayed");
                break;
            case "validation for blank verification code":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_VerifiCode_Blank()), PropertyUtility.getMessage("VerifCode_Err_Blank"),  PropertyUtility.getMessage("VerifCode_Err_Blank")+" should be displayed", PropertyUtility.getMessage("VerifCode_Err_Blank")+" is displayed", PropertyUtility.getMessage("VerifCode_Err_Blank")+" is not displayed");
                break;
            case "validation for incorrect verification code":
                testStepVerify.isEquals(action.getText(Page_Driver_Reg.ERR_VerifiCode_Invlid()), PropertyUtility.getMessage("VerifCode_Err_Invalid"),  PropertyUtility.getMessage("VerifCode_Err_Invalid")+" should be displayed", PropertyUtility.getMessage("VerifCode_Err_Invalid")+" is displayed", PropertyUtility.getMessage("VerifCode_Err_Invalid")+" is not displayed");
                break;
            case "Verification Successful":
                testStepAssert.isEquals(action.getText(Page_Driver_Reg.Label_Success()), p0,  p0+" should be displayed", p0+" is displayed", p0+" is not displayed");
                break;
            default:
                break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I enter \"([^\"]*)\" verification code$")
    public void i_enter_something_verification_code(String strArg1) throws Throwable {
       try{
           switch (strArg1) {
            case "correct":
                String DriverPhone = (String) cucumberContextManager.getScenarioContext("DriverPhone");
                String VerificationCode = DbUtility.getVerificationCode(DriverPhone);
                action.clearSendKeys(Page_Driver_Reg.TextBox_VerificationCode(), VerificationCode);
                break;
            case "empty":
                action.clear(Page_Driver_Reg.TextBox_VerificationCode());
                break;
            case "incorrect":
                action.clearSendKeys(Page_Driver_Reg.TextBox_VerificationCode(), PropertyUtility.getDataProperties("Invalid_DriverPhoneNumber"));
                break;
            default:
                break;
        }
        log("I should able to enter "+strArg1+" verification code","I entered "+strArg1, true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I enter driver Phone number as \"([^\"]*)\" and valid password$")
    public void i_enter_driver_phone_number_as_something_and_valid_password(String phone) throws Throwable {
        try{
        Thread.sleep(3000);
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), phone);
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("web.valid.common.driver.password"));
        //  action.click(Page_Driver_Login.Button_DriverLogin());
        log("I enter driver Phone number as "+phone+" and valid password","I have entered driver Phone number as "+phone+" and valid password", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I enter \"([^\"]*)\" driver phone number on Signup page$")
    public void i_enter_something_driver_phone_number_on_signup_page(String p0) throws Throwable {
       try{
           switch (p0) {
            case "unique":
                String DriverPhone = utility.generateMobileNumber();
                cucumberContextManager.setScenarioContext("DriverPhone", DriverPhone);
                action.clearSendKeys(Page_Driver_Reg.TextBox_PhoneNumber(), DriverPhone);
                break;
            case "invalid":
                action.clearSendKeys(Page_Driver_Reg.TextBox_PhoneNumber(), PropertyUtility.getDataProperties("Invalid_DriverPhoneNumber"));
                break;
            case "existing":
                action.clearSendKeys(Page_Driver_Reg.TextBox_PhoneNumber(), PropertyUtility.getDataProperties("Existing_DriverPhoneNumber"));
                break;
            default:
                break;
        }
        log("I should able to enter "+p0+" driver phone number on Signup page","I entered "+p0 +" driver phone number on signup page", true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I should receive \"([^\"]*)\" email$")
    public void i_should_receive_something_email(String emailSubject) throws Throwable {

        try{
       String emailBody  = utility.GetSpecificPlainTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"),emailSubject);
        if (emailBody == null) {
            testStepAssert.isFail("Email : " + emailSubject + " not received");
        }
        String driverName ="";
       String message = "";
        emailBody= emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ","");
        logger.detail("Email Body (Actual) : "+ emailBody);
        switch (emailSubject)
       {
           case "Your application has been rejected.":
               driverName = (String) cucumberContextManager.getScenarioContext("FIRSTNAME");
               message = utility.getExpectedDriverRejectionEmailContent(driverName);
               break;
           case "BUNGII: Application Received.":
               driverName = (String) cucumberContextManager.getScenarioContext("FIRSTNAME");
               message = utility.getExpectedDriverSubmissionEmailContent(driverName);
               break;
           case "BUNGII: Time to Hit the Road!":
               driverName = (String) cucumberContextManager.getScenarioContext("FIRSTNAME");
               message = utility.getExpectedDriverApprovalEmailContent(driverName);
               message = utility.setDownloadLink(message,emailBody);
               break;
           case "BUNGII: Action Required!":
               driverName = (String) cucumberContextManager.getScenarioContext("FIRSTNAME");
               message = utility.getExpectedDriverActionRequiredEmailContent(driverName);
               message = utility.setDownloadLink(message,emailBody);
               break;
       }
        message= message.replaceAll(" ","");
        logger.detail("Email Body (Expected) : "+ message);

       testStepAssert.isEquals(emailBody, message,"Email (Expected): "+message+" content should match", "Email (Actual): "+emailBody+" content matches", "Email (Actual) "+emailBody+" content doesn't match");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^Admin should receive \"([^\"]*)\" email$")
    public void admin_should_receive_something_email(String emailSubject) throws Throwable {
        try{
            String emailBody  = utility.GetSpecificPlainTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"),emailSubject);
        if (emailBody == null) {
            testStepAssert.isFail("Email : " + emailSubject + " not received");
        }
        emailBody= emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ","");
        String driverName ="";
        String driverPhone = "";
        String message = "";
        logger.detail("Email Body (Actual) : "+ emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ",""));

        switch (emailSubject)
        {
            case "New driver registration complete!":
                driverName = (String) cucumberContextManager.getScenarioContext("FIRSTNAME") +" "+(String) cucumberContextManager.getScenarioContext("LASTNAME");
                driverPhone = (String) cucumberContextManager.getScenarioContext("DriverPhone");
                message = utility.getExpectedDriverRegistrationCompleteEmailContent(driverName, driverPhone);
                break;
            case "Bungii Refund Receipt for customer":
                String pickUpRef= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
                String driver = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
                String[] splitCust = custName.split(" ");
                String total= (String) cucumberContextManager.getScenarioContext("DELIVERY_TOTAL");
                String[] splitTotal = total.split("\\.");
                String [] locations=DbUtility.getFullPickUpAndDropOff(pickUpRef);
                message = utility.getExpectedBungiiRefundAdminEmail(splitCust[0], splitTotal[0], driver,locations[0],locations[1]);
                break;

        }
        message= message.replaceAll(" ","");
        logger.detail("Email Body (Expected) : "+ message);
        testStepAssert.isEquals(emailBody, message,"Email (Expected): "+message+" content should match", "Email (Actual): "+emailBody+" content matches", "Email (Actual) "+emailBody+" content doesn't match");

    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @Then("^I should see the \"([^\"]*)\" displayed$")
    public void i_should_see_the_something_displayed(String strArg1) throws Throwable {
        try {
            String updatedVerbiage = "Driving with Bungii is a flexible, easy way to earn extra money on the side. To get started, fill out the application below and we’ll get you on the road in no time.";
            action.waitUntilIsElementExistsAndDisplayed(Page_Driver_Reg.Text_Verbiage(), (long) 5000);
            String existingVerbiage = action.getText(Page_Driver_Reg.Text_Verbiage());
            testStepAssert.isEquals(existingVerbiage,updatedVerbiage,"Verbiage text should be updated to newest text","Verbiage text is updated to newest text","Verbiage text is not updated to newest text");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see the \"([^\"]*)\" textbox not displayed$")
    public void i_should_see_the_something_textbox_not_displayed(String strArg1) throws Throwable {
        try {
            testStepAssert.isFalse(action.isElementPresent(Page_Driver_Reg.TextBox_ConfirmPassword(true)), "Confirm password textbox should not be displayed", "Confirm password textbox is not displayed","Confirm password textbox is displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^The password should be masked$")
    public void the_password_should_be_masked() throws Throwable {
        try {
            String passwordMasked = "password";
            Thread.sleep(1000);
            String expectedPasswordMasked =Page_Driver_Reg.TextBox_CreatePassword().getAttribute("type");
            testStepAssert.isTrue(expectedPasswordMasked.contentEquals(passwordMasked),"Password should be masked","Password is masked","Password is not masked");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I click on the \"([^\"]*)\" button$")
    public void i_click_on_the_something_button(String strArg1) throws Throwable {
        try {
            action.waitUntilIsElementExistsAndDisplayed(Page_Driver_Reg.Link_EyeOpen(), (long) 5000);
            action.click(Page_Driver_Reg.Link_EyeOpen());
            log("I should be able to click on the closed eye button","I could click on the closed eye button",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see the password as text$")
    public void i_should_see_the_password_as_text() throws Throwable {
        try {
            String passwordUnmasked = "text";
            Thread.sleep(1000);
            String expectedPasswordUnMasked =Page_Driver_Reg.TextBox_CreatePassword().getAttribute("type");
            testStepAssert.isTrue(expectedPasswordUnMasked.contentEquals(passwordUnmasked),"Password should not be masked","Password is not masked","Password is masked");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }


    @And("I note trip count for driver")
    public void iNoteTripCountForDriver() {
        try{
            String tripCount = action.getText(Page_Driver_Details.Count_TotalTrips());
            cucumberContextManager.setScenarioContext("OldTripCount",tripCount);
            log("Trip count for driver should be " + tripCount,"Trip count for is driver " + tripCount);
        }catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("I close {string} Page")
    public void iClosePage(String page) {
        try {
            switch (page) {
                case "Updated Terms & Conditions":
                    driver.close();
                break;
                case "Updated Privacy Policy":
                    driver.close();
                break;
            }
            log("I should able to close " + page,"I am able to close " + page, false);
        }catch(Exception e){
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step should be successful", "Error performing step,Please check logs for more details",
                        true);
            }
    }

    @When("^I enter \"([^\"]*)\" phone number on \"([^\"]*)\" screen$")
    public void i_enter_something_phone_number_on_something_screen(String passwordType, String strArg2) throws Throwable {
        try{
        action.clearSendKeys(adminLoginPage.Textbox_CellPhoneNumber(), PropertyUtility.getDataProperties("admin.user3"));
        cucumberContextManager.setScenarioContext("Admin3LoginPhoneNumber",PropertyUtility.getDataProperties("admin.user3"));
            log("I should able to enter "+ passwordType+" phone number","I could enter "+ passwordType+" phone number", false);
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
}
