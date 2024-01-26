package com.bungii.web.stepdefinitions.partner;

import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.stepdefinitions.admin.LogInSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_DashboardPage;
import com.bungii.web.pages.admin.Admin_DriversPage;
import com.bungii.web.pages.partner.Partner_DashboardPage;
import com.bungii.web.pages.partner.Partner_DeliveryPage;
import com.bungii.web.pages.partner.Partner_Done;
import com.bungii.web.pages.partnerManagement.PartnerManagement_Email;
import com.bungii.web.pages.partnerManagement.PartnerManagement_LocationPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.openqa.selenium.WebElement;


import java.io.File;
import java.io.FileFilter;

import java.time.*;
import java.time.format.DateTimeFormatter;


import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;


public class Partner_Reports_Steps extends DriverBase {

    private static LogUtility logger = new LogUtility(LogInSteps.class);
    ActionManager action = new ActionManager();
    Partner_Done Page_Partner_Done = new Partner_Done();
    Partner_DeliveryPage Page_Partner_Delivery = new Partner_DeliveryPage();
    Partner_DashboardPage Page_Partner_Dashboard = new Partner_DashboardPage();
    Admin_DriversPage admin_DriverPage=new Admin_DriversPage();
    PartnerManagement_Email Page_PartnerManagement_Email = new PartnerManagement_Email();
    PartnerManagement_LocationPage Page_PartnerManagement_Location = new PartnerManagement_LocationPage();
    Admin_DashboardPage admin_DashboardPage = new Admin_DashboardPage();


