package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_DetailsPage extends PageBase {

    //Header
    public WebElement DriverReg_AllPagesHeader () { return findElement("//div[@id='tab-title']/h3", LocatorType.XPath); }

    //driver Details - Blank field validation
    public WebElement Err_DriverDetails_AllBlank () { return findElement("summary1", LocatorType.Id); }

    //Details - Address Information - Street Address
    public WebElement TextBox_StreetAddress () { return findElement("Address1", LocatorType.Name); }

    public WebElement List_StreetAddress(){return  findElement("//div[@id='divPlacesResult']/div[1]",LocatorType.XPath);}

    //Details - Address Information - City
    public WebElement TextBox_City () { return findElement("City", LocatorType.Name); }

    //Details - Address Information - State
    public WebElement DropDown_State () { return findElement("DriverDetails_State", LocatorType.Id); }
    public WebElement TextBox_State () { return findElement("State", LocatorType.Name); }

    //Details - Address Information - Zip Code
    public WebElement TextBox_ZipCode () { return findElement("ZipPostalCode", LocatorType.Name); }

    //Details - Address Information - Zip Code - Error
    public WebElement Err_ZipCode () { return findElement("DriverDetails_ZipPostalCode-error", LocatorType.Id); }

    //Details - 'Im 18 or older' checkbox
    public WebElement CheckBox_Age18 () { return findElement("//input[@id='DriverDetails.MeetsAgeLimit']/following-sibling::label", LocatorType.XPath); }

    //Details - 'I'm able to lift up 75 pounds' checkbox
    public WebElement CheckBox_Lift75 () { return findElement("//input[@id='DriverDetails.CanLiftRequiredPounds']/following-sibling::label", LocatorType.XPath); }

    //Details - 'I have had at least one year of driving experience' checkbox
    public WebElement CheckBox_DrivingExp () { return findElement("//input[@id='DriverDetails.HasSufficientDrivingExperience']/following-sibling::label", LocatorType.XPath); }

    //Details - driver Availability - Clear all
    public WebElement Link_ClearAll () { return findElement("//input[@class='clearAll']/parent::label", LocatorType.XPath); }

    //Details - driver Availability - Wednesday Afternoon
    public WebElement CheckBox_WedAftrn () { return findElement("//input[@id='DriverDetails_DriverAvailability_TimeSlots_2__Afternoon']", LocatorType.XPath); }

    //Details - driver Availability - Select all
    public WebElement Link_SelectAll () { return findElement("//input[@class='selectAll']/parent::label", LocatorType.XPath); }

    //Details - driver Availability - Sunday Evening
    public WebElement CheckBox_SunEve () { return findElement("//input[@id='DriverDetails_DriverAvailability_TimeSlots_6__Evening']", LocatorType.XPath); }

    //Details - TextArea - Other
    public WebElement TextArea_Other () { return findElement("DriverDetails_DriverAvailability_OtherAvailabilityDetails", LocatorType.Id); }

    //Details - TextArea - Other - Error
    public WebElement Err_Other () { return findElement("DriverDetails_DriverAvailability_OtherAvailabilityDetails-error", LocatorType.Id); }

    //Details - TextArea - Current / Primary Occupation
    public WebElement TextArea_Occupation () { return findElement("DriverDetails_CurrentOccupation", LocatorType.Id); }

    //Details - driver Picture Upload
    public WebElement Link_DriverPicture () { return findElement("//div[@id='dropzone1']/div/p/a[contains(text(),'click here')]", LocatorType.XPath); }
    public WebElement Area_DriverPictureDrop(){return  findElement("//*[@class=='dz-default dz-message row']",LocatorType.Id);}
    //Details - driver Picture Upload - Crop Button
    public WebElement Button_Crop () { return findElement("//div[@class='modal-content']/div[@class='modal-footer']/button[text()='Crop']", LocatorType.XPath); }

    //Details - driver Picture Upload - Remove File Link
    public WebElement Link_RemoveFile () { return findElement("//div[@id='dropzone1']/div/a[text()='Remove file']", LocatorType.XPath); }

    //Details - More Details - Social Security Number
    public WebElement TextBox_SSN () { return findElement("DriverDetails_SocialSecurityNumber", LocatorType.Id); }

    //Details - More Details - Social Security Number - Error
    public WebElement Err_SSN () { return findElement("DriverDetails_SocialSecurityNumber-error", LocatorType.Id); }

    //Details - More Details - Birthday
    public WebElement TextBox_Birthday () { return findElement("DriverDetails_DateOfBirth", LocatorType.Id); }

    //Details - More Details - Birthday - Error
    public WebElement Err_Birthday () { return findElement("DriverDetails_DateOfBirth-error", LocatorType.Id); }

    //Details - More Details - How’d you hear about us?
    public WebElement DropDown_Info () { return findElement("DriverDetails_ReferralSource", LocatorType.Id); }

    //Details - "I consent to background and driving record checks" checkbox
    public WebElement CheckBox_Consent () { return findElement("//input[@id='DriverDetails.HasBackgroundConsent']/following-sibling::label", LocatorType.XPath); }

    //Details - Next Button
    public WebElement Button_DetailsNext () { return findElement("//li[contains(text(),'Driver Details')]", LocatorType.XPath); }

    public WebElement DropZoneHiddenFileTag_ProfileImage () { return findElement("//input[@class = 'dz-hidden-input']", LocatorType.XPath, true); }

    public WebElement Wrapper_Spinner () { return findElement("//div[@class='modal fade loader in' and @style='display: block;']", LocatorType.XPath, true); }

    public WebElement loading_Wrapper(boolean...ignoreException){ return  findElement("modal-backdrop fade in",LocatorType.ClassName,ignoreException); }

    public  WebElement Menu_DriverDetails () { return findElement("//li[contains(text(),'Driver Details')]", LocatorType.XPath); }

    public WebElement Textbox_DriverDetails_SSN () { return findElement("SocialSecurityNumber", LocatorType.Id); }

    public WebElement Textbox_DriverDetails_DOB () { return findElement("DateOfBirth", LocatorType.Name); }

    public WebElement Button_Update () { return findElement("//button[text()='UPDATE']", LocatorType.XPath); }

    public WebElement Button_Submit () { return findElement("btnsend", LocatorType.Id);}

    public WebElement Button_ConfirmSubmit () { return findElement("btnsendagree", LocatorType.Id);}

    public WebElement Link_Logout () { return findElement("//a[text()='log out']", LocatorType.XPath); }

    public WebElement Text_DriverTotalEarnings () { return findElement("//p[contains(text(),'Total Earnings')]/following-sibling::h3", LocatorType.XPath); }

    public WebElement Text_TotalTrips () { return findElement("//p[contains(text(),'Total Trips')]",LocatorType.XPath);}

    public WebElement Count_TotalTrips () { return findElement("//p[contains(text(),'Total Trips')]/following-sibling::h3",LocatorType.XPath);}

    public WebElement Text_TripsMonth () { return findElement("//p[contains(text(),'Trips / Month')]",LocatorType.XPath);}

    public WebElement Count_TripsMonth () { return findElement("//p[contains(text(),'Trips / Month')]/following-sibling::h3",LocatorType.XPath);}

    public WebElement Text_TotalEarnings () { return findElement("//p[contains(text(),'Total Earnings')]",LocatorType.XPath);}

    public WebElement Text_TotalTips () { return findElement("//p[contains(text(),'Total Tips')]",LocatorType.XPath);}

    public WebElement Money_TotalTips () { return findElement("//p[contains(text(),'Total Tips')]/following-sibling::h3",LocatorType.XPath);}

    public WebElement Text_EarningsMonth () { return findElement("//p[contains(text(),'Earnings / Month')]",LocatorType.XPath);}

    public WebElement Money_EarningsMonth () { return findElement("//p[contains(text(),'Earnings / Month')]/following-sibling::h3",LocatorType.XPath);}

    public WebElement Text_MyRating () { return findElement("//p[contains(text(),'My Rating')]",LocatorType.XPath);}

    public WebElement Value_MyRating () { return findElement("//p[contains(text(),'My Rating')]/following-sibling::h3",LocatorType.XPath);}

    public WebElement Text_HoursWorked () { return findElement("//div[@class='media']/div[@class='media-body']/p[contains(text(),'Hours worked')]",LocatorType.XPath);}

    public WebElement DateRange_HoursWorked () { return findElement("//div[@class='media']/div[@class='media-body']/p[contains(text(),'Hours worked')]/following-sibling::small",LocatorType.XPath);}

    public WebElement Value_HoursWorked () { return findElement("//div[@class='media']/div[@class='media-body']/p[contains(text(),'Hours worked')]/following-sibling::h2",LocatorType.XPath);}

    public WebElement Text_HoursWorkedQuarterDate () { return findElement("//p[contains(text(),'Hours worked quarter to date')]",LocatorType.XPath);}

    //public WebElement DateRange_HoursWorkedQuarterDate () { return findElement("//div[@class='media']/div[@class='media-body']/p[contains(text(),'Hours worked')]/following-sibling::small",LocatorType.XPath);}

    public WebElement Value_HoursWorkedQuarterDate () { return findElement("//p[contains(text(),'Hours worked quarter to date')]/following-sibling::h2",LocatorType.XPath);}

    //Driver Basic info
    public WebElement Button_BasicInfo () { return findElement("//li[contains(text(),'Driver Basic Info')]", LocatorType.XPath); }
    public WebElement TextBox_FirstName () { return findElement("FirstName", LocatorType.Name); }
    public WebElement TextBox_LastName () { return findElement("LastName", LocatorType.Name); }
    public WebElement TextBox_Email () { return findElement("EmailAddress", LocatorType.Name); }
    public WebElement TextBox_PhoneNumber () { return findElement("PhoneNo", LocatorType.Name); }

}
