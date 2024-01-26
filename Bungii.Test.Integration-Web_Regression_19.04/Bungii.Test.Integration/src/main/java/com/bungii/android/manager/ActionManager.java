package com.bungii.android.manager;

import com.bungii.SetupManager;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bungii.SetupManager.getDriver;
import static com.bungii.common.manager.ResultManager.error;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;

public class ActionManager {
    private static LogUtility logger = new LogUtility(ActionManager.class);
    private final long DRIVER_WAIT_TIME;
    private static WebDriver driver=null;

    public ActionManager() {
        DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));

    }
    public static void keyBoardEvent(int eventNumber) {
        try {
            String strCmdText;
            strCmdText = "cmd /C adb shell input keyevent " + eventNumber;
            Process myProcess = new ProcessBuilder("CMD.exe", strCmdText).start();
            logger.detail("ACTION | Performed Keyboard Event : " + eventNumber);


        } catch (Exception ex) {
        }
    }

    public void clear(WebElement element) {
        try {
            element.clear();
            logger.detail("ACTION | Clear element by locator -> " + getElementDetails(element));
        }
        catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to clear element -> " + getElementDetails(element),
                    true);
        }
    }

    public static void NavigateBack() {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        driver.navigate().back();
        logger.detail("ACTION | Navigated back");
        }
        catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to navigate back",
                    true);
        }
    }

/*    public static void HideKeyboard() {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            driver.hideKeyboard();
            logger.detail("Hide Keyboard");
        } catch (Exception ex) {
        }
    }*/

    public boolean isElementPresent(WebElement element) {
        //Set the timeout to something low
    //    AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
     //   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            boolean isdisplayed = element.isDisplayed();
            return isdisplayed;
        } catch (Exception Ex) {
            return false;
        }
    }

    public boolean isElementEnabled(WebElement element) {
        //Set the timeout to something low
        //    AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
        //   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {
            boolean isdisplayed = element.isEnabled();
            return isdisplayed;
        } catch (Exception Ex) {
            return false;
        }
    }
    public void waitUntilIsElementExistsAndDisplayed(WebElement element) {
        try {
            //for android element
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            if(element!= null)
            wait.until((ExpectedConditions.visibilityOf(element)));
        } catch (StaleElementReferenceException ex) {

        }
        catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Following element is not displayed -> " + getElementDetails(element),
                    true);
        }
    }

    public void waitUntilElementIsDisplayed_ForWeb(WebElement element) {
        try {
            //for web element
            WebDriver driver = SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, 12);
            wait.until((ExpectedConditions.visibilityOf(element)));
        } catch (Exception ex) {
            Assert.fail("Following element is not displayed : " + getElementDetails(element));
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Following element is not displayed -> " + getElementDetails(element),
                    true);
        }
    }
    public void waitUntilIsElementExistsAndDisplayed(WebElement element, Long waitTime) {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, waitTime);
            wait.until((ExpectedConditions.visibilityOf(element)));
        }
        catch (StaleElementReferenceException ex) {

        }
        catch (Exception ex) {
            Assert.fail("Following element is not displayed : " + element);
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Following element is not displayed : " + getElementDetails(element),
                    true);
        }
    }

