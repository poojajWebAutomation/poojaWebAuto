package com.bungii.android.stepdefinitions.Driver;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.driver.*;
import com.bungii.android.pages.driver.ScheduledBungiiPage;
import com.bungii.android.utilityfunctions.*;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.*;

public class ScheduledBungiiSteps extends DriverBase {
    ScheduledBungiiPage scheduledBungiiPage = new ScheduledBungiiPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    BungiiRequest Page_BungiiRequest = new BungiiRequest();
    InProgressBungiiPages inProgressBungiiPages = new InProgressBungiiPages();
    TripDetailsPage tripDetailsPage = new TripDetailsPage();
    private static LogUtility logger = new LogUtility(ScheduledBungiiSteps.class);
    @And("I open first Trip from driver scheduled trip")
    public void iSelectFirstTripFromDriverScheduledTrip() {
        try{
            boolean skipNormalFlow = false;
            boolean isSelected = false;
            if(action.isNotificationAlertDisplayed()){
                if(action.getText(Page_BungiiRequest.Alert_Msg(true)).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))){
                    utility.acceptNotificationAlert();
                    skipNormalFlow=true;
                }
                else{
                    action.click(Page_BungiiRequest.AlertButton_Cancel());
                }
                isSelected=true;
            }
            if(!skipNormalFlow) {
                String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
                List<WebElement> listOfScheduledTrip = scheduledBungiiPage.List_ScheduledBungiis();
                String timeZone=utility.getTimeZoneBasedOnGeofence();
                for (WebElement element : listOfScheduledTrip) {
                    WebElement schDate = element.findElement(By.id("com.bungii.driver:id/scheduled_row_textview_scheduleddatetime"));
                        WebElement rowViewIcom = element.findElement(By.id("com.bungii.driver:id/scheduled_row_textview_icon"));
                        action.click(new Point(rowViewIcom.getLocation().getX(), rowViewIcom.getLocation().getY()));
                        //action.click(rowViewIcom);
                        isSelected = true;
                        break;
                }
            }
            if(skipNormalFlow)
                testStepVerify.isTrue(isSelected,"I should able to Select Trip from driver scheduled trip","I selected trip using alert for upcoming trip to driver ","I was not able to start Bungii");
            else
                testStepVerify.isTrue(isSelected,"I should able to Select Trip from driver scheduled trip","I selected trip using list of Bungii's present in avialable bungii list","I was not able to start Bungii");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }
    @And("^I open second Trip from driver scheduled trip$")
    public void i_open_second_trip_from_driver_scheduled_trip() throws Throwable {
        try{
            action.click(scheduledBungiiPage.Cell_SecondTrip());
            pass("I select already scheduled bungii", "I select second scheduled bungii", true);

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("^I should see the notification for address change$")
    public void i_should_see_the_notification_for_address_change() throws Throwable {
      try{
          action.scrollToTop();
          action.isElementPresent(Page_BungiiRequest.Notification_AddressChanged());
          action.click(Page_BungiiRequest.Button_NotificationOk());
          log("I should be to see address change notification","I was able to see address change notification",false);
      }
      catch (Exception e) {
          logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
          error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
      }
    }
    @And("^I swipe to check trip details$")
    public void i_swipe_to_check_trip_details() throws Throwable {
      try{
          action.scrollToBottom();
          Thread.sleep(3000);

          log("I should be able to swipe to view delivery details","I am able to swipe to view delivery details",false);
      }
      catch (Exception e) {
          logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
          error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
      }
    }

    @Then("^I check if \"([^\"]*)\" is updated for live trip$")
    public void i_check_if_something_is_updated_for_live_trip(String address) throws Throwable {
       try{
           switch (address){
               case "dropoff address":
                   action.scrollToBottom();
                   String changedDropOff= (String) cucumberContextManager.getScenarioContext("Change_Drop_Off");
                   String actualDropOff=Page_BungiiRequest.Text_DropOffAddress().getText();
                   testStepAssert.isEquals(actualDropOff,changedDropOff, "The drop off address should be updated", "The drop off address is updated", "The drop off address is not updated");
                   break;

               case "pickup address":
                   action.scrollToTop();
                   String changedPickup = (String) cucumberContextManager.getScenarioContext("Change_Pickup");
                   String actualPickUp = Page_BungiiRequest.Text_PickUpAddress().getText();
                   testStepAssert.isEquals(actualPickUp,changedPickup, "The pick up address should be updated", "The pick up address is updated", "The pick up address is not updated");
                   break;

           }
       }
       catch (Exception e) {
           logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
           error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
       }
    }

    @And("^I check if \"([^\"]*)\" is updated$")
    public void i_check_if_something_is_updated(String addressType) throws Throwable {
        try{
            switch (addressType){
                case "pickup address":
                    action.scrollToTop();
                    String changedPickup[] = cucumberContextManager.getScenarioContext("Change_Pickup").toString().split(",");
                    String actualPickUpLineOne=action.getText(Page_BungiiRequest.Text_PickupLocation_LineOne1());;
                    testStepAssert.isEquals(actualPickUpLineOne,changedPickup[0], "The pick up address should be updated", "The pick up address is updated", "The pick up address is not updated");
                    break;
                case "dropoff address":
                    action.scrollToTop();
                    String changedDropOff[]=cucumberContextManager.getScenarioContext("Change_Drop_Off").toString().split(",");
                    String actualDropOffLineOne=action.getText(Page_BungiiRequest.Text_DropOffLocation_LineOne1());;
                    testStepAssert.isEquals(actualDropOffLineOne,changedDropOff[0], "The drop off address should be updated", "The drop off address is updated", "The drop off address is not updated");
                    break;
            }
        }
        catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @And("I Select Trip from driver scheduled trip")
    public void iSelectTripFromDriverScheduledTrip() {
        try{
            if(action.isElementPresent(Page_BungiiRequest.Alert_NewBungiiRequest(true))){
                action.click(Page_BungiiRequest.Button_No_Thanks());
            }
        boolean skipNormalFlow = false;
        boolean isSelected = false;
        if(action.isNotificationAlertDisplayed()){
            if(action.getText(Page_BungiiRequest.Alert_Msg(true)).equalsIgnoreCase(PropertyUtility.getMessage("driver.alert.upcoming.scheduled.trip"))){
                utility.acceptNotificationAlert();
                skipNormalFlow=true;
            }
            else{
                action.click(Page_BungiiRequest.AlertButton_Cancel());
            }
            isSelected=true;
        }
        if(!skipNormalFlow) {
            String tripTime = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_TIME"));
            List<WebElement> listOfScheduledTrip = scheduledBungiiPage.List_ScheduledBungiis();
            String timeZone=utility.getTimeZoneBasedOnGeofence();
            for (WebElement element : listOfScheduledTrip) {
                WebElement schDate = element.findElement(By.id("com.bungii.driver:id/scheduled_row_textview_scheduleddatetime"));
               // WebElement schTimeZone = element.findElement(By.id("com.bungii.driver:id/scheduled_row_textview_timezone_label"));
               /* if(!tripTime.contains(timeZone))
                    tripTime=tripTime+" "+timeZone;*/
                //if ((action.getText(schDate)+""+action.getText(schTimeZone)).equalsIgnoreCase(tripTime)) {
                if(TimeZone.getTimeZone("CST6CDT").inDaylightTime(new Date()))
                    tripTime = tripTime.replace("ST","DT");

              //  Thread.sleep(4000);
                //if ((action.getText(schDate)).equalsIgnoreCase(tripTime)) {
                    WebElement rowViewIcom = element.findElement(By.id("com.bungii.driver:id/scheduled_row_textview_icon"));
                    action.click(new Point(rowViewIcom.getLocation().getX(), rowViewIcom.getLocation().getY()));
                    //action.click(rowViewIcom);
                    isSelected = true;
                    break;
                //}
            }
        }
        if(skipNormalFlow)
            testStepAssert.isTrue(isSelected,"I should able to Select Trip from driver scheduled trip","I selected trip using alert for upcoming trip to driver ","I was not able to select Bungii");
        else
            testStepAssert.isTrue(isSelected,"I should able to Select Trip from driver scheduled trip","I selected trip using list of Bungii's present in avialable bungii list","I was not able to select Bungii");

    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }
    @And("^I start selected Bungii for \"([^\"]*)\"$")
    public void i_start_selected_bungii_for_something(String type) throws Throwable {
     try{
         switch (type){
             case "floor and decor 106":
             case "Tile Shop":
             case "Equip-bid":
             case "floor and decor":
                 Thread.sleep(3000);
                 action.scrollToBottom();
                 Thread.sleep(3000);
                 action.scrollToBottom();
                 action.click(Page_BungiiRequest.Button_Start());
                 log("I start selected Bungii ", "I started selected Bungii", true);
                 break;
         }
     }
     catch (Exception e) {
         logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
         error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
     }
    }

    @And("^I click the \"([^\"]*)\" button on \"([^\"]*)\" screen$")
    public void i_click_the_something_button_on_something_screen(String strArg1, String strArg2) throws Throwable {
        try {
            action.click(inProgressBungiiPages.Button_MoreOptions());
            action.click(inProgressBungiiPages.Button_Cancel());
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @When("^I wait for Minimum duration for Bungii Start Time$")
    public void i_wait_for_minimum_duration_for_bungii_start_time() {
        /*try {
            Date currentDate = new Date();

            String bungiiTime = (String) cucumberContextManager.getScenarioContext("BUNGII_TIME");
      //      bungiiTime =bungiiTime+" "+(currentDate.getYear()+1900);
            int mininumWaitTime = Integer.parseInt(PropertyUtility.getProp("scheduled.min.start.time"));
            if (!bungiiTime.equalsIgnoreCase("NOW")) {
                DateFormat formatter = new SimpleDateFormat("MMM d, h:mm a");
          //      DateFormat formatter = new SimpleDateFormat("MMM dd, hh:mm a yyyy");
             //   formatter.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()));
                formatter.setTimeZone(TimeZone.getTimeZone(utility.getTimeZoneBasedOnGeofenceId()));
                Date bungiiDate = formatter.parse(bungiiTime);


                bungiiDate.setYear(currentDate.getYear());//(Integer.parseInt(currentDate.getYear()));
                long duration = bungiiDate.getTime() - currentDate.getTime();

                long diffInMinutes;
                if (duration > 0) {
                    diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration) - mininumWaitTime;
                    diffInMinutes = diffInMinutes > 0 ? diffInMinutes : 0;
                } else {
                    diffInMinutes = 0;
                }
                action.hardWaitWithSwipeUp((int) diffInMinutes + 1);
                log("I wait for "+diffInMinutes+" Minutes for Bungii Start Time ", "I waited for "+diffInMinutes, true);
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
        */
        logger.detail("Temparory Commented since it is taking longer time.");
    }


    @Then("^I should see a popup \"([^\"]*)\" displayed$")
    public void i_should_see_a_popup_something_displayed(String popupNotificationText) throws Throwable {
        try{
            Thread.sleep(3000);
            boolean isDisplayed = action.isElementPresent(Page_BungiiRequest.Alert_NewBungiiRequest(true));
            testStepAssert.isTrue(isDisplayed,"Schedule bungii popup should be displayed","Schedule bungii popup is displayed","Schedule bungii popup is not displayed");
            String  popupText = action.getText(Page_BungiiRequest.Alert_NewBungiiRequest(true));
            testStepVerify.isEquals(popupText,popupNotificationText,"New bungii request text should displayed","New bungii request text is displayed","New bungii request text is not displayed");
            } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    @Then("^I should see the trip details$")
    public void i_should_see_the_trip_details() throws Throwable {
        try
        {
        String pickupLine1 = (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1");
        String pickupLine2 = (String) cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2");
        String dropOffLine1 = (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1");
        String dropOffLine2 = (String) cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2");
        String totalDistance = (String) cucumberContextManager.getScenarioContext("BUNGII_DISTANCE");

        String uiPickupLocationline1 =action.getText(tripDetailsPage.Text_Pickup_Location_line1());
        String uiPickupLocationline2 =action.getText(tripDetailsPage.Text_Pickup_Location_line2());
        String uiDropOffLocationline1 = action.getText(tripDetailsPage.Text_DropOff_Location_line1());
        String uiDropOffLocationline2 =action.getText(tripDetailsPage.Text_DropOff_Location_line2());
        String distance = action.getText(tripDetailsPage.Text_Total_Distance());

        testStepAssert.isEquals(uiPickupLocationline1,pickupLine1,"Pickup Location line 1 text should be " + pickupLine1,"Pickup Location line 1 text is " + uiPickupLocationline1,"Pickup Location line 1 text is not " + pickupLine1);
        testStepAssert.isEquals(uiPickupLocationline2,pickupLine2,"Pickup Location line 2 text should be " + pickupLine2,"Pickup Location line 2 text is " + uiPickupLocationline2,"Pickup Location line 2 text is not " + pickupLine2);
        testStepAssert.isEquals(uiDropOffLocationline1,dropOffLine1,"DropOff Location line 1 text should be " + dropOffLine1,"dropOff Location line 1 text is " + uiDropOffLocationline1,"dropOff Location line 1 text is not " + dropOffLine1);
        testStepAssert.isEquals(uiDropOffLocationline2,dropOffLine2,"DropOff Location line 2 text should be " + dropOffLine2,"dropOff Location line 2 text is " + uiDropOffLocationline2,"dropOff Location line 2 text is not " + dropOffLine2);
        testStepAssert.isEquals(distance,totalDistance,"total distance should be " + totalDistance,"total distance is " + distance,"total distance is not " + totalDistance);
    } catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
    }
    }

    @Then("^I should see service level information displayed for \"([^\"]*)\" address$")
    public void i_should_see_service_level_information_displayed_for_something_address(String centre) throws Throwable {
        try{
            Thread.sleep(5000);
            if(centre.equalsIgnoreCase("Store")) {
                String expectedStoreAddress = PropertyUtility.getDataProperties("baltimore.store.address");
                String addressLine1 = action.getText(tripDetailsPage.Text_Pickup_Location_line1());
                String addressLine2 = action.getText(tripDetailsPage.Text_Pickup_Location_line2()).replace(", ", " ");
                String properAddress = addressLine1 + " " + addressLine2;
                testStepAssert.isEquals(properAddress, expectedStoreAddress, "Proper Store address should be displayed", "Proper Store address is displayed", " Store address displayed is wrong");
            }
            else{
                String expectedWarehouseAddress = PropertyUtility.getDataProperties("baltimore.warehouse.address");
                String addressLine1 = action.getText(tripDetailsPage.Text_Pickup_Location_line1());
                String addressLine2 = action.getText(tripDetailsPage.Text_Pickup_Location_line2()).replace(", ", " ");
                String properAddress = addressLine1 + " " + addressLine2;
                testStepAssert.isEquals(properAddress, expectedWarehouseAddress, "Proper warehouse address should be displayed", "Proper warehouse address is displayed", " warehouse address displayed is wrong");
            }

            Thread.sleep(2000);
            String servicePickupInstruction = PropertyUtility.getDataProperties("baltimore.pickup.instructions");
            String serviceDropOffInstruction = PropertyUtility.getDataProperties("baltimore.dropoff.instructions");

            action.scrollToBottom();


            String expectedServicePickupInstructions = action.getText(tripDetailsPage.Text_PickupInstructions());
            String expectedServiceDropOffInstructions = action.getText(tripDetailsPage.Text_DropOffInstructions());

            testStepAssert.isTrue(action.isElementPresent(tripDetailsPage.Label_PickupInstructions()), "Pickup instruction should be displayed", "Pickup instruction is displayed", "Pickup instruction is not displayed");
            testStepAssert.isTrue(action.isElementPresent(tripDetailsPage.Label_DropOffInstructions()), "Dropoff instruction should be displayed", "Dropoff instruction is displayed", "Dropoff instruction is not displayed");

            testStepAssert.isEquals(expectedServicePickupInstructions, servicePickupInstruction, servicePickupInstruction + "service instructions should be displayed", expectedServicePickupInstructions + "service instructions is displayed", servicePickupInstruction + "service instructions is displayed");
            testStepAssert.isEquals(expectedServiceDropOffInstructions, serviceDropOffInstruction, serviceDropOffInstruction + "service instructions should be displayed", expectedServiceDropOffInstructions + "service instructions is displayed", serviceDropOffInstruction + "service instructions is displayed");
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^The delivery details on \"([^\"]*)\" deliveries should have proper pickup \"([^\"]*)\" location and service level instructions displayed$")
    public void the_delivery_details_on_something_deliveries_should_have_proper_pickup_something_location_and_service_level_instructions_displayed(String deliveryStatus, String centre) throws Throwable {
        try{
            if (centre.equalsIgnoreCase("Store")) {
                String expectedStoreAddress = PropertyUtility.getDataProperties("baltimore.store.address.with.zipcode");
                String StoreLocation = action.getText(tripDetailsPage.Text_PickupLocationAdminPortal());
                testStepAssert.isEquals(StoreLocation, expectedStoreAddress, "Store address should be " + expectedStoreAddress, "Store address is " + StoreLocation, "Store address is not " + expectedStoreAddress);
            } else {
                String expectedWarehouseAddress = PropertyUtility.getDataProperties("baltimore.warehouse.address.with.zipcode");
                String warehouseLocation = action.getText(tripDetailsPage.Text_PickupLocationAdminPortal());
                testStepAssert.isEquals(warehouseLocation, expectedWarehouseAddress, "Warehouse address should be " + expectedWarehouseAddress, "Warehouse address is " + warehouseLocation, "Warehouse address is not " + expectedWarehouseAddress);
            }
            switch (deliveryStatus) {
                case "Live":

                    String expectedPickupServiceProvided = PropertyUtility.getDataProperties("baltimore.pickup.instructions");
                    String expectedDropOffServiceProvided = PropertyUtility.getDataProperties("baltimore.dropoff.instructions");
                    String pickupServiceProvided = action.getText(tripDetailsPage.Text_DriverPickupInstructionsServiceAdminPortal());
                    String dropOffServiceProvided = action.getText(tripDetailsPage.Text_DriverDropOffInstructionsServiceAdminPortal());

                    testStepAssert.isTrue(action.isElementPresent(tripDetailsPage.Label_DriverPickupInstructionsAdminPortal()), "Driver pickup instructions label should be displayed", "Driver pickup instructions label is displayed", "Driver pickup instructions label is not displayed");
                    testStepAssert.isTrue(action.isElementPresent(tripDetailsPage.Label_DriverDropOffInstructionsAdminPortal()), "Driver dropoff instructions label should be displayed", "Driver dropoff instructions label is displayed", "Driver dropoff instructions label is not displayed");

                    testStepAssert.isEquals(pickupServiceProvided, expectedPickupServiceProvided, expectedPickupServiceProvided + "Service should be provided for pickup", pickupServiceProvided + "Service is provided for pickup", expectedPickupServiceProvided + "Service is not provided for pickup");
                    testStepAssert.isEquals(dropOffServiceProvided, expectedDropOffServiceProvided, expectedDropOffServiceProvided + "Service should be provided for dropoff", dropOffServiceProvided + "Service is provided for dropoff", expectedDropOffServiceProvided + "Service is not provided for dropoff");

                    break;
            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @And("^I set the pickup address for \"([^\"]*)\"$")
    public void i_set_the_pickup_address_for_something(String address) throws Throwable {
        try {
            switch (address) {
                case "Warehouse":
                    cucumberContextManager.setScenarioContext("WarehouseCity", "Catonsville");
                    break;
                case "Store":
                    cucumberContextManager.setScenarioContext("StoreCity", "MD");
                    break;
                case "Equip-bid in phoenix geofence":
                    cucumberContextManager.setScenarioContext("PhoenixEquip-bid", "phoenix");
                    break;
            }
            log("I should be able to set the pickup address", "I could set the pickup address", false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
    @Then("^The service level information should be displayed$")
    public void the_service_level_information_should_be_displayed() throws Throwable {
        try{
            Thread.sleep(6000);
            action.scrollToBottom();

            String expectedPickupServiceProvided = PropertyUtility.getDataProperties("baltimore.pickup.instructions");
            String expectedDropOffServiceProvided = PropertyUtility.getDataProperties("baltimore.dropoff.instructions");

            String pickupInstructions = action.getText(tripDetailsPage.Text_PickupInstructionsScheduleBungii());
            String dropOffInstructions = action.getText(tripDetailsPage.Text_DropOffInstructionsScheduleBungii());


            testStepAssert.isTrue(action.isElementPresent(tripDetailsPage.Label_PickupInstructionScheduleBungii()), "Driver pickup instructions label should be displayed", "Driver pickup instructions label is displayed", "Driver pickup instructions label is not displayed");
            testStepAssert.isTrue(action.isElementPresent(tripDetailsPage.Label_DropOffInstructionsScheduleBungii()), "Driver dropoff instructions label should be displayed", "Driver dropoff instructions label is displayed", "Driver dropoff instructions label is not displayed");

            testStepAssert.isEquals(pickupInstructions, expectedPickupServiceProvided, expectedPickupServiceProvided + "Service should be provided for pickup", pickupInstructions + "Service is provided for pickup", expectedPickupServiceProvided + "Service is not provided for pickup");
            testStepAssert.isEquals(dropOffInstructions, expectedDropOffServiceProvided, expectedDropOffServiceProvided + "Service should be provided for dropoff", dropOffInstructions + "Service is provided for dropoff", expectedDropOffServiceProvided + "Service is not provided for dropoff");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }
}
