package com.bungii.android.stepdefinitions.Driver;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.EstimatePage;
import com.bungii.android.pages.driver.*;
import com.bungii.android.pages.driver.TripAlertSettingsPage;
import com.bungii.android.pages.driver.DriverHomePage;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class HomePageSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(HomePageSteps.class);
    ActionManager action = new ActionManager();
    DriverHomePage driverHomePage = new DriverHomePage();
    AccountsPage driverAccountPage = new AccountsPage();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    GeneralUtility utility = new GeneralUtility();
    TripAlertSettingsPage tripAlertSettingsPage = new TripAlertSettingsPage();
    EstimatePage estimatePage = new EstimatePage();
    EarningsPage earningsPage = new EarningsPage();

    @And("^I Select \"([^\"]*)\" from driver App menu$")
    public void i_select_something_from_driver_app_memu(String menuItem) {
        try {
            Thread.sleep(15000);
            if(action.isAlertPresent()){
                if(action.isElementPresent(Page_BungiiRequest.findElement("com.bungii.driver:id/appCompatTextView21", PageBase.LocatorType.Id,true))){
                    if (action.getText(Page_BungiiRequest.Alert_Msg_Stay_Online()).contains(PropertyUtility.getMessage("driver.alert.stay.online"))) {
                        action.click(Page_BungiiRequest.Button_Stay_Online());
                    }
                }
            }
            if(action.isElementPresent(Page_BungiiRequest.Alert_NewBungiiRequest(true))){
                action.click(Page_BungiiRequest.Button_No_Thanks());
            }
            boolean isClicked = false;
            if(action.isElementPresent(driverHomePage.Button_NavigationBar(true)))
                action.click(driverHomePage.Button_NavigationBar());
            else{
                if (action.isElementPresent(estimatePage.Alert_ConfirmRequestMessage(true))) {
                    action.click(estimatePage.Button_RequestConfirmCancel());
                    logger.detail("Push notification alert was shown on driver dashboard");
                }
                action.click(driverHomePage.Button_NavigationBar());
            }
            List<WebElement> elements = driverHomePage.Button_NavigationBarText();
            if (action.isAlertPresent()) {
                if (action.getText(Page_BungiiRequest.Alert_Msg(true)).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))) {
                    utility.acceptNotificationAlert();
                    if (action.isAlertPresent()) {
                        if (action.isElementPresent(estimatePage.Button_OK(true)))
                            action.click(estimatePage.Button_OK());
                    }
                } else {
                    action.click(Page_BungiiRequest.AlertButton_Cancel());
                }

            }
            for (WebElement element : elements) {
                if (element.getText().equalsIgnoreCase(menuItem)) {
                    action.click(element);
                    isClicked = true;
                    break;
                }
            }
            if (!isClicked) {
                action.scrollToBottom();
                isClicked = utility.clickDriverMenu(menuItem);
            }
            testStepAssert.isTrue(isClicked, "I should able to click " + menuItem, "Not able to select " + menuItem + " from App menu");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I Select \"([^\"]*)\" from ACCOUNT menu$")
    public void i_select_something_from_account_menu(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "ACCOUNT INFO":
                    action.click(driverAccountPage.Link_Account_Info());
                    break;
                case "ALERT SETTINGS":
                    action.click(driverAccountPage.Link_Account_Settings());
                    break;
                case "PRIVACY POLICY":
                    action.click(driverAccountPage.Link_Privacy_Policy());
                    break;
                case "LOGOUT":
                    action.click(driverAccountPage.Link_Logout());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I should be navigated to Home screen on driver app$")
    public void i_should_be_navigated_to_home_screen_on_driver_app() throws Throwable {
        try {
            Thread.sleep(2000);
            action.waitUntilIsElementExistsAndDisplayed(driverHomePage.Generic_HeaderElement(true));
            String headerText = action.getText(driverHomePage.Generic_HeaderElement());
            if(headerText.contains("Your $5 Guarantee is waiting")){
                String onlineOrOffline = action.getText(driverHomePage.Text_OnlineOrOffline());
                cucumberContextManager.setScenarioContext("DriverStatusOnLoggedIn",onlineOrOffline);
            }
            else {
                cucumberContextManager.setScenarioContext("DriverStatusOnLoggedIn",headerText);
            }
            String getNaviagationText =(String) cucumberContextManager.getScenarioContext("DriverStatusOnLoggedIn");
            boolean isHomePage = getNaviagationText.equals("OFFLINE") || getNaviagationText.equals("ONLINE");
            testStepAssert.isTrue(isHomePage, "I should be navigated to Driver Home screen",  getNaviagationText + " screen is displayed instead of Driver Home screen");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @When("^I click \"([^\"]*)\" button on Home screen on driver app$")
    public void i_click_something_button_on_home_screen_on_driver_app(String button) throws Throwable {
        try {
            switch (button) {
                case "OFFLINE":
                case "ONLINE":
                    Thread.sleep(4000);
                    action.click(driverHomePage.Button_OnlineOffline());
                    Thread.sleep(4000);
                    break;
                case "Available Bungiis":
                    action.click(driverHomePage.Link_AvailableTrips());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("I should able to click"+button,"I was able to click "+button,true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^the status of the driver should be \"([^\"]*)\"$")
    public void the_status_of_the_driver_should_be_something(String status) throws Throwable {
        try {
            switch (status.toUpperCase()) {
                case "OFFLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Generic_HeaderElement()), PropertyUtility.getMessage("driver.home.title.offline"));
                    testStepVerify.isEquals(action.getText(driverHomePage.Button_OnlineOffline()), PropertyUtility.getMessage("driver.home.offline"));
                    break;
                case "ONLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Generic_HeaderElement()), PropertyUtility.getMessage("driver.home.title.online"));
                    testStepVerify.isEquals(action.getText(driverHomePage.Button_OnlineOffline()), PropertyUtility.getMessage("driver.home.online"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^The \"([^\"]*)\" for \"([^\"]*)\" driver should be correctly displayed$")
    public void the_something_for_something_driver_should_be_correctly_displayed(String info, String driver) throws Throwable {
        try {
            String driverPhoneNumber, driverName, driverVehicle;
            if (driver.toLowerCase().equals("valid")) {
                driverPhoneNumber = PropertyUtility.getDataProperties("valid.driver.phone");
                driverName = PropertyUtility.getDataProperties("valid.driver.name");
                driverVehicle = PropertyUtility.getDataProperties("android.driver.vehicle");
            } else {
                driverPhoneNumber = PropertyUtility.getDataProperties("ios.valid.driver2.phone");
                driverName = PropertyUtility.getDataProperties("ios.driver2.name");
                driverVehicle = PropertyUtility.getDataProperties("ios.driver2.vehicle");
            }

            switch (info.toLowerCase()) {
                case "name":
                    testStepVerify.isEquals(action.getText(driverHomePage.Text_DriverName()), driverName);
                    break;
                case "vehicle info":
                    testStepVerify.isEquals(action.getText(driverHomePage.Text_DriverInfo()), driverVehicle);
                    break;
                case "rating":
                    testStepVerify.isElementEnabled(driverHomePage.Text_RattingBar(), "Ratting bar Should be correctly displayed");
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");

            }
        } catch (
                Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^The title of button should change to \"([^\"]*)\" on driver app$")
    public void the_title_of_button_should_change_to_something_on_driver_app(String buttonTitle) throws Throwable {
        try {
            switch (buttonTitle.toUpperCase()) {
                case "ONLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Button_OnlineOffline()), buttonTitle.toUpperCase());
                    break;
                case "GO OFFLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Button_OnlineOffline()), buttonTitle.toUpperCase());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");


            }

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^Info text should be updated$")
    public void info_text_should_be_updated() throws Throwable {
        try {
            testStepVerify.isEquals(action.getText(driverHomePage.Text_DriverName()), PropertyUtility.getMessage("DriverStatusInfo"));
            testStepVerify.isEquals(action.getText(driverHomePage.Text_DriverInfo()), PropertyUtility.getMessage("DriverInfo.android"));
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^The navigation title should change to \"([^\"]*)\"$")
    public void the_navigation_title_should_change_to_something(String navTitle) throws Throwable {
        try {

            switch (navTitle.toUpperCase()) {
                case "ONLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Generic_HeaderElement()), PropertyUtility.getMessage("driver.home.title.online"));
                    break;
                case "OFFLINE":
                    testStepVerify.isEquals(action.getText(driverHomePage.Generic_HeaderElement()), PropertyUtility.getMessage("driver.home.title.offline"));
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I update sms setting of \"([^\"]*)\" to \"([^\"]*)\" to \"([^\"]*)\"$")
    public void i_update_sms_setting_of_sunday_to_something_to_something(String strArg0, String strArg1, String strArg2) {
        try {
            switch (strArg0.toUpperCase()) {
                case "SUNDAY":
                    action.click(tripAlertSettingsPage.Text_Sunday());
                    break;
                case "TODAY":

                    String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat  simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
                    simpleDateformat.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
                    String dayOfWeek=simpleDateformat.format(calendar.getTime());
                    WebElement element=SetupManager.getDriver().findElement(By.xpath("//*[@resource-id='com.bungii.driver:id/text_settings_row_text_day' and @text='"+dayOfWeek+"']"));
                    action.click(element);
                    break;
                default:
                    String from = (strArg1.split(":")[0]);
                    from = from.startsWith("0") ? from.substring(1) : from;
                    String test=((strArg1.split(" ")[0]).split(":")[1]).trim();
                    String test1=(strArg1.split(" ")[1]).trim();

                    String toHour = (strArg2.split(":")[0]);
                    toHour = toHour.startsWith("0") ? toHour.substring(1) : toHour;
                    String tohour1=((strArg2.split(" ")[0]).split(":")[1]).trim();
                    String tohour2=(strArg2.split(" ")[1]).trim();

                    WebElement element1=SetupManager.getDriver().findElement(By.xpath("//*[@resource-id='com.bungii.driver:id/text_settings_row_text_day' and @text='"+strArg0+"']"));
                    action.click(element1);
                    break;
            }
            String from = (strArg1.split(":")[0]);
            from = from.startsWith("0") ? from.substring(1) : from;
            String test=((strArg1.split(" ")[0]).split(":")[1]).trim();
            String test1=(strArg1.split(" ")[1]).trim();

            String toHour = (strArg2.split(":")[0]);
            toHour = toHour.startsWith("0") ? toHour.substring(1) : toHour;
            String tohour1=((strArg2.split(" ")[0]).split(":")[1]).trim();
            String tohour2=(strArg2.split(" ")[1]).trim();
            log("Updated setting of" + strArg0 + " , to " + strArg1 + "-" + strArg2, "Updated settings of" + strArg0 + " , to " + strArg1 + "-" + strArg2, true);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I update trip setting of \"([^\"]*)\" to \"([^\"]*)\" to \"([^\"]*)\"$")
    public void i_update_trip_setting_of_sunday_to_something_to_something(String strArg0, String strArg1, String strArg2) {

        i_update_sms_setting_of_sunday_to_something_to_something(strArg0, strArg1, strArg2);
    }

    @And("^I update kansas driver todays trip alert setting to outside current time$")
    public void i_update_todays_trip_alert_setting_of_today_to_outside_current_time() throws Throwable {
        cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE","Kansas");
        String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
        Calendar calendar = Calendar.getInstance();
        //current time plus 60 mins
        calendar.add(Calendar.MINUTE, +60);
        DateFormat formatter = new SimpleDateFormat("hh:mm aa");
        formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
        String strdate = formatter.format(calendar.getTime());
        SimpleDateFormat  simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        simpleDateformat.setTimeZone(TimeZone.getTimeZone(geofenceLabel));

        String dayOfWeek=simpleDateformat.format(calendar.getTime());
        i_update_sms_setting_of_sunday_to_something_to_something(dayOfWeek,strdate,"11:59 PM");
        Thread.sleep(5000);
    }

    @Then("^I check if driver cut is reflected$")
    public void i_check_if_driver_cut_is_reflected() throws Throwable {
        try{
            String expectedDriverCharges = (String) cucumberContextManager.getScenarioContext("NEW_DRIVER_CUT");
            String driverCharges =action.getText(driverHomePage.Text_OverriddenPrice());
            String actualDriverCharges = driverCharges.substring(1);
            testStepAssert.isEquals(actualDriverCharges, expectedDriverCharges,
                    "Driver Charges should be overridden",
                    "Driver Charges are overridden",
                    "Driver Charges are not overridden");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }
    @Then("^I check if \"([^\"]*)\" icon is displayed$")
    public void i_check_if_something_icon_is_displayed(String icon) throws Throwable {
        try{
            switch (icon){
                case "$":
                    testStepAssert.isElementDisplayed(driverHomePage.Icon_Referral(),
                            "The referral icon $ should be displayed",
                            "The referral icon $ is displayed",
                            "The referral icon $ is not displayed");
                    break;
                case "i":
                    testStepAssert.isElementDisplayed(driverHomePage.Icon_Earnings(),
                            "The referral icon i should be displayed",
                            "The referral icon i is displayed",
                            "The referral icon i is not displayed");
                    break;
            }
            log("I should be able to see the icon","I am able to see the icon",false);

        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I verify the elements on driver referral page$")
    public void i_verify_the_elements_on_driver_referral_page() throws Throwable {
        try{
            String expectedSubHeader= PropertyUtility.getMessage("subHeader.driver.referral.page");
            testStepAssert.isEquals(driverHomePage.Text_SubHeader().getText(),expectedSubHeader,
                    "The sub-header should be displayed",
                    "The sub-header is displayed",
                    "The sub-header is not displayed");

            testStepAssert.isElementDisplayed(driverHomePage.Icon_DollarSign(),
                    "The dollar sign should be displayed",
                    "The dollar sign is displayed",
                    "The dollar sign is not displayed");

            String expectedInstructions= PropertyUtility.getMessage("message.driver.referral.page");
            testStepAssert.isEquals(driverHomePage.Text_Instructions().getText(),expectedInstructions,
                    "The correct instructions should be displayed",
                    "The correct instructions are displayed",
                    "The correct instructions are not displayed");

            testStepAssert.isElementDisplayed(driverHomePage.Tab_ReferralCode(),
                    "The referral code should be displayed",
                    "The referral code is displayed",
                    "The referral code is not displayed");

            testStepAssert.isElementDisplayed(driverHomePage.Text_TapToCopy(),
                    "Tab to copy should be displayed",
                    "Tab to copy is displayed",
                    "Tab to copy is not displayed");

            testStepAssert.isElementDisplayed(driverHomePage.Button_Share(),
                    "Share button should be displayed",
                    "Share button is displayed",
                    "Share button is not displayed");

            testStepAssert.isElementDisplayed(driverHomePage.Text_ShareToContacts(),
                    "The tab Share to contacts should be displayed",
                    "The tab Share to contacts is displayed",
                    "The tab Share to contacts is not displayed");
            testStepAssert.isElementDisplayed(driverHomePage.Text_ShareOnMedia(),
                    "The tab Share on social media should be displayed",
                    "The tab Share on social media is displayed",
                    "The tab Share on social media is not displayed");
            testStepAssert.isElementDisplayed(driverHomePage.Text_MoreInformation(),
                    "The tab for more information should be displayed",
                    "The tab for more information is displayed",
                    "The tab for more information is not displayed");
            testStepAssert.isElementDisplayed(driverHomePage.Text_ReferralHistory(),
                    "The tab for referral history should be displayed",
                    "The tab for referral history is displayed",
                    "The tab for referral history is not displayed");

            action.scrollToBottom();
            String expectedFooter= PropertyUtility.getMessage("footer.driver.referral.page");
            testStepAssert.isEquals(driverHomePage.Text_Footer().getText(),expectedFooter,
                    "The correct footer should be displayed",
                    "The correct footer is displayed",
                    "The correct footer is not displayed");

        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I check if referral icon is not shown$")
    public void i_check_if_referral_icon_is_not_shown() throws Throwable {
        try{
            testStepVerify.isElementNotDisplayed(driverHomePage.Icon_Referral(true),
                    "Referral icon should not be displayed",
                    "Referral icon is not displayed",
                    "Referral icon is displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I verify the elements of home page$")
    public void i_verify_the_elements_of_home_page() throws Throwable {
        try{
            testStepAssert.isElementDisplayed(driverHomePage.Tab_ReferralMessage(),
                    "The tab containing referral instructions should be displayed on home page",
                    "The tab containing referral instructions is displayed on home page",
                    "The tab containing referral instructions is not displayed on home page");
            testStepAssert.isElementDisplayed(driverHomePage.Text_ReferralHeader(),
                    "The referral header should be displayed on home page",
                    "The referral header is displayed on home page",
                    "The referral header is not displayed on home page");
            testStepAssert.isElementDisplayed(driverHomePage.Text_ReferralSubHeader(),
                    "The referral sub header should be displayed on home page",
                    "The referral sub header is displayed on home page",
                    "The referral sub header is not displayed on home page");
            testStepAssert.isElementDisplayed(driverHomePage.Button_Invite(),
                    "The invite button should be displayed on home page",
                    "The invite button is displayed on home page",
                    "The invite button is not displayed on home page");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I click on \"([^\"]*)\" link$")
    public void i_click_on_something_link(String name) throws Throwable {
        try{
            switch (name){
                case "Referral history":
                    action.scrollToBottom();
                    action.click(driverHomePage.Text_ReferralHistory());
                    break;
            }
            log("I should be able to click on link","I am able to click on link",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I check if the amount is updated on invite screen$")
    public void i_check_if_the_amount_is_updated_on_invite_screen() throws Throwable {
        try{
            String newReferralAmount= (String) cucumberContextManager.getScenarioContext("NEW_REFERRAL_AMT");
            String actualDisplayed = driverHomePage.Text_SubHeader().getText();
            testStepAssert.isTrue(actualDisplayed.contains(newReferralAmount),
                    "The referral amount should be updated on the invite screen",
                    "The referral amount is not updated on the invite screen");

        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I check if \"([^\"]*)\" button is displayed$")
    public void i_check_if_something_button_is_displayed(String button) throws Throwable {

        try{
            switch (button){
                case "Branch app":
                    testStepAssert.isElementDisplayed(earningsPage.Button_BranchWallet(),
                            "The branch app button should be displayed",
                            "The branch app button is displayed",
                            "The branch app button is not displayed");
                    break;
                case "changed payment":
                    String defaultMethod= (String) cucumberContextManager.getScenarioContext("DEFAULT_PAYMENT");
                    testStepAssert.isEquals(earningsPage.Button_PaymentSetting().getAttribute("text"),defaultMethod,
                            "The selected payment method should not updated to default when driver logs out from app",
                            "The selected payment method is not updated to default when driver logs out from app",
                            "The selected payment method is updated to default when driver logs out from app");
                    break;
            }
            log("I should be able to check if "+button+" button is displayed","I am able to check if "+button+" button is displayed",false);
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^I should be navigated to \"([^\"]*)\"$")
    public void i_should_be_navigated_to_something(String screen) throws Throwable {
        try {
            switch (screen){
                case "default browser":
                    Thread.sleep(2000);
                    testStepAssert.isElementDisplayed(earningsPage.Screen_DefaultBrowser(),
                            "I should be on browser screen for branch app.",
                            "I am on browser screen for branch app.",
                            "I am not navigated to browser screen for branch app.");
                    break;
            }
            log("I should be able to navigate to "+screen,"I am able to navigate to "+screen,false);
        }
        catch (Exception e){
            logger.error("Error performing step", e);
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

}