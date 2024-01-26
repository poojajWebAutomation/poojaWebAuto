package com.bungii.ios.utilityfunctions;

import com.bungii.SetupManager;
import com.bungii.api.stepdefinitions.BungiiSteps;
import com.bungii.api.utilityFunctions.AuthServices;
import com.bungii.api.utilityFunctions.GoogleMaps;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.EmailUtility;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.enums.Status;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.*;
import com.bungii.ios.pages.admin.LogInPage;
import com.bungii.ios.pages.admin.ScheduledTripsPage;
import com.bungii.ios.pages.customer.*;
import com.bungii.ios.pages.driver.BungiiCompletedPage;
import com.bungii.ios.pages.driver.HomePage;
import com.bungii.ios.pages.driver.LoginPage;
import com.bungii.ios.pages.driver.UpdateStatusPage;
import com.bungii.ios.pages.other.MessagesPage;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.ios.stepdefinitions.admin.LogInSteps;
import com.bungii.ios.stepdefinitions.admin.*;
import io.appium.java_client.MobileElement;
import io.appium.java_client.appmanagement.ApplicationState;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.pass;

public class GeneralUtility extends DriverBase {
    private static LogUtility logger = new LogUtility(GeneralUtility.class);

    NotificationPage notificationPage = new NotificationPage();
    HomePage driverHomePage = new HomePage();
    com.bungii.ios.pages.customer.HomePage customerHomePage = new com.bungii.ios.pages.customer.HomePage();
    EstimatePage estimatePage = new EstimatePage();
    ActionManager action = new ActionManager();
    LoginPage driverLoginPage = new LoginPage();
    UpdateStatusPage driverUpdateStatusPage = new UpdateStatusPage();
    MessagesPage messagesPage = new MessagesPage();
    BungiiCompletePage bungiiCompletePage = new BungiiCompletePage();
    PromotionPage promotionPage = new PromotionPage();
    BungiiCompletedPage driverBungiiCompletedPage = new BungiiCompletedPage();
    EnableNotificationPage enableNotificationPage = new EnableNotificationPage();
    EnableLocationPage enableLocationPage = new EnableLocationPage();
    com.bungii.ios.pages.customer.UpdateStatusPage customerUpdateStatusPage = new com.bungii.ios.pages.customer.UpdateStatusPage();
    ScheduledBungiiPage scheduledBungiiPage = new ScheduledBungiiPage();
    EmailUtility emailUtility = new EmailUtility();
    DashBoardPage admin_dashboardPage = new DashBoardPage();
    int[][] rgb = {
            {238, 29, 55},
            {255, 169, 66},
            {169, 204, 50},
            {37, 171, 226},
            {50, 51, 255},

    };
    private TermsAndConditionPage termsAndConditionPage = new TermsAndConditionPage();
    private TutorialPage tutorialPage = new TutorialPage();

    /**
     * @param file File object pointing to screenshot image file
     * @param x    x co-ordinate of point whose pixel color needs to be find out
     * @param y    y co-ordinate of point whose pixel color needs to be find out
     * @return RGB value of pount
     * @throws IOException
     */
    public static int[] getPixelColor(File file, int x, int y) throws IOException {

        BufferedImage image = ImageIO.read(file);
        image.getWidth();
        int deviceWidth = SetupManager.getDriver().manage().window().getSize().width;
        int retinaRatio = image.getWidth() / deviceWidth;
        // Getting pixel color by position x and y
        int clr = image.getRGB(retinaRatio * x, retinaRatio * y);
        int red = (clr & 0x00ff0000) >> 16;
        int green = (clr & 0x0000ff00) >> 8;
        int blue = clr & 0x000000ff;
        logger.detail("Red Color value = " + red + "| Green Color value = " + green + " | Blue Color value = " + blue);
      //  logger.detail("Green Color value = " + green);
       // logger.detail("Blue Color value = " + blue);

        int[] rgbValue = {red, green, blue};
        return rgbValue;
    }

