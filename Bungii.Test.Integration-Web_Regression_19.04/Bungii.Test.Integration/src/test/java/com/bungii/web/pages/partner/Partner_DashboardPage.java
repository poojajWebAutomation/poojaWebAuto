package com.bungii.web.pages.partner;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Partner_DashboardPage extends PageBase {

    //Partner Get Estimate Header
    public WebElement Label_Get_Estimate_Header() { return findElement("//h1[contains(text(),'Get Estimate')]", LocatorType.XPath); }

    //Start Over
    public WebElement Label_Start_Over() { return findElement("//span[contains(text(),'Start Over')]",LocatorType.XPath);}

    //Logout button
    public WebElement Button_Partner_LogOut(){ return findElement("//span[text()='Log Out']", LocatorType.XPath); }

    //Solo Radio Button
    public WebElement RadioButton_Partner_Solo() {return findElement("solo",LocatorType.Id);}

    //Duo Radio Button
    public WebElement RadioButton_Partner_Duo() {return findElement("duo",LocatorType.Id);}

    //Pickup Edit button
    public WebElement Button_Pickup_Edit() { return findElement("//span[@class='link']",LocatorType.XPath);}

    //Pickup Clear button X
    public WebElement Button_PickupClear() { return findElement("pickupAddCloseIcon",LocatorType.Id);}

    //Pickup Address in edit
    public WebElement Dropdown_Pickup_Address() { return findElement("pickValue",LocatorType.Id);}

    //Pickup Address in non edit
    public WebElement Text_Pickup_Address() { return findElement("//label[@class='pickup form-label']/following::address",LocatorType.XPath);}

    //Pickup Address List
    public WebElement List_Pickup_Address() { return findElement("//div[contains(@class,'pac-container pac-logo')]/div[1]/span[2]",LocatorType.XPath);}

    //Set Pickup Address
    public WebElement SetPickupAddress() { return findElement("//input[@id='pickupAdd']",LocatorType.XPath);}

    //Delivery Clear button X
    public WebElement Button_DeliveryClear() { return findElement("dropoffAddCloseIcon",LocatorType.Id );}

    //Delivery Address
    public WebElement Dropdown_Delivery_Address() { return findElement("dropValue",LocatorType.Id);}

    public WebElement Icon_PickupAddSearch_Address() { return findElement("pickupAddSearchIcon",LocatorType.Id);}


    //Delivery Address List
    public WebElement List_Delivery_Address() { return findElement("//div[contains(@class,'pac-container pac-logo')]/div[1]/span[2]",LocatorType.XPath);}

    //Driver Helper Carry checkbox
    public WebElement Checkbox_Driver_HelperCarry() { return findElement("//input[@id='DriverHelpCarryFields']",LocatorType.XPath);}

    //Set Delivery Address
    public WebElement SetDeliveryAddress() { return findElement("//input[@id='dropoffAdd']",LocatorType.XPath);}

    //Load Upload Time Dropdown
    public WebElement Dropdown_Load_Unload_Time() { return findElement("//div[contains(@class,'MuiFormControl-root load-time')]//div[contains(@class,'MuiInputBase-formControl')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_15() { return findElement("//li[contains(text(),'15 minutes')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_30() { return findElement("//li[contains(text(),'30 minutes')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_45() { return findElement("//li[contains(text(),'45 minutes')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_60() { return findElement("//li[contains(text(),'60 minutes')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_75() { return findElement("//li[contains(text(),'75 minutes')]",LocatorType.XPath);}
    public WebElement Load_Unload_Time_90() { return findElement("//li[contains(text(),'90+ minutes')]",LocatorType.XPath);}


    //Pickup Date dropdown
    public WebElement Dropdown_Pickup_Date() { return findElement("//div[@class='MuiFormControl-root pickup-date']",LocatorType.XPath);}

    //Today and 4 days
    public WebElement Pickup_Date() { return findElement("//label[contains(text(),'Pickup Date')]/following::div[1]/div/div/input",LocatorType.XPath);}
/*    public WebElement Pickup_Date_Today() { return findElement("//li[contains(@class,'MuiButtonBase-root MuiListItem-root MuiMenuItem-root Mui-selected MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button Mui-selected')]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_1() { return findElement("//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'][1]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_2() { return findElement("//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'][2]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_3() { return findElement("//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'][3]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_4() { return findElement("//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'][4]",LocatorType.XPath);}*/
    public WebElement Pickup_Date_Today() { return findElement("//div[contains(text(),'Today')]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_1() { return findElement("//div[contains(text(),'Tomorrow')]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_2() { return findElement("//div[@class='date-container auto-dimensions row']/div/div[3]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_3() { return findElement("//div[@class='date-container auto-dimensions row']/div/div[4]",LocatorType.XPath);}
    public WebElement Pickup_date_Today_4() { return findElement("//div[@class='date-container auto-dimensions row']/div/div[5]",LocatorType.XPath);}
    public WebElement Pickup_date_Tomorrow() { return findElement("//div[contains(text(),'Tomorrow')]",LocatorType.XPath);}

    //Pickup Time dropdown
    public WebElement Dropdown_Pickup_Time(boolean...ignoreException) { return findElement("//label[contains(text(),'Pickup Time')]/following-sibling::div/div/div",LocatorType.XPath,ignoreException);}
    public WebElement Text_Pickup_Time() { return findElement("//div[@class='MuiFormControl-root pickup-time']/div/div/input",LocatorType.XPath);}
    public WebElement Pickup_Time1() { return findElement("//li[@class='MuiButtonBase-root MuiListItem-root MuiMenuItem-root MuiMenuItem-gutters MuiListItem-gutters MuiListItem-button'][1]",LocatorType.XPath);}

    //Get Estimate Button
    public WebElement Button_Get_Estimate() { return findElement("get-estimate",LocatorType.Id);}
    public WebElement Label_DeliveryCostEstimate() { return findElement("//label[contains(text(),'Delivery Cost:')]/strong",LocatorType.XPath);}
    public WebElement Label_DeliveryCostDelivery() { return findElement("//h2[text()='Delivery Cost']//following::span/strong",LocatorType.XPath);}
    //Estimated Cost label
    public WebElement Label_Estimated_Cost() { return findElement("//label[contains(text(),'Estimated Cost:')]",LocatorType.XPath);}

    public WebElement Label_Distance() { return findElement("//label[contains(text(),'Distance in Miles -')]/strong",LocatorType.XPath);}

    public WebElement Label_EstDeliveryTime(){return findElement("//label[contains(text(),'Est. Delivery Time')]/strong",LocatorType.XPath);}

    //Continue button
    //public  WebElement Button_Continue() { return findElement("//a[@class='btn']",LocatorType.XPath);}
    public  WebElement Button_Continue() { return findElement("//button[@class='btn']",LocatorType.XPath);}

    //Blank message for pickup address
    public WebElement Message_Blank_Pickup() { return findElement("//div[contains(text(),'Pickup Address is required.')]",LocatorType.XPath);}

    //Blank message for delivery address
    public WebElement Message_Blank_Delivery() { return findElement("//div[contains(text(),'Drop Off Address is required.')]",LocatorType.XPath);}

    //Blank message for load unload time
    public WebElement Message_Blank_LoadUnload_Time() { return findElement("//div[contains(text(),'Load Time is required.')]",LocatorType.XPath);}

    public WebElement Icon_SearchPickupAdd() { return findElement("pickupAddSearchIcon",LocatorType.Id);}
    //Highlighted fields message
    public WebElement Message_Highlighted_Fields() { return findElement("//div[contains(text(),'Please verify the highlighted fields above.')]",LocatorType.XPath);}

    //Information Icon What’s needed?
    public  WebElement Information_Icon_Whats_Needed() { return findElement("//label[contains(text(),\"WHAT'S NEEDED\")]/i",LocatorType.XPath);}

    //Information Icon Delivery Address
    public  WebElement Information_Icon_Delivery_Address() { return findElement("//label[contains(text(),'Delivery Address')]/i",LocatorType.XPath);}

    //Information Icon Load + Unload Time
    public  WebElement Information_Icon_LoadUpload() { return findElement("//label[contains(text(),'Load + Unload Time')]/i",LocatorType.XPath);}

    //Information Icon Pickup Date
    public  WebElement Information_Icon_Pickup_Date() { return findElement("//label[contains(text(),'Pickup Date')]/i",LocatorType.XPath);}

    //Inner text of each Information Icon
    public WebElement InnerText_Information_Icon() { return findElement("//div[@class='tooltip-inner']",LocatorType.XPath);}

    //Partner Logout button
    //public WebElement Button_Partner_Logout() { return findElement("//a[@class='header-menu-btn logout-btn']",LocatorType.XPath);}
    public WebElement Button_Partner_Logout() { return findElement("//span[contains(text(),'Log Out')]",LocatorType.XPath);}

    //Service Level information icon
    public WebElement Information_Icon__Service_Level() { return findElement("//label[contains(text(),'Service Level')]//i",LocatorType.XPath);}

    //Service Level dropdown button
    public WebElement Dropdown_Service_Level() { return findElement("//div[@id='service-level-menu']",LocatorType.XPath);}

    //No service selected text
    public WebElement Text_No_Service() { return findElement("//div[@class='service-title']",LocatorType.XPath);}

    //Service Level text
    public WebElement Text_Service_Level() { return findElement("//h2[contains(text(),'Service Level')]",LocatorType.XPath);}

    //Curbside service level
    public WebElement Text_Curbside() { return findElement("//span[contains(text(),'Curbside')]",LocatorType.XPath);}
    public WebElement Radio_Button_Curbside() { return findElement("//span[@class='MuiButtonBase-root MuiIconButton-root jss72 MuiRadio-root MuiRadio-colorSecondary jss73 Mui-checked MuiIconButton-colorSecondary']//input[@name='radio-button-demo']",LocatorType.XPath);}

    //Threshold service level
    public WebElement Text_Threshold() { return findElement("//span[contains(text(),'Threshold')]",LocatorType.XPath);}
    public WebElement Radio_Button_Threshold() { return findElement("//div[2]//div[1]//span[1]//span[1]//input[1]",LocatorType.XPath);}

    //Room of Choice
    public WebElement Text_Room_of_Choice() { return findElement("//span[contains(text(),'Room of Choice')]",LocatorType.XPath);}

    //White Glove
    public WebElement Text_White_Glove() { return findElement("//span[contains(text(),'White Glove')]",LocatorType.XPath);}

    public WebElement Dropdown_ServiceLevel(String serviceLevel) { return findElement("//span[contains(@class, 'service-title') and contains(@data-name, '"+serviceLevel+"')]",LocatorType.XPath);}

    //Close button on service level
    public WebElement Button_close() { return findElement("//span[@class='modal-close']",LocatorType.XPath);}

    //Selected service name
    public WebElement Text_Service_Name() { return findElement("//span[@class='service-title d-block space-below']",LocatorType.XPath);}

    //Service name and decription
    public WebElement Text_Service_Decription() { return findElement("//label[text()='Service Level']/following::p[1]",LocatorType.XPath);}

    //Delivery Cost
    public WebElement Label_Delivery_Cost() { return findElement("//div/label[contains(text(),'Delivery Cost:')]",LocatorType.XPath);}

    //Delivery cost on delivery details
    public WebElement Text_Delivery_Cost() {return findElement("//h2[contains(text(),'Delivery Cost')]/span/strong",LocatorType.XPath);}

    public WebElement Label_NoServiceSelected() { return findElement("//div[contains(text(),'No service selected.')]",LocatorType.XPath);}

    //Pickup Address in edit
    public WebElement Header_QuotesOnly() { return findElement("//h1[text()='Get Quote']",LocatorType.XPath);}

    //Label 1Pallet
    public WebElement Label_1Pallet() { return findElement("//label[contains(text(),'1 Pallet')]",LocatorType.XPath);}

    //Label 2Pallets
    public WebElement Label_2Pallets() { return findElement("//label[contains(text(),'2 Pallets')]",LocatorType.XPath);}

    //time
    public WebElement Time() { return findElement(" //form/div[5]/div[2]",LocatorType.XPath);}


    public WebElement SelectTime() { return findElement("//div/ul[@class =\"MuiList-root MuiMenu-list jss3 MuiList-padding\"]/li[3]",LocatorType.XPath);}
    //Tracking Id Column
    public WebElement Text_TrackingId_Column() { return findElement("//tr/th[5]/div",LocatorType.XPath);}

    // Summary Tracking Id
    public WebElement Text_Summary_TrackingId() { return findElement("//section/div[1]/p",LocatorType.XPath);}
    // Summary Delivery pick up address
    public WebElement Text_Summary_PickupAddress() { return findElement("//section/div[1]/div/p",LocatorType.XPath);}

    // Summary Delivery Address
    public WebElement Text_Summary_DeliveryAddress() { return findElement("//section/div[2]/div/p",LocatorType.XPath);}



    //Search Bar
    public WebElement Textbox_SearchBar() { return findElement("searchText", LocatorType.Id); }

    //Trip DeliveryDate
    public WebElement Trip_DeliveryDate() { return findElement("//tbody/tr[1]/td[1]/div",LocatorType.XPath);}

    //Trip TrackingId
    public WebElement Text_Trip_TrackingId() { return findElement("//tr[1]/td[5]/div[1]",LocatorType.XPath);}

    //trip Customer
    public WebElement Text_Trip_Customer() { return findElement("//tr[1]/td[3]/div[1]",LocatorType.XPath);}

    //Trip Delivery Address
    public WebElement Text_Trip_DeliveryAddress() { return findElement("//tr[1]/td[4]/div[1]",LocatorType.XPath);}

    //Trip Delivery Status
    public WebElement Trip_DeliveryStatus() { return findElement("//tr[1]/td[5]/div[1]",LocatorType.XPath);}

    //No Trip Message
    public WebElement Label_Trip_ErrorMessage() { return findElement("//td/div/div[text() =\"Sorry, no records found\"]",LocatorType.XPath);}

    //Text Support
    public WebElement Text_TextSupport() { return findElement("//strong[contains(text(),'Text Support:')]",LocatorType.XPath);}

    //Text Support Number
    public WebElement Number_TextSupport() { return findElement("//strong[contains(text(),'Text Support:')]/following-sibling::span",LocatorType.XPath);}

    //Text Email Support
    public WebElement Text_EmailSupport() { return findElement("//strong[contains(text(),'Email Support:')]",LocatorType.XPath);}

    //Email Support value
    public WebElement Email_EmailSupport() { return findElement("//strong[contains(text(),'Email Support:')]/following-sibling::span/a",LocatorType.XPath);}

    //Select trip from track delivery
    public WebElement Link_SelectTripTrackDeliveries() { return findElement("//table/tbody/tr",LocatorType.XPath);}

    //click checkbox
    public WebElement Label_Checkbox() { return findElement("DriverHelpCarryFields",LocatorType.Id);}

    //Select Next month on calender
    public WebElement Link_NextMonth() { return findElement("//div/div[@class=\"react-datepicker\"]/button[text()=\"Next Month\"]",LocatorType.XPath);}

    //Select the date on calender
    public WebElement FutureTrip(String date) { return findElement(String.format("//div[@class=\"react-datepicker__week\"]/div[text()=\"%s\"]",date),LocatorType.XPath);}

    public WebElement Text_DateSelectedFromUi() { return findElement("//div[@class=\"input-wrapper\"]/input",LocatorType.XPath);}

    //Partner portal trip time
    public WebElement Text_PartnerPortalGeofenceTime() { return findElement("//div[@class =\"col-padding row\"]/div[2]/div/div/div/div",LocatorType.XPath);}

    //Click pickup address in partner portal
    public WebElement DropDown_PickupAddressPartnerPortal() { return findElement("//div[@class =\"position-relative form-group\"][1]/div/div",LocatorType.XPath);}

    //Select pickup address from dropdown
    public WebElement Text_PickupAddressesFromPartnerPortalDropDown(int addressNo) { return findElement(String.format("//div[3]/ul/li[%d]",addressNo),LocatorType.XPath);}

    //Tooltip Pickup Date
    public WebElement Icon_ToolTip_PickupDate() { return findElement("//label[text()='Pickup Date']/i",LocatorType.XPath);}

    //Tooltip Pickup Date
    public WebElement Label_ToolTip_PickupDate() { return findElement("//div[@class='tooltip-inner']",LocatorType.XPath);}

    public WebElement Text_PartnerName() { return findElement("//tr/td[8]",LocatorType.XPath);}
    public WebElement Text_PartnerNameDeliveryDetailsPage() { return findElement("//h4[contains(text(),'Partner :')]/following-sibling::span",LocatorType.XPath);}
    public WebElement Text_PartnerNameAllDeliveryPage() { return findElement("//tr/td[9]",LocatorType.XPath);}


    //Partner portal filter
    public WebElement DropDown_Filter() { return findElement("//div[@class='caret filter']",LocatorType.XPath);}

    //Select checkbox from partner portal filter
    public WebElement Checkbox_Completed(String filter) { return findElement(String.format("//div/div/label[text()='%s']",filter),LocatorType.XPath);}

    //Partner portal apply filter button
    public WebElement Button_Apply() { return findElement("//div[@class=\"dropdown-menu show\"]/div/div[6]/button[1]",LocatorType.XPath);}

    //Delivery details page partner portal driver Name
    public WebElement Text_DriverName() { return findElement("//div/div[@class=\"media\"]/div/p",LocatorType.XPath);}

    //Header Receipt Number
    public WebElement HeaderText_ReceiptNumber() { return findElement("//th[2]/span/div/div[1]",LocatorType.XPath);}

    //Receipt Number value of first row
    public WebElement TextValue_ReceiptNumber() { return findElement("//tr[1]/td[2]/div[1]",LocatorType.XPath);}

    //First row of result
    public WebElement FirstRow(boolean ...ignoreException) { return findElement("//tr[1]",LocatorType.XPath,ignoreException);}

    //First data in first row of result
    public WebElement FirstRowData() { return findElement("//tr[1]/td[1]/div[1]",LocatorType.XPath);}

    //Status data in first row of result
    public WebElement Text_DeliveryStatus() { return findElement("//tr[1]/td[6]/div[1]",LocatorType.XPath);}

    //Partner portal select pickup time
    public WebElement Button_PickupTime() { return findElement("//label[text()=\"Pickup Time\"]/following-sibling::div[1]",LocatorType.XPath);}

    //Patner portal last time slot
    public WebElement Text_LastTimeSlot(int index) { return findElement(String.format("//div[@id=\"menu-\"]/div/ul/li[%d]",index),LocatorType.XPath);}

    //Patner portal first time slot
    public WebElement Text_FirstTimeSlot() { return findElement("//div[@id=\"menu-\"]/div/ul/li[1]",LocatorType.XPath);}

    //Patner portal calender sunday disabled
    public WebElement Button_SundayDisabled() { return findElement("//div[@class=\"left\"]/div[@class=\"item excluded\"]",LocatorType.XPath);}

    //Partner portal disclaimer
    public WebElement Text_PartnerPortalDisclaimer() { return findElement("//ul[contains(@class,'partner-disclaimer-mge')]", LocatorType.XPath);}

    //Partner portal custom quotes section tile
    public WebElement Text_CustomQuotesHeader() { return findElement("//h5[contains(text(),'Custom Quotes')]", LocatorType.XPath);}

    //Partner portal custom quotes description
    public WebElement Text_CustomQuotesDescription() { return findElement("//div[contains(text(),'For more than 2 pallets')]", LocatorType.XPath);}

    //Partner portal custom quotes link
    public WebElement Link_CustomQuotesForm() { return findElement("//a[contains(text(), 'fill out this form')]", LocatorType.XPath);}

    //Quote request header title
    public WebElement Text_QuoteRequestPageHeader() { return findElement("//h1[contains(text(),'Quote Request')]", LocatorType.XPath);}

    //Quote request header title
    public WebElement Header_AdminPasswordRequired() { return findElement("//h2[contains(text(),'Admin Password Required')]", LocatorType.XPath);}

    public WebElement Section_WhatsNeeded() { return findElement("//body/div/main/div/div/div/form/div[1]/div[1]", LocatorType.XPath);}

    public WebElement Section_CustomQuotes() { return findElement("//body/div/main/div/div/div/form/div[1]/div[2]", LocatorType.XPath);}

    public WebElement Text_DisclaimerMessage(boolean...ignoreException) { return findElement("//body/div/main/div/div/div/form/div/ul/li", LocatorType.XPath,ignoreException);}

}
