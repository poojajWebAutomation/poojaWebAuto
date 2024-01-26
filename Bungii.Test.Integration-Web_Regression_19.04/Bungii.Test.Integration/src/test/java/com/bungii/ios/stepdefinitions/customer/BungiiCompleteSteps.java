package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.manager.*;
import com.bungii.ios.pages.customer.BungiiCompletePage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebElement;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;


public class BungiiCompleteSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(BungiiCompleteSteps.class);
    BungiiCompletePage bungiiCompletePage = new BungiiCompletePage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    DbUtility dbUtility = new DbUtility();


    @Then("^Bungii customer should see \"([^\"]*)\" on Bungii completed page$")
    public void bungii_customer_should_see_something_on_bungii_completed_page(String identifier) throws Throwable {
        try {
            verifyBungiiCompletedPage();

            switch (identifier) {
                case "correct details with promo":
                    action.swipeUP();

                    verifyTripValue();
                    verifyDiscount();
                    break;
                case "correct details with delivery promo":
                    verifyPromoterValue();
                    verifyPromoterDiscount();
                    break;
                case "correct details":
                    verifyTripValue();
                    break;
                case "correct details for duo trip":
                    action.swipeUP();
                    verifyTripValue();
                    break;
                case "correct rating detail for duo":
                    testStepVerify.isElementEnabled(bungiiCompletePage.Image_Profile1Placeholder(), " Driver 1 image placeholder should be displayed");
                    testStepVerify.isElementEnabled(bungiiCompletePage.Image_Profile2Placeholder(), " Driver 2 image placeholder should be displayed");
                    testStepVerify.isElementTextEquals(bungiiCompletePage.Text_GiveATip(), "Give a tip");
                    testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Driver2GiveATip(), "Give a tip");
                    testStepVerify.isElementTextEquals(bungiiCompletePage.Text_RateDriver(), "Rate Your Driver");
                    testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Driver2RateDriver(), "Rate Your Driver");
                    String driver1 = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
                    driver1 = driver1.substring(0, driver1.indexOf(" ") + 2);
                    String driver2 = (String) cucumberContextManager.getScenarioContext("DRIVER_2");
                    driver2 = driver2.substring(0, driver2.indexOf(" ") + 2);

                    testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Driver1Name(), driver1);
                    testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Driver2Name(), driver2);
                    break;
                case "correct rating detail for solo":
                    testStepVerify.isElementEnabled(bungiiCompletePage.Image_Profile1Placeholder(), " Driver 1 image placeholder should be displayed");

                    testStepVerify.isElementTextEquals(bungiiCompletePage.Text_TabStarToRate(), "Tap a star to rate your driver.");
                    testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Rate1Driver(), "Tap a star to rate your driver.");
                    String driver = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
                    driver = driver.substring(0, driver.indexOf(" ") + 2);
                    String actualDriverName=bungiiCompletePage.Text_DriversoloName().getText();
                    testStepVerify.isEquals(actualDriverName.replace("  "," "), driver);
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error in comparing values shown on Bungii completed page",
                    true);
        }
    }

    @When("^I select \"([^\"]*)\" Ratting star for duo Driver 1$")
    public void i_select_somethingrd_ratting_star_for_driver_1(String strArg1) throws Throwable {
        try {
            //List<WebElement> star= bungiiCompletePage.Button_GenericDriver1star();
            switch (strArg1) {
                case "3":
                    action.click(bungiiCompletePage.Button_DuoDriver13star());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I select \"([^\"]*)\" Ratting star for solo Driver 1$")
    public void i_select_somethingrd_ratting_star_for_solodriver_1(String strArg1) throws Throwable {
        try {
            //List<WebElement> star= bungiiCompletePage.Button_GenericDriver1star();
            switch (strArg1) {
                case "5":
                    action.click(bungiiCompletePage.Button_Solo5Star());
                    cucumberContextManager.setScenarioContext("RATING_VALUE","5");
                    break;
                case "1":
                    action.click(bungiiCompletePage.Button_Solo1Star());
                    cucumberContextManager.setScenarioContext("RATING_VALUE","1");
                    break;
                case "2":
                    action.click(bungiiCompletePage.Button_Solo2Star());
                    cucumberContextManager.setScenarioContext("RATING_VALUE","2");
                    break;
                case "3":
                    action.click(bungiiCompletePage.Button_Solo3Star());
                    cucumberContextManager.setScenarioContext("RATING_VALUE","3");
                    break;
                case "4":
                    action.click(bungiiCompletePage.Button_Solo4Star());
                    cucumberContextManager.setScenarioContext("RATING_VALUE","4");
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I check all the elements are displayed on driver rating page$")
    public void i_check_all_the_elements_are_displayed_on_driver_rating_page() throws Throwable {
         try
         {
             testStepAssert.isElementDisplayed(bungiiCompletePage.Header_RateTeamate(),
                        "The header Rate Duo teammate should be displayed",
                        "The header Rate Duo teammate is displayed",
                        "The header Rate Duo teammate is not displayed");

             testStepAssert.isElementDisplayed(bungiiCompletePage.Star_Rating(),
                     "Star ratings should be displayed",
                     "Star ratings are displayed",
                     "Star ratings is not displayed");

             testStepAssert.isElementDisplayed(bungiiCompletePage.Text_ChooseRating(),
                     "Choose Rating should be displayed",
                     "Choose Rating is displayed",
                     "Choose Rating is not displayed");

             testStepAssert.isElementDisplayed(bungiiCompletePage.Text_DriverExperience(),
                     "Driver experience should be displayed",
                     "Driver experience is displayed",
                     "Driver experience is not displayed");

             testStepAssert.isElementDisplayed(bungiiCompletePage.Button_Submit(),
                     "The submit button should be displayed",
                     "The submit button is displayed",
                     "The submit button is not displayed");

         }
         catch (Exception e) {
             logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
             error("Step  Should be successful", "Error performing step,Please check logs for more details",
                     true);
         }
    }
    @Then("^I check if the rating is saved in the db$")
    public void i_check_if_the_rating_is_saved_in_the_db() throws Throwable {
       try{
            String pickupRef = (String) cucumberContextManager.getScenarioContext("PICKUP_REQUEST");
            String driverRating = dbUtility.getDriverRatingFromDriver(pickupRef);
           if(!(driverRating.isEmpty()))
           {
               testStepAssert.isTrue(true,"The driver rating is saved in db","The driver rating is not saved in db");
           }
           else{
               testStepAssert.isTrue(false,"The driver rating should be saved in db","The driver rating is not saved in db");
           }
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details",
                   true);
       }
    }
    @And("^I add a comment for driver$")
    public void i_add_a_comment_for_driver() throws Throwable {
        try{
            action.swipeUP();
            testStepAssert.isElementDisplayed(bungiiCompletePage.Textbox_AdditionalFeedback(),
                    "The textbox to add additional feedback should be displayed",
                    "The textbox to add additional feedback is displayed",
                    "The textbox to add additional feedback is not displayed");

            action.sendKeys(bungiiCompletePage.Textbox_AdditionalFeedback(),"The driver was helpful");
            action.click(bungiiCompletePage.Text_Additional());

            log("I should be able to add a comment for duo driver teammate","I was able to add a comment for duo driver teammate",false);
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @When("^I select \"([^\"]*)\" Ratting star for duo Driver 2$")
    public void i_select_somethingrd_ratting_star_for_driver_2(String strArg1) throws Throwable {
        try {
            //List<WebElement> star= bungiiCompletePage.Button_GenericDriver1star();
            switch (strArg1) {
                case "4":
                    action.click(bungiiCompletePage.Button_DuoDriver2_4star());
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^\"([^\"]*)\" stars should be highlighted for Driver1$")
    public void something_stars_should_be_highlighted(String strArg1) throws Throwable {
        try {
            List<WebElement> star = bungiiCompletePage.Button_Driver1Filled();
            List<WebElement> unfilledStar = bungiiCompletePage.Button_Driver1Empty();
            switch (strArg1) {
                case "3":
                    testStepVerify.isTrue(star.size() == 3, strArg1 + " stars are displayed ");
                    testStepVerify.isTrue(unfilledStar.size() == (5 - Integer.parseInt(strArg1)), strArg1 + " stars are displayed ");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^\"([^\"]*)\" stars should be highlighted for solo Driver1$")
    public void something_stars_should_be_solohighlighted(String strArg1) throws Throwable {
        try {
            List<WebElement> star = bungiiCompletePage.Button_DriverSoloFilled();
            List<WebElement> unfilledStar = bungiiCompletePage.Button_DriverSoloUnFilled();
            switch (strArg1) {
                case "1":
                    testStepVerify.isTrue(star.size() == 1, strArg1 + " stars are displayed ");
                    testStepVerify.isTrue(unfilledStar.size() == (5 - Integer.parseInt(strArg1)), strArg1 + " stars are displayed ");
                    break;
                case "2":
                    testStepVerify.isTrue(star.size() == 2, strArg1 + " stars are displayed ");
                    testStepVerify.isTrue(unfilledStar.size() == (5 - Integer.parseInt(strArg1)), strArg1 + " stars are displayed ");
                    break;
                case "3":
                    testStepVerify.isTrue(star.size() == 3, strArg1 + " stars are displayed ");
                    testStepVerify.isTrue(unfilledStar.size() == (5 - Integer.parseInt(strArg1)), strArg1 + " stars are displayed ");
                    break;
                case "4":
                    testStepVerify.isTrue(star.size() == 4, strArg1 + " stars are displayed ");
                    testStepVerify.isTrue(unfilledStar.size() == (5 - Integer.parseInt(strArg1)), strArg1 + " stars are displayed ");
                    break;
                case "5":
                    testStepVerify.isTrue(star.size() == 5, strArg1 + " stars are displayed ");
                    testStepVerify.isTrue(unfilledStar.size() == (5 - Integer.parseInt(strArg1)), strArg1 + " stars are displayed ");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^\"([^\"]*)\" stars should be highlighted for Driver2$")
    public void something_starts_should_be_highlightedDriver2(String strArg1) throws Throwable {
        try {
            List<WebElement> star = bungiiCompletePage.Button_Driver2Filled();
            List<WebElement> unfilledStar = bungiiCompletePage.Button_Driver2Empty();
            switch (strArg1) {
                case "4":
                    testStepVerify.isTrue(star.size() == 4, strArg1 + " starts are displayed ");
                    testStepVerify.isTrue(unfilledStar.size() == (5 - Integer.parseInt(strArg1)), strArg1 + " starts are displayed ");
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    /**
     * Verify Static texts on Bungii Completed page
     */
    public void verifyBungiiCompletedPage() {
        action.swipeUP();
        testStepVerify.isElementEnabled(bungiiCompletePage.PageTitle_BungiiComplete(), "Bungii Complete Page should be displayed");
        //     testStepVerify.isElementEnabled(bungiiCompletePage.Title_RateYourDriver(),"'Rate Your driver'  should be displayed");
        String totalTime = action.getValueAttribute(bungiiCompletePage.Text_BungiiTime()), totalDistance = action.getValueAttribute(bungiiCompletePage.Text_Distance());
        int tripActualTime = Integer.parseInt(utility.getActualTime());
        String tripDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");
        tripDistance= tripDistance.replace(" miles","").trim();
        logger.detail("Calculated Distance : "+tripDistance);
        logger.detail("Actual Distance : "+totalDistance);
        Double value = Double.valueOf(tripDistance);
        tripDistance = new DecimalFormat("#.00").format(value);
        if (tripActualTime == 1)
            testStepVerify.isTrue(totalTime.contains(tripActualTime + "  min") || totalTime.contains(tripActualTime + " min"), "Total time should contains" + tripActualTime + " minute");
        else
            testStepVerify.isTrue(totalTime.contains(tripActualTime + "  mins") || totalTime.contains(tripActualTime + " mins"), "Total time should contains" + tripActualTime + " minute");
        testStepVerify.isTrue(totalDistance.contains(tripDistance), "Total distance should contain " + tripDistance);
        //Vishal[2503]:TODO: add more
    }

    /**
     * Verify variable texts in Bungii Complete Page
     */
    public void verifyTripValue() {
        action.swipeDown();
        double tripActualTime = Double.parseDouble(utility.getActualTime());

        String totalCost = "";

        //	String totalTime=action.getValueAttribute(bungiiCompletePage.Text_BungiiTime()).split(" ")[0],totalDistance=action.getValueAttribute(bungiiCompletePage.Text_Distance()).split(" ")[0],totalCost=action.getValueAttribute(bungiiCompletePage.Text_FinalCost()).split(" ")[0];
        String promoValue = String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE"));

        String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
        String totalDistance = utility.getEstimateDistance();


        Double expectedTotalCost = utility.bungiiCustomerCost(totalDistance, String.valueOf(tripActualTime), promoValue, numberOfDriver);
        String truncValue = new DecimalFormat("#.00").format(expectedTotalCost);
        //	if(!truncValue.contains("."))truncValue=truncValue+".00";

        if (numberOfDriver.equalsIgnoreCase("DUO"))
            totalCost = action.getValueAttribute(bungiiCompletePage.Text_FinalCost_Duo()).split(" ")[0];
        else
            totalCost = action.getValueAttribute(bungiiCompletePage.Text_FinalCost()).split(" ")[0];

        testStepVerify.isEquals(totalCost, "$" + String.valueOf(truncValue));
        cucumberContextManager.setScenarioContext("BUNGII_COST_CUSTOMER", totalCost);

    }

    public void verifyPromoterValue() {
        action.swipeDown();
        double tripActualTime = Double.parseDouble(utility.getActualTime());

        String totalCost = "";

        //	String totalTime=action.getValueAttribute(bungiiCompletePage.Text_BungiiTime()).split(" ")[0],totalDistance=action.getValueAttribute(bungiiCompletePage.Text_Distance()).split(" ")[0],totalCost=action.getValueAttribute(bungiiCompletePage.Text_FinalCost()).split(" ")[0];
        String promoValue = String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE"));

        String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));
        String totalDistance = utility.getEstimateDistance();


        Double expectedTotalCost = utility.bungiiCustomerCost(totalDistance, String.valueOf(tripActualTime), "ADD", numberOfDriver);
        String truncValue = new DecimalFormat("#.00").format(expectedTotalCost);


        if (numberOfDriver.equalsIgnoreCase("DUO"))
            totalCost = action.getValueAttribute(bungiiCompletePage.Text_FinalCost_Duo()).split(" ")[0];
        else
            totalCost = action.getValueAttribute(bungiiCompletePage.Text_FinalCost()).split(" ")[0];

        testStepVerify.isEquals(totalCost, "$0.00");
        cucumberContextManager.setScenarioContext("BUNGII_COST_CUSTOMER", expectedTotalCost + "");

    }

    public void verifyDiscount() {
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get minimum cost,Mile value,Minutes value of Geofence
        double minCost = Double.parseDouble(utility.getGeofenceData(currentGeofence, "geofence.minimum.cost")),
                perMileValue = Double.parseDouble(utility.getGeofenceData(currentGeofence, "geofence.dollar.per.miles")),
                perMinutesValue = Double.parseDouble(utility.getGeofenceData(currentGeofence, "geofence.dollar.per.minutes"));

        double tripActualTime = Double.parseDouble(utility.getActualTime());
        String totalTime = action.getValueAttribute(bungiiCompletePage.Text_BungiiTime()).split(" ")[0], totalDistance = action.getValueAttribute(bungiiCompletePage.Text_Distance()).split(" ")[0];
        String Promo = String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE"));
        String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

        Double promoValue = 0.0;
        String distanceValueDB = utility.getEstimateDistance();

        double distance = Double.parseDouble(distanceValueDB);// Double.parseDouble(totalDistance.replace(" miles", ""));

        //double tripActualTime = Double.parseDouble(totalTime);
        double tripValue = distance * perMileValue + tripActualTime * perMinutesValue;
        if (numberOfDriver.equalsIgnoreCase("DUO"))
            tripValue = tripValue * 2;

        if (Promo.contains("$"))
            promoValue = Double.valueOf(Promo.replace("-$", ""));
        else if (Promo.contains("%"))
            promoValue = Double.valueOf(tripValue * Double.parseDouble(Promo.replace("-", "").replace("%", "")) / 100);
        //if final cost with promo is less than 39, then discount is reduced
        if ((tripValue - promoValue) < minCost)
            promoValue = tripValue - minCost;

        String promoDiscountValue="";
        if (numberOfDriver.equalsIgnoreCase("DUO")) {
            DecimalFormat df = new DecimalFormat("#.00");
            df.setRoundingMode(RoundingMode.FLOOR);
            promoDiscountValue = df.format(promoValue);
        }else{
            promoDiscountValue = new DecimalFormat("#.00").format(promoValue);

        }
        if (promoDiscountValue.indexOf(".") == 0) promoDiscountValue = "0" + promoDiscountValue;

        cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", promoDiscountValue);
        action.swipeUP();
        if (numberOfDriver.equalsIgnoreCase("DUO"))
            testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Discount_Duo(), "$" + promoDiscountValue, "Discount value should be promo Value" + promoDiscountValue, "Discount value is " + promoDiscountValue, "Discount value is not " + promoDiscountValue);
        else
            testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Discount(), "$" + promoDiscountValue, "Discount value should be promo Value" + promoDiscountValue, "Discount value is " + promoDiscountValue, "Discount value is not " + promoDiscountValue);
    }

    public void verifyPromoterDiscount() {
        //get current geofence
        String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");
        //get minimum cost,Mile value,Minutes value of Geofence
        double minCost = Double.parseDouble(utility.getGeofenceData(currentGeofence, "geofence.minimum.cost")),
                perMileValue = Double.parseDouble(utility.getGeofenceData(currentGeofence, "geofence.dollar.per.miles")),
                perMinutesValue = Double.parseDouble(utility.getGeofenceData(currentGeofence, "geofence.dollar.per.minutes"));

        double tripActualTime = Double.parseDouble(utility.getActualTime());
        String totalTime = action.getValueAttribute(bungiiCompletePage.Text_BungiiTime()).split(" ")[0], totalDistance = action.getValueAttribute(bungiiCompletePage.Text_Distance()).split(" ")[0];
        String Promo = String.valueOf(cucumberContextManager.getScenarioContext("PROMOCODE_VALUE"));
        String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

        Double promoValue = 0.0;
        String distanceValueDB = utility.getEstimateDistance();

        double distance = Double.parseDouble(distanceValueDB);// Double.parseDouble(totalDistance.replace(" miles", ""));

        //double tripActualTime = Double.parseDouble(totalTime);
        double tripValue = distance * perMileValue + tripActualTime * perMinutesValue;
        if (numberOfDriver.equalsIgnoreCase("DUO"))
            tripValue = tripValue * 2;

        if (Promo.contains("$"))
            promoValue = Double.valueOf(Promo.replace("-$", ""));
        else if (Promo.contains("%"))
            promoValue = Double.valueOf(tripValue * Double.parseDouble(Promo.replace("-", "").replace("%", "")) / 100);


        String promoDiscountValue = new DecimalFormat("#.00").format(promoValue);

        if (promoDiscountValue.indexOf(".") == 0) promoDiscountValue = "0" + promoDiscountValue;

        cucumberContextManager.setScenarioContext("DISCOUNT_VALUE", promoDiscountValue);

        testStepVerify.isElementTextEquals(bungiiCompletePage.Text_Discount(), "$" + promoDiscountValue, "Discount value should be promo Value" + promoDiscountValue, "Discount value is " + promoDiscountValue, "Discount value is not " + promoDiscountValue);
    }

    //TODO: Handle Duo
    @When("^I rate Bungii Driver  with following details and Press \"([^\"]*)\" Button$")
    public void iRateBungiiDriverWithFollowingDetailsAndPressButton(String button, DataTable tipInformation) {
        try {
            String numberOfDriver = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER"));

            //Input from user
            Map<String, String> data = tipInformation.transpose().asMap(String.class, String.class);
            String ratting = data.get("Ratting"), tip = data.get("Tip");
            //give tip and fetch actual tip
            giveTip(Integer.parseInt(tip));
            String actualTip = bungiiCompletePage.Text_TipValue().getAttribute("value").replace("$", "");

            giveRatting(Integer.parseInt(ratting));

            switch (button.toUpperCase()) {
                case "OK":
                    action.click(bungiiCompletePage.Button_Ok());
                    break;
                case "CLOSE":
                    action.click(bungiiCompletePage.Button_Close());
                    ;
                    break;
                default:
                    throw new Exception(" UNIMPLEMENTED STEP");
            }
            if (!numberOfDriver.toUpperCase().equals("DUO"))
                testStepVerify.isTrue((int) Double.parseDouble(actualTip) == Integer.parseInt(tip), "driver should be given tip for " + tip, "Bungii driver is given tip for" + actualTip,
                        "Bungii driver is given tip for" + actualTip);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }


    /**
     * Give Tip to driver
     *
     * @param tipAmmount tip ammount
     */
    public void giveTip(int tipAmmount) {
        for (int i = 0; i < tipAmmount; i++) {
            action.click(bungiiCompletePage.Button_Plus());
        }
    }


    /**
     * Give rating stars to driver
     *
     * @param starCount Number of stars
     */
    public void giveRatting(int starCount) {
        List<WebElement> Button_StarElement = bungiiCompletePage.Button_Star();
        if (starCount <= Button_StarElement.size()) {
            action.click(Button_StarElement.get(starCount - 1));
        }

    }
}
