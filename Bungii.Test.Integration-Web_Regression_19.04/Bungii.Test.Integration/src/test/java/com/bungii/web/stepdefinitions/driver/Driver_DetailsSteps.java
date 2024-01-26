package com.bungii.web.stepdefinitions.driver;

import com.bungii.SetupManager;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.api.stepdefinitions.BungiiSteps;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.*;
import com.bungii.web.pages.admin.Admin_GeofencePage;
import com.bungii.web.pages.driver.*;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Driver_DetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(Driver_DetailsSteps.class);
    Driver_ForgotPasswordPage Page_ForgotPassword = new Driver_ForgotPasswordPage();
    Driver_VerifyPhonePage Page_VerifyPhone = new Driver_VerifyPhonePage();
    Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage();
    Driver_DetailsPage Page_Driver_Details = new Driver_DetailsPage();
    Driver_PickUpInfoPage Page_Driver_PickupInfo = new Driver_PickUpInfoPage();
    Driver_DocumentationPage Page_Driver_Doc = new Driver_DocumentationPage();
    Driver_BankDetailsPage Page_Driver_Bank = new Driver_BankDetailsPage();
    Driver_TermsPage Page_Driver_Terms = new Driver_TermsPage();
    Driver_VideoTrainingPage Page_Driver_Video = new Driver_VideoTrainingPage();

    DriverRegistrationSteps driverRegistrationSteps = new DriverRegistrationSteps();
    Driver_ViewDetailsPage Page_Driver_ViewDetails = new Driver_ViewDetailsPage();

    Admin_GeofencePage geofencePage = new Admin_GeofencePage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    BungiiSteps bungiiSteps = new BungiiSteps();

    @When("^I enter \"([^\"]*)\" data on Driver Details page$")
    public void i_enter_something_data_on_driver_details_page(String strArg1) throws Throwable {
        String driverImagePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),
                                PropertyUtility.getImageLocations("DRIVER_IMAGE"));
        switch (strArg1) {
            case "valid":
                action.clearSendKeys(Page_Driver_Details.TextBox_StreetAddress(), PropertyUtility.getDataProperties("DriverStreet"));
                Thread.sleep(5000);
                try{
                    JavascriptExecutor executor = (JavascriptExecutor) SetupManager.getDriver();
                    executor.executeScript("arguments[0].click();", Page_Driver_Details.List_StreetAddress());
                    if(Page_Driver_Details.TextBox_City().getAttribute("value").equalsIgnoreCase(""))
                    {
                        logger.detail("Google places API limit Exhausted.. Proceeding with manually entering address details");
                        action.clearSendKeys(Page_Driver_Details.TextBox_City(), PropertyUtility.getDataProperties("DriverCity"));
                        action.selectElementByText(Page_Driver_Details.DropDown_State(), PropertyUtility.getDataProperties("DriverState"));
                        action.clearSendKeys(Page_Driver_Details.TextBox_ZipCode(), PropertyUtility.getDataProperties("DriverZipCode"));
                    }
                }
                catch(Exception ex) {
                   // action.JavaScriptClick(Page_Driver_Details.List_StreetAddress());
                  //  Thread.sleep(2000);
                    logger.detail("Google places API limit Exhausted.. Proceeding with manually entering address details");
                    action.clearSendKeys(Page_Driver_Details.TextBox_City(), PropertyUtility.getDataProperties("DriverCity"));
                    action.selectElementByText(Page_Driver_Details.DropDown_State(), PropertyUtility.getDataProperties("DriverState"));
                    action.clearSendKeys(Page_Driver_Details.TextBox_ZipCode(), PropertyUtility.getDataProperties("DriverZipCode"));
                }
                action.click(Page_Driver_Details.CheckBox_Age18());
                action.click(Page_Driver_Details.CheckBox_Lift75());
                action.click(Page_Driver_Details.CheckBox_DrivingExp());

                action.clearSendKeys(Page_Driver_Details.TextArea_Other(), PropertyUtility.getDataProperties("DriverOther"));

                action.clearSendKeys(Page_Driver_Details.TextArea_Occupation(), PropertyUtility.getDataProperties("DriverOccupation"));
               // action.clearSendKeys(Page_Driver_Details.TextBox_SSN(), PropertyUtility.getDataProperties("DriverSSN"));
                action.clearSendKeys(Page_Driver_Details.TextBox_Birthday(), PropertyUtility.getDataProperties("DriverBirthday"));
                action.selectRandomDropdown(Page_Driver_Details.DropDown_Info());
                utility.addImageInDropZone(Page_Driver_Details.DropZoneHiddenFileTag_ProfileImage(), driverImagePath);

                action.click(Page_Driver_Details.Button_Crop());
                action.invisibilityOfElementLocated(Page_Driver_Details.Wrapper_Spinner());

                testStepVerify.isElementEnabled(Page_Driver_Details.Link_RemoveFile(), "driver remove link should be present", "driver remove link is present", "driver remove link is not present");


                action.click(Page_Driver_Details.CheckBox_Consent());

                break;

            case "invalid":
                action.clearSendKeys(Page_Driver_Details.TextBox_ZipCode(), PropertyUtility.getDataProperties("DriverZipCode_Invalid"));

                action.click(Page_Driver_Details.Link_ClearAll());
                action.click(Page_Driver_Details.Link_SelectAll());
                testStepVerify.isElementSelected(Page_Driver_Details.CheckBox_SunEve(), "Checkbox for sunday evening should be selected ", " Checkbox for sunday evening was selected", "Checkbox for sunday evening is not selected");
                action.click(Page_Driver_Details.Link_ClearAll());
                testStepVerify.isElementNotSelected(Page_Driver_Details.CheckBox_WedAftrn(), "Checkbox for Wed afternoon should not be selected ", " Checkbox for Wed afternoon is not selected", "Checkbox for Wed afternoon is selected");

                utility.addImageInDropZone(Page_Driver_Details.DropZoneHiddenFileTag_ProfileImage(), driverImagePath);
                action.click(Page_Driver_Details.Button_Crop());
                action.invisibilityOfElementLocated(Page_Driver_Details.Wrapper_Spinner());

                action.click(Page_Driver_Details.Link_RemoveFile());

                //action.clearSendKeys(Page_Driver_Details.TextBox_SSN(), PropertyUtility.getDataProperties("DriverSSN_Invalid"));

                action.clearSendKeys(Page_Driver_Details.TextBox_Birthday(), PropertyUtility.getDataProperties("Date_Invalid"));

                break;

            default:
                break;
        }
        log("I enter "+strArg1+" data on Driver Details page","I have entered "+strArg1 +" data on Driver Details page", false);

    }

    @Then("^I should see individual field validations on \"([^\"]*)\" page$")
    public void i_should_see_individual_field_validations_on_something_page(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "driver Details":
                    testStepVerify.isElementTextEquals(Page_Driver_Details.Err_ZipCode(), PropertyUtility.getMessage("Err_DriverDetails_ZipCode"));
                    testStepVerify.isElementTextEquals(Page_Driver_Details.Err_Other(), PropertyUtility.getMessage("Err_DriverDetails_Other"));
                    testStepVerify.isElementEnabled(Page_Driver_Details.Link_DriverPicture(), "driver Picture should be displayed", "driver Picture is displayed", "driver picture was not displayed");
                   // testStepVerify.isElementTextEquals(Page_Driver_Details.Err_SSN(), PropertyUtility.getMessage("Err_DriverDetails_SSN"));
                    testStepVerify.isElementTextEquals(Page_Driver_Details.Err_Birthday(), PropertyUtility.getMessage("Err_DriverDetails_Birthday"));
                    break;

                case "Pickup Information - i":
                    testStepVerify.isElementTextEquals(Page_Driver_PickupInfo.Err_AddTruckImages(), PropertyUtility.getMessage("Err_Add1MoreTruckImage"));
                    break;

                case "Pickup Information - ii":
                    testStepVerify.isElementTextEquals(Page_Driver_PickupInfo.Err_AddTruckImages(), PropertyUtility.getMessage("Err_Add2MoreTruckImage"));
                    break;

                case "date on Documentation":
                    testStepVerify.isElementTextEquals(Page_Driver_Doc.Err_LicenseExpiry(), PropertyUtility.getMessage("Err_InvalidDate"));
                    testStepVerify.isElementTextEquals(Page_Driver_Doc.Err_InsuranceExpiry(), PropertyUtility.getMessage("Err_InvalidDate"));
                    break;

                case "Documentation":
                    testStepVerify.isElementTextEquals(Page_Driver_Doc.Err_LicenseExpiry(), PropertyUtility.getMessage("Err_InvalidLicenseExpiryDate"));
                    testStepVerify.isElementTextEquals(Page_Driver_Doc.Err_InsuranceExpiry(), PropertyUtility.getMessage("Err_InvalidInsuranceExpiryDate"));
                    testStepVerify.isElementTextEquals(Page_Driver_Doc.Err_LicenseNumber(), PropertyUtility.getMessage("Err_LicenseNumber"));
                    break;

                case "bank account on Bank Details":
                    testStepVerify.isElementTextEquals(Page_Driver_Bank.Err_BankAccNumber(), PropertyUtility.getMessage("Err_ShortBankAccount"));
                    break;

                case "Bank Details":
                    testStepVerify.isElementTextEquals(Page_Driver_Bank.Err_RoutingNumber(), PropertyUtility.getMessage("Err_InvalidRoutingNumber"));
                    testStepVerify.isElementTextEquals(Page_Driver_Bank.Err_BankAccNumber(), PropertyUtility.getMessage("Err_InvalidBankAccount"));
                    break;

                default:
                    break;
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I uncheck \"([^\"]*)\" checkbox$")
    public void i_uncheck_something_checkbox(String strArg1) throws Throwable {
       try{ if(Page_Driver_Terms.CheckBox_Agree().isSelected())
            action.click(Page_Driver_Terms.CheckBox_Agree());
        log("I uncheck "+strArg1+" checkbox","I have uncheck "+strArg1 +" checkbox", false);
       } catch(Exception e){
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step should be successful", "Error performing step,Please check logs for more details",
                   true);
       }

    }

    @And("^I check \"([^\"]*)\" checkbox$")
    public void i_check_something_checkbox(String strArg1) throws Throwable {
        try{
        if(!Page_Driver_Terms.CheckBox_Agree().isSelected())
            action.click(Page_Driver_Terms.CheckBox_Agree());
        log("I check "+strArg1+" checkbox","I have check "+strArg1 +" checkbox", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I click Next on \"([^\"]*)\" page$")
    public void i_click_next_on_something_page(String strArg1) throws Throwable {
        try{
        switch (strArg1) {
            case "Driver Details":
                Thread.sleep(3000);
                action.waitUntilIsElementExistsAndDisplayed(Page_Driver_Details.Button_DetailsNext(), (long) 5000);
                action.click(Page_Driver_Details.Button_DetailsNext());
                break;
            case"Vehicle Information":
            case "Pickup Information":
                action.click(Page_Driver_PickupInfo.Button_PickUpNext());
                break;
            case "Documentation":
                Thread.sleep(3000);
                action.waitUntilIsElementExistsAndDisplayed(Page_Driver_Doc.Button_DocNext(), (long) 5000);
                action.click(Page_Driver_Doc.Button_DocNext());
                break;
            case "Bank Details":
                action.click(Page_Driver_Bank.Button_BankNext());
                break;
            case "Terms & Conditions":
                action.click(Page_Driver_Terms.Button_TermsNext());
                break;
            case "Privacy Policy":
                action.click(Page_Driver_Terms.Button_PrivacyPolicyNext());
                break;
            case "Video Training":
                action.click(Page_Driver_Video.Button_VideoNext());
                break;
            case "Driver Basic Info":
                Thread.sleep(3000);
                action.waitUntilIsElementExistsAndDisplayed(Page_Driver_Details.Button_BasicInfo(), (long) 5000);
                action.click(Page_Driver_Details.Button_BasicInfo());
                break;
            default:
                break;
        }
        log("I should able to click " + strArg1 + " page", "I clicked " + strArg1 + " page", true);
        }
        catch(Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Driver should log in to driver portal", "Driver is not signed in due to error.", true);
        }
    }

    @Then("^I should see blank fields validation on \"([^\"]*)\" page$")
    public void i_should_see_blank_fields_validation_on_something_page(String strArg1) throws Throwable {
        try{
            switch (strArg1) {
                case "Forgot Password":
                    testStepAssert.isElementTextEquals(Page_ForgotPassword.Err_ForgotPass_BlankField(), PropertyUtility.getMessage("Err_Pages_BlankFields"), PropertyUtility.getMessage("Err_Pages_BlankFields") + " should be displayed", PropertyUtility.getMessage("Err_Pages_BlankFields") + " is displayed", PropertyUtility.getMessage("Err_Pages_BlankFields") + " was not displayed");
                    break;
                case "Verify your phone":
                    testStepAssert.isElementTextEquals(Page_VerifyPhone.Err_VerifyPhone_BlankField(), PropertyUtility.getMessage("Err_Pages_BlankFields"),"Verify your phone should be displayed","Verify your phone is displayed","Verify your phone is not displayed");
                    break;
                case "driver Details":
                    testStepAssert.isElementTextEquals(Page_Driver_Details.Err_DriverDetails_AllBlank(), PropertyUtility.getMessage("Err_Pages_BlankFields"),"driver details should be displayed","driver details is displayed","driver details is not displayed");
                    break;
                case "Pickup Information":
                    testStepAssert.isElementTextEquals(Page_Driver_PickupInfo.Err_PickupInfo_AllBlank(), PropertyUtility.getMessage("Err_Pages_BlankFields"),"Pickup Information should be displayed","Pickup Information is displayed","Pickup Information is not displayed");
                    break;
                case "Documentation":
                    testStepAssert.isElementTextEquals(Page_Driver_Doc.Err_Documentation_AllBlank(), PropertyUtility.getMessage("Err_Pages_BlankFields"),"Documentation should be displayed","Documentation is displayed","Documentation is not displayed");
                    break;
                case "Bank Details":
                    testStepAssert.isElementTextEquals(Page_Driver_Bank.Err_BankDetails_AllBlank(), PropertyUtility.getMessage("Err_Pages_BlankFields"),"Bank Details should be displayed","Bank Details is displayed","Bank Details is not displayed");
                    break;
                case "Terms & Conditions":
                    testStepAssert.isElementTextEquals(Page_Driver_Terms.Err_Terms(), PropertyUtility.getMessage("Err_Pages_BlankFields"),"Terms & Conditions should be displayed","Terms & Conditions is displayed","Terms & Conditions is not displayed");
                    break;
                case "Video Training":
                    testStepAssert.isElementTextEquals(Page_Driver_Video.Err_Video(), PropertyUtility.getMessage("Err_Pages_BlankFields"),"Video Training should be displayed","Video Training is displayed","Video Training is not displayed");
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


    @And("^I update the rejected \"([^\"]*)\" field$")
    public void i_update_the_rejected_something_field(String strArg1) throws Throwable {
       try{ driverRegistrationSteps.i_navigate_to_something("Driver Details");
        action.JavaScriptClear(Page_Driver_Details.Textbox_DriverDetails_DOB());
        action.sendKeys(Page_Driver_Details.Textbox_DriverDetails_DOB(),"01/01/1992");
        log("I update the rejected DOB field" ,
                "I have updated the rejected DOB field");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I update the accepted \"([^\"]*)\" field$")
    public void i_update_the_accepted_something_field(String str){
        try{
        //action.JavaScriptClear(Page_Driver_Details.Textbox_DriverDetails_SSN());
       // action.sendKeys(Page_Driver_Details.Textbox_DriverDetails_SSN(),"1111111111");
        log("I update the approved SSN field" ,
                "As a part of CORE-1453, SSN field cannot be viewed or edited");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I submit the updated application$")
    public void i_submit_the_updated_application() throws Throwable {
        try{action.click(Page_Driver_Details.Button_Submit());
        action.click(Page_Driver_Details.Button_ConfirmSubmit());
        log("I can submit the updated application" ,
                "I have submitted the updated application");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I logout of driver portal$")
    public void i_logout_of_driver_portal() throws Throwable {
        try{
            action.waitUntilIsElementExistsAndDisplayed(Page_Driver_Details.Link_Logout(),(long) 5000);
            Page_Driver_Details.Link_Logout().click();
            log("I should be logged out" ,
                    "I am logged out");
        }catch (StaleElementReferenceException e){ }

    }

    @Then("^The 'My Stats' section should be shown on the Dashboard page$")
    public void the_my_stats_section_should_be_shown_on_the_dashboard_page() throws Throwable {
        testStepAssert.isElementDisplayed(Page_Driver_ViewDetails.Label_Header_MyStats(),"My Stats section should be displayed", "My Stats sesction is displayed","My Stats section is not displayed");
    }

    @And("^Below stats are displayed correctly$")
    public void below_stats_are_displayed_correctly( DataTable data) throws Throwable {
        try{
        List<Map<String, String>> DataList = data.asMaps();
        int i = 0;
        cucumberContextManager.setScenarioContext("TOTAL_TRIPS", Page_Driver_ViewDetails.Label_TotalTripsCount().getText());
        while (i < DataList.size()) {
            String statistics = DataList.get(i).get("Statistics").trim();
            String xpath = String.format("//p[contains(text(),'%s')]/following-sibling::h3",statistics);
            if (statistics.equals("Total Earnings per year"))
                testStepAssert.isElementDisplayed(Page_Driver_ViewDetails.Label_Statistics_Total_Earnings_Per_Year(),statistics + " should be displayed" , statistics+ " is displayed",statistics + " is not displayed");
            else
             testStepAssert.isElementDisplayed(Page_Driver_ViewDetails.Label_Statistics(xpath),statistics + " should be displayed",statistics+ " is displayed", statistics + " is not displayed");
            i++;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The My Stats section should be updated$")
    public void the_my_stats_section_should_be_updated() throws Throwable {
        try{
            SetupManager.getDriver().navigate().refresh();
        driverRegistrationSteps.i_click_something_on_driver_portal("LOG IN link");
        String driverPhone = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
        driverRegistrationSteps.i_enter_driver_phone_number_as_something_and_valid_password(driverPhone);
        driverRegistrationSteps.i_click_something_on_driver_portal("LOG IN button");

        String driverRef = DbUtility.getDriverReference(driverPhone);


        //Verify that Total Trips count is incremented by 1
        String old_count_string = (String) cucumberContextManager.getScenarioContext("OldTripCount");
        int old_count = Integer.parseInt(old_count_string);
        int new_count = old_count + 1 ;
        Boolean isCountIncremented = false;

        String tripCountString = action.getText(Page_Driver_Details.Count_TotalTrips());
        int tripCount = Integer.parseInt(tripCountString);
        if(tripCount==new_count){
            isCountIncremented=true;
        }
        testStepAssert.isTrue(isCountIncremented,"Total Trip count should be incremented", "Total trip count is incremented", "DATA SYNCH ISSUE | Total trip count is not incremented");

        testStepAssert.isElementDisplayed(Page_Driver_Details.Text_TotalTrips(),"Total Trips text should be displayed.","Total Trips text is displayed.","Total Trips text is not displayed.");
        testStepVerify.isEquals(action.getText(Page_Driver_Details.Count_TotalTrips()),String.valueOf(new_count));

        testStepAssert.isElementDisplayed(Page_Driver_Details.Text_TripsMonth(),"Trips/Month text should be displayed","Trips/Month text is displayed","Trips/Month text is not displayed");

        testStepAssert.isElementDisplayed(Page_Driver_Details.Text_EarningsMonth(),"Total Earnings text should be displayed","Total Earnings text is displayed","Total");

         testStepVerify.isElementDisplayed(Page_Driver_Details.Text_MyRating(),"My Rating text should be displayed","My Rating text is display","My Rating text is not displayed");

         testStepVerify.isElementDisplayed(Page_Driver_Details.Text_HoursWorked(),"Hours Worked text should be displayed","Hours Worked text is displayed","Hours Worked text is not displayed");

         testStepVerify.isElementDisplayed(Page_Driver_Details.Text_HoursWorkedQuarterDate(),"Hours worked quarter to date text should be displayed","Hours worked quarter to date text is displayed","Hours worked quarter to date text is not displayed");

         Date currentDate = new Date();

         Date quarterStartDate = getFirstDayOfQuarter(currentDate);
         Date quarterEndDate = getLastDayOfQuarter(currentDate);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

         String QSD = dateFormat.format(quarterStartDate);
         String QED = dateFormat.format(quarterEndDate);

         List <String> valueHoursWorkedQuarterToDate = DbUtility.getHoursWorkedQuarterToDate(driverRef,QSD,QED);
         int sum =0;
         for(int j=0; j<=valueHoursWorkedQuarterToDate.size()-1;j++){
             int value= Integer.parseInt(valueHoursWorkedQuarterToDate.get(j));
             sum= sum + value;
         }

         DecimalFormat frmt = new DecimalFormat();
         frmt.setMaximumFractionDigits(2);
         float total = (float)sum/60;
         String sumValue= frmt.format(total);

         testStepVerify.isEquals(action.getText(Page_Driver_Details.Value_HoursWorkedQuarterDate()),sumValue);

        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I open datepicker and hit enter key$")
    public void i_open_datepicker_and_hit_enter_key() throws Throwable {
        try{
            action.click(Page_Driver_ViewDetails.Calendar_TripsDaterange());
            Thread.sleep(3000);
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
            log("I open datepicker and hit enter key",
                    "I opened datepicker and hit enter key", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^I should see delivery details displayed to the driver$")
    public void i_should_see_delivery_details_displayed_to_the_driver() throws Throwable {
       try{
       String pickupref= (String)cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String amount = "$"+DbUtility.getDriverShare(pickupref);
        String rating = DbUtility.getDriverRatings(pickupref);
        testStepAssert.isElementDisplayed(Page_Driver_ViewDetails.findElements(String.format("//td[text()='%s']/following-sibling::td[contains(text(),'%s')]/following-sibling::td[text()='Processing']",amount,rating), PageBase.LocatorType.XPath).get(0),"My Bungii details should be displayed","My Bungii details is displayed","My Bungii details is not displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @When("^I click on calendar to select date range$")
    public void i_click_on_calendar_to_select_date_range() throws Throwable {
       try{
           action.click(Page_Driver_ViewDetails.Calendar_TripsDaterange());
        log("I select the date range",
                "I selected the date range ", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I can select date range for one year$")
    public void i_can_select_date_range_for_one_year() throws Throwable {
        try{
            while(!Page_Driver_ViewDetails.Calendar_FromDateMonth().getText().equalsIgnoreCase("Jan 2019"))
        {
            action.click(Page_Driver_ViewDetails.Calendar_PreviousMonth());
        }
        action.click(Page_Driver_ViewDetails.Calendar_FromDate());
        while(!Page_Driver_ViewDetails.Calendar_ToDateMonth().getText().equalsIgnoreCase("Jan 2020"))
        {
            action.click(Page_Driver_ViewDetails.Calendar_NextMonth());
        }
        action.click(Page_Driver_ViewDetails.Calendar_ToDate());
        testStepAssert.isElementDisplayed(Page_Driver_ViewDetails.Label_SelectedDateRange(),"Selected date range should be displayed","Selected date range is displayed","Selected date range is not displayed");
        action.click(Page_Driver_ViewDetails.Button_Apply());
        testStepAssert.isElementDisplayed(Page_Driver_ViewDetails.Label_SearchResultDateRange(),"Selected date range should be displayed","Selected date range is displayed","Selected date range is not displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I wait for data to synch$")
    public void i_wait_for_data_to_synch() throws Throwable {
        try{
        Thread.sleep(120000);
        log("I wait for data to synch","I waited for data to synch", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @When("^I edit the Driver$")
    public void i_edit_the_Driver() throws Throwable {
        try{
            action.click(geofencePage.Button_Edit());
            log("I edit the Driver","I have edited the Driver", false);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    private static Date getFirstDayOfQuarter(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3);
        return cal.getTime();
    }

    private static Date getLastDayOfQuarter(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)/3 * 3 + 2);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    @And("I verify all fields on {string} page")
    public void iVerifyAllFieldsOnPage(String page) {
        try{
            switch (page){
                case "Driver Basic Info":
                    testStepAssert.isFalse(Page_Driver_Details.TextBox_FirstName().isEnabled(),"First name should be uneditable on driver portal",
                            "First name is uneditable on driver portal","First name  is editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_Details.TextBox_LastName().isEnabled(),"Last name  should be uneditable on driver portal",
                            "Last name is uneditable on driver portal","Last name editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_Details.TextBox_Email().isEnabled(),"Email  should be uneditable on driver portal",
                            "Email is uneditable on driver portal","Email is editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_Details.TextBox_PhoneNumber().isEnabled(),"Phone number should be uneditable on driver portal",
                            "Phone number is uneditable on driver portal","Phone number is editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_Details.Button_Update().isEnabled(),"Update button should be disabled on driver portal",
                            "Update button is disabled  on driver portal","Update button is enabled on driver portal");
                    break;
                case "Driver Details":
                     testStepAssert.isFalse(Page_Driver_Details.TextBox_StreetAddress().isEnabled(),"Street address should be uneditable on driver portal",
                            "Street address is uneditable on driver portal","Street address is editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_Details.TextBox_City().isEnabled(),"City should be uneditable on driver portal",
                            "City is uneditable on driver portal","City editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_Details.TextBox_State().isEnabled(),"State should be uneditable on driver portal",
                            "State is uneditable on driver portal","State is editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_Details.TextBox_ZipCode().isEnabled(),"Zipcode should be uneditable on driver portal",
                            "Zipcode is uneditable on driver portal","Zipcode is editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_Details.Button_Update().isEnabled(),"Update button should be disabled on driver portal",
                            "Update button is disabled  on driver portal","Update button is enabled on driver portal");
                    break;
                case "Vehicle Information":
                    testStepAssert.isFalse(Page_Driver_PickupInfo.TextBox_PickupMake().isEnabled(),"Vehicle make should be uneditable on driver portal",
                            "Vehicle make is uneditable on driver portal","Vehicle make is editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_PickupInfo.TextBox_PickupModel().isEnabled(),"Vehicle model should be uneditable on driver portal",
                            "Vehicle model is uneditable on driver portal","Vehicle model editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_PickupInfo.DropDown_PickupYear().isEnabled(),"Year should be uneditable on driver portal",
                            "Year is uneditable on driver portal","Year is editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_PickupInfo.TextBox_LicenseNo().isEnabled(),"VehicleNumber should be uneditable on driver portal",
                            "VehicleNumber is uneditable on driver portal","VehicleNumber is editable on driver portal");
                    testStepAssert.isFalse(Page_Driver_Details.Button_Update().isEnabled(),"Update button should be disabled on driver portal",
                            "Update button is disabled  on driver portal","Update button is enabled on driver portal");
                    break;
                case "Documentation":
                    testStepAssert.isTrue(Page_Driver_Doc.TextBox_LicenseNumber().isEnabled(),"License number should be editable on driver portal",
                            "License number is editable on driver portal","License number is not editable on driver portal");
                    testStepAssert.isTrue(Page_Driver_Doc.TextBox_LicenseExpiry().isEnabled(),"License expiry should be editable on driver portal",
                            "License expiry is editable on driver portal","License expiry is not editable on driver portal");
                    testStepAssert.isTrue(Page_Driver_Doc.TextBox_InsuranceExpiry().isEnabled(),"Insurance Expiry should be editable on driver portal",
                            "Insurance Expiry is editable on driver portal","Insurance Expiry is not editable on driver portal");
                    testStepAssert.isTrue(Page_Driver_Details.Button_Update().isEnabled(),"Update button should be enabled on driver portal",
                            "Update button is enabled  on driver portal","Update button is disabled on driver portal");
                    break;
            }
            log("I check for data ","I checked for data ", false);
        }catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
