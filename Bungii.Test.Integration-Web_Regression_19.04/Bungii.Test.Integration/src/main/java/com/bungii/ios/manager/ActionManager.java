package com.bungii.ios.manager;

import com.bungii.SetupManager;
import com.bungii.common.core.PageBase;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import io.appium.java_client.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.bungii.common.manager.ResultManager.error;
import static io.appium.java_client.touch.offset.PointOption.point;

public class ActionManager {
    private static LogUtility logger = new LogUtility(ActionManager.class);
    private final long DRIVER_WAIT_TIME;

    public ActionManager() {
        DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));

    }
    private String getElementDetails(WebElement element)
    {
        return element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
    }
    public static void waitUntilIsElementExistsAndDisplayed(WebElement element) {
        try {
            IOSDriver<MobileElement> driver = (IOSDriver<MobileElement>) SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until((ExpectedConditions.visibilityOf(element)));
        } catch (Exception Ex) {
            //  Assert.fail("Following element is not displayed : " + element);
        }
    }
    public static void waitUntilIsElementClickable(WebElement element) {
        try {
            IOSDriver<MobileElement> driver = (IOSDriver<MobileElement>) SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until((ExpectedConditions.elementToBeClickable(element)));
        } catch (Exception Ex) {
            //  Assert.fail("Following element is not displayed : " + element);
        }
    }
    /**
     * @param element , locator of field
     * @param text    , Text value that is to be sent
     */
    public void sendKeys(WebElement element, String text) {
        try {
            element.sendKeys(text);
            logger.detail("ACTION | Send  " + text + " in element -> " + getElementDetails(element));
        }
         catch(Exception ex)
        {
            logger.error("ACTION FAILED | Send  " + text + " in element -> " + getElementDetails(element), ExceptionUtils.getStackTrace(ex));
            error("Send  " + text + " in element -> " + getElementDetails(element), "Unable to send " + text + " in element -> " + getElementDetails(element),
                    true);
        }
    }
    /**
     * @param element , locator of field
     * @param text    , Text value that is to be sent
     */
    public void clearSendKeys(WebElement element, String text) {
        try {
            element.clear();
            element.sendKeys(text);
            logger.detail("ACTION | Send  " + text + " in element -> " + getElementDetails(element));
        }
        catch(Exception ex)
        {
            logger.error("ACTION FAILED | Send  " + text + " in element -> " + getElementDetails(element), ExceptionUtils.getStackTrace(ex));
            error("Send  " + text + " in element -> " + getElementDetails(element), "Unable to send " + text + " in element -> " + getElementDetails(element),
                    true);
        }
    }
    /**
     * @return boolean value according to alert existence
     */
    public boolean isAlertPresent() {
        try {
            Thread.sleep(1000);
            SetupManager.getDriver().switchTo().alert();
            String alertMessage = SetupManager.getDriver().switchTo().alert().getText();
            logger.detail("VERIFY | Alert is present : " + alertMessage);

            if (alertMessage.contains("no such alert"))
                return false;
            else
                return true;
        } catch (NoAlertPresentException | InterruptedException Ex) {
           // logger.detail("Alert is not present");
            return false;
        } catch (Exception ex) {
            logger.error("ACTION FAILED | Error switching to Alert Dailog due to error : " + ex);
            return false;
        }
    }

    public String getValueAttribute(WebElement element) {
        String value = "";
        try {
            if(element!= null) {
                value = element.getAttribute("value");
                logger.detail("GET | Element -> " + getElementDetails(element) + " value is " + value);
            }

        }
           catch(Exception ex)
        {
            logger.error("ACTION FAILED | Error in getting value for element by locator -> " + getElementDetails(element), ExceptionUtils.getStackTrace(ex));
            error("Get value for element by locator -> " + getElementDetails(element), "Unable to get value for element by locator -> " + getElementDetails(element),
                    true);
        }
        return value;
    }

    public String getNameAttribute(WebElement element) {
        String value = "";
        try {
            value = element.getAttribute("name");

        logger.detail("GET | Element -> " + getElementDetails(element) + " name is " + value);
        }
        catch(Exception ex)
        {
            logger.error("ACTION FAILED | Error in getting name for element by locator -> " + getElementDetails(element), ExceptionUtils.getStackTrace(ex));
            error("Get name for element by locator -> " + getElementDetails(element), "Unable to get name for element by locator -> " + getElementDetails(element),
                    true);
        }
        return value;

    }
    public String getScreenHeader(WebElement element) {
        String value = "";
        try {
            value = element.getAttribute("name");

            logger.detail("GET | Screen Header is " + value);
        }
        catch(Exception ex)
        {
           // logger.error("ACTION FAILED | Error in getting Screen Header", ExceptionUtils.getStackTrace(ex));
            //error("Get name for element by locator -> " + getElementDetails(element), "Unable to get name for element by locator -> " + getElementDetails(element),
                  //  true);
        }
        return value;

    }
    public String getAppName(WebElement element) {
        String value = "";
        try {
            value = element.getAttribute("name");

            logger.detail("GET | App Name is " + value);
        }
        catch(Exception ex)
        {
            // logger.error("ACTION FAILED | Error in getting Screen Header", ExceptionUtils.getStackTrace(ex));
            //error("Get name for element by locator -> " + getElementDetails(element), "Unable to get name for element by locator -> " + getElementDetails(element),
            //  true);
        }
        return value;

    }
    public void click(WebElement element) {
        try{
        element.click();
        logger.detail("ACTION | Click on element by locator -> " + getElementDetails(element));

    }
    catch(ElementClickInterceptedException ex)
    {
        waitUntilIsElementClickable(element);
        element.click();

    }
         catch(Exception ex)
    {
        logger.error("ACTION FAILED | Error Clicking on element by locator -> " + getElementDetails(element), ExceptionUtils.getStackTrace(ex));
        error("Click on element by locator -> " + getElementDetails(element), "Unable to click on element -> " + getElementDetails(element),
                true);
    }
    }
    public void JavaScriptClick(WebElement element) {
        try{
            JavascriptExecutor executor = (JavascriptExecutor) SetupManager.getDriver();
            executor.executeScript("arguments[0].click();", element);
            logger.detail(" JS Click on element by locator" + getElementDetails(element));

        }  catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to click on element -> " + getElementDetails(element) ,
                    true);
        }
    }

    public void tapByElement(WebElement element) {
        AppiumDriver<WebElement> driver = (AppiumDriver<WebElement>) SetupManager.getDriver();
        int startX = element.getLocation().getX();
        int addition = (int) (element.getSize().height * 0.5);
        int endX = startX + addition;
        int startY = element.getLocation().getY();
        new TouchAction(driver).tap(point(endX, startY)).perform();
        logger.detail("ACTION | Tap element by locator -> " + getElementDetails(element));
    }
    public void doubleTapByElement(WebElement element) {
        AppiumDriver<WebElement> driver = (AppiumDriver<WebElement>) SetupManager.getDriver();
        TouchActions action = new TouchActions(driver);
        action.doubleTap(element);
        action.perform();
        logger.detail("ACTION | Tap element by locator -> " + getElementDetails(element));
    }
    public void clickMiddlePoint(WebElement element) {
        Point elementLocation = element.getLocation();
        Dimension elementSize = element.getSize();

        int leftX = elementLocation.getX();
        int width = elementSize.getWidth();
        int upperY = elementLocation.getY();
        int hight = elementSize.getHeight();
        Point p = new Point(leftX + (width / 2), upperY + (hight / 2));
        click(p);
        logger.detail("ACTION | Click on locator by element -> " + getElementDetails(element) + p);
    }

    public void waitForAlert() {
        try {

            (new WebDriverWait(SetupManager.getDriver(), 60)).until(ExpectedConditions.alertIsPresent());
            logger.detail("WAITED | Alert to be displayed");

        } catch (Exception ex) {
            logger.error("WAITING TIMEOUT | Alert is not displayed");
            error("Alert should be displayed", "Alert is not displayed",
                    true);

        }
    }
    public WebElement getElementByXPath(String Locator) {
        return new PageBase().findElement(Locator, PageBase.LocatorType.XPath);
    }

    public String getText(WebElement element) {
        try {
            Long  DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));
            Thread.sleep(3000);
            String text = element.getText();
            logger.detail("GET | Text value for element -> " + getElementDetails(element)+" is  " + text );

            return text;
        }
        catch(StaleElementReferenceException ex)
        {
            String text = element.getText();
            logger.detail("GET RETRY | Text value for element -> " + getElementDetails(element)+" is  " + text );
            return text;
        }
        catch(Exception ex)
        {
            logger.error("ACTION FAILED | Error performing step : Unable to get text from element -> " + getElementDetails(element) +" | STACK TRACE : "+ ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to get text from element -> " + getElementDetails(element) ,
                    true);
            return null;
        }
    }
    /**
     * Swipe up on current mobile screen IOS SPECIFIC
     */
    public void swipeUP() {
        try {

            JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
            Map<String, Object> params = new HashMap<>();
            params.put("direction", "up");
            js.executeScript("mobile: swipe", params);
            logger.detail("ACTION | Swiping up successfull");

        } catch (Exception ex) {
            logger.error("ACTION FAILED | Error performing step | Error swiping up due to " + ex);

        }
    }

    /**
     * Swipe up on current mobile screen IOS SPECIFIC
     */
    public void swipeDown() {
        try {


            JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
            Map<String, Object> params = new HashMap<>();
            params.put("direction", "down");
            js.executeScript("mobile: swipe", params);
            logger.detail("ACTION | Swiping down successfull");
        } catch (Exception ex) {
            logger.error("ACTION FAILED | Error performing step | Error swiping down due to " + ex);

        }
    }

    /**
     * Swipe up on current mobile screen IOS SPECIFIC
     */
    public void swipeRight(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "right");
        params.put("element", ((RemoteWebElement) element).getId());
        //     params.put("element", ((IOSElement) element).getId());
        js.executeScript("mobile: swipe", params);
        logger.detail("ACTION | Swiping right successfull");
    }

    public String getDeviceTimeZone() {
        String time2 = ((AppiumDriver) SetupManager.getDriver()).getDeviceTime();
        logger.detail("GET | Device Timezone : "+ time2);
        return time2;
    }

    /**
     * Swipe left on current mobile screen IOS SPECIFIC
     */
    public void swipeLeft(WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "left");
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: swipe", params);
        logger.detail("ACTION | Swiping left successfull");

    }

    /**
     * IOS SPECIFIC
     *
     * @param picker      locator for picker element
     * @param wheel       locator for wheel inside picker
     * @param forwordDate No of days from today
     * @param hour        value for hour
     * @param minutes     value for minute
     * @param meridiem    value for meridiem , AM/PM
     */
    public void dateTimePicker(By picker, By wheel, int forwordDate, String hour, String minutes, String meridiem) {
        List<WebElement> Columns = waitForExpectedElement(picker).findElements(wheel);
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> hp = new HashMap<String, Object>();
        hp.put("order", "next");
        hp.put("offset", 0.15);
        hp.put("element", Columns.get(0));
        try{
        for (int row = 0; row < forwordDate; row++)
            js.executeScript("mobile: selectPickerWheelValue", hp);}catch (Exception e){}
        if(!meridiem.equals(""))
            if(Columns.size()==4)
            Columns.get(3).sendKeys(meridiem);

        if(!minutes.equals(""))
            Columns.get(2).sendKeys(minutes);

        if(!hour.equals("")) {
            if(Columns.size()==4)
                Columns.get(1).sendKeys(hour);
            else {
               // String h1= hour+ Integer.toString(12);
                //Columns.get(1).sendKeys(hour+12);
                int num = Integer.parseInt(hour);
                String hrs ="";
                if(num>12) {
                    num = num + 12;
                     hrs = String.valueOf(num);
                }

                if(num==24)
                    hrs="00";


                Columns.get(1).sendKeys(hrs);
            }
        }
        if(!meridiem.equals("")) {
            if(Columns.size()==4) {
                if (!Columns.get(3).getAttribute("value").equals(meridiem))
                    Columns.get(3).sendKeys(meridiem);
                logger.detail("ACTION | Select time from picker : Scheduled time  " + Columns.get(0).getAttribute("value") + " , "
                        + Columns.get(1).getAttribute("value") + ":" + Columns.get(2).getAttribute("value") + " "
                        + Columns.get(3).getAttribute("value"));
            }
            logger.detail("ACTION | Select time from picker : Scheduled time  " + Columns.get(0).getAttribute("value") + " , "
                    + Columns.get(1).getAttribute("value") + ":" + Columns.get(2).getAttribute("value"));
        }
        else{
            logger.detail("ACTION | Select time from picker : Scheduled time  " + Columns.get(0).getAttribute("value") + " , "
                    + Columns.get(1).getAttribute("value") + ":" + Columns.get(2).getAttribute("value"));
        }


    }

    /**
     * Drag from one point to andother IOS SPECIFIC
     *
     * @param startx   X coordinate for initial location of swipe
     * @param starty   Y coordinate for initial location of swipe
     * @param endx     X coordinate for end location of swipe
     * @param endy     Y coordinate for end location of swipe
     * @param duration time duration in which swipe should be performed
     * @param element  Reference element for all the coordinate
     */
    public void dragFromToForDuration(int startx, int starty, int endx, int endy, int duration, WebElement element) {
        logger.detail("ACTION | Slide started for element -> "+ getElementDetails(element));

        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("duration", duration);
        params.put("fromX", startx);
        params.put("fromY", starty);
        params.put("toX", endx);
        params.put("toY", endy);
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: dragFromToForDuration", params);
        logger.detail("ACTION | Slide ended for element -> "+ getElementDetails(element));
    }

    /**
     * Drag from one point to andother IOS SPECIFIC
     *
     * @param startx   X coordinate for initial location of swipe
     * @param starty   Y coordinate for initial location of swipe
     * @param endx     X coordinate for end location of swipe
     * @param endy     Y coordinate for end location of swipe
     * @param duration time duration in which swipe should be performed
     */
    public void dragFromToForDuration(int startx, int starty, int endx, int endy, int duration) {
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("duration", duration);
        params.put("fromX", startx);
        params.put("fromY", starty);
        params.put("toX", endx);
        params.put("toY", endy);
        js.executeScript("mobile: dragFromToForDuration", params);
        logger.detail("ACTION | Dragged From [ "+startx +","+starty+" ] to To [ "+endx +","+endy+" ] coordinates For Duration [ "+duration+" ]");

    }

    /**
     * Hide keyboard from screen
     */
    public void hideKeyboard() {
        try {
            //new method is updated appium versions
            ((AppiumDriver) SetupManager.getDriver()).hideKeyboard();
/*            IOSElement element = (IOSElement) ((AppiumDriver) SetupManager.getDriver())
                    .findElementByClassName("XCUIElementTypeKeyboard");
            Point keyboardPoint = element.getLocation();
            TouchAction touchAction = new TouchAction((AppiumDriver) SetupManager.getDriver());
            PointOption top = point(keyboardPoint.getX() + 2, keyboardPoint.getY() - 2);

            touchAction.tap(top).perform();

            Thread.sleep(200);*/

            logger.detail("ACTION | Hide Keyboard");

        } catch (Exception e) {
            //  e.printStackTrace();
            logger.error(e.getStackTrace());
        }
    }

    /**
     * Hide keyboard from screen
     */
    public void nextFieldKeyboard() {
        try {
            IOSElement element = (IOSElement) ((AppiumDriver) SetupManager.getDriver())
                    .findElementByName("Next:");
            click(element);
            logger.detail("ACTION | Click on Next Field On Keyboard");

        } catch (Exception e) {
            //  e.printStackTrace();
            logger.error("ACTION FAILED | Error in clicking on Next Field On Keyboard "+e.getStackTrace());
        }
    }

    /**
     * Press button from keyboard from screen
     */
    public void pressFieldKeyboard(String keyName) {
        try {
            IOSElement element = (IOSElement) ((AppiumDriver) SetupManager.getDriver())
                    .findElementByName(keyName);
            click(element);
            logger.detail("ACTION | Click on "+keyName + " Field On Keyboard");

        } catch (Exception e) {
            //  e.printStackTrace();
            logger.error("ACTION FAILED | Error in clicking on "+keyName+" Field On Keyboard" + e.getStackTrace());
        }
    }

    public void click(Point p) {
        TouchAction touchAction = new TouchAction((AppiumDriver) SetupManager.getDriver());
        PointOption top = point(p.getX(), p.getY());
        touchAction.tap(top).perform();
        logger.detail("ACTION | Tap on coordinates [ "+p.getX() +","+p.getY()+" ]");

    }

    /**
     * IOS SPECIFIC
     *
     * @param bundleId of application that needs to be activated
     */
    public void switchApplication(String bundleId) {
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();

        HashMap<String, Object> args = new HashMap<>();
        args.put("bundleId", bundleId);
        js.executeScript("mobile: launchApp", args);
        logger.detail("ACTION | Launch App : "+bundleId);

    }

    public void terminateApp(String bundleId) {
        ((IOSDriver) SetupManager.getDriver()).terminateApp(bundleId);
        logger.detail("ACTION | Terminate App : "+bundleId);

    }

    /**
     * Wrapper for wait, clear data and clearSendKeys in Input Text box
     **/
    public void clearEnterText(WebElement element, String inputText) {
        try {
            element.clear();
           // logger.detail("ACTION | Clear element : "+getElementDetails(element));
        }catch (Exception e)
        {

        }
        try {
            element.sendKeys(inputText);
            logger.detail("ACTION | Entered Text " + inputText + " in element ->" + getElementDetails(element) + " after clearing the field");
        }
            catch (Exception e){
             //Retry
            element.clear();
            element.sendKeys(inputText);
            logger.detail("ACTION | Entered Text " + inputText + " in element ->" + getElementDetails(element) + " after clearing the field");
        }


    }

    /**
     * Find the dynamic element wait until its visible
     *
     * @param by Element location found xpath, etc...
     **/
    public WebElement waitForExpectedElement(final By by) {
        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME);
        WebElement element = wait.until(visibilityOfElementLocated(by));
        logger.detail("WAITING | Visibility of element by locator -> " + by );

        return element;
    }

    public boolean waitForExpectedElementToBeDisplayed(final By by) {
        try{
        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME);
        WebElement element = wait.until(visibilityOfElementLocated(by));
        logger.detail("WAITING | Visibility of element by locator -> " + by );
        boolean isdisplayed = element.isDisplayed();
        logger.detail("WAITING | Visibility of element by locator -> " + by +" till its Displayed");
        return isdisplayed;

    } catch (Exception Ex) {
        return false;
    }
    }


    public void hardWaitWithSwipeUp(int minutes) throws InterruptedException {
        for (int i = minutes; i > 0; i--) {
            logger.detail("WAITING | Intentional Waiting With Swipe Up | waiting for " + i + " minutes");
            Thread.sleep(30000);
            swipeDown();
            Thread.sleep(30000);
        }
    }

    /**
     * An expectation for checking that an element is either invisible or not
     * present on the DOM.
     *
     * @param element used to find the element
     */
    public boolean invisibilityOfElementLocated(WebElement element) {

        try {
            return (new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME))
                    .until(ExpectedConditions.invisibilityOf(element));

        } catch (Exception e) {
            return true;
        }
    }

    /**
     * @param by Element location found by xpath etc...
     * @return
     * @throws NoSuchElementException
     */
    private ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException {
        return driver -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
            WebElement element = SetupManager.getDriver().findElement(by);
            return element.isEnabled() ? element : null;
        };
    }

    /**
     * An expectation for checking if the given text is present in the specified
     * elements value attribute.
     */
    public void textToBePresentInElementName(final WebElement element, final String text) {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(SetupManager.getDriver()).withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.attributeToBe(element, "name", text));
            logger.detail("WAITING | For element name to be "+text);
        } catch (Exception e) {
            logger.detail("WAITING FAILED | For element name to be "+text+" has failed");
        }
    }

    /**
     * Show notification
     */
    public void showNotifications() {
        manageNotifications(true);
    }

    /**
     * Hide notification
     */
    public void hideNotifications() {
        manageNotifications(false);
    }

    /**
     * Manage notification
     *
     * @param show
     */
    private void manageNotifications(Boolean show) {
try {
    Dimension screenSize = SetupManager.getDriver().manage().window().getSize();
    int yMargin = 5;
    int xMid = screenSize.width / 2;
    PointOption top = point(xMid, yMargin);
    PointOption bottom = point(xMid, screenSize.height - yMargin);

    TouchAction action = new TouchAction((AppiumDriver) SetupManager.getDriver());
    if (show) {
        action.press(top);
        logger.detail("ACTION | Open notification tray ");
    } else {
        action.press(bottom);
        logger.detail("ACTION | Close notification tray ");
    }
    action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
    if (show) {
        action.moveTo(bottom);
        // logger.detail("ACTION | Open notification tray ");
    } else {
        action.moveTo(top);
        //logger.detail("ACTION | Close notification tray ");
    }
    action.perform();
}
        catch(Exception ex)
    {

    }
    }

    /**
     * Make Sure driver is set to ios/Andriod specific
     *
     * @param key
     * @return
     */
    public boolean verifyImageIsPresent(String key) {
        WebDriverWait driverWait = new WebDriverWait(SetupManager.getDriver(), 4);

        String imagePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),
                PropertyUtility.getImageLocations(key));
        IOSDriver<MobileElement> driver;
        driver = (IOSDriver<MobileElement>) SetupManager.getDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, 0.26);
        driver.setSetting(Setting.FIX_IMAGE_FIND_SCREENSHOT_DIMENSIONS, false);
        try {
            File enroute = new File(imagePath);
            File refImgFile = Paths.get(enroute.toURI()).toFile();

            String base64Enroute = Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
            By enrouteBase64 = MobileBy.image(base64Enroute);
            driverWait.until(ExpectedConditions.presenceOfElementLocated(enrouteBase64));
            logger.detail("Verify | Image is present : "+ key);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> getListOfAlertButton() {
        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME);
        wait.until(ExpectedConditions.alertIsPresent());
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();

        HashMap<String, String> params = new HashMap<>();
        params.put("action", "getButtons");
        List<String> buttons = (List<String>) js.executeScript("mobile: alert", params);
        logger.detail("GET | List of alert button[s]:" + buttons.toString());
        return buttons;
    }

    public boolean clickAlertButton(String label) {
        List<String> buttons = getListOfAlertButton();

        String buttonLabel = "";
        for (String button : buttons) {
            if (button.equalsIgnoreCase(label)) {
                buttonLabel = button;
                break;
            }
        }
        if (buttonLabel.equals("")) {
            logger.detail("ACTION | No Alert button : " + label);
            return false;
        }
        else {
            HashMap<String, String> params = new HashMap<>();
            JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
             SetupManager.getDriver().findElement(By.id(label)).click();
            logger.detail("ACTION | Accept Alert button : "+ label);

            return true;
        }
    }

    /**
     * @return value of alert message
     */
    public String getAlertMessage() {
        if (isAlertPresent()) {
             String alertMessage = SetupManager.getDriver().switchTo().alert().getText();
            logger.detail("ACTION | Alert message displayed is : "+ alertMessage);
            return alertMessage;
        } else {
            return "";
        }
    }

    public Rectangle getLocatorRectangle(WebElement element) {

        // MobileElement element = (MobileElement) waitForExpectedElement(by);
        Point elementLocation = element.getLocation();
        Dimension elementSize = element.getSize();
        int leftX = elementLocation.getX();
        int width = leftX + elementSize.getWidth();
        int upperY = elementLocation.getY();
        int hight = upperY + elementSize.getHeight();
        Rectangle area = new Rectangle(leftX, upperY, width, hight);
        logger.detail("GET | Locator Rectangle for element -> "+ getElementDetails(element));
        return area;

    }

    public boolean isElementPresent(WebElement element) {
        //Set the timeout to something low
        //    AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
        //   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            if(element!= null) {
                boolean isdisplayed = element.isDisplayed();
                logger.detail("GET | Element -> " + getElementDetails(element) + " is displayed : " + isdisplayed);
                return isdisplayed;
            }
            else
                return false;
        } catch (Exception Ex) {
            return false;
        }
    }

    public void selectElementByText(WebElement element, String text)
    {
        try{
            new Select(element).selectByVisibleText(text);
            logger.detail("ACTION | Select "+text+" in element -> " + getElementDetails(element));

        }
        catch(Exception ex)
        {
            logger.error("ACTION FAILED | Error performing step | Select "+text+" in element -> " + getElementDetails(element), ExceptionUtils.getStackTrace(ex));
            error("Select "+text+" in element -> " + getElementDetails(element), "Unable to Select "+text +" in element -> " + getElementDetails(element),
                    true);
        }
    }

    public void clickBy4Points(int Point1,int Point2,int Point3, int Point4) throws InterruptedException {
        try {
            AppiumDriver<WebElement> driver = (AppiumDriver<WebElement>) SetupManager.getDriver();
            TouchAction touchAction = new TouchAction(driver);
            PointOption pointStart = PointOption.point(Point1, Point2);
            PointOption pointEnd = PointOption.point(Point3, Point4);
            touchAction.press(pointStart).moveTo(pointEnd).release().perform();
            Thread.sleep(7000);
        } catch(Exception ex){
            logger.error("ACTION FAILED | Error performing step | Could not click on the desired position by 4 points -> ", ExceptionUtils.getStackTrace(ex));
            error("Click on element based on 4 points -> ", "Unable to click the element based on 4 points-> " ,
                    true);
        }
    }

    public void clickBy2Points(int Point1,int Point2) throws InterruptedException {
        try {
            AppiumDriver<WebElement> driver = (AppiumDriver<WebElement>) SetupManager.getDriver();
            TouchAction touchAction = new TouchAction(driver);
            PointOption pointStart = PointOption.point(Point1, Point2);
            touchAction.press(pointStart).release().perform();
            Thread.sleep(7000);
        }
        catch (Exception ex){
            logger.error("ACTION FAILED | Error performing step | Could not click on the desired position by 2 points -> ", ExceptionUtils.getStackTrace(ex));
            error("Click on element based on 2 points -> ", "Unable to click the element based on 2 points-> " ,
                    true);
        }
    }
    public void deleteAllCookies() {
        try{
            SetupManager.getDriver().manage().deleteAllCookies();
        } catch (Exception ex){
            logger.error("ACTION FAILED | Error performing step | Could not delete all cookies ", ExceptionUtils.getStackTrace(ex));
            error("I should be able to delete all cookies -> ", "Could not delete all cookies-> " ,
                    true);
        }
    }

    public void navigateTo(String url) {
        try{
        SetupManager.getDriver().navigate().to(url);
    } catch (Exception ex){
        logger.error("ACTION FAILED | Error performing step | Could not navigate to "+url, ExceptionUtils.getStackTrace(ex));
        error("I should be able to navigate to "+ url, "Could not navigate to "+url ,
                true);
    }
    }

    public String getCurrentURL() {
        try {
            String s = SetupManager.getDriver().getCurrentUrl();
            return s;
        } catch (Exception ex) {
            error("I should be able to get current URL ", "Could not get current URL", true);
            return "Could not get current URL";
        }
    }


    public void DrawSignature(int xStart,int yStart,int xEnd,int yEnd) throws InterruptedException {
        IOSDriver<MobileElement> driver = (IOSDriver<MobileElement>) SetupManager.getDriver();
        try {
            new TouchAction(driver).press(PointOption.point(xStart, yStart))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1))).moveTo(PointOption.point(xEnd, yEnd)).release().perform();
        } catch (Exception ex) {
            logger.error("ACTION FAILED | Error performing step | Could not draw signature -> ", ExceptionUtils.getStackTrace(ex));
            error("I Should be able to draw signature ", "Unable to draw signature ",
                    true);
        }
    }

    public void clickOnDropdown() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(300);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(300);
        } catch (Exception Ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(Ex));
            error("Step should be successful", "Unable to select from dropdown",
                    true);
        }
    }
    public void clear(WebElement element) {
        try {
            element.clear();
            logger.detail("ACTION | Clear element " + getElementDetails(element));
        }
        catch(Exception ex)
        {
            logger.error("ACTION FAILED | Clear element  " + getElementDetails(element), ExceptionUtils.getStackTrace(ex));
            error("Clear element  " + getElementDetails(element), "Unable to clear element " + getElementDetails(element),
                    true);
        }
    }
}
