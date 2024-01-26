package com.bungii.ios.stepdefinitions.customer;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.BungiiDetails;
import com.bungii.ios.pages.customer.ScheduledBungiiPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.*;

public class BungiiDetailsSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(BungiiDetailsSteps.class);
    ActionManager action = new ActionManager();
    ScheduledBungiiPage scheduledBungiiPage = new ScheduledBungiiPage();
    BungiiDetails bungiiDetails;

    public BungiiDetailsSteps(BungiiDetails bungiiDetails) {
        this.bungiiDetails = bungiiDetails;
    }


    @Then("^I Cancel selected Bungii$")
    public void i_cancel_selected_bungii() {
        try {
            action.swipeUP();
            action.click(bungiiDetails.Button_CancelBungii());
            Thread.sleep(1000);
            action.waitForAlert();
            SetupManager.getDriver().switchTo().alert().accept();
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^trips status should be \"([^\"]*)\"$")
    public void trips_status_should_be_something(String key) throws Throwable {

            String tripStatus = "";
            switch (key.toLowerCase()) {
                case "contacting drivers":
                    tripStatus = action.getNameAttribute(scheduledBungiiPage.Trip_Status());
                    testStepAssert.isEquals(tripStatus, "Contacting Drivers", "Contacting Drivers should be displayed","Contacting Drivers is displayed","Contacting Drivers is not displayed");
                    break;
                case "estimated cost":
                    tripStatus = action.getNameAttribute(scheduledBungiiPage.Trip_Status());
                    testStepAssert.isEquals(tripStatus, (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"),"Estimated cost should be displayed","Estimated cost is displayed","Estimated cost is not displayed");
                    break;
                case "estimated cost of duo trip":
                    String estimate = (String) cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE");
                    double flestimate=Double.valueOf(estimate.replace("~$","").trim());
                    //transaction fee different for solo and duo
                    double transactionFee=((flestimate*0.029*0.5)+0.3)*2;
                    double estimatedDriverCut=(0.7*flestimate)-transactionFee;
                    //divide by 2 for individual driver value
                    String truncValue = new DecimalFormat("#.00").format(estimatedDriverCut/2);
                    tripStatus = action.getNameAttribute(scheduledBungiiPage.Trip_Status());
                    testStepAssert.isEquals(tripStatus,"~$"+truncValue,"Estimated cost should be displayed","Estimated cost is displayed","Estimated cost is not displayed");
                    break;
                case "contacting other driver":
                    tripStatus = action.getNameAttribute(scheduledBungiiPage.Trip_Status());
                    testStepAssert.isEquals(tripStatus, "Contacting Other Driver", "Contacting Other Driver should be displayed","Contacting Other Driver is displayed","Contacting Other Driver is not displayed");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
    }

    @When("^I try to contact driver using \"([^\"]*)\"$")
    public void i_select_something_option_for_something(String key) throws Throwable {
        try {
            switch (key.toLowerCase()) {
                case "call driver1":
                    action.click(bungiiDetails.Button_Driver1Call());
                    break;
                case "sms driver1":
                    action.click(bungiiDetails.Button_Driver1SMS());
                    break;
                case "call driver2":
                    action.click(bungiiDetails.Button_Driver2Call());
                    break;
                case "sms driver2":
                    action.click(bungiiDetails.Button_Driver2SMS());
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            log("cusomer should able to click on"+key,"Customer clicked on "+key, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^trips status on bungii details should be \"([^\"]*)\"$")
    public void trips_status_on_bungii_details_should_be_something(String strArg1) throws Throwable {
        try {
            String tripStatus = "";
            switch (strArg1.toLowerCase()) {
                case "driver 1 - contacting drivers":
                    tripStatus = action.getNameAttribute(bungiiDetails.Text_Driver1Status_iOS11_2());
                    testStepVerify.isEquals(tripStatus, "Contacting");
                    testStepVerify.isElementEnabled(bungiiDetails.Text_Driver1Status_iOS11_Tag(), " Driver # 1 tag should be displayed");
                    break;
                case "driver 2 - contacting drivers":
                    tripStatus = action.getNameAttribute(bungiiDetails.Text_Driver2Status_iOS11_2());
                    testStepVerify.isEquals(tripStatus, "Contacting");
                    testStepVerify.isElementEnabled(bungiiDetails.Text_Driver2Status_iOS11_Tag(), " Driver # 2 tag should be displayed");
                    break;
                case "driver1 name":
                    tripStatus = action.getNameAttribute(bungiiDetails.Text_Driver1Name());
                    String expectedDriverName = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
                    expectedDriverName = expectedDriverName.substring(0, expectedDriverName.indexOf(" ") + 2);
                    testStepVerify.isEquals(tripStatus, expectedDriverName);
                    break;
                case "driver2 name":
                    tripStatus = action.getNameAttribute(bungiiDetails.Text_Driver2Name());
                    String expectedDriver2Name = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
                    expectedDriver2Name = expectedDriver2Name.substring(0, expectedDriver2Name.indexOf(" ") + 2);
                    testStepVerify.isEquals(tripStatus, expectedDriver2Name);
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^message stating contact driver should be \"([^\"]*)\"$")
    public void message_stating_contact_driver_should_be_something(String strArg1) throws Throwable {
        try {
            String tripStatus = "";
            switch (strArg1.toLowerCase()) {
                case "displayed":
                    tripStatus = action.getNameAttribute(bungiiDetails.Text_MessageToCustomer());
                    testStepVerify.isEquals(tripStatus, "Your driver will contact you when they are en-route.");
                    testStepVerify.isElementEnabled(bungiiDetails.Text_MessageToCustomer(), " text stating that driver can be contacted on the Bungii Details page should be displayed");
                    break;
                case "not be displayed":
                    testStepVerify.isTrue(!action.isElementPresent(bungiiDetails.Text_MessageToCustomer(true)), " text stating that driver can be contacted on the Bungii Details page should not be displayed");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    //TODO: Add duo
    @Then("^Trip Information should be correctly displayed on BUNGII DETAILS screen$")
    public void trip_information_should_be_correctly_displayed_on_something_screen() {
        try {

            String[] tripInfo = getSoloBungiiLocationInformation();
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME")),
                    estimate = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_ESTIMATE"));
            String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

            if(TimeZone.getTimeZone("America/New_York").inDaylightTime(new Date())) {
                tripTime = tripTime.replace("ST","DT").replace("st","dt");
            }

            String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",", "").trim();
            String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",", "").trim();

            String dropOffLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",", "").trim();
            String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",", "").trim();

            boolean isPickUpAddressCorrect = tripInfo[0].equals(pickUpLocationLineOne) && tripInfo[1].equals(pickUpLocationLineTwo),
                    isDropAddressCorrect = tripInfo[5].equals(dropOffLocationLineOne) && tripInfo[6].equals(dropOffLocationLineTwo),
                    isTimeCorrect = tripInfo[3].contains(tripTime.substring(0,13).replace(",", " |")),
                    isEstimateCorrect = tripInfo[4].equals(estimate);

            if (!tripNoOfDriver.toUpperCase().equals("SOLO")) {
                // customerbungiiDetails.getDriver2Status()
            }

            testStepVerify.isTrue(isPickUpAddressCorrect,
                    "Pick up address should be " + pickUpLocationLineOne + pickUpLocationLineTwo, "Pick up address is " + pickUpLocationLineOne + pickUpLocationLineTwo,
                    "Expected pickup address is " + pickUpLocationLineOne + pickUpLocationLineTwo + ", but actual is" + tripInfo[0] + tripInfo[1]);
            testStepVerify.isTrue(isDropAddressCorrect,
                    "Drop address should be " + dropOffLocationLineOne + dropOffLocationLineTwo, "Drop address is " + dropOffLocationLineOne + dropOffLocationLineTwo,
                    "Expected Drop address is " + dropOffLocationLineOne + dropOffLocationLineTwo + ", but actual is" + tripInfo[1]);
            testStepVerify.isTrue(isTimeCorrect,
                    "Trip time should be " + tripTime, "Trip time is " + tripTime,
                    "Expected Trip time is " + tripTime + ", but actual is" + tripInfo[3]);
            testStepVerify.isTrue(isEstimateCorrect,
                    "Trip Estimate should be " + estimate, "Trip Estimate is " + estimate,
                    "Expected Trip Estimate is " + estimate + ", but actual is" + tripInfo[4]);
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^ratting should be correctly displayed on Bungii detail page$")
    public void ratting_should_be_correctly_displayed_on_bungii_accepted_page() throws Throwable {
        try {
            String    driverPhoneNumber=(String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");

            String ratingString = DbUtility.getDriverRating(driverPhoneNumber);
            cucumberContextManager.setScenarioContext("DRIVER_CURRENT_RATTING",ratingString);
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(ratingString));
            int ratingInt = bigDecimal.intValue();
            BigDecimal ratingDecimal = bigDecimal.subtract(new BigDecimal(ratingInt));

            System.out.println("ratingString: " + ratingString);
            System.out.println("Integer Part: " + ratingInt);
            System.out.println("Decimal Part: " + ratingDecimal);

            bungiiDetails.WaitUntilElementIsDisplayed(By.xpath("//XCUIElementTypeButton[@name=\"rating filled star icon\"])"));

            int filledStarCount = bungiiDetails.FilledStars().size();
            int HalfFilledStarCount = bungiiDetails.HalfFilledStar().size();

            testStepVerify.isEquals(filledStarCount, ratingInt);

            if (ratingDecimal.doubleValue() >= 0.5) {
                testStepVerify.isEquals(HalfFilledStarCount, 1);
            }
        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    /**
     * Get Bungii pickup/drop information on Bungii details page
     *
     * @return details for bungii
     */
    public String[] getSoloBungiiLocationInformation() {

        String[] locationInformation = new String[7];
        locationInformation[0] = action.getValueAttribute(bungiiDetails.Text_PickUpLocationLine1()).replace(",", "").trim();
        locationInformation[1] = action.getValueAttribute(bungiiDetails.Text_PickUpLocationLine2()).replace(",", "").trim();
        if (action.isElementPresent(bungiiDetails.Text_Driver1Status(true))) {
            locationInformation[2] = action.getValueAttribute(bungiiDetails.Text_Driver1Status());
            locationInformation[3] = action.getValueAttribute(bungiiDetails.Text_Time());
            locationInformation[4] = action.getValueAttribute(bungiiDetails.Text_TotalEstimate());
        } else {
            locationInformation[2] = action.getValueAttribute(bungiiDetails.Text_Driver1Status_iOS11_2());
            locationInformation[3] = action.getValueAttribute(bungiiDetails.Text_Time_iOS11_2());
            locationInformation[4] = action.getValueAttribute(bungiiDetails.Text_TotalEstimate_iOS11_2());
        }

        locationInformation[5] = action.getValueAttribute(bungiiDetails.Text_DropLocationLine1()).replace(",", "").trim();
        locationInformation[6] = action.getValueAttribute(bungiiDetails.Text_DropLocationLine2()).replace(",", "").trim();
        return locationInformation;

    }

    /**
     * Get driver 2 status
     *
     * @return return status of driver 2
     */
    public String getDriver2Status() {
        return action.getValueAttribute(bungiiDetails.Text_Driver2Status());
    }


}
