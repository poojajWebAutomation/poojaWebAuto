package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_RefundsPage;
import com.bungii.web.pages.admin.Admin_RevivalPage;
import com.bungii.web.pages.admin.Admin_TripDetailsPage;
import com.bungii.web.pages.admin.Admin_TripsPage;
import com.bungii.web.utilityfunctions.DbUtility;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_RevivalSteps extends DriverBase {

    Admin_RevivalPage admin_RevivalPage = new Admin_RevivalPage();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_RevivalSteps.class);
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();
    Admin_TripsPage admin_TripsPage = new Admin_TripsPage();
    Admin_RevivalPage admin_revivalPage = new Admin_RevivalPage();
    Admin_TripDetailsPage admin_tripDetailsPage = new Admin_TripDetailsPage();

    Admin_TripsPage adminTripsPage = new Admin_TripsPage();
    @Then("^Revive button should be displayed beside the trip$")
    public void revive_button_should_be_displayed_beside_the_trip() throws Throwable {
        try {
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
            SetupManager.getDriver().manage().window().maximize();
            SetupManager.getDriver().manage().window().setSize(new Dimension(1900, 1280));

            String link = String.format("//td[contains(.,'%s')]/following-sibling::td/span[@class='revive']/img", customerName);
            testStepAssert.isTrue(action.isElementPresent(admin_TripsPage.findElement(link, PageBase.LocatorType.XPath)), "Revive button should be displayed", "Revive button is displayed", "Revive button is not displayed");
            cucumberContextManager.setScenarioContext("REVIVE_LINK", link);
            String partnerName = action.getText(admin_TripsPage.findElement(String.format("//td[contains(.,'%s')]/following-sibling::td[1]", customerName), PageBase.LocatorType.XPath));
            cucumberContextManager.setScenarioContext("PARTNER",partnerName);

        } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^I should see \"([^\"]*)\" details on review popup$")
    public void i_should_see_something_and_something_details_on_review_popup(String who) throws Throwable {
        try {
            String customer ="";
        switch(who.toLowerCase()) {
          case "customer":
               customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
          testStepAssert.isEquals(action.getText(admin_TripsPage.Label_ReviveCustomerDetail()),customer,"Customer "+ customer+" details should be shown","Customer "+ customer+" details are shown","Customer "+ customer+" details are not shown");
          break;
          case "partner":
               customer = (String) cucumberContextManager.getScenarioContext("CUSTOMER");
              testStepAssert.isEquals(action.getText(admin_TripsPage.Label_ReviveCustomerDetail()),customer,"Customer "+ customer+" details should be shown","Customer "+ customer+" details are shown","Customer "+ customer+" details are not shown");
              String partner = (String) cucumberContextManager.getScenarioContext("PARTNER");
              testStepAssert.isEquals(action.getText(admin_TripsPage.Label_RevivePartnerDetail()),partner,"Partner "+ partner+" details should be shown","Partner "+ partner+" details are shown","Partner "+ partner+" details are not shown");
              break;
      }
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I should not see \"([^\"]*)\" on review popup$")
    public void i_should_not_see_something_and_something_details_on_review_popup(String who) throws Throwable {
        try {
            switch(who.toLowerCase()) {
            case "pickup origin":
                testStepAssert.isFalse(action.isElementPresent(admin_TripsPage.Label_RevivePickupOriginDetail(true)),"Pickup origin details should not be shown","Pickup origin details is not shown","Pickup origin details is shown");
                break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @And("^\"([^\"]*)\" and \"([^\"]*)\" buttons should have background color \"([^\"]*)\" and \"([^\"]*)\" respectively$")
    public void something_and_something_buttons_should_have_background_color_something_and_something_respectively(String primaryButton, String secondaryButton, String primaryColor, String secondaryColor) throws Throwable {

        try {
            String expectedHighlightColor = "";
            switch (primaryColor.toLowerCase()) {
                case "blue":
                    expectedHighlightColor = "rgba(44, 118, 168, 1)";
                    break;
                case "white":
                    expectedHighlightColor = "rgba(255, 255, 255, 1)";
                    break;
            }
            String primaryButtonBackgroundColor ="";
            switch (primaryButton.toLowerCase()) {
                case "confirm":
                primaryButtonBackgroundColor = admin_revivalPage.Button_Confirm().getCssValue("background-color");
                  break;
                case "save":
                    primaryButtonBackgroundColor = admin_tripDetailsPage.Button_Save().getCssValue("background-color");
                    break;
            }

            testStepAssert.isEquals(primaryButtonBackgroundColor, expectedHighlightColor, primaryButton +" button should be highlighted with "+primaryColor+" color", primaryButton +" button is highlighted with "+primaryColor+" color", primaryButton +" button is not highlighted with "+primaryColor+" color");
            switch (secondaryColor.toLowerCase()) {
                case "blue":
                    expectedHighlightColor = "rgba(68, 138, 193, 1)";
                    break;
                case "white":
                    expectedHighlightColor = "none";
                    break;
            }
            String secondaryButtonBackgroundColor = admin_revivalPage.Button_Cancel().getCssValue("background");
            testStepAssert.isTrue(secondaryButtonBackgroundColor.contains(expectedHighlightColor), secondaryButton +" button should be highlighted with "+secondaryColor+" color", secondaryButton +" button is highlighted with "+secondaryColor+" color", secondaryButton +" button is not highlighted with "+secondaryColor+" color");



        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see \"([^\"]*)\" message on popup with PickupId anad Pickup Origin$")
    public void i_should_see_something_message_on_popup_with_pickupid_anad_pickup_origin(String message) throws Throwable {

        try{
            Thread.sleep(2000);
        testStepAssert.isTrue(action.isElementPresent(admin_RevivalPage.Label_HeaderPopup()),message+" should be displayed", message+" is displayed", message+" is not displayed");
        String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String pickupId = dbUtility.getPickupIdFromFactPickup(pickuprequest);
        String source = "Customer Delivery";
        String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");

        testStepAssert.isElementTextEquals(admin_RevivalPage.Label_PickupId(),pickupId, pickupId +" should be displayed", pickupId +" is displayed", pickupId+" is not displayed");
        //testStepAssert.isElementTextEquals(admin_RevivalPage.Label_PickupOrigin(),source, source +" should be displayed", source +" is displayed", source+" is not displayed");
        testStepAssert.isElementTextEquals(admin_RevivalPage.Label_PickupCustomer(),customerName, customerName +" should be displayed", customerName +" is displayed", customerName+" is not displayed");
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @Then("^I should see \"([^\"]*)\" message on popup with PickupId, Pickup Origin and Partner Name$")
    public void i_should_see_something_message_on_popup_with_pickupid_pickup_origin_and_partner_name(String message) throws Throwable {
        try{
            testStepAssert.isTrue(action.isElementPresent(admin_RevivalPage.Label_HeaderPopup()),message+" should be displayed", message+" is displayed", message+" is not displayed");
            String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String pickupId = dbUtility.getPickupIdFromFactPickup(pickuprequest);
            String source = "Customer Delivery";
            String customerName = (String) cucumberContextManager.getScenarioContext("CUSTOMER");

            testStepAssert.isElementTextEquals(admin_RevivalPage.Label_PickupId(),pickupId, pickupId +" should be displayed", pickupId +" is displayed", pickupId+" is not displayed");
            testStepAssert.isElementDisplayed(admin_RevivalPage.Label_PickupPartnerPortal(),"Pickup Partner portal is displayed","Pickup Partner portal is displayed","Pickup Partner portal is not displayed");

        }catch(Exception e){
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
                action.click(admin_revivalPage.Button_Confirm());
                Thread.sleep(10000);
                String pickuprequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
                cucumberContextManager.setScenarioContext("OLD_PICKUP_REQUEST",pickuprequest);
                pickuprequest = dbUtility.getLinkedPickupRef(pickuprequest);
                cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickuprequest);
                break;
            case "Cancel":
                action.click(admin_revivalPage.Button_Cancel());
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


    @And("^I select \"([^\"]*)\" from the dropdown$")
    public void i_select_something_from_the_dropdown(String status) throws Throwable {
        try{
        Thread.sleep(5000);
        action.click(admin_RevivalPage.Link_ChangeDeliveryStatus());
        Thread.sleep(4000);
        action.click(admin_RevivalPage.DropDown_DeliveryStatus());
        switch (status){
            case "Admin Canceled":
            case "Partner Canceled":
            case "Driver Canceled":
            case "Customer Canceled":
                action.click(admin_RevivalPage.Text_DeliveryStatus(status));
                break;
        }
        log("I should be able to click on "+status+" link", "I could click on "+status+" link",false);
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @Then("^I should see the change status link \"([^\"]*)\"$")
    public void i_should_see_the_change_status_link_something(String changeStatusLink) throws Throwable {
        try{
        switch (changeStatusLink){
            case "Not Displayed":
                testStepAssert.isNotElementDisplayed(admin_RevivalPage.Link_ChangeDeliveryStatus(true),"Element should not be displayed","Element is not displayed","Element is displayed");
                break;
            case "Is Displayed":
                testStepAssert.isElementDisplayed(admin_RevivalPage.Link_ChangeDeliveryStatus(),"Element should be displayed","Element is displayed","Element is not displayed");
                break;
        }
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I select \"([^\"]*)\" as the reason from the reason dropdown$")
    public void i_select_something_as_the_reason_from_the_reason_dropdown(String changestatusreason) throws Throwable {
        try{
        action.click(admin_RevivalPage.DropDown_DeliveryStatusReason());
        switch (changestatusreason){
            case "Driver initiated":
            case "Customer initiated - other reason":
            case "Outside of delivery scope":
            case "Solo: Driver not found":
            case "Other":
            case "No drivers available":
                action.click(admin_RevivalPage.Text_DeliveryStatusReason(changestatusreason));
                break;
        }
            log("I should be able to click on "+changestatusreason+" option", "I could click on "+changestatusreason+" option",false);
        }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @Then("^I should be able to see the comment textbox displayed$")
    public void i_should_be_able_to_see_the_comment_textbox_displayed() throws Throwable {
        try{
        testStepAssert.isElementDisplayed(admin_RevivalPage.Textbox_CommentForStatus(),"Textbox should be displayed","Textbox is displayed","Textbox is not  displayed");
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I enter the text \"([^\"]*)\" in the textarea$")
    public void i_enter_the_text_something_in_the_textarea(String textmessage) throws Throwable {
        try{
        action.clearSendKeys(admin_RevivalPage.Textbox_CommentForStatus(), textmessage+ Keys.ENTER);
        Thread.sleep(3000);
        log("I should be able to enter the text "+textmessage+" in the textarea",
                "I could enter the text "+textmessage+" in the textarea",false);
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @Then("^The \"([^\"]*)\" should be in \"([^\"]*)\" state$")
    public void the_something_should_be_in_something_state(String adminPage ,String deliveryStatus) throws Throwable {
        try{
            switch (adminPage){
                case "Scheduled Deliveries":
                    switch (deliveryStatus){
                        case "Assigning Driver(s)":
                            Thread.sleep(3000);
                            String status = action.getText(admin_RevivalPage.Text_DeliveryStatus(13));
                            testStepAssert.isEquals(status,deliveryStatus,"Delivery Should be in " +deliveryStatus+ " state",
                                    "Delivery is  in " +status+ " state",
                                    "Delivery is not in " +deliveryStatus+ " state");
                            break;
                    }
                    break;
                case "All Deliveries":
                    switch (deliveryStatus){
                        case "Admin Canceled - No Driver(s) Found":
                        case "Partner Canceled":
                        case "Driver Canceled":
                        case "Payment Pending":
                        case "Payment Successful":
                            Thread.sleep(3000);
                            String status = action.getText(admin_RevivalPage.Text_DeliveryStatus(12));
                            testStepAssert.isEquals(status,deliveryStatus,"Delivery Should be in " +deliveryStatus+ " state",
                                    "Delivery is  in " +status+ " state",
                                    "Delivery is not in " +deliveryStatus+ " state");
                            break;
                    }
                    break;
            }
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
}

    @Then("^The amount should be \"([^\"]*)\" and in \"([^\"]*)\" state$")
    public void the_amount_should_be_something_and_in_something_state(String strArg1, String deliveryState) throws Throwable {
        try{
        String PickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        int paymentStatusTransaction = Integer.parseInt(new DbUtility().getPaymentTransactionType(PickupRequest));
        String paymentStatus = new DbUtility().getStatusMessage(PickupRequest);
        String isDriverPaid = new DbUtility().getDriverPaid(PickupRequest);
        testStepAssert.isTrue(isDriverPaid.equals("0"),"Driver paid should be false", "Driver paid is false","Driver paid is not in false state");
        testStepAssert.isTrue(paymentStatusTransaction==5,"Amount should be refunded", "Amount is refunded","Amount is not refunded");
        testStepAssert.isTrue(paymentStatus.equals(deliveryState),"Delivery should be in voided state", "Delivery is in voided state","Delivery is not in voided state");
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
}

    @Then("^The Below accessorial charges should be present in the db$")
    public void the_below_accessorial_charges_should_be_present_in_the_db(DataTable data) throws Throwable {
        try{
        Map<String, String> dataMap = data.transpose().asMap(String.class, String.class);

        String additionalMileageAmount = dataMap.get("Additional Mileage").trim();
        String additionalWeightPalletAmount = dataMap.get("Additional Weight / Pallet").trim();
        String customerRejectedReturnedAmount = dataMap.get("Customer Rejected / Returned").trim();
        String limitedAccessAmount = dataMap.get("Limited Access").trim();
        String excessWaitTimeAmount = dataMap.get("Excess Wait Time").trim();
        String cancelationAmount = dataMap.get("Cancelation").trim();
        String mountainousAmount = dataMap.get("Mountainous").trim();
        String otherAmount = dataMap.get("Other").trim();
        String PickupRequest = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
        String TripType = (String) cucumberContextManager.getScenarioContext("TripType");

        if(TripType.equals("PartnerTrip")) {
            List<HashMap<String, Object>> isDriverPaid = new DbUtility().getAccessorialAmount(PickupRequest);

            String[] amt = new String[10];
            for (int i = 0; i < isDriverPaid.size(); i++) {
                String amount;
                if (isDriverPaid.get(i).get("Amount").toString().contains(".00")) {
                    amount = "$" + isDriverPaid.get(i).get("Amount").toString().replace(".00", "");
                    amt[i] = amount;
                } else {
                    amount = "$" + isDriverPaid.get(i).get("Amount");
                    amt[i] = amount;
                }
            }
            testStepAssert.isEquals(amt[0], otherAmount, "Other charges should be present and not refunded", "Other charges is present and not refunded", "Other charges is not present in db");
            testStepAssert.isEquals(amt[1], mountainousAmount, "Mountainious charges should be present and not refunded", "Mountainious charges is present and not refunded", "Mountainious charges is not present in db");
            testStepAssert.isEquals(amt[2], limitedAccessAmount, "Limited Access Amount charges should be present and not refunded", "Limited Access Amount charges is present and not refunded", "Limited Access Amount charges is not present in db");
            testStepAssert.isEquals(amt[3], excessWaitTimeAmount, "Excess wait time charges should be present and not refunded", "Excess wait time charges is present and not refunded", "Excess wait time charges is not present in db");
            testStepAssert.isEquals(amt[4], customerRejectedReturnedAmount, "Customer Rejected Returned Amount charges should be present and not refunded", "Customer Rejected Returned Amount charges is present and not refunded", "Customer Rejected Returned Amount charges is not present in db");
            testStepAssert.isEquals(amt[5], cancelationAmount, "Cancellation charges should be present and not refunded", "Cancellation charges is present and not refunded", "Cancellation charges is not present in db");
            testStepAssert.isEquals(amt[6], additionalWeightPalletAmount, "Additional Weight / Pallet Amount charges should be present and not refunded", "Additional Weight / Pallet Amount charges is present and not refunded", "Additional Weight / Pallet Amount charges is not present in db");
            testStepAssert.isEquals(amt[7], additionalMileageAmount, "Additional Mileage Amount charges should be present and not refunded", "Additional Mileage Amount charges is present and not refunded", "Additional Mileage Amount charges is not present in db");
        }

        else {
            List<HashMap<String, Object>> isDriverPaid = new DbUtility().getAccessorialAmount(PickupRequest);
            String[] amt = new String[10];
            for (int i = 0; i < isDriverPaid.size(); i++) {
                String amount;
                if (isDriverPaid.get(i).get("Amount").toString().contains(".00")) {
                    amount = "$" + isDriverPaid.get(i).get("Amount").toString().replace(".00", "");
                    amt[i] = amount;
                } else {
                    amount = "$" + isDriverPaid.get(i).get("Amount");
                    amt[i] = amount;
                }
            }
            testStepAssert.isEquals(amt[0], otherAmount, "Other charges should be present and not refunded", "Other charges is present and not refunded", "Other charges is not present in db");
            testStepAssert.isEquals(amt[1], mountainousAmount, "Mountainious charges should be present and not refunded", "Mountainious charges is present and not refunded", "Mountainious charges is not present in db");
            testStepAssert.isEquals(amt[2], limitedAccessAmount, "Limited Access Amount charges should be present and not refunded", "Limited Access Amount charges is present and not refunded", "Limited Access Amount charges is not present in db");
            testStepAssert.isEquals(amt[3], excessWaitTimeAmount, "Excess wait time charges should be present and not refunded", "Excess wait time charges is present and not refunded", "Excess wait time charges is not present in db");
            testStepAssert.isEquals(amt[4], customerRejectedReturnedAmount, "Customer Rejected Returned Amount charges should be present and not refunded", "Customer Rejected Returned Amount charges is present and not refunded", "Customer Rejected Returned Amount charges is not present in db");
            testStepAssert.isEquals(amt[5], cancelationAmount, "Cancellation charges should be present and not refunded", "Cancellation charges is present and not refunded", "Cancellation charges is not present in db");
            testStepAssert.isEquals(amt[6], additionalWeightPalletAmount, "Additional Weight / Pallet Amount charges should be present and not refunded", "Additional Weight / Pallet Amount charges is present and not refunded", "Additional Weight / Pallet Amount charges is not present in db");
            testStepAssert.isEquals(amt[7], additionalMileageAmount, "Additional Mileage Amount charges should be present and not refunded", "Additional Mileage Amount charges is present and not refunded", "Additional Mileage Amount charges is not present in db");
        }
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see the cancelled trip icon displayed for the delivery$")
    public void i_should_see_the_cancelled_trip_icon_displayed_for_the_delivery() throws Throwable {
        try{
        Thread.sleep(1000);
        testStepAssert.isElementDisplayed(admin_RevivalPage.Icon_CancelledTrip(),"Cancelled icon should be displayed","Cancelled icon is displayed","Cancelled icon is not displayed");
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^I search the delivery using old pickup reference$")
    public void i_search_the_delivery_using_old_pickup_reference() throws Throwable {
        try{
        String oldPickupRef = (String) cucumberContextManager.getScenarioContext("OLD_PICKUP_REQUEST");
        Thread.sleep(2000);
        action.clearSendKeys(adminTripsPage.TextBox_Search(), oldPickupRef + Keys.ENTER);
        log("I should be able to search the delivery using the old pickup reference",
                "I could search the delivery using the old pickup reference",false);
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^The pickup reference should be changed to the new pickup reference$")
    public void the_pickup_reference_should_be_changed_to_the_new_pickup_reference() throws Throwable {
        try{
      String[] newPickupReferenceEntireText = action.getText(admin_RevivalPage.Label_PickUpReference()).split(":");
      String newPickupReference = newPickupReferenceEntireText[1].trim();
      String oldPickupRef = (String) cucumberContextManager.getScenarioContext("OLD_PICKUP_REQUEST");
      testStepVerify.isEquals(newPickupReference,oldPickupRef,"The pickup reference should be changed to " +newPickupReference,"The pickup reference is changed to " +newPickupReference,"The pickup reference is not changed to " +newPickupReference);
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }


    @And("^I set the cancelled delivery pickup reference as recent pickup reference to verify data in db$")
    public void i_set_the_cancelled_delivery_pickup_reference_as_recent_pickup_reference_to_verify_data_in_db() throws Throwable {
        try{
        String pickupreference = (String) cucumberContextManager.getScenarioContext("OLD_PICKUP_REQUEST");
        cucumberContextManager.setScenarioContext("PICKUP_REQUEST",pickupreference);
    }catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @Then("^The revive button should not be displayed$")
    public void the_revive_button_should_not_be_displayed() throws Throwable {
        try{
            Thread.sleep(2000);
            testStepAssert.isFalse(action.isElementPresent(admin_RevivalPage.Button_ReviveTrip(true)),"Revive button should not be displayed", "Revive button is not displayed", "Revive button is displayed");
        } catch (Exception e) {;
            error("Step  Should be successful", "Error in viewing alert", true);
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        }
    }



}