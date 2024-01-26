package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Driver_PickUpInfoPage extends PageBase {
    //Pickup Information - Blank field validation
    public WebElement Err_PickupInfo_AllBlank () { return findElement("summary2", LocatorType.Id); }

    //Pickup Information - Pickup make
    public WebElement TextBox_PickupMake () { return findElement("Make", LocatorType.Name); }

    //Pickup Information - Pickup model
    public WebElement TextBox_PickupModel () { return findElement("Model", LocatorType.Name); }

    //Pickup Information - Pickup year
    public WebElement DropDown_PickupYear () { return findElement("Year", LocatorType.Name); }

    //Pickup Information - License plate number
    public WebElement TextBox_LicenseNo () { return findElement("VehicleNumber", LocatorType.Name); }

    //Pickup Information - Truck Image Upload
    public WebElement DropZone2_TruckImages () { return findElement("//div[@id='dropzone2']", LocatorType.XPath); }

    //Pickup Information - Truck image error
    public WebElement Err_AddTruckImages () { return findElement("dropzone2-error", LocatorType.Id); }

    //Pickup Information - Uploaded Truck Image 1
    public WebElement Image_Truck1 () { return findElement("//div[@id='dropzone2']/div[2]", LocatorType.XPath); }

    //Pickup Information - Uploaded Truck Image 2
    public WebElement Image_Truck2 () { return findElement("//div[@id='dropzone2']/div[3]", LocatorType.XPath); }

    //Pickup Information - Uploaded Truck Image 3
    public WebElement Image_Truck3 () { return findElement("//div[@id='dropzone2']/div[4]", LocatorType.XPath); }

    //Pickup Information - Remove
    public WebElement Link_Truck1Image_Remove () { return findElement("//div[@id='dropzone2']/div[2]/a[text()='Remove file']", LocatorType.XPath); }

    //ickup Information - Next Button
    public WebElement Button_PickUpNext () { return findElement("//li[contains(text(),'Vehicle Information')]", LocatorType.XPath); }

    public WebElement DropZoneHiddenFileTag_TruckImage () { return findElement("//input[@class = 'dz-hidden-input'][2]", LocatorType.XPath, true );}

    public List<WebElement> Div_UploadedImages () { return findElements("//div[contains(@class,'dz-success dz-complete')]", LocatorType.XPath); }

    public WebElement Wrapper_Spinner () { return findElement("//div[@class='modal fade loader in' and @style='display: block;']", LocatorType.XPath, true); }
    public WebElement cancel_upload(boolean ...ignoreException){return findElement("//*[text()='Cancel upload']",LocatorType.XPath,ignoreException);}
}
