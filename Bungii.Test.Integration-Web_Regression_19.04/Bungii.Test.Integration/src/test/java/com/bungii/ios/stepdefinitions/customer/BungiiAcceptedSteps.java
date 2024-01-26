package com.bungii.ios.stepdefinitions.customer;

import com.bungii.api.utilityFunctions.GoogleMaps;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.BungiiAcceptedPage;
import com.bungii.ios.utilityfunctions.DbUtility;
import com.bungii.ios.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class BungiiAcceptedSteps extends DriverBase {
    BungiiAcceptedPage bungiiAcceptedPage;
    ActionManager action = new ActionManager();
    GeneralUtility utility= new GeneralUtility();
    private static LogUtility logger = new LogUtility(BungiiAcceptedSteps.class);

    public  BungiiAcceptedSteps(BungiiAcceptedPage bungiiAcceptedPage){
        this.bungiiAcceptedPage=bungiiAcceptedPage;
    }


    @Then("^correct details should do be displayed on (.+) screen for Stack screen$")
    public void correct_details_should_do_be_displayed_on_bungii_accepted_screen_for_stack_screen(String key)  {
        try{
        switch (key.trim()){
            case"BUNGII ACCEPTED":
                String driverName=(String)cucumberContextManager.getScenarioContext("DRIVER_1");
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Label_DriverName(),driverName.substring(0, driverName.indexOf(" ") + 2));
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Text_StackInfo(), PropertyUtility.getMessage("customer.stack.accepted.info"));
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Text_BungiiAccepted(), PropertyUtility.getMessage("customer.bungii.accepted"));
                testStepVerify.isElementDisplayed(bungiiAcceptedPage.Image_RattingBar(),"Ratting bar should be displayed","Ratting bar is displayed","Ratting bar is not displayed");
                break;
            case"BUNGII ACCEPTED with arrival time":
                String driver1Name=(String)cucumberContextManager.getScenarioContext("DRIVER_1");
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Textlabel_DriverNearby(),PropertyUtility.getMessage("customer.stack.driver.neighborhood").replace("<DRIVER_NAME>",driver1Name.substring(0, driver1Name.indexOf(" ") + 2)));
                testStepVerify.isElementTextEquals(bungiiAcceptedPage.Textlabel_StackSubtitle(),PropertyUtility.getMessage("customer.stack.driver.subtitle.ios"));
                testStepVerify.isElementEnabled(bungiiAcceptedPage.Textlabel_ProjectedTime(),"Projected driver arrival time label should be displayed");

                String label="";
                String currentGeofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

                if (currentGeofence.equalsIgnoreCase("goa") || currentGeofence.equalsIgnoreCase(""))
                    label =  PropertyUtility.getDataProperties("time.label");
                else
                    label =  utility.getTimeZoneBasedOnGeofence();

                testStepVerify.isElementEnabled(bungiiAcceptedPage.Button_CancelBungii(),"Cancel Bungii should be displayed");
                String expectedArrivalValue=(String)cucumberContextManager.getScenarioContext("DRIVER_MIN_ARRIVAL")+" - "+(String)cucumberContextManager.getScenarioContext("DRIVER_MAX_ARRIVAL")+" "+label;
                expectedArrivalValue=expectedArrivalValue.replace("am", "AM").replace("pm","PM");
                String actual = action.getText(bungiiAcceptedPage.Textlabel_ProjectedTimeValue());
                testStepVerify.isTrue(expectedArrivalValue.contains(actual),"Projected arrival Time : "+ actual +" is displayed","Projected arrival Time : "+ actual +" is displayed instead of "+ expectedArrivalValue);
                break;
            case "BUNGII ACCEPTED with recalculation":
                String labelOne="";
                String geofence = (String) cucumberContextManager.getScenarioContext("BUNGII_GEOFENCE");

                if (geofence.equalsIgnoreCase("goa") || geofence.equalsIgnoreCase(""))
                    labelOne =  PropertyUtility.getDataProperties("time.label");
                else
                    labelOne =  utility.getTimeZoneBasedOnGeofence();
                testStepVerify.isElementEnabled(bungiiAcceptedPage.Button_CancelBungii(),"Cancel Bungii should be displayed");
                String expectedPAT=(String)cucumberContextManager.getScenarioContext("PAT_LOWER_RANGE")+" - "+(String)cucumberContextManager.getScenarioContext("PAT_UPPER_RANGE")+" "+labelOne;
                expectedPAT=expectedPAT.replace("am", "AM").replace("pm","PM");
                String actualPAT = action.getText(bungiiAcceptedPage.Textlabel_ProjectedTimeValue());
                testStepVerify.isTrue(expectedPAT.contains(actualPAT),"Projected arrival Time : "+ actualPAT +" is displayed","Projected arrival Time : "+ actualPAT +" is displayed instead of "+ expectedPAT);
                break;
        }}
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^ratting should be correctly displayed on Bungii accepted page$")
    public void ratting_should_be_correctly_displayed_on_bungii_accepteed_page() throws Throwable {
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

            bungiiAcceptedPage.WaitUntilElementIsDisplayed(By.xpath("//XCUIElementTypeButton[@name=\"rating filled star icon\"])"));

            int filledStarCount = bungiiAcceptedPage.FilledStars().size();
            int HalfFilledStarCount = bungiiAcceptedPage.HalfFilledStar().size();

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
    @And("^I calculate the \"([^\"]*)\" time after \"([^\"]*)\"$")
    public void i_calculate_the_something_time_after_something(String timeType, String changeType) throws Throwable {
        try{
            switch (changeType){
                case "changed pickup":
                    switch (timeType){
                        case "telet":
//                      TELET = Pickup address Edited time + ((Estimated Duration from Pickup point to drop off point + loading/unloading time) * 1.5)+30
                            String phoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"); //phoneNumber="9403960189"; c/// Stacked trip will be 2 customer you need of first trip
                            String custRef = DbUtility.getCustomerRefference(phoneNumber);
                            String pickUpID = DbUtility.getPickupID(custRef);
                            String geofenceLabel = utility.getTimeZoneBasedOnGeofenceId();
                            String adminEditTime= DbUtility.getAdminEditTime(pickUpID);
                            String teletTimeInDb = DbUtility.getTELETfromDb(custRef);
                            String[] pickup1Locations = DbUtility.getPickupAndDropLocation(phoneNumber);
                            int  loadUnloadTime = Math.round(Float.valueOf(DbUtility.getLoadUnloadTime(pickUpID)));


                            String[] dropLocation = new String[2];
                            dropLocation[0] = pickup1Locations[2];
                            dropLocation[1] = pickup1Locations[3];
                            String[] pickupLocations = new String[2];
                            pickupLocations[0] = pickup1Locations[0];
                            pickupLocations[1] = pickup1Locations[1];

                            long[] timeToCoverDistance = new GoogleMaps().getDurationInTraffic(pickupLocations, dropLocation);
                            logger.detail("timeToCoverDistance [google api call] "+timeToCoverDistance[0]+" and "+timeToCoverDistance[1]);

                            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            //By default data is in UTC
                            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                            Date adminEditTimeInUtc = formatter.parse(adminEditTime);
                            formatter.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
                            String adminEditTimeInLocalTime = formatter.format(adminEditTimeInUtc);

                            int minutes = (int) ((((timeToCoverDistance[0]/60) + loadUnloadTime)*1.5)+30);
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(adminEditTimeInUtc);
                            calendar.add(Calendar.MINUTE, minutes);

                            DateFormat formatterForLocalTimezone = new SimpleDateFormat("hh:mm a");
                            formatterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabel));
                            String teletInLocalTime = formatterForLocalTimezone.format(calendar.getTime());
                            cucumberContextManager.setScenarioContext("NEW_TELET",teletInLocalTime);
                            cucumberContextManager.setScenarioContext("PAT_LOWER_RANGE",teletInLocalTime);

                            calendar.add(Calendar.MINUTE, 45);
                            String upperRangeInLocalTime = formatterForLocalTimezone.format(calendar.getTime());
                            cucumberContextManager.setScenarioContext("PAT_UPPER_RANGE",upperRangeInLocalTime);
                            break;

                        case "telet-short stack":
//                             Formula = (TELET + drive time from drop off A to pickup B) - 10 minutes, + 20 minutes
                            cucumberContextManager.setScenarioContext("BUNGII_GEOFENCE", "goa");
                            String phoneNum = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE"); //phoneNumber="9403960189"; c/// Stacked trip will be 2 customer you need of first trip
                            String custReference = DbUtility.getCustomerRefference(phoneNum);
                            String pickUpId = DbUtility.getPickupID(custReference);
                            String[] pickup1Location = DbUtility.getPickupAndDropLocation(phoneNum);
                            int  loadunloadTime = Math.round(Float.valueOf(DbUtility.getLoadUnloadTime(pickUpId)));

                            String driverArrivalTime= DbUtility.getDriverArrivalTime(pickUpId);
                            DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            //By default data is in UTC
                            formatter2.setTimeZone(TimeZone.getTimeZone("UTC"));
                            Date driverArrivalTimeInUTC = formatter2.parse(driverArrivalTime);

                            DateFormat formatter1 = new SimpleDateFormat("HH:mm:ss");
                            Calendar calendar1 = Calendar.getInstance();
                            calendar1.setTime(driverArrivalTimeInUTC);

                            String[] dropLocations = new String[2];
                            dropLocations[0] = pickup1Location[2];
                            dropLocations[1] = pickup1Location[3];
                            String[] pickupLocation = new String[2];
                            pickupLocation[0] = pickup1Location[0];
                            pickupLocation[1] = pickup1Location[1];

                            long[] timeToCoverDistance1 = new GoogleMaps().getDurationInTraffic(pickupLocation, dropLocations);
                            logger.detail("timeToCoverDistance [google api call] "+timeToCoverDistance1[0]+" and "+timeToCoverDistance1[1]);

                            int min = (int) (((timeToCoverDistance1[0]/60) + loadunloadTime)*1.5);
                            calendar1.add(Calendar.MINUTE, min);
                            String telet = formatter1.format(calendar1.getTime());
                            cucumberContextManager.setScenarioContext("TELET_RECALCULATED",telet);

                            String customerPhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER_PHONE");//customerPhoneNumber="9999991889";
                            String customer2PhoneNumber = (String) cucumberContextManager.getScenarioContext("CUSTOMER2_PHONE");//customer2PhoneNumber="9999991259";

                            String[] pickupLoc= DbUtility.getPickupAndDropLocation(customerPhoneNumber);
                            String[] pickup2Locations = DbUtility.getPickupAndDropLocation(customer2PhoneNumber);

                            String[] dropLoc = new String[2];
                            dropLocations[0] = pickupLoc[2];
                            dropLocations[1] = pickupLoc[3];
                            String[] newPickupLocations = new String[2];
                            newPickupLocations[0] = pickup2Locations[0];
                            newPickupLocations[1] = pickup2Locations[1];

                            long[] timeToCoverDistance2 = new GoogleMaps().getDurationInTraffic(dropLoc, newPickupLocations);
                            logger.detail("timeToCoverDistance [google api call] "+timeToCoverDistance2[0]+" and "+timeToCoverDistance2[1]);
                            int time = (int) (timeToCoverDistance2[0]/60);

                            calendar1.add(Calendar.MINUTE,time);
                            calendar1.add(Calendar.MINUTE,-10);
                            DateFormat formaterForLocalTimezone = new SimpleDateFormat("hh:mm a");
                            String geofenceLabe = utility.getTimeZoneBasedOnGeofenceId();
                            formaterForLocalTimezone.setTimeZone(TimeZone.getTimeZone(geofenceLabe));
                            String lowerRange = formaterForLocalTimezone.format(calendar1.getTime());
                            cucumberContextManager.setScenarioContext("PAT_LOWER_RANGE",lowerRange);
                            calendar1.add(Calendar.MINUTE,30);
                            String upperRange = formaterForLocalTimezone.format(calendar1.getTime());
                            cucumberContextManager.setScenarioContext("PAT_UPPER_RANGE",upperRange);
                            break;

                    }
                    break;
            }
            log("I should be able to calculate the telet","I am able to calculate the telet",false);

        }
        catch (Throwable e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}


