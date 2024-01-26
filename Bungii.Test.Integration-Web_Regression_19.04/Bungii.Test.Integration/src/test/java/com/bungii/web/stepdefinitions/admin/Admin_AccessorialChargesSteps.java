package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.*;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_DetailsPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.admin.Admin_AccessorialChargesPage;
import com.bungii.web.pages.admin.Admin_LiveTripsPage;
import com.bungii.web.pages.admin.Admin_TripsPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import com.google.common.collect.Ordering;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_AccessorialChargesSteps extends DriverBase {

    private static LogUtility logger = new LogUtility(Admin_AccessorialChargesSteps.class);
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_LiveTripsPage admin_liveTripsPage = new Admin_LiveTripsPage();
    Admin_AccessorialChargesPage admin_accessorialChargesPage= new Admin_AccessorialChargesPage();
    Admin_TripsPage adminTripsPage = new Admin_TripsPage();
    Driver_DetailsPage Page_Driver_Details = new Driver_DetailsPage();
    Driver_DashboardPage driver_DashboardPage = new  Driver_DashboardPage();
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    Admin_LoginPage Page_AdminLogin = new Admin_LoginPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();

    @When("^I add following accessorial charges and save it$")
    public void i_add_following_accessorial_charges_and_save_it(DataTable data) throws Throwable {

        try {
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                String amount = DataList.get(i).get("Amount").trim();
                String feeType = DataList.get(i).get("Fee Type").trim();
                String comment = DataList.get(i).get("Comment").trim();
                if (DataList.get(i).containsKey("Driver Cut")){
                    String driver_cut = DataList.get(i).get("Driver Cut").trim();
                    action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialDriverCut(),driver_cut);
                }
                else {
                    cucumberContextManager.setScenarioContext("TripType", "PartnerTrip");
                }
                Thread.sleep(5000);
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialAmount(), amount);
                action.selectElementByText(admin_accessorialChargesPage.DropDown_AccessorialFeeType(), feeType);
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_Comment(), comment);
                cucumberContextManager.setScenarioContext("NOTE",comment);
                action.click(admin_accessorialChargesPage.Button_Save());
                action.click(admin_accessorialChargesPage.Button_Confirm());
                Thread.sleep(7000);
                i++;
            }
            log("I add following accessorial charges and save it", "I added field values in accessorial charges and saved it", false);

    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("I add following accessorial charges for Duo trip and save it")
    public void iAddFollowingAccessorialChargesForDuoTripAndSaveIt(DataTable data) {
        try {
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                String amount = DataList.get(i).get("Amount").trim();
                String feeType = DataList.get(i).get("Fee Type").trim();
                String comment = DataList.get(i).get("Comment").trim();

                if (DataList.get(i).containsKey("Driver1 Cut")){
                    String driver1_cut = DataList.get(i).get("Driver1 Cut").trim();
                    String driver2_cut = DataList.get(i).get("Driver2 Cut").trim();

                    action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialDriver1Cut(),driver1_cut);
                    action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialDriver2Cut(),driver2_cut);

                }
                Thread.sleep(5000);
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialAmount(), amount);
                action.selectElementByText(admin_accessorialChargesPage.DropDown_AccessorialFeeType(), feeType);
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_Comment(), comment);
                cucumberContextManager.setScenarioContext("NOTE",comment);
                action.click(admin_accessorialChargesPage.Button_Save());
                action.click(admin_accessorialChargesPage.Button_Confirm());
                Thread.sleep(7000);
                i++;
            }
            log("I add following accessorial charges and save it", "I added field values in accessorial charges and saved it", false);

        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see \"([^\"]*)\" section displayed$")
    public void i_should_see_something_section_displayed(String section) throws Throwable {
        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Header_Section(),section, section+" should be displayed", section+" is displayed", section+" is not displayed");
        //removed in Sprint47
        //testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Message_Mandatory(),"Fields marked with * are mandatory.", "Fields marked with * are mandatory. should be displayed", "Fields marked with * are mandatory. is displayed", "Fields marked with * are mandatory. is not displayed");
    }
    @Then("^I should see the following fee type displayed$")
    public void i_should_see_the_following_fee_type_displayed(DataTable data) throws Throwable {
        try {
            Thread.sleep(1000);
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                Thread.sleep(1000);
                String feeType = DataList.get(i).get("Fee Type").trim();
                switch (feeType) {
                    case "Excess Wait Time":
                        Thread.sleep(2000);
                        String[] excessTimeCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(1)).split("-");
                        String excessWaitTimeText = excessTimeCharge[0].trim();
                        testStepAssert.isEquals(excessWaitTimeText,feeType,"Excess Wait time charges should match","Excess Wait time charges  match","Excess Wait time charges dont match");
                        break;
                    case "Cancelation":
                        Thread.sleep(2000);
                        String [] cancellationTimeCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(2)).split("-");
                        String cancelationText = cancellationTimeCharge[0].trim();
                        testStepAssert.isEquals(cancelationText,feeType,"Cancellation charges should match","Cancellation charges match","Cancellation charges dont should match");
                        break;
                    case "Mountainous":
                        Thread.sleep(2000);
                        String [] mountainiousCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(3)).split("-");
                        String mountainiousChargeText = mountainiousCharge[0].trim();
                        testStepAssert.isEquals(mountainiousChargeText,feeType,"Mountainious charges should match","Mountainious charges match","Mountainious charges dont match");
                        break;
                    case "Other":
                        Thread.sleep(2000);
                        String[] otherCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(4)).split("-");
                        String otherChargeText = otherCharge[0].trim();
                        testStepAssert.isEquals(otherChargeText,feeType,"Other charges should match","Other charges match","Other charges dont match");
                        break;
                    case "Additional Mileage":
                        Thread.sleep(2000);
                        String[] additionalMileageCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(5)).split("-");
                        String additionalMileageChargeText = additionalMileageCharge[0].trim();
                        testStepAssert.isEquals(additionalMileageChargeText,feeType,"Additional Mileage charges should match","Additional Mileage charges match","Additional Mileage charges dont match");
                        break;
                    case "Additional Weight / Pallet":
                        Thread.sleep(2000);
                        String[] additionalWeightPalletCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(6)).split("-");
                        String additionalWeightPerPalletChargeText = additionalWeightPalletCharge[0].trim();
                        testStepAssert.isEquals(additionalWeightPerPalletChargeText,feeType,"Additional Weight / Pallet charges should match","Additional Weight / Pallet charges match","Additional Weight / Pallet charges dont match");
                        break;
                    case "Customer Rejected / Returned":
                        Thread.sleep(2000);
                        String[] customerRejectedReturenedCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(7)).split("-");
                        String customerRejectedReturenedChargeText = customerRejectedReturenedCharge[0].trim();
                        testStepAssert.isEquals(customerRejectedReturenedChargeText,feeType,"Customer Rejected / Returned charges should match","Customer Rejected / Returned charges match","Customer Rejected / Returned charges dont match");
                        break;
                    case "Limited Access":
                        Thread.sleep(2000);
                        String[] limitedAccessCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(8)).split("-");
                        String limitedAccessChargeText = limitedAccessCharge[0].trim();
                        testStepAssert.isEquals(limitedAccessChargeText,feeType,"Customer Rejected / Returned charges should match","Customer Rejected / Returned charges match","Customer Rejected / Returned charges dont match");
                        break;
                }
                i++;
            }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see following details in the Accessorial charges section$")
    public void i_should_see_following_details_in_the_accessorial_charges_section(DataTable data) throws Throwable {
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);
        String additionalMileageAmount = dataMap.get("Additional Mileage").trim();
        String additionalWeightPalletAmount = dataMap.get("Additional Weight / Pallet").trim();
        String cancelationAmount = dataMap.get("Cancelation").trim();
        String customerRejectedReturedAmount = dataMap.get("Customer Rejected / Returned").trim();
        String excessWaitTimeAmount = dataMap.get("Excess Wait Time").trim();
        String limitedAccessAmount = dataMap.get("Limited Access").trim();
        String mountainousAmount = dataMap.get("Mountainous").trim();
        String otherAmount = dataMap.get("Other").trim();
        String totalAmount = dataMap.get("Total").trim();
        cucumberContextManager.setScenarioContext("TOTAL_AMOUNT", totalAmount);
        cucumberContextManager.setScenarioContext("OTHER_AMOUNT", otherAmount);

        String[] excessTimeCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(1)).split("-");
        String actualExcessWaitTime = excessTimeCharge[1].trim();
        String [] cancellationTimeCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(2)).split("-");
        String actualCancelation = cancellationTimeCharge[1].trim();
        String [] mountainiousCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(3)).split("-");
        String actualMountainiousCharge = mountainiousCharge[1].trim();
        String[] otherCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(4)).split("-");
        String actualOtherCharge = otherCharge[1].trim();
        String[] additionalMileageCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(5)).split("-");
        String actualAdditionalMileage = additionalMileageCharge[1].trim();
        String[] additionalWeightPalletCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(6)).split("-");
        String actualAdditionalWeightPallet = additionalWeightPalletCharge[1].trim();
        String[] customerRejectedReturedCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(7)).split("-");
        String actualCustomerRejectedRetured = customerRejectedReturedCharge[1].trim();
        String[] limitedAccessCharge = action.getText(admin_accessorialChargesPage.Text_DiffAccessorial(8)).split("-");
        String actualLimitedAccess = limitedAccessCharge[1].trim();
        Thread.sleep(2000);
        testStepAssert.isEquals(actualExcessWaitTime,excessWaitTimeAmount,"Expected excess time charges should match the Actual excess time charges","Expected excess time charges matches the Actual excess time charges","Expected excess time charges doesnt match the Actual excess time charges");
        testStepAssert.isEquals(actualCancelation,cancelationAmount,"Expected cancellation charges should match the Actual cancellation charges","Expected cancellation charges matches match the Actual cancellation charges","Expected cancellation charges doesnt match the Actual cancellation charges");
        testStepAssert.isEquals(actualMountainiousCharge,mountainousAmount,"Expected mountainious charges should match the Actual mountainious charges","Expected mountainious charges matches the Actual mountainious charges","Expected mountainious charges doesnt match the Actual mountainious charges");
        testStepAssert.isEquals(actualOtherCharge,otherAmount,"Expected other charges should match the Actual other charges","Expected other charges matches the Actual other charges","Expected other charges doesnt match the Actual other charges");
        testStepAssert.isEquals(actualAdditionalMileage,additionalMileageAmount,"Additional mileage charges should match the Actual other charges","Additional mileage charges matches the Actual other charges","Additional mileage charges doesnt match the Actual other charges");
        testStepAssert.isEquals(actualAdditionalWeightPallet,additionalWeightPalletAmount,"Additional weight / pallet charges should match the Actual other charges","Additional weight / pallet charges matches the Actual other charges","Additional weight / pallet charges doesnt match the Actual other charges");
        testStepAssert.isEquals(actualCustomerRejectedRetured,customerRejectedReturedAmount,"Customer rejected / returned charges should match the Actual other charges","Customer rejected / returned charges matches the Actual other charges","Customer rejected / returned charges doesnt match the Actual other charges");
        testStepAssert.isEquals(actualLimitedAccess,limitedAccessAmount,"Limited Access charges should match the Actual other charges","Limited Access charges matches the Actual other charges","Limited Access charges doesnt match the Actual other charges");