/*    public static void HideKeyboard() {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            driver.hideKeyboard();
            logger.detail("Hide Keyboard");
        } catch (Exception ex) {
        }
    }*/

    public void waitUntilAlertDisplayed(WebElement element) {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until((ExpectedConditions.visibilityOfAllElements(element)));
        } catch (Exception ex) {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Alert not received : " + getElementDetails(element),
                    true);
            Assert.fail("Alert not received : " + element);

        }
    }

    public static boolean waitUntilAlertDisplayed(Long delay) {
        boolean isDisplayed = false;
        try { //
            new WebDriverWait(SetupManager.getDriver(), delay)
                    .ignoring(NoAlertPresentException.class)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(@resource-id,'notification_alert_message')]")));

            //      .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@resource-id='com.bungii.driver:id/notification_alert_message']")));
            isDisplayed = true;
        } catch (Exception Ex) {
            isDisplayed = false;
            //   Assert.fail("Alert not received : " );
        }
        return isDisplayed;
    }

    public String getValueAttribute(WebElement element) {
        String value = element.getAttribute("value");
        logger.detail("GET | 'value' attribute for element ->" + getElementDetails(element) + " is " + value);
        return value;
    }

    public String getAttribute(WebElement element, String attribute) {
        String value = element.getAttribute(attribute);
        logger.detail("GET | "+ attribute + " attribute for element -> " + getElementDetails(element) + " is " + value);
        return value;
    }


    /**
     * @param element , locator of field
     * @param text    , Text value that is to be sent
     */
    public void sendKeys(WebElement element, String text) {
        try{
        element.sendKeys(text);
        //AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
        hideKeyboard();
        logger.detail("ACTION | Send  " + text + " in element -> " + getElementDetails(element));
        }
        catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to Send  " + text + " in element -> " + getElementDetails(element),
                    true);
        }
    }

    public void hideKeyboard() {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            driver.hideKeyboard();
        } catch (Exception e) {

        }

    }
