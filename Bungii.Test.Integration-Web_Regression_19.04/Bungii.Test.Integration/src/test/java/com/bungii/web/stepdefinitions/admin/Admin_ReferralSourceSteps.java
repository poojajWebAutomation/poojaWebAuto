package com.bungii.web.stepdefinitions.admin;

import com.bungii.SetupManager;
import com.bungii.common.core.DriverBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.ios.stepdefinitions.customer.EstimateSteps;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_PromoCodesPage;
import com.bungii.web.pages.admin.Admin_PromoterPage;
import com.bungii.web.pages.admin.Admin_ReferralSourcePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.eo.Se;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_ReferralSourceSteps extends DriverBase {
    Admin_ReferralSourcePage admin_ReferralSourcePage = new Admin_ReferralSourcePage();
    Admin_PromoterPage admin_PromoterPage = new Admin_PromoterPage();
    List<List<String>> DefaultGridData =  new ArrayList<>();
    ActionManager action = new ActionManager();
    private static LogUtility logger = new LogUtility(Admin_ReferralSourceSteps.class);
    List<WebElement> GridColumn1, GridColumn2, GridColumn3, GridColumn4, GridColumn5;
    String[] array = new String[0];
    String[] array1 = new String[0];
    List<List<String>> GridData =  new ArrayList<>();


    @When("^I click on \"([^\"]*)\" header \"([^\"]*)\" on \"([^\"]*)\" grid$")
    public void i_click_on_something_header_something(String header, String sortOrder , String grid) throws Throwable {
        try{
        int pageno = 2;
        String sort = null;
        Thread.sleep(5000);
    switch (grid) {
        case "Referral Sources":

            switch (header) {
                case "Sources":
                    DefaultGridData = paginateAndGetGridData(5);
                    action.click(admin_ReferralSourcePage.Header_Source());
                    sort = admin_ReferralSourcePage.Header_Source().getAttribute("aria-label");
                    if (sortOrder.equals("Ascending")) {
                        if (!sort.equals("Sources sort asc")) {
                            action.click(admin_ReferralSourcePage.Header_Source());
                        }
                    } else {
                        if (!sort.equals("Sources sort desc")) {
                            action.click(admin_ReferralSourcePage.Header_Source());
                        }
                    }
                    break;
                case "Accounts Created":
                    sort = admin_ReferralSourcePage.Header_AccountsCreated().getAttribute("class");
                    if (sortOrder.equals("Ascending")) {
                        if (!sort.equals("sorting_asc")) {
                            action.click(admin_ReferralSourcePage.Header_AccountsCreated());

                        }
                    } else {
                        if (!sort.equals("sorting_desc")) {
                            action.click(admin_ReferralSourcePage.Header_AccountsCreated());
                        }
                    }
                    break;
                case "Percentage of total(Accounts Created)":
                    sort = admin_ReferralSourcePage.Header_PercentageOfTotalAC().getAttribute("class");
                    if (sortOrder.equals("Ascending")) {
                        if (!sort.equals("sorting_asc")) {
                            action.click(admin_ReferralSourcePage.Header_PercentageOfTotalAC());
                        }
                    } else {
                        if (!sort.equals("sorting_desc")) {
                            action.click(admin_ReferralSourcePage.Header_PercentageOfTotalAC());

                        }
                    }
                    break;
                case "Trips Completed":
                    sort = admin_ReferralSourcePage.Header_TripsCompleted().getAttribute("class");
                    if (sortOrder.equals("Ascending")) {
                        if (!sort.equals("sorting_asc")) {
                            action.click(admin_ReferralSourcePage.Header_TripsCompleted());
                        }
                    } else {
                        if (!sort.equals("sorting_desc")) {
                            action.click(admin_ReferralSourcePage.Header_TripsCompleted());
                        }
                    }
                    break;
                case "Percentage of total(Trips Completed)":
                    sort = admin_ReferralSourcePage.Header_PercentageOfTotalTC().getAttribute("class");
                    if (sortOrder.equals("Ascending")) {
                        if (!sort.equals("sorting_asc")) {
                            action.click(admin_ReferralSourcePage.Header_PercentageOfTotalTC());
                        }
                    } else {
                        if (!sort.equals("sorting_desc")) {
                            action.click(admin_ReferralSourcePage.Header_PercentageOfTotalTC());
                        }
                    }
                    break;
            }
                break;

                case "Promoter":
                    switch (header) {
                        case "Name":
                            DefaultGridData = paginateAndGetGridData(3);
                           sort = admin_PromoterPage.Header_Name().getAttribute("aria-label");
                            if (sortOrder.equals("Ascending")) {
                                if (!sort.contains("ASC")) {
                                    action.click(admin_PromoterPage.Header_Name());
                                    action.click(admin_PromoterPage.Header_Name());
                               }
                            } else {
                               if (!sort.contains("DESC")) {
                                    action.click(admin_PromoterPage.Header_Name());
                               }
                            }

                            break;
                        case "Created":
                            sort = admin_PromoterPage.Header_Created().getAttribute("aria-label");
                            if (sortOrder.equals("Ascending")) {
                                if (!sort.contains("ASC")) {
                                    action.click(admin_PromoterPage.Header_Created());
                                    action.click(admin_PromoterPage.Header_Created());
                                }
                            } else {
                                if (!sort.contains("DESC")) {
                                    action.click(admin_PromoterPage.Header_Created());
                                }
                            }
                            break;
                        case "Code Initials":
                            sort = admin_PromoterPage.Header_CodeInitials().getAttribute("aria-label");
                            if (sortOrder.equals("Ascending")) {
                                if (!sort.contains("ASC")) {
                                    action.click(admin_PromoterPage.Header_CodeInitials());
                                    action.click(admin_PromoterPage.Header_CodeInitials());
                                }
                            } else {
                                if (!sort.contains("DESC")) {
                                    action.click(admin_PromoterPage.Header_CodeInitials());
                                }
                            }

                            break;
                    }
                break;

    }
        log("I click on "+header+" to sort by order "+ sortOrder ,
                "I have clicked on "+header+" to sort by order "+ sortOrder, false);
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @Then("^the \"([^\"]*)\" list should be sorted by \"([^\"]*)\" order of \"([^\"]*)\"$")
    public void the_something_list_should_be_sorted_by_something_order_of_something(String strArg1, String sortOrder, String field) throws Throwable {
      try{
        int pageno = 2;
        List<List<String>> CurrentGridData =  new ArrayList<>();

        switch(strArg1) {
    case "Referral Sources":
        CurrentGridData = paginateAndGetGridData(5);
    switch (field) {
        case "Sources":
            Collections.sort(DefaultGridData.get(0));
            if (sortOrder.equals("Ascending")) {
                testStepAssert.isTrue(DefaultGridData.get(0).equals(CurrentGridData.get(0)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
            } else {
                Collections.reverse(DefaultGridData.get(0));
                testStepAssert.isTrue(DefaultGridData.get(0).equals(CurrentGridData.get(0)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
            }
            break;
        case "Accounts Created":
            Collections.sort(DefaultGridData.get(1), Comparator.comparingInt(Integer::parseInt));
            if (sortOrder.equals("Ascending")) {
                testStepAssert.isTrue(DefaultGridData.get(1).equals(CurrentGridData.get(1)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
            } else {
                Collections.reverse(DefaultGridData.get(1));
                testStepAssert.isTrue(DefaultGridData.get(1).equals(CurrentGridData.get(1)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
            }
            break;
        case "Percentage of total(Accounts Created)":
            Collections.sort(DefaultGridData.get(2), Comparator.comparingDouble(Double::parseDouble));
            if (sortOrder.equals("Ascending")) {
                testStepAssert.isTrue(DefaultGridData.get(2).equals(CurrentGridData.get(2)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
            } else {
                Collections.reverse(DefaultGridData.get(2));
                testStepAssert.isTrue(DefaultGridData.get(2).equals(CurrentGridData.get(2)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
            }
            break;
        case "Trips Completed":
            Collections.sort(DefaultGridData.get(3), Comparator.comparingInt(Integer::parseInt));
            if (sortOrder.equals("Ascending")) {

                testStepAssert.isTrue(DefaultGridData.get(3).equals(CurrentGridData.get(3)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
            } else {
                Collections.reverse(DefaultGridData.get(3));
                testStepAssert.isTrue(DefaultGridData.get(3).equals(CurrentGridData.get(3)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
            }
            break;
        case "Percentage of total(Trips Completed)":
            Collections.sort(DefaultGridData.get(4), Comparator.comparingDouble(Double::parseDouble));
            if (sortOrder.equals("Ascending")) {
                testStepAssert.isTrue(DefaultGridData.get(4).equals(CurrentGridData.get(4)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
            } else {
                Collections.reverse(DefaultGridData.get(4));
                testStepAssert.isTrue(DefaultGridData.get(4).equals(CurrentGridData.get(4)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
            }
            break;
    }
    break;
    case "Partners":
        CurrentGridData = paginateAndGetGridData(3);
        switch (field) {
            case "Name":
                Collections.sort(DefaultGridData.get(0));
                if (sortOrder.equals("Ascending")) {
                  testStepAssert.isTrue(DefaultGridData.get(0).equals(CurrentGridData.get(0)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                } else {
                   Collections.reverse(DefaultGridData.get(0));
                   testStepAssert.isTrue(DefaultGridData.get(0).equals(CurrentGridData.get(0)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                }
                break;
            case "Created":
                ArrayList<String> dateList = new ArrayList<String>();
                DateFormat parser = new SimpleDateFormat("MMM dd, yyyy");
                DateFormat parser2 = new SimpleDateFormat("MMM dd, yyyy");

                for (String dateString : DefaultGridData.get(1)) {
                    try {
                        Date convertedDate = parser.parse(dateString);
                        String date = parser2.format(convertedDate);
                        dateList.add(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

                Collections.sort(dateList, (s1, s2) -> LocalDate.parse(s1, formatter).
                        compareTo(LocalDate.parse(s2, formatter)));
                dateList.replaceAll(String::toUpperCase);
                if (sortOrder.equals("Ascending")) {
                    testStepAssert.isTrue(dateList.equals(CurrentGridData.get(1)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                } else {
                    Collections.reverse(dateList);
                  testStepAssert.isTrue(dateList.equals(CurrentGridData.get(1)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                }
                break;
            case "Code Initials":
                Collections.sort(DefaultGridData.get(2));
                if (sortOrder.equals("Ascending")) {
                    testStepAssert.isTrue(DefaultGridData.get(2).equals(CurrentGridData.get(2)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                } else {
                    Collections.reverse(DefaultGridData.get(2));
                    testStepAssert.isTrue(DefaultGridData.get(2).equals(CurrentGridData.get(2)), field + " should sort by " + sortOrder, field + " is not sorted by " + sortOrder);
                }
                break;
        }
        break;
}
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @Then("^the \"([^\"]*)\" should display accurate value for each Source$")
    public void the_something_should_display_accurate_value_for_each_source(String strArg1) throws Throwable {
        try{
        GridColumn2 = SetupManager.getDriver().findElements(By.xpath("//tr/td[2]"));
        GridColumn3 = SetupManager.getDriver().findElements(By.xpath("//tr/td[3]"));

        DecimalFormat df = new DecimalFormat("0.00");
        List<String> Column2 = new ArrayList<String>();
        List<String> Column3 = new ArrayList<String>();

        List<String> Percentage1 = new ArrayList<String>();
        int PotAC = 0;
        for (WebElement e : GridColumn2) {
            Column2.add(e.getText());
            PotAC = PotAC + Integer.parseInt(e.getText());
        }
        for (WebElement e : GridColumn3) {
            Column3.add(e.getText());
        }
        int i = 0;
        for (WebElement e : GridColumn2) {
            Double Numerator = Double.parseDouble(e.getText());
            int Denominator = PotAC;
            Double value = (Numerator * 100) / Denominator;

            Percentage1.add(df.format(value).toString());
            i++;
        }

        testStepAssert.isTrue(Percentage1.equals(Column3), "Percentage of total of Accounts created should match", "Percentage of total of Accounts created doesn't match");

    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }

    @And("^ the \"([^\"]*)\" should display accurate values for each Source$")
    public void the_something_should_display_accurate_values_for_each_source(String strArg1) throws Throwable {
        try{
        GridColumn4 = SetupManager.getDriver().findElements(By.xpath("//tr/td[4]"));
        GridColumn5 = SetupManager.getDriver().findElements(By.xpath("//tr/td[5]"));
        List<String> Column4 = new ArrayList<String>();
        List<String> Column5 = new ArrayList<String>();
        DecimalFormat df = new DecimalFormat("0.00");
        List<String> Percentage2 = new ArrayList<String>();
        int PotTC = 0, i = 0;

        for (WebElement e : GridColumn4) {
            Column4.add(e.getText());
            PotTC = PotTC + Integer.parseInt(e.getText());
        }
        for (WebElement e : GridColumn5) {
            Column5.add(e.getText());
        }

        i = 0;
        for (WebElement e : GridColumn4) {
            Double Numerator = Double.parseDouble(e.getText());
            int Denominator = PotTC;
            Double value = (Numerator * 100) / Denominator;

            Percentage2.add(df.format(value).toString());
            i++;
        }
        testStepAssert.isTrue(Percentage2.equals(Column5), "Percentage of total of Trips completed should match", "Percentage of total of Trips completed doesn't match");
        } catch(Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step should be successful", "Error performing step,Please check logs for more details",
                    true);
        }
    }

    @When("^I click on \"([^\"]*)\" button with entering \"([^\"]*)\" and \"([^\"]*)\" date$")
    public void i_click_on_something_button_with_entering_something_and_something_date(String strArg1, String strArg2, String strArg3) throws Throwable  {
        try{
                        action.click(admin_ReferralSourcePage.Button_Search());
                        log("I click Search on Referral source page",
                "I have clicked Search on Referral source page", false);
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
      }

    @Then("^the \"([^\"]*)\" message is displayed beside \"([^\"]*)\" field$")
    public void the_something_message_is_displayed_beside_something_field(String message, String field) throws Throwable {
try{
        switch(message) {
            case "From Date is required":
                testStepAssert.isElementTextEquals(admin_ReferralSourcePage.Label_FromDateError(), message, "From date is required should be displayed", "From date is required is displayed", "From date is required is not displayed");
                break;
            case "To Date is required":
            testStepAssert.isElementTextEquals(admin_ReferralSourcePage.Label_ToDateError(), message, message + " should be displayed", message+ " is displayed", message+" is not displayed");
        break;
        }
    } catch(Exception e){
        logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
        error("Step should be successful", "Error performing step,Please check logs for more details",
                true);
    }
    }
    @When("^I enter \"([^\"]*)\" less than the \"([^\"]*)\"$")
    public void i_enter_something_less_than_the_something(String strArg1, String strArg2) throws Throwable {
        try {
            action.sendKeys(admin_ReferralSourcePage.TextBox_FromDate(), "11/11/2019");
            action.sendKeys(admin_ReferralSourcePage.TextBox_ToDate(), "10/11/2015");
            log("I enter From and To date on Referral source page",
                    "I have entered From and To date on Referral source page", true);
        }
        catch (Exception e){
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Delivery should get selected.", "Unable to send from and to date in referral source filter.",
                    true);
        }
    }

    List<List<String>> getGridData( List<List<String>> Grid, int columncount)
    {
        for (int i=1; i<=columncount; i++) {
            try {
                Thread.sleep(2000);
            }
            catch(Exception ex){}
            String columnXPath = String.format("//tr/td[%s]", i);
            List<WebElement> GridColumn = SetupManager.getDriver().findElements(By.xpath(columnXPath));
            ArrayList<String> GridColumnData = new ArrayList<String>();;


                if(Grid.size() < columncount) {
                    for ( WebElement e : GridColumn) {
                        GridColumnData.add(e.getText().toUpperCase());
                    }
                    Grid.add(GridColumnData);
                }
                else
                    for ( WebElement e : GridColumn) {
                        Grid.get(i-1).add(e.getText().toUpperCase());
                    }
        }

        return Grid;
    }

    List<List<String>> paginateAndGetGridData(int column) {

        List<List<String>> GridPageData = new ArrayList<>();
        List<List<String>> Grid = new ArrayList<>();

        int pageno = 2;
        if (SetupManager.getDriver().findElements(By.id(String.valueOf(pageno))).size() != 0) {
            do {
                GridPageData = getGridData(Grid, column);

                try {
                    SetupManager.getDriver().findElement(By.id(String.valueOf(pageno)));
                } catch (Exception e) {
                    while (pageno > 1) {
                        pageno = pageno - 2;
                        action.click(SetupManager.getDriver().findElement(By.id(String.valueOf(pageno))));
                    }

                    break;
                }

                action.click(SetupManager.getDriver().findElement(By.id(String.valueOf(pageno))));
                pageno = pageno + 1;
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                }


        } while (true) ;

    }
    else {

               GridPageData = getGridData(Grid,column);

        }

        return GridPageData;
    }
}
