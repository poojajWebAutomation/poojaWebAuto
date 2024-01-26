package com.bungii.web.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.LogUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_LogviewPage;
import com.bungii.web.utilityfunctions.GeneralUtility;
import cucumber.api.java.en.*;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.bungii.common.manager.ResultManager.error;
import static com.bungii.common.manager.ResultManager.log;

public class Admin_LogviewSteps extends DriverBase {

    Admin_LogviewPage admin_logviewPage = new Admin_LogviewPage();
    private static LogUtility logger = new LogUtility(Admin_LogviewSteps.class);
    ActionManager action = new ActionManager();
    GeneralUtility utility = new GeneralUtility();

    @When("^I Select Database \"([^\"]*)\"$")
    public void i_select_database_something(String database) throws Throwable {
        try{
            action.selectElementByText(admin_logviewPage.Dropdown_Database(),database);
            log("I select database "+ database,
                    "I selected database "+ database , false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in selecting database",
                    true);
        }
    }

    @Then("^the \"([^\"]*)\" records from the \"([^\"]*)\" should be displayed$")
    public void the_something_records_from_the_should_be_displayed(String limit, String table) throws Throwable {
        try{
            int lim = Integer.parseInt(limit)+1;
         testStepAssert.isElementDisplayed(admin_logviewPage.findElement(String.format("//i[contains(.,'%s record(s) found')]",limit),PageBase.LocatorType.XPath),limit + " records should be displayed",limit + " records are displayed", limit + " records are not displayed");
            testStepAssert.isTrue((admin_logviewPage.findElements("//table/tbody/tr",PageBase.LocatorType.XPath).size()==lim),limit + " rows should be displayed",limit + " rows are displayed", limit + " rows are not displayed");

        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing result set",
                    true);
        }
    }

    @Then("^the file should be downloaded having \"([^\"]*)\" separated values$")
    public void the_file_should_be_downloaded_having_something_separated_values(String strArg1) throws Throwable {
        try{
            List<WebElement> gridRows =admin_logviewPage.findElements("//table/tbody/tr",PageBase.LocatorType.XPath);

            Thread.sleep(5000);
          //  String home = System.getProperty("user.home");
            String home = SystemUtils.getUserHome().getPath();
            File theNewestFile = null;
            File dir = new File(home + "\\Downloads");
            FileFilter fileFilter = new WildcardFileFilter("*.csv");
            File[] files = dir.listFiles(fileFilter);
            logger.detail("Download directory : "+ home + "\\Downloads");
            if(files!=null) {
                logger.detail("Files Length : " + files.length);
                for (int i = 0; i<files.length;i++)
                logger.detail("File : "+ files[i].getName());

                if (files.length > 0) {
                    Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
                    theNewestFile = files[0];
                }
                Scanner sc = new Scanner(theNewestFile);
                sc.useDelimiter("\r\n");   //sets the delimiter pattern

                int i = 0;
                List<String> gridValue = new ArrayList<>();
                List<String> fileValue = new ArrayList<>();

                for (WebElement row : gridRows) {

                    switch (strArg1) {

                        case "comma":
                            gridValue.add(row.getText().replace(" ", ","));
                            logger.detail("Grid Content : " + row.getText().replace(" ", ",") + "\n");

                            break;
                        case "pipe":
                            gridValue.add(row.getText().replace(" ", "|"));
                            logger.detail("Grid Content : " + row.getText().replace(" ", "|") + "\n");

                            break;
                    }

                }
                while (sc.hasNext())  //returns a boolean value
                {
                    String value = sc.next();
                    System.out.print("File Content : " + value + "\n");  //find and returns the next complete token from this scanner
                    fileValue.add(value);

                }
                sc.close();  //closes the scanner
                testStepAssert.isTrue(fileValue.equals(gridValue), "Download content should match with grid content", "Download content matches with grid content", "Download content doesnt match with grid content");
            }
            else
            {
                testStepAssert.isFail("Files not found in "+ home + "/Downloads");

            }
    } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in viewing downloaded file",
                    true);
        }
    }

    @And("^I enter \"([^\"]*)\" query with limit \"([^\"]*)\"$")
    public void i_enter_query_with_limit_something(String sqlstatement, String limit) throws Throwable {
        try{
            action.sendKeys(admin_logviewPage.Textarea_Query(),sqlstatement +" limit " + limit);
            log("I enter "+sqlstatement+" query with limit "+ limit ,
                    "I entered "+sqlstatement+" query with limit "+ limit  , false);
        } catch (Exception e) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(e));
            error("Step Should be successful", "Error in selecting database",
                    true);
        }
    }
}