    @And("^I store the delivery details$")
    public void i_store_the_delivery_details() throws Throwable {
        try {
      Thread.sleep(1000);
        cucumberContextManager.setScenarioContext("TRACKINGID_DELIVERY_SUMMARY", action.getText(Page_Partner_Dashboard.Text_Summary_TrackingId()));
        Thread.sleep(1000);
        cucumberContextManager.setScenarioContext("PICKUP_DELIVERY_ADDRESS", action.getText(Page_Partner_Dashboard.Text_Summary_PickupAddress()).replace(",", "").replace(" United States", ""));
        Thread.sleep(1000);
        cucumberContextManager.setScenarioContext("DROPOFF_DELIVERY_ADDRESS", action.getText(Page_Partner_Dashboard.Text_Summary_DeliveryAddress()).replace(",", "").replace(" United States", ""));
       log("I should be able to store the delivery details","I could store the delivery details",false);
        }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^The dropdown menu should have an \"([^\"]*)\" icon$")
    public void the_dropdown_menu_should_have_an_something_icon(String strArg1) throws Throwable {
        try {
        WebElement source = Page_Partner_Delivery.Image_dropdown_Setting();
        String hamburgerIconpath = "https://qaauto-brandsmart.gobungii-dev.com/static/media/head-hamburger.deeb6afd.svg";
        Thread.sleep(1000);
        testStepAssert.isEquals(source.getAttribute("src"),hamburgerIconpath,"Hamburger icon path should match","Hamburger icon path matches the expected path","Hamburger icon path doesnt match the expected path");
        testStepAssert.isTrue(source.isDisplayed(),"Hamburger icon should be displayed","Hamburger icon is displayed", "Hamburger icon is not displayed");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }


    @When("^I click on the \"([^\"]*)\" button on the top right side of the page$")
    public void i_click_on_the_something_button_on_the_top_right_side_of_the_page(String strArg1) throws Throwable {
        try {
        Thread.sleep(5000);
        action.click(Page_Partner_Done.Dropdown_Setting());
        log("I should be able to click on the dropdown","I could click on the dropdown",false);
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^I should see \"([^\"]*)\" as an option$")
    public void i_should_see_something_as_an_option(String expectedOption) throws Throwable {
        try {
            boolean optionDisplayed = Page_Partner_Done.Text_Report().isDisplayed();
            testStepAssert.isTrue(optionDisplayed,"option should be displayed", "option is displayed","option is not displayed");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @When("^I click on the \"([^\"]*)\" link$")
    public void i_click_on_the_something_link(String text) throws Throwable {
        try{
        switch (text){
            case "Reports":
                action.click(Page_Partner_Delivery.Link_Report());
                break;
            case "Today":
                action.click(Page_Partner_Delivery.Link_ReportFilter(text));
                break;
            case "Sort City":
                action.waitUntilIsElementExistsAndDisplayed(admin_DriverPage.Link_SortCity(), (long) 5000);
                action.click(admin_DriverPage.Link_SortCity());
                break;
            case "Partner location":
                action.click(Page_PartnerManagement_Email.Link_PartnerLocationSelectFromSideBar());
                break;
            case "Arrow":
                Thread.sleep(3000);
                action.click(Page_PartnerManagement_Location.Link_AccessChildLocation());
                break;
            case "Partners":
                action.click(Page_PartnerManagement_Location.Link_Partners());
                break;
            case "Partner Portal":
            case "First Child Partner":
                action.click(Page_PartnerManagement_Location.Link_FirstChildPartner());
                break;
            case "Clear text":
                action.click(Page_PartnerManagement_Location.Link_ClearText());
                break;
            case "Partner Settings":
                action.click(admin_DashboardPage.Link_PartnerSettings());
                Thread.sleep(6000);
                action.switchToTab(1);
                break;
            case "PARTNERS":
                action.click(admin_DashboardPage.Link_Partners());
                break;
        }
        log("I should be able to click on the" + text +" button","I could click on the" + text +" button",false);
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }

    }

    @Then("^I should see \"([^\"]*)\" message on the popup$")
    public void i_should_see_something_message_on_the_popup(String text) throws Throwable {
        try{
        Thread.sleep(1000);;
        testStepAssert.isTrue(action.isElementPresent(Page_Partner_Delivery.Label_Report_HeaderPopup()),text+" text  should be displayed", text+" text is displayed", text+" text is not displayed");
        Thread.sleep(1000);
        boolean buttonState = Page_Partner_Delivery.Button_GenerateReport().isEnabled();
        testStepAssert.isFalse(buttonState,"Generate report button should be disabled","Generate report button is disabled","Generate report button is not disabled");
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I select the month which is two months ahead of the current month$")
    public void i_select_the_month_which_is_two_months_ahead_of_the_current_month() throws Throwable {
        try {
        action.click(Page_Partner_Delivery.Dropdown_Calender2_Month());
        Thread.sleep(1000);
        int currentMonthIndex  =((Calendar. getInstance(). get(Calendar. MONTH)+1) + 2);
        action.click(Page_Partner_Delivery.Text_MonthOfTheYear(currentMonthIndex));
        log("I should be able to select a month which is two months ahead of the current month","I could select a month which is two months ahead of the current month",false);
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^The dates should be disabled$")
    public void the_dates_should_be_disabled() throws Throwable {
        try {
        Thread.sleep(1000);
        String dateDisabled = Page_Partner_Delivery.Text_DisabledDate().getAttribute("disabled");
        testStepAssert.isTrue(dateDisabled.equals("true"),"Date should be disabled","Date is disabled","Date is not disabled");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @When("^I click on the below filter options i should see the respective date displayed$")
    public void i_click_on_the_below_filter_options_i_should_see_the_respective_date_displayed(DataTable data) throws Throwable {
        try{
        List<Map<String, String>> DataList = data.asMaps();
        int i = 0;
        while (i < DataList.size()) {
            Thread.sleep(1000);
            String filterBy = DataList.get(i).get("Filter DateBy").trim();
            String[] monthAndYear = LocalDate.now().toString().split("-");
            int year = Integer.parseInt( monthAndYear[0]);
            switch (filterBy) {
                case "Today":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    String[] date = LocalDate.now().toString().split("-");
                    String currentDate = date[2];

                    if (currentDate.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("DateOfToday",currentDate.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("DateOfToday",currentDate);
                    }

                    List<WebElement> dateForToday= Page_Partner_Delivery.List_AllDatesOfTheMonth();
                    String uiTodaysDate = dateForToday.get(0).getText();

                   testStepAssert.isEquals(uiTodaysDate, (String) cucumberContextManager.getScenarioContext("DateOfToday"), "Todays date should be" + (String) cucumberContextManager.getScenarioContext("DateOfToday"), "Todays date is " + uiTodaysDate, "The wrong date displayed is " + uiTodaysDate);
                   break;
                case "Yesterday":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    LocalDate today = LocalDate.now();
                    String[] previousdate = (today.minusDays(1)).format(DateTimeFormatter.ISO_DATE).split("-");
                    String yesterdaysDate = previousdate[2];

                    if (yesterdaysDate.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("DateOfYesterday",yesterdaysDate.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("DateOfYesterday",yesterdaysDate);
                    }

                    List<WebElement> dateForYesterday= Page_Partner_Delivery.List_AllDatesOfTheMonth();
                    String uiYesterdayssDate = dateForYesterday.get(0).getText().replace("0","");

                    testStepAssert.isEquals(uiYesterdayssDate, (String) cucumberContextManager.getScenarioContext("DateOfYesterday"), "Yesterdays date should be " + (String) cucumberContextManager.getScenarioContext("DateOfYesterday"), "Yesterdays date is " + uiYesterdayssDate, "The wrong date displayed is " + uiYesterdayssDate);
                    break;
                case "This Week":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    LocalDate todaysDate = LocalDate.now();

                    LocalDate sunday = todaysDate;
                    while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
                        sunday = sunday.minusDays(1);
                    }
                    LocalDate saturday = todaysDate;
                    while (saturday.getDayOfWeek() != DayOfWeek.SATURDAY) {
                        saturday = saturday.plusDays(1);
                    }

                    String[] weekStart = sunday.toString().split("-");
                    String[] weekEnd = saturday.toString().split("-");
                    String startOfThisWeek = weekStart[2] ;
                    String endOfThisWeek = weekEnd[2];


                    if (startOfThisWeek.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("StartOfTheWeekDate",startOfThisWeek.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("StartOfTheWeekDate",startOfThisWeek);
                    }

                    if (endOfThisWeek.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("EndOfTheWeekDate",endOfThisWeek.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("EndOfTheWeekDate",endOfThisWeek);
                    }

                    List<WebElement> allDatesForThisWeek = Page_Partner_Delivery.List_AllDatesOfTheMonth();


                    ArrayList dates = new ArrayList();
                    for (int j = 0; j < allDatesForThisWeek.size(); j++) {
                        dates.add(allDatesForThisWeek.get(j).getText());
                    }

                    testStepAssert.isEquals(dates.get(0).toString(),(String) cucumberContextManager.getScenarioContext("StartOfTheWeekDate"), "Start of this week should be " + (String) cucumberContextManager.getScenarioContext("StartOfTheWeekDate"), "Start of this week is " + (String) cucumberContextManager.getScenarioContext("StartOfTheWeekDate"), "Wrong end date of this  " + dates.get(0).toString());
                    testStepAssert.isEquals(dates.get(1).toString(), (String) cucumberContextManager.getScenarioContext("EndOfTheWeekDate"), "End of this week should be " + (String) cucumberContextManager.getScenarioContext("EndOfTheWeekDate"), "End of this week is " + (String) cucumberContextManager.getScenarioContext("EndOfTheWeekDate"), "Wrong end date of this week " + dates.get(1).toString());
                    break;
                case "Last Week":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    final ZonedDateTime input = ZonedDateTime.now();
                    final ZonedDateTime startOfLastWeek = input.minusWeeks(2).with(DayOfWeek.SUNDAY);
                    final ZonedDateTime endOfLastWeek = startOfLastWeek.plusDays(6);

                    String[] weekStarts = startOfLastWeek.format(DateTimeFormatter.ISO_LOCAL_DATE).split("-");
                    String[] weekEnds= endOfLastWeek.format(DateTimeFormatter.ISO_LOCAL_DATE).split("-");
                    String LastWeekStart = weekStarts[2];
                    String LastWeekEnd = weekEnds[2];

                    if (LastWeekStart.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("StartOfLastWeekDate",LastWeekStart.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("StartOfLastWeekDate",LastWeekStart);
                    }

                    if (LastWeekEnd.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("EndOfLastWeekDate",LastWeekEnd.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("EndOfLastWeekDate",LastWeekEnd);
                    }

                    List<WebElement> allDatesForLastWeek = Page_Partner_Delivery.List_AllDatesOfTheMonth();
                    ArrayList datesForLastWeek = new ArrayList();
                    for (int j = 0; j < allDatesForLastWeek.size(); j++) {
                        datesForLastWeek.add(allDatesForLastWeek.get(j).getText());
                    }

                    testStepAssert.isEquals( datesForLastWeek.get(0).toString(), (String) cucumberContextManager.getScenarioContext("StartOfLastWeekDate"),"Start of last week should be "+ (String) cucumberContextManager.getScenarioContext("StartOfLastWeekDate"),"Start of last week is "+ datesForLastWeek.get(0).toString(),"Wrong end date of last week "+ datesForLastWeek.get(0).toString());
                    testStepAssert.isEquals( datesForLastWeek.get(1).toString(),(String) cucumberContextManager.getScenarioContext("EndOfLastWeekDate"),"End of last week should be "+ (String) cucumberContextManager.getScenarioContext("EndOfLastWeekDate"),"End of last week is "+ datesForLastWeek.get(1).toString(),"Wrong end date of last week "+ datesForLastWeek.get(1).toString());
                    break;
                case "Last 7 Days":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    String[] weekBeforeFirst = LocalDate.now().minusDays(7).toString().split("-");
                    String[] weekBeforeLast= LocalDate.now().toString().split("-");
                    String firstDayofLastSevenDays = weekBeforeFirst[2];
                    String LasttDayofLastSevenDays = weekBeforeLast[2];

                    List<WebElement> lastSevenDays = Page_Partner_Delivery.List_AllDatesOfTheMonth();
                    ArrayList datesForLastSevenDays= new ArrayList();
                    for (int j = 0; j < lastSevenDays.size(); j++) {
                        datesForLastSevenDays.add(lastSevenDays.get(j).getText());
                    }

                    if (firstDayofLastSevenDays.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("DayOneOfSeven",firstDayofLastSevenDays.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("DayOneOfSeven",firstDayofLastSevenDays);
                    }

                    if (LasttDayofLastSevenDays.startsWith("0")) {
                        cucumberContextManager.setScenarioContext("DaySevenOfSeven",LasttDayofLastSevenDays.replace("0",""));
                    }
                    else{
                        cucumberContextManager.setScenarioContext("DaySevenOfSeven",LasttDayofLastSevenDays);
                    }

                    testStepAssert.isEquals( datesForLastSevenDays.get(0).toString(),(String) cucumberContextManager.getScenarioContext("DayOneOfSeven"),"Starting date should be "+ (String) cucumberContextManager.getScenarioContext("DayOneOfSeven"),"Starting date is "+ datesForLastSevenDays.get(0).toString(),"Wrong starting date "+ datesForLastSevenDays.get(0).toString());
                    testStepAssert.isEquals( datesForLastSevenDays.get(1).toString(),(String) cucumberContextManager.getScenarioContext("DaySevenOfSeven"),"Ending date should be "+ (String) cucumberContextManager.getScenarioContext("DaySevenOfSeven"),"Ending date is "+ datesForLastSevenDays.get(1).toString(),"Wrong ending date "+ datesForLastSevenDays.get(1).toString());
                    break;
                case "This Month":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    int month = Integer.parseInt( monthAndYear[1]);
                    YearMonth yearMonth = YearMonth.of( year , month );
                    String[] endOfMonthDate = yearMonth.atEndOfMonth().toString().split("-");
                    String[] StartOfMonthDate = MonthDay.of(month,1).toString().replace("0","").split("-");
                    String CurrentMonthEndDate = endOfMonthDate[2];
                    String CurrentMonthStartDate = StartOfMonthDate[3];

                    List<WebElement> currentMonth = Page_Partner_Delivery.List_AllDatesOfTheMonth();
                    ArrayList datesForCurrentMonth= new ArrayList();
                    for (int j = 0; j < currentMonth.size(); j++) {
                        datesForCurrentMonth.add(currentMonth.get(j).getText());
                    }

                    testStepAssert.isEquals( datesForCurrentMonth.get(0).toString(),CurrentMonthStartDate,"Starting date of the month should be "+ CurrentMonthStartDate,"Starting date of the month is "+ datesForCurrentMonth.get(0).toString(),"Wrong starting date of the month "+ datesForCurrentMonth.get(0).toString());
                    testStepAssert.isEquals( datesForCurrentMonth.get(1).toString(),CurrentMonthEndDate,"Ending date of the month should be  "+ CurrentMonthEndDate,"Ending  date of the month is "+ datesForCurrentMonth.get(1).toString(),"Wrong ending date of the month"+ datesForCurrentMonth.get(1).toString());
                    break;
                case "Last Month":
                    action.click(Page_Partner_Delivery.Link_ReportFilter(filterBy));
                    int previousMonth = Integer.parseInt( monthAndYear[1])-1;
                        if(previousMonth ==0){
                        YearMonth forDecember = YearMonth.of( year , 12 );
                        String[] endOfLastMonthDate = forDecember.atEndOfMonth().toString().split("-");
                        String[] StartOfLastMonthDate = MonthDay.of(previousMonth,1).toString().replace("0","").split("-");
                        String lastMonthEndDate = endOfLastMonthDate[2];
                        String lastMonthStartDate = StartOfLastMonthDate[3];


                        List<WebElement> lastMonth = Page_Partner_Delivery.List_AllDatesOfTheMonth();
                        ArrayList datesForLastMonth= new ArrayList();
                        for (int j = 0; j < lastMonth.size(); j++) {
                            datesForLastMonth.add(lastMonth.get(j).getText());
                        }

                        testStepAssert.isEquals( datesForLastMonth.get(0).toString(),lastMonthStartDate,"Starting date of last month should be "+ lastMonthStartDate,"Starting date of last month is "+ datesForLastMonth.get(0).toString(),"Wrong starting date of last month "+ datesForLastMonth.get(0).toString());
                        testStepAssert.isEquals( datesForLastMonth.get(1).toString(),lastMonthEndDate,"Ending date of last month should be  "+ lastMonthEndDate,"Ending  date of last month is "+ datesForLastMonth.get(1).toString(),"Wrong ending date of last month"+ datesForLastMonth.get(1).toString());
                        break;
                    }
                        else {

                            YearMonth forDecember = YearMonth.of(year, previousMonth);
                            String[] endOfLastMonthDate = forDecember.atEndOfMonth().toString().split("-");
                            String[] StartOfLastMonthDate = MonthDay.of(previousMonth, 1).toString().replace("0","").split("-");
                            String lastMonthEndDate = endOfLastMonthDate[2];
                            String lastMonthStartDate = StartOfLastMonthDate[3];


                            List<WebElement> lastMonth = Page_Partner_Delivery.List_AllDatesOfTheMonth();
                            ArrayList datesForLastMonth = new ArrayList();
                            for (int j = 0; j < lastMonth.size(); j++) {
                                datesForLastMonth.add(lastMonth.get(j).getText());
                            }

                            testStepAssert.isEquals(datesForLastMonth.get(0).toString(), lastMonthStartDate, "Starting date of last month should be " + lastMonthStartDate, "Starting date of last month is " + datesForLastMonth.get(0).toString(), "Wrong starting date of last month " + datesForLastMonth.get(0).toString());
                            testStepAssert.isEquals(datesForLastMonth.get(1).toString(), lastMonthEndDate, "Ending date of last month should be  " + lastMonthEndDate, "Ending  date of last month is " + datesForLastMonth.get(1).toString(), "Wrong ending date of last month" + datesForLastMonth.get(1).toString());
                            break;
                        }
            }

            i++;
        }
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }



    @Then("^The csv file should get downloaded having name \"([^\"]*)\"$")
    public void the_csv_file_should_get_downloaded_having_name_something(String expectedText) throws Throwable {
        try {
        Thread.sleep(7000);
        String home = SystemUtils.getUserHome().getPath();
        File theNewestFile = null;
        File dir = new File(home + "\\Downloads");
        FileFilter fileFilter = new WildcardFileFilter("*.csv");
        File[] files = dir.listFiles(fileFilter);
        logger.detail("Download directory : "+ home + "\\Downloads");
        if(files!=null) {
            logger.detail("Files Length : " + files.length);
            for (int i = 0; i < files.length; i++)
                logger.detail("File : " + files[i].getName());

            if (files.length > 0) {
                Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
                theNewestFile = files[0];
                String fileName =theNewestFile.getName();
                cucumberContextManager.setScenarioContext("CSVFILENAME",fileName);
            }
        }
        String properFileName = (String) cucumberContextManager.getScenarioContext("CSVFILENAME");
        testStepAssert.isEquals(properFileName,expectedText,"File names should match","File names matches","File names dont match");
    }catch (Exception e) {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }

    }

    @And("^The csv file data should match the details of the delivery$")
    public void the_csv_file_data_should_match_the_details_of_the_delivery() throws Throwable {
        try{
            String home = SystemUtils.getUserHome().getPath();
            File theNewestFile = null;
            File dir = new File(home + "\\Downloads");
            FileFilter fileFilter = new WildcardFileFilter("*.csv");
            File[] files = dir.listFiles(fileFilter);
            logger.detail("Download directory : "+ home + "\\Downloads");
            if(files!=null) {
                logger.detail("Files Length : " + files.length);
                for (int i = 0; i < files.length; i++)
                    logger.detail("File : " + files[i].getName());

                if (files.length > 0) {
                    Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
                    theNewestFile = files[0];
                }
            }

        String  trackingId =(String) cucumberContextManager.getScenarioContext("TRACKINGID_DELIVERY_SUMMARY");
        String  pickupAddress = (String) cucumberContextManager.getScenarioContext("PICKUP_DELIVERY_ADDRESS");
        String  dropoffAddress = (String) cucumberContextManager.getScenarioContext("DROPOFF_DELIVERY_ADDRESS");


        List<String[]> fileValue = new ArrayList<>();
        Scanner sc = new Scanner(theNewestFile);
        sc.useDelimiter("\r\n");

            while (sc.hasNext()){
                String value = sc.next();
                String [] dataFromCsvFile =  value.replace("-","").replace("\"" ,"").split(",");
                fileValue.add(dataFromCsvFile);
            }

        sc.close();
        for (int i =1;i<fileValue.size();i++){
            String eachLineOfCsvFile[]= fileValue.get(i);
            String csvFileTrackingId = eachLineOfCsvFile[0];
            String csvFilePickupAdd =eachLineOfCsvFile[8].replace(" United States", "");
            String csvFileDropoffAdd = eachLineOfCsvFile[9].replace(" United States", "");

            if(csvFileTrackingId.contains(trackingId)){
                testStepAssert.isEquals(csvFileTrackingId,trackingId, trackingId +" Tracking Id should be present in csv file",csvFileTrackingId + " Tracking Id is  present in csv file",csvFileTrackingId + " Tracking Id is not present in csv file");

                if (csvFilePickupAdd.contains(pickupAddress)){
                    testStepAssert.isEquals(csvFilePickupAdd,pickupAddress, pickupAddress +" Pickup address should be present in csv file",csvFilePickupAdd + " Pickup address  is  present in csv file",csvFilePickupAdd+ " Pickup address is not present in csv file");

                    if(csvFileDropoffAdd.contains(dropoffAddress)){
                        testStepAssert.isEquals(csvFileDropoffAdd,dropoffAddress, dropoffAddress +" Drop off address should be present in csv file",csvFileDropoffAdd + " Drop off address is  present in csv file",csvFileDropoffAdd + " Drop off address is not present in csv file");
                    }
                }
            }

        }
        boolean fileExists = theNewestFile.exists();
        testStepAssert.isTrue(fileExists,"File should exist","File exist","File doesnt exist");

        boolean deleteFile =  theNewestFile.delete();

        boolean fileDoesntExist = theNewestFile.exists();
        testStepAssert.isFalse(fileDoesntExist,"File should be deleted","File is deleted","File is not deleted");
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^I should see the the message \"([^\"]*)\"$")
    public void i_should_see_the_the_message_something(String expectedText) throws Throwable {
        try {
       String zeroDeliveriesPresent= action.getText(Page_Partner_Delivery.Text_NoDeliveryError());
       testStepAssert.isEquals(zeroDeliveriesPresent,expectedText,"Error message should match","Error messages match","Error messages dont match");
        }catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }



}


