package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.admin.ScheduledTripsPage;
import com.bungii.android.pages.customer.*;
import com.bungii.android.pages.driver.*;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.android.pages.customer.PromosPage;

import com.bungii.android.pages.driver.TripAlertSettingsPage;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;
import static com.bungii.common.manager.ResultManager.pass;
import static com.bungii.web.utilityfunctions.DbUtility.getLinkedPickupRef;

public class TripAlertSettingsMenuSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    com.bungii.web.utilityfunctions.DbUtility dbUtility = new com.bungii.web.utilityfunctions.DbUtility();
    TripAlertSettingsPage tripAlertSettingsPage= new TripAlertSettingsPage();
    EstimatePage estimatePage = new EstimatePage();
    ScheduledBungiisPage scheduledBungiisPage=new ScheduledBungiisPage();
    PromosPage promosPage=new PromosPage();
    BungiiRequest bungiiRequestPage = new BungiiRequest();
    InProgressBungiiPages inProgressPages=new InProgressBungiiPages();
    HomePage homePage=new HomePage();
    ScheduledTripsPage scheduledTripsPage = new ScheduledTripsPage();
    SetPickupTimePage setPickupTimePage = new SetPickupTimePage();
    SearchingPage searchingPage = new SearchingPage();
    MyBungiisPage myBungiisPage = new MyBungiisPage();
    EarningsPage earningsPage = new EarningsPage();
    BungiiCompletedPage bungiiCompletedPage = new BungiiCompletedPage();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    BungiiCompletedPage Page_BungiiComplete = new BungiiCompletedPage();
    AccountPage accountPage = new AccountPage();
    InProgressBungiiPages Page_DriverBungiiProgress = new InProgressBungiiPages();
    EstimatePage bungiiEstimatePage = new EstimatePage();
    BungiiDetailsPage bungiiDetailsPage=new BungiiDetailsPage();

    UpdateStatusPage updateStatusPage = new UpdateStatusPage();

    @And("^I click on \"([^\"]*)\" tab$")
    public void i_click_on_something_tab(String option) throws Throwable {
        try {
            switch (option) {
                case "Delivery Alerts":
                    action.click(tripAlertSettingsPage.Tab_TripAlerts());
                    break;

                case "SMS Alerts":
                    action.click(tripAlertSettingsPage.Tab_SMSAlerts());
                    break;

                case "Scheduled":
                    action.click(homePage.Tab_MyBungiisScheduled());
                    break;

                case "Past":
                    Thread.sleep(10000);
                    action.click(homePage.Tab_MyBungiisPast());
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log(" I tap on " + option + " on My Bungiis",
                    "I  tap on  " + option, true);        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }


    @Then("^I should be able to see \"([^\"]*)\" Text and Time$")
    public void i_should_be_able_to_see_something_text_and_time(String tab)  {
        String data=null;Boolean b;
        WebElement element;
        String time=PropertyUtility.getDataProperties("alert.time.to.android");
        try {
            action.scrollToTop();
            int count =0;
            switch (tab) {
                case "Delivery Alerts":
                    data = action.getText(tripAlertSettingsPage.Text_TripAndSMSAlertsText());
                    testStepVerify.isEquals(data.trim(), PropertyUtility.getMessage("trip.alert.android.text").trim());
                    //b = clickDriverMenu(time);
                    //testStepVerify.isEquals(b.toString(), "true");
                     count = getTimeRow();
                     //At a time u can see only 6
                    testStepAssert.isTrue(count==6,"All Weekdays should be displayed","All Weekdays are displayed","All Weekdays are not displayed. Days displayed : "+ count);
                    action.scrollToBottom();
                    element = getLastTimeRow();
                    testStepAssert.isEquals(element.getText(),"Saturday","All Weekdays should be displayed","All Weekdays are displayed","All Weekdays are not displayed.");

                    break;

                case "SMS Alerts":
                    data = action.getText(tripAlertSettingsPage.Text_TripAndSMSAlertsText());
                    testStepVerify.isEquals(data.trim(), PropertyUtility.getMessage("sms.alert.text"));
                  //  b = clickDriverMenu(time);
                   // testStepVerify.isEquals(b.toString(), "true");
                    //At a time u can see  7 in SMS alerts
                    count = getTimeRow();
                    testStepAssert.isTrue(count==7,"All Weekdays should be displayed","All Weekdays are displayed","All Weekdays are not displayed. Days displayed : "+ count);
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should be able to see \"([^\"]*)\" Text$")
    public void i_should_be_able_to_see_something_text(String tab)  {
        try {
            switch (tab) {
                case "Note Details":
                    String noteText=action.getText(estimatePage.Text_DetailsNote());
                    String enteredNoteText=(String)cucumberContextManager.getScenarioContext("NOTE_TEXT");
                    if(noteText.equals(enteredNoteText)){
                        testStepAssert.isTrue(true, "The note text should match.", "The note text didn't match.");
                    }
                    else
                    {
                        testStepAssert.isFail("The note text didn't match.");
                    }
                    break;

                case "Customer Note":
                    noteText=action.getText(bungiiRequestPage.Text_CustomerNote());
                    enteredNoteText=(String)cucumberContextManager.getScenarioContext("NOTE_TEXT");
                    if(noteText.equals(enteredNoteText)){
                        testStepAssert.isTrue(true, "The note text of customer and driver should match.", "The note text of customer and driver didn't match.");
                    }
                    else
                    {
                        testStepAssert.isFail("The note text of customer and driver didn't match.");
                    }
                    break;

                case "Details From Customer":
                    noteText=action.getText(inProgressPages.Text_DeliveryInstructions());
                    enteredNoteText=(String)cucumberContextManager.getScenarioContext("NOTE_TEXT");
                    if(noteText.equals(enteredNoteText)){
                        testStepAssert.isTrue(true, "The note text of customer and driver should match.", "The note text of customer and driver didn't match.");
                    }
                    else
                    {
                        testStepAssert.isFail("The note text of customer and driver didn't match.");
                    }
                    break;

                case "No Note":
                        testStepAssert.isFalse(action.isElementPresent(estimatePage.Text_DetailsNote(true)), "The note section is not displayed.", "The note section is displayed.");
                    break;

                case "Customer Entered":
                    String expectedSchdlDateTime= (String) cucumberContextManager.getScenarioContext("SCHEDULE_BUNGII_DATE");
                    String actualSchdlDateTime=setPickupTimePage.Text_DateTime().getText();
                    testStepAssert.isEquals(actualSchdlDateTime, expectedSchdlDateTime,expectedSchdlDateTime+" is expected schedule date and time.", expectedSchdlDateTime+" is displayed.", expectedSchdlDateTime+" is not displayed.");
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP ");
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I click on time and change \"([^\"]*)\" time$")
    public void i_click_on_time_and_change_something_time(String strArg1) throws Throwable {
        try {
            action.click(tripAlertSettingsPage.Image_TimeSettingsArrow());
            action.click(tripAlertSettingsPage.Text_TimeSettingsFromTime());
            action.click(tripAlertSettingsPage.TimePicker_ChangeTime());
            log(" I click on time and change " + strArg1 + "",
                    "I clicked on time and change  " + strArg1, true);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }
    @And("^I get the new pickup reference generated$")
    public void i_get_the_new_pickup_reference_generated() throws Throwable {

        try {
            String pickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            pickupRequest = getLinkedPickupRef(pickupRequest);
            cucumberContextManager.setScenarioContext("PICKUP_REQUEST", pickupRequest);
            log("I get the new pickup reference generated",
                    "Pickupref is " + pickupRequest, false);
        }
        catch (Exception ex){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "New pickup reference is not generated",
                    true);
        }

    }
    @And("^I select the live trip for \"([^\"]*)\" customer for delivery details$")
    public void i_select_the_live_trip_for_something_customer_for_delivery_details(String cust) throws Throwable {
        try {
            String custName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            action.sendKeys(scheduledTripsPage.Text_SearchCriteria(), custName.substring(0, custName.indexOf(" ")));
            action.click(scheduledTripsPage.Button_Search());
            Thread.sleep(5000);
            action.click(scheduledTripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/img", custName), PageBase.LocatorType.XPath));
            action.click(scheduledTripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/ul/li/*[contains(text(),'Delivery Details')]", custName),PageBase.LocatorType.XPath));

            log("I should be able to open delivery details for the customer",
                    "I am able to open delivery details for the customer",false);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I should see \"([^\"]*)\" message on popup with PickupId anad Pickup Origin$")
    public void i_should_see_something_message_on_popup_with_pickupid_anad_pickup_origin(String message) throws Throwable {

        try{
            testStepAssert.isTrue(action.isElementPresent(scheduledTripsPage.Label_HeaderPopup()),message+" should be displayed", message+" is displayed", message+" is not displayed");
            String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String pickupId = dbUtility.getPickupIdFromFactPickup(pickuprequest);
            String source = "Customer Delivery";
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");

            testStepAssert.isElementTextEquals(scheduledTripsPage.Label_PickupId(),pickupId, pickupId +" should be displayed", pickupId +" is displayed", pickupId+" is not displayed");
            //testStepAssert.isElementTextEquals(admin_RevivalPage.Label_PickupOrigin(),source, source +" should be displayed", source +" is displayed", source+" is not displayed");
            testStepAssert.isElementTextEquals(scheduledTripsPage.Label_PickupCustomer(),customerName, customerName +" should be displayed", customerName +" is displayed", customerName+" is not displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I click on \"([^\"]*)\" button$")
    public void i_click_on_something_button(String Name) throws Throwable {
        try {
            switch (Name) {
                case "SAVE TIME":
                    action.click(tripAlertSettingsPage.TimePicker_OK());
                    action.click(tripAlertSettingsPage.Button_SaveTime());
                    break;
                case "UPDATE BUNGII":
                    action.click(tripAlertSettingsPage.Button_UpdateBungii());
                    break;

                case "ADD":
                    action.click(promosPage.Button_AddPromoCode());
                    break;

                case "OK":
                    action.click(promosPage.Button_Ok());
                    break;

                case "SAVE MONEY":
                    action.click(scheduledBungiisPage.Button_SaveMoney());
                    break;

                case "GET MORE MONEY":
                    action.click(promosPage.Button_GetMoreMoney());
                    break;

                case "ADD NOTE":
                    action.click(estimatePage.Button_AddNotes());
                    break;

                case "MORE":
                    action.click(inProgressPages.Button_MoreOptions());
                    break;

                case "DETAILS FROM CUSTOMER":
                    action.click(inProgressPages.Button_DetailsFromCustomer());
                    break;

                case "VERIFY":
                    String editLiveDelivery = action.getText(scheduledTripsPage.Header_EditLiveBungiiOrEditScheduledBungii());
                    if(editLiveDelivery.contentEquals("Edit Live Bungii")) {
                        action.javaScriptScrollDown(scheduledTripsPage.Button_VerifyDriver());
                        Thread.sleep(5000);
                        action.click(scheduledTripsPage.Button_VerifyDriver());
                    }
                    else {
                        action.javaScriptScrollDown(scheduledTripsPage.Button_VerifyDriverForScheduled());
                        Thread.sleep(5000);
                        action.click(scheduledTripsPage.Button_VerifyDriverForScheduled());
                    }
                    break;

                case "SAVE CHANGES":
                    action.javaScriptScrollDown(scheduledTripsPage.Button_SaveChanges());
                    Thread.sleep(5000);
                    action.click(scheduledTripsPage.Button_SaveChanges());
                    break;

                case "Edit Trip":
                    scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//p[@id='btnEdit']")).click();
                    break;

                case "Edit Trip1":
                    scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row1']/td/p[@id='btnEdit'][1]")).click();
                    break;

                case "Edit Trip2":
                    Thread.sleep(2000);
                    scheduledTripsPage.TableBody_TripDetails().findElement(By.xpath("//tr[@id='row2']/td/p[@id='btnEdit'][1]")).click();
                    break;

                case "Close":
                    action.click(scheduledTripsPage.Button_ClosePopUp());
                    break;

                case "SET PICKUP LOCATION":
                    action.click(homePage.Button_ETASet());
                    break;

                case "SET DROP OFF LOCATION":
                    Thread.sleep(2000);
                    action.click(homePage.Button_ETASet());
                    break;

                case "SCHEDULE BUNGII":
                    Thread.sleep(2000);
                    String actualSchdlDateTime=setPickupTimePage.Text_DateTime().getText();
                    cucumberContextManager.setScenarioContext("NEW_SCHDL_BUNGII_TIME", actualSchdlDateTime);
                    Thread.sleep(5000);
                    action.click(setPickupTimePage.Button_ScheduleBungii());
                    break;

                case "CANCEL":
                    action.click(searchingPage.Link_CancelSearch());
                    break;
                case "Account Cancel":
                    action.click(accountPage.Button_Cancel());
                    break;

                case "Cancel Bungii":
                    action.click(scheduledTripsPage.Button_Submit());
                    break;

                case "SUBMIT":
                    action.click(setPickupTimePage.Button_EnterCancellationReason());
                    break;

                case "SUBMIT REASON":
                    action.click(setPickupTimePage.Button_SubmitCancellationReason());
                    break;

                case "BACK":
                    ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK));
                    //action.click(myBungiisPage.Button_Back());
                    break;
                case "BACK TO BACK":
                    action.click(myBungiisPage.Button_Back());
                    Thread.sleep(2000);
                    action.click(myBungiisPage.Button_Back());
                    break;
                case "Itemized Earnings":
                    action.click(earningsPage.Button_ItemizedEarnings());
                    break;

                case "Delivery Instructions":
                    action.click(inProgressPages.Button_DeliveryInstructions());
                    break;
                case "SUBMIT RATING":
                    action.click(Page_BungiiComplete.Button_SubmitRating());
                    break;

                case "Revive":
                    action.click(scheduledTripsPage.Button_ReviveTrip());
                    break;
                case "Confirm":
                    action.click(scheduledTripsPage.Button_Confirm());
                    Thread.sleep(10000);
                    String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                    pickuprequest = dbUtility.getLinkedPickupRef(pickuprequest);
                    cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickuprequest);
                    break;

                case "Accept":
                    Thread.sleep(5000);
                    action.click(Page_BungiiRequest.Button_Accept());
                    break;

                case "View Request":
                    Thread.sleep(3000);
                    action.click(Page_BungiiRequest.Alert_ViewRequest());
                    break;

                case "STAY ONLINE":
                    action.click(bungiiCompletedPage.Button_StayOnline());
                    break;

                case "GO OFFLINE":
                    action.click(bungiiCompletedPage.Button_GoOffline());
                    break;
                case "Delete":
                    action.click(accountPage.Button_Delete());
                    break;
                case "GOT IT":
                    action.click((Page_DriverBungiiProgress.Button_GeneralInstructions_GotIt()));
                    break;
                case "Clear Signature":
                    action.click(updateStatusPage.Button_ClearSignature());
                    break;
                case "Confirm Status":
                    action.click(updateStatusPage.Button_ConfirmStatus());
                    break;
                case "Close Status":
                    action.click(updateStatusPage.Button_CloseStatus());
                    break;
                case "Remove Driver":
                    action.click(updateStatusPage.Button_RemoveDrivers());
                    break;
                case "CALCULATE COST":
                    action.click(updateStatusPage.Button_CalculateCost());
                    break;
                case "CONFIRM CHANGES":
                    action.click(updateStatusPage.Button_Confirm());
                    break;
                case "Got It":
                    action.click(updateStatusPage.Alert_DropOffInstructionsGotIt());
                    break;
                case "Skip Customer Signature":
                    action.click(updateStatusPage.Button_SkipCustomerSignature());
                    break;
                case "CLOSE":
                    action.click(updateStatusPage.Button_CloseDeliveryDetails());
                    break;
                case "Branch app":
                    action.click(earningsPage.Button_BranchWallet());
                    break;
                case "Payment Setting":
                    cucumberContextManager.setScenarioContext("DEFAULT_PAYMENT",earningsPage.Button_PaymentSetting().getAttribute("text"));
                    action.click(earningsPage.Button_PaymentSetting());
                    break;
                case "Close Payment Settings":
                    action.click(earningsPage.Button_Close());
                    break;
                case "Change default payment":
                    String defaultMethod= (String) cucumberContextManager.getScenarioContext("DEFAULT_PAYMENT");
                    if(defaultMethod.equalsIgnoreCase("same day")){
                        action.click(earningsPage.Checkbox_TwiceWeek());
                        cucumberContextManager.setScenarioContext("DEFAULT_PAYMENT", "2x week");
                    }
                    else{
                        action.click(earningsPage.Checkbox_SameDay());
                        cucumberContextManager.setScenarioContext("DEFAULT_PAYMENT","same day");
                    }
                    action.click(earningsPage.Button_Confirm());
                    break;
                case "Scan item barcode":
                    action.click(updateStatusPage.Button_ScanItemBarCode());
                    break;
                case "Allow":
                    action.click(bungiiEstimatePage.Permissions_CameraAllow());
                    break;
                case "Skip":
                    action.click(updateStatusPage.Button_SkipBarCode());
                    break;
                case "Continue":
                    action.click(bungiiDetailsPage.Button_Yes());
                    break;
                case "Get Estimate":
                    action.click(bungiiDetailsPage.Button_GetEstimate());
                    break;
                case "Schedule Bungii":
                    action.click(bungiiDetailsPage.Button_ScheduleBungii());
                    break;
                case "Get Quote":
                    action.click(bungiiDetailsPage.Button_GetQuote());
                    break;
                case "CONTINUE":
                    action.click(bungiiDetailsPage.Button_Continue());
                    break;
                case "Disclaimer":
                    action.scrollToBottom();
                    action.click(bungiiDetailsPage.Checkbox_Disclaimer());
                    break;
                case "No Thanks":
                    action.click(Page_BungiiRequest.Button_No_Thanks());
                    break;
                default:
                    error("Implemented Step", "UnImplemented Step");
            }
            pass(" I click on " + Name + " button",
                    "I clicked on  " + Name +" button");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I verify that changes in time are saved$")
    public void i_verify_that_changes_in_time_are_saved() throws Throwable {
        try {
            //Boolean b = checkTimeChange("08:00 - 09:00");
            action.scrollToTop();
            int count = getTimeRow();
            testStepAssert.isTrue(count==6,"All Weekdays should be displayed","All Weekdays are displayed","All Weekdays are not displayed. Days displayed : "+ count);
            action.scrollToBottom();
            WebElement element = getLastTimeRow();
            testStepAssert.isEquals(element.getText(),"Saturday","All Weekdays should be displayed","All Weekdays are displayed","All Weekdays are not displayed.");

        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    public boolean checkTime(String time) {
        Boolean isClicked = false;
        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsTime();
        for (WebElement element : elements) {
            if (element.getText().equals(time)) {
                isClicked = true;
                break;
            }
        }
        return isClicked;
    }

    public boolean checkTimeChange(String time) {
        Boolean isClicked = false;
        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsTime();
        for (WebElement element : elements) {
            if (element.getText().equals(time)) {
                isClicked = true;
            }
        }
        return isClicked;
    }

    public boolean clickDriverMenu(String time) {
        Boolean isCorrectTime = true;

        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsTime();
        for (WebElement element : elements) {
            if (element.getText().contains(time) && isCorrectTime) {
                isCorrectTime = true;
                 }else{
                isCorrectTime = false;
            }
        }
        if(elements.size()==0)
            isCorrectTime=false;

        return isCorrectTime;
    }
    public int getTimeRow() {
        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsTime();
        return elements.size();
    }

    public WebElement getLastTimeRow() {
        List<WebElement> elements = tripAlertSettingsPage.Text_TripAlertsDay();
        return elements.get(5);
    }

    @Then("I click on {string} dropdown")
    public void iClickOnDropdown(String dropdown) {
        try {
            switch(dropdown){
                case "year":
                    action.click(earningsPage.Dropdown_Year());
                    break;
                case "first value":
                    action.click(earningsPage.Dropdown_Firstvalue());
                    String earnings = action.getText(earningsPage.Text_Earnings());
                    cucumberContextManager.setScenarioContext("FIRST_VALUE",earnings);
                    break;
                case "second value":
                    action.click(earningsPage.Dropdown_Secondvalue());
                    String earnings2 = action.getText(earningsPage.Text_Earnings());
                    cucumberContextManager.setScenarioContext("SECOND_VALUE",earnings2);
                    break;
                case "third value":
                    action.click(earningsPage.Dropdown_Thirdvalue());
                    String earnings3 = action.getText(earningsPage.Text_Earnings());
                    cucumberContextManager.setScenarioContext("THIRD_VALUE",earnings3);
                    break;
            }
            log("I should be able to click on the "+dropdown+" dropdown",
                    "I could click on the "+dropdown+" dropdown",false);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("I check {string}")
    public void iCheck(String type) {
        try {
            switch (type) {
                case "total earnings":
                    String firstValue = (String) cucumberContextManager.getScenarioContext("FIRST_VALUE");
                    String secondValue = (String) cucumberContextManager.getScenarioContext("SECOND_VALUE");
                    String thirdValue = (String) cucumberContextManager.getScenarioContext("THIRD_VALUE");
                    firstValue = firstValue.substring(1);
                    secondValue = secondValue.substring(1);
                    thirdValue = thirdValue.substring(1);
                    float totalEarnings = Float.parseFloat(secondValue) + Float.parseFloat(thirdValue);
                    testStepAssert.isTrue(Float.parseFloat(firstValue)==totalEarnings,"First value should be the sum of second & third value","First value is not the sum of second & third value");
                    break;

            }
        }


        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }


    }
    @Then("^The \"([^\"]*)\" icon should be displayed for the completed delivery$")
    public void the_something_icon_should_be_displayed_for_the_completed_delivery(String strArg1) throws Throwable {
        try{
            Thread.sleep(2000);
            testStepAssert.isTrue(action.isElementPresent(earningsPage.Icon_IOnCompletedDelivery()),"The 'i' Icon should be displayed for the completed delivery","The 'i' Icon is displayed for the completed delivery","The 'i' Icon is not  displayed for the completed delivery");
        }
        catch (Exception e){
            logger.error("Error performing step", e);
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
}
