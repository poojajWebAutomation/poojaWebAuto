package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.AccountPage;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class AccountSteps extends DriverBase {
	AccountPage accountPage;
	ActionManager action = new ActionManager();
	private static LogUtility logger = new LogUtility(AccountSteps.class);

	public AccountSteps(AccountPage accountPage) {
		this.accountPage = accountPage;
	}

	/**
	 * Read customer details and store it in scenario context
	 * 
	 */
	@Then("^I get customer account details$")
	public void i_get_customer_account_details() {
		try {
			String[] details = new String[2];
			details[0]=action.getValueAttribute(accountPage.Text_Name());
			details[1]=action.getValueAttribute(accountPage.Text_Phone());

			//replace all character
			String phone =details[1].replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
			//store customer name and phone in scenario context
			cucumberContextManager.setScenarioContext("CUSTOMER", details[0]);
			cucumberContextManager.setScenarioContext("CUSTOMER_PHONE",phone );
			
			logger.detail("I get customer account details , Customer name is " + details[0]+ " and Phone Number is "+ details[1]);
			log( "I get customer account details", "Customer name is " + details[0] + " and Phone Number is "+ details[1],
					true);
		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
		}
	}



}
