package com.bungii.runner;

import com.bungii.common.enums.TargetPlatform;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.hooks.CucumberHooks;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

@CucumberOptions(features = "target/test-classes/features/web", monochrome = true, tags = "@sn", plugin = {
        "pretty", "html:target/cucumber-report/single",
        "json:target/cucumber-report/single/cucumber.json",
        "rerun:target/cucumber-report/single/rerun.txt", "com.bungii.common.utilities.CustomFormatter"},
        glue = {"com.bungii.web.stepdefinitions", "com.bungii.api", "com.bungii.hooks"}
)
public class RunAutoSuite extends AbstractTestNGCucumberTests {
    CucumberHooks hooks;
    private static final String INITIAL_FILE_NAME="login";
    /**
     * @param device Device variable from maven
     */
    @Parameters({"test.Device", "test.Platform", "test.Environment", "test.Category","multiple.data.file","remote.adb.ip"})
    public RunAutoSuite(@Optional("device1") String device, @Optional("android") String Platform, @Optional("QA_AUTO") String environment, @Optional("underconst") String category,@Optional("false") String multipleLoginFile,@Optional("") String remoteAdbHost) {
        //Use below statement only in test runner running which are not suppose to run with maven ,
        //In case of maven logFilepath is set in maven set in POM.xml

        System.setProperty("LogFilePath", "Results");

        String ClassName = this.getClass().getSimpleName();
        if (Platform.equalsIgnoreCase("ios") || Platform.equalsIgnoreCase("android")) {
            String[] deviceList = device.split(",");
            //if mutiple devices are pass from maven then get class number and use that device for running that class
            if (deviceList.length > 1) {

                int threadNumber = Integer.parseInt(ClassName.substring(2, 4));
                System.setProperty("DEVICE", deviceList[threadNumber - 1]);
            } else {
                System.setProperty("DEVICE", device);

            }
        }
        if(multipleLoginFile.trim().equalsIgnoreCase("true")){
            // ClassName="Parallel02IT";
            String threadNumber = ClassName.substring(2, 4);
            System.setProperty("LOGIN_FILE",INITIAL_FILE_NAME+"_"+environment.toLowerCase()+"_"+threadNumber);
            System.out.println("LOGIN FILE :"+INITIAL_FILE_NAME+"_"+environment.toLowerCase()+"_"+threadNumber);
        }
        System.setProperty("runner.class", ClassName);
        //this is to update values from config value
        PropertyUtility.environment=environment;
        PropertyUtility.targetPlatform=Platform;
        System.setProperty("remoteAdbHost",remoteAdbHost);
        this.hooks = new CucumberHooks();

    }

    /**
     * @param resultFolder Result Folder variable from maven
     */
    @BeforeSuite
    @Parameters({"NameWithtimestamp", "test.Platform", "test.Environment"})
    public void start(@Optional("") String resultFolder, @Optional("web") String Platform, @Optional("QA") String environment) {
        //vishal[0102]:commented this as logic to update config properties is moved while reading property file in PropertyUtility class
        //TODO: Remove commented block
/*        Properties props = new Properties();
        String propsFileName = "./src/main/resources/UserProperties/config.properties";
        try {
            //first load old one:
            FileInputStream configStream = new FileInputStream(propsFileName);
            props.load(configStream);
            configStream.close();
            //modifies existing or adds new property
            props.setProperty("target.platform", Platform);
            props.setProperty("environment", environment);
            //save modified property file
            FileOutputStream output = new FileOutputStream(propsFileName);
            props.store(output, "");
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
        this.hooks.start(resultFolder);
    }

    /**
     * After suite method
     *
     * @throws IOException
     */
    @AfterSuite
    public void afterSuite() throws IOException {
        try {
            this.hooks.tearDown();
        }
        catch (Exception ex)
        {

        }
    }

}