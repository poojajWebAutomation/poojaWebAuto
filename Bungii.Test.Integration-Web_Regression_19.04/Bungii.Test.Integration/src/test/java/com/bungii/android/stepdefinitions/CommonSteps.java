package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.DashBoardPage;
import com.bungii.android.pages.admin.LogInPage;
import com.bungii.android.pages.admin.ScheduledTripsPage;
import com.bungii.android.pages.customer.*;
import com.bungii.android.pages.customer.LocationPage;
import com.bungii.android.pages.driver.*;
import com.bungii.android.pages.otherApps.ChromePage;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.EmailUtility;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.google.common.collect.ImmutableMap;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.common.manager.ResultManager.*;

public class CommonSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(CommonSteps.class);
    private static String ANDROID_PHOTO_PATH = "/sdcard/Pictures";
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    EmailUtility emailUtility = new EmailUtility();
    EstimatePage estimatePage = new EstimatePage();
    HomePage homePage = new HomePage();
    DriverHomePage driverHomePage = new DriverHomePage();
    InProgressBungiiPages inProgressBungiiPages = new InProgressBungiiPages();
    DriverNotAvailablePage driverNotAvailablePage = new DriverNotAvailablePage();
    BungiiDetailsPage bungiiDetailsPage = new BungiiDetailsPage();
    BungiiRequest bungiiRequest = new BungiiRequest();
    BungiiAcceptedPage bungiiAcceptedPage = new BungiiAcceptedPage();
    LocationPage locationPage = new LocationPage();
    SignupPage Page_Signup= new SignupPage();
    TripDetailsPage tripDetailsPage = new TripDetailsPage();
    private DbUtility dbUtility = new DbUtility();
    com.bungii.android.pages.driver.LoginPage driverLoginPage = new com.bungii.android.pages.driver.LoginPage();
    LogInPage logInPage=  new LogInPage();
    DashBoardPage dashBoardPage=new DashBoardPage();
    PhonePage phonePage = new PhonePage();
    ChromePage chromePage = new ChromePage();
    ScheduledTripsPage scheduledTripsPage = new ScheduledTripsPage();
    AvailableTripsPage availableTrips = new AvailableTripsPage();
    DashBoardPage admin_dashboardPage = new DashBoardPage();
    TripAlertSettingsPage tripAlertSettingsPage = new TripAlertSettingsPage();

    @Given("^I have Large image on my device$")
    public void i_have_large_image_on_my_device() throws Throwable {
        List<String> removePicsArgs = Arrays.asList(

                "/sdcard/ALARGE_IMAGE/"
        );
        Map<String, Object> removePicsCmd = ImmutableMap.of(
                "command", "ls ",
                "args", removePicsArgs
        );
        ((AndroidDriver) SetupManager.getDriver()).executeScript("mobile: shell", removePicsCmd);

        String pickupImage = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"), PropertyUtility.getImageLocations("LARGE_IMAGE"));

        File img = new File(pickupImage);

        ((AndroidDriver) SetupManager.getDriver()).pushFile(ANDROID_PHOTO_PATH + "/" + img.getName(), img);

    }
    @When("^I terminate \"([^\"]*)\" app on \"([^\"]*)\" devices$")
    public void i_terminate_app(String appName, String device) {
        boolean isApplicationIsInForeground = false;

        try {
            if(action.isElementPresent(phonePage.Container_Notification(true)))
            {
                ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                logger.detail("Attempted to hide container");

            }
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(5000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    ((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                    break;
                case "CUSTOMER":
                    ((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }


                pass("Terminated " + appName + " application", "Termination of " + appName + " application is successful");

            //    Thread.sleep(5000);
            //     testStepVerify.isTrue(isApplicationIsInForeground, "Switch to " + appName + " application", "Switch to " + appName + " application is successful", "Switch to " + appName + " application was not successfull");
        } catch (Throwable e) {
        }

    }
    @When("^I Switch to \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_switch_to_something_application_on_something_devices(String appName, String device) {
        boolean isApplicationIsInForeground = false;
        try {
            if(action.isElementPresent(phonePage.Container_Notification(true)))
            {
                ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                logger.detail("Attempted to hide container");

            }
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(3000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    //((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));

                    ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));

                    //  utility.launchDriverApplication();
                    Thread.sleep(5000);
                    isApplicationIsInForeground = utility.isDriverApplicationOpen();
                    break;
                case "CUSTOMER":
                    //  utility.launchCustomerApplication();
                    //((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));

                    ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    Thread.sleep(5000);
                    isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        try {
            //if switch was unsucessfull, try to switch again
            if (!isApplicationIsInForeground) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        utility.launchDriverApplication();
                        //SetupManager.getObject().launchApp(PropertyUtility.getProp("bundleId_Driver"));
                        Thread.sleep(5000);
                        isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        }
                        break;
                    case "CUSTOMER":
                        utility.launchCustomerApplication();
                        // SetupManager.getObject().restartApp();
                        Thread.sleep(4000);
                        isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        }
                        break;
                    default:
                        error("UnImplemented Step or in correct app", "UnImplemented Step");
                        break;
                }
            }
            Thread.sleep(2000);
            if (!isApplicationIsInForeground)
                warning("Switch to " + appName + " application", "Not able to currently verify if " + appName + " application was not successfull");
            if(action.isElementPresent(phonePage.Container_Notification(true)))
            {
                ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                logger.detail("Attempted to hide container");

            }
            else
                pass("Switch to " + appName + " application", "Switch to " + appName + " application is successful");

            //    Thread.sleep(5000);
            //     testStepVerify.isTrue(isApplicationIsInForeground, "Switch to " + appName + " application", "Switch to " + appName + " application is successful", "Switch to " + appName + " application was not successfull");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }
    @When("^I go to \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_switch_to_application_on_something_devices(String appName, String device) {
        boolean isApplicationIsInForeground = false;

        try {
            if(action.isElementPresent(phonePage.Container_Notification(true)))
            {
                ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                logger.detail("Attempted to hide container");

            }
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(5000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                   // ((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));

                    ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));

                    //  utility.launchDriverApplication();
                    Thread.sleep(5000);
                    isApplicationIsInForeground = utility.isDriverApplicationOpen();
                    break;
                case "CUSTOMER":
                    //  utility.launchCustomerApplication();
                   // ((AndroidDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));

                    ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    Thread.sleep(5000);
                    isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        try {
            //if switch was unsucessfull, try to switch again
            if (!isApplicationIsInForeground) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        utility.launchDriverApplication();
                        //SetupManager.getObject().launchApp(PropertyUtility.getProp("bundleId_Driver"));
                        Thread.sleep(5000);
                        isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        }
                        break;
                    case "CUSTOMER":
                        utility.launchCustomerApplication();
                        // SetupManager.getObject().restartApp();
                        Thread.sleep(4000);
                        isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        }
                        break;
                    default:
                        error("UnImplemented Step or in correct app", "UnImplemented Step");
                        break;
                }
            }
            Thread.sleep(2000);
            if (!isApplicationIsInForeground)
                warning("Switch to " + appName + " application", "Not able to currently verify if " + appName + " application was not successfull");
            if(action.isElementPresent(phonePage.Container_Notification(true)))
            {
                ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                logger.detail("Attempted to hide container");

            }
            else
                pass("Switch to " + appName + " application", "Switch to " + appName + " application is successful");

            //    Thread.sleep(5000);
            //     testStepVerify.isTrue(isApplicationIsInForeground, "Switch to " + appName + " application", "Switch to " + appName + " application is successful", "Switch to " + appName + " application was not successfull");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }
    //open app without restart
    @When("^I Open \"([^\"]*)\" application on \"([^\"]*)\" devices$")
    public void i_open_to_something_application_on_something_devices(String appName, String device) {
        boolean isApplicationIsInForeground = false;

        try {
            if (!device.equalsIgnoreCase("same")) {
                i_switch_to_something_instance(device);
                Thread.sleep(5000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    //  ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));

                    utility.launchDriverApplication();
                    Thread.sleep(4000);
                    isApplicationIsInForeground = utility.isDriverApplicationOpen();
                    break;
                case "CUSTOMER":
                    utility.launchCustomerApplication();
                    // ((AndroidDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    Thread.sleep(4000);
                    isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        try {
            //if switch was unsucessfull, try to switch again
            if (!isApplicationIsInForeground) {
                switch (appName.toUpperCase()) {
                    case "DRIVER":
                        utility.launchDriverApplication();
                        //SetupManager.getObject().launchApp(PropertyUtility.getProp("bundleId_Driver"));
                        isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isDriverApplicationOpen();
                        }
                        break;
                    case "CUSTOMER":
                        utility.launchCustomerApplication();
                        // SetupManager.getObject().restartApp();
                        isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        if (!isApplicationIsInForeground) {
                            action.click(new Point(0, 0));
                            isApplicationIsInForeground = utility.isCustomerApplicationOpen();
                        }
                        break;
                    default:
                        error("UnImplemented Step or in correct app", "UnImplemented Step");
                        break;
                }
            }
            Thread.sleep(2000);
            if (!isApplicationIsInForeground)
                warning("Switch to " + appName + " application", "Not able to currently verify if " + appName + " application was not successfull");
            else
                pass("Switch to " + appName + " application", "Switch to " + appName + " application is successful");

            //    Thread.sleep(5000);
            //     testStepVerify.isTrue(isApplicationIsInForeground, "Switch to " + appName + " application", "Switch to " + appName + " application is successful", "Switch to " + appName + " application was not successfull");
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @Given("^I have \"([^\"]*)\" app \"([^\"]*)\"$")
    public void i_have_something_app_something(String appName, String expectedOutcome) throws Throwable {
        try {
            boolean isAppInstalled = false;
            switch (appName.toUpperCase()) {
                case "TWITTER":
                    isAppInstalled = ((AndroidDriver) (SetupManager.getDriver())).isAppInstalled(PropertyUtility.getDataProperties("twitter.bundle.id"));
                    break;
                case "FACEBOOK":
                    SetupManager.getObject().terminateApp(PropertyUtility.getDataProperties("facebook.bundle.id"));
                    // ((AndroidDriver) (SetupManager.getDriver())).terminateApp(PropertyUtility.getDataProperties("facebook.bundle.id"));
                    isAppInstalled = ((AndroidDriver) (SetupManager.getDriver())).isAppInstalled(PropertyUtility.getDataProperties("facebook.bundle.id"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            switch (expectedOutcome.toUpperCase()) {
                case "INSTALLED":
                    testStepAssert.isTrue(isAppInstalled, appName + " should be installed", appName + " is Not installed");
                    break;
                case "NOT INSTALLED":
                    testStepAssert.isFalse(isAppInstalled, appName + " should be installed", appName + " is Not installed");
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

    @Given("^I have device which has location permission$")
    public void i_have_device_which_has_location_permission() throws Throwable {
        try {
            String deviceApiLevel = ((AndroidDriver) SetupManager.getDriver()).getCapabilities().getCapability("deviceApiLevel").toString();
            testStepAssert.isFalse(Integer.parseInt(deviceApiLevel)<23,"Device api level should be or above 23","Device level is "+deviceApiLevel);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I open Admin portal and navigate to \"([^\"]*)\" page$")
    public void i_open_admin_portal_and_navigate_to_something_page(String option) throws Throwable {
        try {
        i_open_new_something_browser_for_something_instance("CHROME","ADMIN PORTAL");
        SetupManager.getDriver().get(utility.GetAdminUrl());
        logInPage.TextBox_Phone().sendKeys(PropertyUtility.getDataProperties("admin.user"));
        logInPage.TextBox_Pass().sendKeys(PropertyUtility.getDataProperties("admin.password"));
        logInPage.Button_LogIn().click();
            switch (option.toLowerCase()) {
                case "scheduled deliveries":
//                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/ScheduledTrips");
                    Thread.sleep(6000);
                    action.waitUntilElementIsDisplayed_ForWeb(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_Trips());
                    Thread.sleep(5000);
                    action.click(dashBoardPage.Button_ScheduledTrips());
                    //action.click(dashBoardPage.Button_Trips());
                    //action.click(dashBoardPage.Button_ScheduledTrips());
                    break;
                case "live deliveries":
//                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/Trips?isComplete=False");
                    action.waitUntilElementIsDisplayed_ForWeb(dashBoardPage.Button_Trips());
                    action.click(dashBoardPage.Button_Trips());
                    action.waitUntilElementIsDisplayed_ForWeb(dashBoardPage.Button_LiveTrips());
                    action.click(dashBoardPage.Button_LiveTrips());
                    break;
                case "promo code":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/PromoCodes");
                    //action.click(dashBoardPage.Button_PromoCode());
                   // action.click(dashBoardPage.Link_StandardCodes());
                    break;
                case "referral source":
//                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/ReferralSource");

                    action.click(dashBoardPage.Button_Marketing());
                    action.waitUntilElementIsDisplayed_ForWeb(dashBoardPage.Link_ReferralSource());
                    action.click(dashBoardPage.Link_ReferralSource());
                    break;
                case "customers":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/Customers");

                   // action.click(dashBoardPage.Button_Customers());
                    break;
                case "deliveries":
                    SetupManager.getDriver().get(utility.GetAdminUrl().replace("Admin/Login","")+"BungiiReports/Trips?isComplete=True");
                    //action.click(dashBoardPage.Button_Trips());
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

    @When("^I open new \"([^\"]*)\" browser for \"([^\"]*)\"$")
    public void i_open_new_something_browser_for_something_instance(String browser, String instanceName) {
        try {
            switch (instanceName){
                case "ADMIN PORTAL":
                    if (PropertyUtility.targetPlatform.equalsIgnoreCase("IOS") || PropertyUtility.targetPlatform.equalsIgnoreCase("ANDROID")) {
                        String currentKey = DriverManager.getCurrentKey();
                        if(DriverManager.driverArray.size()>1) {
                            for (Map.Entry<String, WebDriver> entry : DriverManager.driverArray.entrySet()) {
                                entry.getValue().getPageSource();
                                logger.detail("Pinging : "+ entry.getKey());
                            }
                            DriverManager.driverArray.get(currentKey).getPageSource();
                            //Ping all instances to keep them running in browserstack, used in duo scenarioss
                        }
                    }
                    SetupManager.getObject().createNewWebdriverInstance(instanceName, browser);
                    SetupManager.getObject().useDriverInstance(instanceName);
                    break;
                case "MOBILE DEVICE":
                    AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
                    driver.navigate().back();
                    action.swipeForApps(chromePage.Icon_Swipe(),chromePage.Swipe_EndPoint());
                    action.click(chromePage.Textbox_SearchBar());
                    action.clearSendKeys(chromePage.Textbox_SearchBar(),browser);
                    action.click(chromePage.Icon_Chrome());
                    break;
            }
            log(
                    "I open new " + browser + " browser for " + instanceName + " instance",
                    "I open new " + browser + " browser for " + instanceName + " instance", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I connect to \"([^\"]*)\" using \"([^\"]*)\" instance$")
    public void i_connect_to_something_using_something_instance(String deviceId, String instanceName) {
        try {
            SetupManager.getObject().createNewAndroidInstance(instanceName, deviceId);
            SetupManager.getObject().useDriverInstance(instanceName);
            log("I should be connected to " + deviceId,
                    "I am connected to " + deviceId + " device and assigned session name " + instanceName, true);
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
            log("I switch to  " + instanceName + " instance session",
                    "I switch to  " + instanceName + "instance session", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" page should be opened$")
    public void ThenPageShouldBeOpened(String page) {
        try {
            switch(page)
            {
                case "bungii.com":
//                    action.click(locationPage.Option_Chrome());
//                    action.click(locationPage.Button_Always());
                    testStepAssert.isElementDisplayed(locationPage.Header_DrivePage(),
                            "Correct Header should be displayed",
                            "Correct Header is displayed" ,
                            "Correct Header is not displayed");
                    break;

            }
            boolean isCorrectPage = utility.isCorrectPage(page);
            testStepAssert.isTrue(isCorrectPage, page + " should be displayed", page + " is displayed correctly  ", page + " is not displayed correct");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on allow location screen$")
    public void i_should_see_something_on_allow_location_screen(String identifier) throws Throwable {
        try {

            switch (identifier.toLowerCase()) {
                case "all details":
                    testStepVerify.isEquals(action.getText(locationPage.Subheader_FAQPage()), PropertyUtility.getMessage("customer.navigation.allow.location.header"));
                    testStepVerify.isEquals(action.getText(locationPage.Text_Info()), PropertyUtility.getMessage("customer.navigation.allow.location.text"));
                    testStepVerify.isElementEnabled(locationPage.Image_Compass(), " Compass image should be displayed");
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

    @Then("^I verify and allow access of Location from Bungii application$")
    public void i_allow_access_of_location_from_bungii_application() throws Throwable {
        try {
            action.click(locationPage.Button_Sure());
            testStepVerify.isEquals(action.getText(locationPage.Alert_Text()), "Allow Bungii to access this device's location?");
            action.click(locationPage.Button_Allow());
            pass("I allow access of Location from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify and deny access of Location from Bungii application$")
    public void i_deny_access_of_location_from_bungii_application() throws Throwable {
        try {
            action.click(locationPage.Button_Sure());
            testStepVerify.isEquals(action.getText(locationPage.Alert_Text()), "Allow Bungii to access this device's location?");
            action.click(locationPage.Button_Deny());
            pass("I allow access of Location from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify and allow access of Location upon reasking from Bungii application$")
    public void i_asdaccess_of_location_from_bungii_application() throws Throwable {
        try {
            testStepVerify.isEquals(action.getText(locationPage.Alert_Text()), "Allow Bungii to access this device's location?");
            testStepVerify.isEquals(action.getText(locationPage.CheckBox_DontAskAgain()), "Don't ask again");
            action.click(locationPage.Button_Allow());
            pass("I allow access of Location from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify and allow access of Location from Bungii driver application$")
    public void i_deny_access_of_location_from_bungiidriver_application() throws Throwable {
        try {
            com.bungii.android.pages.driver.LocationPage locationPage = new com.bungii.android.pages.driver.LocationPage();

            action.click(locationPage.Button_Sure());
            testStepVerify.isEquals(action.getText(locationPage.Alert_Text()), "Allow Bungii Driver to access this device's location?");
            action.click(locationPage.Button_Allow());
            pass("I allow access of Location from Bungii application", "I clicked on allow button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see \"([^\"]*)\" on allow location driver screen$")
    public void i_should_see_something_on_allow_locationdriver_screen(String identifier) throws Throwable {
        try {
            com.bungii.android.pages.driver.LocationPage locationPage = new com.bungii.android.pages.driver.LocationPage();
            switch (identifier.toLowerCase()) {
                case "all details":
                    testStepVerify.isEquals(action.getText(locationPage.Subheader_FAQPage()), PropertyUtility.getMessage("customer.navigation.allow.location.header"));
                    testStepVerify.isEquals(action.getText(locationPage.Text_Info()), PropertyUtility.getMessage("driver.navigation.allow.location.text"));
                    testStepVerify.isElementEnabled(locationPage.Image_Compass(), " Compass image should be displayed");
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

    @And("^I check that \"([^\"]*)\" pages of turotial are present$")
    public void i_check_that_something_pages_of_turotial_are_present(String strArg1) throws Throwable {
        try {
            List<WebElement> xpath = homePage.Button_PdfPages();
            int xpathCount = xpath.size();
            if (xpathCount == 5) {
                testStepAssert.isTrue(true, "There should be 5 pdf pages", "There are 5 pdf pages.");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I check that if i can swipe the pages$")
    public void i_check_that_if_i_can_swipe_the_pages() throws Throwable {
        try {
            List<WebElement> xpath = homePage.Button_PdfPages();
            WebDriver driver = null;
            WebElement ele = null;
            int xpathCount = xpath.size();
            boolean isClicked = false, isSwiped = false;
            for (WebElement tutorialPage : xpath) {
                action.click(tutorialPage);
                isClicked = true;
            }
            testStepAssert.isTrue(isClicked, "5 pages are present.", "5 pages are not present.");
            action.click(homePage.Text_TutorialPdfPage1());
            for (int i = 0; i < xpathCount - 1; i++) {
                action.swipeLeft(homePage.Text_TutorialPdf());
                isSwiped = true;
            }

            testStepAssert.isTrue(isSwiped, "Swiped through the pages.", "Couldn't swipe through the pages.");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I tap the \"([^\"]*)\" button is present on last page$")
    public void i_tap_the_something_button_is_present_on_last_page(String strArg1) throws Throwable {
        try {
            testStepVerify.isElementEnabled(homePage.Button_StartApp(), "START button enabled", "START button enabled", "START button not enabled");
            action.click(homePage.Button_StartApp());
            testStepAssert.isElementTextEquals(homePage.Title_HomePage(), "BUNGII", "Expected Text is present.", "Expected Text is present.", "Expected Text is not present.");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I verify that the tutorial is displayed only once$")
    public void i_verify_that_the_tutorial_is_displayed_only_once() throws Throwable {
        try {
            testStepAssert.isElementTextEquals(homePage.Title_HomePage(), "BUNGII", "Expected Text is present.", "Expected Text is present.", "Expected Text is not present.");

            // testStepAssert.isNotElementDisplayed(homePage.Text_TutorialPdf(), "Tutorials should not be displayed.", "Tutorials should not be displayed.", "Tutorials are displayed.");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @Then("^Alert message with (.+) text should be displayed$")
    public void alert_message_with_text_should_be_displayed(String message) {
        try {
            String actualMessage = "";
            Thread.sleep(10000);
            if (action.isElementPresent(estimatePage.Alert_ConfirmRequestMessage(true))) {
                actualMessage = estimatePage.Alert_ConfirmRequestMessage(true).getText();
            } else if (actualMessage.equals("")) {
                actualMessage = action.getText(driverHomePage.Alert_NewBungii(true));
            } else {
                actualMessage = bungiiRequest.Alert_Msg(true).getText();
            }
            String expectedMessage = null;
            switch (message.toUpperCase()) {
                case "DRIVER CANCELLED":
                    expectedMessage = PropertyUtility.getMessage("customer.alert.driver.cancel");
                    break;
                case "TRIP CANNOT BE CANCELED AS CONTROL DRIVER NOT STARTED":
                    expectedMessage = PropertyUtility.getMessage("driver.alert.noncontrol.cancel.before.control");
                    logger.detail("PAGE SOURCE" + SetupManager.getDriver().getPageSource());
                    break;
                case "OOPS! WE FOCUS ON LOCAL DELIVERIES WITHIN 150 MILES OF PICKUP. IT LOOKS LIKE THIS TRIP IS A LITTLE OUTSIDE OUR SCOPE.":
                    expectedMessage = PropertyUtility.getMessage("customer.alert.long.haul");
                    break;
                case "HMM, IT LOOKS LIKE YOU ALREADY HAVE A BUNGII SCHEDULED. AT THIS TIME, OUR SYSTEM ONLY ALLOWS ONE BUNGII AT A TIME.":
                    expectedMessage = PropertyUtility.getMessage("customer.alert.alreadyscheduled");
                    action.click(estimatePage.Button_SystemCalenderOK());
                    break;
                case "ACCEPT BUNGII QUESTION":
                    expectedMessage = PropertyUtility.getMessage("driver.bungii.request.ondemand.question");
                    break;
                case "DRIVER CANCEL BUNGII":
                    expectedMessage = PropertyUtility.getMessage("driver.cancel.bungii");
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
                case "DELETE WARNING":
                    expectedMessage = PropertyUtility.getMessage("customer.payment.delete");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }

            testStepAssert.isEquals(actualMessage, expectedMessage,
                    "Alert with text " + expectedMessage + "should be displayed",
                    "Alert with text ," + expectedMessage + " should be displayed",
                    "Alert Message is not displayed, actual Message " + actualMessage + " Expected is "
                            + expectedMessage);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step, error in fetching alert : "+ message, true);
        }
    }

    @Then("^user is alerted for \"([^\"]*)\"$")
    public void user_is_alerted_for_something(String key) {
        try {
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
                    expectedText = PropertyUtility.getMessage("customer.forgotpassword.failed.reset");
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
                case "FOR EMERGENCY CONTACT SUPPORT LINE":
                    expectedText = PropertyUtility.getMessage("driver.cancel.support.contact");
                    break;
                case "PICKUP REQUEST NO LONGER AVAILABLE":
                    expectedText=PropertyUtility.getMessage("driver.request.unavailable");
                    break;
                case "60 MINS BEFORE SCHEDULE TRIP TIME":
                    expectedText = PropertyUtility.getMessage("driver.start.60.mins.before");
                    break;
                case "PICKUP ALREADY ACCEPTED BY YOU":
                    expectedText = PropertyUtility.getMessage("driver.request.already.accepted");
                    break;
                case "REQUIRED DRIVER NOT ACCEPTED":
                    expectedText = PropertyUtility.getMessage("driver.required.not.accepted");
                    break;
                case "CUSTOMER HAS ONGOING BUNGII":
                    expectedText = PropertyUtility.getMessage("driver.start.customer.ongoing");
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            String alertText = driverNotAvailablePage.Alert_ConfirmRequestMessage().getText();
            testStepVerify.isEquals(alertText, expectedText);
            action.click(bungiiDetailsPage.Button_Yes());
            Thread.sleep(1000);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^User should see message \"([^\"]*)\" text on the screen$")
    public void user_should_see_message_something_text_on_the_screen(String message) throws Throwable {
        try {
            String actualMessage = "";//utility.getSnackBarMessage();
            String expectedMessage;
            switch (message.toUpperCase()) {
                case "OUTSIDE BUISSNESS HOUR":
                    actualMessage = utility.getCustomerSnackBarMessage();
                    expectedMessage = "We’re only able to schedule Bungii’s between 12:15 AM - 11:30 PM. Please choose a time in that range."; //PropertyUtility.getMessage("customer.alert.outsidebuissnesshour.android");
                    action.click(estimatePage.Samsung_Time_Cancel());
                    break;
                case "ACCOUNT DELETED SUCCESSFULLY":
                    actualMessage = utility.getCustomerSnackBarMessage();
                    expectedMessage = PropertyUtility.getMessage("customer.account.deleted.successfully");
                    break;
                case "DELETE WARNING":
                    actualMessage = utility.getCustomerSnackBarMessage();
                    expectedMessage = PropertyUtility.getMessage("customer.payment.delete");
                    break;
                case "60 MINS BEFORE SCHEDULE TRIP TIME":
                    actualMessage = utility.getDriverSnackBarMessage();
                    expectedMessage=PropertyUtility.getMessage("driver.start.60.mins.before");
                    break;
                case "Please install a browser in order to access this link.":
                    actualMessage = utility.getCustomerSnackBarMessage();
                    expectedMessage = PropertyUtility.getMessage("browser.uninstalled.message");
                    action.click(inProgressBungiiPages.Button_Cancel_Yes());
                    break;
                case "THERE IS A CONFLICTING DELIVERY":
                    actualMessage = utility.getDriverSnackBarMessage();
                    expectedMessage = PropertyUtility.getMessage("conflicting.delivery.error.message");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }

            testStepVerify.isEquals(actualMessage, expectedMessage,
                    "Alert with text" + expectedMessage + "should be displayed",
                    "Alert with text ," + expectedMessage + " should be displayed",
                    "Alert Message is not displayed, actual Message" + actualMessage + " Expected is "
                            + expectedMessage);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            fail("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" on alert message$")
    public void i_click_something_on_alert_message(String strArg1) throws Throwable {
        try {
            if (strArg1.equalsIgnoreCase("cancel"))
                action.click(estimatePage.Button_Cancel());
            else if(strArg1.equalsIgnoreCase("View"))
                action.click(estimatePage.Button_AcceptRequest());
            else
                action.click(estimatePage.Button_OK());

            log("I should able to click " + strArg1 + "on Alert Message",
                    "I clicked " + strArg1 + "on Alert Message", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I click \"([^\"]*)\" button on alert message$")
    public void i_click_something_button_on_alert_message(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "YES":
                    action.click(bungiiRequest.AlertButton_View());
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^Alert should have \"([^\"]*)\" button$")
    public void alert_should_have_something_button(String list) throws Throwable {
        switch (list) {
            case "cancel,proceed":
                testStepVerify.isElementEnabled(estimatePage.Button_Cancel(true), "Cancel button should be displayed");
                testStepVerify.isElementEnabled(estimatePage.Button_Proceed(true), " Proceed button should be displayed");
                break;

            default:
                throw new Exception(" UNIMPLEMENTED STEP");
        }
    }

    @Given("^I newly installed \"([^\"]*)\" app$")
    public void i_newly_installed_something_app(String strArg1) throws Throwable {
        try {
            GeneralUtility utility = new GeneralUtility();
            utility.resetApp();
            log("I reset Cancel App Data",
                    "I reset Estimate App Data", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I click on device \"([^\"]*)\" button$")
    public void i_click_on_device_something_button(String strArg1) throws Throwable {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            driver.navigate().back();
            log("I tap on device back button",
                    "I tapped on device back button", false);
        } catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I click \"([^\"]*)\" on Confirmation Popup$")
    public void i_click_something_on_confirmation_popup(String option) throws Throwable {
        try {
            switch (option) {
                case "Yes":
                    action.click(inProgressBungiiPages.Button_Cancel_Yes());
                    break;
            }
        } catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I get TELET time of of the current trip$")
    public void i_get_telet_time_of_of_the_current_trip() throws Throwable {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //    phoneNumber="8888889907";
        String custRef = dbUtility.getCustomerRefference(phoneNumber);
        String teletTime = dbUtility.getTELETfromDb(custRef);

        cucumberContextManager.setScenarioContext("TELET", teletTime);
        logger.detail("TELET Time of Trip of customer : "+ phoneNumber + " is " + teletTime );
    }

    @And("^I get TELET time of currrent trip of customer 2$")
    public void i_get_telet_time_of_of_the_currewnt_trip() throws Throwable {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");
        //    phoneNumber="8888889907";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String teletTime = dbUtility.getTELETfromDb(custRef);

        cucumberContextManager.setScenarioContext("TELET", teletTime);
        logger.detail("TELET Time of Trip of customer 2 : "+ phoneNumber + " is " + teletTime );
    }

    @Then("^Telet time of current trip should be correctly calculated$")
    public void telet_time_of_current_trip_should_be_correctly_calculated() throws Throwable {
        com.bungii.android.utilityfunctions.GeneralUtility utility = new com.bungii.android.utilityfunctions.GeneralUtility();
        String teletTimeLocal = utility.calculateTeletTime();
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
        testStepVerify.isEquals(strdateDB, strdatelocal);

    }

    @Then("^Telet time of research trip should be not be same as previous trips$")
    public void telet_time_of_current_trip_should_be_correctly_calculatedtrip() throws Throwable {
        String previousTelet = (String) cucumberContextManager.getScenarioContext("TELET");
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //    phoneNumber="8888889907";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String newTeletTime = dbUtility.getTELETfromDb(custRef);
        testStepAssert.isTrue(!previousTelet.equals(newTeletTime), "Research trip time should not be same as Telet Time", "Research trip time is same as Telet Time");

    }

    @And("^I tap on \"([^\"]*)\" button of android mobile$")
    public void i_tap_on_something_button_of_android_mobile(String strArg1) throws Throwable {
        action.NavigateBack();
        log("I tap back button "  ,"I tapped back button",
                true);
    }

    @And("^I tap on \"([^\"]*)\" icon of page$")
    public void i_tap_on_something_icon_of_page(String strArg1) throws Throwable {
        if (!action.isElementPresent(estimatePage.Button_Back(true))) {
            action.NavigateBack();
        } else {
            action.click(estimatePage.Button_Back(true));
        }
        log("I tap back button "  ,"I tapped back button",
                 true);
    }

    @Then("^I wait for \"([^\"]*)\" mins$")
    public void i_wait_for_something_mins(String strArg1) throws Throwable {
        action.hardWaitWithSwipeUp(Integer.parseInt(strArg1));
    }

    @Then("^I save customer phone and referral code in feature context$")
    public void i_save_customer_phone_and_referral_code_in_feature_context() throws Throwable {
        try {
            cucumberContextManager.setFeatureContextContext("INVITE_CODE", (String) cucumberContextManager.getScenarioContext("INVITE_CODE"));
            cucumberContextManager.setFeatureContextContext("CUSTOMER_HAVING_REF_CODE", (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER"));
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Given("^I have customer with referral code$")
    public void i_save_customer_phone_and_referral_code_iADDED_PROMO_CODEn_feature_context() throws Throwable {
        try {
            String refCode = (String) cucumberContextManager.getFeatureContextContext("INVITE_CODE");//refCode="119W5";
            String phoneNumber = (String) cucumberContextManager.getFeatureContextContext("CUSTOMER_HAVING_REF_CODE");//phoneNumber="9999992799";
           // phoneNumber = (String) cucumberContextManager.getFeatureContextContext("NEW_USER_NUMBER");//phoneNumber="9999992799";
            cucumberContextManager.setScenarioContext("ADDED_PROMO_CODE", refCode);
            cucumberContextManager.setScenarioContext("NEW_USER_NUMBER", phoneNumber);
            testStepAssert.isTrue(refCode.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
            testStepAssert.isTrue(phoneNumber.length() > 1, "I Should have customer with ref code", "I dont have customer with ref code");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I click \"([^\"]*)\" on the alert message$")
    public void i_click_something_on_the_alert_message(String strArg1) throws Throwable {
        try {
            Thread.sleep(2000);
            switch (strArg1) {
                case "OK":
                    action.click(bungiiAcceptedPage.Button_OK());
                    break;
                case "YES":
                    action.click(inProgressBungiiPages.Button_Cancel_Yes());
                    break;

            }
            log("I click "+strArg1+" on the alert message","I clicked "+strArg1+" on the alert message",false);
        } catch (Exception e) {
            log("I should able to click " + strArg1 + "on Alert Message",
                    "I clicked " + strArg1 + "on Alert Message", true);
        }
    }

    @Then("^Driver should see \"([^\"]*)\" message$")
    public void driver_should_see_something_message(String strArg1) throws Throwable {
        try {
            testStepAssert.isEquals(action.getText(locationPage.Alert_Text()), strArg1, strArg1 +" should be displayed.", strArg1 +" is displayed.", strArg1 +" is not displayed.");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in viewing alert", true);
        }
    }

    @And("^I Enter \"([^\"]*)\" value in \"([^\"]*)\" field in \"([^\"]*)\" Page$")
    public void i_enter_something_value_in_something_field_in_something_page(String value, String field, String screen, DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

            String referralCode=dataMap.get("Referral Code").trim();

            switch (field.toUpperCase()) {
                case "REFERRAL CODE":
                    action.click(Page_Signup.CheckBox_Promo());
                    action.click(Page_Signup.TextField_Referral());
                    action.sendKeys(Page_Signup.TextField_Referral(),referralCode);
                    break;
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }
            log("I should able to Enter " + referralCode + " value in " + field + " field in " + screen + " Page",
                    "I Entered " + referralCode + " in " + field + " field", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.getStackTrace();
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should be navigated to \"([^\"]*)\" screen$")
    public void i_should_be_naviagated_to_something_screen(String screen) {
        try {
            boolean isCorrectPage = false;
            isCorrectPage = utility.isCorrectPage(screen);
            testStepAssert.isTrue(isCorrectPage, "I should be naviagated to " + screen + " screen",
                    "I should be navigated to " + screen, "I was not navigated to " + screen + " screen ");

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    public String[] bungiiTimeForScroll(Date date) {
        //get timezone
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d MMM|h|mm|a");
        String formattedDate = sdf.format(date);
        String[] SplitDate = formattedDate.split("\\|");
        if (DateUtils.isSameDay(date, new Date())) {
            SplitDate[0] = "Today";
        }
        return SplitDate;
    }

    @And("^Customer should receive \"([^\"]*)\" email$")
    public void customer_should_receive_something_email(String emailSubject) throws Throwable {
        String emailBody = utility.GetSpecificURLs(PropertyUtility.getEmailProperties("email.from.address"), PropertyUtility.getEmailProperties("email.client.id"), emailSubject);
        String url = "";
        if(emailBody!=null) {
            action.navigateTo(emailBody);
            url = action.getCurrentURL();
        }
        else
            testStepAssert.isTrue(false,"Email should be received","Email is received :"+ emailSubject,"Email is not received : "+ emailSubject);

        String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        String survey_link = null;
        switch(geofence)
        {
            case "atlanta" :
                survey_link = PropertyUtility.getDataProperties("atlanta.survey.email.link");
                break;
            case "baltimore":
                survey_link = PropertyUtility.getDataProperties("atlanta.survey.email.link");
                break;
        }
        testStepAssert.isTrue(url.contains(survey_link),"Survey Email link should be "+survey_link,"Survey email link is "+ survey_link,"Survey email link is "+ url);
    }
    @And("^Customer should receive \"([^\"]*)\" receipt email$")
    public void customer_should_receive_something_receipt_email(String strArg1) throws Throwable {
        GeneralUtility utility = new GeneralUtility();
        String emailSubject="Your Bungii Receipt";
        //  String emailBody = utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"), "POOR DRIVER RATING");

        String emailBody  =  utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"),emailSubject);
        String driverName=(String) cucumberContextManager.getScenarioContext("DRIVER_1");/*driverName="Testdrivertywd_appledv_b_matt Stark_dvOnE";*/
        String customerName=(String)cucumberContextManager.getScenarioContext("CUSTOMER");/*customerName="Testcustomertywd_appleZTDafc Stark";*/
        String bungiiDate="";
        String pickupAddress=(String)cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")+cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2");
        String dropAddress=(String)cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")+cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2");
        String bungiiDuration=utility.getActualTime();
        String bungiiCost=(String)cucumberContextManager.getScenarioContext("BUNGII_COST_CUSTOMER");

        String estimatedTime=(String)cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE_TIME");
        String actualLoadUnloadTime="";
        String estimatedLoadUnloadTime=(String)cucumberContextManager.getScenarioContext("BUNGII_LOADTIME");/*ratingValue="3";*/
        if(emailBody== "")
        {
            testStepAssert.isFail("Email : "+ emailSubject + " not received");
        }
        String tripDetailsLink=extractUrls(emailBody).get(0);
        String message = null;
       // message = utility.getExpectedPoorRatingMail(driverName, customerName, ratingValue, tripDetailsLink);
        testStepAssert.isEquals(emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ",""), message.replaceAll(" ",""),"Email "+emailBody+" content should match", "Email  "+emailBody+" content matches", "Email "+emailBody+"  content doesn't match");
    }

    @Then("^Customer should receive signup email$")
    public void partner_firm_should_receive_something_email() throws Throwable {
        String emailSubject="New to Bungii? Good.";
/*        cucumberContextManager.setScenarioContext("NEW_USER_EMAIL_ADDRESS","bungiiauto+obKm@gmail.com");
        cucumberContextManager.setScenarioContext("FIRST_NAME","TestCustomertywdappleMzr");*/

        String emailBody = utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.welcome.from.address"), (String)cucumberContextManager.getScenarioContext("NEW_USER_EMAIL_ADDRESS"), emailSubject);
        List<String> tripDetailsLinks=extractUrls(emailBody);
        utility.getCustomerSignupTemplate((String)cucumberContextManager.getScenarioContext("NEW_USER_EMAIL_ADDRESS"));
        if (emailBody == "") {
            testStepAssert.isFail("Email : " + emailSubject + " not received");
        }
        else{

            boolean isEmailCorrect=utility.validateCustomerSignupEmail(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath())+"\\EmailTemplate\\CustomerSignup.txt",emailBody, (String)cucumberContextManager.getScenarioContext("NEW_USER_FIRST_NAME"),tripDetailsLinks.get(0),tripDetailsLinks.get(1),tripDetailsLinks.get(2),tripDetailsLinks.get(3),tripDetailsLinks.get(4),tripDetailsLinks.get(5),tripDetailsLinks.get(6),tripDetailsLinks.get(7),tripDetailsLinks.get(8));
            testStepAssert.isTrue(isEmailCorrect,"Email should be correct","Email is not correct , check logs for more details");
        }
    }
    @Then("^poor driver ratting should be sent to customer$")
    public void poor_driver_ratting_should_be_sent_to_customer() throws Throwable {
        GeneralUtility utility = new GeneralUtility();
        String emailSubject="POOR DRIVER RATING";
        //  String emailBody = utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"), "POOR DRIVER RATING");

        String emailBody  =  utility.GetSpedificMultipartTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"),emailSubject);
        String driverName=(String) cucumberContextManager.getScenarioContext("DRIVER_1");/*driverName="Testdrivertywd_appledv_b_matt Stark_dvOnE";*/
        String customerName=(String)cucumberContextManager.getScenarioContext("CUSTOMER");/*customerName="Testcustomertywd_appleZTDafc Stark";*/
        String ratingValue=(String)cucumberContextManager.getScenarioContext("RATING_VALUE");/*ratingValue="3";*/
        String tripDetailsLink="";
        if(emailBody!=null)
            tripDetailsLink = extractUrls(emailBody).get(0);
        if(emailBody== null)
        {
            testStepAssert.isFail("Email : "+ emailSubject + " not received");
        }
        String message = null;
        message = utility.getExpectedPoorRatingMail(driverName, customerName, ratingValue, tripDetailsLink);
        testStepAssert.isEquals(emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ",""), message.replaceAll(" ",""),"Email "+emailBody+" content should match", "Email  "+emailBody+" content matches", "Email "+emailBody+"  content doesn't match");
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

    @Then("^I accept \"([^\"]*)\" and \"([^\"]*)\" permission if exist$")
    public void I_acceptNotificationAndLocationPermissionIfExist(String Notification, String Location) throws Throwable {
        try {
            GeneralUtility utility = new GeneralUtility();
            String pageName = utility.getPageHeader();
            if(pageName.equalsIgnoreCase("OFFLINE")|| pageName.equalsIgnoreCase("ONLINE")) {
                //do nothing
                logger.detail("Driver app is on Home Screen");
            }
            else{
                if (action.isElementPresent(driverHomePage.Button_Sure(true))) {
                    action.click(driverLoginPage.Button_Sure());
                    action.click(driverLoginPage.Button_Allow());
                    Thread.sleep(15000);
                }

                pageName = utility.getPageHeader();
                if (action.isElementPresent(driverHomePage.Button_Sure(true))) {
                    action.click(driverLoginPage.Button_Sure());
                    action.click(driverLoginPage.Button_Allow());
                }
            }

        } catch (Exception e) {

        }

    }

    @Then("^The revive button should not be displayed$")
    public void the_revive_button_should_not_be_displayed() throws Throwable {
        try{
        testStepAssert.isFalse(action.isElementPresent(scheduledTripsPage.Button_ReviveTrip(true)),"Revive button should not be displayed", "Revive button is not displayed", "Revive button is displayed");
    } catch (Exception e) {;
            error("Step  Should be successful", "Error in viewing alert", true);
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
    }
    }

    @Then("^The trip should not be present in available bungiis$")
    public void the_trip_should_not_be_present_in_available_bungiis() throws Throwable {
        try{
        boolean isDeliveryPresentInDriverApp = true;
        String fullCustomerName = cucumberContextManager.getScenarioContext("CUSTOMER").toString().substring(0,27);
        List<WebElement> AvailableDeliveriesDriverApp =availableTrips.List_AllDeliveriesDriverApp();
        if(AvailableDeliveriesDriverApp.size()==0){
            pass("Delivery should not be present in available bungiis","Delivery is not present in available bungiis");
        }
        else{
            for(int i=0;i<AvailableDeliveriesDriverApp.size();i++){
                String customerNameFromDelivery =action.getText(availableTrips.CustomerName(i+1)).substring(0,27);
                if(customerNameFromDelivery.contentEquals(fullCustomerName)){
                    testStepAssert.isFail("Delivery is present in available bungiis");
                    isDeliveryPresentInDriverApp=false;
                }
            }
            if(isDeliveryPresentInDriverApp ==true) {
                testStepAssert.isTrue(true,"Delivery should not be present in available bungiis","Delivery is not present in available bungiis","Delivery is  present in available bungiis");
            }
        }
    } catch (Throwable e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful",
                "Error performing step,Please check logs for more details", true);
    }
    }


    @And("^I enter cancellation fee and Comments$")
    public void i_enter_cancellation_fee_and_comments() throws Throwable {
        try{
            action.clearSendKeys(scheduledTripsPage.Textbox_CancellationFee(), "0");
            action.clearSendKeys(scheduledTripsPage.Textbox_CancellationComment(), "Cancelling");
            action.selectElementByText(scheduledTripsPage.Dropdown_CancellationReason(), "Other");
            log("I enter cancellation fee amount and comments",
                    "I have entered cancellation fee amount and comments", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The \"([^\"]*)\" message should be displayed$")
    public void the_something_message_should_be_displayed(String message) throws Throwable {
        try{
        Thread.sleep(3000);
        testStepAssert.isElementTextEquals(scheduledTripsPage.Label_CancelSuccessMessage(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
    } catch (Throwable e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful",
                "Error performing step,Please check logs for more details", true);
    }
    }

    @And("^I select \"([^\"]*)\" from the \"([^\"]*)\" dropdown$")
    public void i_select_something_from_the_something_dropdown(String strArg1, String field) throws Throwable {
        try{
            switch(field) {
                case "Cancellation Reason":
                    action.selectElementByText(scheduledTripsPage.Dropdown_CancellationReason(),strArg1);
                    log("I select element from Cancellation reason dropdown",
                            "I have selected element from Cancellation reason dropdown", true);
                    break;
            }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I click on \"([^\"]*)\" button on Revival Popup$")
    public void i_click_on_something_button_on_revival_popup(String button) throws Throwable {
        try{
            switch(button)
            {
                case "Confirm":
                    action.click(scheduledTripsPage.Button_Confirm());
                    Thread.sleep(10000);
                    String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    cucumberContextManager.setScenarioContext("OLD_PICKUP_REQUEST",pickuprequest);
                    pickuprequest = dbUtility.getLinkedPickupRef(pickuprequest);
                    cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickuprequest);
                    break;
                case "Cancel":
                    action.click(scheduledTripsPage.Button_Cancel());
                    break;
            }
            log("I click on the "+button+" button on Revival Popup",
                    "I have clicked the "+button+" button on Revival Popup", true);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^The trip should be present in my bungiis$")
    public void the_trip_should_be_present_in_my_bungiis() throws Throwable {
        try{
        Thread.sleep(3000);
        boolean isTripPresent = false;
        String scheduled_time = cucumberContextManager.getScenarioContext("BUNGII_TIME").toString().substring(0, 16);
        List<WebElement> allCustomerDeliveries = availableTrips.List_AllCustomerDeliveries();
        if (allCustomerDeliveries.size() == 0) {
            testStepAssert.isFail("Delivery is not present in available bungiis");
        } else {
            for (int i = 0; i < allCustomerDeliveries.size(); i++) {
                String [] fullCustomerTripTime= action.getText(availableTrips.Text_CustomerDeliveryTime(i + 1)).substring(0, 23).split(",");
                String onlyMonthAndDate = fullCustomerTripTime[0];
                String [] fullDeliveryTimeFormat = fullCustomerTripTime[1].split("-");
                String onlyTimeFormat = fullDeliveryTimeFormat[1];
                String dateAndTime = onlyMonthAndDate + "," + onlyTimeFormat;
                if (dateAndTime.contentEquals(scheduled_time)) {
                    pass("Delivery should be present in available bungiis","Delivery is present in available bungiis",false);
                    isTripPresent = true;
                    break;
                }
            }
            if (isTripPresent == false) {
                testStepAssert.isFail("Delivery is not present in available bungiis");
            }
        }
    } catch (Throwable e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful",
                "Error performing step,Please check logs for more details", true);
    }
    }

    @Then("^Revive button should be displayed beside the trip$")
    public void revive_button_should_be_displayed_beside_the_trip() throws Throwable {
        try {
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            SetupManager.getDriver().manage().window().maximize();
            SetupManager.getDriver().manage().window().setSize(new Dimension(1900, 1280));

            String link = String.format("//td[contains(.,'%s')]/following-sibling::td/a[@class='revive-trip-link']/img", customerName);
            testStepAssert.isTrue(action.isElementPresent(scheduledTripsPage.findElement(link, PageBase.LocatorType.XPath)), "Revive button should be displayed", "Revive button is displayed", "Revive button is not displayed");
            cucumberContextManager.setScenarioContext("REVIVE_LINK", link);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    
    @Given("^I navigate to \"([^\"]*)\" portal configured for \"([^\"]*)\" URL$")
    public void i_navigate_to_something(String page, String url) throws Throwable {
        try{  switch (page)
        {
            case "Partner":
                String partnerUrl =  utility.NavigateToPartnerLogin(url);
                cucumberContextManager.setScenarioContext("PartnerPortalURL",partnerUrl);
                cucumberContextManager.setScenarioContext("IS_PARTNER","TRUE");
                pass("I should be navigate to " + page + " portal configured for "+ url ,
                        "I navigated to " + page + " portal configured for "+ url +" ["+partnerUrl+"]", true);
                break;
            default:break;
        }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @When("^I enter \"([^\"]*)\" password on Partner Portal$")
    public void WhenIEnterPasswordOnPartnerPortal(String str)
    {
        try{
            //SetupManager.getObject().manage().window().maximize();
            switch (str)
            {
                case "valid":
                    action.clearSendKeys(scheduledTripsPage.TextBox_PartnerLoginPassword(), PropertyUtility.getDataProperties("PartnerPassword"));
                    break;
                case "invalid":
                    action.clearSendKeys(scheduledTripsPage.TextBox_PartnerLoginPassword(), PropertyUtility.getDataProperties("Invalid_PartnerPassword"));
                    break;
                default: break;
            }
            log("I should able to enter "+str+" driver Password on Partner portal","I entered "+str +" partner Password on Partner portal", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I click \"([^\"]*)\" button on Partner Portal$")
    public void I_Click_Some_Button_On_Partner_Portal(String str) throws InterruptedException {
        try {
            switch (str) {
                case "SIGN IN":
                    action.click(scheduledTripsPage.Button_SignIn());
                    break;
                case "Track Deliveries":
                    Thread.sleep(5000);
                    action.click(scheduledTripsPage.Dropdown_Setting());
                    Thread.sleep(5000);
                    action.click(scheduledTripsPage.Button_TrackDeliveries());
                    Thread.sleep(5000);
                    if(action.getCurrentURL().contains("login")|| action.getCurrentURL().contains("Login"))
                    {
                        //Workaround for app getting logged out when run in parallel
                        action.clearSendKeys(scheduledTripsPage.TextBox_PartnerLoginPassword(), PropertyUtility.getDataProperties("PartnerPassword"));
                        action.click(scheduledTripsPage.Button_SignIn());
                        Thread.sleep(5000);
                        testStepVerify.isEquals(action.getText(scheduledTripsPage.Label_StartOver()), PropertyUtility.getMessage("Start_Over_Header"));
                        Thread.sleep(5000);
                        if(!action.isElementPresent(scheduledTripsPage.Dropdown_Setting(true))) {
                            action.click(scheduledTripsPage.Link_Setting());
                            action.clearSendKeys(scheduledTripsPage.Textbox_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                            action.click(scheduledTripsPage.Button_Continue());
                        }
                        action.click(scheduledTripsPage.Dropdown_Setting());
                        action.click(scheduledTripsPage.Button_TrackDeliveries());

                    }
                    break;
                case "Cancel Delivery link":
                    action.click(scheduledTripsPage.Link_CancelDelivery());
                    break;
                case "OK":
                    action.click(scheduledTripsPage.Button_OK());
                    break;
                case "OK on Delivery Cancellation Failed":
                    action.click(scheduledTripsPage.Button_OkOnDeliveryCancellationFailed());
                    break;
                case "Cancel Delivery":
                    action.click(scheduledTripsPage.Button_CancelDelivery());
                    break;
                default:
                    break;

            }
            log("I click on "+str+ " button ", "I clicked on "+str+ " button ", true);

        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step , I Should "+ str,
                    true);
        }
    }
    @And("^I click the \"([^\"]*)\" button on Partner Portal$")
    public void I_Click_the_Some_Button_On_Partner_Portal(String str) throws InterruptedException {
        try {
            Thread.sleep(2000);
            action.click(scheduledTripsPage.Dropdown_Setting());
            action.click(scheduledTripsPage.Button_TrackDeliveries());
            Thread.sleep(5000);
            String  columnTracking = "TRACKING ID";
            cucumberContextManager.setScenarioContext("TRACKINGID_COLUMN", action.getText(scheduledTripsPage.Text_TrackingIdColumn()));
            testStepAssert.isEquals((String) cucumberContextManager.getScenarioContext("TRACKINGID_COLUMN"),columnTracking,"Tracking ID column should  exist","Tracking ID column exists","Tracking Id column doesnt exist");

            cucumberContextManager.setScenarioContext("PARTNER_CUSTOMERNAME",action.getText(scheduledTripsPage.Text_TripCustomer()));
            cucumberContextManager.setScenarioContext("PARTNER_TRACKINGID", action.getText(scheduledTripsPage.Text_TripTrackingId()));
            cucumberContextManager.setScenarioContext("DELIVERY_ADDRESS", action.getText(scheduledTripsPage.Text_TripDeliveryAddress()).replace(",", ""));
            Thread.sleep(2000);
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I click on the delivery based on customer name$")
    public void i_click_on_the_delivery_based_on_customer_name() throws Throwable {
        try{
            action.click(scheduledTripsPage.Textbox_SearchBar());
            Thread.sleep(1000);
            action.clearSendKeys(scheduledTripsPage.Textbox_SearchBar(), (String)cucumberContextManager.getScenarioContext("CUSTOMER") + Keys.ENTER);
            Thread.sleep(1000);
            action.click(scheduledTripsPage.Link_SelectTripTrackDeliveries());
            log("I should be able to click on the customer trip","I could click on the customer trip",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }


    @And("^I verify the driver earnings displayed on driver app for \"([^\"]*)\"$")
    public void i_verify_the_driver_earnings_displayed_on_driver_app_for_something(String type) throws Throwable {
        try{
            switch (type)
            {
                case "solo":
                    Thread.sleep(2000);
                    String soloDriverEarnings = action.getText(scheduledTripsPage.Text_SoloDriverEarningsApp());
                    float soloDriverEarnings1 = Float.parseFloat(soloDriverEarnings.substring(1));
                    float driverShareCalculated =Float.parseFloat((String) cucumberContextManager.getScenarioContext("CALCULATED_DRIVER_SHARE"));
                    testStepVerify.isTrue(soloDriverEarnings1==driverShareCalculated,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");
                    break;
                case "duo":
                    Thread.sleep(2000);
                    action.scrollToTop();
                    action.scrollToBottom();
                    float duoDriver1Earnings = Float.parseFloat((action.getText(scheduledTripsPage.Text_DuoDriver1EarningsApp()).substring(1)));
                    float driverShareCalculatedDriver1 =Float.parseFloat((String) cucumberContextManager.getScenarioContext("CALCULATED_DRIVER_SHARE_SAME_TIRE"));
                    testStepAssert.isTrue(duoDriver1Earnings==driverShareCalculatedDriver1,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");
                    float duoDriver2Earnings = Float.parseFloat((action.getText(scheduledTripsPage.Text_DuoDriver2EarningsApp()).substring(1)));
                    float driverShareCalculatedDriver2 =Float.parseFloat((String) cucumberContextManager.getScenarioContext("CALCULATED_DRIVER_SHARE_SAME_TIRE"));
                    testStepAssert.isTrue(duoDriver2Earnings==driverShareCalculatedDriver2,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");
                    break;

                case "duo-different tier":
                    Thread.sleep(2000);
                    action.scrollToBottom();
                    float duoDriver1EarningsTier1 = Float.parseFloat((action.getText(scheduledTripsPage.Text_DuoDriver1EarningsApp()).substring(1)));
                    float driverShareCalculatedDriver1Tier1 =Float.parseFloat((String) cucumberContextManager.getScenarioContext("CALCULATED_DRIVER1_SHARE_DIFFERENT_TIRE"));
                    testStepAssert.isTrue(duoDriver1EarningsTier1==driverShareCalculatedDriver1Tier1,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");
                    float duoDriver2EarningsTier2 = Float.parseFloat((action.getText(scheduledTripsPage.Text_DuoDriver2EarningsApp()).substring(1)));
                    float driverShareCalculatedDriver2Tier2 =Float.parseFloat((String) cucumberContextManager.getScenarioContext("CALCULATED_DRIVER2_SHARE_DIFFERENT_TIRE"));
                    testStepAssert.isTrue(duoDriver2EarningsTier2==driverShareCalculatedDriver2Tier2,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");

                    break;

                case "changed address and service level":
                    Thread.sleep(2000);
                    float soloDriverEarningsChangedSL = Float.parseFloat((action.getText(scheduledTripsPage.Text_SoloDriverEarningsApp1()).substring(1)));
                    float driverShareCalculatedChangedSL =Float.parseFloat((String) cucumberContextManager.getScenarioContext("DRIVER_SHARE_FOR_CHANGED_SL_AND_ADDRESS"));
                    testStepAssert.isTrue(soloDriverEarningsChangedSL==driverShareCalculatedChangedSL,
                            "The driver earnings calculated should be same as displayed",
                            "The driver earnings calculated is same as displayed",
                            "The driver earnings calculated is not same as displayed");
                    break;
            }
            log("I should be able to verify the driver earnings displayed",
                    "I am able to verify the driver earnings displayed",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I select \"([^\"]*)\" from items$")
    public void i_select_something_from_items(String pallet) throws Throwable {
        try{
            switch (pallet){
                case "Pallet-1":
                    action.scrollToBottom();
                    action.click(scheduledTripsPage.RadioButton_PalletOne());
                    break;
                case "Pallet-1 available page":
                    action.click(scheduledTripsPage.RadioButton_PalletOne());
                    break;
                case "Pallet-2":
                    action.click(scheduledTripsPage.RadioButton_PalletTwo());
                    break;
            }
            log("I should be able to select the pallet",
                    "I am able to select the pallet",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I check inadequate payload pop up is displayed$")
    public void i_check_inadequate_payload_pop_up_is_displayed() throws Throwable {
        try{
            action.click(tripDetailsPage.Button_Accept());
            String expectedPopUpMesssage = PropertyUtility.getMessage("low.payload.capacity.message");
            testStepVerify.isEquals(utility.getDriverSnackBarMessage(), expectedPopUpMesssage);

            log("I should be able to see the inadequate payload message",
                    "I am able to see the inadequate payload message",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I check information of both the pallets are displayed separately$")
    public void i_check_information_of_both_the_pallets_are_displayed_separately() throws Throwable {
        try{
            testStepAssert.isElementDisplayed(scheduledTripsPage.Text_PalletOne(),
                    "The pallet one information should be displayed",
                    "The pallet one information is displayed",
                    "The pallet one information is not displayed");

            action.scrollToBottom();

            testStepAssert.isElementDisplayed(scheduledTripsPage.Text_PalletTwo(),
                    "The pallet two information should be displayed",
                    "The pallet two information is displayed",
                    "The pallet two information is not displayed");

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^Partner firm should receive \"([^\"]*)\" email$")
    public void partner_firm_should_receive_something_email(String emailSubject) throws Throwable {
        try{
        String driverName = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
        String driverPhone = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
        String Customer_Name = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        String customerPhone = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        String pickUpRef =(String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

        String emailBody  = utility.GetSpecificPlainTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"),PropertyUtility.getEmailProperties("email.client.id"),emailSubject);
        if (emailBody == null) {
            testStepAssert.isFail("Email : " + emailSubject + " not received");
        }
        emailBody= emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ","");
        String message = "";
        logger.detail("Email Body (Actual) : "+ emailBody.replaceAll("\r","").replaceAll("\n","").replaceAll(" ",""));


        switch (emailSubject) {
            case "Bungii Delivery Scheduled":
            case "A Bungii driver is heading your way":
            case "A Bungii driver has arrived":
                String driverCarLicenceNumber = DbUtility.getDriverVehicleInfo(driverPhone);
                String []OnlyLicenceplate = driverCarLicenceNumber.replace("}","").replace("\"","").split(":");
                if(emailSubject.contentEquals("A Bungii driver is heading your way")){
                    message = utility.getABungiiDriverIsHeadingYourWay(driverName,driverPhone,OnlyLicenceplate[3],Customer_Name);
                }
                else if(emailSubject.contentEquals("A Bungii driver has arrived")){
                    message = utility.getABungiiDriverHasArrived(driverName,driverPhone,OnlyLicenceplate[3],Customer_Name);

                }
                else {
                    String pickupdate []=  cucumberContextManager.getScenarioContext("BUNGII_TIME").toString().split(" ");

                    int yearBasedOnDate= LocalDate.now().getYear();
                    String dayBasedOnDate= LocalDate.now().getDayOfWeek().toString().toLowerCase();
                    String monthBasedOnDate= LocalDate.now().getMonth().toString().toLowerCase();;
                    if(pickupdate[2].startsWith("0")){
                        String timeWithout0 = pickupdate[2].replaceFirst("0","");
                        cucumberContextManager.setScenarioContext("TimeWithoutZero",timeWithout0);
                    }
                    else {
                        cucumberContextManager.setScenarioContext("TimeWithoutZero",pickupdate[2]);
                    }
                    String ProperTime = (String) cucumberContextManager.getScenarioContext("TimeWithoutZero");
                    String properTime = dayBasedOnDate.substring(0,1).toUpperCase() +dayBasedOnDate.substring(1) +", "+monthBasedOnDate.substring(0,1).toUpperCase() +monthBasedOnDate.substring(1) +" "+pickupdate[1] +" "+yearBasedOnDate+ " "+ProperTime+ " "+pickupdate[3] +" "+pickupdate[4];
                    String [] locations=DbUtility.getFullPickUpAndDropOff(pickUpRef);
                    String scheduledBy = (String) cucumberContextManager.getScenarioContext("ScheduleBY");
                    String rbsbNumber = (String) cucumberContextManager.getScenarioContext("RB/SB_Number");
                    String specialInstructions = (String) cucumberContextManager.getScenarioContext("SpecialInstruction");
                    String deliveryPurpose = (String) cucumberContextManager.getScenarioContext("DeliveryPurpose");
                    String itemsToDeliver = (String) cucumberContextManager.getScenarioContext("ItemsToDeliver");

                    message = utility.getABungiiDeliveryScheduled(locations[0],properTime,Customer_Name, customerPhone,driverName,driverPhone,OnlyLicenceplate[3],itemsToDeliver,specialInstructions,deliveryPurpose,rbsbNumber,scheduledBy);
                }
                break;
        }
        message= message.replaceAll(" ","");
        //message= message.replaceAll("EST","EDT");
        logger.detail("Email Body (Expected): "+message);
        testStepAssert.isEquals(emailBody, message,"Email "+ message+" content should match with Actual", "Email  "+emailBody+" content matches with Expected", "Email "+emailBody+"  content doesn't match with Expected");
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful",
                "Error performing step,Please check logs for more details", true);
    }
    }

    @Then("^The partner firm should not receive \"([^\"]*)\" email$")
    public void the_partner_firm_should_not_receive_something_email(String emailSubject) throws Throwable {
        try {
            String emailBody = utility.GetSpecificPlainTextEmailIfReceived(PropertyUtility.getEmailProperties("email.from.address"), PropertyUtility.getEmailProperties("email.client.id"), emailSubject);
            if (emailBody == null) {
                testStepAssert.isTrue(true, "Email having subject '" + emailSubject + "' should  not be received",
                        "Email having subject '" + emailSubject + "' is not  received",
                        "Email having subject '" + emailSubject + "' is received");
            } else {
                testStepAssert.isFail("Email with subject " + emailSubject + " is present in mail");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I click on the \"([^\"]*)\" Button on \"([^\"]*)\" popup$")
    public void i_click_on_the_something_button_on_something_popup(String button, String popup) throws Throwable {
        try{
            switch (popup){
                case "Accept Delivery":
                    Thread.sleep(5000);
                    action.click(tripAlertSettingsPage.Button_OKPopupOnAcceptedDelivery());
                    break;
            }

            log("I click on "+button+" on "+ popup ,
                    "I have clicked on "+button+" on "+ popup, false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }
}
