package com.bungii.web.stepdefinitions.partner;

import com.bungii.SetupManager;
import com.bungii.common.core.PageBase;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.stepdefinitions.admin.DashBoardSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryList;
import com.bungii.web.stepdefinitions.admin.Admin_BusinessUsersSteps;
import com.bungii.web.stepdefinitions.admin.Admin_TripsSteps;
import com.bungii.web.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.*;
import static com.bungii.web.utilityfunctions.DbUtility.getActualPrice;
import static com.bungii.web.utilityfunctions.DbUtility.getBungiiRate;

public class Partner_trips extends DriverBase {
    private static LogUtility logger = new LogUtility(DashBoardSteps.class);
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    Partner_DeliveryList Page_Partner_Delivery_List = new Partner_DeliveryList();
    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();

    Admin_TripDetailsPage Page_Admin_Trips_Details = new Admin_TripDetailsPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    com.bungii.web.utilityfunctions.DbUtility dbUtility = new DbUtility();
    com.bungii.web.utilityfunctions.GeneralUtility webUtility = new com.bungii.web.utilityfunctions.GeneralUtility();

    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();
    Admin_CustomerPage admin_CustomerPage = new Admin_CustomerPage();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_LiveTripsPage admin_LiveTripsPage = new Admin_LiveTripsPage();
    Admin_ScheduledTripsPage admin_ScheduledTripsPage = new Admin_ScheduledTripsPage();
    Admin_TripDetailsPage admin_TripDetailsPage = new Admin_TripDetailsPage();
    Admin_EditScheduledBungiiPage admin_EditScheduledBungiiPage = new Admin_EditScheduledBungiiPage();
    Admin_BusinessUsersSteps admin_businessUsersSteps = new Admin_BusinessUsersSteps();
    //ActionManager action = new ActionManager();
    //private static LogUtility logger = new LogUtility(Admin_TripsSteps.class);
    //com.bungii.web.utilityfunctions.GeneralUtility utility = new com.bungii.web.utilityfunctions.GeneralUtility();

