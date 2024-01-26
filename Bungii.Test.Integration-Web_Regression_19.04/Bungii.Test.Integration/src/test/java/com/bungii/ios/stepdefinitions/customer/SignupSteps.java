package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.enums.REFERRAL_SOURCE;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.SignupPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import cucumber.api.java.en.And;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;

public class SignupSteps extends DriverBase {
	private SignupPage signupPage;
	private static LogUtility logger = new LogUtility(SignupSteps.class);
	ActionManager action = new ActionManager();
	public SignupSteps(SignupPage signupPage) {
		this.signupPage = signupPage;
	}



	@And("^I Select Referral source as \"([^\"]*)\"$")
	public void iSelectReferralSourceAs(String source) {

		if (source.equals("{BLANK}")) {
			action.swipeUP();
			pass( "I leave referral source as empty",
					" I left  referral source as empty", true);
		} else {
			try {
				action.hideKeyboard();
				action.swipeUP();
				action.click(signupPage.Button_Chevron());
						String key = source.toUpperCase().replaceAll(" ", "_");
				if (EnumUtils.isValidEnum(REFERRAL_SOURCE.class, source.toUpperCase().replaceAll(" ", "_"))) {
					String value = REFERRAL_SOURCE.valueOf(key).toString();
					clickReferralSource(value);
					action.click(signupPage.Button_Done());
					pass("I should able to entered referral source as "+source,
							"I entered referral source as "+source, true);
				} else {

					error("Please enter valid referral source",
							"Error performing step,Please check logs for more details", true);

				}
			} catch (Exception e) {
				logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
				error( "Step  Should be successful",
						"Error performing step,Please check logs for more details", true);
			}
		}
	}
	@And("^I Get SMS CODE for new \"([^\"]*)\"$")
	public void i_get_sms_code_for_new_something(String strArg1) {
		try {

			String phone = (String) cucumberContextManager.getScenarioContext("NEW_USER_NUMBER");
			// TODO remove this
			Thread.sleep(20000);
			String smsCode = DbUtility.getVerificationCode(phone);

			cucumberContextManager.setScenarioContext("SMS_CODE", smsCode);
			testStepAssert.isFalse(smsCode.equals(""),
					"I should able to fetch value for sms code", "SMS CODE for " + strArg1 + " is " + smsCode,
					"Not able to fetch sms code for " + strArg1);
		} catch (Throwable e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details",
					true);
		}
	}

	/**
	 * Click on referral source base on input key
	 * @param source Source  of referral
	 */
	public void clickReferralSource(String source){

		action.click(signupPage.findElement("//XCUIElementTypeStaticText[@value='"+source+"']", PageBase.LocatorType.XPath));

	}

}
