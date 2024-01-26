package com.bungii.android.stepdefinitions.Customer;

import com.bungii.android.manager.ActionManager;
import com.bungii.android.pages.customer.BungiiProgressPage;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import cucumber.api.java.en.Then;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.bungii.common.manager.ResultManager.*;

public class BungiiInProgressSteps extends DriverBase {
    private static LogUtility logger = new LogUtility(SignupSteps.class);
    BungiiProgressPage bungiiProgressPage = new BungiiProgressPage();
    ActionManager action = new ActionManager();

    @Then("^Trip Information should be correctly displayed on \"([^\"]*)\" status screen for customer$")
    public void trip_information_should_be_correctly_displayed_on_something_status_screen_for_customer(String key) {
        try {
            logger.detail("before driver name");
            String expectedDriverName = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
            expectedDriverName = expectedDriverName.substring(0, expectedDriverName.indexOf(" ") + 2);
            boolean isDriver1Displayed ;
            String actualDriver1Name="";

            if(String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_NO_DRIVER")).equalsIgnoreCase("DUO")){
                String driver1Name = (String) cucumberContextManager.getScenarioContext("DRIVER_1");
                String driver2Name=(String) cucumberContextManager.getScenarioContext("DRIVER_2");
                String acutalDriver1Name=action.getText(bungiiProgressPage.Text_DuoDriver1_Name());

                isDriver1Displayed=acutalDriver1Name.equals(driver1Name);
                String acutalDriver2Name=action.getText(bungiiProgressPage.Text_DuoDriver2_Name());
                boolean isDriver2NameCorrect=acutalDriver2Name.equals(driver2Name);
                testStepVerify.isTrue(isDriver2NameCorrect,
                        "Driver name '"+driver2Name+"' should correctly display",
                        "Driver name'"+driver2Name+"' was correctly display",
                        "Driver name '"+driver2Name+"' was not correctly display. Actual is"+acutalDriver2Name);
            }else{
                actualDriver1Name= getDriverName();
             isDriver1Displayed = actualDriver1Name.equals(expectedDriverName);}
            logger.detail("after driver name");

            switch (key) {
                case "EN ROUTE":
                     validateEnRouteInfo(getTripInformation(key));
                    break;
                case "ARRIVED":
                    validateArrivedInfo(getTripInformation(key), key);
                    break;
                case "LOADING ITEM":
                    validateArrivedInfo(getTripInformation(key), key);
                    break;
                case "DRIVING TO DROP OFF":
                    validateDrivingInfo(getTripInformation(key));
                    break;
                case "UNLOADING ITEM":
                    validateUnloadingInfo(getTripInformation(key));
                    break;
                default:
                    error("UnImplemented Step or incorrect button name", "UnImplemented Step");
                    break;
            }

            if (isDriver1Displayed) {
                pass(
                        "Trip Information should be correctly displayed and driver name :" + expectedDriverName
                                + "should be displayed",
                        "Trip Information is correctly displayed and driver name :" + expectedDriverName
                                + "is displayed correctly");
            } else {
                fail(
                        "Trip Information should be correctly displayed and driver name :" + expectedDriverName
                                + "should be displayed",
                        "Trip Information is not correctly displayed and actual driver name :" + actualDriver1Name+
                                 "Instead of"+expectedDriverName);

            }
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step  Should be successful", "Error performing step,Please check logs for more details", true);
        }
    }

    /**
     * Get trip information , Information while is below status icon , ex drop location etc
     *
     * @return Get trip information
     */
    public List<String> getTripInformation(String key) {
        List<String> details = new ArrayList<>();

        switch (key) {
            case "EN ROUTE":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                details.add(action.getText(bungiiProgressPage.Bungii_ETA()));
                break;
            case "ARRIVED":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                break;
            case "LOADING ITEM":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                break;
            case "DRIVING TO DROP OFF":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                details.add(action.getText(bungiiProgressPage.Bungii_ETA()));
                break;
            case "UNLOADING ITEM":
                details.add(action.getText(bungiiProgressPage.Bungii_LocationTitle()));
                details.add(action.getText(bungiiProgressPage.Bungii_Location()));
                break;
            default:
                break;
        }

        return details;
    }

    public String getDriverName(){
        return action.getText(bungiiProgressPage.Bungii_Driver_Name());
    }

