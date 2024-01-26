package com.bungii.ios.stepdefinitions.customer;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.ScheduledBungiiPage;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.pass;


public class ScheduledBungiiSteps extends DriverBase {
	ScheduledBungiiPage scheduledBungiiPage;
	ActionManager action = new ActionManager();
	String Image_Solo = "bungii_type-solo";
	GeneralUtility utility= new GeneralUtility();
	private static LogUtility logger = new LogUtility(ScheduledBungiiSteps.class);

	public ScheduledBungiiSteps(ScheduledBungiiPage scheduledBungiiPage) {
		this.scheduledBungiiPage = scheduledBungiiPage;
	}

	@And("^I should select the \"([^\"]*)\" customer on driver app$")
	public void i_should_select_the_something_customer_on_driver_app(String customer) throws Throwable {
		try{
			String customerTripDateTime = (String) cucumberContextManager.getScenarioContext("CUSTOMER_APP_TRIP_TIME");
			action.click(scheduledBungiiPage.Customer_ScheduledDelivery(customerTripDateTime));
		pass("I should able to select scheduled bungii for customer"+customer,"I have selected scheduled bungii for customer"+customer);
		}
		catch (Exception e) {
			//logger.error("Error performing step", SetupManager.getDriver().getPageSource());
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
		}
	}

	@When("^I select already scheduled bungii$")
	public void i_select_already_scheduled_bungii() {
		try {
			String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
			String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
			//String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
			logger.detail("Trip Time is ",tripTime);
			selectBungii(tripNoOfDriver, tripTime);
			pass("I select scheduled bungii", "I have selected already scheduled bungii of "+tripNoOfDriver+" type and at time: " + tripTime , true);
		} catch (Exception e) {
			//logger.error("Error performing step", SetupManager.getDriver().getPageSource());
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
		}
	}

	@When("^I select 1st trip from scheduled bungii$")
	public void i_select_first_already_scheduled_bungii() {
		try {
			action.click(scheduledBungiiPage.Cell_FirstTrip());
			pass("I select already scheduled bungii", "I selected first already scheduled bungii" , true);
		} catch (Exception e) {
			logger.error("Error performing step", SetupManager.getDriver().getPageSource());
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful", "Error performing step,Please check logs for more details", true);
		}
	}


	@Then("^Bungii must be removed from \"([^\"]*)\" screen$")
	public void bungii_must_be_removed_from_something_screen(String screen) {
		try {
			String tripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
			String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));