/*
    public void scrollToBottom()
    {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        Dimension dimensions = driver.manage().window().getSize();
        Double screenHeightStart = ((Dimension) dimensions).getHeight() * 0.5;
        int scrollstart = screenHeightStart.intValue();

        Double screenHeightEnd = ((Dimension) dimensions).getHeight()  * 0.2;

        int scrollend = screenHeightEnd.intValue();
//
  }*/

    public String getText(WebElement element) {
        String text = element.getText();
        logger.detail("ACTION | Value is  " + text + " for element -> " + getElementDetails(element));

        return text;
    }
    /**
     * An expectation for checking if the given text is present in the specified
     * elements value attribute.
     *
     */
    public void eitherTextToBePresentInElementText(final WebElement element, final String text1,final String text2) {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(SetupManager.getDriver()).withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        try {
            //  wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            wait.until(
                    ExpectedConditions.or(
                            ExpectedConditions.textToBePresentInElement(element,text1),
                            ExpectedConditions.textToBePresentInElement(element,text2)
                    )
            );        } catch (Exception e) {
            logger.detail("GET | Wait failed for : " + text1 + " or "+ text2);
        }
    }
    /**
     * An expectation for checking if the given text is present in the specified
     * elements value attribute.
     *
     */
    public void textToBePresentInElementText(final WebElement element, final String text1) {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(SetupManager.getDriver()).withTimeout(Duration.ofSeconds(120))
                .pollingEvery(Duration.ofMillis(500)).ignoring(NoSuchElementException.class);
        try {
            //  wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            wait.until(
                    ExpectedConditions.or(
                            ExpectedConditions.textToBePresentInElement(element,text1)
                    )
            );        } catch (Exception e) {
            logger.detail("Wait failed");
        }
    }
    public void clearSendKeys(WebElement element, String text) {
        try{
        element.clear();
        element.sendKeys(text);
        hideKeyboard();
        logger.detail("ACTION | Send  " + text + " in element -> " + getElementDetails(element));
    }
        catch(Exception ex)
    {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step should be successful", "Unable to Send  " + text + " in element -> " + getElementDetails(element),
                true);
    }
    }

    public void enterText(WebElement element, String text) {
        try{
            click(element);
            JavascriptExecutor js =(JavascriptExecutor) SetupManager.getDriver();
            Map<String, Object> params = new HashMap<>();
            params.put("text", text);
            params.put("element", ((RemoteWebElement) element).getId());
            js.executeScript("mobile:type", params);
                 logger.detail("ACTION | Send  " + text + " in element [Mobile Type] -> " + getElementDetails(element));

            }
        catch(Exception ex)
                {
                logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
                error("Step should be successful", "Unable to Send  " + text + " in element -> " + getElementDetails(element),
                true);
                }
                }
    /**
     * SendKeys using adb shell
     *
     * @param input
     */
   /* public void sendKeys(String input) {



        AndroidDriver driver = (AndroidDriver) SetupManager.getDriver();
        Map<String, Object> args = new HashMap<>();
        args.put("command", "input");
        args.put("args", Lists.newArrayList("text", input));
        driver.executeScript("mobile: shell", args);
    } */

    /**
     * @return boolean value according to alert existence
     */
    public boolean isAlertPresent() {
        try {
            Thread.sleep(1000);
            SetupManager.getDriver().switchTo().alert();
            logger.detail("GET | Alert is Displayed : "+ SetupManager.getDriver().switchTo().alert().getText());
            return true;
        } catch (NoAlertPresentException | InterruptedException Ex) {
            logger.detail("GET | No Alert is Displayed");
            return false;
        }
    }

    public boolean isNotificationAlertDisplayed() {
        boolean isDisplayed = false;
        try {
            new WebDriverWait(SetupManager.getDriver(), 30)
                    .ignoring(NoAlertPresentException.class)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(@resource-id,'notification_alert_message')]")));
            isDisplayed = true;
        } catch (Exception Ex) {
            isDisplayed = false;
            //   Assert.fail("Alert not received : " );
        }
        return isDisplayed;
    }

    /**
     * @param element ,locator that is to be clicked
     */
    public void click(WebElement element) {
        try{
            logger.detail("ACTION | Click on element by locator -> " + getElementDetails(element)); //logged prior so that snackbar doesnt disappear before viewing
            element.click();
    }
        catch(Exception ex)
    {
        logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
        error("Step should be successful", "Unable to click on  element -> " + getElementDetails(element),
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
    public void tap(WebElement element) {
        try{
            TouchActions action = new TouchActions(SetupManager.getDriver());
            action.singleTap(element);
            action.perform();
            logger.detail("ACTION | Tap on element by locator -> " + getElementDetails(element));
        }
        catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to Tap on  element -> " + getElementDetails(element),
                    true);
        }
    }



    /**
     * @param element ,locator that is to be clicked
     */
    public void longPress(WebElement element) {

        TouchAction action = new TouchAction((AndroidDriver)SetupManager.getDriver()).longPress(longPressOptions().withElement(element(element)).withDuration(Duration.ofMillis(10000))).release().perform();
      //  Thread.sleep(5000);


    }
    public void click(Point p) {
        try {
            TouchAction touchAction = new TouchAction((AndroidDriver<MobileElement>) SetupManager.getDriver());
            PointOption top = PointOption.point(p.getX(), p.getY());
            touchAction.tap(top).perform();
            logger.detail("ACTION | Clicked point at , (" + p.getX() + "," + p.getY() + ")");
        }
          catch(Exception ex)
        {
            logger.error("Error performing step", ExceptionUtils.getStackTrace(ex));
            error("Step should be successful", "Unable to click point at , (" + p.getX() + "," + p.getY() + ")",
                    true);
        }
    }

    public void scrollToBottom() {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

            //if pressX was zero it didn't work for me
            int pressX = driver.manage().window().getSize().width / 2;
            // 4/5 of the screen as the bottom finger-press point
            int bottomY = driver.manage().window().getSize().height * 4 / 5;
            // just non zero point, as it didn't scroll to zero normally
            int topY = driver.manage().window().getSize().height / 8;
            //scroll with TouchAction by itself
            scroll(pressX, bottomY, pressX, topY);
        } catch (Exception e) {
           // logger.detail(ExceptionUtils.getStackTrace(e)+"Not able to scroll to botton"); //Scrolling if fails it should not mark testcase inconclusive
          //  error("Step should be successful", "Unable to scroll to bottom",
              //      true);
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
            return (new WebDriverWait(SetupManager.getDriver(), Long.parseLong(PropertyUtility.getProp("WaitTime"))))
                    .until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            return true;
        }
    }

    private void scroll(int fromX, int fromY, int toX, int toY) {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release().perform();

    }

    public void scrollUntilElementDisplayed(WebElement element) {
        do scrollToBottom();
        while (isElementPresent(element) == false);
    }

    public void scrollToTop() {
        try {
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

            //if pressX was zero it didn't work for me
            int pressX = driver.manage().window().getSize().width / 2;
            // 4/5 of the screen as the bottom finger-press point
            int bottomY = driver.manage().window().getSize().height * 4 / 5;
            // just non zero point, as it didn't scroll to zero normally
            int topY = driver.manage().window().getSize().height / 6;
            //scroll with TouchAction by itself
            scroll(pressX, topY+180, pressX, bottomY);
        } catch (Exception e) {
           // logger.detail("Failed to drap to top");
           // error("Step should be successful", "Failed to scroll to top",
                //    true);
        }
    }

