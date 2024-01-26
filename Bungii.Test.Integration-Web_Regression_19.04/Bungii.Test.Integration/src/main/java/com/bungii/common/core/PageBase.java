package com.bungii.common.core;

import com.bungii.SetupManager;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.ThreadLocalStepDefinitionMatch;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.bungii.common.manager.ResultManager.error;


public class PageBase extends DriverBase {
    private long DRIVER_WAIT_TIME;

    public PageBase() {
        DRIVER_WAIT_TIME = Long.parseLong(PropertyUtility.getProp("WaitTime"));

    }

    public void WaitUntilElementIsDisplayed(By locator) {
        try {
            WebDriver driver = DriverManager.getObject().getDriver();
            WebDriverWait wait = new WebDriverWait(driver, DRIVER_WAIT_TIME);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        } catch (Exception ex) {
            // Assert.Fail("Following element is not displayed : " + locator);
        }
    }

    public void waitForPageLoad() {
        new WebDriverWait(SetupManager.getDriver(), DRIVER_WAIT_TIME).until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public List<WebElement> findElements(String identifier, LocatorType locatorType) {
        WebDriver driver = DriverManager.getObject().getDriver();

        List<WebElement> elements = null;

        switch (locatorType) {
            case Id: {
                WaitUntilElementIsDisplayed(By.id(identifier));
                elements = driver.findElements(By.id(identifier));
                break;
            }
            case Name: {
                WaitUntilElementIsDisplayed(By.name(identifier));
                elements = driver.findElements(By.name(identifier));
                break;
            }
            case ClassName: {
                WaitUntilElementIsDisplayed(By.className(identifier));
                elements = driver.findElements(By.className(identifier));
                break;
            }
            case XPath: {
                WaitUntilElementIsDisplayed(By.xpath(identifier));
                elements = driver.findElements(By.xpath(identifier));
                break;
            }
            case LinkText: {
                WaitUntilElementIsDisplayed(By.linkText(identifier));
                elements = driver.findElements(By.linkText(identifier));
                break;
            }
            case PartialLinkText: {
                WaitUntilElementIsDisplayed(By.partialLinkText(identifier));
                elements = driver.findElements(By.partialLinkText(identifier));
                break;
            }
            case CssSelector: {
                WaitUntilElementIsDisplayed(By.cssSelector(identifier));
                elements = driver.findElements(By.cssSelector(identifier));
                break;
            }
            case TagName: {
                WaitUntilElementIsDisplayed(By.tagName(identifier));
                elements = driver.findElements(By.tagName(identifier));
                break;
            }
            case AccessibilityId: {
                WaitUntilElementIsDisplayed(MobileBy.AccessibilityId(identifier));
                elements = driver.findElements(MobileBy.AccessibilityId(identifier));
                break;
            }
            case Predicate: {
                WaitUntilElementIsDisplayed(MobileBy.iOSNsPredicateString(identifier));
                elements = driver.findElements(MobileBy.iOSNsPredicateString(identifier));
                break;
            }
            case ClassChain: {
                WaitUntilElementIsDisplayed(MobileBy.iOSClassChain(identifier));
                elements = driver.findElements(MobileBy.iOSClassChain(identifier));
                break;
            }
        }
        return elements;
    }

    private void updateWaitTime(boolean... ignoreException) {
        if (ignoreException.length > 0)
            if (ignoreException[0] == true)
                DRIVER_WAIT_TIME = 4L;

    }

    public void updateWaitTime(Long waitTime) {
        DRIVER_WAIT_TIME = waitTime;
    }

    public WebElement findElement(String identifier, LocatorType locatorType, boolean... ignoreException) {
        WebDriver driver = DriverManager.getObject().getDriver();
        updateWaitTime(ignoreException);
        WebElement element = null;
        boolean retry=false;
        do {
            try {
                switch (locatorType) {
                    case Id: {
                        WaitUntilElementIsDisplayed(By.id(identifier));
                        element = driver.findElement(By.id(identifier));
                        break;
                    }
                    case Name: {
                        WaitUntilElementIsDisplayed(By.name(identifier));
                        element = driver.findElement(By.name(identifier));
                        break;
                    }
                    case ClassName: {
                        WaitUntilElementIsDisplayed(By.className(identifier));
                        element = driver.findElement(By.className(identifier));
                        break;
                    }
                    case XPath: {
                        WaitUntilElementIsDisplayed(By.xpath(identifier));
                        element = driver.findElement(By.xpath(identifier));
                        break;
                    }
                    case LinkText: {
                        WaitUntilElementIsDisplayed(By.linkText(identifier));
                        element = driver.findElement(By.linkText(identifier));
                        break;
                    }
                    case PartialLinkText: {
                        WaitUntilElementIsDisplayed(By.partialLinkText(identifier));
                        element = driver.findElement(By.partialLinkText(identifier));
                        break;
                    }
                    case CssSelector: {
                        WaitUntilElementIsDisplayed(By.cssSelector(identifier));
                        element = driver.findElement(By.cssSelector(identifier));
                        break;
                    }
                    case TagName: {
                        WaitUntilElementIsDisplayed(By.tagName(identifier));
                        element = driver.findElement(By.tagName(identifier));
                        break;
                    }
                    case AccessibilityId: {
                        WaitUntilElementIsDisplayed(MobileBy.AccessibilityId(identifier));
                        element = driver.findElement(MobileBy.AccessibilityId(identifier));
                        break;
                    }
                    case Predicate: {
                        WaitUntilElementIsDisplayed(MobileBy.iOSNsPredicateString(identifier));
                        element = driver.findElement(MobileBy.iOSNsPredicateString(identifier));
                        break;
                    }
                    case ClassChain: {
                        WaitUntilElementIsDisplayed(MobileBy.iOSClassChain(identifier));
                        element = driver.findElement(MobileBy.iOSClassChain(identifier));
                        break;
                    }
                }
            }
            catch (StaleElementReferenceException e) {
                //if retry is true mean this is second time in loop .so break
                if(retry)
                {
                    error("Element with [Locator : "+identifier+" ] by type [ "+locatorType+" ] should be displayed", "Element with [Locator : "+identifier+" ] by type [ "+locatorType+" ] is not displayed. Please refer error logs for more details.",
                            true);
                    break;
                }
                retry=true;
            }catch (NoSuchElementException e) {
                if (ignoreException.length > 0) {
                    if (ignoreException[0] == true) {
                        //ignore exception
                        //or to do action
                    } else {
                      //  error("Element with Locator : "+identifier+" by type : "+locatorType+" should be displayed", "Locator "+identifier+" by type "+locatorType+" is not displayed. Please find screenshot for more details.",
                               // true);
                        cucumberContextManager.setScenarioContext("ERROR","Element with [Locator : "+identifier+" ] by type [ "+locatorType+" ] is not displayed. Please refer error logs for more details.");
                        cucumberContextManager.setScenarioContext("STEP",ThreadLocalStepDefinitionMatch.get());
                        throw new NoSuchElementException(identifier);
                    }
                } else {
                  //  error("Element with Locator : "+identifier+" by type : "+locatorType+" should be displayed", "Locator "+identifier+" by type "+locatorType+" is not displayed. Please find screenshot for more details.",
                         //   true);
                    cucumberContextManager.setScenarioContext("ERROR","Element with [Locator : "+identifier+" ] by type [ "+locatorType+" ] is not displayed. Please refer error logs for more details.");
                    cucumberContextManager.setScenarioContext("STEP",ThreadLocalStepDefinitionMatch.get());
                    throw new NoSuchElementException(identifier);
                }
            }  finally {
                updateWaitTime(Long.parseLong(PropertyUtility.getProp("WaitTime")));

            }
        }while(retry);

        return element;
    }

    public void open(String PageUrl) {
        DriverManager.getObject().getDriver().navigate().to(PageUrl);
    }

    public String get() {
        return DriverManager.getObject().getDriver().getCurrentUrl();
    }

    /**
     * An expectation for checking that an element, known to be present on the
     * DOM of a page, is visible. Visibility means that the element is not only
     * displayed but also has a height and width that is greater than 0.
     */

    public WebElement visibilityOf(final WebElement element) {
        return (new WebDriverWait(DriverManager.getObject().getDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementEnabled(final WebElement element) {
        try {
            return element.isEnabled();
        } catch (TimeoutException exception) {
            return false;
        }

    }

    public enum LocatorType {
        Id,
        Name,
        ClassName,
        LinkText,
        PartialLinkText,
        CssSelector,
        TagName,
        XPath, AccessibilityId, Predicate, ClassChain
    }
    public WebElement getTextElement_DriverVerification(String field, boolean status, boolean...ignoreException){
        String var = "glyphicon glyphicon-remove";
        if(status){
            var = "glyphicon glyphicon-ok";
        }
        return findElement("//td[text()='"+field+"']/following-sibling::td[2]/div/button/span[@class='"+var+"']", LocatorType.XPath,ignoreException);
        //return findElement("//td[contains(text(),'"+field+"')]/following-sibling::td[2]/div/button/span[@class='"+var+"']", LocatorType.XPath,ignoreException);
   }

   public WebElement getInputElement_DriverVerification(String field, String var, boolean...ignoreException){
        return findElement("//td[text()='"+field+"']/following-sibling::td[2]/div/input[@name ='"+var+"']", LocatorType.XPath,ignoreException);
   }
}
