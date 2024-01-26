package com.bungii.common.manager;

import com.bungii.common.enums.ResultType;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.ScreenshotUtility;
import com.bungii.common.utilities.ThreadLocalStepDefinitionMatch;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResultManager {
    private static ReportManager reportManager;
    private static String screenShotFolder;
    private static LogUtility logger = new LogUtility(ResultManager.class);
    private String detailsPath;

    public ResultManager(ReportManager reportManager) {
        this.reportManager = reportManager;
        this.detailsPath = reportManager.getTestResultFolderName();

    }

    /**
     * Log step details to Result
     *
     * @param expected   Expected result of step
     * @param actual     Actual result of step
     * @param screenDump take screenshot or not
     */
    public static void log(String expected, String actual, Boolean... screenDump) {
        String name = ThreadLocalStepDefinitionMatch.get();
    //    reportManager.addTestData(getDataMap(name, expected, actual, ResultType.DONE.toString(), screenDump));
        reportManager.addTestData(getDataMap(name, expected, actual, ResultType.DONE.toString(), false));
        logger.trace("LOG| Step : " + name + ", Expected is : " + expected + " and Actual is : " + actual);
    }

    /**
     * Log step details to Result with status pass
     *
     * @param expected   Expected result of step
     * @param actual     Actual result of step
     * @param screenDump take screenshot or not
     */
    public static void pass(String expected, String actual, Boolean... screenDump) {
        String name = ThreadLocalStepDefinitionMatch.get();
       // reportManager.addTestData(getDataMap(name, expected, actual, ResultType.PASSED.toString(), screenDump));

        reportManager.addTestData(getDataMap(name, expected, actual, ResultType.PASSED.toString(), false));
        logger.detail("TEST STEP - PASS| Actual Result : " + actual);
    }

    /**
     * Log step details to Result with fail status . Dont stop test
     * <p>
     * Name of step
     *
     * @param expected   Expected result of step
     * @param actual     Actual result of step
     * @param screenDump take screenshot or not
     */
    public static void fail(String expected, String actual, Boolean... screenDump) {
        String name = ThreadLocalStepDefinitionMatch.get();
        reportManager.addTestData(getDataMap(name, expected, actual, ResultType.FAILED.toString(), screenDump));
        logger.error("FAIL| Step : " + name + " Actual is : " + actual);
        reportManager.verificationFailed(getDataMap(name, expected, actual, ResultType.FAILED.toString()));
        Assert.fail("Step : " + name + " Actual is : " + actual); // Added for failure
    }
    /**
     * Log step details to Result with fail status . Dont stop test
     * <p>
     * Name of step
     *
     * @param expected   Expected result of step
     * @param actual     Actual result of step
     * @param screenDump take screenshot or not
     */
    public static void failureStep(String step, String expected, String actual, Boolean... screenDump) {
        String name = step;
        reportManager.addTestData(getDataMap(name, expected, actual, ResultType.WARNING.toString(), screenDump));
        logger.error("FAIL| Step : " + name + ", Expected is : " + expected + " and Actual is : " + actual);
        reportManager.verificationFailed(getDataMap(name, expected, actual, ResultType.WARNING.toString()));
       // Assert.fail("For step : " + name + ", Expected is : " + expected + " and Actual is : " + actual); // Added for failure
    }
    /**
     * Log step details to Result with fail status . Stop test
     *
     * @param expected   Expected result of step
     * @param actual     Actual result of step
     * @param screenDump take screenshot or not
     */
    public static void error(String expected, String actual, Boolean... screenDump) {
        String name = ThreadLocalStepDefinitionMatch.get();

        reportManager.addTestData(getDataMap(name, expected, actual, ResultType.ERROR.toString(), screenDump));
        logger.error("ERROR| Step : " + name + ", Expected is : " + expected + " and Actual is : " + actual);
        reportManager.verificationFailed(getDataMap(name, expected, actual, ResultType.ERROR.toString()));
        Assert.fail("Step : " + name + ", Expected is : " + expected + " and Actual is : " + actual);
    }

    /**
     * Log step details to Result with warning status .
     *
     * @param expected   Expected result of step
     * @param actual     Actual result of step
     * @param screenDump take screenshot or not
     */
    public static void warning(String expected, String actual, Boolean... screenDump) {
        String name = ThreadLocalStepDefinitionMatch.get();
    //    reportManager.addTestData(getDataMap(name, expected, actual, ResultType.WARNING.toString(), screenDump));

        reportManager.addTestData(getDataMap(name, expected, actual, ResultType.WARNING.toString(), false));
        logger.warning("WARNING|Step : " + name + ", Expected is : " + expected + " and Actual is : " + actual);
    }

    /**
     * @param name       Name of step
     * @param expected   Expected result of step
     * @param actual     Actual result of step
     * @param logType    Log type
     * @param screenDump capture screenshot or not
     * @return combine input data and return it as map
     */
    public static Map<String, String> getDataMap(String name, String expected, String actual, String logType,
                                                  Boolean... screenDump) {

        ScreenshotUtility screenshotManager = new ScreenshotUtility();
        Map<String, String> data = new HashMap<String, String>();
        // If screendump flag is true, capture screenshot and put it data map
        try {
            if (screenDump.length > 0) {
                if (screenDump[0] == true)
                    data.put("screenDump", PropertyUtility.getResultConfigProperties("SCREENSHOTS_DIRECTORY") + "/" + screenshotManager.screenshot(reportManager.getTestScreenShotFolderName()));
            }

        } catch (IOException e) {
            logger.error("Error while capturing/copying screenshot" + e.getMessage());
        }


        data.put("name", name);
        data.put("type", logType);
        data.put("expected", expected);
        data.put("actual", actual);
        return data;
    }

    public static String getScreenShotFolder() {
        return reportManager.getTestScreenShotFolderName();
    }

    public static void setStacktrace(String stackTrace)
    {
        int len = stackTrace.length();
        if (len>1500)
            len =1500;
        stackTrace = stackTrace.substring(0,len)+" ...";
        reportManager.addStackTrace(getDataMap("", "", stackTrace,"", false));

    }
}
