package com.bungii.ios.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.api.utilityFunctions.AuthServices;
import com.bungii.api.utilityFunctions.CoreServices;
import com.bungii.api.utilityFunctions.GoogleMaps;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.manager.AssertManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.*;
import com.bungii.ios.pages.customer.*;
import com.bungii.ios.pages.driver.BungiiCompletedPage;
import com.bungii.ios.pages.driver.BungiiRequestPage;
import com.bungii.ios.pages.driver.DriverBungiiDetailsPage;
import com.bungii.ios.pages.driver.TripDetailsPage;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.pages.other.SafariPage;
import com.bungii.ios.stepdefinitions.customer.HomeSteps;
import com.bungii.ios.stepdefinitions.customer.LogInSteps;
import com.bungii.ios.stepdefinitions.driver.*;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.bungii.ios.stepdefinitions.driver.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bungii.SetupManager.*;
import static com.bungii.common.manager.ResultManager.*;


public class CommonSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(CommonSteps.class);
    ActionManager action = new ActionManager();
    String Image_Solo = "bungii_type-solo", Image_Duo = "bungii_type-duo";
    private EstimatePage estimatePage;
    private com.bungii.ios.pages.customer.HomePage homePage;
    private com.bungii.ios.pages.driver.HomePage driverHomePage;
    private BungiiDetails customerbungiiDetails;
    private DriverBungiiDetailsPage driverbungiiDetailspage;
    private SuccessPage successPage;
    private LoginPage loginPage;
    private SignupPage signupPage;
    private TripDetailsPage tripDetails;
    private com.bungii.ios.pages.driver.UpdateStatusPage driverUpdateStatusPage;
    private com.bungii.ios.pages.customer.UpdateStatusPage customerUpdateStatusPage;
    private TripDetailsPage tripDetailsPage;
    private BungiiCompletedPage driverBungiiCompletedPage;
    private BungiiCompletePage customerBungiiCompletePage;
    private PromotionPage promotionPage;
    private ScheduledTripsPage scheduledTripsPage;
    private ScheduledBungiiPage customerScheduledBungiiPage;
    private com.bungii.ios.pages.driver.ScheduledBungiiPage driverScheduledBungiiPage;
    private FaqPage faqPage;
    private PaymentPage paymentPage;
    private SupportPage supportPage;
    private PromosPage promosPage;
    private PromoCodePage promosCodePage;
    private AccountPage accountPage;
    private ScheduledBungiiPage scheduledBungiiPage;
    private InvitePage invitePage;
    private ForgotPasswordPage forgotPasswordPage;
    private VerificationPage verificationPage;
    private SearchingPage searchingPage;
    private DriverNotAvailablePage driverNotAvailablePage;
    private NotificationPage notificationPage;
    private BungiiRequestPage bungiiRequestPage;
    private BungiiAcceptedPage bungiiAcceptedPage;
    private TermsAndConditionPage termsAndConditionPage;
    private EnableNotificationPage enableNotificationPage;
    private EnableLocationPage enableLocationPage;
    private TutorialPage tutorialPage;
    private LogInPage logInPage;
    DashBoardPage dashBoardPage;
    private DbUtility dbUtility = new DbUtility();
    private BungiiDetails bungiiDetails = new BungiiDetails();
    private GeneralUtility utility = new GeneralUtility();
    private com.bungii.ios.pages.driver.HomePage driverhomepage;
    AuthServices authServices = new AuthServices();
    CoreServices coreServices = new CoreServices();
    public CommonSteps(com.bungii.ios.pages.driver.HomePage driverhomepage, DashBoardPage dashBoardPage, LogInPage logInPage, PromoCodePage promosCodePage, FaqPage faqPage, ScheduledBungiiPage scheduledBungiiPage, AccountPage accountPage,
                       PaymentPage paymentPage, SupportPage supportPage, PromosPage promosPage, EstimatePage estimatePage,
                       HomePage homePage, LoginPage loginPage, SignupPage signupPage,
                       ScheduledBungiiPage customerScheduledBungiiPage,
                       com.bungii.ios.pages.driver.ScheduledBungiiPage driverScheduledBungiiPage, BungiiDetails bungiiDetails,
                       TripDetailsPage tripDetails, DriverBungiiDetailsPage driverbungiiDetailspage,
                       com.bungii.ios.pages.driver.UpdateStatusPage updateStatusPage, SuccessPage successPage, TripDetailsPage tripDetailsPage,
                       BungiiCompletedPage bungiiCompletedPage, BungiiCompletePage customerBungiiCompletePage,
                       PromotionPage promotionPage, ScheduledTripsPage scheduledTripsPage, InvitePage invitePage,
                       ForgotPasswordPage forgotPasswordPage, SearchingPage searchingPage,
                       DriverNotAvailablePage driverNotAvailablePage, NotificationPage notificationPage,
                       BungiiRequestPage bungiiRequestPage, com.bungii.ios.pages.customer.UpdateStatusPage customerUpdateStatusPage,
                       BungiiAcceptedPage bungiiAcceptedPage, com.bungii.ios.pages.driver.HomePage driverHomePage,
                       VerificationPage verificationPage, TermsAndConditionPage termsAndConditionPage, EnableNotificationPage enableNotificationPage, EnableLocationPage enableLocationPage, TutorialPage tutorialPage) {
        this.estimatePage = estimatePage;
        this.homePage = homePage;
        this.loginPage = loginPage;
        this.signupPage = signupPage;
        this.customerbungiiDetails = bungiiDetails;
        this.tripDetails = tripDetails;
        this.driverbungiiDetailspage = driverbungiiDetailspage;
        this.driverUpdateStatusPage = updateStatusPage;
        this.successPage = successPage;
        this.tripDetails = tripDetails;
        this.driverBungiiCompletedPage = bungiiCompletedPage;
        this.customerBungiiCompletePage = customerBungiiCompletePage;
        this.promotionPage = promotionPage;
        this.scheduledTripsPage = scheduledTripsPage;
        this.customerScheduledBungiiPage = customerScheduledBungiiPage;
        this.driverScheduledBungiiPage = driverScheduledBungiiPage;
        this.faqPage = faqPage;
        this.paymentPage = paymentPage;
        this.supportPage = supportPage;
        this.promosPage = promosPage;
        this.accountPage = accountPage;
        this.scheduledBungiiPage = scheduledBungiiPage;
        this.invitePage = invitePage;
        this.forgotPasswordPage = forgotPasswordPage;
        this.searchingPage = searchingPage;
        this.driverNotAvailablePage = driverNotAvailablePage;
        this.notificationPage = notificationPage;
        this.bungiiRequestPage = bungiiRequestPage;
        this.customerUpdateStatusPage = customerUpdateStatusPage;
        this.bungiiAcceptedPage = bungiiAcceptedPage;
        this.driverHomePage = driverHomePage;
        this.termsAndConditionPage = termsAndConditionPage;
        this.verificationPage = verificationPage;
        this.enableNotificationPage = enableNotificationPage;
        this.enableLocationPage = enableLocationPage;
        this.tutorialPage = tutorialPage;
        this.promosCodePage = promosCodePage;
        this.logInPage = logInPage;
        this.dashBoardPage = dashBoardPage;
        this.driverhomepage = driverhomepage;
    }
    LiveTripsPage liveTripsPage=new LiveTripsPage();
    SafariPage safariPage=new SafariPage();
    com.bungii.ios.pages.driver.UpdateStatusPage updateStatusPage = new com.bungii.ios.pages.driver.UpdateStatusPage();

    @Then("^\"([^\"]*)\" message should be displayed on \"([^\"]*)\" page$")
    public void something_message_should_be_displayed_on_something_page(String messageElement, String screen) {
        try {
            boolean messageDisplayed = false;

            switch (messageElement.toUpperCase()) {
                case "BUNGII CANCEL":
                    Thread.sleep(35000);
                    messageDisplayed = scheduledTripsPage.isElementEnabled(scheduledTripsPage.Text_Success()) && scheduledTripsPage.Text_Success().getText().equals(PropertyUtility.getMessage("admin.cancel.sucess"));
                    break;
                case "ADD NEW CARD":
                    messageDisplayed = action.getValueAttribute(paymentPage.Text_AddInfo()).equals(PropertyUtility.getMessage("customer.payment.add")) && paymentPage.isElementEnabled(paymentPage.Text_AddHeader());
                    break;
                case "FORGOT PASSWORD INFORMATION":
                    messageDisplayed = action.getValueAttribute(forgotPasswordPage.Text_Info())
                            .equals(PropertyUtility.getMessage("customer.forgotpassword.info"));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }
            testStepVerify.isTrue(messageDisplayed,
                    messageElement + " should be displayed", messageElement + " Message is Displayed",
                    messageElement + " Message is not Displayed");
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" should be present in \"([^\"]*)\" screen$")
    public void something_should_be_present_in_something_screen(String button, String screen) {

        try {
            boolean isFound = false , isFound2 = false;
            switch (button.toUpperCase()) {
                case "GET ESTIMATE":
                    // homePage.clickEstimateButton();
                    break;
                case "INVITE REFERRALS":
                    isFound = homePage.isElementEnabled((homePage.Button_Invite()));
                    break;
                case "BUNGII CUSTOMER LOGO":
                    isFound = supportPage.isElementEnabled(supportPage.Image_CustLogo());
                    break;
                case "SUPPORT QUESTION":
                    isFound = action.getValueAttribute(supportPage.Text_SupportQuestion()).equals(PropertyUtility.getMessage("customer.support.question")) ;
                    isFound2 = action.getValueAttribute(supportPage.Text_SupportLabelQuestion()).equals(PropertyUtility.getMessage("customer.support.question.label"))||action.getValueAttribute(supportPage.Text_SupportLabelQuestion()).equals(PropertyUtility.getMessage("customer.support.question.label.android")); //Times are shown as AM or a.m.
                    isFound = isFound && isFound2;
                    break;
                case "ADD IMAGE":
                    isFound = paymentPage.isElementEnabled(paymentPage.Image_Add());
                    break;
                case "ADD":
                    isFound = paymentPage.isElementEnabled(paymentPage.Button_ADD());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name",
                            "UnImplemented Step");
                    break;
            }

            testStepVerify.isTrue(isFound,
                    button + "should be present in " + screen + " screen",
                    button + " is present in " + screen + " screen", button + " is not present in " + screen + " screen");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" screen for first time promocode$")
    public void iClickButtonOnScreenforFirstTime(String button, String screen) {
        try {

            switch (button.toUpperCase()) {

                case "ADD":
                        action.click(promosPage.Button_Add());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name",
                            "UnImplemented Step");
                    break;
            }
            log("Click " + button + " button ",
                    "Clicked " + button + " button", true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" message$")
    public void i_should_see_something_message(String message) throws Throwable {
        try {

            switch (message) {
                case "No Mail Accounts":
                    String text= action.getAlertMessage().toString();
                    action.clickAlertButton("OK");
                    testStepAssert.isTrue(text.contains("No Mail Accounts"),"No Mail Accounts Popup should be displayed", text +" is displayed",text+" is not displayed");
                    break;
                case "Account deleted successfully":
                    String textMsg= action.getAlertMessage().toString();
                    action.clickAlertButton("OK");
                    testStepAssert.isTrue(textMsg.contains("Account deleted successfully"),"Account deleted successfully Popup should be displayed", textMsg +" is displayed",textMsg+" is not displayed");
                    break;
                case "Incorrect password":
                    String incorrectPassword= action.getAlertMessage().toString();
                    action.clickAlertButton("OK");
                    testStepAssert.isTrue(incorrectPassword.contains("Incorrect password"),"Incorrect password Popup should be displayed", incorrectPassword +" is displayed",incorrectPassword+" is not displayed");
                    action.click(accountPage.Button_Cancel());
                    break;
                case "Account can't be deleted due to pending deliveries":
                    String textMsg1= action.getAlertMessage().toString();
                    action.clickAlertButton("OK");
                    testStepAssert.isTrue(textMsg1.contains("You seem to have scheduled Bungii(s), please cancel any pending deliveries to proceed or contact admin in case of any issues"),"Account can't be deleted due to pending deliveries Popup should be displayed", textMsg1 +" is displayed",textMsg1+" is not displayed");
                    break;
                case "Account can't be deleted due to active deliveries":
                    String textMsg2= action.getAlertMessage().toString();
                    action.clickAlertButton("OK");
                    testStepAssert.isTrue(textMsg2.contains("You seem to have an active Bungii, please cancel any active delivery to proceed or contact admin in case of any issues."),"Account can't be deleted due to active deliveries Popup should be displayed", textMsg2 +" is displayed",textMsg2+" is not displayed");
                    break;

                case "Your duo teammate is on the way":
                    String textMessage= action.getAlertMessage().toString();
                    testStepAssert.isTrue(message.contains(textMessage),"Your duo teammate is on the way message should be shown.","Your duo teammate is on the way message is not shown instead of that following message is shown "+textMessage);
                    break;
                case "Your duo teammate has arrived at the pickup location. Please coordinate to begin loading":
                    String arrivedTextMessage= action.getAlertMessage().toString();
                    testStepAssert.isTrue(message.contains(arrivedTextMessage),"Your duo teammate has arrived at the pickup location. Please coordinate to begin loading. message should be shown.","Your duo teammate has arrived at the pickup location. Please coordinate to begin loading. message is not shown instead of that following message is shown "+arrivedTextMessage);
                    break;
                case "Admin Password Required":
                    testStepAssert.isElementDisplayed(safariPage.PopUp_AdminPassword(),"Admin Password Required pop-up should be displayed","Admin Password Required is displayed","Admin Password Required is not displayed");
                    break;
                case "Password is required.":
                    testStepAssert.isElementDisplayed(safariPage.Text_PasswordRequired(),"Admin Password Required pop-up should be displayed","Admin Password Required is displayed","Admin Password Required is not displayed");
                    break;
            }
            log("No Mail Accounts Popup should be displayed",
                    "No Mail Accounts Popup is displayed", true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I select a new time$")
    public void i_select_a_new_time() throws Throwable {
        action.click(estimatePage.Label_TimeSelect());
        action.dateTimePicker(estimatePage.DatePicker_BungiiTime, estimatePage.DateWheel_BungiiTime,1, "11", "45", "" );
        //  action.click(estimatePage.Row_TimeSelect());
        action.click(estimatePage.Button_Set());

    }

    @Then("^If time is already passed then i should see \"([^\"]*)\" message$")
    public void if_time_is_already_passed_then_i_should_see_something_message(String list1) throws Throwable {

        Thread.sleep(5000);

        if (action.isAlertPresent()) {
            testStepAssert.isEquals(action.getAlertMessage(),list1,list1 + " should be displayed",list1 + " is displayed", list1 + " is not displayed");
           action.clickAlertButton("OK");
        }

    }
    @And("^I check if the status is \"([^\"]*)\"$")
    public void i_check_if_the_status_is_something(String status) throws Throwable {
       try{
           switch (status){
               case "ONLINE":
                   testStepAssert.isTrue(action.isElementPresent(driverBungiiCompletedPage.Slider_Online()),
                           "The status should be online",
                           "The status is not online");
                break;
               case "OFFLINE":
                   testStepAssert.isTrue(action.isElementPresent(driverBungiiCompletedPage.Slider_Offline()),
                           "The status should be offline",
                           "The status is not offline");
                   break;
               case "Processing":
                   testStepAssert.isTrue(action.isElementPresent(driverBungiiCompletedPage.Text_ProcessingStatus()),
                           "The status should be processing",
                           "The status is not processing");
                   break;
           }
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }
    @And("^I check online or offline pop up is displayed$")
    public void i_check_online_or_offline_pop_up_is_displayed() throws Throwable {
        try {
            testStepAssert.isElementDisplayed(driverBungiiCompletedPage.Notification_DriverStatus(),
                    "The driver should get a pop-up to change status",
                    "The driver got a pop-up to change status",
                    "The driver did not get a pop-up to change status");

            String header = driverBungiiCompletedPage.Notification_DriverStatus().getText();
            String expectedHeader =PropertyUtility.getMessage("header.stayOnline.goOffline.notification");
            testStepAssert.isEquals(header,expectedHeader,
                    expectedHeader+" should be displayed as header",
                    expectedHeader+" is displayed as header",
                    expectedHeader+" is not displayed as header");

            String subText = driverBungiiCompletedPage.Text_NotificationDriverStatus().getText();
            String expectedSubText =PropertyUtility.getMessage("subHeader.stayOnline.goOffline.notification");
            testStepAssert.isEquals(subText,expectedSubText,
                    expectedSubText+" should be displayed as sub text",
                    expectedSubText+" is displayed as sub text",
                    expectedSubText+" is not displayed as sub text");

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I check online or offline pop up is not displayed$")
    public void i_check_online_or_offline_pop_up_is_not_displayed() throws Throwable {
       try{
           testStepAssert.isFalse(action.isElementPresent(driverBungiiCompletedPage.Notification_DriverStatus(true)),
                   "The driver should not get a pop-up to change status",
                   "The driver did not get a pop-up to change status",
                   "The driver did get a pop-up to change status");
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }
    @And("^I check the elements displayed on rate customer screen$")
    public void i_check_the_elements_displayed_on_rate_customer_screen() throws Throwable {
      try {
          testStepAssert.isElementDisplayed(driverBungiiCompletedPage.ExperienceRating(),
                  "No rating should be selected",
                  "No rating is selected",
                  "Rating is selected before driver can select");

          testStepAssert.isElementDisplayed(driverBungiiCompletedPage.Text_RateCustomer(),
                  "Header Rate customer should be displayed",
                  "Header Rate customer is displayed",
                  "Header Rate customer is not displayed");

          testStepAssert.isElementDisplayed(driverBungiiCompletedPage.Text_ChooseRating(),
                  "Choose Rating should be displayed",
                  "Choose Rating is displayed",
                  "Choose Rating is not displayed");

          testStepAssert.isElementDisplayed(driverBungiiCompletedPage.Text_DriverExperience(),
                  "Driver experience question should be displayed",
                  "Driver experience question is displayed",
                  "Driver experience question is not displayed");

          testStepAssert.isElementDisplayed(driverBungiiCompletedPage.Button_Submit(),
                  "Submit button should be displayed",
                  "Submit button is displayed",
                  "Submit button is not displayed");

          testStepAssert.isElementDisplayed(driverBungiiCompletedPage.Button_Skip_This_Step(),
                  "Skip this step button should be displayed",
                  "Skip this step button is displayed",
                  "Skip this step button is not displayed");


          log("I should be able to verify the elements present on rate customer page",
                  "I am able to verify the elements present on rate customer page",false);

      }
      catch (Exception e) {
          logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
          error("Step  Should be successful",
                  "Error performing step,Please check logs for more details", true);
      }
    }
    @And("^I add comment on rate customer page$")
    public void i_add_comment_on_rate_customer_page() throws Throwable {
        try{
            action.swipeUP();

            testStepAssert.isElementDisplayed(driverBungiiCompletedPage.Textbox_AdditionalFeedback(),
                    "Textbox for additional feedback should be displayed",
                    "Textbox for additional feedback is displayed",
                    "Textbox for additional feedback is not displayed");

            action.sendKeys(driverBungiiCompletedPage.Textbox_AdditionalFeedback(),"The customer was friendly.");

            action.click(driverBungiiCompletedPage.Textbox_Additional());

            action.click(driverBungiiCompletedPage.Button_Submit());

            log("I should be able to add a comment for customer rating","I am able to add a comment for customer rating",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I get pickupref for \"([^\"]*)\" customer$")
    public void i_get_pickuref_for_something_customer(String string1) throws Throwable {
        try{
            String custPhone = (String)cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
            //String custRef = dbUtility.getCustomerRefference(custPhone);
            String pickupRef = dbUtility.getPickupRef(custPhone);
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST2",pickupRef);
            logger.detail("Pickupref for customer "+custPhone+" is"+pickupRef);

        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in fetching the pickupref for customer ",
                    true);
        }
    }

    @And("^I click \"([^\"]*)\" button on \"([^\"]*)\" screen$")
    public void iClickButtonOnScreen(String button, String screen) {
        try {

            switch (button.toUpperCase()) {
                case "GET ESTIMATE":
                    action.click(homePage.Button_GetEstimate());
                    break;
                case "PROMO CODE LINE":
                    action.click(estimatePage.Row_PromoCode());
                    break;
                case "SAVE MONEY":
                    action.click(scheduledBungiiPage.Button_SaveMoney());
                    break;
                case "RESEND":
                    action.click(forgotPasswordPage.Button_Resend());
                    break;
                case "ADD-BUTTON":
                    action.click(paymentPage.Button_ADD());
                    break;
                case "INFO":
                    action.click(promosPage.Button_Info());
                    break;
                case "INVITE REFERRALS":
                    action.click(homePage.Button_Invite());
                    break;
                case "SHARE":
                    action.click(invitePage.Button_Share());
                    break;
                case "LOG IN":
                    if (screen.equalsIgnoreCase("log in")) {
                        action.click(loginPage.Button_Login());
                        acceptCustomerPermissions("TERMS & CONDITIONS" , "ALLOW NOTIFICATIONS" , "ALLOW LOCATION");
                        closeTutorial("Tutorial");
                      //  iAmOnCustomerLoggedInHomePage(); //Commented purposely for customers having active pickup
                       // new GeneralUtility().logCustomerDeviceToken((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA"));
                    }
                    else if (screen.equalsIgnoreCase("sign up"))
                        action.click(signupPage.Button_Login());
                    else
                        System.err.println("test");
                    // none, error
                    break;
                case "LOGIN":
                    if (screen.equalsIgnoreCase("log in")) {
                        action.click(loginPage.Button_Login());
                        acceptCustomerPermissions("TERMS & CONDITIONS" , "ALLOW NOTIFICATIONS" , "ALLOW LOCATION");
                        closeTutorial("Tutorial");
                        iAmOnCustomerLoggedInHomePage();
                        // new GeneralUtility().logCustomerDeviceToken((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA"));
                    }
                    else if (screen.equalsIgnoreCase("sign up"))
                        action.click(signupPage.Button_Login());
                    else
                        System.err.println("test");
                    // none, error
                    break;
                case "SIGN UP":
                    if (screen.equalsIgnoreCase("SIGN UP")) {
                        action.hideKeyboard();
                        action.swipeUP();
                        //action.click(signupPage.Textfield_Phonenumber()); //added to address swipe
                        action.swipeUP();
                        action.click(signupPage.Button_Signup());
                    } else
                        action.click(loginPage.Button_SignUp());
                    break;
                case "DONE":
                    if (screen.equalsIgnoreCase("invite"))
                        action.click(invitePage.Button_Done());
                    else {
                        action.swipeUP();
                        if (!action.isElementPresent(successPage.Button_Done(true)))
                            action.swipeUP();
                        action.click(successPage.Button_Done());
                    }
                    break;
                case "ON TO THE NEXT ONE":
                    //sometime earning popup comes late
                    if (action.isAlertPresent()) {
                        logger.detail("Alert message" + action.getAlertMessage());
                        ;
                        SetupManager.getDriver().switchTo().alert().dismiss();
                        Thread.sleep(1000);
                    }
                    action.click(driverBungiiCompletedPage.Button_Next_Bungii());
                    break;
                case "SUBMIT":
                    Thread.sleep(1000);
                    action.click(driverBungiiCompletedPage.Button_Submit());
                    break;
                case "SKIP THIS STEP":
                    Thread.sleep(1000);
                    action.click(driverBungiiCompletedPage.Button_Skip_This_Step());
                    break;
                case "I DON'T LIKE FREE MONEY":
                    takeActionOnPromotion("REJECT");
                    break;
                case "YES, I'LL TAKE $5":
                    takeActionOnPromotion("ACCEPT");
                    break;
                case "PICK UP CLEAR TEXT":
                    action.click(homePage.Button_ClearPickup());
                    break;
                case "PICK UP CLEAR BUTTON":
                    action.clickMiddlePoint(homePage.Button_ClearPickup());
                    break;
                case "DROP CLEAR TEXT":
                    action.click(homePage.Button_ClearDrop());
                    break;
                case "CANCEL":
                    if (screen.equalsIgnoreCase("payment"))
                        action.click(paymentPage.Button_Cancel());
                    else if (screen.equalsIgnoreCase("update")) {
                        action.click(driverUpdateStatusPage.Button_MoreOptions());
                        Thread.sleep(1000);
                        action.click(driverUpdateStatusPage.Button_Cancel());
                    }
                    else if(screen.equalsIgnoreCase("Delete Account"))
                        action.click(accountPage.Button_Cancel());
                    else
                        action.click(estimatePage.Button_Cancel());
                    break;
                case "SEND":
                    if (screen.equalsIgnoreCase("forget password"))
                        action.click(forgotPasswordPage.Button_Send());
                    else
                        action.click(supportPage.Button_Send());
                    break;
                case "SMS":
                    if (screen.equalsIgnoreCase("customer-Update"))
                        action.click(customerUpdateStatusPage.Button_Sms());
                    else
                        action.click(driverUpdateStatusPage.Button_Sms());
                    break;
                case "CALL":
                    if (screen.equalsIgnoreCase("customer-Update"))
                        action.click(customerUpdateStatusPage.Button_Call());
                    else
                        action.click(driverUpdateStatusPage.Button_Call());
                    break;
                case "ADD":
                    if (screen.equalsIgnoreCase("Estimate"))
                        action.click(estimatePage.Button_AddPromoCode());
                    else {
                        action.click(promosPage.Button_Add());
                        if(action.isAlertPresent()) {
                            String alertText = SetupManager.getDriver().switchTo().alert().getText();
                            if(alertText==PropertyUtility.getMessage("customer.select.other.than.first.time.code")) {
                                warning("Alert Displayed Incase First TIme promocode is present", "Alert Received: "+ alertText );
                                SetupManager.getDriver().switchTo().alert().accept();
                            }
                        }
                    }
                    break;
                case "GET MORE MONEY":
                    action.click(promosPage.Button_GetMoreMoney());
                    break;
                case "ADD NEW":
                    action.click(paymentPage.Button_AddNew());
                    break;
                case "ADD PAYMENT METHOD":
                    action.click(paymentPage.Button_AddPayment());
                    break;
                case "SAVE":
                    action.click(paymentPage.Button_Save());
                    break;
                case "FORGOT PASSWORD":
                    action.click(loginPage.Button_ForgotPassword());
                    break;
                case "CONTINUE":
                    action.nextFieldKeyboard();
                    action.click(forgotPasswordPage.Button_Continue());
                    break;
                case "BACK":
                    action.click(forgotPasswordPage.Button_Back());
                    break;
                case "REQUEST BUNGII":
                    action.click(estimatePage.Button_RequestBungii());
                    break;
                case "OK":
                    if (screen.equalsIgnoreCase("BUNGII ACCEPTED")) {
                        //bungiiAcceptedPage.clickOkButton();
                        if (action.getScreenHeader(homePage.Text_NavigationBar()).equals("BUNGII ACCEPTED") && action.isElementPresent(bungiiAcceptedPage.Button_Ok(true)))
                            bungiiAcceptedPage.Button_Ok().click();
                        if (screen.equalsIgnoreCase("ENROUTE")) {
                            //Do nothing as  BUNGII ACCEPTED screen is not displayed and directly enrotue screen is displayed
                        }
                    }
                    else
                        action.click(driverNotAvailablePage.Button_OK());
                    break;
                case "ACCEPT":
                    action.click(bungiiRequestPage.Button_Accept());
                    break;
                case "REJECT":
                    action.click(bungiiRequestPage.Button_Reject());
                    break;
                case "REQUEST YOUR CITY":
                    action.click(homePage.Text_OutOfOffice_RequestCity());
                    Thread.sleep(5000);
                    break;
                case "SHARE ON FACEBOOK":
                case "SHARE ON TWITTER":
                case "SHARE BY EMAIL":
                case "SHARE BY TEXT MESSAGE":
                    Thread.sleep(5000);
                    shareInviteCode(button);
                    break;
                case "CLOSE BUTTON":
                    action.click(customerBungiiCompletePage.Button_Close());
                    ;
                    break;
                case "TOP BACK":
                    action.click(bungiiDetails.Button_Back());
                    break;
                case "SCHEDULE BUNGII":
                    action.click(estimatePage.Button_ScheduleBungii());
                    break;
                case "DELETE ACCOUNT":
                    action.click(accountPage.Button_DeleteAccount());
                    break;
                case "SCHEDULED BUNGIIS":
                    if (screen.equalsIgnoreCase("update")) {
                        action.click(driverUpdateStatusPage.Button_MoreOptions());
                        Thread.sleep(1000);
                        action.click(driverUpdateStatusPage.Button_ScheduledBungiis());
                    }
                    break;
                case "TAKE PHOTO":
                    action.click(driverUpdateStatusPage.Button_TakePhoto());
                    break;
                case "MORE OPTIONS":
                    action.click(driverUpdateStatusPage.Button_MoreOptions());
                    break;
                case "CANCEL DELIVERY":
                    action.click(driverUpdateStatusPage.Tab_CancelDelivery());
                    break;
                case "CUSTOMER SIGNATURE":
                    action.click(driverUpdateStatusPage.Tab_CustomerSignature());
                    break;
                case "SUBMIT DATA":
                    action.click(driverUpdateStatusPage.Button_Submit());
                    break;
                case "AVAILABLE BUNGII ICON":
                    action.click(driverUpdateStatusPage.Icon_AvailableBungii());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name",
                            "UnImplemented Step");
                    break;
            }
            log("Click " + button + " button ",
                    "Clicked " + button + " button", true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error in clicking button " +button + " on "+screen+" screen", true);
        }
    }

    @And("^I enter \"([^\"]*)\" password and click on delete button$")
    public void i_enter_something_password_and_click_on_delete_button(String password) throws Throwable {
        try{
            if(password.equalsIgnoreCase("valid")) {
                action.clearSendKeys(accountPage.Text_AccountPassword(), PropertyUtility.getDataProperties("customer.password"));
            }
            else if(password.equalsIgnoreCase("invalid")){
                action.clearSendKeys(accountPage.Text_AccountPassword(), PropertyUtility.getDataProperties("customer.password.invalid"));
            }
            action.click(accountPage.Button_Delete());
        }catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error for customer account deletion", true);
        }
    }

    @And("^I confirm that Delete button is disable$")
    public void i_confirm_that_delete_button_is_disable() throws Throwable {
        try{
            //action.click(accountPage.Button_Delete());
            testStepAssert.isElementNotEnabled(accountPage.Button_Delete(),"Delete button should be disable","Delete button is disabled.","Delete button is enable.");

        }catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Delete button is not disable", true);
        }
    }

    /**
     * Accept or reject on promotion page
     *
     * @param actions Take action on promotion page
     * @return if action is taken or nots
     */
    public boolean takeActionOnPromotion(String actions) {
        boolean isClicked = false;
        switch (actions.toUpperCase()) {
            case "ACCEPT":
                promotionPage.Button_AcceptPromo().click();
                isClicked = true;
                break;
            case "REJECT":
                promotionPage.Button_IdontLikePromo().click();
                isClicked = true;
                break;
            default:
                break;
        }

        return isClicked;
    }

    /**
     * Click on Invite code button for social media platform
     *
     * @param target Identifer for share option
     */
    public void shareInviteCode(String target) {
        if (target.contains("FACEBOOK")) {
            action.click(invitePage.Button_Facebook());
        } else if (target.contains("TWITTER")) {
            action.click(invitePage.Button_Twitter());
        } else if (target.contains("EMAIL")) {
            action.click(invitePage.Button_Email());
        } else if (target.contains("TEXT")) {
            action.click(invitePage.Button_TextMessage());
        }
    }

    @Then("^I should be navigated to \"([^\"]*)\" screen$")
    public void i_should_be_naviagated_to_something_screen(String screen) {
        try {
            boolean isCorrectPage = false;

            GeneralUtility utility = new GeneralUtility();


            if (screen.equalsIgnoreCase("Home")) {
                screen = "BUNGII";
                Thread.sleep(5000);
                isCorrectPage = utility.verifyPageHeader(screen);
            }
            else if (screen.equalsIgnoreCase("Driver Home")) {
                Thread.sleep(5000);
                isCorrectPage = utility.verifyPageHeader(screen);
            }
            else if (screen.equalsIgnoreCase("Set pickup time")) {
                Thread.sleep(5000);
                isCorrectPage = utility.verifyPageHeader(screen);
            }
            else {
                Thread.sleep(5000);
                isCorrectPage = utility.verifyPageHeader(screen);
            }
            testStepAssert.isTrue(isCorrectPage, "I should be navigated to " + screen + " screen",
                    "I should be navigated to " + screen, "Error in navigating to " + screen + " screen ");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error in navigating to " + screen + " screen ", true);
        }
    }

    @And("^I tap \"([^\"]*)\" button on DRIVER NOT AVAILABLE screen$")
    public void i_tap_something_button_on_driver_not_available_screen(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Schedule Bungii":
                    action.click(invitePage.Button_ScheduleBungii());
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Given("^I have \"([^\"]*)\" app \"([^\"]*)\"$")
    public void i_have_something_app_something(String appName, String expectedOutcome) throws Throwable {
        try {
            boolean isAppInstalled = false;
            switch (appName.toUpperCase()) {
                case "TWITTER":
                    isAppInstalled = ((IOSDriver) (SetupManager.getDriver())).isAppInstalled(PropertyUtility.getDataProperties("twitter.bundle.ios.id"));
                    break;
                case "FACEBOOK":
                    isAppInstalled = ((IOSDriver) (SetupManager.getDriver())).isAppInstalled(PropertyUtility.getDataProperties("facebook.bundle.ios.id"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            switch (expectedOutcome.toUpperCase()) {
                case "INSTALLED":
                    testStepAssert.isTrue(isAppInstalled, appName + " should be present on device", "Warning : Test cannot continue as " + appName + " app is not present on device");
                    break;
                case "NOT INSTALLED":
                    testStepAssert.isFalse(isAppInstalled, appName + " should not be present on device", "Warning : Test cannot continue as " +appName + " app is present on device");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I login as \"([^\"]*)\" customer and on Home page$")
    public void i_login_as_something_customer_and_on_home_page(String key) throws Throwable {
        i_am_on_the_something_page("LOG IN");
        i_logged_in_customer_application_using_something_user(key);
        acceptCustomerPermissions("TERMS & CONDITIONS" , "ALLOW NOTIFICATIONS" , "ALLOW LOCATION");
        closeTutorial("Tutorial");
        iAmOnCustomerLoggedInHomePage();
    }
    public void acceptCustomerPermissions(String terms, String notification, String location) {
        try {
            GeneralUtility utility = new GeneralUtility();
            Thread.sleep(3000);
            String pageHeader = utility.getPageHeader();

            if(action.isElementPresent(termsAndConditionPage.Button_CheckOff())) {
                action.click(termsAndConditionPage.Button_CheckOff());
                action.click(termsAndConditionPage.Button_Continue());
                Thread.sleep(3000);
                pageHeader = utility.getPageHeader();
                // pageHeader = utility.getPageHeader();
            }
            if(action.isElementPresent(enableNotificationPage.Button_Sure())) {
                action.click(enableNotificationPage.Button_Sure());
                Thread.sleep(3000);
                action.clickAlertButton("Allow");
                Thread.sleep(3000);
                pageHeader = utility.getPageHeader();
                // pageHeader = utility.getPageHeader();
            }
            if(action.isElementPresent(enableLocationPage.Button_Sure())) {
                action.click(enableLocationPage.Button_Sure());
                Thread.sleep(3000);
                action.waitForAlert();
                action.clickAlertButton("Allow While Using App");  //Customer App alert for ios 16
                Thread.sleep(3000);
                pageHeader = utility.getPageHeader();
                // pageHeader = utility.getPageHeader();
            }

        } catch (Exception e) {
        }
    }

    public void closeTutorial(String Tutorial) throws Throwable {
        try {
            if(action.isElementPresent(tutorialPage.Button_Close(true))) {
                //action.swipeLeft(tutorialPage.Image_Generictutorialstep());
                //action.swipeLeft(tutorialPage.Image_Generictutorialstep());
               // action.swipeLeft(tutorialPage.Image_Generictutorialstep());
                //action.swipeLeft(tutorialPage.Image_Generictutorialstep());
               // action.click(tutorialPage.Button_Start());
                action.click(tutorialPage.Button_Close());
                if (action.isAlertPresent()) {
                    String alertMessage = action.getAlertMessage();
                    List<String> getListOfAlertButton = action.getListOfAlertButton();
                    if (alertMessage.contains("we are not operating in your area")) {
                        if (getListOfAlertButton.contains("Done")) {
                            action.clickAlertButton("Done");
                        }
                    }
                }
            }

        } catch (Exception e) {
        }

    }

    @Then("^I see \"([^\"]*)\" screen$")
    public void i_see_something_screen(String screen) throws Throwable {
        try {
            boolean isCorrectPage = false;
            switch (screen) {
                case "Rate customer":
                    Thread.sleep(5000);
                    //String abc = bungiiCompletePage.Text_RateCustomer().getText();
                    isCorrectPage = action.getScreenHeader(driverBungiiCompletedPage.Text_RateCustomer()).equals(screen);
                    testStepAssert.isTrue(isCorrectPage, "I should be naviagated to " + screen + " screen",
                            "I should be navigated to " + screen, "Error in navigating to " + screen + " screen ");
                    break;
                default:
                    logger.detail("Lands on wrong screen");
                    break;
            }
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }

    }

    @And("^I select \"([^\"]*)\" customer rating$")
    public void i_select_something_customer_rating(String ratingStar) throws Throwable {
        try {
            int i = 0;
            switch (ratingStar) {
                case "1":
                    i = 1;
                    action.click(driverBungiiCompletedPage.StarRatings(i));
                    break;
                case "2":
                    i = 2;
                    action.click(driverBungiiCompletedPage.StarRatings(i));
                    break;
                case "3":
                    i = 3;
                    action.click(driverBungiiCompletedPage.StarRatings(i));
                    break;
                case "4":
                    i = 4;
                    action.click(driverBungiiCompletedPage.StarRatings(i));
                    break;
                case "5":
                    i = 5;
                    action.click(driverBungiiCompletedPage.StarRatings(i));
                    break;
                default:
                    logger.detail("Not selected any start rating");
            }
        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
    }
    @Given("^I am on the \"([^\"]*)\" page$")
    public void i_am_on_the_something_page(String screen) {
        try {

            if (action.isAlertPresent()) {
               // if (action.getAlertMessage().equalsIgnoreCase(PropertyUtility.getMessage("customer.alert.delay.scheduled"))) {
                    warning("I see location popup", "I accepted location popup", true);
                    SetupManager.getDriver().switchTo().alert().accept();

              //  }
            }

            String NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());
            switch (screen.toUpperCase()) {
                case "LOG IN":
                    goToLogInPage(NavigationBarName);
                    break;
                case "SIGN UP":
                    goToSignUpPage(NavigationBarName);
                    break;
                case "HOME":
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
    }

    public void goToLogInPage(String navigationBarName) throws Throwable {
        HomeSteps homeSteps = new HomeSteps(homePage);
        if (action.isAlertPresent()) {
            String alertMessage = action.getAlertMessage();
            logger.detail("Alert is present on screen, Alert message:" + alertMessage);
            List<String> getListOfAlertButton = action.getListOfAlertButton();
            if (getListOfAlertButton.contains("Done"))
                action.clickAlertButton("Done");
            else if(alertMessage.contains("Unable to find network connection"))
                action.clickAlertButton("OK");

        }
        if (!navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))) {

            if (navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.signup"))) {
                iClickButtonOnScreen("LOG IN", "sign up");
            } else if (navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
                iClickButtonOnScreen("CANCEL", "SEARCHING");
                iAcceptAlertMessage();
                homeSteps.i_selectlogout();

            } else if (navigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                new GeneralUtility().navigateFromTermToHomeScreen();
                homeSteps.i_selectlogout();
            } else if (navigationBarName.equalsIgnoreCase("NOTIFICATIONS")) {
                action.click(enableNotificationPage.Button_Sure());
                action.clickAlertButton("Allow");
                if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
                    action.click(enableLocationPage.Button_Sure());
                    action.clickAlertButton("Always Allow");
                }

                homeSteps.i_selectlogout();
            /*}else if (navigationBarName.equalsIgnoreCase("LOCATION")) {
                action.click(enableLocationPage.Button_Sure());
                action.clickAlertButton("Allow");
                if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
                    action.click(enableLocationPage.Button_Sure());
                    action.clickAlertButton("Allow");
                }
                homeSteps.i_selectlogout(); */
            } else if (navigationBarName.equals("WANT $5?")) {
                takeActionOnPromotion("REJECT");
                homeSteps.i_selectlogout();
            }
            else {
                homeSteps.i_selectlogout();

            }
            log("I should be on LOG IN page",
                    "I am on LOG IN page", true);
        }
    }

    public void goToSignUpPage(String navigationBarName) throws Throwable {
        HomeSteps homeSteps = new HomeSteps(homePage);


        if (!navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.signup"))) {

            if (navigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login")))
                iClickButtonOnScreen("SIGN UP", "sign up");
            else {
                homeSteps.i_select_something_from_customer_app_menu("LOGOUT");
                Thread.sleep(10000);
                iClickButtonOnScreen("SIGN UP", "sign up");

            }
        }
    }
    private List<String> getDriverCredentials(String user) throws Throwable
    {
        List<String> credentials =  new ArrayList<String>();

        String phone, password;
        boolean shouldLoginSucessful;
        switch (user.toLowerCase()) {
            case "valid":
                phone = PropertyUtility.getDataProperties("ios.valid.driver.phone");
                password = PropertyUtility.getDataProperties("ios.valid.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case"valid driver 2":
                SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
                phone = PropertyUtility.getDataProperties("ios.valid.driver2.phone");
                password = PropertyUtility.getDataProperties("ios.valid.driver2.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_2", PropertyUtility.getDataProperties("ios.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_2_PHONE", phone);
                break;
            case"valid duo driver 1":
                phone = PropertyUtility.getDataProperties("ios.valid.driver.duo.phone");
                password = PropertyUtility.getDataProperties("ios.valid.driver.duo.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("ios.driver.duo.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid miami":
                phone = PropertyUtility.getDataProperties("miami.driver.phone");
                password = PropertyUtility.getDataProperties("miami.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("miami.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid nashville":
                phone = PropertyUtility.getDataProperties("nashville.driver.phone");
                password = PropertyUtility.getDataProperties("nashville.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("nashville.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid nashville1":
                phone = PropertyUtility.getDataProperties("nashville.driver1.phone");
                password = PropertyUtility.getDataProperties("nashville.driver1.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("nashville.driver1.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid nashville2":
                phone = PropertyUtility.getDataProperties("nashville.driver2.phone");
                password = PropertyUtility.getDataProperties("nashville.driver2.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("nashville.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid denver":
                phone = PropertyUtility.getDataProperties("denver.driver.phone");
                password = PropertyUtility.getDataProperties("denver.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid denver driver 2":
                phone = PropertyUtility.getDataProperties("denver.driver2.phone");
                password = PropertyUtility.getDataProperties("denver.driver2.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver2.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "new driver":
                phone = PropertyUtility.getDataProperties("new.driver.phone");
                password = PropertyUtility.getDataProperties("new.driver.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("new.driver.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid partner kansas driver":
                phone = PropertyUtility.getDataProperties("valid.driver.kansas.phone");
                password = PropertyUtility.getDataProperties("partner.kansas.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("valid.driver.kansas.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            case "valid denver driver 3":
                phone = PropertyUtility.getDataProperties("denver.driver3.phone");
                password = PropertyUtility.getDataProperties("denver.driver3.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("denver.driver3.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;

            case "valid partner kansas driver2":
                phone = PropertyUtility.getDataProperties("Kansas.driver48.phone");
                password = PropertyUtility.getDataProperties("partner.kansas.driver.password");
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Kansas.driver48.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;

            case "new washington":
                phone = PropertyUtility.getDataProperties("Washington.driver37.phone");
                password = PropertyUtility.getDataProperties("Washington.driver11.password");
                shouldLoginSucessful = true;
                cucumberContextManager.setScenarioContext("DRIVER_1", PropertyUtility.getDataProperties("Washington.driver37.name"));
                cucumberContextManager.setScenarioContext("DRIVER_1_PHONE", phone);
                break;
            default:
                throw new Exception("Please specify valid input");
        }
        credentials.add(phone);
        credentials.add(password);
        logger.detail("Driver Credentials : " + credentials.get(0) +" / "+ credentials.get(1));
        return credentials;
    }

    @And("I mark the driver {string}")
    public void iMarkTheDriver(String driverStatus) {
        try{
            switch (driverStatus){
                case "online":
                        goOnline();
                    break;
                case "offline":
                    goOffline();
                    break;
            }
            pass("The driver should be able to mark "+driverStatus,"The driver is marked "+driverStatus,false);
        }catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I login as \"([^\"]*)\" driver on \"([^\"]*)\" device and make driver status as \"([^\"]*)\"$")
    public void i_login_as_something_driver_on_something_device_and_make_driver_status_something_as(String user, String device, String driverStatus) throws Throwable {
        try {
            String navigationBarName = "";
            //utility.switchToApp("driver",device);
            int retry =2;
            while(retry>0) {
                if (action.isElementPresent(driverHomePage.Text_NavigationBar(true)))
                 navigationBarName = action.getScreenHeader(driverHomePage.Text_NavigationBar(true));
                else
                    Thread.sleep(5000);
                retry--;
            }
            goToDriverLogInPage(navigationBarName);

            List<String> credentials =  getDriverCredentials(user);
            utility.loginToDriverApp(credentials.get(0), credentials.get(1));
            Thread.sleep(5000);

            acceptDriverPermissions("ALLOW NOTIFICATIONS" , "ALLOW LOCATION");
            // navigationBarName =  action.getScreenHeader(driverHomePage.NavigationBar_Text());
            // new GeneralUtility().logDriverDeviceToken(credentials.get(0));
                    switch (driverStatus.toUpperCase()) {
                        case "ONLINE":
                            goOnline();
                             break;
                        case "OFFLINE":
                            goOffline();
                             break;
            }

        log("I log in as driver "+user+" and make driver status as "+ driverStatus," I am loggedin as driver using ["+credentials.get(0)+" / "+credentials.get(1)+"] and make driver status as "+ driverStatus,true);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error( "Step should be successful", "Error in login as driver and updating driver status", true);
    }
    }
    public void acceptDriverPermissions(String Notification, String Location) throws Throwable {
        try {
            GeneralUtility utility = new GeneralUtility();
            //String pageName = utility.getPageHeader();
            if(action.isElementPresent(enableNotificationPage.Icon_Notification())){
                if(action.isElementPresent(enableNotificationPage.Button_Sure())){
                    action.click(enableNotificationPage.Button_Sure());
                }
            }
            action.waitForAlert();
            if(action.isAlertPresent()) {
                String alertMessage = action.getAlertMessage();
                logger.detail("Alert is present on screen,Alert message:" + alertMessage);
                List<String> getListOfAlertButton = action.getListOfAlertButton();
                if (alertMessage.contains(PropertyUtility.getDataProperties("driver.notification.alert"))) {
                    if (getListOfAlertButton.contains("Allow")) {
                        action.clickAlertButton("Allow");
                    }
                    Thread.sleep(3000);
                }
                else if(alertMessage.contains(PropertyUtility.getDataProperties("driver.location.alert"))){
                    if (getListOfAlertButton.contains("Allow While Using App")) {
                        action.clickAlertButton("Allow While Using App");
                        Thread.sleep(3000);
                    }
                }
                else if(alertMessage.contains(PropertyUtility.getDataProperties("driver.always.allow.location.alert"))){
                    if (getListOfAlertButton.contains("Change to Always Allow")) {
                        action.clickAlertButton("Change to Always Allow");
                        Thread.sleep(3000);
                        if(action.isElementPresent(enableLocationPage.Button_Done())) {
                            action.click(enableLocationPage.Button_Done());
                        }
                    }
                }
                Thread.sleep(3000);
                if(!action.isAlertPresent()){
                    if(action.isElementPresent(enableLocationPage.Button_Sure())) {
                        action.click(enableLocationPage.Button_Sure());
                    }
                }
                Thread.sleep(3000);
                if(action.isAlertPresent())
                {
                    if (alertMessage.contains(PropertyUtility.getDataProperties("driver.location.alert"))) {
                        if (getListOfAlertButton.contains("Allow While Using App")) {
                            action.clickAlertButton("Allow While Using App");
                            Thread.sleep(3000);
                        }
                    }
                    Thread.sleep(3000);
                    if (alertMessage.contains(PropertyUtility.getDataProperties("driver.always.allow.location.alert"))) {
                        if (getListOfAlertButton.contains("Change to Always Allow")) {
                            action.clickAlertButton("Change to Always Allow");
                            if(action.isElementPresent(enableLocationPage.Button_Done())) {
                                action.click(enableLocationPage.Button_Done());
                                Thread.sleep(3000);
                            }
                        }
                    }
                }
                if(!action.isAlertPresent()){
                    if(action.isElementPresent(enableLocationPage.Button_Done())) {
                        action.click(enableLocationPage.Button_Done());
                    }
                }
            }

        } catch (Exception e) {

        }

    }
    /**
     * driver goes online
     */
    public void goOnline() {
        String navigationHeaderName = "";
        try{
         navigationHeaderName = action.getScreenHeader(driverhomepage.NavigationBar_Status(true));
        } catch (Exception e) {

        }
        if (navigationHeaderName.equals("ONLINE"))
            logger.warning("driver Status is already Online");
        else if (navigationHeaderName.equals("OFFLINE") || navigationHeaderName.equals("")) {
            action.click(driverhomepage.Button_GoOnline());
        } else if (action.isElementPresent(driverhomepage.Button_GoOnline(true)))
            action.click(driverhomepage.Button_GoOnline());
        else
            logger.error("Not able to get driver status");
    }

    /**
     * driver goes offline
     */
    public void goOffline() {
        String navigationHeaderName = "";
        try{
            navigationHeaderName = action.getScreenHeader(driverhomepage.NavigationBar_Status(true));
        } catch (Exception e) {

        }
        if (navigationHeaderName.equals("OFFLINE")) {
            logger.warning("driver Status is already offline");
        } else if (navigationHeaderName.equals("ONLINE") || navigationHeaderName.equals("")) {
            action.click(driverhomepage.Button_GoOffline());
        } else if (action.isElementPresent(driverhomepage.Button_GoOffline(true)))
            action.click(driverhomepage.Button_GoOffline());
        else if (action.isElementPresent(driverhomepage.Button_GoOnline(true)))
            logger.warning("driver Status is already offline");
        else
            logger.error("Not able to get driver status");
    }

    public void goToDriverLogInPage(String navigationBarName) throws Throwable {
        HomePageSteps homeSteps = new HomePageSteps(driverHomePage);
        if (action.isAlertPresent()) {
            String alertMessage = action.getAlertMessage();
            logger.detail("Alert is present on screen, Alert message:" + alertMessage);
            List<String> getListOfAlertButton = action.getListOfAlertButton();
            if (getListOfAlertButton.contains("Done"))
                action.clickAlertButton("Done");

        }
        if(navigationBarName.equalsIgnoreCase("Bungii Completed")){
            action.click(driverBungiiCompletedPage.Button_Next_Bungii());
            //homeSteps.i_select_something_from_driver_app_memu("LOGOUT");
        }

        if (!navigationBarName.equals(PropertyUtility.getMessage("driver.navigation.login"))) {
            if (navigationBarName.equals("LOCATION"))
            {
                action.click(enableLocationPage.Button_Sure());
                action.clickAlertButton("Allow While Using App");
                Thread.sleep(3000);
                if(action.isAlertPresent()){
                    action.clickAlertButton("Change to Always Allow");
                    if(action.isElementPresent(enableLocationPage.Button_Done())) {
                        action.click(enableLocationPage.Button_Done());
                    }
                }
            }
            else{
                if (action.isAlertPresent()) {
                    String alertMessage = action.getAlertMessage();
                    logger.detail("Alert is present on screen, Alert message:" + alertMessage);
                    List<String> getListOfAlertButton = action.getListOfAlertButton();
                    if (getListOfAlertButton.contains("Allow While Using App")){
                        action.clickAlertButton("Allow While Using App");
                    }
                    Thread.sleep(3000);
                    if(action.isAlertPresent()){
                        action.clickAlertButton("Change to Always Allow");
                        if(action.isElementPresent(enableLocationPage.Button_Done())) {
                            action.click(enableLocationPage.Button_Done());
                        }
                    }
                }
            }
            if (!navigationBarName.equals("SIGN UP"))
            homeSteps.i_select_something_from_driver_app_memu("LOGOUT");
            else if (navigationBarName.equals("SIGN UP"))
                action.click(signupPage.Button_Login());
        }


    }


    @When("^I Switch to \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_switch_to_something_application_on_something_devices(String appName, String device) {
        try {
            logger.detail ("*** Switching to : " + appName + " application ****");
            String appHeader = "";
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(1000);
                logger.detail ("Switched To : " + device + " device");
            }
            //Vishal[20092019]: added terminate before switching the app, works faster
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    //action.switchApplication(PropertyUtility.getProp("bundleId_Driver"));
                   ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                    Thread.sleep(5000);
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    //appHeader = "Bungii Driver QAAuto";
                    appHeader = PropertyUtility.getDataProperties("driver.app.name");
                    break;
                case "CUSTOMER":
                    ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                    Thread.sleep(5000);
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    //appHeader = "Bungii QAAuto";
                    appHeader = PropertyUtility.getDataProperties("customer.app.name");
                    //action.switchApplication(PropertyUtility.getProp("bundleId_Customer"));
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }        //temp fixed
            new GeneralUtility().handleIosUpdateMessage();
            new GeneralUtility().handleAppleIDVerification();
            WebElement element = homePage.Application_Name(true);
            if(element != null){
            if (!action.getAppName(element).equals(appHeader)) {
                logger.detail("Retrying to start app 2nd time ");//:Page source:", SetupManager.getDriver().getPageSource());
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                        break;
                    case "CUSTOMER":
                        ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                        break;
                }
            }
            }
            else
            {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                        break;
                    case "CUSTOMER":
                        ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                        break;
                }

            }
            new GeneralUtility().handleIosUpdateMessage();
            new GeneralUtility().handleAppleIDVerification();
            if (!action.getScreenHeader(homePage.Application_Name()).equals(appHeader)) {
                logger.detail("Retrying to start app 3rd time ");//:Page source:", SetupManager.getDriver().getPageSource());

                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                        break;
                    case "CUSTOMER":
                        ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                        break;
                }
            }
            pass("Switch to : " + appName + " application on device instance",
                    "Switched to : " + appName + " application on device instance", true);
            cucumberContextManager.setFeatureContextContext("CURRENT_APPLICATION", appName.toUpperCase());
        } catch (Throwable e) {
            logger.error("Error in switching to app "+ appName, ExceptionUtils.getStackTrace(e));
          //  logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step should be successful",
                    "Error in switching to app "+ appName, true);

        }
    }

    //This is same as above method except apps are not reminated before starting
    @When("^I open \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_open_something_application_on_something_devices(String appName, String device) throws Throwable {
        try {
            String appHeader = "";
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(1000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                    //appHeader = "Bungii Driver QAAuto";
                    appHeader = PropertyUtility.getDataProperties("driver.app.name");
                    break;
                case "CUSTOMER":
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    //appHeader = "Bungii QAAuto";
                    appHeader = PropertyUtility.getDataProperties("customer.app.name");
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }        //temp fixed
            new GeneralUtility().handleIosUpdateMessage();
            if (!action.getScreenHeader(homePage.Application_Name()).equals(appHeader)) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                        break;
                    case "CUSTOMER":
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                        break;
                }

            }
            pass("Open " + appName + " application on " + device + " devices",
                    "Open " + appName + " application" + device + " devices", true);
            cucumberContextManager.setFeatureContextContext("CURRENT_APPLICATION", appName.toUpperCase());

        } catch (Throwable e) {
            logger.error("Error in Opening " + appName + " application" + device + " devices", ExceptionUtils.getStackTrace(e));
            //logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful",
                    "Error in Opening " + appName + " application" + device + " devices", true);

        }
    }

    // TODO change catch to error
    @Then("^Alert message with (.+) text should be displayed$")
    public void alert_message_with_text_should_be_displayed(String message) throws Exception {
        String actualMessage = "";
        try {
            Thread.sleep(4000);
            action.waitForAlert();
             actualMessage = action.getAlertMessage();
            if(actualMessage.equalsIgnoreCase("")){Thread.sleep(30000);actualMessage = action.getAlertMessage();}
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Alert not displayed", true);
        }
            String expectedMessage;
            switch (message.toUpperCase()) {
                case "INVALID_PASSWORD":
                    expectedMessage = PropertyUtility.getMessage("customer.error.invalidpassword");
                    break;
                case "EMPTY_FIELD":
                    expectedMessage = PropertyUtility.getMessage("customer.error.emptyfield");
                    break;
                case "DELETE WARNING":
                    expectedMessage = PropertyUtility.getMessage("customer.payment.delete");
                    break;
                case "NO PROMO CODE":
                    expectedMessage = PropertyUtility.getMessage("customer.signup.nopromo");
                    break;
                case "CARD IS ASSOCIATED TO TRIP":
                    expectedMessage = PropertyUtility.getMessage("customer.payment.associated.to.trip");
                    break;
                case "SCHEDULE BUNGII OPTION":
                    expectedMessage = PropertyUtility.getMessage("customer.driver.unavailable.schedule");
                    break;
                case "ACCEPT BUNGII QUESTION":
                    expectedMessage = PropertyUtility.getMessage("driver.bungii.request.ondemand.question");
                    break;
                case "DRIVER CANCELLED":
                    expectedMessage = PropertyUtility.getMessage("customer.alert.driver.cancel");
                    break;
                case "DRIVER CANCEL BUNGII":
                    expectedMessage = PropertyUtility.getMessage("driver.cancel.bungii");
                    break;
                case "STACK TRIP REQUEST AVAILABLE":
                    expectedMessage = PropertyUtility.getMessage("driver.alert.stack.alert.message.ios");
                    break;
                case "STACK TRIP REQUEST ACCEPTED":
                    expectedMessage = PropertyUtility.getMessage("driver.alert.stack.after.current");
                    break;
                case "TRIP CANNOT BE CANCELED AS CONTROL DRIVER NOT STARTED":
                    expectedMessage = PropertyUtility.getMessage("driver.alert.noncontrol.cancel.before.control");
                    break;
                case "ACCEPT SCHEDULED BUNGII QUESTION":
                    expectedMessage = PropertyUtility.getMessage("driver.bungii.request.scheduled.question");
                    break;
                case "CUSTOMER CANCELLED SCHEDULED BUNGII":
                    expectedMessage = PropertyUtility.getMessage("driver.bungii.customer.scheduled.cancel");
                    break;
                case "OTHER DRIVER CANCELLED BUNGII":
                    expectedMessage = PropertyUtility.getMessage("driver.other.driver.bungii.cancel");
                    break;
                case "INACTIVE PROMO CODE MESSAGE":
                    expectedMessage=PropertyUtility.getMessage("customer.signup.inactivepromo.android");
                    break;
                default:
                    throw new Exception("UNIMPLEMENTED STEP");
            }
            testStepAssert.isEquals(actualMessage, expectedMessage,
                    "Alert with text" + expectedMessage + "should be displayed",
                    "Alert with text ," + expectedMessage + " is displayed",
                    "Actual Message is displayed " + actualMessage + " instead of  "
                            + expectedMessage);

    }

    @Then("^Alert should have \"([^\"]*)\" button$")
    public void alert_should_have_something_button(String list) {
        try {
            action.waitForAlert();
            List<String> getListOfAlertButton = action.getListOfAlertButton();
            switch (list) {
                case "cancel,proceed":
                    testStepVerify.isTrue(getListOfAlertButton.contains("CANCEL"), "Alert should have cancel button");
                    testStepVerify.isTrue(getListOfAlertButton.contains("PROCEED"), "proceed should have cancel button");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" on alert message$")
    public void i_click_something_on_alert_message(String buttonLabel) {
        try {
            action.waitForAlert();
            boolean clicked = action.clickAlertButton(buttonLabel);

            testStepAssert.isTrue(clicked,
                    "Clicked on " + buttonLabel + " button", "Alert Message with " + buttonLabel + "button not displayed");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" on alert message if any$")
    public void i_click_something_on_alert_messageifany(String buttonLabel) {
        try {
            Thread.sleep(20000);
            if(action.isAlertPresent())
                action.clickAlertButton(buttonLabel);

            log("Alert Message should be Cancelled if any", "Alert Message should be Cancelled if any");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I accept Alert message$")
    public void iAcceptAlertMessage() {
        try {
            SetupManager.getDriver().switchTo().alert().accept();
            log("Alert Message should be accepted", "Alert Message is accepted");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I accept Alert message if exist$")
    public void iAcceptAlertMessageIfExist() {
        try {
            if(action.isAlertPresent())
            SetupManager.getDriver().switchTo().alert().accept();
        } catch (Throwable e) {
        }
    }
    @And("^I reject Alert message$")
    public void iRejectAlertMessage() {
        try {
            SetupManager.getDriver().switchTo().alert().dismiss();
            log("Alert Message should be reject", "Alert Message is reject");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @When("^I logged in Customer application using  \"([^\"]*)\" user$")
    public void i_logged_in_customer_application_using_something_user(String key) {
        try {
            String NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());
            String userName = "", password = "";
            switch (key.toLowerCase()) {
                case "existing":
                    userName = PropertyUtility.getDataProperties("customer.user");
                    password = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "existing app user":
                    userName = PropertyUtility.getDataProperties("customer.user.hasTrip");
                    password = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name.hasTrip"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case"newly created user":
                    userName = (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");
                    password = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "new":
                    userName = PropertyUtility.getDataProperties("new.customer.user");
                    password = PropertyUtility.getDataProperties("new.customer.password");
                    break;
                case "referral":
                    userName = PropertyUtility.getDataProperties("referral.customer.phone");
                    password = PropertyUtility.getDataProperties("referral.customer.password");
                    break;
                case "customer-duo":
                    userName = PropertyUtility.getDataProperties("customer.phone.usedin.duo");
                    password = PropertyUtility.getDataProperties("customer.password.usedin.duo");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name.usedin.duo"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid miami":
                    userName = PropertyUtility.getDataProperties("miami.customer.phone");
                    password = PropertyUtility.getDataProperties("miami.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("miami.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid miami 2":
                    userName = PropertyUtility.getDataProperties("miami.customer2.phone");
                    password = PropertyUtility.getDataProperties("miami.customer2.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("miami.customer2.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid nashville":
                    userName = PropertyUtility.getDataProperties("nashville.customer.phone");
                    password = PropertyUtility.getDataProperties("nashville.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("nashville.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid nashville3":
                    userName = PropertyUtility.getDataProperties("nashville.customer3.phone");
                    password = PropertyUtility.getDataProperties("nashville.customer3.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("nashville.customer3.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid nashville first time":
                    userName = PropertyUtility.getDataProperties("nashville.common.customer.phone");
                    password = PropertyUtility.getDataProperties("nashville.common.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("nashville.common.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid denver":
                    userName = PropertyUtility.getDataProperties("denver.customer.phone");
                    password = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid denver1":
                    userName = PropertyUtility.getDataProperties("denver1.customer.phone");
                    password = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver1.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid denver2":
                    userName = PropertyUtility.getDataProperties("denver2.customer.phone");
                    password = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver2.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid denver3":
                    userName = PropertyUtility.getDataProperties("denver3.customer.phone");
                    password = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver3.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid denver4":
                    userName = PropertyUtility.getDataProperties("denver4.customer.phone");
                    password = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver4.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid customer2":
                    userName = PropertyUtility.getDataProperties("customer.phone.usedin.duo");
                    password = PropertyUtility.getDataProperties("customer.password.usedin.duo");
                    cucumberContextManager.setScenarioContext("CUSTOMER2", PropertyUtility.getDataProperties("customer.name.usedin.duo"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2_PHONE", userName);
                    break;
                case "valid chicago":
                    userName = PropertyUtility.getDataProperties("chicago.customer.phone");
                    password = PropertyUtility.getDataProperties("chicago.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("chicago.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid denver5":
                    userName = PropertyUtility.getDataProperties("denver5.customer.phone");
                    password = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver5.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid denver9":
                    userName = PropertyUtility.getDataProperties("denver9.customer.phone");
                    password = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver9.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            cucumberContextManager.setScenarioContext("LATEST_LOGGEDIN_CUSTOMER_PHONE", userName);
            goToLogInPage(NavigationBarName);

            LogInSteps logInSteps = new LogInSteps(loginPage);
            logInSteps.i_enter_valid_and_as_per_below_table(userName, password);
            iClickButtonOnScreen("Log In", "Log In");
            Thread.sleep(2000);

            NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar(true));

            if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                new GeneralUtility().navigateFromTermToHomeScreen();
            }
          //  new GeneralUtility().logCustomerDeviceToken(userName);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful",
                    "Error in login to a customer app using "+ key, true);
        }
    }
    @When("^I navigate to \"([^\"]*)\" on Admin portal$")
    public void i_navigate_to_something_on_admin_portal(String option) throws Throwable {
        try {
        i_open_new_something_browser_for_something_instance("Chrome", "ADMIN PORTAL");
        SetupManager.getDriver().get(utility.GetAdminUrl());
        logInPage.TextBox_Phone().sendKeys(PropertyUtility.getDataProperties("admin.user"));
        logInPage.TextBox_Pass().sendKeys(PropertyUtility.getDataProperties("admin.password"));
        logInPage.Button_LogIn().click();

            switch (option.toLowerCase()) {
                case "scheduled trip":
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_ScheduledTrips());
                    break;
                case "promo code":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_PromoCode());
                    break;
                case "referral source":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_ReferralSource());
                    break;
                case "live trips":
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_LiveTrips());
                    break;
                case "trips":
                    action.click(dashBoardPage.Button_Trips());
                    break;
                case "customers":
                    action.click(dashBoardPage.Button_Customers());
                    break;
                case "drivers":
                    action.click(dashBoardPage.Button_Drivers());
                    break;
                case "geofence":
                    action.click(dashBoardPage.Menu_Geofences());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I should able to select "+option,"I Selected "+option+" on admin sidebar" ,true );
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I open the trip for \"([^\"]*)\" the customer$")
    public void i_open_the_trip_for_something_the_customer(String custName) throws Throwable {
        try {
            String[] name = custName.split(" ");

            action.clearSendKeys(scheduledTripsPage.Text_SearchCriteria(),name[0]);
            action.click(scheduledTripsPage.Button_Search());

            Thread.sleep(25000);

            List<WebElement> rows_editicon = scheduledTripsPage.findElements(String.format("//td/span[contains(text(),'%s')]/parent::td/following-sibling::td/div/img",name[0]),PageBase.LocatorType.XPath);

            if(rows_editicon.size()>0)
            {
                rows_editicon.get(0).click();
                List<WebElement> rows_editlink = scheduledTripsPage.findElements(String.format("//td/span[contains(text(),'%s')]/ancestor::td/following::div/div/a[contains(text(),'Edit')]",name[0]),PageBase.LocatorType.XPath);
                rows_editlink.get(0).click();
            }

            pass("I should able to open trip", "I viewed scheduled delivery",
                    false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Problem in selecting a Bungii Scheduled deliveries in admin portal for customer "+custName,
                    true);
        }
    }
    @And("^I Select \"([^\"]*)\" option$")
    public void i_select_something_option(String option) throws Throwable {
        try {
            switch (option) {
                case "Edit Trip Details":
                    String editLiveDelivery = action.getText(scheduledTripsPage.Header_EditLiveBungiiOrEditScheduledBungii());
                    if(editLiveDelivery.contentEquals("Edit Live Bungii")){
                        action.click(scheduledTripsPage.RadioBox_EditTrip());
                        Thread.sleep(10000);
                    }
                    else {
                        action.click(scheduledTripsPage.RadioBox_EditTripForScheduled());
                        Thread.sleep(10000);
                    }
                    break;
                case "Research Driver":
                    action.click(scheduledTripsPage.RadioBox_Research());
                    break;
                case "Cancel Trip":
                    action.click(scheduledTripsPage.RadioBox_Cancel());
                    break;
                default:
                    error("UnImplemented Step or incorrect option.", "UnImplemented Step");
                    break;
            }
            pass("I should able to select option", "I was able to select option");
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I edit the drop off address$")
    public void i_edit_the_drop_off_address() throws Throwable {
        try{
            String editLiveDelivery = action.getText(scheduledTripsPage.Header_EditLiveBungiiOrEditScheduledBungii());
            if(editLiveDelivery.contentEquals("Edit Live Bungii")) {
                testStepAssert.isElementDisplayed(scheduledTripsPage.Label_Drop_Off_Location(), "Drop off location should display", "Drop off location is display", "Drop off location is not display");
                action.click(scheduledTripsPage.Button_Edit_Drop_Off_Address());
            }
            else {
                testStepAssert.isElementDisplayed(scheduledTripsPage.Label_Drop_Off_Location_For_Scheduled(), "Drop off location should display", "Drop off location is display", "Drop off location is not display");
                action.click(scheduledTripsPage.Button_Edit_Drop_Off_Address_For_scheduled());

            }
            log("I edit the drop off address ",
                    "I have edited the dropoff address ");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I click on the \"([^\"]*)\" and select future time$")
    public void i_click_on_the_something_and_select_future_time(String scheduleDate) throws Throwable {
        try{
            switch (scheduleDate) {
                case "Time":
                    action.click(scheduledTripsPage.TimePicker_Time());
                    Thread.sleep(3000);
                    action.click(scheduledTripsPage.Dropdown_ScheduledDate_Time());
                    String timeChanged = scheduledTripsPage.TimePicker_Time().getText();
                    cucumberContextManager.setScenarioContext("Time_Changed", timeChanged);
                    break;
                default: break;
            }
            log("I can select future time/date",
                    "I was able to change time/date to future time/date", false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }

    }

    @Then("^I change the drop off address to \"([^\"]*)\"$")
    public void i_change_the_drop_off_address_to_something(String address) throws Throwable {

        try{
            String editLiveDelivery = action.getText(scheduledTripsPage.Header_EditLiveBungiiOrEditScheduledBungii());
            if(editLiveDelivery.contentEquals("Edit Live Bungii")) {
                action.sendKeys(scheduledTripsPage.Textbox_Drop_Off_Location(), address);
                Thread.sleep(3000);
//                action.clickOnDropdown();
                action.click(scheduledTripsPage.Dropdown_FirstAddress(address));
                Thread.sleep(1000);
                String Change_Address = action.getText(scheduledTripsPage.DropOff_Address());
                cucumberContextManager.setScenarioContext("Change_Drop_Off", Change_Address);
            }
            else {
                action.sendKeys(scheduledTripsPage.Textbox_Drop_Off_Location_For_Scheduled(), address);
                Thread.sleep(3000);
                action.clickOnDropdown();
                Thread.sleep(1000);
                String Change_Address = action.getText(scheduledTripsPage.DropOff_Address_For_Scheduled());
                cucumberContextManager.setScenarioContext("Change_Drop_Off", Change_Address);
            }
            log("I change the dropoff address to "+address,
                    "I have changed the dropoff address to "+address);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I select the live trip for \"([^\"]*)\" customer$")
    public void i_select_the_live_trip_for_something_customer(String custName) throws Throwable {
        try {
            String pickupReference= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            action.clearSendKeys(scheduledTripsPage.Text_SearchCriteria(),pickupReference);

            if(custName.equalsIgnoreCase("Ondemand"))
            {
                String pickupReferenceOndemand=(String) cucumberContextManager.getScenarioContext("ONDEMAND_PICKUP_ID");
                action.clearSendKeys(scheduledTripsPage.Text_SearchCriteria(),pickupReferenceOndemand);
            }

            action.click(scheduledTripsPage.Button_Search());

            Thread.sleep(25000);

            action.JavaScriptClick(scheduledTripsPage.Icon_Dropdown());
            action.click(scheduledTripsPage.Option_Edit());


            pass("I should able to open trip", "I viewed live delivery",
                    false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Problem in selecting Live delivery in admin portal for customer "+custName,
                    true);
        }
    }
    @And("^I select the live trip for \"([^\"]*)\"$")
    public void i_select_the_live_trip_for_something(String custName) throws Throwable {
        try{
            action.clearSendKeys(scheduledTripsPage.Text_SearchCriteria(),custName);
            action.click(scheduledTripsPage.Button_Search());
            Thread.sleep(25000);
            action.click(scheduledTripsPage.Icon_Dropdown());
            action.click(scheduledTripsPage.Option_Edit());
            log("I should be able to see the trip with "+custName,"I am able to see the trip with "+custName,false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Problem in selecting Live delivery in admin portal for customer "+custName,
                    true);
        }
    }

    @When("^I open new \"([^\"]*)\" browser for \"([^\"]*)\"$")
    public void i_open_new_something_browser_for_something_instance(String browser, String instanceName) {
        try {

            switch (instanceName){
                case "ADMIN PORTAL":
                    SetupManager.getObject().createNewWebdriverInstance(instanceName, browser);
                    SetupManager.getObject().useDriverInstance(instanceName);
                    break;
                case "MOBILE DEVICE":
                    action.click(safariPage.Icon_Safari());
                    break;

            }

            log(
                    "I open new " + browser + " browser for " + instanceName + " instance",
                    "I open new " + browser + " browser for " + instanceName + " instance", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error in opening admin portal on browser", true);
        }
    }

    @Given("^I get \"([^\"]*)\" promocode from the admin portal$")
    public void i_get_something_promocode_from_the_admin_portal(String codeType) throws Throwable {
        i_open_new_something_browser_for_something_instance("Chrome", "ADMIN PORTAL");
        SetupManager.getDriver().get(utility.GetAdminUrl());
        logInPage.TextBox_Phone().sendKeys(PropertyUtility.getDataProperties("admin.user"));
        logInPage.TextBox_Pass().sendKeys(PropertyUtility.getDataProperties("admin.password"));
        logInPage.Button_LogIn().click();
        action.click(dashBoardPage.Button_Marketing());
        action.click(dashBoardPage.Button_PromoCode());
        action.click(promosCodePage.Button_Filter());
        action.click(promosCodePage.CheckBox_FilterPromo());
        action.click(promosCodePage.Button_Apply());
        Thread.sleep(2000);
        cucumberContextManager.setFeatureContextContext("VALID", getPromoCode(codeType));

    }
    /**
     * Find required promocode and return list of it
     *
     * @param key type of promocode that is to be searched
     * @return list of promocode for input category
     */
    public List<String> getPromoCode(String key) throws InterruptedException {
        List<String> codeList = new ArrayList<String>();
        //Vishal[12042019]: Temp fixed , Duo to QA _ Auto , TODO: Remove this
        // if (!promosPage.Text_ActivePageNumber().getText().equals("1"))
        //      promosPage.Button_Previouspage().click();
        //   while (codeList.size() <= 5) {

        while (codeList.size() <= 1) {
            List<WebElement> codes = new ArrayList<WebElement>();
            switch (key.toLowerCase()) {
                case "referral":
                    codes = promosCodePage.Text_ReferralCode();
                    break;
                case "one off":
                    codes = promosCodePage.Text_OneOffCode();
                    break;
                case "used one off":
                    codes = promosCodePage.Text_UsedOneOffCode();
                    break;
                case "unused one off":
                    codes = promosCodePage.Text_UnUsedOneOffCode();
                    break;
                case "valid":
                case "promo":
                    codes = promosCodePage.Text_PromoCode();
                    break;
                case "expired":
                    codes = promosCodePage.Text_ExpiredPromoCode();
                    break;
                case "promo fixed":
                    codes = promosCodePage.Text_PromoCodeFixed();
                    break;
                case "{promo percent}":
                    codes = promosCodePage.Text_PromoCodePercent();
                    break;
                case"promoter_type_promo":
                    codes = promosCodePage.Text_PromoCodePromoter();
                    break;
                default:
                    break;
            }
            for (WebElement code : codes) {
                codeList.add(code.getText());
            }
            Thread.sleep(1000);
            //   action.click(promosPage.Button_Nextpage());
            //   promosPage.waitForPageLoad();
            //  action.invisibilityOfElementLocated(promosPage.Loadder());
        }
        logger.detail("Promo code list for key "+key+ " is "+String.join(", ", codeList));
        return codeList;
    }

    @When("^I connect to \"([^\"]*)\" using \"([^\"]*)\" instance$")
    public void i_connect_to_something_using_something_instance(String deviceId, String instanceName) {
        try {
            SetupManager.getObject().createNewAppiumInstance(instanceName, deviceId);
            SetupManager.getObject().useDriverInstance(instanceName);
            log(
                    "I connect to  " + deviceId + " using " + instanceName + " instance$",
                    "I connect to " + deviceId + "using " + instanceName + " instance$", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I switch to \"([^\"]*)\" instance$")
    public void i_switch_to_something_instance(String instanceName) {
        try {
            SetupManager.getObject().useDriverInstance(instanceName);
            log("I switch to " + instanceName + " device instance",
                    "I switch to  " + instanceName + " device instance", false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I Select delivery \"([^\"]*)\" from scheduled deliveries$")
    public void i_select_first_delivery_from_scheduled_trip(String count) {
        try {
            String currentApplication = (String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION");
            Thread.sleep(3000);
            if (action.isAlertPresent()) {
                SetupManager.getDriver().switchTo().alert().dismiss();
                Thread.sleep(1000);
            }
            if (currentApplication.equalsIgnoreCase("CUSTOMER")) {
                action.swipeDown();
                List<WebElement> deliveries = scheduledBungiiPage.List_SchBungii();
                int deliveryCount = deliveries.size()-1;

                if(deliveryCount==0)
                {
                    testStepAssert.isFail("Scheduled deliveries of a customer is not displayed in Scheduled list");
                }
                else
                   action.click(deliveries.get(Integer.parseInt(count)));
            } else {

                if (!action.isAlertPresent()) {

                    action.swipeDown();
                    Thread.sleep(2000);
                    List<WebElement> deliveries = scheduledBungiiPage.List_SchBungii();
                    int deliveryCount = deliveries.size()-1;

                    if(deliveryCount==0)
                    {
                        testStepAssert.isFail("Scheduled deliveries of a Driver is not displayed in Scheduled list");
                    }
                    else
                        action.click(deliveries.get(Integer.parseInt(count)));
                } else {
                    //If alert is present accept it , it will automatically select Bungii
                    SetupManager.getDriver().switchTo().alert().accept();
                }
            }
            log("I Select first delivery from scheduled deliveries ",
                    "I Selected first delivery from scheduled deliveries", true);

        } catch (Exception e) {
            logger.error("Error performing  step" +  ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I swipe to check trip details$")
    public void i_swipe_to_check_trip_details() throws Throwable {
        try{
            swipeForTripDetails();
            Thread.sleep(3000);

            log("I should be able to swipe to view delivery details","I am able to swipe to view delivery details",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I check if \"([^\"]*)\" is updated for live trip$")
    public void i_check_if_something_is_updated_for_live_trip(String address) throws Throwable {
        try{
            switch (address){
                case "dropoff address":
                    action.swipeUP();
                    String changedDropOff= (String) cucumberContextManager.getScenarioContext("Change_Drop_Off");
                    String actualDropOff=bungiiRequestPage.Text_DropOffAddress().getText();
                    testStepAssert.isEquals(actualDropOff,changedDropOff, "The drop off address should be updated", "The drop off address is updated", "The drop off address is not updated");
                    break;

                case "pickup address":
                    action.swipeDown();
                    String changedPickup = (String) cucumberContextManager.getScenarioContext("Change_Pickup");
                    String actualPickUp = bungiiRequestPage.Text_PickUpAddress().getText();
                    testStepAssert.isEquals(actualPickUp,changedPickup, "The pick up address should be updated", "The pick up address is updated", "The pick up address is not updated");
                    break;

            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I check if \"([^\"]*)\" is updated$")
    public void i_check_if_something_is_updated(String addressType) throws Throwable {
        try{
            switch (addressType){
                case "pickup address":
                    action.swipeDown();
                    String changedPickup[] = cucumberContextManager.getScenarioContext("Change_Pickup").toString().split(",");
                    String actualPickUpLineOne[]=action.getText(scheduledBungiiPage.Text_PickupLocation_LineOne1()).split(",");;
                    testStepAssert.isEquals(actualPickUpLineOne[0],changedPickup[0], "The drop off address should be updated", "The drop off address is updated", "The drop off address is not updated");
                    break;
                case "dropoff address":
                    action.swipeDown();
                    String changedDropOff[]=cucumberContextManager.getScenarioContext("Change_Drop_Off").toString().split(",");
                    String actualDropOffLineOne[]=action.getText(scheduledBungiiPage.Text_DropOffLocation_LineOne1()).split(",");;
                    testStepAssert.isEquals(actualDropOffLineOne[0],changedDropOff[0], "The drop off address should be updated", "The drop off address is updated", "The drop off address is not updated");
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I edit the pickup address$")
    public void i_edit_the_pickup_address() throws Throwable {
        try{
            String editLiveDelivery = action.getText(scheduledTripsPage.Header_EditLiveBungiiOrEditScheduledBungii());
            if(editLiveDelivery.contentEquals("Edit Live Bungii")) {
                testStepAssert.isElementDisplayed(scheduledTripsPage.Label_Pickup_Location_For_Live(), "Drop off location should display", "Drop off location is display", "Drop off location is not display");
                action.click(scheduledTripsPage.Button_Edit_Pickup_Address_For_Live());
            }
            else {
                testStepAssert.isElementDisplayed(scheduledTripsPage.Label_Drop_Off_Location(), "Drop off location should display", "Drop off location is display", "Drop off location is not display");
                action.click(scheduledTripsPage.Button_Edit_Pickup_Address());

            }
            log("I edit the pickup address.",
                    "I have edited the pickup address.");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @And("^The \"([^\"]*)\" message is displayed$")
    public void The_something_message_is_displayed(String message) throws Throwable {
        try{
            String actualMessage = null;
            switch (message){
                case "Your changes are good to be saved.":
                    actualMessage=action.getText(scheduledTripsPage.Text_VerifyChangesSavedMessage());
                    break;
                case "Bungii Saved!":
                    Thread.sleep(3000);
                    actualMessage=action.getText(scheduledTripsPage.Text_SuccessMessage());
                    break;
                default:
                    error("UnImplemented Step or incorrect option.", "UnImplemented Step");
                    break;
            }
            testStepAssert.isTrue(actualMessage.contains(message),"Expected message is displayed.","Expected message is not displayed. Actual is : "+actualMessage );

        }catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error in viewing "+message+"message", true);
        }
    }
    @Then("^I change the pickup address to \"([^\"]*)\"$")
    public void i_change_the_pickup_address_to_something(String address) throws Throwable {

        try{
            String editLiveDelivery = action.getText(scheduledTripsPage.Header_EditLiveBungiiOrEditScheduledBungii());
            if(editLiveDelivery.contentEquals("Edit Live Bungii")) {
                action.click(scheduledTripsPage.Textbox_Pickup_Location_For_Live());
                action.sendKeys(scheduledTripsPage.Textbox_Pickup_Location_For_Live(),address);
                Thread.sleep(1000);
                action.click(scheduledTripsPage.DropdownPickupResult(address));
                String Change_Address = action.getText(scheduledTripsPage.Text_Pickup_Address_For_Live());
                cucumberContextManager.setScenarioContext("Change_Drop_Off",Change_Address);
            }
            else {
                action.sendKeys(scheduledTripsPage.Textbox_Pickup_Location(),address);
                Thread.sleep(1000);
                action.click(scheduledTripsPage.DropdownPickupResult(address));
                Thread.sleep(1000);
                String Change_Address = action.getText(scheduledTripsPage.Pickup_Address());
                cucumberContextManager.setScenarioContext("Change_Drop_Off",Change_Address);
            }
            log("I change the pickup address to "+address,
                    "I have changed the pickup address to "+address);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I Select Trip from scheduled trip$")
    public void i_select_trip_from_scheduled_trip() {
        try {

            String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            String currentApplication = (String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION");
            Thread.sleep(3000);
            if (action.isAlertPresent()) {
                SetupManager.getDriver().switchTo().alert().dismiss();
                Thread.sleep(1000);
            }
            //   tripNoOfDriver="DUO";tripTime="Jan 10, 07:15 PM GMT+5:30";currentApplication="DRIVER";
            tripTime = tripTime.substring(0,13).trim();

            /*if (tripTime.contains(PropertyUtility.getDataProperties("time.label")))
                tripTime = tripTime.replace(PropertyUtility.getDataProperties("time.label"), "").trim();

            tripTime=tripTime.replace("am","AM").replace("pm","PM");
            //code to check daylight savings required here
            if(TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date()))
            {
                tripTime=tripTime.replace("st","dt").replace("ST","DT");
                logger.detail("Daylight Savings is ON");
            }

            if(BrowserStackLocal().equalsIgnoreCase("true")) {
                tripTime = tripTime.replace("am","a.m.").replace("pm","p.m.").replace("AM","a.m.").replace("PM","p.m.");
                tripTime = utility.getGmtTime(tripTime);
                //testStepVerify.isEquals(displayedTime, strTime);
            }
*/
            logger.detail("TRIP TIME [According to Daylight Savings]: "+tripTime);

            if (currentApplication.equalsIgnoreCase("CUSTOMER")) {
                //customerScheduledBungiiPage.selectBungiiFromList(tripNoOfDriver, tripTime);
                String imageTag = "";
                if (tripNoOfDriver.toUpperCase().equals("SOLO")) {
                    imageTag = Image_Solo;
                } else if (tripNoOfDriver.toUpperCase().equals("DUO")) {
                    imageTag = Image_Duo;
                }

                action.swipeDown();
                logger.detail("SCHEDULED DELIVERY "+ "//XCUIElementTypeStaticText[contains(@name,'" + tripTime + "')]/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell");
                WebElement Image_SelectBungii = scheduledBungiiPage.findElement("//XCUIElementTypeStaticText[contains(@name,'" + tripTime + "')]/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);
                action.click(Image_SelectBungii);
            } else {
                //driverScheduledBungiiPage.selectBungiiFromList(tripNoOfDriver, tripTime);

                if (!action.isAlertPresent()) {
                    String imageTag = "";
                    if (tripNoOfDriver.toUpperCase().equals("SOLO")) {
                        imageTag = Image_Solo;
                    } else if (tripNoOfDriver.toUpperCase().equals("DUO")) {
                        imageTag = Image_Duo;
                    }

                    action.swipeDown();
                    Thread.sleep(2000);
                    //vishal[0801]:Commented due to new build
                    //WebElement Image_SelectBungii = driverScheduledBungiiPage.findElement("//XCUIElementTypeStaticText[@name=“" + tripTime + "”]/following-sibling::XCUIElementTypeImage[@name=“" + imageTag + "”]/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);
                    WebElement Image_SelectBungii = scheduledBungiiPage.findElement("//XCUIElementTypeStaticText[contains(@name,'" + tripTime + "')]/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath, true);
                    if (Image_SelectBungii == null) {
                        Thread.sleep(30000);
                        action.swipeDown();
                        Image_SelectBungii = scheduledBungiiPage.findElement("//XCUIElementTypeStaticText[contains(@name,'" + tripTime + "')]/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath, true);
                    }
                    if (action.isAlertPresent()) {
                        SetupManager.getDriver().switchTo().alert().dismiss();
                        Thread.sleep(1000);
                    }
                    if (Image_SelectBungii == null) {
                        //Thread.sleep(30000);
                        action.swipeDown();
                        Image_SelectBungii = scheduledBungiiPage.findElement("//XCUIElementTypeStaticText[contains(@name,'" + tripTime + "')]/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath, true);
                    }
                    logger.detail("SCHEDULED DELIVERY "+ "//XCUIElementTypeStaticText[contains(@name,'" + tripTime + "')]/parent::XCUIElementTypeCell");
                    action.click(Image_SelectBungii);
                } else {
                    //If alert is present accept it , it will automatically select Bungii
                    SetupManager.getDriver().switchTo().alert().accept();
                }

            }
            log("I Select Trip from scheduled trips ",
                    "I Selected Trip scheduled for "+tripTime+" from driver's scheduled trips", true);

        } catch (Exception e) {
            logger.error("Error performing  step" +  ExceptionUtils.getStackTrace(e));
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            error("Step  Should be successful", "Error in selecting delivery scheduled for "+ tripTime +" on app",
                    true);
        }
    }

/*
    public void navigateFromTermToHomeScreen() {
        action.click(termsAndConditionPage.Button_CheckOff());
        action.click(termsAndConditionPage.Button_Continue());
        if (action.isElementPresent(enableNotificationPage.Button_Sure(true))) {
            action.click(enableNotificationPage.Button_Sure());
            action.clickAlertButton("Allow");
        }

        if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
            action.click(enableLocationPage.Button_Sure());
            action.clickAlertButton("Allow");
        }

        action.click(tutorialPage.Button_Close());


    }
*/

    @Given("^I am on Customer logged in Home page$")
    public void iAmOnCustomerLoggedInHomePage() {
        try {
            LogInSteps logInSteps = new LogInSteps(new LoginPage());
            HomeSteps homeSteps = new HomeSteps(homePage);
            GeneralUtility utility = new GeneralUtility();
            String NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());

            if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))
                    || NavigationBarName.equals("SIGN UP")) {
                if (NavigationBarName.equals("SIGN UP"))
                    iClickButtonOnScreen("LOG IN", "sign up");

                logInSteps.i_enter_valid_and_as_per_below_table(PropertyUtility.getDataProperties("customer.user"),
                        PropertyUtility.getDataProperties("customer.password"));
                // cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer.user"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PUSH_PHONE", PropertyUtility.getDataProperties("customer.user"));
                cucumberContextManager.setScenarioContext("CUSTOMER_PUSH_PWD", PropertyUtility.getDataProperties("customer.password"));

                iClickButtonOnScreen("Log In", "Log In");
                Thread.sleep(2000);
                NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());
                if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                    //DO Nothing
                } else if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                    utility.navigateFromTermToHomeScreen();
                }

                //homeSteps.user_should_be_successfully_logged_in_to_the_system();
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                // do nothing
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
                iClickButtonOnScreen("CANCEL", "SEARCHING");
                iAcceptAlertMessage();
                //iRejectAlertMessage();
            } else if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                utility.navigateFromTermToHomeScreen();
            } else if (NavigationBarName.equalsIgnoreCase("NOTIFICATIONS")) {
                action.click(enableNotificationPage.Button_Sure());
                action.clickAlertButton("Allow");
                if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
                    action.click(enableLocationPage.Button_Sure());
                    action.clickAlertButton("Always Allow");
                }
            }else if (NavigationBarName.equalsIgnoreCase("WANT $5?")){
                takeActionOnPromotion("REJECT");
            } else {
                homeSteps.i_select_something_from_customer_app_menu("HOME");
            }



            log("Given customer is logged in as customer","Customer is logged in");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Given("^I am on Customer \"([^\"]*)\" logged in Home page$")
    public void iAmOnCustomerALoggedInHomePage(String user) {
        try {
            LogInSteps logInSteps = new LogInSteps(new LoginPage());
            HomeSteps homeSteps = new HomeSteps(homePage);
            GeneralUtility utility = new GeneralUtility();
            String NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());

            if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.login"))
                    || NavigationBarName.equals("SIGN UP")) {
                if (NavigationBarName.equals("SIGN UP"))
                    iClickButtonOnScreen("LOG IN", "sign up");

                switch (user) {
                    case "A":
                        logInSteps.i_enter_valid_and_as_per_below_table(PropertyUtility.getDataProperties("customer.ios.userA"),
                                PropertyUtility.getDataProperties("customer.password"));
                        break;
                    case "B":
                        logInSteps.i_enter_valid_and_as_per_below_table(PropertyUtility.getDataProperties("customer.ios.userB"),
                                PropertyUtility.getDataProperties("customer.password"));
                        break;
                }

                iClickButtonOnScreen("Log In", "Log In");
                Thread.sleep(2000);
                NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());
                if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                    //DO Nothing
                } else if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                    utility.navigateFromTermToHomeScreen();
                }

                //homeSteps.user_should_be_successfully_logged_in_to_the_system();
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.home"))) {
                // do nothing
            } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
                iClickButtonOnScreen("CANCEL", "SEARCHING");
                iAcceptAlertMessage();
                //iRejectAlertMessage();
            } else if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                utility.navigateFromTermToHomeScreen();
            } else if (NavigationBarName.equalsIgnoreCase("NOTIFICATIONS")) {
                action.click(enableNotificationPage.Button_Sure());
                action.clickAlertButton("Allow");
                if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
                    action.click(enableLocationPage.Button_Sure());
                    action.clickAlertButton("Always Allow");
                }
            }else if (NavigationBarName.equalsIgnoreCase("WANT $5?")){
                takeActionOnPromotion("REJECT");
            } else {
                homeSteps.i_select_something_from_customer_app_menu("HOME");
            }
          //  cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
          //  cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer.user"));
            log("Given customer is logged in as customer","Customer is logged in");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    //Except first time all code is fetch on fly, first time is read from file
    @SuppressWarnings("unchecked")
    public List<String> getRefferalCode(String codeType) {

        List<String> code = new ArrayList<String>();
        try{
        switch (codeType.toLowerCase()) {
            case "referral":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("REFERRAL");
                break;
            case "valid":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("VALID");
                break;
            case "promo":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("PROMO");
                break;

            case "expired":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("EXPIRED");
                break;
            case "one off":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("ONE_OFF");
                break;
            case "used one off":
                code = Arrays.asList(PropertyUtility.getDataProperties("promocode.usedoneoff"));
                //    code = (List<String>) cucumberContextManager.getFeatureContextContext("USED_ONE_OFF");
                break;
            case "unused one off":
                code = (List<String>) cucumberContextManager.getFeatureContextContext("UNUSED_ONE_OFF");
                break;
            case "referral code":
                code = Arrays.asList((String) cucumberContextManager.getScenarioContext("INVITE_CODE"));
                break;
            case "first time only":
                code = Arrays.asList(PropertyUtility.getDataProperties("promocode.firsttime"));
                break;
            case "promocode":
                code = Arrays.asList(PropertyUtility.getDataProperties("promocode.dollar.off"));
                break;
            default:
                code.add(codeType);
                break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
        return code;
    }

    @Then("^I save customer phone and referral code in feature context$")
    public void i_save_customer_phone_and_referral_code_in_feature_context() throws Throwable {
        try {
            cucumberContextManager.setFeatureContextContext("INVITE_CODE", (String) cucumberContextManager.getScenarioContext("INVITE_CODE"));
            cucumberContextManager.setFeatureContextContext("CUSTOMER_HAVING_REF_CODE", (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER"));
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I have customer with referral code$")
    public void i_save_customer_phone_and_referral_code_iADDED_PROMO_CODEn_feature_context() throws Throwable {
        try {

            String refCode = "S2WG3"; //(String) cucumberContextManager.getFeatureContextContext("INVITE_CODE");//refCode="119W5";
            String phoneNumber = (String) cucumberContextManager.getFeatureContextContext("CUSTOMER_HAVING_REF_CODE");//phoneNumber="9999992799";
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", refCode);
            cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", phoneNumber);
            testStepAssert.isTrue(refCode.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
            testStepAssert.isTrue(phoneNumber.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I note customer with referral code$")
    public void i_save_customer_phone_and_referral_code_iADDED_PROMO_CODEn_feature() throws Throwable {
        try {

            String refCode = (String) cucumberContextManager.getFeatureContextContext("INVITE_CODE");//refCode="119W5";
            String phoneNumber = (String) cucumberContextManager.getFeatureContextContext("CUSTOMER_HAVING_REF_CODE");//phoneNumber="9999992799";
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", refCode);
            cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", phoneNumber);
            testStepAssert.isTrue(refCode.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
            testStepAssert.isTrue(phoneNumber.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Given("^I have customer with referral code received$")
    public void i_save_customer_phone_and_referral_code_iADDED_PROMO_CODEreceived() throws Throwable {
        try {

            String refCode = (String) cucumberContextManager.getFeatureContextContext("INVITE_CODE");//refCode="119W5";
            String phoneNumber = (String) cucumberContextManager.getFeatureContextContext("CUSTOMER_HAVING_REF_CODE");//phoneNumber="9999992799";
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", refCode);
            cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", phoneNumber);

            if(refCode.length() <= 1)
            {

            }
            else
            testStepAssert.isTrue(refCode.length() > 1, "I Should have customer with referral code", "I dont have customer with referral code");
            testStepAssert.isTrue(phoneNumber.length() > 1, "I Should have customer with phoneNumber", "I dont have customer with phoneNumber");




        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    public String generateMobileNumber() {

        String phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");
        while (!DbUtility.isPhoneNumberUnique(phoneNumber)) {
            phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");

        }
        return phoneNumber;
    }

    @When("^I Enter \"([^\"]*)\" value in \"([^\"]*)\" field in \"([^\"]*)\" Page$")
    public void iEnterValueInFieldInPage(String value, String field, String screen) {

        try {
            String inputValue = RandomGeneratorUtility.getData(value, 10);

            if (value.equalsIgnoreCase("{RANDOM_EMAIL}")) {
                String inputValue1 = RandomGeneratorUtility.getData(value, 5);
                inputValue = "bungiiauto+"+inputValue1+"@gmail.com";
            }
            else
            if (!value.equalsIgnoreCase("{RANDOM_PHONE_NUM}")) {
                if(value.equalsIgnoreCase("Deleted Phone")){
                    inputValue = (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");
                }
                else {
                    inputValue = value.equalsIgnoreCase("{EMPTY}") ? "     " : inputValue;
                    inputValue = value.equalsIgnoreCase("{BLANK}") ? "" : inputValue;
                }
            }
            else {
                inputValue = generateMobileNumber();
            }

            switch (field.toUpperCase()) {
                case "SUPPORT TEXTBOX":
                    action.clearEnterText(supportPage.TextBox_Support(), inputValue);
                    break;
                case "FIRST NAME":
                    if(inputValue.equalsIgnoreCase("RandomTestcustomertywd_apple"))
                    {
                        String randomString= generateMobileNumber();
                        action.clearEnterText(signupPage.Textfield_FirstName(), inputValue+"_"+randomString);
                        cucumberContextManager.setScenarioContext("NEW_USER_FIRST_NAME", inputValue+"_"+randomString);
                    }
                    action.clearEnterText(signupPage.Textfield_FirstName(), inputValue);
                    cucumberContextManager.setScenarioContext("NEW_USER_FIRST_NAME", inputValue);
                    break;
                case "LAST NAME":
                    action.clearEnterText(signupPage.Textfield_LastName(), inputValue);
                    cucumberContextManager.setScenarioContext("NEW_USER_LAST_NAME", inputValue);
                    action.hideKeyboard();
                    break;
                case "EMAIL":
                    action.clearEnterText(signupPage.Textfield_Email(), inputValue);
                    action.hideKeyboard();
                    cucumberContextManager.setScenarioContext("NEW_USER_EMAIL_ADDRESS",inputValue);
                    break;
                case "PHONE NUMBER":
                    if (screen.equalsIgnoreCase("FORGOT PASSWORD")) {
                        inputValue = value.equalsIgnoreCase("{VALID USER}") ? PropertyUtility.getDataProperties("new.customer.user") : inputValue;
                        action.clearEnterText(forgotPasswordPage.Text_InputNumber(), inputValue);
                        cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", inputValue);
                    } else {
                        inputValue = value.equalsIgnoreCase("{VALID USER}") ? PropertyUtility.getDataProperties("new.customer.user") : inputValue;
                        action.clearEnterText(signupPage.Textfield_Phonenumber(), inputValue);
                        cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", inputValue);
                    }
                    break;
                case "PASSWORD":
                    action.clearEnterText(signupPage.Textfield_Password(), inputValue);
                    break;
                case "REFERRAL CODE":
                    action.hideKeyboard();
                    action.click(signupPage.Button_CheckBox_Referral());
                    List<String> inputValueList = getRefferalCode(inputValue);
                    action.clearEnterText(signupPage.Textfield_PromoCode(), inputValueList.get(0));
                    // cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputValue);
                    cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputValueList.get(0));
                    break;
                case "PROMO CODE":
                    List<String> ValueList = getRefferalCode(inputValue);
                    action.tapByElement(promosPage.TextBox_EnterCode());
                    action.clearEnterText(promosPage.TextBox_EnterCode(), ValueList.get(0));
                    //  cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", inputValue);
                    cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", ValueList.get(0));
                    break;
                case "SMS CODE":
                    inputValue = inputValue.equalsIgnoreCase("valid") || inputValue.equalsIgnoreCase("old") ? (String) cucumberContextManager.getScenarioContext("SMS_CODE")
                            : "111";
                    action.clearEnterText(forgotPasswordPage.Text_SmsCode(), inputValue);
                    action.hideKeyboard();

                    break;
                case "NEW PASSWORD":
                    action.clearEnterText(forgotPasswordPage.Text_Password(), inputValue);
                    action.hideKeyboard();
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            log("I should able to Enter " + value + " value in " + field + " field in " + screen + " Page",
                    "I Entered " + inputValue + " in " + field + " field", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I install Bungii App again$")
    public void i_reset_bungii_app_data() {
        try {
            GeneralUtility utility = new GeneralUtility();
            boolean isNewInstalled = utility.installCustomerApp();
            testStepAssert.isTrue(isNewInstalled, "I should able to install bungii App again", "I was not able to install bungii app again");
            log("I install Bungii",
                    "I installed Bungii", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I install Bungii Driver App again$")
    public void i_install_bungii_app_data() {
        try {
            GeneralUtility utility = new GeneralUtility();
            boolean isNewInstalled = utility.installDriverApp();
            testStepAssert.isTrue(isNewInstalled, "I should able to install bungii Driver App again", "I was not able to install bungii Driver app again");
            log("I install Bungii",
                    "I installed Bungii", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^user is virtually alerted for \"([^\"]*)\"$")
    public void user_is_virtually_alerted_for_something(String key) {
        try {
            String expectedText = "";
            switch (key.toUpperCase()) {
                case "ALREADY SCHEDULED BUNGII":
                    expectedText = PropertyUtility.getMessage("customer.alert.alreadyscheduled");
                    break;
                case "PICKUP ALREADY ACCEPTED BY YOU":
                    expectedText = PropertyUtility.getMessage("customer.alert.alreadyaccepted");
                    break;

            }
            String alertText = (String)cucumberContextManager.getScenarioContext("API_RESPONSE");
            testStepVerify.isEquals(alertText, expectedText);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details [Virtual Alert]", true);
        }
    }
    @Then("^user is alerted for \"([^\"]*)\"$")
    public void user_is_alerted_for_something(String key) {
        try {
            action.waitForAlert();
            if (!action.isAlertPresent())
                action.waitForAlert();
            String expectedText = "";
            switch (key.toUpperCase()) {
                case "ALREADY SCHEDULED BUNGII":
                    expectedText = PropertyUtility.getMessage("customer.alert.alreadyscheduled");
                    break;
                case "SUPPORT QUESTION SUBMITTED":
                    expectedText = PropertyUtility.getMessage("customer.support.submitted");
                    break;
                case "EMPTY SUPPORT QUESTION":
                    expectedText = PropertyUtility.getMessage("customer.support.emptyfield");
                    break;
                case "NO TWITTER INSTALLED":
                    expectedText = PropertyUtility.getMessage("customer.invite.notwitter");
                    break;
                case "EXPIRED PROMO":
                    expectedText = PropertyUtility.getMessage("customer.promos.expired");
                    break;
                case "INVALID PROMO":
                    expectedText = PropertyUtility.getMessage("customer.promos.invalid");
                    break;
                case "EMPTY SIGNUP FIELD":
                    expectedText = PropertyUtility.getMessage("customer.signup.emptyfield");
                    break;
                case "EXISTING USER":
                    expectedText = PropertyUtility.getMessage("customer.signup.existinguser");
                    break;
                case "INVALID EMAIL WHILE SIGNUP":
                    expectedText = PropertyUtility.getMessage("customer.signup.invalidemail");
                    break;
                case "INVALID PHONE WHILE SIGNUP":
                    expectedText = PropertyUtility.getMessage("customer.signup.invalidphonenumber");
                    break;
                case "INVALID PASSWORD WHILE SIGNUP":
                    expectedText = PropertyUtility.getMessage("customer.signup.invalidpassword");
                    break;
                case "INVALID PROMO WHILE SIGNUP":
                    expectedText = PropertyUtility.getMessage("customer.signup.invalidpromo");
                    break;
                case "REFERRAL FOR NEW USER":
                    expectedText = PropertyUtility.getMessage("customer.promos.referral.error");
                    break;
                case "FIRST TIME ONLY PROMO":
                    expectedText = PropertyUtility.getMessage("customer.promos.first.time.error");
                    break;
                case "ALREADY EXISTING CODE":
                    expectedText = PropertyUtility.getMessage("customer.promos.already.existing.code");
                    break;
                case "FAILED TO SEND TOKEN":
                    //expectedText = PropertyUtility.getMessage("customer.forgotpassword.failed.reset");
                    expectedText = PropertyUtility.getMessage("common.failed.message");
                    break;
                case "PASSWORD CHANGE SUCCESS":
                    expectedText = PropertyUtility.getMessage("customer.forgotpassword.sucess");
                    break;
                case "INVALID SMS CODE":
                    expectedText = PropertyUtility.getMessage("customer.forgotpassword.invalid.code");
                    break;
                case "INVALID PASSWORD WHILE RESET":
                    expectedText = PropertyUtility.getMessage("customer.forgotpassword.invalid.password");
                    break;
                case "CANCEL BUNGII":
                    expectedText = PropertyUtility.getMessage("customer.alert.cancel.bungii");
                    break;
                case "OUTSIDE BUISSNESS HOUR":
                    expectedText = PropertyUtility.getMessage("customer.alert.outsidebuissnesshour");
                    String alertText = SetupManager.getDriver().switchTo().alert().getText();
                    if(alertText.contains("AM")||alertText.contains("PM"))
                        expectedText = PropertyUtility.getMessage("customer.alert.outsidebuissnesshour.android"); // fix to handle AM vs a.m.
                    break;
                case "SCHEDULED ONLY 5 DAYS":
                    expectedText = PropertyUtility.getMessage("customer.alert.six.day.ahead");
                    break;
                case "LONG HAUL":
                    expectedText = PropertyUtility.getMessage("customer.alert.long.haul");
                    break;
                case "DRIVER FINISHING CURRENT BUNGII":
                    expectedText = PropertyUtility.getMessage("customer.alert.driver.bungii.inprogress");
                    break;
                case "MORE THAN 1 HOUR FROM SCHEDULED TIME":
                    expectedText = PropertyUtility.getMessage("customer.alert.more.than.one.hour");
                    break;
                case "PICKUP REQUEST NO LONGER AVAILABLE":
                    expectedText = PropertyUtility.getMessage("driver.request.unavailable");
                    break;
                case "PICKUP ALREADY ACCEPTED BY YOU":
                    expectedText = PropertyUtility.getMessage("driver.request.already.accepted");
                    break;
                case "60 MINS BEFORE SCHEDULE TRIP TIME":
                    expectedText = PropertyUtility.getMessage("driver.start.60.mins.before");
                    break;
                case "REQUIRED DRIVER NOT ACCEPTED":
                    expectedText = PropertyUtility.getMessage("driver.required.not.accepted");
                    break;
                case "CUSTOMER HAS ONGOING BUNGII":
                    expectedText = PropertyUtility.getMessage("driver.start.customer.ongoing");
                    break;
                case "FOR EMERGENCY CONTACT SUPPORT LINE":
                    expectedText = PropertyUtility.getMessage("driver.cancel.support.contact");
                    break;
                case "CONTACT SUPPORT TO CANCEL":
                    expectedText = PropertyUtility.getMessage("customer.support.contact.to.cancel");
                    break;
                case "SMS CODE SENT":
                    expectedText = PropertyUtility.getMessage("customer.sms.code.sent");
                    break;
                case "3 OUT OF 5 ATTEMPT":
                    expectedText = PropertyUtility.getMessage("customer.3.out.5.attempt.login");
                    break;
                case "USER ACCOUNT LOCKED":
                    expectedText = PropertyUtility.getMessage("customer.user.account.locked");
                    break;
                case "MINIMUM COST STILL APPLIES":
                    expectedText = PropertyUtility.getMessage("customer.promos.minimum.info");
                    break;
                case "FIRST TIME PROMO CODE":
                    expectedText = PropertyUtility.getMessage("customer.first.time.promos.info");
                    break;
                case "ADD CARD BEFORE REQUEST BUNGII":
                    expectedText = PropertyUtility.getMessage("customer.add.card.before.request");
                    break;
                case "ADD IMAGE OF ITEM":
                    expectedText = PropertyUtility.getMessage("customer.request.add.image");
                    break;
                case "CHOSSING NON FIRST TIME CODE":
                    expectedText = PropertyUtility.getMessage("customer.select.other.than.first.time.code");
                    break;
                case "PLEASE ENABLE LOCATION SERVICES":
                    expectedText = PropertyUtility.getMessage("driver.enable.location.services");
                    break;
                case "PLEASE INSTALL A BROWSER":
                    expectedText = PropertyUtility.getMessage("customer.install.browser");
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            String alertText = SetupManager.getDriver().switchTo().alert().getText();
           // testStepVerify.isEquals(alertText, expectedText);
            testStepAssert.isEquals(alertText, expectedText,alertText+" should be displayed",alertText+" is displayed", alertText+" is displayed instead of "+expectedText );
            SetupManager.getDriver().switchTo().alert().accept();
            Thread.sleep(1000);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,User alert is not found : "+ key, true);
        }
    }

    @And("^I get TELET time of of the current trip$")
    public void i_get_telet_time_of_of_the_current_trip() throws Throwable {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //    phoneNumber="8888889907";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String teletTime = dbUtility.getTELETfromDb(custRef);

        cucumberContextManager.setScenarioContext("TELET", teletTime);
    }
    @And("^I get TELET time of currrent trip of customer 2$")
    public void i_get_telet_time_of_of_the_currewnt_trip() throws Throwable {
        try{
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
        //    phoneNumber="8888889907";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String teletTime = dbUtility.getTELETfromDb(custRef);

        cucumberContextManager.setScenarioContext("TELET", teletTime);
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^Telet time of current trip should be correctly calculated$")
    public void telet_time_of_current_trip_should_be_correctly_calculated() throws Throwable {
        try{
        GeneralUtility utility= new GeneralUtility();
       // String teletTimeLocal =utility.calculateTeletTime();
            String teletTimeLocal =utility.calculateTeletTimeValue();

            String teletTimeDB = (String) cucumberContextManager.getScenarioContext("TELET");

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //By default data is in UTC
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date Db = formatter.parse(teletTimeDB);

        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();

        DateFormat formatterForLocalTimezone = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

        String strdateDB = formatter.format(Db);
        String strdatelocal = teletTimeLocal;
        testStepVerify.isEquals(strdateDB,strdatelocal);
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error in calculating in Telet time of current trip", true);
        }
    }
    @Then("^Telet time of research trip should be not be same as previous trips$")
    public void telet_time_of_current_trip_should_be_correctly_calculatedtrip() throws Throwable {
        try{
        String previousTelet = (String) cucumberContextManager.getScenarioContext("TELET");
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //    phoneNumber="8888889907";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String newTeletTime = dbUtility.getTELETfromDb(custRef);
        testStepAssert.isTrue(!previousTelet.equalsIgnoreCase(newTeletTime),"TELET TIME should not be equal to old pickup TELET time", newTeletTime +" is different than old pickups TELET"+ previousTelet,newTeletTime +" is same as old pickups TELET"+ previousTelet);
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^for a Bungii I should see \"([^\"]*)\"$")
    public void for_a_bungii_i_should_see_something(String strArg1) throws Throwable {
        try{
            switch (strArg1)
            {
                case "Bungii Home page with locations":
                    String addressPickUPline1= (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1");
                    String addressDropOffline1= (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1");
                    String pickUpAddress=homePage.TextBox_Pickup_LineOne().getText();
                    String DropOffAddress=homePage.TextBox_Drop_LineOne().getText();

                    if(pickUpAddress.contains(addressPickUPline1) && DropOffAddress.contains(addressDropOffline1))
                    {
                        pass(addressPickUPline1,DropOffAddress,true);
                    }
                    else{
                        fail(addressPickUPline1,DropOffAddress,true);
                    }
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }

        }
        catch (Exception e) {
            logger.error("Error performing step", e);
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I manually end bungii created by \"([^\"]*)\" with stage as \"([^\"]*)\"$")
    public void i_manually_end_bungii_created_by_something_with_stage_as_something(String customer, String bungiiStage) throws Throwable {
        try{
        String status =bungiiStage;
        String tripTypeAndCategory = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        String tripType[] = tripTypeAndCategory.split(" ");
        customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

        cucumberContextManager.setScenarioContext("STATUS",status);
        if (status.equalsIgnoreCase("Scheduled") ||status.equalsIgnoreCase("Searching Drivers")
                || status.equalsIgnoreCase("Driver Removed") || (status.equalsIgnoreCase("Admin Cancelled"))) {
            String xpath= String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[4]", tripType[0].toUpperCase(), customer);
            int retrycount =10;

            boolean retry = true;
            while (retry == true && retrycount >0) {
                try {
                    WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                    retry = false;
                } catch (Exception ex) {
                    SetupManager.getDriver().navigate().refresh();
                    retrycount--;
                    retry = true;
                }

            }
            int retryCount = 1;
            while (!SetupManager.getDriver().findElement(By.xpath(xpath)).getText().equalsIgnoreCase(status)) {
                if (retryCount >= 20) break;
                Thread.sleep(15000); //Wait for 15 seconds
                retryCount++;
                SetupManager.getDriver().navigate().refresh();
            }
            cucumberContextManager.setScenarioContext("XPATH",xpath);
            testStepAssert.isElementTextEquals(SetupManager.getDriver().findElement(By.xpath(xpath)), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
        }

        //Select the trip
        String xpath=  (String)cucumberContextManager.getScenarioContext("XPATH");
        action.click((WebElement) SetupManager.getDriver().findElement(By.xpath(xpath)));
        } catch (Throwable e) {
            logger.error("Error performing step" + e);
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^Customer should receive signup email$")
    public void partner_firm_should_receive_something_email(){
        try{
        GeneralUtility utility = new GeneralUtility();

        String emailSubject="New to Bungii? Good.";
/*        cucumberContextManager.setScenarioContext("NEW_USER_EMAIL_ADDRESS","bungiiauto+obKm@gmail.com");
        cucumberContextManager.setScenarioContext("NEW_USER_FIRST_NAME","TestCustomertywdappleMzr");*/

        String emailBody = utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.welcome.from.address"), (String)cucumberContextManager.getScenarioContext("NEW_USER_EMAIL_ADDRESS"), emailSubject);
        List<String> tripDetailsLinks=extractUrls(emailBody);
        utility.getCustomerSignupTemplate((String)cucumberContextManager.getScenarioContext("NEW_USER_EMAIL_ADDRESS"));
        if (emailBody == "") {
            testStepAssert.isFail("Email : " + emailSubject + " is not received");
        }
        else{
            boolean isEmailCorrect=utility.validateCustomerSignupEmail(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath())+"/EmailTemplate/CustomerSignup.txt",emailBody, (String)cucumberContextManager.getScenarioContext("NEW_USER_FIRST_NAME"),tripDetailsLinks.get(0),tripDetailsLinks.get(1),tripDetailsLinks.get(2),tripDetailsLinks.get(3),tripDetailsLinks.get(4),tripDetailsLinks.get(5),tripDetailsLinks.get(6),tripDetailsLinks.get(7),tripDetailsLinks.get(8));
            testStepAssert.isTrue(isEmailCorrect,"Email should be correct","Email is not correct , check logs for more details");



        }
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }

    @Then("^poor driver ratting should be sent to customer$")
    public void poor_driver_ratting_should_be_sent_to_customer() {
        try{
        GeneralUtility utility = new GeneralUtility();
        String emailSubject="POOR DRIVER RATING";
      //  String emailBody = utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"), "POOR DRIVER RATING");

        String emailBody  =  utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"),emailSubject);
        String driverName=(String) cucumberContextManager.getScenarioContext("DRIVER_1");/*driverName="Testdrivertywd_appledv_b_matt Stark_dvOnE";*/
        String customerName=(String)cucumberContextManager.getScenarioContext("CUSTOMER");/*customerName="Testcustomertywd_appleZTDafc Stark";*/
        String ratingValue=(String)cucumberContextManager.getScenarioContext("RATING_VALUE");/*ratingValue="3";*/
            List<String> tripDetailsLinks=extractUrls(emailBody);
            String tripDetailsLink=tripDetailsLinks.size()>=1?tripDetailsLinks.get(0):"";
        if(emailBody!= null) {
            if (emailBody == "") {
                testStepAssert.isFail("Email : " + emailSubject + " email is not received");
            }
        }
        else
        {
            testStepAssert.isFail("Email : " + emailSubject + " email is not received");
        }
        String message = null;
        message = utility.getExpectedPoorRatingMail(driverName, customerName, ratingValue, tripDetailsLink);
        testStepAssert.isEquals(emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ",""), message.replaceAll(" ",""),"Email "+emailBody+" content should match", "Email  "+emailBody+" content matches", "Email "+emailBody+"  content doesn't match");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error in fetching poor rating email", true);
    }
    }
    /**
     * Returns a list with all links contained in the input
     */
    public static List<String> extractUrls(String text)
    {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find())
        {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        return containedUrls;
    }
    @When("^I cancel Bungii as Admin$")
    public void i_cancel_bungii_as_admin() throws Throwable {
        try {
            String custPhoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        String custPassword = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PASSWORD");
        custPassword = custPassword.equalsIgnoreCase("") ? "Cci12345" : custPassword;

        if (!custPhoneNum.equalsIgnoreCase("")) {
            String custAccessToken = authServices.getCustomerToken("1", custPhoneNum, custPassword);
            coreServices.cancelAllScheduledBungiis(custAccessToken);
        }
        log("I cancel Bungii as Admin","I canceled Bungii as Admin" ,true );
    } catch (Throwable e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error in canceling Bungii as admin ",
                true);
    }
}


    @And("^I open Admin portal and navigate to \"([^\"]*)\" page$")
    public void i_open_admin_portal_and_navigate_to_something_page(String option) throws Throwable {
        try {
            i_open_new_something_browser_for_something_instance("CHROME","ADMIN PORTAL");
            SetupManager.getDriver().navigate().to(utility.GetAdminUrl());
            action.sendKeys(logInPage.TextBox_Phone(),PropertyUtility.getDataProperties("admin.user"));
            action.sendKeys(logInPage.TextBox_Pass(),PropertyUtility.getDataProperties("admin.password"));
            action.click(logInPage.Button_LogIn());
            pass("I log in to admin portal",
                    "I got log in to admin portal", true);

            switch (option.toLowerCase()) {
                case "scheduled deliveries":
                    action.click(dashBoardPage.Button_Trips());
                    Thread.sleep(3000);
                    action.click(dashBoardPage.Button_ScheduledTrips());
                    break;
                case "live deliveries":
                    Thread.sleep(3000);
                    action.click(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_LiveTrips());
                    break;
                case "promo code":
                    action.click(dashBoardPage.Button_PromoCode());
                    action.click(dashBoardPage.Link_StandardCodes());
                    break;
                case "referral source":
                    action.click(dashBoardPage.Button_Marketing());
                    action.click(dashBoardPage.Button_ReferralSource());
                    break;
                case "customers":
                    action.click(dashBoardPage.Button_Customers());
                    break;
                case "deliveries":
                    action.click(dashBoardPage.Button_Trips());
                    break;
                case "geofence":
                    Thread.sleep(2000);
                    action.click(dashBoardPage.Menu_Geofences());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I open Admin portal and navigate to "+option+ " page","I am on admin "+ option+" page" ,true );
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in navigating to admin portal ",
                    true);
        }
    }

    /*
    This method is rewritten for using in some scenarios for logging in as particular geofence customer.
     */
    @And("^I logged in as \"([^\"]*)\" customer$")
    public void i_logged_in_as_something_customer(String key) throws Throwable {
        try {
            String NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar());
            String userName = "", password = "";
            switch (key.toLowerCase()) {
                case "valid existing":
                    userName = PropertyUtility.getDataProperties("valid.existing.customer.phone");
                    password = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("valid.existing.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    cucumberContextManager.setScenarioContext("CUSTOMER_PASSWORD", password);
                    break;
                case "valid existing stack":
                    userName = PropertyUtility.getDataProperties("valid.existing.stackcustomer.phone");
                    //userName = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE") ;
                    password = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("valid.existing.stackcustomer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    cucumberContextManager.setScenarioContext("CUSTOMER_PASSWORD", password);
                    break;
                case "existing":
                    userName = PropertyUtility.getDataProperties("customer.user");
                    password = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "existing app user":
                    userName = PropertyUtility.getDataProperties("customer.user.hasTrip");
                    password = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name.hasTrip"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case"newly created user":
                    userName = (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");
                    password = PropertyUtility.getDataProperties("customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "new":
                    userName = PropertyUtility.getDataProperties("new.customer.user");
                    password = PropertyUtility.getDataProperties("new.customer.password");
                    break;
                case "referral":
                    userName = PropertyUtility.getDataProperties("referral.customer.phone");
                    password = PropertyUtility.getDataProperties("referral.customer.password");
                    break;
                case "customer-duo":
                    userName = PropertyUtility.getDataProperties("customer.phone.usedin.duo");
                    password = PropertyUtility.getDataProperties("customer.password.usedin.duo");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("customer.name.usedin.duo"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid miami":
                    userName = PropertyUtility.getDataProperties("miami.customer.phone");
                    password = PropertyUtility.getDataProperties("miami.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("miami.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid miami 2":
                    userName = PropertyUtility.getDataProperties("miami.customer2.phone");
                    password = PropertyUtility.getDataProperties("miami.customer2.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("miami.customer2.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid nashville":
                    userName = PropertyUtility.getDataProperties("nashville.customer.phone");
                    password = PropertyUtility.getDataProperties("nashville.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("nashville.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid nashville2":
                    userName = PropertyUtility.getDataProperties("nashville.customer2.phone");
                    password = PropertyUtility.getDataProperties("nashville.customer2.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("nashville.customer2.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid nashville first time":
                    userName = PropertyUtility.getDataProperties("nashville.common.customer.phone");
                    password = PropertyUtility.getDataProperties("nashville.common.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("nashville.common.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid denver":
                    userName = PropertyUtility.getDataProperties("denver.customer.phone");
                    password = PropertyUtility.getDataProperties("denver.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("denver.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                case "valid customer2":
                    userName = PropertyUtility.getDataProperties("customer.phone.usedin.duo");
                    password = PropertyUtility.getDataProperties("customer.password.usedin.duo");
                    cucumberContextManager.setScenarioContext("CUSTOMER2", PropertyUtility.getDataProperties("customer.name.usedin.duo"));
                    cucumberContextManager.setScenarioContext("CUSTOMER2_PHONE", userName);
                    break;
                case "valid chicago":
                    userName = PropertyUtility.getDataProperties("chicago.customer.phone");
                    password = PropertyUtility.getDataProperties("chicago.customer.password");
                    cucumberContextManager.setScenarioContext("CUSTOMER", PropertyUtility.getDataProperties("chicago.customer.name"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", userName);
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            goToLogInPage(NavigationBarName);

            LogInSteps logInSteps = new LogInSteps(loginPage);
            logInSteps.i_enter_valid_and_as_per_below_table(userName, password);
            action.click(loginPage.Button_Login());

            Thread.sleep(4000);

            NavigationBarName = action.getScreenHeader(homePage.Text_NavigationBar(true));

            if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
                new GeneralUtility().navigateFromTermToHomeScreen();
            }
            //  new GeneralUtility().logCustomerDeviceToken(userName);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }
    public void swipeForTripDetails(){
        WebElement sliderStart = bungiiRequestPage.Text_SwipeUpDetails();
        WebElement sliderEnd = bungiiRequestPage.Navbar_DeliveryProgress();
        Rectangle initialPoint;
        Rectangle finalPoint;
        initialPoint = action.getLocatorRectangle(sliderStart);
        finalPoint = action.getLocatorRectangle(sliderEnd);
        action.dragFromToForDuration(initialPoint.getX(),initialPoint.getY(),finalPoint.getX(),finalPoint.getY(),1);


    }
    @And("^I change the service level to \"([^\"]*)\" in \"([^\"]*)\" portal$")
    public void i_change_the_service_level_to_something_in_something_portal(String Service_Name, String Site_Name) throws Throwable {
        try {
            switch (Site_Name) {
                case "Admin":
                    //action.click(Page_Admin_ScheduledTrips.Admin_Dropdown_ServiceLevel(Service_Name));
                    action.selectElementByText(scheduledTripsPage.Admin_Dropdown_ServiceLevel(), Service_Name);
                    cucumberContextManager.setScenarioContext("Change_service", Service_Name);
                    break;
                default:
                    logger.error("Wrong site name is pass.Please Pass correct site.");
            }
            log("I should able to change the service level to " + Service_Name, "Service name should get changed to " + Service_Name, true);

        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to change the service " + Service_Name + "for" + Site_Name + "portal",
                    true);
        }
    }

    @And("^Driver status should be \"([^\"]*)\"$")
    public void driver_status_should_be_something(String DriverStatus) throws Throwable {
        try {
            String phoneNumber= (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
            switch (DriverStatus){
                case "Online":
                    String expectedDriverOnlineStatus ="1";
                    String driverOnlineStatus = com.bungii.web.utilityfunctions.DbUtility.getDriverStatus(phoneNumber);
                    testStepAssert.isEquals(driverOnlineStatus,expectedDriverOnlineStatus,"Driver status should be online","Driver Status is online","Driver status is not online");
                    break;
                case "Offline":
                    String driverStatus ="0";
                    String driverOfflineStatus = com.bungii.web.utilityfunctions.DbUtility.getDriverStatus(phoneNumber);
                    testStepAssert.isEquals(driverOfflineStatus,driverStatus,"Driver status should be offline","Driver Status is offline","Driver status is not offline");
                    break;
            }
        } catch (Throwable e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful",
                "Error performing step,Please check logs for more details", true);
    }
    }
    @And("^I verify alias is displayed correctly on \"([^\"]*)\"$")
    public void i_verify_alias_is_displayed_correctly_on_something(String page) throws Throwable {
        try {
            String aliasPartnerPortalName= PropertyUtility.getDataProperties("partner.floor.and.decor.alias.name");
            switch (page){
                case "live delivery page":
                    Thread.sleep(2000);
                    testStepAssert.isEquals(action.getText(scheduledTripsPage.Text_PartnerNameLiveDeliveryPage()),aliasPartnerPortalName,
                            "The portal name displayed should be correct",
                            "The portal name displayed is correct",
                            "The portal name displayed is incorrect");
                    break;
            }
            log("I should be able to verify the alias is displayed correctly",
                    "I am able to verify the alias is displayed correctly",false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see \"([^\"]*)\" header displayed$")
    public void i_should_see_something_header_displayed(String strArg1) throws Throwable {
        try{
        action.swipeUP();
        Thread.sleep(3000);
        switch (strArg1){
            case "SOLO LIFT":
                boolean isSoloLiftDisplayed = scheduledTripsPage.Label_SoloLift().isDisplayed();
                testStepAssert.isTrue(isSoloLiftDisplayed,"Solo Lift label should be displayed","Solo Lift label is displayed","Solo Lift label is not displayed");
                String expectedSoloLiftMessage = PropertyUtility.getDataProperties("solo.lift.message");
                String soloLiftInstructions = action.getText(scheduledTripsPage.Text_SoloLiftMessage());
                testStepVerify.isEquals(soloLiftInstructions,expectedSoloLiftMessage,expectedSoloLiftMessage+" Message should be displayed",soloLiftInstructions+" Message is displayed",expectedSoloLiftMessage+" Message is not displayed");
                break;
            case "CUSTOMER HELP":
                boolean isCustomerHelpLabelDisplayed = scheduledTripsPage.Label_CustomerHelp().isDisplayed();
                testStepAssert.isTrue(isCustomerHelpLabelDisplayed,"Solo Lift header should be displayed","Solo Lift header is displayed","Solo Lift header is not displayed");
                String expectedCustomerHelpMessage = PropertyUtility.getDataProperties("customer.help.message");
                String customerHelpInstructions = action.getText(scheduledTripsPage.Text_CustomerHelpMessage());
                testStepVerify.isEquals(customerHelpInstructions,expectedCustomerHelpMessage,expectedCustomerHelpMessage+" Message should be displayed",customerHelpInstructions+" Message is displayed",expectedCustomerHelpMessage+" Message is not displayed");
                break;
            case "DUO LIFT":
                boolean isDuoLiftDisplayed = scheduledTripsPage.Label_DuoLift().isDisplayed();
                testStepAssert.isTrue(isDuoLiftDisplayed,"Duo Lift label should be displayed","Duo Lift label is displayed","Duo Lift label is not displayed");
                String expectedDuoLiftMessage = PropertyUtility.getDataProperties("duo.lift.message");
                String duoLiftInstructions = action.getText(scheduledTripsPage.Text_DuoLiftMessage());
                testStepVerify.isEquals(duoLiftInstructions,expectedDuoLiftMessage,expectedDuoLiftMessage+" Message should be displayed",duoLiftInstructions+" Message is displayed",expectedDuoLiftMessage+" Message is not displayed");
                break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I click on the \"([^\"]*)\" link beside scheduled bungii for \"([^\"]*)\"$")
    public void i_click_on_the_something_link_beside_scheduled_bungii_for_something(String strArg1, String deliveryType) throws Throwable {
        try{
            switch (deliveryType){
                case "Completed Deliveries":
                    Thread.sleep(4000);
                    action.click(scheduledTripsPage.Link_DeliveryDetails());
                    Thread.sleep(2000);
                    action.click(scheduledTripsPage.List_ViewDeliveries());
                    break;
            }
            log("I should be able to click on "+deliveryType+" link","I could click on "+deliveryType+" link",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^The \"([^\"]*)\" deliveries should have a lead time for \"([^\"]*)\" partner portal$")
    public void the_something_deliveries_should_have_a_lead_time_for_something_partner_portal(String deliveryType, String partnerLocation) throws Throwable {
       try{
        switch (partnerLocation){
            case "Kansas":
                ZoneId zoneId = ZoneId.of("America/Chicago");
                ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), zoneId);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
                String expectedTime = zonedDateTime.format(formatter);
                if(deliveryType.equalsIgnoreCase("Solo")){
                   String timeInMiliSeconds = dbUtility.getPartnerPortalLeadTimeSoloDelivery();
                    int timeInMinutes = Integer.parseInt(timeInMiliSeconds)/60000;
                    zonedDateTime = zonedDateTime.plusMinutes(timeInMinutes);

                }
                else{
                    String timeInMiliSeconds = dbUtility.getPartnerPortalLeadTimeDuoDelivery();
                    int timeInMinutes = Integer.parseInt(timeInMiliSeconds)/60000;
                    zonedDateTime = zonedDateTime.plusMinutes(timeInMinutes).minusMinutes(2);
                }
                expectedTime = zonedDateTime.format(formatter);
                int minutes = zonedDateTime.getMinute();
                int difference = 0;
                if(minutes > 0 && minutes < 15) {
                    difference = 15 - minutes;
                } else if (minutes > 15 && minutes < 30) {
                    difference = 30 - minutes;
                } else if (minutes > 30  && minutes < 45) {
                    difference = 45 - minutes;
                } else if(minutes > 45) {
                    difference = 60 - minutes;
                }
                zonedDateTime =  zonedDateTime.plusMinutes(difference);
                expectedTime = zonedDateTime.format(formatter);
                cucumberContextManager.setScenarioContext("ExpectedLeadTimeDelivery",expectedTime);
                break;

        }
        log("I should be able to get the current time from" +partnerLocation+ " and  partner specifed lead time",
                "I could get the current time from" +partnerLocation+ " and  partner specifed lead time",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @Then("^\"([^\"]*)\" icon should be displayed in all deliveries details page$")
    public void something_icon_should_be_displayed_in_all_deliveries_details_page(String expectedText) throws Throwable {
        try{
            Thread.sleep(2000);
            String expectedBackgroundColor =PropertyUtility.getDataProperties("customer.help.highlight");
            testStepAssert.isTrue(action.isElementPresent(scheduledTripsPage.Icon_CustomerHelpAdminPortal()),"Customer Help Icon should be displayed","Customer Help icon is displayed","Customer help icon is not displayed");
            String backgroundIconColor = scheduledTripsPage.Icon_CustomerHelpAdminPortal().getCssValue("background-color");
            testStepAssert.isEquals(backgroundIconColor,expectedBackgroundColor,"Icon should have yellow highlight","Icon has yellow highlight","Icon doesnt have yellow highlight");
            String iconText =action.getText(scheduledTripsPage.Icon_CustomerHelpAdminPortal()).toLowerCase();
            testStepAssert.isEquals(iconText,expectedText.toLowerCase(),"The text should be "+ expectedText,"The text is "+iconText,"The text is not  "+ expectedText);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^The first timeslot should display the time including the provided partner portal lead time$")
    public void the_first_timeslot_should_display_the_time_including_the_provided_partner_portal_lead_time() throws Throwable {
        try{
        Thread.sleep(5000);
        String realTimeWithLead = (String) cucumberContextManager.getScenarioContext("ExpectedLeadTimeDelivery");
        String expectedTimeWithLead = action.getText(scheduledTripsPage.Text_PickupTime());
        testStepAssert.isEquals(expectedTimeWithLead,realTimeWithLead,"Partner portal with lead time should be "+realTimeWithLead,"Partner portal with lead is  "+expectedTimeWithLead,"Partner portal with lead time is not "+realTimeWithLead);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The \"([^\"]*)\" delivery shoudnt have lead time$")
    public void the_something_delivery_shoudnt_have_lead_time(String deliveryType) throws Throwable {
        try {
            switch (deliveryType) {
                case "SOLO":
                    ZoneId zoneIdForNashVille = ZoneId.of("America/Chicago");
                    ZonedDateTime zonedDateTimeNashville = ZonedDateTime.ofInstant(Instant.now().plusSeconds(1800), zoneIdForNashVille);
                    DateTimeFormatter formatterNashville = DateTimeFormatter.ofPattern("hh:mm a");
                    String expectedTimeNashville = zonedDateTimeNashville.format(formatterNashville);
                    System.out.println(expectedTimeNashville);
                    int minutes = zonedDateTimeNashville.getMinute();
                    int difference = 0;
                    if (minutes > 0 && minutes < 15) {
                        difference = 15 - minutes;
                    } else if (minutes > 15 && minutes < 30) {
                        difference = 30 - minutes;
                    } else if (minutes > 30 && minutes < 45) {
                        difference = 45 - minutes;
                    } else if (minutes > 45) {
                        difference = 60 - minutes;
                    }
                    zonedDateTimeNashville = zonedDateTimeNashville.plusMinutes(difference);
                    expectedTimeNashville = zonedDateTimeNashville.format(formatterNashville);
                    cucumberContextManager.setScenarioContext("PartnerPortalWithoutLeadTime", expectedTimeNashville);
                    break;
                case "DUO":
                    ZoneId zoneIdForNashVilleDuo = ZoneId.of("America/Chicago");
                    ZonedDateTime zonedDateTimeNashvilleDuo = ZonedDateTime.ofInstant(Instant.now().plusSeconds(1800).minusSeconds(120), zoneIdForNashVilleDuo);
                    DateTimeFormatter formatterNashvilleDuo = DateTimeFormatter.ofPattern("hh:mm a");
                    String expectedTimeNashvilleDuo = zonedDateTimeNashvilleDuo.format(formatterNashvilleDuo);
                    System.out.println(expectedTimeNashvilleDuo);
                    int minutesDuo = zonedDateTimeNashvilleDuo.getMinute();
                    int differenceDuo = 0;
                    if (minutesDuo > 0 && minutesDuo < 15) {
                        differenceDuo = 15 - minutesDuo;
                    } else if (minutesDuo > 15 && minutesDuo < 30) {
                        differenceDuo = 30 - minutesDuo;
                    } else if (minutesDuo > 30 && minutesDuo < 45) {
                        differenceDuo = 45 - minutesDuo;
                    } else if (minutesDuo > 45) {
                        differenceDuo = 60 - minutesDuo;
                    }
                    zonedDateTimeNashvilleDuo = zonedDateTimeNashvilleDuo.plusMinutes(differenceDuo);
                    expectedTimeNashvilleDuo = zonedDateTimeNashvilleDuo.format(formatterNashvilleDuo);
                    cucumberContextManager.setScenarioContext("PartnerPortalWithoutLeadTime", expectedTimeNashvilleDuo);
                    break;
            }
            log("I should be able to get the current time based on geofence","I could get the current time based on geofence",false);
        }catch(Exception e){
                logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                error("Step should be successful", "Error performing step,Please check logs for more details",
                        true);
            }
        }

    @Then("^The First timeslot should display the time without partner portal lead time$")
    public void the_first_timeslot_should_display_the_time_without_partner_portal_lead_time() throws Throwable {
    try{
        Thread.sleep(10000);
        String realTimeWithoutLead = (String) cucumberContextManager.getScenarioContext("PartnerPortalWithoutLeadTime");
        String expectedTimeWithoutLead = action.getText(scheduledTripsPage.Text_PickupTime());
        testStepAssert.isEquals(expectedTimeWithoutLead,realTimeWithoutLead,"Partner portal without lead time should be "+realTimeWithoutLead,"Partner portal without lead is  "+expectedTimeWithoutLead,"Partner portal without lead time is not "+realTimeWithoutLead);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
        true);
        }
    }
    @Then("^I check if miles are updated for \"([^\"]*)\" in \"([^\"]*)\"$")
    public void i_check_if_miles_are_updated_for_something_in_something(String edit, String stage) throws Throwable {
        try{
            String reference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            switch (stage){
                case "Loading":
                    switch (edit){
                        case "pick-up":
                            String[] newLocations = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                            String[] newPickupLocations = new String[2];
                            newPickupLocations[0] = newLocations[0];
                            newPickupLocations[1] = newLocations[1];
                            String[] oldPickupLocations = new String[2];
                            oldPickupLocations[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_PICKUP");
                            oldPickupLocations[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_PICKUP");
                            String newPickupToOldPickup = new GoogleMaps().getMilesWithLatLong(oldPickupLocations, newPickupLocations);

                            String[] oldDropLocation = new String[2];
                            oldDropLocation[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_DROPOFF");
                            oldDropLocation[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_DROPOFF");
                            String newPickupToOldDropOff = new GoogleMaps().getMilesWithLatLong(newPickupLocations, oldDropLocation);

                            float distance = Float.parseFloat(newPickupToOldPickup) + Float.parseFloat(newPickupToOldDropOff);
                            String actualMiles= dashBoardPage.Text_DeliveryMiles().getText();
                            cucumberContextManager.setScenarioContext("MILES",actualMiles.substring(0,5));
                            testStepVerify.isEquals(actualMiles.substring(0,4), String.valueOf(distance),
                                    "The miles displayed should be correct after admin edit.",
                                    "The miles displayed is incorrect after admin edit.");

                            break;
                    }
                    break;
                case "driving to dropoff":
                    switch (edit){
                        case "drop-off":
//                            P1 to D1+ D1 to D2
                            String[] newLocations = DbUtility.getLatAndLonPickupAndDropLocation(reference);
                            String[] driverLocation = new String[2];
                            driverLocation[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_DROPOFF");
                            driverLocation[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_DROPOFF");
                            String[] oldPickupLocations = new String[2];
                            oldPickupLocations[0] = (String) cucumberContextManager.getScenarioContext("OLD_LAT_PICKUP");
                            oldPickupLocations[1] = (String) cucumberContextManager.getScenarioContext("OLD_LONG_PICKUP");
                            String oldPickupToDriver = new GoogleMaps().getMilesWithLatLong(oldPickupLocations, driverLocation);

                            String[] newDropLocation = new String[2];
                            newDropLocation[0] = newLocations[2];
                            newDropLocation[1] = newLocations[3];
                            String driverLocToDropOff = new GoogleMaps().getMilesWithLatLong(driverLocation, newDropLocation);
                            float distance = Float.parseFloat(oldPickupToDriver) + Float.parseFloat(driverLocToDropOff);
                            String actualMiles= dashBoardPage.Text_DeliveryMiles().getText();
                            cucumberContextManager.setScenarioContext("MILES",actualMiles.substring(0,5));
                            testStepVerify.isEquals(actualMiles.substring(0,4), String.valueOf(distance),
                                    "The miles displayed should be correct after admin edit.",
                                    "The miles displayed is incorrect after admin edit.");
                            break;
                    }
                    break;
            }
            log("I should be able to see updated miles.","I am able to see updated miles.",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I check if correct \"([^\"]*)\" is displayed on delivery details$")
    public void i_check_if_correct_something_is_displayed_on_delivery_details(String costType) throws Throwable {
        try{
            Float miles= Float.parseFloat((String) cucumberContextManager.getScenarioContext("MILES"));
            String reference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            DecimalFormat df = new DecimalFormat("0.00");
            switch (costType){
                case "customer price-loading":
                    String[] range = utility.getMilesRange(miles);
                    String amount = dbUtility.getFixedBasedPrice(range[0],range[1]);
                    String actualAmt= liveTripsPage.Text_EstimatedCharge().getText();
                    testStepVerify.isEquals(actualAmt.substring(1), amount,
                            "The estimated cost  displayed should be correct after admin edit.",
                            "The estimated cost displayed is incorrect after admin edit.");
                    String driverAmt = dbUtility.getFixedBasedDriverCut(range[0],range[1]);
                    String actualDriverAmt= scheduledTripsPage.Text_SoloDriverEarnings().getText();
                    testStepVerify.isEquals(actualDriverAmt.substring(1), driverAmt,
                            "The driver earnings  displayed should be correct after admin edit.",
                            "The driver earnings displayed is incorrect after admin edit.");
                    break;
                case "customer price-driving to dropoff":
                    float estimateCharge = Float.parseFloat(action.getText(scheduledTripsPage.Text_EstimateCharge()).substring(1));
                    String[] range1 = utility.getMilesRange(miles);
                    float amount1 = Float.parseFloat(dbUtility.getDriverShareWeightBased(range1[0],range1[1]));
                    float merchantAmt=(float) (Math.round((estimateCharge-amount1)* 100.0) / 100.0);
                    float transFeeSolo= (float) (Math.round(((merchantAmt+amount1)*0.029+0.30)* 100.0) / 100.0);
                    float driverEarningsCalculated =(float) (Math.round((amount1-transFeeSolo)* 100.0) / 100.0);
                    float driverShareCalculatedRound = (float) (Math.round(driverEarningsCalculated * 100.0) / 100.0);
                    String actualAmt1= scheduledTripsPage.Text_DuoDriver1Earnings().getText();
                    testStepVerify.isEquals(actualAmt1.substring(1), String.valueOf(driverShareCalculatedRound),
                            "The driver share displayed should be correct after admin edit.",
                            "The driver share displayed is incorrect after admin edit.");
                    break;
            }
            log("I should be able to see the correct estimated charge and driver charge.","I am able to see the correct estimated charge and driver charge.",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I get the old values of pickup and drop off$")
    public void i_get_the_old_values_of_pickup_and_drop_off() throws Throwable {
        try{
            String reference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String[] locations = com.bungii.web.utilityfunctions.DbUtility.getLatAndLonPickupAndDropLocation(reference);

            String[] oldPickupLocations = new String[2];
            oldPickupLocations[0] = locations[0];
            oldPickupLocations[1] = locations[1];
            String[] oldDropLocation = new String[2];
            oldDropLocation[0] = locations[2];
            oldDropLocation[1] = locations[3];

            cucumberContextManager.setScenarioContext("OLD_LAT_PICKUP",oldPickupLocations[0]);
            cucumberContextManager.setScenarioContext("OLD_LONG_PICKUP",oldPickupLocations[1]);
            cucumberContextManager.setScenarioContext("OLD_LAT_DROPOFF",oldDropLocation[0]);
            cucumberContextManager.setScenarioContext("OLD_LONG_DROPOFF",oldDropLocation[1]);

            log("I should be able to get old latitude and longitude values of addresses.","I am able to get old latitude and longitude values of addresses.",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^The \"([^\"]*)\" \"([^\"]*)\" should not be displayed$")
    public void the_something_something_should_not_be_displayed(String element, String strArg2) throws Throwable {
        try{
            switch (element){
                case "Contact Duo Teammate":
                    Thread.sleep(3000);
                    testStepAssert.isFalse(action.isElementPresent(updateStatusPage.Text_ContactDuo(true)),"Contact Duo text should not be displayed","Contact Duo text is not displayed","Contact Duo text is displayed");
                    testStepAssert.isFalse(action.isElementPresent(updateStatusPage.Text_TeamMate(true)),"Teammate text should not be displayed","Teammate text is not displayed","Teammate text is displayed");
                    break;
            }
        }    catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The \"([^\"]*)\" should match$")
    public void the_something_should_match(String strArg1) throws Throwable {
        try{
         int driverTime= Integer.parseInt(PropertyUtility.getDataProperties("driver.buffer.drive.time"));
         String pickupReference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String []ArrivalTimeAndUnloadingLoadingTime = DbUtility.getArrivalTimeAndLoadingUnloadingTime(pickupReference);
        switch (strArg1){
            case "Arrival time":
                // based on the scheduled  delivery time in utc
                String calculatedArrivalTime = ConvertTimeToTheRequiredGeoFence(ArrivalTimeAndUnloadingLoadingTime[1].split(" "));

                if(calculatedArrivalTime.startsWith("0")){

                    String hourWithoutZero =calculatedArrivalTime.replaceFirst("0","");
                    cucumberContextManager.setScenarioContext("ArrivalTime",hourWithoutZero);
                }

                else {
                    cucumberContextManager.setScenarioContext("ArrivalTime",calculatedArrivalTime);
                }

                String arrivalTimeOnUi= action.getText(updateStatusPage.ArrivalTimeAtPickupValue());
                String properArrivalTime = (String) cucumberContextManager.getScenarioContext("ArrivalTime");

                testStepAssert.isEquals(arrivalTimeOnUi,properArrivalTime,"The arrival time should be "+ properArrivalTime,
                        "The arrival time time is  "+properArrivalTime,
                        "The  incorrect arrival time displayed is  "+properArrivalTime);
                break;
            case "stacked bungii":
                String onlyHours= (String) cucumberContextManager.getScenarioContext("Hours");
                String onlyMinutes=(String) cucumberContextManager.getScenarioContext("Minutes");

                int deliveryCreatedTimeInMinutes = (Integer.parseInt( onlyHours)*60) +Integer.parseInt( onlyMinutes) ;

                String[] delivery2pickupLatAndLong = DbUtility.getLatAndLonPickupAndDropLocation(pickupReference);
                String[] dropLocationOfFirstDelivery = new String[2];
                dropLocationOfFirstDelivery[0] =delivery2pickupLatAndLong[0];
                dropLocationOfFirstDelivery[1]=delivery2pickupLatAndLong[1];

                String[] pickUpLocationOfSecondDelivery = new String[2];
                pickUpLocationOfSecondDelivery[0] = (String) cucumberContextManager.getScenarioContext("onlyDropOffLat");
                pickUpLocationOfSecondDelivery[1] =(String) cucumberContextManager.getScenarioContext("onlyDropOffLong");;

                long[] timeFromDropOffTo2ndDeliveryPickup= new GoogleMaps().getDurationInTraffic(dropLocationOfFirstDelivery, pickUpLocationOfSecondDelivery);
//              for Stacked bungii  formula -->  ongoing Pickup TELET + Driver ongoing Drop-off to New Pickup
                int finalValue = (int) (deliveryCreatedTimeInMinutes + Math.round(timeFromDropOffTo2ndDeliveryPickup[0]/60));

                String arrivalTimeForStackedRequest= action.getText(updateStatusPage.ArrivalTimeAtPickupValueForStacked());
                String calculatedStackedDeliveryRequestTime =LocalTime.MIN.plus(Duration.ofMinutes( finalValue)).toString();

                if (calculatedStackedDeliveryRequestTime.startsWith("0")) {
                    String hourWithoutZero = calculatedStackedDeliveryRequestTime.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("StackedDeliveryArrivalTime", hourWithoutZero);
                } else {
                    cucumberContextManager.setScenarioContext("StackedDeliveryArrivalTime", calculatedStackedDeliveryRequestTime);
                }
                String stackedArrivalTime =(String) cucumberContextManager.getScenarioContext("StackedDeliveryArrivalTime");

                testStepVerify.isEquals(arrivalTimeForStackedRequest.substring(0,arrivalTimeForStackedRequest.length()-3),stackedArrivalTime,
                        "Expected Arrival time for stacked delivery should be "+stackedArrivalTime+" +/- 5 minutes max",
                        "Expected Arrival time for stacked delivery is  "+stackedArrivalTime+" +/- 5 minutes max");
                break;
            case "Ondemand bungii":
                String onDemandArrivalTIme = ConvertTimeToTheRequiredGeoFence(ArrivalTimeAndUnloadingLoadingTime[1].split(" "));
                String[] createdDeliveryInHoursAndMinutes =onDemandArrivalTIme.substring(0, onDemandArrivalTIme.length() - 3).split(":");
                String onlyHour= createdDeliveryInHoursAndMinutes[0];
                String onlyMinute = createdDeliveryInHoursAndMinutes[1];
                int deliveryCreatedTimeInMinute = (Integer.parseInt( onlyHour)*60) +Integer.parseInt( onlyMinute) ;

                String[] pickup1Locations = DbUtility.getLatAndLonPickupAndDropLocation(pickupReference);
                String[] pickup2Locations = DbUtility.getLatAndLonPickupAndDropLocation(pickupReference);

                String[] dropLocation = new String[2];
                dropLocation[0] = pickup1Locations[2];
                dropLocation[1] = pickup1Locations[3];
                String[] newPickupLocations = new String[2];
                newPickupLocations[0] = pickup2Locations[0];
                newPickupLocations[1] = pickup2Locations[1];

                long[] timeToCoverDistance = new GoogleMaps().getDurationInTraffic(newPickupLocations, dropLocation);
                //for ONdemand formula --> 	bungii created time + ETA
                int timeToCoverDistanceInMinutes = (int) (deliveryCreatedTimeInMinute + Math.round(timeToCoverDistance[0]/60));
                String arrivalTimeBasedOnCalculation = LocalTime.MIN.plus(Duration.ofMinutes( timeToCoverDistanceInMinutes)).toString();


                String arrivalTimeForOndemand= action.getText(updateStatusPage.Text_ArrivalTimeValue());

                if (arrivalTimeBasedOnCalculation.startsWith("0")) {
                    String hourWithoutZero = arrivalTimeBasedOnCalculation.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("ArrivalTime", hourWithoutZero);
                } else {
                    cucumberContextManager.setScenarioContext("ArrivalTime", arrivalTimeBasedOnCalculation);
                }
                String ondemandArrivalTime =(String) cucumberContextManager.getScenarioContext("ArrivalTime");


                testStepVerify.isEquals(arrivalTimeForOndemand.substring(0,arrivalTimeForOndemand.length()-3),ondemandArrivalTime,
                        "Expected On demand Arrival time for stacked delivery should be "+ondemandArrivalTime+" +/- 5 minutes max",
                        "Expected On demand Arrival time for stacked delivery on ui is  "+ondemandArrivalTime+" +/- 5 minutes max");
                break;
            case "Expected time at drop-off":
            case "Stacked delivery dropOff range":
            case "Ondemand delivery dropOff range":
            case "driver at arrival state":
            case"admin edits dropoff Address":
            case "Expected time at drop-off for duo":
                if(strArg1.contentEquals("Expected time at drop-off")){
                    String arrivalTime = (String) cucumberContextManager.getScenarioContext("ArrivalTime");
                    String[] hoursAndMinutes =arrivalTime.substring(0, arrivalTime.length() - 3).split(":");
                    String hours = hoursAndMinutes[0];
                    String minutes = hoursAndMinutes[1];
                    cucumberContextManager.setScenarioContext("Hours",hours);
                    cucumberContextManager.setScenarioContext("Minutes",minutes);
                    // for scheduled deliveries formula -->
                    // [Projected start time] + ([Projected LoadUnload Time] / 3) + [Projected Drive Time] + 40
                }
                else if((strArg1.contentEquals("Stacked delivery dropOff range"))||(strArg1.contentEquals("Ondemand delivery dropOff range"))){
                    String dropOffRangeTime = ConvertTimeToTheRequiredGeoFence(ArrivalTimeAndUnloadingLoadingTime[1].split(" "));
                    String[] createdDeliveryInHoursAndMinutess =dropOffRangeTime.substring(0, dropOffRangeTime.length() - 3).split(":");
                    String hours= createdDeliveryInHoursAndMinutess[0];
                    String minutes = createdDeliveryInHoursAndMinutess[1];
                    cucumberContextManager.setScenarioContext("Hours",hours);
                    cucumberContextManager.setScenarioContext("Minutes",minutes);
                    //for stacked delivery dropOff range and ondemand delivery dropoff range formula -->
                    // [bungii created time] + ([Projected LoadUnload Time] / 3) + [Projected Drive Time] + 40

                }
                else if ((strArg1.contentEquals("driver at arrival state"))){
                    String pickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    String changedDeliveryDetailsTime[]= DbUtility.getStatusTimestamp(pickupRef).split(" ");
                    String removedValueFromDot= changedDeliveryDetailsTime[1].substring(0, changedDeliveryDetailsTime[1].length() - 4);
                    changedDeliveryDetailsTime[1] = removedValueFromDot;
                    String arrivalStateAdminEdit = ConvertTimeToTheRequiredGeoFence(changedDeliveryDetailsTime);
                    String[] hoursAndMinutes =arrivalStateAdminEdit.substring(0, arrivalStateAdminEdit.length() - 3).split(":");
                    String hours = hoursAndMinutes[0];
                    String minutes = hoursAndMinutes[1];
                    cucumberContextManager.setScenarioContext("Hours",hours);
                    cucumberContextManager.setScenarioContext("Minutes",minutes);
                    //when admit edit live delivery when it is in arrival state and so on
                    // [recalc. arrived time] + ([Projected LoadUnload Time] / 3) + [Projected Drive Time] + 40
                }
                else if ((strArg1.contentEquals("admin edits dropoff Address"))){
                    String pickupId = DbUtility.getPickupId(pickupReference);
                    String changedDeliveryDetailsTime[]= DbUtility.getAdminEditTime(pickupId).split(" ");
                    String z = ConvertTimeToTheRequiredGeoFence(changedDeliveryDetailsTime);
                    String[] hoursAndMinutes =z.substring(0, z.length() - 3).split(":");
                    String hours = hoursAndMinutes[0];
                    String minutes = hoursAndMinutes[1];
                    cucumberContextManager.setScenarioContext("Hours",hours);
                    cucumberContextManager.setScenarioContext("Minutes",minutes);
                    //when admit edit live delivery when it is in enroute state only
                    //[address edited time] + ([Projected LoadUnload Time] / 3) + [Projected Drive Time] + 40
                }
                String hours =(String) cucumberContextManager.getScenarioContext("Hours");
                String minutes =(String) cucumberContextManager.getScenarioContext("Minutes");

                int convertHoursToMinutes = (Integer.parseInt( hours)*60) +Integer.parseInt( minutes) ;
                if (strArg1.contentEquals("Expected time at drop-off for duo")) {
                    String serviceBasedTime = (String) cucumberContextManager.getScenarioContext("ServiceBasedTotalTime");
                    cucumberContextManager.setScenarioContext("ServiceTimeOrUnloadingLoadingTIme", serviceBasedTime);
                } else {
                    int unloadingLoadingTimeWithoutServiceLevel = (int) Float.parseFloat(ArrivalTimeAndUnloadingLoadingTime[2]);
                    cucumberContextManager.setScenarioContext("ServiceTimeOrUnloadingLoadingTIme", unloadingLoadingTimeWithoutServiceLevel);
                }
                int unloadingLoadingTime = Integer.parseInt((String) cucumberContextManager.getScenarioContext("ServiceTimeOrUnloadingLoadingTIme"));
                int totalMinutes = convertHoursToMinutes  + (unloadingLoadingTime/3)+ (Integer.parseInt(ArrivalTimeAndUnloadingLoadingTime[0]))+driverTime;
                final SimpleDateFormat formatTochangeChangeTo12Hours = new SimpleDateFormat("hh:mm");

                String roundedTime =roundedUpTime(LocalTime.MIN.plus(Duration.ofMinutes( totalMinutes)).toString());
                LocalTime TimeInhours =LocalTime.parse(roundedTime);
                String plus1Hour = formatTochangeChangeTo12Hours.format(formatTochangeChangeTo12Hours.parse(String.valueOf(TimeInhours.plusHours(1)))) ;
                String minus1Hour = formatTochangeChangeTo12Hours.format(formatTochangeChangeTo12Hours.parse(String.valueOf(TimeInhours.minusHours(1)))) ;
                cucumberContextManager.setScenarioContext("Timeplus1hour",plus1Hour);
                cucumberContextManager.setScenarioContext("Timeminus1hour",minus1Hour);

                String timeOneHourAhead = (String) cucumberContextManager.getScenarioContext("Timeplus1hour");
                String timeOneHourBack =(String) cucumberContextManager.getScenarioContext("Timeminus1hour") ;

                if (timeOneHourAhead.startsWith("0")) {
                    String hourWithoutZero = timeOneHourAhead.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("StartingDropOffTimeRange", hourWithoutZero);
                } else {
                    cucumberContextManager.setScenarioContext("StartingDropOffTimeRange", timeOneHourAhead);

                }
                if (timeOneHourBack.startsWith("0")) {
                    String hourWithoutZero = timeOneHourBack.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("EndingDropOffTimeRange", hourWithoutZero);
                } else {
                    cucumberContextManager.setScenarioContext("EndingDropOffTimeRange", timeOneHourBack);

                }

                if((strArg1.contentEquals("driver at arrival state")) || strArg1.contentEquals("admin edits dropoff Address")|| strArg1.contentEquals("Ondemand delivery dropOff range")){
                    action.swipeUP();
                    action.swipeUP();
                    Thread.sleep(2000);
                    String expectedDroffTimeRange= action.getText(updateStatusPage.Text_DropOffRangeFromDeliveryDetailsForChanges());
                    cucumberContextManager.setScenarioContext("DropoffTime",expectedDroffTimeRange);

                }
                else if(strArg1.contentEquals("Stacked delivery dropOff range")){
                    action.swipeUP();
                    action.swipeUP();
                    Thread.sleep(2000);
                    String expectedDroffTimeRange= action.getText(updateStatusPage.Text_ExpectedTimeAtDropOffForStacked());
                    cucumberContextManager.setScenarioContext("DropoffTime",expectedDroffTimeRange);
                }
                else{
                    String expectedDroffTimeRange= action.getText(updateStatusPage.Text_ExpectedTimeAtDropOff());
                    cucumberContextManager.setScenarioContext("DropoffTime",expectedDroffTimeRange);
                }

                String expectedDroffTimeRange= (String) cucumberContextManager.getScenarioContext("DropoffTime");
                String startingTimeRange =(String) cucumberContextManager.getScenarioContext("StartingDropOffTimeRange");
                String endingTimeRange =(String) cucumberContextManager.getScenarioContext("EndingDropOffTimeRange");

                if(expectedDroffTimeRange.contains("PM") && expectedDroffTimeRange.contains("AM")||expectedDroffTimeRange.contains("pm") && expectedDroffTimeRange.contains("am")){

                    String onlyTimeRange = expectedDroffTimeRange.replace("PM","").replace("AM","").replace(" ","");;
                    cucumberContextManager.setScenarioContext("UITimeRange",onlyTimeRange);
                }
                else {
                    String onlyTimeRange = expectedDroffTimeRange.substring(0, expectedDroffTimeRange.length()-3).replace(" ","");
                    cucumberContextManager.setScenarioContext("UITimeRange",onlyTimeRange);
                }

                String UITimeRange = (String) cucumberContextManager.getScenarioContext("UITimeRange");
                String calculatedDropoffTimeRange =endingTimeRange +"-"+startingTimeRange;
                cucumberContextManager.setScenarioContext("DropOffRangeCalculated",calculatedDropoffTimeRange);
                testStepAssert.isEquals(UITimeRange,calculatedDropoffTimeRange,"The dropOff time range should be "+ calculatedDropoffTimeRange,
                        "The dropOff time range is  "+calculatedDropoffTimeRange,
                        "The  incorrect dropOff time range displayed is  "+UITimeRange);
                break;
        }
        }    catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^The \"([^\"]*)\" for customer delivery should match$")
    public void the_something_for_customer_delivery_should_match(String strArg1) throws Throwable {
        try{
        int driverTime= Integer.parseInt(PropertyUtility.getDataProperties("driver.buffer.drive.time"));
        String custPhone = (String)cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        String custRef = DbUtility.getCustomerRefference(custPhone);
        String []ArrivalTimeAndUnloadingLoadingTime = DbUtility.getArrivalTimeAndLoadingUnloadingTimeForCustomer(custRef);
        switch (strArg1){
            case "Arrival time":
                String calculatedArrivalTime = ConvertTimeToTheRequiredGeoFence(ArrivalTimeAndUnloadingLoadingTime[1].split(" "));

                if(calculatedArrivalTime.startsWith("0")){

                    String hourWithoutZero =calculatedArrivalTime.replaceFirst("0","");
                    cucumberContextManager.setScenarioContext("ArrivalTime",hourWithoutZero);
                }

                else {
                    cucumberContextManager.setScenarioContext("ArrivalTime",calculatedArrivalTime);
                }

                String arrivalTimeOnUi= action.getText(updateStatusPage.ArrivalTimeAtPickupValueForStacked());
                String properArrivalTime = (String) cucumberContextManager.getScenarioContext("ArrivalTime");

                testStepAssert.isEquals(arrivalTimeOnUi,properArrivalTime,"The arrival time should be "+ properArrivalTime,
                        "The arrival time time is  "+properArrivalTime,
                        "The  incorrect arrival time displayed is  "+properArrivalTime);
                break;
            case "Expected time at drop-off":
                if(strArg1.contentEquals("Expected time at drop-off")){
                    String arrivalTime = (String) cucumberContextManager.getScenarioContext("ArrivalTime");
                    String[] hoursAndMinutes =arrivalTime.substring(0, arrivalTime.length() - 3).split(":");
                    String hours = hoursAndMinutes[0];
                    String minutes = hoursAndMinutes[1];
                    cucumberContextManager.setScenarioContext("Hours",hours);
                    cucumberContextManager.setScenarioContext("Minutes",minutes);
                    // for scheduled deliveries formula -->
                    // [Projected start time] + ([Projected LoadUnload Time] / 3) + [Projected Drive Time] + 40
                }
                String hours =(String) cucumberContextManager.getScenarioContext("Hours");
                String minutes =(String) cucumberContextManager.getScenarioContext("Minutes");

                int convertHoursToMinutes = (Integer.parseInt( hours)*60) +Integer.parseInt( minutes) ;
                if(strArg1.contentEquals("Expected time at drop-off for duo")){
                    String serviceBasedTime = (String) cucumberContextManager.getScenarioContext("ServiceBasedTotalTime");
                    cucumberContextManager.setScenarioContext("ServiceTimeOrUnloadingLoadingTIme",serviceBasedTime);
                }
                else {
                    int unloadingLoadingTimeWithoutServiceLevel = (int) Float.parseFloat(ArrivalTimeAndUnloadingLoadingTime[2]);
                    cucumberContextManager.setScenarioContext("ServiceTimeOrUnloadingLoadingTIme",unloadingLoadingTimeWithoutServiceLevel);
                }
                int unloadingLoadingTime = Integer.parseInt((String) cucumberContextManager.getScenarioContext("ServiceTimeOrUnloadingLoadingTIme"));
                int totalMinutes = convertHoursToMinutes  + (unloadingLoadingTime/3)+ (Integer.parseInt(ArrivalTimeAndUnloadingLoadingTime[0]))+driverTime;
                final SimpleDateFormat formatTochangeChangeTo12Hours = new SimpleDateFormat("hh:mm");

                String roundedTime =roundedUpTime(LocalTime.MIN.plus(Duration.ofMinutes( totalMinutes)).toString());
                LocalTime TimeInhours =LocalTime.parse(roundedTime);
                String plus1Hour = formatTochangeChangeTo12Hours.format(formatTochangeChangeTo12Hours.parse(String.valueOf(TimeInhours.plusHours(1)))) ;
                String minus1Hour = formatTochangeChangeTo12Hours.format(formatTochangeChangeTo12Hours.parse(String.valueOf(TimeInhours.minusHours(1)))) ;
                cucumberContextManager.setScenarioContext("Timeplus1hour",plus1Hour);
                cucumberContextManager.setScenarioContext("Timeminus1hour",minus1Hour);

                String timeOneHourAhead = (String) cucumberContextManager.getScenarioContext("Timeplus1hour");
                String timeOneHourBack =(String) cucumberContextManager.getScenarioContext("Timeminus1hour") ;

                if (timeOneHourAhead.startsWith("0")) {
                    String hourWithoutZero = timeOneHourAhead.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("StartingDropOffTimeRange", hourWithoutZero);
                } else {
                    cucumberContextManager.setScenarioContext("StartingDropOffTimeRange", timeOneHourAhead);

                }
                if (timeOneHourBack.startsWith("0")) {
                    String hourWithoutZero = timeOneHourBack.replaceFirst("0", "");
                    cucumberContextManager.setScenarioContext("EndingDropOffTimeRange", hourWithoutZero);
                } else {
                    cucumberContextManager.setScenarioContext("EndingDropOffTimeRange", timeOneHourBack);

                }

                String expectedDroffTimeRange= action.getText(updateStatusPage.Text_ExpectedTimeAtDropOffForStacked());
                String startingTimeRange =(String) cucumberContextManager.getScenarioContext("StartingDropOffTimeRange");
                String endingTimeRange =(String) cucumberContextManager.getScenarioContext("EndingDropOffTimeRange");

                if(expectedDroffTimeRange.contains("PM") && expectedDroffTimeRange.contains("AM")||expectedDroffTimeRange.contains("pm") && expectedDroffTimeRange.contains("am")){

                    String onlyTimeRange = expectedDroffTimeRange.replace("PM","").replace("AM","").replace(" ","");;
                    cucumberContextManager.setScenarioContext("UITimeRange",onlyTimeRange);
                }
                else {
                    String onlyTimeRange = expectedDroffTimeRange.substring(0, expectedDroffTimeRange.length()-3).replace(" ","");
                    cucumberContextManager.setScenarioContext("UITimeRange",onlyTimeRange);
                }

                String UITimeRange = (String) cucumberContextManager.getScenarioContext("UITimeRange");
                String calculatedDropoffTimeRange =endingTimeRange +"-"+startingTimeRange;
                cucumberContextManager.setScenarioContext("DropOffRangeCalculated",calculatedDropoffTimeRange);
                testStepAssert.isEquals(UITimeRange,calculatedDropoffTimeRange,"The dropOff time range should be "+ calculatedDropoffTimeRange,
                        "The dropOff time range is  "+calculatedDropoffTimeRange,
                        "The  incorrect dropOff time range displayed is  "+UITimeRange);

                break;
        }
        }    catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @Then("^I save the dropoff latitude and longitude of the first delivery$")
    public void i_save_the_dropoff_latitude_and_longitude_of_the_first_delivery() throws Throwable {
        try{
        String pickupReference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String teletTimeInDb = DbUtility.getTelet(pickupReference);
        String convertedTeletTime = ConvertTimeToTheRequiredGeoFence(teletTimeInDb.substring(0, teletTimeInDb.length() - 4).split(" "));
        String[] tcreatedDeliveryInHoursAndMinutes =convertedTeletTime.substring(0, convertedTeletTime.length() - 3).split(":");
        String onlyHours = tcreatedDeliveryInHoursAndMinutes[0];
        String onlyMinutes = tcreatedDeliveryInHoursAndMinutes[1];
        cucumberContextManager.setScenarioContext("Hours",onlyHours);
        cucumberContextManager.setScenarioContext("Minutes",onlyMinutes);;
        String[] location1PickupAndDropOffLatLong = DbUtility.getLatAndLonPickupAndDropLocation(pickupReference);
        cucumberContextManager.setScenarioContext("onlyDropOffLat",location1PickupAndDropOffLatLong[2]);
        cucumberContextManager.setScenarioContext("onlyDropOffLong",location1PickupAndDropOffLatLong[3]);;
        log("I should be able to save the dropoff latitide and longitude of the first delivery",
                "I could save the dropoff latitide and longitude of the first delivery",false);
        }    catch(Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    private String roundedUpTime(String IncorrectTime) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("hh:mm");
        Date dt = formatter.parse(IncorrectTime);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int unroundedMinutes = cal.get(Calendar.MINUTE);
        int mod = unroundedMinutes % 5;
        if (mod ==0){
            ; cal.add(Calendar.MINUTE,(0-mod));
        }
        else  if ( (mod>=1) && (mod < 3) ){
            cal.add(Calendar.MINUTE,(5-mod));
        }
        else {
            cal.add(Calendar.MINUTE,(5-mod));
        }

        String hourAndMinute = formatter.format(cal.getTime());
        logger.detail("The rounded up time for "+ IncorrectTime+ " time is "+ hourAndMinute);
        return hourAndMinute;
    }

    private String ConvertTimeToTheRequiredGeoFence(String[] uctToCstTime) {
        String date[] = uctToCstTime[0].split("-");
        String time[] = uctToCstTime[1].split(":");
        if(time[2].contains(".")){
            String seconds = time[2].substring(0,time[2].length()-4);
            cucumberContextManager.setScenarioContext("SecondsWithoutPointValue",seconds);
        }
        else {
            cucumberContextManager.setScenarioContext("SecondsWithoutPointValue",time[2]);

        }
        String seconds = (String) cucumberContextManager.getScenarioContext("SecondsWithoutPointValue");
        ZonedDateTime instant1 = ZonedDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),Integer.parseInt(time[0]),Integer.parseInt(time[1]),Integer.parseInt(seconds),0, ZoneId.of("UTC"));
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        ZonedDateTime instantInUTC = instant1.withZoneSameInstant(ZoneId.of(geofenceLabel));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String convertedTime = instantInUTC.format(formatter);
        return convertedTime;
    }

    @Then("^I should able to see \"([^\"]*)\" scheduled trip$")
    public void i_should_able_to_see_something_scheduled_trip(String strArg1) throws Throwable {
        try{
            List<WebElement> listOfScheduledTrip = updateStatusPage.List_ScheduledBungiis();
            switch (strArg1) {
                case "one":
                    testStepAssert.isTrue(listOfScheduledTrip.size() == 1, "There should be one scheduled trip", "There is/are "+listOfScheduledTrip.size()+" Scheduled trips");
                    break;
                default:
                    error("UnImplemented Step or incorrect scheduled delivery count", "Incorrect scheduled delivery count");
            }
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }
}
