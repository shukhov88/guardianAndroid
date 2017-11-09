package by.oxagile.guardian.helpers;

import by.oxagile.guardian.managers.CarerManager;
import by.oxagile.guardian.managers.PatientManager;
import by.oxagile.guardian.managers.Wait;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginHelper extends BaseHelper {

    private static final LoginHelperLocators LOCATORS = new LoginHelperLocators();

    public LoginHelper(PatientManager patientManager) {
        super(patientManager);
    }

    public LoginHelper(CarerManager carerManager) {
        super(carerManager);
    }

    Logger logger = LoggerFactory.getLogger(LoginHelper.class);

    public void acceptPermissions() {
        androidDriver.findElement(LOCATORS.proceedToPermissions).click();
        logger.info("User tapped 'continue' button to proceed to permissions");
        for (int i = 0; i < LOCATORS.permissionsQTY; i++) {
            androidDriver.findElement(LOCATORS.allowPermission).click();
        }
        logger.info("User accepted permissions");
    }

    public void as(String mobilePhone) {
        ((AndroidElement) androidDriver.findElement(LOCATORS.loginFirstNameField)).setValue("1");
        logger.info("User entered first name at login screen");
        ((AndroidElement) androidDriver.findElement(LOCATORS.loginLastNameField)).setValue("1");
        logger.info("User entered last name at login screen");
        ((AndroidElement) androidDriver.findElement(LOCATORS.loginPhoneField)).setValue(mobilePhone);
        logger.info("User entered mobile phone at login screen");
        androidDriver.findElement(LOCATORS.loginNextButton).click();
        logger.info("User tapped 'next' button at login screen");
    }

    public void skipUberSignin() {
        waitForElementPresence(LOCATORS.uberLoginField, Wait.FOR_UBER_PAGE.getValue());
        logger.info("Uber sign in web view opened");
        androidDriver.pressKeyCode(AndroidKeyCode.BACK);
        logger.info("Uber sign in skipped");
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