    @When("^I request for \"([^\"]*)\" Bungii trip in partner portal$")
    public void i_request_something_bungii_trip_in_partner_portal(String Type, DataTable data) throws InterruptedException {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

        String Pickup_Address = dataMap.get("Pickup_Address");
        String Delivery_Address = dataMap.get("Delivery_Address");
        cucumberContextManager.setScenarioContext("Delivery_Address", Delivery_Address);
        String Load_Unload = dataMap.get("Load_Unload_Time");

        //int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;
        int numberOf_Driver = dataMap.get("Driver").trim().equalsIgnoreCase("duo") ? 2 :1;

        ///cucumberContextManager.setScenarioContext("GEOFENCE", geofence);


        switch (Type)
        {
            case "Solo":
                //action.click(Page_Partner_Dashboard.Partner_Solo());

                action.click(Page_Partner_Dashboard.Button_Pickup_Edit());


               action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(),Pickup_Address+ Keys.TAB);
               action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
               // Thread.sleep(2000);
                action.click(Page_Partner_Dashboard.List_Pickup_Address());

                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(),Delivery_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                //Thread.sleep(1000);
                action.click(Page_Partner_Dashboard.List_Delivery_Address());

                Thread.sleep(5000);

                action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                switch (Load_Unload) {
                    case "15 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                        break;
                    case "30 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                        break;
                    case "45 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                        break;
                    case "60 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                        break;
                    case "75 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                        break;
                    case "90+ minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                        break;
                    default:break;
                }
            break;
            case "Duo":
                action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                action.click(Page_Partner_Dashboard.Button_Pickup_Edit());

                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(),Pickup_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                Thread.sleep(2000);
                action.click(Page_Partner_Dashboard.List_Pickup_Address());

                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(),Delivery_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                Thread.sleep(2000);
                action.click(Page_Partner_Dashboard.List_Delivery_Address());

                action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                switch (Load_Unload) {
                    case "15 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                        break;
                    case "30 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                        break;
                    case "45 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                        break;
                    case "60 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                        break;
                    case "75 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                        break;
                    case "90+ minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                        break;
                    default:break;
                }
             break;
            default: break;


        }
            log("I request for "+Type+" Bungii trip in partner portal","I have requested for "+Type+" Bungii delivery in partner portal", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting pickup and drop off address.", true);
        }

    }

    @When("^I clear the existing pickup address details$")
    public void i_clear_the_existing_pickup_address_details(){
try{
        action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
        action.click(Page_Partner_Dashboard.Button_PickupClear());
        log("I clear the existing pickup address details","I have cleared the existing pickup address details", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

       @When("^I click on Pickup date$")
    public  void i_click_on_pickup_date(){
try{
        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Date());
           log("I click on Pickup date","I have clicked on Pickup date", false);
       } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
       }

    @Then("^I should see five future days including today$")
    public void i_should_see_five_future_days_including_today() {

        try{
        testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Pickup_Date_Today(),"Today date should be display","Today date is display.","Today day is not displayed.");
        testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Pickup_date_Today_1(),"Second day should be display","Second day is display.","Second day is not displayed.");
        testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Pickup_date_Today_2(),"Third day should be display","Third day is display.","Third day is not displayed.");
        testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Pickup_date_Today_3(),"Fourth day should be display","Fourth day is display.","Fourth day is not displayed.");
        testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Pickup_date_Today_4(),"Fifth day should be display","Fifth day is display.","Fifth day is not displayed.");

        action.click(Page_Partner_Dashboard.Pickup_date_Today_1());
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }



    @And("^I select Pickup Date and Pickup Time$")
    public  void i_select_pickupdate_time(DataTable data) throws Throwable {

        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String PickupDate =dataMap.get("PickUp_Date");
        String PickUpTime =dataMap.get("PickUp_Time");
        String strTime = "";

        if (PickupDate.equalsIgnoreCase("NEXT_POSSIBLE")) {
            strTime = enterTime(PickUpTime);
            strTime=strTime.replace("am","AM").replace("pm","PM");
        }

        cucumberContextManager.setScenarioContext("ScheduledDate",strTime);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And("^I select Pickup Date and Pickup Time on partner portal$")
    public  void i_select_pickupdate_time_on_partner_portal(DataTable data) throws Throwable {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String PickupDate =dataMap.get("PickUp_Date");
        String PickUpTime =dataMap.get("PickUp_Time");
        String strTime = "";


        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Date());

        switch(PickupDate){
            case "Today":
                action.click(Page_Partner_Dashboard.Pickup_Date_Today());
                strTime = action.getText(Page_Partner_Dashboard.Pickup_Date());
                break;
            case "Today+1":
                action.click(Page_Partner_Dashboard.Pickup_date_Tomorrow());
                strTime = action.getAttributeValue(Page_Partner_Dashboard.Pickup_Date());
                break;
            case "Today+2":
                action.click(Page_Partner_Dashboard.Pickup_date_Today_2());
                strTime = action.getText(Page_Partner_Dashboard.Pickup_Date());
                break;
            case "Today+3":
                action.click(Page_Partner_Dashboard.Pickup_date_Today_3());
                strTime = action.getText(Page_Partner_Dashboard.Pickup_Date());
                break;
            case "Today+4":
                action.click(Page_Partner_Dashboard.Pickup_date_Today_4());
                strTime = action.getText(Page_Partner_Dashboard.Pickup_Date());
                break;
            default:break;

        }
        cucumberContextManager.setScenarioContext("ScheduledDate",strTime);
        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Time());

        if(!PickUpTime.equalsIgnoreCase("")) {
            Thread.sleep(2000);
            action.getElementByXPath("//li[contains(text(),'"+PickUpTime+"')]").click();
            cucumberContextManager.setScenarioContext("ScheduledTime",action.getAttributeValue(Page_Partner_Dashboard.Text_Pickup_Time()));
        }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting pickup and drop off address.", true);
        }

    }

    @And("^I select Next Possible Pickup Date and Pickup Time$")
    public  void i_select_next_possible_pickupdate_time(DataTable data) throws Throwable {

        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        //String PickupDate =dataMap.get("PickUp_Date");
        String Next_PickUpTime =dataMap.get("Trip_Time");
        String strTime = "";

        //if (Next_PickUpTime.equalsIgnoreCase("NEXT_POSSIBLE")) {
            strTime = enterTime(Next_PickUpTime);
            strTime=strTime.replace("am","AM").replace("pm","PM");

        cucumberContextManager.setScenarioContext("Scheduled_Time", strTime);
        cucumberContextManager.setScenarioContext("BUNGII_TIME",strTime);

        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I select Next Pickup Date and Pickup Time$")
    public  void i_select_next_days_pickupdate_time(DataTable data) throws Throwable {

        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        //String PickupDate =dataMap.get("PickUp_Date");
        String Next_PickUpTime =dataMap.get("Trip_Time");
        String strTime = "";

        //if (Next_PickUpTime.equalsIgnoreCase("NEXT_POSSIBLE")) {
        strTime = enterTime(Next_PickUpTime);
        strTime=strTime.replace("am","AM").replace("pm","PM");

        cucumberContextManager.setScenarioContext("Scheduled_Time", strTime);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @And("^I check correct price is shown for selected service$")
    public void i_check_correct_price_is_shown_for_selected_service(){
        try{
            String Alias_Name= (String) cucumberContextManager.getScenarioContext("Alias");

        String Selected_Service =(String) cucumberContextManager.getScenarioContext("Selected_service");
        String Trip_Type = (String) cucumberContextManager.getScenarioContext("Partner_Bungii_type");
        int Driver_Number=1;

        if(Trip_Type.equalsIgnoreCase("Duo")){
            Driver_Number=2;
        }

        String Display_Price = action.getText(Page_Partner_Dashboard.Label_DeliveryCostDelivery());
                //action.getElementByXPath("//h2[text()='Delivery Cost']//following::span/strong").getText();
        Display_Price = Display_Price.substring(1);

        //String Estimate_distance = dbUtility.getEstimateDistance(Alias_Name);
        String Estimate_distance = (String) cucumberContextManager.getScenarioContext("Distance_Estimate_Page");
        double Estimate_distance_value = Double.parseDouble(Estimate_distance);

        String Last_Tier_Milenge_Min_Range = dbUtility.getMaxMilengeValue(Alias_Name,Selected_Service);
        double Last_Tier_Milenge_Min_Range_value = Double.parseDouble(Last_Tier_Milenge_Min_Range);

        String Price="";
        if(Estimate_distance_value <= Last_Tier_Milenge_Min_Range_value) {
            Price = dbUtility.getServicePrice(Alias_Name, Driver_Number, Estimate_distance, Selected_Service);
        }
        else{
            Price = dbUtility.getServicePriceLastTier(Alias_Name, Driver_Number, Estimate_distance, Selected_Service);
        }

        String Estimated_Price = (String) cucumberContextManager.getScenarioContext("Price_Estimate_Page");

        testStepAssert.isEquals(Estimated_Price,Price,"Estimated Cost On Estimate screen : "+ Estimated_Price+" should be match calculated price",Estimated_Price+" matches calculated price",Estimated_Price+" does not match calculated price instead "+ Price + " is displayed");
        testStepAssert.isEquals(Display_Price,Price,"Estimated Cost On Delivery Detail screen :  "+Display_Price+" should be match calculated price",Display_Price+" matches calculated price",Display_Price+" does not match calculated price instead "+ Price + " is displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }

    @Then("^I should see \"([^\"]*)\"$")
    public void i_should_see_something_on_screen(String str){
        try{
        switch (str)
        {
            case "Estimated Cost":
                String Total_Estimated_Cost = action.getText(Page_Partner_Dashboard.Label_Estimated_Cost());
                //String Estimated_Cost_Label = Total_Estimated_Cost.substring(0,Total_Estimated_Cost.indexOf(':'));
                String[] splitTotalestimatedCost = Total_Estimated_Cost.split(": ");
                String Estimated_Cost_Label = splitTotalestimatedCost[0];
                String Estimated_Cost = splitTotalestimatedCost[1];
                cucumberContextManager.setScenarioContext("Estimated_Cost",Estimated_Cost);
                testStepVerify.isEquals(Estimated_Cost_Label, PropertyUtility.getMessage("Estimated_Cost_Label"));
                String estimatedDeliveryTime = Page_Partner_Dashboard.Label_EstDeliveryTime().getText();
                cucumberContextManager.setScenarioContext("ESTIMATED_DELIVERY_TIME",estimatedDeliveryTime);
                String estimatedDistance = action.getText(Page_Partner_Dashboard.Label_Distance()).replace(" miles","");//calculate values as per the displayed miles value to avoid mismatch in calculation
                cucumberContextManager.setScenarioContext("ESTIMATED_DISTANCE", estimatedDistance);
                break;
            case "see validation message for mandatory fields":
                String Blank_Pickup_Address = PropertyUtility.getMessage("Message_Blank_Pickup");
                String Blank_Delivery_Address = PropertyUtility.getMessage("Message_Blank_Delivery");
                String Blank_Load_Unload_Time = PropertyUtility.getMessage("Message_Blank_Load_Unload_Time");
                String Highlighted_Fields = PropertyUtility.getMessage("Message_Highlighted_Fileds");

                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Message_Blank_Pickup()),Blank_Pickup_Address);
                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Message_Blank_Delivery()),Blank_Delivery_Address);
                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Message_Blank_LoadUnload_Time()),Blank_Load_Unload_Time);
                testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Message_Highlighted_Fields()),Highlighted_Fields);
                break;
            case "Text Support Number and Email":
                testStepVerify.isElementDisplayed(Page_Partner_Dashboard.Text_TextSupport(),"Text Support text should be shown.","Text Support text is shown.","Text Support text is not shown.");
                testStepVerify.isElementTextEquals(Page_Partner_Dashboard.Number_TextSupport(),PropertyUtility.getDataProperties("support.phone.number"));
                testStepVerify.isElementDisplayed(Page_Partner_Dashboard.Text_EmailSupport(),"Email Support text should be shown.","Email Support text is shown.","Email Support text is not shown.");
                testStepVerify.isElementTextEquals(Page_Partner_Dashboard.Email_EmailSupport(),PropertyUtility.getDataProperties("support.email.address"));
                break;
            case "Admin Password Required":
                testStepVerify.isElementDisplayed(Page_Partner_Dashboard.Header_AdminPasswordRequired(),"Admin Password Required popup should be shown.","Admin Password Required popup is shown.","Admin Password Required popup not shown.");
                break;
            default: break;
        }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in viewing "+ str, true);
        }
    }

    @And("^I select the Scheduled Bungii from Delivery List$")
    public void i_select_scheduled_bungii_from_delivery_list(){
        try{
        String scheduled_time =(String) cucumberContextManager.getScenarioContext("Partner_Schedule_Time");;
        String customer =(String) cucumberContextManager.getScenarioContext("Customer_Name");
        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]", scheduled_time, customer);

        action.getElementByXPath(XPath).click();
        //action.click(Page_Partner_Delivery_List.Record1());
        log("I should able to select the Scheduled Bungii from Delivery List","Scheduled Bungii from Delivery List get selected.",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the change service level should be displayed on partner portal delivery details page$")
    public void the_change_service_level_should_be_displayed_on_partner_portal_delivery_details_page() throws Throwable {
        try{
            String changeServiceLevel = (String) cucumberContextManager.getScenarioContext("Change_service");
            String displayServiceLevel = action.getText(Page_Partner_Dashboard.Text_Service_Decription());

            if(changeServiceLevel.equalsIgnoreCase("White Glove"))
            {
                changeServiceLevel = PropertyUtility.getDataProperties("change.service.description");
            }

            testStepVerify.isEquals(changeServiceLevel,displayServiceLevel,"the change service level " +changeServiceLevel+ "should be same as service level display " +displayServiceLevel+ "on delivery details page of partner portal","the change service level " +changeServiceLevel+ " is not same as service level displayed " +displayServiceLevel+ "on delivery details page of partner portal");

        }
        catch(Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "The changed service level is not displayed on partner portal delivery details page",
                    true);
        }
    }

    @Then("^the price for the partner portal delivery shown as per the changed service level$")
    public void the_price_for_the_partner_portal_delivery_shown_as_per_the_changed_service_level() throws Throwable {
        try{
            String Alias_Name= (String) cucumberContextManager.getScenarioContext("Alias");
            String Change_Service =(String) cucumberContextManager.getScenarioContext("Change_service");
            String Trip_Type = (String) cucumberContextManager.getScenarioContext("Partner_Bungii_type");
            int Driver_Number=1;

            if(Trip_Type.equalsIgnoreCase("Duo")){
                Driver_Number=2;
            }

            String Display_Price = action.getText(Page_Partner_Dashboard.Text_Delivery_Cost());
            Display_Price = Display_Price.substring(1);

            String Estimate_distance = dbUtility.getEstimateDistance(Alias_Name);
            double Estimate_distance_value = Double.parseDouble(Estimate_distance);

            String Last_Tier_Milenge_Min_Range = dbUtility.getMaxMilengeValue(Alias_Name,Change_Service);
            double Last_Tier_Milenge_Min_Range_value = Double.parseDouble(Last_Tier_Milenge_Min_Range);

            String Price="";
            if(Estimate_distance_value <= Last_Tier_Milenge_Min_Range_value) {
                Price = dbUtility.getServicePrice(Alias_Name, Driver_Number, Estimate_distance, Change_Service);
            }
            else{
                Price = dbUtility.getServicePriceLastTier(Alias_Name, Driver_Number, Estimate_distance, Change_Service);
            }

            testStepVerify.isEquals(Display_Price,Price);

            log("The price for the delivery on partner portal should be shown as per the changed service level",
                    "The price for the delivery on partner portal is shown as per the changed service level", false);
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "The price for the partner portal delivery is not shown as per the changed service level",
                    true);
        }
    }

    @And("^I close the Trip Delivery Details page$")
    public void i_close_the_trip_delivery_details_page(){
        try{
        action.click(Page_Partner_Delivery_List.Button_Close());
        log("I should able to close the Trip Delivery Details page.","I am able to closed the Trip Delivery Details page.", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I request for \"([^\"]*)\" Bungii trip in partner portal in \"([^\"]*)\" $")
    public void i_request_something_bungii_trip_in_partner_portal_for_some_geofence(String Type,String geofence, DataTable data) throws InterruptedException {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String Pickup_Address;
        String Delivery_Address;

        //cucumberContextManager.setScenarioContext("BUNGII_TYPE", Type);
        cucumberContextManager.setScenarioContext("Partner_Bungii_type",Type);
        if(geofence.equalsIgnoreCase("washingtondc")) {
            //String Pickup_Address = dataMap.get("Pickup_Address");
            Pickup_Address = PropertyUtility.getDataProperties("partner.pickup.washingtondc");
            //String Delivery_Address = dataMap.get("Delivery_Address");
            Delivery_Address = PropertyUtility.getDataProperties("partner.drop.washingtondc");
            cucumberContextManager.setScenarioContext("Delivery_Address", Delivery_Address);
        }
        else{
            Pickup_Address = dataMap.get("Pickup_Address");
            Delivery_Address = dataMap.get("Delivery_Address");
            cucumberContextManager.setScenarioContext("Delivery_Address", Delivery_Address);
        }
        String Load_Unload = dataMap.get("Load_Unload_Time");

        //int numberOfDriver = bungiiType.trim().equalsIgnoreCase("duo") ? 2 : 1;
        int numberOf_Driver = dataMap.get("Driver").trim().equalsIgnoreCase("duo") ? 2 :1;

        cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", geofence);

        switch (Type)
        {
            case "Solo":
                //action.click(Page_Partner_Dashboard.Partner_Solo());

                action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
                action.click(Page_Partner_Dashboard.Button_PickupClear());

                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(),Pickup_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                Thread.sleep(1000);
                action.click(Page_Partner_Dashboard.List_Pickup_Address());

                Thread.sleep(2000);
                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(),Delivery_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                //Thread.sleep(1000);
                action.click(Page_Partner_Dashboard.List_Delivery_Address());

                Thread.sleep(5000);

                action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                switch (Load_Unload) {
                    case "15 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",15);
                        break;
                    case "30 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",30);
                        break;
                    case "45 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",45);
                        break;
                    case "60 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",60);
                        break;
                    case "75 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",75);
                        break;
                    case "90+ minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                        cucumberContextManager.setScenarioContext("LoadUnload_Time",90);
                        break;
                    default:break;
                }
                break;
            case "Duo":
                action.click(Page_Partner_Dashboard.RadioButton_Partner_Duo());
                action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
                action.click(Page_Partner_Dashboard.Button_PickupClear());

                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(),Pickup_Address+ Keys.TAB);
                //action.sendKeys((Page_Partner_Dashboard.Pickup_Address(),Pickup_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
                Thread.sleep(1000);
                action.click(Page_Partner_Dashboard.List_Pickup_Address());

                Thread.sleep(2000);
                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(),Delivery_Address+ Keys.TAB);
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                //Thread.sleep(1000);
                action.click(Page_Partner_Dashboard.List_Delivery_Address());

                action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());

                switch (Load_Unload) {
                    case "15 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                        break;
                    case "30 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                        break;
                    case "45 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                        break;
                    case "60 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                        break;
                    case "75 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                        break;
                    case "90+ minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                        break;
                    default:break;
                }
                break;
            default: break;

        }
            log("I request for "+Type+" Bungii delivery in partner portal in "+ geofence + " geofence","I requested for "+Type+" Bungii delivery in partner portal in "+ geofence + " geofence", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting pickup and drop off address.", true);
        }
    }

    @When("^I click on \"([^\"]*)\" information icon and verify its text contents$")
    public void i_click_on_some_information_icon(String Information_Icon)throws Throwable{
        try{
        String expectedMessage = "", actualMessage = "";
        Thread.sleep(3000);
        switch (Information_Icon){
            case "WHAT’S NEEDED?":
                action.click(Page_Partner_Dashboard.Information_Icon_Whats_Needed());
                expectedMessage = PropertyUtility.getMessage("Partner_What_Needed_Info");
                actualMessage = action.getText(Page_Partner_Dashboard.InnerText_Information_Icon());
                action.click(Page_Partner_Dashboard.Information_Icon_Whats_Needed());
                break;
            case "Delivery Address":
                action.click(Page_Partner_Dashboard.Information_Icon_Delivery_Address());
                expectedMessage = PropertyUtility.getMessage("Partner_Delivery_Address_Info");
                actualMessage = action.getText(Page_Partner_Dashboard.InnerText_Information_Icon());
                action.click(Page_Partner_Dashboard.Information_Icon_Delivery_Address());
                break;
            case "Load/Unload Time":
                action.click(Page_Partner_Dashboard.Information_Icon_LoadUpload());
                expectedMessage = PropertyUtility.getMessage("Partner_LoadUnload_Info");
                actualMessage = action.getText(Page_Partner_Dashboard.InnerText_Information_Icon());
                action.click(Page_Partner_Dashboard.Information_Icon_LoadUpload());
                break;
            case "PickUp Date":
                action.click(Page_Partner_Dashboard.Information_Icon_Pickup_Date());
                expectedMessage = PropertyUtility.getMessage("Partner_Pickup_Date_Info");
                actualMessage = action.getText(Page_Partner_Dashboard.InnerText_Information_Icon());
                action.click(Page_Partner_Dashboard.Information_Icon_Pickup_Date());
                break;
            default:break;
        }
        log("I click on Information Icon "+ Information_Icon +"and verify it text contents",
                "I have clicked on Information Icon "+ Information_Icon +" and verified its test contents",true);
        testStepAssert.isEquals(expectedMessage,actualMessage,expectedMessage+" should be displayed ", expectedMessage+" is displayed ", actualMessage+" is displayed ");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I change the \"([^\"]*)\" and click on Get Estimate button$")
    public void i_change_something_and_click_on_get_estimate_button(String str,DataTable data) throws InterruptedException {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String Load_Unload = dataMap.get("Load_Unload_Time");

        switch (str){
            case "Load Unload Time":
                action.click(Page_Partner_Dashboard.Dropdown_Load_Unload_Time());
                switch (Load_Unload) {
                    case "15 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_15());
                        break;
                    case "30 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_30());
                        break;
                    case "45 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_45());
                        break;
                    case "60 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_60());
                        break;
                    case "75 minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_75());
                        break;
                    case "90+ minutes":
                        action.click(Page_Partner_Dashboard.Load_Unload_Time_90());
                        break;
                    default:break;
                }
                break;
            case "Delivery Address":
                String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

                String Delivery_Address = dataMap.get("Delivery_Address");

                action.click(Page_Partner_Dashboard.Button_DeliveryClear());
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(),Delivery_Address+ Keys.TAB);
                Thread.sleep(5000);
                action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
                Thread.sleep(5000);
                action.click(Page_Partner_Dashboard.List_Delivery_Address());
                Thread.sleep(2000);

                break;
            default:break;

        }
        action.click(Page_Partner_Dashboard.Button_Get_Estimate());
            log("I update  "+str+" and click on estimate","I updated  "+str+" and click on estimate", true);

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in selecting pickup and drop off address.", true);
        }
    }

    @And("^I clear the Pickup Address on Get Estimate screen of Partner Portal$")
    public void i_clear_the_pickup_address_on_get_estimate_screen(){
try{
        action.click(Page_Partner_Dashboard.Button_PickupClear());
        log("I clear pickup address ","I cleared pickup address ", true);
} catch(Exception e){
    logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
    error("Step should be successful", "Error performing step,Please check logs for more details",
            true);
}
    }

    @Then("^I check that Address field on Get Estimate screen get clear$")
    public void i_check_that_Address_field_on_get_estimate_screen_get_clear(){
        try{
        String Delivery_Address = action.getText(Page_Partner_Dashboard.Dropdown_Pickup_Address());
        testStepAssert.isEquals(Delivery_Address, "", "Address field on estimate should be clear.", "Address field on estimate page is cleared.", "Address field on estimate is not cleared.");
        } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
        }

    }

    @Then("^Estimate Cost should get recalculate$")
    public void Estimate_Cost_get_recalculate(){
        try{
            String Total_Estimated_Cost = action.getText(Page_Partner_Dashboard.Label_Estimated_Cost());
            //String Estimated_Cost_Label = Total_Estimated_Cost.substring(0,Total_Estimated_Cost.indexOf(':'));
            String[] Split_Total_estimated_Cost = Total_Estimated_Cost.split(":");
            //String Estimated_Cost_Label = Split_Total_estimated_Cost[0];
            String New_Estimated_Cost = Split_Total_estimated_Cost[1];
            String Old_Estimated_Cost = (String)cucumberContextManager.getScenarioContext("Estimated_Cost");

            testStepAssert.isFalse(New_Estimated_Cost.equals(Old_Estimated_Cost),
                        "total Estimated cost should be recalculated",
                        "Total Estimate cost is recalculated , previous cost is" + Old_Estimated_Cost + " , new cost is" + New_Estimated_Cost,
                        "Total Estimate cost was not recalculated");
            Old_Estimated_Cost = New_Estimated_Cost;
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I check correct estimated price calculated on Partner Portal$")
    public void i_should_see_correct_estimated_price(){
        try {
           // String Alias_Name= (String) cucumberContextManager.getScenarioContext("Alias");
            String estimate = (String)cucumberContextManager.getScenarioContext("Estimated_Cost");
            //estimate = estimate.replace("~$", "");
            //String LT = (String)cucumberContextManager.getScenarioContext("LoadUnload_Time");
            //String loadTime = String.valueOf(LT);
            String loadTime = String.valueOf(cucumberContextManager.getScenarioContext("LoadUnload_Time"));

            com.bungii.web.utilityfunctions.GeneralUtility utility = new com.bungii.web.utilityfunctions.GeneralUtility();
            String partnerRef = (String)cucumberContextManager.getScenarioContext("PARTNERREF");

            //TODO: verify DB and phone value
            String totalDistance = dbUtility.getEstimateDistanceByPartnerReference(partnerRef);
            String totalEstimateTime = dbUtility.getEstimateTimeByPartnerReference(partnerRef);

            double expectedValue = utility.bungiiEstimate(totalDistance, loadTime, totalEstimateTime, "");

            String expectedEstimatedCost = String.valueOf(expectedValue);

            String actualValue = estimate.substring(0, estimate.length() - 1);
            String truncValue = new DecimalFormat("#.00").format(expectedValue);

            testStepAssert.isEquals(expectedEstimatedCost,truncValue.trim(), "Estimate value for trip should be properly displayed.(NOTE: Failure might me due to truncation)", "Expected Estimate value for bungii is " + truncValue + " and Actual value is" + actualValue + ",(Truncate to single float point)", "Expected Estimate value for bungii is" + truncValue + " and Actual value is" + estimate);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }


    }

    @Then("^I should be able to see the respective partner portal trip with \"([^\"]*)\" state$")
    public void i_should_be_able_to_see_the_respective_partner_portal_trip_with_something_state(String strArg1) throws Throwable {
        try{
            String pickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            action.clearSendKeys(admin_LiveTripsPage.TextBox_Search_Field(),pickupRef);
            action.click(admin_LiveTripsPage.Button_Search());
            String status = strArg1;
        String ST = (String) cucumberContextManager.getScenarioContext("Scheduled_Time");
        String geofence = (String)  cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        /*
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a z", Locale.ENGLISH);//for checking the MMMM month format
        DateTimeFormatter dft1 = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a z",Locale.ENGLISH);//for converting to MMM month format
        //String geoLabel = utility.getTimeZoneBasedOnGeofenceId();
        String geoLabel = utility.getTripTimezone(geofence);
        TimeZone zone = TimeZone.getTimeZone(geoLabel);
        ZonedDateTime abc = LocalDateTime.parse(ST,dft).atZone(zone.toZoneId());
        ST = dft1.format(abc);
        ST = utility.getbungiiDayLightTimeValue(ST);

         */

        String BT = (String) cucumberContextManager.getScenarioContext("Bungii_Type");
        String Client = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        BT = BT.replace("Solo Scheduled","Solo");
        String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]", BT, ST,Client,status);

        int retrycount = 12;

        boolean retry = true;
        while (retry == true && retrycount > 0) {
            try {
                WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                retry = false;

            } catch (Exception ex) {
                SetupManager.getDriver().navigate().refresh();
                retrycount--;
                retry = true;
            }

        }

        testStepAssert.isElementTextEquals(action.getElementByXPath(XPath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should be able to see the respective bungii partner portal trip with the below status$")
    public void i_should_be_able_to_see_the_respective_bungii_partner_portal_trip_with_the_below_status(DataTable data) throws Throwable {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String status = dataMap.get("Status").trim();
            String tripType = (String) cucumberContextManager.getScenarioContext("Partner_Bungii_type");
            String customer = (String) cucumberContextManager.getScenarioContext("Customer_Name");

            String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
            String pickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            cucumberContextManager.setScenarioContext("pickupRequest",pickupRef);

            String geofenceName = getGeofence(geofence);
            action.clearSendKeys(admin_LiveTripsPage.TextBox_Search_Field(),pickupRef);
            action.click(admin_LiveTripsPage.Button_Search());

            cucumberContextManager.setScenarioContext("STATUS", status);
            String pageName = action.getText(admin_LiveTripsPage.Text_Page_Header());

            if (status.equalsIgnoreCase("Scheduled") || (status.equalsIgnoreCase("Assigning Driver(s)") && pageName.contains("Scheduled")) || status.equalsIgnoreCase("Driver Removed")|| status.equalsIgnoreCase("Driver(s) Not Found")) {
                String xpath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[5]", tripType.toUpperCase(), customer);
                int retrycount = 13;

                boolean retry = true;
                while (retry == true && retrycount > 0) {
                    try {
                        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                        retry = false;

                    } catch (Exception ex) {
                        SetupManager.getDriver().navigate().refresh();
                        Thread.sleep(10000); //Wait for 10 seconds
                        retrycount--;
                        retry = true;
                    }

                }

                Thread.sleep(3000);
                int retryCount = 1;
                String str1="";
                do{
                    if (retryCount >= 20) break;
                    action.refreshPage();
                    str1 = action.getElementByXPath(xpath).getText();
                    Thread.sleep(15000); //Wait for 15 seconds
                    retryCount++;
                }while (!str1.equalsIgnoreCase(status));

                cucumberContextManager.setScenarioContext("XPATH", xpath);
                String St2 = status;
                String St1 = action.getElementByXPath(xpath).getText();
                testStepAssert.isElementTextEquals(action.getElementByXPath(xpath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

        } else {

                String XPath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[4]", tripType, customer);
                int retrycount = 10;

                boolean retry = true;
                while (retry == true && retrycount > 0) {
                    try {
                        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), 10);
                        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
                        retry = false;
                    } catch (Exception ex) {
                        SetupManager.getDriver().navigate().refresh();
                        retrycount--;
                        retry = true;
                    }

                }
                Thread.sleep(3000);
                int retryCount = 1;
                String str1="";
                do{
                    if (retryCount >= 20) break;
                    action.refreshPage();
                    str1 = action.getElementByXPath(XPath).getText();
                    Thread.sleep(15000); //Wait for 15 seconds
                    retryCount++;
                }while (!str1.equalsIgnoreCase(status));

                cucumberContextManager.setScenarioContext("XPATH", XPath);
                testStepAssert.isElementTextEquals(action.getElementByXPath(XPath), status, "Trip Status " + status + " should be updated", "Trip Status " + status + " is updated", "Trip Status " + status + " is not updated");

            }
            log("I should see the respective bungii partner portal trip with the status "+ status,"Partner portal delivery with the status "+ status +" is displayed", true);
            //tripType = (String) cucumberContextManager.getScenarioContext("BUNGII_TYPE");
        }
        catch(Exception e)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);

        }

    }
    @And("^I wait for Minimum duration for \"([^\"]*)\" Bungii to be in Driver not accepted state$")
    public void i_wait_for_minimum_duration_for_something_bungii_to_be_in_driver_not_accepted_state(String strArg1) {
        try {
            long initialTime;
            if (strArg1.equalsIgnoreCase("current"))
                initialTime = (long) cucumberContextManager.getFeatureContextContext("BUNGII_INITIAL_SCH_TIME");
            else
                initialTime = (long) cucumberContextManager.getFeatureContextContext("BUNGII_INITIAL_SCH_TIME" + "_" + strArg1);
            long currentTime = System.currentTimeMillis() / 1000L;
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(currentTime - initialTime);
            if (diffInMinutes > 5) {
                //do nothing
            } else {
                // minimum wait of 30 mins
                action.hardWaitWithSwipeUp(5 - (int) diffInMinutes);

            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^I view the correct Driver Est. Earnings for geofence based pricing model$")
    public void i_view_the_correct_Driver_Est_Earnings(){
        try{
        String DriverEstEarning= action.getElementByXPath("//td[text()='Driver Earnings']/following::td[1]").getText();
        //DriverEstEarning=DriverEstEarning.substring(1,DriverEstEarning.length());
        DriverEstEarning=DriverEstEarning.substring(1,DriverEstEarning.length());

        String ExpectedDriverEstEarning= webUtility.calDriverEstEarning();

        testStepVerify.isEquals(ExpectedDriverEstEarning, DriverEstEarning.trim(), "Driver Est. Earning value for trip should be properly displayed.(NOTE: Failure might me due to truncation)", "Expected Driver Est. Value for bungii is" + ExpectedDriverEstEarning + " and Actual value is" + DriverEstEarning + ",(Truncate to single float point)", "Expected Est. Earning value for bungii is" + ExpectedDriverEstEarning + " and Actual value is" + DriverEstEarning);
        action.JavaScriptScrollToTop();
        action.click(admin_TripDetailsPage.Button_Ok());
        log("I should able to view the correct Driver Est. Earnings for geofence based pricing model","I am able to viewed the correct Driver Est. Earnings for geofence based pricing model", true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I note the Driver Est. Earnings for the search delivery$")
    public void i_note_the_Driver_Est_Earnings_for_the_search_delivery()throws Throwable {
        try{
            String DriverEstEarning= action.getText(Page_Admin_Trips_Details.Text_Driver_Est_Earnings_Customer_Delivery());

            DriverEstEarning=DriverEstEarning.substring(1,DriverEstEarning.length());
            cucumberContextManager.setScenarioContext("Old_Driver_Earning",DriverEstEarning);

            log("I should able to note the Driver Est. Earnings for the search delivery.","The noted Driver Est. Earnings for the delivery is: "+DriverEstEarning, true);
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to note the driver est earning for the search delivery",
                    true);
        }
    }

    @Then("^I confirm that Driver Est. Earnings for the delivery remain same$")
    public void the_Driver_Est_Earnings_for_the_delivery_remain_same()throws Throwable {
        try {
            String NewDriverEstEarning = action.getText(Page_Admin_Trips_Details.Text_Driver_Est_Earnings_Customer_Delivery());

            NewDriverEstEarning = NewDriverEstEarning.substring(1, NewDriverEstEarning.length());

            String Old_Driver_Est_Earning = (String) cucumberContextManager.getScenarioContext("Old_Driver_Earning");

            testStepAssert.isEquals(NewDriverEstEarning, Old_Driver_Est_Earning, "Old driver estimate earning should be same as new driver estimate earning", "Old driver estimate earning is same as new driver estimate earning", "Old driver estimate earning is not same as new driver estimate earning");

            cucumberContextManager.setScenarioContext("Old_Driver_Earning", NewDriverEstEarning);

            log("Old Driver Est Earning -" + Old_Driver_Est_Earning + " should be same as New Driver Est Earning -" + NewDriverEstEarning, "Old Driver Est Earning -" + Old_Driver_Est_Earning + " is same as New Driver Est Earning -" + NewDriverEstEarning, true);
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to confirm driver est earning remain same on delivery detail page",
                    true);
        }
    }

    @And("^I navigate back to Scheduled Deliveries$")
    public void i_navigate_back_to_scheduled_deliveries() throws Throwable {
        try{
            action.click(Page_Admin_Trips_Details.Button_Ok());
            log("I should able to navigate back to Scheduled Deliveries.","I have navigated back to Scheduled Deliveries", true);
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to navigate back to Scheduled Deliveries",
                    true);

        }
    }

    @Then("^I view the correct Driver Earnings for geofence based pricing model$")
    public void i_view_the_correct_Driver_Earnings(){
        try{
            String DriverEarning= action.getElementByXPath("//td[text()='Driver Earnings']/following::td[1]").getText();
        //To remove $ sign
        DriverEarning=DriverEarning.substring(1,DriverEarning.length());

        String ExpectedDriverEarning= utility.calDriverEarning();

        testStepVerify.isEquals(ExpectedDriverEarning, DriverEarning.trim(), "Driver Est. Earning value for trip should be properly displayed.(NOTE: Failure might me due to truncation)", "Expected Driver Est. Value for bungii is" + ExpectedDriverEarning + " and Actual value is" + DriverEarning + ",(Truncate to single float point)", "Expected Est. Earning value for bungii is" + ExpectedDriverEarning + " and Actual value is" + DriverEarning);
        action.click(Page_Partner_Delivery_List.Button_OK_Admin_Portal());

        log("I should able to view the correct Driver Earnings for geofence based pricing model.","I am able to viewed the correct Driver Earnings for geofence based pricing model", true);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }


    @And("^I navigate to partner portal$")
    public void i_navigate_to_partner_portal(){
        ArrayList<String> tabs = new ArrayList<String> (SetupManager.getDriver().getWindowHandles());
        SetupManager.getDriver().switchTo().window(tabs.get(0));
        action.refreshPage();
        log("I should able to navigate to partner portal.","I am able to navigate to partner portal.", true);
    }

    @And("^I navigate to partner portal and view the Trip status with below status$")
    public void i_view_the_scheduled_trips_list_on_the_partner_portal_with_some_status(DataTable data) throws InterruptedException {
        try{Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String Partner_Status = dataMap.get("Partner_Status").trim();
        ArrayList<String> tabs = new ArrayList<String> (SetupManager.getDriver().getWindowHandles());
        SetupManager.getDriver().switchTo().window(tabs.get(0));
        SetupManager.getDriver().manage().window().maximize();
        String Delivery_Date = (String) cucumberContextManager.getScenarioContext("PickupDateTime");
        String CustomerName = (String) cucumberContextManager.getScenarioContext("Customer_Name");
        String DeliveryAddress = (String) cucumberContextManager.getScenarioContext("Delivery_Address");

        if(Partner_Status.equalsIgnoreCase("Completed")){
            action.click(Page_Partner_Delivery_List.Dropdown_Partner_Status());
            action.click(Page_Partner_Delivery_List.Checkbox_Completed_Status());
            action.click(Page_Partner_Delivery_List.Button_Apply());
            Thread.sleep(2000);
            action.click(Page_Partner_Delivery_List.Dropdown_Partner_Status());
        }else if(Partner_Status.equalsIgnoreCase("Canceled")){
            action.click(Page_Partner_Delivery_List.Dropdown_Partner_Status());
            action.click(Page_Partner_Delivery_List.Checkbox_Canceled_Status());
            action.click(Page_Partner_Delivery_List.Button_Apply());
            Thread.sleep(2000);
            action.click(Page_Partner_Delivery_List.Dropdown_Partner_Status());

        }

        String xpath = String.format("//td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[3]", Delivery_Date, CustomerName);
        if(!Partner_Status.equalsIgnoreCase("Canceled")) {
            if(!Partner_Status.equalsIgnoreCase("Completed")) {
                action.refreshPage();
            }
        }
        Thread.sleep(1000);
        testStepAssert.isElementTextEquals(action.getElementByXPath(xpath), Partner_Status, "Trip Status " + Partner_Status + " should be updated", "Trip Status " + Partner_Status + " is updated", "Trip Status " + Partner_Status + " is not updated. Expected : "+ xpath);
            if (!Partner_Status.equalsIgnoreCase("Canceled")) {
                if (!Partner_Status.equalsIgnoreCase("Completed")) {
                    SetupManager.getDriver().switchTo().window(tabs.get(1));
                }
            }
        log("I should able to navigate to partner portal and view the Trip status with status as "+Partner_Status,"I get navigate to partner portal and viewed the Trip status with status as "+Partner_Status, true);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }


    @And("^I click on the delivery based on customer name$")
    public void i_click_on_the_delivery_based_on_customer_name() throws Throwable {
        try{
        action.click(Page_Partner_Dashboard.Textbox_SearchBar());
        Thread.sleep(1000);
        action.clearSendKeys(Page_Partner_Dashboard.Textbox_SearchBar(), (String)cucumberContextManager.getScenarioContext("CUSTOMER") + Keys.ENTER);
        Thread.sleep(1000);
        action.click(Page_Partner_Dashboard.Link_SelectTripTrackDeliveries());
        log("I should be able to click on the customer trip","I could click on the customer trip",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The time should be different when the pickup address is changed to a different geofence$")
    public void the_time_should_be_different_when_the_pickup_address_is_changed_to_a_different_geofence() throws Throwable {
        try{
            String oldGeofencePickupAddressTime= (String) cucumberContextManager.getScenarioContext("OLDGEOFENCETIME");
            String newGeofencePickupAddressTime = action.getText(Page_Partner_Dashboard.Text_PartnerPortalGeofenceTime());
            testStepAssert.isFalse(newGeofencePickupAddressTime.contentEquals(oldGeofencePickupAddressTime),
                    "Time displayed when the pickup address is changed from 1 geofence to other should be different",
                    "Time displayed when the pickup address is changed from 1 geofence to other is different",
                    "Time displayed when the pickup address is changed from 1 geofence to other is not different"
            );
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see partner disclaimer info$")
    public void i_should_see_partner_disclaimer_info() {
        try {
            testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Text_PartnerPortalDisclaimer(),
                    "Partner Portal Disclaimer should be displayed",
                    "Partner Portal Disclaimer is displayed", "Partner Portal Disclaimer is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }

    @Then("^I should see \"([^\"]*)\" title$")
    public void i_should_see_title(String string) {
        try {
            testStepAssert.isEquals(action.getText(Page_Partner_Dashboard.Text_CustomQuotesHeader()), string,
                    "Custom Quotes title should be displayed", "Custom Quotes title is displayed",
                    "Custom Quotes title is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }

    @Then("^I should see Custom Quotes description$")
    public void i_should_see_Custom_Quotes_description() {
        try {
            testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Text_CustomQuotesDescription(),
                    "Custom Quotes description should be displayed",
                    "Custom Quotes description is displayed", "Custom Quotes description is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }

    @Then("^I should see \"([^\"]*)\" link$")
    public void i_should_see_link(String string) {
        try {
            testStepAssert.isEquals(action.getText(Page_Partner_Dashboard.Link_CustomQuotesForm()), string,
                    "Fill out this form link should be displayed",
                    "Fill out this form link is displayed", "Fill out this form link is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }

    @Then("^I click on fill out this form link$")
    public void i_click_on_fill_out_this_form_link() {
        try {
            action.click(Page_Partner_Dashboard.Link_CustomQuotesForm());
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }

    @Then("^I should be redirected to \"([^\"]*)\" tab$")
    public void i_should_be_redirected_to_tab(String string) {
        try {
            action.switchToTab(1);
            testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Text_QuoteRequestPageHeader(),
                    "Quote Request page should be displayed",
                    "Quote Request page should is displayed", "Quote Request page should is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }

    @Then("^I close the Quote Request tab$")
    public void i_close_the_Quote_Request_tab() {
        try {
            action.switchToTab(0);
            testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Text_CustomQuotesHeader(),
                    "Get Quote page should be displayed",
                    "Get Quote page is displayed", "Get Quote page is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing Quotes only page",
                    true);
        }
    }



    @Then("^I should be able to schedule a trip \"([^\"]*)\"days from today$")
    public void i_should_be_able_to_schedule_a_trip_somethingdays_from_today(String tripDate) throws Throwable {
        try{
            action.click(Page_Partner_Dashboard.Dropdown_Pickup_Date());
            String [] entireDayAndMonth = new DateTime().plusDays(Integer.parseInt(tripDate)).toDate().toString().split(" ");
            String date = entireDayAndMonth[2];
            String deliveryMonth = entireDayAndMonth[1];
            String [] todaysDate =  new DateTime().toDate().toString().split(" ");
            String currentMonth = todaysDate[1];

            if(currentMonth.equals(deliveryMonth)){

                if(date.startsWith("0")){
                    String dateWithoutZero = date.replace("0","");
                    action.click(Page_Partner_Dashboard.FutureTrip(dateWithoutZero));
                    cucumberContextManager.setScenarioContext("Future_Date",dateWithoutZero);
                }
                else{
                    action.click(Page_Partner_Dashboard.FutureTrip(date));
                    cucumberContextManager.setScenarioContext("Future_Date",date);
                }

            }
            else{
                if(date.startsWith("0")){
                    String dateWithoutZero = date.replace("0","");
                    Thread.sleep(3000);
                    action.click(Page_Partner_Dashboard.Link_NextMonth());
                    action.click(Page_Partner_Dashboard.FutureTrip(dateWithoutZero));
                    Thread.sleep(2000);
                    cucumberContextManager.setScenarioContext("Future_Date",dateWithoutZero);

                }

                else{
                    Thread.sleep(3000);
                    action.click(Page_Partner_Dashboard.Link_NextMonth());
                    action.click(Page_Partner_Dashboard.FutureTrip(date));
                    Thread.sleep(2000);
                    cucumberContextManager.setScenarioContext("Future_Date",date);

                }
            }
            String [] dateMonthAndDayFromUi =Page_Partner_Dashboard.Text_DateSelectedFromUi().getAttribute("value").split("\\(");
            String onlyMonthAndDateFromUi = dateMonthAndDayFromUi[1].replace(")","").replace(" ","");
            String dateAndMonthFromCalender =deliveryMonth + (String) cucumberContextManager.getScenarioContext("Future_Date");
            testStepAssert.isEquals(onlyMonthAndDateFromUi,dateAndMonthFromCalender,"I should be able to select a date "+tripDate+ " days ahead",
                    "I could select a date "+tripDate+ " days ahead",
                    "I coudnt select a date "+tripDate+ " days ahead");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @And("^I click on the checkbox$")
    public void i_click_on_the_checkbox() throws Throwable {
        try{
            action.click(Page_Partner_Dashboard.Label_Checkbox());
            String oldPickupAddressTime= action.getText(Page_Partner_Dashboard.Text_PartnerPortalGeofenceTime());
            cucumberContextManager.setScenarioContext("OLDGEOFENCETIME",oldPickupAddressTime);
            log("I should be able to click on the checkbox","I could click on the checkbox",false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the trip scheduled for \"([^\"]*)\" days ahead$")
    public void i_should_see_the_trip_scheduled_for_something_days_ahead(String tripdays) throws Throwable {
        try{
        String[] deliveryTime=  cucumberContextManager.getScenarioContext("Schedule_Date_Time").toString().split(" ");
        Thread.sleep(3000);
        String deliveryDateAndMonth = deliveryTime[0].substring(0,3) + deliveryTime[1];
        String []adminPortalDeliveryTime = action.getText(admin_ScheduledTripsPage.Text_ScheduledTripDate()).split(" ");
        String adminPortalDeliveryDateAndTime = adminPortalDeliveryTime[0]+adminPortalDeliveryTime[1];

        testStepVerify.isEquals(adminPortalDeliveryDateAndTime.replace(","," "),deliveryDateAndMonth.replace(","," "),"Scheduled trips should be  " +tripdays +" days ahead","Scheduled trips is " +tripdays +" days ahead","Scheduled trips should be " +tripdays +" days ahead");
        cucumberContextManager.setScenarioContext("INITIALTRIP_TIMEANDDATE",adminPortalDeliveryDateAndTime);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I change the trip delivery date to \"([^\"]*)\" days ahead from today$")
    public void i_change_the_trip_delivery_date_to_something_days_ahead_from_today(String noOfDays) throws Throwable {
        try {
        String [] entireDayAndMonth = new DateTime().plusDays(Integer.parseInt(noOfDays)).toDate().toString().split(" ");
        String tripDateAhead = entireDayAndMonth[2];
        String deliveryMonth = entireDayAndMonth[1];
        String [] todaysDate =  new DateTime().toDate().toString().split(" ");
        String currentMonth =todaysDate[1];
        action.click(admin_EditScheduledBungiiPage.DatePicker_ScheduledDate());
        if(!deliveryMonth.equals(currentMonth)) {
            action.click(admin_ScheduledTripsPage.Link_EditScheduleTripCalenderPreviousMonth());
        }

        if(deliveryMonth.equals(currentMonth)) {
            if(tripDateAhead.startsWith("0")){
                String TripAheadWithoutZero= tripDateAhead.replace("0","");
                action.click(admin_ScheduledTripsPage.Link_NewScheduleDeliveryDate(TripAheadWithoutZero));
            }
            else {
                action.click(admin_ScheduledTripsPage.Link_NewScheduleDeliveryDate(tripDateAhead));
            }

        }
        else{
            action.click(admin_ScheduledTripsPage.Link_EditScheduleTripCalenderNextMonth());

                if (tripDateAhead.startsWith("0")) {
                    String datewithoutzero = tripDateAhead.replace("0", "");
                    action.click(admin_ScheduledTripsPage.Link_NewScheduleDeliveryDate(datewithoutzero));
                } else {
                    action.click(admin_ScheduledTripsPage.Link_NewScheduleDeliveryDate(tripDateAhead));
                }
        }
        log("I should be able to schedule the delivery "+noOfDays +" days from today","I could schedule the delivery " +noOfDays+" days from today",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I unselect the Pending status from the filter category$")
    public void i_unselect_the_pending_status_from_the_filter_category() throws Throwable {
        try{
        action.click(admin_TripsPage.Button_Filter());
        Thread.sleep(1000);
        action.click(admin_TripsPage.CheckBox_FilterPending());
        log("I should be able to unclick the pending status from the filter category",
                "I could unclick the pending status from the filter category",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see the message \"([^\"]*)\" displayed$")
    public void i_should_see_the_message_something_displayed(String expectedMessage) throws Throwable {
        try{
        action.waitUntilIsElementExistsAndDisplayed(admin_TripsPage.Text_NoDeliveriesFound(),(long) 5000);
        String NoDeliveries = action.getText(admin_TripsPage.Text_NoDeliveriesFound()).toLowerCase();
        testStepAssert.isEquals(NoDeliveries,expectedMessage.toLowerCase(),"I should see " +expectedMessage+ " text displayed","Text message displayed is " + NoDeliveries,expectedMessage +" is not displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I change the pickup address to \"([^\"]*)\" on partner portal$")
    public void i_change_the_pickup_address_to_something_on_partner_portal(String pickupAddress) throws Throwable {
        try{
        action.click(Page_Partner_Dashboard.Button_Pickup_Edit());
        action.click(Page_Partner_Dashboard.Button_PickupClear());
        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Pickup_Address(), pickupAddress + Keys.TAB);
        action.click(Page_Partner_Dashboard.Dropdown_Pickup_Address());
        Thread.sleep(3000);
        action.click(Page_Partner_Dashboard.List_Pickup_Address());

        log("I should be able to change the pickup address on partner portal to " +pickupAddress,
              "I could change the pickup address on partner portal to " +pickupAddress,false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^The scheduled trip date should be changed to the new date$")
    public void the_scheduled_trip_date_should_be_changed_to_the_new_date() throws Throwable {
        try{
            String []adminPortalDeliveryTime = action.getText(admin_ScheduledTripsPage.Text_ScheduledTripDate()).split(" ");
            String adminPortalDeliveryDateAndTime = adminPortalDeliveryTime[0]+adminPortalDeliveryTime[1];
            String oldDeliveryDateAndTime =(String) cucumberContextManager.getScenarioContext("INITIALTRIP_TIMEANDDATE");
            testStepAssert.isFalse(adminPortalDeliveryDateAndTime.contentEquals(oldDeliveryDateAndTime), "The schedule delivery date should be changed from "+oldDeliveryDateAndTime+" to" +adminPortalDeliveryDateAndTime, "The schedule delivery date is changed from "+oldDeliveryDateAndTime+" to" +adminPortalDeliveryDateAndTime, "The schedule delivery date doesnt change from "+oldDeliveryDateAndTime+" to" +adminPortalDeliveryDateAndTime);

    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I select the \"([^\"]*)\" address from the pickup address dropdown$")
    public void i_select_the_something_address_from_the_pickup_address_dropdown(String addressNumber) throws Throwable {
        try{
        action.click(Page_Partner_Dashboard.DropDown_PickupAddressPartnerPortal());
        Thread.sleep(5000);
        switch(addressNumber){
            case "First":
                Thread.sleep(5000);
                action.click(Page_Partner_Dashboard.Text_PickupAddressesFromPartnerPortalDropDown(1));
                String addressOnePickupAddressFromDropDown = action.getText(Page_Partner_Dashboard.Text_PartnerPortalGeofenceTime());
                cucumberContextManager.setScenarioContext("PICKUPADDRESS_1_TIME",addressOnePickupAddressFromDropDown);
                break;
            case"Second":
                System.out.println("second");
                Thread.sleep(5000);
                action.click(Page_Partner_Dashboard.Text_PickupAddressesFromPartnerPortalDropDown(2));
                String addressTwoPickupAddressFromDropDown = action.getText(Page_Partner_Dashboard.Text_PartnerPortalGeofenceTime());
                cucumberContextManager.setScenarioContext("PICKUPADDRESS_2_TIME",addressTwoPickupAddressFromDropDown);
                break;
        }
        log("I should be able to click on the "+addressNumber+" address" ,"I could click on the "+addressNumber+" address",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The pickup time should be same for both the addresses from the dropdown$")
    public void the_pickup_time_should_be_same_for_both_the_addresses_from_the_dropdown() throws Throwable {
        try{
        String Address1 = (String)cucumberContextManager.getScenarioContext("PICKUPADDRESS_1_TIME");
        String address2 =(String) cucumberContextManager.getScenarioContext("PICKUPADDRESS_2_TIME");
        testStepAssert.isEquals(Address1,address2,"Pickup time for both the addresses should be the same", "Pickup time for both the addresses is the same", "Pickup time for both the addresses are not the same");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I click on tooltip beside \"([^\"]*)\" field$")
    public void i_click_on_tooltip_beside_something_field(String field) throws Throwable {
        try {
            switch (field) {
                case "Pickup Date":
                    action.click(Page_Partner_Dashboard.Icon_ToolTip_PickupDate());
                    break;
            }
            log("I click on tooltip beside "+field ,"I have clicked on tooltip beside "+field,false);

        } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see tooltip value based on configured value in database$")
    public void i_should_see_tooltip_value_based_on_configured_value_in_database() throws Throwable {
        String subdomain= (action.getCurrentURL().split("[.]")[0]).split("//")[1];
        String dayCount = DbUtility.getScheduledDays(subdomain);
       testStepAssert.isEquals(action.getText(Page_Partner_Dashboard.Label_ToolTip_PickupDate()), "Please select a delivery date within the next "+dayCount+" days.", dayCount+ " days should be displayed",dayCount+ " days is displayed",dayCount+ " days is not displayed");
    }
    @And("^I verify alias is displayed correctly on \"([^\"]*)\"$")
    public void i_verify_alias_is_displayed_correctly_on_something(String page) throws Throwable {
        try {
            String aliasPartnerPortalName= PropertyUtility.getDataProperties("partner.floor.and.decor.alias.name");
            switch (page){
                case "scheduled delivery page":
                    action.waitUntilIsElementExistsAndDisplayed(Page_Partner_Dashboard.Text_PartnerName(), (long) 5000);
                    testStepAssert.isEquals(action.getText(Page_Partner_Dashboard.Text_PartnerName()),aliasPartnerPortalName,
                            "The portal name displayed should be correct",
                            "The portal name displayed is correct",
                            "The portal name displayed is incorrect");
                    break;
                case "delivery details page":
                    testStepAssert.isEquals(action.getText(Page_Partner_Dashboard.Text_PartnerNameDeliveryDetailsPage()),aliasPartnerPortalName,
                            "The portal name displayed should be correct",
                            "The portal name displayed is correct",
                            "The portal name displayed is incorrect");
                    break;
                case "all delivery page":
                    Thread.sleep(2000);
                    testStepAssert.isEquals(action.getText(Page_Partner_Dashboard.Text_PartnerNameAllDeliveryPage()),aliasPartnerPortalName,
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


    @And("^I add the delivery address as \"([^\"]*)\"$")
    public void i_add_the_delivery_address_as_something(String address) throws Throwable {
        try{
        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
        action.clearSendKeys(Page_Partner_Dashboard.Dropdown_Delivery_Address(), address + Keys.TAB);
        Thread.sleep(3000);
        action.click(Page_Partner_Dashboard.Dropdown_Delivery_Address());
        Thread.sleep(5000);
        action.click(Page_Partner_Dashboard.List_Delivery_Address());
        log("I should be able to add the dropoff delivery address as "+address,"I could add the dropoff delivery address as "+address,false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("I select below delivery status in filter")
    public void iSelectBelowDeliveryStatusInFilter(DataTable data) {
        try {
            Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
            String Partner_Status = dataMap.get("Partner_Status").trim();
            if (Partner_Status.equalsIgnoreCase("Canceled")) {
                Thread.sleep(2000);
                action.click(Page_Partner_Delivery_List.Dropdown_Partner_Status());
                Thread.sleep(1000);
                action.click(Page_Partner_Delivery_List.Checkbox_Canceled_Status());
                Thread.sleep(2000);
                action.click(Page_Partner_Delivery_List.Button_Apply());
            }
            log("I should able to select the "+Partner_Status+" status in filter.","I can select the "+Partner_Status+" status in filter.", false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The Pickup contact name \"([^\"]*)\" and pickup contact phone number \"([^\"]*)\" field should be filled$")
    public void the_pickup_contact_name_something_and_pickup_contact_phone_number_something_field_should_be_filled(String contactName, String contactPhone) throws Throwable {
     try{
       String uiContactName = action.getAttributeValue(Page_Partner_Delivery.TextBox_Pickup_Contact_Name());
        String uiContactPhone = action.getAttributeValue(Page_Partner_Delivery.TextBox_Pickup_Contact_Phone());

        testStepAssert.isEquals(uiContactName,contactName,"Pickup contact name should be " +contactName,"Pickup contact name is " +uiContactName,"Pickup contact name  " +contactName+" is not displayed");

        testStepAssert.isEquals(uiContactPhone,contactPhone,"Pickup contact number should be " +contactPhone,"Pickup contact number is " +uiContactPhone,"Pickup contact number  " +contactPhone+" is not displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("default pickup address should be shown")
    public void defaultPickupAddressShouldBeShown() {
        try{
            action.isElementPresent(Page_Partner_Dashboard.Button_Pickup_Edit());
            testStepVerify.isEquals(action.getText(Page_Partner_Dashboard.Text_Pickup_Address()),PropertyUtility.getDataProperties("equipbid_default_address"),"Correct default pickup address should be shown.","Correct default pickup address is shown.","Wrong default pickup address is shown.");

        }catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @Then("^For \"([^\"]*)\" first time slot is \"([^\"]*)\" and last time slot is \"([^\"]*)\"$")
    public void for__something_first_time_slot_is_something_and_last_time_slot_is_something(String day, String strArg2, String strArg3) throws Throwable {
        try{
            String partnerPortalName =PropertyUtility.getDataProperties("qa.home.outlet.url").substring(8,25);
        for(int i=0;i<7;i++){
            LocalDate todayDateWithMonthAndYeat = LocalDate.now().plusDays(i);
            DayOfWeek dayBasedOnDate=todayDateWithMonthAndYeat.getDayOfWeek();
            int currentDate= todayDateWithMonthAndYeat.getDayOfMonth();
            if (dayBasedOnDate.toString().equalsIgnoreCase(day)){
                int currentDayIndex=dayBasedOnDate.getValue();
                cucumberContextManager.setScenarioContext("CurrentDate",currentDate);
                cucumberContextManager.setScenarioContext("CurrentDayOfTheWeek",dayBasedOnDate);
                cucumberContextManager.setScenarioContext("IndexOfTheCurrentDay",currentDayIndex);
                break;
            }
        }
        String currentDate = (String) cucumberContextManager.getScenarioContext("CurrentDate");
        String currentDay = (String) cucumberContextManager.getScenarioContext("CurrentDayOfTheWeek");
        String currentDayIndex = (String) cucumberContextManager.getScenarioContext("IndexOfTheCurrentDay");

        if (currentDay.equalsIgnoreCase("Sunday")){
            Thread.sleep(2000);
            String IsButtonDisabled = Page_Partner_Dashboard.Button_SundayDisabled().getAttribute("class");
            testStepAssert.isEquals(IsButtonDisabled,"item excluded","Button should be not clickable as Partner is closed on Sundays",
                    "Button is  not clickable as Partner is closed on Sundays",
                    "Button is clickable even though partner is closed on Sundays" );
        testStepAssert.isElementDisplayed(Page_Partner_Dashboard.Button_SundayDisabled(),
                currentDate+" should be displayed but not clickable as its "+currentDay,
                 currentDate+" is displayed but not clickable as its "+currentDay,
                currentDate+" is displayed but is clickable as its "+currentDay);

        }
        else {
                Thread.sleep(3000);
                action.click(Page_Partner_Dashboard.FutureTrip(currentDate));
                Thread.sleep(3000);
                action.click(Page_Partner_Dashboard.Button_PickupTime());
                if(currentDay.equalsIgnoreCase("Saturday")) {
                    String firstTimeSlot = action.getText(Page_Partner_Dashboard.Text_FirstTimeSlot());
                    String lastTimeSlot = action.getText(Page_Partner_Dashboard.Text_LastTimeSlot(33));
                    String firstTimeSlotInDB = "0"+ new com.bungii.api.utilityFunctions.DbUtility().getFromTime(currentDayIndex,currentDay,partnerPortalName) + ":00 AM";
                    String lastTimeSlotInDB = "0"+ new com.bungii.api.utilityFunctions.DbUtility().getToTime(currentDayIndex, currentDay,partnerPortalName) +":00 PM";;
                    testStepAssert.isEquals(firstTimeSlot,firstTimeSlotInDB,
                            "The partner portal first time slot should be "+firstTimeSlotInDB ,
                            "The partner portal first time slot is "+firstTimeSlot ,
                            "The partner portal first time slot is not "+ firstTimeSlotInDB);
                    testStepAssert.isEquals(lastTimeSlot,lastTimeSlotInDB,
                            "The partner portal last time slot should be "+ lastTimeSlotInDB,
                            "The partner portal last time slot is "+ lastTimeSlot,
                            "The partner portal last time slot is not "+lastTimeSlotInDB );

                }
                else {
                    String firstTimeSlot = action.getText(Page_Partner_Dashboard.Text_FirstTimeSlot());
                    String lastTimeSlot = action.getText(Page_Partner_Dashboard.Text_LastTimeSlot(37));

                    String firstTimeSlotInDB = "0"+ new com.bungii.api.utilityFunctions.DbUtility().getFromTime(currentDayIndex,currentDay,partnerPortalName) + ":00 AM";
                    String lastTimeSlotInDB = "0"+ new com.bungii.api.utilityFunctions.DbUtility().getToTime(currentDayIndex, currentDay,partnerPortalName) +":00 PM";;
                    testStepAssert.isEquals(firstTimeSlot,firstTimeSlotInDB,
                            "The partner portal first time slot should be "+firstTimeSlotInDB ,
                            "The partner portal first time slot is "+firstTimeSlot ,
                            "The partner portal first time slot is not "+ firstTimeSlotInDB);
                    testStepAssert.isEquals(lastTimeSlot,lastTimeSlotInDB,
                            "The partner portal last time slot should be "+ lastTimeSlotInDB,
                            "The partner portal last time slot is "+ lastTimeSlot,
                            "The partner portal last time slot is not "+lastTimeSlotInDB );
                }

        }
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    public String getGeofence(String geofence) {
        String geofenceName = "";
        switch (geofence) {
            case "washingtondc":
                geofenceName = "Washington DC";
                break;

        }
        return geofenceName;
    }

    public String enterTime(String time) throws ParseException {
        String strTime = "";

        if (time.equalsIgnoreCase("NEXT_POSSIBLE")) {
            Date date = getNextScheduledBungiiTime();
           // String[] dateScroll = bungiiTimeForScroll(date);
            strTime = bungiiTimeDisplayInTextArea(date);


           // selectBungiiTime(0, dateScroll[1], dateScroll[2], dateScroll[3]);

            //action.click(Page_Partner_Dashboard.Dropdown_Pickup_Time());
            //action.click(Page_Partner_Dashboard.Pickup_Time1());
        }
        if (time.equalsIgnoreCase("NEXT_DAY")) {

        }
        return strTime;
    }

        /**
         * Read property file for minimum difference for next bunii time
         *
         * @return next possible valid bungii time
         */
        public Date getNextScheduledBungiiTime() {
            return getFormatedTime();
        }

    public Date getFormatedTime() {
        Date date1 = Calendar.getInstance().getTime();
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(getDateForTimeZone());
            System.out.println("\t" + date1);
        } catch (Exception e) {
        }

        return date1;
    }

    public String getDateForTimeZone() {
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        int nextTripTime = Integer.parseInt(PropertyUtility.getProp("scheduled.bungii.time"));
        Calendar calendar = Calendar.getInstance();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + nextTripTime);
        int unroundedMinutes = calendar.get(Calendar.MINUTE);
        calendar.add(Calendar.MINUTE, (15 - unroundedMinutes % 15));

        String strdate = formatter.format(calendar.getTime());
        return strdate;
    }

    /**
     * Format input date and return in required format
     *
     * @param date input date
     * @return formated date
     */
    public String bungiiTimeDisplayInTextArea(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, hh:mm a");
        String formattedDate = sdf.format(date);
        //After sprint 27 /26 IST is being added in scheduled page
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

        if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase(""))
            formattedDate = formattedDate + " " + PropertyUtility.getDataProperties("time.label");
        else
            formattedDate = formattedDate + " " + utility.getTimeZoneBasedOnGeofence();
        return formattedDate;
    }

    /**
     * Format input date and return in required format
     *
     * @param date input date
     * @return formated date
     */
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

}
