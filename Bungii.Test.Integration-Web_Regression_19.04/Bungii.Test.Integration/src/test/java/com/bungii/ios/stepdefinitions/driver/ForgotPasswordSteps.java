package com.bungii.ios.stepdefinitions.driver;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.pages.driver.ForgotPasswordPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class ForgotPasswordSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(ForgotPasswordSteps.class);

    ForgotPasswordPage driverForgotPasswordPage = new ForgotPasswordPage();

    @And("^I Get SMS CODE for \"([^\"]*)\" number on driverApp$")
    public void i_get_sms_code_for_driverNumber(String strArg1) {
        try {
            String phoneNumber="";
            if(strArg1.equalsIgnoreCase("{VALID USER}")) {
                phoneNumber = strArg1.equalsIgnoreCase("{VALID USER}") ? PropertyUtility.getDataProperties("ios.valid.driver.phone") : strArg1;
            }else if(strArg1.equalsIgnoreCase("{VALID USER1}")) {
                phoneNumber = strArg1.equalsIgnoreCase("{VALID USER1}") ? PropertyUtility.getDataProperties("ios.valid.driver1.phone") : strArg1;
            }
            Thread.sleep(3000);
            String smsCode = DbUtility.getVerificationCodeDriver(phoneNumber);
//            driverForgotPasswordPage.visibilityOf(driverForgotPasswordPage.Button_Continue());

            cucumberContextManager.setScenarioContext("SMS_CODE", smsCode);
            testStepAssert.isFalse(smsCode.equals(""),
                    "I should able to fetch value for sms code", "SMS CODE for " + strArg1 + " is " + smsCode,
                    "Not able to fetch sms code for " + strArg1);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

}
