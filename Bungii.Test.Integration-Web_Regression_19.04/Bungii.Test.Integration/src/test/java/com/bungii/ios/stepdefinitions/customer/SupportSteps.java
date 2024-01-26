package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.SupportPage;
import com.bungii.ios.stepdefinitions.CommonSteps;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import static com.bungii.common.manager.ResultManager.error;

public class SupportSteps extends DriverBase {
	private SupportPage supportPage;
	public SupportSteps(SupportPage supportPage) {
		this.supportPage =supportPage;
	}
	ActionManager action = new ActionManager();
	private static LogUtility logger = new LogUtility(SupportSteps.class);

	@Then("^I should see \"([^\"]*)\" on Support Page$")
	public void i_should_see_something_on_support_page(String identifier) throws Throwable {

		try {
			switch (identifier.toUpperCase()) {
				case "support text label":
					// homePage.clickEstimateButton();
					break;
				default:
					error("UnImplemented Step or incorrect button name",
							"UnImplemented Step");
					break;
			}


		} catch (Throwable e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error("Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}	}


	/**
	 * Enter data in support text box
	 * @param inputData Text that is to be entered on Support text box
	 */
	public void enterDataInSupport(String inputData){
		supportPage.TextBox_Support().sendKeys( inputData);
	}

	/**
	 * Click on send button
	 */
	public void clickSendButton(){
			action.click(supportPage.Button_Send());
	}

	/**
	 * Get support question displayed
	 * @return get valye of support question
	 */
	public String getSupportQuestion(){
		return action.getValueAttribute(supportPage.Text_SupportQuestion());
	}

	/**
	 * Check if Bungii customer logo is displayed
	 * @return boolean value that bungii customer logo is displayed on not
	 */
	public boolean isCustomerLogoPresent(){
		return supportPage.isElementEnabled(supportPage.Image_CustLogo());
	}

}
