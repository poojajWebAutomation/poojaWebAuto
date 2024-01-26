package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.InProgressBungiiPages;
import com.bungii.android.pages.otherApps.MessagesPage;
import com.bungii.android.stepdefinitions.Driver.LoginSteps;
import com.bungii.android.utilityfunctions.GeneralUtility;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.android.pages.customer.*;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class TripStatusSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(LoginSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    EstimatePage estimatePage=new EstimatePage();
    InProgressBungiiPages inProgressBungiiPages = new InProgressBungiiPages();
    MessagesPage messagesPage =new MessagesPage();

    @Then("^correct support details should be displayed to customer on \"([^\"]*)\" app$")
    public void correct_support_details_should_be_displayed_to_customer_on_something_app(String key) {
        try {
            switch (key.toUpperCase()) {
                case "SMS":
                    validateSMSNumber(action.getText(messagesPage.Text_ToField()).replace(" ", ""), PropertyUtility.getMessage("scheduled.support.number"));
                    break;

                case "CALL":
                    validateCallButtonAction(PropertyUtility.getMessage("scheduled.support.number"));
                    break;
                default:
                    throw new Exception("UN IMPLEMENTED STEPS");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    private void validateCallButtonAction(String expectedNumber) {
        action.waitForAlert();
        String actualMessage = estimatePage.Alert_ConfirmRequestMessage().getText().replace("(", "").replace(")", "").replace(" ", "").replace("-", "")
                .replace("?", "").replace("+", "").trim();
        actualMessage = actualMessage.substring(1, actualMessage.length() - 1);
        String expectedMessage = expectedNumber.replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "").replace("+", "").trim();
        //   List<String> options = action.getListOfAlertButton();

        boolean isMessageCorrect = actualMessage.equals(expectedMessage)/*,isOptionCorrect = options.contains("Cancel") && options.contains("Call")*/;


        testStepVerify.isTrue(isMessageCorrect,
                "I should be alerted to call twillo number", "Twillo number was displayed in alert message",
                "Twillo number was not displayed in alert message , Actual message :" + actualMessage
                        + " , Expected Message:" + PropertyUtility.getMessage("twilio.number"));

/*            testStepVerify
                    .isTrue(isOptionCorrect,
                            "Alert should have option to cancel and call twilio number ",
                            "Alert  have option to cancel and call twilio number , options are" + options.get(0)
                                    + " and " + options.get(1),
                            "Alert dont have option to cancel and call twilio number");*/

        action.NavigateBack();
    }

    private void validateSMSNumber(String actualValue,String expectedValue) {
        String expectedNumber = expectedValue.replace("(", "").replace(")", "").replace(" ", "")
                .replace("-", "");
        
        boolean isPhoneNumCorrect = actualValue.contains(expectedNumber);

        testStepVerify.isTrue(isPhoneNumCorrect,
                "To Field should contains " + expectedNumber,
                "To Field should contains " + expectedNumber + "and  actual value is" + actualValue,
                "To Field should contains " + expectedNumber + "and  actual value is" + actualValue);

        action.NavigateBack();
        try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
    }
}
