package com.bungii.android.stepdefinitions;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.DriversPage;
import com.bungii.android.pages.admin.ScheduledTripsPage;
import com.bungii.android.pages.customer.LoginPage;
import com.bungii.android.pages.customer.MyBungiisPage;
import com.bungii.android.pages.driver.EarningsPage;
import com.bungii.android.stepdefinitions.Customer.LoginSteps;
import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class VerifyBungiiDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    LoginPage loginPage = new LoginPage();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    MyBungiisPage myBungiisPage = new MyBungiisPage();
    ScheduledTripsPage scheduledTripsPage = new ScheduledTripsPage();
    DriversPage driversPage = new DriversPage();
    EarningsPage earningsPage = new EarningsPage();

    @Then("^I verify driver names and trip cost$")
    public void i_verify_driver_names_pickup_and_drop_off_address_and_trip_cost() throws Throwable {
        String expectedDriverName=(String)cucumberContextManager.getScenarioContext("DRIVER_1");
        String[] Name = expectedDriverName.split(" ");
        expectedDriverName = Name[0]+" "+Name[1].charAt(0); //Last Name initial
        String actualDriverName=action.getText(myBungiisPage.Text_FirstDriverName());

        testStepAssert.isEquals(actualDriverName,expectedDriverName,"Driver name expected is "+expectedDriverName,"Expected Driver name is displayed.",expectedDriverName+" driver name is not displayed.");

        expectedDriverName=(String)cucumberContextManager.getScenarioContext("DRIVER_2");
        if(expectedDriverName!="") {
            Name = expectedDriverName.split(" ");
            expectedDriverName = Name[0] + " " + Name[1].charAt(0); //Last Name initial
            actualDriverName = action.getText(myBungiisPage.Text_SecondDriverName());
            testStepAssert.isEquals(actualDriverName, expectedDriverName, "Driver name expected is " + expectedDriverName, "Expected Driver name is displayed.", expectedDriverName + " driver name is not displayed.");
        }
        String expectedTripCost=(String)cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
        expectedTripCost= expectedTripCost.replace("~","");
        String actualTripCost=action.getText(myBungiisPage.Text_TripCost());
        testStepAssert.isEquals(actualTripCost,expectedTripCost,"Trip cost expected is "+expectedTripCost,"Expected Trip Cost is displayed.",expectedTripCost+" is not displayed.");

    }

    @Then("^Driver names and trip cost is displayed correctly$")
    public void i__driver_names_pickup_and_drop_off_address_and_trip_cost() throws Throwable {
        String expectedDriverName=(String)cucumberContextManager.getScenarioContext("DRIVER_1");
        String[] Name = expectedDriverName.split(" ");
        expectedDriverName = Name[0]+" "+Name[1].charAt(0); //Last Name initial
        String actualDriverName=action.getText(myBungiisPage.Text_FirstDriverName());

        testStepAssert.isEquals(actualDriverName,expectedDriverName,"Driver name expected is "+expectedDriverName,"Expected Driver name is displayed.",expectedDriverName+" driver name is not displayed.");

        expectedDriverName=(String)cucumberContextManager.getScenarioContext("DRIVER_2");
        if(expectedDriverName!="") {
            Name = expectedDriverName.split(" ");
            expectedDriverName = Name[0] + " " + Name[1].charAt(0); //Last Name initial
            actualDriverName = action.getText(myBungiisPage.Text_SecondDriverName());
            testStepAssert.isEquals(actualDriverName, expectedDriverName, "Driver name expected is " + expectedDriverName, "Expected Driver name is displayed.", expectedDriverName + " driver name is not displayed.");
        }
       // String expectedTripCost=(String)cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
        String pickupref =  (String)cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String expectedTripCost = dbUtility.getFinalBungiiCost(pickupref);
        expectedTripCost= expectedTripCost.replace("~","");
        String actualTripCost=action.getText(myBungiisPage.Text_TripCost());
        testStepAssert.isEquals(actualTripCost,"$"+expectedTripCost,"Trip cost expected is "+expectedTripCost,"Expected Trip Cost is displayed.",expectedTripCost+" is not displayed.");

    }
    @Then("^I verify the field \"([^\"]*)\"$")
    public void i_verify_the_field_something(String option) throws Throwable {
        try{
            switch (option){
                case "driver name":
                    String expectedDriverName=(String)cucumberContextManager.getScenarioContext("DRIVER1NAME");
                    expectedDriverName= expectedDriverName.replace(".","");
                    String actualDriverName=action.getText(myBungiisPage.Text_FirstDriverName());
                    testStepAssert.isEquals(actualDriverName,expectedDriverName,"Driver name expected is "+expectedDriverName,"Expected Driver name is displayed.",expectedDriverName+" driver name is not displayed.");
                    break;
                case "pickup address":
                    String expectedpickuplocation1=(String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1");
                    String expectedpickuplocation2=(String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2");
                    String actualpickuplocation1=action.getText(myBungiisPage.Text_PickUp_Location1());
                    String actualpickuplocation2=action.getText(myBungiisPage.Text_PickUp_Location2());
                    if(expectedpickuplocation1.equalsIgnoreCase(actualpickuplocation1) && expectedpickuplocation2.equalsIgnoreCase(actualpickuplocation2))
                    {
                        testStepAssert.isTrue(true,"Pickup Address is correct.", "Pickup Address does not match.");
                    }
                    else
                    {
                        testStepAssert.isFail("Pickup Address does not match.");
                    }
                    break;
                case "dropoff address":
                    String expecteddropofflocation1=(String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1");
                    String expecteddropofflocation2=(String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2");
                    String actualdropofflocation1=action.getText(myBungiisPage.Text_DropOff_Location1());
                    String actualdropofflocation2=action.getText(myBungiisPage.Text_DropOff_Location2());
                    if(expecteddropofflocation1.equalsIgnoreCase(actualdropofflocation1) && expecteddropofflocation2.equalsIgnoreCase(actualdropofflocation2))
                    {
                        testStepAssert.isTrue(true,"DropOff Address is correct.", "DropOff Address does not match.");
                    }
                    else
                    {
                        testStepAssert.isFail("DropOff Address does not match.");
                    }
                    break;
                case "trip cost":
                    String cost = (String)cucumberContextManager.getScenarioContext("ACTUAL_COST");
                    String expectedTripCost = new DecimalFormat("0.00").format(Double.parseDouble(cost)).toString();
                    expectedTripCost="$"+expectedTripCost;
                    String actualTripCost=action.getText(myBungiisPage.Text_TripCost());
                    testStepAssert.isEquals(actualTripCost,expectedTripCost,"Trip cost expected is "+expectedTripCost,"Expected Trip Cost is displayed. ",expectedTripCost+" is not displayed.");
                    break;
                case "timezone":
                    String expectedBungiiTime=(String)cucumberContextManager.getScenarioContext("BUNGII_TIME");
                     expectedBungiiTime=expectedBungiiTime.replace(" GMT+5:30","");

                    if(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()).inDaylightTime( new Date() ))
                        expectedBungiiTime = expectedBungiiTime.replace("ST","DT");
                    String actualBungiiTime=action.getText(myBungiisPage.Text_TripScheduledDate());
                    testStepAssert.isEquals(actualBungiiTime,expectedBungiiTime,"Bungii time expected is "+expectedBungiiTime,"Expected Bungii Time is displayed.",expectedBungiiTime+" is not displayed.");
                    break;
                default:
                    error("UnImplemented Step or incorrect option.", "UnImplemented Step");
                    break;
            }

        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in verifying details under Past Bungiis", true);
        }

    }
    @Then("^correct date of the trip is displayed as per the timezone of the geofence$")
    public void correct_date_of_the_trip_is_displayed_as_per_the_timezone_of_the_geofence() throws Throwable {
        String expectedBungiiTime=(String)cucumberContextManager.getScenarioContext("BUNGII_TIME");
        expectedBungiiTime=expectedBungiiTime.replace("GMT+5:30","GMT+05:30");

        if(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()).inDaylightTime( new Date() ))
            expectedBungiiTime = expectedBungiiTime.replace("ST","DT");
        int year = new DateTime().getYear();
        expectedBungiiTime = expectedBungiiTime.substring(0,7)+expectedBungiiTime.substring(16,expectedBungiiTime.length());
        String actualBungiiTime =action.getText(myBungiisPage.Text_TripScheduledDate()).replace(" "+String.valueOf(year),"");
        testStepAssert.isTrue(expectedBungiiTime.contains(actualBungiiTime),"Bungii time expected is "+expectedBungiiTime,"Expected Bungii Time is displayed.",actualBungiiTime+" is displayed instead of "+expectedBungiiTime);
    }

    @When("^I view last completed bungii$")
    public void i_view_last_completed_bungii() throws Throwable {
        action.click(myBungiisPage.Text_DeliveryDate());
    }
    @And("^I open first trip in past trips$")
    public void i_view_last_completed_bungii_trip() throws Throwable {
        try{

            List<WebElement> selectDriver;
            selectDriver= myBungiisPage.findElements("//android.widget.ImageView[@resource-id='com.bungii.customer:id/item_my_bungii_iv_arrow'][1]",PageBase.LocatorType.XPath);
            action.click(selectDriver.get(0));
            log("I open first trip from Past Bungiis ","I opened first trip from Past Bungiis ",true);
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            //logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Trip is not displayed in Past Trips", true);
        }
    }

    @And("^I open the trip for \"([^\"]*)\" driver$")
    public void i_open_the_trip_for_something_driver(String driverName) throws Throwable {
        try{

            String[] Name = driverName.split(" ");
            driverName = Name[0]+" "+Name[1].charAt(0)+"."; //Last Name initial
             Thread.sleep(5000);
            List<WebElement> selectDriver;
            selectDriver= myBungiisPage.findElements("//*[contains(@text, '"+driverName+"')]/following::android.widget.ImageView[@resource-id='com.bungii.customer:id/item_my_bungii_iv_arrow'][1]",PageBase.LocatorType.XPath);
            if(selectDriver.size()>0) {
                action.click(selectDriver.get(0));
                log("I open trip from Past Bungiis ","I opened trip of customer of driver "+driverName+" from Past Bungiis ",true);
            }
            else
            {
                testStepAssert.isFail("Delivery of customer of driver "+driverName+" from Past Bungiis is not displayed");
            }

            cucumberContextManager.setScenarioContext("DRIVER1NAME",driverName);
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            //logger.error("Page source", SetupManager.getDriver().getPageSource());
            error("Step  Should be successful", "Completed Delivery for customer of driver "+ driverName+" is not displayed in Past Trips", true);
        }
    }
    @And("^I get \"([^\"]*)\" from earnings page$")
    public void i_get_something_from_earnings_page(String earningsType) throws Throwable {
        try{
            switch (earningsType){
                case "Itemized Earnings":
                    Thread.sleep(7000);
                    action.click(myBungiisPage.Button_ItemizedEarnings());
                    Thread.sleep(5000);
                    String itemizedEarnings = action.getText(myBungiisPage.Text_ItemizedEarnings());
                    String actualItemizedEarnings = itemizedEarnings.substring(1);
                    cucumberContextManager.setScenarioContext("DRIVER_ITEMIZED_EARNINGS",actualItemizedEarnings);
                    break;
            }
            log("I should be able to get "+earningsType,
                    "I could get the "+earningsType,false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error while getting earnings from earnings page", true);
        }
    }
    @And("^I verify all the elements on itemized earnings page$")
    public void i_verify_all_the_elements_on_itemized_earnings_page() throws Throwable {
        try{
            boolean isCorrectPage = false;
            isCorrectPage = utility.isCorrectPage("ITEMIZED EARNINGS");
            testStepAssert.isTrue(isCorrectPage,
                    "I should be navigated to ITEMIZED EARNINGS screen",
                    "I have navigated to ITEMIZED EARNINGS screen" ,
                    "I was not navigated to ITEMIZED EARNINGS screen ");

            testStepAssert.isElementDisplayed(myBungiisPage.Dropdown_EndDate(),"The element should be displayed","The element is displayed","The element is not displayed");
            action.click(myBungiisPage.Dropdown_EndDate());
            testStepAssert.isElementDisplayed(myBungiisPage.Calendar_StartDate(),"The element should be displayed","The element is displayed","The element is not displayed");
            action.click(myBungiisPage.Button_Cancel());

            testStepAssert.isElementDisplayed(myBungiisPage.Dropdown_StartDate(),"The element should be displayed","The element is displayed","The element is not displayed");
            action.click(myBungiisPage.Dropdown_StartDate());
            testStepAssert.isElementDisplayed(myBungiisPage.Calendar_StartDate(),"The element should be displayed","The element is displayed","The element is not displayed");

            action.click(myBungiisPage.Calendar_SelectDate());
            String date = action.getText(myBungiisPage.Text_Date());
            int length= date.length();
            String year = action.getText(myBungiisPage.Text_Year());
            String expectedDate;
            if(length==10){
                expectedDate  = date.substring(5,8)+" 0"+date.substring(9,10)+", "+year;
            }
            else {
                 expectedDate= date.substring(5,8)+" "+date.substring(9,11)+", "+year;
            }
            action.click(myBungiisPage.Button_Okay());
            String actualDate=action.getText(myBungiisPage.Text_StartDate());
            testStepAssert.isEquals(actualDate,expectedDate,"The date set on calendar should be same as date displayed","The date set on calendar is same as date displayed","The date set on calendar is not same as date displayed");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error while verifying if element is present", true);
        }
    }
    @And("^I verify all the elements on earnings page$")
    public void i_verify_all_the_elements_on_earnings_page() throws Throwable {
        try {
            boolean isCorrectPage = false;
            isCorrectPage = utility.isCorrectPage("EARNINGS");
            testStepAssert.isTrue(isCorrectPage,
                    "I should be navigated to EARNINGS screen",
                    "I have navigated to EARNINGS screen" ,
                    "I was not navigated to EARNINGS screen ");

            testStepAssert.isElementDisplayed(myBungiisPage.Dropdown_SelectYear(),"The element should be displayed","The element is displayed","The element is not displayed");
            testStepAssert.isElementDisplayed(myBungiisPage.Button_ItemizedEarnings(),"The itemized earnings button should be displayed","The itemized earnings button is displayed","The itemized earnings button is not displayed");

            String actualDisclaimer = action.getText(myBungiisPage.Text_Disclaimer());
            String expectedDisclaimer = PropertyUtility.getMessage("android.earnings.page.disclaimer");
            testStepAssert.isEquals(actualDisclaimer,expectedDisclaimer,
                    "The Disclaimer displayed should be "+expectedDisclaimer,
                    "The Disclaimer displayed is "+expectedDisclaimer,
                    "The Disclaimer displayed is incorrect");

            testStepAssert.isElementDisplayed(myBungiisPage.Text_MilesDriven(),"The Miles Driven should be displayed","The Miles Driven are displayed","The  Miles Driven are not displayed");
            testStepAssert.isElementDisplayed(myBungiisPage.Text_WorkHours(),"The Work Hours should be displayed","The Work Hours are displayed","The Work Hours are not displayed");
            testStepAssert.isElementDisplayed(myBungiisPage.Text_NoOfTrips(),"The number of trips should be displayed","The number of trips are displayed","The number of trips are not displayed");
            testStepAssert.isElementDisplayed(myBungiisPage.Text_DisbursementInfo(),"The Disbursement Info should be displayed","The Disbursement Info  is displayed","The Disbursement Info is not displayed");


        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error while verifying if element is present", true);
        }
    }
    @And("^I search for \"([^\"]*)\" driver on driver details$")
    public void i_search_for_something_driver_on_driver_details(String driverName) throws Throwable {
        try{
            action.clearSendKeys(scheduledTripsPage.Text_SearchCriteria(),driverName);
            action.click(scheduledTripsPage.Button_Search());

            Thread.sleep(25000);
            log("I should be able to search for the driver",
                    "I was able to search the driver",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in searching the driver", true);
        }
    }
    @And("^I click on \"([^\"]*)\" icon on driver page$")
    public void i_click_on_something_icon_on_driver_page(String icon) throws Throwable {
        try {
            switch (icon){
                case "Driver Earnings":
                    action.click(myBungiisPage.Icon_DriverEarnings());
                    break;
                case "View":
                    action.click(myBungiisPage.Link_ViewTrips());
                    break;
                case "Profile":
                    action.click(driversPage.Button_DriverProfileLink());
                    break;
            }
            log("I should be able to click on "+icon,
                    "I could click on"+icon,false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error while clicking on"+icon, true);
        }
    }
    @And("^I get \"([^\"]*)\" from driver earnings page on admin portal for \"([^\"]*)\"$")
    public void i_get_something_from_driver_earnings_page_on_admin_portal_for_something(String strArg1, String bungiiType) throws Throwable {
        try{
            switch(bungiiType){
                case "solo driver":
                    String driverEarnings = action.getText(myBungiisPage.Text_DriverEarnings());
                    String actualDriverEarnings = driverEarnings.substring(1);
                    cucumberContextManager.setScenarioContext("DRIVER_EARNINGS_ADMIN",actualDriverEarnings);
                    break;
                case "duo first driver":
                    String firstDriverEarnings = action.getText(myBungiisPage.Text_DriverEarnings());
                    String firstActualDriverEarnings = firstDriverEarnings.substring(1);
                    cucumberContextManager.setScenarioContext("DRIVER_ONE_EARNINGS_ADMIN",firstActualDriverEarnings);
                    break;
                case "duo second driver":
                    String secondDriverEarnings = action.getText(myBungiisPage.Text_DriverEarnings());
                    String secondActualDriverEarnings = secondDriverEarnings.substring(1);
                    cucumberContextManager.setScenarioContext("DRIVER_TWO_EARNINGS_ADMIN",secondActualDriverEarnings);
                    break;

            }
            log("I should get the driver earnings from the admin portal",
                    "I could get the driver earnings from the admin portal",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in verifying details under Past Bungiis", true);
        }

    }
    @Then("^I compare with earnings from admin portal for \"([^\"]*)\"$")
    public void i_compare_with_earnings_from_admin_portal_for_something(String bungiiDriver) throws Throwable {
        try{
            switch (bungiiDriver){
                case "solo driver":
                    String driverEarningsOnAdminPortal= (String) cucumberContextManager.getScenarioContext("DRIVER_EARNINGS_ADMIN");
                    String driverEarningsOnDriverApp= (String) cucumberContextManager.getScenarioContext("DRIVER_ITEMIZED_EARNINGS");
                    testStepAssert.isEquals(driverEarningsOnDriverApp,driverEarningsOnAdminPortal,
                            "The earnings should be same on admin portal and driver app",
                            "The earnings are same on admin portal and driver app",
                            "The earnings are not same on admin portal and driver app");
                    break;
                case "duo first driver":
                    String firstDriverEarningsOnAdminPortal= (String) cucumberContextManager.getScenarioContext("DRIVER_ONE_EARNINGS_ADMIN");
                    String firstDriverEarningsOnDriverApp= (String) cucumberContextManager.getScenarioContext("DRIVER_ITEMIZED_EARNINGS");
                    testStepAssert.isEquals(firstDriverEarningsOnDriverApp,firstDriverEarningsOnAdminPortal,
                            "The earnings should be same on admin portal and driver app",
                            "The earnings are same on admin portal and driver app",
                            "The earnings are not same on admin portal and driver app");
                    break;
                case "duo second driver":
                    String secondDriverEarningsOnAdminPortal= (String) cucumberContextManager.getScenarioContext("DRIVER_TWO_EARNINGS_ADMIN");
                    String secondDriverEarningsOnDriverApp= (String) cucumberContextManager.getScenarioContext("DRIVER_ITEMIZED_EARNINGS");
                    testStepAssert.isEquals(secondDriverEarningsOnDriverApp,secondDriverEarningsOnAdminPortal,
                            "The earnings should be same on admin portal and driver app",
                            "The earnings are same on admin portal and driver app",
                            "The earnings are not same on admin portal and driver app");
                    break;
            }

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error while getting driver earnings", true);
        }
    }
    @Then("^I compare with earnings from admin portal$")
    public void i_compare_with_earnings_from_admin_portal() throws Throwable {
      try{
          String driverEarningsOnAdminPortal= (String) cucumberContextManager.getScenarioContext("DRIVER_EARNINGS_ADMIN");
          String driverEarningsOnDriverApp= (String) cucumberContextManager.getScenarioContext("DRIVER_ITEMIZED_EARNINGS");
          testStepAssert.isEquals(driverEarningsOnDriverApp,driverEarningsOnAdminPortal,
                  "The earnings should be same on admin portal and driver app",
                  "The earnings are same on admin portal and driver app",
                  "The earnings are not same on admin portal and driver app");
      }
      catch (Exception e) {
          logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
          error("Step  Should be successful", "Error while comparing the driver earnings on admin portal and driver app", true);
      }
    }

    @And("^I check \"([^\"]*)\" in db$")
    public void i_check_something_in_db(String type) throws Throwable {
        try{
            String driver = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
            switch (type){
                case "no branch registration":
                    String branchRegistrationDate = dbUtility.getDriverBranchRegistrationDate(driver);
                    if(branchRegistrationDate != null){
                        testStepAssert.isFail("There is registration date for non registered drivers.");
                    }
                    else {
                        testStepAssert.isTrue(true,"There should be no registration date for non registered drivers.",
                                "There is registration date for non registered drivers.");
                    }
                    break;
                case "branch registered with wallet":
                    String wallet = dbUtility.getDriverWalletInfo(driver);
                    if(!(wallet.isEmpty())){
                        testStepAssert.isTrue(true,"There should be wallet details present for drivers with wallet.",
                                "There are no wallet details present for drivers with wallet.");
                    }
                    else {
                        testStepAssert.isFail("There are no wallet details present for drivers with wallet.");
                    }
                    break;
                case "branch registered without wallet":
                    String withoutWallet = dbUtility.getDriverWalletInfo(driver);
                    if(withoutWallet != null){
                        testStepAssert.isFail("There are wallet details present for driver without wallet.");
                    }
                    else {
                        testStepAssert.isTrue(true,"There should not be wallet details present for drivers without wallet.",
                                "There are wallet details present for driver without wallet.");
                    }
                    break;
            }
            log("I should be able to check details in db","I am able to check details in db",false);
        }
        catch (Exception e){
            logger.error("Error performing step", e);
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("I verify the elements of payment setting screen page")
    public void iVerifyTheElementsOfPaymentSettingScreenPage()throws Throwable {
        try{
                testStepAssert.isTrue(action.isElementPresent(earningsPage.Text_PaymentSetting()),
                        "Payment Setting should be displayed.",
                        "Payment Setting is not displayed.");

                String expectedInfo = PropertyUtility.getDataProperties("payment.setting.info");
                testStepVerify.isTrue(action.getText(earningsPage.Text_PaymentSettingInfo()).contains(expectedInfo),
                        "Payment setting info displayed should be correct.",
                        "Payment setting info displayed is correct.",
                        "Payment setting info displayed is incorrect.");

                testStepAssert.isTrue(action.isElementPresent(earningsPage.Option_2xWeek()),
                    "The option for twice a week should be displayed.",
                    "The option for twice a week is not displayed.");
                testStepAssert.isTrue(action.isElementPresent(earningsPage.Option_SameDay()),
                    "The option for same day should be displayed.",
                    "The option for same day is not displayed.");

                testStepAssert.isTrue(action.isElementPresent(earningsPage.Button_Close()),
                    "The close button should be displayed.",
                    "The close button is not displayed.");

                testStepAssert.isTrue(action.isElementPresent(earningsPage.Button_Confirm()),
                    "The confirm button should be displayed.",
                    "The confirm button is not displayed.");

        }
        catch (Exception e){
            logger.error("Error performing step", e);
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}

