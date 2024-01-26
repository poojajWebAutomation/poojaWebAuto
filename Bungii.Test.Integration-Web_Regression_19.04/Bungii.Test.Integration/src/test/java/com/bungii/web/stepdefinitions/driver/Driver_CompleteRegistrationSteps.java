package com.bungii.web.stepdefinitions.driver;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.driver.Driver_BankDetailsPage;
import com.bungii.web.pages.driver.Driver_DetailsPage;
import com.bungii.web.pages.driver.Driver_DocumentationPage;
import com.bungii.web.pages.driver.Driver_PickUpInfoPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.Keys;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Driver_CompleteRegistrationSteps extends DriverBase {
    Driver_PickUpInfoPage Page_Driver_PickupInfo = new Driver_PickUpInfoPage();
    Driver_DetailsPage Page_Driver_Details= new Driver_DetailsPage();
    Driver_DocumentationPage Page_Driver_Doc = new Driver_DocumentationPage();
    Driver_BankDetailsPage Page_Driver_Bank = new Driver_BankDetailsPage();
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();
    private static LogUtility logger = new LogUtility(Driver_CompleteRegistrationSteps.class);

    @When("^I enter \"([^\"]*)\" data on Pickup Information page$")
    public void i_enter_something_data_on_pickup_information_page(String strArg1) throws Throwable {
        try{
        switch (strArg1) {
            case "valid":
                action.clearSendKeys(Page_Driver_PickupInfo.TextBox_PickupMake(), PropertyUtility.getDataProperties("PickupMake"));
                action.clearSendKeys(Page_Driver_PickupInfo.TextBox_PickupModel(), PropertyUtility.getDataProperties("PickupModel"));
                action.selectRandomDropdown(Page_Driver_PickupInfo.DropDown_PickupYear());
                action.clearSendKeys(Page_Driver_PickupInfo.TextBox_LicenseNo(), PropertyUtility.getDataProperties("PickupLicenseNo"));
                utility.addImageInDropZone(Page_Driver_PickupInfo.DropZoneHiddenFileTag_TruckImage(), getTruckImages());
                action.invisibilityOfElementLocated(Page_Driver_PickupInfo.Wrapper_Spinner());
                int size =Page_Driver_PickupInfo.Div_UploadedImages().size();
                int count = 0;
                while (size !=3) {
                Thread.sleep(2000);
                if (count >=20 )
                    break;

                size =Page_Driver_PickupInfo.Div_UploadedImages().size();
                count++;
                }

                testStepVerify.isElementDisplayed(Page_Driver_PickupInfo.Image_Truck1(), " Image truck 1 should be displayed ", "Pickup truck  1 image is displayed", "Pickup image 1 is not displayed");
                testStepVerify.isElementDisplayed(Page_Driver_PickupInfo.Image_Truck2(), " Image truck 2 should be displayed ", "Pickup truck  2 image is displayed", "Pickup image 2 is not displayed");
                testStepVerify.isElementDisplayed(Page_Driver_PickupInfo.Image_Truck3(), " Image truck 3 should be displayed ", "Pickup truck  3 image is displayed", "Pickup image 3 is not displayed");
                break;
            case "less truck image - i":
                //action.click(Page_Driver_PickupInfo.DropZone2_TruckImages());
                utility.addImageInDropZone(Page_Driver_PickupInfo.DropZoneHiddenFileTag_TruckImage(), getTruckImages());
                action.invisibilityOfElementLocated(Page_Driver_PickupInfo.Wrapper_Spinner());
                action.invisibilityOfElementLocated(Page_Driver_PickupInfo.cancel_upload(true));
          //      action.invisibilityOfElementLocated(Page_Driver_PickupInfo.cancel_upload(true));
         //       Thread.sleep(5000);
                action.JavaScriptClick(Page_Driver_PickupInfo.Link_Truck1Image_Remove());
        //        action.click(Page_Driver_PickupInfo.Link_Truck1Image_Remove());
                break;

            case "less truck image - ii":
                action.invisibilityOfElementLocated(Page_Driver_PickupInfo.Wrapper_Spinner());
                action.invisibilityOfElementLocated(Page_Driver_PickupInfo.cancel_upload(true));
             //   Thread.sleep(5000);
           //     action.click(Page_Driver_PickupInfo.Link_Truck1Image_Remove());
                action.JavaScriptClick(Page_Driver_PickupInfo.Link_Truck1Image_Remove());
                break;

            default:
                break;
        }
        log("I enter "+strArg1+" data on Pickup Information page","I have entered "+strArg1 +" data on Pickup Information page", false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    public String[] getTruckImages() {
        String[] truckImageList = new String[3];
        truckImageList[0] = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("TRUCK1_IMAGE"));
        truckImageList[1] = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("TRUCK2_IMAGE"));
        truckImageList[2] = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("TRUCK3_IMAGE"));
        return truckImageList;
    }

    @When("^I enter \"([^\"]*)\" data on Documentation page$")
    public void i_enter_something_data_on_documentation_page(String p0) throws Throwable {
       try{
           switch (p0) {
            case "valid":
                String licenseImagePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),
                        PropertyUtility.getImageLocations("LICENSE_IMAGE"));
                String insuranceImagePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),
                        PropertyUtility.getImageLocations("INSURANCE_IMAGE"));
                //  action.click(Page_Driver_Doc.DropZone3_LicenseImage());
                utility.addImageInDropZone(Page_Driver_Doc.DropZoneHiddenFileTag_LicenseImage(), licenseImagePath);
                Thread.sleep(5000);
              //ToDO  if(action.isElementDisplayed(Page_Driver_Details.loading_Wrapper(true)))
                //ToDO    action.invisibilityOfElementLocated(Page_Driver_Details.loading_Wrapper(true));
                testStepVerify.isElementDisplayed(Page_Driver_Doc.Link_LicenseRemoveFile(), " Licence remove file should be displayed ", "Licence remove file is displayed", "Licence remove file is not displayed");

                action.clearSendKeys(Page_Driver_Doc.TextBox_LicenseNumber(),Long.toHexString(Double.doubleToLongBits(Math.random())));
                action.clearSendKeys(Page_Driver_Doc.TextBox_LicenseExpiry(), PropertyUtility.getDataProperties("ExpirationDate"));
                action.clearSendKeys(Page_Driver_Doc.TextBox_InsuranceExpiry(), PropertyUtility.getDataProperties("ExpirationDate"));
                utility.addImageInDropZone(Page_Driver_Doc.DropZoneHiddenFileTag_InsuranceImage(), insuranceImagePath);
                Thread.sleep(7000);
               //ToDO if(action.isElementDisplayed(Page_Driver_Details.loading_Wrapper(true)))
                //ToDO    action.invisibilityOfElementLocated(Page_Driver_Details.loading_Wrapper(true));
                testStepVerify.isElementDisplayed(Page_Driver_Doc.Link_InsuranceRemoveFile(true), " Insurance remove file should be displayed ", " Insurance remove file is displayed", " Insurance remove file is not displayed");
                break;

            case "invalid date":
                action.clearSendKeys(Page_Driver_Doc.TextBox_LicenseExpiry(), PropertyUtility.getDataProperties("Date_Invalid"));
                action.clearSendKeys(Page_Driver_Doc.TextBox_InsuranceExpiry(), PropertyUtility.getDataProperties("Date_Invalid"));
                break;

            case "invalid":
                action.clearSendKeys(Page_Driver_Doc.TextBox_LicenseExpiry(), PropertyUtility.getDataProperties("Date_2015"));
                action.clearSendKeys(Page_Driver_Doc.TextBox_LicenseNumber(), PropertyUtility.getDataProperties("DriverLicenseNumber_Existing")+ " "+Keys.BACK_SPACE);//ZH5J56W3
              //  action.click(Page_Driver_Doc.TextBox_InsuranceExpiry());
                action.clearSendKeys(Page_Driver_Doc.TextBox_InsuranceExpiry(), PropertyUtility.getDataProperties("Date_2015"));
                break;
            case "update":
                action.clearSendKeys(Page_Driver_Doc.TextBox_LicenseNumber(),Long.toHexString(Double.doubleToLongBits(Math.random())));
                action.clearSendKeys(Page_Driver_Doc.TextBox_LicenseExpiry(), PropertyUtility.getDataProperties("ExpirationDate"));
                action.clearSendKeys(Page_Driver_Doc.TextBox_InsuranceExpiry(), PropertyUtility.getDataProperties("ExpirationDate"));
                action.click(Page_Driver_Details.Button_Update());
                testStepVerify.isElementDisplayed(Page_Driver_Doc.Text_ValidationMsg(), "Valdiation message should be displayed ", "Valdiation message is displayed", "Valdiation message is not displayed");
                testStepAssert.isEquals(action.getText(Page_Driver_Doc.Text_ValidationMsg()),PropertyUtility.getMessage("driver.portal.validation.message"),"Valdiation message displayed should match",
                        "Valdiation message displayed are matches with expected & actual","Valdiation message displayed does not matches with expected & actual");
                break;
            default:
                break;
        }
        log("I enter "+p0+"  data on Documentation page","I entered "+p0 +"  data on Documentation page", true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I enter \"([^\"]*)\" data on Bank Details page$")
    public void i_enter_something_data_on_bank_details_page(String strArg1) throws Throwable {
       try{
           switch (strArg1) {
            case "valid":
                action.clearSendKeys(Page_Driver_Bank.TextBox_RoutingNumber(), PropertyUtility.getDataProperties("DriverRoutingNumber"));
                action.clearSendKeys(Page_Driver_Bank.TextBox_BankAccNumber(), PropertyUtility.getDataProperties("DriverBankAccNumber"));
                break;
            case "short bank account":
                action.clearSendKeys(Page_Driver_Bank.TextBox_BankAccNumber(), PropertyUtility.getDataProperties("InvalidValue"));
                break;
            case "invalid":
                action.clearSendKeys(Page_Driver_Bank.TextBox_RoutingNumber(), PropertyUtility.getDataProperties("InvalidValue"));
                action.clearSendKeys(Page_Driver_Bank.TextBox_BankAccNumber(), PropertyUtility.getDataProperties("DriverBankAccNumber_Invalid"));
                break;
            default:
                break;
        }
        log("I enter "+strArg1+" data on Bank Details page","I entered "+strArg1 +" data on Bank Details page", true);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
}