    private boolean validateDrivingInfo(List<String> actualInfo) {
        logger.detail("customer trip info");

        boolean isTagDisplayed = actualInfo.get(0).equals("DROP OFF LOCATION"),
                isEtaDisplayed = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("mins");
        String actualDroplocation=actualInfo.get(1).replace(",","").replace("  "," ");

        //country is not displayed now
        String dropOffLocationLineOne=String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        boolean isDropDisplayed =actualDroplocation.contains(dropOffLocationLineOne) &&actualDroplocation.contains(dropOffLocationLineTwo) ;


        testStepVerify.isTrue(isTagDisplayed,

                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'DROP OFF LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isEtaDisplayed,
                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

            testStepVerify.isTrue(isDropDisplayed,

                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + "and expected is"
                            + dropOffLocationLineOne+dropOffLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualInfo.get(1)
                            + "and expected is" + dropOffLocationLineOne+dropOffLocationLineTwo);

        return isTagDisplayed && isEtaDisplayed && isDropDisplayed;
    }

    private boolean validateUnloadingInfo(List<String> actualInfo) {
        logger.detail("customer trip info");

        boolean isTagDisplayed = actualInfo.get(0).equals("DROP OFF LOCATION");
        String actualDroplocation=actualInfo.get(1).replace(",","").replace("  "," ");

        //country is not displayed now
        String dropOffLocationLineOne=String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_1")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        String dropOffLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_DROP_LOCATION_LINE_2")).replace(",","").replace("Rd","Road").replace(PropertyUtility.getDataProperties("bungii.country.name"),"").replace("  "," ").trim();
        boolean isDropDisplayed =actualDroplocation.contains(dropOffLocationLineOne) &&actualDroplocation.contains(dropOffLocationLineTwo) ;
            testStepVerify.isTrue(isTagDisplayed,

                    "'DROP OFF LOCATION' Tag should correctly displayed",
                    "'DROP OFF LOCATION' Tag is correctly displayed",
                    "'PDROP OFF LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isDropDisplayed,

                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + "and expected is"
                            +dropOffLocationLineOne+dropOffLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualInfo.get(1)
                            + "and expected is" + dropOffLocationLineOne+dropOffLocationLineTwo);

        return isTagDisplayed && isDropDisplayed;
    }

    private boolean validateEnRouteInfo(List<String> actualInfo) {
        logger.detail("customer trip info");

        boolean isTagDisplayed = actualInfo.get(0).equals("PICKUP LOCATION"),
                isEtaCorrect = actualInfo.get(2).contains("ETA:") && actualInfo.get(2).contains("mins");
        String actualPickuplocation=actualInfo.get(1).replace(",","").replace("  "," ");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",","").replace("  "," ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",","").replace("  "," ").trim();
        boolean isPickUpDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) &&actualPickuplocation.contains(pickUpLocationLineTwo);
            testStepVerify.isTrue(isTagDisplayed,

                    "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
                    "'PICKUP LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isEtaCorrect,

                    "ETA should be correctly displayed",
                    "'ETA' Tag and minutes was correctly displayed , Actual ETA is " + actualInfo.get(2),
                    "'ETA' Tag and minutes was not displayed  correctly, Actual ETA is " + actualInfo.get(2));

            testStepVerify.isTrue(isPickUpDisplayed,

                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + "and expected is"
                            + pickUpLocationLineOne+pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualInfo.get(1)
                            + "and expected is" + pickUpLocationLineOne+pickUpLocationLineTwo);


        return isTagDisplayed && isEtaCorrect && isPickUpDisplayed;
    }

    private boolean validateArrivedInfo(List<String> actualInfo, String screen) {
        logger.detail("customer trip info");

        boolean isTagDisplayed = actualInfo.get(0).equals("PICKUP LOCATION");
        String actualPickuplocation=actualInfo.get(1).replace(",","").replace("  "," ");
        String pickUpLocationLineOne = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_1")).replace(",","").replace("  "," ").trim();
        String pickUpLocationLineTwo = String.valueOf(cucumberContextManager.getScenarioContext("BUNGII_PICK_LOCATION_LINE_2")).replace(",","").replace("  "," ").trim();
        boolean isPickUpDisplayed = actualPickuplocation
                .contains(pickUpLocationLineOne) &&actualPickuplocation.contains(pickUpLocationLineTwo);

        testStepVerify.isTrue(isTagDisplayed,

                    "'PICKUP LOCATION' Tag should correctly displayed", "'PICKUP LOCATION' Tag is correctly displayed",
                    "'PICKUP LOCATION' Tag was not correctly displayed");

            testStepVerify.isTrue(isPickUpDisplayed,

                    "Pick up location should be correctly displayed ",
                    "Pick up location was correctly displayed , actual was is" + actualInfo.get(1) + "and expected is"
                            + pickUpLocationLineOne+pickUpLocationLineTwo,
                    "Pick up location was not displayed correctly, actual was is" + actualInfo.get(1)
                            + "and expected is" +pickUpLocationLineOne+pickUpLocationLineTwo);

        return isTagDisplayed && isPickUpDisplayed;
    }

}
