package by.oxagile.guardian.helpers;

import by.oxagile.guardian.managers.AssistManager;
import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.StartsActivity;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;



public class BaseHelper {
    protected CarerManager carer;
    protected PatientManager patient;
    protected AndroidDriver androidDriver;
    protected AssistManager assist;
    protected WebDriver webDriver;

    public BaseHelper(CarerManager carerManager) {
        this.carer = carerManager;
        this.androidDriver = carer.getCarerDriver();
    }

    public BaseHelper(PatientManager patientManager) {
        this.patient = patientManager;
        this.androidDriver = patientManager.getPatientDriver();
    }

    public BaseHelper(AssistManager assist) {
        this.assist = assist;
        this.webDriver = assist.getDriver();
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

    public boolean isElementPresent(By locator) {
        WebDriverWait wait = new WebDriverWait(androidDriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return androidDriver.findElements(locator).size() == 1;
    }

    public String getText(By locator) {
        return androidDriver.findElement(locator).getText();
    }

}
