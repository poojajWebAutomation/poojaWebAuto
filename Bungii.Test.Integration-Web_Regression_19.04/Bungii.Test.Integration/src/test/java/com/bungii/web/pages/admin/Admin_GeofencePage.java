package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin_GeofencePage extends PageBase {

    public WebElement Menu_Geofences () { return findElement("//span[text()='Geofences']", LocatorType.XPath); }

    public WebElement Menu_Attributes(){return findElement("//a[text()='Attributes']", LocatorType.XPath);}

    public WebElement Header_Geofences() { return findElement("//div[@id='page-wrapper']/div/h4", LocatorType.XPath); }

    public WebElement Header_Attributes() { return findElement("//div/h4", LocatorType.XPath); }

    public WebElement TextBox_Primary() { return findElement("Primary", LocatorType.Id); }

    public WebElement TextBox_Secondary() { return findElement("Secondary", LocatorType.Id); }

    public WebElement TextBox_GeoName() { return findElement("GeoName", LocatorType.Id); }

    public WebElement Label_GeoName(boolean...ignoreException) { return findElement("GeofenceSettings_0__Name", LocatorType.Id,ignoreException); }

    public WebElement Dropdown_Timezone() { return findElement("(//select[@class='form-select'])[2]", LocatorType.XPath); }

    public WebElement Dropdown_Region() { return findElement("//h4[text()='Geo (Region, Time-zone & Status)']/following-sibling::div[1]/div[1]", LocatorType.XPath); }

    public WebElement Dropdown_Status() { return findElement("(//select[@class='form-select'])[3]", LocatorType.XPath); }

    public WebElement Button_Scale() { return findElement("//button[text()='Scale']", LocatorType.XPath); }

    public WebElement Button_Save() { return findElement("//button[text()='Save']", LocatorType.XPath); }

    public WebElement Button_Cancel() { return findElement("//a[text()='cancel']", LocatorType.XPath); }

    public WebElement Label_GeneralErrorContainer() { return findElement("error-summary", LocatorType.Id); }

    public WebElement Label_PrimaryErrorContainer() { return findElement("PolylineForServiceArea-error", LocatorType.Id); }

    public WebElement Label_SecondaryErrorContainer() { return findElement("PolylineForSecondaryGeofence-error", LocatorType.Id); }

    public WebElement Label_NameErrorContainer() { return findElement("Name-error", LocatorType.Id); }

    public WebElement Button_Edit() { return findElement("//button[text()=\"Edit\"]", LocatorType.XPath); }

    public WebElement Button_Settings() { return findElement("//button[contains(text(),'Settings')]", LocatorType.XPath); }

    public WebElement Label_CustomerFAQLink() { return findElement("//td[contains(text(),'Customer FAQ link')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_DriverFAQLink() { return findElement("//td[contains(text(),'Driver FAQ link')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_MinTimeForDuoTrip() { return findElement("//td[contains(text(),'Minimum scheduled time for Duo trip')]/following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_MinTimeForSoloTrip() { return findElement("//td[contains(text(),'Minimum scheduled time for Solo trip')]/following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_MinTripCost() { return findElement("//td[contains(text(),'Minimum trip cost')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_SurveyEmailLink() { return findElement("//td[contains(text(),'Survey email link')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_TripCostPerMile() { return findElement("//td[contains(text(),'Trip cost per mile')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Label_TripCostPerMinute() { return findElement("//td[contains(text(),'Trip cost per minute')]//following-sibling::td[2]", LocatorType.XPath); }

    public WebElement Checkbox_Solo() {return  findElement("//tr/td[text()='Driver(s) for Scheduled trip']/following-sibling::td/div[1]/input",LocatorType.XPath);}

    public WebElement Checkbox_Duo() {return  findElement("//tr/td[text()='Driver(s) for Scheduled trip']/following-sibling::td/div[2]/input", LocatorType.XPath);}
    public WebElement Button_Next() {return  findElement("//a/span[text()='Next']", LocatorType.XPath);}

    public WebElement Checkbox_OnDemand() {return findElement("//tr/td[text()='Driver(s) for On-Demand trip']/following-sibling::td/div/input", LocatorType.XPath);}

    public WebElement Label_SettingsError() {return  findElement("//p[text()=\"Active geofence should allow either Scheduled or On demand trip.\"]", LocatorType.XPath);}

    public WebElement Button_SaveGeofenceSettings() {return findElement("//button[text()='Save']" , LocatorType.XPath);}

    public WebElement Select_ChicagoGeofence() { return findElement("//tbody[@id='NewApplicantsTBody']/tr/td[contains(text(),'Chicago')]", LocatorType.XPath);}

    public WebElement TextBox_MinimumScheduledtimeforduo(){return findElement("//td[text()='Minimum scheduled time for Duo trip']/following-sibling::td/div/div[1]/div/div/input",LocatorType.XPath);}

    public WebElement TextBox_MinimumScheduledtimeforsolo(){return findElement("//td[text()='Minimum scheduled time for Solo trip']/following-sibling::td/div/div[1]/div/div/input",LocatorType.XPath);}

    public WebElement Text_ErrorScheduleTimeForDuo() { return findElement("//tr/td[text()='Minimum scheduled time for Duo trip']/following-sibling::td/div/div/div/div[2]", LocatorType.XPath);}
    public WebElement Text_ErrorScheduleTimeForSolo() { return findElement("//tr/td[text()='Minimum scheduled time for Solo trip']/following-sibling::td/div/div/div/div[2]", LocatorType.XPath);}

    public WebElement Select_KansasGeofence() { return findElement("//tbody[@id='NewApplicantsTBody']/tr/td[contains(text(),'Kansas')]\n", LocatorType.XPath);}

    public WebElement Select_GoaGeofence() { return findElement("//tbody[@id='NewApplicantsTBody']/tr/td[contains(text(),'Goa')]\n", LocatorType.XPath);}

    public WebElement TextBox_Bunggi_Cut_Rate() { return findElement("//div[@class=\"row\"][1]/div[1]/div/div[1]/div/input",LocatorType.XPath);}
    public WebElement TextBox_Driver_Cut_Rate() { return findElement("attributeValueDiverCutPerDelivery",LocatorType.Id);}

    public WebElement TextError_BunggiCut() { return findElement("//tr/td[text()='Bungii Cut Per Delivery']/following-sibling::td/div/div[1]/div/div/div[2]",LocatorType.XPath);}
    public WebElement TextError_General() { return findElement("//span[contains(text(),'Oops! It looks like you missed something. Please f')]",LocatorType.XPath);}

    public WebElement Checkbox_Active_Geofences() { return findElement("ActiveGeofencesOnly",LocatorType.Id);}

    public WebElement Row_geofenceList(String Name,String Status,String Timezone) {return  findElement(String.format("//tr/td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]/following-sibling::td[contains(.,'%s')]",Name,Status,Timezone), LocatorType.XPath);}

    public WebElement List_Geofence() {return findElement("dropdown-autoclose-outside" , LocatorType.Id);}
    public WebElement TextBox_SearchGeofence() {return findElement("foo" , LocatorType.Id);}
    public WebElement Button_ApplyGeofence() {return findElement("//button[contains(text(),'APPLY')]" , LocatorType.XPath);}
    public WebElement Button_Clear() {return findElement("//button[contains(text(),'CLEAR')]" , LocatorType.XPath);}

    public WebElement Checkbox_Geofence(String geofence , boolean... ignoreException) {return findElements(String.format("//span[contains(.,'%s')]/preceding::span/span",geofence) , LocatorType.XPath).get(0);}
    public WebElement Checkbox_GeofenceLabel(String geofence , boolean... ignoreException) {return findElement(String.format("//span[contains(.,'%s')]",geofence) , LocatorType.XPath, ignoreException);}

    public WebElement Button_DownloadZipCodes() {return findElement("//div[@class='geofence-form col-sm-5']/following-sibling::div/a/span" , LocatorType.XPath);}
    public List<WebElement> List_RowCount() {return findElements("//tbody[@id='NewApplicantsTBody']/tr[@class='clickable-row']/td[2]" , LocatorType.XPath);}
    public WebElement List_ActiveGeofence(int i) {return findElement("//tbody[@id='NewApplicantsTBody']/tr["+i+"]/td[2]" , LocatorType.XPath);}

    public WebElement Row_GeofenceName(String geoName) { return findElement("//td[contains(text(),'"+geoName+"')]",LocatorType.XPath);}
    public WebElement Text_GeoHistory(boolean...ignoreException) {return findElement("//h4[contains(text(),'Geo-History')]",LocatorType.XPath, ignoreException);}
    public List<WebElement> Rows_GeoHistoryLogs() { return findElements("//tbody[@id='GeofenceHistoryTBody']/tr[@class='geo-tr']",LocatorType.XPath);}
    public WebElement Text_SrNo() { return findElement("//th[contains(text(),'Sr.No.')]",LocatorType.XPath);}
    public WebElement Text_ModifiedDate() { return findElement("//th[contains(text(),'Modified Date')]",LocatorType.XPath);}
    public WebElement Text_ModifiedBy() { return findElement("//th[contains(text(),'Modified By')]",LocatorType.XPath);}
    public WebElement Text_Phone() { return findElement("//th[contains(text(),'Phone')]",LocatorType.XPath);}
    public WebElement Text_Changes() { return findElement("//th[contains(text(),'Changes')]",LocatorType.XPath);}
    public WebElement Button_GeofenceCancel() { return findElement("//div[@id='btnCancel']",LocatorType.XPath);}
    public WebElement Link_Changes() { return findElement("//tbody/tr[@id='historyID0']/td[6]/a",LocatorType.XPath);}
    public WebElement Text_Fields() { return findElement("//div[@id='historyLog_historyID0']/table/thead/tr/th[contains(text(),'Fields')]",LocatorType.XPath);}
    public WebElement Text_OldValue() { return findElement("//div[@id='historyLog_historyID0']/table/thead/tr/th[contains(text(),'Old Value')]",LocatorType.XPath);}
    public WebElement Text_NewValue() { return findElement("//div[@id='historyLog_historyID0']/table/thead/tr/th[contains(text(),'New Value')]",LocatorType.XPath);}

    public WebElement Value_ModifiedDate() { return findElement("//tbody/tr[@id='historyID0']/td[3]",LocatorType.XPath);}
    public WebElement Value_ModifiedBy() { return findElement("//tbody/tr[@id='historyID0']/td[4]",LocatorType.XPath);}
    public WebElement Value_Phone() { return findElement("//tbody/tr[@id='historyID0']/td[5]",LocatorType.XPath);}
    public WebElement Value_Changes() { return findElement("//tbody/tr[@id='historyID0']/td[6]/a",LocatorType.XPath);}

    public WebElement Text_GeofenceHighlighted() { return findElement("//ol/li/ol/li/span/label/span[3]/span/mark", LocatorType.XPath);}

    public List<WebElement> List_GeofenceRegions() { return findElements("//div[@id=\"tree\"]/ul/li/div/span[4]", LocatorType.XPath);}

}
