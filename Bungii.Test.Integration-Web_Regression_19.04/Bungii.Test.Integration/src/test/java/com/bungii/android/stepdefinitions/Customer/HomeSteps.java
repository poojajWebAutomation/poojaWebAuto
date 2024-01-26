package com.bungii.android.stepdefinitions.Customer;

import com.bungii.SetupManager;
import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.*;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;

public class HomeSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(HomeSteps.class);
    GeneralUtility utility = new GeneralUtility();
    HomePage homePage = new HomePage();
    PaymentPage paymentPage = new PaymentPage();
    SignupPage Page_Signup = new SignupPage();
    EstimatePage estimatePage = new EstimatePage();
    ActionManager action = new ActionManager();
    SetPickupTimePage setPickupTimePage = new SetPickupTimePage();
    HomePage Page_CustHome = new HomePage();
    ScheduledBungiisPage scheduledBungiisPage = new ScheduledBungiisPage();

    @When("^I Select \"([^\"]*)\" from customer app menu list$")
    public void i_select_something_from_customer_app_menu_list(String strArg1) throws Throwable {
        try {
            //    action.click(homePage.Button_NavigationBar());
            utility.clickCustomerMenuItem(strArg1);
            log(" I Select " + strArg1 + " from customer app menu list", " I tapped on " + strArg1 + " from customer app menu list");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I tap on \"([^\"]*)\" > \"([^\"]*)\" link$")
    public void i_tap_on_something_something_link(String strArg1, String strArg2) throws Throwable {
        try {
            utility.clickCustomerMenuItem(strArg2);
            Thread.sleep(2000);
            action.scrollToBottom();

            log(" I should able to tap on " + strArg2, " I tapped on " + strArg2, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in tapping on menu item : "+ strArg2, true);
        }
    }

    @When("^I tap on the \"([^\"]*)\" link$")
    public void i_tap_on_the_something_link(String strArg1) throws Throwable {
        try {
            utility.clickCustomerMenuItem(strArg1);
            Thread.sleep(2000);
            action.scrollToBottom();

            log(" I should able to tap on " + strArg1, " I tapped on " + strArg1, true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in tapping on menu item : "+ strArg1, true);
        }
    }

    @Then("^Customer active flag should be \"([^\"]*)\"$")
    public void i_driveractive_flag_should_be_something(String strArg1) throws Throwable {
        try {
            String phone = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");
            String actualActiveFlag = DbUtility.getActiveFlag(phone);
            testStepVerify.isEquals(strArg1, actualActiveFlag, "Active flag should be :" + strArg1, "Active flag is :" + actualActiveFlag);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^Driver active flag should be \"([^\"]*)\"$")
    public void i_active_flag_should_be_something(String strArg1) throws Throwable {
        try {
            String phone = (String) cucumberContextManager.getScenarioContext("DRIVER_1_PHONE");
            String actualActiveFlag = DbUtility.getDriverActiveFlag(phone);
            testStepVerify.isEquals(strArg1, actualActiveFlag, "Active flag should be :" + strArg1, "Active flag is :" + actualActiveFlag);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    @When("^I tap \"([^\"]*)\" on Home page$")
    public void i_tap_something_on_home_page(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Referral Invite link":
                    Thread.sleep(3000);
                    action.click(homePage.Link_Invite());
                    break;
                case "Drop Clear Text":
                    Thread.sleep(3000);
                    action.click(homePage.Button_ClearDrop());
                    break;
                case "Pick Up Clear Text":
                    action.click(homePage.Button_ClearPickUp());
                    break;
                case "My location":
                    action.click(homePage.Button_Locator());
                    Thread.sleep(3000);
                    String addressValue = action.getText(homePage.TextBox_PickUpLocLine1());
                    if (addressValue.isEmpty())
                        action.click(homePage.Button_Locator());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");

            }
            log(" I should able to tap on " + strArg1 + " on Home page", " I tapped on " + strArg1 + " on Home page", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^Trip Information should be correctly displayed on CUSTOMER HOME screen$")
    public void trip_information_should_be_correctly_displayed_on_something_screen() {
        try {
            String expectedPickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")),expectedPickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")),
                    expectedDropLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")),expectedDropLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")), expectedTripNoOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO") ? "DUO" : "SOLO";

            String pickUpLocationLine1 = action.getText(homePage.TextBox_PickUpLocLine1()),pickUpLocationLine2= action.getText(homePage.TextBox_PickUpLocLine2());
            String dropUpLocationLine1 = action.getText(homePage.TextBox_DropOffLine1()),dropUpLocationLine2 = action.getText(homePage.TextBox_DropOffLine2());

            testStepVerify.isTrue(pickUpLocationLine1.equals(expectedPickUpLocationLineOne) && pickUpLocationLine2.equals(expectedPickUpLocationLineTwo),

                    "Pick up address should be " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo, "Pick up address is " + pickUpLocationLine1+pickUpLocationLine2,
                    "Expected pickup address is " + expectedPickUpLocationLineOne +expectedPickUpLocationLineTwo + ", but actual is" + pickUpLocationLine1+pickUpLocationLine2);
            testStepVerify.isTrue(dropUpLocationLine1.equals(expectedDropLocationLineOne) &&  dropUpLocationLine2.equals(expectedDropLocationLineTwo),

                    "Drop address should be " + expectedDropLocationLineOne +expectedDropLocationLineTwo, "Drop address is " + dropUpLocationLine1 +dropUpLocationLine2,
                    "Expected Drop address is " + expectedDropLocationLineOne +expectedDropLocationLineTwo + ", but actual is" + dropUpLocationLine1 +dropUpLocationLine2);
            testStepVerify.isTrue(verifyNoOfDriver(expectedTripNoOfDriver),
                    "Number of driver for Bungii should be " + expectedTripNoOfDriver, "Number of driver for Bungii is " + expectedTripNoOfDriver,
                    "Number of driver for Bungii is not " + expectedTripNoOfDriver);

        } catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    /**
     * Verify number of driver selected
     *
     * @param strDriverType : Identifer for driver type , SOLO, DUO
     * @return
     */
    public boolean verifyNoOfDriver(String strDriverType) {
        String expectedText = "";

        switch (strDriverType.toUpperCase()) {
            case "SOLO":
                expectedText = "1";
                break;
            case "DUO":
                expectedText = "2";
                break;
            default:
                logger.error("Please enter driver type correctly");
        }
        return action.getText(homePage.Switch_SoloDuo()).equals(expectedText);
    }

    @Given("^I am on Customer logged in Home page$")
    public void iAmOnCustomerLoggedInHomePage() {
        try {
            boolean skipNormalFlow=false;
            String currentPage = action.getText(Page_Signup.GenericHeader(true));
            switch (currentPage.toUpperCase()) {
                case "BUNGII":
                    skipNormalFlow=true;
                    break;
                case "FAQ":
                case "ACCOUNT":
                case "SCHEDULED BUNGIIS":
                case "PAYMENT":
                case "SUPPORT":
                case "PROMOS":
                    skipNormalFlow=true;
                    i_tap_on_something_something_link("Menu", "HOME");
                    break;
                case"SIGN UP":
                case"LOGIN":
                    skipNormalFlow=true;
                    utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                    cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer_generic.phonenumber"));
                    break;

            }
                if(!skipNormalFlow){
            //   String NavigationBarName = action.getText(homePage.Title_HomePage(true));
              if (utility.isCorrectPage("Home")) {
                            // do nothing
                        }
              else if (utility.isCorrectPage("Signup") || utility.isCorrectPage("Login")) {
                // utility.loginToCustomerApp(PropertyUtility.getDataProperties("valid.customer.phone"), PropertyUtility.getDataProperties("valid.customer.password"));
                utility.loginToCustomerApp(PropertyUtility.getDataProperties("customer_generic.phonenumber"), PropertyUtility.getDataProperties("customer_generic.password"));
                  cucumberContextManager.setScenarioContext("CUSTOMER_PHONE", PropertyUtility.getDataProperties("customer_generic.phonenumber"));
              } else if (utility.isCorrectPage("Searching")) {
                //  iClickButtonOnScreen("CANCEL", "SEARCHING");
                // iRejectAlertMessage();
            } else {
                i_tap_on_something_something_link("Menu", "HOME");
            }}
            pass(" I should be on Customer Home page", "I am on Customer Home page");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        } catch (Throwable throwable) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(throwable));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I close tutorial Page$")
    public void i_close_tutorial_page() throws Throwable {
        try {
            action.click(homePage.Button_Closetutorials());
            pass("I close tutorial Page", "I clicked on close button",
                    true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^current location should be present as pickup location$")
    public void current_location_should_be_present_as_pickup_location() {
        try {
            String addressValue = action.getText(homePage.TextBox_PickUpLocLine1());
            if (addressValue.isEmpty())
                testStepVerify.isTrue(!addressValue.isEmpty() && !addressValue.equals("Set Pickup Location"),
                        "Pickup location value should be non empty", "Pickup location value is" + addressValue,
                        "Pickup location value should be non empty");

         //   testStepVerify.isEquals(action.getText(homePage.Text_ETAvalue()), "0 MINS");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));

            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^\"([^\"]*)\" box header and ETA bar header should be correctly displayed$")
    public void something_box_header_and_eta_bar_header_should_be_correctly_displayed(String action) {
        try {
            switch (action.toUpperCase()) {
                case "DROP":
                    testStepVerify.isEquals(getEtaBarHeader("DROP"), PropertyUtility.getMessage("customer.drop.etaheader"));
/*                    testStepVerify.isEquals(getLocationBoxHeaderValue("DROP"),
                            PropertyUtility.getMessage("customer.drop.boxheader"));*/
                    break;
                case "PICK UP":
                    testStepVerify.isEquals(getEtaBarHeader("PICKUP"),
                            PropertyUtility.getMessage("customer.pickup.etaheader"));
/*                    testStepVerify.isEquals(getLocationBoxHeaderValue("PICKUP"),
                            PropertyUtility.getMessage("customer.pickup.boxheader"));*/
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));

            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    /**
     * @param location whose eta header is required
     * @return value of eta header
     */
    public String getEtaBarHeader(String location) {
        if (location.equalsIgnoreCase("PICKUP"))
            return action.getText(homePage.Text_ETAHeader());
        else if (location.equalsIgnoreCase("DROP"))
            return action.getText(homePage.Text_ETAHeader());
        else
            return "";
    }

    /**
     * @param location whoes data is required ,pickup or drop
     * @return value of header
     */
    public String[] getLocationBoxHeaderValue(String location) {
        String [] locationBoxText= new String[2];
        if (location.equalsIgnoreCase("PICKUP")) {
            locationBoxText[0] = action.getText(homePage.TextBox_PickUpLocLine1());
            locationBoxText[1] = action.getText(homePage.TextBox_PickUpLocLine2());
        }
        else if (location.equalsIgnoreCase("DROP"))
        {
            locationBoxText[0] = action.getText(homePage.TextBox_PickUpLocLine1());
            locationBoxText[1] = action.getText(homePage.TextBox_PickUpLocLine2());
        }
        return locationBoxText;
    }
    /**
     * @param location whose data is required ,pickup or drop
     * @return value of header
     */
    public String getLocationBoxEditableValue(String location) {
        String locationBoxText= "";
        if (location.equalsIgnoreCase("PICKUP")) {
            locationBoxText = action.getText(homePage.TextBox_PickUp());
        }
        else if (location.equalsIgnoreCase("DROP"))
        {
            locationBoxText = action.getText(homePage.TextBox_DropOff());
        }
        return locationBoxText;
    }

    @When("^I select \"([^\"]*)\" location$")
    public void i_select_something_location(String toDoAction) {
        try {
            switch (toDoAction.toUpperCase()) {
                case "PICK UP":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                         action.click(Page_CustHome.TextBox_PickUpTextBox());
                        utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(),"6800 Zoo Drive");
                    //selectDropLocation(1);
                    break;
                case "DROP":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    utility.selectAddress(Page_CustHome.TextBox_DropOffTextBox(),"6800 Zoo Drive");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP ");
            }
            pass(toDoAction + " location should be selected",
                    toDoAction + " location is selected", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^\"([^\"]*)\" address should be displayed in text box$")
    public void something_address_should_be_displayed_in_text_box(String action) {
        try {
            String []textBoxValue;;
            Thread.sleep(7000);
            switch (action.toUpperCase()) {
                case "DROP":
                    textBoxValue = getLocationBoxHeaderValue("DROP");
                    break;
                case "PICK UP":
                    textBoxValue = getLocationBoxHeaderValue("PICKUP");
                    break;
                default:
                    throw new Exception(" UN IMPLEMENTED STEP");
            }
            testStepVerify.isTrue(
                    !textBoxValue[0].isEmpty() &&!textBoxValue[1].isEmpty() /*&& !textBoxValue.equals("Set Drop Off Location")
                            && !textBoxValue.equals("Set Pickup Location")*/,
                    action + "address bar value should be not empty", action + "address bar value is " + textBoxValue,
                    action + "address bar value is empty");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^\"([^\"]*)\" address should be empty$")
    public void something_address_should_be_empty(String action) {
        try {
            String textBoxValue ;
            switch (action.toUpperCase()) {
                case "DROP":
                    textBoxValue = getLocationBoxEditableValue("DROP");
                    break;
                case "PICK UP":
                    textBoxValue = getLocationBoxEditableValue("PICKUP");
                    break;
                default:
                    throw new Exception(" UN IMPLEMENTED STEP");
            }
            testStepVerify.isTrue(
                    textBoxValue.isEmpty() || textBoxValue.equals("Set Drop Off Location")
                            || textBoxValue.equals("Set Pickup Location"),
                    action + "address bar value is empty",
                    action + "address bar value is not empty , its value is" + textBoxValue);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^\"([^\"]*)\" address text box should be displayed on app screen$")
    public void something_address_text_box_should_be_displayed_on_app_screen(String strArg1) throws Throwable {
        switch (strArg1) {
            case "Drop Off":
                testStepVerify.isTrue(action.isElementPresent(homePage.TextBox_DropOff()), "true");
                break;
        }
    }

    @When("^I clear \"([^\"]*)\" location$")
    public void i_clear_something_location(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Pick up":
                    action.click(homePage.Button_ClearPickUp());
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^The ETA bar is seen on screen$")
    public void the_eta_bar_is_seen_on_screen() throws Throwable {
        try {
            //testStepVerify.isElementDisplayed(homePage.Button_ETASet(), "ETA SET button should be displayed.", "ETA SET button is displayed.", "ETA SET button is not displayed.");
            testStepVerify.isElementDisplayed(homePage.Label_ETAContainer(), "ETA bar should be displayed.", "ETA bar is displayed.", "ETA bar is not displayed.");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @Then("^The ETA bar is not seen on screen$")
    public void the_eta_bar_is_not_seen_on_screen() throws Throwable {
        try {
            //testStepVerify.isElementDisplayed(homePage.Button_ETASet(), "ETA SET button should be displayed.", "ETA SET button is displayed.", "ETA SET button is not displayed.");
            testStepVerify.isElementNotDisplayed(homePage.Label_ETAContainer(true), "ETA bar should not be displayed.", "ETA bar is not displayed.", "ETA bar is displayed.");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I select \"([^\"]*)\" location to check driver within 30mins$")
    public void i_select_something_location_to_check_driver_within_30mins(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Pick up":
                    if (action.isElementPresent(Page_CustHome.Button_ClearPickUp(true)))
                        action.click(Page_CustHome.Button_ClearPickUp());
                    action.click(Page_CustHome.TextBox_PickUpTextBox());
                    utility.selectAddress(Page_CustHome.TextBox_PickUpTextBox(),"6800 Zoo Drive");
                   // action.click(homePage.Button_Locator());
                   // action.click(homePage.Button_Locator());
                    Thread.sleep(3000);
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP ");
            }
            pass(strArg1 + " location should be selected",
                    strArg1 + " location is selected", true);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The ETA bar is seen on screen with less then \"([^\"]*)\" mins$")
    public void the_eta_bar_is_seen_on_screen_with_less_then_something_mins(String strArg1)  {
        try {
            String minutes = homePage.Text_ETAvalue().getText();
            minutes = minutes.replace(" mins", "");
            int ETA = Integer.parseInt(minutes);
            if (ETA <= 30) {
                testStepAssert.isElementDisplayed(homePage.Text_ETAvalue(), "Less than 30mins", "Less than 30mins", "More than 30mins");
            } else {
                fail(minutes + " Driver not present.",
                        minutes + " Driver not present.", true);
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I enter \"([^\"]*)\" on Bungii estimate screen$")
    public void i_enter_something_on_bungii_estimate_screen(String strArg1) throws Throwable {
        try {
            switch (strArg1) {
                case "Goa pickup and dropoff locations":
                    if (action.isElementPresent(homePage.Button_ClearPickUp(true)))
                        action.click(homePage.Button_ClearPickUp());
                    utility.selectAddress(homePage.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("current.location"));
                    Thread.sleep(2000);
                    if(action.isElementPresent(homePage.Button_ETASet(true)))
                        action.click(homePage.Button_ETASet());
                    utility.selectAddress(homePage.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("pickup.locationA"));
                    Thread.sleep(4000);
                    if(action.isElementPresent(homePage.Button_ETASet(true)))
                        action.click(homePage.Button_ETASet());
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "goa");
                    Thread.sleep(5000);
                    break;

                case "Atlanta pickup and Indiana dropoff location":
                    if (action.isElementPresent(homePage.Button_ClearPickUp(true)))
                        action.click(homePage.Button_ClearPickUp());
                    utility.selectAddress(homePage.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.atlantaB"));
                    if(action.isElementPresent(homePage.Button_ETASet(true)))
                        action.click(homePage.Button_ETASet());
                    Thread.sleep(2000);
                    action.click(homePage.Button_ETASet(true));
                    utility.selectAddress(homePage.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.atlantaB"));
                    if(action.isElementPresent(homePage.Button_ETASet(true)))
                        action.click(homePage.Button_ETASet());
                    cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "atlanta");
                    Thread.sleep(5000);
                    break;

                case "Non Geofence pickup location":
                    if (action.isElementPresent(homePage.Button_ClearPickUp(true)))
                        action.click(homePage.Button_ClearPickUp());
                    utility.selectAddress(homePage.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.nongeofence"));
                    if(action.isElementPresent(homePage.Button_ETASet(true)))
                        action.click(homePage.Button_ETASet());
                    Thread.sleep(5000);
                    break;

                case "Goa Geofence pickup location":
                    if (action.isElementPresent(homePage.Button_ClearPickUp(true)))
                        action.click(homePage.Button_ClearPickUp());
                    utility.selectAddress(homePage.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Goa"));
                    if(action.isElementPresent(homePage.Button_ETASet(true)))
                       action.click(homePage.Button_ETASet());
                    Thread.sleep(2000);
                    break;
                case "Goa pickup location":
                    if (action.isElementPresent(homePage.Button_ClearPickUp(true)))
                        action.click(homePage.Button_ClearPickUp());
                    utility.selectAddress(homePage.TextBox_PickUpTextBox(), PropertyUtility.getDataProperties("pickup.location.Goa"));
                    //if(action.isElementPresent(homePage.Button_ETASet(true)))
                    //  action.click(homePage.Button_ETASet());
                    Thread.sleep(2000);
                    break;
                case "Goa Geofence dropoff location":
                    utility.selectAddress(homePage.TextBox_DropOffTextBox(), PropertyUtility.getDataProperties("dropoff.location.Goa"));
                    if(action.isElementPresent(homePage.Button_ETASet(true)))
                        action.click(homePage.Button_ETASet());
                    Thread.sleep(2000);
                    break;

            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I get the error popup message for \"([^\"]*)\"$")
    public void i_get_the_error_popup_message_for_something(String message) throws Throwable {
        try {
            switch (message) {
                case "More than 150 miles trip":
                    testStepAssert.isElementTextEquals(homePage.Text_ErrorMessage150Miles(), PropertyUtility.getMessage("Err_Trip150Miles"),
                            "Trip greater then 150 miles message should be displayed.", "Error message is displayed", "Error message is not displayed");
                    action.click(homePage.Button_ErrorMessage150Miles());
                    break;

                case "Non Geofence Location":
                    testStepAssert.isElementTextEquals(homePage.Text_ErrorNonGeofence(), PropertyUtility.getMessage("Err_TripNonGeofence"),
                            "Not operating in selected location should be displayed.", "Error message is displayed", "Error message is not displayed");
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    @Then("^I should see placeholder textbox$")
    public void i_should_see_blank_textbox() throws Throwable {
       String noText=action.getText(estimatePage.TextBox_DetailsNote());
       String placeholder ="Apt #, door codes, special instructions, etc.\n\nIf you need 2 people for your delivery, double check that you selected the 2 people icon at the bottom right of the home screen.";
       //String placeholder = "Apt #, door codes, special instruction, etc.\n\nIf you need 2 people for your delivery, double check that you selected the 2 people icon at the bottom right of the home screen.";
       if(noText.contains(placeholder)){
           testStepAssert.isTrue(true,"TextBox is showing placeholder ."+ noText,"TextBox doesnot have placeholder.");
       }
       else {
           testStepAssert.isFail("TextBox is does not contain placeholder. placeholder is " + noText);
       }
    }

    @When("^I enter \"([^\"]*)\" in Additional Notes field$")
    public void i_enter_something_in_additional_notes_field(String textValue) throws Throwable {
        try{
            switch (textValue){
                case "text":
                    action.sendKeys(estimatePage.TextBox_DetailsNote(),"text");
                    cucumberContextManager.setScenarioContext("NOTE_TEXT","text");
                    break;
                case "500 characters":
                    action.clearSendKeys(estimatePage.TextBox_DetailsNote(),PropertyUtility.getDataProperties("500.characters"));
                    break;
                case "1 more character":
                    action.clearSendKeys(estimatePage.TextBox_DetailsNote(),PropertyUtility.getDataProperties("500.characters")+"A");
                    break;
                case "special characters":
                    action.clearSendKeys(estimatePage.TextBox_DetailsNote(),PropertyUtility.getDataProperties("special.characters"));
                    break;
                default:
                    action.clearSendKeys(estimatePage.TextBox_DetailsNote(),textValue);
                    cucumberContextManager.setScenarioContext("NOTE_TEXT",textValue);
                    break;
            }

        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }

    }

    @Then("^the \"([^\"]*)\" should change$")
    public void the_something_should_change(String value) throws Throwable {
        try{
            switch (value){
                case "remaining characters":
                    String actualText=action.getText(estimatePage.TextBox_DetailsNote());
                    int charCount=actualText.length();
                    int totalCharCount=500;
                    int remainingChar=totalCharCount-charCount;
                    String text=action.getText(estimatePage.Text_CharactersRemaining());
                    if(text.contains(String.valueOf(remainingChar))){
                        testStepAssert.isTrue(true, "The remaining character count decreases.", "The remaining character count doesn't decrease.");
                    }
                    else{
                        testStepAssert.isFail("The remaining character count doesn't decrease.");
                    }
                    break;

                case "remaining characters value= 0":
                     actualText=action.getText(estimatePage.TextBox_DetailsNote());
                     charCount=actualText.length();
                     totalCharCount=500;
                     remainingChar=totalCharCount-charCount;
                     text=action.getText(estimatePage.Text_CharactersRemaining());
                    if(remainingChar == 0){
                        testStepAssert.isTrue(true, "The remaining character count is 0.", "The remaining character count is not 0. Remaining characters are "+ remainingChar);
                    }
                    else{
                        testStepAssert.isFail("The remaining character count is not 0. Remaining characters are "+ remainingChar);
                    }
                    break;
            }
        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error performing step,Please check logs for more details", true);
        }
    }


    @And("^I should see \"([^\"]*)\" message displayed$")
    public void i_should_see_something_message_displayed(String strArg1) throws Throwable {

        try {
            String expected ="";
            switch (strArg1) {
                case "Scheduled info":
                    String actualText = action.getText(homePage.Text_ScheduledBungiisInfo());
                    expected = "You don’t have any scheduled Bungiis at this time.";//.replace("�","'").replace("â??","'");
                    actualText = actualText.replace("\n", " ").replace("�","'");
                    testStepAssert.isEquals(actualText,expected, "The message should be displayed.", "The expected message is displayed.", "The expected message is not displayed. Actual is "+ actualText);
                    break;

                case "Past info":
                    if(action.isElementPresent(scheduledBungiisPage.Button_SaveMoney(true))){
                        testStepAssert.isTrue(true, "Save Money Button should be displayed.", "Save Money Button is displayed.", "Save Money Button is not displayed");
                    }
                        else{
                    actualText = action.getText(homePage.Text_PastBungiisInfo());
                    expected = "You don’t have any completed Bungiis at this time.";// PropertyUtility.getMessage("no.scheduled.bungiis").replace("�","'").replace("â??","'");
                    actualText = actualText.replace("\n", " ").replace("�", "'");
                    testStepAssert.isEquals(actualText, expected, "The message should be displayed.", "The expected message is displayed.", "The expected message is not displayed. Actual is " + actualText);
                }
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error in viewing "+ strArg1 , true);
        }
    }


    @Then("^I verify that \"([^\"]*)\" is displayed$")
    public void i_verify_that_something_is_displayed(String strArg1) {
        try {
            switch (strArg1) {
                case "Location picker":
                    testStepAssert.isElementDisplayed(homePage.LocationPicker(),"Location Picker must be displayed.", "The Location Picker is displayed.","The Location Picker is not displayed.");
                    break;

                case "SET PICKUP LOCATION BUTTON":
                    String actualBtnText=homePage.Button_ETASet(true).getText();
                    String expectedBtnText="SET PICKUP LOCATION";
                    testStepAssert.isEquals(actualBtnText, expectedBtnText,"SET PICKUP LOCATION should be displayed.","SET PICKUP LOCATION is displayed.","SET PICKUP LOCATION is not displayed.");
                    break;

                case "SET DROP OFF LOCATION BUTTON":
                    actualBtnText=homePage.Button_ETASet(true).getText();
                    expectedBtnText="SET DROP OFF LOCATION";
                    testStepAssert.isEquals(actualBtnText, expectedBtnText,"SET DROP OFF LOCATION should be displayed.","SET DROP OFF LOCATION is displayed.","SET DROP OFF LOCATION is not displayed.");
                    break;

                case "ETA bar":
                    testStepAssert.isElementDisplayed(homePage.TextBox_ETAContainer(),"ETA Container must be displayed.", "The ETA Container is displayed.","The ETA Container is not displayed.");
                    break;

                case "GET ESTIMATE":
                    testStepAssert.isElementDisplayed(homePage.Button_GetEstimate(),"Get Estimate button must be displayed.", "The ETA Container is displayed.","The ETA Container is not displayed.");
                    break;

                case "SET PICKUP TIME PAGE":
                    testStepAssert.isElementDisplayed(setPickupTimePage.Text_DriversBusyMessage(),"Driver Busy message should be shown","Driver Busy message is shown","Driver Busy message is not shown");
                    testStepAssert.isElementTextEquals(setPickupTimePage.Text_SetPickupTimeTitle(),"SET PICKUP TIME", "SET PICKUP TIME page title should be displayed.", "SET PICKUP TIME page title is displayed.","SET PICKUP TIME page title is not displayed.");
                    break;

                case "DRIVERS NOT AVAILABLE":
                    System.out.println(PropertyUtility.getMessage("drivers.busy.on.demand"));
                    System.out.println(setPickupTimePage.Text_DriversBusyMessage().getText());
                    if(setPickupTimePage.Text_DriversBusyMessage().getText().contains(PropertyUtility.getMessage("drivers.busy.on.demand"))) {
                        testStepAssert.isTrue(true, "The message is displayed.", "The expected message is displayed.", "The expected message is not displayed.");
                    }
                    break;

                case "Message Popup":
                    String actualMessage=setPickupTimePage.Icon_PickupTimeInfoMessage().getText();
                    String expectedMessage= PropertyUtility.getMessage("customer.info.cancel.ondemand.bungii");
                    testStepAssert.isEquals(actualMessage,expectedMessage,expectedMessage+" is displayed.",expectedMessage+" is displayed.",expectedMessage+" is not displayed.");
                    break;
                case "Pickup Message Popup":
                    String actual=setPickupTimePage.Icon_PickupTimeInfoMessage().getText();
                    String expected= PropertyUtility.getMessage("customer.info.time");
                    testStepAssert.isEquals(actual,expected,expected+" is displayed.",expected+" is displayed.",expected+" is not displayed.");
                    break;
                case "Four Reasons":
                    testStepAssert.isElementDisplayed(setPickupTimePage.Text_FirstCancellationReason(),"First cancellation reason should be displayed.","First cancellation reason is displayed.", "First cancellation reason is not displayed.");
                    testStepAssert.isElementDisplayed(setPickupTimePage.Text_SecondCancellationReason(),"Second cancellation reason should be displayed.","Second cancellation reason is displayed.", "Second cancellation reason is not displayed.");
                    testStepAssert.isElementDisplayed(setPickupTimePage.Text_ThirdCancellationReason(),"Third cancellation reason should be displayed.","Third cancellation reason is displayed.", "Third cancellation reason is not displayed.");
                    testStepAssert.isElementDisplayed(setPickupTimePage.Text_FourthCancellationReason(),"Fourth cancellation reason should be displayed.","Fourth cancellation reason is displayed.", "Fourth cancellation reason is not displayed.");
                    break;

                default:
                    throw new Exception(" UNIMPLEMENTED STEP ");
            }
            pass("I verify that "+strArg1 +" is displayed",strArg1 +" is displayed");
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful",
                    "Error in verifying "+strArg1+" is displayed", true);
        }
    }
}