//        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Excess Wait Time"),excessWaitTimeAmount, "Excess Wait Time "+excessWaitTimeAmount+" should be displayed", excessWaitTimeAmount+" is displayed", excessWaitTimeAmount+" is not displayed");
//        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Cancelation"),cancelationAmount, "Cancelation "+cancelationAmount+" should be displayed", cancelationAmount+" is displayed", cancelationAmount+" is not displayed");
//        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Mountainous"),mountainousAmount, "Mountainous "+mountainousAmount+" should be displayed", mountainousAmount+" is displayed", mountainousAmount+" is not displayed");
//        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRow("Other"),otherAmount, "Other "+otherAmount+" should be displayed", otherAmount+" is displayed", otherAmount+" is not displayed");
//        testStepAssert.isElementTextEquals(admin_accessorialChargesPage.GridRowTotal("Total"),totalAmount, "Total "+totalAmount+" should be displayed", totalAmount+" is displayed", totalAmount+" is not displayed");

    }

    @And("^I click on the Accessorial Charges links and I should see the Drivers cut displayed$")
    public void i_click_on_the_accessorial_charges_links_and_i_should_see_the_drivers_cut_displayed(DataTable data) throws Throwable {
        try {
            Thread.sleep(1000);
            List<Map<String, String>> DataList = data.asMaps();
            int i = 0;
            while (i < DataList.size()) {
                String feeType = DataList.get(i).get("Fee Type").trim();
                String driverCut = DataList.get(i).get("Driver Cut").trim();
                switch (feeType) {
                    case "Excess Wait Time":
                        String excessWaitTime = feeType.replace(" ", "");
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(1));
                        Thread.sleep(2000);
                        String entireDriverCutForExcessWaitime[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(1)).split("\\$");

                        String  properDriverCutForExcessWaitTime =entireDriverCutForExcessWaitime[1];
                        cucumberContextManager.setScenarioContext("ExcessWaitCut",properDriverCutForExcessWaitTime.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("ExcessWaitCut"), "Excess Wait time driver cut charges should match","Excess Wait time driver cut charges match","Excess Wait time driver cut charges dont match");
                        break;
                    case "Cancelation":
                        String cancellation= feeType.replace(" ", "");
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(2));
                        Thread.sleep(2000);

                        String entireDriverCutForCancelation[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(2)).split("\\$");
                        String  properDriverCutForCancelation=entireDriverCutForCancelation[1];
                        cucumberContextManager.setScenarioContext("CancellationCut",properDriverCutForCancelation.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("CancellationCut"), "Cancelation driver cut charges should match","Cancelation driver cut charges match","Cancelation driver cut charges dont match");
                        break;
                    case "Mountainious":
                        String mountainous= feeType.replace(" ", "");
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(3));
                        Thread.sleep(2000);

                        String entireDriverCutForMountainous[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(3)).split("\\$");
                        String properDriverCutForMountainous=entireDriverCutForMountainous[1];
                        cucumberContextManager.setScenarioContext("MountainousCut",properDriverCutForMountainous.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("MountainousCut"), "Mountainous driver cut charges should match","Mountainous driver cut charges match","Mountainous driver cut charges dont match");
                        break;
                    case "Other" :
                        String other= feeType.replace(" ", "") +"s";
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(4));
                        Thread.sleep(2000);

                        String entireDriverCutForOther[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(4)).split("\\$");
                        String properDriverCutValueForOther =entireDriverCutForOther[1];
                        cucumberContextManager.setScenarioContext("OtherCut",properDriverCutValueForOther.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("OtherCut"), "Other driver cut charges should match","Other driver cut charges match","Other driver cut charges dont match");
                        break;
                    case "Additional Mileage" :
                        String additionalMileage= feeType.replace(" ", "") +"s";
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(5));
                        Thread.sleep(2000);

                        String entireDriverCutForAdditionalMileage[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(5)).split("\\$");
                        String properDriverCutValueForAdditionalMileage =entireDriverCutForAdditionalMileage[1];
                        cucumberContextManager.setScenarioContext("AdditionalMileage",properDriverCutValueForAdditionalMileage.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("AdditionalMileage"), "Additional Mileage driver cut charges should match","Additional Mileage driver cut charges match","Additional Mileage driver cut charges dont match");
                        break;
                    case "Additional Weight / Pallet" :
                        String additionalWeightPallet= feeType.replace(" ", "") +"s";
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(6));
                        Thread.sleep(2000);

                        String entireDriverCutForAdditionalWeightPallet[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(6)).split("\\$");
                        String properDriverCutValueForAdditionalWeightPallet= entireDriverCutForAdditionalWeightPallet[1];
                        cucumberContextManager.setScenarioContext("AdditionalWeightPallet",properDriverCutValueForAdditionalWeightPallet.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("AdditionalWeightPallet"), "Additional Weight / Pallet driver cut charges should match","Additional Weight / Pallet driver cut charges match","Additional Weight / Pallet driver cut charges dont match");
                        break;
                    case "Customer Rejected / Returned" :
                        String additionalCustomerRejectedReturned= feeType.replace(" ", "") +"s";
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(7));
                        Thread.sleep(2000);

                        String entireDriverCutForCustomerRejectedReturned[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(7)).split("\\$");
                        String properDriverCutValueForCustomerRejectedReturned= entireDriverCutForCustomerRejectedReturned[1];
                        cucumberContextManager.setScenarioContext("CustomerRejectedReturned",properDriverCutValueForCustomerRejectedReturned.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("CustomerRejectedReturned"), "Customer Rejected / Returned driver cut charges should match","Customer Rejected / Returned driver cut charges match","Customer Rejected / Returned driver cut charges dont match");
                        break;
                    case "Limited Access" :
                        String additionalLimitedAccess= feeType.replace(" ", "") +"s";
                        action.click(admin_accessorialChargesPage.Text_DiffAccessorial(8));
                        Thread.sleep(2000);

                        String entireDriverCutForLimitedAccess[]=action.getText(admin_accessorialChargesPage.Text_DriverCut(8)).split("\\$");
                        String properDriverCutValueForLimitedAccess= entireDriverCutForLimitedAccess[1];
                        cucumberContextManager.setScenarioContext("LimitedAccess",properDriverCutValueForLimitedAccess.trim());
                        testStepAssert.isEquals(driverCut, (String) cucumberContextManager.getScenarioContext("LimitedAccess"), "Limited Access driver cut charges should match","Limited Access driver cut charges match","Limited Access driver cut charges dont match");
                        break;
                }
                i++;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @And("^I search the delivery of Customer and view it$")
    public void i_search_the_delivery_of_customerAndView() throws Throwable {
        try {
            String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            Thread.sleep(15000);
            action.clearSendKeys(admin_TripsPage.TextBox_Search(), pickuprequest + Keys.ENTER);
            ///////////////////
            Thread.sleep(5000);
            utility.resetGeofenceDropdown();
            Thread.sleep(5000);
            //String status = "Payment Successful";
            action.click(admin_TripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/img", customerName), PageBase.LocatorType.XPath));
            action.click(admin_TripsPage.Link_DeliveryDetails());
            //action.click(admin_TripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td/div/ul/li/p[contains(text(),'Delivery Details')]", customerName), PageBase.LocatorType.XPath));

            log("I search the delivery of Customer and view it", "I searched the delivery of Customer and viewed it", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @And("^I search the delivery of Customer \"([^\"]*)\"$")
    public void i_search_the_delivery_of_customer_(String customer) throws Throwable {
        try{
        Thread.sleep(10000);
        action.clearSendKeys(admin_TripsPage.TextBox_Search(),customer+Keys.ENTER);
        Thread.sleep(10000);
        log("I search the delivery of Customer","I searched the delivery of Customer "+ customer,false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @And("^I search the delivery of Customer$")
    public void i_search_the_delivery_of_customer() throws Throwable {
        String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        //pickuprequest = dbUtility.getLinkedPickupRef(pickuprequest);

        String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
        Thread.sleep(10000);
        action.clearSendKeys(admin_TripsPage.TextBox_Search(),pickuprequest+Keys.ENTER);
        Thread.sleep(10000);
        log("I search the delivery of Customer","I searched the delivery of Customer",false);
    }
    @When("^I click on \"([^\"]*)\" button on Accessorial Charges$")
    public void i_click_on_something_button_on_accessorial_charges(String button) throws Throwable {
        try{
        switch(button.toUpperCase()) {
            case "SAVE":
            action.click(admin_accessorialChargesPage.Button_Save());
            break;

        }
        log("I click on"+button+" button on Accessorial Charges","I clicked on"+button+" button on Accessorial Charges",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I should get following error for following accessorial charges fields values when saved$")
    public void i_should_get_following_error_for_following_accessorial_charges_fields_values_when_saved(DataTable data) throws Throwable {
        try{
        List<Map<String, String>> DataList = data.asMaps();
        int i = 0;
        while (i < DataList.size()) {
            String amount = DataList.get(i).get("Amount").trim();
            String feeType = DataList.get(i).get("Fee Type").trim();
            String comment = DataList.get(i).get("Comment").trim();
            String field = DataList.get(i).get("Field").trim();
            String driver_cut = DataList.get(i).get("Driver Cut").trim();


            String message = DataList.get(i).get("Message").trim();

            if(amount.equalsIgnoreCase("Blank")) {
                action.clearAllText(admin_accessorialChargesPage.TextBox_AccessorialAmount());
            }
            else{
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialAmount(), amount);
            }
            if(driver_cut.equalsIgnoreCase("Blank")){
                action.clearAllText(admin_accessorialChargesPage.TextBox_AccessorialDriverCut());
            }else{
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_AccessorialDriverCut(),driver_cut);
            }
            if(feeType.equalsIgnoreCase("Blank")) {
                action.selectElementByText(admin_accessorialChargesPage.DropDown_AccessorialFeeType(), "--Select Fee Type--");
            }
            else{
                action.selectElementByText(admin_accessorialChargesPage.DropDown_AccessorialFeeType(), feeType);
            }
            if(comment.equalsIgnoreCase("Blank")) {
                action.clearAllText(admin_accessorialChargesPage.TextBox_Comment());
            }
            else {
                action.clearSendKeys(admin_accessorialChargesPage.TextBox_Comment(), comment);
            }
            action.click(admin_accessorialChargesPage.Button_Save());

            switch(field.toUpperCase()) {

                case "AMOUNT":
                    testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Error_AccessorialFeeAmount(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                    break;
                case "FEE TYPE":
                    testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Error_AccessorialFeeType(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                    break;
                case "COMMENT":
                    testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Error_AccessorialFeeComment(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                    break;
                case "DRIVER AMOUNT":
                    testStepAssert.isElementTextEquals(admin_accessorialChargesPage.Error_AccessorialFeeDriverCut(), message, message + " should be displayed", message + " is displayed", message + " is not displayed");
                    break;
            }
        i++;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^\"([^\"]*)\" should show total amount in the triprequest table in Database$")
    public void something_should_show_total_amount_in_the_triprequest_table_in_database(String strArg1) throws Throwable {
        try{
        String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String expectedTotal = (String) cucumberContextManager.getScenarioContext("TOTAL_AMOUNT");

        String actualTotalAmount = dbUtility.getAccessorialCharge(pickuprequest);
        testStepAssert.isEquals("$"+actualTotalAmount,expectedTotal, "Total "+expectedTotal+" should be displayed", expectedTotal+" is displayed", expectedTotal+" is not displayed");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^\"([^\"]*)\" should show comment without quotes in the trippaymentdetails table in Database$")
    public void something_should_show_comment_without_quotes_in_the_trippaymentdetails_table_in_database(String strArg1) throws Throwable {
        try{
        String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String note = (String) cucumberContextManager.getScenarioContext("NOTE");

        String dbnote = dbUtility.getBusinessNotes(pickuprequest);
        testStepAssert.isEquals(dbnote,note, "Total "+note+" should be displayed", note+" is displayed", note+" is not displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see the delivery highlighted in \"([^\"]*)\"$")
    public void i_should_see_the_delivery_highlighted_in_something(String color) throws Throwable {
        try {
            String expectedHighlightColor="";
            switch(color.toLowerCase()){
                case "red":
                 expectedHighlightColor = "rgba(254, 201, 166, 1)";
                    break;
                case "blue":
                     expectedHighlightColor = "rgba(228, 242, 255, 1)";
                    break;
                case "grey":
                    expectedHighlightColor = "rgba(238, 239, 239, 1)";
                    break;
            }
        Thread.sleep(1000);
        boolean liveDeliveryhighlightDisplayed =  admin_liveTripsPage.Text_DeliveryHighlight().isDisplayed();
        String liveDeliveryHighlightColor =  admin_liveTripsPage.Text_DeliveryHighlight().getCssValue("background-color");

        testStepAssert.isTrue(liveDeliveryhighlightDisplayed,"Highlight should be displayed","Highlight is displayed","Highlight is not displayed");
        testStepAssert.isEquals(liveDeliveryHighlightColor,expectedHighlightColor,"Delivery should be highlighted with "+color+" color","Delivery is highlighted with "+color+" color","Delivery is not  highlighted with "+color+" color");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see \"([^\"]*)\" tooltip beside the bungii$")
    public void i_should_see_something_tooltip_beside_the_bungii(String expectedTooltipText) throws Throwable {
        try {
            action.refreshPage();
            Thread.sleep(10000);
            action.Hover(admin_liveTripsPage.Icon_Hover());
            String actualTooltiptext = action.getText(admin_liveTripsPage.Label_Tooltip());

            testStepAssert.isEquals(actualTooltiptext,expectedTooltipText,"Tooltip "+expectedTooltipText+" should be displayed","Tooltip "+expectedTooltipText+" is displayed","Tooltip "+expectedTooltipText+" is not displayed");
            String actualWidth = admin_liveTripsPage.Label_Tooltip().getCssValue("width");
            String expectedWidth = "95px";

            testStepAssert.isEquals(actualWidth,expectedWidth,"Tool tip width should be 95px","Tool tip width is 95px","Tool tip width is not 95px");


        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
        }

    @And("^The delivery should not be highlighted in \"([^\"]*)\" for \"([^\"]*)\"$")
    public void the_delivery_should_not_be_highlighted_in_something_for_something(String strArg1, String deliveryType) throws Throwable {
        try {
        String expectedHighlightColor = "rgba(228, 242, 255, 1)";
       switch (deliveryType){
           case "Scheduled Deliveries":
           case "Live Deliveries":
               Thread.sleep(1000);
               boolean scheduledDeliveryhighlightDisplayed =  admin_liveTripsPage.Text_DeliveryHighlight().isDisplayed();
               String scheduledDeliveryHighlightColor =  admin_liveTripsPage.Text_DeliveryHighlight().getCssValue("background-color");

               testStepAssert.isTrue(scheduledDeliveryhighlightDisplayed,"Highlight should be displayed","Highlight is displayed","Highlight is not displayed");
               testStepAssert.isFalse(scheduledDeliveryHighlightColor.contentEquals(expectedHighlightColor),"Delivery should not be highlighted with blue color","Delivery is not highlighted with blue color","Delivery is highlighted with blue color");

               break;
           case "All Deliveries":
               Thread.sleep(1000);
               boolean allDeliveryhighlightDisplayed =  admin_liveTripsPage.Text_AllDeliveryHighlight().isDisplayed();
               String allDeliveryHighlightColor =  admin_liveTripsPage.Text_AllDeliveryHighlight().getCssValue("background-color");

               testStepAssert.isTrue(allDeliveryhighlightDisplayed,"Highlight should be displayed","Highlight is displayed","Highlight is not displayed");
               testStepAssert.isFalse(allDeliveryHighlightColor.contentEquals(expectedHighlightColor),"Delivery should not be highlighted with blue color","Delivery is not highlighted with blue color","Delivery is highlighted with blue color");
               break;
       }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }



    @Then("^the Accessorial Charges should not be displayed$")
    public void the_accessorial_charges_should_not_be_displayed() throws Throwable {
     try {
    Thread.sleep(1000);
     testStepAssert.isFalse(action.isElementPresent(admin_accessorialChargesPage.Header_Section(true)),"Accessorial Charges should not be displayed","Accessorial Charges is not displayed","Accessorial Charges is displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I use the old pickup reference to search the driver cancelled trip$")
    public void i_use_the_old_pickup_reference_to_search_the_driver_cancelled_trip() throws Throwable {
     try{
        String oldPickupRef = (String) cucumberContextManager.getScenarioContext("OLD_PICKUP_REQUEST");
        Thread.sleep(2000);
        action.clearSendKeys(adminTripsPage.TextBox_Search(), oldPickupRef + Keys.ENTER);
        log("I should be able to search the delivery using the old pickup reference",
                "I could search the delivery using the old pickup reference",false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I click on \"([^\"]*)\" link to get the total driver earnings value screen and navigate back to admin portal$")
    public void i_click_on_something_link_to_get_the_total_driver_earnings_value_screen_and_navigate_back_to_admin_portal(String strArg1) throws Throwable {
        try{
        Thread.sleep(1000);
        action.click(driver_DashboardPage.Link_DriverBasicInfo());
        String entireDriverEarning = action.getText(Page_Driver_Details.Text_DriverTotalEarnings()).replace("\\$","");
        cucumberContextManager.setScenarioContext("Initial_Driver_Total_Earning",entireDriverEarning);
        ArrayList<String> tabs = new ArrayList<String> (SetupManager.getDriver().getWindowHandles());
        SetupManager.getDriver().switchTo().window(tabs.get(0));
        action.refreshPage();

        Thread.sleep(2000);
        //action.sendKeys(Page_AdminLogin.TextBox_Phone(), PropertyUtility.getDataProperties("admin.user"));
        //action.sendKeys(Page_AdminLogin.TextBox_Password(), PropertyUtility.getDataProperties("admin.password"));
        //action.click(Page_AdminLogin.Button_AdminLogin());
        log("I should be able to click on the Delivery details link and get the total earning value and navigate back to admin portal",
                "I could click on the Delivery details link and get the total earning value and navigate back to admin portal");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }
    @And("^I login to driver portal on a new tab with driver phone number \"([^\"]*)\"$")
    public void i_login_to_driver_portal_on_a_new_tab_with_driver_phone_number_something(String phone) throws Throwable {
        try{
        action.openNewTab();
        utility.NavigateToDriverLogin();
        Thread.sleep(1000);

        action.click(Page_Driver_Login.Tab_LogIn());
        testStepAssert.isElementDisplayed(Page_Driver_Login.Link_Terms(),
                "The terms link should be displayed",
                "The terms link is displayed",
                "The terms link is not displayed");
        testStepAssert.isEquals(Page_Driver_Login.Link_Terms().getAttribute("href"),PropertyUtility.getDataProperties("terms.page.link"),
                "The correct link should be present for terms.",
                "The correct link is present for terms.",
                "The correct link is not present for terms.");
        testStepAssert.isElementDisplayed(Page_Driver_Login.Link_PrivacyPolicy(),
                "The privacy policy link should be displayed",
                "The privacy policy  link is displayed",
                "The privacy policy link is not displayed");
        testStepAssert.isEquals(Page_Driver_Login.Link_PrivacyPolicy().getAttribute("href"),PropertyUtility.getDataProperties("privacy.policy.page.link"),
                    "The correct link should be present for privacy policy.",
                    "The correct link is present for privacy policy.",
                    "The correct link is not present for privacy policy.");
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), phone);
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), PropertyUtility.getDataProperties("web.valid.common.driver.password"));
        Thread.sleep(1000);
        action.click(Page_Driver_Login.Button_DriverLogin());
        log("I should be able to open new tab and login to driver portal using driver phone number "+ phone ,
                "I could open new tab and login to driver portal using driver phone number " + phone,false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The accessorial charges cut should be displayed in total earnings$")
    public void The_accessorial_charges_cut_should_be_displayed_in_total_earnings() throws Throwable {
        try {
        Thread.sleep(1000);
        action.click(driver_DashboardPage.Link_DriverBasicInfo());

        Float entireDriverEarning = Float.parseFloat(action.getText(Page_Driver_Details.Text_DriverTotalEarnings()).replace("$", ""));
        String initialDriverTotal =(String)  cucumberContextManager.getScenarioContext("Initial_Driver_Total_Earning").toString().replace("$","");
        String driverAdditionalMileageCutAmount =(String) cucumberContextManager.getScenarioContext("AdditionalMileage").toString().replace("$","");
        String driverAdditionalWeightPalletCutAmount =(String) cucumberContextManager.getScenarioContext("AdditionalWeightPallet").toString().replace("$","");
        String driverCustomerRejectedReturnedCutAmount =(String) cucumberContextManager.getScenarioContext("CustomerRejectedReturned").toString().replace("$","");
        String driverLimitedAccessCutAmount =(String) cucumberContextManager.getScenarioContext("LimitedAccess").toString().replace("$","");
        String driverExcessWaitCutAmount =(String) cucumberContextManager.getScenarioContext("ExcessWaitCut").toString().replace("$","");
        String driverCancellationCutAmount =(String) cucumberContextManager.getScenarioContext("CancellationCut").toString().replace("$","");
        String driverMountainousCutAmount =(String)cucumberContextManager.getScenarioContext("MountainousCut").toString().replace("$","");
        String driverOtherCutAmount =(String) cucumberContextManager.getScenarioContext("OtherCut").toString().replace("$","");
        Float newDriverTotalEarning = Float.parseFloat(driverAdditionalMileageCutAmount) +Float.parseFloat(driverAdditionalWeightPalletCutAmount) +Float.parseFloat(driverCustomerRejectedReturnedCutAmount) +Float.parseFloat(driverLimitedAccessCutAmount) +Float.parseFloat(driverExcessWaitCutAmount) + Float.parseFloat(driverCancellationCutAmount) +Float.parseFloat(driverMountainousCutAmount) + Float.parseFloat(driverOtherCutAmount) +Float.parseFloat(initialDriverTotal);
        testStepAssert.isTrue(newDriverTotalEarning>Float.parseFloat(initialDriverTotal),"Accessorial charges cut should be displayed in total earning","Accessorial charges cut is displayed in total earning","Accessorial charges cut is not displayed in total earning");
        testStepAssert.isEquals(String.format("%.1f",newDriverTotalEarning),String.valueOf(entireDriverEarning),"Proper driver cut amount should be displayed","Proper driver cut amount is displayed","Proper driver cut amount is not displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }



    @And("^I should see the following fee type displayed in the Report Database$")
    public void i_should_see_the_following_fee_type_displayed_in_the_report_database(DataTable data) throws Throwable {
        try {
            Thread.sleep(1000);
            String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            List<Map<String, String>> DataList = data.asMaps();
            List<String> expectedFeeType=new ArrayList();
            for (int i=0; i < DataList.size();i++)
           {Thread.sleep(1000);
                String feeType = DataList.get(i).get("Fee Type").trim();
                expectedFeeType.add(feeType);
           }
           List<String> actualFeeType = dbUtility.getAccessorialFeeType(pickuprequest);
            testStepAssert.isTrue(actualFeeType.containsAll(expectedFeeType), expectedFeeType+" should be displayed", expectedFeeType+" is displayed", expectedFeeType+" is not displayed");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("I should see Fee type sorted in alphabetical order")
    public void iShouldSeeFeeTypeSortedInAlphabeticalOrder() {
        try {
            List<WebElement> allFeeTypes= admin_accessorialChargesPage.List_SelectFeeType();
            ArrayList<String> tabs = new ArrayList<String>();
            for (WebElement feeType : allFeeTypes) {
                tabs.add(feeType.getText());
            }
            tabs.remove(0);
            boolean areFeeTypesOrderedAlphabetically = Ordering.natural().isOrdered(tabs);;
            testStepAssert.isTrue(areFeeTypesOrderedAlphabetically,"Fee Type should be alphabetically ordered","Fee Type are alphabetically ordered","Fee Type are not alphabetically ordered");
        }
        catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("I verify correct {string} is set in db for accessorial charge")
    public void iVerifyCorrectPaymentTransactionTypeIsSetInDbForAccessorialCharge(String type) {
        try {
            String pickUpRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String driverOne = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
            String driverTwo = (String) cucumberContextManager.getScenarioContext("DRIVER_2_PHONE");
            String paymenttransTypeDriverOne = dbUtility.getDisbursementType(type, pickUpRef, driverOne);
            String paymenttransTypeDriverTwo = dbUtility.getDisbursementType(type, pickUpRef, driverTwo);
            testStepAssert.isEquals(paymenttransTypeDriverOne, PropertyUtility.getDataProperties("accessorial.charge.transaction.type.value"),
                    "Correct Payment transaction type value should be set for Accessorial charge in DB",
                    "Correct Payment transaction type value is set for Accessorial charge in DB",
                    "Incorrect Payment transaction type value should be set for Accessorial charge in DB");
            testStepAssert.isEquals(paymenttransTypeDriverTwo, PropertyUtility.getDataProperties("accessorial.charge.transaction.type.value"),
                    "Correct Payment transaction type value should be set for Accessorial charge in DB",
                    "Correct Payment transaction type value is set for Accessorial charge in DB",
                    "Incorrect Payment transaction type value should be set for Accessorial charge in DB");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}