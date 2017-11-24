package by.oxagile.guardian.helpers;

import by.oxagile.guardian.managers.AssistManager;
import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import by.oxagile.guardian.managers.Wait;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;


public class BaseHelper {
    protected CarerManager carer;
    protected PatientManager patient;
    protected AndroidDriver androidDriver;
    protected AssistManager assistManager;
    protected WebDriver webDriver;
    Logger debugLogger = LoggerFactory.getLogger(BaseHelper.class);
    Logger logger;

    public BaseHelper(CarerManager carerManager) {
        this.carer = carerManager;
        this.androidDriver = carer.getCarerDriver();
        logger = LoggerFactory.getLogger(CarerManager.class);
    }

    public BaseHelper(PatientManager patientManager) {
        this.patient = patientManager;
        this.androidDriver = patient.getPatientDriver();
        logger = LoggerFactory.getLogger(PatientManager.class);
    }

    public BaseHelper(AssistManager assist) {
        this.assistManager = assist;
        this.webDriver = assistManager.getDriver();
    }

    protected void click(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            WebElement element = webDriver.findElement(locator);
            wait.until(ExpectedConditions.visibilityOf(element));
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();", element);
        } catch (NoSuchElementException e) {
            debugLogger.error("NoSuchElementException. Locator: " + locator, e);
            Assert.fail("Couldn't find element to click: " + locator, e);
        }

    }

    protected void input(By locator, String text) {
        try {
            click(locator);
            if (text != null) {
                String existingTest = webDriver.findElement(locator).getAttribute("value");
                if (!text.equals(existingTest)) {
                    webDriver.findElement(locator).clear();
                    webDriver.findElement(locator).sendKeys(text);
                }
            }
        } catch (NoSuchElementException e) {
            debugLogger.error("NoSuchElementException. Locator: " + locator, e);
            Assert.fail("Couldn't find element to type text to: " + locator, e);
        }
    }

    /*public void scrollToElement(WebElement element) {
        Dimension scrSize = androidDriver.manage().window().getSize();
        int fromY = (int) (scrSize.height * 0.80);
        int toY = (int) (scrSize.height * 0.20);
        int fromX = (int) (scrSize.width * 0.20);
        int toX = (int) (scrSize.width * 0.20);

        new TouchAction(androidDriver).press(fromX, fromY).moveTo(element).release().perform();
    }*/

    public String getText(By locator) {
        String text = "";
        try {
            text = androidDriver.findElement(locator).getText();
        } catch (NoSuchElementException e) {
            debugLogger.error("NoSuchElementException. Locator: " + locator, e);
            Assert.fail("Couldn't find element to get text from: " + locator, e);
        }
        return text;
    }

    public void waitForElementPresence(By locator, int seconds) {
        setImplicitlyWait(0);
        try {
            WebDriverWait wait = new WebDriverWait(androidDriver, seconds);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (NoSuchElementException e) {
            debugLogger.error("NoSuchElementException. Locator: " + locator, e);
            Assert.fail("Waited " + seconds + " sec for element to appear: " + locator, e);
        } finally {
            setImplicitlyWait(5);
        }
    }

    public void setImplicitlyWait(int seconds) {
        androidDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        logger.debug("AppiumDriver implicitly wait has set to " + seconds);
    }

    public boolean isElementPresent(By locator) {
        setImplicitlyWait(Wait.FOR_ELEMENT_PRESENT.getValue());
        return androidDriver.findElements(locator).size() == 1;
    }

    protected void tap(By locator) {
        try {
            androidDriver.findElement(locator).click();
        } catch (NoSuchElementException e) {
            debugLogger.error("NoSuchElementException. Locator: " + locator, e);
            Assert.fail("Couldn't find element to tap: " + locator, e);
        }
    }

    protected void type(By locator, String text) {
        try {
            ((AndroidElement) androidDriver.findElement(locator)).setValue(text);
        } catch (NoSuchElementException e) {
            debugLogger.error("NoSuchElementException. Locator: " + locator, e);
            Assert.fail("Couldn't find element to type text to: " + locator, e);
        }
    }

    protected String getContentDescriptionValue(By locator) {
        String contentDescription = "";
        try {
            contentDescription = androidDriver.findElement(locator).getAttribute("contentDescription");
        } catch (NoSuchElementException e) {
            debugLogger.error("NoSuchElementException. Locator: " + locator, e);
            Assert.fail("Couldn't find element to get attribute from: " + locator, e);
        }
        return contentDescription;
    }
}