/*    public void swipeLeft(WebElement row) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();


        int xShift = Math.toIntExact(Math.round(row.getSize().width * 0.20));
        int xStart = (row.getSize().width) - xShift;
        int xEnd = xShift;

        TouchAction action = new TouchAction(driver)
                .press(point(xStart, (row.getSize().height / 2)))
                .waitAction(waitOptions(Duration.ofMillis(1000)))
                .moveTo(point(xEnd, (row.getSize().height / 2)))
                .release().perform();
        Thread.sleep(2000);


    }*/

    public void swipeRight(WebElement row) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();


        int xAxisStartPoint = row.getLocation().getX() + row.getSize().getWidth() / 20;
        int xAxisEndPoint = row.getLocation().getX() + row.getSize().getWidth();
        int yAxis = row.getLocation().getY() + row.getRect().getHeight() / 2;
        TouchAction act = new TouchAction(driver);
//pressed x axis & y axis of seekbar and move seekbar till the end
        act.longPress(PointOption.point(xAxisStartPoint, yAxis)).moveTo(PointOption.point(xAxisEndPoint - 1, yAxis)).release().perform();
    }

    public void swipeLeft(WebElement row) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();


        int xAxisStartPoint = row.getLocation().getX() + row.getSize().getWidth() / 20;
        int xAxisEndPoint = row.getLocation().getX() + row.getSize().getWidth();
        int yAxis = row.getLocation().getY() + row.getRect().getHeight() / 2;
        TouchAction act = new TouchAction(driver);