			Thread.sleep(20000);
			action.swipeDown();
			boolean isBungiiPresent =isBungiiPresent(tripNoOfDriver, tripTime);
			//do half screen swipe if Bungii is present
			if(isBungiiPresent)
			{	halfScreenSwipe();
				isBungiiPresent = isBungiiPresent(tripNoOfDriver, tripTime);
			}
			testStepVerify.isFalse(isBungiiPresent, "Bungii must be removed from " + screen + " screen",
					"Bungii is be deleted ,there is no bungii for time:"+tripTime, "Bungii is not deleted");
		} catch (Exception e) {
			logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
			error( "Step  Should be successful",
					"Error performing step,Please check logs for more details", true);
		}
	}
	private void halfScreenSwipe(){
		Dimension size = SetupManager.getDriver().manage().window().getSize();
		int startx = (int) (size.width * 0.5);
		int endx = (int) (size.width * 0.5);
		int starty = size.height / 2;
		int endy = size.height ;

		action.dragFromToForDuration(startx, starty, endx, endy, 1);
	}



	/**
	 * Construct locator for bungii from given bungii information
	 *
	 * @param bungiiType
	 *            identifer for bungii type
	 * @param bungiiTime
	 *            Scheduled bungii time
	 * @return
	 */
	public WebElement getLocatorForBungii(String bungiiType, String bungiiTime) {
		String UIBungiiTime = action.getText(scheduledBungiiPage.Text_ScheduledTime());
		String imageTag = "";
		if (bungiiType.toUpperCase().equals("SOLO")) {
			imageTag = Image_Solo;
		}
		action.swipeDown();
		String currentYear = String.valueOf(DateTime.now().getYear());
		String gmtTime="";
		String justTime="";
		if(UIBungiiTime.contains("AM")||UIBungiiTime.contains("PM")) {
			gmtTime = bungiiTime.replace("-", "");
			justTime = bungiiTime.replace(" - ", "");
		}else if(UIBungiiTime.contains("a.m.")||UIBungiiTime.contains("p.m.")){
			//gmtTime = bungiiTime.replace("AM", "a.m.").replace("PM", "p.m.").replace(currentYear, "").replace(" - ", "");
			gmtTime = bungiiTime.replace("AM", "a.m.").replace("PM", "p.m.").replace(" - ", "");
			//justTime = bungiiTime.replace("AM", "").replace("PM", "").replace(currentYear, "").replace(" - ", "");
			justTime = bungiiTime.replace("AM", "").replace("PM", "").replace(" - ", "");
		}

		if(gmtTime.contains("MDT")||gmtTime.contains("MST"))
			gmtTime = gmtTime.replace("MDT","GMT-6").replace("MST","GMT-6");
		if(gmtTime.contains("CST")||gmtTime.contains("CDT"))
			gmtTime = gmtTime.replace("CST","GMT-5").replace("CDT","GMT-5");
		if(gmtTime.contains("EST")||gmtTime.contains("EDT"))
			gmtTime = gmtTime.replace("EST","GMT-4").replace("EDT","GMT-4");
		if(gmtTime.contains("PST")||gmtTime.contains("PDT"))
			gmtTime = gmtTime.replace("PST","GMT-7").replace("PDT","GMT-7");
		if(gmtTime.contains("IST"))
			gmtTime = gmtTime.replace("IST","GMT+5:30");

		if(justTime.contains("MDT")||justTime.contains("MST"))
			justTime = justTime.replace("MDT","").replace("MST","");
		if(justTime.contains("CST")||justTime.contains("CDT"))
			justTime = justTime.replace("CST","").replace("CDT","");
		if(justTime.contains("EST")||justTime.contains("EDT"))
			justTime = justTime.replace("EST","").replace("EDT","");
		if(justTime.contains("PST")||justTime.contains("PDT"))
			justTime = justTime.replace("PST","").replace("PDT","");
		if(justTime.contains("IST"))
			justTime = justTime.replace("IST","");
		if(justTime.contains("GMT"))
			justTime = justTime.substring(0,12);

		String browserStackTime = "//XCUIElementTypeStaticText[contains(@name,'"+gmtTime+"') or contains(@name,'" + bungiiTime + "') or contains(@name,'"+ justTime.trim()+"')]/parent::XCUIElementTypeCell";
		//By Image_SelectBungii = MobileBy.xpath("//XCUIElementTypeStaticText[@name='" + bungiiTime+ "']/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell");
		WebElement Image_SelectBungii =null;
		logger.detail("Trip xpath : "+ browserStackTime);
		try {
			//	WebElement Image_SelectBungii=scheduledBungiiPage.findElement("//XCUIElementTypeStaticText[@name='" + bungiiTime+ "']/following-sibling::XCUIElementTypeImage[@name='" + imageTag + "']/parent::XCUIElementTypeCell", PageBase.LocatorType.XPath);
			if (action.isElementPresent(scheduledBungiiPage.findElement(browserStackTime, PageBase.LocatorType.XPath, true)))
				Image_SelectBungii = scheduledBungiiPage.findElement(browserStackTime, PageBase.LocatorType.XPath);
			else
				Image_SelectBungii = scheduledBungiiPage.findElement(browserStackTime, PageBase.LocatorType.XPath);
		}
		catch(Exception ex)
		{
			//error("Element with [Locator : "+browserStackTime+" ] by type [ "+"XPath"+" ] should be displayed in Driver Scheduled List", "Element with [Locator : "+browserStackTime+" ] by type [ "+"XPath"+" ] is not displayed in Driver Scheduled List. Please refer error logs for more details.",
			//		true);
		}
		return Image_SelectBungii;
	}


	/**
	 * select bungii
	 * @param bungiiType
	 *            identifer for bungii type
	 * @param bungiiTime
	 *            Scheduled bungii time
	 */
	public void selectBungii(String bungiiType, String bungiiTime) {
		Date currentDate = new Date();
		int year=currentDate.getYear()+1900;
		//Handle code to update Daylight
		if(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()).inDaylightTime( new Date() ))
		bungiiTime=bungiiTime.replace("ST","DT");
		//XCUIElementTypeStaticText[contains(@name,'Oct 13, 11:45 a.m. GMT-6') or contains(@name,'Oct 13, 2020 - 11:45 AM MDT') ]/parent::XCUIElementTypeCell

		//action.click(getLocatorForBungii(bungiiType, bungiiTime.replace(",",", "+year+" -")));
		action.click(getLocatorForBungii(bungiiType, bungiiTime));
	}

	/**
	 * Check if bungii is present
	 * @param bungiiType
	 *            Bungii Type , Solo /Duo
	 * @param bungiiTime
	 *            Scheduled bungii time
	 * @return
	 */
	public boolean isBungiiPresent(String bungiiType, String bungiiTime) {
		try{
		return scheduledBungiiPage.isElementEnabled(getLocatorForBungii(bungiiType, bungiiTime));}
		catch (Exception e){
			return  false;
		}
	}


}
