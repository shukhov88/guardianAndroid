package by.oxagile.guardian.helpers;

import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import by.oxagile.guardian.managers.Wait;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginHelper extends BaseHelper {

    private static final LoginHelperLocators LOCATORS = new LoginHelperLocators();

    public LoginHelper(PatientManager patientManager) {
        super(patientManager);
    }

    public LoginHelper(CarerManager carerManager) {
        super(carerManager);
    }

    public void acceptPermissions() {
        androidDriver.findElement(LOCATORS.proceedToPermissions).click();
        for (int i = 0; i < LOCATORS.permissionsQTY; i++) {
            androidDriver.findElement(LOCATORS.allowPermission).click();
        }
    }

    public void as(String mobilePhone) {

        androidDriver.findElement(LOCATORS.loginField).sendKeys(mobilePhone);
        androidDriver.findElement(LOCATORS.loginButton).click();
    }

    public void skipUberSignin() {
        waitForElementPresence(LOCATORS.uberLoginField, Wait.FOR_UBER_PAGE.getValue());
        androidDriver.pressKeyCode(AndroidKeyCode.BACK);
    }

    public void toAppAs(boolean permissions, String mobile) {
        if (permissions) {
            acceptPermissions();
        }
        as(mobile);
        skipUberSignin();
    }

    //Not ready to use due to SMS code validation added by Uber. Need to implement code extract from SMS:
    /*public void signinToUber(String login, String password) {
        androidDriver.findElement(uberLoginField).sendKeys(login);
        androidDriver.findElementByAccessibilityId(uberNextButton).click();
        androidDriver.findElement(uberPassField).sendKeys(password);
        androidDriver.findElementByAccessibilityId(uberNextButton).click();
        androidDriver.findElement(uberOTP).sendKeys(getUberCode());
        androidDriver.findElementByAccessibilityId(uberVerifyButton).click();
    }

    public String getUberCode() {
        Activity sms = new Activity("appPackage", "appActivity");
        String activity = androidDriver.currentActivity();
        String packge = androidDriver.getCurrentPackage();
        Activity assist = new Activity(packge, activity);
        androidDriver.startActivity(sms);
        // code to fetch the CODE
        androidDriver.startActivity(assist);
        return "";
    }*/
}
