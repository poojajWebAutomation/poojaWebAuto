package com.bungii.ios.stepdefinitions.customer;

import com.bungii.android.pages.otherApps.OtherAppsPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.VerificationPage;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;


public class VerificationSteps extends DriverBase {
	VerificationPage verificationPage;
	OtherAppsPage otherAppsPage = new OtherAppsPage();
	private static LogUtility logger = new LogUtility(VerificationSteps.class);
	ActionManager action = new ActionManager();
	public VerificationSteps(VerificationPage verificationPage) {
		this.verificationPage = verificationPage;
	}

	@When("^I enter \"([^\"]*)\" Verification code$")
	public void i_enter_something_verificationcode(String strArg1) {
		try {
			String smsCode = (String) cucumberContextManager.getScenarioContext("SMS_CODE");
			action.clearEnterText(verificationPage.TextBox_SmsCode(),smsCode);
			action.hideKeyboard();
			action.swipeUP();
		//	if(action.isElementPresent(otherAppsPage.Button_ReturnKey())){otherAppsPage.Button_ReturnKey();}
			action.click(verificationPage.Button_Verify());

			pass( "I should able to enter verification code",
					"I entered verification code : " + smsCode +"in sms code field", true);
			//TODO:REMOVE THIS
			Thread.sleep(20000);

		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
		}
	}



}