//pressed x axis & y axis of seekbar and move seekbar till the end
        act.longPress(PointOption.point(xAxisEndPoint - 1, yAxis)).moveTo(PointOption.point(xAxisStartPoint, yAxis)).release().perform();
    }

    public void scrollUp(WebElement row) throws InterruptedException {
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();

        int xShift = Math.toIntExact(Math.round(row.getSize().width * 0.20));
        int xStart = (row.getSize().height) - xShift;
        int xEnd = xShift;

        TouchAction action = new TouchAction(driver)
                .press(point(xStart, (row.getSize().width / 2)))
                .waitAction(waitOptions(Duration.ofMillis(1000)))
                .moveTo(point(xEnd, (row.getSize().width / 2)))
                .release().perform();


        Thread.sleep(2000);
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

        Dimension screenSize = SetupManager.getDriver().manage().window().getSize();
        int yMargin = 5;
        int xMid = screenSize.width / 2;
        PointOption top = PointOption.point(xMid, yMargin);
        PointOption bottom = PointOption.point(xMid, screenSize.height - yMargin);

        TouchAction action = new TouchAction((AppiumDriver) SetupManager.getDriver());
        if (show) {
            action.press(top);
        } else {
            action.press(bottom);
            ((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.HOME));

        }
        action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
        if (show) {
            action.moveTo(bottom);
        } else {
            action.moveTo(top);
            //((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.HOME));
            ///((AndroidDriver) getDriver()).pressKey(new KeyEvent(AndroidKey.BACK)); Back button is not needed so commented out on browserstack
            logger.detail("ACTION | Pressed HOME Button to Remove Push notification tray");


        }
        action.perform();
        //SetupManager.getDriver().getPageSource();
    }

    public void hardWaitWithSwipeUp(int minutes) throws InterruptedException {
        for (int i = minutes; i > 0; i--) {
            logger.detail("Waiting for " + i + " minutes");
            Thread.sleep(30000);
            scrollToTop();
            Thread.sleep(30000);
            scrollToTop();
        }
    }

    public void waitForAlert() {
        (new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.alertIsPresent());
    }

    public void hardWait(int minutes) throws InterruptedException {
        for (int i = minutes; i > 0; i--) {
            logger.detail("Inside Hard wait , wait for " + i + " minutes");
            Thread.sleep(30000);
            //Send some command after 30 sec so that connection wont die
            ((AndroidDriver)SetupManager.getDriver()).getDeviceTime();
            Thread.sleep(30000);
            //Send some command after 30 sec so that connection wont die
            ((AndroidDriver)SetupManager.getDriver()).getDeviceTime();
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
        return area;

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
        logger.detail("Slide started");

        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("duration", duration);
        params.put("fromX", startx);
        params.put("fromY", starty);
        params.put("toX", endx);
        params.put("toY", endy);
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: dragFromToForDuration", params);
        logger.detail("Slide ended");
    }

    public void navigateTo(String url) {
        SetupManager.getDriver().navigate().to(url);
    }

    public String getCurrentURL() {
        String s = SetupManager.getDriver().getCurrentUrl();
        return s;
    }
    private String getElementDetails(WebElement element)
    {
        return element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
    }

    public void selectElementByText(WebElement element, String text)
    {
        try{
            new Select(element).selectByVisibleText(text);
            logger.detail("Select "+text+" in element -> " + getElementDetails(element));

        }
        catch(Exception ex)
        {
            logger.error("Error performing step | Select "+text+" in element -> " + getElementDetails(element), ExceptionUtils.getStackTrace(ex));
            error("Select "+text+" in element -> " + getElementDetails(element), "Unable to Select "+text +" in element -> " + getElementDetails(element),
                    true);
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

        HashMap<String, String> params = new HashMap<>();
        JavascriptExecutor js = (JavascriptExecutor) SetupManager.getDriver();

        if (label.equalsIgnoreCase("ALLOW")){
            js.executeScript("mobile: acceptAlert");
        logger.detail("ACTION | Accept Alert button : " + label);
        return false;
    }
        else if (label.equalsIgnoreCase( "DENY")){
            js.executeScript("mobile: dismissAlert");

            logger.detail("ACTION | Dismiss Alert button : " + label);
            return false;
        }
        else
            return false;

    }

    public void javaScriptScrollDown(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor) SetupManager.getDriver();
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void deleteAllCookies() {
        SetupManager.getDriver().manage().deleteAllCookies();
    }

    public void DrawSignature(int xStart,int yStart,int xEnd,int yEnd) throws InterruptedException {
        try{
        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            new TouchAction(driver).press(PointOption.point(xStart, yStart))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1))).moveTo(PointOption.point(xEnd, yEnd)).release().perform();
    } catch (Exception ex) {
        logger.error("ACTION FAILED | Error performing step | Could not draw signature -> ", ExceptionUtils.getStackTrace(ex));
        error("I Should be able to draw signature ", "Unable to draw signature ",
                true);
    }
    }

    public boolean waitForExpectedElementToBeDisplayed(final By by) {
        try{
        WebDriverWait wait = new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME);
        WebElement element = wait.until(visibilityOfElementLocated(by));
        boolean isdisplayed = element.isDisplayed();
        logger.detail("WAITING | Visibility of element by locator -> " + by +" till its Displayed");
        return isdisplayed;

    } catch (Exception Ex) {
        return false;
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
    public void swipeForApps( WebElement sliderStart, WebElement sliderEnd){
        try{
            AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>) SetupManager.getDriver();
            int startX = sliderStart.getLocation().getX() + (sliderStart.getSize().getWidth() / 2);
            int startY = sliderStart.getLocation().getY() + (sliderStart.getSize().getHeight() / 2);
            int endX = sliderEnd.getLocation().getX() + (sliderEnd.getSize().getWidth() / 2);
            int endY = sliderEnd.getLocation().getY() + (sliderEnd.getSize().getHeight() / 2);
            new TouchAction(driver)
                    .press(point(startX,startY))
                    .waitAction(waitOptions(Duration.ofMillis(1000)))
                    .moveTo(point(endX, endY))
                    .release().perform();
        }
        catch (Exception ex) {
            logger.error("ACTION FAILED | Error performing step | Could not swipe up for apps -> ", ExceptionUtils.getStackTrace(ex));
            error("I Should be able to swipe up for apps ", "Unable to swipe up for apps  ",
                    true);
        }
    }
}
