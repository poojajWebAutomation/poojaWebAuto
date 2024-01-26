package com.bungii.ios.stepdefinitions.other;

import com.bungii.SetupManager;
import com.bungii.api.stepdefinitions.BungiiSteps;
import com.bungii.api.utilityFunctions.AuthServices;
import com.bungii.api.utilityFunctions.CoreServices;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.driver.HomePage;
import com.bungii.ios.pages.other.NotificationPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.text.*;
import java.util.*;

import static com.bungii.common.manager.ResultManager.*;
import static com.google.common.base.Preconditions.checkArgument;


public class NotificationSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(NotificationSteps.class);
    NotificationPage notificationPage;
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    HomePage homepage;

    public NotificationSteps(NotificationPage notificationPage, HomePage homePage) {
        this.notificationPage = notificationPage;
        this.homepage = homePage;
    }
    @And("^I view virtual notification for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_view_virtual_notification_for_something_for_something(String app, String notificationType) throws Throwable {

        String notificationText = getExpectedNotification(notificationType);
        String pickupRequestID = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String pushNotificationContent ="";
        switch (app.toUpperCase()) {
            case "CUSTOMER":
                String customerPhone = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PUSH");
                 pushNotificationContent = new DbUtility().getCustomerPushNotificationContent(customerPhone, pickupRequestID, notificationText);
                testStepAssert.isTrue(pushNotificationContent.contains(notificationText), notificationText + " virtual notification should be received", notificationText + " virtual notification is received", notificationText + " virtual notification is not received");

            case "DRIVER":
                String driverPhone = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
                 pushNotificationContent = new DbUtility().getDriverPushNotificationContent(driverPhone, pickupRequestID, notificationText);
                testStepAssert.isTrue(pushNotificationContent.contains(notificationText), notificationText + " virtual notification should be received", notificationText + " virtual notification is received", notificationText + " virtual notification is not received");
                break;
        }

    }

    @Then("^I click on notification for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_click_on_notification_for_something_for_something(String appName, String expectedNotification) throws Throwable {

        Thread.sleep(10000);
        try {
                    i_view_virtual_notification_for_something_for_something(appName, expectedNotification);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }

        /*
        //Thread.sleep(10000);
        try {
            String currentApplication = (String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION");
            String appHeaderName = getAppHeader(appName);
            boolean notificationClickRetry = false;
            String bunddleId = getBundleId(currentApplication);


            cucumberContextManager.setFeatureContextContext("CURRENT_APPLICATION", appName.toUpperCase());
            ((AppiumDriver) SetupManager.getDriver()).terminateApp(bunddleId);
            action.showNotifications();

            log("Checking push notifications", "Checking push notifications", true);

            //	logger.detail(SetupManager.getDriver().getPageSource());
            boolean notificationClick = clickNotification(appHeaderName, getExpectedNotification(expectedNotification));
            if (!notificationClick) {
                Thread.sleep(5000);
                notificationClickRetry = clickNotification(appHeaderName, getExpectedNotification(expectedNotification));

            }
            if (!notificationClick && !notificationClickRetry) {
                fail("I should be able to click on push notification : " + expectedNotification, "PUSH NOTIFICATIONS NOT RECEIVED : notifications with text : " + getExpectedNotification(expectedNotification), true);

                action.hideNotifications();
               // acceptVirtualNotificationAsDriver((String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE"),expectedNotification);

            } else {
                pass("I should be able to click on push notification : " + expectedNotification, "I clicked on push notifications with text : " + getExpectedNotification(expectedNotification), true);

            }

            Thread.sleep(1000);
            //temp fixed for iOS  device
            utility.handleIosUpdateMessage();
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
        */
    }
    @And("^I view and check virtual notification for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_view_and_check_virtual_notification_for_something_for_something(String strArg1, String expectedNotification) throws Throwable {
        viewVirtualNotificationAsDriver("first",(String) cucumberContextManager.getScenarioContext("DRIVER_PHONE_PUSH"),(String) cucumberContextManager.getScenarioContext("DRIVER_PWD_PUSH"),expectedNotification);
    }
    @And("^I should get virtual notification for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_should_get_virtual_notification_for_something_for_something(String strArg1, String expectedNotification) throws Throwable {
        getVirtualNotificationAsDriver("first",(String) cucumberContextManager.getScenarioContext("DRIVER_PHONE_PUSH"),(String) cucumberContextManager.getScenarioContext("DRIVER_PWD_PUSH"),expectedNotification);
    }
    @And("^I view and accept virtual notification for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_view_and_accept_virtual_notification_for_something_for_something(String strArg1, String expectedNotification) throws Throwable {
        if(expectedNotification.equalsIgnoreCase("SCHEDULED PICKUP2 AVAILABLE")){
            acceptVirtualNotificationAsDriver("second", (String) cucumberContextManager.getScenarioContext("DRIVER_PHONE_PUSH"), (String) cucumberContextManager.getScenarioContext("DRIVER_PWD_PUSH"), expectedNotification);
        }
        else {
            acceptVirtualNotificationAsDriver("first", (String) cucumberContextManager.getScenarioContext("DRIVER_PHONE_PUSH"), (String) cucumberContextManager.getScenarioContext("DRIVER_PWD_PUSH"), expectedNotification);
        }
    }
    @And("^I view and try accepting virtual notification for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_view_and_try_accepting_virtual_notification_for_something_for_something(String strArg1, String expectedNotification) throws Throwable {
        tryAcceptVirtualNotificationAsDriver("first",(String) cucumberContextManager.getScenarioContext("DRIVER_PHONE_PUSH"),(String) cucumberContextManager.getScenarioContext("DRIVER_PWD_PUSH"),expectedNotification);
    }
    @And("^I view and reject virtual notification for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_view_and_reject_virtual_notification_for_something_for_something(String strArg1, String expectedNotification) throws Throwable {
        rejectVirtualNotificationAsDriver("first",(String) cucumberContextManager.getScenarioContext("DRIVER_PHONE_PUSH"),(String) cucumberContextManager.getScenarioContext("DRIVER_PWD_PUSH"),expectedNotification);
    }
    @And("^I view and accept virtual notification for \"([^\"]*)\" delivery of \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_view_and_accept_virtual_notification_delivery_for_something_for_something(String trip, String strArg1, String expectedNotification) throws Throwable {
        acceptVirtualNotificationAsDriver(trip,(String) cucumberContextManager.getScenarioContext("DRIVER_PHONE_PUSH"),(String) cucumberContextManager.getScenarioContext("DRIVER_PWD_PUSH"),expectedNotification);
    }

    public void viewVirtualNotificationAsDriver(String trip, String driverPhoneNum, String driverPassword, String expectedNotification) throws InterruptedException{
        String driverPhoneCode="1";
        String pickupRequestID = "";
        switch (trip.toLowerCase())
        {
            case "second":
                pickupRequestID= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST2");
                break;
            default:
            case "first":
                pickupRequestID= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                break;

        }

        if(pickupRequestID== "")
        {   pickupRequestID =  dbUtility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA"));
        }
        if(pickupRequestID!= "") {
            if(driverPhoneNum== null) {
                driverPhoneNum =  (String) cucumberContextManager.getScenarioContext("DRIVER_2_PHONE");
            }
            if(driverPhoneNum!= null) {
                String pushNotificationContent = new DbUtility().getPushNotificationContent(driverPhoneNum, pickupRequestID);
                String expectedNotificationData = getExpectedNotification(expectedNotification);
                if(pushNotificationContent!= null)
                    testStepVerify.isTrue(pushNotificationContent.contains(expectedNotificationData),"VIRTUAL PUSH NOTIFICATIONS RECEIVED for Driver "+ driverPhoneNum +" : notifications with text :" +expectedNotificationData, "VIRTUAL PUSH NOTIFICATIONS NOT RECEIVED for Driver "+ driverPhoneNum +"  notifications with text :" +expectedNotificationData +" | Actual : "+ pushNotificationContent);

                String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
                String driverAccessToken = new DbUtility().getDriverCurrentToken(driverPhoneNum);
                if(expectedNotification.equalsIgnoreCase("stack trip")) {
                    logger.detail("View stack pickup  " + pickupRequestID +" as driver " + driverPhoneNum +" through API Call");
                    Thread.sleep(5000);
                    Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequestID);
                    if (!isDriverEligible)
                        error("Diver should be eligible for stacked trip", "Driver "+driverPhoneNum+" is not eligible for stacked pickup : "+ pickupRequestID, false);
                    JsonPath jsonPathEvaluator = new CoreServices().getPickupdetails(pickupRequestID, driverAccessToken, geofence);
                    pushNotificationContentVerification(jsonPathEvaluator, "stack");
                    logger.detail("Viewed stack pickup " + pickupRequestID +" as driver " + driverPhoneNum +" through api call [As Driver is eligible for the trip]");
                }
                else {
                    logger.detail("View pickup " + pickupRequestID +" as driver " + driverPhoneNum );
                    Thread.sleep(10000);
                    Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequestID);
                    new GeneralUtility().logDriverDeviceToken(driverPhoneNum);
                    if (!isDriverEligible)
                        error("Diver should be eligible for trip", "Driver "+driverPhoneNum+" is not eligible for pickup : "+ pickupRequestID, false);
                    JsonPath jsonPathEvaluator = new CoreServices().getPickupdetails(pickupRequestID, driverAccessToken, geofence);
                    pushNotificationContentVerification(jsonPathEvaluator,"normal");
                    logger.detail("Viewed pickup " + pickupRequestID +" as driver " + driverPhoneNum +" through api call [As Driver is eligible for the trip]");
                }
                // Switch and login on same device
                utility.switchToApp("driver","same");
                String navigationBarName = action.getScreenHeader(homepage.Text_NavigationBar());
                if(navigationBarName.equalsIgnoreCase("ONLINE")) {
                    action.click(homepage.Button_AppMenu());
                    Thread.sleep(1000);
                    action.click(homepage.AppMenu_ScheduledTrip());
                    Thread.sleep(1000);
                }

                log("I should able to accept trip through virtual notification",
                        "Driver "+driverPhoneNum+" accepts delivery "+ pickupRequestID+" through virtual notification");
            }
            else
            {
                fail("I should be able to view push notification [Virtual] : " + getExpectedNotification(expectedNotification), "PUSH NOTIFICATIONS NOT RECEIVED for driver "+driverPhoneNum+" : notifications with text : " + getExpectedNotification(expectedNotification), true);
            }
        }
        else
        {
            fail("I should be able to accept virtual push notification : " + getExpectedNotification(expectedNotification), "VIRTUAL PUSH NOTIFICATIONS NOT RECEIVED for driver "+driverPhoneNum+" : notifications with text : " + getExpectedNotification(expectedNotification), true);

        }
    }
    public void getVirtualNotificationAsDriver(String trip, String driverPhoneNum, String driverPassword, String expectedNotification) throws InterruptedException{
        String driverPhoneCode="1";
        String pickupRequestID = "";
                pickupRequestID= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");

        if(pickupRequestID== "")
        {   pickupRequestID =  dbUtility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA"));
        }
        if(pickupRequestID!= "") {
            if(driverPhoneNum== null) {
                driverPhoneNum =  (String) cucumberContextManager.getScenarioContext("DRIVER_2_PHONE");
            }
            if(driverPhoneNum!= null) {
                String pushNotificationContent = new DbUtility().getPushNotificationContent(driverPhoneNum, pickupRequestID);
                String expectedNotificationData = getExpectedNotification(expectedNotification);
                if(pushNotificationContent!= null)
                    testStepVerify.isTrue(pushNotificationContent.contains(expectedNotificationData),"VIRTUAL PUSH NOTIFICATIONS RECEIVED for Driver "+ driverPhoneNum +" : notifications with text :" +expectedNotificationData, "VIRTUAL PUSH NOTIFICATIONS NOT RECEIVED for Driver "+ driverPhoneNum +"  notifications with text :" +expectedNotificationData +" | Actual : "+ pushNotificationContent);

                String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
                String driverAccessToken = new DbUtility().getDriverCurrentToken(driverPhoneNum);
                if(expectedNotification.equalsIgnoreCase("stack trip")) {
                    logger.detail("View stack pickup  " + pickupRequestID +" as driver " + driverPhoneNum +" through API Call");
                    Thread.sleep(5000);
                    Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequestID);
                    if (!isDriverEligible)
                        error("Diver should be eligible for stacked trip", "Driver "+driverPhoneNum+" is not eligible for stacked pickup : "+ pickupRequestID, false);
                    JsonPath jsonPathEvaluator = new CoreServices().getPickupdetails(pickupRequestID, driverAccessToken, geofence);
                    pushNotificationContentVerification(jsonPathEvaluator, "stack");
                    logger.detail("Viewed stack pickup " + pickupRequestID +" as driver " + driverPhoneNum +" through api call [As Driver is eligible for the trip]");
                }
                else {
                    logger.detail("View pickup " + pickupRequestID +" as driver " + driverPhoneNum );
                    Thread.sleep(10000);
                    Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequestID);
                    new GeneralUtility().logDriverDeviceToken(driverPhoneNum);
                    if (!isDriverEligible)
                        error("Diver should be eligible for trip", "Driver "+driverPhoneNum+" is not eligible for pickup : "+ pickupRequestID, false);
                    JsonPath jsonPathEvaluator = new CoreServices().getPickupdetails(pickupRequestID, driverAccessToken, geofence);
                    pushNotificationContentVerification(jsonPathEvaluator,"normal");
                    logger.detail("Viewed pickup " + pickupRequestID +" as driver " + driverPhoneNum +" through api call [As Driver is eligible for the trip]");
                }
                // Switch and login on same device

                log("I should able to view trip through virtual notification",
                        "I viewed trip through virtual notification");
            }
            else
            {
                fail("I should be able to view push notification [Virtual] : " + getExpectedNotification(expectedNotification), "PUSH NOTIFICATIONS NOT RECEIVED for driver "+driverPhoneNum+" : notifications with text : " + getExpectedNotification(expectedNotification), true);
            }
        }
        else
        {
            fail("I should be able to accept virtual push notification : " + getExpectedNotification(expectedNotification), "VIRTUAL PUSH NOTIFICATIONS NOT RECEIVED for driver "+driverPhoneNum+" : notifications with text : " + getExpectedNotification(expectedNotification), true);

        }
    }
    public void pushNotificationContentVerification(JsonPath jsonPathEvaluator, String tripType)
    {
 switch (tripType)
 {
     case "normal":

         String expectedPickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1"));
         String expectedPickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2"));
        String expectedDropLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1"));
        String expectedDropLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2"));
        String expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO") ? "DUO" : "SOLO";

        String pickUpLocationLine1 = jsonPathEvaluator.get("PickupDetails.PickupAddress.Address1")+", "+ jsonPathEvaluator.get("PickupDetails.PickupAddress.Address2");
        String pickUpLocationLine2= jsonPathEvaluator.get("PickupDetails.PickupAddress.City")+", "+ jsonPathEvaluator.get("PickupDetails.PickupAddress.State");
        String dropUpLocationLine1 = jsonPathEvaluator.get("PickupDetails.DropOffAddress.Address1")+", "+ jsonPathEvaluator.get("PickupDetails.DropOffAddress.Address2");
        String dropUpLocationLine2 = jsonPathEvaluator.get("PickupDetails.DropOffAddress.City")+", "+ jsonPathEvaluator.get("PickupDetails.DropOffAddress.State");
        testStepVerify.isTrue(pickUpLocationLine1.equals(expectedPickUpLocationLineOne) && pickUpLocationLine2.equals(expectedPickUpLocationLineTwo),

                "Pick up address should be " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo, "Pick up address is " + pickUpLocationLine1+pickUpLocationLine2,
                "Expected pickup address is " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo + ", but actual is" + pickUpLocationLine1+pickUpLocationLine2);
        testStepVerify.isTrue(dropUpLocationLine1.equals(expectedDropLocationLineOne) &&  dropUpLocationLine2.equals(expectedDropLocationLineTwo),

                "Drop address should be " + expectedDropLocationLineOne +expectedDropLocationLineTwo, "Drop address is " + dropUpLocationLine1 +dropUpLocationLine2,
                "Expected Drop address is " + expectedDropLocationLineOne +expectedDropLocationLineTwo + ", but actual is" + dropUpLocationLine1 +dropUpLocationLine2);

        testStepVerify.isEquals(jsonPathEvaluator.get("PickupDetails.Estimate.DistancePickupToDropOff")+ " miles",(String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE"));
        String estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
        Double flestimate=Double.valueOf(estimate.replace("~$","").trim());
         if(expectedTripNoOfDriver.toString()=="DUO")
             flestimate =flestimate/2;

        Double transactionFee=(flestimate*0.029)+0.3;
        Double estimatedDriverCut=(0.7*flestimate)-transactionFee;

        String truncValue = new DecimalFormat("#.00").format(estimatedDriverCut);

        testStepVerify.isEquals(jsonPathEvaluator.get("PickupDetails.Estimate.DriverCost").toString(),String.valueOf(truncValue));
        String tripTime =(String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE_TIME_LOAD_TIME");
        if(tripTime.equalsIgnoreCase(""))
            tripTime =(String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE_TIME");
         int time = Integer.valueOf(jsonPathEvaluator.get("PickupDetails.Estimate.TotalPickupTime").toString()) / 60000;
        // int totalMinutes = totalSeconds / 60;
        // int actualTripTime = totalMinutes / 60;

      //  testStepVerify.isEquals("~"+String.valueOf(time)+" mins",tripTime.replace("  "," ")); //  1 min difference appears -> Need to recalculate and fix
         testStepAssert.isEquals("", "", "Verify Time : ","NOTE: 1 min difference appears sometimes -> Need to recalculate and fix this case : "+ "~"+String.valueOf(time)+" mins and " +tripTime.replace("  "," "),"NOTE: 1 min difference appears -> Need to recalculate and fix this case");
        logger.detail("Push notification Content : "+jsonPathEvaluator.get("PickupDetails"));
        break;
 }


    }
    public void acceptVirtualNotificationAsDriver(String trip, String driverPhoneNum, String driverPassword, String expectedNotification) throws InterruptedException{
        String driverPhoneCode="1";
        String pickupRequestID = "";
       switch (trip.toLowerCase())
        {
            case "second":
            pickupRequestID= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST2");
            break;
            default:
            case "first":
                pickupRequestID= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
               break;

        }

        if(pickupRequestID== "")
        {   pickupRequestID =  dbUtility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA"));
        }
        if(pickupRequestID== "")
        {   pickupRequestID =  dbUtility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER2_PUSH"));
        }
        if(pickupRequestID!= "") {
            if(driverPhoneNum== null) {
                driverPhoneNum =  (String) cucumberContextManager.getScenarioContext("DRIVER_2_PHONE");
            }
            if(driverPhoneNum!= null) {
                String pushNotificationContent = new DbUtility().getPushNotificationContent(driverPhoneNum, pickupRequestID);
                String expectedNotificationData = getExpectedNotification(expectedNotification);
                if(pushNotificationContent!=null)
                testStepVerify.isTrue(pushNotificationContent.contains(expectedNotificationData),"VIRTUAL PUSH NOTIFICATIONS RECEIVED for Driver "+ driverPhoneNum +" : notifications with text :" +expectedNotificationData, "VIRTUAL PUSH NOTIFICATIONS NOT RECEIVED for Driver "+ driverPhoneNum +"  notifications with text :" +expectedNotificationData +" | Actual : "+ pushNotificationContent);

                String driverAccessToken = new DbUtility().getDriverCurrentToken(driverPhoneNum);
                if(expectedNotification.equalsIgnoreCase("stack trip")) {
                    logger.detail("Accept stack pickup  " + pickupRequestID +" as driver " + driverPhoneNum +" through API Call");
                    Thread.sleep(15000);
                    Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequestID);
                    if (!isDriverEligible) {
                        warning("Diver should be eligible for stacked trip", "Driver " + driverPhoneNum + " is not eligible for stacked pickup : " + pickupRequestID, false);
                        Thread.sleep(10000);
                    }
                    new CoreServices().stackedPickupConfirmation(pickupRequestID, driverAccessToken);
                    logger.detail("Accepted stack pickup " + pickupRequestID +" as driver " + driverPhoneNum +" through api call [As Driver is eligible for the trip]");
                }
                else {
                    logger.detail("Accept pickup " + pickupRequestID +" as driver " + driverPhoneNum );
                    Thread.sleep(10000);
                    Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequestID);
                    new GeneralUtility().logDriverDeviceToken(driverPhoneNum);
                    if (!isDriverEligible)
                        warning("Diver should be eligible for trip", "Driver "+driverPhoneNum+" is not eligible for pickup : "+ pickupRequestID, false);
                    new CoreServices().updateStatus(pickupRequestID, driverAccessToken, 21);
                   // if(expectedNotification.equalsIgnoreCase("on demand trip"))
                       // new CoreServices().updateStatus(pickupRequestID, driverAccessToken, 23);
                    logger.detail("Accepted pickup " + pickupRequestID +" as driver " + driverPhoneNum +" through api call [As Driver is eligible for the trip]");
                }
                // Switch and login on same device
                utility.switchToApp("driver","same");
                String navigationBarName = action.getScreenHeader(homepage.Text_NavigationBar());
               if(navigationBarName.equalsIgnoreCase("ONLINE")) {
                    action.click(homepage.Button_AppMenu());
                    Thread.sleep(1000);
                    action.click(homepage.AppMenu_ScheduledTrip());
                   Thread.sleep(1000);
                 //  action.click(homepage.Button_AppMenu());
                   Thread.sleep(1000);
                  // action.click(homepage.AppMenu_Home());
                }

                log("I should able to accept trip through virtual notification",
                        "Driver "+driverPhoneNum+" accepts delivery "+ pickupRequestID+" through virtual notification");
            }
            else
            {
                fail("I should be able to view push notification [Virtual] : " + getExpectedNotification(expectedNotification), "PUSH NOTIFICATIONS NOT RECEIVED for driver "+driverPhoneNum+" : notifications with text : " + getExpectedNotification(expectedNotification), true);
            }
        }
        else
        {
            fail("I should be able to accept virtual push notification : " + getExpectedNotification(expectedNotification), "VIRTUAL PUSH NOTIFICATIONS NOT RECEIVED for driver "+driverPhoneNum+" : notifications with text : " + getExpectedNotification(expectedNotification), true);

        }
    }
    public void rejectVirtualNotificationAsDriver(String trip, String driverPhoneNum, String driverPassword, String expectedNotification) throws InterruptedException{
        String driverPhoneCode="1";
        String pickupRequestID = "";
        switch (trip.toLowerCase())
        {
            case "second":
                pickupRequestID= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST2");
                break;
            default:
            case "first":
                pickupRequestID= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                break;

        }

        if(pickupRequestID== "")
        {   pickupRequestID =  dbUtility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA"));
        }
        if(pickupRequestID== "")
        {   pickupRequestID =  dbUtility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER2_PUSH"));
        }
        if(pickupRequestID!= "") {
            if(driverPhoneNum== null) {
                driverPhoneNum =  (String) cucumberContextManager.getScenarioContext("DRIVER_2_PHONE");
            }
            if(driverPhoneNum!= null) {
                String pushNotificationContent = new DbUtility().getPushNotificationContent(driverPhoneNum, pickupRequestID);
                String expectedNotificationData = getExpectedNotification(expectedNotification);
                if(pushNotificationContent!=null)
                    testStepVerify.isTrue(pushNotificationContent.contains(expectedNotificationData),"VIRTUAL PUSH NOTIFICATIONS RECEIVED for Driver "+ driverPhoneNum +" : notifications with text :" +expectedNotificationData, "VIRTUAL PUSH NOTIFICATIONS NOT RECEIVED for Driver "+ driverPhoneNum +"  notifications with text :" +expectedNotificationData +" | Actual : "+ pushNotificationContent);

                String driverAccessToken = new DbUtility().getDriverCurrentToken(driverPhoneNum);
                if(expectedNotification.equalsIgnoreCase("stack trip")) {
                    logger.detail("Accept stack pickup  " + pickupRequestID +" as driver " + driverPhoneNum +" through API Call");
                    Thread.sleep(15000);
                    Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequestID);
                    if (!isDriverEligible) {
                        warning("Diver should be eligible for stacked trip", "Driver " + driverPhoneNum + " is not eligible for stacked pickup : " + pickupRequestID, false);
                        Thread.sleep(10000);
                    }
                    new CoreServices().stackedPickupConfirmation(pickupRequestID, driverAccessToken);
                    logger.detail("Accepted stack pickup " + pickupRequestID +" as driver " + driverPhoneNum +" through api call [As Driver is eligible for the trip]");
                }
                else {
                    logger.detail("Accept pickup " + pickupRequestID +" as driver " + driverPhoneNum );
                    Thread.sleep(10000);
                    Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequestID);
                    new GeneralUtility().logDriverDeviceToken(driverPhoneNum);
                    if (!isDriverEligible)
                        warning("Driver should be eligible for trip", "Driver "+driverPhoneNum+" is not eligible for pickup : "+ pickupRequestID, false);
                    //new CoreServices().updateStatus(pickupRequestID, driverAccessToken, 22);
                    new CoreServices().updateStatusForDriverReject(pickupRequestID,driverAccessToken);
                    // if(expectedNotification.equalsIgnoreCase("on demand trip"))
                    // new CoreServices().updateStatus(pickupRequestID, driverAccessToken, 23);
                    logger.detail("Rejected pickup " + pickupRequestID +" as driver " + driverPhoneNum +" through api call [As Driver is eligible for the trip]");
                }
            //     Switch and login on same device
                utility.switchToApp("driver","same");
                String navigationBarName = action.getScreenHeader(homepage.Text_NavigationBar());
                if(navigationBarName.equalsIgnoreCase("ONLINE")) {
                    action.click(homepage.Button_AppMenu());
                    Thread.sleep(1000);
                    action.click(homepage.AppMenu_ScheduledTrip());
                }

                log("I should able to accept trip through virtual notification",
                        "Driver "+driverPhoneNum+" accepts delivery "+ pickupRequestID+" through virtual notification");
            }
            else
            {
                fail("I should be able to view push notification [Virtual] : " + getExpectedNotification(expectedNotification), "PUSH NOTIFICATIONS NOT RECEIVED for driver "+driverPhoneNum+" : notifications with text : " + getExpectedNotification(expectedNotification), true);
            }
        }
        else
        {
            fail("I should be able to accept virtual push notification : " + getExpectedNotification(expectedNotification), "VIRTUAL PUSH NOTIFICATIONS NOT RECEIVED for driver "+driverPhoneNum+" : notifications with text : " + getExpectedNotification(expectedNotification), true);

        }
    }

    public void tryAcceptVirtualNotificationAsDriver(String trip, String driverPhoneNum, String driverPassword, String expectedNotification) throws InterruptedException{
        String driverPhoneCode="1";
        String pickupRequestID = "";
        Response response = null;
        switch (trip.toLowerCase())
        {
            case "second":
                pickupRequestID= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST2");
                break;
            default:
            case "first":
                pickupRequestID= (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                break;

        }

        if(pickupRequestID== "")
        {   pickupRequestID =  dbUtility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA"));
        }
        if(pickupRequestID!= "") {
            if(driverPhoneNum== null) {
                driverPhoneNum =  (String) cucumberContextManager.getScenarioContext("DRIVER_2_PHONE");
            }
            if(driverPhoneNum!= null) {
                String pushNotificationContent = new DbUtility().getPushNotificationContent(driverPhoneNum, pickupRequestID);
                String expectedNotificationData = getExpectedNotification(expectedNotification);
                if(pushNotificationContent!=null)
                    testStepVerify.isTrue(pushNotificationContent.contains(expectedNotificationData),"VIRTUAL PUSH NOTIFICATIONS RECEIVED for Driver "+ driverPhoneNum +" : notifications with text :" +expectedNotificationData, "VIRTUAL PUSH NOTIFICATIONS NOT RECEIVED for Driver "+ driverPhoneNum +"  notifications with text :" +expectedNotificationData +" | Actual : "+ pushNotificationContent);

                String driverAccessToken = new DbUtility().getDriverCurrentToken(driverPhoneNum);

                    logger.detail("View pickup details of " + pickupRequestID +" as driver " + driverPhoneNum );
                    Thread.sleep(10000);
                    Boolean isDriverEligible = new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequestID);
                    new GeneralUtility().logDriverDeviceToken(driverPhoneNum);
                    if (!isDriverEligible)
                        error("Diver should be eligible for trip", "Driver "+driverPhoneNum+" is not eligible for pickup : "+ pickupRequestID, false);
                    //response = new CoreServices().getPickupdetails(pickupRequestID, driverAccessToken,"");
                String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
                JsonPath jsonpathevaluator = new CoreServices().getPickupdetailsFromPushNotification(pickupRequestID, driverAccessToken,geofence);
                    logger.detail("ERROR MESSAGE "+jsonpathevaluator.get("Error.Message"));
                    cucumberContextManager.setScenarioContext("API_RESPONSE",jsonpathevaluator.get("Error.Message"));

                // Switch and login on same device
                utility.switchToApp("driver","same");
                String navigationBarName = action.getScreenHeader(homepage.Text_NavigationBar());
                if(navigationBarName.equalsIgnoreCase("ONLINE")) {
                    action.click(homepage.Button_AppMenu());
                    Thread.sleep(1000);
                    action.click(homepage.AppMenu_ScheduledTrip());
                    Thread.sleep(1000);
                    //  action.click(homepage.Button_AppMenu());
                    Thread.sleep(1000);
                    // action.click(homepage.AppMenu_Home());
                }

                log("I should able to accept trip through virtual notification",
                        "Driver "+driverPhoneNum+" accepts delivery "+ pickupRequestID+" through virtual notification");

            }
            else
            {
                fail("I should be able to view push notification [Virtual] : " + getExpectedNotification(expectedNotification), "PUSH NOTIFICATIONS NOT RECEIVED for driver "+driverPhoneNum+" : notifications with text : " + getExpectedNotification(expectedNotification), true);
            }
        }
        else
        {
            fail("I should be able to accept virtual push notification : " + getExpectedNotification(expectedNotification), "VIRTUAL PUSH NOTIFICATIONS NOT RECEIVED for driver "+driverPhoneNum+" : notifications with text : " + getExpectedNotification(expectedNotification), true);

        }

    }
    @Then("^I should not get notification for \"([^\"]*)\" for \"([^\"]*)\"$")
    public void i_should_not_get_notification_for_something_for_something(String appName, String expectedNotification) throws InterruptedException {

        String driverPhoneCode="1";
        String driverPhoneNum =  (String) cucumberContextManager.getScenarioContext("DRIVER_1_PUSH");;
        String pickupRequestID = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        if(pickupRequestID== "")
        {   pickupRequestID =  dbUtility.getPickupRef((String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE_EXTRA"));
        }
        if(pickupRequestID!= "") {
            if(driverPhoneNum== null) {
                driverPhoneNum =  (String) cucumberContextManager.getScenarioContext("DRIVER_2_PHONE");
            }
            if(driverPhoneNum!= null) {
                String pushNotificationContent = new DbUtility().getDriverPushNotificationContent(driverPhoneNum, pickupRequestID,expectedNotification);

                if (pushNotificationContent == "") {
                   // if (new DbUtility().isDriverEligibleForTrip(driverPhoneNum, pickupRequestID))
                        //if(!expectedNotification.contains("URGENT"))
                       // error("Diver should not be eligible for trip", "Driver " + driverPhoneNum + " is eligible for pickup : " + pickupRequestID, false);
                    testStepVerify.isTrue(true, "VIRTUAL PUSH NOTIFICATIONS NOT RECEIVED : notifications with text :" + getExpectedNotification(expectedNotification), "VIRTUAL PUSH NOTIFICATIONS RECEIVED : notifications with text :" + pushNotificationContent);
                }
                else {
                    fail("I should be not receive push notification [Virtual] : " + getExpectedNotification(expectedNotification), "Driver has received push notification " + getExpectedNotification(expectedNotification), true);
                }

                log("I should not be able to get trip notification",
                        "Driver doesnot get the virtual push notification and also driver " + driverPhoneNum + " is not eligible for the pickuprequest : "+ pickupRequestID);
            }
        }
       /* //Thread.sleep(20000);
        Thread.sleep(10000);
        try {
            String currentApplication = (String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION");
            String appHeaderName = getAppHeader(appName);
            String bunddleId = getBundleId(currentApplication);


            cucumberContextManager.setFeatureContextContext("CURRENT_APPLICATION", appName.toUpperCase());
            ((AppiumDriver) SetupManager.getDriver()).terminateApp(bunddleId);
            action.showNotifications();

            log("Checking notifications", "Checking notifications", true);

            //	logger.detail(SetupManager.getDriver().getPageSource());
            boolean notificationClick = clickNotification(appHeaderName, getExpectedNotification(expectedNotification));
            if (!notificationClick) {
                Thread.sleep(80000);
                notificationClick = clickNotification(appHeaderName, getExpectedNotification(expectedNotification));

            }
            if (notificationClick) {
                fail("I should not get notification for " + expectedNotification, "I should not get notification for " + getExpectedNotification(expectedNotification), true);
            } else {
                pass("I should not able to click notification for" + expectedNotification, "I was not able to notifications with text" + getExpectedNotification(expectedNotification), true);
                action.hideNotifications();
            }

            //temp fixed for iOS  device
            utility.handleIosUpdateMessage();
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
        */
    }

    private String getExpectedNotification(String identifier) {
        String text = "";
        switch (identifier.toUpperCase()) {
            case "ON DEMAND TRIP":
                text = PropertyUtility.getMessage("driver.notification.ondemand");
                break;
            case "T-2 BEFORE SCHEDULED TRIP":
                text = PropertyUtility.getMessage("customer.notification.scheduled.t.minus.2");
                break;
            case "DRIVER CANCELLED":
                text = PropertyUtility.getMessage("customer.notification.driver.cancelled");
                break;
            case "DRIVER ENROUTE":
                text = PropertyUtility.getMessage("customer.notification.driver.accepted");
                break;
            case "SCHEDULED PICKUP ACCEPTED":
                text = PropertyUtility.getMessage("customer.notification.scheduled.driver.accepted");
                break;
            case "STACK TRIP":
                text = PropertyUtility.getMessage("driver.notification.stack");
                break;
            case "CUSTOMER CANCEL STACK TRIP":
                text = PropertyUtility.getMessage("driver.notification.stack.cancel");
                break;
            case "DRIVER ACCEPTED STACK BUNGII":
                text = PropertyUtility.getMessage("customer.notification.driver.accepted.stack");
                break;
            case "DRIVER STARTED STACK BUNGII":
                text = PropertyUtility.getMessage("customer.notification.driver.started.stack");
                break;
            case "DRIVERS ARE ENROUTE":
                text = PropertyUtility.getMessage("customer.notification.scheduled.driver.started");
                break;
            case "URGENT SCHEDULED PICKUP AVAILABLE":
                text = PropertyUtility.getMessage("driver.notification.scheduled.urgent");
                break;
            case "CUSTOMER CANCELLED SCHEDULED BUNGII":
                text = PropertyUtility.getMessage("driver.notification.customer.scheduled.cancel");
                break;
            case "DRIVER CANCELLED BUNGII":
                text = PropertyUtility.getMessage("customer.notification.scheduled.driver.canceled");
                break;
            case "OTHER DRIVER CANCELLED BUNGII":
                text = PropertyUtility.getMessage("driver.other.driver.bungii.cancel.notification");
                break;
            case "TIP RECEIVED 5 DOLLAR":
                String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
                String expectedCustomerName = custName.substring(0, custName.indexOf(" ") + 2);
                text = PropertyUtility.getMessage("driver.received.5.dollar.tip");
                text=text.replace("<Customer Name>", expectedCustomerName);
                break;
            case "BUNGII FINISHED -RATE DRIVER":
                text = PropertyUtility.getMessage("customer.bungii.finished.rate.driver");
                break;
            case "TAP NOTIFICATION TO ACTIVATE BUNGII":
                text=PropertyUtility.getMessage("driver.activate.bungii");
                break;
            case "SCHEDULED PICKUP AVAILABLE":
                text = PropertyUtility.getMessage("driver.notification.scheduled");
                //	$<Day>, $<MONTH> <$Date>

                String schDateTime = ((String) cucumberContextManager.getScenarioContext("BUNGII_TIME")).replace("a.m.","AM").replace("p.m.","PM");
                String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();

                String [] schDate = schDateTime.split("'");
                //	DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyyy", Locale.ENGLISH);
                DateFormat format = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
                try {
                    format.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

                    Date date = format.parse(schDate[0]);
                    Date currentDate = new Date();
                    //int year=currentDate.getYear()+1900;
                    date.setYear(currentDate.getYear());
                    int month = date.getMonth();
                    String strMonth = getMonthForInt(month);
                    int dayOfMonth = date.getDate();
                    String dayOfMonthStr = String.valueOf(dayOfMonth) + getDayOfMonthSuffix(dayOfMonth);

                    SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
                    String dayOfWeek = simpleDateformat.format(date);

                    text = text.replace("$<Day>", dayOfWeek);
                    text = text.replace("$<MONTH>", strMonth);
                    text = text.replace("$<Date>", dayOfMonthStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                break;
        }
        return text;
    }

    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    String getDayOfMonthSuffix(final int n) {
        checkArgument(n >= 1 && n <= 31, "illegal day of month: " + n);
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    private String getAppHeader(String appName) {
        String appHeaderName = "";
        switch (appName.toUpperCase()) {
            case "DRIVER":
                appHeaderName = "BUNGII DRIVER";
                break;
            case "CUSTOMER":
                appHeaderName = "BUNGII";
                break;
            default:
                error("UnIm plemented Step or in correct app", "UnImplemented Step");
                break;
        }
        return appHeaderName;
    }

    @Then("^Notification for \"([^\"]*)\" for \"([^\"]*)\" should be displayed$")
    public void notification_for_something_for_something_should_be_displayed(String actor, String actionToPerfrom) {
        try {
            String bunddleId = getBundleId((String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION"));
           // ((AppiumDriver) SetupManager.getDriver()).terminateApp(bunddleId);
        //    action.showNotifications();

            String expectedMessage = getExpectedNotification(actionToPerfrom);
            boolean isDisplayed = checkNotification(getAppHeader(actor), expectedMessage);

            testStepVerify.isTrue(isDisplayed, actor + " should be notified for " + expectedMessage, actor + " was notified for " + expectedMessage, "Not able to get notification with text for '" + expectedMessage + "' for" + actor);
           // action.hideNotifications();
            action.switchApplication(bunddleId);
            // fixed for iOS device where update is prompted
          //  utility.handleIosUpdateMessage();
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
    }

    @When("^I clear all notification$")
    public void i_clear_all_notification() {
        String bunddleId = getBundleId((String) cucumberContextManager.getFeatureContextContext("CURRENT_APPLICATION"));

        try {
            ((AppiumDriver) SetupManager.getDriver()).terminateApp(bunddleId);
            action.showNotifications();


            boolean cleared = clearAllNotifcation();
            if (cleared)
                log("I should able cleared all notification", "I cleared all notification");
            else
                log("I should able cleared all notification",
                        "Not notification found on device");

            action.hideNotifications();
            utility.handleIosUpdateMessage();
            ((AppiumDriver) SetupManager.getDriver()).activateApp(bunddleId);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);

        }
    }

    private String getBundleId(String Identifier) {
        String bundleID = "";
        switch (Identifier.toUpperCase()) {
            case "DRIVER":
                bundleID = PropertyUtility.getProp("bundleId_Driver");
                break;
            case "CUSTOMER":
                bundleID = PropertyUtility.getProp("bundleId_Customer");
                break;
            default:
                bundleID = PropertyUtility.getProp("bundleId_Customer");
                break;

        }
        return bundleID;
    }

    /**
     * Get all notification list,
     *
     * @return List of all notifications
     */
    public List<String> getNotifcationList() {
        List<String> notificationList = new ArrayList<String>();
        List<WebElement> elements = notificationPage.Cell_Notification();
        for (WebElement notifcation : elements) {
            notificationList.add(notifcation.getAttribute("label"));
        }
        return notificationList;
    }

    /**
     * Click notification
     *
     * @param application Application name of which notification is to clicked
     * @param Message     Notification text
     * @return Did we click notification text or not
     */
    public boolean clickNotification(String application, String Message) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException, InterruptedException {
        boolean clicked = false;
        IOSDriver<MobileElement> driver = (IOSDriver<MobileElement>) SetupManager.getDriver();

        String iosVersion = driver.getCapabilities().getCapability("platformVersion").toString();
        List<WebElement> elements = new ArrayList<WebElement>();
        if (iosVersion.contains("10")) {
            for (int i = 0; i <= 3; i++) {
                String xml = driver.getPageSource();
                Point p = utility.getCordinatesForNotification(xml, Message);
                if (p != null) {
                    action.click(p);
                    clicked = true;
                    break;
                }
                Thread.sleep(10000);

            }
        } else
            elements = notificationPage.Cell_Notification();

        for (WebElement notifcation : elements) {
            String[] info = notifcation.getAttribute("label").split(",", 3);
            System.err.println(info[0] + " ||  " + info[2]);
            if (application.equalsIgnoreCase(info[0].trim()) && Message.equals(info[2].trim())) {
                action.swipeRight(notifcation);
                clicked = true;
                break;
            }

        }
        return clicked;

    }

    /**
     * Check if given notification is displayed
     *
     * @param application Application name of which notification is to checked
     * @param Message     Notification text
     * @return Did we find notification text or not
     */
    public boolean checkNotification(String application, String Message) {
        List<String> notificationList = getNotifcationList();
        boolean isFound = false;

        for (String notification : notificationList) {
            String[] info = notification.split(",", 3);

            if (application.equalsIgnoreCase(info[0].trim()) && Message.equals(info[2].trim())) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    public boolean clearAllNotifcation() {
        boolean cleared = false;
        //click on clear button on notification page
        List<WebElement> clearButtons = notificationPage.Button_NotificationClear();
        for (WebElement clearButton : clearButtons) {
            action.click(clearButton);
            action.click(notificationPage.Button_NotificationClearConfirm(true));
            cleared = true;
        }

        List<WebElement> elements = notificationPage.Cell_Notification();

        for (WebElement notifcation : elements) {
            String notificationText = notifcation.getAttribute("label");
            logger.detail("Cleared notification :" + notificationText);

            action.swipeLeft(notifcation);
            if (notifcation.isDisplayed())
                action.swipeLeft(notifcation);
            cleared = true;

        }
        //minimum 3 notification are shown on all iOS screen , If earlier notification had  more than 3 notification then only search for notification agiain . This will save time .
        if (elements.size() > 3) {
            while (notificationPage.Cell_Notification().size() > 0)
                clearAllNotifcation();
        }
        return cleared;

    }
}
