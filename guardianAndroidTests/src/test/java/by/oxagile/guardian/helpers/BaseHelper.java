package by.oxagile.guardian.helpers;

import by.oxagile.guardian.managers.AssistManager;
import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class BaseHelper {
    protected CarerManager carer;
    protected PatientManager patient;
    protected AndroidDriver androidDriver;
    protected AssistManager assistManager;
    protected WebDriver webDriver;
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
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = webDriver.findElement(locator);
        wait.until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();", element);
    }

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingTest = webDriver.findElement(locator).getAttribute("value");
            if (!text.equals(existingTest)) {
                webDriver.findElement(locator).clear();
                webDriver.findElement(locator).sendKeys(text);
            }
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
        return androidDriver.findElement(locator).getText();
    }

    public void waitForElementPresence(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(androidDriver, seconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void setImplicitlyWait(int seconds) {
        androidDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public boolean isElementPresent(By locator) {
        setImplicitlyWait(10);
        return androidDriver.findElements(locator).size() == 1;
    }
}
