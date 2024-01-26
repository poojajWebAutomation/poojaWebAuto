package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.LogInSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_ScheduledTripsPage;
import com.bungii.web.pages.partner.*;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.pass;
import static com.bungii.web.utilityfunctions.DbUtility.getListOfService;


public class Partner_LoginSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LogInSteps.class);
    Partner_LoginPage Page_Partner_Login = new Partner_LoginPage();
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();
    Partner_Done Page_Partner_Done = new Partner_Done();
    Partner_DeliveryList Page_Partner_Delivery_List = new Partner_DeliveryList();
    Admin_ScheduledTripsPage Page_Admin_ScheduledTrips = new Admin_ScheduledTripsPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    //DbUtility dbUtility = new DbUtility();
    Kiosk_Page Page_Kiosk = new Kiosk_Page();
    Driver_RatingPage Page_DriverRating = new Driver_RatingPage();

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
            case "Admin":
                utility.AdminLoginFromPartner();
                pass("I should be navigate to " + page  ,
                        "I navigated to " + page , true);
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
        SetupManager.getObject().manage().window().maximize();
        switch (str)
        {
            case "valid":
                action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("Invalid_PartnerPassword"));
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

    @And("^I click on close button on service level$")
    public void i_click_on_close_button_on_service_level(){
        try{
        action.click(Page_Partner_Dashboard.Button_close());
        log("I should able to click on close button on service level.","Service level should get close on clicked on close button." , false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I change the service level to \"([^\"]*)\" in \"([^\"]*)\" portal$")
    public void i_change_the_service_level_to_something_in_something_portal(String Service_Name, String Site_Name) throws Throwable {
        try {
            switch (Site_Name) {
                case "Partner":
                    action.click(Page_Partner_Dashboard.Dropdown_ServiceLevel(Service_Name));
                    cucumberContextManager.setScenarioContext("Selected_service", Service_Name);
                    break;
                case "Admin":
                    //action.click(Page_Admin_ScheduledTrips.Admin_Dropdown_ServiceLevel(Service_Name));
                    //String PreviousServiceLevel=Page_Admin_ScheduledTrips.Admin_Dropdown_ServiceLevel().getText();
                    String PreviousServiceLevel=action.getText(Page_Admin_ScheduledTrips.Admin_DropdownServiceLevelSelected());
                    cucumberContextManager.setScenarioContext("Old_service", PreviousServiceLevel);
                    action.selectElementByText(Page_Admin_ScheduledTrips.Admin_Dropdown_ServiceLevel(), Service_Name);
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

    @And("^I change the service level to \"([^\"]*)\"$")
    public void i_change_the_service_level(String Service_Name) throws InterruptedException {
        try{
        action.click(Page_Partner_Dashboard.Dropdown_ServiceLevel(Service_Name));
        cucumberContextManager.setScenarioContext("Selected_service",Service_Name);
        log("I should able to change the service level to "+Service_Name,"Service name should get changed to "+Service_Name , false);
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
                    action.click(Page_Partner_Login.Button_Sign_In());
                    break;
                case "GET QUOTE":
                case "GET ESTIMATE":
                    action.click(Page_Partner_Dashboard.Button_Get_Estimate());
                    Thread.sleep(3000);
                    break;
                case "Continue":
                    String Partner_Portal_Site = (String) cucumberContextManager.getScenarioContext("PP_Site");
                    if (Partner_Portal_Site.equalsIgnoreCase("service level")) {

                        String Price_Estimated_Page = action.getText(Page_Partner_Dashboard.Label_DeliveryCostEstimate());
                                //action.getElementByXPath("//label[contains(text(),'Delivery Cost:')]//following::strong").getText();
                        Price_Estimated_Page = Price_Estimated_Page.substring(1);
                        cucumberContextManager.setScenarioContext("Price_Estimate_Page", Price_Estimated_Page);
                        String Estimate_distance = action.getText(Page_Partner_Dashboard.Label_Distance()).replace(" miles","");//calculate values as per the displayed miles value to avoid mismatch in calculation
                        cucumberContextManager.setScenarioContext("Distance_Estimate_Page", Estimate_distance);
                        String Estimated_Delivery_Time=action.getText(Page_Partner_Dashboard.Label_EstDeliveryTime());
                        cucumberContextManager.setScenarioContext("Estimated_Delivery_Time", Estimated_Delivery_Time);

                        action.click(Page_Partner_Dashboard.Button_Get_Estimate());
                    }
                    else if(Partner_Portal_Site.equalsIgnoreCase("FloorDecor service level")){
                        String Estimated_Delivery_Time=action.getText(Page_Partner_Dashboard.Label_EstDeliveryTime());
                        cucumberContextManager.setScenarioContext("Estimated_Delivery_Time", Estimated_Delivery_Time);
                        action.click(Page_Partner_Dashboard.Button_Continue());
                    }
                    else {
                        action.click(Page_Partner_Dashboard.Button_Continue());
                    }
                    break;
                case "Schedule Bungii":
                    action.JavaScriptScrolldown();
                    action.click(Page_Partner_Delivery.Button_Schedule_Bungii());
                    cucumberContextManager.setFeatureContextContext("BUNGII_INITIAL_SCH_TIME", System.currentTimeMillis() / 1000L);

                    break;
                case "New Bungii":
                    action.click(Page_Partner_Delivery.Button_New_Bungii());
                    break;
                case "Track Deliveries":
                    Thread.sleep(5000);
                    action.click(Page_Partner_Done.Dropdown_Setting());
                    Thread.sleep(5000);
                    action.click(Page_Partner_Done.Button_Track_Deliveries());
                    Thread.sleep(5000);
                    if(action.getCurrentURL().contains("login")|| action.getCurrentURL().contains("Login"))
                    {
                        //Workaround for app getting logged out when run in parallel
                        action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                        action.click(Page_Partner_Login.Button_Sign_In());
                        Thread.sleep(5000);
                        testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_Start_Over()), PropertyUtility.getMessage("Start_Over_Header"));
                        Thread.sleep(5000);
                        if(!action.isElementPresent(Page_Partner_Done.Dropdown_Setting(true))) {
                            action.click(Page_Kiosk.Link_Setting());
                            action.clearSendKeys(Page_Kiosk.Textbox_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                            action.click(Page_Kiosk.Button_Continue());
                        }
                            action.click(Page_Partner_Done.Dropdown_Setting());
                            action.click(Page_Partner_Done.Button_Track_Deliveries());

                    }
                    break;
                case "Back to Estimate":
                    action.click(Page_Partner_Delivery.Link_Back_To_Estimate());
                    break;
                case "Cancel Delivery link":
                    action.click(Page_Partner_Delivery_List.Link_Cancel_Delivery());
                    break;
                case "OK":
                    action.click(Page_Partner_Delivery_List.Button_OK());
                    break;
                case "OK on Delivery Cancellation Failed":
                    action.click(Page_Partner_Delivery_List.Button_Ok__On_Delivery_Cancellation_Failed());
                    break;
                case "Cancel Delivery":
                    action.click(Page_Partner_Delivery_List.Button_Cancel_Delivery());
                    break;
                case "Service Level List":
                    action.click(Page_Partner_Dashboard.Dropdown_Service_Level());
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
    @And("^I calculate the estimated delivery time for \"([^\"]*)\"$")
    public void i_calculate_the_estimated_delivery_time_for_something(String portalType) throws Throwable {
        try {
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            int driverTime= Integer.parseInt(PropertyUtility.getDataProperties("driver.buffer.drive.time"));
            switch (portalType){
                case "geofence based portal":
                    String scheduledDate= (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
                    String pickUpId = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    String time=scheduledDate.substring(8,13);
                    Time timeValue = new Time(formatter.parse(time).getTime());

                    int  loadUnloadTime = Math.round(Float.valueOf(dbUtility.getLoadUnloadTime(pickUpId)));
                    int calLoadUnload=loadUnloadTime/3;
                    int projectedDriveTime= Integer.parseInt(dbUtility.getProjectedDriverTime(pickUpId));
                    int minutes=calLoadUnload+projectedDriveTime+driverTime;
                    utility.calculateEstDeliveryTime(minutes,timeValue);
                    break;

                case "edited address for geofence based portal":
                    String changedTime= (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
                    String newPickUpId = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    String time1=changedTime.substring(8,13);
                    Time timeValue1 = new Time(formatter.parse(time1).getTime());

                    int  loadUnloadTime1 = Math.round(Float.valueOf(dbUtility.getLoadUnloadTime(newPickUpId)));
                    int calLoadUnload1=loadUnloadTime1/3;
                    int projectedDriveTime1= Integer.parseInt(dbUtility.getProjectedDriverTime(newPickUpId));
                    int mins=calLoadUnload1+projectedDriveTime1+driverTime;
                    utility.calculateEstDeliveryTime(mins,timeValue1);
                    String lowerRange= (String) cucumberContextManager.getScenarioContext("ESTIMATED_LOWER_RANGE_DELIVERY_TIME");
                    String upperRange= (String) cucumberContextManager.getScenarioContext("ESTIMATED_UPPER_RANGE_DELIVERY_TIME");
                    Time lowerTimeValue = new Time(formatter.parse(lowerRange).getTime());
                    Time upperTimeValue = new Time(formatter.parse(upperRange).getTime());
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(lowerTimeValue);
                    calendar.add(Calendar.MINUTE, -5);
                    String lowerRangeTime=String.valueOf(calendar.getTime());
                    calendar.setTime(upperTimeValue);
                    calendar.add(Calendar.MINUTE, -5);
                    String upperRangeTime=String.valueOf(calendar.getTime());
                    String estimateLowerRange=lowerRangeTime.substring(11,16);
                    cucumberContextManager.setScenarioContext("ESTIMATED_LOWER_RANGE_DELIVERY_TIME",estimateLowerRange);
                    String estimateUpperRange=upperRangeTime.substring(11,16);
                    cucumberContextManager.setScenarioContext("ESTIMATED_UPPER_RANGE_DELIVERY_TIME",estimateUpperRange);
                    break;

                case "fixed distance based":
                    String scheduledDat= (String) cucumberContextManager.getScenarioContext("Partner_Schedule_Time");
                    String pickUpReference = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    String serviceName = (String) cucumberContextManager.getScenarioContext("Selected_service");
                    String Time=scheduledDat.substring(16,20);
                    Time timeValue2 = new Time(formatter.parse(Time).getTime());

                    long default_Pickup_Time = dbUtility.getDefaultPickupTime(serviceName, "Biglots");
                    default_Pickup_Time = utility.Milliseconds_To_Minutes(default_Pickup_Time);
                    long default_Dropoff_time = dbUtility.getDefaultDropoffTime(serviceName, "Biglots");
                    default_Dropoff_time = utility.Milliseconds_To_Minutes(default_Dropoff_time);
                    int  sumLoadUnload = (int) (default_Pickup_Time+default_Dropoff_time);
                    int loadUnload=sumLoadUnload/3;
                    int projectedDriveTime2= Integer.parseInt(dbUtility.getProjectedDriverTime(pickUpReference));
                    int min=loadUnload+projectedDriveTime2+driverTime;
                    utility.calculateEstDeliveryTime(min,timeValue2);
                    break;

                case "weight based":
                    String scheduledTime= (String) cucumberContextManager.getScenarioContext("Partner_Schedule_Time");
                    String pickUpRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    String serviceLevel = (String) cucumberContextManager.getScenarioContext("Selected_service");
                    String time2=scheduledTime.substring(16,20);
                    Time timevalue = new Time(formatter.parse(time2).getTime());
                    long defaultPickupTime = dbUtility.getDefaultPickupTime(serviceLevel, "floordecor166");
                    defaultPickupTime = utility.Milliseconds_To_Minutes(defaultPickupTime);
                    long defaultDropoffTime = dbUtility.getDefaultDropoffTime(serviceLevel, "floordecor166");
                    defaultDropoffTime = utility.Milliseconds_To_Minutes(defaultDropoffTime);
                    int  sumLoadUnload1 = (int) (defaultPickupTime+defaultDropoffTime);
                    int loadUnload1=sumLoadUnload1/3;
                    int projectedDriveTime3= Integer.parseInt(dbUtility.getProjectedDriverTime(pickUpRef));
                    int minute=loadUnload1+projectedDriveTime3+driverTime;
                    utility.calculateEstDeliveryTime(minute,timevalue);
                    break;

            }
            log("I should be able to calculate the correct estimated delivery time range","I am able to calculate the correct estimated delivery time range",false);

        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I check if correct \"([^\"]*)\" is displayed$")
    public void i_check_if_correct_something_is_displayed(String portal) throws Throwable {
       try{
           String deliveryEstimateTimePP= (String) cucumberContextManager.getScenarioContext("Estimated_Delivery_Time");
           switch (portal){
               case "estimated time on admin portal":
                   String deliveryEstimateTimeAdminPortal=action.getText(Page_Partner_Delivery_List.Text_EstimatedDeliveryTime());
                   testStepAssert.isEquals(deliveryEstimateTimeAdminPortal,deliveryEstimateTimePP,
                           "Estimated delivery time displayed on partner portal and admin portal should be same.",
                           "Estimated delivery time displayed on partner portal and admin portal is the same.",
                           "Estimated delivery time displayed on partner portal and admin portal are not the same.");
                   break;

               case "estimated time on partner portal":
                   String deliveryEstimateTimePartnerPortal=action.getText(Page_Partner_Delivery_List.Text_DeliveryTime());
                   testStepAssert.isEquals(deliveryEstimateTimePartnerPortal,deliveryEstimateTimePP,
                           "Estimated delivery time displayed on partner portal delivery details and while creating trip should be same.",
                           "Estimated delivery time displayed on partner portal delivery details and while creating trip is the same.",
                           "Estimated delivery time displayed on partner portal delivery details and while creating trip are not the same.");
                   break;

               case "estimated time weight based Partner portal":
               case "estimated time fixed distance based Partner portal":
                   String calLowerRange1 = (String) cucumberContextManager.getScenarioContext("ESTIMATED_LOWER_RANGE_DELIVERY_TIME");
                   String calUpperRange1 = (String) cucumberContextManager.getScenarioContext("ESTIMATED_UPPER_RANGE_DELIVERY_TIME");
                   String actualLowerRange1 = action.getText(Page_Partner_Delivery_List.Text_DeliveryTime()).toString().substring(0,5);
                   testStepAssert.isEquals(actualLowerRange1,calLowerRange1,
                           "The calculated lower range and the displayed should be same",
                           "The calculated lower range and the displayed is same",
                           "The calculated lower range and the displayed is not the same");
                   String actualUpperRange1 = action.getText(Page_Partner_Delivery_List.Text_DeliveryTime()).toString().substring(11,16);
                   testStepAssert.isEquals(actualUpperRange1,calUpperRange1,
                           "The calculated upper range and the displayed should be same",
                           "The calculated upper range and the displayed is same",
                           "The calculated upper range and the displayed is not the same");
                   break;

               case "estimated time geofence based Partner portal":
                   action.refreshPage();
                   String calLowerRange = (String) cucumberContextManager.getScenarioContext("ESTIMATED_LOWER_RANGE_DELIVERY_TIME");
                   String calUpperRange = (String) cucumberContextManager.getScenarioContext("ESTIMATED_UPPER_RANGE_DELIVERY_TIME");
                   String actualLowerRange = action.getText(Page_Partner_Delivery_List.Text_EstimatedDeliveryTime()).toString().substring(0,5);
                   testStepAssert.isEquals(actualLowerRange,calLowerRange,
                           "The calculated lower range and the displayed should be same",
                           "The calculated lower range and the displayed is same",
                           "The calculated lower range and the displayed is not the same");
                   String actualUpperRange = action.getText(Page_Partner_Delivery_List.Text_EstimatedDeliveryTime()).toString().substring(11,16);
                   testStepAssert.isEquals(actualUpperRange,calUpperRange,
                           "The calculated upper range and the displayed should be same",
                           "The calculated upper range and the displayed is same",
                           "The calculated upper range and the displayed is not the same");
                   break;
           }
            log("I should be able to check the estimated delivery time.","I am able to check the estimated delivery time.",false);
       }
       catch(Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }

    @Then("^I should \"([^\"]*)\" for \"([^\"]*)\" Alias$")
    public void i_should_something_for_something_alias(String str,String Alias){
        try {
            cucumberContextManager.setScenarioContext("Alias", Alias);
            //List Service_name = new DbUtility().getServiceName(Alias);
            List<HashMap<String, Object>> Service_name = getListOfService(Alias);
            switch (str) {
                case "see all the Service Level":
                    if (Alias.equalsIgnoreCase("Biglots")||Alias.equalsIgnoreCase("Cort Furniture #7302")) {

                        for (int i = 0; i < Service_name.size(); i++) {
                            String Db_Service_Name = Service_name.get(i).values().toString();
                            Db_Service_Name = Db_Service_Name.substring(1, Db_Service_Name.length() - 1);
                            String Xpath = "//span[contains(text(),'" + Db_Service_Name + "')]";
//                        String Display_Service_name= action.getElementByXPath(Xpath).getText();
                            testStepAssert.isElementDisplayed(action.getElementByXPath(Xpath), "Service Name:-" + Db_Service_Name + " should be shown", "Service Name:-" + Db_Service_Name + " is shown", "Service Name-" + Db_Service_Name + " is not shown");
                            //testStepVerify.isEquals(Display_Service_name, Db_Service_Name);

                        }
                        log("All service for " + Alias + " should be listed ", "All service for " + Alias + " are listed ", true);
                    }
                    break;
                case "see correct Estimation Duration":
                    long total_Estimation_Duration;
                    String subDomain = Alias;
                    String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    String service_name = (String) cucumberContextManager.getScenarioContext("Selected_service");
                    long db_EST_Time = dbUtility.getEstimateTimeforPickup(pickupRequest);
                    long default_Pickup_Time = dbUtility.getDefaultPickupTime(service_name, subDomain);
                    default_Pickup_Time = utility.Milliseconds_To_Minutes(default_Pickup_Time);
                    long default_Dropoff_time = dbUtility.getDefaultDropoffTime(service_name, subDomain);
                    default_Dropoff_time = utility.Milliseconds_To_Minutes(default_Dropoff_time);

                    total_Estimation_Duration = db_EST_Time + default_Pickup_Time + default_Dropoff_time;

                    String display_Estimation_Duration = action.getText(Page_Partner_Delivery_List.Text_Estimated_Duration());
                    String estimated_Duration[] = display_Estimation_Duration.split(":");
                    String hours = estimated_Duration[0];
                    String minutes = estimated_Duration[1];

                    long hrs = Long.parseLong(hours);
                    long mins = hrs * 60;

                    long total_Display_Estimated_Duration = mins + Long.parseLong(minutes);
                    testStepVerify.isEquals(String.valueOf(total_Display_Estimated_Duration), String.valueOf(total_Estimation_Duration), "Correct Total estimated duration is shown.", "Wrong Total estimated duration is shown");
                    log("Expected Estimated Duration= " + total_Estimation_Duration, "Actual Estimated Duration= " + total_Display_Estimated_Duration, true);
                    break;
                default:
                    break;
            }
        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step , I Should "+ str,
                    true);
        }
    }

    @Then("^I should \"([^\"]*)\"$")
    public void IShould(String str) throws ParseException {
        try {
            switch (str) {
                case "be logged in":
                    //testStepVerify.isEquals(action.getText(Page_Driver_Dashboard.Header_Dashboard()), PropertyUtility.getMessage("DriverDashboardHeader"));
                    //action.waitUntilIsElementExistsAndDisplayed(Page_Partner_Dashboard.Label_Get_Estimate_Header(), (long) 2000);
                    testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_Start_Over()), PropertyUtility.getMessage("Start_Over_Header"));
                    break;
                case "see 1 pallet and 2 pallets":
                    testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_1Pallet()), PropertyUtility.getDataProperties("1Pallet"));
                    testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_2Pallets()), PropertyUtility.getDataProperties("2Pallets"));
                    break;
                case "see validations message for blank password field":
                    testStepVerify.isEquals(action.getText(Page_Partner_Login.Message_Blank_Incorrect_Password()), PropertyUtility.getMessage("Blank_Password"));
                    break;
                case "see validations message for incorrect password field":
                    testStepVerify.isEquals(action.getText(Page_Partner_Login.Message_Blank_Incorrect_Password()), PropertyUtility.getMessage("Incorrect_Password"));
                    break;
                case "see Delivery Details screen":
                    /////////////////////////////WorkAround to eliminate logout on Track deliveries/////////////////////////////////////
                    Thread.sleep(5000);
                    if(SetupManager.getObject().getCurrentUrl().contains("login"))
                    {
                        action.clearSendKeys(Page_Partner_Login.TextBox_PartnerLogin_Password(), PropertyUtility.getDataProperties("PartnerPassword"));
                        action.click(Page_Partner_Login.Button_Sign_In());
                        action.click(Page_Partner_Done.Dropdown_Setting());
                        action.click(Page_Partner_Done.Button_Track_Deliveries());
                        logger.detail("PARTNER RELOGIN AS A WORKAROUND TO ELIMINATE FALSE KICKOUT");
                    }
                    /////////////////////////////WorkAround Ends/////////////////////////////////////

                    String PP_Site = (String) cucumberContextManager.getScenarioContext("SiteUrl");
                    if (PP_Site.equalsIgnoreCase("normal")) {
                        testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Text_Delivery_Details_Header()), PropertyUtility.getMessage("Delivery_Details_Header"));
                    } else if (PP_Site.equalsIgnoreCase("kiosk mode")) {
                        testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Text_Delivery_Details_Header()), PropertyUtility.getMessage("Delivery_Details_Header"));
                    } else if (PP_Site.equalsIgnoreCase("service level")) {
                        testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Text_Delivery_Details_Header()), PropertyUtility.getMessage("Service_Delivery_Details_Header"));
                    }else if (PP_Site.equalsIgnoreCase("Cort service level")) {
                        testStepVerify.isEquals(action.getText(Page_Partner_Delivery.Text_Delivery_Details_Header()), PropertyUtility.getMessage("Delivery_Details_Header"));
                    }
                    action.waitUntilIsElementExistsAndDisplayed(Page_Partner_Delivery.Text_Pickup_DateTime(),(long)7000);
                    String PickupDateTime = action.getText(Page_Partner_Delivery.Text_Pickup_DateTime());

                    String[] splitDate = PickupDateTime.split(" ", 2);
                    String Month = splitDate[0].substring(0, 3);
                    splitDate[0] = Month + " ";
                    PickupDateTime = splitDate[0] + splitDate[1];
                    cucumberContextManager.setScenarioContext("ActualPickupDateTime", PickupDateTime.replaceAll("[()]", "")); //This will be used in Track Delivery List

                    char ch = PickupDateTime.charAt(4);
                    if (PickupDateTime.charAt(4) == '0') {
                        StringBuilder sb = new StringBuilder(PickupDateTime);
                       // sb.deleteCharAt(4); this is not required as day is displayed in xx format
                        PickupDateTime = sb.toString();
                    }
                    PickupDateTime = PickupDateTime.replaceAll("[()]", "");
                    cucumberContextManager.setScenarioContext("PickupDateTime", PickupDateTime); //This will be used further
                    cucumberContextManager.setScenarioContext("TRACKINGID_SUMMARY", action.getText(Page_Partner_Dashboard.Text_Summary_TrackingId()));
                    break;
                case "see Done screen":
                    String Customer_Phone="";
                    String site = (String) cucumberContextManager.getScenarioContext("Site");
                    if(site.equalsIgnoreCase("service level")){
                        Customer_Phone = (String) cucumberContextManager.getScenarioContext("Customer_Mobile");
                    }
                    else {
                        Customer_Phone = (String) cucumberContextManager.getScenarioContext("CustomerPhone");
                    }
                    action.waitUntilIsElementExistsAndDisplayed(Page_Partner_Done.Text_Schedule_Done_Success_Header(), (long)7000);
                    testStepVerify.isEquals(action.getText(Page_Partner_Done.Text_Schedule_Done_Success_Header()), PropertyUtility.getMessage("Done_Success_Header"));
                    String PickupRequest = new DbUtility().getPickupRef(Customer_Phone);
                    String PickupToken = new DbUtility().getPickupToken(PickupRequest);
                    //String ScheduledTime = new DbUtility().getScheduledTime(Customer_Phone);
                    //String FromFormat="yyyy-mm-dd HH:mm:ss";
                    //String ToFormat ="MMM dd, YYYY at HH:mm aa z";
                    //String date = utility.GetDateInFormat(ScheduledTime,FromFormat,ToFormat);
                    //String ST = DateFormat("MMM dd, YYYY at HH:mm aa z",ScheduledTime);
                    //cucumberContextManager.setScenarioContext("Scheduled_Time",date);
                    //cucumberContextManager.setScenarioContext("pickupRequestPartner", PickupRequest);
                    cucumberContextManager.setScenarioContext("PICKUP_REQUEST",PickupRequest);
                    cucumberContextManager.setScenarioContext("PICKUP_TOKEN",PickupToken);
                    break;
                case "see the trip in the Delivery List":
                    String scheduled_time =(String) cucumberContextManager.getScenarioContext("Schedule_Date_Time");
                    scheduled_time =scheduled_time.replace("at","").replace("(","").replace(")","").replace("  "," ");
                    DateTimeFormatter dft = DateTimeFormatter.ofPattern("MMMM dd, yyyy h:mm a z", Locale.ENGLISH);//for checking the MMMM month format
                    DateTimeFormatter dft1 = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm a z",Locale.ENGLISH);//for converting to MMM month format
                    String geoLabel = utility.getTimeZoneBasedOnGeofenceId();
                    TimeZone zone = TimeZone.getTimeZone(geoLabel);
                    ZonedDateTime abc = LocalDateTime.parse(scheduled_time,dft).atZone(zone.toZoneId());
                    scheduled_time = dft1.format(abc);
                    scheduled_time = utility.getbungiiDayLightTimeValue(scheduled_time);

                    StringBuilder sb = new StringBuilder(scheduled_time);
                    sb.insert(13, "at ");
                    scheduled_time = sb.toString();
                    logger.detail("ScheduledTime : "+scheduled_time);
                    cucumberContextManager.setScenarioContext("Partner_Schedule_Time",scheduled_time);
                    String customer =(String) cucumberContextManager.getScenarioContext("Customer_Name");
                    action.clear(Page_Partner_Delivery_List.Textbox_Search());
                    logger.detail("Xpath : "+String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]", scheduled_time, customer));
                    testStepAssert.isElementDisplayed(Page_Partner_Delivery_List.Row_DeliveryList(scheduled_time,customer), "Trip for "+ customer +"[Scheduled Time :"+scheduled_time+"] should be displayed on partner portal", "Trip is displayed on partner portal", "Trip is not displayed on partner portal");
                    break;
                case "see the trip details":
                    String emailPickupAddress = action.getText(Page_Partner_Delivery.Text_Pick_Address());
                    cucumberContextManager.setScenarioContext("EmailPickupAddress",emailPickupAddress);
                    String emailDeliveryAddress = action.getText(Page_Partner_Delivery.Text_Delivery_Address());
                    cucumberContextManager.setScenarioContext("EmailDeliveryAddress",emailDeliveryAddress);

                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Delivery_Details_Dashboard()), PropertyUtility.getMessage("Delivery_Details_Dashboard"));
                    break;
                case "see the cancel delivery warning message":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Cancel_Delivery()), PropertyUtility.getMessage("Message_Cancel_Delivery"));
                    break;
                case "see delivery has been cancelled message":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Cancel_Confirmation()), PropertyUtility.getMessage("Confirmation_Message_Delivery_Cancel"));
                    break;
                case "see Canceled trip message":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Cancel_Trip()), PropertyUtility.getMessage("Message_Cancel_Trip"));
                    break;
                case "see Get Estimate screen":
                    testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Label_Get_Estimate_Header(), "Get Estimate START OVER should be shown", "Get Estimate START OVER is shown", "Get Estimate START OVER is not shown");
                    //testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Label_Get_Estimate_Header()), PropertyUtility.getMessage("Get_Estimate_Header"));
                    break;
                case "see five future days including today":
                    Calendar calendar = Calendar.getInstance();
                    TimeZone fromTimeZone = calendar.getTimeZone();
                    TimeZone toTimeZone = TimeZone.getTimeZone("CST");
                    break;
                case "see Delivery cancellation failed message":
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Message_Delivery_Cancellation_Failed()), PropertyUtility.getMessage("Message_Delivery_Cancellation_Failed"));
                    break;
                case "Your delivery has been canceled message":
                    Thread.sleep(2000);
                    testStepVerify.isEquals(action.getText(Page_Partner_Delivery_List.Pop_Message_Delivery_Cancelled()), PropertyUtility.getMessage("Message_Delivery_Cancelled"));
                    break;
                case "see Service Level":
                    //String xpath ="//div[@class='service-level form-group']/div/p";
                    String Service_Name = action.getText(Page_Partner_Dashboard.Text_Service_Name());
                    cucumberContextManager.setScenarioContext("Selected_service", Service_Name);
                    testStepVerify.isElementDisplayed(Page_Partner_Dashboard.Information_Icon__Service_Level(), "Information icon for service level should be display", "Information icon for service level is display", "Information icon for service level is not display");
                    //testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Text_Service_Level_Edit()),"SERVICE LEVEL EDIT");
                    //testStepAssert.isElementTextEquals(Page_Partner_Dashboard.Text_Service_Level(),"Service Level","Service Level configuration is present","Service Level configuration is not present");
                    break;
                case "see No service selected":
                    testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Text_No_Service()), "No service selected.");
                    break;
                case "see the service name":
                    String Service_Name1 = (String) cucumberContextManager.getScenarioContext("Selected_service");
                    action.JavaScriptScrolldown();
                    String Display_Service_name = action.getText(Page_Partner_Delivery_List.Text_Selected_Service());
                    testStepVerify.isEquals(Display_Service_name, Service_Name1);
                    break;
                case "see Delivery Cost: N/A":
                    String Display_Delivery_Cost = "Delivery Cost: N/A";
                    String NA_Delivery_Cost = action.getText(Page_Partner_Dashboard.Label_Delivery_Cost());
                    testStepVerify.isEquals(NA_Delivery_Cost,Display_Delivery_Cost);
                    break;
                case "see Delivery Cost: N/A on Delivery Details screen":
                    String Shown_Delivery_Cost = "Delivery Cost\nN/A";
                    String NA_Delivery_cost = action.getText(Page_Partner_Delivery.Label_Delivery_Cost());
                    testStepVerify.isEquals(NA_Delivery_cost,Shown_Delivery_Cost);
                    break;
                case "see Ratings submitted successfully message":
                    testStepVerify.isEquals(action.getText(Page_DriverRating.Text_Rating_Submitted_Successfully()),PropertyUtility.getMessage("Ratings_submitted_successfully"));
                    break;
                default:
                    break;
                case "see the extra recipient numbers added on delivery details":
                    String expected_Recipient1PhoneNumber,displayed_Recipient1PhoneNumber,expected_Recipient2PhoneNumber,expected_Recipient3PhoneNumber, displayed_Recipient2PhoneNumber, displayed_Recipient3PhoneNumber;
                    String numRecipients=(String) cucumberContextManager.getScenarioContext("num_Recipients");
                    switch(numRecipients)
                    {
                        case "one time":
                            expected_Recipient1PhoneNumber= (String) cucumberContextManager.getScenarioContext("SMS_RecipientNo1");
                            displayed_Recipient1PhoneNumber = (action.getText(Page_Partner_Delivery.PhoneNo_Recipient1())).trim();
                            displayed_Recipient1PhoneNumber = displayed_Recipient1PhoneNumber.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
                            testStepAssert.isEquals(displayed_Recipient1PhoneNumber, expected_Recipient1PhoneNumber, "Recipient number added should match expected", "Phone number is correctly displayed on partner portal", "Incorrect number displayed on partner portal");
                            log("Added SMS Recipient should be displayed on delivery details page","Added one SMS Recipient has been displayed on delivery details page.",false);
                            break;
                        case "two times":
                            expected_Recipient1PhoneNumber = (String) cucumberContextManager.getScenarioContext("SMS_RecipientNo1");
                            displayed_Recipient1PhoneNumber = (action.getText(Page_Partner_Delivery.PhoneNo_Recipient1())).trim();
                            displayed_Recipient1PhoneNumber = displayed_Recipient1PhoneNumber.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
                            testStepAssert.isEquals(displayed_Recipient1PhoneNumber, expected_Recipient1PhoneNumber, "Recipient number added should match expected", "Phone number is correctly displayed on partner portal", "Incorrect number displayed on partner portal");
                            expected_Recipient2PhoneNumber = (String) cucumberContextManager.getScenarioContext("SMS_RecipientNo2");
                            displayed_Recipient2PhoneNumber = action.getText(Page_Partner_Delivery.PhoneNo_Recipient2());
                            displayed_Recipient2PhoneNumber = displayed_Recipient2PhoneNumber.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
                            testStepAssert.isEquals(displayed_Recipient2PhoneNumber, expected_Recipient2PhoneNumber, "Recipient number added should match expected", "Phone number is correctly displayed on partner portal", "Incorrect number displayed on partner portal");
                            log("Added two SMS Recipients should be displayed on delivery details page","Added two SMS Recipients have been displayed on delivery details page.",false);
                            break;
                        case "three times":
                            expected_Recipient1PhoneNumber = (String) cucumberContextManager.getScenarioContext("SMS_RecipientNo1");
                            displayed_Recipient1PhoneNumber = (action.getText(Page_Partner_Delivery.PhoneNo_Recipient1())).trim();
                            displayed_Recipient1PhoneNumber = displayed_Recipient1PhoneNumber.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
                            testStepAssert.isEquals(displayed_Recipient1PhoneNumber, expected_Recipient1PhoneNumber, "Recipient number added should match expected", "Phone number is correctly displayed on partner portal", "Incorrect number displayed on partner portal");
                            expected_Recipient2PhoneNumber = (String) cucumberContextManager.getScenarioContext("SMS_RecipientNo2");
                            displayed_Recipient2PhoneNumber = action.getText(Page_Partner_Delivery.PhoneNo_Recipient2());
                            displayed_Recipient2PhoneNumber = displayed_Recipient2PhoneNumber.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
                            testStepAssert.isEquals(displayed_Recipient2PhoneNumber, expected_Recipient2PhoneNumber, "Recipient number added should match expected", "Phone number is correctly displayed on partner portal", "Incorrect number displayed on partner portal");
                            expected_Recipient3PhoneNumber = (String) cucumberContextManager.getScenarioContext("SMS_RecipientNo3");
                            displayed_Recipient3PhoneNumber = action.getText(Page_Partner_Delivery.PhoneNo_Recipient3());
                            displayed_Recipient3PhoneNumber = displayed_Recipient3PhoneNumber.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");
                            testStepAssert.isEquals(displayed_Recipient3PhoneNumber, expected_Recipient3PhoneNumber, "Recipient number added should match expected", "Phone number is correctly displayed on partner portal", "Incorrect number displayed on partner portal");
                            log("Added three SMS Recipients should be displayed on delivery details page","Added three SMS Recipients have been displayed on delivery details page.",false);

                            break;
                        default:
                            error("Step should be successful", "Error performing step",true);
                    }
                    break;
            }
        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step , I Should "+ str,
                    true);
        }
    }

    @And("^I should logout from Partner Portal$")
    public void i_should_logout_from_partner_portal() throws Throwable {
        try{
        action.click(Page_Partner_Done.Dropdown_Setting());
        //Thread.sleep(5000);
        utility.PartnerLogout();

        testStepAssert.isElementDisplayed(action.getElementByXPath("//button[@id='login']"), "SIGN IN button should be displayed on partner portal", "SIGN IN button is displayed on partner portal", "SIGN IN button is not displayed on partner portal");
        //action.getElementByXPath(Page_Partner_Login.Button_Sign_In())
        log("I should be logged out from Partner Portal ","I clicked ", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should be navigated to Login screen$")
    public void i_should_be_navigated_to_login_screen() throws Throwable {
        try{
            testStepAssert.isElementDisplayed(action.getElementByXPath("//button[@id='login']"), "SIGN IN button should be displayed on partner portal", "SIGN IN button is displayed on partner portal", "SIGN IN button is not displayed on partner portal");
            //action.getElementByXPath(Page_Partner_Login.Button_Sign_In())
            log("I should be on Partner Portal login page","I should be on Partner Portal login page.", true);
        }
        catch (Exception e){
            logger.error("Error performing step navigate to login screen", ExceptionUtils.getStackTrace(e));
            error("I should be on Partner Portal login page", "I am not on Partner Portal login page",
                    true);
        }
    }

    @And("^I Clear the browser local storage and refresh the Page$")
    public void i_clear_the_browser_local_storage_and_refresh_the_page() throws Throwable {
        try{
            JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
            js.executeScript(String.format("window.localStorage.clear();"));
            Thread.sleep(5000);
            SetupManager.getDriver().navigate().refresh();
            log("I Clear the browser local storage and refresh the Page","I have cleared the browser local storage and refresh the Page", false);

        }
        catch (Exception e){
            logger.error("Error performing step clearing local storag", ExceptionUtils.getStackTrace(e));
            error("Browser local storage should get clear.", "Browser local storage is not get cleared.",
                    true);
        }
    }

    @And("^I click on Filter and select check/unchecked all checkbox$")
    public void i_click_on_filter() throws Throwable {
        //throw new PendingException();
        try{
        action.click(Page_Partner_Done.Dropdown_Filter());
        action.click(Page_Partner_Done.Checkbox_Check_UnCheck_All());
        log("I click on Filter and select check/unchecked all checkbox","I have clicked on Filter and select check/unchecked all checkbox", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And("^I click on Apply button on Filter$")
    public void i_click_on_apply_button_on_filter() throws Throwable {
        try{
        //throw new PendingException();
        action.click(Page_Partner_Done.Button_Apply());

        log("I click on Apply button on Filter","I have clicked on Apply button on Filter", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should not able to see Filter screen$")
    public void i_should_not_be_able_to_see_filter_screen() throws Throwable {
        //throw new PendingException();
        testStepAssert.isNotElementDisplayed(Page_Partner_Done.Button_Apply(),"Filter window should close and Apply button shouldn't be shown","Filter window is close and Apply button is not shown","Filter window is not close and Apply button is shown");
    }

    @Then("^I open the link to provide driver rating$")
    public void i_open_the_link_to_provide_driver_rating() throws Throwable {
        try{
            utility.NavigateDriverRatingWebLink();
            log("I open the link to provide driver rating.","I have opened the link to provide driver rating.", false);

        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Link should get open for providing the driver rating", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I check details on link page open for driver rating$")
    public void i_check_details_on_link_page_open_for_driver_rating() throws Throwable {
        try {
            String BungiiType = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");

            testStepVerify.isElementDisplayed(Page_DriverRating.Label_Delivery_Status(), "Delivery status should be display", "Delivery status is display", "Delivery status is not display");
            testStepVerify.isElementDisplayed(Page_DriverRating.Text_Successfully_Completed(), "Successfully Completed! text should be display", "Successfully Completed! text is display", "Successfully Completed! text is not display");
            testStepVerify.isElementTextEquals(Page_DriverRating.Text_Sucessfully_Completed_Para(), PropertyUtility.getMessage("Driver_Rating_Successfully_Completed_Text"));
            String Driver_Name = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            testStepVerify.isElementDisplayed(Page_DriverRating.Label_Driver_Name(Driver_Name), "Driver1 name should be display", "Driver1 name is display", "Driver1 name is not display");
            testStepVerify.isElementDisplayed(Page_DriverRating.Image_All_Stars(), "All rating stars for driver1 should be display", "All rating stars for driver1 is display", "All rating stars for driver1 is not display");


            if(BungiiType.equalsIgnoreCase("Solo")){
                WebElement Image_All_Ok = Page_DriverRating.Image_All_Ok_Solo();
                String expectedImage = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"), PropertyUtility.getImageLocations("ALL_OK"));
                String expectedImageSource = utility.encodeImage(expectedImage);
                String actualImageSource = Image_All_Ok.getAttribute("src");

                testStepVerify.isEquals(actualImageSource, expectedImageSource);
            }
            if(BungiiType.equalsIgnoreCase("Duo")){
                String Driver_Name2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
                testStepVerify.isElementDisplayed(Page_DriverRating.Label_Driver_Name(Driver_Name2), "Driver2 name should be display", "Driver2 name is display", "Driver2 name is not display");
                testStepVerify.isElementDisplayed(Page_DriverRating.Image_All_Stars(), "All rating stars for driver2 should be display", "All rating stars for driver 2 is display", "All rating stars for driver 2 is not display");
                WebElement Image_All_Ok = Page_DriverRating.Image_All_Ok_Duo();
                String expectedImage = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"), PropertyUtility.getImageLocations("ALL_OK"));
                String expectedImageSource = utility.encodeImage(expectedImage);
                String actualImageSource = Image_All_Ok.getAttribute("src");

                testStepVerify.isEquals(actualImageSource, expectedImageSource);
            }

        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("I check that the details on link page open for driver rating", "Error performing step,Please check logs for more details",
                    true);

        }

    }

    @Then("^I check that rating stars are not shown on driver rating page once the ratings are submitted for the delivery$")
    public void i_check_that_rating_stars_are_not_shown_on_driver_rating_page_once_the_ratings_are_submitted_for_that_delivery() throws Throwable {
        try{
                testStepVerify.isElementNotDisplayed(Page_DriverRating.Image_All_Stars(true),"All rating stars should not be display","All rating stars is not display", "All rating stars are display");
                testStepVerify.isElementNotDisplayed(Page_DriverRating.Button_Submit(true),"Submit button should not be shown.","Submit button is not shown.","Submit button is shown.");

        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("I check that rating stars are not shown on driver rating page once the ratings are submitted for the delivery", "Error performing step,Please check logs for more details",
                    true);

        }
    }

    @Then("^I change the rating to \"([^\"]*)\" stars for \"([^\"]*)\"$")
    public void i_change_the_rating_to_something_stars_for_something(String Rating, String DriverType) throws Throwable {
        try{
            int driverRating = Integer.parseInt(Rating);
            switch (DriverType){
                case "Driver1":
                    action.JavaScriptClick(Page_DriverRating.Image_Stars(driverRating));
                    cucumberContextManager.setScenarioContext("Driver_Rating",String.valueOf(driverRating));
                    break;
                case "Driver2":
                    action.JavaScriptClick(Page_DriverRating.Image_Stars2(driverRating));
                    cucumberContextManager.setScenarioContext("Driver2_Rating",String.valueOf(driverRating));
                    break;
            }
            log("I should able to change the rating to " +Rating+ " stars for " +DriverType,"I have changed the rating to " +Rating+ " stars for " +DriverType,false);
        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Driver rating should get change to "+Rating, "Driver rating is not change to "+Rating,
                    true);

        }
    }

    @And("^I click on \"([^\"]*)\" button on Driver Rating Page$")
    public void i_click_on_something_button_on_driver_rating_page(String buttonType) throws Throwable {
        try{
            action.click(Page_DriverRating.Button_Submit());
            log("I should able to click on " +buttonType+ " on Driver Rating Page.","I am able to click on " +buttonType+ " on Driver Rating Page.", false);

        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("I should able to click "+buttonType+" button", "I am not able to click "+buttonType+" button",
                    true);

        }
    }

    @Then("^Submitted driver ratings are saved in the database$")
    public void submitted_driver_ratings_are_saved_in_the_database() throws Throwable {
        try{
            String Bungii_Type= (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
            String PickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String PickupId=dbUtility.getPickupId(PickupRef);

            List <HashMap<String,Object>>dbDriverRating= dbUtility.getDriverRating(PickupId);

            if(Bungii_Type.equalsIgnoreCase("Solo")||Bungii_Type.equalsIgnoreCase("Solo Scheduled")) {
                String Driver1Rating = dbDriverRating.get(0).toString();
                testStepVerify.isEquals((String) cucumberContextManager.getScenarioContext("Driver_Rating"),Driver1Rating);

            }else if(Bungii_Type.equalsIgnoreCase("Duo")) {
                String Driver1Rating = dbDriverRating.get(0).toString();
                String Driver2Rating = dbDriverRating.get(1).toString();
                testStepVerify.isEquals((String) cucumberContextManager.getScenarioContext("Driver_Rating"),Driver1Rating);
                testStepVerify.isEquals((String) cucumberContextManager.getScenarioContext("Driver2_Rating"),Driver2Rating);
            }

            log("Submitted driver ratings should be saved in the database.","Submitted driver ratings is saved in the database.", false);

        }catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Correct submitted driver rating value should be shown in database", "Correct submitted driver rating value is not shown in database",
                    true);

        }
    }

    @Then("^Default driver ratings are saved in the database$")
    public void Default_driver_ratings_are_saved_in_the_database() throws Throwable {
        try {
            String pickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String pickupId = dbUtility.getPickupId(pickupRef);

            List<HashMap<String, Object>> dbDriverRating = dbUtility.getDriverRating(pickupId);

            String driverRating = dbDriverRating.get(0).toString();
            testStepAssert.isEquals(driverRating, "{DriverRating=5}", "Default driver rating should be stored", "Default driver rating is stored", "Default driver rating is stored");

        } catch (Exception e) {

            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Correct default driver rating value should be stored in database", "Correct default driver rating value is not stored in database",
                    true);

        }
    }
    @And("^I check in the db the number of timeslots available \"([^\"]*)\"$")
    public void i_check_in_the_db_the_number_of_timeslots_available_something(String duration) throws Throwable {
       try{
           String scheduledTime= (String) cucumberContextManager.getScenarioContext("Scheduled_Time");
           String bestBuyAddress=PropertyUtility.getDataProperties("partner.bestbuy.address1");
           String time=scheduledTime.substring(8,13);
           Date now = new Date();
           LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           String date = String.valueOf(localDate);
           switch (duration){
               case "before schedule for best buy":
                   String timeSlotsUsed = dbUtility.getSlotUsedCount(date,time,bestBuyAddress);
                   cucumberContextManager.setScenarioContext("TIME_SLOTS_BEFORE_SCHEDULE",timeSlotsUsed);
                   break;

               case "after schedule for best buy":
                   String beforeSchedule= (String) cucumberContextManager.getScenarioContext("TIME_SLOTS_BEFORE_SCHEDULE");
                   int timeSlotsBefore = Integer.parseInt(beforeSchedule);
                   String timeSlotsUsedAfterSchedule = dbUtility.getSlotUsedCount(date,time,bestBuyAddress);
                   int timeSlotsAfter = Integer.parseInt(timeSlotsUsedAfterSchedule);
                   cucumberContextManager.setScenarioContext("TIME_SLOTS_AFTER_SCHEDULE",timeSlotsUsedAfterSchedule);
                   testStepAssert.isTrue(timeSlotsAfter == (timeSlotsBefore+1),
                               "Time slot used count should increase after schedule","Time slot used count is not increased after schedule");
                   break;

               case "after schedule for duo for best buy":
                   String beforeScheduleDuo= (String) cucumberContextManager.getScenarioContext("TIME_SLOTS_BEFORE_SCHEDULE");
                   int timeSlotsBeforeDuo = Integer.parseInt(beforeScheduleDuo);
                   String timeSlotsUsedAfterScheduleDuo = dbUtility.getSlotUsedCount(date,time,bestBuyAddress);
                   int timeSlotsAfterDuo = Integer.parseInt(timeSlotsUsedAfterScheduleDuo);
                   cucumberContextManager.setScenarioContext("TIME_SLOTS_AFTER_SCHEDULE",timeSlotsUsedAfterScheduleDuo);
                   testStepAssert.isTrue(timeSlotsAfterDuo ==(timeSlotsBeforeDuo +2),
                               "Time slot used count should increase after schedule","Time slot used count is not increased after schedule");
                   break;

               case "after changing bungii type from solo to duo":
                   String afterSchedule = (String) cucumberContextManager.getScenarioContext("TIME_SLOTS_AFTER_SCHEDULE");
                   int timeSlotsAfterSoloToDuo = Integer.parseInt(afterSchedule);
                   String timeSlotsUsedAfterChangingBungiiTypeSoloToDuo = dbUtility.getSlotUsedCount(date,time,bestBuyAddress);
                   int soloToDuoCount= Integer.parseInt(timeSlotsUsedAfterChangingBungiiTypeSoloToDuo);
                   testStepAssert.isTrue(soloToDuoCount == (timeSlotsAfterSoloToDuo+1),
                           "Time slot used count should increase after change from solo to duo","Time slot used count remains unchanged");
                  cucumberContextManager.setScenarioContext("TIME_SLOTS_AFTER_CONVERSION_SOLO_TO_DUO",timeSlotsUsedAfterChangingBungiiTypeSoloToDuo);
                   break;

               case "after changing bungii type from duo to solo":
                   String afterScheduleDuoToSolo = (String) cucumberContextManager.getScenarioContext("TIME_SLOTS_AFTER_SCHEDULE");
                   int timeSlotsAfterDuoToSolo = Integer.parseInt(afterScheduleDuoToSolo);
                   String timeSlotsUsedAfterChangingBungiiTypeDuoToSolo = dbUtility.getSlotUsedCount(date,time,bestBuyAddress);
                   int duoToSoloCount= Integer.parseInt(timeSlotsUsedAfterChangingBungiiTypeDuoToSolo);
                   testStepAssert.isTrue(duoToSoloCount == (timeSlotsAfterDuoToSolo-1),
                           "Time slot used count should decrease after change from solo to duo","Time slot used count remains unchanged");
                   cucumberContextManager.setScenarioContext("TIME_SLOTS_AFTER_SCHEDULE_DUO_TO_SOLO",timeSlotsUsedAfterChangingBungiiTypeDuoToSolo);
                   break;

               case "after cancelling duo trip":
                   String afterScheduleSoloToDuo = (String) cucumberContextManager.getScenarioContext("TIME_SLOTS_AFTER_CONVERSION_SOLO_TO_DUO");
                   int timeSlotsAfterScheduleSoloToDuo = Integer.parseInt(afterScheduleSoloToDuo);
                   String timeSlotsUsedAfterCancellingDuoTrip = dbUtility.getSlotUsedCount(date,time,bestBuyAddress);
                   int cancellingDuoTrip= Integer.parseInt(timeSlotsUsedAfterCancellingDuoTrip);
                   testStepAssert.isTrue(cancellingDuoTrip == (timeSlotsAfterScheduleSoloToDuo-2),
                           "Time slot used count should decrease by two after cancelling duo trip","Time slot used count remains unchanged");
                   break;

               case "after cancelling solo trip":
                   String afterScheduleTrip = (String) cucumberContextManager.getScenarioContext("TIME_SLOTS_AFTER_SCHEDULE");
                   int timeSlotsAfterSchedule = Integer.parseInt(afterScheduleTrip);
                   String timeSlotsUsedAfterCancellingSoloTrip = dbUtility.getSlotUsedCount(date,time,bestBuyAddress);
                   int cancellingSoloTrip= Integer.parseInt(timeSlotsUsedAfterCancellingSoloTrip);
                   testStepAssert.isTrue(cancellingSoloTrip == (timeSlotsAfterSchedule-1),
                           "Time slot used count should decrease by one after cancelling solo trip","Time slot used count remains unchanged");

                   break;

               case "after changing date and time":
                   String AfterSchedule = (String) cucumberContextManager.getScenarioContext("TIME_SLOTS_AFTER_SCHEDULE");
                   int timeSlotAfterSchedule = Integer.parseInt(AfterSchedule);
                   String timeSlotsUsedAfterChangingTime = dbUtility.getSlotUsedCount(date,time,bestBuyAddress);
                   int changingTime= Integer.parseInt(timeSlotsUsedAfterChangingTime);
                   testStepAssert.isTrue(changingTime != timeSlotAfterSchedule,
                           "Time slot used count should change after admin changes date and time","Time slot used count remains unchanged");
                   break;

               case "after changing address":
                   String afterSchedule1 = (String) cucumberContextManager.getScenarioContext("TIME_SLOTS_AFTER_SCHEDULE_DUO_TO_SOLO");
                   int timeSlotAfterSchedule1 = Integer.parseInt(afterSchedule1);
                   String timeSlotsUsedAfterChangingAddress = dbUtility.getSlotUsedCount(date,time,bestBuyAddress);
                   int changingAddress= Integer.parseInt(timeSlotsUsedAfterChangingAddress);
                   testStepAssert.isTrue(changingAddress != timeSlotAfterSchedule1,
                           "Time slot used count should change after admin changes address","Time slot used count remains unchanged");
                   break;

               case "before schedule for mrfm":
                   String mrfmAddress=PropertyUtility.getDataProperties("partner.mrfm.address1");
                   String timeSlotMrfm = dbUtility.getSlotUsedCount(date,time,mrfmAddress);
                   cucumberContextManager.setScenarioContext("TIME_SLOT_BEFORE_SCHEDULE_MRFM",timeSlotMrfm);
                   break;

               case "after schedule for mrfm":
                   String beforeScheduleMrfm= (String) cucumberContextManager.getScenarioContext("TIME_SLOT_BEFORE_SCHEDULE_MRFM");
                   int timeSlotsBeforeMrfm = Integer.parseInt(beforeScheduleMrfm);
                   String mrfmAddress1=PropertyUtility.getDataProperties("partner.mrfm.address1");
                   String timeSlotsUsedAfterScheduleMrfm = dbUtility.getSlotUsedCount(date,time,mrfmAddress1);
                   int timeSlotsAfterMrfm = Integer.parseInt(timeSlotsUsedAfterScheduleMrfm);
                   testStepAssert.isTrue(timeSlotsBeforeMrfm == timeSlotsAfterMrfm,
                           "Time slot used count should remain unchanged","Time slot used count changes");
                   break;
           }
           log("I should be able to check the number of slots used","I am able to check the number of slots used",false);
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("The number of slots available should be correctly received.", "The number of slots available is not correctly received.",
                   true);

       }
    }
    @When("^I check in the db the number of timeslots available \"([^\"]*)\" new portal$")
    public void i_check_in_the_db_the_number_of_timeslots_available_something_new_portal(String duration) throws Throwable {
       try{
           String scheduledTime= (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
           String time=scheduledTime.substring(8,13);
           Date now = new Date();
           LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           String date = String.valueOf(localDate);
           switch (duration){
               case "for bestbuy first address":
                   String bestBuyStore1=PropertyUtility.getDataProperties("partner.new.bestbuy.store1");
                   String timeSlotsUsedBestBuyStore1 = dbUtility.getSlotUsedCountByStoreName(date,time,bestBuyStore1);
                   int timeSlots = Integer.parseInt(timeSlotsUsedBestBuyStore1);
                   testStepAssert.isTrue(timeSlots == 1,
                           "Time slot used count should be one for solo schedule for the first address","Time slot used count is not one");
                   break;

               case "for bestbuy second address":
                   String bestBuyStore2=PropertyUtility.getDataProperties("partner.new.bestbuy.store2");
                   String timeSlotsUsedBestBuyStore2 = dbUtility.getSlotUsedCountByStoreName(date,time,bestBuyStore2);
                   int timeSlots2 = Integer.parseInt(timeSlotsUsedBestBuyStore2);
                   testStepAssert.isTrue(timeSlots2 == 0,
                           "Time slot used count should not be affected when a delivery is placed in different address for partner portal","Time slot used count is affected when a delivery is placed in different address for partner portal");

                   break;
           }
           log("I should be able to check the number of slots used","I am able to check the number of slots used",false);
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("The number of slots available should be correctly received.", "The number of slots available is not correctly received.",
                   true);

       }
    }
    @And("^I check if partner trips are already present$")
    public void i_check_if_partner_trips_are_already_present() throws Throwable {
        try{
            String partnerPortal = PropertyUtility.getDataProperties("partner.baltimore.name");
            List<WebElement> listOfScheduledTrip=Page_Partner_Delivery.Text_PartnerName();
                if(listOfScheduledTrip.size()>0){
                    logger.error("The test case will fail because trips are already present for "+partnerPortal+" portal.");
                }
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I check if \"([^\"]*)\" field accepts only integer values$")

    public void i_check_if_something_field_accepts_only_integer_values(String type) throws Throwable {
        try{
            String alphabetValue= PropertyUtility.getDataProperties("alphabet.value");
            String specialCharacterValues= PropertyUtility.getDataProperties("specialCharacters.value");
            String integerValues=PropertyUtility.getDataProperties("integer.value");
            switch (type)
            {
                case "Order number":
                    action.clearSendKeys(Page_Partner_Login.Input_OrderNo(), alphabetValue);
                    testStepVerify.isEquals(action.getAttributeValue(Page_Partner_Login.Input_OrderNo()), "");
                    action.clearSendKeys(Page_Partner_Login.Input_OrderNo(),specialCharacterValues);
                    testStepVerify.isEquals(action.getAttributeValue(Page_Partner_Login.Input_OrderNo()), "");
                    action.clearSendKeys(Page_Partner_Login.Input_OrderNo(), integerValues);
                    testStepVerify.isEquals(action.getAttributeValue(Page_Partner_Login.Input_OrderNo()), integerValues);
                    break;

                case "Employee number":
                    action.clearSendKeys(Page_Partner_Login.Input_EmployeeNo(), alphabetValue);
                    testStepVerify.isEquals(action.getAttributeValue(Page_Partner_Login.Input_EmployeeNo()), "");
                    action.clearSendKeys(Page_Partner_Login.Input_EmployeeNo(), specialCharacterValues);
                    testStepVerify.isEquals(action.getAttributeValue(Page_Partner_Login.Input_EmployeeNo()), "");
                    action.clearSendKeys(Page_Partner_Login.Input_EmployeeNo(), integerValues);
                    testStepVerify.isEquals(action.getAttributeValue(Page_Partner_Login.Input_EmployeeNo()), integerValues);
                    break;
            }

            log("I should be able to check if "+type+" field accepts only integer value",
                    "I am able to check if "+type+ " field accepts only integer value" , false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("I check {string} section is displayed")
    public void iCheckSectionIsDisplayed(String section) {
        try{
            switch(section) {
                case "What's needed?":
                    action.waitUntilIsElementExistsAndDisplayed(Page_Partner_Dashboard.Section_WhatsNeeded(), (long) 5000);
                    boolean isWhatsNeedDisplayed= Page_Partner_Dashboard.Section_WhatsNeeded().isDisplayed();
                    testStepAssert.isTrue(isWhatsNeedDisplayed, "What's Needed section should be displayed", "What's Needed section is displayed", "What's Needed section is not displayed");
                    break;

                case "Custom Quotes":
                    action.waitUntilIsElementExistsAndDisplayed(Page_Partner_Dashboard.Section_CustomQuotes(), (long) 5000);
                    boolean isCustomQuoteDisplayed= Page_Partner_Dashboard.Section_CustomQuotes().isDisplayed();
                    testStepAssert.isTrue(isCustomQuoteDisplayed, "Customer Quote section should be displayed", "Customer Quote  section is displayed", "Customer Quote  section is not displayed");
                    break;
            }
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("I check {string} is not present")
    public void iCheckIsNotPresent(String message) {
        try{
            switch(message) {
                case "Disclaimer message":
                    testStepAssert.isFalse(action.isElementPresent(Page_Partner_Dashboard.Text_DisclaimerMessage(true)),
                            "Disclaimer message should not be present",
                            "Disclaimer message is not present",
                            "Disclaimer message is present");
                    break;
            }
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("I verify the UI for Space between {string} & {string} section is corectly displayed")
    public void iVerifyTheUIForSpaceBetweenSectionIsCorectlyDisplayed(String arg0, String arg1) {
        try{
            Point whatsNeededSection= Page_Partner_Dashboard.Section_WhatsNeeded().getLocation();
            Dimension whatsNeededSectionSize=Page_Partner_Dashboard.Section_WhatsNeeded().getSize();
            int whatsNeededSectionBottom= whatsNeededSection.getY() + whatsNeededSectionSize.getHeight();

            Point customQuoteSection= Page_Partner_Dashboard.Section_CustomQuotes().getLocation();
            int customQuoteSectionTop= customQuoteSection.getY();

            int spacing= customQuoteSectionTop -whatsNeededSectionBottom;
            String actualSpacing=Integer.toString(spacing);

            String expctedspacing = PropertyUtility.getDataProperties("spacing.between.whatsneeded.and.customquote.in.pixel");

            testStepAssert.isEquals(actualSpacing, expctedspacing,
                    "Sufficient space should be present in between What's Needed & Custom Quote",
                    "Sufficient space is present in between What's Needed & Custom Quote",
                    "Sufficient space is not present in between What's Needed & Custom Quote");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("I should see previously selected Date and time correctly")
    public void iShouldSeePreviouslySelectedDateAndTimeCorrectly() {
        try{
            String actualDate=action.getText(Page_Partner_Dashboard.Pickup_Date());
            String expectedDate=(String) cucumberContextManager.getScenarioContext("ScheduledDate");
            testStepAssert.isEquals(expectedDate, actualDate, "Updated Date should be displayed correctly", "Updated Date is displayed correctly", "Updated Date is not displayed correctly");

            String actualTime= action.getText(Page_Partner_Dashboard.Text_Pickup_Time());
            String expectedTime= (String) cucumberContextManager.getScenarioContext("ScheduledTime");
            testStepAssert.isEquals(expectedTime, actualTime, "Updated Time should be displayed correctly", "Updated Time is displayed correctly", "Updated Time is not displayed correctly");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}