    public String GetAdminUrl() {
        String adminURL = null;
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("DEV"))
            adminURL = PropertyUtility.getDataProperties("dev.admin.url");
        if (environment.equalsIgnoreCase("QA") || environment.equalsIgnoreCase("QA_AUTO")|| environment.equalsIgnoreCase("QA_AUTO_AWS"))
            adminURL = PropertyUtility.getDataProperties("qa.admin.url");
        if (environment.equalsIgnoreCase("STAGE"))
            adminURL = PropertyUtility.getDataProperties("stage.admin.url");
        return adminURL;
    }

    public void handleIosUpdateMessage() {
        try {
            if (action.isAlertPresent()) {
                String alertMessage = action.getAlertMessage();
                List<String> getListOfAlertButton = action.getListOfAlertButton();
                if (alertMessage.contains("new iOS update")) {
                    if (getListOfAlertButton.contains("Close")) {
                        action.clickAlertButton("Close");
                    }
                } else if (alertMessage.contains("Failed to fetch your profile")) {
                    if (getListOfAlertButton.contains("OK")) {
                        action.clickAlertButton("OK");
                    }
                } else if (alertMessage.contains("we are not operating in your area")) {
                    if (getListOfAlertButton.contains("Done")) {
                        action.clickAlertButton("Done");
                    }
                }else if (alertMessage.contains("would like to paste from")) {
                    if (getListOfAlertButton.contains("Allow Paste")) {
                        action.clickAlertButton("Allow Paste");
                    }
                }

            }
        }catch( Exception e){

        }
    }
    public void handleAppleIDVerification() {
        try {
            if (action.isAlertPresent()) {
                String alertMessage = action.getAlertMessage();
                List<String> getListOfAlertButton = action.getListOfAlertButton();
                if (alertMessage.contains("Apple ID Verification")) {
                    if (getListOfAlertButton.contains("Not Now")) {
                        action.clickAlertButton("Not Now");
                    }
                } else if (alertMessage.contains("Failed to fetch your profile")) {
                    if (getListOfAlertButton.contains("OK")) {
                        action.clickAlertButton("OK");
                    }
                } else if (alertMessage.contains("we are not operating in your area")) {
                    if (getListOfAlertButton.contains("Done")) {
                        action.clickAlertButton("Done");
                    }
                }
                else if (alertMessage.contains("Enabling location services allows you to connect with Bungii customers")) {
                    if (getListOfAlertButton.contains("OK")) {
                        action.clickAlertButton("OK");
                    }
                }

            }
        }catch( Exception e){

        }
    }

    public void recoverScenarioscheduled() {

        try {
            logger.detail("Inside recovery scenario for scheduled");

            //restart app
            ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
            ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
            action.click(customerHomePage.Button_AppMenu());
            action.click(customerHomePage.AppMenu_MyBungiisTrip());
            List<WebElement> allschBungii = scheduledBungiiPage.List_SchBungii();
            ArrayList<String> detailsArray = new ArrayList<String>();
            if (allschBungii.size() > 0) {
                for (int i = 0; i < allschBungii.size(); i++) {
                    detailsArray.add(action.getNameAttribute(allschBungii.get(i)));
                }
                SetupManager.getObject().createNewWebdriverInstance("RECOVERY", "chrome");
                SetupManager.getObject().useDriverInstance("RECOVERY");
                SetupManager.getDriver().get(GetAdminUrl());
                new LogInSteps(new LogInPage()).i_log_in_to_admin_portal();
                new DashBoardSteps(new DashBoardPage()).i_select_something_from_admin_sidebar("scheduled trip");
                Map<String, String> tripDetails = new HashMap<String, String>();
                tripDetails.put("CUSTOMER", (String) cucumberContextManager.getScenarioContext("CUSTOMER"));
                String bungiiTime = "";
                for (int i = 0; i < detailsArray.size(); i++) {
                    bungiiTime = detailsArray.get(i);
                    logger.detail("bungiiTime" + bungiiTime);
                    tripDetails.put("SCHEDULED_DATE", new ScheduledTripSteps(new ScheduledTripsPage()).getPortalTime(bungiiTime.replace("CDT", "CST").replace("EDT", "EST").replace("MDT", "MST")));
                    tripDetails.put("BUNGII_DISTANCE", "");
                    new ScheduledTripSteps(new ScheduledTripsPage()).cancelBungii(tripDetails, "5", "RECOVERY");

                }
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));

        }
        SetupManager.getObject().useDriverInstance("ORIGINAL");
    }
    public void hideNotifications() {
    action.hideNotifications();
    }
    public void resetDriverAppsStateToInital()
    {
        String driverPhoneCode="1";
        String driverPhoneNum=((String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE")) ;
        if(driverPhoneNum!= "") {
            String driverPassword =(String) cucumberContextManager.getScenarioContext("DRIVER_1_PASSWORD");
            new AuthServices().driverLogin(driverPhoneCode, driverPhoneNum, driverPassword);
        }
        String driverPhoneNum2=((String) cucumberContextManager.getScenarioContext("DRIVER_2_PHONE")) ;
        if(driverPhoneNum2!= "") {
            String driverPassword = (String) cucumberContextManager.getScenarioContext("DRIVER_2_PASSWORD");
           new AuthServices().driverLogin(driverPhoneCode, driverPhoneNum2, driverPassword);
        }
    }
    public void recoverScenario() {
        logger.detail("***** RECOVERING CUSTOMER AND DRIVER STATE : UI ACTIONS *****");

        try {
    if (action.isElementPresent(customerHomePage.Application_Name(true))) {
        //do nothing
       // if(action.getScreenHeader(customerHomePage.Application_Name()).equalsIgnoreCase("BUNGII DRIVER"));
       // SetupManager.getObject().restartApp();

    } else if (action.isElementPresent(customerHomePage.AppIcon_Phone(true))) {
        //if app is closed and just phone screen is present then restart app
        SetupManager.getObject().restartApp();
    }
    //  else if (action.isElementPresent(notificationPage.Button_NotificationScreen(true)) || action.isElementPresent(notificationPage.Cell_Notification(true))) {
    else if (action.isElementPresent(notificationPage.Generic_Notification(true))) {
        //Remove notification screen
        action.hideNotifications();
        logger.detail("Notification page is removed");
    }
    //Handle Alert
    if (action.isAlertPresent()) {

        String alertMessage = action.getAlertMessage();
        logger.detail("Alert is present on screen,Alert message:" + alertMessage);

        List<String> getListOfAlertButton = action.getListOfAlertButton();

        if (alertMessage.contains("Software Update")) {
            if (getListOfAlertButton.contains("Later")) {
                action.clickAlertButton("Later");
                if (action.isElementPresent(messagesPage.Button_RemindMeLater(true)))
                    action.click(messagesPage.Button_RemindMeLater());
            }
        } else if (alertMessage.contains("new iOS update")) {
            if (getListOfAlertButton.contains("Close")) {
                action.clickAlertButton("Close");

            }
        } else if (getListOfAlertButton.contains("Cancel")) {
            action.clickAlertButton("Cancel");
        } else {
            if (getListOfAlertButton.contains("Done"))
                action.clickAlertButton("Done");

            if (getListOfAlertButton.contains("Close"))
                action.clickAlertButton("Close");

            else if (getListOfAlertButton.contains("Always Allow"))
                action.clickAlertButton("Always Allow");
            else if (getListOfAlertButton.contains("Allow"))
                action.clickAlertButton("Allow");

        }
    }
    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Driver"));
    // action.switchApplication(PropertyUtility.getProp("bundleId_Driver"));
    logger.detail("Switched to Driver");

    //If we restart app then close view item page is dismissed
    //view item page
/*        if (action.isElementPresent(driverUpdateStatusPage.Button_CloseViewItems(true))) {
            action.click(driverUpdateStatusPage.Button_CloseViewItems());
            logger.detail("Clicked Close on view item screen");

        }*/
    if (action.isElementPresent(driverUpdateStatusPage.Text_NavigationBar(true))) {

        String screen = action.getScreenHeader(driverUpdateStatusPage.Text_NavigationBar());
        logger.detail("screen is " + screen);
        if (screen.equalsIgnoreCase(Status.ARRIVED.toString())) {
            logger.detail("Driver struck on arrived screen");
            action.click(driverUpdateStatusPage.Button_Cancel());
            action.clickAlertButton("Yes");
        } else if (screen.equals(Status.EN_ROUTE.toString())) {
            logger.detail("Driver struck on EN_ROUTE screen");
            action.click(driverUpdateStatusPage.Button_Cancel());
            action.clickAlertButton("Yes");
        } else if (screen.equals(Status.LOADING_ITEM.toString())) {
            logger.detail("Driver struck on LOADING_ITEM screen");
            updateStatus();
            updateStatus();
            updateStatus();
            if (action.isAlertPresent()) {
                if (action.getListOfAlertButton().contains("Initiate")) {
                    action.clickAlertButton("Initiate");
                }
            }
            action.click(driverBungiiCompletedPage.Button_Next_Bungii());
        } else if (screen.equals(Status.DRIVING_TO_DROP_OFF.toString())) {
            logger.detail("Driver struck on DRIVING_TO_DROP_OFF screen");
            updateStatus();
            updateStatus();
            if (action.isAlertPresent()) {
                if (action.getListOfAlertButton().contains("Initiate")) {
                    action.clickAlertButton("Initiate");
                }
            }
            action.click(driverBungiiCompletedPage.Button_Next_Bungii());
        } else if (screen.equals(Status.UNLOADING_ITEM.toString())) {
            logger.detail("Driver struck on UNLOADING_ITEM screen");
            updateStatus();
            if (action.isAlertPresent()) {
                if (action.getListOfAlertButton().contains("Initiate")) {
                    action.clickAlertButton("Initiate");
                }
            }
            action.click(driverBungiiCompletedPage.Button_Next_Bungii());
        } else if (screen.equals(PropertyUtility.getMessage("driver.navigation.bungii.completed"))) {
            logger.detail("Driver struck on bungii completed screen");
            action.click(driverBungiiCompletedPage.Button_Next_Bungii());
        }

    }
    SetupManager.getObject().restartApp(PropertyUtility.getProp("bundleId_Customer"));
    // action.switchApplication(PropertyUtility.getProp("bundleId_Customer"));
    if (action.isAlertPresent()) {
        List<String> getListOfAlertButton = action.getListOfAlertButton();
        if (getListOfAlertButton.contains("OK"))
            action.clickAlertButton("OK");
    }
    String NavigationBarName = action.getScreenHeader(customerHomePage.Text_NavigationBar());
    if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.searching"))) {
        logger.detail("Customer struck on searching screen");
        action.click(estimatePage.Button_Cancel());
        SetupManager.getDriver().switchTo().alert().accept();
    } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.bungii.complete"))) {
        logger.detail("Customer struck on bungii complete screen");
        action.click(bungiiCompletePage.Button_Close());
        ;
        action.click(promotionPage.Button_IdontLikePromo());
    } else if (NavigationBarName.equals(PropertyUtility.getMessage("customer.navigation.promotion"))) {
        logger.detail("Customer struck on promotion screen");
        action.click(promotionPage.Button_IdontLikePromo());
    } else if (NavigationBarName.equalsIgnoreCase(PropertyUtility.getMessage("customer.navigation.terms.condition"))) {
        navigateFromTermToHomeScreen();
    } else if (NavigationBarName.equalsIgnoreCase("NOTIFICATIONS")) {
        action.click(enableNotificationPage.Button_Sure());
        action.clickAlertButton("Allow");
        if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
            action.click(enableLocationPage.Button_Sure());
            action.clickAlertButton("Always Allow");
        }
    }
}
     catch(Exception e){}
    }

    public void navigateFromTermToHomeScreen() throws InterruptedException {
        //action.click(termsAndConditionPage.Button_CheckOff());
        //action.click(termsAndConditionPage.Button_Continue());
        if(action.isElementPresent(termsAndConditionPage.Button_CheckOff(true))) {
            action.click(termsAndConditionPage.Button_CheckOff());
            action.click(termsAndConditionPage.Button_Continue());
            Thread.sleep(3000);
            // pageHeader = utility.getPageHeader();
        }
        if (action.isElementPresent(enableNotificationPage.Button_Sure(true))) {
            action.click(enableNotificationPage.Button_Sure());
            action.clickAlertButton("Allow");
        }

        if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
            action.click(enableLocationPage.Button_Sure());
            Thread.sleep(3000);
            action.clickAlertButton("Always Allow"); // Added for customer App changes  Krishna
        }
        Thread.sleep(5000);
        action.click(tutorialPage.Button_Close());

       // logger.detail("***** RECOVERING STATE : UI ACTIONS COMPLETE *****");

    }

    /**
     * Slide the slider to update status
     */
    public void updateStatus() {
        //get locator rectangle is time consuming process
/*        if (initial == null)
            initial = action.getLocatorRectangle(updateStatusPage.AreaSlide());*/
        Rectangle initial;
        if (!isSliderValueContainsInContext("DRIVER")) {
            initial = action.getLocatorRectangle(driverUpdateStatusPage.AreaSlide());
            addSliderValueToFeatureContext("DRIVER", initial);

        } else {
            initial = getSliderValueFromContext("DRIVER");
        }

        action.dragFromToForDuration(0, 0, initial.getWidth(), initial.getHeight(), 1, driverUpdateStatusPage.AreaSlide());
    }

    /**
     * Calculate estimate cost of trip check if less than minimum cost then
     * return minimum cost
     *
     * @param tripDistance Trip distance
     * @param loadTime     load / unload time
     * @param estTime      estimate trip complete time
     * @param Promo        Promo
     * @return
     */
    public double bungiiEstimate(String tripDistance, String loadTime, String estTime, String Promo) {
        //get bungii type and current geofence type.
        String bungiiType = (String) cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER");
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get minimum cost,Mile value,Minutes value of Geofence
        double minCost = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.minimum.cost")),
                perMileValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.miles")),
                perMinutesValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.minutes"));

        //Get trip distance from db instead of screen
        double distance = Double.parseDouble(tripDistance.trim());
        //     double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double loadUnloadTime = Double.parseDouble(loadTime.replace(" mins", ""));
        double tripTime = Double.parseDouble(estTime);

        double estimateCost = distance * perMileValue + loadUnloadTime * perMinutesValue + tripTime * perMinutesValue;
        //check if trip is duo trip , if yes then double estimate cost
        if (bungiiType.equalsIgnoreCase("DUO"))
            estimateCost = estimateCost * 2;
        //Subtract discount value from estimate cost
        Promo = Promo.contains("ADD") ? "0" : Promo;
        double discount = 0;
        if (Promo.contains("$"))
            discount = Double.parseDouble(Promo.replace("-$", ""));
        else if (Promo.contains("%"))
            discount = estimateCost * Double.parseDouble(Promo.replace("-", "").replace("%", "")) / 100;
        estimateCost = estimateCost - discount;
        //Check if estimate is greater than min
        estimateCost = estimateCost > minCost ? estimateCost : minCost;

        return estimateCost;
    }

    /**
     * Get geofence data from properties file
     *
     * @param geofenceName Geofence name
     * @param partialKey   this is partial value that is to be searched in properties file
     * @return get message from Geofence propertiese file
     */
    public String getGeofenceData(String geofenceName, String partialKey) {
        geofenceName = (geofenceName.isEmpty() || geofenceName.equals("")) ? PropertyUtility.getGeofenceData("current.geofence") : geofenceName.toLowerCase();
        String actualKey = geofenceName + "." + partialKey;
        return PropertyUtility.getGeofenceData(actualKey);
    }

    /**
     * Get timezone for geofence, read it from properties file and conver into Time zone object
     *
     * @return
     */
    public String getTimeZoneBasedOnGeofence() {
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get timezone value of Geofence
        //Add Code to handle daylight

        String getGeofenceTimeZone = getGeofenceData(currentGeofence, "geofence.timezone");
        if(TimeZone.getTimeZone(getGeofenceTimeZone).inDaylightTime( new Date() ))
            getGeofenceTimeZone = getGeofenceTimeZone.replace("ST","DT");
        return getGeofenceTimeZone;
    }

    /**
     * Get timezone for geofence, read it from properties file and conver into Time zone object
     *
     * @return
     */
    public String getTimeZoneBasedOnGeofenceId() {
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        // currentGeofence="kansas";
        //get timezone value of Geofence
        String getGeofenceTimeZone = getGeofenceData(currentGeofence, "geofence.timezone.id");
        return getGeofenceTimeZone;
    }

    /**
     * Reset application defined in app capabilite
     */
    public boolean installCustomerApp() {
        boolean isInstalled = false;
        try {

            String customerIPAFile = FileUtility.getSuiteResource("", PropertyUtility.getDataProperties("ipa.file.location").replace("{ENVT}", PropertyUtility.environment));
            if (!Files.exists(Paths.get(customerIPAFile))) {
                customerIPAFile = PropertyUtility.getDataProperties("ipa.file.location").replace("{ENVT}", PropertyUtility.environment);
            }

            logger.detail("IPA file Location " + customerIPAFile);
            if (!Files.exists(Paths.get(customerIPAFile))) {
                logger.detail("IPA file doesnot exist " + customerIPAFile);

/*                warning("IPA file doesnot exist on local machine",
                        "File " + customerIPAFile, false);*/
            }
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).closeApp();
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).removeApp(PropertyUtility.getProp("bundleId_Customer"));
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).installApp(customerIPAFile);
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).launchApp();
            isInstalled = true;

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        return isInstalled;


    }


    /**
     * Reset application defined in app capabilite
     */
    public boolean installDriverApp() {
        boolean isInstalled = false;
        try {

            String driverIpaFile = FileUtility.getSuiteResource("", PropertyUtility.getDataProperties("driver.ipa.file.location").replace("{ENVT}", PropertyUtility.environment));
            if (!Files.exists(Paths.get(driverIpaFile))) {
                driverIpaFile = PropertyUtility.getDataProperties("driver.ipa.file.location").replace("{ENVT}", PropertyUtility.environment);
            }

            logger.detail("IPA file Location " + driverIpaFile);
            if (!Files.exists(Paths.get(driverIpaFile))) {
                logger.detail("IPA file doesnot exist " + driverIpaFile);

   /*             warning("IPA file doesnot exist on local machine",
                        "File " + driverIpaFile, false);*/
            }
            ((IOSDriver<MobileElement>) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
            try {
                ((IOSDriver<MobileElement>) SetupManager.getDriver()).removeApp(PropertyUtility.getProp("bundleId_Driver"));

            } catch (Exception e) {
            }
            logger.detail("Trying to install app " + driverIpaFile);

            ((IOSDriver<MobileElement>) SetupManager.getDriver()).installApp(driverIpaFile);
            logger.detail("done Trying to install app " + driverIpaFile);

            ((IOSDriver<MobileElement>) SetupManager.getDriver()).launchApp();
            isInstalled = true;

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
        return isInstalled;


    }

    public boolean verifyDriverPageHeader(String key) throws InterruptedException{
        String currentApplication = (String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION");

        boolean isCorrectPage = false;
        String expectedMessage = getExpectedHeader(key.toUpperCase(), currentApplication);

        if(key.equalsIgnoreCase("Bungii")){
            List<WebElement> count = Collections.singletonList(driverHomePage.Text_HomeLoginNavigationBar());
            if(count.size() == 1)
            isCorrectPage = true;

        }
        else {
            action.textToBePresentInElementName(driverHomePage.Text_LoginNavigationBar(key), expectedMessage);
            isCorrectPage = action.getScreenHeader(driverHomePage.Text_LoginNavigationBar(key)).equals(expectedMessage);
        }
        return isCorrectPage;
    }

    public boolean verifyPageHeader(String key) throws InterruptedException {
        String currentApplication = (String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION");

        boolean isCorrectPage = false;
        switch (key.toUpperCase()) {
            case "BUNGII.COM":
                Thread.sleep(5000);
                // waitForExpectedElement(By.xpath("//XCUIElementTypeApplication[@name='Safari']"));
                isCorrectPage = driverHomePage.isElementEnabled(driverHomePage.findElement("//XCUIElementTypeApplication[@name='Safari']", PageBase.LocatorType.XPath))
                        && action.getValueAttribute(driverHomePage.findElement("//*[@label='Address']", PageBase.LocatorType.XPath)).contains("bungii.com");
                break;
            case "AVAILABLE BUNGIIS":
                if (currentApplication.equals("DRIVER")) {
//                    driverHomePage.visibilityOf(driverHomePage.Text_AvailableTrips());
                    isCorrectPage = action.getScreenHeader(driverHomePage.Text_NavigationBar()).equals("AVAILABLE BUNGIIS");
                    break;
                }
            case "DRIVER HOME":
            case "HOME":
                if (currentApplication.equals("DRIVER")) {
                    String naviagationBar = action.getScreenHeader(driverHomePage.Text_NavigationBar());
                    if (naviagationBar.equals("ONLINE") || naviagationBar.equals("OFFLINE") || naviagationBar.equals("Bungii_Driver.AppView")) {
                        isCorrectPage = true;
                    } else {
                        Thread.sleep(7000);
                        isCorrectPage = action.getScreenHeader(driverHomePage.Text_NavigationBar()).equals("ONLINE") || action.getScreenHeader(driverHomePage.Text_NavigationBar()).equals("OFFLINE") || action.getScreenHeader(driverHomePage.Text_NavigationBar()).equals("Bungii_Driver.AppView");
                    }
                    break;
                } else {
                    String expectedMessage = PropertyUtility.getMessage("customer.navigation.home");
                    action.textToBePresentInElementName(driverHomePage.Text_NavigationBar(), expectedMessage);
                    logger.detail(" Verifying Home page , actual is|"+action.getScreenHeader(driverHomePage.Text_NavigationBar())+"| expected is|"+expectedMessage);
                    isCorrectPage = action.getScreenHeader(driverHomePage.Text_NavigationBar()).equals(expectedMessage);
                    break;
                    //Customer app
                }
            case "BUNGII":{
                    logger.detail(" CUSTOMER APP ");
                    String expectedMessage = PropertyUtility.getMessage("customer.navigation.home");
                    action.textToBePresentInElementName(driverHomePage.Text_NavigationBar(), expectedMessage);
                    logger.detail(" Verifying Home page , actual is|"+action.getScreenHeader(driverHomePage.Text_NavigationBar())+"| expected is|"+expectedMessage);
                    isCorrectPage = action.getScreenHeader(driverHomePage.Text_NavigationBar()).equals(expectedMessage);
                    break;}
                    //Customer app
            case "BUNGII COMPLETED":
                logger.detail("DRIVER APP");
                String bungiiCompleted = PropertyUtility.getMessage("driver.navigation.bungii.completed");
                isCorrectPage = action.getScreenHeader(driverHomePage.Text_Bungii_Completed()).equals(bungiiCompleted);
                break;
            case "ITEMIZED EARNINGS":
                logger.detail("DRIVER APP");
                isCorrectPage = action.getScreenHeader(driverHomePage.Header_ItemizedEarnings()).equals("ITEMIZED EARNINGS");
                break;
            case "EARNINGS":
                logger.detail("DRIVER APP");
                isCorrectPage = action.getScreenHeader(driverHomePage.Header_Earnings()).equals("EARNINGS");
                break;
            case "RATE DUO TEAMMATE":
                logger.detail("DRIVER APP");
                isCorrectPage = action.getScreenHeader(driverHomePage.Header_RateDuoTeammate()).equals("Rate duo teammate");
                break;
            case "REFERRAL":
                logger.detail("DRIVER APP");
                isCorrectPage = action.getScreenHeader(driverHomePage.Header_Referral()).equals("REFERRAL");
                break;
            case "REFERRAL HISTORY":
                logger.detail("DRIVER APP");
                isCorrectPage = action.getScreenHeader(driverHomePage.Header_ReferralHistory()).equals("REFERRAL HISTORY");
                break;
            case "SEARCHING":
                logger.detail("CUSTOMER APP");
                String expectedHeader = getExpectedHeader(key.toUpperCase(), currentApplication);
                isCorrectPage = action.getScreenHeader(driverHomePage.Header_Searching()).equals(expectedHeader);
                break;
            case "INVITE":
                logger.detail("CUSTOMER APP");
                isCorrectPage = action.getScreenHeader(driverHomePage.Header_Invite()).equals(getExpectedHeader(key.toUpperCase(), currentApplication));
                break;
            default:
                String expectedMessage = getExpectedHeader(key.toUpperCase(), currentApplication);
                try {
                    if (!action.isElementPresent(driverHomePage.Text_NavigationBar(true))) {
                        Thread.sleep(25000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    action.textToBePresentInElementName(driverHomePage.Text_NavigationBar(), expectedMessage);
                    isCorrectPage = action.getScreenHeader(driverHomePage.Text_NavigationBar()).equals(expectedMessage);
                    if (!isCorrectPage) {
                        if(expectedMessage.equalsIgnoreCase("BUNGII ACCEPTED") && action.getScreenHeader(driverHomePage.Text_NavigationBar()).equalsIgnoreCase("EN ROUTE"))
                        {
                            isCorrectPage= true;
                            logger.detail("Bypassed BUNGII ACCEPTED screen and directly showing Enroute screen");
                        }
                        else {
                            if(currentApplication.equalsIgnoreCase("DRIVER")){
                                action.textToBePresentInElementName(driverHomePage.Text_DriverNavigationBar(key), expectedMessage);
                                isCorrectPage = action.getScreenHeader(driverHomePage.Text_DriverNavigationBar(key)).equals(expectedMessage);
                            }
                            else {
                                action.textToBePresentInElementName(driverHomePage.Text_NavigationBar(), expectedMessage);
                                isCorrectPage = action.getScreenHeader(driverHomePage.Text_NavigationBar()).equals(expectedMessage);
                            }
                        }
                    }
        }
        return isCorrectPage;
    }

    public String getPageHeader() {
        return action.getText(driverHomePage.Text_NavigationBar());
    }
    private String getExpectedHeader(String screen, String currentApplication) {
        String expectedMessage = "";
        switch (screen.toUpperCase()) {
            case "BUNGII DETAILS":
                if (currentApplication.equals("CUSTOMER"))
                    expectedMessage = PropertyUtility.getMessage("customer.navigation.bungiidetails");
                else
                    expectedMessage = PropertyUtility.getMessage("driver.navigation.bungiidetails");
                break;
         /*   case "BUN DETAILS":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.trip.details");
                break;*/
            case "HOME":
            case "BUNGII":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.home");
                break;
            case "SET PICKUP TIME":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.setPickupTime");
                break;
            case "FAQ":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.faq");
                break;
            case "ACCOUNT INFO":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.account");
                break;
            case "CUSTOMER PRIVACY POLICY":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.privacy.policy");
                break;
            case "SCHEDULED BUNGII":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.scheduled.bungii");
                break;
            case "AVAILABLE BUNGIIS":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.available.trips");
                break;
            case "LEADERBOARD":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.leaderboard");
                break;
            case "FEEDBACK":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.feedback");
                break;
            case "EARNINGS":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.earnings");
                break;
            case "ALERT SETTINGS":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.trip.alert.settings");
                break;
            case "PRIVACY POLICY":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.privacy.policy");
                break;
            case "BUNGII STORE":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.store");
                break;
            case "SCHEDULED BUNGIIS":
            case "MY BUNGIIS":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.scheduled.bungii");
                break;
            case "PAYMENT":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.payment");
                break;
            case "SUPPORT":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.support");
                break;
            case "PROMOS":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.promos");
                break;

            case "ESTIMATE":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.estimate");
                break;
            case "SUCCESS":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.success");
                break;
            case "BUNGII COMPLETED":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.bungii.completed");
                break;
            case "RATE CUSTOMER":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.rate.customer");
                break;
            case "BUNGII COMPLETE":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.bungii.complete");
                break;
            case "PROMOTION":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.promotion");
                break;
            case "LOG IN":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.login");
                break;
            case "PAYMENT MODE":
                expectedMessage = "PAYMENT MODE";
                break;
            case "INVITE":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.invite");
                break;
            case "SIGN UP":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.signup");
                break;
            case "FORGOT PASSWORD":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.forgot.password");
                break;
            case "DRIVER NOT AVAILABLE":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.driver.not.found");
                break;
            case "BUNGII REQUEST":
                expectedMessage = PropertyUtility.getMessage("driver.navigation.bungii.request");
                break;
            case "BUNGII ACCEPTED":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.bungii.accepted");
                break;
            case "VERIFICATION":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.verification");
                break;
            case "TERMS AND CONDITION":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.terms.condition");
                break;
            case "ALLOW NOTIFICATIONS":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.allow.notifications");
                break;
            case "ALLOW LOCATION":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.allow.locations");
                break;
            case "DRIVER SEARCH":
            case "SEARCHING":
                expectedMessage = PropertyUtility.getMessage("customer.navigation.searching");
                break;
            case "ARRIVED":
                expectedMessage = Status.ARRIVED.toString();
                break;
            case "EN ROUTE":
                expectedMessage = Status.EN_ROUTE.toString();
                break;
            case "LOADING ITEM":
                expectedMessage = Status.LOADING_ITEM.toString();
                break;
            case "LOADING ITEMS":
                expectedMessage = Status.LOADING_ITEMS.toString();
                break;
            case "DRIVING TO DROP OFF":
                expectedMessage = Status.DRIVING_TO_DROP_OFF.toString();
                break;
            case "DRIVING TO DROP-OFF":
                expectedMessage = Status.DRIVING_TO_DROPOFF.toString();
                break;
            case "UNLOADING ITEM":
                expectedMessage = Status.UNLOADING_ITEM.toString();
                break;
            case "UNLOADING ITEMS":
                expectedMessage = Status.UNLOADING_ITEMS.toString();
                break;
            default:
                // error("Verify Screen " + screen, "UnImplemented Step or in correct screen", "UnImplemented Step", true);
                break;
        }
        return expectedMessage;
    }


    public void grantPermissionToDriverApp() throws InterruptedException {
        if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
            action.click(enableLocationPage.Button_Sure());
            List<String> getListOfAlertButton = action.getListOfAlertButton();
            if(getListOfAlertButton.contains("Allow")){
                action.clickAlertButton("Allow");
                Thread.sleep(5000);
            }
        }
        if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
            action.click(enableLocationPage.Button_Sure());
            action.clickAlertButton("Allow While Using App");
        }
        Thread.sleep(3000);
        if(action.isAlertPresent()){
            List<String> getListOfAlertButton = action.getListOfAlertButton();
            if(getListOfAlertButton.contains("Allow While Using App")){
                action.clickAlertButton("Allow While Using App");
                Thread.sleep(3000);
            }
            if(action.isAlertPresent()){
                if(getListOfAlertButton.contains("Change to Always Allow")) {
                    action.clickAlertButton("Change to Always Allow");
                    Thread.sleep(3000);
                }
            if(action.isElementPresent(enableLocationPage.Button_Done())) {
                action.click(enableLocationPage.Button_Done());
            }
            }
        }
    }

    public void loginToDriverApp(String phone, String password) throws InterruptedException {
        String navigationBarName = action.getScreenHeader(driverHomePage.NavigationBar_Status());
        if (!(navigationBarName.equalsIgnoreCase("ONLINE") || navigationBarName.equalsIgnoreCase("OFFLINE"))) {
            if (action.isAlertPresent()) {

                List<String> getListOfAlertButton = action.getListOfAlertButton();
                if (getListOfAlertButton.contains("OK"))
                    action.clickAlertButton("OK");
            }
            if (action.isElementPresent(driverLoginPage.TextField_PhoneNumber(true))) {
                WebElement element = driverLoginPage.TextField_PhoneNumber();
                action.sendKeys(driverLoginPage.TextField_PhoneNumber(), phone);
                action.sendKeys(driverLoginPage.Textfield_Password(), password);
                action.click(driverLoginPage.Button_Login());
                Thread.sleep(5000);
                cucumberContextManager.setScenarioContext("DRIVER_PHONE_PUSH", phone);
                cucumberContextManager.setScenarioContext("DRIVER_PWD_PUSH", password);
try {
    navigationBarName = action.getNameAttribute(driverHomePage.NavigationBar_Status(true));

    if (navigationBarName != null && !navigationBarName.isEmpty())
        if (navigationBarName.equals("NOTIFICATIONS") || navigationBarName.equals(PropertyUtility.getDataProperties("driver.notification.class.name"))) {
            grantPermissionToDriverApp();
            Thread.sleep(3000);
            if (action.isElementPresent(enableLocationPage.Button_Sure(true))) {
                Thread.sleep(3000);
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
        }
}
catch(Exception ex)
{
    //Ignore exception
}
            } else {
                //Not on Login page
            }
        }
    }

    public void switchToApp(String appName, String device) {
        try {
            logger.detail ("*** Switching to : " + appName + " application ****");
            String appHeader = "";
            if (!device.equalsIgnoreCase("same")) {
                try {
                    SetupManager.getObject().useDriverInstance(device);
                    log("I switch to " + device + " device instance",
                            "I switch to  " + device + " device instance", false);

                } catch (Exception e) {
                    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
                    error("Step should be successful",
                            "Error in switching to second device", true);
                }
                Thread.sleep(1000);
            }
            switch (appName.toUpperCase()) {
                case "DRIVER":
                    //action.switchApplication(PropertyUtility.getProp("bundleId_Driver"));
                    //((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                    int retry = 3;
                    String appstate = "";
                    while(retry>0) {
                        //((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Driver"));
                        ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                        //appHeader = "Bungii Driver QAAuto";
                        appHeader = PropertyUtility.getDataProperties("driver.app.name");
                        ApplicationState state = ((IOSDriver) SetupManager.getDriver()).queryAppState(PropertyUtility.getProp("bundleId_Driver"));
                        appstate = state.toString();
                        logger.detail("Switched To App : " + PropertyUtility.getProp("bundleId_Driver") + " | App State : " + appstate);
                        state = ((IOSDriver) SetupManager.getDriver()).queryAppState(PropertyUtility.getProp("bundleId_Customer"));
                        appstate = state.toString();
                        logger.detail("State of other App : " + PropertyUtility.getProp("bundleId_Customer") + " | App State : " + appstate);
                         if(appstate.equalsIgnoreCase("RUNNING_IN_BACKGROUND_SUSPENDED"))
                         {
                            // ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                             ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                             ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Driver"));
                         }
                        Thread.sleep(10000);
                        String pageSource = SetupManager.getDriver().getPageSource();
                        //logger.detail(" After switching Page Source : " + pageSource);
                        if(pageSource.contains("XCUIElementTypeApplication")) {
                           String appTitle = action.getAppName(driverHomePage.Application_Name(true));
                            if (appTitle!=null) {
                                if (!appTitle.equals(" ")) {
                                    // if(SetupManager.getDriver().getPageSource().contains(appHeader)){
                                    logger.detail("Actual App Header After Switching : " + appTitle);
                                    break;
                                }} else {
                                    if (action.isAlertPresent()) {
                                        String alertMessage = action.getAlertMessage();
                                        logger.detail("Alert is present on screen, Alert message:" + alertMessage);
                                        List<String> getListOfAlertButton = action.getListOfAlertButton();
                                        if (alertMessage.contains("Apple ID Verification"))
                                            action.clickAlertButton("Not Now");
                                    }
                                }
                        }
                        else
                        { int getPageCount =3;
                            while (getPageCount > 0){
                                 pageSource = SetupManager.getDriver().getPageSource();
                                if(pageSource.contains("XCUIElementTypeApplication")) {
                                    logger.detail("Driver App is running");
                                    break;
                                }
                                getPageCount--;
                            }

                        }
                        retry--;
                    }
                    break;
                case "CUSTOMER":
                    //((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                    int retry1 = 3;
                    String appstate1 = "";
                    while(retry1>0) {
                       // ((IOSDriver) SetupManager.getDriver()).terminateApp(PropertyUtility.getProp("bundleId_Customer"));
                    ((IOSDriver) SetupManager.getDriver()).activateApp(PropertyUtility.getProp("bundleId_Customer"));
                    //appHeader = "Bungii QAAuto";
                    appHeader = PropertyUtility.getDataProperties("customer.app.name");
                        ApplicationState state = ((IOSDriver) SetupManager.getDriver()).queryAppState(PropertyUtility.getProp("bundleId_Customer"));
                        appstate = state.toString();
                        logger.detail("Switched To App : " + PropertyUtility.getProp("bundleId_Customer") + " | App State : " + appstate1);
                        Thread.sleep(5000);
                        String pageSource = SetupManager.getDriver().getPageSource();
                        if(pageSource.contains("XCUIElementTypeApplication")) {
                            String appTitle = action.getAppName(customerHomePage.Application_Name());
                            if (appTitle != null) {
                                if (appTitle.equals(appHeader)) {
                                    //if(SetupManager.getDriver().getPageSource().contains(appHeader)){
                                    logger.detail("Actual App Header After Switching : " + appHeader);
                                    break;
                                } else {
                                    if (action.isAlertPresent()) {
                                        String alertMessage = action.getAlertMessage();
                                        logger.detail("Alert is present on screen, Alert message:" + alertMessage);
                                        List<String> getListOfAlertButton = action.getListOfAlertButton();
                                        if (alertMessage.contains("Apple ID Verification"))
                                            action.clickAlertButton("Not Now");
                                    }
                                }
                            }
                        }
                        else
                        {
                            logger.detail("Customer App is not running");
                        }
                        retry1--;
                    }
                default:
                    error("UnImplemented Step or in correct app", "UnImplemented Step");
                    break;
            }        //temp fixed
           // logger.detail("Expected App Header After Switching : "+ appHeader);
            Thread.sleep(5000);

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

    public Point getCordinatesForNotification(String pageSource, String expectedText) {
        String REGEX = "name=\"BUNGII DRIVER,.*y=\".{1,}\"";
        String INPUT = pageSource;
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);   // get a matcher object
        int count = 0;
        Point notificationPoint = null;
        while (m.find()) {
            count++;
            System.out.println("Match number " + count);
            System.out.println("start(): " + m.start());
            System.out.println("end(): " + m.end());
            String subString = INPUT.substring(m.start(), m.end());
            if (subString.contains(expectedText)) {
                int idx = subString.indexOf("x=\"") + 3;
                String x = subString.substring(idx, subString.indexOf("\"", idx));
                int idy = subString.indexOf("y=\"") + 3;
                String y = subString.substring(idy, subString.indexOf("\"", idy));

                int idwidth = subString.indexOf("width=\"") + 7;
                String width = subString.substring(idwidth, subString.indexOf("\"", idwidth));
                int idheight = subString.indexOf("height=\"") + 8;
                String height = subString.substring(idheight, subString.indexOf("\"", idheight));
                notificationPoint = new Point(Integer.parseInt(x) + (Integer.parseInt(width) / 2), Integer.parseInt(y) + (Integer.parseInt(height) / 2));
                break;
                //       System.out.println(x+y+idwidth+idheight);
            }

        }
        return notificationPoint;
    }

    public boolean checkRGBValue(int[] actualColor, int[] expectedRGB) {

        Color actual = new Color(actualColor[0], actualColor[1], actualColor[2]);
        Color expected = new Color(expectedRGB[0], expectedRGB[1], expectedRGB[2]);
        double diff = ColourDistance(actual, expected);
       // logger.detail("Difference between actual and expected is :" + diff);

        boolean isEqual = diff < 15;
/*      for(int i =0;i<actualColor.length;i++){
          if(Math.abs(actualColor[i]-expectedRGB[i])>7){
              //if flag is not set as false then set
              if(isEqual)
                  isEqual=false;
          }
      }*/
        return isEqual;
    }

    public String getActualTime() {

        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        String custRef = com.bungii.android.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String pickUpId = com.bungii.android.utilityfunctions.DbUtility.getPickupID(custRef);
        return DbUtility.getActualTime(pickUpId);
    }

    public String getEstimateDistance() {
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
        //   phoneNumber="9403960188";
        String custRef = com.bungii.ios.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        return com.bungii.ios.utilityfunctions.DbUtility.getEstimateDistance(custRef);
    }

    /**
     * Calculate  cost of trip check if less than minimum cost then
     * return minimum cost
     *
     * @param tripDistance
     * @param Promo
     * @return
     */
    public double bungiiCustomerCost(String tripDistance, String tripTime, String Promo, String tripType) {
        logger.detail("Trip Distance : " + tripDistance + "| Trip Time : " + tripTime + "| Promo : " + Promo + "| Trip Type : " + tripType);
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get minimum cost,Mile value,Minutes value of Geofence
        double minCost = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.minimum.cost")),
                perMileValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.miles")),
                perMinutesValue = Double.parseDouble(getGeofenceData(currentGeofence, "geofence.dollar.per.minutes"));

        double distance = Double.parseDouble(tripDistance.replace(" miles", ""));
        double tripActualTime = Double.parseDouble(tripTime);
        double tripValue = distance * perMileValue + tripActualTime * perMinutesValue;
        if (tripType.equalsIgnoreCase("DUO")) {
            tripValue = tripValue * 2;
            minCost = minCost * 2;
        }
        Promo = Promo.contains("ADD") ? "0" : Promo;

        double discount = 0;
        if (Promo.contains("$"))
            discount = Double.parseDouble(Promo.replace("-$", ""));
        else if (Promo.contains("%")) {
            discount = tripValue * Double.parseDouble(Promo.replace("-", "").replace("%", "")) / 100;
            if (tripType.equalsIgnoreCase("DUO")) {
                //discount is rounded to floor
                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.FLOOR);
                discount = new Double(df.format(discount));
            }
        }
        double costToCustomer = tripValue - discount;
        costToCustomer = costToCustomer > minCost ? costToCustomer : minCost;

        return costToCustomer;
    }


    public double ColourDistance(Color c1, Color c2) {
        double rmean = (c1.getRed() + c2.getRed()) / 2;
        int r = c1.getRed() - c2.getRed();
        int g = c1.getGreen() - c2.getGreen();
        int b = c1.getBlue() - c2.getBlue();
        double weightR = 2 + rmean / 256;
        double weightG = 4.0;
        double weightB = 2 + (255 - rmean) / 256;
        return Math.sqrt(weightR * r * r + weightG * g * g + weightB * b * b);
    }

    public void addStatusBarLocationToFeatureContext(String appKey, String statusKey, Point point, Dimension dimension) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey + "_" + statusKey;
        cucumberContextManager.setFeatureContextContext(key + "_POINT", point);
        cucumberContextManager.setFeatureContextContext(key + "_DIM", dimension);
    }

    public void addSliderValueToFeatureContext(String appKey, Rectangle rectangle) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey;
        cucumberContextManager.setFeatureContextContext(key + "_RECT", rectangle);
    }

    public boolean isSliderValueContainsInContext(String appKey) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey;
        return cucumberContextManager.isFeatureContextContains(key + "_RECT");
    }

    public Rectangle getSliderValueFromContext(String appKey) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey;
        return (Rectangle) cucumberContextManager.getFeatureContextContext(key + "_RECT");
    }

    public boolean isContextContainsStatusKey(String appKey, String statusKey) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey + "_" + statusKey;
        return cucumberContextManager.isFeatureContextContains(key + "_POINT");
    }

    public Point getInitialLocationFromContext(String appKey, String statusKey) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey + "_" + statusKey;
        return (Point) cucumberContextManager.getFeatureContextContext(key + "_POINT");
    }

    public Dimension getDimenstionFromContext(String appKey, String statusKey) {
        String instanceName = SetupManager.getObject().getCurrentInstanceKey().toUpperCase();
        String key = instanceName + "_" + appKey + "_" + statusKey;
        return (Dimension) cucumberContextManager.getFeatureContextContext(key + "_DIM");
    }

    public boolean[] checkStatusOnDriver() {
        boolean[] array = new boolean[5];
        List<Map> data = new ArrayList<>();
        Map<String, Object> info = new HashedMap();

        try {
            File srcFile = ((TakesScreenshot) SetupManager.getObject().getDriver()).getScreenshotAs(OutputType.FILE);
            Point initialLocation;
            Dimension elementSize;

            if (!isContextContainsStatusKey("DRIVER", "1")) {
                WebElement pickUpStatus1 = driverUpdateStatusPage.Image_Trip_State_1();
                initialLocation = pickUpStatus1.getLocation();
                elementSize = pickUpStatus1.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "1", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "1");
                elementSize = getDimenstionFromContext("DRIVER", "1");
            }

            System.out.println("status of element 1");
            int[] statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[0] = checkRGBValue(statusPixelValue, rgb[0]);


            if (!isContextContainsStatusKey("DRIVER", "2")) {
                WebElement pickUpStatus2 = driverUpdateStatusPage.Image_Trip_State_2();
                initialLocation = pickUpStatus2.getLocation();
                elementSize = pickUpStatus2.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "2", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "2");
                elementSize = getDimenstionFromContext("DRIVER", "2");
            }

            System.out.println("status of element 2");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[1] = checkRGBValue(statusPixelValue, rgb[1]);


            if (!isContextContainsStatusKey("DRIVER", "3")) {
                WebElement pickUpStatus3 = driverUpdateStatusPage.Image_Trip_State_3();
                initialLocation = pickUpStatus3.getLocation();
                elementSize = pickUpStatus3.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "3", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "3");
                elementSize = getDimenstionFromContext("DRIVER", "3");
            }

            System.out.println("status of element 3");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[2] = checkRGBValue(statusPixelValue, rgb[2]);


            if (!isContextContainsStatusKey("DRIVER", "4")) {
                WebElement pickUpStatus4 = driverUpdateStatusPage.Image_Trip_State_4();
                initialLocation = pickUpStatus4.getLocation();
                elementSize = pickUpStatus4.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "4", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "4");
                elementSize = getDimenstionFromContext("DRIVER", "4");
            }
            System.out.println("status of element 4");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 6), initialLocation.getY() + (elementSize.height / 2));
            array[3] = checkRGBValue(statusPixelValue, rgb[3]);


            if (!isContextContainsStatusKey("DRIVER", "5")) {
                WebElement pickUpStatus5 = driverUpdateStatusPage.Image_Trip_State_5();
                initialLocation = pickUpStatus5.getLocation();
                elementSize = pickUpStatus5.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "5", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "5");
                elementSize = getDimenstionFromContext("DRIVER", "5");
            }

            System.out.println("status of element 5");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[4] = checkRGBValue(statusPixelValue, rgb[4]);
        } catch (IOException e) {
            logger.error("Error while capturing/coping screenshot" + e.getMessage());
        }
        return array;

    }

    public boolean[] checkStatusOnCustomer() {
        boolean[] array = new boolean[5];
        try {
            File srcFile = ((TakesScreenshot) SetupManager.getObject().getDriver()).getScreenshotAs(OutputType.FILE);
            Point initialLocation;
            Dimension elementSize;
            if (!isContextContainsStatusKey("DRIVER", "1")) {
                WebElement pickUpStatus1 = customerUpdateStatusPage.Image_Trip_State_1();
                initialLocation = pickUpStatus1.getLocation();
                elementSize = pickUpStatus1.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "1", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "1");
                elementSize = getDimenstionFromContext("DRIVER", "1");
            }
            System.out.println("status of element 1");
            int[] statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[0] = checkRGBValue(statusPixelValue, rgb[0]);


            if (!isContextContainsStatusKey("DRIVER", "2")) {
                WebElement pickUpStatus2 = customerUpdateStatusPage.Image_Trip_State_2();
                initialLocation = pickUpStatus2.getLocation();
                elementSize = pickUpStatus2.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "2", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "2");
                elementSize = getDimenstionFromContext("DRIVER", "2");
            }

            System.out.println("status of element 2");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[1] = checkRGBValue(statusPixelValue, rgb[1]);


            if (!isContextContainsStatusKey("DRIVER", "3")) {
                WebElement pickUpStatus3 = customerUpdateStatusPage.Image_Trip_State_3();
                initialLocation = pickUpStatus3.getLocation();
                elementSize = pickUpStatus3.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "3", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "3");
                elementSize = getDimenstionFromContext("DRIVER", "3");
            }
            System.out.println("status of element 3");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[2] = checkRGBValue(statusPixelValue, rgb[2]);


            if (!isContextContainsStatusKey("DRIVER", "4")) {
                WebElement pickUpStatus4 = customerUpdateStatusPage.Image_Trip_State_4();
                initialLocation = pickUpStatus4.getLocation();
                elementSize = pickUpStatus4.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "4", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "4");
                elementSize = getDimenstionFromContext("DRIVER", "4");
            }
            System.out.println("status of element 4");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 6), initialLocation.getY() + (elementSize.height / 2));
            array[3] = checkRGBValue(statusPixelValue, rgb[3]);


            if (!isContextContainsStatusKey("DRIVER", "5")) {
                WebElement pickUpStatus5 = customerUpdateStatusPage.Image_Trip_State_5();

                initialLocation = pickUpStatus5.getLocation();
                elementSize = pickUpStatus5.getSize();
                addStatusBarLocationToFeatureContext("DRIVER", "5", initialLocation, elementSize);

            } else {
                initialLocation = getInitialLocationFromContext("DRIVER", "5");
                elementSize = getDimenstionFromContext("DRIVER", "5");
            }
            System.out.println("status of element 5");
            statusPixelValue = getPixelColor(srcFile, initialLocation.getX() + (elementSize.getWidth() / 4), initialLocation.getY() + (elementSize.height / 2));
            array[4] = checkRGBValue(statusPixelValue, rgb[4]);
        } catch (IOException e) {
            logger.error("Error while capturing/coping screenshot" + e.getMessage());
        }
        return array;

    }

    public String[] getTeletTimeinLocalTimeZone() {
        String[] calculatedTime = new String[3];
        try {
            String geofenceLabel = getTimeZoneBasedOnGeofenceId();
            String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"); //phoneNumber="9403960189"; c/// Stacked trip will be 2 customer you need of first trip
            String custRef = DbUtility.getCustomerRefference(phoneNumber);
            String teletTime = DbUtility.getTELETfromDb(custRef);
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            //By default data is in UTC
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date teletTimeInUtc = formatter.parse(teletTime);
            DateFormat formatterForLocalTimezone = new SimpleDateFormat("hh:mm a");
            formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
            String teletInLocalTime = formatterForLocalTimezone.format(teletTimeInUtc);
            long t = teletTimeInUtc.getTime();
            long ONE_MINUTE_IN_MILLIS = Long.parseLong(PropertyUtility.getDataProperties("one.minute.in.milliseconds"));
            Date minTime = new Date(t - (1 * ONE_MINUTE_IN_MILLIS));
            String strMindate = formatterForLocalTimezone.format(minTime);


            Date maxTime = new Date(t + (45 * ONE_MINUTE_IN_MILLIS));
            String strMaxdate = formatterForLocalTimezone.format(maxTime);
            calculatedTime[0] = teletInLocalTime;
            calculatedTime[1] = teletInLocalTime;
            calculatedTime[2] = strMaxdate;
            logger.detail("[As Per calculation Of Long Stack for Trip of Customer "+phoneNumber+"] Driver to Finish By :"+ teletInLocalTime + "Range ["+strMindate+" : "+strMaxdate+"]");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calculatedTime;
    }

    public String calculateTeletTime() throws ParseException {

        String scheduledTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");

        // scheduledTime = "Dec 21, 11:15 AM GMT+5:30";

        Date bungiiDate = new SimpleDateFormat("MMM d, h:mm a").parse(scheduledTime);
        Date currentDate = new Date();


        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"); //phoneNumber="9403960189";
        String loadtime = (String) cucumberContextManager.getScenarioContext("BUNGII_LOADTIME");//, "15 mins");
        loadtime = loadtime.toLowerCase().replace("mins", "").replace("min", "").trim();
        String custRef = com.bungii.android.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String estimateTime = com.bungii.android.utilityfunctions.DbUtility.getEstimateTime(custRef);
        long totalEstimateDuration = Integer.parseInt(loadtime) + Integer.parseInt(estimateTime);
        double timeToBeAdded = (totalEstimateDuration * 1.5) +45;//+ 30;
        Date telet = DateUtils.addMinutes(bungiiDate, (int) timeToBeAdded);

        //int year=currentDate.getYear()+1900;
        telet.setYear(currentDate.getYear());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //By default data is in UTC
        //   dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String teletTimeInUtc = null;

        teletTimeInUtc = dateFormat.format(telet);
        return teletTimeInUtc;


    }
    public String calculateTeletTimeValue() throws ParseException {

        String scheduledTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
        String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"); //phoneNumber="9403960189";
        String loadtime = (String) cucumberContextManager.getScenarioContext("BUNGII_LOADTIME");//, "15 mins")

        Date bungiiDate = new SimpleDateFormat("MMM d, h:mm a").parse(scheduledTime);
        Date currentDate = new Date();
        loadtime = loadtime.toLowerCase().replace("mins", "").replace("min", "").trim();
        String custRef = com.bungii.android.utilityfunctions.DbUtility.getCustomerRefference(phoneNumber);
        String estimateTime = com.bungii.android.utilityfunctions.DbUtility.getEstimateTime(custRef);
        long totalEstimateDuration = Integer.parseInt(loadtime) + Integer.parseInt(estimateTime);
        double timeToBeAdded = (totalEstimateDuration * 1.5) +30;//+ 30; TELET CALCULATION
        Date telet = DateUtils.addMinutes(bungiiDate, (int) timeToBeAdded);
        telet.setYear(currentDate.getYear());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String teletTimeInUtc = null;
        teletTimeInUtc = dateFormat.format(telet);
        return teletTimeInUtc;


    }

    public void calculateShortStack() throws ParseException {
//        cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "kansas");
try {

    int FROM_RANGE_FROM = -10;
    int FROM_RANGE_TO = +20;
    long ONE_MINUTE_IN_MILLIS = Long.parseLong(PropertyUtility.getDataProperties("one.minute.in.milliseconds"));

    String geofenceLabel = getTimeZoneBasedOnGeofenceId();
    String customerPhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");//customerPhoneNumber="9999991889";
    String customer2PhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");//customer2PhoneNumber="9999991259";
    String driverPhoneNumber = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");//driverPhoneNumber="9955112208";

    //String[] loadingTimeStamp = com.bungii.android.utilityfunctions.DbUtility.getLoadingTimeStamp(customerPhoneNumber);
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //By default data is in UTC
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

    //String[] driverLocation = com.bungii.android.utilityfunctions.DbUtility.getDriverLocation(driverPhoneNumber);
    String[] pickup1Locations = com.bungii.android.utilityfunctions.DbUtility.getPickupAndDropLocation(customerPhoneNumber);
    String[] pickup2Locations = com.bungii.android.utilityfunctions.DbUtility.getPickupAndDropLocation(customer2PhoneNumber);

    //String Pickup_request_ongoing= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
    String custRef = DbUtility.getCustomerRefference(customerPhoneNumber);
    String Telet = DbUtility.getTELETfromDb(custRef);
    Date TeletDateTime = formatter.parse(Telet);
    long TeletTimeInMs = TeletDateTime.getTime();
    //long TeletTimeInMs = TimeUnit.MILLISECONDS.toMinutes(TeletTime);//Db telet time in MS

    String[] dropLocation = new String[2];
    dropLocation[0] = pickup1Locations[2];
    dropLocation[1] = pickup1Locations[3];
    String[] newPickupLocations = new String[2];
    newPickupLocations[0] = pickup2Locations[0];
    newPickupLocations[1] = pickup2Locations[1];

    long[] timeToCoverDistance = new GoogleMaps().getDurationInTraffic(dropLocation, newPickupLocations);
    logger.detail("timeToCoverDistance [google api call] "+timeToCoverDistance[0]+" and "+timeToCoverDistance[1]);

    DateFormat formatterForLocalTimezone = new SimpleDateFormat("hh:mm a");
    formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
    //Double TeletDistanceTime = TeletTimeInMs + (double) timeToCoverDistance[0] / 60;
    long TeletDistanceTime = TeletTimeInMs + timeToCoverDistance[0] * 1000;
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(TeletDistanceTime);
    Date dt = calendar.getTime();

    Date minTime = new Date(dt.getTime() + (FROM_RANGE_FROM * ONE_MINUTE_IN_MILLIS));
    String strMindate = formatterForLocalTimezone.format(minTime);

    //Date maxTime = new Date(timeStampToCalculateDate.getTime() + (FROM_RANGE_TO * ONE_MINUTE_IN_MILLIS));
    Date maxTime = new Date(dt.getTime() + (FROM_RANGE_TO * ONE_MINUTE_IN_MILLIS));
    String strMaxdate = formatterForLocalTimezone.format(maxTime);

    String driverTime = formatterForLocalTimezone.format(TeletDateTime);
    cucumberContextManager.setScenarioContext("DRIVER_FINISH_BY", driverTime);
    cucumberContextManager.setScenarioContext("DRIVER_MIN_ARRIVAL", strMindate);
    cucumberContextManager.setScenarioContext("DRIVER_MAX_ARRIVAL", strMaxdate);
    logger.detail("[As Per calculation Of Short Stack for Trip of Customer "+customer2PhoneNumber+"] Driver to Finish By :"+ driverTime + "Range "+FROM_RANGE_FROM+","+FROM_RANGE_TO+"["+strMindate+" : "+strMaxdate+"]");

}
catch (Exception e)
{
    e.printStackTrace();
}

    }

    public String GetSpedificMultipartTextEmailIfReceived(String expectedFromAddress, String expectedToAddress, String expectedSubject) {
        String strEmailContent = "";

        try {
            Message[] recentMessages = emailUtility.getEmailObject(expectedFromAddress, expectedToAddress, expectedSubject, 1);
            System.out.println("No of Total recent Messages : " + recentMessages.length);
            String fromAddress = expectedFromAddress;
            for (int i = recentMessages.length; i > 0; i--) {

                System.out.println("*****************************************************************************");
                System.out.println("MESSAGE " + (i) + ":");
                Message msg = recentMessages[i - 1];
                System.out.println(msg.getMessageNumber());
                String subject = msg.getSubject();//important value

                System.out.println("Subject: " + subject);
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("To: " + msg.getAllRecipients()[0]);//important value
                System.out.println("Date: " + msg.getReceivedDate());
                //  System.out.println("Message with Multipart: " + getText(msg));

                //  readLineByLineJava8("D:\\Bungii-QA-Automation\\Bungii.Test.Integration\\src\\main\\resources\\EmailTemplate\\BungiiReceipt.txt", getText(msg));
                //System.out.println("Size: "+msg.getSize());
                //System.out.println(msg.getFlags());
                if ((msg.getFrom()[0].toString().contains(fromAddress)) && (subject.equals(expectedSubject)) && (msg.getAllRecipients()[0].toString().contains(expectedToAddress))) {
                    String EmailContent = msg.getContent().toString();
                    // System.out.println("Email Found!!!\nEmail Content: \n" + EmailContent);//need to get extract link value from here
                    //Invoke jSoupHTMLToString object
                    Document emailContent = Jsoup.parse(EmailContent);
                    strEmailContent = emailUtility.readPlainContent((javax.mail.internet.MimeMessage) msg);
                    System.out.println("Plain text: " + emailUtility.getTextFromMessage(msg));

                    break;
                }


            }
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strEmailContent;

    }

    public String getCustomerSignupTemplate(String customerName) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "/EmailTemplate/CustomerSignup.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%CustomerName%", customerName)
                    ;
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }

    public String GetSpecificPlainTextEmailIfReceived(String expectedFromAddress, String expectedToAddress, String expectedSubject) {

        try {
            Message[] recentMessages = emailUtility.getEmailObject(expectedFromAddress, expectedToAddress, expectedSubject, 1);
            System.out.println("No of Total recent Messages : " + recentMessages.length);
            String fromAddress = expectedFromAddress;
            boolean emailFound = false;
            String emailContent = "";
            for (int i = recentMessages.length; i > 0; i--) {

                System.out.println("MESSAGE " + (i) + ":");
                Message msg = recentMessages[i - 1];
                System.out.println(msg.getMessageNumber());
                String subject = msg.getSubject();//important value

                System.out.println("Subject: " + subject);
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("To: " + msg.getAllRecipients()[0]);//important value
                System.out.println("Date: " + msg.getReceivedDate());
                System.out.println("Plain text: " + emailUtility.getTextFromMessage(msg));
                if ((msg.getFrom()[0].toString().contains(fromAddress)) && (subject.contains(expectedSubject)) && (msg.getAllRecipients()[0].toString().contains(expectedToAddress))) {
                    // String EmailContent = msg.getContent().toString();
                    emailContent = emailUtility.readPlainContent((javax.mail.internet.MimeMessage) msg);
                    emailUtility.deleteEmailWithSubject(expectedSubject, null);
                    return emailContent;
                }
            }
            return null;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public boolean validateCustomerSignupEmail(String filePath, String emailValue, String customerName, String url1, String url2, String url3, String url4, String url5, String url6, String url7, String url8, String url9) throws IOException {
        boolean isEmailCorrect = false;
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\r\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileValue = contentBuilder.toString();
        Path p1 = Paths.get(filePath);

        List<String> listF1 = Files.readAllLines(p1);
        List<String> listF2 = Arrays.asList(emailValue.split("\r\n"));

        for (int i = 0; i < listF1.size(); i++) {
            if (listF1.get(i).contains("%CustomerName%")) {
                listF1.set(i, listF1.get(i).replace("%CustomerName%", customerName));
            }
            if (listF1.get(i).contains("%URL_1%")) {
                listF1.set(i, listF1.get(i).replace("%URL_1%", url1));
            }
            if (listF1.get(i).contains("%URL_2%")) {
                listF1.set(i, listF1.get(i).replace("%URL_2%", url2));
            }
            if (listF1.get(i).contains("%URL_3%")) {
                listF1.set(i, listF1.get(i).replace("%URL_3%", url3));
            }
            if (listF1.get(i).contains("%URL_4%")) {
                listF1.set(i, listF1.get(i).replace("%URL_4%", url4));
            }
            if (listF1.get(i).contains("%URL_5%")) {
                listF1.set(i, listF1.get(i).replace("%URL_5%", url5));
            }
            if (listF1.get(i).contains("%URL_6%")) {
                listF1.set(i, listF1.get(i).replace("%URL_6%", url6));
            }
            if (listF1.get(i).contains("%URL_7%")) {
                listF1.set(i, listF1.get(i).replace("%URL_7%", url7));
            }
            if (listF1.get(i).contains("%URL_8%")) {
                listF1.set(i, listF1.get(i).replace("%URL_8%", url8));
            }
            if (listF1.get(i).contains("%URL_9%")) {
                listF1.set(i, listF1.get(i).replace("%URL_9%", url9));
            }
        }
        if (listF1.size() == listF2.size()) {
            if ((listF1.equals(listF2))) {
                System.out.println("Both list are matching");
                isEmailCorrect = true;
            } else {
                isEmailCorrect = false;

                //both list are not matching ,iterate over all line to check value
                for (int i = 0; i < listF1.size(); i++) {

                    if (listF1.get(i).equals(listF2.get(i))) {

                    } else {
                        logger.detail("EMAIL MISMACTH |||" + i + "|||" + listF1.get(i) + "|||" + listF2.get(i));
                        //  System.out.println(listF1.get(i));
                        //  System.out.println(listF2.get(i));

                    }
                }
            }
        }

        return isEmailCorrect;
    }


    public String getExpectedPoorRatingMail(String driverName, String customerName, String ratingValue, String tripDetails) {
        String emailMessage = "";

        try {
            FileReader fr = new FileReader(new File(DriverBase.class.getProtectionDomain().getCodeSource().getLocation().getPath()) + "/EmailTemplate/PoorRatingEmail.txt");
            String s;
            try (

                    BufferedReader br = new BufferedReader(fr)) {

                while ((s = br.readLine()) != null) {
                    s = s.replaceAll("%DriverName%", driverName)
                            .replaceAll("%CustomerName%", customerName)
                            .replaceAll("%RatingValue%", ratingValue)
                            .replaceAll("%TripDetailsUrl%", tripDetails);
                    emailMessage += s;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailMessage;
    }
    public void logCustomerDeviceToken(String phoneNumber){
        try {
            if(!phoneNumber.trim().equalsIgnoreCase(""))
                logger.detail("Device token of customer ["+phoneNumber+"] is "+com.bungii.ios.utilityfunctions.DbUtility.getCustomerDeviceToken(phoneNumber));
        }catch (Exception e){
            logger.detail("Error getting deviceToken - ", ExceptionUtils.getStackTrace(e));
        }
    }
    public void logDriverDeviceToken(String phoneNumber){
        try {
            if(!phoneNumber.trim().equalsIgnoreCase(""))
                logger.detail("Device token of Driver ["+phoneNumber+"] is "+com.bungii.ios.utilityfunctions.DbUtility.getDriverDeviceToken(phoneNumber));
        }catch (Exception e){
            logger.detail("Error getting deviceToken - ", ExceptionUtils.getStackTrace(e));
        }
    }
    public void logCustomerRecentTrip(String phoneNumber){
        try {
            if(!phoneNumber.trim().equalsIgnoreCase("")) {
                String pickupref = com.bungii.ios.utilityfunctions.DbUtility.getCustomersMostRecentBungii(phoneNumber);
                logger.detail("Most recent trip of customer [" + phoneNumber + "] is with pickup ref " + pickupref);
                cucumberContextManager.setScenarioContext("REQUESTED_PICKUPREF", pickupref);
                cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupref);
            }

        }catch (Exception e){
            logger.detail("Error getting deviceToken - ", ExceptionUtils.getStackTrace(e));
        }
    }
    public String getGmtTime(String time) {
        String gmtTime = "";
        if (TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date())) {
            if (time.contains("MDT") || time.contains("MST")) {
                String Geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
                if (Geofence.equalsIgnoreCase("Denver")) {
                    gmtTime = time.replace("MDT", "GMT-6").replace("MST", "GMT-6");
                } else {
                    gmtTime = time.replace("MDT", "GMT-6").replace("MST", "GMT-6");
                }
            }
            if (time.contains("CST") || time.contains("CDT")) {
                String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
                if (currentGeofence.equalsIgnoreCase("Chicago") || currentGeofence.equalsIgnoreCase("Nashville"))

                    gmtTime = time.replace("CST", "GMT-5").replace("CDT", "GMT-5");
                else
                    gmtTime = time.replace("CST", "GMT-5").replace("CDT", "GMT-5");
            }
            if (time.contains("EST") || time.contains("EDT"))
                gmtTime = time.replace("EST", "GMT-4").replace("EDT", "GMT-4");
            if (time.contains("PST") || time.contains("PDT"))
                gmtTime = time.replace("PST", "GMT-7").replace("PDT", "GMT-7");
            if (time.contains("IST"))
                gmtTime = time.replace("IST", "GMT+5:30");
        } else {
            if (time.contains("MDT") || time.contains("MST")) {
                String Geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
                if (Geofence.equalsIgnoreCase("Denver")) {
                    gmtTime = time.replace("MDT", "GMT-7").replace("MST", "GMT-7");
                } else {
                    gmtTime = time.replace("MDT", "GMT-6").replace("MST", "GMT-6");
                }
            }
            if (time.contains("CST") || time.contains("CDT")) {
                String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
                if (currentGeofence.equalsIgnoreCase("Chicago") || currentGeofence.equalsIgnoreCase("Nashville"))

                    gmtTime = time.replace("CST", "GMT-6").replace("CDT", "GMT-6");
                else
                    gmtTime = time.replace("CST", "GMT-5").replace("CDT", "GMT-5");
            }
            if (time.contains("EST") || time.contains("EDT"))
                gmtTime = time.replace("EST", "GMT-4").replace("EDT", "GMT-4");
            if (time.contains("PST") || time.contains("PDT"))
                gmtTime = time.replace("PST", "GMT-7").replace("PDT", "GMT-7");
            if (time.contains("IST"))
                gmtTime = time.replace("IST", "GMT+5:30");


        }
        return gmtTime;
    }
    public void selectGeofenceDropdown(String geofence){
        action.click(admin_dashboardPage.List_Geofence());
        action.clearSendKeys(admin_dashboardPage.TextBox_SearchGeofence(),geofence);
        action.JavaScriptClick(admin_dashboardPage.Checkbox_Geofence(geofence));
        action.click(admin_dashboardPage.Button_ApplyGeofence());
    }

    public void reApplyGeofenceDropdown(){
        action.click(admin_dashboardPage.List_Geofence());
        action.click(admin_dashboardPage.Button_ApplyGeofence());
    }
    public String NavigateToPartnerLogin(String Site){

        String partnerURL = GetPartnerUrl(Site);
        action.deleteAllCookies();
        action.navigateTo(partnerURL);
        return partnerURL;
    }

    private String GetPartnerUrl(String PP_Site) {
        String partnerURL = null;
        cucumberContextManager.setScenarioContext("SiteUrl", PP_Site);
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("QA_AUTO") || environment.equalsIgnoreCase("QA_AUTO_AWS")) {
            if (PP_Site.equalsIgnoreCase("normal")) {
                partnerURL = PropertyUtility.getDataProperties("qa.partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("service level")) {
                partnerURL = PropertyUtility.getDataProperties("qa.service_level_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.service_level_partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("FloorDecor service level")) {
                partnerURL = PropertyUtility.getDataProperties("qa.fnd_service_level_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.fnd_service_level_partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("kiosk mode")) {
                partnerURL = PropertyUtility.getDataProperties("qa.kiosk_mode_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.kiosk_mode_partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("BestBuy service level")) {
                partnerURL = PropertyUtility.getDataProperties("qa.bestbuy.service_level_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.bestbuy.service_level_partner.ref"));

            } else if (PP_Site.equalsIgnoreCase("Cort service level")) {
                partnerURL = PropertyUtility.getDataProperties("qa.cort_service_level_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.cort_service_level_partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("BestBuy2 service level")) {
                partnerURL = PropertyUtility.getDataProperties("qa.bestbuy2.service_level_partner.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.bestbuy2.service_level_partner.ref"));
            } else if (PP_Site.equalsIgnoreCase("Equip-bid")) {
            partnerURL = PropertyUtility.getDataProperties("qa.equip-bid.url");
            cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.equip-bid.ref"));
        }else if (PP_Site.equalsIgnoreCase("Tile Shop")) {
                partnerURL = PropertyUtility.getDataProperties("qa.tileshop.url");
                cucumberContextManager.setScenarioContext("PARTNERREF", PropertyUtility.getDataProperties("qa.tileshop.ref"));
            }

        }
        return partnerURL;
    }
    public String getTimeZoneBasedOnGeofenceIdForIos() {
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        // currentGeofence="kansas";
        //get timezone value of Geofence
        String getGeofenceTimeZone = getGeofenceData(currentGeofence, "geofence.timezone.id");
        return getGeofenceTimeZone;
    }
    public String[] getMilesRange(float miles){
        String range[] = new String[2];
        String portalName= (String) cucumberContextManager.getScenarioContext("Portal_Name");
        if (portalName.equalsIgnoreCase("Floor and Decor")){
            if (miles>0 && miles<=10)
            {
                range[0]="0";
                range[1]="10";
            }
            else if(miles>10 && miles<=20)
            {
                range[0]="10";
                range[1]="20";
            }
            else if(miles>20 && miles<=30)
            {
                range[0]="20";
                range[1]="30";
            }
            else if(miles>30 && miles<=40)
            {
                range[0]="30";
                range[1]="40";
            }
            else if(miles>40 && miles<=50)
            {
                range[0]="40";
                range[1]="50";
            }
            else{
                range[0]="50";
                range[1]="150";
            }
        }else{
             if (miles>0 && miles<=10)
            {
                 range[0]="0";
                 range[1]="10";
            }
            else if(miles>10 && miles<=15)
            {
                range[0]="10";
                range[1]="15";
            }
            else if(miles>15 && miles<=30)
            {
                range[0]="15";
                range[1]="30";
            }
            else if(miles>30 && miles<=100)
            {
                range[0]="30";
                range[1]="100";
            }
            else{
                range[0]="100";
                range[1]="120";
            }
        }
        return range;
    }
}
